package com.google.android.material.navigation;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.BaselineLayout;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;

/* loaded from: classes5.dex */
public abstract class NavigationBarItemView extends FrameLayout implements NavigationBarMenuItemView {
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_LABELED_TRANSFORM = null;
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_UNLABELED_TRANSFORM = null;
    private static final int[] CHECKED_STATE_SET = null;
    private static final int INVALID_ITEM_POSITION = -1;
    private ValueAnimator activeIndicatorAnimator;
    private int activeIndicatorDesiredHeight;
    private int activeIndicatorDesiredWidth;
    private boolean activeIndicatorEnabled;
    private int activeIndicatorExpandedDesiredHeight;
    private int activeIndicatorExpandedDesiredWidth;
    private int activeIndicatorExpandedMarginHorizontal;
    private int activeIndicatorLabelPadding;
    private int activeIndicatorMarginHorizontal;
    private float activeIndicatorProgress;
    private boolean activeIndicatorResizeable;
    private ActiveIndicatorTransform activeIndicatorTransform;
    private final View activeIndicatorView;
    private BadgeDrawable badgeDrawable;
    private int badgeFixedEdge;
    private boolean boldText;
    private final LinearLayout contentContainer;
    private BaselineLayout currentLabelGroup;
    private boolean expanded;
    private BaselineLayout expandedLabelGroup;
    private float expandedLabelScaleDownFactor;
    private float expandedLabelScaleUpFactor;
    private float expandedLabelShiftAmountY;
    private TextView expandedLargeLabel;
    private TextView expandedSmallLabel;
    private int horizontalTextAppearanceActive;
    private int horizontalTextAppearanceInactive;
    private final ImageView icon;
    private final FrameLayout iconContainer;
    private int iconLabelHorizontalSpacing;
    private ColorStateList iconTint;
    private boolean initialized;
    private final LinearLayout innerContentContainer;
    private boolean isShifting;
    private Rect itemActiveIndicatorExpandedPadding;
    Drawable itemBackground;
    private MenuItemImpl itemData;
    private int itemGravity;
    private int itemIconGravity;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private int itemPosition;
    private ColorStateList itemRippleColor;
    private final BaselineLayout labelGroup;
    private int labelVisibilityMode;
    private final TextView largeLabel;
    private boolean measurePaddingFromBaseline;
    private boolean onlyShowWhenExpanded;
    private Drawable originalIconDrawable;
    private float scaleDownFactor;
    private boolean scaleLabelSizeWithFont;
    private float scaleUpFactor;
    private float shiftAmountY;
    private final TextView smallLabel;
    private int textAppearanceActive;
    private int textAppearanceInactive;
    private ColorStateList textColor;
    private Drawable wrappedIconDrawable;

    /* renamed from: com.google.android.material.navigation.NavigationBarItemView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ NavigationBarItemView this$0;
        final /* synthetic */ int val$width;

