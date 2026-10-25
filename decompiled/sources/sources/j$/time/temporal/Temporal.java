package j$.time.temporal;

import j$.time.LocalDate;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface Temporal extends l {
    Temporal a(long r1, p r3);

    Temporal b(long r1, TemporalUnit r3);

    default Temporal c(long r3, TemporalUnit r5) {
        if (r3 != Long.MIN_VALUE) goto L7;
        Temporal this = b(LongCompanionObject.MAX_VALUE, r5);
        long r32 = 1;
    L6:
        return this.b(r32, r5);
    L7:
        r32 = -r3;
        goto L6
    }

    default Temporal h(LocalDate r1) {
        return r1.e(this);
    }

    long n(Temporal r1, TemporalUnit r2);
}
