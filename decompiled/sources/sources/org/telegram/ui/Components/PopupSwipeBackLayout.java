package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.core.view.GestureDetectorCompat;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.ActionBarMenuSlider;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class PopupSwipeBackLayout extends FrameLayout {
    private int currentForegroundIndex;
    private GestureDetectorCompat detector;
    private ValueAnimator foregroundAnimator;
    private int foregroundColor;
    private Drawable foregroundDrawable;
    private Paint foregroundPaint;
    private Rect hitRect;
    private boolean isAnimationInProgress;
    private boolean isProcessingSwipe;
    private boolean isSwipeBackDisallowed;
    private boolean isSwipeDisallowed;
    private int lastHeightReported;
    float lastToProgress;
    float lastTransitionProgress;
    private Path mPath;
    private RectF mRect;
    private AnimationNotificationsLocker notificationsLocker;
    private Runnable onForegroundOpen;
    private IntCallback onHeightUpdateListener;
    private ArrayList<OnSwipeBackProgressListener> onSwipeBackProgressListeners;
    private Paint overlayPaint;
    private float overrideForegroundHeight;
    SparseIntArray overrideHeightIndex;
    Theme.ResourcesProvider resourcesProvider;
    public boolean stickToCenterHorizontal;
    public boolean stickToRight;
    private float toProgress;
    public float transitionProgress;

    /* renamed from: org.telegram.ui.Components.PopupSwipeBackLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ PopupSwipeBackLayout this$0;
        final /* synthetic */ int val$touchSlop;

        public AnonymousClass1(PopupSwipeBackLayout r1, int r2) {
            this.this$0 = r1;
            this.val$touchSlop = r2;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r1) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            if (PopupSwipeBackLayout.m11906$$Nest$fgetisAnimationInProgress(this.this$0) == false) goto L5;
        L10:
            return false;
        L5:
            if (PopupSwipeBackLayout.m11909$$Nest$fgetisSwipeDisallowed(this.this$0) == true) goto L10;
            if (r3 < 600.0f) goto L10;
            PopupSwipeBackLayout.m11919$$Nest$mclearFlags(this.this$0);
            PopupSwipeBackLayout.m11918$$Nest$manimateToState(this.this$0, 0.0f, r3 / 6000.0f);
            goto L10
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r12, MotionEvent r13, float r14, float r15) {
            if (PopupSwipeBackLayout.m11907$$Nest$fgetisProcessingSwipe(this.this$0) == true) goto L27;
            if (PopupSwipeBackLayout.m11909$$Nest$fgetisSwipeDisallowed(this.this$0) == true) goto L27;
            if (PopupSwipeBackLayout.m11908$$Nest$fgetisSwipeBackDisallowed(this.this$0) == false) goto L9;
        L25:
            PopupSwipeBackLayout.m11916$$Nest$fputisSwipeDisallowed(this.this$0, true);
            goto L27
        L9:
            if (this.this$0.transitionProgress != 1.0f) goto L25;
            if (r14 > (-this.val$touchSlop)) goto L25;
            if (Math.abs(r14) < Math.abs(r15 * 1.5f)) goto L25;
            PopupSwipeBackLayout r142 = this.this$0;
            int r0 = 0;
            if (r142.transitionProgress <= 0.5f) goto L17;
            int r152 = 1;
        L19:
            if (PopupSwipeBackLayout.m11920$$Nest$misDisallowedView(r142, r13, r142.getChildAt(r152)) == true) goto L25;
            PopupSwipeBackLayout.m11915$$Nest$fputisProcessingSwipe(this.this$0, true);
            MotionEvent r143 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
        L22:
            if (r0 >= this.this$0.getChildCount()) goto L24;
            this.this$0.getChildAt(r0).dispatchTouchEvent(r143);
            r0 = r0 + 1;
            goto L22
        L24:
            r143.recycle();
            goto L27
        L17:
            r152 = 0;
        L27:
            if (PopupSwipeBackLayout.m11907$$Nest$fgetisProcessingSwipe(this.this$0) == false) goto L30;
            PopupSwipeBackLayout.m11917$$Nest$fputtoProgress(this.this$0, -1.0f);
            this.this$0.transitionProgress = 1.0f - Math.max(0.0f, Math.min(1.0f, (r13.getX() - r12.getX()) / this.this$0.getWidth()));
            this.this$0.invalidateTransforms();
        L30:
            return PopupSwipeBackLayout.m11907$$Nest$fgetisProcessingSwipe(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.PopupSwipeBackLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ PopupSwipeBackLayout this$0;
        final /* synthetic */ float val$f;

        public AnonymousClass2(PopupSwipeBackLayout r1, float r2) {
            this.this$0 = r1;
            this.val$f = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            PopupSwipeBackLayout.m11910$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            PopupSwipeBackLayout r32 = this.this$0;
            float r0 = this.val$f;
            r32.transitionProgress = r0;
            if (r0 > 0.0f) goto L5;
            PopupSwipeBackLayout.m11912$$Nest$fputcurrentForegroundIndex(r32, -1);
        L5:
            this.this$0.invalidateTransforms();
            PopupSwipeBackLayout.m11914$$Nest$fputisAnimationInProgress(this.this$0, false);
            if (PopupSwipeBackLayout.m11911$$Nest$fgetonForegroundOpen(this.this$0) != null) goto L8;
            return;
        L8:
            if (Math.abs(this.val$f - 1.0f) >= 0.01f) goto L12;
            PopupSwipeBackLayout.m11911$$Nest$fgetonForegroundOpen(this.this$0).run();
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            PopupSwipeBackLayout.m11914$$Nest$fputisAnimationInProgress(this.this$0, true);
            PopupSwipeBackLayout.m11917$$Nest$fputtoProgress(this.this$0, this.val$f);
        }
    }

    /* renamed from: org.telegram.ui.Components.PopupSwipeBackLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ PopupSwipeBackLayout this$0;

        public AnonymousClass3(PopupSwipeBackLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PopupSwipeBackLayout.m11914$$Nest$fputisAnimationInProgress(this.this$0, false);
            PopupSwipeBackLayout.m11913$$Nest$fputforegroundAnimator(this.this$0, null);
        }
    }

    public interface IntCallback {
        void run(int r1);
    }

    public interface OnSwipeBackProgressListener {
        void onSwipeBackProgress(PopupSwipeBackLayout r1, float r2, float r3);
    }

    /* renamed from: $r8$lambda$Zj7N9RYC-le1IC9-M-ylYXTI0mw, reason: not valid java name */
    public static /* synthetic */ void m11904$r8$lambda$Zj7N9RYCle1IC9MylYXTI0mw(PopupSwipeBackLayout r0, ValueAnimator r1) {
        r0.lambda$setNewForegroundHeight$1(r1);
    }

    /* renamed from: $r8$lambda$sJbbM1hRNrRE-GfghyIV90a2kcM, reason: not valid java name */
    public static /* synthetic */ void m11905$r8$lambda$sJbbM1hRNrREGfghyIV90a2kcM(PopupSwipeBackLayout r0, ValueAnimator r1) {
        r0.lambda$animateToState$0(r1);
    }

    /* renamed from: -$$Nest$fgetisAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11906$$Nest$fgetisAnimationInProgress(PopupSwipeBackLayout r0) {
        return r0.isAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgetisProcessingSwipe, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11907$$Nest$fgetisProcessingSwipe(PopupSwipeBackLayout r0) {
        return r0.isProcessingSwipe;
    }

    /* renamed from: -$$Nest$fgetisSwipeBackDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11908$$Nest$fgetisSwipeBackDisallowed(PopupSwipeBackLayout r0) {
        return r0.isSwipeBackDisallowed;
    }

    /* renamed from: -$$Nest$fgetisSwipeDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11909$$Nest$fgetisSwipeDisallowed(PopupSwipeBackLayout r0) {
        return r0.isSwipeDisallowed;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m11910$$Nest$fgetnotificationsLocker(PopupSwipeBackLayout r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetonForegroundOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11911$$Nest$fgetonForegroundOpen(PopupSwipeBackLayout r0) {
        return r0.onForegroundOpen;
    }

    /* renamed from: -$$Nest$fputcurrentForegroundIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11912$$Nest$fputcurrentForegroundIndex(PopupSwipeBackLayout r0, int r1) {
        r0.currentForegroundIndex = r1;
    }

    /* renamed from: -$$Nest$fputforegroundAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11913$$Nest$fputforegroundAnimator(PopupSwipeBackLayout r0, ValueAnimator r1) {
        r0.foregroundAnimator = r1;
    }

    /* renamed from: -$$Nest$fputisAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11914$$Nest$fputisAnimationInProgress(PopupSwipeBackLayout r0, boolean r1) {
        r0.isAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputisProcessingSwipe, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11915$$Nest$fputisProcessingSwipe(PopupSwipeBackLayout r0, boolean r1) {
        r0.isProcessingSwipe = r1;
    }

    /* renamed from: -$$Nest$fputisSwipeDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11916$$Nest$fputisSwipeDisallowed(PopupSwipeBackLayout r0, boolean r1) {
        r0.isSwipeDisallowed = r1;
    }

    /* renamed from: -$$Nest$fputtoProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11917$$Nest$fputtoProgress(PopupSwipeBackLayout r0, float r1) {
        r0.toProgress = r1;
    }

    /* renamed from: -$$Nest$manimateToState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11918$$Nest$manimateToState(PopupSwipeBackLayout r0, float r1, float r2) {
        r0.animateToState(r1, r2);
    }

    /* renamed from: -$$Nest$mclearFlags, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11919$$Nest$mclearFlags(PopupSwipeBackLayout r0) {
        r0.clearFlags();
    }

    /* renamed from: -$$Nest$misDisallowedView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11920$$Nest$misDisallowedView(PopupSwipeBackLayout r0, MotionEvent r1, View r2) {
        return r0.isDisallowedView(r1, r2);
    }

    public PopupSwipeBackLayout(Context r3, Theme.ResourcesProvider r4) {
        super(r3);
        this.overrideHeightIndex = new SparseIntArray();
        this.toProgress = -1.0f;
        this.overlayPaint = new Paint(1);
        this.foregroundPaint = new Paint();
        this.foregroundColor = 0;
        this.mPath = new Path();
        this.mRect = new RectF();
        this.onSwipeBackProgressListeners = new ArrayList();
        this.currentForegroundIndex = -1;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.lastHeightReported = -1;
        this.hitRect = new Rect();
        this.resourcesProvider = r4;
        this.detector = new GestureDetectorCompat(r3, new AnonymousClass1(this, ViewConfiguration.get(r3).getScaledTouchSlop()));
        this.overlayPaint.setColor(-16777216);
    }

    private void animateToState(float r4, float r5) {
        ValueAnimator r52 = ValueAnimator.ofFloat(new float[]{this.transitionProgress, r4}).setDuration((long) (Math.max(0.5f, Math.abs(this.transitionProgress - r4) - Math.min(0.2f, r5)) * 300.0f));
        r52.setInterpolator(CubicBezierInterpolator.DEFAULT);
        int r0 = UserConfig.selectedAccount;
        this.notificationsLocker.lock();
        r52.addUpdateListener(new PopupSwipeBackLayout$$ExternalSyntheticLambda0(this));
        r52.addListener(new AnonymousClass2(this, r4));
        r52.start();
    }

    private void clearFlags() {
        this.isProcessingSwipe = false;
        this.isSwipeDisallowed = false;
    }

    private void invalidateVisibility() {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L31;
        View r2 = getChildAt(r1);
        if (r1 != 0) goto L18;
        if (this.transitionProgress != 1.0f) goto L13;
        if (r2.getVisibility() == 4) goto L13;
        r2.setVisibility(4);
    L13:
        if (this.transitionProgress == 1.0f) goto L30;
        if (r2.getVisibility() == 0) goto L30;
        r2.setVisibility(0);
    L30:
        r1 = r1 + 1;
        goto L4
    L18:
        if (r1 == this.currentForegroundIndex) goto L20;
        r2.setVisibility(4);
        goto L30
    L20:
        if (this.transitionProgress != 0.0f) goto L25;
        if (r2.getVisibility() == 4) goto L25;
        r2.setVisibility(4);
    L25:
        if (this.transitionProgress == 0.0f) goto L30;
        if (r2.getVisibility() == 0) goto L30;
        r2.setVisibility(0);
        goto L30
    }

    private boolean isDisallowedView(MotionEvent r5, View r6) {
        r6.getHitRect(this.hitRect);
        if (this.hitRect.contains((int) r5.getX(), (int) r5.getY()) == false) goto L10;
        if (r6.canScrollHorizontally(-1) == false) goto L7;
    L8:
        return true;
    L7:
        if ((r6 instanceof ActionBarMenuSlider) == true) goto L8;
    L10:
        if ((r6 instanceof ViewGroup) == false) goto L18;
        ViewGroup r62 = (ViewGroup) r6;
        int r0 = 0;
    L13:
        if (r0 >= r62.getChildCount()) goto L18;
        if (isDisallowedView(r5, r62.getChildAt(r0)) == true) goto L16;
        r0 = r0 + 1;
        goto L13
    L16:
        return true;
    L18:
        return false;
    }

    private /* synthetic */ void lambda$animateToState$0(ValueAnimator r1) {
        this.transitionProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidateTransforms();
    }

    private /* synthetic */ void lambda$setNewForegroundHeight$1(ValueAnimator r1) {
        this.overrideForegroundHeight = ((Float) r1.getAnimatedValue()).floatValue();
        invalidateTransforms();
    }

    private boolean processTouchEvent(MotionEvent r4) {
        int r0 = r4.getAction() & 255;
        if (this.isAnimationInProgress == false) goto L6;
        return true;
    L6:
        if (this.detector.onTouchEvent(r4) == true) goto L24;
        if (r0 == 1) goto L12;
        if (r0 != 3) goto L24;
    L12:
        if (this.isProcessingSwipe == false) goto L19;
        clearFlags();
        if (this.transitionProgress < 0.5f) goto L16;
        float r42 = 1.0f;
    L17:
        animateToState(r42, 0.0f);
        return false;
    L16:
        r42 = 0.0f;
        goto L17
    L19:
        if (this.isSwipeDisallowed == false) goto L26;
        clearFlags();
        return false;
    L26:
        return false;
    L24:
        return this.isProcessingSwipe;
    }

    public void addOnSwipeBackProgressListener(OnSwipeBackProgressListener r1) {
        this.onSwipeBackProgressListeners.add(r1);
    }

    @Override // android.view.ViewGroup
    public void addView(View r1, int r2, ViewGroup.LayoutParams r3) {
        super.addView(r1, r2, r3);
        invalidateTransforms();
    }

    public void closeForeground() {
        closeForeground(true);
    }

    public void closeForeground(boolean r2) {
        if (this.isAnimationInProgress == false) goto L6;
        return;
    L6:
        if (r2 == true) goto L9;
        this.currentForegroundIndex = -1;
        this.transitionProgress = 0.0f;
        invalidateTransforms();
        return;
    L9:
        animateToState(0.0f, 0.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r11) {
        if (getChildCount() == 0) goto L39;
        View r0 = getChildAt(0);
        float r1 = r0.getTop();
        float r2 = r0.getMeasuredWidth();
        float r3 = r0.getMeasuredHeight();
        int r4 = this.currentForegroundIndex;
        if (r4 != (-1)) goto L8;
    L24:
        int r02 = r11.save();
        this.mPath.rewind();
        int r42 = AndroidUtilities.dp(12.0f);
        if (this.stickToRight == false) goto L28;
        this.mRect.set(getWidth() - r2, r1, getWidth(), r3 + r1);
    L31:
        float r32 = r42;
        this.mPath.addRoundRect(this.mRect, r32, r32, Path.Direction.CW);
        r11.clipPath(this.mPath);
        super.dispatchDraw(r11);
        r11.restoreToCount(r02);
        if (this.onHeightUpdateListener != null) goto L34;
        return;
    L34:
        if (this.lastHeightReported == this.mRect.height()) goto L38;
        IntCallback r112 = this.onHeightUpdateListener;
        int r03 = (int) this.mRect.height();
        this.lastHeightReported = r03;
        r112.run(r03);
        return;
    L38:
        return;
    L28:
        if (this.stickToCenterHorizontal == false) goto L30;
        float r5 = (getWidth() - r2) / 2.0f;
        this.mRect.set(r5, r1, r2 + r5, r3 + r1);
        goto L31
    L30:
        this.mRect.set(0.0f, r1, r2, r3 + r1);
        goto L31
    L8:
        if (r4 >= getChildCount()) goto L24;
        View r43 = getChildAt(this.currentForegroundIndex);
        float r52 = r43.getTop();
        float r7 = r43.getMeasuredWidth();
        float r8 = this.overrideForegroundHeight;
        if (r8 != 0.0f) goto L15;
        r8 = r43.getMeasuredHeight();
    L15:
        if (r0.getMeasuredWidth() == 0) goto L24;
        if (r0.getMeasuredHeight() == 0) goto L24;
        if (r43.getMeasuredWidth() == 0) goto L24;
        if (r43.getMeasuredHeight() == 0) goto L24;
        r1 = AndroidUtilities.lerp(r1, r52, this.transitionProgress);
        r2 = AndroidUtilities.lerp(r2, r7, this.transitionProgress);
        r3 = AndroidUtilities.lerp(r3, r8, this.transitionProgress);
        goto L24
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r8) {
        if (processTouchEvent(r8) == false) goto L5;
        return true;
    L5:
        int r0 = r8.getActionMasked();
        RectF r2 = this.mRect;
        if (r2 == null) goto L8;
        r2.contains(r8.getX(), r8.getY());
    L8:
        if (r0 == 0) goto L10;
    L13:
        int r22 = this.currentForegroundIndex;
        if (r22 < 0) goto L32;
        if (r22 >= getChildCount()) goto L32;
        View r3 = getChildAt(0);
        View r4 = getChildAt(this.currentForegroundIndex);
        if (this.transitionProgress <= 0.5f) goto L21;
        r3 = r4;
    L21:
        boolean r32 = r3.dispatchTouchEvent(r8);
        if (r32 == true) goto L25;
        if (r0 != 0) goto L25;
        return true;
    L25:
        if (r32 == false) goto L27;
    L30:
        return true;
    L27:
        if (onTouchEvent(r8) == true) goto L30;
        return false;
    L32:
        return super.dispatchTouchEvent(r8);
    L10:
        if (this.mRect.contains(r8.getX(), r8.getY()) == true) goto L13;
        callOnClick();
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r10, View r11, long r12) {
        int r7 = indexOfChild(r11);
        int r8 = r10.save();
        if (r7 == 0) goto L12;
        Drawable r2 = this.foregroundDrawable;
        if (r2 == null) goto L7;
        r2.setBounds((int) r11.getX(), 0, (int) (r11.getX() + r11.getMeasuredWidth()), getMeasuredHeight());
        this.foregroundDrawable.draw(r10);
        goto L12
    L7:
        int r22 = this.foregroundColor;
        Paint r3 = this.foregroundPaint;
        if (r22 != 0) goto L10;
        r3.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, this.resourcesProvider));
    L11:
        r10.drawRect(r11.getX(), 0.0f, r11.getMeasuredWidth() + r11.getX(), getMeasuredHeight(), this.foregroundPaint);
        goto L12
    L10:
        r3.setColor(r22);
    L12:
        boolean r6 = super.drawChild(r10, r11, r12);
        if (r7 != 0) goto L15;
        this.overlayPaint.setAlpha((int) (this.transitionProgress * 64.0f));
        r10.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.overlayPaint);
    L15:
        r10.restoreToCount(r8);
        return r6;
    }

    public void invalidateTransforms() {
        invalidateTransforms(true);
    }

    public void invalidateTransforms(boolean r11) {
        int r1 = 0;
        if (this.lastToProgress != this.toProgress) goto L7;
        if (this.lastTransitionProgress != this.transitionProgress) goto L7;
    L13:
        View r0 = getChildAt(0);
        int r2 = this.currentForegroundIndex;
        if (r2 >= 0) goto L16;
    L18:
        View r22 = null;
    L19:
        r0.setTranslationX(((-this.transitionProgress) * getWidth()) * 0.5f);
        float r3 = ((1.0f - this.transitionProgress) * 0.05f) + 0.95f;
        r0.setScaleX(r3);
        r0.setScaleY(r3);
        if (r22 == null) goto L22;
        r22.setTranslationX((1.0f - this.transitionProgress) * getWidth());
    L22:
        invalidateVisibility();
        float r32 = r0.getMeasuredWidth();
        float r5 = r0.getMeasuredHeight();
        if (r22 == null) goto L28;
        float r7 = r22.getMeasuredWidth();
        float r8 = this.overrideForegroundHeight;
        if (r8 != 0.0f) goto L30;
        r8 = r22.getMeasuredHeight();
    L30:
        if (r0.getMeasuredWidth() != 0) goto L32;
        return;
    L32:
        if (r0.getMeasuredHeight() == 0) goto L49;
        ViewParent r02 = getParent();
        if ((r02 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L41;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r03 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r02;
        float r23 = this.transitionProgress;
        float r52 = (r5 + ((r8 - r5) * r23)) + (r03.getPaddingTop() + r03.getPaddingBottom());
        r03.updateAnimation = false;
        r03.setBackScaleX(((r32 + ((r7 - r32) * r23)) + (r03.getPaddingLeft() + r03.getPaddingRight())) / r03.getMeasuredWidth());
        if (r11 == false) goto L39;
        r03.setBackScaleY(Math.min(1.0f, r52 / r03.getMeasuredHeight()));
    L39:
        r03.updateAnimation = true;
    L41:
        if (r1 >= getChildCount()) goto L43;
        View r112 = getChildAt(r1);
        r112.setPivotX(0.0f);
        r112.setPivotY(0.0f);
        r1 = r1 + 1;
        goto L41
    L43:
        invalidate();
        return;
    L49:
        return;
    L28:
        r7 = 0.0f;
        r8 = 0.0f;
        goto L30
    L16:
        if (r2 >= getChildCount()) goto L18;
        r22 = getChildAt(this.currentForegroundIndex);
    L7:
        if (this.onSwipeBackProgressListeners.isEmpty() == true) goto L12;
        int r04 = 0;
    L10:
        if (r04 >= this.onSwipeBackProgressListeners.size()) goto L12;
        this.onSwipeBackProgressListeners.get(r04).onSwipeBackProgress(this, this.toProgress, this.transitionProgress);
        r04 = r04 + 1;
    L12:
        this.lastToProgress = this.toProgress;
        this.lastTransitionProgress = this.transitionProgress;
        goto L13
    }

    public boolean isForegroundOpen() {
        if (this.transitionProgress <= 0.0f) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L22;
        View r1 = getChildAt(r0);
        if ((r1.getLayoutParams() instanceof FrameLayout.LayoutParams) == true) goto L8;
    L10:
        boolean r2 = false;
    L12:
        if (this.stickToRight == false) goto L15;
        int r3 = (r10 - r8) - r1.getMeasuredWidth();
    L18:
        if (r2 == false) goto L20;
        int r22 = r11 - r9;
        r1.layout(r3, r22 - r1.getMeasuredHeight(), r1.getMeasuredWidth() + r3, r22);
    L21:
        r0 = r0 + 1;
        goto L4
    L20:
        r1.layout(r3, 0, r1.getMeasuredWidth() + r3, r1.getMeasuredHeight());
        goto L21
    L15:
        if (this.stickToCenterHorizontal == false) goto L17;
        r3 = ((r10 - r8) - r1.getMeasuredWidth()) / 2;
        goto L18
    L17:
        r3 = 0;
        goto L18
    L8:
        if (((FrameLayout.LayoutParams) r1.getLayoutParams()).gravity != 80) goto L10;
        r2 = true;
        goto L12
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        invalidateTransforms();
    }

    public void openForeground(int r2) {
        if (this.isAnimationInProgress == false) goto L5;
        return;
    L5:
        this.currentForegroundIndex = r2;
        this.overrideForegroundHeight = this.overrideHeightIndex.get(r2);
        animateToState(1.0f, 0.0f);
    }

    public void setForegroundColor(int r1) {
        this.foregroundColor = r1;
    }

    public void setForegroundDrawable(Drawable r1) {
        this.foregroundDrawable = r1;
        invalidate();
    }

    public void setNewForegroundHeight(int r3, int r4, boolean r5) {
        this.overrideHeightIndex.put(r3, r4);
        int r0 = this.currentForegroundIndex;
        if (r3 != r0) goto L22;
        if (r0 >= 0) goto L7;
        return;
    L7:
        if (r0 >= getChildCount()) goto L23;
        ValueAnimator r32 = this.foregroundAnimator;
        if (r32 == null) goto L12;
        r32.cancel();
        this.foregroundAnimator = null;
    L12:
        if (r5 == false) goto L19;
        View r33 = getChildAt(this.currentForegroundIndex);
        float r52 = this.overrideForegroundHeight;
        if (r52 != 0.0f) goto L17;
        r52 = r33.getMeasuredHeight();
    L17:
        ValueAnimator r34 = ValueAnimator.ofFloat(new float[]{r52, r4}).setDuration(240);
        r34.setInterpolator(Easings.easeInOutQuad);
        r34.addUpdateListener(new PopupSwipeBackLayout$$ExternalSyntheticLambda1(this));
        this.isAnimationInProgress = true;
        r34.addListener(new AnonymousClass3(this));
        r34.start();
        this.foregroundAnimator = r34;
        return;
    L19:
        this.overrideForegroundHeight = r4;
        invalidateTransforms();
        return;
    L23:
        return;
    }

    public void setOnForegroundOpenFinished(Runnable r1) {
        this.onForegroundOpen = r1;
    }

    public void setOnHeightUpdateListener(IntCallback r1) {
        this.onHeightUpdateListener = r1;
    }

    public void setStickToCenterHorizontal(boolean r1) {
        this.stickToCenterHorizontal = r1;
        if (r1 == false) goto L6;
        this.stickToRight = false;
        return;
    }

    public void setStickToRight(boolean r1) {
        this.stickToRight = r1;
        if (r1 == false) goto L6;
        this.stickToCenterHorizontal = false;
        return;
    }

    public void setSwipeBackDisallowed(boolean r1) {
        this.isSwipeBackDisallowed = r1;
    }
}
