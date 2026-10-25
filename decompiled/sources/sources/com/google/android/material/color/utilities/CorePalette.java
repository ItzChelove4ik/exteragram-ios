package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class CorePalette {
    public TonalPalette a1;
    public TonalPalette a2;
    public TonalPalette a3;
    public TonalPalette error;
    public TonalPalette n1;
    public TonalPalette n2;

    private CorePalette(int r11, boolean r12) {
        Hct r112 = Hct.fromInt(r11);
        double r0 = r112.getHue();
        double r2 = r112.getChroma();
        if (r12 == false) goto L5;
        this.a1 = TonalPalette.fromHueAndChroma(r0, r2);
        this.a2 = TonalPalette.fromHueAndChroma(r0, r2 / 3.0d);
        this.a3 = TonalPalette.fromHueAndChroma(60.0d + r0, r2 / 2.0d);
        this.n1 = TonalPalette.fromHueAndChroma(r0, Math.min(r2 / 12.0d, 4.0d));
        this.n2 = TonalPalette.fromHueAndChroma(r0, Math.min(r2 / 6.0d, 8.0d));
    L6:
        this.error = TonalPalette.fromHueAndChroma(25.0d, 84.0d);
        return;
    L5:
        this.a1 = TonalPalette.fromHueAndChroma(r0, Math.max(48.0d, r2));
        this.a2 = TonalPalette.fromHueAndChroma(r0, 16.0d);
        this.a3 = TonalPalette.fromHueAndChroma(60.0d + r0, 24.0d);
        this.n1 = TonalPalette.fromHueAndChroma(r0, 4.0d);
        this.n2 = TonalPalette.fromHueAndChroma(r0, 8.0d);
        goto L6
    }

    public static CorePalette contentOf(int r2) {
        return new CorePalette(r2, true);
    }

    public static CorePalette of(int r2) {
        return new CorePalette(r2, false);
    }
}
