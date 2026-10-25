package org.telegram.ui.Components.Crop;

/* loaded from: classes7.dex */
public class CropTransform {
    private float cropAreaX;
    private float cropAreaY;
    private int cropOrientation;
    private float cropPh;
    private float cropPw;
    private float cropPx;
    private float cropPy;
    private float cropRotation;
    private float cropScale;
    private boolean hasTransform;
    private boolean isMirrored;
    private float minScale;
    private float trueCropScale;

    public CropTransform() {
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return m10070clone();
    }

    /* renamed from: clone, reason: collision with other method in class */
    public CropTransform m10070clone() {
        CropTransform r0 = new CropTransform();
        r0.hasTransform = this.hasTransform;
        r0.cropPx = this.cropPx;
        r0.cropPy = this.cropPy;
        r0.cropAreaX = this.cropAreaX;
        r0.cropAreaY = this.cropAreaY;
        r0.cropScale = this.cropScale;
        r0.cropRotation = this.cropRotation;
        r0.isMirrored = this.isMirrored;
        r0.cropOrientation = this.cropOrientation;
        r0.cropPw = this.cropPw;
        r0.cropPh = this.cropPh;
        r0.trueCropScale = this.trueCropScale;
        r0.minScale = this.minScale;
        return r0;
    }

    public float getCropAreaX() {
        return this.cropAreaX;
    }

    public float getCropAreaY() {
        return this.cropAreaY;
    }

    public float getCropPh() {
        return this.cropPh;
    }

    public float getCropPw() {
        return this.cropPw;
    }

    public float getCropPx() {
        return this.cropPx;
    }

    public float getCropPy() {
        return this.cropPy;
    }

    public float getMinScale() {
        return this.minScale;
    }

    public int getOrientation() {
        return this.cropOrientation;
    }

    public float getRotation() {
        return this.cropRotation;
    }

    public float getScale() {
        return this.cropScale;
    }

    public float getTrueCropScale() {
        return this.trueCropScale;
    }

    public boolean hasViewTransform() {
        return this.hasTransform;
    }

    public boolean isMirrored() {
        return this.isMirrored;
    }

    public void setViewTransform(boolean r1) {
        this.hasTransform = r1;
    }

    public void setViewTransform(boolean r1, float r2, float r3, float r4, int r5, float r6, float r7, float r8, float r9, float r10, float r11, float r12, boolean r13) {
        this.hasTransform = r1;
        this.cropPx = r2;
        this.cropPy = r3;
        this.cropScale = r6;
        this.cropRotation = r4;
        this.cropOrientation = r5;
    L3:
        int r14 = this.cropOrientation;
        if (r14 >= 0) goto L6;
        this.cropOrientation = r14 + 360;
    L6:
        int r15 = this.cropOrientation;
        if (r15 < 360) goto L9;
        this.cropOrientation = r15 - 360;
        goto L6
    L9:
        this.cropPw = r9;
        this.cropPh = r10;
        this.cropAreaX = r11;
        this.cropAreaY = r12;
        this.trueCropScale = r7;
        this.minScale = r8;
        this.isMirrored = r13;
    }
}
