package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* loaded from: classes5.dex */
class CutoutDrawable extends MaterialShapeDrawable {
    CutoutDrawableState drawableState;

    /* renamed from: com.google.android.material.textfield.CutoutDrawable$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class CutoutDrawableState extends MaterialShapeDrawable.MaterialShapeDrawableState {
        private final RectF cutoutBounds;

        private CutoutDrawableState(ShapeAppearanceModel r2, RectF r3) {
            super(r2, null);
            this.cutoutBounds = r3;
        }

        public /* synthetic */ CutoutDrawableState(ShapeAppearanceModel r1, RectF r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        private CutoutDrawableState(CutoutDrawableState r1) {
            super(r1);
            this.cutoutBounds = r1.cutoutBounds;
        }

        public /* synthetic */ CutoutDrawableState(CutoutDrawableState r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ RectF access$200(CutoutDrawableState r0) {
            return r0.cutoutBounds;
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable.MaterialShapeDrawableState, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            CutoutDrawable r0 = CutoutDrawable.access$400(this);
            r0.invalidateSelf();
            return r0;
        }
    }

    public static class ImplApi18 extends CutoutDrawable {
        public ImplApi18(CutoutDrawableState r2) {
            super(r2, null);
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable
        public void drawStrokeShape(Canvas r4) {
            if (CutoutDrawableState.access$200(this.drawableState).isEmpty() == false) goto L6;
            super.drawStrokeShape(r4);
            return;
        L6:
            r4.save();
            int r0 = Build.VERSION.SDK_INT;
            CutoutDrawableState r1 = this.drawableState;
            if (r0 < 26) goto L9;
            r4.clipOutRect(CutoutDrawableState.access$200(r1));
        L10:
            super.drawStrokeShape(r4);
            r4.restore();
            return;
        L9:
            r4.clipRect(CutoutDrawableState.access$200(r1), Region.Op.DIFFERENCE);
            goto L10
        }
    }

    private CutoutDrawable(CutoutDrawableState r1) {
        super(r1);
        this.drawableState = r1;
    }

    public /* synthetic */ CutoutDrawable(CutoutDrawableState r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static /* synthetic */ CutoutDrawable access$400(CutoutDrawableState r0) {
        return create(r0);
    }

    public static CutoutDrawable create(ShapeAppearanceModel r3) {
        if (r3 != null) goto L7;
        r3 = new ShapeAppearanceModel();
    L7:
        return create(new CutoutDrawableState(r3, new RectF(), null));
    }

    private static CutoutDrawable create(CutoutDrawableState r1) {
        return new ImplApi18(r1);
    }

    public boolean hasCutout() {
        return !CutoutDrawableState.access$200(this.drawableState).isEmpty();
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.drawableState = new CutoutDrawableState(this.drawableState, null);
        return this;
    }

    public void removeCutout() {
        setCutout(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void setCutout(float r2, float r3, float r4, float r5) {
        if (r2 == CutoutDrawableState.access$200(this.drawableState).left) goto L5;
    L12:
        CutoutDrawableState.access$200(this.drawableState).set(r2, r3, r4, r5);
        invalidateSelf();
        return;
    L5:
        if (r3 != CutoutDrawableState.access$200(this.drawableState).top) goto L12;
        if (r4 != CutoutDrawableState.access$200(this.drawableState).right) goto L12;
        if (r5 != CutoutDrawableState.access$200(this.drawableState).bottom) goto L12;
    }

    public void setCutout(RectF r4) {
        setCutout(r4.left, r4.top, r4.right, r4.bottom);
    }
}
