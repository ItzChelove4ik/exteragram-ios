package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/* loaded from: classes5.dex */
public final class DynamicColor {
    public final Function<DynamicScheme, DynamicColor> background;
    public final ContrastCurve contrastCurve;
    private final HashMap<DynamicScheme, Hct> hctCache;
    public final boolean isBackground;
    public final String name;
    public final Function<DynamicScheme, Double> opacity;
    public final Function<DynamicScheme, TonalPalette> palette;
    public final Function<DynamicScheme, DynamicColor> secondBackground;
    public final Function<DynamicScheme, Double> tone;
    public final Function<DynamicScheme, ToneDeltaPair> toneDeltaPair;

    public static /* synthetic */ Double $r8$lambda$CVVhFDc2ooBbGADpOace6TmxBDU(Hct r0, DynamicScheme r1) {
        return Double.valueOf(r0.getTone());
    }

    /* renamed from: $r8$lambda$HYD-TTRX49hWk5qNJTOhVJLMxRg, reason: not valid java name */
    public static /* synthetic */ TonalPalette m1827$r8$lambda$HYDTTRX49hWk5qNJTOhVJLMxRg(TonalPalette r0, DynamicScheme r1) {
        return r0;
    }

    public DynamicColor(String r2, Function<DynamicScheme, TonalPalette> r3, Function<DynamicScheme, Double> r4, boolean r5, Function<DynamicScheme, DynamicColor> r6, Function<DynamicScheme, DynamicColor> r7, ContrastCurve r8, Function<DynamicScheme, ToneDeltaPair> r9) {
        this.hctCache = new HashMap();
        this.name = r2;
        this.palette = r3;
        this.tone = r4;
        this.isBackground = r5;
        this.background = r6;
        this.secondBackground = r7;
        this.contrastCurve = r8;
        this.toneDeltaPair = r9;
        this.opacity = null;
    }

    public DynamicColor(String r2, Function<DynamicScheme, TonalPalette> r3, Function<DynamicScheme, Double> r4, boolean r5, Function<DynamicScheme, DynamicColor> r6, Function<DynamicScheme, DynamicColor> r7, ContrastCurve r8, Function<DynamicScheme, ToneDeltaPair> r9, Function<DynamicScheme, Double> r10) {
        this.hctCache = new HashMap();
        this.name = r2;
        this.palette = r3;
        this.tone = r4;
        this.isBackground = r5;
        this.background = r6;
        this.secondBackground = r7;
        this.contrastCurve = r8;
        this.toneDeltaPair = r9;
        this.opacity = r10;
    }

    public static double enableLightForeground(double r1) {
        if (tonePrefersLightForeground(r1) == true) goto L5;
        return r1;
    L5:
        if (toneAllowsLightForeground(r1) == true) goto L9;
        return 49.0d;
    L9:
        return r1;
    }

    public static double foregroundTone(double r10, double r12) {
        double r0 = Contrast.lighterUnsafe(r10, r12);
        double r2 = Contrast.darkerUnsafe(r10, r12);
        double r4 = Contrast.ratioOfTones(r0, r10);
        double r6 = Contrast.ratioOfTones(r2, r10);
        if (tonePrefersLightForeground(r10) == false) goto L19;
        if (Math.abs(r4 - r6) < 0.1d) goto L7;
    L11:
        boolean r102 = false;
    L13:
        if (r4 < r12) goto L15;
    L23:
        return r0;
    L15:
        if (r4 >= r6) goto L23;
        if (r102 == true) goto L23;
    L24:
        return r2;
    L7:
        if (r4 >= r12) goto L11;
        if (r6 >= r12) goto L11;
        r102 = true;
        goto L13
    L19:
        if (r6 >= r12) goto L24;
        if (r6 < r4) goto L23;
        goto L23
    }

