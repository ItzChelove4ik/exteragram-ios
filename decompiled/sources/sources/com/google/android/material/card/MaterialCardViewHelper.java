package com.google.android.material.card;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R$attr;
import androidx.cardview.R$style;
import androidx.cardview.R$styleable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.StateListShapeAppearanceModel;

/* loaded from: classes5.dex */
class MaterialCardViewHelper {
    private static final float CARD_VIEW_SHADOW_MULTIPLIER = 1.5f;
    private static final int CHECKED_ICON_LAYER_INDEX = 2;
    private static final Drawable CHECKED_ICON_NONE = null;
    private static final double COS_45 = 0.0d;
    public static final int DEFAULT_FADE_ANIM_DURATION = 300;
    private static final int DEFAULT_STROKE_VALUE = -1;
    private static final int NOT_SET = -1;
    private final MaterialShapeDrawable bgDrawable;
    private float cardCornerRadius;
    private boolean checkable;
    private float checkedAnimationProgress;
    private Drawable checkedIcon;
    private int checkedIconGravity;
    private int checkedIconMargin;
    private int checkedIconSize;
    private ColorStateList checkedIconTint;
    private LayerDrawable clickableForegroundDrawable;
    private Drawable fgDrawable;
    private final MaterialShapeDrawable foregroundContentDrawable;
    private MaterialShapeDrawable foregroundShapeDrawable;
    private ValueAnimator iconAnimator;
    private final TimeInterpolator iconFadeAnimInterpolator;
    private final int iconFadeInAnimDuration;
    private final int iconFadeOutAnimDuration;
    private boolean isBackgroundOverwritten;
    private final MaterialCardView materialCardView;
    private ColorStateList rippleColor;
    private Drawable rippleDrawable;
    private ShapeAppearance shapeAppearanceModel;
    private ColorStateList strokeColor;
    private int strokeWidth;
    private final Rect userContentPadding;

    /* renamed from: com.google.android.material.card.MaterialCardViewHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends InsetDrawable {
        final /* synthetic */ MaterialCardViewHelper this$0;

        public AnonymousClass1(MaterialCardViewHelper r1, Drawable r2, int r3, int r4, int r5, int r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return -1;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return -1;
        }

