package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public final class TemperatureCache {
    private final Hct input;
    private Hct precomputedComplement;
    private List<Hct> precomputedHctsByHue;
    private List<Hct> precomputedHctsByTemp;
    private Map<Hct, Double> precomputedTempsByHct;

    public static /* synthetic */ Double $r8$lambda$VKoHiU00a9ybz_sbHTTerV8DYaI(TemperatureCache r0, Hct r1) {
        return r0.getTempsByHct().get(r1);
    }

    private TemperatureCache() {
        throw new UnsupportedOperationException();
    }

    public TemperatureCache(Hct r1) {
        this.input = r1;
    }

    private Hct getColdest() {
        return getHctsByTemp().get(0);
    }

    private List<Hct> getHctsByHue() {
        List<Hct> r0 = this.precomputedHctsByHue;
        if (r0 == null) goto L5;
        return r0;
    L5:
        ArrayList r02 = new ArrayList();
        double r3 = 0.0d;
    L7:
        if (r3 > 360.0d) goto L9;
        r02.add(Hct.from(r3, this.input.getChroma(), this.input.getTone()));
        r3 = r3 + 1.0d;
        goto L7
    L9:
        List<Hct> r03 = Collections.unmodifiableList(r02);
        this.precomputedHctsByHue = r03;
        return r03;
    }

    private List<Hct> getHctsByTemp() {
        List<Hct> r0 = this.precomputedHctsByTemp;
        if (r0 == null) goto L5;
        return r0;
    L5:
        ArrayList r02 = new ArrayList(getHctsByHue());
        r02.add(this.input);
        Collections.sort(r02, Comparator.comparing(new TemperatureCache$$ExternalSyntheticLambda0(this), new TemperatureCache$$ExternalSyntheticLambda1()));
        this.precomputedHctsByTemp = r02;
        return r02;
    }

    private Map<Hct, Double> getTempsByHct() {
        Map<Hct, Double> r0 = this.precomputedTempsByHct;
        if (r0 == null) goto L5;
        return r0;
    L5:
        ArrayList r02 = new ArrayList(getHctsByHue());
        r02.add(this.input);
        HashMap r1 = new HashMap();
        int r2 = r02.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L8;
        Object r4 = r02.get(r3);
        r3 = r3 + 1;
        Hct r42 = (Hct) r4;
        r1.put(r42, Double.valueOf(rawTemperature(r42)));
        goto L6
    L8:
        this.precomputedTempsByHct = r1;
        return r1;
    }

    private Hct getWarmest() {
        return getHctsByTemp().get(getHctsByTemp().size() - 1);
    }

    private static boolean isBetween(double r3, double r5, double r7) {
        if (r5 >= r7) goto L11;
        if (r5 <= r3) goto L7;
    L9:
        return false;
    L7:
        if (r3 > r7) goto L9;
        return true;
    L11:
        if (r5 > r3) goto L13;
    L16:
        return true;
    L13:
        if (r3 <= r7) goto L16;
        return false;
    }

    public static double rawTemperature(Hct r7) {
        double[] r72 = ColorUtils.labFromArgb(r7.toInt());
        double r1 = MathUtils.sanitizeDegreesDouble(Math.toDegrees(Math.atan2(r72[2], r72[1])));
        return ((Math.pow(Math.hypot(r72[1], r72[2]), 1.07d) * 0.02d) * Math.cos(Math.toRadians(MathUtils.sanitizeDegreesDouble(r1 - 50.0d)))) - 0.5d;
    }

    public List<Hct> getAnalogousColors() {
        return getAnalogousColors(5, 12);
    }

    public List<Hct> getAnalogousColors(int r20, int r21) {
        int r3 = (int) Math.round(this.input.getHue());
        Hct r4 = getHctsByHue().get(r3);
        double r5 = getRelativeTemperature(r4);
        ArrayList r7 = new ArrayList();
        r7.add(r4);
        double r8 = 0.0d;
        double r12 = 0.0d;
        int r11 = 0;
    L4:
        if (r11 >= 360) goto L6;
        double r14 = getRelativeTemperature(getHctsByHue().get(MathUtils.sanitizeDegreesInt(r3 + r11)));
        r12 = r12 + Math.abs(r14 - r5);
        r11 = r11 + 1;
        r5 = r14;
        goto L4
    L6:
        double r122 = r12 / r21;
        double r42 = getRelativeTemperature(r4);
        int r112 = 1;
    L8:
        if (r7.size() >= r21) goto L28;
        Hct r6 = getHctsByHue().get(MathUtils.sanitizeDegreesInt(r3 + r112));
        double r16 = getRelativeTemperature(r6);
        r8 = r8 + Math.abs(r16 - r42);
        if (r8 < (r7.size() * r122)) goto L12;
        boolean r43 = true;
    L13:
        int r52 = 1;
    L14:
        if (r43 == false) goto L22;
        if (r7.size() >= r21) goto L22;
        r7.add(r6);
        int r18 = r112;
        if (r8 < ((r7.size() + r52) * r122)) goto L20;
        r43 = true;
    L21:
        r52 = r52 + 1;
        r112 = r18;
        goto L14
    L20:
        r43 = false;
    L22:
        r112 = r112 + 1;
        if (r112 > 360) goto L25;
        r42 = r16;
    L25:
        if (r7.size() >= r21) goto L28;
        r7.add(r6);
        goto L25
    L12:
        r43 = false;
    L28:
        ArrayList r2 = new ArrayList();
        r2.add(this.input);
        int r0 = (int) Math.floor((r20 - 1.0d) / 2.0d);
        int r32 = 1;
    L30:
        if (r32 >= (r0 + 1)) goto L38;
        int r44 = 0 - r32;
    L32:
        if (r44 >= 0) goto L35;
        r44 = r44 + r7.size();
        goto L32
    L35:
        if (r44 < r7.size()) goto L37;
        r44 = r44 % r7.size();
    L37:
        r2.add(0, (Hct) r7.get(r44));
        r32 = r32 + 1;
        goto L30
    L38:
        int r02 = r20 - r0;
        int r62 = 1;
    L39:
        if (r62 >= r02) goto L47;
        int r1 = r62;
    L41:
        if (r1 >= 0) goto L44;
        r1 = r1 + r7.size();
        goto L41
    L44:
        if (r1 < r7.size()) goto L46;
        r1 = r1 % r7.size();
    L46:
        r2.add((Hct) r7.get(r1));
        r62 = r62 + 1;
        goto L39
    L47:
        return r2;
    }

    public Hct getComplement() {
        Hct r1 = this.precomputedComplement;
        if (r1 == null) goto L5;
        return r1;
    L5:
        double r4 = getColdest().getHue();
        double r8 = getTempsByHct().get(getColdest()).doubleValue();
        double r6 = getWarmest().getHue();
        double r10 = getTempsByHct().get(getWarmest()).doubleValue() - r8;
        boolean r12 = isBetween(this.input.getHue(), r4, r6);
        if (r12 == false) goto L8;
        double r14 = r6;
    L9:
        if (r12 == false) goto L11;
        double r16 = r4;
    L12:
        Hct r13 = getHctsByHue().get((int) Math.round(this.input.getHue()));
        double r42 = 1.0d;
        double r2 = 1.0d - getRelativeTemperature(this.input);
        double r62 = 1000.0d;
        double r18 = 0.0d;
    L14:
        if (r18 > 360.0d) goto L22;
        double r122 = MathUtils.sanitizeDegreesDouble((r42 * r18) + r14);
        if (isBetween(r122, r14, r16) == true) goto L18;
        double r20 = r42;
    L21:
        r18 = r18 + r20;
        r42 = r20;
        goto L14
    L18:
        r20 = r42;
        Hct r43 = getHctsByHue().get((int) Math.round(r122));
        double r123 = Math.abs(r2 - ((getTempsByHct().get(r43).doubleValue() - r8) / r10));
        if (r123 >= r62) goto L21;
        r13 = r43;
        r62 = r123;
        goto L21
    L22:
        this.precomputedComplement = r13;
        return r13;
    L11:
        r16 = r6;
        goto L12
    L8:
        r14 = r4;
        goto L9
    }

    public double getRelativeTemperature(Hct r5) {
        double r0 = getTempsByHct().get(getWarmest()).doubleValue() - getTempsByHct().get(getColdest()).doubleValue();
        double r2 = getTempsByHct().get(r5).doubleValue() - getTempsByHct().get(getColdest()).doubleValue();
        if (r0 != 0.0d) goto L7;
        return 0.5d;
    L7:
        return r2 / r0;
    }
}
