package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatImageHelper;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.TransformationCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.expandable.ExpandableTransformationWidget;
import com.google.android.material.expandable.ExpandableWidgetHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButtonImpl;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.stateful.ExtendableSavedState;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes5.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements ExpandableTransformationWidget, Shapeable, CoordinatorLayout.AttachedBehavior {
    static final String ACCESSIBIILTY_FAB_ROLE = "com.google.android.material.floatingactionbutton.FloatingActionButton";
    private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
    private static final int DEF_STYLE_RES = 0;
    private static final String EXPANDABLE_WIDGET_HELPER_KEY = "expandableWidgetHelper";
    private static final String LOG_TAG = "FloatingActionButton";
    public static final int NO_CUSTOM_SIZE = 0;
    public static final int SIZE_AUTO = -1;
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL = 0;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private int borderWidth;
    boolean compatPadding;
    private int customSize;
    private final ExpandableWidgetHelper expandableWidgetHelper;
    private final AppCompatImageHelper imageHelper;
    private PorterDuff.Mode imageMode;
    private int imagePadding;
    private ColorStateList imageTint;
    private FloatingActionButtonImpl impl;
    private int maxImageSize;
    private ColorStateList rippleColor;
    final Rect shadowPadding;
    private int size;
    private final Rect touchArea;

    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButton$1, reason: invalid class name */
    public class AnonymousClass1 implements FloatingActionButtonImpl.InternalVisibilityChangedListener {
        final /* synthetic */ FloatingActionButton this$0;
        final /* synthetic */ OnVisibilityChangedListener val$listener;

        public AnonymousClass1(FloatingActionButton r1, OnVisibilityChangedListener r2) {
            this.this$0 = r1;
            this.val$listener = r2;
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalVisibilityChangedListener
        public void onHidden() {
            this.val$listener.onHidden(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalVisibilityChangedListener
        public void onShown() {
            this.val$listener.onShown(this.this$0);
        }
    }

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private static final boolean AUTO_HIDE_DEFAULT = true;
        private boolean autoHideEnabled;
        private OnVisibilityChangedListener internalAutoHideListener;
        private Rect tmpRect;

        public BaseBehavior() {
            this.autoHideEnabled = true;
        }

        public BaseBehavior(Context r2, AttributeSet r3) {
            super(r2, r3);
            TypedArray r22 = r2.obtainStyledAttributes(r3, R.styleable.FloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = r22.getBoolean(R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            r22.recycle();
        }

        private boolean ignoreUpdateVisibility(View r3, FloatingActionButton r4) {
            CoordinatorLayout.LayoutParams r0 = (CoordinatorLayout.LayoutParams) r4.getLayoutParams();
            if (this.autoHideEnabled == true) goto L6;
            return true;
        L6:
            if (r0.getAnchorId() == r3.getId()) goto L9;
            return true;
        L9:
            if (r4.getUserSetVisibility() == 0) goto L11;
            return true;
        L11:
            return false;
        }

        private static boolean isBottomSheet(View r1) {
            ViewGroup.LayoutParams r12 = r1.getLayoutParams();
            if ((r12 instanceof CoordinatorLayout.LayoutParams) == true) goto L5;
            return false;
        L5:
            return ((CoordinatorLayout.LayoutParams) r12).getBehavior() instanceof BottomSheetBehavior;
        }

        private void offsetIfNeeded(CoordinatorLayout r6, FloatingActionButton r7) {
            Rect r5 = r7.shadowPadding;
            if (r5.centerX() > 0) goto L5;
            return;
        L5:
            if (r5.centerY() <= 0) goto L25;
            CoordinatorLayout.LayoutParams r0 = (CoordinatorLayout.LayoutParams) r7.getLayoutParams();
            int r3 = 0;
            if (r7.getRight() < (r6.getWidth() - ((ViewGroup.MarginLayoutParams) r0).rightMargin)) goto L10;
            int r1 = r5.right;
        L14:
            if (r7.getBottom() < (r6.getHeight() - ((ViewGroup.MarginLayoutParams) r0).bottomMargin)) goto L17;
            r3 = r5.bottom;
        L19:
            if (r3 == 0) goto L21;
            ViewCompat.offsetTopAndBottom(r7, r3);
        L21:
            if (r1 == 0) goto L26;
            ViewCompat.offsetLeftAndRight(r7, r1);
            return;
        L26:
            return;
        L17:
            if (r7.getTop() > ((ViewGroup.MarginLayoutParams) r0).topMargin) goto L19;
            r3 = -r5.top;
            goto L19
        L10:
            if (r7.getLeft() > ((ViewGroup.MarginLayoutParams) r0).leftMargin) goto L12;
            r1 = -r5.left;
            goto L14
        L12:
            r1 = 0;
            goto L14
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout r3, AppBarLayout r4, FloatingActionButton r5) {
            if (ignoreUpdateVisibility(r4, r5) == false) goto L6;
            return false;
        L6:
            if (this.tmpRect != null) goto L8;
            this.tmpRect = new Rect();
        L8:
            Rect r0 = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(r3, r4, r0);
            int r32 = r0.bottom;
            int r42 = r4.getMinimumHeightForVisibleOverlappingContent();
            OnVisibilityChangedListener r2 = this.internalAutoHideListener;
            if (r32 > r42) goto L11;
            r5.hide(r2, false);
            return true;
        L11:
            r5.show(r2, false);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View r4, FloatingActionButton r5) {
            if (ignoreUpdateVisibility(r4, r5) == false) goto L5;
            return false;
        L5:
            CoordinatorLayout.LayoutParams r0 = (CoordinatorLayout.LayoutParams) r5.getLayoutParams();
            int r42 = r4.getTop();
            int r2 = (r5.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) r0).topMargin;
            OnVisibilityChangedListener r3 = this.internalAutoHideListener;
            if (r42 >= r2) goto L8;
            r5.hide(r3, false);
            return true;
        L8:
            r5.show(r3, false);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout r1, View r2, Rect r3) {
            return getInsetDodgeRect(r1, (FloatingActionButton) r2, r3);
        }

        public boolean getInsetDodgeRect(CoordinatorLayout r4, FloatingActionButton r5, Rect r6) {
            Rect r3 = r5.shadowPadding;
            r6.set(r5.getLeft() + r3.left, r5.getTop() + r3.top, r5.getRight() - r3.right, r5.getBottom() - r3.bottom);
            return true;
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams r1) {
            if (r1.dodgeInsetEdges != 0) goto L6;
            r1.dodgeInsetEdges = 80;
            return;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout r1, View r2, View r3) {
            return onDependentViewChanged(r1, (FloatingActionButton) r2, r3);
        }

        public boolean onDependentViewChanged(CoordinatorLayout r2, FloatingActionButton r3, View r4) {
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
            return onLayoutChild(r1, (FloatingActionButton) r2, r3);
        }

        public boolean onLayoutChild(CoordinatorLayout r6, FloatingActionButton r7, int r8) {
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
            offsetIfNeeded(r6, r7);
            return true;
        }

        public void setAutoHideEnabled(boolean r1) {
            this.autoHideEnabled = r1;
        }

        public void setInternalAutoHideListener(OnVisibilityChangedListener r1) {
            this.internalAutoHideListener = r1;
        }
    }

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        public Behavior(Context r1, AttributeSet r2) {
            super(r1, r2);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout r1, FloatingActionButton r2, Rect r3) {
            return super.getInsetDodgeRect(r1, r2, r3);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean isAutoHideEnabled() {
            return super.isAutoHideEnabled();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams r1) {
            super.onAttachedToLayoutParams(r1);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout r1, FloatingActionButton r2, View r3) {
            return super.onDependentViewChanged(r1, r2, r3);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout r1, FloatingActionButton r2, int r3) {
            return super.onLayoutChild(r1, r2, r3);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ void setAutoHideEnabled(boolean r1) {
            super.setAutoHideEnabled(r1);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ void setInternalAutoHideListener(OnVisibilityChangedListener r1) {
            super.setInternalAutoHideListener(r1);
        }
    }

    public static abstract class OnVisibilityChangedListener {
        public OnVisibilityChangedListener() {
        }

        public void onHidden(FloatingActionButton r1) {
        }

        public void onShown(FloatingActionButton r1) {
        }
    }

    public class ShadowDelegateImpl implements ShadowViewDelegate {
        final /* synthetic */ FloatingActionButton this$0;

        public ShadowDelegateImpl(FloatingActionButton r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public float getRadius() {
            return this.this$0.getSizeDimension() / 2.0f;
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public boolean isCompatPaddingEnabled() {
            return this.this$0.compatPadding;
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public void setBackgroundDrawable(Drawable r1) {
            if (r1 == null) goto L5;
            FloatingActionButton.access$101(this.this$0, r1);
            return;
        }

        @Override // com.google.android.material.shadow.ShadowViewDelegate
        public void setShadowPadding(int r3, int r4, int r5, int r6) {
            this.this$0.shadowPadding.set(r3, r4, r5, r6);
            FloatingActionButton r0 = this.this$0;
            r0.setPadding(r3 + FloatingActionButton.access$000(r0), r4 + FloatingActionButton.access$000(this.this$0), r5 + FloatingActionButton.access$000(this.this$0), r6 + FloatingActionButton.access$000(this.this$0));
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Size {
    }

    public class TransformationCallbackWrapper<T extends FloatingActionButton> implements FloatingActionButtonImpl.InternalTransformationCallback {
        private final TransformationCallback<T> listener;
        final /* synthetic */ FloatingActionButton this$0;

        public TransformationCallbackWrapper(FloatingActionButton r1, TransformationCallback<T> r2) {
            this.this$0 = r1;
            this.listener = r2;
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof TransformationCallbackWrapper) == true) goto L5;
            return false;
        L5:
            if (((TransformationCallbackWrapper) r2).listener.equals(this.listener) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            return this.listener.hashCode();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalTransformationCallback
        public void onScaleChanged() {
            this.listener.onScaleChanged(this.this$0);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.InternalTransformationCallback
        public void onTranslationChanged() {
            this.listener.onTranslationChanged(this.this$0);
        }
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Design_FloatingActionButton;
    }

    public FloatingActionButton(Context r2) {
        this(r2, null);
    }

    public FloatingActionButton(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.floatingActionButtonStyle);
    }

    public FloatingActionButton(Context r11, AttributeSet r12, int r13) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r11, r12, r13, r4), r12, r13);
        this.shadowPadding = new Rect();
        this.touchArea = new Rect();
        Context r0 = getContext();
        TypedArray r122 = ThemeEnforcement.obtainStyledAttributes(r0, r12, R.styleable.FloatingActionButton, r13, r4, new int[0]);
        this.backgroundTint = MaterialResources.getColorStateList(r0, r122, R.styleable.FloatingActionButton_backgroundTint);
        this.backgroundTintMode = ViewUtils.parseTintMode(r122.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.rippleColor = MaterialResources.getColorStateList(r0, r122, R.styleable.FloatingActionButton_rippleColor);
        this.size = r122.getInt(R.styleable.FloatingActionButton_fabSize, -1);
        this.customSize = r122.getDimensionPixelSize(R.styleable.FloatingActionButton_fabCustomSize, 0);
        this.borderWidth = r122.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
        float r132 = r122.getDimension(R.styleable.FloatingActionButton_elevation, 0.0f);
        float r5 = r122.getDimension(R.styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float r2 = r122.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.compatPadding = r122.getBoolean(R.styleable.FloatingActionButton_useCompatPadding, false);
        int r6 = getResources().getDimensionPixelSize(R.dimen.mtrl_fab_min_touch_target);
        setMaxImageSize(r122.getDimensionPixelSize(R.styleable.FloatingActionButton_maxImageSize, 0));
        MotionSpec r7 = MotionSpec.createFromAttribute(r0, r122, R.styleable.FloatingActionButton_showMotionSpec);
        MotionSpec r8 = MotionSpec.createFromAttribute(r0, r122, R.styleable.FloatingActionButton_hideMotionSpec);
        ShapeAppearanceModel r02 = ShapeAppearanceModel.builder(r0, r12, r13, r4, ShapeAppearanceModel.PILL).build();
        boolean r112 = r122.getBoolean(R.styleable.FloatingActionButton_ensureMinTouchTargetSize, false);
        setEnabled(r122.getBoolean(R.styleable.FloatingActionButton_android_enabled, true));
        r122.recycle();
        AppCompatImageHelper r123 = new AppCompatImageHelper(this);
        this.imageHelper = r123;
        r123.loadFromAttributes(r12, r13);
        this.expandableWidgetHelper = new ExpandableWidgetHelper(this);
        getImpl().setShapeAppearance(r02);
        getImpl().initializeBackgroundDrawable(this.backgroundTint, this.backgroundTintMode, this.rippleColor, this.borderWidth);
        getImpl().setMinTouchTargetSize(r6);
        getImpl().setElevation(r132);
        getImpl().setHoveredFocusedTranslationZ(r5);
        getImpl().setPressedTranslationZ(r2);
        getImpl().setShowMotionSpec(r7);
        getImpl().setHideMotionSpec(r8);
        getImpl().setEnsureMinTouchTargetSize(r112);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public static /* synthetic */ int access$000(FloatingActionButton r0) {
        return r0.imagePadding;
    }

    public static /* synthetic */ void access$101(FloatingActionButton r0, Drawable r1) {
        super.setBackgroundDrawable(r1);
    }

    private FloatingActionButtonImpl getImpl() {
        if (this.impl != null) goto L6;
        this.impl = new FloatingActionButtonImpl(this, new ShadowDelegateImpl(this));
    L6:
        return this.impl;
    }

    private int getSizeDimension(int r4) {
        int r0 = this.customSize;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        Resources r02 = getResources();
        if (r4 == (-1)) goto L13;
        if (r4 == 1) goto L11;
        return r02.getDimensionPixelSize(R.dimen.design_fab_size_normal);
    L11:
        return r02.getDimensionPixelSize(R.dimen.design_fab_size_mini);
    L13:
        if (Math.max(r02.getConfiguration().screenWidthDp, r02.getConfiguration().screenHeightDp) >= AUTO_MINI_LARGEST_SCREEN_WIDTH) goto L17;
        return getSizeDimension(1);
    L17:
        return getSizeDimension(0);
    }

    private void getTouchTargetRect(Rect r1) {
        getMeasuredContentRect(r1);
        int r0 = -this.impl.getTouchTargetPadding();
        r1.inset(r0, r0);
    }

    private void offsetRectWithShadow(Rect r3) {
        int r0 = r3.left;
        Rect r2 = this.shadowPadding;
        r3.left = r0 + r2.left;
        r3.top += r2.top;
        r3.right -= r2.right;
        r3.bottom -= r2.bottom;
    }

    private void onApplySupportImageTint() {
        Drawable r0 = getDrawable();
        if (r0 != null) goto L5;
        return;
    L5:
        ColorStateList r1 = this.imageTint;
        if (r1 != null) goto L9;
        DrawableCompat.clearColorFilter(r0);
        return;
    L9:
        int r12 = r1.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode r4 = this.imageMode;
        if (r4 != null) goto L12;
        r4 = PorterDuff.Mode.SRC_IN;
    L12:
        r0.mutate().setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(r12, r4));
    }

    private void updateTooltip() {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        if (isClickable() == false) goto L7;
        CharSequence r0 = getContentDescription();
    L8:
        setTooltipText(r0);
        return;
    L7:
        r0 = null;
        goto L8
    }

    private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(OnVisibilityChangedListener r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        return new AnonymousClass1(this, r2);
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener r1) {
        getImpl().addOnHideAnimationListener(r1);
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener r1) {
        getImpl().addOnShowAnimationListener(r1);
    }

    public void addTransformationCallback(TransformationCallback<? extends FloatingActionButton> r3) {
        getImpl().addTransformationCallback(new TransformationCallbackWrapper(this, r3));
    }

    public void clearCustomSize() {
        setCustomSize(0);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
    }

    @Override // android.widget.ImageButton, android.widget.ImageView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return ACCESSIBIILTY_FAB_ROLE;
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.backgroundTint;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().getElevation();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().getHoveredFocusedTranslationZ();
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().getPressedTranslationZ();
    }

    public Drawable getContentBackground() {
        return getImpl().getContentBackground();
    }

    @Deprecated
    public boolean getContentRect(Rect r4) {
        if (isLaidOut() == false) goto L6;
        r4.set(0, 0, getWidth(), getHeight());
        offsetRectWithShadow(r4);
        return true;
    L6:
        return false;
    }

    public int getCustomSize() {
        return this.customSize;
    }

    @Override // com.google.android.material.expandable.ExpandableTransformationWidget
    public int getExpandedComponentIdHint() {
        return this.expandableWidgetHelper.getExpandedComponentIdHint();
    }

    public MotionSpec getHideMotionSpec() {
        return getImpl().getHideMotionSpec();
    }

    public void getMeasuredContentRect(Rect r4) {
        r4.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        offsetRectWithShadow(r4);
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList r0 = this.rippleColor;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getDefaultColor();
    }

    public ColorStateList getRippleColorStateList() {
        return this.rippleColor;
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return (ShapeAppearanceModel) Preconditions.checkNotNull(getImpl().getShapeAppearance());
    }

    public MotionSpec getShowMotionSpec() {
        return getImpl().getShowMotionSpec();
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeDimension() {
        return getSizeDimension(this.size);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.imageTint;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.imageMode;
    }

    public boolean getUseCompatPadding() {
        return this.compatPadding;
    }

    public void hide() {
        hide(null);
    }

    public void hide(OnVisibilityChangedListener r2) {
        hide(r2, true);
    }

    public void hide(OnVisibilityChangedListener r2, boolean r3) {
        getImpl().hide(wrapOnVisibilityChangedListener(r2), r3);
    }

    @Override // com.google.android.material.expandable.ExpandableWidget
    public boolean isExpanded() {
        return this.expandableWidgetHelper.isExpanded();
    }

    public boolean isOrWillBeHidden() {
        return getImpl().isOrWillBeHidden();
    }

    public boolean isOrWillBeShown() {
        return getImpl().isOrWillBeShown();
    }

    @Override // android.widget.ImageView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getImpl().onAttachedToWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getImpl().onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int r3, int r4) {
        int r0 = getSizeDimension();
        this.imagePadding = (r0 - this.maxImageSize) / 2;
        getImpl().updatePadding();
        int r32 = Math.min(View.resolveSize(r0, r3), View.resolveSize(r0, r4));
        Rect r42 = this.shadowPadding;
        setMeasuredDimension((r42.left + r32) + r42.right, (r32 + r42.top) + r42.bottom);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof ExtendableSavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        ExtendableSavedState r22 = (ExtendableSavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        this.expandableWidgetHelper.onRestoreInstanceState((Bundle) Preconditions.checkNotNull(r22.extendableStates.get(EXPANDABLE_WIDGET_HELPER_KEY)));
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable r0 = super.onSaveInstanceState();
        if (r0 != null) goto L5;
        r0 = new Bundle();
    L5:
        ExtendableSavedState r1 = new ExtendableSavedState(r0);
        r1.extendableStates.put(EXPANDABLE_WIDGET_HELPER_KEY, this.expandableWidgetHelper.onSaveInstanceState());
        return r1;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r4) {
        if (r4.getAction() != 0) goto L9;
        getTouchTargetRect(this.touchArea);
        if (this.touchArea.contains((int) r4.getX(), (int) r4.getY()) == true) goto L9;
        return false;
    L9:
        return super.onTouchEvent(r4);
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener r1) {
        getImpl().removeOnHideAnimationListener(r1);
    }

    public void removeOnShowAnimationListener(Animator.AnimatorListener r1) {
        getImpl().removeOnShowAnimationListener(r1);
    }

    public void removeTransformationCallback(TransformationCallback<? extends FloatingActionButton> r3) {
        getImpl().removeTransformationCallback(new TransformationCallbackWrapper(this, r3));
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable r1) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int r1) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList r2) {
        if (this.backgroundTint == r2) goto L6;
        this.backgroundTint = r2;
        getImpl().setBackgroundTintList(r2);
        return;
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode r2) {
        if (this.backgroundTintMode == r2) goto L6;
        this.backgroundTintMode = r2;
        getImpl().setBackgroundTintMode(r2);
        return;
    }

    @Override // android.view.View
    public void setClickable(boolean r1) {
        super.setClickable(r1);
        updateTooltip();
    }

    public void setCompatElevation(float r1) {
        getImpl().setElevation(r1);
    }

    public void setCompatElevationResource(int r2) {
        setCompatElevation(getResources().getDimension(r2));
    }

    public void setCompatHoveredFocusedTranslationZ(float r1) {
        getImpl().setHoveredFocusedTranslationZ(r1);
    }

    public void setCompatHoveredFocusedTranslationZResource(int r2) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(r2));
    }

    public void setCompatPressedTranslationZ(float r1) {
        getImpl().setPressedTranslationZ(r1);
    }

    public void setCompatPressedTranslationZResource(int r2) {
        setCompatPressedTranslationZ(getResources().getDimension(r2));
    }

    @Override // android.view.View
    public void setContentDescription(CharSequence r1) {
        super.setContentDescription(r1);
        updateTooltip();
    }

    public void setCustomSize(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("Custom size must be non-negative");
        return;
    L4:
        if (r2 == this.customSize) goto L9;
        this.customSize = r2;
        requestLayout();
        return;
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        getImpl().updateShapeElevation(r1);
    }

    public void setEnsureMinTouchTargetSize(boolean r2) {
        if (r2 == getImpl().getEnsureMinTouchTargetSize()) goto L6;
        getImpl().setEnsureMinTouchTargetSize(r2);
        requestLayout();
        return;
    }

    @Override // com.google.android.material.expandable.ExpandableWidget
    public boolean setExpanded(boolean r1) {
        return this.expandableWidgetHelper.setExpanded(r1);
    }

    @Override // com.google.android.material.expandable.ExpandableTransformationWidget
    public void setExpandedComponentIdHint(int r1) {
        this.expandableWidgetHelper.setExpandedComponentIdHint(r1);
    }

    public void setHideMotionSpec(MotionSpec r1) {
        getImpl().setHideMotionSpec(r1);
    }

    public void setHideMotionSpecResource(int r2) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), r2));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable r2) {
        if (getDrawable() == r2) goto L8;
        super.setImageDrawable(r2);
        getImpl().updateImageMatrixScale();
        if (this.imageTint == null) goto L9;
        onApplySupportImageTint();
        return;
    L9:
        return;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int r2) {
        this.imageHelper.setImageResource(r2);
        onApplySupportImageTint();
    }

    public void setMaxImageSize(int r1) {
        this.maxImageSize = r1;
        getImpl().setMaxImageSize(r1);
    }

    public void setRippleColor(int r1) {
        setRippleColor(ColorStateList.valueOf(r1));
    }

    public void setRippleColor(ColorStateList r2) {
        if (this.rippleColor == r2) goto L6;
        this.rippleColor = r2;
        getImpl().setRippleColor(this.rippleColor);
        return;
    }

    @Override // android.view.View
    public void setScaleX(float r1) {
        super.setScaleX(r1);
        getImpl().onScaleChanged();
    }

    @Override // android.view.View
    public void setScaleY(float r1) {
        super.setScaleY(r1);
        getImpl().onScaleChanged();
    }

    public void setShadowPaddingEnabled(boolean r1) {
        getImpl().setShadowPaddingEnabled(r1);
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel r1) {
        getImpl().setShapeAppearance(r1);
    }

    public void setShowMotionSpec(MotionSpec r1) {
        getImpl().setShowMotionSpec(r1);
    }

    public void setShowMotionSpecResource(int r2) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), r2));
    }

    public void setSize(int r2) {
        this.customSize = 0;
        if (r2 == this.size) goto L6;
        this.size = r2;
        requestLayout();
        return;
    }

    public void setSupportBackgroundTintList(ColorStateList r1) {
        setBackgroundTintList(r1);
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode r1) {
        setBackgroundTintMode(r1);
    }

    public void setSupportImageTintList(ColorStateList r2) {
        if (this.imageTint == r2) goto L6;
        this.imageTint = r2;
        onApplySupportImageTint();
        return;
    }

    public void setSupportImageTintMode(PorterDuff.Mode r2) {
        if (this.imageMode == r2) goto L6;
        this.imageMode = r2;
        onApplySupportImageTint();
        return;
    }

    @Override // android.view.View
    public void setTranslationX(float r1) {
        super.setTranslationX(r1);
        getImpl().onTranslationChanged();
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        getImpl().onTranslationChanged();
    }

    @Override // android.view.View
    public void setTranslationZ(float r1) {
        super.setTranslationZ(r1);
        getImpl().onTranslationChanged();
    }

    public void setUseCompatPadding(boolean r2) {
        if (this.compatPadding == r2) goto L6;
        this.compatPadding = r2;
        getImpl().onCompatShadowChanged();
        return;
    }

    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
    public void setVisibility(int r1) {
        super.setVisibility(r1);
    }

    public boolean shouldEnsureMinTouchTargetSize() {
        return getImpl().getEnsureMinTouchTargetSize();
    }

    public void show() {
        show(null);
    }

    public void show(OnVisibilityChangedListener r2) {
        show(r2, true);
    }

    public void show(OnVisibilityChangedListener r2, boolean r3) {
        getImpl().show(wrapOnVisibilityChangedListener(r2), r3);
    }
}
