package j$.time.temporal;

import j$.time.Duration;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface TemporalUnit {
    long between(Temporal r1, Temporal r2);

    Duration getDuration();

    boolean isDurationEstimated();

    Temporal s(Temporal r1, long r2);
}
