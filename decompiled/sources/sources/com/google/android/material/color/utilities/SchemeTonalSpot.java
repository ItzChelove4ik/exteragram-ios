package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class SchemeTonalSpot extends DynamicScheme {
    public SchemeTonalSpot(Hct r12, boolean r13, double r14) {
        super(r12, Variant.TONAL_SPOT, r13, r14, TonalPalette.fromHueAndChroma(r12.getHue(), 36.0d), TonalPalette.fromHueAndChroma(r12.getHue(), 16.0d), TonalPalette.fromHueAndChroma(MathUtils.sanitizeDegreesDouble(r12.getHue() + 60.0d), 24.0d), TonalPalette.fromHueAndChroma(r12.getHue(), 6.0d), TonalPalette.fromHueAndChroma(r12.getHue(), 8.0d));
    }
}
