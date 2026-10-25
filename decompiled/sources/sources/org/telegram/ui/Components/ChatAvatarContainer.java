package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.utils.ui.ChatHeaderUiHelper;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.BusinessLinksController;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AutoDeletePopupWrapper;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.StoriesUtilities;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.community.CommunityArrowDrawable;

/* loaded from: classes3.dex */
public class ChatAvatarContainer extends FrameLayout implements FactorAnimator.Target, NotificationCenter.NotificationCenterDelegate {
    private ActionBar actionBar;
    public boolean allowDrawStories;
    public boolean allowShorterStatus;
    private AnimatedTextView animatedSubtitleTextView;
    private final BoolAnimator animatorTimeVisible;
    private int avatarBaseRadius;
    private AvatarDrawable avatarDrawable;
    private boolean avatarImageIsHidden;
    public BackupImageView avatarImageView;
    private boolean avatarIsForum;
    private int avatarSizeInDp;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable botVerificationDrawable;
    public ButtonBounce bounce;
    private ImageView communityItem;
    private int currentAccount;
    private int currentConnectionState;
    StatusDrawable currentTypingDrawable;
    private Drawable emojiStatusDefaultDrawable;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emojiStatusDrawable;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emojiStatusDrawable2;
    private boolean glassMode;
    public boolean ignoreTouches;
    private boolean[] isOnline;
    private int largerWidth;
    private CharSequence lastSubtitle;
    private int lastSubtitleColorKey;
    private int lastWidth;
    private int leftPadding;
    private Drawable mutedIcon;
    private boolean occupyStatusBar;
    private Runnable onLongClick;
    private int onlineCount;
    private Integer overrideSubtitleColor;
    private ChatActivity parentFragment;
    public boolean premiumIconHiddable;
    private boolean pressed;
    private Theme.ResourcesProvider resourcesProvider;
    private int rightAvatarPadding;
    private String rightDrawable2ContentDescription;
    private String rightDrawableContentDescription;
    private boolean rightDrawableIsScam;
    private boolean rightDrawableIsScamOrVerified;
    private boolean secretChatTimer;
    private SharedMediaLayout.SharedMediaPreloader sharedMediaPreloader;
    private boolean showingSavedMessagesHint;
    private ImageView starBgItem;
    private ImageView starFgItem;
    public boolean stars;
    private StatusDrawable[] statusDrawables;
    public boolean[] statusMadeShorter;
    private Integer storiesForceState;
    private boolean subtitleIsThinkingBot;
    private AtomicReference<SimpleTextView> subtitleTextLargerCopyView;
    private SimpleTextView subtitleTextView;
    private ImageView timeItem;
    private TimerDrawable timerDrawable;
    private AnimatorSet titleAnimation;
    private AtomicReference<SimpleTextView> titleTextLargerCopyView;
    private SimpleTextView titleTextView;
    private Drawable verifiedBackground;
    private Drawable verifiedCheck;

    /* renamed from: org.telegram.ui.Components.ChatAvatarContainer$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends BackupImageView {
        StoriesUtilities.AvatarStoryParams params;
        final /* synthetic */ ChatAvatarContainer this$0;
        final /* synthetic */ boolean val$avatarClickable;
        final /* synthetic */ BaseFragment val$baseFragment;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        /* renamed from: org.telegram.ui.Components.ChatAvatarContainer$1$1, reason: invalid class name and collision with other inner class name */
        public class C00491 extends StoriesUtilities.AvatarStoryParams {
            final /* synthetic */ AnonymousClass1 this$1;

            public static /* synthetic */ boolean $r8$lambda$E2s2UmrNRv50CyunegdIgyBNRCI(C00491 r0, long r1, int r3, int r4, int r5, StoryViewer.TransitionViewHolder r6) {
                return r0.lambda$openStory$0(r1, r3, r4, r5, r6);
            }

            public C00491(AnonymousClass1 r1, boolean r2) {
                this.this$1 = r1;
                super(r2);
            }

            private /* synthetic */ boolean lambda$openStory$0(long r1, int r3, int r4, int r5, StoryViewer.TransitionViewHolder r6) {
                AnonymousClass1 r12 = this.this$1;
                ImageReceiver r2 = r12.imageReceiver;
                r6.storyImage = r2;
                r6.crossfadeToAvatarImage = r2;
                StoriesUtilities.AvatarStoryParams r22 = r12.params;
                r6.params = r22;
                r6.isLive = r22.drawnLive;
                BackupImageView r13 = r12.this$0.avatarImageView;
                r6.view = r13;
                r6.alpha = r13.getAlpha();
                r6.clipTop = 0.0f;
                r6.clipBottom = AndroidUtilities.displaySize.y;
                r6.clipParent = (View) this.this$1.getParent();
                return true;
            }

            @Override // org.telegram.ui.Stories.StoriesUtilities.AvatarStoryParams
            public void openStory(long r3, Runnable r5) {
                this.this$1.val$baseFragment.getOrCreateStoryViewer().open(this.this$1.getContext(), r3, new ChatAvatarContainer$1$1$$ExternalSyntheticLambda0(this));
            }
        }

