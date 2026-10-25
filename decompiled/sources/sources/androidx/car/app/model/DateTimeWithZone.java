package androidx.car.app.model;

import android.annotation.SuppressLint;
import c.f$$ExternalSyntheticBUOutline1;
import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.ZonedDateTime;
import j$.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public final class DateTimeWithZone {
    private static final long MAX_ZONE_OFFSET_SECONDS = 0;
    private final long mTimeSinceEpochMillis;
    private final int mZoneOffsetSeconds;
    private final String mZoneShortName;

    public static final class Api26Impl {
        public static DateTimeWithZone create(ZonedDateTime r5) {
            Objects.requireNonNull(r5);
            LocalDateTime r0 = r5.toLocalDateTime();
            ZoneId r52 = r5.getZone();
            ZoneOffset r1 = r52.getRules().getOffset(r0);
            return DateTimeWithZone.create(TimeUnit.SECONDS.toMillis(r0.toEpochSecond(r1)), r1.getTotalSeconds(), r52.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        }
    }

    static {
        MAX_ZONE_OFFSET_SECONDS = 64800;
    }

    private DateTimeWithZone() {
        this.mTimeSinceEpochMillis = 0;
        this.mZoneOffsetSeconds = 0;
        this.mZoneShortName = null;
    }

    private DateTimeWithZone(long r1, int r3, String r4) {
        this.mTimeSinceEpochMillis = r1;
        this.mZoneOffsetSeconds = r3;
        this.mZoneShortName = r4;
    }

    public static DateTimeWithZone create(long r6, int r8, String r9) {
        if (r6 >= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Time since epoch must be greater than or equal to zero");
        return null;
    L5:
        if (Math.abs(r8) > MAX_ZONE_OFFSET_SECONDS) goto L12;
        Objects.requireNonNull(r9);
        if (r9.isEmpty() == false) goto L9;
        f$$ExternalSyntheticBUOutline1.m("The time zone short name can not be null or empty");
        return null;
    L9:
        return new DateTimeWithZone(r6, r8, r9);
    L12:
        f$$ExternalSyntheticBUOutline1.m("Zone offset not in valid range: -18:00 to +18:00");
        return null;
    }

    public static DateTimeWithZone create(long r4, TimeZone r6) {
        if (r4 < 0) goto L6;
        Objects.requireNonNull(r6);
        return create(r4, (int) (r6.getOffset(r4) / 1000), r6.getDisplayName(false, 0));
    L6:
        f$$ExternalSyntheticBUOutline1.m("timeSinceEpochMillis must be greater than or equal to zero");
        return null;
    }

    public static DateTimeWithZone create(ZonedDateTime r0) {
        return Api26Impl.create(r0);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof DateTimeWithZone) == true) goto L8;
        return false;
    L8:
        DateTimeWithZone r82 = (DateTimeWithZone) r8;
        if (this.mTimeSinceEpochMillis == r82.mTimeSinceEpochMillis) goto L11;
    L15:
        return false;
    L11:
        if (this.mZoneOffsetSeconds != r82.mZoneOffsetSeconds) goto L15;
        if (Objects.equals(this.mZoneShortName, r82.mZoneShortName) == false) goto L15;
        return true;
    }

    public long getTimeSinceEpochMillis() {
        return this.mTimeSinceEpochMillis;
    }

    @SuppressLint({"MethodNameUnits"})
    public int getZoneOffsetSeconds() {
        return this.mZoneOffsetSeconds;
    }

    public String getZoneShortName() {
        return this.mZoneShortName;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.mTimeSinceEpochMillis), Integer.valueOf(this.mZoneOffsetSeconds), this.mZoneShortName});
    }

    public String toString() {
        return "[time since epoch (ms): " + this.mTimeSinceEpochMillis + "( " + new Date(this.mTimeSinceEpochMillis) + ")  zone offset (s): " + this.mZoneOffsetSeconds + ", zone: " + this.mZoneShortName + "]";
    }
}
