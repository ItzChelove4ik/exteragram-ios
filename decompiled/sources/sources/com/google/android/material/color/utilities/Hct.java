package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class Hct {
    private int argb;
    private double chroma;
    private double hue;
    private double tone;

    private Hct(int r1) {
        setInternalState(r1);
    }

    public static Hct from(double r0, double r2, double r4) {
        return new Hct(HctSolver.solveToInt(r0, r2, r4));
    }

    public static Hct fromInt(int r1) {
        return new Hct(r1);
    }

    private void setInternalState(int r4) {
        this.argb = r4;
        Cam16 r0 = Cam16.fromInt(r4);
        this.hue = r0.getHue();
        this.chroma = r0.getChroma();
        this.tone = ColorUtils.lstarFromArgb(r4);
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public double getTone() {
        return this.tone;
    }

    public Hct inViewingConditions(ViewingConditions r8) {
        double[] r7 = Cam16.fromInt(toInt()).xyzInViewingConditions(r8, null);
        Cam16 r0 = Cam16.fromXyzInViewingConditions(r7[0], r7[1], r7[2], ViewingConditions.DEFAULT);
        return from(r0.getHue(), r0.getChroma(), ColorUtils.lstarFromY(r7[1]));
    }

    public void setChroma(double r7) {
        setInternalState(HctSolver.solveToInt(this.hue, r7, this.tone));
    }

    public void setHue(double r7) {
        setInternalState(HctSolver.solveToInt(r7, this.chroma, this.tone));
    }

    public void setTone(double r7) {
        setInternalState(HctSolver.solveToInt(this.hue, this.chroma, r7));
    }

    public int toInt() {
        return this.argb;
    }
}
