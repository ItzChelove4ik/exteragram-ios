package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.TransformationCallback;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    private static final int DEF_STYLE_RES = 0;
    private static final int FAB_ALIGNMENT_ANIM_DURATION_ATTR = 0;
    private static final int FAB_ALIGNMENT_ANIM_DURATION_DEFAULT = 300;
    private static final int FAB_ALIGNMENT_ANIM_EASING_ATTR = 0;
    private static final float FAB_ALIGNMENT_ANIM_EASING_MIDPOINT = 0.2f;
    public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
    public static final int FAB_ALIGNMENT_MODE_END = 1;
    public static final int FAB_ANCHOR_MODE_CRADLE = 1;
    public static final int FAB_ANCHOR_MODE_EMBED = 0;
    public static final int FAB_ANIMATION_MODE_SCALE = 0;
    public static final int FAB_ANIMATION_MODE_SLIDE = 1;
    public static final int MENU_ALIGNMENT_MODE_AUTO = 0;
    public static final int MENU_ALIGNMENT_MODE_START = 1;
    private static final int NO_FAB_END_MARGIN = -1;
    private static final int NO_MENU_RES_ID = 0;
    private int animatingModeChangeCounter;
    private ArrayList<AnimationListener> animationListeners;
    private Behavior behavior;
    private int bottomInset;
    private int fabAlignmentMode;
    private int fabAlignmentModeEndMargin;
    private int fabAnchorMode;
    AnimatorListenerAdapter fabAnimationListener;
    private int fabAnimationMode;
    private boolean fabAttached;
    private final int fabOffsetEndMode;
    TransformationCallback<FloatingActionButton> fabTransformationCallback;
    private boolean hideOnScroll;
    private int leftInset;
    private final MaterialShapeDrawable materialShapeDrawable;
    private int menuAlignmentMode;
    private boolean menuAnimatingWithFabAlignmentMode;
    private Animator menuAnimator;
    private Animator modeAnimator;
    private Integer navigationIconTint;
    private final boolean paddingBottomSystemWindowInsets;
    private final boolean paddingLeftSystemWindowInsets;
    private final boolean paddingRightSystemWindowInsets;
    private int pendingMenuResId;
    private final boolean removeEmbeddedFabElevation;
    private int rightInset;

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomAppBar this$0;

        public AnonymousClass1(BottomAppBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            if (BottomAppBar.access$000(this.this$0) == true) goto L6;
            BottomAppBar r22 = this.this$0;
            BottomAppBar.access$300(r22, BottomAppBar.access$100(r22), BottomAppBar.access$200(this.this$0));
            return;
        }
    }

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$2, reason: invalid class name */
    public class AnonymousClass2 implements TransformationCallback<FloatingActionButton> {
        final /* synthetic */ BottomAppBar this$0;

        public AnonymousClass2(BottomAppBar r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.animation.TransformationCallback
        public /* bridge */ /* synthetic */ void onScaleChanged(View r1) {
            onScaleChanged((FloatingActionButton) r1);
        }

        public void onScaleChanged(FloatingActionButton r3) {
            MaterialShapeDrawable r0 = BottomAppBar.access$500(this.this$0);
            if (r3.getVisibility() == 0) goto L5;
        L7:
            float r2 = 0.0f;
        L8:
            r0.setInterpolation(r2);
            return;
        L5:
            if (BottomAppBar.access$400(this.this$0) != 1) goto L7;
            r2 = r3.getScaleY();
            goto L8
        }

        @Override // com.google.android.material.animation.TransformationCallback
        public /* bridge */ /* synthetic */ void onTranslationChanged(View r1) {
            onTranslationChanged((FloatingActionButton) r1);
        }

        public void onTranslationChanged(FloatingActionButton r4) {
            if (BottomAppBar.access$400(this.this$0) == 1) goto L5;
            return;
        L5:
            float r0 = r4.getTranslationX();
            if (BottomAppBar.access$600(this.this$0).getHorizontalOffset() == r0) goto L8;
            BottomAppBar.access$600(this.this$0).setHorizontalOffset(r0);
            BottomAppBar.access$500(this.this$0).invalidateSelf();
        L8:
            float r1 = 0.0f;
            float r02 = Math.max(0.0f, -r4.getTranslationY());
            if (BottomAppBar.access$600(this.this$0).getCradleVerticalOffset() == r02) goto L11;
            BottomAppBar.access$600(this.this$0).setCradleVerticalOffset(r02);
            BottomAppBar.access$500(this.this$0).invalidateSelf();
        L11:
            MaterialShapeDrawable r3 = BottomAppBar.access$500(this.this$0);
            if (r4.getVisibility() != 0) goto L14;
            r1 = r4.getScaleY();
        L14:
            r3.setInterpolation(r1);
        }
    }

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$3, reason: invalid class name */
    public class AnonymousClass3 implements ViewUtils.OnApplyWindowInsetsListener {
        final /* synthetic */ BottomAppBar this$0;

        public AnonymousClass3(BottomAppBar r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5, ViewUtils.RelativePadding r6) {
            if (BottomAppBar.access$700(this.this$0) == false) goto L5;
            BottomAppBar.access$802(this.this$0, r5.getSystemWindowInsetBottom());
        L5:
            boolean r62 = true;
            boolean r0 = false;
            if (BottomAppBar.access$900(this.this$0) == true) goto L8;
            boolean r42 = false;
        L14:
            if (BottomAppBar.access$1100(this.this$0) == true) goto L16;
        L20:
            if (r42 == true) goto L24;
            if (r0 == true) goto L24;
            return r5;
        L24:
            BottomAppBar.access$1300(this.this$0);
            BottomAppBar.access$1400(this.this$0);
            BottomAppBar.access$1500(this.this$0);
            return r5;
        L16:
            if (BottomAppBar.access$1200(this.this$0) != r5.getSystemWindowInsetRight()) goto L19;
            r62 = false;
        L19:
            BottomAppBar.access$1202(this.this$0, r5.getSystemWindowInsetRight());
            r0 = r62;
            goto L20
        L8:
            if (BottomAppBar.access$1000(this.this$0) == r5.getSystemWindowInsetLeft()) goto L10;
            r42 = true;
        L11:
            BottomAppBar.access$1002(this.this$0, r5.getSystemWindowInsetLeft());
            goto L14
        L10:
            r42 = false;
            goto L11
        }
    }

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomAppBar this$0;

        public AnonymousClass4(BottomAppBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            BottomAppBar.access$1700(this.this$0);
            BottomAppBar.access$1802(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            BottomAppBar.access$1600(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$5, reason: invalid class name */
    public class AnonymousClass5 extends FloatingActionButton.OnVisibilityChangedListener {
        final /* synthetic */ BottomAppBar this$0;
        final /* synthetic */ int val$targetMode;

        /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$5$1, reason: invalid class name */
        public class AnonymousClass1 extends FloatingActionButton.OnVisibilityChangedListener {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1) {
                this.this$1 = r1;
            }

            @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener
            public void onShown(FloatingActionButton r1) {
                BottomAppBar.access$1700(this.this$1.this$0);
            }
        }

        public AnonymousClass5(BottomAppBar r1, int r2) {
            this.this$0 = r1;
            this.val$targetMode = r2;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener
        public void onHidden(FloatingActionButton r3) {
            r3.setTranslationX(BottomAppBar.access$1900(this.this$0, this.val$targetMode));
            r3.show(new AnonymousClass1(this));
        }
    }

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomAppBar this$0;

        public AnonymousClass6(BottomAppBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            BottomAppBar.access$1700(this.this$0);
            BottomAppBar.access$002(this.this$0, false);
            BottomAppBar.access$2002(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            BottomAppBar.access$1600(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        public boolean cancelled;
        final /* synthetic */ BottomAppBar this$0;
        final /* synthetic */ ActionMenuView val$actionMenuView;
        final /* synthetic */ boolean val$targetAttached;
        final /* synthetic */ int val$targetMode;

        public AnonymousClass7(BottomAppBar r1, ActionMenuView r2, int r3, boolean r4) {
            this.this$0 = r1;
            this.val$actionMenuView = r2;
            this.val$targetMode = r3;
            this.val$targetAttached = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (this.cancelled == false) goto L5;
            return;
        L5:
            if (BottomAppBar.access$2100(this.this$0) == 0) goto L7;
            boolean r42 = true;
        L8:
            BottomAppBar r0 = this.this$0;
            r0.replaceMenu(BottomAppBar.access$2100(r0));
            BottomAppBar.access$2200(this.this$0, this.val$actionMenuView, this.val$targetMode, this.val$targetAttached, r42);
            return;
        L7:
            r42 = false;
            goto L8
        }
    }

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$8, reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ BottomAppBar this$0;
        final /* synthetic */ ActionMenuView val$actionMenuView;
        final /* synthetic */ int val$fabAlignmentMode;
        final /* synthetic */ boolean val$fabAttached;

        public AnonymousClass8(BottomAppBar r1, ActionMenuView r2, int r3, boolean r4) {
            this.this$0 = r1;
            this.val$actionMenuView = r2;
            this.val$fabAlignmentMode = r3;
            this.val$fabAttached = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$actionMenuView.setTranslationX(this.this$0.getActionMenuViewTranslationX(r0, this.val$fabAlignmentMode, this.val$fabAttached));
        }
    }

    /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomAppBar this$0;

        public AnonymousClass9(BottomAppBar r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            this.this$0.fabAnimationListener.onAnimationStart(r2);
            FloatingActionButton r22 = BottomAppBar.access$2300(this.this$0);
            if (r22 == null) goto L6;
            r22.setTranslationX(BottomAppBar.access$2400(this.this$0));
            return;
        }
    }

    public interface AnimationListener {
        void onAnimationEnd(BottomAppBar r1);

        void onAnimationStart(BottomAppBar r1);
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect fabContentRect;
        private final View.OnLayoutChangeListener fabLayoutListener;
        private int originalBottomMargin;
        private WeakReference<BottomAppBar> viewRef;

        /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$Behavior$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnLayoutChangeListener {
            final /* synthetic */ Behavior this$0;

            public AnonymousClass1(Behavior r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
                BottomAppBar r22 = (BottomAppBar) Behavior.access$2500(this.this$0).get();
                if (r22 != null) goto L5;
            L23:
                r1.removeOnLayoutChangeListener(this);
                return;
            L5:
                if ((r1 instanceof FloatingActionButton) == false) goto L7;
            L9:
                int r32 = r1.getHeight();
                if ((r1 instanceof FloatingActionButton) == false) goto L12;
                FloatingActionButton r33 = (FloatingActionButton) r1;
                r33.getMeasuredContentRect(Behavior.access$2600(this.this$0));
                int r42 = Behavior.access$2600(this.this$0).height();
                r22.setFabDiameter(r42);
                r22.setFabCornerSize(r33.getShapeAppearanceModel().getTopLeftCornerSize().getCornerSize(new RectF(Behavior.access$2600(this.this$0))));
                r32 = r42;
            L12:
                CoordinatorLayout.LayoutParams r43 = (CoordinatorLayout.LayoutParams) r1.getLayoutParams();
                if (Behavior.access$2700(this.this$0) == 0) goto L15;
            L21:
                BottomAppBar.access$1400(r22);
                return;
            L15:
                if (BottomAppBar.access$400(r22) != 1) goto L17;
                ((ViewGroup.MarginLayoutParams) r43).bottomMargin = BottomAppBar.access$2800(r22) + (r22.getResources().getDimensionPixelOffset(R.dimen.mtrl_bottomappbar_fab_bottom_margin) - ((r1.getMeasuredHeight() - r32) / 2));
            L17:
                ((ViewGroup.MarginLayoutParams) r43).leftMargin = BottomAppBar.access$2900(r22);
                ((ViewGroup.MarginLayoutParams) r43).rightMargin = BottomAppBar.access$3000(r22);
                if (ViewUtils.isLayoutRtl(r1) == false) goto L20;
                ((ViewGroup.MarginLayoutParams) r43).leftMargin += BottomAppBar.access$3100(r22);
                goto L21
            L20:
                ((ViewGroup.MarginLayoutParams) r43).rightMargin += BottomAppBar.access$3100(r22);
                goto L21
            L7:
                if ((r1 instanceof ExtendedFloatingActionButton) == true) goto L9;
                goto L9
            }
        }

        public Behavior() {
            this.fabLayoutListener = new AnonymousClass1(this);
            this.fabContentRect = new Rect();
        }

        public Behavior(Context r1, AttributeSet r2) {
            super(r1, r2);
            this.fabLayoutListener = new AnonymousClass1(this);
            this.fabContentRect = new Rect();
        }

        public static /* synthetic */ WeakReference access$2500(Behavior r0) {
            return r0.viewRef;
        }

        public static /* synthetic */ Rect access$2600(Behavior r0) {
            return r0.fabContentRect;
        }

        public static /* synthetic */ int access$2700(Behavior r0) {
            return r0.originalBottomMargin;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout r1, View r2, int r3) {
            return onLayoutChild(r1, (BottomAppBar) r2, r3);
        }

        public boolean onLayoutChild(CoordinatorLayout r4, BottomAppBar r5, int r6) {
            this.viewRef = new WeakReference(r5);
            View r0 = BottomAppBar.access$3200(r5);
            if (r0 != null) goto L5;
        L21:
            r4.onLayoutChild(r5, r6);
            return super.onLayoutChild(r4, r5, r6);
        L5:
            if (r0.isLaidOut() == true) goto L21;
            BottomAppBar.access$3300(r5, r0);
            this.originalBottomMargin = ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) r0.getLayoutParams())).bottomMargin;
            if ((r0 instanceof FloatingActionButton) == false) goto L20;
            FloatingActionButton r1 = (FloatingActionButton) r0;
            if (BottomAppBar.access$400(r5) != 0) goto L14;
            if (BottomAppBar.access$3400(r5) == false) goto L14;
            r1.setElevation(0.0f);
            r1.setCompatElevation(0.0f);
        L14:
            if (r1.getShowMotionSpec() != null) goto L17;
            r1.setShowMotionSpecResource(R.animator.mtrl_fab_show_motion_spec);
        L17:
            if (r1.getHideMotionSpec() != null) goto L19;
            r1.setHideMotionSpecResource(R.animator.mtrl_fab_hide_motion_spec);
        L19:
            BottomAppBar.access$3500(r5, r1);
        L20:
            r0.addOnLayoutChangeListener(this.fabLayoutListener);
            BottomAppBar.access$1400(r5);
            goto L21
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout r1, View r2, View r3, View r4, int r5, int r6) {
            return onStartNestedScroll(r1, (BottomAppBar) r2, r3, r4, r5, r6);
        }

        public boolean onStartNestedScroll(CoordinatorLayout r2, BottomAppBar r3, View r4, View r5, int r6, int r7) {
            if (r3.getHideOnScroll() == true) goto L5;
            return false;
        L5:
            if (super.onStartNestedScroll(r2, r3, r4, r5, r6, r7) == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAlignmentMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAnchorMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FabAnimationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MenuAlignmentMode {
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        int fabAlignmentMode;
        boolean fabAttached;

        /* renamed from: com.google.android.material.bottomappbar.BottomAppBar$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
            public AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r2) {
                return new SavedState(r2, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return new SavedState(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return createFromParcel(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            this.fabAlignmentMode = r1.readInt();
            if (r1.readInt() == 0) goto L5;
            boolean r12 = true;
        L6:
            this.fabAttached = r12;
            return;
        L5:
            r12 = false;
            goto L6
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeInt(this.fabAlignmentMode);
            r1.writeInt(this.fabAttached ? 1 : 0);
        }
    }

    public static /* synthetic */ void $r8$lambda$yePjeUKKGjaM8o2xaoShf70hUmI(View r0) {
        r0.requestLayout();
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_BottomAppBar;
        FAB_ALIGNMENT_ANIM_DURATION_ATTR = R.attr.motionDurationLong2;
        FAB_ALIGNMENT_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    }

    public BottomAppBar(Context r2) {
        this(r2, null);
    }

    public BottomAppBar(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.bottomAppBarStyle);
    }

    public BottomAppBar(Context r13, AttributeSet r14, int r15) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r13, r14, r15, r4), r14, r15);
        MaterialShapeDrawable r132 = new MaterialShapeDrawable();
        this.materialShapeDrawable = r132;
        this.animatingModeChangeCounter = 0;
        this.pendingMenuResId = 0;
        this.menuAnimatingWithFabAlignmentMode = false;
        this.fabAttached = true;
        this.fabAnimationListener = new AnonymousClass1(this);
        this.fabTransformationCallback = new AnonymousClass2(this);
        Context r0 = getContext();
        TypedArray r142 = ThemeEnforcement.obtainStyledAttributes(r0, r14, R.styleable.BottomAppBar, r15, r4, new int[0]);
        ColorStateList r152 = MaterialResources.getColorStateList(r0, r142, R.styleable.BottomAppBar_backgroundTint);
        if (r142.hasValue(R.styleable.BottomAppBar_navigationIconTint) == false) goto L5;
        setNavigationIconTint(r142.getColor(R.styleable.BottomAppBar_navigationIconTint, -1));
    L5:
        int r2 = r142.getDimensionPixelSize(R.styleable.BottomAppBar_elevation, 0);
        float r8 = r142.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleMargin, 0);
        float r9 = r142.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float r10 = r142.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.fabAlignmentMode = r142.getInt(R.styleable.BottomAppBar_fabAlignmentMode, 0);
        this.fabAnimationMode = r142.getInt(R.styleable.BottomAppBar_fabAnimationMode, 0);
        this.fabAnchorMode = r142.getInt(R.styleable.BottomAppBar_fabAnchorMode, 1);
        this.removeEmbeddedFabElevation = r142.getBoolean(R.styleable.BottomAppBar_removeEmbeddedFabElevation, true);
        this.menuAlignmentMode = r142.getInt(R.styleable.BottomAppBar_menuAlignmentMode, 0);
        this.hideOnScroll = r142.getBoolean(R.styleable.BottomAppBar_hideOnScroll, false);
        this.paddingBottomSystemWindowInsets = r142.getBoolean(R.styleable.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.paddingLeftSystemWindowInsets = r142.getBoolean(R.styleable.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.paddingRightSystemWindowInsets = r142.getBoolean(R.styleable.BottomAppBar_paddingRightSystemWindowInsets, false);
        this.fabAlignmentModeEndMargin = r142.getDimensionPixelOffset(R.styleable.BottomAppBar_fabAlignmentModeEndMargin, -1);
        boolean r5 = r142.getBoolean(R.styleable.BottomAppBar_addElevationShadow, true);
        r142.recycle();
        this.fabOffsetEndMode = getResources().getDimensionPixelOffset(R.dimen.mtrl_bottomappbar_fabOffsetEndMode);
        r132.setShapeAppearanceModel(ShapeAppearanceModel.builder().setTopEdge(new BottomAppBarTopEdgeTreatment(r8, r9, r10)).build());
        if (r5 == false) goto L8;
        r132.setShadowCompatibilityMode(2);
    L11:
        r132.setPaintStyle(Paint.Style.FILL);
        r132.initializeElevationOverlay(r0);
        r132.setTintList(r152);
        setElevation(r2);
        setBackground(r132);
        ViewUtils.doOnApplyWindowInsets(this, r14, r15, r4, new AnonymousClass3(this));
        return;
    L8:
        r132.setShadowCompatibilityMode(1);
        if (Build.VERSION.SDK_INT < 28) goto L11;
        setOutlineAmbientShadowColor(0);
        setOutlineSpotShadowColor(0);
        goto L11
    }

    public static /* synthetic */ boolean access$000(BottomAppBar r0) {
        return r0.menuAnimatingWithFabAlignmentMode;
    }

    public static /* synthetic */ boolean access$002(BottomAppBar r0, boolean r1) {
        r0.menuAnimatingWithFabAlignmentMode = r1;
        return r1;
    }

    public static /* synthetic */ int access$100(BottomAppBar r0) {
        return r0.fabAlignmentMode;
    }

    public static /* synthetic */ int access$1000(BottomAppBar r0) {
        return r0.leftInset;
    }

    public static /* synthetic */ int access$1002(BottomAppBar r0, int r1) {
        r0.leftInset = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$1100(BottomAppBar r0) {
        return r0.paddingRightSystemWindowInsets;
    }

    public static /* synthetic */ int access$1200(BottomAppBar r0) {
        return r0.rightInset;
    }

    public static /* synthetic */ int access$1202(BottomAppBar r0, int r1) {
        r0.rightInset = r1;
        return r1;
    }

    public static /* synthetic */ void access$1300(BottomAppBar r0) {
        r0.cancelAnimations();
    }

    public static /* synthetic */ void access$1400(BottomAppBar r0) {
        r0.setCutoutStateAndTranslateFab();
    }

    public static /* synthetic */ void access$1500(BottomAppBar r0) {
        r0.setActionMenuViewPosition();
    }

    public static /* synthetic */ void access$1600(BottomAppBar r0) {
        r0.dispatchAnimationStart();
    }

    public static /* synthetic */ void access$1700(BottomAppBar r0) {
        r0.dispatchAnimationEnd();
    }

    public static /* synthetic */ Animator access$1802(BottomAppBar r0, Animator r1) {
        r0.modeAnimator = r1;
        return r1;
    }

    public static /* synthetic */ float access$1900(BottomAppBar r0, int r1) {
        return r0.getFabTranslationX(r1);
    }

    public static /* synthetic */ boolean access$200(BottomAppBar r0) {
        return r0.fabAttached;
    }

    public static /* synthetic */ Animator access$2002(BottomAppBar r0, Animator r1) {
        r0.menuAnimator = r1;
        return r1;
    }

    public static /* synthetic */ int access$2100(BottomAppBar r0) {
        return r0.pendingMenuResId;
    }

    public static /* synthetic */ void access$2200(BottomAppBar r0, ActionMenuView r1, int r2, boolean r3, boolean r4) {
        r0.translateActionMenuView(r1, r2, r3, r4);
    }

    public static /* synthetic */ FloatingActionButton access$2300(BottomAppBar r0) {
        return r0.findDependentFab();
    }

    public static /* synthetic */ float access$2400(BottomAppBar r0) {
        return r0.getFabTranslationX();
    }

    public static /* synthetic */ int access$2800(BottomAppBar r0) {
        return r0.getBottomInset();
    }

    public static /* synthetic */ int access$2900(BottomAppBar r0) {
        return r0.getLeftInset();
    }

    public static /* synthetic */ void access$300(BottomAppBar r0, int r1, boolean r2) {
        r0.maybeAnimateMenuView(r1, r2);
    }

    public static /* synthetic */ int access$3000(BottomAppBar r0) {
        return r0.getRightInset();
    }

    public static /* synthetic */ int access$3100(BottomAppBar r0) {
        return r0.fabOffsetEndMode;
    }

    public static /* synthetic */ View access$3200(BottomAppBar r0) {
        return r0.findDependentView();
    }

    public static /* synthetic */ void access$3300(BottomAppBar r0, View r1) {
        updateFabAnchorGravity(r0, r1);
    }

    public static /* synthetic */ boolean access$3400(BottomAppBar r0) {
        return r0.removeEmbeddedFabElevation;
    }

    public static /* synthetic */ void access$3500(BottomAppBar r0, FloatingActionButton r1) {
        r0.addFabAnimationListeners(r1);
    }

    public static /* synthetic */ int access$400(BottomAppBar r0) {
        return r0.fabAnchorMode;
    }

    public static /* synthetic */ MaterialShapeDrawable access$500(BottomAppBar r0) {
        return r0.materialShapeDrawable;
    }

    public static /* synthetic */ BottomAppBarTopEdgeTreatment access$600(BottomAppBar r0) {
        return r0.getTopEdgeTreatment();
    }

    public static /* synthetic */ boolean access$700(BottomAppBar r0) {
        return r0.paddingBottomSystemWindowInsets;
    }

    public static /* synthetic */ int access$802(BottomAppBar r0, int r1) {
        r0.bottomInset = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$900(BottomAppBar r0) {
        return r0.paddingLeftSystemWindowInsets;
    }

    private void addFabAnimationListeners(FloatingActionButton r2) {
        r2.addOnHideAnimationListener(this.fabAnimationListener);
        r2.addOnShowAnimationListener(new AnonymousClass9(this));
        r2.addTransformationCallback(this.fabTransformationCallback);
    }

    private void cancelAnimations() {
        Animator r0 = this.menuAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        Animator r1 = this.modeAnimator;
        if (r1 == null) goto L9;
        r1.cancel();
        return;
    }

    private void createFabTranslationXAnimation(int r4, List<Animator> r5) {
        ObjectAnimator r42 = ObjectAnimator.ofFloat(findDependentFab(), "translationX", new float[]{getFabTranslationX(r4)});
        r42.setDuration(getFabAlignmentAnimationDuration());
        r5.add(r42);
    }

    private void createMenuViewTranslationAnimation(int r10, boolean r11, List<Animator> r12) {
        ActionMenuView r0 = getActionMenuView();
        if (r0 == null) goto L14;
        float r1 = getFabAlignmentAnimationDuration();
        Animator r3 = ObjectAnimator.ofFloat(r0, "alpha", new float[]{1.0f});
        r3.setDuration((long) (0.8f * r1));
        if (Math.abs(r0.getTranslationX() - getActionMenuViewTranslationX(r0, r10, r11)) <= 1.0f) goto L10;
        ObjectAnimator r5 = ObjectAnimator.ofFloat(r0, "alpha", new float[]{0.0f});
        r5.setDuration((long) (r1 * FAB_ALIGNMENT_ANIM_EASING_MIDPOINT));
        r5.addListener(new AnonymousClass7(this, r0, r10, r11));
        AnimatorSet r9 = new AnimatorSet();
        r9.playSequentially(new Animator[]{r5, r3});
        r12.add(r9);
        return;
    L10:
        if (r0.getAlpha() >= 1.0f) goto L13;
        r12.add(r3);
        return;
    L13:
        return;
    }

    private void dispatchAnimationEnd() {
        int r0 = this.animatingModeChangeCounter - 1;
        this.animatingModeChangeCounter = r0;
        if (r0 != 0) goto L9;
        ArrayList<AnimationListener> r02 = this.animationListeners;
        if (r02 == null) goto L11;
        int r1 = r02.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L12;
        AnimationListener r3 = r02.get(r2);
        r2 = r2 + 1;
        r3.onAnimationEnd(this);
        goto L7
    L12:
        return;
    L11:
        return;
    }

    private void dispatchAnimationStart() {
        int r0 = this.animatingModeChangeCounter;
        this.animatingModeChangeCounter = r0 + 1;
        if (r0 != 0) goto L9;
        ArrayList<AnimationListener> r02 = this.animationListeners;
        if (r02 == null) goto L11;
        int r1 = r02.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L12;
        AnimationListener r3 = r02.get(r2);
        r2 = r2 + 1;
        r3.onAnimationStart(this);
        goto L7
    L12:
        return;
    L11:
        return;
    }

    private FloatingActionButton findDependentFab() {
        View r1 = findDependentView();
        if ((r1 instanceof FloatingActionButton) == true) goto L5;
        return null;
    L5:
        return (FloatingActionButton) r1;
    }

    private View findDependentView() {
        if ((getParent() instanceof CoordinatorLayout) == true) goto L5;
        return null;
    L5:
        Iterator<View> r3 = ((CoordinatorLayout) getParent()).getDependents(this).iterator();
    L7:
        if (r3.hasNext() == false) goto L13;
        View r0 = r3.next();
        if ((r0 instanceof FloatingActionButton) == true) goto L12;
        if ((r0 instanceof ExtendedFloatingActionButton) == false) goto L7;
    L12:
        return r0;
    L13:
        return null;
    }

    private ActionMenuView getActionMenuView() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L10;
        View r1 = getChildAt(r0);
        if ((r1 instanceof ActionMenuView) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return (ActionMenuView) r1;
    L10:
        return null;
    }

    private int getBottomInset() {
        return this.bottomInset;
    }

    private int getFabAlignmentAnimationDuration() {
        return MotionUtils.resolveThemeDuration(getContext(), FAB_ALIGNMENT_ANIM_DURATION_ATTR, 300);
    }

    private float getFabTranslationX() {
        return getFabTranslationX(this.fabAlignmentMode);
    }

    private float getFabTranslationX(int r6) {
        boolean r0 = ViewUtils.isLayoutRtl(this);
        int r1 = 1;
        if (r6 != 1) goto L19;
        View r62 = findDependentView();
        if (r0 == false) goto L7;
        int r2 = this.leftInset;
    L9:
        if (this.fabAlignmentModeEndMargin == (-1)) goto L13;
        if (r62 == null) goto L13;
        int r63 = (r62.getMeasuredWidth() / 2) + this.fabAlignmentModeEndMargin;
    L14:
        int r5 = (getMeasuredWidth() / 2) - (r2 + r63);
        if (r0 == false) goto L18;
        r1 = -1;
    L18:
        return r5 * r1;
    L13:
        r63 = this.fabOffsetEndMode;
        goto L14
    L7:
        r2 = this.rightInset;
        goto L9
    L19:
        return 0.0f;
    }

    private float getFabTranslationY() {
        if (this.fabAnchorMode == 1) goto L5;
        View r0 = findDependentView();
        if (r0 == null) goto L9;
        int r2 = (-((getMeasuredHeight() + getBottomInset()) - r0.getMeasuredHeight())) / 2;
    L11:
        return r2;
    L9:
        r2 = 0;
        goto L11
    L5:
        return -getTopEdgeTreatment().getCradleVerticalOffset();
    }

    private int getLeftInset() {
        return this.leftInset;
    }

    private int getRightInset() {
        return this.rightInset;
    }

    private BottomAppBarTopEdgeTreatment getTopEdgeTreatment() {
        return (BottomAppBarTopEdgeTreatment) this.materialShapeDrawable.getShapeAppearanceModel().getTopEdge();
    }

    private boolean isFabVisibleOrWillBeShown() {
        FloatingActionButton r0 = findDependentFab();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isOrWillBeShown() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private void maybeAnimateMenuView(int r4, boolean r5) {
        if (isLaidOut() == true) goto L6;
        this.menuAnimatingWithFabAlignmentMode = false;
        replaceMenu(this.pendingMenuResId);
        return;
    L6:
        Animator r0 = this.menuAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
    L9:
        ArrayList r02 = new ArrayList();
        if (isFabVisibleOrWillBeShown() == true) goto L12;
        r4 = 0;
        r5 = false;
    L12:
        createMenuViewTranslationAnimation(r4, r5, r02);
        AnimatorSet r42 = new AnimatorSet();
        r42.playTogether(r02);
        this.menuAnimator = r42;
        r42.addListener(new AnonymousClass6(this));
        this.menuAnimator.start();
    }

    private void maybeAnimateModeChange(int r4) {
        if (this.fabAlignmentMode != r4) goto L5;
        return;
    L5:
        if (isLaidOut() == false) goto L17;
        Animator r0 = this.modeAnimator;
        if (r0 == null) goto L10;
        r0.cancel();
    L10:
        ArrayList r02 = new ArrayList();
        if (this.fabAnimationMode != 1) goto L13;
        createFabTranslationXAnimation(r4, r02);
    L14:
        AnimatorSet r42 = new AnimatorSet();
        r42.playTogether(r02);
        r42.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), FAB_ALIGNMENT_ANIM_EASING_ATTR, AnimationUtils.LINEAR_INTERPOLATOR));
        this.modeAnimator = r42;
        r42.addListener(new AnonymousClass4(this));
        this.modeAnimator.start();
        return;
    L13:
        createFabDefaultXAnimation(r4, r02);
        goto L14
    }

    private Drawable maybeTintNavigationIcon(Drawable r2) {
        if (r2 != null) goto L4;
        return r2;
    L4:
        if (this.navigationIconTint == null) goto L8;
        Drawable r22 = DrawableCompat.wrap(r2.mutate());
        r22.setTint(this.navigationIconTint.intValue());
        return r22;
    L8:
        return r2;
    }

    private void setActionMenuViewPosition() {
        ActionMenuView r0 = getActionMenuView();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.menuAnimator != null) goto L13;
        r0.setAlpha(1.0f);
        if (isFabVisibleOrWillBeShown() == true) goto L10;
        translateActionMenuView(r0, 0, false);
        return;
    L10:
        translateActionMenuView(r0, this.fabAlignmentMode, this.fabAttached);
        return;
    }

    private void setCutoutStateAndTranslateFab() {
        getTopEdgeTreatment().setHorizontalOffset(getFabTranslationX());
        MaterialShapeDrawable r0 = this.materialShapeDrawable;
        if (this.fabAttached == true) goto L5;
    L9:
        float r1 = 0.0f;
    L10:
        r0.setInterpolation(r1);
        View r02 = findDependentView();
        if (r02 == null) goto L14;
        r02.setTranslationY(getFabTranslationY());
        r02.setTranslationX(getFabTranslationX());
        return;
    L14:
        return;
    L5:
        if (isFabVisibleOrWillBeShown() == false) goto L9;
        if (this.fabAnchorMode != 1) goto L9;
        r1 = 1.0f;
        goto L10
    }

    private void translateActionMenuView(ActionMenuView r2, int r3, boolean r4) {
        translateActionMenuView(r2, r3, r4, false);
    }

    private void translateActionMenuView(ActionMenuView r2, int r3, boolean r4, boolean r5) {
        AnonymousClass8 r0 = new AnonymousClass8(this, r2, r3, r4);
        if (r5 == false) goto L6;
        r2.post(r0);
        return;
    L6:
        r0.run();
    }

    private static void updateFabAnchorGravity(BottomAppBar r2, View r3) {
        CoordinatorLayout.LayoutParams r32 = (CoordinatorLayout.LayoutParams) r3.getLayoutParams();
        r32.anchorGravity = 17;
        int r22 = r2.fabAnchorMode;
        if (r22 != 1) goto L5;
        r32.anchorGravity = 17 | 48;
    L5:
        if (r22 != 0) goto L8;
        r32.anchorGravity |= 80;
        return;
    }

    public void addAnimationListener(AnimationListener r2) {
        if (this.animationListeners != null) goto L5;
        this.animationListeners = new ArrayList();
    L5:
        this.animationListeners.add(r2);
    }

    public void addOnScrollStateChangedListener(HideBottomViewOnScrollBehavior.OnScrollStateChangedListener r1) {
        getBehavior().addOnScrollStateChangedListener(r1);
    }

    public void clearOnScrollStateChangedListeners() {
        getBehavior().clearOnScrollStateChangedListeners();
    }

    public void createFabDefaultXAnimation(int r2, List<Animator> r3) {
        FloatingActionButton r32 = findDependentFab();
        if (r32 != null) goto L5;
        return;
    L5:
        if (r32.isOrWillBeHidden() == true) goto L10;
        dispatchAnimationStart();
        r32.hide(new AnonymousClass5(this, r2));
        return;
    }

    public int getActionMenuViewTranslationX(ActionMenuView r6, int r7, boolean r8) {
        int r1 = 0;
        if (this.menuAlignmentMode == 1) goto L7;
        if (r7 != 1) goto L6;
        if (r8 == true) goto L7;
    L6:
        return 0;
    L7:
        boolean r72 = ViewUtils.isLayoutRtl(this);
        if (r72 == false) goto L10;
        int r82 = getMeasuredWidth();
    L11:
        int r0 = 0;
    L13:
        if (r0 >= getChildCount()) goto L22;
        View r2 = getChildAt(r0);
        if ((r2.getLayoutParams() instanceof Toolbar.LayoutParams) == false) goto L21;
        if ((((Toolbar.LayoutParams) r2.getLayoutParams()).gravity & 8388615) != 8388611) goto L21;
        if (r72 == false) goto L20;
        r82 = Math.min(r82, r2.getLeft());
        goto L21
    L20:
        r82 = Math.max(r82, r2.getRight());
    L21:
        r0 = r0 + 1;
        goto L13
    L22:
        if (r72 == false) goto L24;
        int r62 = r6.getRight();
    L25:
        if (r72 == false) goto L27;
        int r02 = this.rightInset;
    L29:
        if (getNavigationIcon() != null) goto L35;
        int r5 = getResources().getDimensionPixelOffset(R.dimen.m3_bottomappbar_horizontal_padding);
        if (r72 == false) goto L33;
    L32:
        r1 = r5;
        goto L35
    L33:
        r5 = -r5;
    L35:
        return r82 - ((r62 + r02) + r1);
    L27:
        r02 = -this.leftInset;
        goto L29
    L24:
        r62 = r6.getLeft();
        goto L25
    L10:
        r82 = 0;
        goto L11
    }

    public ColorStateList getBackgroundTint() {
        return this.materialShapeDrawable.getTintList();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public /* bridge */ /* synthetic */ CoordinatorLayout.Behavior getBehavior() {
        return getBehavior();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public Behavior getBehavior() {
        if (this.behavior != null) goto L6;
        this.behavior = new Behavior();
    L6:
        return this.behavior;
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().getCradleVerticalOffset();
    }

    public int getFabAlignmentMode() {
        return this.fabAlignmentMode;
    }

    public int getFabAlignmentModeEndMargin() {
        return this.fabAlignmentModeEndMargin;
    }

    public int getFabAnchorMode() {
        return this.fabAnchorMode;
    }

    public int getFabAnimationMode() {
        return this.fabAnimationMode;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().getFabCradleMargin();
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().getFabCradleRoundedCornerRadius();
    }

    public boolean getHideOnScroll() {
        return this.hideOnScroll;
    }

    public int getMenuAlignmentMode() {
        return this.menuAlignmentMode;
    }

    public boolean isScrolledDown() {
        return getBehavior().isScrolledDown();
    }

    public boolean isScrolledUp() {
        return getBehavior().isScrolledUp();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialShapeDrawable);
        if ((getParent() instanceof ViewGroup) == false) goto L6;
        ((ViewGroup) getParent()).setClipChildren(false);
        return;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (r1 == false) goto L9;
        cancelAnimations();
        setCutoutStateAndTranslateFab();
        View r12 = findDependentView();
        if (r12 == null) goto L9;
        if (r12.isLaidOut() == false) goto L9;
        r12.post(new BottomAppBar$$ExternalSyntheticLambda0(r12));
    L9:
        setActionMenuViewPosition();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        this.fabAlignmentMode = r22.fabAlignmentMode;
        this.fabAttached = r22.fabAttached;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        r1.fabAlignmentMode = this.fabAlignmentMode;
        r1.fabAttached = this.fabAttached;
        return r1;
    }

    public void performHide() {
        performHide(true);
    }

    public void performHide(boolean r2) {
        getBehavior().slideDown(this, r2);
    }

    public void performShow() {
        performShow(true);
    }

    public void performShow(boolean r2) {
        getBehavior().slideUp(this, r2);
    }

    public void removeAnimationListener(AnimationListener r1) {
        ArrayList<AnimationListener> r0 = this.animationListeners;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.remove(r1);
    }

    public void removeOnScrollStateChangedListener(HideBottomViewOnScrollBehavior.OnScrollStateChangedListener r1) {
        getBehavior().removeOnScrollStateChangedListener(r1);
    }

    public void replaceMenu(int r2) {
        if (r2 == 0) goto L5;
        this.pendingMenuResId = 0;
        getMenu().clear();
        inflateMenu(r2);
        return;
    }

    public void setBackgroundTint(ColorStateList r1) {
        this.materialShapeDrawable.setTintList(r1);
    }

    public void setCradleVerticalOffset(float r2) {
        if (r2 == getCradleVerticalOffset()) goto L6;
        getTopEdgeTreatment().setCradleVerticalOffset(r2);
        this.materialShapeDrawable.invalidateSelf();
        setCutoutStateAndTranslateFab();
        return;
    }

    @Override // android.view.View
    public void setElevation(float r2) {
        this.materialShapeDrawable.setElevation(r2);
        int r22 = this.materialShapeDrawable.getShadowRadius() - this.materialShapeDrawable.getShadowOffsetY();
        getBehavior().setAdditionalHiddenOffsetY(this, r22);
    }

    public void setFabAlignmentMode(int r2) {
        setFabAlignmentModeAndReplaceMenu(r2, 0);
    }

    public void setFabAlignmentModeAndReplaceMenu(int r1, int r2) {
        this.pendingMenuResId = r2;
        this.menuAnimatingWithFabAlignmentMode = true;
        maybeAnimateMenuView(r1, this.fabAttached);
        maybeAnimateModeChange(r1);
        this.fabAlignmentMode = r1;
    }

    public void setFabAlignmentModeEndMargin(int r2) {
        if (this.fabAlignmentModeEndMargin == r2) goto L6;
        this.fabAlignmentModeEndMargin = r2;
        setCutoutStateAndTranslateFab();
        return;
    }

    public void setFabAnchorMode(int r1) {
        this.fabAnchorMode = r1;
        setCutoutStateAndTranslateFab();
        View r12 = findDependentView();
        if (r12 == null) goto L6;
        updateFabAnchorGravity(this, r12);
        r12.requestLayout();
        this.materialShapeDrawable.invalidateSelf();
        return;
    }

    public void setFabAnimationMode(int r1) {
        this.fabAnimationMode = r1;
    }

    public void setFabCornerSize(float r2) {
        if (r2 == getTopEdgeTreatment().getFabCornerRadius()) goto L6;
        getTopEdgeTreatment().setFabCornerSize(r2);
        this.materialShapeDrawable.invalidateSelf();
        return;
    }

    public void setFabCradleMargin(float r2) {
        if (r2 == getFabCradleMargin()) goto L6;
        getTopEdgeTreatment().setFabCradleMargin(r2);
        this.materialShapeDrawable.invalidateSelf();
        return;
    }

    public void setFabCradleRoundedCornerRadius(float r2) {
        if (r2 == getFabCradleRoundedCornerRadius()) goto L6;
        getTopEdgeTreatment().setFabCradleRoundedCornerRadius(r2);
        this.materialShapeDrawable.invalidateSelf();
        return;
    }

    public boolean setFabDiameter(int r2) {
        float r22 = r2;
        if (r22 == getTopEdgeTreatment().getFabDiameter()) goto L6;
        getTopEdgeTreatment().setFabDiameter(r22);
        this.materialShapeDrawable.invalidateSelf();
        return true;
    L6:
        return false;
    }

    public void setHideOnScroll(boolean r1) {
        this.hideOnScroll = r1;
    }

    public void setMenuAlignmentMode(int r3) {
        if (this.menuAlignmentMode == r3) goto L8;
        this.menuAlignmentMode = r3;
        ActionMenuView r32 = getActionMenuView();
        if (r32 == null) goto L9;
        translateActionMenuView(r32, this.fabAlignmentMode, isFabVisibleOrWillBeShown());
        return;
    L9:
        return;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable r1) {
        super.setNavigationIcon(maybeTintNavigationIcon(r1));
    }

    public void setNavigationIconTint(int r1) {
        this.navigationIconTint = Integer.valueOf(r1);
        Drawable r12 = getNavigationIcon();
        if (r12 == null) goto L6;
        setNavigationIcon(r12);
        return;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence r1) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence r1) {
    }
}
