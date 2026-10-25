package j$.time.chrono;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum s extends Enum implements l {
    public static final s BCE = null;
    public static final s CE = null;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ s[] f354a = null;

    static {
        s r0 = new s("BCE", 0);
        BCE = r0;
        s r1 = new s("CE", 1);
        CE = r1;
        f354a = new s[]{r0, r1};
    }

    public static s valueOf(String r1) {
        return (s) Enum.valueOf(s.class, r1);
    }

    public static s[] values() {
        return (s[]) f354a.clone();
    }

    @Override // j$.time.chrono.l
    public final int getValue() {
        return ordinal();
    }
}
