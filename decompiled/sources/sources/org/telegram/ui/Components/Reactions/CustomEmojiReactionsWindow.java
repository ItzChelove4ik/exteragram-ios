package org.telegram.ui.Components.Reactions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.utils.chats.GlassMenuHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmojiTabsStrip;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Premium.PremiumLockIconView;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.StableAnimator;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.SelectAnimatedEmojiDialog;

/* loaded from: classes7.dex */
public class CustomEmojiReactionsWindow {
    private int account;
    HashSet<View> animatingEnterChild;
    ArrayList<ValueAnimator> animators;
    boolean attachToParent;
    BaseFragment baseFragment;
    private boolean cascadeAnimation;
    public ContainerView containerView;
    private float dismissProgress;
    private boolean dismissed;
    public RectF drawingRect;
    boolean enterTransitionFinished;
    float enterTransitionProgress;
    private int frameDrawCount;
    float fromRadius;
    RectF fromRect;
    float fromRectTranslateX;
    float fromRectTranslateY;
    BlurredBackgroundDrawable glassBackground;
    BlurredBackgroundDrawable glassBackgroundSearchBox;
    private boolean invalidatePath;
    boolean isShowing;
    float keyboardHeight;
    int[] location;
    final AnimationNotificationsLocker notificationsLocker;
    private Runnable onDismiss;
    private final Path pathToClipApi20;
    List<ReactionsLayoutInBubble.VisibleReaction> reactions;
    ReactionsContainerLayout reactionsContainerLayout;
    Theme.ResourcesProvider resourcesProvider;
    SelectAnimatedEmojiDialog selectAnimatedEmojiDialog;
    public boolean transition;
    private final int type;
    private ValueAnimator valueAnimator;
    private boolean wasFocused;
    WindowManager windowManager;
    public FrameLayout windowView;
    float yTranslation;

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        Bulletin.Delegate bulletinDelegate;
        final /* synthetic */ CustomEmojiReactionsWindow this$0;

        /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$1$1, reason: invalid class name and collision with other inner class name */
        public class C00551 implements Bulletin.Delegate {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00551(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                return (int) this.this$1.this$0.keyboardHeight;
            }
        }

