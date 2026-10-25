package com.google.android.material.transition.platform;

/* loaded from: classes5.dex */
class FadeModeResult {
    final int endAlpha;
    final boolean endOnTop;
    final int startAlpha;

    private FadeModeResult(int r1, int r2, boolean r3) {
        this.startAlpha = r1;
        this.endAlpha = r2;
        this.endOnTop = r3;
    }

    public static FadeModeResult endOnTop(int r2, int r3) {
        return new FadeModeResult(r2, r3, true);
    }

    public static FadeModeResult startOnTop(int r2, int r3) {
        return new FadeModeResult(r2, r3, false);
    }
}
