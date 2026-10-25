package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int ANIM_STATE_HIDING = 1;
    private static final int ANIM_STATE_NONE = 0;
    private static final int ANIM_STATE_SHOWING = 2;
    private static final int DEF_STYLE_RES = 0;
    private static final int EXTEND = 3;
    private static final int EXTEND_STRATEGY_AUTO = 0;
    private static final int EXTEND_STRATEGY_MATCH_PARENT = 2;
    private static final int EXTEND_STRATEGY_WRAP_CONTENT = 1;
    static final Property<View, Float> HEIGHT = null;
    private static final int HIDE = 1;
    static final Property<View, Float> PADDING_END = null;
    static final Property<View, Float> PADDING_START = null;
    private static final int SHOW = 0;
    private static final int SHRINK = 2;
    static final Property<View, Float> WIDTH = null;
    private int animState;
    private boolean animateShowBeforeLayout;
    private boolean animationEnabled;
    private final CoordinatorLayout.Behavior<ExtendedFloatingActionButton> behavior;
    private final AnimatorTracker changeVisibilityTracker;
    private int collapsedSize;
    private final MotionStrategy extendStrategy;
    private final int extendStrategyType;
    private int extendedPaddingEnd;
    private int extendedPaddingStart;
    private final MotionStrategy hideStrategy;
    private boolean isExtended;
    private boolean isTransforming;
    private int originalHeight;
    protected ColorStateList originalTextCsl;
    private int originalWidth;
    private final MotionStrategy showStrategy;
    private final MotionStrategy shrinkStrategy;

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1, reason: invalid class name */
    public class AnonymousClass1 implements Size {
        final /* synthetic */ ExtendedFloatingActionButton this$0;

        public AnonymousClass1(ExtendedFloatingActionButton r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getHeight() {
            return this.this$0.getCollapsedSize();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public ViewGroup.LayoutParams getLayoutParams() {
            return new ViewGroup.LayoutParams(getWidth(), getHeight());
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getPaddingEnd() {
            return this.this$0.getCollapsedPadding();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getPaddingStart() {
            return this.this$0.getCollapsedPadding();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getWidth() {
            return this.this$0.getCollapsedSize();
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$2, reason: invalid class name */
    public class AnonymousClass2 implements Size {
        final /* synthetic */ ExtendedFloatingActionButton this$0;

        public AnonymousClass2(ExtendedFloatingActionButton r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getHeight() {
            return this.this$0.getMeasuredHeight();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public ViewGroup.LayoutParams getLayoutParams() {
            return new ViewGroup.LayoutParams(-2, -2);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getPaddingEnd() {
            return ExtendedFloatingActionButton.access$100(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getPaddingStart() {
            return ExtendedFloatingActionButton.access$000(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getWidth() {
            return (((this.this$0.getMeasuredWidth() - this.this$0.getPaddingStart()) - this.this$0.getPaddingEnd()) + ExtendedFloatingActionButton.access$000(this.this$0)) + ExtendedFloatingActionButton.access$100(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$3, reason: invalid class name */
    public class AnonymousClass3 implements Size {
        final /* synthetic */ ExtendedFloatingActionButton this$0;
        final /* synthetic */ Size val$wrapContentSize;

        public AnonymousClass3(ExtendedFloatingActionButton r1, Size r2) {
            this.this$0 = r1;
            this.val$wrapContentSize = r2;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getHeight() {
            int r0 = ExtendedFloatingActionButton.access$200(this.this$0);
            ExtendedFloatingActionButton r1 = this.this$0;
            if (r0 != (-1)) goto L23;
            if ((r1.getParent() instanceof View) == false) goto L7;
            View r02 = (View) this.this$0.getParent();
            ViewGroup.LayoutParams r12 = r02.getLayoutParams();
            if (r12 != null) goto L11;
        L14:
            int r13 = r02.getPaddingTop() + r02.getPaddingBottom();
            if ((this.this$0.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L19;
            ViewGroup.MarginLayoutParams r4 = (ViewGroup.MarginLayoutParams) this.this$0.getLayoutParams();
            if (r4 == null) goto L19;
            int r2 = r4.topMargin + r4.bottomMargin;
        L21:
            return (r02.getHeight() - r2) - r13;
        L19:
            r2 = 0;
            goto L21
        L11:
            if (r12.height != (-2)) goto L14;
            return this.val$wrapContentSize.getHeight();
        L7:
            return this.val$wrapContentSize.getHeight();
        L23:
            if (ExtendedFloatingActionButton.access$200(r1) == 0) goto L30;
            if (ExtendedFloatingActionButton.access$200(this.this$0) == (-2)) goto L30;
            return ExtendedFloatingActionButton.access$200(this.this$0);
        L30:
            return this.val$wrapContentSize.getHeight();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public ViewGroup.LayoutParams getLayoutParams() {
            if (ExtendedFloatingActionButton.access$200(this.this$0) != 0) goto L5;
            int r2 = -2;
        L7:
            return new ViewGroup.LayoutParams(-1, r2);
        L5:
            r2 = ExtendedFloatingActionButton.access$200(this.this$0);
            goto L7
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getPaddingEnd() {
            return ExtendedFloatingActionButton.access$100(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getPaddingStart() {
            return ExtendedFloatingActionButton.access$000(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getWidth() {
            if ((this.this$0.getParent() instanceof View) == false) goto L5;
            View r0 = (View) this.this$0.getParent();
            ViewGroup.LayoutParams r1 = r0.getLayoutParams();
            if (r1 != null) goto L9;
        L12:
            int r12 = r0.getPaddingLeft() + r0.getPaddingRight();
            if ((this.this$0.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L17;
            ViewGroup.MarginLayoutParams r3 = (ViewGroup.MarginLayoutParams) this.this$0.getLayoutParams();
            if (r3 == null) goto L17;
            int r2 = r3.leftMargin + r3.rightMargin;
        L19:
            return (r0.getWidth() - r2) - r12;
        L17:
            r2 = 0;
            goto L19
        L9:
            if (r1.width != (-2)) goto L12;
            return this.val$wrapContentSize.getWidth();
        L5:
            return this.val$wrapContentSize.getWidth();
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$4, reason: invalid class name */
    public class AnonymousClass4 implements Size {
        final /* synthetic */ ExtendedFloatingActionButton this$0;
        final /* synthetic */ Size val$matchParentSize;
        final /* synthetic */ Size val$wrapContentSize;

        public AnonymousClass4(ExtendedFloatingActionButton r1, Size r2, Size r3) {
            this.this$0 = r1;
            this.val$matchParentSize = r2;
            this.val$wrapContentSize = r3;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getHeight() {
            if (ExtendedFloatingActionButton.access$200(this.this$0) != (-1)) goto L7;
            return this.val$matchParentSize.getHeight();
        L7:
            if (ExtendedFloatingActionButton.access$200(this.this$0) == 0) goto L14;
            if (ExtendedFloatingActionButton.access$200(this.this$0) == (-2)) goto L14;
            return ExtendedFloatingActionButton.access$200(this.this$0);
        L14:
            return this.val$wrapContentSize.getHeight();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public ViewGroup.LayoutParams getLayoutParams() {
            int r2 = -2;
            if (ExtendedFloatingActionButton.access$300(this.this$0) != 0) goto L5;
            int r1 = -2;
        L7:
            if (ExtendedFloatingActionButton.access$200(this.this$0) == 0) goto L11;
            r2 = ExtendedFloatingActionButton.access$200(this.this$0);
        L11:
            return new ViewGroup.LayoutParams(r1, r2);
        L5:
            r1 = ExtendedFloatingActionButton.access$300(this.this$0);
            goto L7
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getPaddingEnd() {
            return ExtendedFloatingActionButton.access$100(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getPaddingStart() {
            return ExtendedFloatingActionButton.access$000(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.Size
        public int getWidth() {
            if (ExtendedFloatingActionButton.access$300(this.this$0) != (-1)) goto L7;
            return this.val$matchParentSize.getWidth();
        L7:
            if (ExtendedFloatingActionButton.access$300(this.this$0) == 0) goto L14;
            if (ExtendedFloatingActionButton.access$300(this.this$0) == (-2)) goto L14;
            return ExtendedFloatingActionButton.access$300(this.this$0);
        L14:
            return this.val$wrapContentSize.getWidth();
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        private boolean cancelled;
        final /* synthetic */ ExtendedFloatingActionButton this$0;
        final /* synthetic */ OnChangedCallback val$callback;
        final /* synthetic */ MotionStrategy val$strategy;

        public AnonymousClass5(ExtendedFloatingActionButton r1, MotionStrategy r2, OnChangedCallback r3) {
            this.this$0 = r1;
            this.val$strategy = r2;
            this.val$callback = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.cancelled = true;
            this.val$strategy.onAnimationCancel();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$strategy.onAnimationEnd();
            if (this.cancelled == true) goto L6;
            this.val$strategy.onChange(this.val$callback);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            this.val$strategy.onAnimationStart(r2);
            this.cancelled = false;
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$6, reason: invalid class name */
    public class AnonymousClass6 extends Property<View, Float> {
        public AnonymousClass6(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(View r1) {
            return Float.valueOf(r1.getLayoutParams().width);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r1, Float r2) {
            ViewGroup.LayoutParams r0 = r1.getLayoutParams();
            r0.width = r2.intValue();
            r1.requestLayout();
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, Float r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$7, reason: invalid class name */
    public class AnonymousClass7 extends Property<View, Float> {
        public AnonymousClass7(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(View r1) {
            return Float.valueOf(r1.getLayoutParams().height);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r1, Float r2) {
            ViewGroup.LayoutParams r0 = r1.getLayoutParams();
            r0.height = r2.intValue();
            r1.requestLayout();
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, Float r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$8, reason: invalid class name */
    public class AnonymousClass8 extends Property<View, Float> {
        public AnonymousClass8(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(View r1) {
            return Float.valueOf(r1.getPaddingStart());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r3, Float r4) {
            r3.setPaddingRelative(r4.intValue(), r3.getPaddingTop(), r3.getPaddingEnd(), r3.getPaddingBottom());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, Float r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$9, reason: invalid class name */
    public class AnonymousClass9 extends Property<View, Float> {
        public AnonymousClass9(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(View r1) {
            return Float.valueOf(r1.getPaddingEnd());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r3, Float r4) {
            r3.setPaddingRelative(r3.getPaddingStart(), r3.getPaddingTop(), r4.intValue(), r3.getPaddingBottom());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, Float r2) {
            set2(r1, r2);
        }
    }

    public class ChangeSizeStrategy extends BaseMotionStrategy {
        private final boolean extending;
        private final Size size;
        final /* synthetic */ ExtendedFloatingActionButton this$0;

        public ChangeSizeStrategy(ExtendedFloatingActionButton r1, AnimatorTracker r2, Size r3, boolean r4) {
            this.this$0 = r1;
            super(r1, r2);
            this.size = r3;
            this.extending = r4;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public AnimatorSet createAnimator() {
            MotionSpec r0 = getCurrentMotionSpec();
            if (r0.hasPropertyValues("width") == false) goto L6;
            PropertyValuesHolder[] r2 = r0.getPropertyValues("width");
            r2[0].setFloatValues(new float[]{this.this$0.getWidth(), this.size.getWidth()});
            r0.setPropertyValues("width", r2);
        L6:
            if (r0.hasPropertyValues("height") == false) goto L9;
            PropertyValuesHolder[] r22 = r0.getPropertyValues("height");
            r22[0].setFloatValues(new float[]{this.this$0.getHeight(), this.size.getHeight()});
            r0.setPropertyValues("height", r22);
        L9:
            if (r0.hasPropertyValues("paddingStart") == false) goto L12;
            PropertyValuesHolder[] r23 = r0.getPropertyValues("paddingStart");
            r23[0].setFloatValues(new float[]{this.this$0.getPaddingStart(), this.size.getPaddingStart()});
            r0.setPropertyValues("paddingStart", r23);
        L12:
            if (r0.hasPropertyValues("paddingEnd") == false) goto L15;
            PropertyValuesHolder[] r24 = r0.getPropertyValues("paddingEnd");
            r24[0].setFloatValues(new float[]{this.this$0.getPaddingEnd(), this.size.getPaddingEnd()});
            r0.setPropertyValues("paddingEnd", r24);
        L15:
            if (r0.hasPropertyValues("labelOpacity") == false) goto L25;
            PropertyValuesHolder[] r25 = r0.getPropertyValues("labelOpacity");
            int r6 = Color.alpha(this.this$0.getCurrentOriginalTextColor());
            int r7 = Color.alpha(this.this$0.getCurrentTextColor());
            float r8 = 0.0f;
            if (r6 == 0) goto L19;
            float r72 = r7 / r6;
        L21:
            if (this.extending == false) goto L23;
            r8 = 1.0f;
        L23:
            r25[0].setFloatValues(new float[]{r72, r8});
            r0.setPropertyValues("labelOpacity", r25);
            goto L25
        L19:
            r72 = 0.0f;
        L25:
            return super.createAnimator(r0);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            if (this.extending == false) goto L7;
            return R.animator.mtrl_extended_fab_change_size_expand_motion_spec;
        L7:
            return R.animator.mtrl_extended_fab_change_size_collapse_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.access$702(this.this$0, false);
            this.this$0.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams r0 = this.this$0.getLayoutParams();
            if (r0 != null) goto L5;
            return;
        L5:
            r0.width = this.size.getLayoutParams().width;
            r0.height = this.size.getLayoutParams().height;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator r2) {
            super.onAnimationStart(r2);
            ExtendedFloatingActionButton.access$502(this.this$0, this.extending);
            ExtendedFloatingActionButton.access$702(this.this$0, true);
            this.this$0.setHorizontallyScrolling(true);
            ExtendedFloatingActionButton.access$600(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback r2) {
            if (r2 != null) goto L4;
            return;
        L4:
            boolean r0 = this.extending;
            ExtendedFloatingActionButton r1 = this.this$0;
            if (r0 == false) goto L8;
            r2.onExtended(r1);
            return;
        L8:
            r2.onShrunken(r1);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            ExtendedFloatingActionButton.access$502(this.this$0, this.extending);
            ViewGroup.LayoutParams r0 = this.this$0.getLayoutParams();
            if (r0 != null) goto L6;
            return;
        L6:
            if (this.extending == true) goto L8;
            ExtendedFloatingActionButton.access$302(this.this$0, r0.width);
            ExtendedFloatingActionButton.access$202(this.this$0, r0.height);
        L8:
            r0.width = this.size.getLayoutParams().width;
            r0.height = this.size.getLayoutParams().height;
            boolean r02 = this.extending;
            ExtendedFloatingActionButton r1 = this.this$0;
            if (r02 == false) goto L12;
            r1.silentlyUpdateTextColor(r1.originalTextCsl);
        L16:
            this.this$0.setPaddingRelative(this.size.getPaddingStart(), this.this$0.getPaddingTop(), this.size.getPaddingEnd(), this.this$0.getPaddingBottom());
            this.this$0.requestLayout();
            ExtendedFloatingActionButton.access$600(this.this$0);
            return;
        L12:
            if (r1.getText() == null) goto L16;
            if (this.this$0.getText() == _UrlKt.FRAGMENT_ENCODE_SET) goto L16;
            this.this$0.silentlyUpdateTextColor(ColorStateList.valueOf(0));
            goto L16
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            if (this.extending != ExtendedFloatingActionButton.access$500(this.this$0)) goto L5;
            return true;
        L5:
            if (this.this$0.getIcon() != null) goto L7;
            return true;
        L7:
            if (TextUtils.isEmpty(this.this$0.getText()) == true) goto L14;
            return false;
        L14:
            return true;
        }
    }

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private static final boolean AUTO_HIDE_DEFAULT = false;
        private static final boolean AUTO_SHRINK_DEFAULT = true;
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private OnChangedCallback internalAutoHideCallback;
        private OnChangedCallback internalAutoShrinkCallback;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context r2, AttributeSet r3) {
            super(r2, r3);
            TypedArray r22 = r2.obtainStyledAttributes(r3, R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = r22.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = r22.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            r22.recycle();
        }

        private static boolean isBottomSheet(View r1) {
            ViewGroup.LayoutParams r12 = r1.getLayoutParams();
            if ((r12 instanceof CoordinatorLayout.LayoutParams) == true) goto L5;
            return false;
        L5:
            return ((CoordinatorLayout.LayoutParams) r12).getBehavior() instanceof BottomSheetBehavior;
        }

        private boolean shouldUpdateVisibility(View r3, ExtendedFloatingActionButton r4) {
            CoordinatorLayout.LayoutParams r42 = (CoordinatorLayout.LayoutParams) r4.getLayoutParams();
            if (this.autoHideEnabled == true) goto L8;
            if (this.autoShrinkEnabled == true) goto L8;
            return false;
        L8:
            if (r42.getAnchorId() == r3.getId()) goto L10;
            return false;
        L10:
            return true;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout r2, AppBarLayout r3, ExtendedFloatingActionButton r4) {
            if (shouldUpdateVisibility(r3, r4) == true) goto L7;
            return false;
        L7:
            if (this.tmpRect != null) goto L9;
            this.tmpRect = new Rect();
        L9:
            Rect r0 = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(r2, r3, r0);
            if (r0.bottom > r3.getMinimumHeightForVisibleOverlappingContent()) goto L12;
            shrinkOrHide(r4);
            return true;
        L12:
            extendOrShow(r4);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View r3, ExtendedFloatingActionButton r4) {
            if (shouldUpdateVisibility(r3, r4) == true) goto L6;
            return false;
        L6:
            CoordinatorLayout.LayoutParams r0 = (CoordinatorLayout.LayoutParams) r4.getLayoutParams();
            if (r3.getTop() >= ((r4.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) r0).topMargin)) goto L9;
            shrinkOrHide(r4);
            return true;
        L9:
            extendOrShow(r4);
            return true;
        }

        public void extendOrShow(ExtendedFloatingActionButton r2) {
            boolean r0 = this.autoShrinkEnabled;
            if (r0 == false) goto L5;
            OnChangedCallback r1 = this.internalAutoShrinkCallback;
        L6:
            if (r0 == false) goto L8;
            int r02 = 3;
        L9:
            ExtendedFloatingActionButton.access$400(r2, r02, r1);
            return;
        L8:
            r02 = 0;
            goto L9
        L5:
            r1 = this.internalAutoHideCallback;
            goto L6
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout r1, View r2, Rect r3) {
            return getInsetDodgeRect(r1, (ExtendedFloatingActionButton) r2, r3);
        }

        public boolean getInsetDodgeRect(CoordinatorLayout r1, ExtendedFloatingActionButton r2, Rect r3) {
            return super.getInsetDodgeRect(r1, r2, r3);
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        public boolean isAutoShrinkEnabled() {
            return this.autoShrinkEnabled;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams r1) {
            if (r1.dodgeInsetEdges != 0) goto L6;
            r1.dodgeInsetEdges = 80;
            return;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout r1, View r2, View r3) {
            return onDependentViewChanged(r1, (ExtendedFloatingActionButton) r2, r3);
        }

        public boolean onDependentViewChanged(CoordinatorLayout r2, ExtendedFloatingActionButton r3, View r4) {
            if ((r4 instanceof AppBarLayout) == false) goto L6;
            updateFabVisibilityForAppBarLayout(r2, (AppBarLayout) r4, r3);
            return false;
        L6:
            if (isBottomSheet(r4) == false) goto L11;
            updateFabVisibilityForBottomSheet(r4, r3);
            return false;
        L11:
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout r1, View r2, int r3) {
            return onLayoutChild(r1, (ExtendedFloatingActionButton) r2, r3);
        }

        public boolean onLayoutChild(CoordinatorLayout r6, ExtendedFloatingActionButton r7, int r8) {
            List<View> r0 = r6.getDependencies(r7);
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L15;
            View r3 = r0.get(r2);
            if ((r3 instanceof AppBarLayout) == false) goto L10;
            if (updateFabVisibilityForAppBarLayout(r6, (AppBarLayout) r3, r7) == true) goto L15;
        L14:
            r2 = r2 + 1;
            goto L3
        L10:
            if (isBottomSheet(r3) == false) goto L14;
            if (updateFabVisibilityForBottomSheet(r3, r7) == false) goto L14;
        L15:
            r6.onLayoutChild(r7, r8);
            return true;
        }

        public void setAutoHideEnabled(boolean r1) {
            this.autoHideEnabled = r1;
        }

        public void setAutoShrinkEnabled(boolean r1) {
            this.autoShrinkEnabled = r1;
        }

        public void setInternalAutoHideCallback(OnChangedCallback r1) {
            this.internalAutoHideCallback = r1;
        }

        public void setInternalAutoShrinkCallback(OnChangedCallback r1) {
            this.internalAutoShrinkCallback = r1;
        }

        public void shrinkOrHide(ExtendedFloatingActionButton r2) {
            boolean r0 = this.autoShrinkEnabled;
            if (r0 == false) goto L5;
            OnChangedCallback r1 = this.internalAutoShrinkCallback;
        L6:
            if (r0 == false) goto L8;
            int r02 = 2;
        L9:
            ExtendedFloatingActionButton.access$400(r2, r02, r1);
            return;
        L8:
            r02 = 1;
            goto L9
        L5:
            r1 = this.internalAutoHideCallback;
            goto L6
        }
    }

    public class HideStrategy extends BaseMotionStrategy {
        private boolean isCancelled;
        final /* synthetic */ ExtendedFloatingActionButton this$0;

        public HideStrategy(ExtendedFloatingActionButton r1, AnimatorTracker r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            return R.animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationCancel() {
            super.onAnimationCancel();
            this.isCancelled = true;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.access$802(this.this$0, 0);
            if (this.isCancelled == true) goto L6;
            this.this$0.setVisibility(8);
            return;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator r2) {
            super.onAnimationStart(r2);
            this.isCancelled = false;
            this.this$0.setVisibility(0);
            ExtendedFloatingActionButton.access$802(this.this$0, 1);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback r1) {
            if (r1 == null) goto L5;
            r1.onHidden(this.this$0);
            return;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            this.this$0.setVisibility(8);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.access$1000(this.this$0);
        }
    }

    public static abstract class OnChangedCallback {
        public OnChangedCallback() {
        }

        public void onExtended(ExtendedFloatingActionButton r1) {
        }

        public void onHidden(ExtendedFloatingActionButton r1) {
        }

        public void onShown(ExtendedFloatingActionButton r1) {
        }

        public void onShrunken(ExtendedFloatingActionButton r1) {
        }
    }

    public class ShowStrategy extends BaseMotionStrategy {
        final /* synthetic */ ExtendedFloatingActionButton this$0;

        public ShowStrategy(ExtendedFloatingActionButton r1, AnimatorTracker r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public int getDefaultMotionSpecResource() {
            return R.animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationEnd() {
            super.onAnimationEnd();
            ExtendedFloatingActionButton.access$802(this.this$0, 0);
        }

        @Override // com.google.android.material.floatingactionbutton.BaseMotionStrategy, com.google.android.material.floatingactionbutton.MotionStrategy
        public void onAnimationStart(Animator r2) {
            super.onAnimationStart(r2);
            this.this$0.setVisibility(0);
            ExtendedFloatingActionButton.access$802(this.this$0, 2);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void onChange(OnChangedCallback r1) {
            if (r1 == null) goto L5;
            r1.onShown(this.this$0);
            return;
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public void performNow() {
            this.this$0.setVisibility(0);
            this.this$0.setAlpha(1.0f);
            this.this$0.setScaleY(1.0f);
            this.this$0.setScaleX(1.0f);
        }

        @Override // com.google.android.material.floatingactionbutton.MotionStrategy
        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.access$900(this.this$0);
        }
    }

    public interface Size {
        int getHeight();

        ViewGroup.LayoutParams getLayoutParams();

        int getPaddingEnd();

        int getPaddingStart();

        int getWidth();
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
        WIDTH = new AnonymousClass6(Float.class, "width");
        HEIGHT = new AnonymousClass7(Float.class, "height");
        PADDING_START = new AnonymousClass8(Float.class, "paddingStart");
        PADDING_END = new AnonymousClass9(Float.class, "paddingEnd");
    }

    public ExtendedFloatingActionButton(Context r2) {
        this(r2, null);
    }

    public ExtendedFloatingActionButton(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.extendedFloatingActionButtonStyle);
    }

    public ExtendedFloatingActionButton(Context r18, AttributeSet r19, int r20) {
        int r5 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r18, r19, r20, r5), r19, r20);
        this.animState = 0;
        this.animationEnabled = true;
        AnimatorTracker r1 = new AnimatorTracker();
        this.changeVisibilityTracker = r1;
        ShowStrategy r9 = new ShowStrategy(this, r1);
        this.showStrategy = r9;
        HideStrategy r10 = new HideStrategy(this, r1);
        this.hideStrategy = r10;
        this.isExtended = true;
        this.isTransforming = false;
        this.animateShowBeforeLayout = false;
        Context r12 = getContext();
        this.behavior = new ExtendedFloatingActionButtonBehavior(r12, r19);
        TypedArray r3 = ThemeEnforcement.obtainStyledAttributes(r12, r19, R.styleable.ExtendedFloatingActionButton, r20, r5, new int[0]);
        MotionSpec r6 = MotionSpec.createFromAttribute(r12, r3, R.styleable.ExtendedFloatingActionButton_showMotionSpec);
        MotionSpec r11 = MotionSpec.createFromAttribute(r12, r3, R.styleable.ExtendedFloatingActionButton_hideMotionSpec);
        MotionSpec r122 = MotionSpec.createFromAttribute(r12, r3, R.styleable.ExtendedFloatingActionButton_extendMotionSpec);
        MotionSpec r13 = MotionSpec.createFromAttribute(r12, r3, R.styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        this.collapsedSize = r3.getDimensionPixelSize(R.styleable.ExtendedFloatingActionButton_collapsedSize, -1);
        int r14 = r3.getInt(R.styleable.ExtendedFloatingActionButton_extendStrategy, 1);
        this.extendStrategyType = r14;
        this.extendedPaddingStart = getPaddingStart();
        this.extendedPaddingEnd = getPaddingEnd();
        AnimatorTracker r15 = new AnimatorTracker();
        ChangeSizeStrategy r7 = new ChangeSizeStrategy(this, r15, getSizeFromExtendStrategyType(r14), true);
        this.extendStrategy = r7;
        ChangeSizeStrategy r8 = new ChangeSizeStrategy(this, r15, new AnonymousClass1(this), false);
        this.shrinkStrategy = r8;
        r9.setMotionSpec(r6);
        r10.setMotionSpec(r11);
        r7.setMotionSpec(r122);
        r8.setMotionSpec(r13);
        r3.recycle();
        setShapeAppearanceModel(ShapeAppearanceModel.builder(r12, r19, r20, r5, ShapeAppearanceModel.PILL).build());
        saveOriginalTextCsl();
    }

    public static /* synthetic */ int access$000(ExtendedFloatingActionButton r0) {
        return r0.extendedPaddingStart;
    }

    public static /* synthetic */ int access$100(ExtendedFloatingActionButton r0) {
        return r0.extendedPaddingEnd;
    }

    public static /* synthetic */ boolean access$1000(ExtendedFloatingActionButton r0) {
        return r0.isOrWillBeHidden();
    }

    public static /* synthetic */ int access$200(ExtendedFloatingActionButton r0) {
        return r0.originalHeight;
    }

    public static /* synthetic */ int access$202(ExtendedFloatingActionButton r0, int r1) {
        r0.originalHeight = r1;
        return r1;
    }

    public static /* synthetic */ int access$300(ExtendedFloatingActionButton r0) {
        return r0.originalWidth;
    }

    public static /* synthetic */ int access$302(ExtendedFloatingActionButton r0, int r1) {
        r0.originalWidth = r1;
        return r1;
    }

    public static /* synthetic */ void access$400(ExtendedFloatingActionButton r0, int r1, OnChangedCallback r2) {
        r0.performMotion(r1, r2);
    }

    public static /* synthetic */ boolean access$500(ExtendedFloatingActionButton r0) {
        return r0.isExtended;
    }

    public static /* synthetic */ boolean access$502(ExtendedFloatingActionButton r0, boolean r1) {
        r0.isExtended = r1;
        return r1;
    }

    public static /* synthetic */ void access$600(ExtendedFloatingActionButton r0) {
        r0.updateTooltip();
    }

    public static /* synthetic */ boolean access$702(ExtendedFloatingActionButton r0, boolean r1) {
        r0.isTransforming = r1;
        return r1;
    }

    public static /* synthetic */ int access$802(ExtendedFloatingActionButton r0, int r1) {
        r0.animState = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$900(ExtendedFloatingActionButton r0) {
        return r0.isOrWillBeShown();
    }

    private Size getSizeFromExtendStrategyType(int r4) {
        AnonymousClass2 r0 = new AnonymousClass2(this);
        AnonymousClass3 r1 = new AnonymousClass3(this, r0);
        AnonymousClass4 r2 = new AnonymousClass4(this, r1, r0);
        if (r4 != 1) goto L5;
        return r0;
    L5:
        if (r4 == 2) goto L7;
        return r2;
    L7:
        return r1;
    }

    private boolean isOrWillBeHidden() {
        int r0 = getVisibility();
        int r3 = this.animState;
        if (r0 != 0) goto L8;
        if (r3 != 1) goto L6;
        return true;
    L6:
        return false;
    L8:
        if (r3 == 2) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isOrWillBeShown() {
        int r0 = getVisibility();
        int r3 = this.animState;
        if (r0 != 0) goto L5;
        if (r3 == 1) goto L10;
        return true;
    L10:
        return false;
    L5:
        if (r3 != 2) goto L7;
        return true;
    L7:
        return false;
    }

    private void performMotion(int r4, OnChangedCallback r5) {
        if (r4 != 0) goto L5;
        MotionStrategy r1 = this.showStrategy;
    L16:
        if (r1.shouldCancel() == false) goto L19;
        return;
    L19:
        if (shouldAnimateVisibilityChange() == true) goto L22;
        r1.performNow();
        r1.onChange(r5);
        return;
    L22:
        if (r4 != 2) goto L27;
        ViewGroup.LayoutParams r42 = getLayoutParams();
        if (r42 == null) goto L26;
        this.originalWidth = r42.width;
        this.originalHeight = r42.height;
        goto L27
    L26:
        this.originalWidth = getWidth();
        this.originalHeight = getHeight();
    L27:
        measure(0, 0);
        AnimatorSet r43 = r1.createAnimator();
        r43.addListener(new AnonymousClass5(this, r1, r5));
        Iterator<Animator.AnimatorListener> r3 = r1.getListeners().iterator();
    L29:
        if (r3.hasNext() == false) goto L31;
        r43.addListener(r3.next());
        goto L29
    L31:
        r43.start();
        return;
    L5:
        if (r4 == 1) goto L13;
        if (r4 != 2) goto L8;
        r1 = this.shrinkStrategy;
        goto L16
    L8:
        if (r4 != 3) goto L10;
        r1 = this.extendStrategy;
        goto L16
    L10:
        JsonArray$$ExternalSyntheticBUOutline0.m("Unknown strategy type: ", r4);
        return;
    L13:
        r1 = this.hideStrategy;
        goto L16
    }

    private void saveOriginalTextCsl() {
        this.originalTextCsl = getTextColors();
    }

    private boolean shouldAnimateVisibilityChange() {
        if (this.animationEnabled == true) goto L5;
        return false;
    L5:
        if (isLaidOut() == true) goto L11;
        if (isOrWillBeShown() == false) goto L9;
        return false;
    L9:
        if (this.animateShowBeforeLayout == true) goto L11;
        return false;
    L11:
        if (isInEditMode() == true) goto L18;
        return true;
    L18:
        return false;
    }

    private void updateTooltip() {
        if (Build.VERSION.SDK_INT >= 26) goto L6;
        return;
    L6:
        if (this.isExtended == false) goto L8;
    L13:
        CharSequence r0 = null;
    L15:
        if (TextUtils.equals(getTooltipText(), r0) == true) goto L18;
        setTooltipText(r0);
        return;
    L18:
        return;
    L8:
        if (isClickable() == false) goto L13;
        r0 = getText();
        if (TextUtils.isEmpty(r0) == false) goto L15;
        r0 = getContentDescription();
        goto L15
    }

    public void addOnExtendAnimationListener(Animator.AnimatorListener r1) {
        this.extendStrategy.addAnimationListener(r1);
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener r1) {
        this.hideStrategy.addAnimationListener(r1);
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener r1) {
        this.showStrategy.addAnimationListener(r1);
    }

    public void addOnShrinkAnimationListener(Animator.AnimatorListener r1) {
        this.shrinkStrategy.addAnimationListener(r1);
    }

    public void extend() {
        performMotion(3, null);
    }

    public void extend(OnChangedCallback r2) {
        performMotion(3, r2);
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "com.google.android.material.floatingactionbutton.FloatingActionButton";
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    public int getCollapsedSize() {
        int r0 = this.collapsedSize;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        return (Math.min(getPaddingStart(), getPaddingEnd()) * 2) + getIconSize();
    }

    public int getCurrentOriginalTextColor() {
        return this.originalTextCsl.getColorForState(getDrawableState(), 0);
    }

    public MotionSpec getExtendMotionSpec() {
        return this.extendStrategy.getMotionSpec();
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideStrategy.getMotionSpec();
    }

    public ColorStateList getOriginalTextColor() {
        return this.originalTextCsl;
    }

    public MotionSpec getShowMotionSpec() {
        return this.showStrategy.getMotionSpec();
    }

    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkStrategy.getMotionSpec();
    }

    public void hide() {
        performMotion(1, null);
    }

    public void hide(OnChangedCallback r2) {
        performMotion(1, r2);
    }

    public boolean isAnimationEnabled() {
        return this.animationEnabled;
    }

    public final boolean isExtended() {
        return this.isExtended;
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended == true) goto L5;
    L10:
        updateTooltip();
        return;
    L5:
        if (TextUtils.isEmpty(getText()) == false) goto L10;
        if (getIcon() == null) goto L10;
        this.isExtended = false;
        this.shrinkStrategy.performNow();
    }

    public void removeOnExtendAnimationListener(Animator.AnimatorListener r1) {
        this.extendStrategy.removeAnimationListener(r1);
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener r1) {
        this.hideStrategy.removeAnimationListener(r1);
    }

    public void removeOnShowAnimationListener(Animator.AnimatorListener r1) {
        this.showStrategy.removeAnimationListener(r1);
    }

    public void removeOnShrinkAnimationListener(Animator.AnimatorListener r1) {
        this.shrinkStrategy.removeAnimationListener(r1);
    }

    public void setAnimateShowBeforeLayout(boolean r1) {
        this.animateShowBeforeLayout = r1;
    }

    public void setAnimationEnabled(boolean r1) {
        this.animationEnabled = r1;
    }

    @Override // android.view.View
    public void setClickable(boolean r1) {
        super.setClickable(r1);
        updateTooltip();
    }

    public void setCollapsedSize(int r1) {
        this.collapsedSize = r1;
    }

    @Override // android.view.View
    public void setContentDescription(CharSequence r1) {
        super.setContentDescription(r1);
        updateTooltip();
    }

    public void setExtendMotionSpec(MotionSpec r1) {
        this.extendStrategy.setMotionSpec(r1);
    }

    public void setExtendMotionSpecResource(int r2) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), r2));
    }

    public void setExtended(boolean r2) {
        if (this.isExtended == r2) goto L13;
        if (r2 == false) goto L7;
        MotionStrategy r1 = this.extendStrategy;
    L9:
        if (r1.shouldCancel() == false) goto L11;
        return;
    L11:
        r1.performNow();
        return;
    L7:
        r1 = this.shrinkStrategy;
        goto L9
    }

    public void setHideMotionSpec(MotionSpec r1) {
        this.hideStrategy.setMotionSpec(r1);
    }

    public void setHideMotionSpecResource(int r2) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), r2));
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int r1, int r2, int r3, int r4) {
        super.setPadding(r1, r2, r3, r4);
        if (this.isExtended == true) goto L5;
        return;
    L5:
        if (this.isTransforming == true) goto L9;
        this.extendedPaddingStart = getPaddingStart();
        this.extendedPaddingEnd = getPaddingEnd();
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int r1, int r2, int r3, int r4) {
        super.setPaddingRelative(r1, r2, r3, r4);
        if (this.isExtended == true) goto L5;
        return;
    L5:
        if (this.isTransforming == true) goto L9;
        this.extendedPaddingStart = r1;
        this.extendedPaddingEnd = r3;
        return;
    }

    public void setShowMotionSpec(MotionSpec r1) {
        this.showStrategy.setMotionSpec(r1);
    }

    public void setShowMotionSpecResource(int r2) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), r2));
    }

    public void setShrinkMotionSpec(MotionSpec r1) {
        this.shrinkStrategy.setMotionSpec(r1);
    }

    public void setShrinkMotionSpecResource(int r2) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), r2));
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView
    public void setText(CharSequence r1, TextView.BufferType r2) {
        super.setText(r1, r2);
        updateTooltip();
    }

    @Override // android.widget.TextView
    public void setTextColor(int r1) {
        super.setTextColor(r1);
        saveOriginalTextCsl();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList r1) {
        super.setTextColor(r1);
        saveOriginalTextCsl();
    }

    public void show() {
        performMotion(0, null);
    }

    public void show(OnChangedCallback r2) {
        performMotion(0, r2);
    }

    public void shrink() {
        performMotion(2, null);
    }

    public void shrink(OnChangedCallback r2) {
        performMotion(2, r2);
    }

    public void silentlyUpdateTextColor(ColorStateList r1) {
        super.setTextColor(r1);
    }
}
