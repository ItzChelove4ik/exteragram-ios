package org.telegram.messenger.utils.tlutils;

/* loaded from: classes6.dex */
public enum AmountUtils$Currency extends Enum<AmountUtils$Currency> {
    private static final /* synthetic */ AmountUtils$Currency[] $VALUES = null;
    public static final AmountUtils$Currency STARS = null;
    public static final AmountUtils$Currency TON = null;

    private static /* synthetic */ AmountUtils$Currency[] $values() {
        return new AmountUtils$Currency[]{STARS, TON};
    }

    static {
        STARS = new AmountUtils$Currency("STARS", 0);
        TON = new AmountUtils$Currency("TON", 1);
        $VALUES = $values();
    }

    AmountUtils$Currency(String r1, int r2) {
    }

    public static AmountUtils$Currency valueOf(String r1) {
        return (AmountUtils$Currency) Enum.valueOf(AmountUtils$Currency.class, r1);
    }

    public static AmountUtils$Currency[] values() {
        return (AmountUtils$Currency[]) $VALUES.clone();
    }
}
