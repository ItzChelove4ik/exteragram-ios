package j$.time;

import j$.time.format.TextStyle;
import j$.time.format.w;
import j$.time.format.z;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import java.util.Locale;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum DayOfWeek extends Enum<DayOfWeek> implements j$.time.temporal.l, j$.time.temporal.m {
    public static final DayOfWeek FRIDAY = null;
    public static final DayOfWeek MONDAY = null;
    public static final DayOfWeek SATURDAY = null;
    public static final DayOfWeek SUNDAY = null;
    public static final DayOfWeek THURSDAY = null;
    public static final DayOfWeek TUESDAY = null;
    public static final DayOfWeek WEDNESDAY = null;

    /* renamed from: a, reason: collision with root package name */
    public static final DayOfWeek[] f280a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ DayOfWeek[] f281b = null;

    static {
        DayOfWeek r0 = new DayOfWeek("MONDAY", 0);
        MONDAY = r0;
        DayOfWeek r1 = new DayOfWeek("TUESDAY", 1);
        TUESDAY = r1;
        DayOfWeek r2 = new DayOfWeek("WEDNESDAY", 2);
        WEDNESDAY = r2;
        DayOfWeek r3 = new DayOfWeek("THURSDAY", 3);
        THURSDAY = r3;
        DayOfWeek r4 = new DayOfWeek("FRIDAY", 4);
        FRIDAY = r4;
        DayOfWeek r5 = new DayOfWeek("SATURDAY", 5);
        SATURDAY = r5;
        DayOfWeek r6 = new DayOfWeek("SUNDAY", 6);
        SUNDAY = r6;
        f281b = new DayOfWeek[]{r0, r1, r2, r3, r4, r5, r6};
        f280a = values();
    }

    public static DayOfWeek s(int r2) {
        if (r2 >= 1) goto L5;
    L8:
        j$.nio.file.b.b("Invalid value for DayOfWeek: ", r2);
        return null;
    L5:
        if (r2 > 7) goto L8;
        return f280a[r2 - 1];
    }

    public static DayOfWeek valueOf(String r1) {
        return (DayOfWeek) Enum.valueOf(DayOfWeek.class, r1);
    }

    public static DayOfWeek[] values() {
        return (DayOfWeek[]) f281b.clone();
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f465c) goto L7;
        return ChronoUnit.DAYS;
    L7:
        return super.d(r2);
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        return r4.a(getValue(), j$.time.temporal.a.DAY_OF_WEEK);
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.DAY_OF_WEEK) goto L7;
        return getValue();
    L7:
        return super.g(r2);
    }

    public String getDisplayName(TextStyle r5, Locale r6) {
        j$.time.format.q r0 = new j$.time.format.q();
        j$.time.temporal.a r1 = j$.time.temporal.a.DAY_OF_WEEK;
        Objects.requireNonNull(r1, "field");
        Objects.requireNonNull(r5, "textStyle");
        r0.b(new j$.time.format.n(r1, r5, w.f416c));
        return r0.l(r6, z.SMART, null).a(this);
    }

    public final int getValue() {
        return ordinal() + 1;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L9;
        return false;
    L9:
        if (r2.s(this) == false) goto L16;
        return true;
    L16:
        return false;
    L5:
        if (r2 == j$.time.temporal.a.DAY_OF_WEEK) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.DAY_OF_WEEK) goto L7;
        return getValue();
    L7:
        if ((r2 instanceof j$.time.temporal.a) == true) goto L11;
        return r2.S(this);
    L11:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r2));
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.DAY_OF_WEEK) goto L7;
        return r2.K();
    L7:
        return super.l(r2);
    }
}
