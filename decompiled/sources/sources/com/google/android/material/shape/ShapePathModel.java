package com.google.android.material.shape;

@Deprecated
/* loaded from: classes5.dex */
public class ShapePathModel extends ShapeAppearanceModel {
    public ShapePathModel() {
    }

    @Deprecated
    public void setAllCorners(CornerTreatment r1) {
        this.topLeftCorner = r1;
        this.topRightCorner = r1;
        this.bottomRightCorner = r1;
        this.bottomLeftCorner = r1;
    }

    @Deprecated
    public void setAllEdges(EdgeTreatment r1) {
        this.leftEdge = r1;
        this.topEdge = r1;
        this.rightEdge = r1;
        this.bottomEdge = r1;
    }

    @Deprecated
    public void setBottomEdge(EdgeTreatment r1) {
        this.bottomEdge = r1;
    }

    @Deprecated
    public void setBottomLeftCorner(CornerTreatment r1) {
        this.bottomLeftCorner = r1;
    }

    @Deprecated
    public void setBottomRightCorner(CornerTreatment r1) {
        this.bottomRightCorner = r1;
    }

    @Deprecated
    public void setCornerTreatments(CornerTreatment r1, CornerTreatment r2, CornerTreatment r3, CornerTreatment r4) {
        this.topLeftCorner = r1;
        this.topRightCorner = r2;
        this.bottomRightCorner = r3;
        this.bottomLeftCorner = r4;
    }

    @Deprecated
    public void setEdgeTreatments(EdgeTreatment r1, EdgeTreatment r2, EdgeTreatment r3, EdgeTreatment r4) {
        this.leftEdge = r1;
        this.topEdge = r2;
        this.rightEdge = r3;
        this.bottomEdge = r4;
    }

    @Deprecated
    public void setLeftEdge(EdgeTreatment r1) {
        this.leftEdge = r1;
    }

    @Deprecated
    public void setRightEdge(EdgeTreatment r1) {
        this.rightEdge = r1;
    }

    @Deprecated
    public void setTopEdge(EdgeTreatment r1) {
        this.topEdge = r1;
    }

    @Deprecated
    public void setTopLeftCorner(CornerTreatment r1) {
        this.topLeftCorner = r1;
    }

    @Deprecated
    public void setTopRightCorner(CornerTreatment r1) {
        this.topRightCorner = r1;
    }
}
