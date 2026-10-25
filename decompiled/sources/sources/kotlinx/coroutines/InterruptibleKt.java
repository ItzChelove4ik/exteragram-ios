package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a.\u0010\u0005\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0086@¢\u0006\u0004\b\u0005\u0010\u0006\u001a+\u0010\b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0007\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"T", "Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlin/Function0;", "block", "runInterruptible", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coroutineContext", "runInterruptibleInExpectedContext", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class InterruptibleKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.InterruptibleKt$runInterruptible$2", f = "Interruptible.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: kotlinx.coroutines.InterruptibleKt$runInterruptible$2, reason: invalid class name */
    public static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function0<T> $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function0<? extends T> r1, Continuation<? super AnonymousClass2> r2) {
            this.$block = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$block, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            CoroutineScope r0 = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r3);
            return InterruptibleKt.access$runInterruptibleInExpectedContext(r0.getCoroutineContext(), this.$block);
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public static final /* synthetic */ Object access$runInterruptibleInExpectedContext(CoroutineContext r0, Function0 r1) {
        return runInterruptibleInExpectedContext(r0, r1);
    }

    public static final <T> Object runInterruptible(CoroutineContext r2, Function0<? extends T> r3, Continuation<? super T> r4) {
        return BuildersKt.withContext(r2, new AnonymousClass2(r3, null), r4);
    }

    public static /* synthetic */ Object runInterruptible$default(CoroutineContext r0, Function0 r1, Continuation r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r0 = EmptyCoroutineContext.INSTANCE;
    L6:
        return runInterruptible(r0, r1, r2);
    }

    private static final <T> T runInterruptibleInExpectedContext(CoroutineContext r1, Function0<? extends T> r2) {
        ThreadState r0 = new ThreadState();     // Catch: InterruptedException -> L9
        r0.setup(JobKt.getJob(r1));     // Catch: InterruptedException -> L9
        T r12 = r2.invoke();     // Catch: Throwable -> L6
        r0.clearInterrupt();     // Catch: InterruptedException -> L9
        return r12;
    L6:
        th = move-exception;
        r0.clearInterrupt();     // Catch: InterruptedException -> L9
        throw th;     // Catch: InterruptedException -> L9
    L9:
        e = move-exception;
        throw new CancellationException("Blocking call was interrupted due to parent cancellation").initCause(e);
    }
}
