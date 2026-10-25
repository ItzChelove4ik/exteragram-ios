package org.telegram.messenger.utils.tlutils;

/* loaded from: classes6.dex */
class AmountUtils$AmountLimit {
    private final AmountUtils$Amount max;
    private final AmountUtils$Amount min;

    /* renamed from: -$$Nest$fgetmax, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AmountUtils$Amount m5207$$Nest$fgetmax(AmountUtils$AmountLimit r0) {
        return r0.max;
    }

    /* renamed from: -$$Nest$fgetmin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AmountUtils$Amount m5208$$Nest$fgetmin(AmountUtils$AmountLimit r0) {
        return r0.min;
    }

    public AmountUtils$AmountLimit(AmountUtils$Amount r1, AmountUtils$Amount r2) {
        this.min = r1;
        this.max = r2;
    }
}
