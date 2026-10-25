package kotlinx.coroutines.channels;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.selects.SelectInstance;

/* loaded from: classes5.dex */
public final /* synthetic */ class BufferedChannel$$ExternalSyntheticLambda2 implements Function3 {
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ BufferedChannel f$1;
    public final /* synthetic */ SelectInstance f$2;

    public /* synthetic */ BufferedChannel$$ExternalSyntheticLambda2(Object r1, BufferedChannel r2, SelectInstance r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object r7, Object r8, Object r9) {
        return BufferedChannel.m3524$r8$lambda$yCDUJIks9rZw5_YDKmbWJBUDh4(this.f$0, this.f$1, this.f$2, (Throwable) r7, r8, (CoroutineContext) r9);
    }
}
