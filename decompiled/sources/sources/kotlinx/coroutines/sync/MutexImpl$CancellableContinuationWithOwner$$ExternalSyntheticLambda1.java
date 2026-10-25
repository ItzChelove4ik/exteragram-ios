package kotlinx.coroutines.sync;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.sync.MutexImpl;

/* loaded from: classes5.dex */
public final /* synthetic */ class MutexImpl$CancellableContinuationWithOwner$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ MutexImpl f$0;
    public final /* synthetic */ MutexImpl.CancellableContinuationWithOwner f$1;

    public /* synthetic */ MutexImpl$CancellableContinuationWithOwner$$ExternalSyntheticLambda1(MutexImpl r1, MutexImpl.CancellableContinuationWithOwner r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return MutexImpl.CancellableContinuationWithOwner.m3565$r8$lambda$zaoas616xVDKfkb6aEv1GpR5c(this.f$0, this.f$1, (Throwable) r2);
    }
}
