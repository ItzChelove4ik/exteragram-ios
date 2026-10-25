package kotlinx.coroutines.channels;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* loaded from: classes5.dex */
public final /* synthetic */ class BufferedChannel$$ExternalSyntheticLambda1 implements Function3 {
    public final /* synthetic */ Function1 f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ BufferedChannel$$ExternalSyntheticLambda1(Function1 r1, Object r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object r2, Object r3, Object r4) {
        return BufferedChannel.$r8$lambda$3LIDflb3aV6rxzAC9EUPHtqhyps(this.f$0, this.f$1, (Throwable) r2, r3, (CoroutineContext) r4);
    }
}
