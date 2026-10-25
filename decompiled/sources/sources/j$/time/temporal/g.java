package j$.time.temporal;

import j$.time.DayOfWeek;
import j$.time.LocalDate;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum g extends Enum implements p {
    public static final g DAY_OF_QUARTER = null;
    public static final g QUARTER_OF_YEAR = null;
    public static final g WEEK_BASED_YEAR = null;
    public static final g WEEK_OF_WEEK_BASED_YEAR = null;

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f449a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ g[] f450b = null;

    static {
        c r0 = new c();
        DAY_OF_QUARTER = r0;
        d r1 = new d();
        QUARTER_OF_YEAR = r1;
        e r2 = new e();
        WEEK_OF_WEEK_BASED_YEAR = r2;
        f r3 = new f();
        WEEK_BASED_YEAR = r3;
        f450b = new g[]{r0, r1, r2, r3};
        f449a = new int[]{0, 90, 181, 273, 0, 91, 182, 274};
    }

    public static int a0(LocalDate r5) {
        int r0 = r5.S().ordinal();
        int r1 = r5.Y() - 1;
        int r02 = (3 - r0) + r1;
        int r03 = r02 - ((r02 / 7) * 7);
        int r3 = r03 - 3;
        if (r3 >= (-3)) goto L5;
        r3 = r03 + 4;
    L5:
        if (r1 < r3) goto L7;
        int r12 = ((r1 - r3) / 7) + 1;
        if (r12 != 53) goto L21;
        if (r3 == (-3)) goto L21;
        if (r3 == (-2)) goto L18;
    L20:
        return 1;
    L18:
        if (r5.c0() == false) goto L20;
    L21:
        return r12;
    L7:
        if (r5.Y() == 180) goto L11;
        r5 = LocalDate.h0(r5.f288a, 180);
    L11:
        return (int) d0(r5.l0(-1)).d;
    }

    public static int b0(LocalDate r4) {
        int r0 = r4.getYear();
        int r1 = r4.Y();
        if (r1 > 3) goto L9;
        if ((r1 - r4.S().ordinal()) < (-2)) goto L7;
        return r0;
    L7:
        return r0 - 1;
    L9:
        if (r1 >= 363) goto L11;
        return r0;
    L11:
        if ((((r1 - 363) - (r4.c0() ? 1 : 0)) - r4.S().ordinal()) >= 0) goto L13;
        return r0;
    L13:
        return r0 + 1;
    }

    public static int c0(int r2) {
        LocalDate r22 = LocalDate.of(r2, 1, 1);
        if (r22.S() != DayOfWeek.THURSDAY) goto L5;
        return 53;
    L5:
        if (r22.S() == DayOfWeek.WEDNESDAY) goto L7;
        return 52;
    L7:
        if (r22.c0() == true) goto L14;
        return 52;
    L14:
        return 53;
    }

    public static s d0(LocalDate r4) {
        return s.f(1, c0(b0(r4)));
    }

    public static g valueOf(String r1) {
        return (g) Enum.valueOf(g.class, r1);
    }

    public static g[] values() {
        return (g[]) f450b.clone();
    }

    @Override // j$.time.temporal.p
    public final boolean isDateBased() {
        return true;
    }
}
