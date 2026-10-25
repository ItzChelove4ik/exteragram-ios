package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class SchemeVibrant extends DynamicScheme {
    private static final double[] HUES = null;
    private static final double[] SECONDARY_ROTATIONS = null;
    private static final double[] TERTIARY_ROTATIONS = null;

    static {
        HUES = new double[]{0.0d, 41.0d, 61.0d, 101.0d, 131.0d, 181.0d, 251.0d, 301.0d, 360.0d};
        SECONDARY_ROTATIONS = new double[]{18.0d, 15.0d, 10.0d, 12.0d, 15.0d, 18.0d, 15.0d, 12.0d, 12.0d};
        TERTIARY_ROTATIONS = new double[]{35.0d, 30.0d, 20.0d, 25.0d, 30.0d, 35.0d, 30.0d, 25.0d, 25.0d};
    }

    public SchemeVibrant(Hct r12, boolean r13, double r14) {
        Variant r2 = Variant.VIBRANT;
        TonalPalette r6 = TonalPalette.fromHueAndChroma(r12.getHue(), 200.0d);
        double[] r0 = HUES;
        super(r12, r2, r13, r14, r6, TonalPalette.fromHueAndChroma(DynamicScheme.getRotatedHue(r12, r0, SECONDARY_ROTATIONS), 24.0d), TonalPalette.fromHueAndChroma(DynamicScheme.getRotatedHue(r12, r0, TERTIARY_ROTATIONS), 32.0d), TonalPalette.fromHueAndChroma(r12.getHue(), 10.0d), TonalPalette.fromHueAndChroma(r12.getHue(), 12.0d));
    }
}
