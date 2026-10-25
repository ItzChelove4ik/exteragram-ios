package com.google.android.material.shape;

import android.graphics.Outline;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;

/* loaded from: classes5.dex */
class ShapeableDelegateV22 extends ShapeableDelegate {
    private boolean canUseViewOutline;
    private float cornerRadius;

    /* renamed from: com.google.android.material.shape.ShapeableDelegateV22$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewOutlineProvider {
        final /* synthetic */ ShapeableDelegateV22 this$0;

        public AnonymousClass1(ShapeableDelegateV22 r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r8, Outline r9) {
            ShapeableDelegateV22 r82 = this.this$0;
            if (r82.shapeAppearanceModel != null) goto L5;
            return;
        L5:
            if (r82.maskBounds.isEmpty() == true) goto L9;
            ShapeableDelegateV22 r7 = this.this$0;
            RectF r83 = r7.maskBounds;
            r9.setRoundRect((int) r83.left, (int) r83.top, (int) r83.right, (int) r83.bottom, ShapeableDelegateV22.access$000(r7));
            return;
        }
    }

    public ShapeableDelegateV22(View r2) {
        this.canUseViewOutline = false;
        this.cornerRadius = 0.0f;
        initMaskOutlineProvider(r2);
    }

    public static /* synthetic */ float access$000(ShapeableDelegateV22 r0) {
        return r0.cornerRadius;
    }

    private float getDefaultCornerRadius() {
        ShapeAppearanceModel r0 = this.shapeAppearanceModel;
        if (r0 == null) goto L9;
        RectF r1 = this.maskBounds;
        if (r1 != null) goto L8;
        return 0.0f;
    L8:
        return r0.topRightCornerSize.getCornerSize(r1);
    L9:
        return 0.0f;
    }

    private void initMaskOutlineProvider(View r2) {
        r2.setOutlineProvider(new AnonymousClass1(this));
    }

    private boolean isShapeRoundRect() {
        if (this.maskBounds.isEmpty() == true) goto L9;
        ShapeAppearanceModel r0 = this.shapeAppearanceModel;
        if (r0 != null) goto L8;
        return false;
    L8:
        return r0.isRoundRect(this.maskBounds);
    L9:
        return false;
    }

    private boolean offsetZeroCornerEdgeBoundsIfPossible() {
        if (this.maskBounds.isEmpty() == true) goto L42;
        ShapeAppearanceModel r0 = this.shapeAppearanceModel;
        if (r0 == null) goto L42;
        if (this.offsetZeroCornerEdgeBoundsEnabled == false) goto L42;
        if (r0.isRoundRect(this.maskBounds) == true) goto L42;
        if (shapeUsesAllRoundedCornerTreatments(this.shapeAppearanceModel) == false) goto L42;
        float r02 = this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.maskBounds);
        float r2 = this.shapeAppearanceModel.getTopRightCornerSize().getCornerSize(this.maskBounds);
        float r3 = this.shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(this.maskBounds);
        float r4 = this.shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(this.maskBounds);
        if (r02 == 0.0f) goto L16;
    L20:
        if (r02 != 0.0f) goto L27;
        if (r2 != 0.0f) goto L27;
        if (r3 != r4) goto L27;
        RectF r03 = this.maskBounds;
        r03.set(r03.left, r03.top - r3, r03.right, r03.bottom);
        this.cornerRadius = r3;
        return true;
    L27:
        if (r2 != 0.0f) goto L34;
        if (r4 != 0.0f) goto L34;
        if (r02 != r3) goto L34;
        RectF r1 = this.maskBounds;
        r1.set(r1.left, r1.top, r1.right + r02, r1.bottom);
        this.cornerRadius = r02;
        return true;
    L34:
        if (r3 != 0.0f) goto L42;
        if (r4 != 0.0f) goto L42;
        if (r02 != r2) goto L42;
        RectF r12 = this.maskBounds;
        r12.set(r12.left, r12.top, r12.right, r12.bottom + r02);
        this.cornerRadius = r02;
        return true;
    L16:
        if (r3 != 0.0f) goto L20;
        if (r2 != r4) goto L20;
        RectF r04 = this.maskBounds;
        r04.set(r04.left - r2, r04.top, r04.right, r04.bottom);
        this.cornerRadius = r2;
        return true;
    L42:
        return false;
    }

    private static boolean shapeUsesAllRoundedCornerTreatments(ShapeAppearanceModel r1) {
        if ((r1.getTopLeftCorner() instanceof RoundedCornerTreatment) == true) goto L5;
        return false;
    L5:
        if ((r1.getTopRightCorner() instanceof RoundedCornerTreatment) == true) goto L7;
        return false;
    L7:
        if ((r1.getBottomLeftCorner() instanceof RoundedCornerTreatment) == true) goto L9;
        return false;
    L9:
        if ((r1.getBottomRightCorner() instanceof RoundedCornerTreatment) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    public void invalidateClippingMethod(View r3) {
        this.cornerRadius = getDefaultCornerRadius();
        if (isShapeRoundRect() == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        this.canUseViewOutline = r0;
        r3.setClipToOutline(!shouldUseCompatClipping());
        if (shouldUseCompatClipping() == false) goto L13;
        r3.invalidate();
        return;
    L13:
        r3.invalidateOutline();
        return;
    L5:
        if (offsetZeroCornerEdgeBoundsIfPossible() == true) goto L8;
        r0 = false;
        goto L9
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    public boolean shouldUseCompatClipping() {
        if (this.canUseViewOutline == true) goto L5;
        return true;
    L5:
        if (this.forceCompatClippingEnabled == true) goto L11;
        return false;
    L11:
        return true;
    }
}
