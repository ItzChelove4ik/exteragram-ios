package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    public static final int BADGE_CONTENT_NOT_TRUNCATED = -2;
    public static final int BADGE_FIXED_EDGE_END = 1;
    public static final int BADGE_FIXED_EDGE_START = 0;
    static final int BADGE_RADIUS_NOT_SPECIFIED = -1;

    @Deprecated
    public static final int BOTTOM_END = 8388693;

    @Deprecated
    public static final int BOTTOM_START = 8388691;
    static final String DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX = "+";
    static final String DEFAULT_EXCEED_MAX_BADGE_TEXT_SUFFIX = "…";
    private static final int DEFAULT_STYLE = 0;
    private static final int DEFAULT_THEME_ATTR = 0;
    private static final float FONT_SCALE_THRESHOLD = 0.3f;
    static final int OFFSET_ALIGNMENT_MODE_EDGE = 0;
    static final int OFFSET_ALIGNMENT_MODE_LEGACY = 1;
    private static final String TAG = "Badge";
    public static final int TOP_END = 8388661;
    public static final int TOP_START = 8388659;
    private WeakReference<View> anchorViewRef;
    private final Rect badgeBounds;
    private float badgeCenterX;
    private float badgeCenterY;
    private final WeakReference<Context> contextRef;
    private float cornerRadius;
    private WeakReference<FrameLayout> customBadgeParentRef;
    private float halfBadgeHeight;
    private float halfBadgeWidth;
    private int maxBadgeNumber;
    private final MaterialShapeDrawable shapeDrawable;
    private final BadgeState state;
    private final TextDrawableHelper textDrawableHelper;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeGravity {
    }

    static {
        DEFAULT_STYLE = R.style.Widget_MaterialComponents_Badge;
        DEFAULT_THEME_ATTR = R.attr.badgeStyle;
    }

    private BadgeDrawable(Context r9, int r10, int r11, int r12, BadgeState.State r13) {
        this.contextRef = new WeakReference(r9);
        ThemeEnforcement.checkMaterialTheme(r9);
        this.badgeBounds = new Rect();
        TextDrawableHelper r0 = new TextDrawableHelper(this);
        this.textDrawableHelper = r0;
        r0.getTextPaint().setTextAlign(Paint.Align.CENTER);
        BadgeState r2 = new BadgeState(r9, r10, r11, r12, r13);
        this.state = r2;
        if (hasBadgeContent() == false) goto L5;
        int r102 = r2.getBadgeWithTextShapeAppearanceResId();
    L7:
        if (hasBadgeContent() == false) goto L9;
        int r112 = r2.getBadgeWithTextShapeAppearanceOverlayResId();
    L10:
        this.shapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(r9, r102, r112).build());
        restoreState();
        return;
    L9:
        r112 = r2.getBadgeShapeAppearanceOverlayResId();
        goto L10
    L5:
        r102 = r2.getBadgeShapeAppearanceResId();
        goto L7
    }

    private void autoAdjustWithinGrandparentBounds(View r3) {
        ViewParent r0 = getCustomBadgeParent();
        if (r0 != null) goto L6;
        r0 = r3.getParent();
    L6:
        if ((r0 instanceof View) == true) goto L8;
        return;
    L8:
        if ((r0.getParent() instanceof View) == false) goto L12;
        autoAdjustWithinViewBounds(r3, (View) r0.getParent());
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.view.ViewParent] */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.view.ViewParent] */
    /* JADX WARN: Type inference failed for: r0v9 */
    private void autoAdjustWithinViewBounds(View r8, View r9) {
        FrameLayout r0 = getCustomBadgeParent();
        if (r0 != null) goto L5;
        float r02 = r8.getY();
        float r2 = r8.getX();
        View r03 = r8.getParent();
        float r82 = r02;
    L6:
        boolean r3 = r03 instanceof View;
        if (r3 == false) goto L15;
        if (r03 == r9) goto L15;
        ViewParent r4 = r03.getParent();
        if ((r4 instanceof ViewGroup) == false) goto L15;
        if (((ViewGroup) r4).getClipChildren() == true) goto L15;
        View r32 = r03;
        r82 = r82 + r32.getY();
        r2 = r2 + r32.getX();
        r03 = r03.getParent();
    L15:
        if (r3 == false) goto L36;
        float r92 = getTopCutOff(r82);
        float r33 = getLeftCutOff(r2);
        View r04 = r03;
        float r83 = getBottomCutOff(r04.getHeight(), r82);
        float r05 = getRightCutoff(r04.getWidth(), r2);
        if (r92 >= 0.0f) goto L21;
        this.badgeCenterY += Math.abs(r92);
    L21:
        if (r33 >= 0.0f) goto L24;
        this.badgeCenterX += Math.abs(r33);
    L24:
        if (r83 <= 0.0f) goto L27;
        this.badgeCenterY -= Math.abs(r83);
    L27:
        if (r05 <= 0.0f) goto L35;
        this.badgeCenterX -= Math.abs(r05);
        return;
    L35:
        return;
    L36:
        return;
    L5:
        r82 = 0.0f;
        r2 = 0.0f;
        r03 = r0;
        goto L6
    }

    private void calculateCenterAndBounds(Rect r6, View r7) {
        boolean r0 = hasBadgeContent();
        BadgeState r1 = this.state;
        if (r0 == false) goto L5;
        float r02 = r1.badgeWithTextRadius;
    L6:
        this.cornerRadius = r02;
        if (r02 == (-1.0f)) goto L9;
        this.halfBadgeWidth = r02;
        this.halfBadgeHeight = r02;
    L21:
        if (hasBadgeContent() == false) goto L23;
        String r03 = getBadgeContent();
        this.halfBadgeWidth = Math.max(this.halfBadgeWidth, (this.textDrawableHelper.getTextWidth(r03) / 2.0f) + this.state.getBadgeHorizontalPadding());
        float r04 = Math.max(this.halfBadgeHeight, (this.textDrawableHelper.getTextHeight(r03) / 2.0f) + this.state.getBadgeVerticalPadding());
        this.halfBadgeHeight = r04;
        this.halfBadgeWidth = Math.max(this.halfBadgeWidth, r04);
    L23:
        int r05 = getTotalVerticalOffsetForState();
        int r12 = this.state.getBadgeGravity();
        if (r12 != 8388691) goto L26;
    L28:
        this.badgeCenterY = r6.bottom - r05;
    L29:
        int r06 = getTotalHorizontalOffsetForState();
        int r13 = this.state.getBadgeGravity();
        if (r13 == 8388659) goto L44;
        if (r13 == 8388691) goto L44;
        if (this.state.badgeFixedEdge != 0) goto L39;
        if (r7.getLayoutDirection() != 0) goto L37;
        float r62 = (r6.right + this.halfBadgeWidth) - r06;
    L42:
        this.badgeCenterX = r62;
    L55:
        if (this.state.isAutoAdjustedToGrandparentBounds() == false) goto L58;
        autoAdjustWithinGrandparentBounds(r7);
        return;
    L58:
        autoAdjustWithinViewBounds(r7, null);
        return;
    L37:
        r62 = (r6.left - this.halfBadgeWidth) + r06;
        goto L42
    L39:
        if (r7.getLayoutDirection() != 0) goto L41;
        r62 = (r6.right - this.halfBadgeWidth) + ((this.halfBadgeHeight * 2.0f) - r06);
        goto L42
    L41:
        r62 = (r6.left + this.halfBadgeWidth) - ((this.halfBadgeHeight * 2.0f) - r06);
    L44:
        if (this.state.badgeFixedEdge != 0) goto L50;
        if (r7.getLayoutDirection() != 0) goto L48;
        float r63 = (r6.left + this.halfBadgeWidth) - ((this.halfBadgeHeight * 2.0f) - r06);
    L53:
        this.badgeCenterX = r63;
        goto L55
    L48:
        r63 = (r6.right - this.halfBadgeWidth) + ((this.halfBadgeHeight * 2.0f) - r06);
        goto L53
    L50:
        if (r7.getLayoutDirection() != 0) goto L52;
        r63 = (r6.left - this.halfBadgeWidth) + r06;
        goto L53
    L52:
        r63 = (r6.right + this.halfBadgeWidth) - r06;
        goto L53
    L26:
        if (r12 == 8388693) goto L28;
        this.badgeCenterY = r6.top + r05;
        goto L29
    L9:
        boolean r07 = hasBadgeContent();
        BadgeState r14 = this.state;
        if (r07 == false) goto L13;
        float r08 = r14.badgeWithTextWidth;
    L14:
        this.halfBadgeWidth = Math.round(r08 / 2.0f);
        boolean r09 = hasBadgeContent();
        BadgeState r15 = this.state;
        if (r09 == false) goto L18;
        float r010 = r15.badgeWithTextHeight;
    L19:
        this.halfBadgeHeight = Math.round(r010 / 2.0f);
        goto L21
    L18:
        r010 = r15.badgeHeight;
        goto L19
    L13:
        r08 = r14.badgeWidth;
        goto L14
    L5:
        r02 = r1.badgeRadius;
        goto L6
    }

    public static BadgeDrawable create(Context r6) {
        return new BadgeDrawable(r6, 0, DEFAULT_THEME_ATTR, DEFAULT_STYLE, null);
    }

    public static BadgeDrawable createFromResource(Context r6, int r7) {
        return new BadgeDrawable(r6, r7, DEFAULT_THEME_ATTR, DEFAULT_STYLE, null);
    }

    public static BadgeDrawable createFromSavedState(Context r6, BadgeState.State r7) {
        return new BadgeDrawable(r6, 0, DEFAULT_THEME_ATTR, DEFAULT_STYLE, r7);
    }

    private void drawBadgeContent(Canvas r6) {
        String r0 = getBadgeContent();
        if (r0 == null) goto L11;
        Rect r1 = new Rect();
        this.textDrawableHelper.getTextPaint().getTextBounds(r0, 0, r0.length(), r1);
        float r2 = this.badgeCenterY - r1.exactCenterY();
        float r3 = this.badgeCenterX;
        if (r1.bottom > 0) goto L8;
        int r12 = (int) r2;
    L9:
        r6.drawText(r0, r3, r12, this.textDrawableHelper.getTextPaint());
        return;
    L8:
        r12 = Math.round(r2);
        goto L9
    }

    private String getBadgeContent() {
        if (hasText() == false) goto L7;
        return getTextBadgeText();
    L7:
        if (hasNumber() == true) goto L9;
        return null;
    L9:
        return getNumberBadgeText();
    }

    private float getBottomCutOff(float r2, float r3) {
        return ((this.badgeCenterY + this.halfBadgeHeight) - r2) + r3;
    }

    private CharSequence getEmptyContentDescription() {
        return this.state.getContentDescriptionNumberless();
    }

    private float getLeftCutOff(float r2) {
        return (this.badgeCenterX - this.halfBadgeWidth) + r2;
    }

    private String getNumberBadgeText() {
        if (this.maxBadgeNumber == (-2)) goto L14;
        if (getNumber() <= this.maxBadgeNumber) goto L14;
        Context r0 = this.contextRef.get();
        if (r0 != null) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return String.format(this.state.getNumberLocale(), r0.getString(R.string.mtrl_exceed_max_badge_number_suffix), new Object[]{Integer.valueOf(this.maxBadgeNumber), DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX});
    L14:
        return NumberFormat.getInstance(this.state.getNumberLocale()).format(getNumber());
    }

    private String getNumberContentDescription() {
        if (this.state.getContentDescriptionQuantityStrings() == 0) goto L16;
        Context r0 = this.contextRef.get();
        if (r0 != null) goto L8;
        return null;
    L8:
        if (this.maxBadgeNumber == (-2)) goto L15;
        if (getNumber() <= this.maxBadgeNumber) goto L15;
        return r0.getString(this.state.getContentDescriptionExceedsMaxBadgeNumberStringResource(), new Object[]{Integer.valueOf(this.maxBadgeNumber)});
    L15:
        return r0.getResources().getQuantityString(this.state.getContentDescriptionQuantityStrings(), getNumber(), new Object[]{Integer.valueOf(getNumber())});
    L16:
        return null;
    }

    private float getRightCutoff(float r2, float r3) {
        return ((this.badgeCenterX + this.halfBadgeWidth) - r2) + r3;
    }

    private String getTextBadgeText() {
        String r0 = getText();
        int r1 = getMaxCharacterCount();
        if (r1 == (-2)) goto L14;
        if (r0 == null) goto L14;
        if (r0.length() <= r1) goto L14;
        Context r3 = this.contextRef.get();
        if (r3 != null) goto L13;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return String.format(r3.getString(R.string.m3_exceed_max_badge_text_suffix), new Object[]{r0.substring(0, r1 - 1), DEFAULT_EXCEED_MAX_BADGE_TEXT_SUFFIX});
    L14:
        return r0;
    }

    private CharSequence getTextContentDescription() {
        CharSequence r0 = this.state.getContentDescriptionForText();
        if (r0 == null) goto L6;
        return r0;
    L6:
        return getText();
    }

    private float getTopCutOff(float r2) {
        return (this.badgeCenterY - this.halfBadgeHeight) + r2;
    }

    private int getTotalHorizontalOffsetForState() {
        boolean r0 = hasBadgeContent();
        BadgeState r1 = this.state;
        if (r0 == false) goto L5;
        int r02 = r1.getHorizontalOffsetWithText();
    L7:
        if (this.state.offsetAlignmentMode != 1) goto L14;
        boolean r12 = hasBadgeContent();
        BadgeState r2 = this.state;
        if (r12 == false) goto L11;
        int r13 = r2.horizontalInsetWithText;
    L12:
        r02 = r02 + r13;
        goto L14
    L11:
        r13 = r2.horizontalInset;
    L14:
        return r02 + this.state.getAdditionalHorizontalOffset();
    L5:
        r02 = r1.getHorizontalOffsetWithoutText();
        goto L7
    }

    private int getTotalVerticalOffsetForState() {
        int r0 = this.state.getVerticalOffsetWithoutText();
        if (hasBadgeContent() == false) goto L8;
        r0 = this.state.getVerticalOffsetWithText();
        Context r1 = this.contextRef.get();
        if (r1 == null) goto L8;
        float r12 = AnimationUtils.lerp(0.0f, 1.0f, FONT_SCALE_THRESHOLD, 1.0f, MaterialResources.getFontScale(r1) - 1.0f);
        r0 = AnimationUtils.lerp(r0, r0 - this.state.getLargeFontVerticalOffsetAdjustment(), r12);
    L8:
        if (this.state.offsetAlignmentMode != 0) goto L11;
        r0 = r0 - Math.round(this.halfBadgeHeight);
    L11:
        return r0 + this.state.getAdditionalVerticalOffset();
    }

    private boolean hasBadgeContent() {
        if (hasText() == false) goto L5;
        return true;
    L5:
        if (hasNumber() == true) goto L11;
        return false;
    L11:
        return true;
    }

    private void onAlphaUpdated() {
        this.textDrawableHelper.getTextPaint().setAlpha(getAlpha());
        invalidateSelf();
    }

    private void onBackgroundColorUpdated() {
        ColorStateList r0 = ColorStateList.valueOf(this.state.getBackgroundColor());
        if (this.shapeDrawable.getFillColor() == r0) goto L6;
        this.shapeDrawable.setFillColor(r0);
        invalidateSelf();
        return;
    }

    private void onBadgeContentUpdated() {
        this.textDrawableHelper.setTextSizeDirty(true);
        onBadgeShapeAppearanceUpdated();
        updateCenterAndBounds();
        invalidateSelf();
    }

    private void onBadgeGravityUpdated() {
        WeakReference<View> r0 = this.anchorViewRef;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.get() == null) goto L13;
        View r02 = this.anchorViewRef.get();
        WeakReference<FrameLayout> r1 = this.customBadgeParentRef;
        if (r1 == null) goto L9;
        FrameLayout r12 = r1.get();
    L10:
        updateBadgeCoordinates(r02, r12);
        return;
    L9:
        r12 = null;
        goto L10
    }

    private void onBadgeShapeAppearanceUpdated() {
        Context r0 = this.contextRef.get();
        if (r0 != null) goto L5;
        return;
    L5:
        MaterialShapeDrawable r1 = this.shapeDrawable;
        boolean r2 = hasBadgeContent();
        BadgeState r3 = this.state;
        if (r2 == false) goto L8;
        int r22 = r3.getBadgeWithTextShapeAppearanceResId();
    L9:
        boolean r32 = hasBadgeContent();
        BadgeState r4 = this.state;
        if (r32 == false) goto L12;
        int r33 = r4.getBadgeWithTextShapeAppearanceOverlayResId();
    L13:
        r1.setShapeAppearanceModel(ShapeAppearanceModel.builder(r0, r22, r33).build());
        invalidateSelf();
        return;
    L12:
        r33 = r4.getBadgeShapeAppearanceOverlayResId();
        goto L13
    L8:
        r22 = r3.getBadgeShapeAppearanceResId();
        goto L9
    }

    private void onBadgeTextAppearanceUpdated() {
        Context r0 = this.contextRef.get();
        if (r0 == null) goto L10;
        TextAppearance r1 = new TextAppearance(r0, this.state.getTextAppearanceResId());
        if (this.textDrawableHelper.getTextAppearance() != r1) goto L8;
        return;
    L8:
        this.textDrawableHelper.setTextAppearance(r1, r0);
        onBadgeTextColorUpdated();
        updateCenterAndBounds();
        invalidateSelf();
        return;
    }

    private void onBadgeTextColorUpdated() {
        this.textDrawableHelper.getTextPaint().setColor(this.state.getBadgeTextColor());
        invalidateSelf();
    }

    private void onMaxBadgeLengthUpdated() {
        updateMaxBadgeNumber();
        this.textDrawableHelper.setTextSizeDirty(true);
        updateCenterAndBounds();
        invalidateSelf();
    }

    private void onNumberUpdated() {
        if (hasText() == true) goto L6;
        onBadgeContentUpdated();
        return;
    }

    private void onTextUpdated() {
        onBadgeContentUpdated();
    }

    private void onVisibilityUpdated() {
        setVisible(this.state.isVisible(), false);
    }

    private void restoreState() {
        onBadgeShapeAppearanceUpdated();
        onBadgeTextAppearanceUpdated();
        onMaxBadgeLengthUpdated();
        onBadgeContentUpdated();
        onAlphaUpdated();
        onBackgroundColorUpdated();
        onBadgeTextColorUpdated();
        onBadgeGravityUpdated();
        updateCenterAndBounds();
        onVisibilityUpdated();
    }

    private static void updateAnchorParentToNotClip(View r1) {
        ViewGroup r12 = (ViewGroup) r1.getParent();
        r12.setClipChildren(false);
        r12.setClipToPadding(false);
    }

    private void updateCenterAndBounds() {
        Context r0 = this.contextRef.get();
        WeakReference<View> r1 = this.anchorViewRef;
        FrameLayout r2 = null;
        if (r1 == null) goto L5;
        View r12 = r1.get();
    L6:
        if (r0 == null) goto L21;
        if (r12 == null) goto L23;
        Rect r02 = new Rect();
        r02.set(this.badgeBounds);
        Rect r3 = new Rect();
        r12.getDrawingRect(r3);
        WeakReference<FrameLayout> r4 = this.customBadgeParentRef;
        if (r4 == null) goto L12;
        r2 = r4.get();
    L12:
        if (r2 == null) goto L14;
        r2.offsetDescendantRectToMyCoords(r12, r3);
    L14:
        calculateCenterAndBounds(r3, r12);
        BadgeUtils.updateBadgeBounds(this.badgeBounds, this.badgeCenterX, this.badgeCenterY, this.halfBadgeWidth, this.halfBadgeHeight);
        float r13 = this.cornerRadius;
        if (r13 == (-1.0f)) goto L18;
        this.shapeDrawable.setCornerSize(r13);
    L18:
        if (r02.equals(this.badgeBounds) == true) goto L22;
        this.shapeDrawable.setBounds(this.badgeBounds);
        return;
    L22:
        return;
    L23:
        return;
    L21:
        return;
    L5:
        r12 = null;
        goto L6
    }

    private void updateMaxBadgeNumber() {
        if (getMaxCharacterCount() == (-2)) goto L6;
        this.maxBadgeNumber = ((int) Math.pow(10.0d, getMaxCharacterCount() - 1.0d)) - 1;
        return;
    L6:
        this.maxBadgeNumber = getMaxNumber();
    }

    public void clearNumber() {
        if (this.state.hasNumber() == false) goto L6;
        this.state.clearNumber();
        onNumberUpdated();
        return;
    }

    public void clearText() {
        if (this.state.hasText() == false) goto L6;
        this.state.clearText();
        onTextUpdated();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r2) {
        if (getBounds().isEmpty() == false) goto L5;
        return;
    L5:
        if (getAlpha() != 0) goto L7;
        return;
    L7:
        if (isVisible() == false) goto L16;
        this.shapeDrawable.draw(r2);
        if (hasBadgeContent() == false) goto L15;
        drawBadgeContent(r2);
        return;
    L15:
        return;
    }

    public int getAdditionalHorizontalOffset() {
        return this.state.getAdditionalHorizontalOffset();
    }

    public int getAdditionalVerticalOffset() {
        return this.state.getAdditionalVerticalOffset();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.state.getAlpha();
    }

    public int getBackgroundColor() {
        return this.shapeDrawable.getFillColor().getDefaultColor();
    }

    public int getBadgeGravity() {
        return this.state.getBadgeGravity();
    }

    public Locale getBadgeNumberLocale() {
        return this.state.getNumberLocale();
    }

    public int getBadgeTextColor() {
        return this.textDrawableHelper.getTextPaint().getColor();
    }

    public CharSequence getContentDescription() {
        if (isVisible() == true) goto L7;
        return null;
    L7:
        if (hasText() == false) goto L11;
        return getTextContentDescription();
    L11:
        if (hasNumber() == false) goto L15;
        return getNumberContentDescription();
    L15:
        return getEmptyContentDescription();
    }

    public FrameLayout getCustomBadgeParent() {
        WeakReference<FrameLayout> r0 = this.customBadgeParentRef;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.get();
    }

    public int getHorizontalOffset() {
        return this.state.getHorizontalOffsetWithoutText();
    }

    public int getHorizontalOffsetWithText() {
        return this.state.getHorizontalOffsetWithText();
    }

    public int getHorizontalOffsetWithoutText() {
        return this.state.getHorizontalOffsetWithoutText();
    }

    public int getHorizontalPadding() {
        return this.state.getBadgeHorizontalPadding();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    public int getLargeFontVerticalOffsetAdjustment() {
        return this.state.getLargeFontVerticalOffsetAdjustment();
    }

    public int getMaxCharacterCount() {
        return this.state.getMaxCharacterCount();
    }

    public int getMaxNumber() {
        return this.state.getMaxNumber();
    }

    public int getNumber() {
        if (this.state.hasNumber() == true) goto L5;
        return 0;
    L5:
        return this.state.getNumber();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public BadgeState.State getSavedState() {
        return this.state.getOverridingState();
    }

    public String getText() {
        return this.state.getText();
    }

    public int getVerticalOffset() {
        return this.state.getVerticalOffsetWithoutText();
    }

    public int getVerticalOffsetWithText() {
        return this.state.getVerticalOffsetWithText();
    }

    public int getVerticalOffsetWithoutText() {
        return this.state.getVerticalOffsetWithoutText();
    }

    public int getVerticalPadding() {
        return this.state.getBadgeVerticalPadding();
    }

    public boolean hasNumber() {
        if (this.state.hasText() == false) goto L5;
        return false;
    L5:
        if (this.state.hasNumber() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasText() {
        return this.state.hasText();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] r1) {
        return super.onStateChange(r1);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        invalidateSelf();
    }

    public void setAdditionalHorizontalOffset(int r2) {
        this.state.setAdditionalHorizontalOffset(r2);
        updateCenterAndBounds();
    }

    public void setAdditionalVerticalOffset(int r2) {
        this.state.setAdditionalVerticalOffset(r2);
        updateCenterAndBounds();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.state.setAlpha(r2);
        onAlphaUpdated();
    }

    @Deprecated
    public void setAutoAdjustToWithinGrandparentBounds(boolean r2) {
        if (this.state.isAutoAdjustedToGrandparentBounds() == r2) goto L13;
        this.state.setAutoAdjustToGrandparentBounds(r2);
        WeakReference<View> r22 = this.anchorViewRef;
        if (r22 != null) goto L8;
        return;
    L8:
        if (r22.get() == null) goto L12;
        autoAdjustWithinGrandparentBounds(this.anchorViewRef.get());
        return;
    L12:
        return;
    }

    public void setBackgroundColor(int r2) {
        this.state.setBackgroundColor(r2);
        onBackgroundColorUpdated();
    }

    public void setBadgeFixedEdge(int r3) {
        BadgeState r0 = this.state;
        if (r0.badgeFixedEdge == r3) goto L6;
        r0.badgeFixedEdge = r3;
        updateCenterAndBounds();
        return;
    }

    public void setBadgeGravity(int r3) {
        if (r3 != 8388691) goto L5;
    L6:
        Log.w(TAG, "Bottom badge gravities are deprecated; please use a top gravity instead.");
    L8:
        if (this.state.getBadgeGravity() == r3) goto L11;
        this.state.setBadgeGravity(r3);
        onBadgeGravityUpdated();
        return;
    L11:
        return;
    L5:
        if (r3 != 8388693) goto L8;
        goto L6
    }

    public void setBadgeNumberLocale(Locale r2) {
        if (r2.equals(this.state.getNumberLocale()) == true) goto L6;
        this.state.setNumberLocale(r2);
        invalidateSelf();
        return;
    }

    public void setBadgeTextColor(int r2) {
        if (this.textDrawableHelper.getTextPaint().getColor() == r2) goto L6;
        this.state.setBadgeTextColor(r2);
        onBadgeTextColorUpdated();
        return;
    }

    public void setBadgeWithTextShapeAppearance(int r2) {
        this.state.setBadgeWithTextShapeAppearanceResId(r2);
        onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithTextShapeAppearanceOverlay(int r2) {
        this.state.setBadgeWithTextShapeAppearanceOverlayResId(r2);
        onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithoutTextShapeAppearance(int r2) {
        this.state.setBadgeShapeAppearanceResId(r2);
        onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithoutTextShapeAppearanceOverlay(int r2) {
        this.state.setBadgeShapeAppearanceOverlayResId(r2);
        onBadgeShapeAppearanceUpdated();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setContentDescriptionExceedsMaxBadgeNumberStringResource(int r1) {
        this.state.setContentDescriptionExceedsMaxBadgeNumberStringResource(r1);
    }

    public void setContentDescriptionForText(CharSequence r1) {
        this.state.setContentDescriptionForText(r1);
    }

    public void setContentDescriptionNumberless(CharSequence r1) {
        this.state.setContentDescriptionNumberless(r1);
    }

    public void setContentDescriptionQuantityStringsResource(int r1) {
        this.state.setContentDescriptionQuantityStringsResource(r1);
    }

    public void setHorizontalOffset(int r1) {
        setHorizontalOffsetWithoutText(r1);
        setHorizontalOffsetWithText(r1);
    }

    public void setHorizontalOffsetWithText(int r2) {
        this.state.setHorizontalOffsetWithText(r2);
        updateCenterAndBounds();
    }

    public void setHorizontalOffsetWithoutText(int r2) {
        this.state.setHorizontalOffsetWithoutText(r2);
        updateCenterAndBounds();
    }

    public void setHorizontalPadding(int r2) {
        if (r2 == this.state.getBadgeHorizontalPadding()) goto L6;
        this.state.setBadgeHorizontalPadding(r2);
        updateCenterAndBounds();
        return;
    }

    public void setLargeFontVerticalOffsetAdjustment(int r2) {
        this.state.setLargeFontVerticalOffsetAdjustment(r2);
        updateCenterAndBounds();
    }

    public void setMaxCharacterCount(int r2) {
        if (this.state.getMaxCharacterCount() == r2) goto L6;
        this.state.setMaxCharacterCount(r2);
        onMaxBadgeLengthUpdated();
        return;
    }

    public void setMaxNumber(int r2) {
        if (this.state.getMaxNumber() == r2) goto L6;
        this.state.setMaxNumber(r2);
        onMaxBadgeLengthUpdated();
        return;
    }

    public void setNumber(int r2) {
        int r22 = Math.max(0, r2);
        if (this.state.getNumber() == r22) goto L6;
        this.state.setNumber(r22);
        onNumberUpdated();
        return;
    }

    public void setText(String r2) {
        if (TextUtils.equals(this.state.getText(), r2) == true) goto L6;
        this.state.setText(r2);
        onTextUpdated();
        return;
    }

    public void setTextAppearance(int r2) {
        this.state.setTextAppearanceResId(r2);
        onBadgeTextAppearanceUpdated();
    }

    public void setVerticalOffset(int r1) {
        setVerticalOffsetWithoutText(r1);
        setVerticalOffsetWithText(r1);
    }

    public void setVerticalOffsetWithText(int r2) {
        this.state.setVerticalOffsetWithText(r2);
        updateCenterAndBounds();
    }

    public void setVerticalOffsetWithoutText(int r2) {
        this.state.setVerticalOffsetWithoutText(r2);
        updateCenterAndBounds();
    }

    public void setVerticalPadding(int r2) {
        if (r2 == this.state.getBadgeVerticalPadding()) goto L6;
        this.state.setBadgeVerticalPadding(r2);
        updateCenterAndBounds();
        return;
    }

    public void setVisible(boolean r2) {
        this.state.setVisible(r2);
        onVisibilityUpdated();
    }

    public void updateBadgeCoordinates(View r2) {
        updateBadgeCoordinates(r2, null);
    }

    @Deprecated
    public void updateBadgeCoordinates(View r2, ViewGroup r3) {
        if ((r3 instanceof FrameLayout) == false) goto L6;
        updateBadgeCoordinates(r2, (FrameLayout) r3);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("customBadgeParent must be a FrameLayout");
    }

    public void updateBadgeCoordinates(View r2, FrameLayout r3) {
        this.anchorViewRef = new WeakReference(r2);
        this.customBadgeParentRef = new WeakReference(r3);
        updateAnchorParentToNotClip(r2);
        updateCenterAndBounds();
        invalidateSelf();
    }
}
