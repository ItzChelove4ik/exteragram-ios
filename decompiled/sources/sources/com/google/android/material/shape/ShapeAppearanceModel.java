package com.google.android.material.shape;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public class ShapeAppearanceModel implements ShapeAppearance {
    public static final int CORNER_BOTTOM_LEFT = 4;
    public static final int CORNER_BOTTOM_RIGHT = 8;
    public static final int CORNER_TOP_LEFT = 1;
    public static final int CORNER_TOP_RIGHT = 2;
    public static final int NUM_CORNERS = 4;
    public static final CornerSize PILL = null;
    EdgeTreatment bottomEdge;
    CornerTreatment bottomLeftCorner;
    CornerSize bottomLeftCornerSize;
    CornerTreatment bottomRightCorner;
    CornerSize bottomRightCornerSize;
    EdgeTreatment leftEdge;
    EdgeTreatment rightEdge;
    EdgeTreatment topEdge;
    CornerTreatment topLeftCorner;
    CornerSize topLeftCornerSize;
    CornerTreatment topRightCorner;
    CornerSize topRightCornerSize;

    /* renamed from: com.google.android.material.shape.ShapeAppearanceModel$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private EdgeTreatment bottomEdge;
        private CornerTreatment bottomLeftCorner;
        private CornerSize bottomLeftCornerSize;
        private CornerTreatment bottomRightCorner;
        private CornerSize bottomRightCornerSize;
        private EdgeTreatment leftEdge;
        private EdgeTreatment rightEdge;
        private EdgeTreatment topEdge;
        private CornerTreatment topLeftCorner;
        private CornerSize topLeftCornerSize;
        private CornerTreatment topRightCorner;
        private CornerSize topRightCornerSize;

        public Builder() {
            this.topLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.topRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.bottomRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.bottomLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.rightEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.bottomEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.leftEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        }

        public Builder(ShapeAppearanceModel r3) {
            this.topLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.topRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.bottomRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.bottomLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.rightEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.bottomEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.leftEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
            this.topLeftCorner = r3.topLeftCorner;
            this.topRightCorner = r3.topRightCorner;
            this.bottomRightCorner = r3.bottomRightCorner;
            this.bottomLeftCorner = r3.bottomLeftCorner;
            this.topLeftCornerSize = r3.topLeftCornerSize;
            this.topRightCornerSize = r3.topRightCornerSize;
            this.bottomRightCornerSize = r3.bottomRightCornerSize;
            this.bottomLeftCornerSize = r3.bottomLeftCornerSize;
            this.topEdge = r3.topEdge;
            this.rightEdge = r3.rightEdge;
            this.bottomEdge = r3.bottomEdge;
            this.leftEdge = r3.leftEdge;
        }

        public static /* synthetic */ CornerTreatment access$100(Builder r0) {
            return r0.topLeftCorner;
        }

        public static /* synthetic */ EdgeTreatment access$1000(Builder r0) {
            return r0.rightEdge;
        }

        public static /* synthetic */ EdgeTreatment access$1100(Builder r0) {
            return r0.bottomEdge;
        }

        public static /* synthetic */ EdgeTreatment access$1200(Builder r0) {
            return r0.leftEdge;
        }

        public static /* synthetic */ CornerTreatment access$200(Builder r0) {
            return r0.topRightCorner;
        }

        public static /* synthetic */ CornerTreatment access$300(Builder r0) {
            return r0.bottomRightCorner;
        }

        public static /* synthetic */ CornerTreatment access$400(Builder r0) {
            return r0.bottomLeftCorner;
        }

        public static /* synthetic */ CornerSize access$500(Builder r0) {
            return r0.topLeftCornerSize;
        }

        public static /* synthetic */ CornerSize access$600(Builder r0) {
            return r0.topRightCornerSize;
        }

        public static /* synthetic */ CornerSize access$700(Builder r0) {
            return r0.bottomRightCornerSize;
        }

        public static /* synthetic */ CornerSize access$800(Builder r0) {
            return r0.bottomLeftCornerSize;
        }

        public static /* synthetic */ EdgeTreatment access$900(Builder r0) {
            return r0.topEdge;
        }

        private static float compatCornerTreatmentSize(CornerTreatment r1) {
            if ((r1 instanceof RoundedCornerTreatment) == false) goto L7;
            return ((RoundedCornerTreatment) r1).radius;
        L7:
            if ((r1 instanceof CutCornerTreatment) == true) goto L9;
            return -1.0f;
        L9:
            return ((CutCornerTreatment) r1).size;
        }

        public ShapeAppearanceModel build() {
            return new ShapeAppearanceModel(this, null);
        }

        public Builder setAllCornerSizes(float r1) {
            return setTopLeftCornerSize(r1).setTopRightCornerSize(r1).setBottomRightCornerSize(r1).setBottomLeftCornerSize(r1);
        }

        public Builder setAllCornerSizes(CornerSize r1) {
            return setTopLeftCornerSize(r1).setTopRightCornerSize(r1).setBottomRightCornerSize(r1).setBottomLeftCornerSize(r1);
        }

        public Builder setAllCorners(int r1, float r2) {
            return setAllCorners(MaterialShapeUtils.createCornerTreatment(r1)).setAllCornerSizes(r2);
        }

        public Builder setAllCorners(CornerTreatment r1) {
            return setTopLeftCorner(r1).setTopRightCorner(r1).setBottomRightCorner(r1).setBottomLeftCorner(r1);
        }

        public Builder setAllEdges(EdgeTreatment r1) {
            return setLeftEdge(r1).setTopEdge(r1).setRightEdge(r1).setBottomEdge(r1);
        }

        public Builder setBottomEdge(EdgeTreatment r1) {
            this.bottomEdge = r1;
            return this;
        }

        public Builder setBottomLeftCorner(int r1, float r2) {
            return setBottomLeftCorner(MaterialShapeUtils.createCornerTreatment(r1)).setBottomLeftCornerSize(r2);
        }

        public Builder setBottomLeftCorner(int r1, CornerSize r2) {
            return setBottomLeftCorner(MaterialShapeUtils.createCornerTreatment(r1)).setBottomLeftCornerSize(r2);
        }

        public Builder setBottomLeftCorner(CornerTreatment r2) {
            this.bottomLeftCorner = r2;
            float r22 = compatCornerTreatmentSize(r2);
            if (r22 == (-1.0f)) goto L5;
            setBottomLeftCornerSize(r22);
        L5:
            return this;
        }

        public Builder setBottomLeftCornerSize(float r2) {
            this.bottomLeftCornerSize = new AbsoluteCornerSize(r2);
            return this;
        }

        public Builder setBottomLeftCornerSize(CornerSize r1) {
            this.bottomLeftCornerSize = r1;
            return this;
        }

        public Builder setBottomRightCorner(int r1, float r2) {
            return setBottomRightCorner(MaterialShapeUtils.createCornerTreatment(r1)).setBottomRightCornerSize(r2);
        }

        public Builder setBottomRightCorner(int r1, CornerSize r2) {
            return setBottomRightCorner(MaterialShapeUtils.createCornerTreatment(r1)).setBottomRightCornerSize(r2);
        }

        public Builder setBottomRightCorner(CornerTreatment r2) {
            this.bottomRightCorner = r2;
            float r22 = compatCornerTreatmentSize(r2);
            if (r22 == (-1.0f)) goto L5;
            setBottomRightCornerSize(r22);
        L5:
            return this;
        }

        public Builder setBottomRightCornerSize(float r2) {
            this.bottomRightCornerSize = new AbsoluteCornerSize(r2);
            return this;
        }

        public Builder setBottomRightCornerSize(CornerSize r1) {
            this.bottomRightCornerSize = r1;
            return this;
        }

        public Builder setCornerSizeOverride(int r2, CornerSize r3) {
            if (ShapeAppearanceModel.containsFlag(r2, 1) == false) goto L6;
            setTopLeftCornerSize(r3);
        L6:
            if (ShapeAppearanceModel.containsFlag(r2, 2) == false) goto L9;
            setTopRightCornerSize(r3);
        L9:
            if (ShapeAppearanceModel.containsFlag(r2, 4) == false) goto L12;
            setBottomLeftCornerSize(r3);
        L12:
            if (ShapeAppearanceModel.containsFlag(r2, 8) == false) goto L14;
            setBottomRightCornerSize(r3);
        L14:
            return this;
        }

        public Builder setLeftEdge(EdgeTreatment r1) {
            this.leftEdge = r1;
            return this;
        }

        public Builder setRightEdge(EdgeTreatment r1) {
            this.rightEdge = r1;
            return this;
        }

        public Builder setTopEdge(EdgeTreatment r1) {
            this.topEdge = r1;
            return this;
        }

        public Builder setTopLeftCorner(int r1, float r2) {
            return setTopLeftCorner(MaterialShapeUtils.createCornerTreatment(r1)).setTopLeftCornerSize(r2);
        }

        public Builder setTopLeftCorner(int r1, CornerSize r2) {
            return setTopLeftCorner(MaterialShapeUtils.createCornerTreatment(r1)).setTopLeftCornerSize(r2);
        }

        public Builder setTopLeftCorner(CornerTreatment r2) {
            this.topLeftCorner = r2;
            float r22 = compatCornerTreatmentSize(r2);
            if (r22 == (-1.0f)) goto L5;
            setTopLeftCornerSize(r22);
        L5:
            return this;
        }

        public Builder setTopLeftCornerSize(float r2) {
            this.topLeftCornerSize = new AbsoluteCornerSize(r2);
            return this;
        }

        public Builder setTopLeftCornerSize(CornerSize r1) {
            this.topLeftCornerSize = r1;
            return this;
        }

        public Builder setTopRightCorner(int r1, float r2) {
            return setTopRightCorner(MaterialShapeUtils.createCornerTreatment(r1)).setTopRightCornerSize(r2);
        }

        public Builder setTopRightCorner(int r1, CornerSize r2) {
            return setTopRightCorner(MaterialShapeUtils.createCornerTreatment(r1)).setTopRightCornerSize(r2);
        }

        public Builder setTopRightCorner(CornerTreatment r2) {
            this.topRightCorner = r2;
            float r22 = compatCornerTreatmentSize(r2);
            if (r22 == (-1.0f)) goto L5;
            setTopRightCornerSize(r22);
        L5:
            return this;
        }

        public Builder setTopRightCornerSize(float r2) {
            this.topRightCornerSize = new AbsoluteCornerSize(r2);
            return this;
        }

        public Builder setTopRightCornerSize(CornerSize r1) {
            this.topRightCornerSize = r1;
            return this;
        }
    }

    public interface CornerSizeUnaryOperator {
        CornerSize apply(CornerSize r1);
    }

    static {
        PILL = new RelativeCornerSize(0.5f);
    }

    public ShapeAppearanceModel() {
        this.topLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.topRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.bottomRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.bottomLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
        this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
        this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
        this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
        this.topEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.rightEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.bottomEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.leftEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
    }

    private ShapeAppearanceModel(Builder r2) {
        this.topLeftCorner = Builder.access$100(r2);
        this.topRightCorner = Builder.access$200(r2);
        this.bottomRightCorner = Builder.access$300(r2);
        this.bottomLeftCorner = Builder.access$400(r2);
        this.topLeftCornerSize = Builder.access$500(r2);
        this.topRightCornerSize = Builder.access$600(r2);
        this.bottomRightCornerSize = Builder.access$700(r2);
        this.bottomLeftCornerSize = Builder.access$800(r2);
        this.topEdge = Builder.access$900(r2);
        this.rightEdge = Builder.access$1000(r2);
        this.bottomEdge = Builder.access$1100(r2);
        this.leftEdge = Builder.access$1200(r2);
    }

    public /* synthetic */ ShapeAppearanceModel(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Context r1, int r2, int r3) {
        return builder(r1, r2, r3, 0);
    }

    private static Builder builder(Context r1, int r2, int r3, int r4) {
        return builder(r1, r2, r3, new AbsoluteCornerSize(r4));
    }

    private static Builder builder(Context r1, int r2, int r3, CornerSize r4) {
        ContextThemeWrapper r0 = new ContextThemeWrapper(r1, r2);
        if (r3 == 0) goto L6;
        r0.getTheme().applyStyle(r3, true);
    L6:
        return builder(r0.obtainStyledAttributes(R.styleable.ShapeAppearance), r4);
    }

    public static Builder builder(Context r1, AttributeSet r2, int r3, int r4) {
        return builder(r1, r2, r3, r4, 0);
    }

    public static Builder builder(Context r1, AttributeSet r2, int r3, int r4, int r5) {
        return builder(r1, r2, r3, r4, new AbsoluteCornerSize(r5));
    }

    public static Builder builder(Context r1, AttributeSet r2, int r3, int r4, CornerSize r5) {
        TypedArray r22 = r1.obtainStyledAttributes(r2, R.styleable.MaterialShape, r3, r4);
        int r32 = r22.getResourceId(R.styleable.MaterialShape_shapeAppearance, 0);
        int r42 = r22.getResourceId(R.styleable.MaterialShape_shapeAppearanceOverlay, 0);
        r22.recycle();
        return builder(r1, r32, r42, r5);
    }

    public static Builder builder(Resources.Theme r1, int r2) {
        return builder(r1.obtainStyledAttributes(r2, R.styleable.ShapeAppearance), new AbsoluteCornerSize(0.0f));
    }

    private static Builder builder(TypedArray r8, CornerSize r9) {
        int r0 = r8.getInt(R.styleable.ShapeAppearance_cornerFamily, 0);     // Catch: Throwable -> L5
        int r1 = r8.getInt(R.styleable.ShapeAppearance_cornerFamilyTopLeft, r0);     // Catch: Throwable -> L5
        int r2 = r8.getInt(R.styleable.ShapeAppearance_cornerFamilyTopRight, r0);     // Catch: Throwable -> L5
        int r3 = r8.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomRight, r0);     // Catch: Throwable -> L5
        int r02 = r8.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomLeft, r0);     // Catch: Throwable -> L5
        CornerSize r92 = getCornerSize(r8, R.styleable.ShapeAppearance_cornerSize, r9);     // Catch: Throwable -> L5
        CornerSize r4 = getCornerSize(r8, R.styleable.ShapeAppearance_cornerSizeTopLeft, r92);     // Catch: Throwable -> L5
        CornerSize r5 = getCornerSize(r8, R.styleable.ShapeAppearance_cornerSizeTopRight, r92);     // Catch: Throwable -> L5
        CornerSize r6 = getCornerSize(r8, R.styleable.ShapeAppearance_cornerSizeBottomRight, r92);     // Catch: Throwable -> L5
        CornerSize r93 = getCornerSize(r8, R.styleable.ShapeAppearance_cornerSizeBottomLeft, r92);     // Catch: Throwable -> L5
        Builder r94 = new Builder().setTopLeftCorner(r1, r4).setTopRightCorner(r2, r5).setBottomRightCorner(r3, r6).setBottomLeftCorner(r02, r93);     // Catch: Throwable -> L5
        r8.recycle();
        return r94;
    L5:
        th = move-exception;
        r8.recycle();
        throw th;
    }

    public static boolean containsFlag(int r0, int r1) {
        if ((r1 | r0) != r0) goto L6;
        return true;
    L6:
        return false;
    }

    public static CornerSize getCornerSize(TypedArray r2, int r3, CornerSize r4) {
        TypedValue r32 = r2.peekValue(r3);
        if (r32 == null) goto L13;
        int r0 = r32.type;
        if (r0 != 5) goto L10;
        return new AbsoluteCornerSize(TypedValue.complexToDimensionPixelSize(r32.data, r2.getResources().getDisplayMetrics()));
    L10:
        if (r0 != 6) goto L13;
        return new RelativeCornerSize(r32.getFraction(1.0f, 1.0f));
    L13:
        return r4;
    }

    public EdgeTreatment getBottomEdge() {
        return this.bottomEdge;
    }

    public CornerTreatment getBottomLeftCorner() {
        return this.bottomLeftCorner;
    }

    public CornerSize getBottomLeftCornerSize() {
        return this.bottomLeftCornerSize;
    }

    public CornerTreatment getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    public CornerSize getBottomRightCornerSize() {
        return this.bottomRightCornerSize;
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel getDefaultShape() {
        return this;
    }

    public EdgeTreatment getLeftEdge() {
        return this.leftEdge;
    }

    public EdgeTreatment getRightEdge() {
        return this.rightEdge;
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel[] getShapeAppearanceModels() {
        return new ShapeAppearanceModel[]{this};
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel getShapeForState(int[] r1) {
        return this;
    }

    public EdgeTreatment getTopEdge() {
        return this.topEdge;
    }

    public CornerTreatment getTopLeftCorner() {
        return this.topLeftCorner;
    }

    public CornerSize getTopLeftCornerSize() {
        return this.topLeftCornerSize;
    }

    public CornerTreatment getTopRightCorner() {
        return this.topRightCorner;
    }

    public CornerSize getTopRightCornerSize() {
        return this.topRightCornerSize;
    }

    public boolean hasRoundedCorners() {
        if ((this.topRightCorner instanceof RoundedCornerTreatment) == true) goto L5;
        return false;
    L5:
        if ((this.topLeftCorner instanceof RoundedCornerTreatment) == true) goto L7;
        return false;
    L7:
        if ((this.bottomRightCorner instanceof RoundedCornerTreatment) == true) goto L9;
        return false;
    L9:
        if ((this.bottomLeftCorner instanceof RoundedCornerTreatment) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public boolean isRoundRect(RectF r6) {
        if (this.leftEdge.getClass().equals(EdgeTreatment.class) == true) goto L5;
    L11:
        boolean r0 = false;
    L12:
        float r1 = this.topLeftCornerSize.getCornerSize(r6);
        if (this.topRightCornerSize.getCornerSize(r6) == r1) goto L15;
    L19:
        boolean r62 = false;
    L20:
        if (r0 == false) goto L25;
        if (r62 == false) goto L25;
        if (hasRoundedCorners() == false) goto L25;
        return true;
    L25:
        return false;
    L15:
        if (this.bottomLeftCornerSize.getCornerSize(r6) != r1) goto L19;
        if (this.bottomRightCornerSize.getCornerSize(r6) != r1) goto L19;
        r62 = true;
        goto L20
    L5:
        if (this.rightEdge.getClass().equals(EdgeTreatment.class) == false) goto L11;
        if (this.topEdge.getClass().equals(EdgeTreatment.class) == false) goto L11;
        if (this.bottomEdge.getClass().equals(EdgeTreatment.class) == false) goto L11;
        r0 = true;
        goto L12
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public boolean isStateful() {
        return false;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public String toString() {
        return "[" + getTopLeftCornerSize() + ", " + getTopRightCornerSize() + ", " + getBottomRightCornerSize() + ", " + getBottomLeftCornerSize() + "]";
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel withCornerSize(float r1) {
        return toBuilder().setAllCornerSizes(r1).build();
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel withCornerSize(CornerSize r1) {
        return toBuilder().setAllCornerSizes(r1).build();
    }

    public ShapeAppearanceModel withTransformedCornerSizes(CornerSizeUnaryOperator r3) {
        return toBuilder().setTopLeftCornerSize(r3.apply(getTopLeftCornerSize())).setTopRightCornerSize(r3.apply(getTopRightCornerSize())).setBottomLeftCornerSize(r3.apply(getBottomLeftCornerSize())).setBottomRightCornerSize(r3.apply(getBottomRightCornerSize())).build();
    }
}