        @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public boolean getPadding(Rect r1) {
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$6Fls0xe6HKTW6UQK8StrqpOvnl8(MaterialCardViewHelper r2, ValueAnimator r3) {
        r2.getClass();
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        Drawable r1 = r2.checkedIcon;
        r1.setAlpha((int) (255.0f * r32));
        r2.checkedAnimationProgress = r32;
    }

    static {
        COS_45 = Math.cos(Math.toRadians(45.0d));
        if (Build.VERSION.SDK_INT > 28) goto L5;
        ColorDrawable r0 = new ColorDrawable();
    L6:
        CHECKED_ICON_NONE = r0;
        return;
    L5:
        r0 = null;
        goto L6
    }

    public MaterialCardViewHelper(MaterialCardView r5, AttributeSet r6, int r7, int r8) {
        this.userContentPadding = new Rect();
        this.cardCornerRadius = -1.0f;
        this.isBackgroundOverwritten = false;
        this.checkedAnimationProgress = 0.0f;
        this.materialCardView = r5;
        TypedArray r1 = r5.getContext().obtainStyledAttributes(r6, R$styleable.CardView, r7, R$style.CardView);
        MaterialShapeDrawable r2 = new MaterialShapeDrawable(r5.getContext(), r6, r7, r8);
        this.bgDrawable = r2;
        r2.initializeElevationOverlay(r5.getContext());
        r2.setShadowColor(-12303292);
        ShapeAppearanceModel.Builder r62 = r2.getShapeAppearanceModel().toBuilder();
        if (r1.hasValue(R$styleable.CardView_cardCornerRadius) == false) goto L5;
        float r72 = r1.getDimension(R$styleable.CardView_cardCornerRadius, 0.0f);
        this.cardCornerRadius = r72;
        r62.setAllCornerSizes(r72);
    L5:
        this.foregroundContentDrawable = new MaterialShapeDrawable();
        setShapeAppearance(r62.build());
        this.iconFadeAnimInterpolator = MotionUtils.resolveThemeInterpolator(r5.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.iconFadeInAnimDuration = MotionUtils.resolveThemeDuration(r5.getContext(), R.attr.motionDurationShort2, 300);
        this.iconFadeOutAnimDuration = MotionUtils.resolveThemeDuration(r5.getContext(), R.attr.motionDurationShort1, 300);
        r1.recycle();
    }

    private float calculateActualCornerPadding() {
        ShapeAppearanceModel[] r0 = this.shapeAppearanceModel.getShapeAppearanceModels();
        int r1 = r0.length;
        float r2 = 0.0f;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        ShapeAppearanceModel r4 = r0[r3];
        if (r4 == null) goto L7;
        r2 = Math.max(r2, getMaxCornerPadding(r4));
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        return r2;
    }

    private float calculateCornerPaddingForCornerTreatment(CornerTreatment r3, float r4) {
        if ((r3 instanceof RoundedCornerTreatment) == false) goto L7;
        return (float) ((1.0d - COS_45) * r4);
    L7:
        if ((r3 instanceof CutCornerTreatment) == true) goto L9;
        return 0.0f;
    L9:
        return r4 / 2.0f;
    }

    private float calculateHorizontalBackgroundPadding() {
        float r0 = this.materialCardView.getMaxCardElevation();
        if (shouldAddCornerPaddingOutsideCardBackground() == false) goto L5;
        float r2 = calculateActualCornerPadding();
    L7:
        return r0 + r2;
    L5:
        r2 = 0.0f;
        goto L7
    }

    private float calculateVerticalBackgroundPadding() {
        float r0 = this.materialCardView.getMaxCardElevation() * CARD_VIEW_SHADOW_MULTIPLIER;
        if (shouldAddCornerPaddingOutsideCardBackground() == false) goto L5;
        float r2 = calculateActualCornerPadding();
    L7:
        return r0 + r2;
    L5:
        r2 = 0.0f;
        goto L7
    }

    private boolean canClipToOutline() {
        return this.bgDrawable.isRoundRect();
    }

    private Drawable createForegroundRippleDrawable() {
        this.foregroundShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
        return new RippleDrawable(this.rippleColor, null, this.foregroundShapeDrawable);
    }

    private SpringForce createSpringForce(Context r2) {
        return MotionUtils.resolveThemeSpringForce(r2, R.attr.motionSpringFastSpatial, R.style.Motion_Material3_Spring_Standard_Fast_Spatial);
    }

    private Drawable getClickableForeground() {
        if (this.rippleDrawable != null) goto L6;
        this.rippleDrawable = createForegroundRippleDrawable();
    L6:
        if (this.clickableForegroundDrawable != null) goto L9;
        LayerDrawable r0 = new LayerDrawable(new Drawable[]{this.rippleDrawable, this.foregroundContentDrawable, this.checkedIcon});
        FocusRingDrawable.layer(this.materialCardView.getContext(), r0, this.foregroundShapeDrawable);
        r0.setId(2, R.id.mtrl_card_checked_layer_id);
        this.clickableForegroundDrawable = r0;
    L9:
        return this.clickableForegroundDrawable;
    }

    private float getMaxCornerPadding(ShapeAppearanceModel r4) {
        return Math.max(Math.max(calculateCornerPaddingForCornerTreatment(r4.getTopLeftCorner(), this.bgDrawable.getTopLeftCornerResolvedSize()), calculateCornerPaddingForCornerTreatment(r4.getTopRightCorner(), this.bgDrawable.getTopRightCornerResolvedSize())), Math.max(calculateCornerPaddingForCornerTreatment(r4.getBottomRightCorner(), this.bgDrawable.getBottomRightCornerResolvedSize()), calculateCornerPaddingForCornerTreatment(r4.getBottomLeftCorner(), this.bgDrawable.getBottomLeftCornerResolvedSize())));
    }

    private float getParentCardViewCalculatedCornerPadding() {
        if (this.materialCardView.getPreventCornerOverlap() == true) goto L5;
        return 0.0f;
    L5:
        if (this.materialCardView.getUseCompatPadding() == true) goto L7;
        return 0.0f;
    L7:
        return (float) ((1.0d - COS_45) * this.materialCardView.getCardViewRadius());
    }

    private Drawable insetDrawable(Drawable r9) {
        if (this.materialCardView.getUseCompatPadding() == false) goto L5;
        int r5 = (int) Math.ceil(calculateVerticalBackgroundPadding());
        int r4 = (int) Math.ceil(calculateHorizontalBackgroundPadding());
    L7:
        return new AnonymousClass1(this, r9, r4, r5, r4, r5);
    L5:
        r4 = 0;
        r5 = 0;
        goto L7
    }

    private boolean isCheckedIconBottom() {
        if ((this.checkedIconGravity & 80) != 80) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isCheckedIconEnd() {
        if ((this.checkedIconGravity & 8388613) != 8388613) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean shouldAddCornerPaddingInsideCardBackground() {
        if (this.materialCardView.getPreventCornerOverlap() == true) goto L5;
        return false;
    L5:
        if (canClipToOutline() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean shouldAddCornerPaddingOutsideCardBackground() {
        if (this.materialCardView.getPreventCornerOverlap() == true) goto L5;
        return false;
    L5:
        if (canClipToOutline() == true) goto L7;
        return false;
    L7:
        if (this.materialCardView.getUseCompatPadding() == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean shouldUseClickableForeground() {
        if (this.materialCardView.isClickable() == false) goto L6;
        return true;
    L6:
        View r1 = this.materialCardView;
    L8:
        if (r1.isDuplicateParentStateEnabled() == false) goto L13;
        if ((r1.getParent() instanceof View) == false) goto L13;
        r1 = (View) r1.getParent();
    L13:
        return r1.isClickable();
    }

    private void updateInsetForeground(Drawable r3) {
        boolean r0 = this.materialCardView.getForeground() instanceof InsetDrawable;
        MaterialCardView r1 = this.materialCardView;
        if (r0 == false) goto L6;
        ((InsetDrawable) r1.getForeground()).setDrawable(r3);
        return;
    L6:
        r1.setForeground(insetDrawable(r3));
    }

    private void updateRippleColor() {
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L6;
        ((RippleDrawable) r0).setColor(this.rippleColor);
        return;
    }

    public void animateCheckedIcon(boolean r6) {
        if (r6 == false) goto L5;
        float r1 = 1.0f;
    L6:
        float r2 = this.checkedAnimationProgress;
        if (r6 == false) goto L9;
        r2 = 1.0f - r2;
    L9:
        ValueAnimator r0 = this.iconAnimator;
        if (r0 == null) goto L12;
        r0.cancel();
        this.iconAnimator = null;
    L12:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.checkedAnimationProgress, r1});
        this.iconAnimator = r02;
        r02.addUpdateListener(new MaterialCardViewHelper$$ExternalSyntheticLambda0(this));
        this.iconAnimator.setInterpolator(this.iconFadeAnimInterpolator);
        ValueAnimator r03 = this.iconAnimator;
        if (r6 == false) goto L16;
        int r62 = this.iconFadeInAnimDuration;
    L17:
        r03.setDuration((long) (r62 * r2));
        this.iconAnimator.start();
        return;
    L16:
        r62 = this.iconFadeOutAnimDuration;
        goto L17
    L5:
        r1 = 0.0f;
        goto L6
    }

    public void forceRippleRedraw() {
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L6;
        Rect r02 = r0.getBounds();
        int r1 = r02.bottom;
        this.rippleDrawable.setBounds(r02.left, r02.top, r02.right, r1 - 1);
        this.rippleDrawable.setBounds(r02.left, r02.top, r02.right, r1);
        return;
    }

    public MaterialShapeDrawable getBackground() {
        return this.bgDrawable;
    }

    public ColorStateList getCardBackgroundColor() {
        return this.bgDrawable.getFillColor();
    }

    public ColorStateList getCardForegroundColor() {
        return this.foregroundContentDrawable.getFillColor();
    }

    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    public int getCheckedIconGravity() {
        return this.checkedIconGravity;
    }

    public int getCheckedIconMargin() {
        return this.checkedIconMargin;
    }

    public int getCheckedIconSize() {
        return this.checkedIconSize;
    }

    public ColorStateList getCheckedIconTint() {
        return this.checkedIconTint;
    }

    public float getCornerRadius() {
        return this.bgDrawable.getTopLeftCornerResolvedSize();
    }

    public float getProgress() {
        return this.bgDrawable.getInterpolation();
    }

    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public ShapeAppearance getShapeAppearance() {
        return this.shapeAppearanceModel;
    }

    public int getStrokeColor() {
        ColorStateList r0 = this.strokeColor;
        if (r0 != null) goto L7;
        return -1;
    L7:
        return r0.getDefaultColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.strokeColor;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public Rect getUserContentPadding() {
        return this.userContentPadding;
    }

    public boolean isBackgroundOverwritten() {
        return this.isBackgroundOverwritten;
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    public void loadFromAttributes(TypedArray r4) {
        ColorStateList r0 = MaterialResources.getColorStateList(this.materialCardView.getContext(), r4, R.styleable.MaterialCardView_strokeColor);
        this.strokeColor = r0;
        if (r0 != null) goto L5;
        this.strokeColor = ColorStateList.valueOf(-1);
    L5:
        this.strokeWidth = r4.getDimensionPixelSize(R.styleable.MaterialCardView_strokeWidth, 0);
        boolean r02 = r4.getBoolean(R.styleable.MaterialCardView_android_checkable, false);
        this.checkable = r02;
        this.materialCardView.setLongClickable(r02);
        this.checkedIconTint = MaterialResources.getColorStateList(this.materialCardView.getContext(), r4, R.styleable.MaterialCardView_checkedIconTint);
        setCheckedIcon(MaterialResources.getDrawable(this.materialCardView.getContext(), r4, R.styleable.MaterialCardView_checkedIcon));
        setCheckedIconSize(r4.getDimensionPixelSize(R.styleable.MaterialCardView_checkedIconSize, 0));
        setCheckedIconMargin(r4.getDimensionPixelSize(R.styleable.MaterialCardView_checkedIconMargin, 0));
        this.checkedIconGravity = r4.getInteger(R.styleable.MaterialCardView_checkedIconGravity, 8388661);
        ColorStateList r03 = MaterialResources.getColorStateList(this.materialCardView.getContext(), r4, R.styleable.MaterialCardView_rippleColor);
        this.rippleColor = r03;
        if (r03 != null) goto L8;
        this.rippleColor = ColorStateList.valueOf(MaterialColors.getColor(this.materialCardView, R$attr.colorControlHighlight));
    L8:
        setCardForegroundColor(MaterialResources.getColorStateList(this.materialCardView.getContext(), r4, R.styleable.MaterialCardView_cardForegroundColor));
        updateRippleColor();
        updateElevation();
        updateStroke();
        this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
        if (shouldUseClickableForeground() == false) goto L11;
        Drawable r04 = getClickableForeground();
    L12:
        this.fgDrawable = r04;
        this.materialCardView.setForeground(insetDrawable(r04));
        if (this.cardCornerRadius != (-1.0f)) goto L21;
        StateListShapeAppearanceModel r42 = StateListShapeAppearanceModel.create(this.materialCardView.getContext(), r4, R.styleable.MaterialCardView_shapeAppearance);
        if (r42 == null) goto L22;
        SpringForce r05 = createSpringForce(this.materialCardView.getContext());
        this.bgDrawable.setCornerSpringForce(r05);
        this.foregroundContentDrawable.setCornerSpringForce(r05);
        MaterialShapeDrawable r1 = this.foregroundShapeDrawable;
        if (r1 == null) goto L19;
        r1.setCornerSpringForce(r05);
    L19:
        setShapeAppearance(r42);
        return;
    L22:
        return;
    L21:
        return;
    L11:
        r04 = this.foregroundContentDrawable;
        goto L12
    }

    public void recalculateCheckedIconPosition(int r12, int r13) {
        if (this.clickableForegroundDrawable != null) goto L5;
        return;
    L5:
        if (this.materialCardView.getUseCompatPadding() == false) goto L7;
        int r0 = (int) Math.ceil(calculateVerticalBackgroundPadding() * 2.0f);
        int r1 = (int) Math.ceil(calculateHorizontalBackgroundPadding() * 2.0f);
    L8:
        boolean r2 = isCheckedIconEnd();
        int r3 = this.checkedIconMargin;
        if (r2 == false) goto L11;
        r3 = ((r12 - r3) - this.checkedIconSize) - r1;
    L11:
        boolean r22 = isCheckedIconBottom();
        int r4 = this.checkedIconMargin;
        if (r22 == false) goto L14;
    L13:
        int r10 = r4;
        boolean r23 = isCheckedIconEnd();
        int r42 = this.checkedIconMargin;
        if (r23 == true) goto L19;
        r42 = ((r12 - r42) - this.checkedIconSize) - r1;
    L19:
        boolean r122 = isCheckedIconBottom();
        int r14 = this.checkedIconMargin;
        if (r122 == false) goto L22;
        r14 = ((r13 - r14) - this.checkedIconSize) - r0;
    L22:
        int r8 = r14;
        if (this.materialCardView.getLayoutDirection() != 1) goto L25;
        int r9 = r3;
        int r7 = r42;
    L26:
        this.clickableForegroundDrawable.setLayerInset(2, r7, r8, r9, r10);
        return;
    L25:
        r7 = r3;
        r9 = r42;
        goto L26
    L14:
        r4 = ((r13 - r4) - this.checkedIconSize) - r0;
        goto L13
    L7:
        r0 = 0;
        r1 = 0;
        goto L8
    }

    public void setBackgroundOverwritten(boolean r1) {
        this.isBackgroundOverwritten = r1;
    }

    public void setCardBackgroundColor(ColorStateList r1) {
        this.bgDrawable.setFillColor(r1);
    }

    public void setCardForegroundColor(ColorStateList r1) {
        MaterialShapeDrawable r0 = this.foregroundContentDrawable;
        if (r1 != null) goto L5;
        r1 = ColorStateList.valueOf(0);
    L5:
        r0.setFillColor(r1);
    }

    public void setCheckable(boolean r1) {
        this.checkable = r1;
    }

    public void setChecked(boolean r2) {
        setChecked(r2, false);
    }

    public void setChecked(boolean r2, boolean r3) {
        Drawable r0 = this.checkedIcon;
        if (r0 == null) goto L16;
        if (r3 == false) goto L7;
        animateCheckedIcon(r2);
        return;
    L7:
        if (r2 == false) goto L9;
        int r32 = 255;
    L10:
        r0.setAlpha(r32);
        if (r2 == false) goto L13;
        float r22 = 1.0f;
    L14:
        this.checkedAnimationProgress = r22;
        return;
    L13:
        r22 = 0.0f;
        goto L14
    L9:
        r32 = 0;
        goto L10
    }

    public void setCheckedIcon(Drawable r2) {
        if (r2 == null) goto L4;
        Drawable r22 = DrawableCompat.wrap(r2).mutate();
        this.checkedIcon = r22;
        r22.setTintList(this.checkedIconTint);
        setChecked(this.materialCardView.isChecked());
    L5:
        LayerDrawable r23 = this.clickableForegroundDrawable;
        if (r23 == null) goto L9;
        r23.setDrawableByLayerId(R.id.mtrl_card_checked_layer_id, this.checkedIcon);
        return;
    L9:
        return;
    L4:
        this.checkedIcon = CHECKED_ICON_NONE;
        goto L5
    }

    public void setCheckedIconGravity(int r2) {
        this.checkedIconGravity = r2;
        recalculateCheckedIconPosition(this.materialCardView.getMeasuredWidth(), this.materialCardView.getMeasuredHeight());
    }

    public void setCheckedIconMargin(int r1) {
        this.checkedIconMargin = r1;
    }

    public void setCheckedIconSize(int r1) {
        this.checkedIconSize = r1;
    }

    public void setCheckedIconTint(ColorStateList r1) {
        this.checkedIconTint = r1;
        Drawable r0 = this.checkedIcon;
        if (r0 == null) goto L6;
        r0.setTintList(r1);
        return;
    }

    public void setCornerRadius(float r2) {
        this.cardCornerRadius = r2;
        setShapeAppearance(this.shapeAppearanceModel.getDefaultShape().withCornerSize(r2));
        this.fgDrawable.invalidateSelf();
        if (shouldAddCornerPaddingOutsideCardBackground() == false) goto L5;
    L6:
        updateContentPadding();
    L8:
        if (shouldAddCornerPaddingOutsideCardBackground() == false) goto L11;
        updateInsets();
        return;
    L11:
        return;
    L5:
        if (shouldAddCornerPaddingInsideCardBackground() == false) goto L8;
        goto L6
    }

    public void setProgress(float r2) {
        this.bgDrawable.setInterpolation(r2);
        MaterialShapeDrawable r0 = this.foregroundContentDrawable;
        if (r0 == null) goto L5;
        r0.setInterpolation(r2);
    L5:
        MaterialShapeDrawable r1 = this.foregroundShapeDrawable;
        if (r1 == null) goto L9;
        r1.setInterpolation(r2);
        return;
    }

    public void setRippleColor(ColorStateList r1) {
        this.rippleColor = r1;
        updateRippleColor();
    }

    public void setShapeAppearance(ShapeAppearance r2) {
        this.shapeAppearanceModel = r2;
        this.bgDrawable.setShapeAppearance(r2);
        this.foregroundContentDrawable.setShapeAppearance(r2);
        MaterialShapeDrawable r0 = this.foregroundShapeDrawable;
        if (r0 == null) goto L5;
        r0.setShapeAppearance(r2);
    L5:
        this.bgDrawable.setShadowBitmapDrawingEnable(!r1.isRoundRect());
    }

    public void setStrokeColor(ColorStateList r2) {
        if (this.strokeColor != r2) goto L5;
        return;
    L5:
        this.strokeColor = r2;
        updateStroke();
    }

    public void setStrokeWidth(int r2) {
        if (r2 != this.strokeWidth) goto L5;
        return;
    L5:
        this.strokeWidth = r2;
        updateStroke();
    }

    public void setUserContentPadding(int r2, int r3, int r4, int r5) {
        this.userContentPadding.set(r2, r3, r4, r5);
        updateContentPadding();
    }

    public void updateClickable() {
        Drawable r0 = this.fgDrawable;
        if (shouldUseClickableForeground() == false) goto L5;
        Drawable r1 = getClickableForeground();
    L6:
        this.fgDrawable = r1;
        if (r0 == r1) goto L10;
        updateInsetForeground(r1);
        return;
    L10:
        return;
    L5:
        r1 = this.foregroundContentDrawable;
        goto L6
    }

    public void updateContentPadding() {
        if (shouldAddCornerPaddingInsideCardBackground() == false) goto L5;
    L8:
        float r0 = calculateActualCornerPadding();
    L9:
        int r02 = (int) (r0 - getParentCardViewCalculatedCornerPadding());
        MaterialCardView r1 = this.materialCardView;
        Rect r5 = this.userContentPadding;
        r1.setAncestorContentPadding(r5.left + r02, r5.top + r02, r5.right + r02, r5.bottom + r02);
        return;
    L5:
        if (shouldAddCornerPaddingOutsideCardBackground() == true) goto L8;
        r0 = 0.0f;
        goto L9
    }

    public void updateElevation() {
        this.bgDrawable.setElevation(this.materialCardView.getCardElevation());
    }

    public void updateInsets() {
        if (isBackgroundOverwritten() == true) goto L5;
        this.materialCardView.setBackgroundInternal(insetDrawable(this.bgDrawable));
    L5:
        this.materialCardView.setForeground(insetDrawable(this.fgDrawable));
    }

    public void updateStroke() {
        this.foregroundContentDrawable.setStroke(this.strokeWidth, this.strokeColor);
    }
}
