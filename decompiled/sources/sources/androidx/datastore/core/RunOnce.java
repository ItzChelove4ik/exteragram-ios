package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u0005H¤@¢\u0006\u0002\u0010\tJ\u000e\u0010\u000b\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/datastore/core/RunOnce;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "didRun", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "runMutex", "Lkotlinx/coroutines/sync/Mutex;", "awaitComplete", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doRun", "runIfNeeded", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDataStoreImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataStoreImpl.kt\nandroidx/datastore/core/RunOnce\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,538:1\n120#2,10:539\n*S KotlinDebug\n*F\n+ 1 DataStoreImpl.kt\nandroidx/datastore/core/RunOnce\n*L\n495#1:539,10\n*E\n"})
/* loaded from: classes.dex */
public abstract class RunOnce {
    private final CompletableDeferred<Unit> didRun;
    private final Mutex runMutex;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.RunOnce", f = "DataStoreImpl.kt", i = {0, 0, 1, 1}, l = {544, 497}, m = "runIfNeeded", n = {"this", "$this$withLock_u24default$iv", "this", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: androidx.datastore.core.RunOnce$runIfNeeded$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RunOnce this$0;

        public AnonymousClass1(RunOnce r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.runIfNeeded(this);
        }
    }

    public RunOnce() {
        this.runMutex = MutexKt.Mutex$default(false, 1, null);
        this.didRun = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    public final Object awaitComplete(Continuation<? super Unit> r1) {
        Object r0 = this.didRun.await(r1);
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r0;
    L6:
        return Unit.INSTANCE;
    }

    public abstract Object doRun(Continuation<? super Unit> r1);

    public final Object runIfNeeded(Continuation<? super Unit> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L20;
        if (r2 == 1) goto L19;
        if (r2 != 2) goto L17;
        Mutex r7 = (Mutex) r0.L$1;
        RunOnce r02 = (RunOnce) r0.L$0;
        ResultKt.throwOnFailure(r82);     // Catch: Throwable -> L15
    L38:
        CompletableDeferred<Unit> r83 = r02.didRun;     // Catch: Throwable -> L15
        Unit r03 = Unit.INSTANCE;     // Catch: Throwable -> L15
        r83.complete(r03);     // Catch: Throwable -> L15
        r7.unlock(null);
        return r03;
    L15:
        Throwable th = th;
    L41:
        r7.unlock(null);
        throw th;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        Mutex r72 = (Mutex) r0.L$1;
        RunOnce r22 = (RunOnce) r0.L$0;
        ResultKt.throwOnFailure(r82);
        Mutex r84 = r72;
        RunOnce this = r22;
    L45:
    L32:
        th = move-exception;
        Mutex r6 = r84;
        th = th;
        r7 = r6;
        goto L41
    L28:
        if (this.didRun.isCompleted() == false) goto L34;
        Unit r73 = Unit.INSTANCE;     // Catch: Throwable -> L32
        r84.unlock(null);
        return r73;
    L34:
        r0.L$0 = this;     // Catch: Throwable -> L32
        r0.L$1 = r84;     // Catch: Throwable -> L32
        r0.label = 2;     // Catch: Throwable -> L32
        if (this.doRun(r0) != r12) goto L37;
    L36:
        return r12;
    L37:
        r02 = this;
        r7 = r84;
        goto L38
    L20:
        ResultKt.throwOnFailure(r82);
        if (this.didRun.isCompleted() == true) goto L23;
        r84 = this.runMutex;
        r0.L$0 = this;
        r0.L$1 = r84;
        r0.label = 1;
        if (r84.lock(null, r0) != r12) goto L45;
    L23:
        return Unit.INSTANCE;
    L7:
        r0 = new AnonymousClass1(this, r8);
        goto L8
    }
}
