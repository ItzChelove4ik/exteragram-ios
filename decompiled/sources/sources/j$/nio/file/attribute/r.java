package j$.nio.file.attribute;

import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class r {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f207a = null;

    static {
        int[] r0 = new int[TimeUnit.values().length];
        f207a = r0;
        r0[TimeUnit.DAYS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
    L18:
        f207a[TimeUnit.HOURS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
    L22:
        f207a[TimeUnit.MINUTES.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
    L30:
        f207a[TimeUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
    L20:
        f207a[TimeUnit.MILLISECONDS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
    L24:
        f207a[TimeUnit.MICROSECONDS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
    L26:
        f207a[TimeUnit.NANOSECONDS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L17
        return;
    }
}
