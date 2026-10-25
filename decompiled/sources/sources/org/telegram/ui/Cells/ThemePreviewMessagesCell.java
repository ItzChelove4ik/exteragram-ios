package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatBackgroundDrawable;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.Reactions.ReactionsEffectOverlay;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes6.dex */
public class ThemePreviewMessagesCell extends LinearLayout {
    private Drawable backgroundDrawable;
    private BackgroundGradientDrawable.Disposable backgroundGradientDisposable;
    private final Runnable cancelProgress;
    private ChatMessageCell[] cells;
    public boolean customAnimation;
    public BaseFragment fragment;
    private final Runnable invalidateRunnable;
    private Drawable oldBackgroundDrawable;
    private BackgroundGradientDrawable.Disposable oldBackgroundGradientDisposable;
    private Drawable overrideDrawable;
    private final AnimatedFloat overrideDrawableUpdate;
    private INavigationLayout parentLayout;
    private int progress;
    private Drawable shadowDrawable;
    private final int type;

    /* renamed from: org.telegram.ui.Cells.ThemePreviewMessagesCell$1, reason: invalid class name */
    public class AnonymousClass1 extends ChatMessageCell {
        private final AnimatedColor color1;
        private final AnimatedColor color2;
        private GestureDetector gestureDetector;
        final /* synthetic */ ThemePreviewMessagesCell this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ int val$type;

        /* renamed from: org.telegram.ui.Cells.ThemePreviewMessagesCell$1$1, reason: invalid class name and collision with other inner class name */
        public class C00401 extends GestureDetector.SimpleOnGestureListener {
            final /* synthetic */ AnonymousClass1 this$1;

            /* renamed from: org.telegram.ui.Cells.ThemePreviewMessagesCell$1$1$1, reason: invalid class name and collision with other inner class name */
            public class ViewTreeObserverOnPreDrawListenerC00411 implements ViewTreeObserver.OnPreDrawListener {
                final /* synthetic */ C00401 this$2;

                /* renamed from: org.telegram.ui.Cells.ThemePreviewMessagesCell$1$1$1$1, reason: invalid class name and collision with other inner class name */
                public class C00421 extends AnimatorListenerAdapter {
                    final /* synthetic */ ViewTreeObserverOnPreDrawListenerC00411 this$3;

                    public C00421(ViewTreeObserverOnPreDrawListenerC00411 r1) {
                        this.this$3 = r1;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator r2) {
                        super.onAnimationEnd(r2);
                        this.this$3.this$2.this$1.getTransitionParams().resetAnimation();
                        this.this$3.this$2.this$1.getTransitionParams().animateChange = false;
                        this.this$3.this$2.this$1.getTransitionParams().animateChangeProgress = 1.0f;
                    }
                }

                public static /* synthetic */ void $r8$lambda$xvVbfStdRI7hM4vxEFmyYBFdqz0(ViewTreeObserverOnPreDrawListenerC00411 r0, ValueAnimator r1) {
                    r0.lambda$onPreDraw$0(r1);
                }

                public ViewTreeObserverOnPreDrawListenerC00411(C00401 r1) {
                    this.this$2 = r1;
                }

                private /* synthetic */ void lambda$onPreDraw$0(ValueAnimator r2) {
                    ChatMessageCell.TransitionParams r0 = this.this$2.this$1.getTransitionParams();
                    r0.animateChangeProgress = ((Float) r2.getAnimatedValue()).floatValue();
                    this.this$2.this$1.invalidate();
                }

                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    this.this$2.this$1.getViewTreeObserver().removeOnPreDrawListener(this);
                    this.this$2.this$1.getTransitionParams().resetAnimation();
                    this.this$2.this$1.getTransitionParams().animateChange();
                    this.this$2.this$1.getTransitionParams().animateChange = true;
                    this.this$2.this$1.getTransitionParams().animateChangeProgress = 0.0f;
                    ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                    r0.addUpdateListener(new ThemePreviewMessagesCell$1$1$1$$ExternalSyntheticLambda0(this));
                    r0.addListener(new C00421(this));
                    r0.start();
                    return false;
                }
            }

