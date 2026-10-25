package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes.dex */
public enum DeliveryMechanism extends Enum<DeliveryMechanism> {
    private static final /* synthetic */ DeliveryMechanism[] $VALUES = null;
    public static final DeliveryMechanism APP_STORE = null;
    public static final DeliveryMechanism DEVELOPER = null;
    public static final DeliveryMechanism TEST_DISTRIBUTION = null;
    public static final DeliveryMechanism USER_SIDELOAD = null;
    private final int id;

    private static /* synthetic */ DeliveryMechanism[] $values() {
        return new DeliveryMechanism[]{DEVELOPER, USER_SIDELOAD, TEST_DISTRIBUTION, APP_STORE};
    }

    static {
        DEVELOPER = new DeliveryMechanism("DEVELOPER", 0, 1);
        USER_SIDELOAD = new DeliveryMechanism("USER_SIDELOAD", 1, 2);
        TEST_DISTRIBUTION = new DeliveryMechanism("TEST_DISTRIBUTION", 2, 3);
        APP_STORE = new DeliveryMechanism("APP_STORE", 3, 4);
        $VALUES = $values();
    }

    DeliveryMechanism(String r1, int r2, int r3) {
        this.id = r3;
    }

    public static DeliveryMechanism determineFrom(String r0) {
        if (r0 == null) goto L6;
        return APP_STORE;
    L6:
        return DEVELOPER;
    }

    public static DeliveryMechanism valueOf(String r1) {
        return (DeliveryMechanism) Enum.valueOf(DeliveryMechanism.class, r1);
    }

    public static DeliveryMechanism[] values() {
        return (DeliveryMechanism[]) $VALUES.clone();
    }

    public int getId() {
        return this.id;
    }

    @Override // java.lang.Enum
    public String toString() {
        return Integer.toString(this.id);
    }
}
