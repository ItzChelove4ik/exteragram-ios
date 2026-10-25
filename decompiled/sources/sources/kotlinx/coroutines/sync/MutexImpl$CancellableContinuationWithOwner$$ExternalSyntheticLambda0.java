package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.sync.MutexImpl;

/* loaded from: classes.dex */
public final /* synthetic */ class MutexImpl$CancellableContinuationWithOwner$$ExternalSyntheticLambda0 implements Function3 {
    public final /* synthetic */ MutexImpl f$0;
    public final /* synthetic */ MutexImpl.CancellableContinuationWithOwner f$1;

    public /* synthetic */ MutexImpl$CancellableContinuationWithOwner$$ExternalSyntheticLambda0(MutexImpl r1, MutexImpl.CancellableContinuationWithOwner r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object r2, Object r3, Object r4) {
        return MutexImpl.CancellableContinuationWithOwner.m3564$r8$lambda$hZgjKV3c2GVqTUQcoCzQE6L8n4(this.f$0, this.f$1, (Throwable) r2, (Unit) r3, (CoroutineContext) r4);
    }
}
