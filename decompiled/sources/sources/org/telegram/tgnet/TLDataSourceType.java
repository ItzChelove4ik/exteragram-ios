package org.telegram.tgnet;

/* loaded from: classes.dex */
public enum TLDataSourceType extends Enum<TLDataSourceType> {
    private static final /* synthetic */ TLDataSourceType[] $VALUES = null;
    public static final TLDataSourceType NETWORK = null;
    public static final TLDataSourceType UNKNOWN = null;

    private static /* synthetic */ TLDataSourceType[] $values() {
        return new TLDataSourceType[]{UNKNOWN, NETWORK};
    }

    static {
        UNKNOWN = new TLDataSourceType("UNKNOWN", 0);
        NETWORK = new TLDataSourceType("NETWORK", 1);
        $VALUES = $values();
    }

    TLDataSourceType(String r1, int r2) {
    }

    public static TLDataSourceType valueOf(String r1) {
        return (TLDataSourceType) Enum.valueOf(TLDataSourceType.class, r1);
    }

    public static TLDataSourceType[] values() {
        return (TLDataSourceType[]) $VALUES.clone();
    }
}
