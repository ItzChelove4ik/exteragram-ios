package androidx.car.app.hardware.common;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class CarZone {
    public static final int CAR_ZONE_COLUMN_ALL = 16;
    public static final int CAR_ZONE_COLUMN_CENTER = 48;
    public static final int CAR_ZONE_COLUMN_DRIVER = 80;
    public static final int CAR_ZONE_COLUMN_LEFT = 32;
    public static final int CAR_ZONE_COLUMN_PASSENGER = 96;
    public static final int CAR_ZONE_COLUMN_RIGHT = 64;
    public static final CarZone CAR_ZONE_GLOBAL = null;
    public static final int CAR_ZONE_ROW_ALL = 0;
    public static final int CAR_ZONE_ROW_EXCLUDE_FIRST = 4;
    public static final int CAR_ZONE_ROW_FIRST = 1;
    public static final int CAR_ZONE_ROW_SECOND = 2;
    public static final int CAR_ZONE_ROW_THIRD = 3;
    private final int mColumn;
    private final int mRow;

    public static final class Builder {
        int mColumn;
        int mRow;

        public Builder() {
            this.mRow = 0;
            this.mColumn = 16;
        }

        public CarZone build() {
            return new CarZone(this);
        }
    }

    static {
        CAR_ZONE_GLOBAL = new Builder().build();
    }

    private CarZone() {
        this.mRow = 0;
        this.mColumn = 0;
    }

    public CarZone(Builder r2) {
        this.mRow = r2.mRow;
        this.mColumn = r2.mColumn;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CarZone) == true) goto L8;
        return false;
    L8:
        CarZone r52 = (CarZone) r5;
        if (Integer.valueOf(this.mColumn).equals(Integer.valueOf(r52.getColumn())) == true) goto L11;
    L13:
        return false;
    L11:
        if (Integer.valueOf(this.mRow).equals(Integer.valueOf(r52.getRow())) == false) goto L13;
        return true;
    }

    public int getColumn() {
        return this.mColumn;
    }

    public int getRow() {
        return this.mRow;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mRow), Integer.valueOf(this.mColumn)});
    }

    public String toString() {
        int r0 = this.mRow;
        String r1 = "UNKNOWN";
        if (r0 != 0) goto L5;
        String r02 = "CAR_ZONE_ROW_ALL";
    L18:
        int r3 = this.mColumn;
        if (r3 != 16) goto L21;
        r1 = "CAR_ZONE_COLUMN_ALL";
    L38:
        return "[CarZone row value: " + r02 + ", column value: " + r1 + "]";
    L21:
        if (r3 != 32) goto L23;
        r1 = "CAR_ZONE_COLUMN_LEFT";
        goto L38
    L23:
        if (r3 != 48) goto L25;
        r1 = "CAR_ZONE_COLUMN_CENTER";
        goto L38
    L25:
        if (r3 != 64) goto L27;
        r1 = "CAR_ZONE_COLUMN_RIGHT";
        goto L38
    L27:
        if (r3 != 80) goto L29;
        r1 = "CAR_ZONE_COLUMN_DRIVER";
        goto L38
    L29:
        if (r3 != 96) goto L38;
        r1 = "CAR_ZONE_COLUMN_PASSENGER";
        goto L38
    L5:
        if (r0 != 1) goto L7;
        r02 = "CAR_ZONE_ROW_FIRST";
        goto L18
    L7:
        if (r0 != 2) goto L9;
        r02 = "CAR_ZONE_ROW_SECOND";
        goto L18
    L9:
        if (r0 != 3) goto L11;
        r02 = "CAR_ZONE_ROW_THIRD";
        goto L18
    L11:
        if (r0 == 4) goto L13;
        r02 = "UNKNOWN";
        goto L18
    L13:
        r02 = "CAR_ZONE_ROW_EXCLUDE_FIRST";
        goto L18
    }
}
