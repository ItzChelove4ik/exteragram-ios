package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class ToneDeltaPair {
    private final double delta;
    private final TonePolarity polarity;
    private final DynamicColor roleA;
    private final DynamicColor roleB;
    private final boolean stayTogether;

    public ToneDeltaPair(DynamicColor r1, DynamicColor r2, double r3, TonePolarity r5, boolean r6) {
        this.roleA = r1;
        this.roleB = r2;
        this.delta = r3;
        this.polarity = r5;
        this.stayTogether = r6;
    }

    public double getDelta() {
        return this.delta;
    }

    public TonePolarity getPolarity() {
        return this.polarity;
    }

    public DynamicColor getRoleA() {
        return this.roleA;
    }

    public DynamicColor getRoleB() {
        return this.roleB;
    }

    public boolean getStayTogether() {
        return this.stayTogether;
    }
}
