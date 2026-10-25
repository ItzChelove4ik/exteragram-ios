package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.utils.AppUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes3.dex */
public abstract class RightSlidingDialogContainer extends FrameLayout {
    public static long fragmentDialogId;
    private Paint actionModePaint;
    private int currentAccount;
    ActionBar currentActionBarView;
    BaseFragment currentFragment;
    View currentFragmentFullscreenView;
    View currentFragmentView;
    float currentTop;
    public boolean enabled;
    public int fragmentViewPadding;
    boolean isOpenned;
    boolean isPaused;
    int lastSize;
    private boolean maybeStartTracking;
    INavigationLayout navigationLayout;
    private AnimationNotificationsLocker notificationsLocker;
    ValueAnimator openAnimator;
    float openedProgress;
    SpringAnimation replaceAnimation;
    private boolean replaceAnimationInProgress;
    float replaceProgress;
    BaseFragment replacingFragment;
    protected boolean startedTracking;
    private int startedTrackingPointerId;
    private int startedTrackingX;
    private int startedTrackingY;
    float swipeBackX;
    private VelocityTracker velocityTracker;

    /* renamed from: org.telegram.ui.RightSlidingDialogContainer$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ RightSlidingDialogContainer this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass1(RightSlidingDialogContainer r1, BaseFragment r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            RightSlidingDialogContainer r32 = this.this$0;
            if (r32.openAnimator != null) goto L5;
            return;
        L5:
            r32.openAnimator = null;
            RightSlidingDialogContainer.m18504$$Nest$fgetnotificationsLocker(r32).unlock();
            this.val$fragment.onTransitionAnimationEnd(true, false);
            RightSlidingDialogContainer r33 = this.this$0;
            r33.openedProgress = 1.0f;
            r33.updateOpenAnimationProgress();
            this.this$0.openAnimationFinished(false);
        }
    }

    /* renamed from: org.telegram.ui.RightSlidingDialogContainer$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ RightSlidingDialogContainer this$0;

        public AnonymousClass2(RightSlidingDialogContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            RightSlidingDialogContainer r22 = this.this$0;
            if (r22.openAnimator != null) goto L5;
            return;
        L5:
            r22.openAnimator = null;
            r22.openedProgress = 0.0f;
            r22.updateOpenAnimationProgress();
            RightSlidingDialogContainer.m18504$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            RightSlidingDialogContainer.m18505$$Nest$mdestroyCurrentFragment(this.this$0);
            this.this$0.openAnimationFinished(false);
        }
    }

    /* renamed from: org.telegram.ui.RightSlidingDialogContainer$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ RightSlidingDialogContainer this$0;

        public AnonymousClass3(RightSlidingDialogContainer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            RightSlidingDialogContainer r0 = this.this$0;
            if (r0.openAnimator != null) goto L5;
            return;
        L5:
            r0.openAnimator = null;
            r0.openAnimationFinished(true);
        }
    }

    public interface BaseFragmentWithFullscreen {
        View getFullscreenView();
    }

    public static /* synthetic */ void $r8$lambda$MuAchVayraoD_Bi1nD2bFyCe7cA(RightSlidingDialogContainer r0) {
        r0.lambda$presentFragment$1();
    }