        public AnonymousClass1(CustomEmojiReactionsWindow r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.bulletinDelegate = new C00551(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r4) {
            if (r4.getAction() != 1) goto L11;
            if (r4.getKeyCode() != 4) goto L11;
            CustomEmojiReactionsWindow r3 = this.this$0;
            if (r3.enterTransitionFinished == false) goto L9;
            r3.dismiss();
        L9:
            return true;
        L11:
            return super.dispatchKeyEvent(r4);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchSetPressed(boolean r1) {
        }

        @Override // android.view.View
        public boolean fitSystemWindows(Rect r4) {
            CustomEmojiReactionsWindow r0 = this.this$0;
            if (r0.keyboardHeight == r4.bottom) goto L8;
            if (CustomEmojiReactionsWindow.m12345$$Nest$fgetwasFocused(r0) == false) goto L8;
            CustomEmojiReactionsWindow r02 = this.this$0;
            r02.keyboardHeight = r4.bottom;
            CustomEmojiReactionsWindow.m12354$$Nest$mupdateWindowPosition(r02);
        L8:
            return super.fitSystemWindows(r4);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Bulletin.addDelegate(this, this.bulletinDelegate);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bulletin.removeDelegate(this);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$2, reason: invalid class name */
    public class AnonymousClass2 extends SelectAnimatedEmojiDialog {
        final /* synthetic */ CustomEmojiReactionsWindow this$0;
        final /* synthetic */ BaseFragment val$baseFragment;
        final /* synthetic */ ReactionsContainerLayout val$reactionsContainerLayout;

        public static /* synthetic */ void $r8$lambda$UpRK8gz4ILEICVsoqw_VSG3xQKc(AnonymousClass2 r0) {
            r0.lambda$onEmojiSelected$0();
        }

        public AnonymousClass2(CustomEmojiReactionsWindow r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9, ReactionsContainerLayout r10, BaseFragment r11) {
            this.this$0 = r1;
            this.val$reactionsContainerLayout = r10;
            this.val$baseFragment = r11;
            super(r2, r3, r4, r5, r6, r7, r8, r9);
        }

        private /* synthetic */ void lambda$onEmojiSelected$0() {
            CustomEmojiReactionsWindow.m12351$$Nest$mshowUnlockPremiumAlert(this.this$0);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void invalidateParent() {
            this.this$0.containerView.invalidate();
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
            if (this.val$baseFragment == null) goto L13;
            ReactionsContainerLayout r42 = this.val$reactionsContainerLayout;
            if (r42.channelReactions == true) goto L13;
            if (r42.getWindowType() == 13) goto L13;
            if (UserConfig.getInstance(this.val$baseFragment.getCurrentAccount()).isPremium() == true) goto L13;
            this.this$0.windowView.performHapticFeedback(3);     // Catch: Exception -> L24
        L11:
            BulletinFactory.of(this.this$0.windowView, null).createEmojiBulletin(r3, AndroidUtilities.replaceTags(LocaleController.getString(R.string.UnlockPremiumEmojiReaction)), LocaleController.getString(R.string.PremiumMore), new CustomEmojiReactionsWindow$2$$ExternalSyntheticLambda0(this)).show();
            return;
        L13:
            if (r2 != null) goto L16;
            if (r3 != null) goto L16;
            return;
        L16:
            if (r3 == null) goto L18;
            AnimatedEmojiDrawable.getDocumentFetcher(UserConfig.selectedAccount).putDocument(r3);
        L18:
            ReactionsContainerLayout r43 = this.val$reactionsContainerLayout;
            if (r2 != null) goto L21;
            long r22 = r3.id;
        L22:
            r43.onReactionClicked(r1, ReactionsLayoutInBubble.VisibleReaction.fromCustomEmoji(Long.valueOf(r22)), false);
            AndroidUtilities.hideKeyboard(this.this$0.windowView);
            return;
        L21:
            r22 = r2.longValue();
            goto L22
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onInputFocus() {
            if (CustomEmojiReactionsWindow.m12345$$Nest$fgetwasFocused(this.this$0) == true) goto L14;
            CustomEmojiReactionsWindow.m12347$$Nest$fputwasFocused(this.this$0, true);
            CustomEmojiReactionsWindow r0 = this.this$0;
            if (r0.attachToParent == true) goto L7;
            r0.windowManager.updateViewLayout(r0.windowView, CustomEmojiReactionsWindow.m12349$$Nest$mcreateLayoutParams(r0, true));
        L7:
            BaseFragment r02 = this.val$baseFragment;
            if ((r02 instanceof ChatActivity) == false) goto L11;
            ((ChatActivity) r02).needEnterText();
        L11:
            if (this.val$reactionsContainerLayout.getDelegate() == null) goto L15;
            this.val$reactionsContainerLayout.getDelegate().needEnterText();
            return;
        L15:
            return;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onReactionClick(SelectAnimatedEmojiDialog.ImageViewEmoji r3, ReactionsLayoutInBubble.VisibleReaction r4) {
            this.val$reactionsContainerLayout.onReactionClicked(r3, r4, false);
            AndroidUtilities.hideKeyboard(this.this$0.windowView);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public boolean prevWindowKeyboardVisible() {
            if (this.val$reactionsContainerLayout.getDelegate() != null) goto L5;
            return false;
        L5:
            return this.val$reactionsContainerLayout.getDelegate().needEnterText();
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewOutlineProvider {
        final Rect rect;
        final RectF rectF;
        final RectF rectTmp;
        final /* synthetic */ CustomEmojiReactionsWindow this$0;

        public AnonymousClass3(CustomEmojiReactionsWindow r1) {
            this.this$0 = r1;
            this.rect = new Rect();
            this.rectTmp = new RectF();
            this.rectF = new RectF();
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r5, Outline r6) {
            float r0 = AndroidUtilities.lerp(this.this$0.fromRadius, AndroidUtilities.dp(8.0f), this.this$0.enterTransitionProgress);
            this.rectTmp.set(0.0f, 0.0f, r5.getMeasuredWidth(), r5.getMeasuredHeight());
            CustomEmojiReactionsWindow r52 = this.this$0;
            AndroidUtilities.lerp(r52.fromRect, this.rectTmp, r52.enterTransitionProgress, this.rectF);
            this.rectF.round(this.rect);
            r6.setRoundRect(this.rect, r0);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$4, reason: invalid class name */
    public class AnonymousClass4 implements SelectAnimatedEmojiDialog.onLongPressedListener {
        final /* synthetic */ CustomEmojiReactionsWindow this$0;
        final /* synthetic */ ReactionsContainerLayout val$reactionsContainerLayout;

        public AnonymousClass4(CustomEmojiReactionsWindow r1, ReactionsContainerLayout r2) {
            this.this$0 = r1;
            this.val$reactionsContainerLayout = r2;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog.onLongPressedListener
        public void onLongPressed(SelectAnimatedEmojiDialog.ImageViewEmoji r5) {
            boolean r0 = r5.isDefaultReaction;
            ReactionsContainerLayout r4 = this.val$reactionsContainerLayout;
            if (r0 == false) goto L6;
            r4.onReactionClicked(r5, r5.reaction, true);
            return;
        L6:
            r4.onReactionClicked(r5, ReactionsLayoutInBubble.VisibleReaction.fromCustomEmoji(Long.valueOf(r5.span.documentId)), true);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$5, reason: invalid class name */
    public class AnonymousClass5 implements SelectAnimatedEmojiDialog.onRecentClearedListener {
        final /* synthetic */ CustomEmojiReactionsWindow this$0;
        final /* synthetic */ ReactionsContainerLayout val$reactionsContainerLayout;

        public AnonymousClass5(CustomEmojiReactionsWindow r1, ReactionsContainerLayout r2) {
            this.this$0 = r1;
            this.val$reactionsContainerLayout = r2;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog.onRecentClearedListener
        public void onRecentCleared() {
            this.val$reactionsContainerLayout.clearRecentReactions();
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ CustomEmojiReactionsWindow this$0;
        final /* synthetic */ boolean val$enter;

        public AnonymousClass6(CustomEmojiReactionsWindow r1, boolean r2) {
            this.this$0 = r1;
            this.val$enter = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            CustomEmojiReactionsWindow.m12352$$Nest$mupdateContainersAlpha(this.this$0);
            CustomEmojiReactionsWindow.m12353$$Nest$mupdateContentPosition(this.this$0);
            CustomEmojiReactionsWindow.m12348$$Nest$mcheckAnimationEnd(this.this$0, this.val$enter);
            this.this$0.selectAnimatedEmojiDialog.invalidateOutline();
            CustomEmojiReactionsWindow r52 = this.this$0;
            boolean r0 = this.val$enter;
            if (r0 == false) goto L5;
            float r3 = 1.0f;
        L6:
            r52.enterTransitionProgress = r3;
            boolean r32 = true;
            if (r0 == false) goto L9;
            r52.enterTransitionFinished = true;
            r52.containerView.invalidate();
        L9:
            CustomEmojiReactionsWindow r53 = this.this$0;
            r53.reactionsContainerLayout.setCustomEmojiEnterProgress(Utilities.clamp(r53.enterTransitionProgress, 1.0f, 0.0f));
            if (this.val$enter == true) goto L18;
            this.this$0.reactionsContainerLayout.setImportantForAccessibility(0);
            this.this$0.reactionsContainerLayout.setSkipDraw(false);
            this.this$0.removeView();
            Runtime.getRuntime().gc();
            CustomEmojiReactionsWindow r54 = this.this$0;
            ReactionsContainerLayout r1 = r54.reactionsContainerLayout;
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(r54) != 4) goto L14;
        L16:
            r32 = false;
        L17:
            r1.setCustomEmojiReactionsBackground(r32);
            goto L18
        L14:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) == 5) goto L16;
        L18:
            this.this$0.transition = false;
            return;
        L5:
            r3 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ CustomEmojiReactionsWindow this$0;
        final /* synthetic */ boolean val$enter;
        final /* synthetic */ ValueAnimator val$valueAnimator;

        public AnonymousClass7(CustomEmojiReactionsWindow r1, ValueAnimator r2, boolean r3) {
            this.this$0 = r1;
            this.val$valueAnimator = r2;
            this.val$enter = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            super.onAnimationEnd(r2);
            this.this$0.animators.remove(this.val$valueAnimator);
            CustomEmojiReactionsWindow.m12348$$Nest$mcheckAnimationEnd(this.this$0, this.val$enter);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ CustomEmojiReactionsWindow this$0;

        public AnonymousClass8(CustomEmojiReactionsWindow r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.removeView();
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ CustomEmojiReactionsWindow this$0;

        public AnonymousClass9(CustomEmojiReactionsWindow r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            CustomEmojiReactionsWindow.m12348$$Nest$mcheckAnimationEnd(this.this$0, false);
            CustomEmojiReactionsWindow r42 = this.this$0;
            r42.enterTransitionProgress = 0.0f;
            r42.reactionsContainerLayout.setCustomEmojiEnterProgress(Utilities.clamp(0.0f, 1.0f, 0.0f));
            this.this$0.reactionsContainerLayout.setSkipDraw(false);
            this.this$0.windowView.setVisibility(8);
            this.this$0.removeView();
        }
    }

    public class ContainerView extends FrameLayout {
        Paint backgroundPaint;
        private final Path clipPath;
        float enterTransitionOffsetX;
        float enterTransitionOffsetY;
        float enterTransitionScale;
        float enterTransitionScalePx;
        float enterTransitionScalePy;
        int[] radiusTmp;
        Drawable shadow;
        Rect shadowPad;
        final /* synthetic */ CustomEmojiReactionsWindow this$0;
        HashMap<ReactionsLayoutInBubble.VisibleReaction, SelectAnimatedEmojiDialog.ImageViewEmoji> transitionReactions;

        public ContainerView(CustomEmojiReactionsWindow r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            this.shadowPad = new Rect();
            this.backgroundPaint = new Paint(1);
            this.radiusTmp = new int[4];
            this.transitionReactions = new HashMap();
            this.enterTransitionOffsetX = 0.0f;
            this.enterTransitionOffsetY = 0.0f;
            this.enterTransitionScale = 1.0f;
            this.enterTransitionScalePx = 0.0f;
            this.enterTransitionScalePy = 0.0f;
            this.clipPath = new Path();
            this.shadow = ContextCompat.getDrawable(r5, R.drawable.reactions_bubble_shadow).mutate();
            Rect r52 = this.shadowPad;
            int r0 = AndroidUtilities.dp(7.0f);
            r52.bottom = r0;
            r52.right = r0;
            r52.top = r0;
            r52.left = r0;
            this.shadow.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelShadow, r4.resourcesProvider), PorterDuff.Mode.MULTIPLY));
            int r53 = CustomEmojiReactionsWindow.m12343$$Nest$fgettype(r4);
            Paint r3 = this.backgroundPaint;
            if (r53 != 2) goto L6;
            r3.setColor(ColorUtils.blendARGB(-16777216, -1, 0.13f));
            return;
        L6:
            r3.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r4.resourcesProvider));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r27) {
            Canvas r1 = r27;
            CustomEmojiReactionsWindow r2 = this.this$0;
            if (r2.isShowing == true) goto L5;
            return;
        L5:
            float r9 = 1.0f;
            float r10 = 0.0f;
            float r11 = Utilities.clamp(r2.enterTransitionProgress, 1.0f, 0.0f);
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            int r12 = 4;
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 4) goto L8;
            CustomEmojiReactionsWindow r3 = this.this$0;
            r3.fromRect.set(r3.reactionsContainerLayout.rect);
            CustomEmojiReactionsWindow r32 = this.this$0;
            r32.fromRect.offset(r32.fromRectTranslateX, r32.fromRectTranslateY);
        L8:
            CustomEmojiReactionsWindow r33 = this.this$0;
            AndroidUtilities.lerp(r33.fromRect, r22, r33.enterTransitionProgress, r33.drawingRect);
            CustomEmojiReactionsWindow r23 = this.this$0;
            float r34 = r23.fromRadius;
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(r23) != 5) goto L11;
            float r24 = 20.0f;
        L12:
            float r4 = AndroidUtilities.lerp(r34, AndroidUtilities.dp(r24), this.this$0.enterTransitionProgress);
            this.transitionReactions.clear();
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 1) goto L15;
        L18:
            float r17 = 0.05f;
            int r14 = 1;
            this.this$0.reactionsContainerLayout.getDelegate().drawRoundRect(r1, this.this$0.drawingRect, r4, getX(), CustomEmojiReactionsWindow.m12350$$Nest$mgetBlurOffset(this.this$0), 255, true);
            r1 = r1;
            float r8 = r4;
        L26:
            if (this.this$0.reactionsContainerLayout.hintView == null) goto L37;
            r1.save();
            CustomEmojiReactionsWindow r25 = this.this$0;
            RectF r42 = r25.drawingRect;
            float r5 = r42.left;
            float r43 = r42.top + r25.reactionsContainerLayout.hintView.getY();
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 3) goto L30;
        L35:
            float r26 = this.this$0.reactionsContainerLayout.rect.top;
        L36:
            r1.translate(r5, r43 - r26);
            float r18 = 255.0f;
            r1.saveLayerAlpha(0.0f, 0.0f, this.this$0.reactionsContainerLayout.hintView.getMeasuredWidth(), this.this$0.reactionsContainerLayout.hintView.getMeasuredHeight(), (int) ((this.this$0.reactionsContainerLayout.hintView.getAlpha() * 255.0f) * (1.0f - this.this$0.enterTransitionProgress)), 31);
            this.this$0.reactionsContainerLayout.hintView.draw(r1);
            r1.restore();
            r1.restore();
        L38:
            CustomEmojiReactionsWindow r28 = this.this$0;
            RectF r35 = r28.drawingRect;
            float r44 = (r35.left - r28.reactionsContainerLayout.rect.left) + (r35.width() - this.this$0.reactionsContainerLayout.rect.width());
            CustomEmojiReactionsWindow r29 = this.this$0;
            if (r29.enterTransitionProgress <= r17) goto L41;
        L42:
            r1.save();
            CustomEmojiReactionsWindow r210 = this.this$0;
            RectF r36 = r210.drawingRect;
            r1.translate(r44, (r36.top - r210.reactionsContainerLayout.rect.top) + (r36.height() - this.this$0.reactionsContainerLayout.rect.height()));
            this.this$0.reactionsContainerLayout.drawBubbles(r1);
            r1.restore();
        L44:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 5) goto L46;
            this.clipPath.rewind();
            this.clipPath.addRoundRect(this.this$0.drawingRect, r8, r8, Path.Direction.CW);
            r1.save();
            r1.clipPath(this.clipPath);
        L46:
            this.enterTransitionOffsetX = 0.0f;
            this.enterTransitionOffsetY = 0.0f;
            this.enterTransitionScale = 1.0f;
            this.enterTransitionScalePx = 0.0f;
            this.enterTransitionScalePy = 0.0f;
            CustomEmojiReactionsWindow r211 = this.this$0;
            if (r211.reactionsContainerLayout == null) goto L168;
            int r212 = r211.selectAnimatedEmojiDialog.emojiGridView.getChildCount() - r14;
        L49:
            if (r212 < 0) goto L58;
            if ((this.this$0.selectAnimatedEmojiDialog.emojiGridView.getChildAt(r212) instanceof SelectAnimatedEmojiDialog.ImageViewEmoji) == false) goto L57;
            SelectAnimatedEmojiDialog.ImageViewEmoji r37 = (SelectAnimatedEmojiDialog.ImageViewEmoji) this.this$0.selectAnimatedEmojiDialog.emojiGridView.getChildAt(r212);
            if (r37.isFirstReactions == false) goto L57;
            ReactionsLayoutInBubble.VisibleReaction r45 = r37.reaction;
            if (r45 == null) goto L57;
            this.transitionReactions.put(r45, r37);
        L57:
            r212 = r212 - 1;
            goto L49
        L58:
            int r82 = r1.save();
            CustomEmojiReactionsWindow r213 = this.this$0;
            RectF r38 = r213.drawingRect;
            r1.translate(r38.left, r38.top + ((r213.reactionsContainerLayout.getTopOffset() + this.this$0.reactionsContainerLayout.expandSize()) * (1.0f - this.this$0.enterTransitionProgress)));
            if (this.this$0.selectAnimatedEmojiDialog.emojiSearchGridView.getVisibility() != 0) goto L61;
            float r214 = this.this$0.selectAnimatedEmojiDialog.emojiSearchGridView.getAlpha();
        L62:
            float r215 = Math.max(1.0f - r214, 1.0f - this.this$0.enterTransitionProgress);
            if (r215 == 1.0f) goto L65;
            r1.saveLayerAlpha(0.0f, 0.0f, this.this$0.drawingRect.width(), this.this$0.drawingRect.height(), (int) (r215 * r18), 31);
        L65:
            int r216 = (int) (this.this$0.selectAnimatedEmojiDialog.getX() + this.this$0.selectAnimatedEmojiDialog.emojiGridView.getX());
            int r39 = (int) (this.this$0.selectAnimatedEmojiDialog.getY() + this.this$0.selectAnimatedEmojiDialog.emojiGridView.getY());
            if (this.this$0.selectAnimatedEmojiDialog.emojiTabs.getParent() == null) goto L68;
            int r46 = r14;
        L70:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) == 5) goto L76;
            float r6 = r39;
            if (r46 == 0) goto L74;
            float r47 = r216 + (AndroidUtilities.dp(36.0f) * this.this$0.enterTransitionProgress);
        L75:
            r1.clipRect(r6, r47, r39 + this.this$0.selectAnimatedEmojiDialog.emojiGridView.getMeasuredWidth(), r216 + this.this$0.selectAnimatedEmojiDialog.emojiGridView.getMeasuredHeight());
            goto L76
        L74:
            r47 = 0.0f;
        L76:
            int r15 = -1;
            int r217 = -1;
        L78:
            if (r217 >= this.this$0.reactionsContainerLayout.recyclerListView.getChildCount()) goto L167;
            CustomEmojiReactionsWindow r310 = this.this$0;
            if (r217 != r15) goto L82;
            View r311 = r310.reactionsContainerLayout.nextRecentReaction;
        L84:
            if (r311.getLeft() >= 0) goto L86;
        L87:
            int r232 = r217;
            float r222 = r9;
            float r13 = r10;
            int r142 = r12;
        L166:
            r217 = r232 + 1;
            r10 = r13;
            r12 = r142;
            r9 = r222;
            r15 = -1;
            goto L78
        L86:
            if (r311.getVisibility() == 8) goto L87;
            r1.save();
            if ((r311 instanceof ReactionsContainerLayout.ReactionHolderView) == false) goto L164;
            ReactionsContainerLayout.ReactionHolderView r48 = (ReactionsContainerLayout.ReactionHolderView) r311;
            PremiumLockIconView r62 = r48.lockIconView;
            if (r62 == null) goto L93;
            r62.setAlpha(r9 - this.this$0.enterTransitionProgress);
        L93:
            SelectAnimatedEmojiDialog.ImageViewEmoji r63 = this.transitionReactions.get(r48.currentReaction);
            if (r63 == null) goto L126;
            float r172 = r311.getX();
            float r312 = r311.getY();
            float r19 = 2.0f;
            if (r217 != r15) goto L98;
            r172 = r172 - this.this$0.reactionsContainerLayout.recyclerListView.getX();
            r312 = r312 - this.this$0.reactionsContainerLayout.recyclerListView.getY();
        L98:
            float r7 = r172;
            float r173 = (((r63.getX() + this.this$0.selectAnimatedEmojiDialog.getX()) + this.this$0.selectAnimatedEmojiDialog.emojiGridView.getX()) - r48.loopImageView.getX()) - AndroidUtilities.dp(r9);
            float r152 = (((r63.getY() + this.this$0.selectAnimatedEmojiDialog.getY()) + this.this$0.selectAnimatedEmojiDialog.gridViewContainer.getY()) + this.this$0.selectAnimatedEmojiDialog.emojiGridView.getY()) - r48.loopImageView.getY();
            float r132 = r63.getMeasuredWidth();
            if (r63.selected == true) goto L105;
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) == r12) goto L105;
        L103:
            float r122 = r173;
            float r143 = AndroidUtilities.lerp(r7, r122, this.this$0.enterTransitionProgress);
            float r52 = AndroidUtilities.lerp(r312, r152, this.this$0.enterTransitionProgress);
            float r21 = r10;
            float r133 = r132 / r48.loopImageView.getMeasuredWidth();
            float r102 = AndroidUtilities.lerp(r9, r133, this.this$0.enterTransitionProgress);
            float r223 = r9;
            if (r48.position != 0) goto L116;
            float r92 = AndroidUtilities.dp(6.0f);
            float r242 = r92;
            float r233 = r21;
            float r252 = r233;
        L120:
            r1.translate(r143, r52);
            r1.scale(r102, r102);
            if (this.enterTransitionOffsetX == r21) goto L123;
        L125:
            float r313 = r233;
            float r53 = r242;
            float r72 = r252;
        L127:
            if (r63 != null) goto L129;
            r232 = r217;
            boolean r218 = false;
            r13 = 0.0f;
            r142 = 4;
            if (r48.hasEnterAnimation == true) goto L152;
        L154:
            r48.checkPlayLoopImage();
            ImageReceiver r314 = r48.loopImageView.getImageReceiver();
            AnimatedEmojiDrawable r54 = r48.loopImageView.animatedEmojiDrawable;
            if (r54 != null) goto L157;
        L159:
            float r55 = r314.getAlpha();
            r314.setAlpha((1.0f - r11) * r55);
            r48.loopImageView.draw(r1);
            r314.setAlpha(r55);
        L161:
            if (r48.loopImageView.getVisibility() == 0) goto L163;
            invalidate();
        L163:
            r222 = 1.0f;
        L165:
            r1.restore();
            goto L166
        L157:
            if (r54.getImageReceiver() == null) goto L159;
            r314 = r48.loopImageView.animatedEmojiDrawable.getImageReceiver();
            goto L159
        L152:
            if (r48.loopImageView.getImageReceiver().getLottieAnimation() != null) goto L154;
            float r315 = r48.enterImageView.getImageReceiver().getAlpha();
            r48.enterImageView.getImageReceiver().setAlpha((1.0f - r11) * r315);
            r48.enterImageView.draw(r1);
            r48.enterImageView.getImageReceiver().setAlpha(r315);
            goto L161
        L129:
            if (r63.selected == false) goto L132;
            float r123 = r48.getMeasuredWidth() / r19;
            float r134 = r48.getMeasuredHeight() / r19;
            float r144 = r48.getMeasuredWidth() - AndroidUtilities.dp(r19);
            float r103 = AndroidUtilities.lerp(r144, (r63.getMeasuredWidth() - AndroidUtilities.dp(r19)) / r102, this.this$0.enterTransitionProgress);
            RectF r153 = AndroidUtilities.rectTmp;
            float r104 = r103 / r19;
            r232 = r217;
            r153.set(r123 - r104, r134 - r104, r123 + r104, r134 + r104);
            float r219 = AndroidUtilities.lerp(r144 / r19, AndroidUtilities.dp(4.0f), this.this$0.enterTransitionProgress);
            r1.drawRoundRect(r153, r219, r219, this.this$0.selectAnimatedEmojiDialog.selectorPaint);
        L131:
            r218 = false;
            r48.drawSelected = false;
            if (r53 != 0.0f) goto L137;
            r48.draw(r1);
            r13 = 0.0f;
            r142 = 4;
        L146:
            r48.drawSelected = true;
            if (r63.notDraw == true) goto L161;
            r63.notDraw = true;
            r63.invalidate();
            goto L161
        L137:
            ImageReceiver r105 = r48.loopImageView.getImageReceiver();
            r48.checkPlayLoopImage();
            AnimatedEmojiDrawable r124 = r48.loopImageView.animatedEmojiDrawable;
            if (r124 != null) goto L140;
        L142:
            int[] r125 = r105.getRoundRadius();
            int r135 = 0;
            r142 = 4;
        L143:
            if (r135 >= 4) goto L145;
            this.radiusTmp[r135] = r125[r135];
            r135 = r135 + 1;
            goto L143
        L145:
            r13 = 0.0f;
            r105.setRoundRadius((int) AndroidUtilities.lerp(r92, 0.0f, this.this$0.enterTransitionProgress), (int) AndroidUtilities.lerp(r313, 0.0f, this.this$0.enterTransitionProgress), (int) AndroidUtilities.lerp(r72, 0.0f, this.this$0.enterTransitionProgress), (int) AndroidUtilities.lerp(r53, 0.0f, this.this$0.enterTransitionProgress));
            r48.draw(r1);
            r105.setRoundRadius(this.radiusTmp);
            goto L146
        L140:
            if (r124.getImageReceiver() == null) goto L142;
            r105 = r48.loopImageView.animatedEmojiDrawable.getImageReceiver();
            goto L142
        L132:
            r232 = r217;
            goto L131
        L123:
            if (this.enterTransitionOffsetY != r21) goto L125;
            CustomEmojiReactionsWindow r56 = this.this$0;
            this.enterTransitionOffsetX = AndroidUtilities.lerp((r56.fromRect.left + r7) - r122, r21, r56.enterTransitionProgress);
            CustomEmojiReactionsWindow r57 = this.this$0;
            this.enterTransitionOffsetY = AndroidUtilities.lerp((r57.fromRect.top + r312) - r152, r21, r57.enterTransitionProgress);
            this.enterTransitionScale = AndroidUtilities.lerp(r223 / r133, r223, this.this$0.enterTransitionProgress);
            this.enterTransitionScalePx = r122;
            this.enterTransitionScalePy = r152;
            goto L125
        L116:
            if (r48.selected == false) goto L119;
            r92 = AndroidUtilities.dp(6.0f);
        L118:
            r233 = r92;
            r242 = r233;
            r252 = r242;
            goto L120
        L119:
            r92 = r21;
        L105:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != r12) goto L107;
            float r145 = 0.87f * r132;
            r173 = r173 - AndroidUtilities.dp(0.33f);
            r152 = r152 - AndroidUtilities.dp(1.33f);
        L109:
            if (r63.selected == false) goto L111;
            r145 = r145 * 0.95f;
        L111:
            float r136 = (r132 - r145) / 2.0f;
            r173 = r173 + r136;
            r152 = r152 + r136;
            r132 = r145;
            goto L103
        L107:
            r145 = r132;
            goto L109
        L126:
            r19 = 2.0f;
            r1.translate(r311.getX() + r48.loopImageView.getX(), r311.getY() + r48.loopImageView.getY());
            r313 = 0.0f;
            r53 = 0.0f;
            r72 = 0.0f;
            r92 = 0.0f;
            r102 = 1.0f;
            goto L127
        L164:
            r232 = r217;
            r13 = r10;
            r142 = r12;
            float r49 = (r311.getX() + this.this$0.drawingRect.width()) - this.this$0.reactionsContainerLayout.rect.width();
            float r58 = r311.getY();
            CustomEmojiReactionsWindow r64 = this.this$0;
            r1.translate(r49, (r58 + r64.fromRect.top) - r64.drawingRect.top);
            r222 = 1.0f;
            r1.saveLayerAlpha(0.0f, 0.0f, r311.getMeasuredWidth(), r311.getMeasuredHeight(), (int) ((1.0f - r11) * r18), 31);
            float r220 = this.this$0.enterTransitionProgress;
            r1.scale(1.0f - r220, 1.0f - r220, r9.getMeasuredWidth() >> 1, r9.getMeasuredHeight() >> 1);
            r311.draw(r1);
            r1.restore();
            goto L165
        L82:
            r311 = r310.reactionsContainerLayout.recyclerListView.getChildAt(r217);
            goto L84
        L167:
            r1.restoreToCount(r82);
            goto L168
        L68:
            r46 = 0;
            goto L70
        L61:
            r214 = 0.0f;
        L168:
            super.dispatchDraw(r27);
            if (CustomEmojiReactionsWindow.m12342$$Nest$fgetframeDrawCount(this.this$0) < 5) goto L171;
        L175:
            this.this$0.selectAnimatedEmojiDialog.drawBigReaction(r1, this);
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 5) goto L179;
            r1.restore();
        L179:
            if (CustomEmojiReactionsWindow.m12344$$Nest$fgetvalueAnimator(this.this$0) == null) goto L181;
            invalidate();
        L181:
            HwEmojis.exec();
            return;
        L171:
            if (CustomEmojiReactionsWindow.m12342$$Nest$fgetframeDrawCount(this.this$0) != 3) goto L173;
            int r146 = 1;
            this.this$0.reactionsContainerLayout.setSkipDraw(true);
        L174:
            CustomEmojiReactionsWindow r221 = this.this$0;
            CustomEmojiReactionsWindow.m12346$$Nest$fputframeDrawCount(r221, CustomEmojiReactionsWindow.m12342$$Nest$fgetframeDrawCount(r221) + r146);
            goto L175
        L173:
            r146 = 1;
            goto L174
        L41:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(r29) != 5) goto L44;
        L30:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) == 4) goto L35;
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) == 5) goto L35;
            r26 = 0.0f;
            goto L36
        L37:
            r18 = 255.0f;
            goto L38
        L15:
            if (this.this$0.reactionsContainerLayout.getDelegate() != null) goto L17;
        L19:
            this.shadow.setAlpha((int) (Utilities.clamp(r11 / 0.05f, 1.0f, 0.0f) * 255.0f));
            Drawable r224 = this.shadow;
            RectF r59 = this.this$0.drawingRect;
            int r65 = (int) r59.left;
            Rect r73 = this.shadowPad;
            r17 = 0.05f;
            r224.setBounds(r65 - r73.left, ((int) r59.top) - r73.top, ((int) r59.right) + r73.right, ((int) r59.bottom) + r73.bottom);
            this.shadow.draw(r1);
            CustomEmojiReactionsWindow r225 = this.this$0;
            BlurredBackgroundDrawable r316 = r225.glassBackground;
            if (r316 == null) goto L23;
            GlassMenuHelper.draw(r316, r1, r225.drawingRect, r4, 255);
        L22:
            r8 = r4;
            r14 = 1;
            goto L26
        L23:
            r1.drawRoundRect(r225.drawingRect, r4, r4, this.backgroundPaint);
            goto L22
        L17:
            if (this.this$0.reactionsContainerLayout.getDelegate().drawBackground() == false) goto L19;
        L11:
            r24 = 8.0f;
            goto L12
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) == 1) goto L14;
            CustomEmojiReactionsWindow r0 = this.this$0;
            if (r0.glassBackground != null) goto L14;
            ReactionsContainerLayout r02 = r0.reactionsContainerLayout;
            if (r02 != null) goto L9;
            return;
        L9:
            if (r02.getDelegate() != null) goto L11;
            return;
        L11:
            if (this.this$0.reactionsContainerLayout.getDelegate().drawBackground() == true) goto L14;
            return;
        L14:
            this.this$0.selectAnimatedEmojiDialog.invalidateSearchBox();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 1) goto L5;
        L15:
            int r92 = this.this$0.reactionsContainerLayout.getMeasuredWidth();
        L17:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 4) goto L21;
            int r102 = AndroidUtilities.dp(36.0f) * 8;
            int r0 = AndroidUtilities.dp(8.0f);
        L19:
            int r103 = r102 - r0;
        L28:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 5) goto L30;
            r103 = Math.min(AndroidUtilities.dp(254.0f), r103);
        L30:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r92, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r103, TLObject.FLAG_30));
            return;
        L21:
            if (this.this$0.reactionsContainerLayout.showExpandableReactions() == false) goto L26;
            int r104 = (int) Math.ceil(this.this$0.reactions.size() / 8.0f);
            if (r104 > 8) goto L25;
            r103 = (r104 * AndroidUtilities.dp(36.0f)) + AndroidUtilities.dp(8.0f);
            goto L28
        L25:
            r102 = AndroidUtilities.dp(36.0f) * 8;
            r0 = AndroidUtilities.dp(8.0f);
            goto L19
        L26:
            r103 = r92;
            goto L28
        L5:
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) == 2) goto L15;
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) == 4) goto L15;
            if (CustomEmojiReactionsWindow.m12343$$Nest$fgettype(this.this$0) != 5) goto L12;
            r92 = (AndroidUtilities.dp(36.0f) * 8) + AndroidUtilities.dp(12.0f);
            goto L17
        L12:
            r92 = Math.min(View.MeasureSpec.getSize(r9), View.MeasureSpec.getSize(r10));
            int r105 = (AndroidUtilities.dp(36.0f) * 8) + AndroidUtilities.dp(12.0f);
            if (r105 >= r92) goto L17;
            r92 = r105;
            goto L17
        }
    }

    /* renamed from: $r8$lambda$-idh_mpLi2WXdNJjKnOqymGDjas, reason: not valid java name */
    public static /* synthetic */ void m12338$r8$lambda$idh_mpLi2WXdNJjKnOqymGDjas(CustomEmojiReactionsWindow r0) {
        r0.lambda$new$2();
    }

    /* renamed from: $r8$lambda$6oMFllDYRe1NLUqDBk0f-UeLqbA, reason: not valid java name */
    public static /* synthetic */ void m12339$r8$lambda$6oMFllDYRe1NLUqDBk0fUeLqbA(CustomEmojiReactionsWindow r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$B-GxiiMrb-GZxc2yKUnlSQE_h6Y, reason: not valid java name */
    public static /* synthetic */ void m12340$r8$lambda$BGxiiMrbGZxc2yKUnlSQE_h6Y(CustomEmojiReactionsWindow r0, ArrayList r1, ValueAnimator r2) {
        r0.lambda$updateCascadeEnter$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Dzaru1lCZfay1u9LAgl0VmPOSec(CustomEmojiReactionsWindow r0, ValueAnimator r1) {
        r0.lambda$dismiss$8(r1);
    }

    /* renamed from: $r8$lambda$J9Zz_cH5ZcsiZ6uw-vhDLiUj4Js, reason: not valid java name */
    public static /* synthetic */ void m12341$r8$lambda$J9Zz_cH5ZcsiZ6uwvhDLiUj4Js(CustomEmojiReactionsWindow r0, ReactionsContainerLayout r1, Canvas r2, int r3, int r4, int r5, int r6, float r7, float r8) {
        r0.lambda$new$1(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$PWoK7r7T2B_9I0B5sD_mKtlbM0Q(CustomEmojiReactionsWindow r0, ReactionsContainerLayout r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$SpSh5ackqxyh2TZtFEa9oEnVbIo(CustomEmojiReactionsWindow r0, Canvas r1, int r2, int r3, int r4, int r5, float r6, float r7) {
        r0.lambda$setGlassBackground$9(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$cWqSDLsktggDYlqF1DD61Q5zOtc(CustomEmojiReactionsWindow r0, boolean r1, ValueAnimator r2) {
        r0.lambda$createTransition$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ebjh2s034UtsfS1qxYvcXxlibXc(CustomEmojiReactionsWindow r0, ValueAnimator r1) {
        r0.lambda$updateWindowPosition$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$uaxEErRR8c2DWJosStVu4DFBuCY(CustomEmojiReactionsWindow r0) {
        r0.lambda$removeView$7();
    }

    /* renamed from: -$$Nest$fgetframeDrawCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12342$$Nest$fgetframeDrawCount(CustomEmojiReactionsWindow r0) {
        return r0.frameDrawCount;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12343$$Nest$fgettype(CustomEmojiReactionsWindow r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fgetvalueAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m12344$$Nest$fgetvalueAnimator(CustomEmojiReactionsWindow r0) {
        return r0.valueAnimator;
    }

    /* renamed from: -$$Nest$fgetwasFocused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12345$$Nest$fgetwasFocused(CustomEmojiReactionsWindow r0) {
        return r0.wasFocused;
    }

    /* renamed from: -$$Nest$fputframeDrawCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12346$$Nest$fputframeDrawCount(CustomEmojiReactionsWindow r0, int r1) {
        r0.frameDrawCount = r1;
    }

    /* renamed from: -$$Nest$fputwasFocused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12347$$Nest$fputwasFocused(CustomEmojiReactionsWindow r0, boolean r1) {
        r0.wasFocused = r1;
    }

    /* renamed from: -$$Nest$mcheckAnimationEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12348$$Nest$mcheckAnimationEnd(CustomEmojiReactionsWindow r0, boolean r1) {
        r0.checkAnimationEnd(r1);
    }

    /* renamed from: -$$Nest$mcreateLayoutParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager.LayoutParams m12349$$Nest$mcreateLayoutParams(CustomEmojiReactionsWindow r0, boolean r1) {
        return r0.createLayoutParams(r1);
    }

    /* renamed from: -$$Nest$mgetBlurOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12350$$Nest$mgetBlurOffset(CustomEmojiReactionsWindow r0) {
        return r0.getBlurOffset();
    }

    /* renamed from: -$$Nest$mshowUnlockPremiumAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12351$$Nest$mshowUnlockPremiumAlert(CustomEmojiReactionsWindow r0) {
        r0.showUnlockPremiumAlert();
    }

    /* renamed from: -$$Nest$mupdateContainersAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12352$$Nest$mupdateContainersAlpha(CustomEmojiReactionsWindow r0) {
        r0.updateContainersAlpha();
    }

    /* renamed from: -$$Nest$mupdateContentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12353$$Nest$mupdateContentPosition(CustomEmojiReactionsWindow r0) {
        r0.updateContentPosition();
    }

    /* renamed from: -$$Nest$mupdateWindowPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12354$$Nest$mupdateWindowPosition(CustomEmojiReactionsWindow r0) {
        r0.updateWindowPosition();
    }

    public CustomEmojiReactionsWindow(int r26, BaseFragment r27, List<ReactionsLayoutInBubble.VisibleReaction> r28, HashSet<ReactionsLayoutInBubble.VisibleReaction> r29, ReactionsContainerLayout r30, Theme.ResourcesProvider r31, boolean r32) {
        this.fromRect = new RectF();
        this.drawingRect = new RectF();
        this.pathToClipApi20 = new Path();
        this.location = new int[2];
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.animatingEnterChild = new HashSet();
        this.animators = new ArrayList();
        this.frameDrawCount = 0;
        this.type = r26;
        this.reactions = r28;
        this.baseFragment = r27;
        this.resourcesProvider = r31;
        if (r27 == null) goto L6;
        Context r0 = r27.getContext();
    L5:
        Context r3 = r0;
        AnonymousClass1 r02 = new AnonymousClass1(this, r3);
        this.windowView = r02;
        r02.setOnClickListener(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda1(this));
        if (r26 != 2) goto L10;
    L15:
        boolean r5 = true;
    L16:
        this.attachToParent = r5;
        this.containerView = new ContainerView(this, r3);
        int r6 = r30.getWindowType();
        if (r26 == 1) goto L19;
        boolean r7 = true;
    L20:
        AnonymousClass2 r03 = new AnonymousClass2(this, r27, r3, false, null, r6, r7, r31, 16, r30, r27);
        this.selectAnimatedEmojiDialog = r03;
        r03.setOutlineProvider(new AnonymousClass3(this));
        this.selectAnimatedEmojiDialog.setClipToOutline(true);
        this.selectAnimatedEmojiDialog.setPaused(r30.paused, r30.pausedExceptSelected);
        this.selectAnimatedEmojiDialog.setOnLongPressedListener(new AnonymousClass4(this, r30));
        this.selectAnimatedEmojiDialog.setOnRecentClearedListener(new AnonymousClass5(this, r30));
        this.selectAnimatedEmojiDialog.setRecentReactions(r28);
        this.selectAnimatedEmojiDialog.setSelectedReactions(r29);
        this.selectAnimatedEmojiDialog.setDrawBackground(false);
        this.selectAnimatedEmojiDialog.onShow(null);
        this.containerView.addView(this.selectAnimatedEmojiDialog, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
        if (r26 != 5) goto L23;
        int r04 = 2;
    L24:
        if (r26 != 5) goto L26;
        this.containerView.setClipChildren(false);
        this.containerView.setClipToPadding(false);
        this.windowView.setClipChildren(false);
        this.windowView.setClipToPadding(false);
    L26:
        FrameLayout r2 = this.windowView;
        ContainerView r4 = this.containerView;
        if (r26 != 5) goto L30;
        int r52 = 85;
    L31:
        float r05 = r04;
        r2.addView(r4, LayoutHelper.createFrame(-1, -1.0f, r52, r05, r05, r05, 16.0f));
        this.windowView.setClipChildren(false);
        if (r26 != 1) goto L34;
    L37:
        this.selectAnimatedEmojiDialog.setBackgroundDelegate(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda2(this, r30));
    L39:
        if (this.attachToParent == false) goto L41;
        ((ViewGroup) r30.getParent()).addView(this.windowView);
    L42:
        this.reactionsContainerLayout = r30;
        r30.setOnSwitchedToLoopView(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda3(this));
        r30.prepareAnimation(true);
        AndroidUtilities.runOnUIThread(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda4(this, r30), 50);
        if (r26 == 5) goto L46;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{7});
        return;
    L46:
        return;
    L41:
        WindowManager.LayoutParams r06 = createLayoutParams(false);
        WindowManager r22 = AndroidUtilities.findActivity(r3).getWindowManager();
        this.windowManager = r22;
        AndroidUtilities.setPreferredMaxRefreshRate(r22, this.windowView, r06);
        this.windowManager.addView(this.windowView, r06);
        goto L42
    L34:
        if (r30.getDelegate() == null) goto L39;
        if (r30.getDelegate().drawBackground() == false) goto L39;
    L30:
        r52 = 48;
        goto L31
    L23:
        r04 = 16;
        goto L24
    L19:
        r7 = false;
        goto L20
    L10:
        if (r26 == 4) goto L15;
        if (r26 == 5) goto L15;
        if (r32 == true) goto L15;
        r5 = false;
        goto L16
    L6:
        r0 = r30.getContext();
        goto L5
    }

    private void checkAnimationEnd(boolean r4) {
        if (this.animators.isEmpty() == false) goto L26;
        switchLayerType(false);
        HwEmojis.disableHw();
        this.notificationsLocker.unlock();
        this.selectAnimatedEmojiDialog.setEnterAnimationInProgress(false);
        if (r4 == false) goto L27;
        this.selectAnimatedEmojiDialog.emojiTabs.showRecentTabStub(false);
        this.selectAnimatedEmojiDialog.emojiGridView.invalidate();
        this.selectAnimatedEmojiDialog.emojiGridView.invalidateViews();
        this.selectAnimatedEmojiDialog.searchBox.checkInitialization();
        this.selectAnimatedEmojiDialog.sendAccessibilityEvent(32);
        this.reactionsContainerLayout.setImportantForAccessibility(4);
        int r42 = 0;
    L8:
        if (r42 >= this.selectAnimatedEmojiDialog.emojiGridView.getChildCount()) goto L13;
        if ((this.selectAnimatedEmojiDialog.emojiGridView.getChildAt(r42) instanceof SelectAnimatedEmojiDialog.ImageViewEmoji) == true) goto L11;
        r42 = r42 + 1;
        goto L8
    L11:
        View r43 = this.selectAnimatedEmojiDialog.emojiGridView.getChildAt(r42);
    L15:
        if (r43 == null) goto L17;
        r43.performAccessibilityAction(64, null);
    L18:
        float r44 = this.reactionsContainerLayout.getPullingLeftProgress();
        ReactionsContainerLayout r1 = this.reactionsContainerLayout;
        if (r44 <= 0.0f) goto L21;
        r1.isHiddenNextReaction = false;
        r1.onCustomEmojiWindowOpened();
    L22:
        this.selectAnimatedEmojiDialog.resetBackgroundBitmaps();
        syncReactionFrames();
        this.containerView.invalidate();
        return;
    L21:
        r1.isHiddenNextReaction = true;
        r1.onCustomEmojiWindowOpened();
        goto L22
    L17:
        this.selectAnimatedEmojiDialog.performAccessibilityAction(64, null);
        goto L18
    L13:
        r43 = null;
        goto L15
    L27:
        return;
    }

    private WindowManager.LayoutParams createLayoutParams(boolean r3) {
        WindowManager.LayoutParams r0 = new WindowManager.LayoutParams();
        r0.height = -1;
        r0.width = -1;
        int r2 = this.type;
        if (r2 != 0) goto L5;
    L8:
        int r22 = 1000;
    L9:
        r0.type = r22;
        r0.softInputMode = 16;
        if (r3 == false) goto L12;
        r0.flags = 65792;
    L13:
        r0.format = -3;
        return r0;
    L12:
        r0.flags = 65800;
        goto L13
    L5:
        if (r2 == 3) goto L8;
        r22 = 99;
        goto L9
    }

    private void createTransition(boolean r13) {
        this.fromRect.set(this.reactionsContainerLayout.rect);
        ReactionsContainerLayout r0 = this.reactionsContainerLayout;
        this.fromRadius = r0.radius;
        int[] r2 = new int[2];
        if (r13 == false) goto L5;
        r0.getLocationOnScreen(this.location);
    L5:
        this.windowView.getLocationOnScreen(r2);
        int r02 = ((this.location[1] - r2[1]) - AndroidUtilities.dp(44.0f)) - AndroidUtilities.dp(52.0f);
        if (this.selectAnimatedEmojiDialog.includeHint == false) goto L8;
        int r4 = AndroidUtilities.dp(26.0f);
    L9:
        float r03 = (r02 - r4) + this.reactionsContainerLayout.getTopOffset();
        if (this.reactionsContainerLayout.showExpandableReactions() == false) goto L13;
        r03 = (this.location[1] - r2[1]) - AndroidUtilities.dp(12.0f);
    L13:
        if ((this.containerView.getMeasuredHeight() + r03) <= (this.windowView.getMeasuredHeight() - AndroidUtilities.dp(32.0f))) goto L16;
        r03 = (this.windowView.getMeasuredHeight() - AndroidUtilities.dp(32.0f)) - this.containerView.getMeasuredHeight();
    L16:
        if (r03 >= AndroidUtilities.dp(16.0f)) goto L18;
        r03 = AndroidUtilities.dp(16.0f);
    L18:
        float r8 = 0.0f;
        if (this.type != 5) goto L21;
        r03 = Math.min(r03, 0.0f);
    L21:
        int r6 = this.type;
        if (r6 != 1) goto L24;
        this.containerView.setTranslationX(((this.windowView.getMeasuredWidth() - this.containerView.getMeasuredWidth()) / 2.0f) - AndroidUtilities.dp(16.0f));
    L29:
        if (r13 == true) goto L31;
        this.yTranslation = this.containerView.getTranslationY();
    L32:
        RectF r04 = this.fromRect;
        float r42 = (this.location[0] - r2[0]) - this.containerView.getX();
        this.fromRectTranslateX = r42;
        float r22 = (this.location[1] - r2[1]) - this.containerView.getY();
        this.fromRectTranslateY = r22;
        r04.offset(r42, r22);
        this.reactionsContainerLayout.setCustomEmojiEnterProgress(this.enterTransitionProgress);
        if (r13 == true) goto L35;
        this.cascadeAnimation = false;
    L43:
        if (this.cascadeAnimation == false) goto L45;
        updateCascadeEnter(0.0f, true);
    L45:
        updateContainersAlpha();
        this.selectAnimatedEmojiDialog.setEnterAnimationInProgress(true);
        EmojiTabsStrip r05 = this.selectAnimatedEmojiDialog.emojiTabs;
        if (r13 == true) goto L48;
    L50:
        boolean r23 = false;
    L51:
        r05.showRecentTabStub(r23);
        this.account = UserConfig.selectedAccount;
        this.notificationsLocker.lock();
        ValueAnimator r06 = this.valueAnimator;
        if (r06 == null) goto L54;
        r06.cancel();
    L54:
        this.transition = true;
        int r07 = this.type;
        float r24 = this.enterTransitionProgress;
        if (r07 != 4) goto L59;
        if (r13 == false) goto L58;
        r8 = 1.0f;
    L58:
        ValueAnimator r08 = ValueAnimator.ofFloat(new float[]{r24, r8});
    L62:
        this.valueAnimator = r08;
        r08.addUpdateListener(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda6(this, r13));
        if (r13 == true) goto L65;
        syncReactionFrames();
    L65:
        this.valueAnimator.addListener(new AnonymousClass6(this, r13));
        if (this.type != 4) goto L68;
        this.valueAnimator.setDuration(420);
        this.valueAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L72:
        this.containerView.invalidate();
        switchLayerType(true);
        ReactionsContainerLayout r09 = this.reactionsContainerLayout;
        if (r13 == true) goto L75;
        r09.isHiddenNextReaction = true;
        r09.invalidate();
        this.valueAnimator.setStartDelay(30);
        this.valueAnimator.start();
    L76:
        HwEmojis.enableHw();
        return;
    L75:
        r09.setCustomEmojiReactionsBackground(false);
        ValueAnimator r132 = this.valueAnimator;
        Objects.requireNonNull(r132);
        HwEmojis.prepare(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda7(r132), this.cascadeAnimation);
        goto L76
    L68:
        boolean r010 = this.cascadeAnimation;
        ValueAnimator r1 = this.valueAnimator;
        if (r010 == false) goto L71;
        r1.setDuration(450);
        this.valueAnimator.setInterpolator(new OvershootInterpolator(0.5f));
        goto L72
    L71:
        r1.setDuration(350);
        this.valueAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        goto L72
    L59:
        if (r13 == false) goto L61;
        r8 = 1.0f;
    L61:
        r08 = StableAnimator.ofFloat(new float[]{r24, r8});
        goto L62
    L48:
        if (this.cascadeAnimation == false) goto L50;
        r23 = true;
        goto L51
    L35:
        if (SharedConfig.getDevicePerformanceClass() >= 2) goto L37;
    L39:
        boolean r011 = false;
    L40:
        this.cascadeAnimation = r011;
        this.enterTransitionFinished = false;
        goto L43
    L37:
        if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == false) goto L39;
        r011 = true;
        goto L40
    L31:
        this.yTranslation = r03;
        this.containerView.setTranslationY(r03);
        goto L32
    L24:
        if (r6 == 2) goto L28;
        if (r6 == 4) goto L28;
        this.containerView.setTranslationX((this.location[0] - r2[0]) - AndroidUtilities.dp(2.0f));
    L28:
        this.containerView.setTranslationX((this.location[0] - r2[0]) - AndroidUtilities.dp(18.0f));
        goto L29
    L8:
        r4 = 0;
        goto L9
    }

    private float getBlurOffset() {
        int r0 = this.type;
        ContainerView r1 = this.containerView;
        if (r0 != 1) goto L7;
        return r1.getY() - AndroidUtilities.statusBarHeight;
    L7:
        return r1.getY() + this.windowView.getY();
    }

    private /* synthetic */ void lambda$createTransition$5(boolean r4, ValueAnimator r5) {
        this.valueAnimator = null;
        this.enterTransitionProgress = ((Float) r5.getAnimatedValue()).floatValue();
        updateContainersAlpha();
        updateContentPosition();
        this.reactionsContainerLayout.setCustomEmojiEnterProgress(Utilities.clamp(this.enterTransitionProgress, 1.0f, 0.0f));
        this.invalidatePath = true;
        this.containerView.invalidate();
        this.selectAnimatedEmojiDialog.invalidateOutline();
        if (this.cascadeAnimation == false) goto L6;
        updateCascadeEnter(this.enterTransitionProgress, r4);
        return;
    }

    private /* synthetic */ void lambda$dismiss$8(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.dismissProgress = r22;
        this.containerView.setAlpha(1.0f - r22);
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        if (this.enterTransitionFinished == false) goto L6;
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$new$1(ReactionsContainerLayout r2, Canvas r3, int r4, int r5, int r6, int r7, float r8, float r9) {
        RectF r32 = AndroidUtilities.rectTmp;
        r32.set(r4, r5, r6, r7);
        r2.getDelegate().drawRoundRect(r3, r32, 0.0f, this.containerView.getX() + r8, getBlurOffset() + r9, 255, true);
    }

    private /* synthetic */ void lambda$new$2() {
        this.containerView.invalidate();
    }

    private /* synthetic */ void lambda$new$3(ReactionsContainerLayout r3) {
        this.isShowing = true;
        this.containerView.invalidate();
        r3.prepareAnimation(false);
        createTransition(true);
    }

    private /* synthetic */ void lambda$removeView$7() {
        if (this.windowView.getParent() != null) goto L6;
        return;
    L6:
        if (this.attachToParent == false) goto L14;
        AndroidUtilities.removeFromParent(this.windowView);
    L9:
        Runnable r2 = this.onDismiss;
        if (r2 == null) goto L16;
        r2.run();
        return;
    L16:
        return;
    L14:
        this.windowManager.removeView(this.windowView);     // Catch: Exception -> L13
        goto L9
    }

    private /* synthetic */ void lambda$setGlassBackground$9(Canvas r2, int r3, int r4, int r5, int r6, float r7, float r8) {
        RectF r32 = AndroidUtilities.rectTmp;
        r32.set(r3, r4, r5, r6);
        GlassMenuHelper.draw(this.glassBackgroundSearchBox, r2, r32, 0.0f, 255, r7, r8);
    }

    private /* synthetic */ void lambda$updateCascadeEnter$6(ArrayList r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L6;
        setScaleForChild((View) r3.get(r0), r42);
        r0 = r0 + 1;
        goto L4
    L6:
        this.selectAnimatedEmojiDialog.emojiGridViewContainer.invalidate();
    }

    private /* synthetic */ void lambda$updateWindowPosition$4(ValueAnimator r1) {
        this.containerView.invalidate();
    }

    private void setScaleForChild(View r1, float r2) {
        if ((r1 instanceof SelectAnimatedEmojiDialog.ImageViewEmoji) == false) goto L7;
        ((SelectAnimatedEmojiDialog.ImageViewEmoji) r1).setAnimatedScale(r2);
        return;
    L7:
        if ((r1 instanceof EmojiTabsStrip.EmojiTabButton) == false) goto L10;
        r1.setScaleX(r2);
        r1.setScaleY(r2);
        return;
    }

    private void showUnlockPremiumAlert() {
        BaseFragment r0 = this.baseFragment;
        if ((r0 instanceof ChatActivity) == false) goto L6;
        r0.showDialog(new PremiumFeatureBottomSheet(this.baseFragment, 11, false));
        return;
    L6:
        BaseFragment r02 = LaunchActivity.getLastFragment();
        if (r02 == null) goto L10;
        r02.showDialog(new PremiumFeatureBottomSheet(this.baseFragment, 11, false));
        return;
    }

    private void switchLayerType(boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L5;
        int r52 = 2;
    L6:
        this.selectAnimatedEmojiDialog.emojiGridView.setLayerType(r52, null);
        this.selectAnimatedEmojiDialog.searchBox.setLayerType(r52, null);
        if (this.cascadeAnimation == true) goto L9;
        this.selectAnimatedEmojiDialog.emojiTabsShadow.setLayerType(r52, null);
        this.selectAnimatedEmojiDialog.emojiTabs.setLayerType(r52, null);
        return;
    L9:
        if (r0 >= Math.min(this.selectAnimatedEmojiDialog.emojiTabs.contentView.getChildCount(), 16)) goto L11;
        this.selectAnimatedEmojiDialog.emojiTabs.contentView.getChildAt(r0).setLayerType(r52, null);
        r0 = r0 + 1;
        goto L9
    L11:
        return;
    L5:
        r52 = 0;
        goto L6
    }

    private void syncReactionFrames() {
        int r1 = 0;
    L4:
        if (r1 >= this.selectAnimatedEmojiDialog.emojiGridView.getChildCount()) goto L11;
        if ((this.selectAnimatedEmojiDialog.emojiGridView.getChildAt(r1) instanceof SelectAnimatedEmojiDialog.ImageViewEmoji) == false) goto L10;
        SelectAnimatedEmojiDialog.ImageViewEmoji r2 = (SelectAnimatedEmojiDialog.ImageViewEmoji) this.selectAnimatedEmojiDialog.emojiGridView.getChildAt(r1);
        if (r2.reaction == null) goto L10;
        r2.notDraw = false;
        r2.invalidate();
    L10:
        r1 = r1 + 1;
        goto L4
    }

    private void updateCascadeEnter(float r12, boolean r13) {
        int r0 = (int) ((this.selectAnimatedEmojiDialog.getY() + this.selectAnimatedEmojiDialog.contentView.getY()) + this.selectAnimatedEmojiDialog.emojiGridView.getY());
        ArrayList r1 = null;
        int r2 = 0;
        int r3 = 0;
        boolean r4 = false;
    L3:
        int r5 = this.selectAnimatedEmojiDialog.emojiGridView.getChildCount();
        SelectAnimatedEmojiDialog r6 = this.selectAnimatedEmojiDialog;
        if (r3 >= r5) goto L19;
        View r52 = r6.emojiGridView.getChildAt(r3);
        if (this.animatingEnterChild.contains(r52) == true) goto L18;
        float r62 = (r52.getTop() + r0) + (r52.getMeasuredHeight() / 2.0f);
        RectF r7 = this.drawingRect;
        if (r62 < r7.bottom) goto L11;
    L17:
        setScaleForChild(r52, 0.0f);
        r4 = true;
        goto L18
    L11:
        if (r62 <= r7.top) goto L17;
        if (r12 == 0.0f) goto L17;
        if (r1 != null) goto L16;
        r1 = new ArrayList();
    L16:
        r1.add(r52);
        this.animatingEnterChild.add(r52);
    L18:
        r3 = r3 + 1;
        goto L3
    L19:
        int r02 = (int) ((r6.getY() + this.selectAnimatedEmojiDialog.contentView.getY()) + this.selectAnimatedEmojiDialog.emojiTabs.getY());
    L21:
        if (r2 >= this.selectAnimatedEmojiDialog.emojiTabs.contentView.getChildCount()) goto L36;
        View r32 = this.selectAnimatedEmojiDialog.emojiTabs.contentView.getChildAt(r2);
        if (this.animatingEnterChild.contains(r32) == true) goto L35;
        float r53 = (r32.getTop() + r02) + (r32.getMeasuredHeight() / 2.0f);
        RectF r63 = this.drawingRect;
        if (r53 < r63.bottom) goto L28;
    L34:
        setScaleForChild(r32, 0.0f);
        r4 = true;
        goto L35
    L28:
        if (r53 <= r63.top) goto L34;
        if (r12 == 0.0f) goto L34;
        if (r1 != null) goto L33;
        r1 = new ArrayList();
    L33:
        r1.add(r32);
        this.animatingEnterChild.add(r32);
    L35:
        r2 = r2 + 1;
        goto L21
    L36:
        if (r4 == false) goto L38;
        this.selectAnimatedEmojiDialog.emojiGridViewContainer.invalidate();
    L38:
        if (r1 == null) goto L53;
        ValueAnimator r122 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r122.addUpdateListener(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda9(this, r1));
        this.animators.add(r122);
        r122.addListener(new AnonymousClass7(this, r122, r13));
        if (this.type != 4) goto L42;
        r122.setDuration(420);
        r122.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L43:
        r122.start();
        return;
    L42:
        r122.setDuration(350);
        r122.setInterpolator(new OvershootInterpolator(1.0f));
        goto L43
    }

    private void updateContainersAlpha() {
        if (this.cascadeAnimation == true) goto L6;
        this.selectAnimatedEmojiDialog.searchBox.setAlpha(this.enterTransitionProgress);
        this.selectAnimatedEmojiDialog.emojiGridView.setAlpha(this.enterTransitionProgress);
        this.selectAnimatedEmojiDialog.emojiSearchGridView.setAlpha(this.enterTransitionProgress);
        this.selectAnimatedEmojiDialog.emojiTabs.setAlpha(this.enterTransitionProgress);
        this.selectAnimatedEmojiDialog.emojiTabsShadow.setAlpha(this.enterTransitionProgress);
        return;
    }

    private void updateContentPosition() {
        FrameLayout r0 = this.selectAnimatedEmojiDialog.contentView;
        if (this.cascadeAnimation == false) goto L5;
        float r1 = 0.0f;
    L6:
        r0.setTranslationX(r1);
        this.selectAnimatedEmojiDialog.contentView.setTranslationY(this.containerView.enterTransitionOffsetY);
        this.selectAnimatedEmojiDialog.contentView.setPivotX(this.containerView.enterTransitionScalePx);
        this.selectAnimatedEmojiDialog.contentView.setPivotY(this.containerView.enterTransitionScalePy);
        this.selectAnimatedEmojiDialog.contentView.setScaleX(this.containerView.enterTransitionScale);
        this.selectAnimatedEmojiDialog.contentView.setScaleY(this.containerView.enterTransitionScale);
        return;
    L5:
        r1 = this.containerView.enterTransitionOffsetX;
        goto L6
    }

    private void updateWindowPosition() {
        if (this.dismissed == false) goto L5;
        return;
    L5:
        float r0 = this.yTranslation;
        int r1 = AndroidUtilities.dp(32.0f);
        int r2 = this.type;
        if (r2 != 1) goto L8;
    L9:
        r1 = AndroidUtilities.dp(24.0f);
    L10:
        float r12 = r1;
        if ((this.containerView.getMeasuredHeight() + r0) <= ((this.windowView.getMeasuredHeight() - this.keyboardHeight) - r12)) goto L14;
        r0 = ((this.windowView.getMeasuredHeight() - this.keyboardHeight) - this.containerView.getMeasuredHeight()) - r12;
    L14:
        if (r0 >= 0.0f) goto L16;
        r0 = 0.0f;
    L16:
        this.containerView.animate().translationY(r0).setDuration(250).setUpdateListener(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda10(this)).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    L8:
        if (r2 != 2) goto L10;
        goto L9
    }

    public void dismiss() {
        if (this.dismissed == true) goto L16;
        ReactionsContainerLayout r0 = this.reactionsContainerLayout;
        if (r0 == null) goto L8;
        r0.onCustomEmojiWindowClosing();
    L8:
        Bulletin.hideVisible();
        this.dismissed = true;
        AndroidUtilities.hideKeyboard(this.windowView);
        createTransition(false);
        if (this.wasFocused == false) goto L14;
        BaseFragment r2 = this.baseFragment;
        if ((r2 instanceof ChatActivity) == false) goto L15;
        ((ChatActivity) r2).onEditTextDialogClose(true, true);
        return;
    L15:
        return;
    L14:
        return;
    }

    public void dismiss(boolean r3) {
        if (this.dismissed == false) goto L6;
        if (r3 == false) goto L6;
        return;
    L6:
        this.dismissed = true;
        if (r3 == true) goto L10;
        removeView();
        return;
    L10:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r32.addUpdateListener(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda5(this));
        r32.addListener(new AnonymousClass8(this));
        r32.setDuration(150);
        r32.start();
    }

    public void dismissWithAlpha() {
        if (this.dismissed == true) goto L13;
        Bulletin.hideVisible();
        this.dismissed = true;
        AndroidUtilities.hideKeyboard(this.windowView);
        this.windowView.animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass9(this));
        if (this.wasFocused == false) goto L11;
        BaseFragment r4 = this.baseFragment;
        if ((r4 instanceof ChatActivity) == false) goto L12;
        ((ChatActivity) r4).onEditTextDialogClose(true, true);
        return;
    L12:
        return;
    L11:
        return;
    }

    public SelectAnimatedEmojiDialog getSelectAnimatedEmojiDialog() {
        return this.selectAnimatedEmojiDialog;
    }

    public boolean isShowing() {
        return !this.dismissed;
    }

    public void onDismissListener(Runnable r1) {
        this.onDismiss = r1;
    }

    public void removeView() {
        if (this.type == 5) goto L5;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{7});
    L5:
        AndroidUtilities.runOnUIThread(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda8(this));
    }

    public void setGlassBackground(BlurredBackgroundDrawable r1, BlurredBackgroundDrawable r2) {
        this.glassBackground = r1;
        this.glassBackgroundSearchBox = r2;
        SelectAnimatedEmojiDialog.SearchBox r12 = this.selectAnimatedEmojiDialog.searchBox;
        if (r12 == null) goto L5;
        r12.setUseCustomBackground();
    L5:
        this.selectAnimatedEmojiDialog.setBackgroundDelegate(new CustomEmojiReactionsWindow$$ExternalSyntheticLambda0(this));
        this.containerView.invalidate();
    }

    public void setLongPressEnabled(boolean r1) {
        this.selectAnimatedEmojiDialog.setLongPressEnabled(r1);
    }

    public void setRecentReactions(List<ReactionsLayoutInBubble.VisibleReaction> r1) {
        this.selectAnimatedEmojiDialog.setRecentReactions(r1);
    }
}
