package j$.time.chrono;

import j$.time.temporal.ChronoUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class c {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f326a = null;

    static {
        int[] r0 = new int[ChronoUnit.values().length];
        f326a = r0;
        r0[ChronoUnit.DAYS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
    L20:
        f326a[ChronoUnit.WEEKS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
    L24:
        f326a[ChronoUnit.MONTHS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
    L34:
        f326a[ChronoUnit.YEARS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
    L22:
        f326a[ChronoUnit.DECADES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
    L26:
        f326a[ChronoUnit.CENTURIES.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
    L28:
        f326a[ChronoUnit.MILLENNIA.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
    L30:
        f326a[ChronoUnit.ERAS.ordinal()] = 8;     // Catch: NoSuchFieldError -> L19
        return;
    }
}
