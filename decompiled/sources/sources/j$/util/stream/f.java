package j$.util.stream;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collector;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class f {

    /* renamed from: a, reason: collision with root package name */
    public static final Set f494a = null;

    static {
        f494a = Collections.EMPTY_SET;
        Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED));
    }
}
