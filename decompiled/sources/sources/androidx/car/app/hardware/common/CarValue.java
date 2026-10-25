package androidx.car.app.hardware.common;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CarValue<T> {
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_UNAVAILABLE = 3;
    public static final int STATUS_UNIMPLEMENTED = 2;
    public static final int STATUS_UNKNOWN = 0;
    public static final CarValue<List<Float>> UNIMPLEMENTED_FLOAT_LIST = null;
    public static final CarValue<Integer> UNIMPLEMENTED_INTEGER = null;
    public static final CarValue<Boolean> UNKNOWN_BOOLEAN = null;
    public static final CarValue<Float> UNKNOWN_FLOAT = null;
    public static final CarValue<List<Float>> UNKNOWN_FLOAT_LIST = null;
    public static final CarValue<Integer> UNKNOWN_INTEGER = null;
    public static final CarValue<List<Integer>> UNKNOWN_INTEGER_LIST = null;
    public static final CarValue<String> UNKNOWN_STRING = null;
    private final List<CarZone> mCarZones;
    private final int mStatus;
    private final long mTimestampMillis;
    private final T mValue;

    static {
        UNIMPLEMENTED_INTEGER = unimplemented();
        UNKNOWN_INTEGER = unknown();
        UNKNOWN_BOOLEAN = unknown();
        UNKNOWN_FLOAT = unknown();
        UNKNOWN_STRING = unknown();
        UNIMPLEMENTED_FLOAT_LIST = unimplemented();
        UNKNOWN_FLOAT_LIST = unknown();
        UNKNOWN_INTEGER_LIST = unknown();
    }

    private CarValue() {
        this.mValue = null;
        this.mTimestampMillis = 0;
        this.mStatus = 0;
        this.mCarZones = Collections.EMPTY_LIST;
    }

    public CarValue(T r1, long r2, int r4) {
        this.mValue = r1;
        this.mTimestampMillis = r2;
        this.mStatus = r4;
        this.mCarZones = Collections.singletonList(CarZone.CAR_ZONE_GLOBAL);
    }

    public CarValue(T r1, long r2, int r4, List<CarZone> r5) {
        this.mValue = r1;
        this.mTimestampMillis = r2;
        this.mStatus = r4;
        this.mCarZones = r5;
    }

    private static <T> CarValue<T> unimplemented() {
        return new CarValue(null, 0, 2);
    }

    private static <T> CarValue<T> unknown() {
        return new CarValue(null, 0, 0);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof CarValue) == true) goto L8;
        return false;
    L8:
        CarValue r82 = (CarValue) r8;
        if (Objects.equals(this.mValue, r82.mValue) == true) goto L11;
    L17:
        return false;
    L11:
        if (this.mTimestampMillis != r82.mTimestampMillis) goto L17;
        if (this.mStatus != r82.mStatus) goto L17;
        if (Objects.equals(this.mCarZones, r82.mCarZones) == false) goto L17;
        return true;
    }

    public List<CarZone> getCarZones() {
        if (this.mStatus != 2) goto L7;
        return Collections.EMPTY_LIST;
    L7:
        return this.mCarZones;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public long getTimestampMillis() {
        return this.mTimestampMillis;
    }

    public T getValue() {
        return this.mValue;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mValue, Long.valueOf(this.mTimestampMillis), Integer.valueOf(this.mStatus), this.mCarZones});
    }

    public String toString() {
        return "[value: " + this.mValue + ", timestamp: " + this.mTimestampMillis + ", Status: " + this.mStatus + ", CarZones: " + this.mCarZones + "]";
    }
}
