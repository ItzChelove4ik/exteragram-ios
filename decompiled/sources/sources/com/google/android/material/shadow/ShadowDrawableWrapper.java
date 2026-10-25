package com.google.android.material.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import androidx.core.content.ContextCompat;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;

@Deprecated
/* loaded from: classes5.dex */
public class ShadowDrawableWrapper extends DrawableWrapperCompat {
    static final double COS_45 = 0.0d;
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean addPaddingForCorners;
    final RectF contentBounds;
    float cornerRadius;
    final Paint cornerShadowPaint;
    Path cornerShadowPath;
    private boolean dirty;
    final Paint edgeShadowPaint;
    float maxShadowSize;
    private boolean printedShadowClipWarning;
    float rawMaxShadowSize;
    float rawShadowSize;
    private float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    float shadowSize;
    private final int shadowStartColor;

    static {
        COS_45 = Math.cos(Math.toRadians(45.0d));
    }

    public ShadowDrawableWrapper(Context r2, Drawable r3, float r4, float r5, float r6) {
        super(r3);
        this.dirty = true;
        this.addPaddingForCorners = true;
        this.printedShadowClipWarning = false;
        this.shadowStartColor = ContextCompat.getColor(r2, R.color.design_fab_shadow_start_color);
        this.shadowMiddleColor = ContextCompat.getColor(r2, R.color.design_fab_shadow_mid_color);
        this.shadowEndColor = ContextCompat.getColor(r2, R.color.design_fab_shadow_end_color);
        Paint r22 = new Paint(5);
        this.cornerShadowPaint = r22;
        r22.setStyle(Paint.Style.FILL);
        this.cornerRadius = Math.round(r4);
        this.contentBounds = new RectF();
        Paint r42 = new Paint(r22);
        this.edgeShadowPaint = r42;
        r42.setAntiAlias(false);
        setShadowSize(r5, r6);
    }

    private void buildComponents(Rect r7) {
        float r0 = this.rawMaxShadowSize;
        float r1 = SHADOW_MULTIPLIER * r0;
        this.contentBounds.set(r7.left + r0, r7.top + r1, r7.right - r0, r7.bottom - r1);
        Drawable r72 = getDrawable();
        RectF r02 = this.contentBounds;
        r72.setBounds((int) r02.left, (int) r02.top, (int) r02.right, (int) r02.bottom);
        buildShadowCorners();
    }