            public C00401(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent r12) {
                AnonymousClass1 r0 = this.this$1;
                if (r0.val$type == 2) goto L5;
            L12:
                return false;
            L5:
                if (MediaDataController.getInstance(r0.currentAccount).getDoubleTapReaction() == null) goto L12;
                boolean r02 = this.this$1.getMessageObject().selectReaction(ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(MediaDataController.getInstance(this.this$1.currentAccount).getDoubleTapReaction()), false, false);
                AnonymousClass1 r4 = this.this$1;
                r4.setMessageObject(r4.getMessageObject(), null, false, false, false);
                this.this$1.requestLayout();
                ReactionsEffectOverlay.removeCurrent(false);
                if (r02 == false) goto L10;
                ThemePreviewMessagesCell r03 = this.this$1.this$0;
                ReactionsEffectOverlay.show(r03.fragment, null, ThemePreviewMessagesCell.m7105$$Nest$fgetcells(r03)[1], null, r12.getX(), r12.getY(), ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(MediaDataController.getInstance(this.this$1.currentAccount).getDoubleTapReaction()), this.this$1.currentAccount, 0);
                ReactionsEffectOverlay.startAnimation();
            L10:
                this.this$1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserverOnPreDrawListenerC00411(this));
                return true;
            }
        }

        public AnonymousClass1(ThemePreviewMessagesCell r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6, Context r7, int r8) {
            this.this$0 = r1;
            this.val$context = r7;
            this.val$type = r8;
            super(r2, r3, r4, r5, r6);
            this.gestureDetector = new GestureDetector(r7, new C00401(this));
            CubicBezierInterpolator r62 = CubicBezierInterpolator.EASE_OUT;
            this.color1 = new AnimatedColor(this, 0, 180, r62);
            this.color2 = new AnimatedColor(this, 0, 180, r62);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (getMessageObject() != null) goto L5;
        L19:
            this.color1.set(this.avatarDrawable.getColor());
            this.color2.set(this.avatarDrawable.getColor2());
        L21:
            if (getAvatarImage() == null) goto L26;
            if (getAvatarImage().getImageHeight() == 0.0f) goto L26;
            getAvatarImage().setImageCoords(getAvatarImage().getImageX(), (getMeasuredHeight() - getAvatarImage().getImageHeight()) - AndroidUtilities.dp(4.0f), getAvatarImage().getImageWidth(), getAvatarImage().getImageHeight());
            getAvatarImage().setRoundRadius(ExteraConfig.getAvatarCorners(getAvatarImage().getImageHeight(), true));
            getAvatarImage().draw(r6);
        L28:
            super.dispatchDraw(r6);
            return;
        L26:
            if (this.val$type != 2) goto L28;
            invalidate();
            goto L28
        L5:
            if (getMessageObject().overrideLinkColor < 0) goto L19;
            int r0 = getMessageObject().overrideLinkColor;
            if (r0 < 14) goto L17;
            MessagesController r1 = MessagesController.getInstance(UserConfig.selectedAccount);
            MessagesController.PeerColor r2 = null;
            if (r1 == null) goto L11;
            MessagesController.PeerColors r12 = r1.peerColors;
        L12:
            if (r12 == null) goto L14;
            r2 = r12.getColor(r0);
        L14:
            if (r2 == null) goto L16;
            int r02 = r2.getColor1();
            int r13 = getThemedColor(Theme.keys_avatar_background[AvatarDrawable.getPeerColorIndex(r02)]);
            int r03 = getThemedColor(Theme.keys_avatar_background2[AvatarDrawable.getPeerColorIndex(r02)]);
        L18:
            this.avatarDrawable.setColor(this.color1.set(r13), this.color2.set(r03));
            goto L21
        L16:
            long r22 = r0;
            r13 = getThemedColor(Theme.keys_avatar_background[AvatarDrawable.getColorIndex(r22)]);
            r03 = getThemedColor(Theme.keys_avatar_background2[AvatarDrawable.getColorIndex(r22)]);
            goto L18
        L11:
            r12 = null;
            goto L12
        L17:
            long r23 = r0;
            r13 = getThemedColor(Theme.keys_avatar_background[AvatarDrawable.getColorIndex(r23)]);
            r03 = getThemedColor(Theme.keys_avatar_background2[AvatarDrawable.getColorIndex(r23)]);
            goto L18
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (ThemePreviewMessagesCell.m7108$$Nest$mallowLoadingOnTouch(this.this$0) == true) goto L5;
            this.gestureDetector.onTouchEvent(r2);
            return true;
        L5:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Cells.ThemePreviewMessagesCell$2, reason: invalid class name */
    public class AnonymousClass2 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ ThemePreviewMessagesCell this$0;

        public AnonymousClass2(ThemePreviewMessagesCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return ThemePreviewMessagesCell.m7108$$Nest$mallowLoadingOnTouch(this.this$0);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public void didPressInstantButton(ChatMessageCell r2, int r3) {
            if (ThemePreviewMessagesCell.m7108$$Nest$mallowLoadingOnTouch(this.this$0) == false) goto L6;
            ThemePreviewMessagesCell.m7107$$Nest$fputprogress(this.this$0, 2);
            r2.invalidate();
            AndroidUtilities.cancelRunOnUIThread(ThemePreviewMessagesCell.m7104$$Nest$fgetcancelProgress(this.this$0));
            AndroidUtilities.runOnUIThread(ThemePreviewMessagesCell.m7104$$Nest$fgetcancelProgress(this.this$0), 5000);
            return;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public void didPressReplyMessage(ChatMessageCell r1, int r2, float r3, float r4, boolean r5) {
            if (ThemePreviewMessagesCell.m7108$$Nest$mallowLoadingOnTouch(this.this$0) == false) goto L6;
            ThemePreviewMessagesCell.m7107$$Nest$fputprogress(this.this$0, 0);
            r1.invalidate();
            AndroidUtilities.cancelRunOnUIThread(ThemePreviewMessagesCell.m7104$$Nest$fgetcancelProgress(this.this$0));
            AndroidUtilities.runOnUIThread(ThemePreviewMessagesCell.m7104$$Nest$fgetcancelProgress(this.this$0), 5000);
            return;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean isProgressLoading(ChatMessageCell r1, int r2) {
            if (r2 != ThemePreviewMessagesCell.m7106$$Nest$fgetprogress(this.this$0)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public void needOpenWebView(MessageObject r1, String r2, String r3, String r4, String r5, int r6, int r7) {
            if (ThemePreviewMessagesCell.m7108$$Nest$mallowLoadingOnTouch(this.this$0) == false) goto L6;
            ThemePreviewMessagesCell.m7107$$Nest$fputprogress(this.this$0, 2);
            AndroidUtilities.cancelRunOnUIThread(ThemePreviewMessagesCell.m7104$$Nest$fgetcancelProgress(this.this$0));
            AndroidUtilities.runOnUIThread(ThemePreviewMessagesCell.m7104$$Nest$fgetcancelProgress(this.this$0), 5000);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$N1_34XK55FbGevbWlc9Wk5nr4UU(ThemePreviewMessagesCell r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetcancelProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m7104$$Nest$fgetcancelProgress(ThemePreviewMessagesCell r0) {
        return r0.cancelProgress;
    }

    /* renamed from: -$$Nest$fgetcells, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell[] m7105$$Nest$fgetcells(ThemePreviewMessagesCell r0) {
        return r0.cells;
    }

    /* renamed from: -$$Nest$fgetprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7106$$Nest$fgetprogress(ThemePreviewMessagesCell r0) {
        return r0.progress;
    }

    /* renamed from: -$$Nest$fputprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7107$$Nest$fputprogress(ThemePreviewMessagesCell r0, int r1) {
        r0.progress = r1;
    }

    /* renamed from: -$$Nest$mallowLoadingOnTouch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7108$$Nest$mallowLoadingOnTouch(ThemePreviewMessagesCell r0) {
        return r0.allowLoadingOnTouch();
    }

    public ThemePreviewMessagesCell(Context r7, INavigationLayout r8, int r9) {
        this(r7, r8, r9, 0);
    }

    public ThemePreviewMessagesCell(Context r8, INavigationLayout r9, int r10, long r11) {
        this(r8, r9, r10, r11, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"ClickableViewAccessibility"})
    public ThemePreviewMessagesCell(Context r24, INavigationLayout r25, int r26, long r27, Theme.ResourcesProvider r29) {
        super(r24);
        this.invalidateRunnable = new ThemePreviewMessagesCell$$ExternalSyntheticLambda0(this);
        this.cells = new ChatMessageCell[2];
        this.progress = -1;
        this.cancelProgress = new ThemePreviewMessagesCell$$ExternalSyntheticLambda1(this);
        this.overrideDrawableUpdate = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.type = r26;
        int r3 = UserConfig.selectedAccount;
        this.parentLayout = r25;
        setWillNotDraw(false);
        setOrientation(1);
        setPadding(0, AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f));
        Theme.ResourcesProvider r6 = r29;
        this.shadowDrawable = Theme.getThemedDrawableByKey(r24, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow, r6);
        int r0 = (int) (System.currentTimeMillis() / 1000);
        if (r26 != 3) goto L45;
        if (r27 >= 0) goto L7;
        boolean r19 = true;
    L8:
        TLRPC.TL_message r5 = new TLRPC.TL_message();
        if (r19 == false) goto L12;
        int r20 = R.string.ChannelColorPreview;
    L13:
        r5.message = LocaleController.getString(r20);
        TLRPC.TL_messageReplyHeader r15 = new TLRPC.TL_messageReplyHeader();
        r5.reply_to = r15;
        r15.flags |= 1;
        if (r27 != 0) goto L16;
        r15.reply_to_peer_id = new TLRPC.TL_peerUser();
        r5.reply_to.reply_to_peer_id.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
    L17:
        TLRPC.Message r13 = new TLRPC.Message();
        r5.replyMessage = r13;
        r13.media = new TLRPC.TL_messageMediaEmpty();
        TLRPC.Message r132 = r5.replyMessage;
        if (r27 != 0) goto L20;
        r132.from_id = new TLRPC.TL_peerUser();
        r5.replyMessage.from_id.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        r5.replyMessage.peer_id = new TLRPC.TL_peerUser();
        r5.replyMessage.peer_id.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
    L21:
        TLRPC.Message r11 = r5.replyMessage;
        if (r19 == false) goto L24;
        int r12 = R.string.ChannelColorPreviewReply;
    L25:
        r11.message = LocaleController.getString(r12);
        TLRPC.TL_messageMediaWebPage r112 = new TLRPC.TL_messageMediaWebPage();
        r5.media = r112;
        r112.webpage = new TLRPC.TL_webPage();
        TLRPC.WebPage r113 = r5.media.webpage;
        r113.embed_url = "https://telegram.org/";
        r113.flags |= 2;
        r113.site_name = LocaleController.getString(R.string.AppName);
        TLRPC.WebPage r114 = r5.media.webpage;
        r114.flags |= 4;
        if (r19 == false) goto L28;
        int r122 = R.string.ChannelColorPreviewLinkTitle;
    L29:
        r114.title = LocaleController.getString(r122);
        TLRPC.WebPage r115 = r5.media.webpage;
        r115.flags |= 8;
        if (r19 == false) goto L32;
        int r123 = R.string.ChannelColorPreviewLinkDescription;
    L33:
        r115.description = LocaleController.getString(r123);
        r5.date = r0 - 3540;
        r5.dialog_id = 1;
        r5.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
        if (r27 != 0) goto L37;
        TLRPC.TL_peerUser r02 = new TLRPC.TL_peerUser();
        r5.from_id = r02;
        r02.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
    L38:
        r5.id = 1;
        r5.out = false;
        if (r27 != 0) goto L41;
        TLRPC.TL_peerUser r03 = new TLRPC.TL_peerUser();
        r5.peer_id = r03;
        r03.user_id = 0;
    L42:
        MessageObject r04 = new MessageObject(UserConfig.selectedAccount, r5, true, false);
        r04.notime = true;
        r04.forceAvatar = true;
        r04.resetLayout();
        r04.eventId = 1;
        MessageObject r9 = r04;
    L43:
        MessageObject r10 = null;
        int r133 = 0;
    L78:
        int r116 = r133;
    L79:
        ChatMessageCell[] r124 = this.cells;
        if (r116 >= r124.length) goto L95;
        r124[r116] = new AnonymousClass1(this, r24, r3, false, null, r6, r24, r26);
        this.cells[r116].setDelegate(new AnonymousClass2(this));
        ChatMessageCell r05 = this.cells[r116];
        if (r26 == 2) goto L86;
        if (r26 == 4) goto L86;
        boolean r4 = r133;
    L87:
        r05.isChat = r4;
        r05.setFullyDraw(true);
        if (r116 != 0) goto L90;
        MessageObject r18 = r10;
    L91:
        if (r18 == null) goto L94;
        this.cells[r116].setMessageObject(r18, null, false, false, false);
        addView(this.cells[r116], LayoutHelper.createLinear(-1, -2));
    L94:
        r116 = r116 + 1;
        r6 = r29;
        goto L79
    L90:
        r18 = r9;
    L86:
        r4 = 1;
        goto L87
    L95:
        return;
    L41:
        TLRPC.TL_peerChannel r06 = new TLRPC.TL_peerChannel();
        r5.peer_id = r06;
        r06.channel_id = -r27;
        goto L42
    L37:
        TLRPC.TL_peerChannel r07 = new TLRPC.TL_peerChannel();
        r5.from_id = r07;
        r07.channel_id = -r27;
        goto L38
    L32:
        r123 = R.string.UserColorPreviewLinkDescription;
        goto L33
    L28:
        r122 = R.string.UserColorPreviewLinkTitle;
        goto L29
    L24:
        r12 = R.string.UserColorPreviewReply;
        goto L25
    L20:
        r132.from_id = new TLRPC.TL_peerChannel();
        TLRPC.Message r134 = r5.replyMessage;
        long r117 = -r27;
        r134.from_id.channel_id = r117;
        r134.peer_id = new TLRPC.TL_peerChannel();
        r5.replyMessage.peer_id.channel_id = r117;
        goto L21
    L16:
        r15.reply_to_peer_id = new TLRPC.TL_peerChannel();
        r5.reply_to.reply_to_peer_id.channel_id = -r27;
        goto L17
    L12:
        r20 = R.string.UserColorPreview;
        goto L13
    L7:
        r19 = false;
        goto L8
    L45:
        if (r26 != 2) goto L47;
        TLRPC.TL_message r52 = new TLRPC.TL_message();
        r52.message = LocaleController.getString(R.string.DoubleTapPreviewMessage);
        r52.date = r0 - 3540;
        r52.dialog_id = 1;
        r52.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
        TLRPC.TL_peerUser r08 = new TLRPC.TL_peerUser();
        r52.from_id = r08;
        r08.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        r52.id = 1;
        r52.media = new TLRPC.TL_messageMediaEmpty();
        r52.out = false;
        TLRPC.TL_peerUser r42 = new TLRPC.TL_peerUser();
        r52.peer_id = r42;
        r42.user_id = 0;
        MessageObject r43 = new MessageObject(UserConfig.selectedAccount, r52, true, false);
        r43.resetLayout();
        r43.eventId = 1;
        r43.customName = LocaleController.getString(R.string.DoubleTapPreviewSenderName);
        r43.customAvatarDrawable = ContextCompat.getDrawable(r24, R.drawable.dino_pic);
        r43.overrideLinkColor = 5;
        r43.overrideLinkEmoji = 0;
        r9 = r43;
        goto L43
    L47:
        TLRPC.TL_message r53 = new TLRPC.TL_message();
        if (r26 != 0) goto L50;
        r53.message = LocaleController.getString(R.string.FontSizePreviewReply);
    L51:
        int r92 = r53.message.indexOf("👋");
        if (r92 < 0) goto L54;
        TLRPC.TL_messageEntityCustomEmoji r102 = new TLRPC.TL_messageEntityCustomEmoji();
        r102.offset = r92;
        r102.length = 2;
        r102.document_id = 5386654653003864312L;
        r53.entities.add(r102);
    L54:
        int r93 = r0 - 3540;
        r53.date = r93;
        r53.dialog_id = 1;
        r53.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
        TLRPC.TL_peerUser r103 = new TLRPC.TL_peerUser();
        r53.from_id = r103;
        r103.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        r53.id = 1;
        r53.media = new TLRPC.TL_messageMediaEmpty();
        r53.out = true;
        TLRPC.TL_peerUser r118 = new TLRPC.TL_peerUser();
        r53.peer_id = r118;
        r118.user_id = 0;
        MessageObject r119 = new MessageObject(UserConfig.selectedAccount, r53, true, false);
        TLRPC.TL_message r54 = new TLRPC.TL_message();
        if (r26 != 0) goto L57;
        r54.message = LocaleController.getString(R.string.FontSizePreviewLine2);
    L62:
        int r104 = r54.message.indexOf("😎");
        if (r104 < 0) goto L65;
        TLRPC.TL_messageEntityCustomEmoji r125 = new TLRPC.TL_messageEntityCustomEmoji();
        r125.offset = r104;
        r125.length = 2;
        r125.document_id = 5373141891321699086L;
        r54.entities.add(r125);
    L65:
        r54.date = r0 - 2640;
        r54.dialog_id = 1;
        r54.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
        TLRPC.TL_peerUser r09 = new TLRPC.TL_peerUser();
        r54.from_id = r09;
        r09.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        r54.id = 1;
        r54.media = new TLRPC.TL_messageMediaEmpty();
        r54.out = true;
        TLRPC.TL_peerUser r010 = new TLRPC.TL_peerUser();
        r54.peer_id = r010;
        r010.user_id = 0;
        MessageObject r011 = new MessageObject(UserConfig.selectedAccount, r54, true, false);
        r011.resetLayout();
        r011.overrideLinkColor = 5;
        r011.overrideLinkEmoji = 0;
        r011.eventId = 1;
        TLRPC.TL_message r44 = new TLRPC.TL_message();
        if (r26 != 0) goto L68;
        r44.message = LocaleController.getString(R.string.FontSizePreviewLine1);
    L69:
        r44.date = r93;
        r44.dialog_id = 1;
        r44.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED;
        r44.from_id = new TLRPC.TL_peerUser();
        r44.id = 1;
        TLRPC.TL_messageReplyHeader r55 = new TLRPC.TL_messageReplyHeader();
        r44.reply_to = r55;
        r55.flags |= 16;
        r55.reply_to_msg_id = 5;
        r44.media = new TLRPC.TL_messageMediaEmpty();
        r44.out = false;
        TLRPC.TL_peerUser r2 = new TLRPC.TL_peerUser();
        r44.peer_id = r2;
        r2.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        MessageObject r56 = new MessageObject(UserConfig.selectedAccount, r44, true, false);
        if (r26 == 0) goto L73;
        r56.customReplyName = LocaleController.getString(R.string.NewThemePreviewName);
    L73:
        r56.eventId = 1;
        r56.resetLayout();
        r56.replyMessageObject = r119;
        if (r26 != 4) goto L76;
        TLRPC.TL_user r22 = new TLRPC.TL_user();
        String r45 = LocaleController.getString(R.string.GroupThemePreviewSenderName);
        r22.first_name = r45;
        r56.customName = r45;
        r133 = 0;
        r56.customAvatarDrawable = new AvatarDrawable(r22, false);
    L77:
        r9 = r011;
        r10 = r56;
        goto L78
    L76:
        r133 = 0;
        goto L77
    L68:
        r44.message = LocaleController.getString(R.string.NewThemePreviewLine1);
        goto L69
    L57:
        String r105 = LocaleController.getString(R.string.NewThemePreviewLine3);
        StringBuilder r126 = new StringBuilder(r105);
        int r14 = r105.indexOf(42);
        int r106 = r105.lastIndexOf(42);
        if (r14 == (-1)) goto L61;
        if (r106 == (-1)) goto L61;
        r126.replace(r106, r106 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
        r126.replace(r14, r14 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
        TLRPC.TL_messageEntityTextUrl r135 = new TLRPC.TL_messageEntityTextUrl();
        r135.offset = r14;
        r135.length = (r106 - r14) - 1;
        r135.url = "https://telegram.org";
        r54.entities.add(r135);
    L61:
        r54.message = r126.toString();
        goto L62
    L50:
        r53.message = LocaleController.getString(R.string.NewThemePreviewReply);
        goto L51
    }

    private boolean allowLoadingOnTouch() {
        int r1 = this.type;
        if (r1 == 3) goto L8;
        if (r1 == 0) goto L10;
        return false;
    L10:
        return true;
    L8:
        return true;
    }

    private /* synthetic */ void lambda$new$0() {
        this.progress = -1;
        int r0 = 0;
    L3:
        ChatMessageCell[] r1 = this.cells;
        if (r0 >= r1.length) goto L9;
        ChatMessageCell r12 = r1[r0];
        if (r12 == null) goto L8;
        r12.invalidate();
    L8:
        r0 = r0 + 1;
        goto L3
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSetPressed(boolean r1) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        if (this.type == 2) goto L10;
        if (allowLoadingOnTouch() == true) goto L10;
        return false;
    L10:
        return super.dispatchTouchEvent(r3);
    }

    public ChatMessageCell[] getCells() {
        return this.cells;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        int r0 = 0;
    L3:
        ChatMessageCell[] r1 = this.cells;
        if (r0 >= r1.length) goto L6;
        r1[r0].invalidate();
        r0 = r0 + 1;
        goto L3
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable r0 = this.overrideDrawable;
        if ((r0 instanceof ChatBackgroundDrawable) == false) goto L6;
        ((ChatBackgroundDrawable) r0).onAttachedToWindow(this);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BackgroundGradientDrawable.Disposable r0 = this.backgroundGradientDisposable;
        if (r0 == null) goto L5;
        r0.dispose();
        this.backgroundGradientDisposable = null;
    L5:
        BackgroundGradientDrawable.Disposable r02 = this.oldBackgroundGradientDisposable;
        if (r02 == null) goto L8;
        r02.dispose();
        this.oldBackgroundGradientDisposable = null;
    L8:
        Drawable r03 = this.overrideDrawable;
        if ((r03 instanceof ChatBackgroundDrawable) == false) goto L12;
        ((ChatBackgroundDrawable) r03).onDetachedFromWindow(this);
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r14) {
        Drawable r0 = this.overrideDrawable;
        if (r0 != null) goto L7;
        r0 = Theme.getCachedWallpaperNonBlocking();
    L7:
        if (Theme.wallpaperLoadTask == null) goto L10;
        invalidate();
    L10:
        if (r0 == this.backgroundDrawable) goto L23;
        if (r0 == null) goto L23;
        if (Theme.isAnimatingColor() == false) goto L15;
    L20:
        this.oldBackgroundDrawable = this.backgroundDrawable;
        this.oldBackgroundGradientDisposable = this.backgroundGradientDisposable;
    L21:
        this.backgroundDrawable = r0;
        this.overrideDrawableUpdate.set(0.0f, true);
        goto L23
    L15:
        if (this.customAnimation == true) goto L20;
        BackgroundGradientDrawable.Disposable r1 = this.backgroundGradientDisposable;
        if (r1 == null) goto L21;
        r1.dispose();
        this.backgroundGradientDisposable = null;
    L23:
        if (this.customAnimation == false) goto L25;
        float r02 = this.overrideDrawableUpdate.set(1.0f);
    L26:
        int r5 = 0;
    L28:
        if (r5 >= 2) goto L74;
        if (r5 != 0) goto L31;
        Drawable r7 = this.oldBackgroundDrawable;
    L32:
        if (r7 == null) goto L73;
        if (r5 == 1) goto L36;
    L42:
        int r8 = 255;
    L43:
        if (r8 <= 0) goto L73;
        r7.setAlpha(r8);
        if ((r7 instanceof ColorDrawable) == false) goto L48;
    L60:
        r7.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        if ((r7 instanceof BackgroundGradientDrawable) == false) goto L63;
        this.backgroundGradientDisposable = ((BackgroundGradientDrawable) r7).drawExactBoundsSize(r14, this);
    L64:
        if (r5 != 0) goto L73;
        if (this.oldBackgroundDrawable == null) goto L73;
        if (r02 < 1.0f) goto L73;
        BackgroundGradientDrawable.Disposable r6 = this.oldBackgroundGradientDisposable;
        if (r6 == null) goto L72;
        r6.dispose();
        this.oldBackgroundGradientDisposable = null;
    L72:
        this.oldBackgroundDrawable = null;
        invalidate();
        goto L73
    L63:
        r7.draw(r14);
        goto L64
    L48:
        if ((r7 instanceof GradientDrawable) == true) goto L60;
        if ((r7 instanceof MotionBackgroundDrawable) == true) goto L60;
        if ((r7 instanceof BitmapDrawable) == false) goto L59;
        BitmapDrawable r82 = (BitmapDrawable) r7;
        r82.setFilterBitmap(true);
        if (r82.getTileModeX() != Shader.TileMode.REPEAT) goto L57;
        r14.save();
        float r62 = 2.0f / AndroidUtilities.density;
        r14.scale(r62, r62);
        r7.setBounds(0, 0, (int) Math.ceil(getMeasuredWidth() / r62), (int) Math.ceil(getMeasuredHeight() / r62));
    L58:
        r7.draw(r14);
        r14.restore();
        goto L64
    L57:
        int r83 = getMeasuredHeight();
        float r9 = Math.max(getMeasuredWidth() / r7.getIntrinsicWidth(), r83 / r7.getIntrinsicHeight());
        int r10 = (int) Math.ceil(r7.getIntrinsicWidth() * r9);
        int r92 = (int) Math.ceil(r7.getIntrinsicHeight() * r9);
        int r11 = (getMeasuredWidth() - r10) / 2;
        int r84 = (r83 - r92) / 2;
        r14.save();
        r14.clipRect(0, 0, r10, getMeasuredHeight());
        r7.setBounds(r11, r84, r10 + r11, r92 + r84);
        goto L58
    L59:
        StoryEntry.drawBackgroundDrawable(r14, r7, getWidth(), getHeight());
        goto L64
    L36:
        if (this.oldBackgroundDrawable == null) goto L42;
        if (this.parentLayout == null) goto L40;
    L41:
        r8 = (int) (255.0f * r02);
        goto L43
    L40:
        if (this.customAnimation == false) goto L42;
    L73:
        r5 = r5 + 1;
        goto L28
    L31:
        r7 = this.backgroundDrawable;
        goto L32
    L74:
        this.shadowDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.shadowDrawable.draw(r14);
        return;
    L25:
        r02 = this.parentLayout.getThemeAnimationValue();
        goto L26
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (this.type == 2) goto L10;
        if (allowLoadingOnTouch() == true) goto L10;
        return false;
    L10:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (this.type == 2) goto L10;
        if (allowLoadingOnTouch() == true) goto L10;
        return false;
    L10:
        return super.onTouchEvent(r3);
    }

    public void setOverrideBackground(Drawable r1) {
        this.overrideDrawable = r1;
        if (r1 == null) goto L6;
        r1.setCallback(this);
    L6:
        if ((this.overrideDrawable instanceof ChatBackgroundDrawable) == true) goto L8;
    L10:
        invalidate();
        return;
    L8:
        if (isAttachedToWindow() == false) goto L10;
        ((ChatBackgroundDrawable) this.overrideDrawable).onAttachedToWindow(this);
        goto L10
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.overrideDrawable) goto L5;
        return true;
    L5:
        if (r2 != this.oldBackgroundDrawable) goto L7;
        return true;
    L7:
        if (super.verifyDrawable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
