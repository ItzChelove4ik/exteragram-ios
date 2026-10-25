package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* loaded from: classes5.dex */
public final /* synthetic */ class CancellableContinuationImpl$$ExternalSyntheticLambda0 implements Function3 {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ CancellableContinuationImpl$$ExternalSyntheticLambda0(Function1 r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object r1, Object r2, Object r3) {
        return CancellableContinuationImpl.m3520$r8$lambda$Dz09WJjB9rjFw3DzYrFVSkki4w(this.f$0, (Throwable) r1, r2, (CoroutineContext) r3);
    }
}