        public AnonymousClass1(ChatAvatarContainer r1, Context r2, BaseFragment r3, boolean r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            this.val$baseFragment = r3;
            this.val$avatarClickable = r4;
            this.val$resourcesProvider = r5;
            super(r2);
            this.params = new C00491(this, true);
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r5) {
            if (this.this$0.allowDrawStories == true) goto L5;
        L18:
            super.onDraw(r5);
            return;
        L5:
            if (this.animatedEmojiDrawable != null) goto L18;
            this.params.originalAvatarRect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            StoriesUtilities.AvatarStoryParams r0 = this.params;
            r0.drawSegments = true;
            r0.drawInside = true;
            r0.avatarRadius = ChatAvatarContainer.m9939$$Nest$fgetavatarBaseRadius(this.this$0);
            this.params.resourcesProvider = this.val$resourcesProvider;
            if (ChatAvatarContainer.m9941$$Nest$fgetstoriesForceState(this.this$0) == null) goto L10;
            this.params.forceState = ChatAvatarContainer.m9941$$Nest$fgetstoriesForceState(this.this$0).intValue();
        L10:
            if (ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0) == null) goto L12;
            long r02 = ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).getDialogId();
        L16:
            StoriesUtilities.drawAvatarWithStory(r02, r5, this.imageReceiver, this.params);
            return;
        L12:
            BaseFragment r03 = this.val$baseFragment;
            if ((r03 instanceof TopicsFragment) == false) goto L15;
            r02 = ((TopicsFragment) r03).getDialogId();
            goto L16
        L15:
            r02 = 0;
            goto L16
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            if (this.val$avatarClickable == true) goto L5;
        L8:
            r3.setVisibleToUser(false);
            return;
        L5:
            if (getImageReceiver().hasNotThumb() == false) goto L8;
            r3.setText(LocaleController.getString(R.string.AccDescrProfilePicture));
            r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, LocaleController.getString(R.string.Open)));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.allowDrawStories == false) goto L9;
            if (this.params.checkOnTouchEvent(r2, this) == false) goto L9;
            return true;
        L9:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAvatarContainer$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements AutoDeletePopupWrapper.Callback {
        final /* synthetic */ ChatAvatarContainer this$0;
        final /* synthetic */ ActionBarPopupWindow[] val$scrimPopupWindow;

        public AnonymousClass2(ChatAvatarContainer r1, ActionBarPopupWindow[] r2) {
            this.this$0 = r1;
            this.val$scrimPopupWindow = r2;
        }

        @Override // org.telegram.ui.Components.AutoDeletePopupWrapper.Callback
        public void dismiss() {
            ActionBarPopupWindow r1 = this.val$scrimPopupWindow[0];
            if (r1 == null) goto L6;
            r1.dismiss();
            return;
        }

        @Override // org.telegram.ui.Components.AutoDeletePopupWrapper.Callback
        public void setAutoDeleteHistory(int r11, int r12) {
            if (ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0) == null) goto L18;
            ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).getMessagesController().setDialogHistoryTTL(ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).getDialogId(), r11);
            TLRPC.ChatFull r112 = ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).getCurrentChatInfo();
            TLRPC.UserFull r0 = ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).getCurrentUserInfo();
            if (r0 != null) goto L8;
            if (r112 != null) goto L8;
            return;
        L8:
            UndoView r2 = ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).getUndoView();
            if (r2 == null) goto L17;
            long r3 = ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).getDialogId();
            TLRPC.User r6 = ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).getCurrentUser();
            if (r0 == null) goto L13;
            int r10 = r0.ttl_period;
        L14:
            r2.showWithAction(r3, r12, r6, Integer.valueOf(r10), null, null);
            return;
        L13:
            r10 = r112.ttl_period;
            goto L14
        L17:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAvatarContainer$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends ActionBarPopupWindow {
        final /* synthetic */ ChatAvatarContainer this$0;

        public AnonymousClass3(ChatAvatarContainer r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            if (ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0) == null) goto L6;
            ChatAvatarContainer.m9940$$Nest$fgetparentFragment(this.this$0).dimBehindView(false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAvatarContainer$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAvatarContainer this$0;

        public AnonymousClass4(ChatAvatarContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            ChatAvatarContainer.m9943$$Nest$fputtitleAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ChatAvatarContainer.m9942$$Nest$fgettitleAnimation(this.this$0) != r2) goto L6;
            this.this$0.getSubtitleTextView().setVisibility(4);
            ChatAvatarContainer.m9943$$Nest$fputtitleAnimation(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAvatarContainer$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAvatarContainer this$0;

        public AnonymousClass5(ChatAvatarContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ChatAvatarContainer.m9943$$Nest$fputtitleAnimation(this.this$0, null);
        }
    }

    /* loaded from: classes7.dex */
    public class SimpleTextConnectedView extends SimpleTextView {
        private AtomicReference<SimpleTextView> reference;
        final /* synthetic */ ChatAvatarContainer this$0;

        public SimpleTextConnectedView(ChatAvatarContainer r1, Context r2, AtomicReference<SimpleTextView> r3) {
            this.this$0 = r1;
            super(r2);
            this.reference = r3;
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView
        public boolean setText(CharSequence r2) {
            AtomicReference<SimpleTextView> r0 = this.reference;
            if (r0 == null) goto L8;
            SimpleTextView r02 = r0.get();
            if (r02 == null) goto L8;
            r02.setText(r2);
        L8:
            return super.setText(r2);
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            AtomicReference<SimpleTextView> r0 = this.reference;
            if (r0 == null) goto L7;
            SimpleTextView r02 = r0.get();
            if (r02 == null) goto L7;
            r02.setTranslationY(r2);
        L7:
            super.setTranslationY(r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$04xa_y9NX7J55WiAG0SJt2jLZKo(ChatAvatarContainer r0) {
        r0.lambda$fadeOutToLessWidth$4();
    }

    /* renamed from: $r8$lambda$3mtme7gMD3q3Ar-wgqxvxtIC5nA, reason: not valid java name */
    public static /* synthetic */ void m9938$r8$lambda$3mtme7gMD3q3ArwgqxvxtIC5nA(ChatAvatarContainer r0, boolean r1) {
        r0.lambda$setStars$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$C34o_RvbhdKKK594NK0uVWtS2Kk(ChatAvatarContainer r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$CKAcZ6lS3UK9KXTvoj6kPBQ0bK4(ChatAvatarContainer r0) {
        r0.lambda$fadeOutToLessWidth$5();
    }

    public static /* synthetic */ void $r8$lambda$WHHlUVMqFEi52eJeV5F7D23TTfE(ChatAvatarContainer r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$XPQQOE8Y_6CaBq4djQsXNCkkMlY(ChatAvatarContainer r0, boolean r1) {
        r0.lambda$setStars$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$jBoO5MoVbrFdt6fpKXbLWIzhKTg(ChatAvatarContainer r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nQMNM1OQhTYsHGLCsshwCrkt1lw(ChatAvatarContainer r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: -$$Nest$fgetavatarBaseRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9939$$Nest$fgetavatarBaseRadius(ChatAvatarContainer r0) {
        return r0.avatarBaseRadius;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m9940$$Nest$fgetparentFragment(ChatAvatarContainer r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetstoriesForceState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Integer m9941$$Nest$fgetstoriesForceState(ChatAvatarContainer r0) {
        return r0.storiesForceState;
    }

    /* renamed from: -$$Nest$fgettitleAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m9942$$Nest$fgettitleAnimation(ChatAvatarContainer r0) {
        return r0.titleAnimation;
    }

    /* renamed from: -$$Nest$fputtitleAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9943$$Nest$fputtitleAnimation(ChatAvatarContainer r0, AnimatorSet r1) {
        r0.titleAnimation = r1;
    }

    public ChatAvatarContainer(Context r2, BaseFragment r3, boolean r4) {
        this(r2, r3, r4, null);
    }

    public ChatAvatarContainer(Context r20, BaseFragment r21, boolean r22, Theme.ResourcesProvider r23) {
        super(r20);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorTimeVisible = new BoolAnimator(0, this, r3, 320);
        this.avatarSizeInDp = 42;
        this.titleTextLargerCopyView = new AtomicReference();
        this.subtitleTextLargerCopyView = new AtomicReference();
        this.statusDrawables = new StatusDrawable[6];
        this.avatarDrawable = new AvatarDrawable();
        this.currentAccount = UserConfig.selectedAccount;
        this.occupyStatusBar = true;
        this.leftPadding = AndroidUtilities.dp(8.0f);
        this.rightAvatarPadding = 0;
        this.lastWidth = -1;
        this.largerWidth = -1;
        this.isOnline = new boolean[1];
        this.statusMadeShorter = new boolean[1];
        this.onlineCount = -1;
        this.lastSubtitleColorKey = -1;
        this.allowShorterStatus = false;
        this.premiumIconHiddable = false;
        this.bounce = new ButtonBounce(this);
        this.onLongClick = new ChatAvatarContainer$$ExternalSyntheticLambda4(this);
        this.rightDrawableIsScamOrVerified = false;
        this.rightDrawableIsScam = false;
        TLRPC.Chat r12 = null;
        this.rightDrawableContentDescription = null;
        this.rightDrawable2ContentDescription = null;
        this.resourcesProvider = r23;
        boolean r13 = r21 instanceof ChatActivity;
        if (r13 == false) goto L5;
        this.parentFragment = (ChatActivity) r21;
    L5:
        ChatActivity r0 = this.parentFragment;
        if (r0 != null) goto L8;
    L18:
        boolean r4 = false;
    L19:
        this.avatarImageView = new AnonymousClass1(this, r20, r21, r4, r23);
        if (r13 == false) goto L22;
    L23:
        ChatActivity r7 = this.parentFragment;
        if (r7 != null) goto L26;
    L35:
        this.sharedMediaPreloader = new SharedMediaLayout.SharedMediaPreloader(r21);
    L36:
        ChatActivity r32 = this.parentFragment;
        if (r32 != null) goto L39;
    L51:
        boolean r33 = false;
    L52:
        this.avatarImageIsHidden = r33;
        if (r33 == false) goto L55;
        this.avatarImageView.setVisibility(8);
    L55:
        this.avatarImageView.setContentDescription(LocaleController.getString(R.string.AccDescrProfilePicture));
        setAvatarRadius(false);
        addView(this.avatarImageView);
        if (r4 == false) goto L65;
        ChatActivity r34 = this.parentFragment;
        if (r34 == null) goto L60;
        r12 = r34.getCurrentChat();
    L60:
        if (r12 != null) goto L62;
    L64:
        this.avatarImageView.setOnClickListener(new ChatAvatarContainer$$ExternalSyntheticLambda5(this));
        goto L65
    L62:
        if (r12.linked_community_id == 0) goto L64;
        ScaleStateListAnimator.apply(this.avatarImageView, 0.05f, 1.2f);
    L65:
        SimpleTextConnectedView r35 = new SimpleTextConnectedView(this, r20, this.titleTextLargerCopyView);
        this.titleTextView = r35;
        r35.setEllipsizeByGradient(true);
        this.titleTextView.setTextColor(getThemedColor(Theme.key_actionBarDefaultTitle));
        this.titleTextView.setTextSize(18);
        this.titleTextView.setGravity(3);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        this.titleTextView.setLeftDrawableTopPadding(-AndroidUtilities.dp(1.3f));
        this.titleTextView.setCanHideRightDrawable(false);
        this.titleTextView.setRightDrawableOutside(true);
        this.titleTextView.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(12.0f));
        addView(this.titleTextView);
        if (useAnimatedSubtitle() == false) goto L68;
        AnimatedTextView r36 = new AnimatedTextView(r20, true, true, true);
        this.animatedSubtitleTextView = r36;
        char r16 = 2;
        char r17 = 5;
        int r122 = 8;
        Context r8 = r20;
        Theme.ResourcesProvider r132 = r23;
        r36.setAnimationProperties(0.3f, 0, 320, r3);
        this.animatedSubtitleTextView.setEllipsizeByGradient(true);
        AnimatedTextView r1 = this.animatedSubtitleTextView;
        int r2 = Theme.key_actionBarDefaultSubtitle;
        r1.setTextColor(getThemedColor(r2));
        this.animatedSubtitleTextView.setTag(Integer.valueOf(r2));
        this.animatedSubtitleTextView.setTextSize(AndroidUtilities.dp(14.0f));
        this.animatedSubtitleTextView.setGravity(3);
        this.animatedSubtitleTextView.setPadding(0, 0, AndroidUtilities.dp(10.0f), 0);
        this.animatedSubtitleTextView.setTranslationY(-AndroidUtilities.dp(1.0f));
        addView(this.animatedSubtitleTextView);
    L70:
        if (this.parentFragment == null) goto L76;
        ImageView r14 = new ImageView(r8);
        this.communityItem = r14;
        ImageView.ScaleType r37 = ImageView.ScaleType.CENTER;
        r14.setScaleType(r37);
        this.communityItem.setVisibility(r122);
        this.communityItem.setImageDrawable(new CommunityArrowDrawable());
        addView(this.communityItem);
        ImageView r15 = new ImageView(r8);
        this.timeItem = r15;
        r15.setScaleType(r37);
        this.timeItem.setVisibility(r122);
        ImageView r18 = this.timeItem;
        TimerDrawable r38 = new TimerDrawable(r8, r132);
        this.timerDrawable = r38;
        r18.setImageDrawable(r38);
        this.timerDrawable.setBackgroundColor(0);
        addView(this.timeItem);
        this.secretChatTimer = r22;
        this.timeItem.setOnClickListener(new ChatAvatarContainer$$ExternalSyntheticLambda6(this, r132));
        boolean r19 = this.secretChatTimer;
        ImageView r39 = this.timeItem;
        if (r19 == false) goto L74;
        r39.setContentDescription(LocaleController.getString(R.string.SetTimer));
    L75:
        ImageView r110 = new ImageView(r8);
        this.starBgItem = r110;
        r110.setImageResource(R.drawable.star_small_outline);
        this.starBgItem.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefault), PorterDuff.Mode.SRC_IN));
        this.starBgItem.setAlpha(0.0f);
        this.starBgItem.setVisibility(4);
        this.starBgItem.setScaleY(0.0f);
        this.starBgItem.setScaleX(0.0f);
        addView(this.starBgItem);
        ImageView r111 = new ImageView(r8);
        this.starFgItem = r111;
        r111.setImageResource(R.drawable.star_small_inner);
        this.starFgItem.setAlpha(0.0f);
        this.starFgItem.setVisibility(4);
        this.starFgItem.setScaleY(0.0f);
        this.starFgItem.setScaleX(0.0f);
        addView(this.starFgItem);
        goto L76
    L74:
        r39.setContentDescription(LocaleController.getString(R.string.AccAutoDeleteTimer));
    L76:
        ChatActivity r112 = this.parentFragment;
        if (r112 != null) goto L79;
    L105:
        this.emojiStatusDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.titleTextView, AndroidUtilities.dp(24.0f));
        this.emojiStatusDrawable2 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.titleTextView, AndroidUtilities.dp(24.0f));
        this.botVerificationDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.titleTextView, AndroidUtilities.dp(17.0f));
        return;
    L79:
        if (r112.getChatMode() == 0) goto L85;
        if (this.parentFragment.getChatMode() == r122) goto L85;
        if (this.parentFragment.getChatMode() != 3) goto L105;
    L85:
        if (this.parentFragment.isThreadChat() == false) goto L91;
        ChatActivity r113 = this.parentFragment;
        if (r113.isTopic == true) goto L91;
        if (r113.isComments == true) goto L91;
    L97:
        TLRPC.Chat r114 = this.parentFragment.getCurrentChat();
        this.statusDrawables[0] = new TypingDotsDrawable(true);
        this.statusDrawables[1] = new RecordStatusDrawable(true);
        this.statusDrawables[r16] = new SendingFileDrawable(true);
        this.statusDrawables[3] = new PlayingGameDrawable(false, r132);
        this.statusDrawables[4] = new RoundStatusDrawable(true);
        this.statusDrawables[r17] = new ChoosingStickerStatusDrawable(true);
        int r24 = 0;
    L98:
        StatusDrawable[] r310 = this.statusDrawables;
        if (r24 >= r310.length) goto L105;
        StatusDrawable r311 = r310[r24];
        if (r114 == null) goto L103;
        boolean r42 = true;
    L104:
        r311.setIsChat(r42);
        r24 = r24 + 1;
        goto L98
    L103:
        r42 = false;
    L91:
        if (UserObject.isReplyUser(this.parentFragment.getCurrentUser()) == true) goto L97;
        if (this.parentFragment.getCurrentUser() != null) goto L95;
    L96:
        setOnClickListener(new ChatAvatarContainer$$ExternalSyntheticLambda7(this));
        goto L97
    L95:
        if (this.parentFragment.getCurrentUser().id == UserObject.VERIFY) goto L97;
    L68:
        r132 = r23;
        r8 = r20;
        r16 = 2;
        r17 = 5;
        r122 = 8;
        SimpleTextConnectedView r115 = new SimpleTextConnectedView(this, r8, this.subtitleTextLargerCopyView);
        this.subtitleTextView = r115;
        r115.setEllipsizeByGradient(true);
        SimpleTextView r116 = this.subtitleTextView;
        int r25 = Theme.key_actionBarDefaultSubtitle;
        r116.setTextColor(getThemedColor(r25));
        this.subtitleTextView.setTag(Integer.valueOf(r25));
        this.subtitleTextView.setTextSize(14);
        this.subtitleTextView.setGravity(3);
        this.subtitleTextView.setPadding(0, 0, AndroidUtilities.dp(10.0f), 0);
        addView(this.subtitleTextView);
        goto L70
    L39:
        if (r32.isThreadChat() == false) goto L43;
        if (this.parentFragment.isReplyChatComment() == true) goto L43;
    L50:
        r33 = true;
    L43:
        if (this.parentFragment.getChatMode() == 2) goto L50;
        if (this.parentFragment.getChatMode() == 5) goto L50;
        if (this.parentFragment.getChatMode() == 9) goto L50;
        if (this.parentFragment.getChatMode() != 6) goto L51;
    L26:
        if (r7.getChatMode() == 5) goto L36;
        if (this.parentFragment.getChatMode() == 9) goto L36;
        if (this.parentFragment.getChatMode() == 6) goto L36;
        if (this.parentFragment.getChatMode() == 8) goto L36;
        if (this.parentFragment.isInBotForumMode() == true) goto L36;
    L22:
        if ((r21 instanceof TopicsFragment) == false) goto L55;
    L8:
        if (r0.getChatMode() == 0) goto L12;
        if (this.parentFragment.getChatMode() != 8) goto L18;
    L12:
        if (UserObject.isReplyUser(this.parentFragment.getCurrentUser()) == true) goto L18;
        if (this.parentFragment.getCurrentUser() != null) goto L16;
    L17:
        r4 = true;
        goto L19
    L16:
        if (this.parentFragment.getCurrentUser().id == UserObject.VERIFY) goto L18;
        goto L17
    }

    private void checkActionBar(boolean r1) {
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L6;
        r0.checkAvatarContainerWidth(r1);
        return;
    }

    private void fadeOutToLessWidth(int r8) {
        this.largerWidth = r8;
        View r82 = (SimpleTextView) this.titleTextLargerCopyView.get();
        if (r82 == null) goto L5;
        removeView(r82);
    L5:
        SimpleTextView r83 = new SimpleTextView(getContext());
        this.titleTextLargerCopyView.set(r83);
        r83.setTextColor(getThemedColor(Theme.key_actionBarDefaultTitle));
        if (this.glassMode == false) goto L8;
        float r0 = 17.5f;
    L9:
        r83.setTextSizePx(AndroidUtilities.dp(r0));
        r83.setGravity(3);
        r83.setTypeface(AndroidUtilities.bold());
        r83.setLeftDrawableTopPadding(-AndroidUtilities.dp(1.3f));
        r83.setRightDrawable(this.titleTextView.getRightDrawable());
        r83.setRightDrawable2(this.titleTextView.getRightDrawable2());
        r83.setRightDrawableOutside(this.titleTextView.getRightDrawableOutside());
        r83.setLeftDrawable(this.titleTextView.getLeftDrawable());
        r83.setText(this.titleTextView.getText());
        ViewPropertyAnimator r1 = r83.animate().alpha(0.0f).setDuration(350);
        CubicBezierInterpolator r5 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r1.setInterpolator(r5).withEndAction(new ChatAvatarContainer$$ExternalSyntheticLambda0(this)).start();
        addView(r83);
        View r84 = (SimpleTextView) this.subtitleTextLargerCopyView.get();
        if (r84 == null) goto L12;
        removeView(r84);
    L12:
        SimpleTextView r85 = new SimpleTextView(getContext());
        this.subtitleTextLargerCopyView.set(r85);
        int r12 = Theme.key_actionBarDefaultSubtitle;
        r85.setTextColor(getThemedColor(r12));
        r85.setTag(Integer.valueOf(r12));
        if (this.glassMode == false) goto L15;
        float r13 = 13.5f;
    L16:
        r85.setTextSizePx(AndroidUtilities.dp(r13));
        r85.setGravity(3);
        SimpleTextView r02 = this.subtitleTextView;
        if (r02 == null) goto L19;
        r85.setText(r02.getText());
    L22:
        r85.animate().alpha(0.0f).setDuration(350).setInterpolator(r5).withEndAction(new ChatAvatarContainer$$ExternalSyntheticLambda1(this)).start();
        addView(r85);
        setClipChildren(false);
        return;
    L19:
        AnimatedTextView r03 = this.animatedSubtitleTextView;
        if (r03 == null) goto L22;
        r85.setText(r03.getText());
        goto L22
    L15:
        r13 = 14.0f;
        goto L16
    L8:
        r0 = 18.0f;
        goto L9
    }

    public static CharSequence getChatSubtitle(TLRPC.Chat r7, TLRPC.ChatFull r8, int r9) {
        if (ChatObject.isChannel(r7) == false) goto L46;
        if (r8 == null) goto L25;
        int r0 = r8.participants_count;
        if (r0 == 0) goto L25;
        if (r7.megagroup == false) goto L14;
        if (r9 <= 1) goto L13;
        return String.format("%s, %s", new Object[]{LocaleController.formatPluralString("Members", r0, new Object[0]), LocaleController.formatPluralString("OnlineCount", Math.min(r9, r8.participants_count), new Object[0])});
    L13:
        return LocaleController.formatPluralString("Members", r0, new Object[0]);
    L14:
        int[] r92 = new int[1];
        boolean r02 = AndroidUtilities.isAccessibilityScreenReaderEnabled();
        int r82 = r8.participants_count;
        if (r02 == false) goto L17;
        r92[0] = r82;
        String r83 = String.valueOf(r82);
    L19:
        if (r7.megagroup == false) goto L23;
        return LocaleController.formatPluralString("Members", r92[0], new Object[0]).replace(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r92[0])}), r83);
    L23:
        return LocaleController.formatPluralString("Subscribers", r92[0], new Object[0]).replace(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r92[0])}), r83);
    L17:
        r83 = LocaleController.formatShortNumber(r82, r92);
    L25:
        if (r7.megagroup == false) goto L40;
        if (r8 != null) goto L30;
        return LocaleController.getString(R.string.Loading).toLowerCase();
    L30:
        if (r7.has_geo == false) goto L34;
        return LocaleController.getString(R.string.MegaLocation).toLowerCase();
    L34:
        if (ChatObject.isPublic(r7) == false) goto L38;
        return LocaleController.getString(R.string.MegaPublic).toLowerCase();
    L38:
        return LocaleController.getString(R.string.MegaPrivate).toLowerCase();
    L40:
        if (ChatObject.isPublic(r7) == false) goto L44;
        return LocaleController.getString(R.string.ChannelPublic).toLowerCase();
    L44:
        return LocaleController.getString(R.string.ChannelPrivate).toLowerCase();
    L46:
        if (ChatObject.isKickedFromChat(r7) == false) goto L50;
        return LocaleController.getString(R.string.YouWereKicked);
    L50:
        if (ChatObject.isLeftFromChat(r7) == true) goto L52;
        int r72 = r7.participants_count;
        if (r8 == null) goto L58;
        TLRPC.ChatParticipants r84 = r8.participants;
        if (r84 == null) goto L58;
        r72 = r84.participants.size();
    L58:
        if (r9 <= 1) goto L63;
        if (r72 == 0) goto L63;
        return String.format("%s, %s", new Object[]{LocaleController.formatPluralString("Members", r72, new Object[0]), LocaleController.formatPluralString("OnlineCount", r9, new Object[0])});
    L63:
        return LocaleController.formatPluralString("Members", r72, new Object[0]);
    L52:
        return LocaleController.getString(R.string.YouLeft);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$fadeOutToLessWidth$4() {
        SimpleTextView r0 = this.titleTextLargerCopyView.get();
        if (r0 == null) goto L6;
        removeView(r0);
        this.titleTextLargerCopyView.set(null);
        return;
    }

    private /* synthetic */ void lambda$fadeOutToLessWidth$5() {
        SimpleTextView r0 = this.subtitleTextLargerCopyView.get();
        if (r0 == null) goto L8;
        removeView(r0);
        this.subtitleTextLargerCopyView.set(null);
        if (this.allowDrawStories == true) goto L9;
        setClipChildren(true);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        if (onAvatarClick() == true) goto L6;
        openProfile(true);
        return;
    }

    private /* synthetic */ void lambda$new$1(Theme.ResourcesProvider r2, View r3) {
        if (this.secretChatTimer == false) goto L6;
        this.parentFragment.showDialog(AlertsCreator.createTTLAlert(getContext(), this.parentFragment.getCurrentEncryptedChat(), r2).create());
        return;
    L6:
        openSetTimer();
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        openProfile(false);
    }

    private /* synthetic */ void lambda$new$3() {
        this.pressed = false;
        this.bounce.setPressed(false);
        if (canSearch() == false) goto L6;
        openSearch();
        return;
    }

    private /* synthetic */ void lambda$setStars$6(boolean r1) {
        if (r1 == true) goto L5;
        this.starBgItem.setVisibility(4);
        return;
    }

    private /* synthetic */ void lambda$setStars$7(boolean r1) {
        if (r1 == true) goto L5;
        this.starFgItem.setVisibility(4);
        return;
    }

    private void setAvatarRadius(boolean r2) {
        this.avatarIsForum = r2;
        int r22 = ChatHeaderUiHelper.getAvatarRadius(this.avatarSizeInDp, r2);
        this.avatarBaseRadius = r22;
        BackupImageView r1 = this.avatarImageView;
        if (r1 == null) goto L6;
        r1.setRoundRadius(r22);
        return;
    }

    private void setTypingAnimation(boolean r8) {
        SimpleTextView r0 = this.subtitleTextView;
        if (r0 == null) goto L38;
        int r1 = 0;
        if (r8 == true) goto L39;
        this.currentTypingDrawable = null;
        r0.setLeftDrawable(null);
        this.subtitleTextView.replaceTextWithDrawable(null, null);
    L32:
        StatusDrawable[] r82 = this.statusDrawables;
        if (r1 >= r82.length) goto L49;
        StatusDrawable r83 = r82[r1];
        if (r83 == null) goto L37;
        r83.stop();
    L37:
        r1 = r1 + 1;
        goto L32
    L49:
        return;
    L39:
    L28:
        e = move-exception;
        FileLog.e(e);
        return;
    L8:
        if (this.subtitleIsThinkingBot == false) goto L10;
        int r84 = 0;
    L11:
        StatusDrawable r02 = this.statusDrawables[r84];     // Catch: Exception -> L28
        if (r02 == null) goto L48;
        SimpleTextView r3 = this.subtitleTextView;
        if (r84 != 5) goto L17;
        r3.replaceTextWithDrawable(r02, "**oo**");     // Catch: Exception -> L28
        this.statusDrawables[r84].setColor(getThemedColor(Theme.key_chat_status));     // Catch: Exception -> L28
        this.subtitleTextView.setLeftDrawable(null);     // Catch: Exception -> L28
    L18:
        this.currentTypingDrawable = this.statusDrawables[r84];     // Catch: Exception -> L28
    L19:
        StatusDrawable[] r03 = this.statusDrawables;     // Catch: Exception -> L28
        if (r1 >= r03.length) goto L50;
        StatusDrawable r04 = r03[r1];     // Catch: Exception -> L28
        if (r04 == null) goto L27;
        if (r1 != r84) goto L26;
        r04.start();     // Catch: Exception -> L28
        goto L27
    L26:
        r04.stop();     // Catch: Exception -> L28
    L27:
        r1 = r1 + 1;
        goto L19
    L50:
        return;
    L17:
        r3.replaceTextWithDrawable(null, null);     // Catch: Exception -> L28
        this.statusDrawables[r84].setColor(getThemedColor(Theme.key_chat_status));     // Catch: Exception -> L28
        this.subtitleTextView.setLeftDrawable(this.statusDrawables[r84]);     // Catch: Exception -> L28
        goto L18
    L48:
        return;
    L10:
        r84 = MessagesController.getInstance(this.currentAccount).getPrintingStringType(this.parentFragment.getDialogId(), this.parentFragment.getThreadId()).intValue();     // Catch: Exception -> L28
        goto L11
    }

    private void updateCurrentConnectionState() {
        int r0 = this.currentConnectionState;
        if (r0 != 2) goto L5;
        String r02 = LocaleController.getString(R.string.WaitingForNetwork);
    L14:
        if (r02 != null) goto L33;
        CharSequence r03 = this.lastSubtitle;
        if (r03 == null) goto L51;
        SimpleTextView r1 = this.subtitleTextView;
        if (r1 == null) goto L25;
        r1.setText(r03);
        this.lastSubtitle = null;
        Integer r04 = this.overrideSubtitleColor;
        if (r04 == null) goto L22;
        this.subtitleTextView.setTextColor(r04.intValue());
        goto L51
    L22:
        int r05 = this.lastSubtitleColorKey;
        if (r05 < 0) goto L51;
        this.subtitleTextView.setTextColor(getThemedColor(r05));
        this.subtitleTextView.setTag(Integer.valueOf(this.lastSubtitleColorKey));
        goto L51
    L25:
        AnimatedTextView r12 = this.animatedSubtitleTextView;
        if (r12 == null) goto L51;
        r12.setText(r03, !LocaleController.isRTL);
        this.lastSubtitle = null;
        Integer r06 = this.overrideSubtitleColor;
        if (r06 == null) goto L30;
        this.animatedSubtitleTextView.setTextColor(r06.intValue());
        goto L51
    L30:
        int r07 = this.lastSubtitleColorKey;
        if (r07 < 0) goto L51;
        this.animatedSubtitleTextView.setTextColor(getThemedColor(r07));
        this.animatedSubtitleTextView.setTag(Integer.valueOf(this.lastSubtitleColorKey));
    L51:
        checkActionBar(true);
        return;
    L33:
        SimpleTextView r13 = this.subtitleTextView;
        if (r13 != null) goto L36;
        AnimatedTextView r14 = this.animatedSubtitleTextView;
        if (r14 == null) goto L51;
        if (this.lastSubtitle != null) goto L47;
        this.lastSubtitle = r14.getText();
    L47:
        this.animatedSubtitleTextView.setText(r02, !LocaleController.isRTL);
        Integer r08 = this.overrideSubtitleColor;
        AnimatedTextView r15 = this.animatedSubtitleTextView;
        if (r08 == null) goto L50;
        r15.setTextColor(r08.intValue());
        goto L51
    L50:
        int r09 = Theme.key_actionBarDefaultSubtitle;
        r15.setTextColor(getThemedColor(r09));
        this.animatedSubtitleTextView.setTag(Integer.valueOf(r09));
        goto L51
    L36:
        if (this.lastSubtitle != null) goto L38;
        this.lastSubtitle = r13.getText();
    L38:
        this.subtitleTextView.setText(r02);
        Integer r010 = this.overrideSubtitleColor;
        SimpleTextView r16 = this.subtitleTextView;
        if (r010 == null) goto L41;
        r16.setTextColor(r010.intValue());
        goto L51
    L41:
        int r011 = Theme.key_actionBarDefaultSubtitle;
        r16.setTextColor(getThemedColor(r011));
        this.subtitleTextView.setTag(Integer.valueOf(r011));
        goto L51
    L5:
        if (r0 != 1) goto L8;
        r02 = LocaleController.getString(R.string.Connecting);
        goto L14
    L8:
        if (r0 != 5) goto L11;
        r02 = LocaleController.getString(R.string.Updating);
        goto L14
    L11:
        if (r0 != 4) goto L13;
        r02 = LocaleController.getString(R.string.ConnectingToProxy);
        goto L14
    L13:
        r02 = null;
        goto L14
    }

    public boolean canSearch() {
        return false;
    }

    public void checkAndUpdateAvatar() {
        ChatActivity r0 = this.parentFragment;
        if (r0 == null) goto L69;
        TLRPC.User r02 = r0.getCurrentUser();
        TLRPC.Chat r1 = this.parentFragment.getCurrentChat();
        if (this.parentFragment.getChatMode() != 3) goto L11;
        long r03 = this.parentFragment.getSavedDialogId();
        ChatActivity r7 = this.parentFragment;
        if (r03 < 0) goto L10;
        TLRPC.User r8 = r7.getMessagesController().getUser(Long.valueOf(r03));
        r1 = null;
    L13:
        if (r8 == null) goto L45;
        this.avatarDrawable.setInfo(this.currentAccount, r8);
        if (UserObject.isReplyUser(r8) == false) goto L21;
        this.avatarDrawable.setScaleSize(0.8f);
        this.avatarDrawable.setAvatarType(12);
        BackupImageView r04 = this.avatarImageView;
        if (r04 == null) goto L62;
        r04.setAnimatedEmojiDrawable(null);
        this.avatarImageView.setImage(null, null, this.avatarDrawable, r8);
        return;
    L62:
        return;
    L21:
        if (UserObject.isAnonymous(r8) == false) goto L27;
        this.avatarDrawable.setScaleSize(0.8f);
        this.avatarDrawable.setAvatarType(21);
        BackupImageView r05 = this.avatarImageView;
        if (r05 == null) goto L63;
        r05.setAnimatedEmojiDrawable(null);
        this.avatarImageView.setImage(null, null, this.avatarDrawable, r8);
        return;
    L63:
        return;
    L27:
        if (UserObject.isUserSelf(r8) == true) goto L29;
    L34:
        boolean r12 = UserObject.isUserSelf(r8);
        AvatarDrawable r3 = this.avatarDrawable;
        if (r12 == false) goto L40;
        r3.setScaleSize(0.8f);
        this.avatarDrawable.setAvatarType(1);
        BackupImageView r06 = this.avatarImageView;
        if (r06 == null) goto L65;
        r06.setAnimatedEmojiDrawable(null);
        this.avatarImageView.setImage(null, null, this.avatarDrawable, r8);
        return;
    L65:
        return;
    L40:
        r3.setScaleSize(1.0f);
        BackupImageView r07 = this.avatarImageView;
        if (r07 == null) goto L66;
        r07.setAnimatedEmojiDrawable(null);
        this.avatarImageView.imageReceiver.setForUserOrChat(r8, this.avatarDrawable, null, true, 3, false);
        return;
    L66:
        return;
    L29:
        if (this.parentFragment.getChatMode() != 3) goto L34;
        this.avatarDrawable.setScaleSize(0.8f);
        this.avatarDrawable.setAvatarType(22);
        BackupImageView r08 = this.avatarImageView;
        if (r08 == null) goto L64;
        r08.setAnimatedEmojiDrawable(null);
        this.avatarImageView.setImage(null, null, this.avatarDrawable, r8);
        return;
    L64:
        return;
    L45:
        if (ChatObject.isMonoForum(r1) == false) goto L57;
        long r72 = this.parentFragment.getTopicId();
        if (ChatObject.canManageMonoForum(this.currentAccount, r1) == true) goto L49;
    L54:
        this.avatarImageView.setAnimatedEmojiDrawable(null);
        ForumUtilities.setMonoForumAvatar(this.currentAccount, r1, this.avatarDrawable, this.avatarImageView);
    L55:
        setAvatarRadius(false);
        return;
    L49:
        if (r72 == 0) goto L54;
        ChatActivity r13 = this.parentFragment;
        if (r72 <= 0) goto L53;
        TLRPC.User r09 = r13.getMessagesController().getUser(Long.valueOf(r72));
        this.avatarDrawable.setInfo(r09);
        this.avatarImageView.setAnimatedEmojiDrawable(null);
        this.avatarImageView.setForUserOrChat(r09, this.avatarDrawable);
        goto L55
    L53:
        TLRPC.Chat r010 = r13.getMessagesController().getChat(Long.valueOf(-r72));
        this.avatarDrawable.setInfo(r010);
        this.avatarImageView.setAnimatedEmojiDrawable(null);
        this.avatarImageView.setForUserOrChat(r010, this.avatarDrawable);
        goto L55
    L57:
        if (r1 == null) goto L67;
        this.avatarDrawable.setScaleSize(1.0f);
        this.avatarDrawable.setInfo(this.currentAccount, r1);
        BackupImageView r011 = this.avatarImageView;
        if (r011 == null) goto L68;
        r011.setAnimatedEmojiDrawable(null);
        this.avatarImageView.setForUserOrChat(r1, this.avatarDrawable);
        setAvatarRadius(r1.forum);
        return;
    L68:
        return;
    L67:
        return;
    L10:
        r1 = r7.getMessagesController().getChat(Long.valueOf(-r03));
        r8 = null;
        goto L13
    L11:
        r8 = r02;
        goto L13
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.didUpdateConnectionState) goto L9;
        int r12 = ConnectionsManager.getInstance(this.currentAccount).getConnectionState();
        if (this.currentConnectionState == r12) goto L22;
        this.currentConnectionState = r12;
        updateCurrentConnectionState();
        return;
    L22:
        return;
    L9:
        if (r1 != NotificationCenter.emojiLoaded) goto L19;
        SimpleTextView r13 = this.titleTextView;
        if (r13 == null) goto L14;
        r13.invalidate();
    L14:
        if (getSubtitleTextView() == null) goto L16;
        getSubtitleTextView().invalidate();
    L16:
        invalidate();
        return;
    L19:
        if (r1 != NotificationCenter.savedMessagesDialogsUpdate) goto L23;
        updateSubtitle(true);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r6) {
        r6.save();
        float r0 = this.bounce.getScale(0.02f);
        r6.scale(r0, r0, getPivotX(), getHeight() - (ActionBar.getCurrentActionBarHeight() / 2.0f));
        super.dispatchDraw(r6);
        r6.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.ignoreTouches == false) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r2);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r9, View r10, long r11) {
        if (r10 != this.avatarImageView) goto L25;
        ImageView r0 = this.timeItem;
        boolean r1 = false;
        if (r0 != null) goto L7;
    L9:
        boolean r02 = false;
    L10:
        ImageView r3 = this.communityItem;
        if (r3 != null) goto L13;
    L15:
        if (r02 == true) goto L17;
        if (r1 == false) goto L25;
    L17:
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(r10.getX(), r10.getY(), r10.getX() + r10.getWidth(), r10.getY() + r10.getHeight());
        r2.inset(-AndroidUtilities.dp(3.0f), -AndroidUtilities.dp(3.0f));
        r9.saveLayer(r2, null);
        boolean r102 = super.drawChild(r9, r10, r11);
        if (r02 == false) goto L20;
        float r12 = this.timeItem.getX() + (this.timeItem.getWidth() / 2.0f);
        float r03 = this.timeItem.getY() + (this.timeItem.getHeight() / 2.0f);
        r9.drawCircle(r12, r03 - AndroidUtilities.dpf2(0.33f), AndroidUtilities.dpf2(12.0f) * this.timeItem.getScaleX(), Theme.PAINT_CLEAR);
    L20:
        if (r1 == false) goto L22;
        r9.drawCircle(this.communityItem.getX() + (this.communityItem.getWidth() / 2.0f), this.communityItem.getY() + (this.communityItem.getHeight() / 2.0f), AndroidUtilities.dpf2(7.66f) * this.communityItem.getScaleX(), Theme.PAINT_CLEAR);
    L22:
        r9.restore();
        return r102;
    L13:
        if (r3.getVisibility() != 0) goto L15;
        r1 = true;
        goto L15
    L7:
        if (r0.getVisibility() != 0) goto L9;
        r02 = true;
    L25:
        return super.drawChild(r9, r10, r11);
    }

    public BackupImageView getAvatarImageView() {
        return this.avatarImageView;
    }

    public AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable getBotVerificationDrawable(long r3, boolean r5) {
        if (r3 != 0) goto L6;
        return null;
    L6:
        this.botVerificationDrawable.set(r3, r5);
        this.botVerificationDrawable.setColor(Integer.valueOf(getThemedColor(Theme.key_profile_verifiedBackground)));
        this.botVerificationDrawable.offset(0, AndroidUtilities.dp(1.0f));
        return this.botVerificationDrawable;
    }

    public int getGlassPillContentLeft(int r3) {
        if (hasVisibleAvatar() == true) goto L5;
    L14:
        int r32 = AndroidUtilities.dp(3.0f);
        int r2 = this.leftPadding;
    L13:
        return r32 - r2;
    L5:
        if (this.avatarImageView.getMeasuredWidth() == 0) goto L14;
        if (ChatHeaderUiHelper.isMaterial3ChatHeaderStyle() == false) goto L10;
        r32 = AndroidUtilities.dp(3.0f);
    L11:
        r2 = this.avatarImageView.getLeft();
        goto L13
    L10:
        r32 = Math.round((r3 - this.avatarImageView.getMeasuredWidth()) / 2.0f);
        goto L11
    }

    public int getLastSubtitleColorKey() {
        return this.lastSubtitleColorKey;
    }

    public int getLeftPadding() {
        return this.leftPadding;
    }

    public SharedMediaLayout.SharedMediaPreloader getSharedMediaPreloader() {
        return this.sharedMediaPreloader;
    }

    public TextPaint getSubtitlePaint() {
        SimpleTextView r0 = this.subtitleTextView;
        if (r0 == null) goto L7;
        return r0.getTextPaint();
    L7:
        return this.animatedSubtitleTextView.getPaint();
    }

    public View getSubtitleTextView() {
        SimpleTextView r0 = this.subtitleTextView;
        if (r0 == null) goto L5;
        return r0;
    L5:
        AnimatedTextView r1 = this.animatedSubtitleTextView;
        if (r1 == null) goto L8;
        return r1;
    L8:
        return null;
    }

    public ImageView getTimeItem() {
        return this.timeItem;
    }

    public SimpleTextView getTitleTextView() {
        return this.titleTextView;
    }

    public int getVisualWidth() {
        SimpleTextView r0 = this.titleTextView;
        float r1 = 0.0f;
        if (r0 == null) goto L5;
        r1 = Math.max(0.0f, r0.getExactWidthIncludeDrawables());
    L5:
        SimpleTextView r02 = this.subtitleTextView;
        if (r02 == null) goto L9;
        r1 = Math.max(r1, r02.getExactWidthIncludeDrawables());
    L9:
        if (hasVisibleAvatar() == false) goto L12;
        int r2 = AndroidUtilities.dp((this.avatarSizeInDp + 16) + ChatHeaderUiHelper.getGlassPillExtraRightPaddingDp());
    L14:
        return (int) (r1 + r2);
    L12:
        r2 = AndroidUtilities.dp(34.0f);
        goto L14
    }

    public boolean hasVisibleAvatar() {
        BackupImageView r0 = this.avatarImageView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public void hideSubtitle() {
        if (getSubtitleTextView() == null) goto L6;
        getSubtitleTextView().setVisibility(8);
        return;
    }

    public void hideTimeItem(boolean r2) {
        this.animatorTimeVisible.setValue(false, r2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.parentFragment == null) goto L8;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didUpdateConnectionState);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        if (this.parentFragment.getChatMode() != 3) goto L7;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.savedMessagesDialogsUpdate);
    L7:
        this.currentConnectionState = ConnectionsManager.getInstance(this.currentAccount).getConnectionState();
        updateCurrentConnectionState();
    L8:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.emojiStatusDrawable;
        if (r0 == null) goto L11;
        r0.attach();
    L11:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r02 = this.emojiStatusDrawable2;
        if (r02 == null) goto L14;
        r02.attach();
    L14:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2 = this.botVerificationDrawable;
        if (r2 == null) goto L18;
        r2.attach();
        return;
    }

    public boolean onAvatarClick() {
        return false;
    }

    public void onDestroy() {
        onDestroy(false);
    }

    public void onDestroy(boolean r2) {
        SharedMediaLayout.SharedMediaPreloader r0 = this.sharedMediaPreloader;
        if (r0 == null) goto L6;
        r0.onDestroy(this.parentFragment, r2);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.parentFragment == null) goto L7;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didUpdateConnectionState);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        if (this.parentFragment.getChatMode() != 3) goto L7;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.savedMessagesDialogsUpdate);
    L7:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.emojiStatusDrawable;
        if (r0 == null) goto L10;
        r0.detach();
    L10:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r02 = this.emojiStatusDrawable2;
        if (r02 == null) goto L13;
        r02.detach();
    L13:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2 = this.botVerificationDrawable;
        if (r2 == null) goto L17;
        r2.detach();
        return;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L11;
        ImageView r12 = this.timeItem;
        if (r12 == null) goto L12;
        r12.setAlpha(r2);
        float r32 = 0.85f * r2;
        this.timeItem.setScaleX(r32);
        this.timeItem.setScaleY(r32);
        ImageView r0 = this.timeItem;
        if (r2 <= 0.0f) goto L8;
        int r13 = 0;
    L9:
        r0.setVisibility(r13);
        return;
    L8:
        r13 = 8;
        goto L9
    L12:
        return;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        StringBuilder r0 = new StringBuilder();
        r0.append(this.titleTextView.getText());
        if (this.rightDrawableContentDescription == null) goto L6;
        r0.append(", ");
        r0.append(this.rightDrawableContentDescription);
    L6:
        if (this.rightDrawable2ContentDescription == null) goto L8;
        r0.append(", ");
        r0.append(this.rightDrawable2ContentDescription);
    L8:
        r0.append("\n");
        SimpleTextView r1 = this.subtitleTextView;
        if (r1 == null) goto L11;
        r0.append(r1.getText());
    L14:
        r4.setContentDescription(r0);
        if (r4.isClickable() == false) goto L18;
        r4.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, LocaleController.getString(R.string.OpenProfile)));
    L18:
        if (r4.isLongClickable() == false) goto L21;
        r4.addAction(new AccessibilityNodeInfo.AccessibilityAction(32, LocaleController.getString(R.string.Search)));
        return;
    L21:
        return;
    L11:
        AnimatedTextView r3 = this.animatedSubtitleTextView;
        if (r3 == null) goto L14;
        r0.append(r3.getText());
        goto L14
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r9, int r10, int r11, int r12, int r13) {
        int r92 = ActionBar.getCurrentActionBarHeight();
        boolean r102 = ChatHeaderUiHelper.isMaterial3ChatHeaderStyle();
        int r112 = ChatHeaderUiHelper.getAvatarInsetPx();
        int r122 = 0;
        if (r102 == false) goto L5;
        int r132 = 0;
    L6:
        if (r102 == false) goto L8;
    L14:
        int r0 = 0;
    L15:
        int r93 = ((r92 - this.avatarImageView.getMeasuredHeight()) - (r112 * 2)) / 2;
        if (this.occupyStatusBar == false) goto L18;
        int r1 = AndroidUtilities.statusBarHeight;
    L19:
        int r94 = r93 + r1;
        boolean r14 = this.glassMode;
        if (r102 == false) goto L29;
        if (r14 == false) goto L23;
        float r103 = 26.66f;
    L24:
        float r2 = this.avatarSizeInDp;
        if (r14 == false) goto L27;
        float r15 = 10.66f;
    L28:
        float r22 = r2 + r15;
    L36:
        int r104 = AndroidUtilities.dp(r103) + r94;
        BackupImageView r16 = this.avatarImageView;
        int r3 = r132 + r112;
        int r4 = this.leftPadding;
        int r6 = r112 + r94;
        r16.layout(r3 + r4, r6, (r3 + r4) + r16.getMeasuredWidth(), this.avatarImageView.getMeasuredHeight() + r6);
        int r17 = this.leftPadding;
        if (this.avatarImageView.getVisibility() != 0) goto L40;
        int r113 = AndroidUtilities.dp(r22) + r0;
    L43:
        int r18 = (r17 + r113) + this.rightAvatarPadding;
        SimpleTextView r114 = this.titleTextLargerCopyView.get();
        int r123 = getSubtitleTextView().getVisibility();
        SimpleTextView r02 = this.titleTextView;
        if (r123 == 8) goto L48;
        r02.layout(r18, (AndroidUtilities.dp(1.66f) + r94) - this.titleTextView.getPaddingTop(), this.titleTextView.getMeasuredWidth() + r18, (((this.titleTextView.getTextHeight() + r94) + AndroidUtilities.dp(1.66f)) - this.titleTextView.getPaddingTop()) + this.titleTextView.getPaddingBottom());
        if (r114 == null) goto L51;
        r114.layout(r18, AndroidUtilities.dp(1.66f) + r94, r114.getMeasuredWidth() + r18, (r114.getTextHeight() + r94) + AndroidUtilities.dp(1.66f));
    L51:
        float r115 = this.avatarSizeInDp - 42.0f;
        if (this.communityItem == null) goto L55;
        int r124 = (this.leftPadding + r132) + AndroidUtilities.dp(29.0f + r115);
        int r03 = AndroidUtilities.dp(27.33f + r115) + r94;
        ImageView r23 = this.communityItem;
        r23.layout(r124, r03, r23.getMeasuredWidth() + r124, this.communityItem.getMeasuredHeight() + r03);
    L55:
        if (this.timeItem == null) goto L58;
        int r125 = (this.leftPadding + r132) + AndroidUtilities.dp(r115 + 19.333f);
        int r116 = r94 - AndroidUtilities.dp(8.0f);
        ImageView r04 = this.timeItem;
        r04.layout(r125, r116, r04.getMeasuredWidth() + r125, this.timeItem.getMeasuredHeight() + r116);
    L58:
        if (this.starBgItem == null) goto L61;
        int r117 = (this.leftPadding + r132) + AndroidUtilities.dp(this.avatarSizeInDp - 14);
        int r126 = AndroidUtilities.dp(this.avatarSizeInDp - 18) + r94;
        ImageView r05 = this.starBgItem;
        r05.layout(r117, r126, r05.getMeasuredWidth() + r117, this.starBgItem.getMeasuredHeight() + r126);
    L61:
        if (this.starFgItem == null) goto L63;
        int r133 = (r132 + this.leftPadding) + AndroidUtilities.dp(this.avatarSizeInDp - 14);
        int r95 = r94 + AndroidUtilities.dp(this.avatarSizeInDp - 18);
        ImageView r118 = this.starFgItem;
        r118.layout(r133, r95, r118.getMeasuredWidth() + r133, this.starFgItem.getMeasuredHeight() + r95);
    L63:
        SimpleTextView r96 = this.subtitleTextView;
        if (r96 == null) goto L66;
        r96.layout(r18, r104, r96.getMeasuredWidth() + r18, this.subtitleTextView.getTextHeight() + r104);
    L69:
        SimpleTextView r8 = this.subtitleTextLargerCopyView.get();
        if (r8 == null) goto L73;
        r8.layout(r18, r104, r8.getMeasuredWidth() + r18, r8.getTextHeight() + r104);
        return;
    L73:
        return;
    L66:
        AnimatedTextView r97 = this.animatedSubtitleTextView;
        if (r97 == null) goto L69;
        r97.layout(r18, r104, r97.getMeasuredWidth() + r18, this.animatedSubtitleTextView.getTextHeight() + r104);
        goto L69
    L48:
        r02.layout(r18, (AndroidUtilities.dp(11.0f) + r94) - this.titleTextView.getPaddingTop(), this.titleTextView.getMeasuredWidth() + r18, (((this.titleTextView.getTextHeight() + r94) + AndroidUtilities.dp(11.0f)) - this.titleTextView.getPaddingTop()) + this.titleTextView.getPaddingBottom());
        if (r114 == null) goto L51;
        r114.layout(r18, AndroidUtilities.dp(10.0f) + r94, r114.getMeasuredWidth() + r18, (r114.getTextHeight() + r94) + AndroidUtilities.dp(10.0f));
        goto L51
    L40:
        if (this.glassMode == false) goto L42;
        r122 = 12;
    L42:
        r113 = AndroidUtilities.dp(r122 + r112);
        goto L43
    L27:
        r15 = 12.0f;
        goto L28
    L23:
        r103 = 27.0f;
        goto L24
    L29:
        if (r14 == false) goto L31;
        r103 = 23.66f;
    L32:
        if (r14 == false) goto L35;
        float r19 = 49.66f;
    L34:
        r22 = r19;
        goto L36
    L35:
        r19 = 55.0f;
        goto L34
    L31:
        r103 = 24.0f;
        goto L32
    L18:
        r1 = 0;
        goto L19
    L8:
        if (this.glassMode == false) goto L14;
        if ((getParent() instanceof ActionBar) == false) goto L14;
        int r06 = ((ActionBar) getParent()).getGlassMiddlePillChildLeft(this.avatarImageView.getMeasuredWidth());
        if (r06 < 0) goto L14;
        int r07 = ((r06 - getLeft()) - this.leftPadding) - r112;
        r0 = r07 - r132;
        r132 = r07;
        goto L15
    L5:
        r132 = AndroidUtilities.dp(1.0f);
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        int r82 = View.MeasureSpec.getSize(r8);
        int r1 = 0;
        if (this.avatarImageView.getVisibility() != 0) goto L5;
        int r0 = this.avatarSizeInDp + 12;
    L6:
        int r02 = r82 - AndroidUtilities.dp(r0 + 16);
        int r2 = ChatHeaderUiHelper.getAvatarSizePx(this.avatarSizeInDp);
        this.avatarImageView.measure(View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30));
        this.titleTextView.measure(View.MeasureSpec.makeMeasureSpec(r02, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), Integer.MIN_VALUE));
        SimpleTextView r22 = this.subtitleTextView;
        if (r22 == null) goto L9;
        r22.measure(View.MeasureSpec.makeMeasureSpec(r02, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), Integer.MIN_VALUE));
    L12:
        ImageView r03 = this.communityItem;
        if (r03 == null) goto L15;
        r03.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(14.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(14.0f), TLObject.FLAG_30));
    L15:
        ImageView r04 = this.timeItem;
        if (r04 == null) goto L18;
        r04.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(34.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(34.0f), TLObject.FLAG_30));
    L18:
        ImageView r05 = this.starBgItem;
        if (r05 == null) goto L21;
        r05.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
    L21:
        ImageView r06 = this.starFgItem;
        if (r06 == null) goto L24;
        r06.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
    L24:
        setMeasuredDimension(r82, View.MeasureSpec.getSize(r9));
        int r92 = this.lastWidth;
        if (r92 == (-1)) goto L29;
        if (r92 == r82) goto L29;
        if (r92 <= r82) goto L29;
        fadeOutToLessWidth(r92);
    L29:
        SimpleTextView r93 = this.titleTextLargerCopyView.get();
        if (r93 == null) goto L35;
        int r07 = this.largerWidth;
        if (this.avatarImageView.getVisibility() != 0) goto L34;
        r1 = this.avatarSizeInDp + 12;
    L34:
        r93.measure(View.MeasureSpec.makeMeasureSpec(r07 - AndroidUtilities.dp(r1 + 16), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), Integer.MIN_VALUE));
    L35:
        this.lastWidth = r82;
        return;
    L9:
        AnimatedTextView r23 = this.animatedSubtitleTextView;
        if (r23 == null) goto L12;
        r23.measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), Integer.MIN_VALUE));
        goto L12
    L5:
        r0 = 0;
        goto L6
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        if (r6.getAction() != 0) goto L9;
        if (canSearch() == false) goto L9;
        this.pressed = true;
        this.bounce.setPressed(true);
        AndroidUtilities.cancelRunOnUIThread(this.onLongClick);
        AndroidUtilities.runOnUIThread(this.onLongClick, ViewConfiguration.getLongPressTimeout());
        return true;
    L9:
        if (r6.getAction() != 2) goto L22;
        if (this.pressed == false) goto L34;
        float r0 = r6.getX();
        float r1 = r6.getY();
        if (r0 >= 0.0f) goto L15;
    L20:
        this.bounce.setPressed(false);
        this.pressed = false;
        AndroidUtilities.cancelRunOnUIThread(this.onLongClick);
        goto L34
    L15:
        if (r1 < 0.0f) goto L20;
        if (r0 > getWidth()) goto L20;
        if (r1 > getHeight()) goto L20;
    L34:
        return super.onTouchEvent(r6);
    L22:
        if (r6.getAction() == 1) goto L26;
        if (r6.getAction() != 3) goto L34;
    L26:
        if (this.pressed == false) goto L34;
        this.bounce.setPressed(false);
        this.pressed = false;
        if (r6.getAction() == 1) goto L30;
    L32:
        AndroidUtilities.cancelRunOnUIThread(this.onLongClick);
        goto L34
    L30:
        if (isClickable() == false) goto L32;
        openProfile(false);
        goto L32
    }

    public void openProfile(boolean r3) {
        openProfile(r3, true, false);
    }

    public void openProfile(boolean r19, boolean r20, boolean r21) {
        if (r19 == true) goto L5;
    L11:
        boolean r3 = r19;
    L12:
        TLRPC.User r4 = this.parentFragment.getCurrentUser();
        TLRPC.Chat r5 = this.parentFragment.getCurrentChat();
        int r6 = 1;
        if (r5 != null) goto L15;
    L17:
        boolean r7 = false;
    L19:
        if (r5 != null) goto L21;
    L30:
        ImageReceiver r10 = this.avatarImageView.getImageReceiver();
        String r11 = r10.getImageKey();
        ImageLoader r12 = ImageLoader.getInstance();
        if (r11 != null) goto L33;
    L39:
        ChatActivity r2 = this.parentFragment;
        if (r2.isComments == false) goto L46;
        if (r5 == null) goto L100;
        r2.presentFragment(ProfileActivity.of(-r5.id), r21);
        return;
    L100:
        return;
    L46:
        if (r4 != null) goto L48;
        if (r5 == null) goto L98;
        Bundle r22 = new Bundle();
        r22.putLong("chat_id", r5.id);
        int r42 = this.parentFragment.getChatMode();
        ChatActivity r52 = this.parentFragment;
        if (r42 != 3) goto L87;
        r22.putLong("topic_id", r52.getSavedDialogId());
    L89:
        r22.putInt("navBarColor", this.parentFragment.getNavigationBarColor());
        ProfileActivity r43 = new ProfileActivity(r22, this.sharedMediaPreloader);
        if (r7 == true) goto L92;
        r43.setChatInfo(this.parentFragment.getCurrentChatInfo());
    L92:
        if (r20 == false) goto L96;
        if (r3 == false) goto L95;
        r6 = 2;
    L95:
        r43.setPlayProfileAnimation(r6);
    L96:
        this.parentFragment.presentFragment(r43, r21);
        return;
    L87:
        if (r52.isTopic == false) goto L89;
        r22.putLong("topic_id", r52.getThreadMessage().getId());
        goto L89
    L98:
        return;
    L48:
        if (r4.id == UserObject.VERIFY) goto L101;
        Bundle r53 = new Bundle();
        if (UserObject.isUserSelf(r4) == false) goto L61;
        if (this.sharedMediaPreloader.hasSharedMedia() == false) goto L102;
        r53.putLong("dialog_id", this.parentFragment.getDialogId());
        if (this.parentFragment.getChatMode() != 3) goto L58;
        r53.putLong("topic_id", this.parentFragment.getSavedDialogId());
    L58:
        MediaActivity r23 = new MediaActivity(r53, this.sharedMediaPreloader);
        r23.setChatInfo(this.parentFragment.getCurrentChatInfo());
        this.parentFragment.presentFragment(r23, r21);
        return;
    L102:
        return;
    L61:
        if (this.parentFragment.getChatMode() != 3) goto L66;
        long r14 = this.parentFragment.getSavedDialogId();
        r53.putBoolean("saved", true);
        if (r14 < 0) goto L65;
        r53.putLong("user_id", r14);
    L71:
        if (UserObject.isBotForum(r4) == false) goto L73;
        r53.putLong("topic_id", this.parentFragment.getTopicId());
    L73:
        r53.putBoolean("reportSpam", this.parentFragment.hasReportSpam());
        r53.putInt("actionBarColor", getThemedColor(Theme.key_actionBarDefault));
        r53.putInt("navBarColor", this.parentFragment.getNavigationBarColor());
        ProfileActivity r24 = new ProfileActivity(r53, this.sharedMediaPreloader);
        if (r7 == true) goto L76;
        TLRPC.UserFull r44 = this.parentFragment.getCurrentUserInfo();
        ChatActivity r54 = this.parentFragment;
        r24.setUserInfo(r44, r54.profileChannelMessageFetcher, r54.birthdayAssetsFetcher);
    L76:
        if (r20 == false) goto L80;
        if (r3 == false) goto L79;
        r6 = 2;
    L79:
        r24.setPlayProfileAnimation(r6);
    L80:
        this.parentFragment.presentFragment(r24, r21);
        return;
    L65:
        r53.putLong("chat_id", -r14);
        goto L71
    L66:
        r53.putLong("user_id", r4.id);
        if (this.timeItem == null) goto L71;
        if (r7 == true) goto L71;
        r53.putLong("dialog_id", this.parentFragment.getDialogId());
        goto L71
    L101:
        return;
    L33:
        if (r12.isInMemCache(r11, false) == true) goto L39;
        Drawable r102 = r10.getDrawable();
        if ((r102 instanceof BitmapDrawable) == false) goto L39;
        if ((r102 instanceof AnimatedFileDrawable) == true) goto L39;
        r12.putImageToCache((BitmapDrawable) r102, r11, false);
        goto L39
    L21:
        if (r5.monoforum == false) goto L30;
        r5 = this.parentFragment.getMessagesController().getChat(Long.valueOf(r5.linked_monoforum_id));
        if (r5 != null) goto L26;
        return;
    L26:
        if (this.parentFragment.getSendMonoForumPeerId() == 0) goto L30;
        TLRPC.User r103 = this.parentFragment.getMessagesController().getUser(Long.valueOf(this.parentFragment.getSendMonoForumPeerId()));
        if (r103 == null) goto L30;
        r5 = null;
        r4 = r103;
        goto L30
    L15:
        if (r5.monoforum == false) goto L17;
        r7 = true;
        goto L19
    L5:
        if (AndroidUtilities.isTablet() == true) goto L10;
        Point r32 = AndroidUtilities.displaySize;
        if (r32.x > r32.y) goto L10;
        if (this.avatarImageView.getImageReceiver().hasNotThumb() == true) goto L11;
    L10:
        r3 = false;
        goto L12
    }

    public void openSearch() {
    }

    public boolean openSetTimer() {
        if (this.parentFragment.getParentActivity() != null) goto L5;
        return false;
    L5:
        TLRPC.Chat r0 = this.parentFragment.getCurrentChat();
        if (r0 != null) goto L8;
    L13:
        TLRPC.ChatFull r02 = this.parentFragment.getCurrentChatInfo();
        TLRPC.UserFull r2 = this.parentFragment.getCurrentUserInfo();
        if (r2 == null) goto L16;
        int r03 = r2.ttl_period;
    L19:
        AutoDeletePopupWrapper r4 = new AutoDeletePopupWrapper(getContext(), null, new AnonymousClass2(this, r3), true, 0, this.resourcesProvider);
        r4.updateItems(r03);
        AnonymousClass3 r04 = new AnonymousClass3(this, r4.windowLayout, -2, -2);
        ActionBarPopupWindow[] r3 = {r04};
        r04.setPauseNotifications(true);
        r3[0].setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        r3[0].setOutsideTouchable(true);
        r3[0].setClippingEnabled(true);
        r3[0].setAnimationStyle(R.style.PopupContextAnimation);
        r3[0].setFocusable(true);
        r4.windowLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        r3[0].setInputMethodMode(2);
        r3[0].getContentView().setFocusableInTouchMode(true);
        ActionBarPopupWindow r05 = r3[0];
        BackupImageView r32 = this.avatarImageView;
        r05.showAtLocation(r32, 0, (int) (r32.getX() + getX()), (int) this.avatarImageView.getY());
        this.parentFragment.dimBehindView(true);
        return true;
    L16:
        if (r02 == null) goto L18;
        r03 = r02.ttl_period;
        goto L19
    L18:
        r03 = 0;
        goto L19
    L8:
        if (ChatObject.canUserDoAdminAction(r0, 13) == true) goto L13;
        if (this.animatorTimeVisible.getValue() == false) goto L12;
        this.parentFragment.showTimerHint();
    L12:
        return false;
    }

    public void setActionBar(ActionBar r1) {
        this.actionBar = r1;
    }

    public void setAvatarSizeInDp(int r2) {
        if (this.avatarSizeInDp == r2) goto L6;
        this.avatarSizeInDp = r2;
        setAvatarRadius(this.avatarIsForum);
        requestLayout();
        return;
    }

    public void setChatAvatar(TLRPC.Chat r3) {
        this.avatarDrawable.setInfo(this.currentAccount, r3);
        BackupImageView r0 = this.avatarImageView;
        if (r0 == null) goto L12;
        r0.setForUserOrChat(r3, this.avatarDrawable);
        if (r3 != null) goto L7;
    L9:
        boolean r32 = false;
    L10:
        setAvatarRadius(r32);
        return;
    L7:
        if (r3.forum == false) goto L9;
        r32 = true;
        goto L10
    }

    public void setCommunityItemVisible(boolean r2) {
        ImageView r0 = this.communityItem;
        if (r0 == null) goto L11;
        if (r2 == true) goto L6;
    L8:
        int r1 = 8;
    L9:
        r0.setVisibility(r1);
        return;
    L6:
        if (this.avatarImageIsHidden == true) goto L8;
        r1 = 0;
        goto L9
    }

    public void setFeedAvatar() {
        this.avatarDrawable.setInfo(UserConfig.getInstance(this.currentAccount).getClientUserId());
        this.avatarDrawable.setAvatarType(1);
        this.avatarDrawable.setCustomIcon(Theme.avatarDrawables[25]);
        BackupImageView r0 = this.avatarImageView;
        if (r0 == null) goto L6;
        r0.setImage(null, null, this.avatarDrawable, null);
        return;
    }

    public void setGlassMode() {
        SimpleTextView r0 = this.titleTextView;
        if (r0 == null) goto L5;
        r0.setTextSizePx(AndroidUtilities.dp(17.5f));
    L5:
        SimpleTextView r02 = this.subtitleTextView;
        if (r02 == null) goto L8;
        r02.setTextSizePx(AndroidUtilities.dp(13.5f));
    L8:
        this.glassMode = true;
    }

    public void setLeftPadding(int r1) {
        this.leftPadding = r1;
    }

    public void setOccupyStatusBar(boolean r1) {
        this.occupyStatusBar = r1;
    }

    public void setOverrideSubtitleColor(Integer r1) {
        this.overrideSubtitleColor = r1;
    }

    public void setRightAvatarPadding(int r1) {
        this.rightAvatarPadding = r1;
    }

    public void setStars(boolean r7, boolean r8) {
        ImageView r0 = this.starBgItem;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.starFgItem == null) goto L72;
        this.stars = r7;
        float r1 = 1.1f;
        int r2 = 0;
        float r3 = 1.0f;
        if (r8 == false) goto L10;
        if (r7 == false) goto L45;
        r0.setVisibility(0);
        this.starFgItem.setVisibility(0);
    L45:
        ViewPropertyAnimator r82 = this.starBgItem.animate();
        if (r7 == false) goto L48;
        float r02 = 1.0f;
    L49:
        ViewPropertyAnimator r83 = r82.alpha(r02);
        if (r7 == false) goto L52;
        float r03 = 1.1f;
    L53:
        ViewPropertyAnimator r84 = r83.scaleX(r03);
        if (r7 == true) goto L57;
        r1 = 0.0f;
    L57:
        r84.scaleY(r1).withEndAction(new ChatAvatarContainer$$ExternalSyntheticLambda2(this, r7)).start();
        ViewPropertyAnimator r85 = this.starFgItem.animate();
        if (r7 == false) goto L60;
        float r04 = 1.0f;
    L61:
        ViewPropertyAnimator r86 = r85.alpha(r04);
        if (r7 == false) goto L64;
        float r05 = 1.0f;
    L65:
        ViewPropertyAnimator r87 = r86.scaleX(r05);
        if (r7 == true) goto L69;
        r3 = 0.0f;
    L69:
        r87.scaleY(r3).withEndAction(new ChatAvatarContainer$$ExternalSyntheticLambda3(this, r7)).start();
        return;
    L64:
        r05 = 0.0f;
        goto L65
    L60:
        r04 = 0.0f;
        goto L61
    L52:
        r03 = 0.0f;
        goto L53
    L48:
        r02 = 0.0f;
        goto L49
    L10:
        if (r7 == false) goto L12;
        int r5 = 0;
    L13:
        r0.setVisibility(r5);
        ImageView r06 = this.starBgItem;
        if (r7 == false) goto L16;
        float r52 = 1.0f;
    L17:
        r06.setAlpha(r52);
        ImageView r07 = this.starBgItem;
        if (r7 == false) goto L20;
        float r53 = 1.1f;
    L21:
        r07.setScaleX(r53);
        ImageView r08 = this.starBgItem;
        if (r7 == true) goto L25;
        r1 = 0.0f;
    L25:
        r08.setScaleY(r1);
        ImageView r09 = this.starFgItem;
        if (r7 == true) goto L29;
        r2 = 4;
    L29:
        r09.setVisibility(r2);
        ImageView r88 = this.starFgItem;
        if (r7 == false) goto L32;
        float r010 = 1.0f;
    L33:
        r88.setAlpha(r010);
        ImageView r89 = this.starFgItem;
        if (r7 == false) goto L36;
        float r011 = 1.0f;
    L37:
        r89.setScaleX(r011);
        ImageView r6 = this.starFgItem;
        if (r7 == true) goto L41;
        r3 = 0.0f;
    L41:
        r6.setScaleY(r3);
        return;
    L36:
        r011 = 0.0f;
        goto L37
    L32:
        r010 = 0.0f;
        goto L33
    L20:
        r53 = 0.0f;
        goto L21
    L16:
        r52 = 0.0f;
        goto L17
    L12:
        r5 = 4;
        goto L13
    }

    public void setStoriesForceState(Integer r1) {
        this.storiesForceState = r1;
    }

    public void setSubtitle(CharSequence r2) {
        if (this.lastSubtitle != null) goto L10;
        SimpleTextView r0 = this.subtitleTextView;
        if (r0 == null) goto L7;
        r0.setText(r2);
    L11:
        checkActionBar(true);
        return;
    L7:
        AnimatedTextView r02 = this.animatedSubtitleTextView;
        if (r02 == null) goto L11;
        r02.setText(r2);
        goto L11
    L10:
        this.lastSubtitle = r2;
        goto L11
    }

    public void setTime(int r3, boolean r4) {
        if (this.timerDrawable != null) goto L5;
        return;
    L5:
        boolean r0 = this.stars;
        if (r3 == 0) goto L8;
    L10:
        if (r0 == true) goto L13;
        showTimeItem(r4);
        this.timerDrawable.setTime(r3);
        return;
    L13:
        hideTimeItem(r4);
        return;
    L8:
        if (this.secretChatTimer == true) goto L10;
    }

    public void setTitle(CharSequence r10) {
        setTitle(r10, false, false, false, false, null, null, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTitle(CharSequence r7, boolean r8, boolean r9, boolean r10, boolean r11, BadgeDTO r12, TLRPC.EmojiStatus r13, boolean r14) {
        if (r7 == null) goto L5;
        r7 = Emoji.replaceEmoji(r7, this.titleTextView.getPaint().getFontMetricsInt(), false);
    L5:
        this.titleTextView.setText(r7);
        Drawable r72 = this.titleTextView.getRightDrawable();
        if ((r72 instanceof AnimatedEmojiDrawable.WrapSizeDrawable) == false) goto L10;
        Drawable r73 = ((AnimatedEmojiDrawable.WrapSizeDrawable) r72).getDrawable();
        if ((r73 instanceof AnimatedEmojiDrawable) == false) goto L10;
        ((AnimatedEmojiDrawable) r73).removeView(this.titleTextView);
    L10:
        Drawable r74 = this.titleTextView.getRightDrawable2();
        if ((r74 instanceof AnimatedEmojiDrawable.WrapSizeDrawable) == false) goto L15;
        Drawable r75 = ((AnimatedEmojiDrawable.WrapSizeDrawable) r74).getDrawable();
        if ((r75 instanceof AnimatedEmojiDrawable) == false) goto L15;
        ((AnimatedEmojiDrawable) r75).removeView(this.titleTextView);
    L15:
        Drawable r76 = null;
        this.rightDrawableContentDescription = null;
        this.rightDrawable2ContentDescription = null;
        this.rightDrawableIsScamOrVerified = false;
        if (r8 == true) goto L20;
        if (r9 == true) goto L20;
        boolean r2 = false;
    L21:
        this.rightDrawableIsScam = r2;
        this.titleTextView.setRightDrawableTopPadding(0);
        if (DialogObject.getEmojiStatusDocumentId(r13) == 0) goto L24;
        Object[] r22 = true;
    L25:
        ArrayList r3 = new ArrayList();
        if (r8 == true) goto L28;
        if (r9 == true) goto L28;
    L29:
        if (r10 == true) goto L31;
    L34:
        if (r12 == null) goto L41;
        this.emojiStatusDrawable2.set(r12.getDocumentId(), r14);
        this.emojiStatusDrawable2.setParticles(true, false);
        this.emojiStatusDrawable2.setColor(Integer.valueOf(getThemedColor(Theme.key_profile_verifiedBackground)));
        if (r22 == true) goto L38;
    L40:
        r3.add(this.emojiStatusDrawable2);
        this.rightDrawableIsScamOrVerified = true;
        goto L41
    L38:
        if (r3.isEmpty() == true) goto L40;
        r3.remove(r3.size() - 1);
    L41:
        if (r22 == false) goto L45;
        this.emojiStatusDrawable.set(DialogObject.getEmojiStatusDocumentId(r13), r14);
        this.emojiStatusDrawable.setColor(Integer.valueOf(getThemedColor(Theme.key_profile_verifiedBackground)));
        Drawable r82 = this.emojiStatusDrawable;
        if (r3.isEmpty() == true) goto L61;
        r76 = (Drawable) r3.get(0);
    L61:
        if (this.rightDrawableIsScamOrVerified == true) goto L65;
        Drawable r92 = this.mutedIcon;
        if (r92 == null) goto L65;
        this.rightDrawable2ContentDescription = LocaleController.getString(R.string.NotificationsMuted);
        r76 = r92;
    L65:
        this.titleTextView.setRightDrawable(r82);
        this.titleTextView.setRightDrawable2(r76);
        checkActionBar(r14);
        return;
    L45:
        if (r11 == false) goto L54;
        if (r12 == null) goto L50;
        r82 = this.emojiStatusDrawable2;
        r3.remove(r3.size() - 1);
        if (r3.isEmpty() == true) goto L61;
        r76 = (Drawable) r3.get(0);
        goto L61
    L50:
        Drawable r83 = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_premium_liststar).mutate();
        this.emojiStatusDefaultDrawable = r83;
        int r102 = Theme.key_profile_verifiedBackground;
        r83.setColorFilter(new PorterDuffColorFilter(getThemedColor(r102), PorterDuff.Mode.MULTIPLY));
        this.emojiStatusDrawable.set(this.emojiStatusDefaultDrawable, r14);
        this.emojiStatusDrawable.setColor(Integer.valueOf(getThemedColor(r102)));
        r82 = this.emojiStatusDrawable;
        this.rightDrawableContentDescription = LocaleController.getString(R.string.AccDescrPremium);
        if (r3.isEmpty() == true) goto L61;
        r76 = (Drawable) r3.get(0);
        goto L61
    L54:
        if (r3.isEmpty() == true) goto L56;
        r82 = (Drawable) r3.get(0);
    L58:
        if (r3.size() <= 1) goto L61;
        r76 = (Drawable) r3.get(1);
        goto L61
    L56:
        r82 = null;
        goto L58
    L31:
        if (this.verifiedBackground != null) goto L33;
        this.verifiedBackground = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.verified_area).mutate();
        this.verifiedCheck = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.verified_check).mutate();
    L33:
        Drawable r84 = this.verifiedBackground;
        int r103 = getThemedColor(Theme.key_profile_verifiedBackground);
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r84.setColorFilter(new PorterDuffColorFilter(r103, r4));
        this.verifiedCheck.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_profile_verifiedCheck), r4));
        r3.add(new CombinedDrawable(this.verifiedBackground, this.verifiedCheck));
        this.rightDrawable2ContentDescription = LocaleController.getString(R.string.AccDescrVerified);
        this.rightDrawableIsScamOrVerified = true;
    L28:
        ScamDrawable r93 = new ScamDrawable(11, !r8 ? 1 : 0);
        r93.setColor(getThemedColor(Theme.key_actionBarDefaultSubtitle));
        r3.add(r93);
        this.rightDrawable2ContentDescription = LocaleController.getString(R.string.ScamMessage);
        this.rightDrawableIsScamOrVerified = true;
        goto L29
    L24:
        r22 = false;
    L20:
        r2 = true;
        goto L21
    }

    public void setTitleColors(int r2, int r3) {
        this.titleTextView.setTextColor(r2);
        this.subtitleTextView.setTextColor(r3);
        this.subtitleTextView.setTag(Integer.valueOf(r3));
    }

    public void setTitleIcons(Drawable r2, Drawable r3) {
        this.mutedIcon = r3;
        this.titleTextView.setLeftDrawable(r2);
        if (this.rightDrawableIsScamOrVerified == false) goto L5;
    L10:
        checkActionBar(true);
        return;
    L5:
        if (this.rightDrawableIsScam == true) goto L10;
        if (r3 == null) goto L8;
        this.rightDrawable2ContentDescription = LocaleController.getString(R.string.NotificationsMuted);
    L9:
        this.titleTextView.setRightDrawable2(r3);
        goto L10
    L8:
        this.rightDrawable2ContentDescription = null;
        goto L9
    }

    public void setUserAvatar(TLRPC.User r2) {
        setUserAvatar(r2, false);
    }

    public void setUserAvatar(TLRPC.User r4, boolean r5) {
        this.avatarDrawable.setInfo(this.currentAccount, r4);
        if (UserObject.isReplyUser(r4) == false) goto L9;
        this.avatarDrawable.setAvatarType(12);
        this.avatarDrawable.setScaleSize(0.8f);
        BackupImageView r52 = this.avatarImageView;
        if (r52 == null) goto L25;
        r52.setImage(null, null, this.avatarDrawable, r4);
        return;
    L25:
        return;
    L9:
        if (UserObject.isAnonymous(r4) == false) goto L15;
        this.avatarDrawable.setAvatarType(21);
        this.avatarDrawable.setScaleSize(0.8f);
        BackupImageView r53 = this.avatarImageView;
        if (r53 == null) goto L26;
        r53.setImage(null, null, this.avatarDrawable, r4);
        return;
    L26:
        return;
    L15:
        if (UserObject.isUserSelf(r4) == false) goto L21;
        if (r5 == true) goto L21;
        this.avatarDrawable.setAvatarType(1);
        this.avatarDrawable.setScaleSize(0.8f);
        BackupImageView r54 = this.avatarImageView;
        if (r54 == null) goto L27;
        r54.setImage(null, null, this.avatarDrawable, r4);
        return;
    L27:
        return;
    L21:
        this.avatarDrawable.setScaleSize(1.0f);
        BackupImageView r55 = this.avatarImageView;
        if (r55 == null) goto L28;
        r55.setForUserOrChat(r4, this.avatarDrawable);
        return;
    }

    public void showTimeItem(boolean r2) {
        this.animatorTimeVisible.setValue(true, r2);
    }

    public void updateColors() {
        StatusDrawable r0 = this.currentTypingDrawable;
        if (r0 == null) goto L5;
        r0.setColor(getThemedColor(Theme.key_chat_status));
    L5:
        Drawable r02 = this.emojiStatusDefaultDrawable;
        if (r02 == null) goto L8;
        r02.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_profile_verifiedBackground), PorterDuff.Mode.MULTIPLY));
    L8:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r03 = this.botVerificationDrawable;
        if (r03 == null) goto L11;
        r03.setColor(Integer.valueOf(getThemedColor(Theme.key_profile_verifiedBackground)));
    L11:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r04 = this.emojiStatusDrawable;
        if (r04 == null) goto L14;
        r04.setColor(Integer.valueOf(getThemedColor(Theme.key_profile_verifiedBackground)));
    L14:
        Drawable r05 = this.verifiedBackground;
        if (r05 == null) goto L17;
        r05.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_profile_verifiedBackground), PorterDuff.Mode.MULTIPLY));
    L17:
        Drawable r06 = this.verifiedCheck;
        if (r06 == null) goto L20;
        r06.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_profile_verifiedCheck), PorterDuff.Mode.MULTIPLY));
    L20:
        invalidate();
    }

    public void updateOnlineCount() {
        ChatActivity r0 = this.parentFragment;
        if (r0 == null) goto L45;
        int r1 = 0;
        this.onlineCount = 0;
        TLRPC.ChatFull r02 = r0.getCurrentChatInfo();
        if (r02 == null) goto L46;
        int r2 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if ((r02 instanceof TLRPC.TL_chatFull) == true) goto L23;
        boolean r3 = r02 instanceof TLRPC.TL_channelFull;
        if (r3 == true) goto L13;
    L17:
        if (r3 == true) goto L19;
        return;
    L19:
        if (r02.participants_count <= 200) goto L44;
        this.onlineCount = r02.online_count;
        return;
    L44:
        return;
    L13:
        if (r02.participants_count > 200) goto L17;
        if (r02.participants == null) goto L17;
    L23:
        if (r1 >= r02.participants.participants.size()) goto L47;
        TLRPC.User r32 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r02.participants.participants.get(r1).user_id));
        if (r32 == null) goto L35;
        TLRPC.UserStatus r4 = r32.status;
        if (r4 == null) goto L35;
        if (r4.expires > r2) goto L33;
        if (r32.id != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L35;
    L33:
        if (r32.status.expires <= 10000) goto L35;
        this.onlineCount++;
    L35:
        r1 = r1 + 1;
        goto L23
    L47:
        return;
    L46:
        return;
    }

    public void updateSubtitle() {
        updateSubtitle(false);
    }

    public void updateSubtitle(boolean r21) {
        ChatActivity r2 = this.parentFragment;
        if (r2 != null) goto L6;
        return;
    L6:
        if (r2.isFeedSearch() == true) goto L191;
        int r22 = this.parentFragment.getChatMode();
        ChatActivity r3 = this.parentFragment;
        if (r22 != 6) goto L12;
        setSubtitle(BusinessLinksController.stripHttps(r3.businessLink.link));
        return;
    L12:
        TLRPC.User r23 = r3.getCurrentUser();
        TLRPC.Chat r32 = this.parentFragment.getCurrentChat();
        boolean r7 = true;
        if (UserObject.isUserSelf(r23) == true) goto L15;
    L23:
        boolean r4 = false;
    L25:
        if (UserObject.isUserSelf(r23) == false) goto L28;
        if (r4 == true) goto L28;
    L37:
        if (this.parentFragment.getChatMode() != 3) goto L39;
    L42:
        if (r4 == true) goto L44;
    L49:
        this.subtitleIsThinkingBot = false;
        CharSequence r5 = MessagesController.getInstance(this.currentAccount).getPrintingString(this.parentFragment.getDialogId(), this.parentFragment.getThreadId(), false);
        if (r5 != null) goto L52;
        UserObject.isBotForum(r23);
    L52:
        CharSequence r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r5 == null) goto L55;
        r5 = TextUtils.replace(r5, new String[]{"..."}, new String[]{_UrlKt.FRAGMENT_ENCODE_SET});
    L55:
        Property r10 = View.ALPHA;
        Property r11 = View.TRANSLATION_Y;
        boolean[] r12 = null;
        if (r5 == null) goto L84;
        if (r5.length() == 0) goto L84;
        if (ChatObject.isChannel(r32) == false) goto L64;
        float r19 = 0.0f;
        if (r32.megagroup == false) goto L84;
    L66:
        if (this.parentFragment.isThreadChat() == true) goto L68;
    L76:
        Integer r24 = MessagesController.getInstance(this.currentAccount).getPrintingStringType(this.parentFragment.getDialogId(), this.parentFragment.getThreadId());
        if (r24 != null) goto L79;
    L81:
        r9 = r5;
    L82:
        setTypingAnimation(true);
    L171:
        if (r7 == false) goto L173;
        int r25 = Theme.key_chat_status;
    L174:
        this.lastSubtitleColorKey = r25;
        if (this.lastSubtitle != null) goto L186;
        SimpleTextView r26 = this.subtitleTextView;
        if (r26 == null) goto L182;
        r26.setText(r9);
        Integer r27 = this.overrideSubtitleColor;
        SimpleTextView r33 = this.subtitleTextView;
        if (r27 != null) goto L181;
        r33.setTextColor(getThemedColor(this.lastSubtitleColorKey));
        this.subtitleTextView.setTag(Integer.valueOf(this.lastSubtitleColorKey));
    L187:
        checkActionBar(r21);
        return;
    L181:
        r33.setTextColor(r27.intValue());
        goto L187
    L182:
        this.animatedSubtitleTextView.setText(r9, r21);
        Integer r28 = this.overrideSubtitleColor;
        AnimatedTextView r34 = this.animatedSubtitleTextView;
        if (r28 != null) goto L185;
        r34.setTextColor(getThemedColor(this.lastSubtitleColorKey));
        this.animatedSubtitleTextView.setTag(Integer.valueOf(this.lastSubtitleColorKey));
        goto L187
    L185:
        r34.setTextColor(r28.intValue());
        goto L187
    L186:
        this.lastSubtitle = r9;
        goto L187
    L173:
        r25 = Theme.key_actionBarDefaultSubtitle;
        goto L174
    L79:
        if (r24.intValue() != 5) goto L81;
        r9 = Emoji.replaceEmoji(r5, getSubtitlePaint().getFontMetricsInt(), false);
        goto L82
    L68:
        if (this.titleTextView.getTag() == null) goto L76;
        this.titleTextView.setTag(null);
        getSubtitleTextView().setVisibility(0);
        AnimatorSet r29 = this.titleAnimation;
        if (r29 == null) goto L73;
        r29.cancel();
        this.titleAnimation = null;
    L73:
        if (r21 == false) goto L75;
        AnimatorSet r35 = new AnimatorSet();
        this.titleAnimation = r35;
        r35.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.titleTextView, r11, new float[]{r19}), ObjectAnimator.ofFloat(getSubtitleTextView(), r10, new float[]{1.0f})});
        this.titleAnimation.addListener(new AnonymousClass5(this));
        this.titleAnimation.setDuration(180);
        this.titleAnimation.start();
        goto L76
    L75:
        this.titleTextView.setTranslationY(r19);
        getSubtitleTextView().setAlpha(1.0f);
        goto L76
    L64:
        r19 = 0.0f;
    L84:
        if (this.parentFragment.isThreadChat() == true) goto L86;
    L99:
        setTypingAnimation(false);
        int r52 = this.parentFragment.getChatMode();
        ChatActivity r6 = this.parentFragment;
        if (r52 == 8) goto L102;
        int r53 = r6.getChatMode();
        ChatActivity r62 = this.parentFragment;
        if (r53 != 3) goto L123;
        r9 = LocaleController.formatPluralString("SavedMessagesCount", Math.max(1, r62.getMessagesController().getSavedMessagesController().getMessagesCount(this.parentFragment.getSavedDialogId())), new Object[0]);
    L104:
        r7 = false;
        goto L171
    L123:
        if (r62.isTopic == false) goto L132;
        if (r32 == null) goto L132;
        TLRPC.TL_forumTopic r210 = MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(r32.id, this.parentFragment.getTopicId());
        if (r210 == null) goto L128;
        int r211 = r210.totalMessagesCount - 1;
    L129:
        if (r211 <= 0) goto L131;
        String r212 = LocaleController.formatPluralString("messages", r211, new Object[]{Integer.valueOf(r211)});
    L111:
        r9 = r212;
        goto L104
    L131:
        r212 = LocaleController.formatString(R.string.TopicProfileStatus, new Object[]{r32.title});
        goto L111
    L128:
        r211 = 0;
    L132:
        if (r32 == null) goto L134;
        r9 = getChatSubtitle(r32, r62.getCurrentChatInfo(), this.onlineCount);
        goto L104
    L134:
        if (r23 == null) goto L104;
        TLRPC.User r36 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r23.id));
        if (r36 == null) goto L139;
        r23 = r36;
    L139:
        if (UserObject.isReplyUser(r23) == true) goto L104;
        long r54 = r23.id;
        if (r54 == UserObject.VERIFY) goto L104;
        if (r54 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L149;
        if (r4 == false) goto L148;
        r9 = AndroidUtilities.replaceArrows(LocaleController.getString(R.string.SavedMessagesViewAsChatsHint), false);
        goto L104
    L148:
        r9 = LocaleController.getString(R.string.ChatYourSelf);
        goto L104
    L149:
        long r37 = r23.id;
        if (r37 != 333000) goto L152;
    L170:
        r9 = LocaleController.getString(R.string.ServiceNotifications);
        goto L104
    L152:
        if (r37 == 777000) goto L170;
        if (r37 == 42777) goto L170;
        if (MessagesController.isSupportUser(r23) == false) goto L159;
        r9 = LocaleController.getString(R.string.SupportStatus);
        goto L104
    L159:
        boolean r38 = r23.bot;
        if (r38 == false) goto L164;
        int r42 = r23.bot_active_users;
        if (r42 == 0) goto L164;
        r9 = LocaleController.formatPluralStringComma("BotUsers", r42, ',');
    L164:
        if (r38 == false) goto L166;
        r9 = LocaleController.getString(R.string.Bot);
        goto L104
    L166:
        boolean[] r39 = this.isOnline;
        r39[0] = false;
        int r43 = this.currentAccount;
        if (this.allowShorterStatus == false) goto L169;
        r12 = this.statusMadeShorter;
    L169:
        r9 = LocaleController.formatUserStatus(r43, r23, r39, r12, new boolean[1]);
        r7 = this.isOnline[0];
        goto L171
    L102:
        if (r6.isSubscriberSuggestions == false) goto L106;
        r9 = LocaleController.getString(R.string.ChatMessageSuggestions);
        goto L104
    L106:
        if (r6.getTopicId() != 0) goto L112;
        int r213 = this.parentFragment.getMessagesController().getTopicsController().getTopicsCount(-this.parentFragment.getDialogId());
        if (r213 <= 0) goto L110;
        r212 = LocaleController.formatPluralStringComma("Chats", r213);
        goto L111
    L110:
        r212 = LocaleController.getString(R.string.ChatMessageSuggestions);
        goto L111
    L112:
        TLRPC.TL_forumTopic r214 = MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(r32.id, this.parentFragment.getTopicId());
        if (r214 == null) goto L115;
        int r215 = r214.totalMessagesCount;
    L116:
        if (r215 <= 0) goto L118;
        r212 = LocaleController.formatPluralString("messages", r215, new Object[]{Integer.valueOf(r215)});
        goto L111
    L118:
        r212 = LocaleController.formatString(R.string.TopicProfileStatus, new Object[]{ForumUtilities.getMonoForumTitle(this.currentAccount, r32)});
        goto L111
    L115:
        r215 = 0;
        goto L116
    L86:
        if (this.parentFragment.isTopic == true) goto L99;
        if (this.titleTextView.getTag() != null) goto L189;
        this.titleTextView.setTag(1);
        AnimatorSet r216 = this.titleAnimation;
        if (r216 == null) goto L94;
        r216.cancel();
        this.titleAnimation = null;
    L94:
        if (r21 == false) goto L97;
        AnimatorSet r1 = new AnimatorSet();
        this.titleAnimation = r1;
        r1.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.titleTextView, r11, new float[]{AndroidUtilities.dp(9.7f)}), ObjectAnimator.ofFloat(getSubtitleTextView(), r10, new float[]{0.0f})});
        this.titleAnimation.addListener(new AnonymousClass4(this));
        this.titleAnimation.setDuration(180);
        this.titleAnimation.start();
        return;
    L97:
        this.titleTextView.setTranslationY(AndroidUtilities.dp(9.7f));
        getSubtitleTextView().setAlpha(0.0f);
        getSubtitleTextView().setVisibility(4);
        return;
    L189:
        return;
    L44:
        if (getSubtitleTextView().getVisibility() == 0) goto L47;
        getSubtitleTextView().setVisibility(0);
    L47:
        if (this.showingSavedMessagesHint == true) goto L49;
        MessagesController.getGlobalMainSettings().edit().putInt("savedmsgschatshint", MessagesController.getGlobalMainSettings().getInt("savedmsgschatshint", 0) + 1).apply();
        this.showingSavedMessagesHint = true;
        goto L49
    L39:
        if (getSubtitleTextView().getVisibility() == 8) goto L89;
        getSubtitleTextView().setVisibility(8);
        return;
    L89:
        return;
    L28:
        if (UserObject.isReplyUser(r23) == true) goto L37;
        if (r23 == null) goto L33;
        if (r23.id == UserObject.VERIFY) goto L37;
    L33:
        if (this.parentFragment.getChatMode() == 0) goto L42;
        if (this.parentFragment.getChatMode() == 8) goto L42;
    L15:
        if (this.parentFragment.getChatMode() != 0) goto L23;
        if (this.parentFragment.getMessagesController().getSavedMessagesController().getAllCount() < 3) goto L23;
        if (this.showingSavedMessagesHint == false) goto L21;
    L22:
        r4 = true;
        goto L25
    L21:
        if (MessagesController.getGlobalMainSettings().getInt("savedmsgschatshint", 0) >= 3) goto L23;
    }

    public boolean useAnimatedSubtitle() {
        return false;
    }
}
