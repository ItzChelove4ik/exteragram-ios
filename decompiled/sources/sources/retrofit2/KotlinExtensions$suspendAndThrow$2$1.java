package retrofit2;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class KotlinExtensions$suspendAndThrow$2$1 implements Runnable {
    final /* synthetic */ Continuation<?> $continuation;
    final /* synthetic */ Throwable $this_suspendAndThrow;

    public KotlinExtensions$suspendAndThrow$2$1(Continuation<?> r1, Throwable r2) {
        this.$continuation = r1;
        this.$this_suspendAndThrow = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Continuation r0 = IntrinsicsKt.intercepted(this.$continuation);
        Result.Companion r1 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(this.$this_suspendAndThrow)));
    }
}
