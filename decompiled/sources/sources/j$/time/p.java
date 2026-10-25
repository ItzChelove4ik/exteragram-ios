package j$.time;

import j$.time.temporal.ChronoUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class p {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f435a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f436b = null;

    static {
        int[] r0 = new int[ChronoUnit.values().length];
        f436b = r0;
        r0[ChronoUnit.YEARS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L15
    L23:
        f436b[ChronoUnit.DECADES.ordinal()] = 2;     // Catch: NoSuchFieldError -> L16
    L29:
        f436b[ChronoUnit.CENTURIES.ordinal()] = 3;     // Catch: NoSuchFieldError -> L17
    L37:
        f436b[ChronoUnit.MILLENNIA.ordinal()] = 4;     // Catch: NoSuchFieldError -> L18
    L25:
        f436b[ChronoUnit.ERAS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L19
    L10:
        int[] r3 = new int[j$.time.temporal.a.values().length];
        f435a = r3;
        r3[j$.time.temporal.a.YEAR_OF_ERA.ordinal()] = 1;     // Catch: NoSuchFieldError -> L20
    L31:
        f435a[j$.time.temporal.a.YEAR.ordinal()] = 2;     // Catch: NoSuchFieldError -> L21
    L35:
        f435a[j$.time.temporal.a.ERA.ordinal()] = 3;     // Catch: NoSuchFieldError -> L22
        return;
    }
}
