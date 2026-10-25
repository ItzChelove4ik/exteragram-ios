package j$.time;

import j$.time.temporal.ChronoUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class q {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f437a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f438b = null;

    static {
        int[] r0 = new int[ChronoUnit.values().length];
        f438b = r0;
        r0[ChronoUnit.MONTHS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L20
    L31:
        f438b[ChronoUnit.YEARS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L21
    L39:
        f438b[ChronoUnit.DECADES.ordinal()] = 3;     // Catch: NoSuchFieldError -> L22
    L51:
        f438b[ChronoUnit.CENTURIES.ordinal()] = 4;     // Catch: NoSuchFieldError -> L23
    L35:
        f438b[ChronoUnit.MILLENNIA.ordinal()] = 5;     // Catch: NoSuchFieldError -> L24
    L41:
        f438b[ChronoUnit.ERAS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L25
    L13:
        int[] r5 = new int[j$.time.temporal.a.values().length];
        f437a = r5;
        r5[j$.time.temporal.a.MONTH_OF_YEAR.ordinal()] = 1;     // Catch: NoSuchFieldError -> L26
    L45:
        f437a[j$.time.temporal.a.PROLEPTIC_MONTH.ordinal()] = 2;     // Catch: NoSuchFieldError -> L27
    L33:
        f437a[j$.time.temporal.a.YEAR_OF_ERA.ordinal()] = 3;     // Catch: NoSuchFieldError -> L28
    L37:
        f437a[j$.time.temporal.a.YEAR.ordinal()] = 4;     // Catch: NoSuchFieldError -> L29
    L47:
        f437a[j$.time.temporal.a.ERA.ordinal()] = 5;     // Catch: NoSuchFieldError -> L30
        return;
    }
}
