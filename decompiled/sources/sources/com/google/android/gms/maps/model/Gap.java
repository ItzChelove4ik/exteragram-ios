package com.google.android.gms.maps.model;

/* loaded from: classes5.dex */
public final class Gap extends PatternItem {
    public final float length;

    public Gap(float r4) {
        super(2, Float.valueOf(Math.max(r4, 0.0f)));
        this.length = Math.max(r4, 0.0f);
    }

    @Override // com.google.android.gms.maps.model.PatternItem
    public String toString() {
        return "[Gap: length=" + this.length + "]";
    }
}
