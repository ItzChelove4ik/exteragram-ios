package j$.time;

import com.android.dx.io.Opcodes;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public enum k extends Enum implements j$.time.temporal.l, j$.time.temporal.m {
    public static final k APRIL = null;
    public static final k AUGUST = null;
    public static final k DECEMBER = null;
    public static final k FEBRUARY = null;
    public static final k JANUARY = null;
    public static final k JULY = null;
    public static final k JUNE = null;
    public static final k MARCH = null;
    public static final k MAY = null;
    public static final k NOVEMBER = null;
    public static final k OCTOBER = null;
    public static final k SEPTEMBER = null;

    /* renamed from: a, reason: collision with root package name */
    public static final k[] f428a = null;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ k[] f429b = null;

    static {
        k r0 = new k("JANUARY", 0);
        JANUARY = r0;
        k r1 = new k("FEBRUARY", 1);
        FEBRUARY = r1;
        k r2 = new k("MARCH", 2);
        MARCH = r2;
        k r3 = new k("APRIL", 3);
        APRIL = r3;
        k r4 = new k("MAY", 4);
        MAY = r4;
        k r5 = new k("JUNE", 5);
        JUNE = r5;
        k r6 = new k("JULY", 6);
        JULY = r6;
        k r7 = new k("AUGUST", 7);
        AUGUST = r7;
        k r8 = new k("SEPTEMBER", 8);
        SEPTEMBER = r8;
        k r9 = new k("OCTOBER", 9);
        OCTOBER = r9;
        k r10 = new k("NOVEMBER", 10);
        NOVEMBER = r10;
        k r11 = new k("DECEMBER", 11);
        DECEMBER = r11;
        f429b = new k[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11};
        f428a = values();
    }

    public static k K(int r2) {
        if (r2 >= 1) goto L5;
    L8:
        j$.nio.file.b.b("Invalid value for MonthOfYear: ", r2);
        return null;
    L5:
        if (r2 > 12) goto L8;
        return f428a[r2 - 1];
    }

    public static k valueOf(String r1) {
        return (k) Enum.valueOf(k.class, r1);
    }

    public static k[] values() {
        return (k[]) f429b.clone();
    }

    public final int D(boolean r2) {
        int r1 = j.f427a[ordinal()];
        if (r1 != 1) goto L5;
        if (r2 == false) goto L19;
        return 29;
    L19:
        return 28;
    L5:
        if (r1 != 2) goto L7;
        return 30;
    L7:
        if (r1 != 3) goto L9;
        return 30;
    L9:
        if (r1 != 4) goto L11;
        return 30;
    L11:
        if (r1 == 5) goto L23;
        return 31;
    L23:
        return 30;
    }

    public final int I() {
        int r1 = j.f427a[ordinal()];
        if (r1 != 1) goto L5;
        return 29;
    L5:
        if (r1 != 2) goto L7;
        return 30;
    L7:
        if (r1 != 3) goto L9;
        return 30;
    L9:
        if (r1 != 4) goto L11;
        return 30;
    L11:
        if (r1 == 5) goto L20;
        return 31;
    L20:
        return 30;
    }

    @Override // j$.time.temporal.l
    public final Object d(e r2) {
        if (r2 != j$.time.temporal.q.f464b) goto L7;
        return j$.time.chrono.r.f353c;
    L7:
        if (r2 != j$.time.temporal.q.f465c) goto L11;
        return ChronoUnit.MONTHS;
    L11:
        return super.d(r2);
    }

    @Override // j$.time.temporal.m
    public final Temporal e(Temporal r4) {
        if (j$.time.chrono.k.r(r4).equals(j$.time.chrono.r.f353c) == true) goto L5;
        j$.nio.file.b.h("Adjustment only supported on ISO date-time");
        return null;
    L5:
        return r4.a(getValue(), j$.time.temporal.a.MONTH_OF_YEAR);
    }

    @Override // j$.time.temporal.l
    public final int g(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.MONTH_OF_YEAR) goto L7;
        return getValue();
    L7:
        return super.g(r2);
    }

    public final int getValue() {
        return ordinal() + 1;
    }

    @Override // j$.time.temporal.l
    public final boolean i(j$.time.temporal.p r2) {
        if ((r2 instanceof j$.time.temporal.a) == true) goto L5;
        if (r2 != null) goto L9;
        return false;
    L9:
        if (r2.s(this) == false) goto L16;
        return true;
    L16:
        return false;
    L5:
        if (r2 == j$.time.temporal.a.MONTH_OF_YEAR) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // j$.time.temporal.l
    public final long k(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.MONTH_OF_YEAR) goto L7;
        return getValue();
    L7:
        if ((r2 instanceof j$.time.temporal.a) == true) goto L11;
        return r2.S(this);
    L11:
        throw new j$.time.temporal.r(c.a("Unsupported field: ", r2));
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s l(j$.time.temporal.p r2) {
        if (r2 != j$.time.temporal.a.MONTH_OF_YEAR) goto L7;
        return r2.K();
    L7:
        return super.l(r2);
    }

    public final int s(boolean r2) {
        switch(j.f427a[ordinal()]) {
            case 1: goto L26;
            case 2: goto L25;
            case 3: goto L23;
            case 4: goto L21;
            case 5: goto L19;
            case 6: goto L16;
            case 7: goto L15;
            case 8: goto L13;
            case 9: goto L11;
            case 10: goto L9;
            case 11: goto L7;
            default: goto L5;
        };
    L16:
        return 1;
    L26:
        return 32;
    L5:
        return (r2 ? 1 : 0) + 335;
    L7:
        return (r2 ? 1 : 0) + 274;
    L9:
        return (r2 ? 1 : 0) + Opcodes.AND_INT_LIT16;
    L11:
        return (r2 ? 1 : 0) + 182;
    L13:
        return (r2 ? 1 : 0) + 121;
    L15:
        return (r2 ? 1 : 0) + 60;
    L19:
        return (r2 ? 1 : 0) + 305;
    L21:
        return (r2 ? 1 : 0) + 244;
    L23:
        return (r2 ? 1 : 0) + 152;
    L25:
        return (r2 ? 1 : 0) + 91;
    }
}
