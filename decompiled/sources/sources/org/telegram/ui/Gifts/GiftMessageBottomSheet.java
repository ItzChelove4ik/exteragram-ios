package org.telegram.ui.Gifts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.HintsController;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceWrapped;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ChatInputViewsContainer;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.chat.WallpaperBitmapProvider;
import org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider;
import org.telegram.ui.Components.inset.WindowInsetsStateHolder;
import org.telegram.ui.Stories.recorder.PreviewView;

/* loaded from: classes7.dex */
public class GiftMessageBottomSheet extends BottomSheet {
    private final Drawable backgroundDrawable;
    private final int captionLimit;
    private final AnimatedTextView captionLimitView;
    private final FrameLayout chatInputBubbleContainer;
    private final FrameLayout chatInputInAppContainer;
    private final ChatInputViewsContainer chatInputViewsContainer;
    private final ImageView closeButton;
    private int codepointCount;
    private final ChatActivityEnterView commentView;
    private final TL_stars.TL_starGiftUnique gift;
    private boolean hideMyName;
    private Callback mCallback;
    private boolean mLoading;
    private final BlurredBackgroundDrawableViewFactory navbarContentDrawableFactory;
    private final BlurredBackgroundSourceWrapped navbarContentSourceWallpaper;
    private final TextView previewInChatHeader;
    private final FrameLayout publicCheckboxButton;
    private final CheckBox2 publicCheckboxView;
    private final WindowAnimatedInsetsProvider rootAnimatedInsetsListener;
    private final SizeNotifierFrameLayout sizeNotifierFrameLayout;
    private final StarGiftUniqueActionView starGiftUniqueActionView;
    private final long toDialogId;
    private final WallpaperBitmapProvider wallpaperBitmapProvider;
    private final WindowInsetsStateHolder windowInsetsStateHolder;
    private ChatActivityEnterView.SendButton writeButton;

    /* renamed from: org.telegram.ui.Gifts.GiftMessageBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends SizeNotifierFrameLayout {
        final /* synthetic */ GiftMessageBottomSheet this$0;

