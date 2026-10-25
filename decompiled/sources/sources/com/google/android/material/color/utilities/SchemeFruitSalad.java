package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class SchemeFruitSalad extends DynamicScheme {
    public SchemeFruitSalad(Hct r12, boolean r13, double r14) {
        super(r12, Variant.FRUIT_SALAD, r13, r14, TonalPalette.fromHueAndChroma(MathUtils.sanitizeDegreesDouble(r12.getHue() - 50.0d), 48.0d), TonalPalette.fromHueAndChroma(MathUtils.sanitizeDegreesDouble(r12.getHue() - 50.0d), 36.0d), TonalPalette.fromHueAndChroma(r12.getHue(), 36.0d), TonalPalette.fromHueAndChroma(r12.getHue(), 10.0d), TonalPalette.fromHueAndChroma(r12.getHue(), 16.0d));
    }
}
