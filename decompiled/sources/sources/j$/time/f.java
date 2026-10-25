package j$.time;

import j$.time.temporal.ChronoUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class f {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f368a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f369b = null;

    static {
        int[] r0 = new int[ChronoUnit.values().length];
        f369b = r0;
        r0[ChronoUnit.NANOS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L20
    L32:
        f369b[ChronoUnit.MICROS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L21
    L38:
        f369b[ChronoUnit.MILLIS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L22
    L54:
        f369b[ChronoUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L23
    L34:
        f369b[ChronoUnit.MINUTES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L24
    L40:
        f369b[ChronoUnit.HOURS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L25
    L44:
        f369b[ChronoUnit.HALF_DAYS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L26
    L48:
        f369b[ChronoUnit.DAYS.ordinal()] = 8;     // Catch: NoSuchFieldError -> L27
    L14:
        int[] r4 = new int[j$.time.temporal.a.values().length];
        f368a = r4;
        r4[j$.time.temporal.a.NANO_OF_SECOND.ordinal()] = 1;     // Catch: NoSuchFieldError -> L28
    L42:
        f368a[j$.time.temporal.a.MICRO_OF_SECOND.ordinal()] = 2;     // Catch: NoSuchFieldError -> L29
    L46:
        f368a[j$.time.temporal.a.MILLI_OF_SECOND.ordinal()] = 3;     // Catch: NoSuchFieldError -> L30
    L36:
        f368a[j$.time.temporal.a.INSTANT_SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L31
        return;
    }
}