        public AnonymousClass1(GiftMessageBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if (r8 != this.backgroundView) goto L10;
            if ((GiftMessageBottomSheet.m14895$$Nest$fgetnavbarContentSourceWallpaper(this.this$0).getSource() instanceof BlurredBackgroundSourceBitmap) == false) goto L7;
            ((BlurredBackgroundSourceBitmap) GiftMessageBottomSheet.m14895$$Nest$fgetnavbarContentSourceWallpaper(this.this$0).getSource()).setParentSize(getWidth(), getHeight(), 0);
        L7:
            GiftMessageBottomSheet.m14895$$Nest$fgetnavbarContentSourceWallpaper(this.this$0).draw(r7, 0.0f, 0.0f, getWidth(), getHeight());
            return false;
        L10:
            return super.drawChild(r7, r8, r9);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public Drawable getNewDrawable() {
            if (GiftMessageBottomSheet.m14888$$Nest$fgetbackgroundDrawable(this.this$0) == null) goto L7;
            return GiftMessageBottomSheet.m14888$$Nest$fgetbackgroundDrawable(this.this$0);
        L7:
            return super.getNewDrawable();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isStatusBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            GiftMessageBottomSheet.m14899$$Nest$mcheckUi_GiftLayoutPosition(this.this$0);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void onUpdateBackgroundDrawable(Drawable r2) {
            super.onUpdateBackgroundDrawable(r2);
            if ((r2 instanceof MotionBackgroundDrawable) == false) goto L5;
            ((MotionBackgroundDrawable) r2).setFastRenderAllowed();
        L5:
            BlurredBackgroundSource r22 = GiftMessageBottomSheet.m14897$$Nest$fgetwallpaperBitmapProvider(this.this$0).updateSourceFromBackgroundViewDrawable(r2);
            GiftMessageBottomSheet.m14895$$Nest$fgetnavbarContentSourceWallpaper(this.this$0).setSource(r22);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftMessageBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ChatActivityEnterView {
        final /* synthetic */ GiftMessageBottomSheet this$0;

        public AnonymousClass2(GiftMessageBottomSheet r1, Activity r2, SizeNotifierFrameLayout r3, ChatActivity r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void extendActionMode(Menu r7) {
            ChatActivity.fillActionModeMenu(r7, null, false, false, false, false);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void onChangedIslandTotalHeight(float r2) {
            GiftMessageBottomSheet.m14891$$Nest$fgetchatInputViewsContainer(this.this$0).setInputBubbleHeight(r2);
            GiftMessageBottomSheet.m14899$$Nest$mcheckUi_GiftLayoutPosition(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftMessageBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 implements ChatActivityEnterView.ChatActivityEnterViewDelegate {
        final /* synthetic */ GiftMessageBottomSheet this$0;
        final /* synthetic */ TL_stars.TL_starGiftUnique val$gift;

        /* renamed from: org.telegram.ui.Gifts.GiftMessageBottomSheet$3$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$1.this$0).setVisibility(8);
            }
        }

        public AnonymousClass3(GiftMessageBottomSheet r1, TL_stars.TL_starGiftUnique r2) {
            this.this$0 = r1;
            this.val$gift = r2;
        }

        private void onTextChangedInternal(CharSequence r10) {
            GiftMessageBottomSheet.m14896$$Nest$fgetstarGiftUniqueActionView(this.this$0).set(this.val$gift, UserConfig.getInstance(GiftMessageBottomSheet.access$000(this.this$0)).getClientUserId(), GiftMessageBottomSheet.m14893$$Nest$fgetcommentView(this.this$0).getTextWithEntities(), LocaleController.getString(R.string.GiftMessageSendNow), true);
            GiftMessageBottomSheet.m14898$$Nest$fputcodepointCount(this.this$0, Character.codePointCount(r10, 0, r10.length()));
            if (GiftMessageBottomSheet.m14889$$Nest$fgetcaptionLimit(this.this$0) <= 0) goto L22;
            int r102 = GiftMessageBottomSheet.m14889$$Nest$fgetcaptionLimit(this.this$0) - GiftMessageBottomSheet.m14892$$Nest$fgetcodepointCount(this.this$0);
            if (r102 > 15) goto L22;
            if (r102 >= (-9999)) goto L9;
            r102 = -9999;
        L9:
            AnimatedTextView r5 = GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0);
            String r6 = LocaleController.formatNumber(r102, ',');
            if (GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).getVisibility() != 0) goto L12;
            boolean r7 = true;
        L13:
            r5.setText(r6, r7);
            if (GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).getVisibility() == 0) goto L16;
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).setVisibility(0);
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).setAlpha(0.0f);
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).setScaleX(0.5f);
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).setScaleY(0.5f);
        L16:
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).animate().setListener(null).cancel();
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
            GiftMessageBottomSheet r0 = this.this$0;
            if (r102 >= 0) goto L20;
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(r0).setTextColor(GiftMessageBottomSheet.access$100(this.this$0, Theme.key_text_RedRegular));
            return;
        L20:
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(r0).setTextColor(GiftMessageBottomSheet.access$200(this.this$0, Theme.key_windowBackgroundWhiteGrayText));
            return;
        L12:
            r7 = false;
        L22:
            GiftMessageBottomSheet.m14890$$Nest$fgetcaptionLimitView(this.this$0).animate().alpha(0.0f).scaleX(0.5f).scaleY(0.5f).setDuration(100).setListener(new AnonymousClass1(this));
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void bottomPanelTranslationYChanged(float r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void didPressAttachButton() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public boolean isVideoRecordingPaused() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needChangeVideoPreviewState(int r1, float r2) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needSendTyping() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needShowMediaBanHint() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needStartRecordAudio(int r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needStartRecordVideo(int r1, boolean r2, int r3, int r4, int r5, long r6, long r8) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAttachButtonHidden() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAttachButtonShow() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAudioVideoInterfaceUpdated() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onMessageEditEnd(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onMessageSend(CharSequence r1, boolean r2, int r3, int r4, long r5) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onPreAudioVideoRecord() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onSendLongClick() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onStickersExpandedChange() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onStickersTab(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onSwitchRecordMode(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextChanged(CharSequence r1, boolean r2, boolean r3) {
            onTextChangedInternal(r1);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextSelectionChanged(int r1, int r2) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextSpansChanged(CharSequence r1) {
            onTextChangedInternal(r1);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onUpdateSlowModeButton(View r1, boolean r2, CharSequence r3) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onWindowSizeChanged(int r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void toggleVideoRecordingPause() {
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftMessageBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends ChatActivityEnterView.SendButton {
        final /* synthetic */ GiftMessageBottomSheet this$0;

        public AnonymousClass4(GiftMessageBottomSheet r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInScheduleMode() {
            return super.isInScheduleMode();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInactive() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isOpen() {
            return true;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean shouldDrawBackground() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftMessageBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 implements Bulletin.Delegate {
        final /* synthetic */ GiftMessageBottomSheet this$0;

        public AnonymousClass5(GiftMessageBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            return AndroidUtilities.statusBarHeight;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public void onShow(Bulletin r4) {
            r4.getLayout().setCustomBackground(GiftMessageBottomSheet.m14894$$Nest$fgetnavbarContentDrawableFactory(this.this$0).create(r4.getLayout(), true).setColorProvider(BlurredBackgroundProviderImpl.bulletin(GiftMessageBottomSheet.access$300(this.this$0))).setRadius(AndroidUtilities.dp(16.0f)));
        }
    }

    public interface Callback {
        void performSend(TLRPC.TL_textWithEntities r1, boolean r2);
    }

    public static /* synthetic */ void $r8$lambda$0yilnnSNYQylsF85emwwQqJy5Z0(GiftMessageBottomSheet r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$HIIssX8N19M0M3pTn1_BYdZt_y8(GiftMessageBottomSheet r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$TVLB8MxBd2tGRRXfbvRS3Rs1FYw(GiftMessageBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$__59NXidAq4mKm0xSsue6ofsVfg(GiftMessageBottomSheet r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oru1slbRPBRcZhf18JO1YeFEU6w(GiftMessageBottomSheet r0) {
        r0.checkInsets();
    }

    /* renamed from: $r8$lambda$xEpLyhyp8SswY3tHVJggbX-eysg, reason: not valid java name */
    public static /* synthetic */ void m14887$r8$lambda$xEpLyhyp8SswY3tHVJggbXeysg(GiftMessageBottomSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: -$$Nest$fgetbackgroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m14888$$Nest$fgetbackgroundDrawable(GiftMessageBottomSheet r0) {
        return r0.backgroundDrawable;
    }

    /* renamed from: -$$Nest$fgetcaptionLimit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14889$$Nest$fgetcaptionLimit(GiftMessageBottomSheet r0) {
        return r0.captionLimit;
    }

    /* renamed from: -$$Nest$fgetcaptionLimitView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m14890$$Nest$fgetcaptionLimitView(GiftMessageBottomSheet r0) {
        return r0.captionLimitView;
    }

    /* renamed from: -$$Nest$fgetchatInputViewsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatInputViewsContainer m14891$$Nest$fgetchatInputViewsContainer(GiftMessageBottomSheet r0) {
        return r0.chatInputViewsContainer;
    }

    /* renamed from: -$$Nest$fgetcodepointCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14892$$Nest$fgetcodepointCount(GiftMessageBottomSheet r0) {
        return r0.codepointCount;
    }

    /* renamed from: -$$Nest$fgetcommentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityEnterView m14893$$Nest$fgetcommentView(GiftMessageBottomSheet r0) {
        return r0.commentView;
    }

    /* renamed from: -$$Nest$fgetnavbarContentDrawableFactory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawableViewFactory m14894$$Nest$fgetnavbarContentDrawableFactory(GiftMessageBottomSheet r0) {
        return r0.navbarContentDrawableFactory;
    }

    /* renamed from: -$$Nest$fgetnavbarContentSourceWallpaper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceWrapped m14895$$Nest$fgetnavbarContentSourceWallpaper(GiftMessageBottomSheet r0) {
        return r0.navbarContentSourceWallpaper;
    }

    /* renamed from: -$$Nest$fgetstarGiftUniqueActionView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarGiftUniqueActionView m14896$$Nest$fgetstarGiftUniqueActionView(GiftMessageBottomSheet r0) {
        return r0.starGiftUniqueActionView;
    }

    /* renamed from: -$$Nest$fgetwallpaperBitmapProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WallpaperBitmapProvider m14897$$Nest$fgetwallpaperBitmapProvider(GiftMessageBottomSheet r0) {
        return r0.wallpaperBitmapProvider;
    }

    /* renamed from: -$$Nest$fputcodepointCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14898$$Nest$fputcodepointCount(GiftMessageBottomSheet r0, int r1) {
        r0.codepointCount = r1;
    }

    /* renamed from: -$$Nest$mcheckUi_GiftLayoutPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14899$$Nest$mcheckUi_GiftLayoutPosition(GiftMessageBottomSheet r0) {
        r0.checkUi_GiftLayoutPosition();
    }

    public GiftMessageBottomSheet(Context r27, Theme.ResourcesProvider r28, TL_stars.TL_starGiftUnique r29, long r30) {
        super(r27, true, true, null);
        this.wallpaperBitmapProvider = new WallpaperBitmapProvider();
        WindowInsetsStateHolder r4 = new WindowInsetsStateHolder(new GiftMessageBottomSheet$$ExternalSyntheticLambda0(this));
        this.windowInsetsStateHolder = r4;
        AndroidUtilities.enableEdgeToEdge(getWindow());
        this.gift = r29;
        this.toDialogId = r30;
        this.captionLimit = MessagesController.getInstance(this.currentAccount).stargiftsMessageLengthMax;
        BlurredBackgroundSourceWrapped r5 = new BlurredBackgroundSourceWrapped();
        this.navbarContentSourceWallpaper = r5;
        BlurredBackgroundDrawableViewFactory r8 = new BlurredBackgroundDrawableViewFactory(r5);
        this.navbarContentDrawableFactory = r8;
        AnonymousClass1 r52 = new AnonymousClass1(this, r27);
        this.sizeNotifierFrameLayout = r52;
        this.containerView = r52;
        int r10 = this.backgroundPaddingLeft;
        r52.setPadding(r10, 0, r10, 0);
        r8.setSourceRootView(new ViewPositionWatcher(this.containerView), this.containerView);
        WindowAnimatedInsetsProvider r102 = new WindowAnimatedInsetsProvider(this.container);
        this.rootAnimatedInsetsListener = r102;
        r4.setupAnimatedInsetsProvider(r102, this.containerView);
        Drawable r0 = PreviewView.getBackgroundDrawable(null, this.currentAccount, r30, Theme.isCurrentThemeDark());
        this.backgroundDrawable = r0;
        r52.setBackgroundImage(r0, false);
        StarGiftUniqueActionView r82 = new StarGiftUniqueActionView(r27, this.currentAccount, r28);
        this.starGiftUniqueActionView = r82;
        r82.set(r29, UserConfig.getInstance(this.currentAccount).getClientUserId(), null, LocaleController.getString(R.string.GiftMessageSendNow), false);
        r82.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
        r82.setLayoutBackground(Theme.createServiceDrawable(AndroidUtilities.dp(18.0f), r82, this.containerView, getThemedPaint("paintChatActionBackground")));
        r52.addView(r82, LayoutHelper.createFrame(-2, -2, 48));
        ChatInputViewsContainer r12 = new ChatInputViewsContainer(r27);
        this.chatInputViewsContainer = r12;
        r12.setClipChildren(false);
        r12.setWindowInsetsProvider(r4);
        r12.setInputIslandBubbleDrawable(r8.create(r12, BlurredBackgroundProviderImpl.bottomPanelChatActivity(r28)));
        r12.setUnderKeyboardBackgroundDrawable(r8.create(r12, BlurredBackgroundProviderImpl.bottomPanelChatActivity(r28)));
        FrameLayout r13 = r12.getInputIslandBubbleContainer();
        this.chatInputBubbleContainer = r13;
        r13.setClipChildren(false);
        FrameLayout r14 = r12.getInAppKeyboardBubbleContainer();
        this.chatInputInAppContainer = r14;
        AnonymousClass2 r02 = new AnonymousClass2(this, AndroidUtilities.getActivity(), r52, null, false);
        this.commentView = r02;
        r02.setInAppInsetsController(r4);
        r02.setOverrideHint(LocaleController.getString(R.string.GiftMessageAddHint));
        r02.shouldDrawBackground = false;
        this.containerView.setClipChildren(false);
        this.containerView.setClipToPadding(false);
        r02.allowBlur = false;
        r02.forceSmoothKeyboard(true);
        r02.setAllowStickersAndGifs(true, false, false);
        r02.setForceShowSendButton(true, false);
        r02.textFieldContainer.setPadding(0, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(20.0f), 0);
        r02.getSendButton().setAlpha(0.0f);
        r02.getEditField().setMaxLines(3);
        r02.setCustomWindowView(this.container);
        r02.setViewParentForEmoji(r14);
        r13.addView(r02, LayoutHelper.createFrame(-1, -2.0f, 83, 7.0f, 0.0f, 7.0f, 0.0f));
        this.containerView.addView(r12.getFadeView(), LayoutHelper.createFrame(-1, -1.0f));
        this.containerView.addView(r12, LayoutHelper.createFrame(-1, -1.0f));
        r02.setDelegate(new AnonymousClass3(this, r29));
        ChatActivityEnterView.disableNewLines(r02.messageEditText);
        AnimatedTextView r03 = new AnimatedTextView(r27);
        this.captionLimitView = r03;
        r03.setAllowCancel(true);
        r03.setScaleProperty(0.6f);
        r03.setVisibility(8);
        r03.setTextSize(AndroidUtilities.dp(15.0f));
        r03.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        r03.setTypeface(AndroidUtilities.bold());
        r03.setGravity(17);
        this.containerView.addView(r03, LayoutHelper.createFrame(56, 20.0f, 85, 3.0f, 0.0f, 3.0f, 54.0f));
        AnonymousClass4 r04 = new AnonymousClass4(this, r27, R.drawable.send_plane_24, r28);
        this.writeButton = r04;
        r04.setCircleSize(AndroidUtilities.dp(38.0f), AndroidUtilities.dp(38.0f));
        this.writeButton.setCirclePadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(8.0f));
        ChatActivityEnterView.SendButton r05 = this.writeButton;
        r05.newCounterPos = true;
        this.containerView.addView(r05, LayoutHelper.createFrame(110, 50.0f, 85, 0.0f, 0.0f, 0.0f, 2.0f));
        this.writeButton.setScrimViewBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.writeButton.setOnClickListener(new GiftMessageBottomSheet$$ExternalSyntheticLambda1(this));
        TextView r06 = new TextView(r27);
        this.previewInChatHeader = r06;
        int r3 = Theme.key_chat_serviceText;
        r06.setTextColor(getThemedColor(r3));
        r06.setTextSize(1, 14.0f);
        r06.setTypeface(AndroidUtilities.bold());
        r06.setText(LocaleController.getString(R.string.GiftMessagePreviewInChat));
        r06.setGravity(17);
        r06.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        r06.setBackground(Theme.createServiceDrawable(AndroidUtilities.dp(23.0f) / 2, r06, this.containerView, getThemedPaint("paintChatActionBackground")));
        this.containerView.addView(r06, LayoutHelper.createFrame(-2, 23, 49));
        FrameLayout r07 = new FrameLayout(r27);
        this.publicCheckboxButton = r07;
        TextView r2 = new TextView(r27);
        r2.setTextColor(getThemedColor(r3));
        r2.setTextSize(1, 14.0f);
        r2.setTypeface(AndroidUtilities.bold());
        r2.setText(LocaleController.getString(R.string.GiftMessageMakeMessagePublic));
        r07.addView(r2, LayoutHelper.createFrame(-2, -2.0f, 16, 36.0f, 0.0f, 14.0f, 0.0f));
        CheckBox2 r22 = new CheckBox2(r27, 18, r28);
        this.publicCheckboxView = r22;
        r22.getCheckBoxBase().setCuttingCheck(true);
        r22.getCheckBoxBase().checkScale = 0.9f;
        r22.setColor(r3, r3, Theme.key_checkboxCheck);
        r22.setDrawUnchecked(true);
        r22.setChecked(!this.hideMyName, false);
        r82.getLayout().setOnButtonClickListener(new GiftMessageBottomSheet$$ExternalSyntheticLambda2(this));
        r22.setDrawBackgroundAsArc(10);
        r07.addView(r22, LayoutHelper.createFrame(18, 18.0f, 19, 10.0f, 0.0f, 0.0f, 0.0f));
        r07.setBackground(Theme.createServiceDrawable(AndroidUtilities.dp(16.0f), r07, this.containerView, getThemedPaint("paintChatActionBackground")));
        r07.setOnClickListener(new GiftMessageBottomSheet$$ExternalSyntheticLambda3(this));
        this.containerView.addView(r07, LayoutHelper.createFrame(-2, 32, 81));
        ImageView r32 = new ImageView(r27);
        this.closeButton = r32;
        r32.setScaleType(ImageView.ScaleType.CENTER);
        r32.setImageResource(R.drawable.ic_close_white);
        r32.setBackground(Blur3Utils.wrapCenteredDrawable(Theme.createServiceDrawable(AndroidUtilities.dp(16.0f), r32, this.containerView, getThemedPaint("paintChatActionBackground")), AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f)));
        r32.setOnClickListener(new GiftMessageBottomSheet$$ExternalSyntheticLambda4(this));
        this.containerView.addView(r32, LayoutHelper.createFrame(56, 56, 53));
        ScaleStateListAnimator.apply(r07, 0.05f, 1.2f);
        ScaleStateListAnimator.apply(r32);
        ViewCompat.setOnApplyWindowInsetsListener(this.containerView, new GiftMessageBottomSheet$$ExternalSyntheticLambda5(this));
    }

    public static /* synthetic */ int access$000(GiftMessageBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(GiftMessageBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$200(GiftMessageBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(GiftMessageBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private void checkInsets() {
        ChatInputViewsContainer r0 = this.chatInputViewsContainer;
        if (r0 == null) goto L5;
        r0.checkInsets();
    L5:
        ChatActivityEnterView.SendButton r02 = this.writeButton;
        if (r02 == null) goto L8;
        r02.setTranslationY(-this.windowInsetsStateHolder.getAnimatedMaxBottomInset());
    L8:
        AnimatedTextView r03 = this.captionLimitView;
        if (r03 == null) goto L11;
        r03.setTranslationY(-this.windowInsetsStateHolder.getAnimatedMaxBottomInset());
    L11:
        checkUi_GiftLayoutPosition();
    }

    private void checkUi_GiftLayoutPosition() {
        int r0 = this.windowInsetsStateHolder.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()).top;
        float r1 = AndroidUtilities.dp(36.0f) + r0;
        float r2 = (this.windowInsetsStateHolder.getAnimatedMaxBottomInset() + AndroidUtilities.dp(9.0f)) + this.chatInputViewsContainer.getInputBubbleHeight();
        this.starGiftUniqueActionView.setTranslationY(Math.min(((this.containerView.getHeight() - this.starGiftUniqueActionView.getHeight()) / 2.0f) + ((r1 - (AndroidUtilities.dp(46.0f) + r2)) / 2.0f), ((((this.containerView.getHeight() - r2) - AndroidUtilities.dp(14.0f)) - this.publicCheckboxButton.getHeight()) - AndroidUtilities.dp(10.0f)) - this.starGiftUniqueActionView.getHeight()));
        this.starGiftUniqueActionView.invalidate();
        this.previewInChatHeader.setTranslationY(this.starGiftUniqueActionView.getY() - AndroidUtilities.dp(33.0f));
        this.previewInChatHeader.invalidate();
        this.publicCheckboxButton.setTranslationY(-(r2 + AndroidUtilities.dp(14.0f)));
        this.publicCheckboxButton.invalidate();
        this.closeButton.setTranslationY(r0);
        this.closeButton.invalidate();
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        if ((this.captionLimit - this.codepointCount) >= 0) goto L6;
        AndroidUtilities.shakeView(this.captionLimitView);
        return;
    L6:
        Callback r22 = this.mCallback;
        if (r22 == null) goto L10;
        r22.performSend(this.commentView.getTextWithEntities(), this.hideMyName);
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        this.writeButton.performClick();
    }

    private /* synthetic */ void lambda$new$2(View r2) {
        boolean r22 = this.hideMyName;
        this.hideMyName = !r22;
        this.publicCheckboxView.setChecked(r22, true);
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        dismiss();
    }

    private WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        this.windowInsetsStateHolder.setInsets(r2);
        return WindowInsetsCompat.CONSUMED;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    public Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        ChatActivityEnterView r0 = this.commentView;
        if (r0 != null) goto L5;
    L8:
        super.onBackPressed();
        return;
    L5:
        if (r0.isPopupShowing() == false) goto L8;
        this.commentView.hidePopup(true);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        super.onOpenAnimationEnd();
        setAllowNestedScroll(false);
        Bulletin.addDelegate(this.container, new AnonymousClass5(this));
        HintsController.Hint r0 = HintsController.Hint.GiftMessageHint;
        if (r0.show() == false) goto L6;
        r0.increment();
        TLObject r02 = MessagesController.getInstance(this.currentAccount).getUserOrChat(this.toDialogId);
        BulletinFactory.of(this.container, this.resourcesProvider).createUsersBulletin(r02, LocaleController.getString(R.string.GiftMessageAddTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftMessageAddDescription, new Object[]{DialogObject.getShortName(r02), this.gift.title + " #" + LocaleController.formatNumber(this.gift.num, ',')}))).show(true);
        return;
    }

    public void setCallback(Callback r1) {
        this.mCallback = r1;
    }

    public void setLoading(boolean r2) {
        if (this.mLoading == r2) goto L6;
        this.mLoading = r2;
        this.writeButton.setLoading(r2, -3.0f);
        return;
    }
}