    private void buildShadowCorners() {
        float r1 = this.cornerRadius;
        RectF r0 = new RectF(-r1, -r1, r1, r1);
        RectF r12 = new RectF(r0);
        float r2 = this.shadowSize;
        r12.inset(-r2, -r2);
        Path r22 = this.cornerShadowPath;
        if (r22 != null) goto L5;
        this.cornerShadowPath = new Path();
    L6:
        this.cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.cornerShadowPath.moveTo(-this.cornerRadius, 0.0f);
        this.cornerShadowPath.rLineTo(-this.shadowSize, 0.0f);
        this.cornerShadowPath.arcTo(r12, 180.0f, 90.0f, false);
        this.cornerShadowPath.arcTo(r0, 270.0f, -90.0f, false);
        this.cornerShadowPath.close();
        float r10 = -r12.top;
        if (r10 <= 0.0f) goto L9;
        float r23 = this.cornerRadius / r10;
        this.cornerShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, r10, new int[]{0, this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, r23, ((1.0f - r23) / 2.0f) + r23, 1.0f}, Shader.TileMode.CLAMP));
    L9:
        this.edgeShadowPaint.setShader(new LinearGradient(0.0f, r0.top, 0.0f, r12.top, new int[]{this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, SHADOW_HORIZ_SCALE, 1.0f}, Shader.TileMode.CLAMP));
        this.edgeShadowPaint.setAntiAlias(false);
        return;
    L5:
        r22.reset();
        goto L6
    }

    public static float calculateHorizontalPadding(float r6, float r7, boolean r8) {
        if (r8 == true) goto L4;
        return r6;
    L4:
        return (float) (r6 + ((1.0d - COS_45) * r7));
    }

    public static float calculateVerticalPadding(float r6, float r7, boolean r8) {
        if (r8 == false) goto L7;
        return (float) ((r6 * SHADOW_MULTIPLIER) + ((1.0d - COS_45) * r7));
    L7:
        return r6 * SHADOW_MULTIPLIER;
    }

    private void drawShadow(Canvas r19) {
        Canvas r1 = r19;
        int r7 = r1.save();
        r1.rotate(this.rotation, this.contentBounds.centerX(), this.contentBounds.centerY());
        float r8 = this.cornerRadius;
        float r3 = (-r8) - this.shadowSize;
        float r9 = r8 * 2.0f;
        if ((this.contentBounds.width() - r9) <= 0.0f) goto L5;
        boolean r10 = true;
    L7:
        if ((this.contentBounds.height() - r9) <= 0.0f) goto L9;
        boolean r11 = true;
    L10:
        float r2 = this.rawShadowSize;
        float r4 = r2 - (SHADOW_TOP_SCALE * r2);
        float r13 = r8 / ((r2 - (SHADOW_HORIZ_SCALE * r2)) + r8);
        float r14 = r8 / (r4 + r8);
        float r15 = r8 / ((r2 - (r2 * 1.0f)) + r8);
        int r22 = r1.save();
        RectF r42 = this.contentBounds;
        r1.translate(r42.left + r8, r42.top + r8);
        r1.scale(r13, r14);
        r1.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (r10 == false) goto L13;
        r1.scale(1.0f / r13, 1.0f);
        float r17 = 1.0f;
        int r12 = r22;
        r1.drawRect(0.0f, r3, this.contentBounds.width() - r9, -this.cornerRadius, this.edgeShadowPaint);
    L14:
        r1.restoreToCount(r12);
        int r122 = r1.save();
        RectF r23 = this.contentBounds;
        r1.translate(r23.right - r8, r23.bottom - r8);
        r1.scale(r13, r15);
        r1.rotate(180.0f);
        r1.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (r10 == false) goto L17;
        r1.scale(r17 / r13, r17);
        r1.drawRect(0.0f, r3, this.contentBounds.width() - r9, this.shadowSize + (-this.cornerRadius), this.edgeShadowPaint);
    L17:
        r1.restoreToCount(r122);
        int r102 = r1.save();
        RectF r24 = this.contentBounds;
        r1.translate(r24.left + r8, r24.bottom - r8);
        r1.scale(r13, r15);
        r1.rotate(270.0f);
        r1.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (r11 == false) goto L20;
        r1.scale(1.0f / r15, 1.0f);
        r1.drawRect(0.0f, r3, this.contentBounds.height() - r9, -this.cornerRadius, this.edgeShadowPaint);
    L20:
        r1.restoreToCount(r102);
        int r6 = r1.save();
        RectF r25 = this.contentBounds;
        r1.translate(r25.right - r8, r25.top + r8);
        r1.scale(r13, r14);
        r1.rotate(90.0f);
        r1.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (r11 == false) goto L23;
        r1.scale(1.0f / r14, 1.0f);
        r19.drawRect(0.0f, r3, this.contentBounds.height() - r9, -this.cornerRadius, this.edgeShadowPaint);
        r1 = r19;
    L23:
        r1.restoreToCount(r6);
        r1.restoreToCount(r7);
        return;
    L13:
        r17 = 1.0f;
        r12 = r22;
        goto L14
    L9:
        r11 = false;
        goto L10
    L5:
        r10 = false;
        goto L7
    }

    private static int toEven(float r2) {
        int r22 = Math.round(r2);
        if ((r22 % 2) == 1) goto L5;
        return r22;
    L5:
        return r22 - 1;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public void draw(Canvas r2) {
        if (this.dirty == false) goto L5;
        buildComponents(getBounds());
        this.dirty = false;
    L5:
        drawShadow(r2);
        super.draw(r2);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public float getMaxShadowSize() {
        return this.rawMaxShadowSize;
    }

    public float getMinHeight() {
        float r0 = this.rawMaxShadowSize;
        return (Math.max(r0, this.cornerRadius + ((r0 * SHADOW_MULTIPLIER) / 2.0f)) * 2.0f) + ((this.rawMaxShadowSize * SHADOW_MULTIPLIER) * 2.0f);
    }

    public float getMinWidth() {
        float r0 = this.rawMaxShadowSize;
        return (Math.max(r0, this.cornerRadius + (r0 / 2.0f)) * 2.0f) + (this.rawMaxShadowSize * 2.0f);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public boolean getPadding(Rect r4) {
        int r0 = (int) Math.ceil(calculateVerticalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        int r3 = (int) Math.ceil(calculateHorizontalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        r4.set(r3, r0, r3, r0);
        return true;
    }

    public float getShadowSize() {
        return this.rawShadowSize;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        this.dirty = true;
    }

    public void setAddPaddingForCorners(boolean r1) {
        this.addPaddingForCorners = r1;
        invalidateSelf();
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        super.setAlpha(r2);
        this.cornerShadowPaint.setAlpha(r2);
        this.edgeShadowPaint.setAlpha(r2);
    }

    public void setCornerRadius(float r2) {
        float r22 = Math.round(r2);
        if (this.cornerRadius != r22) goto L5;
        return;
    L5:
        this.cornerRadius = r22;
        this.dirty = true;
        invalidateSelf();
    }

    public void setMaxShadowSize(float r2) {
        setShadowSize(this.rawShadowSize, r2);
    }

    public final void setRotation(float r2) {
        if (this.rotation == r2) goto L6;
        this.rotation = r2;
        invalidateSelf();
        return;
    }

    public void setShadowSize(float r2) {
        setShadowSize(r2, this.rawMaxShadowSize);
    }

    public void setShadowSize(float r3, float r4) {
        if (r3 >= 0.0f) goto L5;
    L19:
        f$$ExternalSyntheticBUOutline1.m("invalid shadow size");
        return;
    L5:
        if (r4 < 0.0f) goto L19;
        float r32 = toEven(r3);
        float r42 = toEven(r4);
        if (r32 <= r42) goto L13;
        if (this.printedShadowClipWarning == true) goto L11;
        this.printedShadowClipWarning = true;
    L11:
        r32 = r42;
    L13:
        if (this.rawShadowSize == r32) goto L15;
    L17:
        this.rawShadowSize = r32;
        this.rawMaxShadowSize = r42;
        this.shadowSize = Math.round(r32 * SHADOW_MULTIPLIER);
        this.maxShadowSize = r42;
        this.dirty = true;
        invalidateSelf();
        return;
    L15:
        if (this.rawMaxShadowSize != r42) goto L17;
    }
}
