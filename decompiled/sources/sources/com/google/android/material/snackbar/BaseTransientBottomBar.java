package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline3;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    public static final int ANIMATION_MODE_FADE = 1;
    public static final int ANIMATION_MODE_SLIDE = 0;
    private static final float ANIMATION_SCALE_FROM_VALUE = 0.8f;
    static final int DEFAULT_ANIMATION_FADE_DURATION = 180;
    private static final TimeInterpolator DEFAULT_ANIMATION_FADE_INTERPOLATOR = null;
    private static final int DEFAULT_ANIMATION_FADE_IN_DURATION = 150;
    private static final int DEFAULT_ANIMATION_FADE_OUT_DURATION = 75;
    private static final TimeInterpolator DEFAULT_ANIMATION_SCALE_INTERPOLATOR = null;
    private static final TimeInterpolator DEFAULT_ANIMATION_SLIDE_INTERPOLATOR = null;
    static final int DEFAULT_SLIDE_ANIMATION_DURATION = 250;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    private static final int[] SNACKBAR_STYLE_ATTR = null;
    private static final String TAG = null;
    static final Handler handler = null;
    private final AccessibilityManager accessibilityManager;
    private Anchor anchor;
    private boolean anchorViewLayoutListenerEnabled;
    private final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationFadeOutDuration;
    private final TimeInterpolator animationScaleInterpolator;
    private final int animationSlideDuration;
    private final TimeInterpolator animationSlideInterpolator;
    private int appliedBottomMarginGestureInset;
    private Behavior behavior;
    private final Runnable bottomMarginGestureInsetRunnable;
    private List<BaseCallback<B>> callbacks;
    private final com.google.android.material.snackbar.ContentViewCallback contentViewCallback;
    private final Context context;
    private int duration;
    private int extraBottomMarginAnchorView;
    private int extraBottomMarginGestureInset;
    private int extraBottomMarginWindowInset;
    private int extraLeftMarginWindowInset;
    private int extraRightMarginWindowInset;
    private boolean gestureInsetBottomIgnored;
    SnackbarManager.Callback managerCallback;
    private boolean pendingShowingView;
    private final ViewGroup targetParent;
    protected final SnackbarBaseLayout view;

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$1, reason: invalid class name */
    public class AnonymousClass1 implements Handler.Callback {
        public AnonymousClass1() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message r2) {
            int r1 = r2.what;
            if (r1 == 0) goto L9;
            if (r1 == 1) goto L7;
            return false;
        L7:
            ((BaseTransientBottomBar) r2.obj).hideView(r2.arg1);
            return true;
        L9:
            ((BaseTransientBottomBar) r2.obj).showView();
            return true;
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ BaseTransientBottomBar this$0;
        final /* synthetic */ int val$event;

        public AnonymousClass10(BaseTransientBottomBar r1, int r2) {
            this.this$0 = r1;
            this.val$event = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.onViewHidden(this.val$event);
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$11, reason: invalid class name */
    public class AnonymousClass11 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass11(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            this.this$0.view.setAlpha(((Float) r1.getAnimatedValue()).floatValue());
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$12, reason: invalid class name */
    public class AnonymousClass12 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass12(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.this$0.view.setScaleX(r22);
            this.this$0.view.setScaleY(r22);
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$13, reason: invalid class name */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass13(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.onViewShown();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r3) {
            BaseTransientBottomBar.access$1600(this.this$0).animateContentIn(BaseTransientBottomBar.access$1400(this.this$0) - BaseTransientBottomBar.access$1500(this.this$0), BaseTransientBottomBar.access$1500(this.this$0));
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$14, reason: invalid class name */
    public class AnonymousClass14 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass14(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            this.this$0.view.setTranslationY(((Integer) r1.getAnimatedValue()).intValue());
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$15, reason: invalid class name */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ BaseTransientBottomBar this$0;
        final /* synthetic */ int val$event;

        public AnonymousClass15(BaseTransientBottomBar r1, int r2) {
            this.this$0 = r1;
            this.val$event = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.onViewHidden(this.val$event);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            BaseTransientBottomBar.access$1600(this.this$0).animateContentOut(0, BaseTransientBottomBar.access$1700(this.this$0));
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$16, reason: invalid class name */
    public class AnonymousClass16 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass16(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            this.this$0.view.setTranslationY(((Integer) r1.getAnimatedValue()).intValue());
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass2(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseTransientBottomBar r0 = this.this$0;
            if (r0.view != null) goto L5;
            return;
        L5:
            if (BaseTransientBottomBar.access$000(r0) == null) goto L18;
            int r02 = (WindowUtils.getCurrentWindowBounds(BaseTransientBottomBar.access$000(this.this$0)).height() - BaseTransientBottomBar.access$100(this.this$0)) + ((int) this.this$0.view.getTranslationY());
            int r1 = BaseTransientBottomBar.access$200(this.this$0);
            BaseTransientBottomBar r2 = this.this$0;
            if (r02 < r1) goto L11;
            BaseTransientBottomBar.access$302(r2, BaseTransientBottomBar.access$200(r2));
            return;
        L11:
            ViewGroup.LayoutParams r12 = r2.view.getLayoutParams();
            if ((r12 instanceof ViewGroup.MarginLayoutParams) == true) goto L15;
            Log.w(BaseTransientBottomBar.access$400(), "Unable to apply gesture inset because layout params are not MarginLayoutParams");
            return;
        L15:
            BaseTransientBottomBar r22 = this.this$0;
            BaseTransientBottomBar.access$302(r22, BaseTransientBottomBar.access$200(r22));
            ((ViewGroup.MarginLayoutParams) r12).bottomMargin += BaseTransientBottomBar.access$200(this.this$0) - r02;
            this.this$0.view.requestLayout();
            return;
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$3, reason: invalid class name */
    public class AnonymousClass3 implements OnApplyWindowInsetsListener {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass3(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r2, WindowInsetsCompat r3) {
            BaseTransientBottomBar.access$602(this.this$0, r3.getSystemWindowInsetBottom());
            BaseTransientBottomBar.access$702(this.this$0, r3.getSystemWindowInsetLeft());
            BaseTransientBottomBar.access$802(this.this$0, r3.getSystemWindowInsetRight());
            BaseTransientBottomBar.access$900(this.this$0);
            return r3;
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$4, reason: invalid class name */
    public class AnonymousClass4 extends AccessibilityDelegateCompat {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass4(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.addAction(1048576);
            r2.setDismissable(true);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View r2, int r3, Bundle r4) {
            if (r3 != 1048576) goto L7;
            this.this$0.dismiss();
            return true;
        L7:
            return super.performAccessibilityAction(r2, r3, r4);
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$5, reason: invalid class name */
    public class AnonymousClass5 implements SnackbarManager.Callback {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass5(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void dismiss(int r4) {
            Handler r0 = BaseTransientBottomBar.handler;
            r0.sendMessage(r0.obtainMessage(1, r4, 0, this.this$0));
        }

        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void show() {
            Handler r0 = BaseTransientBottomBar.handler;
            r0.sendMessage(r0.obtainMessage(0, this.this$0));
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$6, reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass6(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.onViewHidden(3);
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$7, reason: invalid class name */
    public class AnonymousClass7 implements SwipeDismissBehavior.OnDismissListener {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass7(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
        public void onDismiss(View r2) {
            if (r2.getParent() == null) goto L5;
            r2.setVisibility(8);
        L5:
            this.this$0.dispatchDismiss(0);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
        public void onDragStateChanged(int r2) {
            if (r2 != 0) goto L4;
            SnackbarManager.getInstance().restoreTimeoutIfPaused(this.this$0.managerCallback);
            return;
        L4:
            if (r2 != 1) goto L6;
        L8:
            SnackbarManager.getInstance().pauseTimeout(this.this$0.managerCallback);
            return;
        L6:
            if (r2 == 2) goto L8;
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$8, reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass8(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            SnackbarBaseLayout r0 = this.this$0.view;
            if (r0 != null) goto L6;
            return;
        L6:
            if (r0.getParent() == null) goto L8;
            this.this$0.view.setVisibility(0);
        L8:
            int r02 = this.this$0.view.getAnimationMode();
            BaseTransientBottomBar r2 = this.this$0;
            if (r02 != 1) goto L12;
            BaseTransientBottomBar.access$1200(r2);
            return;
        L12:
            BaseTransientBottomBar.access$1300(r2);
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ BaseTransientBottomBar this$0;

        public AnonymousClass9(BaseTransientBottomBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.onViewShown();
        }
    }

    public static class Anchor implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<View> anchorView;
        private final WeakReference<BaseTransientBottomBar> transientBottomBar;

        private Anchor(BaseTransientBottomBar r2, View r3) {
            this.transientBottomBar = new WeakReference(r2);
            this.anchorView = new WeakReference(r3);
        }

        public static Anchor anchor(BaseTransientBottomBar r1, View r2) {
            Anchor r0 = new Anchor(r1, r2);
            if (r2.isAttachedToWindow() == false) goto L5;
            ViewUtils.addOnGlobalLayoutListener(r2, r0);
        L5:
            r2.addOnAttachStateChangeListener(r0);
            return r0;
        }

        private boolean unanchorIfNoTransientBottomBar() {
            if (this.transientBottomBar.get() != null) goto L6;
            unanchor();
            return true;
        L6:
            return false;
        }

        public View getAnchorView() {
            return this.anchorView.get();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (unanchorIfNoTransientBottomBar() == false) goto L5;
            return;
        L5:
            if (BaseTransientBottomBar.access$2000(this.transientBottomBar.get()) == false) goto L10;
            BaseTransientBottomBar.access$2100(this.transientBottomBar.get());
            return;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r2) {
            if (unanchorIfNoTransientBottomBar() == false) goto L5;
            return;
        L5:
            ViewUtils.addOnGlobalLayoutListener(r2, this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            if (unanchorIfNoTransientBottomBar() == false) goto L5;
            return;
        L5:
            ViewUtils.removeOnGlobalLayoutListener(r2, this);
        }

        public void unanchor() {
            if (this.anchorView.get() == null) goto L5;
            this.anchorView.get().removeOnAttachStateChangeListener(this);
            ViewUtils.removeOnGlobalLayoutListener(this.anchorView.get(), this);
        L5:
            this.anchorView.clear();
            this.transientBottomBar.clear();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationMode {
    }

    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        public BaseCallback() {
        }

        public void onDismissed(B r1, int r2) {
        }

        public void onShown(B r1) {
        }
    }

    public static class Behavior extends SwipeDismissBehavior<View> {
        private final BehaviorDelegate delegate;

        public Behavior() {
            this.delegate = new BehaviorDelegate(this);
        }

        public static /* synthetic */ void access$1100(Behavior r0, BaseTransientBottomBar r1) {
            r0.setBaseTransientBottomBar(r1);
        }

        private void setBaseTransientBottomBar(BaseTransientBottomBar<?> r1) {
            this.delegate.setBaseTransientBottomBar(r1);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View r1) {
            return this.delegate.canSwipeDismissView(r1);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onInterceptTouchEvent(CoordinatorLayout r2, View r3, MotionEvent r4) {
            this.delegate.onInterceptTouchEvent(r2, r3, r4);
            return super.onInterceptTouchEvent(r2, r3, r4);
        }
    }

    public static class BehaviorDelegate {
        private SnackbarManager.Callback managerCallback;

        public BehaviorDelegate(SwipeDismissBehavior<?> r1) {
            r1.setStartAlphaSwipeDistance(0.1f);
            r1.setEndAlphaSwipeDistance(0.6f);
            r1.setSwipeDirection(0);
        }

        public boolean canSwipeDismissView(View r1) {
            return r1 instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(CoordinatorLayout r2, View r3, MotionEvent r4) {
            int r0 = r4.getActionMasked();
            if (r0 == 0) goto L12;
            if (r0 != 1) goto L7;
        L9:
            SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
            return;
        L7:
            if (r0 == 3) goto L9;
            return;
        L12:
            if (r2.isPointInChildBounds(r3, (int) r4.getX(), (int) r4.getY()) == false) goto L15;
            SnackbarManager.getInstance().pauseTimeout(this.managerCallback);
            return;
        }

        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> r1) {
            this.managerCallback = r1.managerCallback;
        }
    }

    @Deprecated
    public interface ContentViewCallback extends com.google.android.material.snackbar.ContentViewCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static class SnackbarBaseLayout extends FrameLayout {
        private static final View.OnTouchListener consumeAllTouchListener = null;
        private final float actionTextColorAlpha;
        private boolean addingToTargetParent;
        private int animationMode;
        private final float backgroundOverlayColorAlpha;
        private ColorStateList backgroundTint;
        private PorterDuff.Mode backgroundTintMode;
        private BaseTransientBottomBar<?> baseTransientBottomBar;
        private final int maxInlineActionWidth;
        private final int maxWidth;
        private Rect originalMargins;
        private final int originalPaddingEnd;
        ShapeAppearanceModel shapeAppearanceModel;

        /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnTouchListener {
            public AnonymousClass1() {
            }

            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View r1, MotionEvent r2) {
                return true;
            }
        }

        static {
            consumeAllTouchListener = new AnonymousClass1();
        }

        public SnackbarBaseLayout(Context r2) {
            this(r2, null);
        }

        public SnackbarBaseLayout(Context r4, AttributeSet r5) {
            super(MaterialThemeOverlay.wrap(r4, r5, 0, 0), r5);
            Context r42 = getContext();
            TypedArray r1 = r42.obtainStyledAttributes(r5, R.styleable.SnackbarLayout);
            if (r1.hasValue(R.styleable.SnackbarLayout_elevation) == false) goto L5;
            setElevation(r1.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
        L5:
            this.animationMode = r1.getInt(R.styleable.SnackbarLayout_animationMode, 0);
            if (r1.hasValue(R.styleable.SnackbarLayout_shapeAppearance) == false) goto L8;
        L9:
            this.shapeAppearanceModel = ShapeAppearanceModel.builder(r42, r5, 0, 0).build();
        L10:
            this.backgroundOverlayColorAlpha = r1.getFloat(R.styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            setBackgroundTintList(MaterialResources.getColorStateList(r42, r1, R.styleable.SnackbarLayout_backgroundTint));
            setBackgroundTintMode(ViewUtils.parseTintMode(r1.getInt(R.styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.actionTextColorAlpha = r1.getFloat(R.styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            this.maxWidth = r1.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
            this.maxInlineActionWidth = r1.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
            r1.recycle();
            this.originalPaddingEnd = getPaddingEnd();
            setOnTouchListener(consumeAllTouchListener);
            setFocusable(true);
            if (getBackground() != null) goto L14;
            setBackground(createThemedBackground());
            return;
        L14:
            return;
        L8:
            if (r1.hasValue(R.styleable.SnackbarLayout_shapeAppearanceOverlay) == false) goto L10;
            goto L9
        }

        public static /* synthetic */ Rect access$1000(SnackbarBaseLayout r0) {
            return r0.originalMargins;
        }

        public static /* synthetic */ void access$500(SnackbarBaseLayout r0, BaseTransientBottomBar r1) {
            r0.setBaseTransientBottomBar(r1);
        }

        private Drawable createThemedBackground() {
            int r0 = MaterialColors.layer(this, R.attr.colorSurface, R.attr.colorOnSurface, getBackgroundOverlayColorAlpha());
            ShapeAppearanceModel r1 = this.shapeAppearanceModel;
            if (r1 == null) goto L5;
            Drawable r02 = BaseTransientBottomBar.access$1800(r0, r1);
        L7:
            if (this.backgroundTint == null) goto L11;
            Drawable r03 = DrawableCompat.wrap(r02);
            r03.setTintList(this.backgroundTint);
            return r03;
        L11:
            return DrawableCompat.wrap(r02);
        L5:
            r02 = BaseTransientBottomBar.access$1900(r0, getResources());
            goto L7
        }

        private void setBaseTransientBottomBar(BaseTransientBottomBar<?> r1) {
            this.baseTransientBottomBar = r1;
        }

        private void updateOriginalMargins(ViewGroup.MarginLayoutParams r5) {
            this.originalMargins = new Rect(r5.leftMargin, r5.topMargin, r5.rightMargin, r5.bottomMargin);
        }

        public void addToTargetParent(ViewGroup r2) {
            this.addingToTargetParent = true;
            r2.addView(this);
            this.addingToTargetParent = false;
        }

        public float getActionTextColorAlpha() {
            return this.actionTextColorAlpha;
        }

        public int getAnimationMode() {
            return this.animationMode;
        }

        public float getBackgroundOverlayColorAlpha() {
            return this.backgroundOverlayColorAlpha;
        }

        public int getMaxInlineActionWidth() {
            return this.maxInlineActionWidth;
        }

        public int getMaxWidth() {
            return this.maxWidth;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseTransientBottomBar<?> r0 = this.baseTransientBottomBar;
            if (r0 == null) goto L5;
            r0.onAttachedToWindow();
        L5:
            requestApplyInsets();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BaseTransientBottomBar<?> r0 = this.baseTransientBottomBar;
            if (r0 == null) goto L6;
            r0.onDetachedFromWindow();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            BaseTransientBottomBar<?> r0 = this.baseTransientBottomBar;
            if (r0 == null) goto L6;
            r0.onLayoutChange();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, r3);
            if (this.maxWidth <= 0) goto L8;
            int r22 = getMeasuredWidth();
            int r0 = this.maxWidth;
            if (r22 <= r0) goto L9;
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), r3);
            return;
        L9:
            return;
        }

        public void removeOrRestorePaddingEnd(boolean r4) {
            int r0 = getPaddingStart();
            int r1 = getPaddingTop();
            if (r4 == false) goto L5;
            int r42 = 0;
        L6:
            setPaddingRelative(r0, r1, r42, getPaddingBottom());
            return;
        L5:
            r42 = this.originalPaddingEnd;
            goto L6
        }

        public void setAnimationMode(int r1) {
            this.animationMode = r1;
        }

        @Override // android.view.View
        public void setBackground(Drawable r1) {
            setBackgroundDrawable(r1);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable r2) {
            if (r2 != null) goto L4;
        L6:
            super.setBackgroundDrawable(r2);
            return;
        L4:
            if (this.backgroundTint == null) goto L6;
            r2 = DrawableCompat.wrap(r2.mutate());
            r2.setTintList(this.backgroundTint);
            r2.setTintMode(this.backgroundTintMode);
            goto L6
        }

        @Override // android.view.View
        public void setBackgroundTintList(ColorStateList r2) {
            this.backgroundTint = r2;
            if (getBackground() == null) goto L8;
            Drawable r0 = DrawableCompat.wrap(getBackground().mutate());
            r0.setTintList(r2);
            r0.setTintMode(this.backgroundTintMode);
            if (r0 == getBackground()) goto L9;
            super.setBackgroundDrawable(r0);
            return;
        L9:
            return;
        }

        @Override // android.view.View
        public void setBackgroundTintMode(PorterDuff.Mode r2) {
            this.backgroundTintMode = r2;
            if (getBackground() == null) goto L8;
            Drawable r0 = DrawableCompat.wrap(getBackground().mutate());
            r0.setTintMode(r2);
            if (r0 == getBackground()) goto L9;
            super.setBackgroundDrawable(r0);
            return;
        L9:
            return;
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams r2) {
            super.setLayoutParams(r2);
            if (this.addingToTargetParent == false) goto L5;
            return;
        L5:
            if ((r2 instanceof ViewGroup.MarginLayoutParams) == false) goto L11;
            updateOriginalMargins((ViewGroup.MarginLayoutParams) r2);
            BaseTransientBottomBar<?> r1 = this.baseTransientBottomBar;
            if (r1 == null) goto L12;
            BaseTransientBottomBar.access$900(r1);
            return;
        L12:
            return;
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener r2) {
            if (r2 == null) goto L4;
            View.OnTouchListener r0 = null;
        L5:
            setOnTouchListener(r0);
            super.setOnClickListener(r2);
            return;
        L4:
            r0 = consumeAllTouchListener;
            goto L5
        }
    }

    static {
        DEFAULT_ANIMATION_SLIDE_INTERPOLATOR = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        DEFAULT_ANIMATION_FADE_INTERPOLATOR = AnimationUtils.LINEAR_INTERPOLATOR;
        DEFAULT_ANIMATION_SCALE_INTERPOLATOR = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
        SNACKBAR_STYLE_ATTR = new int[]{R.attr.snackbarStyle};
        TAG = BaseTransientBottomBar.class.getSimpleName();
        handler = new Handler(Looper.getMainLooper(), new AnonymousClass1());
    }

    public BaseTransientBottomBar(Context r3, ViewGroup r4, View r5, com.google.android.material.snackbar.ContentViewCallback r6) {
        this.anchorViewLayoutListenerEnabled = false;
        this.bottomMarginGestureInsetRunnable = new AnonymousClass2(this);
        this.managerCallback = new AnonymousClass5(this);
        if (r4 == null) goto L15;
        if (r5 == null) goto L13;
        if (r6 == null) goto L11;
        this.targetParent = r4;
        this.contentViewCallback = r6;
        this.context = r3;
        ThemeEnforcement.checkAppCompatTheme(r3);
        SnackbarBaseLayout r42 = (SnackbarBaseLayout) LayoutInflater.from(r3).inflate(getSnackbarBaseLayoutResId(), r4, false);
        this.view = r42;
        SnackbarBaseLayout.access$500(r42, this);
        if ((r5 instanceof SnackbarContentLayout) == false) goto L9;
        SnackbarContentLayout r62 = (SnackbarContentLayout) r5;
        r62.updateActionTextColorAlphaIfNeeded(r42.getActionTextColorAlpha());
        r62.setMaxInlineActionWidth(r42.getMaxInlineActionWidth());
    L9:
        r42.addView(r5);
        r42.setAccessibilityLiveRegion(1);
        r42.setImportantForAccessibility(1);
        r42.setFitsSystemWindows(true);
        ViewCompat.setOnApplyWindowInsetsListener(r42, new AnonymousClass3(this));
        ViewCompat.setAccessibilityDelegate(r42, new AnonymousClass4(this));
        this.accessibilityManager = (AccessibilityManager) r3.getSystemService("accessibility");
        this.animationSlideDuration = MotionUtils.resolveThemeDuration(r3, R.attr.motionDurationLong2, 250);
        this.animationFadeInDuration = MotionUtils.resolveThemeDuration(r3, R.attr.motionDurationLong2, 150);
        this.animationFadeOutDuration = MotionUtils.resolveThemeDuration(r3, R.attr.motionDurationMedium1, 75);
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(r3, R.attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_FADE_INTERPOLATOR);
        this.animationScaleInterpolator = MotionUtils.resolveThemeInterpolator(r3, R.attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_SCALE_INTERPOLATOR);
        this.animationSlideInterpolator = MotionUtils.resolveThemeInterpolator(r3, R.attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_SLIDE_INTERPOLATOR);
        return;
    L11:
        f$$ExternalSyntheticBUOutline1.m("Transient bottom bar must have non-null callback");
        throw null;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Transient bottom bar must have non-null content");
        throw null;
    L15:
        f$$ExternalSyntheticBUOutline1.m("Transient bottom bar must have non-null parent");
        throw null;
    }

    public BaseTransientBottomBar(ViewGroup r2, View r3, com.google.android.material.snackbar.ContentViewCallback r4) {
        this(r2.getContext(), r2, r3, r4);
    }

    public static /* synthetic */ Context access$000(BaseTransientBottomBar r0) {
        return r0.context;
    }

    public static /* synthetic */ int access$100(BaseTransientBottomBar r0) {
        return r0.getViewAbsoluteBottom();
    }

    public static /* synthetic */ void access$1200(BaseTransientBottomBar r0) {
        r0.startFadeInAnimation();
    }

    public static /* synthetic */ void access$1300(BaseTransientBottomBar r0) {
        r0.startSlideInAnimation();
    }

    public static /* synthetic */ int access$1400(BaseTransientBottomBar r0) {
        return r0.animationSlideDuration;
    }

    public static /* synthetic */ int access$1500(BaseTransientBottomBar r0) {
        return r0.animationFadeInDuration;
    }

    public static /* synthetic */ com.google.android.material.snackbar.ContentViewCallback access$1600(BaseTransientBottomBar r0) {
        return r0.contentViewCallback;
    }

    public static /* synthetic */ int access$1700(BaseTransientBottomBar r0) {
        return r0.animationFadeOutDuration;
    }

    public static /* synthetic */ MaterialShapeDrawable access$1800(int r0, ShapeAppearanceModel r1) {
        return createMaterialShapeDrawableBackground(r0, r1);
    }

    public static /* synthetic */ GradientDrawable access$1900(int r0, Resources r1) {
        return createGradientDrawableBackground(r0, r1);
    }

    public static /* synthetic */ int access$200(BaseTransientBottomBar r0) {
        return r0.extraBottomMarginGestureInset;
    }

    public static /* synthetic */ boolean access$2000(BaseTransientBottomBar r0) {
        return r0.anchorViewLayoutListenerEnabled;
    }

    public static /* synthetic */ void access$2100(BaseTransientBottomBar r0) {
        r0.recalculateAndUpdateMargins();
    }

    public static /* synthetic */ int access$302(BaseTransientBottomBar r0, int r1) {
        r0.appliedBottomMarginGestureInset = r1;
        return r1;
    }

    public static /* synthetic */ String access$400() {
        return TAG;
    }

    public static /* synthetic */ int access$602(BaseTransientBottomBar r0, int r1) {
        r0.extraBottomMarginWindowInset = r1;
        return r1;
    }

    public static /* synthetic */ int access$702(BaseTransientBottomBar r0, int r1) {
        r0.extraLeftMarginWindowInset = r1;
        return r1;
    }

    public static /* synthetic */ int access$802(BaseTransientBottomBar r0, int r1) {
        r0.extraRightMarginWindowInset = r1;
        return r1;
    }

    public static /* synthetic */ void access$900(BaseTransientBottomBar r0) {
        r0.updateMargins();
    }

    private void animateViewOut(int r3) {
        if (this.view.getAnimationMode() != 1) goto L6;
        startFadeOutAnimation(r3);
        return;
    L6:
        startSlideOutAnimation(r3);
    }

    private int calculateBottomMarginForAnchorView() {
        if (getAnchorView() != null) goto L6;
        return 0;
    L6:
        int[] r1 = new int[2];
        getAnchorView().getLocationOnScreen(r1);
        int r12 = r1[1];
        int[] r0 = new int[2];
        this.targetParent.getLocationOnScreen(r0);
        return (r0[1] + this.targetParent.getHeight()) - r12;
    }

    private static GradientDrawable createGradientDrawableBackground(int r2, Resources r3) {
        float r32 = r3.getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
        GradientDrawable r0 = new GradientDrawable();
        r0.setShape(0);
        r0.setCornerRadius(r32);
        r0.setColor(r2);
        return r0;
    }

    private static MaterialShapeDrawable createMaterialShapeDrawableBackground(int r1, ShapeAppearanceModel r2) {
        MaterialShapeDrawable r0 = new MaterialShapeDrawable(r2);
        r0.setFillColor(ColorStateList.valueOf(r1));
        return r0;
    }

    private ValueAnimator getAlphaAnimator(float... r2) {
        ValueAnimator r22 = ValueAnimator.ofFloat(r2);
        r22.setInterpolator(this.animationFadeInterpolator);
        r22.addUpdateListener(new AnonymousClass11(this));
        return r22;
    }

    private ValueAnimator getScaleAnimator(float... r2) {
        ValueAnimator r22 = ValueAnimator.ofFloat(r2);
        r22.setInterpolator(this.animationScaleInterpolator);
        r22.addUpdateListener(new AnonymousClass12(this));
        return r22;
    }

    private int getTranslationYBottom() {
        int r0 = this.view.getHeight();
        ViewGroup.LayoutParams r2 = this.view.getLayoutParams();
        if ((r2 instanceof ViewGroup.MarginLayoutParams) == true) goto L5;
        return r0;
    L5:
        return r0 + ((ViewGroup.MarginLayoutParams) r2).bottomMargin;
    }

    private int getViewAbsoluteBottom() {
        int[] r0 = new int[2];
        this.view.getLocationInWindow(r0);
        return r0[1] + this.view.getHeight();
    }

    private boolean isSwipeDismissable() {
        ViewGroup.LayoutParams r1 = this.view.getLayoutParams();
        if ((r1 instanceof CoordinatorLayout.LayoutParams) == true) goto L5;
        return false;
    L5:
        if ((((CoordinatorLayout.LayoutParams) r1).getBehavior() instanceof SwipeDismissBehavior) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private void recalculateAndUpdateMargins() {
        this.extraBottomMarginAnchorView = calculateBottomMarginForAnchorView();
        updateMargins();
    }

    private void setUpBehavior(CoordinatorLayout.LayoutParams r3) {
        SwipeDismissBehavior<? extends View> r0 = this.behavior;
        if (r0 != null) goto L6;
        r0 = getNewBehavior();
    L6:
        if ((r0 instanceof Behavior) == false) goto L8;
        Behavior.access$1100((Behavior) r0, this);
    L8:
        r0.setListener(new AnonymousClass7(this));
        r3.setBehavior(r0);
        if (getAnchorView() != null) goto L12;
        r3.insetEdge = 80;
        return;
    }

    private boolean shouldUpdateGestureInset() {
        if (this.extraBottomMarginGestureInset > 0) goto L5;
        return false;
    L5:
        if (this.gestureInsetBottomIgnored == false) goto L7;
        return false;
    L7:
        if (isSwipeDismissable() == true) goto L9;
        return false;
    L9:
        if (getAnchorView() != null) goto L16;
        return true;
    L16:
        return false;
    }

    private void showViewImpl() {
        if (shouldAnimate() == false) goto L7;
        animateViewIn();
        return;
    L7:
        if (this.view.getParent() == null) goto L9;
        this.view.setVisibility(0);
    L9:
        onViewShown();
    }

    private void startFadeInAnimation() {
        ValueAnimator r1 = getAlphaAnimator(new float[]{0.0f, 1.0f});
        ValueAnimator r2 = getScaleAnimator(new float[]{ANIMATION_SCALE_FROM_VALUE, 1.0f});
        AnimatorSet r3 = new AnimatorSet();
        r3.playTogether(new Animator[]{r1, r2});
        r3.setDuration(this.animationFadeInDuration);
        r3.addListener(new AnonymousClass9(this));
        r3.start();
    }

    private void startFadeOutAnimation(int r4) {
        ValueAnimator r0 = getAlphaAnimator(new float[]{1.0f, 0.0f});
        r0.setDuration(this.animationFadeOutDuration);
        r0.addListener(new AnonymousClass10(this, r4));
        r0.start();
    }

    private void startSlideInAnimation() {
        int r0 = getTranslationYBottom();
        this.view.setTranslationY(r0);
        ValueAnimator r1 = new ValueAnimator();
        r1.setIntValues(new int[]{r0, 0});
        r1.setInterpolator(this.animationSlideInterpolator);
        r1.setDuration(this.animationSlideDuration);
        r1.addListener(new AnonymousClass13(this));
        r1.addUpdateListener(new AnonymousClass14(this));
        r1.start();
    }

    private void startSlideOutAnimation(int r4) {
        ValueAnimator r0 = new ValueAnimator();
        r0.setIntValues(new int[]{0, getTranslationYBottom()});
        r0.setInterpolator(this.animationSlideInterpolator);
        r0.setDuration(this.animationSlideDuration);
        r0.addListener(new AnonymousClass15(this, r4));
        r0.addUpdateListener(new AnonymousClass16(this));
        r0.start();
    }

    private void updateMargins() {
        ViewGroup.LayoutParams r0 = this.view.getLayoutParams();
        if ((r0 instanceof ViewGroup.MarginLayoutParams) == true) goto L7;
        Log.w(TAG, "Unable to update margins because layout params are not MarginLayoutParams");
        return;
    L7:
        if (SnackbarBaseLayout.access$1000(this.view) != null) goto L11;
        Log.w(TAG, "Unable to update margins because original view margins are not set");
        return;
    L11:
        if (this.view.getParent() != null) goto L14;
        return;
    L14:
        if (getAnchorView() == null) goto L16;
        int r1 = this.extraBottomMarginAnchorView;
    L17:
        ViewGroup.MarginLayoutParams r02 = (ViewGroup.MarginLayoutParams) r0;
        int r2 = SnackbarBaseLayout.access$1000(this.view).bottom + r1;
        int r12 = SnackbarBaseLayout.access$1000(this.view).left + this.extraLeftMarginWindowInset;
        int r3 = SnackbarBaseLayout.access$1000(this.view).right + this.extraRightMarginWindowInset;
        int r4 = SnackbarBaseLayout.access$1000(this.view).top;
        if (r02.bottomMargin == r2) goto L20;
    L27:
        boolean r5 = true;
    L28:
        if (r5 == false) goto L30;
        r02.bottomMargin = r2;
        r02.leftMargin = r12;
        r02.rightMargin = r3;
        r02.topMargin = r4;
        this.view.requestLayout();
    L30:
        if (r5 == true) goto L34;
        if (this.appliedBottomMarginGestureInset != this.extraBottomMarginGestureInset) goto L34;
        return;
    L34:
        if (Build.VERSION.SDK_INT >= 29) goto L36;
        return;
    L36:
        if (shouldUpdateGestureInset() == false) goto L41;
        this.view.removeCallbacks(this.bottomMarginGestureInsetRunnable);
        this.view.post(this.bottomMarginGestureInsetRunnable);
        return;
    L41:
        return;
    L20:
        if (r02.leftMargin != r12) goto L27;
        if (r02.rightMargin != r3) goto L27;
        if (r02.topMargin != r4) goto L27;
        r5 = false;
        goto L28
    L16:
        r1 = this.extraBottomMarginWindowInset;
        goto L17
    }

    public B addCallback(BaseCallback<B> r2) {
        if (r2 != null) goto L5;
        return this;
    L5:
        if (this.callbacks != null) goto L7;
        this.callbacks = new ArrayList();
    L7:
        this.callbacks.add(r2);
        return this;
    }

    public void animateViewIn() {
        this.view.post(new AnonymousClass8(this));
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    public void dispatchDismiss(int r2) {
        SnackbarManager.getInstance().dismiss(this.managerCallback, r2);
    }

    public View getAnchorView() {
        Anchor r0 = this.anchor;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getAnchorView();
    }

    public int getAnimationMode() {
        return this.view.getAnimationMode();
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public Context getContext() {
        return this.context;
    }

    public int getDuration() {
        return this.duration;
    }

    public SwipeDismissBehavior<? extends View> getNewBehavior() {
        return new Behavior();
    }

    public int getSnackbarBaseLayoutResId() {
        if (hasSnackbarStyleAttr() == false) goto L7;
        return R.layout.mtrl_layout_snackbar;
    L7:
        return R.layout.design_layout_snackbar;
    }

    public View getView() {
        return this.view;
    }

    public boolean hasSnackbarStyleAttr() {
        TypedArray r3 = this.context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
        int r2 = r3.getResourceId(0, -1);
        r3.recycle();
        if (r2 == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public final void hideView(int r2) {
        if (shouldAnimate() == true) goto L5;
    L8:
        onViewHidden(r2);
        return;
    L5:
        if (this.view.getVisibility() != 0) goto L8;
        animateViewOut(r2);
    }

    public boolean isAnchorViewLayoutListenerEnabled() {
        return this.anchorViewLayoutListenerEnabled;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.gestureInsetBottomIgnored;
    }

    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(this.managerCallback);
    }

    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
    }

    public void onAttachedToWindow() {
        if (Build.VERSION.SDK_INT < 29) goto L8;
        WindowInsets r0 = this.view.getRootWindowInsets();
        if (r0 == null) goto L9;
        this.extraBottomMarginGestureInset = DrawableUtils$$ExternalSyntheticApiModelOutline3.m(r0.getMandatorySystemGestureInsets());
        updateMargins();
        return;
    L9:
        return;
    }

    public void onDetachedFromWindow() {
        if (isShownOrQueued() == false) goto L6;
        handler.post(new AnonymousClass6(this));
        return;
    }

    public void onLayoutChange() {
        if (this.pendingShowingView == false) goto L6;
        showViewImpl();
        this.pendingShowingView = false;
        return;
    }

    public void onViewHidden(int r3) {
        SnackbarManager.getInstance().onDismissed(this.managerCallback);
        List<BaseCallback<B>> r0 = this.callbacks;
        if (r0 == null) goto L7;
        int r02 = r0.size() - 1;
    L5:
        if (r02 < 0) goto L7;
        this.callbacks.get(r02).onDismissed(this, r3);
        r02 = r02 - 1;
    L7:
        ViewParent r32 = this.view.getParent();
        if ((r32 instanceof ViewGroup) == false) goto L12;
        ((ViewGroup) r32).removeView(this.view);
        return;
    }

    public void onViewShown() {
        SnackbarManager.getInstance().onShown(this.managerCallback);
        List<BaseCallback<B>> r0 = this.callbacks;
        if (r0 == null) goto L7;
        int r02 = r0.size() - 1;
    L5:
        if (r02 < 0) goto L9;
        this.callbacks.get(r02).onShown(this);
        r02 = r02 - 1;
        goto L5
    L9:
        return;
    }

    public B removeCallback(BaseCallback<B> r2) {
        if (r2 == null) goto L6;
        List<BaseCallback<B>> r0 = this.callbacks;
        if (r0 == null) goto L6;
        r0.remove(r2);
        return this;
    L6:
        return this;
    }

    public B setAnchorView(int r2) {
        View r0 = this.targetParent.findViewById(r2);
        if (r0 != null) goto L5;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unable to find anchor view with id: ", r2);
        return null;
    L5:
        return (B) setAnchorView(r0);
    }

    public B setAnchorView(View r2) {
        Anchor r0 = this.anchor;
        if (r0 == null) goto L5;
        r0.unanchor();
    L5:
        if (r2 != null) goto L7;
        Anchor r22 = null;
    L8:
        this.anchor = r22;
        return this;
    L7:
        r22 = Anchor.anchor(this, r2);
        goto L8
    }

    public void setAnchorViewLayoutListenerEnabled(boolean r1) {
        this.anchorViewLayoutListenerEnabled = r1;
    }

    public B setAnimationMode(int r2) {
        this.view.setAnimationMode(r2);
        return this;
    }

    public B setBehavior(Behavior r1) {
        this.behavior = r1;
        return this;
    }

    public B setDuration(int r1) {
        this.duration = r1;
        return this;
    }

    public B setGestureInsetBottomIgnored(boolean r1) {
        this.gestureInsetBottomIgnored = r1;
        return this;
    }

    public boolean shouldAnimate() {
        AccessibilityManager r1 = this.accessibilityManager;
        if (r1 != null) goto L5;
        return true;
    L5:
        List<AccessibilityServiceInfo> r12 = r1.getEnabledAccessibilityServiceList(1);
        if (r12 != null) goto L8;
        return false;
    L8:
        if (r12.isEmpty() == false) goto L12;
        return true;
    L12:
        return false;
    }

    public void show() {
        SnackbarManager.getInstance().show(getDuration(), this.managerCallback);
    }

    public final void showView() {
        if (this.view.getParent() != null) goto L9;
        ViewGroup.LayoutParams r0 = this.view.getLayoutParams();
        if ((r0 instanceof CoordinatorLayout.LayoutParams) == false) goto L7;
        setUpBehavior((CoordinatorLayout.LayoutParams) r0);
    L7:
        this.view.addToTargetParent(this.targetParent);
        recalculateAndUpdateMargins();
        this.view.setVisibility(4);
    L9:
        if (this.view.isLaidOut() == false) goto L12;
        showViewImpl();
        return;
    L12:
        this.pendingShowingView = true;
    }
}
