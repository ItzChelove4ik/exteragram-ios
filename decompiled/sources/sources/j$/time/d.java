package j$.time;

import j$.time.temporal.ChronoUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class d {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f366a = null;

    static {
        int[] r0 = new int[ChronoUnit.values().length];
        f366a = r0;
        r0[ChronoUnit.NANOS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
    L12:
        f366a[ChronoUnit.MICROS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
    L14:
        f366a[ChronoUnit.MILLIS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
    L18:
        f366a[ChronoUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
        return;
    }
}