        public AnonymousClass1(NavigationBarItemView r1, int r2) {
            this.this$0 = r1;
            this.val$width = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.updateActiveIndicatorLayoutParams(this.val$width);
        }
    }

    /* renamed from: com.google.android.material.navigation.NavigationBarItemView$2, reason: invalid class name */
    public class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ NavigationBarItemView this$0;
        final /* synthetic */ float val$newProgress;

        public AnonymousClass2(NavigationBarItemView r1, float r2) {
            this.this$0 = r1;
            this.val$newProgress = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            NavigationBarItemView.access$200(this.this$0, r22, this.val$newProgress);
        }
    }

    public static class ActiveIndicatorTransform {
        private static final float ALPHA_FRACTION = 0.2f;
        private static final float SCALE_X_HIDDEN = 0.4f;
        private static final float SCALE_X_SHOWN = 1.0f;

        private ActiveIndicatorTransform() {
        }

        public /* synthetic */ ActiveIndicatorTransform(AnonymousClass1 r1) {
            this();
        }

        public float calculateAlpha(float r3, float r4) {
            if (r4 != 0.0f) goto L5;
            float r0 = 0.8f;
        L7:
            if (r4 != 0.0f) goto L9;
            float r42 = 1.0f;
        L11:
            return AnimationUtils.lerp(0.0f, 1.0f, r0, r42, r3);
        L9:
            r42 = ALPHA_FRACTION;
            goto L11
        L5:
            r0 = 0.0f;
            goto L7
        }

        public float calculateScaleX(float r2) {
            return AnimationUtils.lerp(SCALE_X_HIDDEN, 1.0f, r2);
        }

        public float calculateScaleY(float r1) {
            return 1.0f;
        }

        public void updateForProgress(float r2, float r3, View r4) {
            r4.setScaleX(calculateScaleX(r2));
            r4.setScaleY(calculateScaleY(r2));
            r4.setAlpha(calculateAlpha(r2, r3));
        }
    }

    public static class ActiveIndicatorUnlabeledTransform extends ActiveIndicatorTransform {
        private ActiveIndicatorUnlabeledTransform() {
            super(null);
        }

        public /* synthetic */ ActiveIndicatorUnlabeledTransform(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.android.material.navigation.NavigationBarItemView.ActiveIndicatorTransform
        public float calculateScaleY(float r1) {
            return calculateScaleX(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$QomMk9yO0suSBr2t2awa7nYn3bM(NavigationBarItemView r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        if (r0.icon.getVisibility() != 0) goto L5;
        r0.tryUpdateBadgeBounds(r0.icon);
    L5:
        FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) r0.innerContentContainer.getLayoutParams();
        int r42 = ((r4 - r2) + r12.rightMargin) + r12.leftMargin;
        int r52 = ((r5 - r3) + r12.topMargin) + r12.bottomMargin;
        boolean r22 = true;
        if (r0.itemIconGravity == 1) goto L8;
        return;
    L8:
        if (r0.activeIndicatorExpandedDesiredWidth != (-2)) goto L23;
        FrameLayout.LayoutParams r13 = (FrameLayout.LayoutParams) r0.activeIndicatorView.getLayoutParams();
        if (r0.activeIndicatorExpandedDesiredWidth == (-2)) goto L12;
    L14:
        boolean r32 = false;
    L16:
        if (r0.activeIndicatorView.getMeasuredHeight() >= r52) goto L18;
        r13.height = r52;
    L19:
        if (r22 == false) goto L24;
        r0.activeIndicatorView.setLayoutParams(r13);
        return;
    L24:
        return;
    L18:
        r22 = r32;
        goto L19
    L12:
        if (r0.activeIndicatorView.getMeasuredWidth() == r42) goto L14;
        r13.width = Math.max(r42, Math.min(r0.activeIndicatorDesiredWidth, r0.getMeasuredWidth() - (r0.activeIndicatorMarginHorizontal * 2)));
        r32 = true;
        goto L16
    }

    static {
        CHECKED_STATE_SET = new int[]{R.attr.state_checked};
        ACTIVE_INDICATOR_LABELED_TRANSFORM = new ActiveIndicatorTransform(null);
        ACTIVE_INDICATOR_UNLABELED_TRANSFORM = new ActiveIndicatorUnlabeledTransform(null);
    }

    public NavigationBarItemView(Context r8) {
        super(r8);
        this.initialized = false;
        this.itemPosition = -1;
        this.textAppearanceActive = 0;
        this.textAppearanceInactive = 0;
        this.horizontalTextAppearanceActive = 0;
        this.horizontalTextAppearanceInactive = 0;
        this.boldText = false;
        this.activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
        this.activeIndicatorProgress = 0.0f;
        this.activeIndicatorEnabled = false;
        this.activeIndicatorDesiredWidth = 0;
        this.activeIndicatorDesiredHeight = 0;
        this.activeIndicatorExpandedDesiredWidth = -2;
        this.activeIndicatorExpandedDesiredHeight = 0;
        this.activeIndicatorResizeable = false;
        this.activeIndicatorMarginHorizontal = 0;
        this.activeIndicatorExpandedMarginHorizontal = 0;
        this.badgeFixedEdge = 0;
        this.itemGravity = 49;
        this.expanded = false;
        this.onlyShowWhenExpanded = false;
        this.measurePaddingFromBaseline = false;
        this.scaleLabelSizeWithFont = false;
        this.itemActiveIndicatorExpandedPadding = new Rect();
        LayoutInflater.from(r8).inflate(getItemLayoutResId(), this, true);
        this.contentContainer = (LinearLayout) findViewById(com.google.android.material.R.id.navigation_bar_item_content_container);
        LinearLayout r82 = (LinearLayout) findViewById(com.google.android.material.R.id.navigation_bar_item_inner_content_container);
        this.innerContentContainer = r82;
        this.activeIndicatorView = findViewById(com.google.android.material.R.id.navigation_bar_item_active_indicator_view);
        this.iconContainer = (FrameLayout) findViewById(com.google.android.material.R.id.navigation_bar_item_icon_container);
        this.icon = (ImageView) findViewById(com.google.android.material.R.id.navigation_bar_item_icon_view);
        BaselineLayout r1 = (BaselineLayout) findViewById(com.google.android.material.R.id.navigation_bar_item_labels_group);
        this.labelGroup = r1;
        TextView r3 = (TextView) findViewById(com.google.android.material.R.id.navigation_bar_item_small_label_view);
        this.smallLabel = r3;
        TextView r4 = (TextView) findViewById(com.google.android.material.R.id.navigation_bar_item_large_label_view);
        this.largeLabel = r4;
        initializeDefaultExpandedLabelGroupViews();
        this.currentLabelGroup = r1;
        setBackgroundResource(getItemBackgroundResId());
        this.itemPaddingTop = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.itemPaddingBottom = r1.getPaddingBottom();
        this.activeIndicatorLabelPadding = 0;
        this.iconLabelHorizontalSpacing = 0;
        r3.setImportantForAccessibility(2);
        r4.setImportantForAccessibility(2);
        this.expandedSmallLabel.setImportantForAccessibility(2);
        this.expandedLargeLabel.setImportantForAccessibility(2);
        setFocusable(true);
        calculateTextScaleFactors();
        this.activeIndicatorExpandedDesiredHeight = getResources().getDimensionPixelSize(com.google.android.material.R.dimen.m3_navigation_item_expanded_active_indicator_height_default);
        r82.addOnLayoutChangeListener(new NavigationBarItemView$$ExternalSyntheticLambda0(this));
    }

    public static /* synthetic */ void access$200(NavigationBarItemView r0, float r1, float r2) {
        r0.setActiveIndicatorProgress(r1, r2);
    }

    private void addDefaultExpandedLabelGroupViews() {
        LinearLayout.LayoutParams r0 = new LinearLayout.LayoutParams(-2, -2);
        r0.gravity = 17;
        this.innerContentContainer.addView(this.expandedLabelGroup, r0);
        setExpandedLabelGroupMargins();
    }

    private void calculateTextScaleFactors() {
        float r0 = this.smallLabel.getTextSize();
        float r1 = this.largeLabel.getTextSize();
        this.shiftAmountY = r0 - r1;
        this.scaleUpFactor = (r1 * 1.0f) / r0;
        this.scaleDownFactor = (r0 * 1.0f) / r1;
        float r02 = this.expandedSmallLabel.getTextSize();
        float r12 = this.expandedLargeLabel.getTextSize();
        this.expandedLabelShiftAmountY = r02 - r12;
        this.expandedLabelScaleUpFactor = (r12 * 1.0f) / r02;
        this.expandedLabelScaleDownFactor = (r02 * 1.0f) / r12;
    }

    private Drawable createItemBackgroundCompat(ColorStateList r3) {
        RippleDrawable r0 = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(r3), null, null);
        return FocusRingDrawable.wrap(getContext(), r0);
    }

    private int getItemVisiblePosition() {
        ViewGroup r0 = (ViewGroup) getParent();
        int r5 = r0.indexOfChild(this);
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r5) goto L10;
        View r3 = r0.getChildAt(r1);
        if ((r3 instanceof NavigationBarItemView) == false) goto L9;
        if (r3.getVisibility() != 0) goto L9;
        r2 = r2 + 1;
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return r2;
    }

    private int getSuggestedIconWidth() {
        BadgeDrawable r0 = this.badgeDrawable;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        LinearLayout.LayoutParams r1 = (LinearLayout.LayoutParams) this.iconContainer.getLayoutParams();
        return (Math.max(r02, r1.leftMargin) + this.icon.getMeasuredWidth()) + Math.max(r02, r1.rightMargin);
    L5:
        r02 = r0.getMinimumWidth() - this.badgeDrawable.getHorizontalOffset();
        goto L6
    }

    private boolean hasBadge() {
        if (this.badgeDrawable == null) goto L6;
        return true;
    L6:
        return false;
    }

    private void initializeDefaultExpandedLabelGroupViews() {
        float r0 = getResources().getDimension(com.google.android.material.R.dimen.default_navigation_text_size);
        float r1 = getResources().getDimension(com.google.android.material.R.dimen.default_navigation_active_text_size);
        BaselineLayout r2 = new BaselineLayout(getContext());
        this.expandedLabelGroup = r2;
        r2.setVisibility(8);
        this.expandedLabelGroup.setDuplicateParentStateEnabled(true);
        this.expandedLabelGroup.setMeasurePaddingFromBaseline(this.measurePaddingFromBaseline);
        TextView r22 = new TextView(getContext());
        this.expandedSmallLabel = r22;
        r22.setMaxLines(1);
        TextView r23 = this.expandedSmallLabel;
        TextUtils.TruncateAt r4 = TextUtils.TruncateAt.END;
        r23.setEllipsize(r4);
        this.expandedSmallLabel.setDuplicateParentStateEnabled(true);
        this.expandedSmallLabel.setIncludeFontPadding(false);
        this.expandedSmallLabel.setGravity(16);
        this.expandedSmallLabel.setTextSize(r0);
        TextView r02 = new TextView(getContext());
        this.expandedLargeLabel = r02;
        r02.setMaxLines(1);
        this.expandedLargeLabel.setEllipsize(r4);
        this.expandedLargeLabel.setDuplicateParentStateEnabled(true);
        this.expandedLargeLabel.setVisibility(4);
        this.expandedLargeLabel.setIncludeFontPadding(false);
        this.expandedLargeLabel.setGravity(16);
        this.expandedLargeLabel.setTextSize(r1);
        this.expandedLabelGroup.addView(this.expandedSmallLabel);
        this.expandedLabelGroup.addView(this.expandedLargeLabel);
    }

    private boolean isActiveIndicatorResizeableAndUnlabeled() {
        if (this.activeIndicatorResizeable == true) goto L5;
        return false;
    L5:
        if (this.labelVisibilityMode != 2) goto L10;
        return true;
    L10:
        return false;
    }

    private void maybeAnimateActiveIndicatorToProgress(float r5) {
        if (this.activeIndicatorEnabled == true) goto L5;
    L14:
        setActiveIndicatorProgress(r5, r5);
        return;
    L5:
        if (this.initialized == false) goto L14;
        if (isAttachedToWindow() == false) goto L14;
        ValueAnimator r0 = this.activeIndicatorAnimator;
        if (r0 == null) goto L12;
        r0.cancel();
        this.activeIndicatorAnimator = null;
    L12:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.activeIndicatorProgress, r5});
        this.activeIndicatorAnimator = r02;
        r02.addUpdateListener(new AnonymousClass2(this, r5));
        this.activeIndicatorAnimator.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), com.google.android.material.R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        this.activeIndicatorAnimator.setDuration(MotionUtils.resolveThemeDuration(getContext(), com.google.android.material.R.attr.motionDurationLong2, getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1)));
        this.activeIndicatorAnimator.start();
    }

    private void refreshChecked() {
        MenuItemImpl r0 = this.itemData;
        if (r0 == null) goto L6;
        setChecked(r0.isChecked());
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.graphics.drawable.LayerDrawable, android.graphics.drawable.RippleDrawable] */
    private void refreshItemBackground() {
        Drawable r0 = this.itemBackground;
        MaterialShapeDrawable r3 = null;
        boolean r4 = true;
        if (this.itemRippleColor == null) goto L13;
        Drawable r1 = getActiveIndicatorDrawable();
        if (this.activeIndicatorEnabled == false) goto L11;
        if (r1 == null) goto L11;
        ?? r42 = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.itemRippleColor), null, r1);
        if ((r1 instanceof MaterialShapeDrawable) == false) goto L10;
        r3 = (MaterialShapeDrawable) r1;
    L10:
        FocusRingDrawable.layer(getContext(), r42, r3);
        r3 = r42;
        r4 = false;
    L11:
        if (r0 != null) goto L13;
        r0 = createItemBackgroundCompat(this.itemRippleColor);
    L13:
        this.iconContainer.setPadding(0, 0, 0, 0);
        this.iconContainer.setForeground(r3);
        setBackground(r0);
        if (Build.VERSION.SDK_INT < 26) goto L17;
        setDefaultFocusHighlightEnabled(r4);
        return;
    }

    private void setActiveIndicatorProgress(float r3, float r4) {
        this.activeIndicatorTransform.updateForProgress(r3, r4, this.activeIndicatorView);
        this.activeIndicatorProgress = r3;
    }

    private void setExpandedLabelGroupMargins() {
        int r1 = 0;
        if (this.icon.getLayoutParams().width <= 0) goto L5;
        int r0 = this.iconLabelHorizontalSpacing;
    L6:
        LinearLayout.LayoutParams r2 = (LinearLayout.LayoutParams) this.expandedLabelGroup.getLayoutParams();
        if (r2 != null) goto L9;
        return;
    L9:
        if (getLayoutDirection() != 1) goto L11;
        int r3 = r0;
    L12:
        r2.rightMargin = r3;
        if (getLayoutDirection() == 1) goto L16;
        r1 = r0;
    L16:
        r2.leftMargin = r1;
        return;
    L11:
        r3 = 0;
        goto L12
    L5:
        r0 = 0;
        goto L6
    }

    private void setLabelPivots(TextView r1) {
        r1.setPivotX(r1.getWidth() / 2);
        r1.setPivotY(r1.getBaseline());
    }

    private void setLayoutConfigurationIconAndLabel(View r5, View r6, float r7, float r8) {
        LinearLayout r0 = this.contentContainer;
        if (this.itemIconGravity != 0) goto L5;
        int r82 = (int) (this.itemPaddingTop + r8);
    L6:
        setViewMarginAndGravity(r0, r82, 0, this.itemGravity);
        LinearLayout r83 = this.innerContentContainer;
        int r02 = this.itemIconGravity;
        if (r02 != 0) goto L9;
        int r1 = 0;
    L10:
        if (r02 != 0) goto L12;
        int r3 = 0;
    L13:
        if (r02 != 0) goto L15;
        int r03 = 17;
    L16:
        setViewMarginAndGravity(r83, r1, r3, r03);
        updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
        this.currentLabelGroup.setVisibility(0);
        setViewScaleValues(r5, 1.0f, 1.0f, 0);
        setViewScaleValues(r6, r7, r7, 4);
        return;
    L15:
        r03 = NavigationBarView.ITEM_GRAVITY_START_CENTER;
        goto L16
    L12:
        r3 = this.itemActiveIndicatorExpandedPadding.bottom;
        goto L13
    L9:
        r1 = this.itemActiveIndicatorExpandedPadding.top;
        goto L10
    L5:
        r82 = 0;
        goto L6
    }

    private void setLayoutConfigurationIconOnly() {
        LinearLayout r0 = this.contentContainer;
        int r1 = this.itemPaddingTop;
        if (this.itemIconGravity != 0) goto L5;
        int r2 = 17;
    L6:
        setViewMarginAndGravity(r0, r1, r1, r2);
        setViewMarginAndGravity(this.innerContentContainer, 0, 0, 17);
        updateViewPaddingBottom(this.labelGroup, 0);
        this.currentLabelGroup.setVisibility(8);
        return;
    L5:
        r2 = this.itemGravity;
        goto L6
    }

    private void setTextAppearanceForLabel(TextView r1, int r2) {
        if (this.scaleLabelSizeWithFont == false) goto L6;
        TextViewCompat.setTextAppearance(r1, r2);
        return;
    L6:
        setTextAppearanceWithoutFontScaling(r1, r2);
    }

    private static void setTextAppearanceWithoutFontScaling(TextView r2, int r3) {
        TextViewCompat.setTextAppearance(r2, r3);
        int r32 = MaterialResources.getUnscaledTextSize(r2.getContext(), r3, 0);
        if (r32 == 0) goto L6;
        r2.setTextSize(0, r32);
        return;
    }

    private static void setViewMarginAndGravity(View r1, int r2, int r3, int r4) {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) r1.getLayoutParams();
        r0.topMargin = r2;
        r0.bottomMargin = r3;
        r0.gravity = r4;
        r1.setLayoutParams(r0);
    }

    private static void setViewScaleValues(View r0, float r1, float r2, int r3) {
        r0.setScaleX(r1);
        r0.setScaleY(r2);
        r0.setVisibility(r3);
    }

    private void tryAttachBadgeToAnchor(View r2) {
        if (hasBadge() == false) goto L9;
        if (r2 == null) goto L8;
        setClipChildren(false);
        setClipToPadding(false);
        BadgeUtils.attachBadgeDrawable(this.badgeDrawable, r2);
        return;
    L8:
        return;
    }

    private void tryRemoveBadgeFromAnchor(View r2) {
        if (hasBadge() == true) goto L5;
        return;
    L5:
        if (r2 == null) goto L7;
        setClipChildren(true);
        setClipToPadding(true);
        BadgeUtils.detachBadgeDrawable(this.badgeDrawable, r2);
    L7:
        this.badgeDrawable = null;
    }

    private void tryUpdateBadgeBounds(View r2) {
        if (hasBadge() == true) goto L5;
        return;
    L5:
        BadgeUtils.setBadgeDrawableBounds(this.badgeDrawable, r2, null);
    }

    private void updateActiveIndicatorTransform() {
        if (isActiveIndicatorResizeableAndUnlabeled() == false) goto L6;
        this.activeIndicatorTransform = ACTIVE_INDICATOR_UNLABELED_TRANSFORM;
        return;
    L6:
        this.activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
    }

    private void updateActiveLabelBoldness() {
        TextView r0 = this.largeLabel;
        r0.setTypeface(r0.getTypeface(), this.boldText ? 1 : 0);
        TextView r02 = this.expandedLargeLabel;
        r02.setTypeface(r02.getTypeface(), this.boldText ? 1 : 0);
    }

    private void updateActiveLabelTextAppearance(TextView r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        setTextAppearanceForLabel(r3, r4);
        calculateTextScaleFactors();
        r3.setMinimumHeight(MaterialResources.getUnscaledLineHeight(r3.getContext(), r4, 0));
        ColorStateList r42 = this.textColor;
        if (r42 == null) goto L7;
        r3.setTextColor(r42);
    L7:
        updateActiveLabelBoldness();
    }

    private void updateInactiveLabelTextAppearance(TextView r3, int r4) {
        if (r3 == null) goto L9;
        setTextAppearanceForLabel(r3, r4);
        calculateTextScaleFactors();
        r3.setMinimumHeight(MaterialResources.getUnscaledLineHeight(r3.getContext(), r4, 0));
        ColorStateList r2 = this.textColor;
        if (r2 == null) goto L8;
        r3.setTextColor(r2);
        return;
    L8:
        return;
    }

    private void updateItemIconGravity() {
        this.badgeFixedEdge = 0;
        this.currentLabelGroup = this.labelGroup;
        int r2 = 8;
        if (this.itemIconGravity == 1) goto L5;
        int r1 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r3 = 8;
        r2 = 0;
    L9:
        this.labelGroup.setVisibility(r2);
        this.expandedLabelGroup.setVisibility(r3);
        ((FrameLayout.LayoutParams) this.contentContainer.getLayoutParams()).gravity = this.itemGravity;
        FrameLayout.LayoutParams r22 = (FrameLayout.LayoutParams) this.innerContentContainer.getLayoutParams();
        r22.leftMargin = r5;
        r22.rightMargin = r6;
        r22.topMargin = r7;
        r22.bottomMargin = r1;
        setPadding(r4, 0, r4, 0);
        updateActiveIndicatorLayoutParams(getWidth());
        return;
    L5:
        if (this.expandedLabelGroup.getParent() != null) goto L7;
        addDefaultExpandedLabelGroupViews();
    L7:
        Rect r12 = this.itemActiveIndicatorExpandedPadding;
        int r42 = r12.left;
        int r52 = r12.right;
        int r62 = r12.top;
        r1 = r12.bottom;
        this.badgeFixedEdge = 1;
        int r32 = this.activeIndicatorExpandedMarginHorizontal;
        this.currentLabelGroup = this.expandedLabelGroup;
        r7 = r62;
        r6 = r52;
        r5 = r42;
        r4 = r32;
        r3 = 0;
        goto L9
    }

    private static void updateViewPaddingBottom(View r3, int r4) {
        r3.setPadding(r3.getPaddingLeft(), r3.getPaddingTop(), r3.getPaddingRight(), r4);
    }

    private void updateVisibility() {
        MenuItemImpl r0 = this.itemData;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isVisible() == true) goto L7;
    L11:
        int r02 = 8;
    L12:
        setVisibility(r02);
        return;
    L7:
        if (this.expanded == false) goto L9;
    L10:
        r02 = 0;
        goto L12
    L9:
        if (this.onlyShowWhenExpanded == true) goto L11;
        goto L10
    }

    public void clear() {
        removeBadge();
        this.itemData = null;
        this.activeIndicatorProgress = 0.0f;
        this.initialized = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.activeIndicatorEnabled == false) goto L6;
        this.iconContainer.dispatchTouchEvent(r2);
    L6:
        return super.dispatchTouchEvent(r2);
    }

    public Drawable getActiveIndicatorDrawable() {
        return this.activeIndicatorView.getBackground();
    }

    public BadgeDrawable getBadge() {
        return this.badgeDrawable;
    }

    public BaselineLayout getExpandedLabelGroup() {
        return this.expandedLabelGroup;
    }

    public int getItemBackgroundResId() {
        return com.google.android.material.R.drawable.mtrl_navigation_bar_item_background;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView, androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    public int getItemDefaultMarginResId() {
        return com.google.android.material.R.dimen.mtrl_navigation_bar_item_default_margin;
    }

    public abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.itemPosition;
    }

    public BaselineLayout getLabelGroup() {
        return this.labelGroup;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.contentContainer.getLayoutParams();
        return (this.contentContainer.getMeasuredHeight() + r0.topMargin) + r0.bottomMargin;
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        if (this.itemIconGravity != 1) goto L6;
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.innerContentContainer.getLayoutParams();
        return (this.innerContentContainer.getMeasuredWidth() + r0.leftMargin) + r0.rightMargin;
    L6:
        LinearLayout.LayoutParams r02 = (LinearLayout.LayoutParams) this.labelGroup.getLayoutParams();
        int r1 = (r02.leftMargin + this.labelGroup.getMeasuredWidth()) + r02.rightMargin;
        return Math.max(getSuggestedIconWidth(), r1);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView, androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl r1, int r2) {
        this.itemData = r1;
        setCheckable(r1.isCheckable());
        setChecked(r1.isChecked());
        setEnabled(r1.isEnabled());
        setIcon(r1.getIcon());
        setTitle(r1.getTitle());
        setId(r1.getItemId());
        if (TextUtils.isEmpty(r1.getContentDescription()) == true) goto L6;
        setContentDescription(r1.getContentDescription());
    L6:
        if (TextUtils.isEmpty(r1.getTooltipText()) == true) goto L8;
        CharSequence r12 = r1.getTooltipText();
    L9:
        TooltipCompat.setTooltipText(this, r12);
        updateVisibility();
        this.initialized = true;
        return;
    L8:
        r12 = r1.getTitle();
        goto L9
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public boolean isExpanded() {
        return this.expanded;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public boolean isOnlyVisibleWhenExpanded() {
        return this.onlyShowWhenExpanded;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int r2) {
        int[] r22 = super.onCreateDrawableState(r2 + 1);
        MenuItemImpl r0 = this.itemData;
        if (r0 != null) goto L5;
    L9:
        return r22;
    L5:
        if (r0.isCheckable() == false) goto L9;
        if (this.itemData.isChecked() == false) goto L9;
        View.mergeDrawableStates(r22, CHECKED_STATE_SET);
        goto L9
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r7) {
        super.onInitializeAccessibilityNodeInfo(r7);
        BadgeDrawable r0 = this.badgeDrawable;
        if (r0 != null) goto L5;
    L10:
        AccessibilityNodeInfoCompat r72 = AccessibilityNodeInfoCompat.wrap(r7);
        r72.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, getItemVisiblePosition(), 1, false, isSelected()));
        if (isSelected() == false) goto L13;
        r72.setClickable(false);
        r72.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
    L13:
        r72.setRoleDescription(getResources().getString(com.google.android.material.R.string.item_view_role_description));
        return;
    L5:
        if (r0.isVisible() == false) goto L10;
        CharSequence r02 = this.itemData.getTitle();
        if (TextUtils.isEmpty(this.itemData.getContentDescription()) == true) goto L9;
        r02 = this.itemData.getContentDescription();
    L9:
        r7.setContentDescription(r02 + ", " + this.badgeDrawable.getContentDescription());
        goto L10
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        post(new AnonymousClass1(this, r1));
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView, androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void removeBadge() {
        tryRemoveBadgeFromAnchor(this.icon);
    }

    public void setActiveIndicatorDrawable(Drawable r2) {
        this.activeIndicatorView.setBackground(r2);
        refreshItemBackground();
    }

    public void setActiveIndicatorEnabled(boolean r2) {
        this.activeIndicatorEnabled = r2;
        refreshItemBackground();
        View r0 = this.activeIndicatorView;
        if (r2 == false) goto L5;
        int r22 = 0;
    L6:
        r0.setVisibility(r22);
        requestLayout();
        return;
    L5:
        r22 = 8;
        goto L6
    }

    public void setActiveIndicatorExpandedHeight(int r1) {
        this.activeIndicatorExpandedDesiredHeight = r1;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setActiveIndicatorExpandedMarginHorizontal(int r3) {
        this.activeIndicatorExpandedMarginHorizontal = r3;
        if (this.itemIconGravity != 1) goto L5;
        setPadding(r3, 0, r3, 0);
    L5:
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setActiveIndicatorExpandedPadding(Rect r1) {
        this.itemActiveIndicatorExpandedPadding = r1;
    }

    public void setActiveIndicatorExpandedWidth(int r1) {
        this.activeIndicatorExpandedDesiredWidth = r1;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setActiveIndicatorHeight(int r1) {
        this.activeIndicatorDesiredHeight = r1;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setActiveIndicatorLabelPadding(int r5) {
        if (this.activeIndicatorLabelPadding == r5) goto L15;
        this.activeIndicatorLabelPadding = r5;
        ((LinearLayout.LayoutParams) this.labelGroup.getLayoutParams()).topMargin = r5;
        if (this.expandedLabelGroup.getLayoutParams() == null) goto L16;
        LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) this.expandedLabelGroup.getLayoutParams();
        if (getLayoutDirection() != 1) goto L9;
        int r1 = r5;
    L10:
        r0.rightMargin = r1;
        if (getLayoutDirection() != 1) goto L13;
        r5 = 0;
    L13:
        r0.leftMargin = r5;
        requestLayout();
        return;
    L9:
        r1 = 0;
        goto L10
    L16:
        return;
    }

    public void setActiveIndicatorMarginHorizontal(int r1) {
        this.activeIndicatorMarginHorizontal = r1;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean r1) {
        this.activeIndicatorResizeable = r1;
    }

    public void setActiveIndicatorWidth(int r1) {
        this.activeIndicatorDesiredWidth = r1;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setBadge(BadgeDrawable r3) {
        if (this.badgeDrawable != r3) goto L6;
        return;
    L6:
        if (hasBadge() == true) goto L8;
    L10:
        this.badgeDrawable = r3;
        r3.setBadgeFixedEdge(this.badgeFixedEdge);
        ImageView r32 = this.icon;
        if (r32 == null) goto L14;
        tryAttachBadgeToAnchor(r32);
        return;
    L14:
        return;
    L8:
        if (this.icon == null) goto L10;
        Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
        tryRemoveBadgeFromAnchor(this.icon);
        goto L10
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setCheckable(boolean r1) {
        refreshDrawableState();
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setChecked(boolean r10) {
        setLabelPivots(this.largeLabel);
        setLabelPivots(this.smallLabel);
        setLabelPivots(this.expandedLargeLabel);
        setLabelPivots(this.expandedSmallLabel);
        if (r10 == false) goto L5;
        float r1 = 1.0f;
    L6:
        maybeAnimateActiveIndicatorToProgress(r1);
        TextView r12 = this.largeLabel;
        TextView r2 = this.smallLabel;
        float r3 = this.shiftAmountY;
        float r4 = this.scaleUpFactor;
        float r5 = this.scaleDownFactor;
        if (this.itemIconGravity != 1) goto L9;
        r12 = this.expandedLargeLabel;
        r2 = this.expandedSmallLabel;
        r3 = this.expandedLabelShiftAmountY;
        r4 = this.expandedLabelScaleUpFactor;
        r5 = this.expandedLabelScaleDownFactor;
    L9:
        int r6 = this.labelVisibilityMode;
        if (r6 == (-1)) goto L24;
        if (r6 == 0) goto L20;
        if (r6 != 1) goto L14;
        if (r10 == false) goto L19;
        setLayoutConfigurationIconAndLabel(r12, r2, r4, r3);
    L31:
        refreshDrawableState();
        setSelected(r10);
        return;
    L19:
        setLayoutConfigurationIconAndLabel(r2, r12, r5, 0.0f);
        goto L31
    L14:
        if (r6 != 2) goto L31;
        setLayoutConfigurationIconOnly();
        goto L31
    L20:
        if (r10 == false) goto L22;
        setLayoutConfigurationIconAndLabel(r12, r2, r4, 0.0f);
        goto L31
    L22:
        setLayoutConfigurationIconOnly();
        goto L31
    L24:
        if (this.isShifting == false) goto L28;
        if (r10 == false) goto L27;
        setLayoutConfigurationIconAndLabel(r12, r2, r4, 0.0f);
        goto L31
    L27:
        setLayoutConfigurationIconOnly();
        goto L31
    L28:
        if (r10 == false) goto L30;
        setLayoutConfigurationIconAndLabel(r12, r2, r4, r3);
        goto L31
    L30:
        setLayoutConfigurationIconAndLabel(r2, r12, r5, 0.0f);
        goto L31
    L5:
        r1 = 0.0f;
        goto L6
    }

    @Override // android.view.View, com.google.android.material.navigation.NavigationBarMenuItemView
    public void setEnabled(boolean r2) {
        super.setEnabled(r2);
        this.smallLabel.setEnabled(r2);
        this.largeLabel.setEnabled(r2);
        this.expandedSmallLabel.setEnabled(r2);
        this.expandedLargeLabel.setEnabled(r2);
        this.icon.setEnabled(r2);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setExpanded(boolean r1) {
        this.expanded = r1;
        updateVisibility();
    }

    public void setHorizontalTextAppearanceActive(int r2) {
        this.horizontalTextAppearanceActive = r2;
        TextView r0 = this.expandedLargeLabel;
        if (r2 != 0) goto L6;
        r2 = this.textAppearanceActive;
    L6:
        updateActiveLabelTextAppearance(r0, r2);
    }

    public void setHorizontalTextAppearanceInactive(int r2) {
        this.horizontalTextAppearanceInactive = r2;
        TextView r0 = this.expandedSmallLabel;
        if (r2 != 0) goto L6;
        r2 = this.textAppearanceInactive;
    L6:
        updateInactiveLabelTextAppearance(r0, r2);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setIcon(Drawable r2) {
        if (r2 != this.originalIconDrawable) goto L5;
        return;
    L5:
        this.originalIconDrawable = r2;
        if (r2 == null) goto L14;
        Drawable.ConstantState r0 = r2.getConstantState();
        if (r0 == null) goto L11;
        r2 = r0.newDrawable();
    L11:
        r2 = DrawableCompat.wrap(r2).mutate();
        this.wrappedIconDrawable = r2;
        ColorStateList r02 = this.iconTint;
        if (r02 == null) goto L14;
        r2.setTintList(r02);
    L14:
        this.icon.setImageDrawable(r2);
    }

    public void setIconLabelHorizontalSpacing(int r2) {
        if (this.iconLabelHorizontalSpacing == r2) goto L6;
        this.iconLabelHorizontalSpacing = r2;
        setExpandedLabelGroupMargins();
        requestLayout();
        return;
    }

    public void setIconSize(int r2) {
        LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) this.icon.getLayoutParams();
        r0.width = r2;
        r0.height = r2;
        this.icon.setLayoutParams(r0);
        setExpandedLabelGroupMargins();
    }

    public void setIconTintList(ColorStateList r2) {
        this.iconTint = r2;
        if (this.itemData == null) goto L8;
        Drawable r0 = this.wrappedIconDrawable;
        if (r0 == null) goto L9;
        r0.setTintList(r2);
        this.wrappedIconDrawable.invalidateSelf();
        return;
    L9:
        return;
    }

    public void setItemBackground(int r2) {
        if (r2 != 0) goto L4;
        Drawable r22 = null;
    L5:
        setItemBackground(r22);
        return;
    L4:
        r22 = getContext().getDrawable(r2);
        goto L5
    }

    public void setItemBackground(Drawable r2) {
        if (r2 != null) goto L4;
    L6:
        this.itemBackground = r2;
        refreshItemBackground();
        return;
    L4:
        if (r2.getConstantState() == null) goto L6;
        r2 = r2.getConstantState().newDrawable().mutate();
        goto L6
    }

    public void setItemGravity(int r1) {
        this.itemGravity = r1;
        requestLayout();
    }

    public void setItemIconGravity(int r2) {
        if (this.itemIconGravity == r2) goto L6;
        this.itemIconGravity = r2;
        updateItemIconGravity();
        refreshItemBackground();
        return;
    }

    public void setItemPaddingBottom(int r2) {
        if (this.itemPaddingBottom == r2) goto L6;
        this.itemPaddingBottom = r2;
        refreshChecked();
        return;
    }

    public void setItemPaddingTop(int r2) {
        if (this.itemPaddingTop == r2) goto L6;
        this.itemPaddingTop = r2;
        refreshChecked();
        return;
    }

    public void setItemPosition(int r1) {
        this.itemPosition = r1;
    }

    public void setItemRippleColor(ColorStateList r1) {
        this.itemRippleColor = r1;
        refreshItemBackground();
    }

    public void setLabelFontScalingEnabled(boolean r1) {
        this.scaleLabelSizeWithFont = r1;
        setTextAppearanceActive(this.textAppearanceActive);
        setTextAppearanceInactive(this.textAppearanceInactive);
        setHorizontalTextAppearanceActive(this.horizontalTextAppearanceActive);
        setHorizontalTextAppearanceInactive(this.horizontalTextAppearanceInactive);
    }

    public void setLabelMaxLines(int r4) {
        this.smallLabel.setMaxLines(r4);
        this.largeLabel.setMaxLines(r4);
        this.expandedSmallLabel.setMaxLines(r4);
        this.expandedLargeLabel.setMaxLines(r4);
        if (Build.VERSION.SDK_INT <= 34) goto L5;
        this.smallLabel.setGravity(17);
        this.largeLabel.setGravity(17);
    L9:
        requestLayout();
        return;
    L5:
        TextView r0 = this.smallLabel;
        if (r4 <= 1) goto L8;
        r0.setEllipsize(null);
        this.largeLabel.setEllipsize(null);
        this.smallLabel.setGravity(17);
        this.largeLabel.setGravity(17);
        goto L9
    L8:
        r0.setGravity(16);
        this.largeLabel.setGravity(16);
        goto L9
    }

    public void setLabelVisibilityMode(int r2) {
        if (this.labelVisibilityMode == r2) goto L6;
        this.labelVisibilityMode = r2;
        updateActiveIndicatorTransform();
        updateActiveIndicatorLayoutParams(getWidth());
        refreshChecked();
        return;
    }

    public void setMeasureBottomPaddingFromLabelBaseline(boolean r2) {
        this.measurePaddingFromBaseline = r2;
        this.labelGroup.setMeasurePaddingFromBaseline(r2);
        this.smallLabel.setIncludeFontPadding(r2);
        this.largeLabel.setIncludeFontPadding(r2);
        this.expandedLabelGroup.setMeasurePaddingFromBaseline(r2);
        this.expandedSmallLabel.setIncludeFontPadding(r2);
        this.expandedLargeLabel.setIncludeFontPadding(r2);
        requestLayout();
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setOnlyShowWhenExpanded(boolean r1) {
        this.onlyShowWhenExpanded = r1;
        updateVisibility();
    }

    public void setShifting(boolean r2) {
        if (this.isShifting == r2) goto L6;
        this.isShifting = r2;
        refreshChecked();
        return;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setShortcut(boolean r1, char r2) {
    }

    public void setTextAppearanceActive(int r2) {
        this.textAppearanceActive = r2;
        updateActiveLabelTextAppearance(this.largeLabel, r2);
    }

    public void setTextAppearanceActiveBoldEnabled(boolean r1) {
        this.boldText = r1;
        setTextAppearanceActive(this.textAppearanceActive);
        setHorizontalTextAppearanceActive(this.horizontalTextAppearanceActive);
        updateActiveLabelBoldness();
    }

    public void setTextAppearanceInactive(int r2) {
        this.textAppearanceInactive = r2;
        updateInactiveLabelTextAppearance(this.smallLabel, r2);
    }

    public void setTextColor(ColorStateList r2) {
        this.textColor = r2;
        if (r2 == null) goto L6;
        this.smallLabel.setTextColor(r2);
        this.largeLabel.setTextColor(r2);
        this.expandedSmallLabel.setTextColor(r2);
        this.expandedLargeLabel.setTextColor(r2);
        return;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public void setTitle(CharSequence r2) {
        this.smallLabel.setText(r2);
        this.largeLabel.setText(r2);
        this.expandedSmallLabel.setText(r2);
        this.expandedLargeLabel.setText(r2);
        MenuItemImpl r0 = this.itemData;
        if (r0 != null) goto L5;
    L6:
        setContentDescription(r2);
    L7:
        MenuItemImpl r02 = this.itemData;
        if (r02 != null) goto L10;
    L13:
        TooltipCompat.setTooltipText(this, r2);
        return;
    L10:
        if (TextUtils.isEmpty(r02.getTooltipText()) == true) goto L13;
        r2 = this.itemData.getTooltipText();
        goto L13
    L5:
        if (TextUtils.isEmpty(r0.getContentDescription()) == false) goto L7;
        goto L6
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuItemView
    public boolean showsIcon() {
        return true;
    }

    public void updateActiveIndicatorLayoutParams(int r5) {
        if (r5 <= 0) goto L4;
    L6:
        int r0 = Math.min(this.activeIndicatorDesiredWidth, r5 - (this.activeIndicatorMarginHorizontal * 2));
        int r1 = this.activeIndicatorDesiredHeight;
        if (this.itemIconGravity != 1) goto L16;
        int r52 = r5 - (this.activeIndicatorExpandedMarginHorizontal * 2);
        int r02 = this.activeIndicatorExpandedDesiredWidth;
        if (r02 != (-1)) goto L12;
    L10:
        r0 = r52;
        r1 = Math.max(this.activeIndicatorExpandedDesiredHeight, this.innerContentContainer.getMeasuredHeight());
        goto L16
    L12:
        if (r02 != (-2)) goto L14;
        r52 = this.contentContainer.getMeasuredWidth();
        goto L10
    L14:
        r52 = Math.min(r02, r52);
    L16:
        FrameLayout.LayoutParams r53 = (FrameLayout.LayoutParams) this.activeIndicatorView.getLayoutParams();
        if (isActiveIndicatorResizeableAndUnlabeled() == false) goto L19;
        r1 = r0;
    L19:
        r53.height = r1;
        r53.width = Math.max(0, r0);
        this.activeIndicatorView.setLayoutParams(r53);
        return;
    L4:
        if (getVisibility() != 0) goto L6;
    }
}
