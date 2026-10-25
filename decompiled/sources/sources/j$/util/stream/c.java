package j$.util.stream;

import java.util.List;
import java.util.function.BinaryOperator;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class c implements BinaryOperator {
    @Override // java.util.function.BiFunction
    public final Object apply(Object r1, Object r2) {
        List r12 = (List) r1;
        r12.addAll((List) r2);
        return r12;
    }
}
