package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class SchemeContent extends DynamicScheme {
    public SchemeContent(Hct r16, boolean r17, double r18) {
        super(r16, Variant.CONTENT, r17, r18, TonalPalette.fromHueAndChroma(r16.getHue(), r16.getChroma()), TonalPalette.fromHueAndChroma(r16.getHue(), Math.max(r16.getChroma() - 32.0d, r16.getChroma() * 0.5d)), TonalPalette.fromHct(DislikeAnalyzer.fixIfDisliked(new TemperatureCache(r16).getAnalogousColors(3, 6).get(2))), TonalPalette.fromHueAndChroma(r16.getHue(), r16.getChroma() / 8.0d), TonalPalette.fromHueAndChroma(r16.getHue(), (r16.getChroma() / 8.0d) + 4.0d));
    }
}
