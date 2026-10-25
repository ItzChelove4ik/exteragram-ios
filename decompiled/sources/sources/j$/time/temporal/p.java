package j$.time.temporal;

import j$.time.format.y;
import j$.time.format.z;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public interface p {
    s D(l r1);

    default l I(Map r1, y r2, z r3) {
        return null;
    }

    s K();

    long S(l r1);

    Temporal Y(Temporal r1, long r2);

    boolean isDateBased();

    boolean s(l r1);
}
