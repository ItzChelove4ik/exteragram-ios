package j$.time;

import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalUnit;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class e implements j$.time.temporal.m {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f367a;

    public /* synthetic */ e(int r1) {
        this.f367a = r1;
    }

    @Override // j$.time.temporal.m
    public Temporal e(Temporal r3) {
        j$.time.temporal.a r2 = j$.time.temporal.a.DAY_OF_MONTH;
        return r3.a(r3.l(r2).d, r2);
    }

    public Object l(j$.time.temporal.l r3) {
        int r2 = this.f367a;
        e r0 = j$.time.temporal.q.f463a;
        switch(r2) {
            case 0: goto L36;
            case 1: goto L34;
            case 2: goto L27;
            case 3: goto L4;
            case 4: goto L26;
            case 5: goto L24;
            case 6: goto L22;
            case 7: goto L17;
            case 8: goto L12;
            case 9: goto L8;
            default: goto L4;
        };
    L4:
        j$.time.temporal.a r22 = j$.time.temporal.a.NANO_OF_DAY;
        if (r3.i(r22) == true) goto L7;
        return null;
    L7:
        return LocalTime.S(r3.k(r22));
    L8:
        j$.time.temporal.a r23 = j$.time.temporal.a.EPOCH_DAY;
        if (r3.i(r23) == true) goto L11;
        return null;
    L11:
        return LocalDate.g0(r3.k(r23));
    L12:
        ZoneId r24 = (ZoneId) r3.d(r0);
        if (r24 == null) goto L16;
        return r24;
    L16:
        return (ZoneId) r3.d(j$.time.temporal.q.d);
    L17:
        j$.time.temporal.a r25 = j$.time.temporal.a.OFFSET_SECONDS;
        if (r3.i(r25) == true) goto L20;
        return null;
    L20:
        return ZoneOffset.ofTotalSeconds(r3.g(r25));
    L27:
        ZoneId r26 = (ZoneId) r3.d(r0);
        if (r26 != null) goto L30;
        return null;
    L30:
        if ((r26 instanceof ZoneOffset) == false) goto L32;
        return null;
    L32:
        return r26;
    L22:
        return (TemporalUnit) r3.d(j$.time.temporal.q.f465c);
    L24:
        return (j$.time.chrono.k) r3.d(j$.time.temporal.q.f464b);
    L26:
        return (ZoneId) r3.d(r0);
    L34:
        return ZonedDateTime.D(r3);
    L36:
        return Instant.D(r3);
    }

    public String toString() {
        switch(this.f367a) {
            case 4: goto L18;
            case 5: goto L16;
            case 6: goto L14;
            case 7: goto L12;
            case 8: goto L10;
            case 9: goto L8;
            case 10: goto L6;
            default: goto L5;
        };
    L6:
        return "LocalTime";
    L8:
        return "LocalDate";
    L10:
        return "Zone";
    L12:
        return "ZoneOffset";
    L14:
        return "Precision";
    L16:
        return "Chronology";
    L18:
        return "ZoneId";
    L5:
        return super.toString();
    }
}
