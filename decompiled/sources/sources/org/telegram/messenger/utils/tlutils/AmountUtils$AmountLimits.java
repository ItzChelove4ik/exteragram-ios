package org.telegram.messenger.utils.tlutils;

/* loaded from: classes6.dex */
public class AmountUtils$AmountLimits {
    private final AmountUtils$AmountLimit[] limits;

    public AmountUtils$AmountLimits() {
        this.limits = new AmountUtils$AmountLimit[AmountUtils$Currency.values().length];
    }

    public AmountUtils$Amount getMax(AmountUtils$Currency r1) {
        return AmountUtils$AmountLimit.m5207$$Nest$fgetmax(this.limits[r1.ordinal()]);
    }

    public AmountUtils$Amount getMin(AmountUtils$Currency r1) {
        return AmountUtils$AmountLimit.m5208$$Nest$fgetmin(this.limits[r1.ordinal()]);
    }

    public void set(AmountUtils$Amount r3, AmountUtils$Amount r4) {
        AmountUtils$Currency r0 = r3.currency;
        if (r0 == r4.currency) goto L5;
        return;
    L5:
        this.limits[r0.ordinal()] = new AmountUtils$AmountLimit(r3, r4);
    }
}
