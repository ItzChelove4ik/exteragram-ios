package j$.time;

import j$.time.temporal.ChronoUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class n {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f432a = null;

    static {
        int[] r0 = new int[ChronoUnit.values().length];
        f432a = r0;
        r0[ChronoUnit.NANOS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
    L18:
        f432a[ChronoUnit.MICROS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
    L22:
        f432a[ChronoUnit.MILLIS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
    L30:
        f432a[ChronoUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
    L20:
        f432a[ChronoUnit.MINUTES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
    L24:
        f432a[ChronoUnit.HOURS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
    L26:
        f432a[ChronoUnit.HALF_DAYS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L17
        return;
    }
}