    public static DynamicColor fromArgb(String r2, int r3) {
        Hct r0 = Hct.fromInt(r3);
        return fromPalette(r2, new DynamicColor$$ExternalSyntheticLambda0(TonalPalette.fromInt(r3)), new DynamicColor$$ExternalSyntheticLambda1(r0));
    }

    public static DynamicColor fromPalette(String r9, Function<DynamicScheme, TonalPalette> r10, Function<DynamicScheme, Double> r11) {
        return new DynamicColor(r9, r10, r11, false, null, null, null, null);
    }

    public static DynamicColor fromPalette(String r9, Function<DynamicScheme, TonalPalette> r10, Function<DynamicScheme, Double> r11, boolean r12) {
        return new DynamicColor(r9, r10, r11, r12, null, null, null, null);
    }

    public static boolean toneAllowsLightForeground(double r2) {
        if (Math.round(r2) > 49) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean tonePrefersLightForeground(double r2) {
        if (Math.round(r2) >= 60) goto L6;
        return true;
    L6:
        return false;
    }

    public int getArgb(DynamicScheme r4) {
        int r0 = getHct(r4).toInt();
        Function<DynamicScheme, Double> r3 = this.opacity;
        if (r3 != null) goto L5;
        return r0;
    L5:
        int r32 = MathUtils.clampInt(0, 255, (int) Math.round(r3.apply(r4).doubleValue() * 255.0d));
        int r33 = r32 << 24;
        return r33 | (16777215 & r0);
    }

    public Hct getHct(DynamicScheme r4) {
        Hct r0 = this.hctCache.get(r4);
        if (r0 == null) goto L5;
        return r0;
    L5:
        double r02 = getTone(r4);
        Hct r03 = this.palette.apply(r4).getHct(r02);
        if (this.hctCache.size() <= 4) goto L8;
        this.hctCache.clear();
    L8:
        this.hctCache.put(r4, r03);
        return r03;
    }

    public double getTone(DynamicScheme r33) {
        boolean r3 = false;
        if (r33.contrastLevel >= 0.0d) goto L5;
        boolean r2 = true;
    L6:
        Function<DynamicScheme, ToneDeltaPair> r7 = this.toneDeltaPair;
        if (r7 == null) goto L71;
        ToneDeltaPair r72 = r7.apply(r33);
        DynamicColor r16 = r72.getRoleA();
        DynamicColor r17 = r72.getRoleB();
        double r18 = r72.getDelta();
        TonePolarity r4 = r72.getPolarity();
        boolean r5 = r72.getStayTogether();
        double r8 = this.background.apply(r33).getTone(r33);
        if (r4 != TonePolarity.NEARER) goto L11;
    L18:
        r3 = true;
    L19:
        if (r3 == false) goto L21;
        DynamicColor r42 = r16;
    L22:
        if (r3 == false) goto L24;
        DynamicColor r32 = r17;
    L25:
        boolean r0 = this.name.equals(r42.name);
        if (r33.isDark == false) goto L28;
        double r10 = 1.0d;
    L29:
        double r6 = r42.contrastCurve.get(r33.contrastLevel);
        double r12 = r32.contrastCurve.get(r33.contrastLevel);
        double r14 = r42.tone.apply(r33).doubleValue();
        if (Contrast.ratioOfTones(r8, r14) >= r6) goto L33;
        r14 = foregroundTone(r8, r6);
    L33:
        double r34 = r32.tone.apply(r33).doubleValue();
        if (Contrast.ratioOfTones(r8, r34) >= r12) goto L37;
        r34 = foregroundTone(r8, r12);
    L37:
        if (r2 == false) goto L40;
        r14 = foregroundTone(r8, r6);
        r34 = foregroundTone(r8, r12);
    L40:
        if (((r34 - r14) * r10) >= r18) goto L45;
        double r1 = r18 * r10;
        r34 = MathUtils.clampDouble(0.0d, 100.0d, r14 + r1);
        if (((r34 - r14) * r10) >= r18) goto L45;
        r14 = MathUtils.clampDouble(0.0d, 100.0d, r34 - r1);
    L45:
        if (50.0d > r14) goto L55;
        if (r14 >= 60.0d) goto L55;
        if (r10 <= 0.0d) goto L52;
        double r122 = Math.max(r34, (r18 * r10) + 60.0d);
    L51:
        r14 = 60.0d;
    L68:
        if (r0 == false) goto L70;
        return r14;
    L70:
        return r122;
    L52:
        double r13 = Math.min(r34, (r18 * r10) + 49.0d);
    L53:
        r122 = r13;
        r14 = 49.0d;
    L55:
        if (50.0d <= r34) goto L57;
    L67:
        r122 = r34;
        goto L68
    L57:
        if (r34 >= 60.0d) goto L67;
        if (r5 == false) goto L64;
        if (r10 <= 0.0d) goto L62;
        r122 = Math.max(r34, (r18 * r10) + 60.0d);
        goto L51
    L62:
        r13 = Math.min(r34, (r18 * r10) + 49.0d);
        goto L53
    L64:
        if (r10 <= 0.0d) goto L66;
        r122 = 60.0d;
        goto L68
    L66:
        r122 = 49.0d;
        goto L68
    L28:
        r10 = -1.0d;
        goto L29
    L24:
        r32 = r16;
        goto L25
    L21:
        r42 = r17;
        goto L22
    L11:
        if (r4 != TonePolarity.LIGHTER) goto L15;
        if (r33.isDark == false) goto L18;
    L15:
        if (r4 != TonePolarity.DARKER) goto L19;
        if (r33.isDark == false) goto L19;
    L71:
        double r43 = this.tone.apply(r33).doubleValue();
        Function<DynamicScheme, DynamicColor> r73 = this.background;
        if (r73 != null) goto L74;
        return r43;
    L74:
        double r74 = r73.apply(r33).getTone(r33);
        double r123 = this.contrastCurve.get(r33.contrastLevel);
        if (Contrast.ratioOfTones(r74, r43) >= r123) goto L78;
        r43 = foregroundTone(r74, r123);
    L78:
        if (r2 == false) goto L81;
        r43 = foregroundTone(r74, r123);
    L81:
        if (this.isBackground == true) goto L83;
    L90:
        double r142 = r43;
    L92:
        if (this.secondBackground == null) goto L120;
        double r44 = this.background.apply(r33).getTone(r33);
        double r02 = this.secondBackground.apply(r33).getTone(r33);
        double r75 = Math.max(r44, r02);
        double r102 = Math.min(r44, r02);
        if (Contrast.ratioOfTones(r75, r142) >= r123) goto L96;
    L98:
        double r76 = Contrast.lighter(r75, r123);
        double r9 = Contrast.darker(r102, r123);
        ArrayList r22 = new ArrayList();
        if (r76 == (-1.0d)) goto L102;
        r22.add(Double.valueOf(r76));
    L102:
        if (r9 == (-1.0d)) goto L105;
        r22.add(Double.valueOf(r9));
    L105:
        if (tonePrefersLightForeground(r44) == false) goto L107;
    L116:
        if (r76 != (-1.0d)) goto L119;
        return 100.0d;
    L119:
        return r76;
    L107:
        if (tonePrefersLightForeground(r02) == true) goto L116;
        if (r22.size() == 1) goto L112;
        if (r9 != (-1.0d)) goto L115;
        return 0.0d;
    L115:
        return r9;
    L112:
        return ((Double) r22.get(0)).doubleValue();
    L96:
        if (Contrast.ratioOfTones(r102, r142) < r123) goto L98;
    L120:
        return r142;
    L83:
        if (50.0d > r43) goto L90;
        if (r43 >= 60.0d) goto L90;
        r142 = 49.0d;
        if (Contrast.ratioOfTones(49.0d, r74) >= r123) goto L92;
        r142 = 60.0d;
        goto L92
    L5:
        r2 = false;
        goto L6
    }
}
