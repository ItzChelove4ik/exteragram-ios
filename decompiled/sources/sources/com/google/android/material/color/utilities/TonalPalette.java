package com.google.android.material.color.utilities;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class TonalPalette {
    Map<Integer, Integer> cache;
    double chroma;
    double hue;
    Hct keyColor;

    public static final class KeyColor {
        private static final double MAX_CHROMA_VALUE = 200.0d;
        private final Map<Integer, Double> chromaCache;
        private final double hue;
        private final double requestedChroma;

        public KeyColor(double r2, double r4) {
            this.chromaCache = new HashMap();
            this.hue = r2;
            this.requestedChroma = r4;
        }

        private double maxChroma(int r8) {
            if (this.chromaCache.get(Integer.valueOf(r8)) != null) goto L6;
            this.chromaCache.put(Integer.valueOf(r8), Double.valueOf(Hct.from(this.hue, MAX_CHROMA_VALUE, r8).getChroma()));
        L6:
            return this.chromaCache.get(Integer.valueOf(r8)).doubleValue();
        }

        public Hct create() {
            int r1 = 100;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L21;
            int r3 = (r2 + r1) / 2;
            int r6 = r3 + 1;
            if (maxChroma(r3) >= maxChroma(r6)) goto L7;
            boolean r4 = true;
        L9:
            if (maxChroma(r3) >= (this.requestedChroma - 0.01d)) goto L11;
            if (r4 == false) goto L19;
            r2 = r6;
        L19:
            r1 = r3;
            goto L3
        L11:
            if (Math.abs(r2 - 50) < Math.abs(r1 - 50)) goto L19;
            if (r2 == r3) goto L15;
            r2 = r3;
            goto L3
        L15:
            return Hct.from(this.hue, this.requestedChroma, r2);
        L7:
            r4 = false;
            goto L9
        L21:
            return Hct.from(this.hue, this.requestedChroma, r2);
        }
    }

    private TonalPalette(double r2, double r4, Hct r6) {
        this.cache = new HashMap();
        this.hue = r2;
        this.chroma = r4;
        this.keyColor = r6;
    }

    public static TonalPalette fromHct(Hct r6) {
        return new TonalPalette(r6.getHue(), r6.getChroma(), r6);
    }

    public static TonalPalette fromHueAndChroma(double r7, double r9) {
        return new TonalPalette(r7, r9, new KeyColor(r7, r9).create());
    }

    public static TonalPalette fromInt(int r0) {
        return fromHct(Hct.fromInt(r0));
    }

    public double getChroma() {
        return this.chroma;
    }

    public Hct getHct(double r7) {
        return Hct.from(this.hue, this.chroma, r7);
    }

    public double getHue() {
        return this.hue;
    }

    public Hct getKeyColor() {
        return this.keyColor;
    }

    public int tone(int r8) {
        Integer r0 = this.cache.get(Integer.valueOf(r8));
        if (r0 != null) goto L6;
        r0 = Integer.valueOf(Hct.from(this.hue, this.chroma, r8).toInt());
        this.cache.put(Integer.valueOf(r8), r0);
    L6:
        return r0.intValue();
    }
}
