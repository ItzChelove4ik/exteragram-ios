package com.google.android.material.animation;

import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;

/* loaded from: classes5.dex */
public class ImageMatrixProperty extends Property<ImageView, Matrix> {
    private final Matrix matrix;

    public ImageMatrixProperty() {
        super(Matrix.class, "imageMatrixProperty");
        this.matrix = new Matrix();
    }

    /* renamed from: get, reason: avoid collision after fix types in other method */
    public Matrix get2(ImageView r2) {
        this.matrix.set(r2.getImageMatrix());
        return this.matrix;
    }

    @Override // android.util.Property
    public /* bridge */ /* synthetic */ Matrix get(ImageView r1) {
        return get2(r1);
    }

    /* renamed from: set, reason: avoid collision after fix types in other method */
    public void set2(ImageView r1, Matrix r2) {
        r1.setImageMatrix(r2);
    }

    @Override // android.util.Property
    public /* bridge */ /* synthetic */ void set(ImageView r1, Matrix r2) {
        set2(r1, r2);
    }
}
