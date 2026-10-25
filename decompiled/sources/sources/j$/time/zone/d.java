package j$.time.zone;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum d extends Enum {
    public static final d STANDARD = null;
    public static final d UTC = null;
    public static final d WALL = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ d[] f478a = null;

    static {
        d r0 = new d("UTC", 0);
        UTC = r0;
        d r1 = new d("WALL", 1);
        WALL = r1;
        d r2 = new d("STANDARD", 2);
        STANDARD = r2;
        f478a = new d[]{r0, r1, r2};
    }

    public static d valueOf(String r1) {
        return (d) Enum.valueOf(d.class, r1);
    }

    public static d[] values() {
        return (d[]) f478a.clone();
    }
}
