package j$.time;

import j$.time.temporal.ChronoUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class i {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f425a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f426b = null;

    static {
        int[] r0 = new int[ChronoUnit.values().length];
        f426b = r0;
        r0[ChronoUnit.NANOS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L33
    L81:
        f426b[ChronoUnit.MICROS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L34
    L61:
        f426b[ChronoUnit.MILLIS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L35
    L77:
        f426b[ChronoUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L36
    L83:
        f426b[ChronoUnit.MINUTES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L37
    L65:
        f426b[ChronoUnit.HOURS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L38
    L67:
        f426b[ChronoUnit.HALF_DAYS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L39
    L16:
        int[] r7 = new int[j$.time.temporal.a.values().length];
        f425a = r7;
        r7[j$.time.temporal.a.NANO_OF_SECOND.ordinal()] = 1;     // Catch: NoSuchFieldError -> L40
    L59:
        f425a[j$.time.temporal.a.NANO_OF_DAY.ordinal()] = 2;     // Catch: NoSuchFieldError -> L41
    L87:
        f425a[j$.time.temporal.a.MICRO_OF_SECOND.ordinal()] = 3;     // Catch: NoSuchFieldError -> L42
    L97:
        f425a[j$.time.temporal.a.MICRO_OF_DAY.ordinal()] = 4;     // Catch: NoSuchFieldError -> L43
    L57:
        f425a[j$.time.temporal.a.MILLI_OF_SECOND.ordinal()] = 5;     // Catch: NoSuchFieldError -> L44
    L71:
        f425a[j$.time.temporal.a.MILLI_OF_DAY.ordinal()] = 6;     // Catch: NoSuchFieldError -> L45
    L95:
        f425a[j$.time.temporal.a.SECOND_OF_MINUTE.ordinal()] = 7;     // Catch: NoSuchFieldError -> L46
    L55:
        f425a[j$.time.temporal.a.SECOND_OF_DAY.ordinal()] = 8;     // Catch: NoSuchFieldError -> L47
    L85:
        f425a[j$.time.temporal.a.MINUTE_OF_HOUR.ordinal()] = 9;     // Catch: NoSuchFieldError -> L48
    L89:
        f425a[j$.time.temporal.a.MINUTE_OF_DAY.ordinal()] = 10;     // Catch: NoSuchFieldError -> L49
    L73:
        f425a[j$.time.temporal.a.HOUR_OF_AMPM.ordinal()] = 11;     // Catch: NoSuchFieldError -> L50
    L79:
        f425a[j$.time.temporal.a.CLOCK_HOUR_OF_AMPM.ordinal()] = 12;     // Catch: NoSuchFieldError -> L51
    L63:
        f425a[j$.time.temporal.a.HOUR_OF_DAY.ordinal()] = 13;     // Catch: NoSuchFieldError -> L52
    L69:
        f425a[j$.time.temporal.a.CLOCK_HOUR_OF_DAY.ordinal()] = 14;     // Catch: NoSuchFieldError -> L53
    L93:
        f425a[j$.time.temporal.a.AMPM_OF_DAY.ordinal()] = 15;     // Catch: NoSuchFieldError -> L54
        return;
    }
}
