package com.google.android.material.shape;

/* loaded from: classes5.dex */
public interface ShapeAppearance {
    ShapeAppearanceModel getDefaultShape();

    ShapeAppearanceModel[] getShapeAppearanceModels();

    ShapeAppearanceModel getShapeForState(int[] r1);

    boolean isStateful();

    ShapeAppearanceModel withCornerSize(float r1);

    ShapeAppearanceModel withCornerSize(CornerSize r1);
}
