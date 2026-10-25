package j$.util.stream;

import j$.desugar.sun.nio.fs.g;
import java.util.List;
import java.util.function.Function;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class d implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object r1) {
        return g.p(((List) r1).toArray());
    }
}
