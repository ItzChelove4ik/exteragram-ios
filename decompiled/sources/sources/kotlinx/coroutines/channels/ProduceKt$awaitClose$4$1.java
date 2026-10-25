package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProduceKt$awaitClose$4$1 implements Function1<Throwable, Unit> {
    final /* synthetic */ CancellableContinuation<Unit> $cont;

    /* JADX WARN: Multi-variable type inference failed */
    public ProduceKt$awaitClose$4$1(CancellableContinuation<? super Unit> r1) {
        this.$cont = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r1) {
        CancellableContinuation<Unit> r0 = this.$cont;
        Result.Companion r12 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
    }
}
