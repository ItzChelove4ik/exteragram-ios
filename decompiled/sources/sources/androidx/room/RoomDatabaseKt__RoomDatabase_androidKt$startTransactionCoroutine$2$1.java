package androidx.room;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RoomDatabaseKt__RoomDatabase_androidKt$startTransactionCoroutine$2$1 implements Runnable {
    final /* synthetic */ CancellableContinuation<R> $continuation;
    final /* synthetic */ RoomDatabase $this_startTransactionCoroutine;
    final /* synthetic */ Function2<CoroutineScope, Continuation<? super R>, Object> $transactionBlock;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabaseKt__RoomDatabase_androidKt$startTransactionCoroutine$2$1$1", f = "RoomDatabase.android.kt", i = {}, l = {2087}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.RoomDatabaseKt__RoomDatabase_androidKt$startTransactionCoroutine$2$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CancellableContinuation<R> $continuation;
        final /* synthetic */ RoomDatabase $this_startTransactionCoroutine;
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super R>, Object> $transactionBlock;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(RoomDatabase r1, CancellableContinuation<? super R> r2, Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> r3, Continuation<? super AnonymousClass1> r4) {
            this.$this_startTransactionCoroutine = r1;
            this.$continuation = r2;
            this.$transactionBlock = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$this_startTransactionCoroutine, this.$continuation, this.$transactionBlock, r5);
            r0.L$0 = r4;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            Continuation r4 = (Continuation) this.L$0;
            ResultKt.throwOnFailure(r5);
        L12:
            r4.resumeWith(Result.m2018constructorimpl(r5));
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r5);
            ContinuationInterceptor r52 = (ContinuationInterceptor) ((CoroutineScope) this.L$0).getCoroutineContext().get(ContinuationInterceptor.Key);
            CoroutineContext r53 = RoomDatabaseKt__RoomDatabase_androidKt.access$createTransactionContext(this.$this_startTransactionCoroutine, r52);
            Continuation r12 = this.$continuation;
            Result.Companion r3 = Result.Companion;
            Function2<CoroutineScope, Continuation<? super R>, Object> r32 = this.$transactionBlock;
            this.L$0 = r12;
            this.label = 1;
            r5 = BuildersKt.withContext(r53, r32, this);
            if (r5 != r0) goto L11;
            return r0;
        L11:
            r4 = r12;
            goto L12
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RoomDatabaseKt__RoomDatabase_androidKt$startTransactionCoroutine$2$1(CancellableContinuation<? super R> r1, RoomDatabase r2, Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> r3) {
        this.$continuation = r1;
        this.$this_startTransactionCoroutine = r2;
        this.$transactionBlock = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BuildersKt.runBlocking(this.$continuation.getContext().minusKey(ContinuationInterceptor.Key), new AnonymousClass1(this.$this_startTransactionCoroutine, this.$continuation, this.$transactionBlock, null));     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        this.$continuation.cancel(th);
    }
}
