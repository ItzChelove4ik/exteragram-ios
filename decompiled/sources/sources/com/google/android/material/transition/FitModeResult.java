package com.google.android.material.transition;

/* loaded from: classes5.dex */
class FitModeResult {
    final float currentEndHeight;
    final float currentEndWidth;
    final float currentStartHeight;
    final float currentStartWidth;
    final float endScale;
    final float startScale;

    public FitModeResult(float r1, float r2, float r3, float r4, float r5, float r6) {
        this.startScale = r1;
        this.endScale = r2;
        this.currentStartWidth = r3;
        this.currentStartHeight = r4;
        this.currentEndWidth = r5;
        this.currentEndHeight = r6;
    }
}