    public static /* synthetic */ void $r8$lambda$PNBQ8lyKj06i3ZXvrNtQokiwXsk(RightSlidingDialogContainer r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$animateReplace$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$SIkwPlJTa8knuwzjVv_6P6Eu2-I, reason: not valid java name */
    public static /* synthetic */ void m18503$r8$lambda$SIkwPlJTa8knuwzjVv_6P6Eu2I(RightSlidingDialogContainer r0, ValueAnimator r1) {
        r0.lambda$finishPreviewInernal$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$gE81AEqsgVhA53QGZvkM00H01FA(RightSlidingDialogContainer r0, ValueAnimator r1) {
        r0.lambda$onTouchEvent$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$iDGN7A3IOouHtvXsmPJ0_t23ehY(RightSlidingDialogContainer r0, BaseFragment r1, BaseFragment r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        r0.lambda$animateReplace$3(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$u6dXrCTNwVBl_SXB2b4pf52KdHQ(RightSlidingDialogContainer r0, ValueAnimator r1) {
        r0.lambda$presentFragment$0(r1);
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m18504$$Nest$fgetnotificationsLocker(RightSlidingDialogContainer r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$mdestroyCurrentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18505$$Nest$mdestroyCurrentFragment(RightSlidingDialogContainer r0) {
        r0.destroyCurrentFragment();
    }

    public RightSlidingDialogContainer(Context r1) {
        super(r1);
        this.openedProgress = 0.0f;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.currentAccount = UserConfig.selectedAccount;
        this.enabled = true;
    }

    private void animateReplace(BaseFragment r7) {
        BaseFragment r0 = this.currentFragment;
        if (SharedConfig.animationsEnabled() == true) goto L6;
        r0.onTransitionAnimationStart(true, false);
        r0.onTransitionAnimationEnd(true, false);
        setReplaceProgress(r7, r0, 1.0f);
        this.replaceAnimationInProgress = false;
        this.replacingFragment = null;
        r7.onPause();
        r7.onFragmentDestroy();
        removeView(r7.getFragmentView());
        removeView(r7.getActionBar());
        this.notificationsLocker.unlock();
        return;
    L6:
        SpringAnimation r1 = this.replaceAnimation;
        if (r1 == null) goto L9;
        r1.cancel();
    L9:
        r0.onTransitionAnimationStart(true, false);
        this.replacingFragment = r7;
        this.replaceAnimationInProgress = true;
        this.notificationsLocker.lock();
        SpringAnimation r12 = new SpringAnimation(new FloatValueHolder(0.0f));
        this.replaceAnimation = r12;
        r12.setSpring(new SpringForce(1000.0f).setStiffness(400.0f).setDampingRatio(1.0f));
        setReplaceProgress(r7, r0, 0.0f);
        this.replaceAnimation.addUpdateListener(new RightSlidingDialogContainer$$ExternalSyntheticLambda5(this));
        this.replaceAnimation.addEndListener(new RightSlidingDialogContainer$$ExternalSyntheticLambda6(this, r0, r7));
        this.replaceAnimation.start();
    }

    private void destroyCurrentFragment() {
        BaseFragment r0 = this.currentFragment;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.onPause();
        this.currentFragment.onFragmentDestroy();
        this.currentFragment.setPreviewDelegate(null);
        ActionBar r02 = this.currentActionBarView;
        if (r02 == null) goto L8;
        r02.listenToBackgroundUpdate(null);
    L8:
        removeAllViews();
        this.currentFragment = null;
        this.currentFragmentView = null;
        this.currentFragmentFullscreenView = null;
        this.currentActionBarView = null;
        fragmentDialogId = 0;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
    }

    public static int getRightPaddingSize() {
        if (SharedConfig.useThreeLinesLayout == false) goto L6;
        return 74;
    L6:
        return 76;
    }

    private /* synthetic */ void lambda$animateReplace$2(DynamicAnimation r1, float r2, float r3) {
        this.replaceProgress = r2 / 1000.0f;
        invalidate();
    }

    private /* synthetic */ void lambda$animateReplace$3(BaseFragment r1, BaseFragment r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        if (this.replaceAnimation != null) goto L5;
        return;
    L5:
        this.replaceAnimation = null;
        r1.onTransitionAnimationEnd(true, false);
        setReplaceProgress(r2, r1, 1.0f);
        this.replaceAnimationInProgress = false;
        this.replacingFragment = null;
        r2.onPause();
        r2.onFragmentDestroy();
        removeView(r2.getFragmentView());
        removeView(r2.getActionBar());
        this.notificationsLocker.unlock();
    }

    private /* synthetic */ void lambda$finishPreviewInernal$4(ValueAnimator r1) {
        this.openedProgress = ((Float) r1.getAnimatedValue()).floatValue();
        updateOpenAnimationProgress();
    }

    private /* synthetic */ void lambda$onTouchEvent$5(ValueAnimator r1) {
        this.openedProgress = ((Float) r1.getAnimatedValue()).floatValue();
        updateOpenAnimationProgress();
    }

    private /* synthetic */ void lambda$presentFragment$0(ValueAnimator r1) {
        this.openedProgress = ((Float) r1.getAnimatedValue()).floatValue();
        updateOpenAnimationProgress();
    }

    private /* synthetic */ void lambda$presentFragment$1() {
        finishPreview();
    }

    private void prepareForMoving(MotionEvent r3) {
        this.maybeStartTracking = false;
        this.startedTracking = true;
        this.startedTrackingX = (int) r3.getX();
        openAnimationStarted(false);
    }

    private void setReplaceProgress(BaseFragment r5, BaseFragment r6, float r7) {
        if (r5 != null) goto L5;
        if (r6 != null) goto L5;
        return;
    L5:
        if (r5 == null) goto L7;
        int r4 = r5.getFragmentView().getMeasuredWidth();
    L9:
        if (r5 != null) goto L11;
    L14:
        if (r6 != null) goto L16;
        return;
    L16:
        if (r6.getFragmentView() == null) goto L18;
        r6.getFragmentView().setAlpha(1.0f);
        r6.getFragmentView().setTranslationX(r4 * (1.0f - r7));
    L18:
        r6.setPreviewReplaceProgress(r7);
        return;
    L11:
        if (r5.getFragmentView() == null) goto L13;
        r5.getFragmentView().setAlpha(1.0f - r7);
        r5.getFragmentView().setTranslationX((r4 * 0.6f) * r7);
    L13:
        r5.setPreviewOpenedProgress(1.0f - r7);
        goto L14
    L7:
        r4 = r6.getFragmentView().getMeasuredWidth();
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r10) {
        if (this.replaceAnimationInProgress == false) goto L5;
        setReplaceProgress(this.replacingFragment, this.currentFragment, this.replaceProgress);
        invalidate();
    L5:
        super.dispatchDraw(r10);
        float r0 = this.openedProgress;
        ActionBar r1 = this.currentActionBarView;
        if (r1 != null) goto L8;
    L11:
        float r12 = 0.0f;
    L12:
        ActionBar r3 = this.currentActionBarView;
        if (r3 != null) goto L15;
        float r32 = 0.0f;
    L16:
        float r7 = r0 * Math.max(r12, r32);
        if (this.currentFragment != null) goto L19;
        return;
    L19:
        if (this.currentActionBarView != null) goto L21;
        return;
    L21:
        if (r7 > 0.0f) goto L23;
        return;
    L23:
        if (this.actionModePaint != null) goto L25;
        this.actionModePaint = new Paint();
    L25:
        this.actionModePaint.setColor(Theme.getColor(Theme.key_actionBarActionModeDefault));
        if (r7 != 1.0f) goto L28;
        r10.save();
    L29:
        r10.drawRect(0.0f, 0.0f, getMeasuredWidth(), this.currentTop, this.actionModePaint);
        r10.translate(this.currentActionBarView.getX(), this.currentActionBarView.getY());
        r10.save();
        r10.translate(this.currentActionBarView.getBackButton().getX(), this.currentActionBarView.getBackButton().getY());
        this.currentActionBarView.getBackButton().draw(r10);
        r10.restore();
        if (this.currentActionBarView.getActionMode() == null) goto L35;
        float r13 = this.openedProgress * this.currentActionBarView.getActionMode().getAlpha();
        ActionBar r2 = this.currentActionBarView;
        if (r7 == r13) goto L34;
        r2.draw(r10);
        r10.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), this.currentTop, (int) (this.currentActionBarView.getActionMode().getAlpha() * 255.0f), 31);
        this.currentActionBarView.getActionMode().draw(r10);
        r10.restore();
    L36:
        r10.restore();
        invalidate();
        return;
    L34:
        r2.getActionMode().draw(r10);
        goto L36
    L35:
        this.currentActionBarView.draw(r10);
        goto L36
    L28:
        r10.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), this.currentTop, (int) (r7 * 255.0f), 31);
        goto L29
    L15:
        r32 = r3.searchFieldVisibleAlpha;
        goto L16
    L8:
        if (r1.getActionMode() == null) goto L11;
        r12 = this.currentActionBarView.getActionMode().getAlpha();
        goto L12
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r3, View r4, long r5) {
        ActionBar r0 = this.currentActionBarView;
        if (r4 != r0) goto L11;
        if (r0.getActionMode() == null) goto L11;
        if (this.currentActionBarView.getActionMode().getAlpha() != 1.0f) goto L11;
        return true;
    L11:
        return super.drawChild(r3, r4, r5);
    }

    public void finishPreview() {
        if (this.isOpenned == true) goto L5;
        return;
    L5:
        openAnimationStarted(false);
        finishPreviewInernal();
    }

    public void finishPreviewInernal() {
        this.isOpenned = false;
        if (SharedConfig.animationsEnabled() == true) goto L6;
        this.openedProgress = 0.0f;
        updateOpenAnimationProgress();
        destroyCurrentFragment();
        openAnimationFinished(false);
        return;
    L6:
        this.notificationsLocker.lock();
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{this.openedProgress, 0.0f});
        this.openAnimator = r0;
        r0.addUpdateListener(new RightSlidingDialogContainer$$ExternalSyntheticLambda0(this));
        this.openAnimator.addListener(new AnonymousClass2(this));
        this.openAnimator.setDuration(250);
        this.openAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.openAnimator.start();
    }

    public long getCurrentFragmetDialogId() {
        return fragmentDialogId;
    }

    public BaseFragment getFragment() {
        return this.currentFragment;
    }

    public View getFragmentView() {
        return this.currentFragmentView;
    }

    public abstract boolean getOccupyStatusbar();

    public boolean hasFragment() {
        if (this.currentFragment == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void onDestroy() {
        ValueAnimator r0 = this.openAnimator;
        if (r0 == null) goto L5;
        this.openAnimator = null;
        r0.cancel();
    L5:
        SpringAnimation r02 = this.replaceAnimation;
        if (r02 == null) goto L8;
        this.replaceAnimation = null;
        r02.cancel();
    L8:
        BaseFragment r03 = this.replacingFragment;
        if (r03 == null) goto L11;
        r03.onPause();
        this.replacingFragment.onFragmentDestroy();
        this.replacingFragment = null;
    L11:
        this.replaceAnimationInProgress = false;
        this.isOpenned = false;
        destroyCurrentFragment();
        this.notificationsLocker.unlock();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return onTouchEvent(r1);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        if (getOccupyStatusbar() == false) goto L5;
        int r0 = AndroidUtilities.statusBarHeight;
    L6:
        View r1 = this.currentFragmentView;
        if (r1 == null) goto L9;
        FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) r1.getLayoutParams();
        r12.leftMargin = AndroidUtilities.dp(getRightPaddingSize());
        r12.topMargin = (ActionBar.getCurrentActionBarHeight() + r0) + this.fragmentViewPadding;
    L9:
        ActionBar r13 = this.currentActionBarView;
        if (r13 == null) goto L12;
        ((FrameLayout.LayoutParams) r13.getLayoutParams()).topMargin = r0;
    L12:
        super.onMeasure(r5, r6);
        int r52 = (getMeasuredHeight() + getMeasuredWidth()) << 16;
        if (this.lastSize == r52) goto L16;
        this.lastSize = r52;
        updateOpenAnimationProgress();
        return;
    L16:
        return;
    L5:
        r0 = 0;
        goto L6
    }

    public void onPause() {
        this.isPaused = true;
        BaseFragment r1 = this.currentFragment;
        if (r1 == null) goto L6;
        r1.onPause();
        return;
    }

    public void onResume() {
        this.isPaused = false;
        BaseFragment r1 = this.currentFragment;
        if (r1 == null) goto L6;
        r1.onResume();
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r10) {
        INavigationLayout r0 = this.navigationLayout;
        if (r0 == null) goto L8;
        if (r0.isInPreviewMode() == false) goto L8;
        return false;
    L8:
        if (hasFragment() == true) goto L10;
    L74:
        return false;
    L10:
        if (this.enabled == false) goto L74;
        if (r10 == null) goto L19;
        if (r10.getAction() != 0) goto L19;
        this.startedTrackingPointerId = r10.getPointerId(0);
        this.maybeStartTracking = true;
        this.startedTrackingX = (int) r10.getX();
        this.startedTrackingY = (int) r10.getY();
        VelocityTracker r102 = this.velocityTracker;
        if (r102 == null) goto L73;
        r102.clear();
    L73:
        return this.startedTracking;
    L19:
        if (r10 == null) goto L43;
        if (r10.getAction() != 2) goto L43;
        if (r10.getPointerId(0) != this.startedTrackingPointerId) goto L43;
        if (this.velocityTracker != null) goto L27;
        this.velocityTracker = VelocityTracker.obtain();
    L27:
        int r2 = Math.max(0, (int) (r10.getX() - this.startedTrackingX));
        int r5 = Math.abs(((int) r10.getY()) - this.startedTrackingY);
        this.velocityTracker.addMovement(r10);
        if (this.maybeStartTracking == false) goto L40;
        if (this.startedTracking == true) goto L40;
        if (r2 < AndroidUtilities.getPixelsInCM(0.15f, true)) goto L40;
        if ((Math.abs(r2) / 3) <= r5) goto L40;
        if (ActionBarLayout.findScrollingChild(this, r10.getX(), r10.getY()) != null) goto L38;
        prepareForMoving(r10);
        goto L73
    L38:
        this.maybeStartTracking = false;
    L40:
        if (this.startedTracking == false) goto L73;
        float r103 = r2;
        this.swipeBackX = r103;
        this.openedProgress = Utilities.clamp(1.0f - (r103 / getMeasuredWidth()), 1.0f, 0.0f);
        updateOpenAnimationProgress();
    L43:
        if (r10 != null) goto L45;
    L68:
        if (r10 != null) goto L73;
        this.maybeStartTracking = false;
        this.startedTracking = false;
        VelocityTracker r104 = this.velocityTracker;
        if (r104 == null) goto L73;
        r104.recycle();
        this.velocityTracker = null;
        goto L73
    L45:
        if (r10.getPointerId(0) != this.startedTrackingPointerId) goto L68;
        if (r10.getAction() == 3) goto L53;
        if (r10.getAction() == 1) goto L53;
        if (r10.getAction() != 6) goto L68;
    L53:
        if (this.velocityTracker != null) goto L55;
        this.velocityTracker = VelocityTracker.obtain();
    L55:
        this.velocityTracker.computeCurrentVelocity(1000);
        if (this.startedTracking == false) goto L65;
        float r105 = this.swipeBackX;
        float r3 = this.velocityTracker.getXVelocity();
        float r6 = this.velocityTracker.getYVelocity();
        if (r105 < (getMeasuredWidth() / 3.0f)) goto L60;
    L64:
        finishPreviewInernal();
        goto L65
    L60:
        if (r3 >= AppUtils.getSwipeVelocity()) goto L62;
    L63:
        ValueAnimator r106 = ValueAnimator.ofFloat(new float[]{this.openedProgress, 1.0f});
        this.openAnimator = r106;
        r106.addUpdateListener(new RightSlidingDialogContainer$$ExternalSyntheticLambda1(this));
        this.openAnimator.addListener(new AnonymousClass3(this));
        this.openAnimator.setDuration(250);
        this.openAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.openAnimator.start();
        goto L65
    L62:
        if (r3 >= r6) goto L64;
    L65:
        this.maybeStartTracking = false;
        this.startedTracking = false;
        VelocityTracker r107 = this.velocityTracker;
        if (r107 == null) goto L73;
        r107.recycle();
        this.velocityTracker = null;
        goto L73
    }

    public abstract void openAnimationFinished(boolean r1);

    public abstract void openAnimationStarted(boolean r1);

    /* JADX WARN: Multi-variable type inference failed */
    public void presentFragment(INavigationLayout r4, BaseFragment r5) {
        if (this.isPaused == true) goto L32;
        this.navigationLayout = r4;
        if (r5.onFragmentCreate() == false) goto L31;
        r5.setInPreviewMode(true);
        r5.setParentLayout(r4);
        View r42 = r5.performCreateView(getContext());
        r5.onResume();
        this.currentFragmentView = r42;
        addView(r42);
        BaseFragment r43 = this.currentFragment;
        if ((r5 instanceof BaseFragmentWithFullscreen) == false) goto L10;
        View r1 = ((BaseFragmentWithFullscreen) r5).getFullscreenView();
        this.currentFragmentFullscreenView = r1;
        addView(r1);
    L10:
        this.currentFragment = r5;
        fragmentDialogId = 0;
        if ((r5 instanceof TopicsFragment) == false) goto L14;
        fragmentDialogId = -((TopicsFragment) r5).chatId;
    L14:
        if (r5.getActionBar() == null) goto L16;
        ActionBar r12 = r5.getActionBar();
        this.currentActionBarView = r12;
        addView(r12);
        this.currentActionBarView.listenToBackgroundUpdate(new RightSlidingDialogContainer$$ExternalSyntheticLambda2(this));
    L16:
        if (r43 == null) goto L19;
        animateReplace(r43);
    L29:
        r5.setPreviewDelegate(new RightSlidingDialogContainer$$ExternalSyntheticLambda4(this));
        ViewCompat.requestApplyInsets(this);
        return;
    L19:
        if (this.isOpenned == true) goto L29;
        this.isOpenned = true;
        if (SharedConfig.animationsEnabled() == true) goto L24;
        openAnimationStarted(true);
        r5.onTransitionAnimationStart(true, false);
        r5.onTransitionAnimationEnd(true, false);
        this.openedProgress = 1.0f;
        updateOpenAnimationProgress();
        openAnimationFinished(false);
        return;
    L24:
        this.notificationsLocker.lock();
        this.openAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.openedProgress = 0.0f;
        openAnimationStarted(true);
        updateOpenAnimationProgress();
        r5.onTransitionAnimationStart(true, false);
        this.openAnimator.addUpdateListener(new RightSlidingDialogContainer$$ExternalSyntheticLambda3(this));
        this.openAnimator.addListener(new AnonymousClass1(this, r5));
        this.openAnimator.setDuration(250);
        this.openAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        ValueAnimator r0 = this.openAnimator;
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L27;
        long r13 = 50;
    L28:
        r0.setStartDelay(r13);
        this.openAnimator.start();
        goto L29
    L27:
        r13 = 150;
        goto L28
    L31:
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View r2) {
        super.removeView(r2);
        if (r2 != this.currentFragmentView) goto L6;
        finishPreview();
        return;
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View r2) {
        super.removeViewInLayout(r2);
        if (r2 != this.currentFragmentView) goto L6;
        finishPreview();
        return;
    }

    public void setCurrentTop(int r4) {
        this.currentTop = r4;
        View r0 = this.currentFragmentView;
        if (r0 == null) goto L5;
        r0.setTranslationY((r4 - r0.getTop()) + this.fragmentViewPadding);
    L5:
        View r3 = this.currentFragmentFullscreenView;
        if (r3 == null) goto L9;
        r3.setTranslationY(r4 - r3.getTop());
        return;
    }

    public void setFragmentViewPadding(int r1) {
        this.fragmentViewPadding = r1;
    }

    public void setOpenProgress(float r1) {
    }

    public void setTransitionPaddingBottom(int r2) {
        BaseFragment r1 = this.currentFragment;
        if ((r1 instanceof TopicsFragment) == false) goto L6;
        ((TopicsFragment) r1).setTransitionPadding(r2);
        return;
    }

    public void updateOpenAnimationProgress() {
        if (this.replaceAnimationInProgress == false) goto L5;
        return;
    L5:
        if (hasFragment() == false) goto L19;
        setOpenProgress(this.openedProgress);
        View r0 = this.currentFragmentView;
        if (r0 == null) goto L10;
        r0.setTranslationX((getMeasuredWidth() - AndroidUtilities.dp(getRightPaddingSize())) * (1.0f - this.openedProgress));
    L10:
        ActionBar r02 = this.currentActionBarView;
        if (r02 == null) goto L13;
        r02.setTranslationX(AndroidUtilities.dp(48.0f) * (1.0f - this.openedProgress));
    L13:
        BaseFragment r03 = this.currentFragment;
        if (r03 == null) goto L16;
        r03.setPreviewOpenedProgress(this.openedProgress);
    L16:
        invalidate();
        return;
    }
}
