package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002:\u0001'B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\r\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0082@¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\u000f\u0010\u000bJ\u001a\u0010\u0010\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0096@¢\u0006\u0004\b\u0010\u0010\u000eJ\u0019\u0010\u0011\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u007f\u0010\"\u001am\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\f0\u00180\u0018j\u0002`!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0013\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070&8\u0002X\u0082\u0004¨\u0006("}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl;", "Lkotlinx/coroutines/sync/SemaphoreAndMutexImpl;", "Lkotlinx/coroutines/sync/Mutex;", _UrlKt.FRAGMENT_ENCODE_SET, "locked", "<init>", "(Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "owner", _UrlKt.FRAGMENT_ENCODE_SET, "holdsLockImpl", "(Ljava/lang/Object;)I", _UrlKt.FRAGMENT_ENCODE_SET, "lockSuspend", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLockImpl", "lock", "tryLock", "(Ljava/lang/Object;)Z", "unlock", "(Ljava/lang/Object;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Lkotlin/Function3;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/ParameterName;", "name", "select", "param", "internalResult", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "onSelectCancellationUnlockConstructor", "Lkotlin/jvm/functions/Function3;", "isLocked", "()Z", "Lkotlinx/atomicfu/AtomicRef;", "CancellableContinuationWithOwner", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMutex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Mutex.kt\nkotlinx/coroutines/sync/MutexImpl\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,314:1\n452#2,12:315\n1#3:327\n*S KotlinDebug\n*F\n+ 1 Mutex.kt\nkotlinx/coroutines/sync/MutexImpl\n*L\n171#1:315,12\n*E\n"})
/* loaded from: classes.dex */
public class MutexImpl extends SemaphoreAndMutexImpl implements Mutex {
    private static final /* synthetic */ AtomicReferenceFieldUpdater owner$volatile$FU = null;
    private final Function3<SelectInstance<?>, Object, Object, Function3<Throwable, Object, CoroutineContext, Unit>> onSelectCancellationUnlockConstructor;
    private volatile /* synthetic */ Object owner$volatile;

    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u001f\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ|\u0010\u0015\u001a\u0004\u0018\u00010\u0006\"\b\b\u0000\u0010\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00028\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u00062M\u0010\u0014\u001aI\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0002\u0018\u00010\rH\u0016¢\u0006\u0004\b\u0015\u0010\u0016Jp\u0010\u0017\u001a\u00020\u0002\"\b\b\u0000\u0010\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00028\u00002M\u0010\u0014\u001aI\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0002\u0018\u00010\rH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0019\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0006H\u0097\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0096\u0001¢\u0006\u0004\b \u0010!J9\u0010%\u001a\u00020\u00022'\u0010$\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00020\"j\u0002`#H\u0096\u0001¢\u0006\u0004\b%\u0010&J$\u0010%\u001a\u00020\u00022\n\u0010(\u001a\u0006\u0012\u0002\b\u00030'2\u0006\u0010*\u001a\u00020)H\u0096\u0001¢\u0006\u0004\b%\u0010+J\u001c\u0010-\u001a\u00020\u0002*\u00020,2\u0006\u0010\u000b\u001a\u00020\u0002H\u0097\u0001¢\u0006\u0004\b-\u0010.J\u001e\u00101\u001a\u00020\u00022\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00020/H\u0096\u0001¢\u0006\u0004\b1\u0010\u001eR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u00102R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u00103R\u0014\u00104\u001a\u00020\u001f8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b4\u00105R\u0014\u00106\u001a\u00020\u001f8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b6\u00105R\u0014\u00107\u001a\u00020\u001f8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b7\u00105R\u0014\u0010\u0013\u001a\u00020\u00128\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b8\u00109¨\u0006:"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$CancellableContinuationWithOwner;", "Lkotlinx/coroutines/CancellableContinuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/Waiter;", "Lkotlinx/coroutines/CancellableContinuationImpl;", "cont", _UrlKt.FRAGMENT_ENCODE_SET, "owner", "<init>", "(Lkotlinx/coroutines/sync/MutexImpl;Lkotlinx/coroutines/CancellableContinuationImpl;Ljava/lang/Object;)V", "R", "value", "idempotent", "Lkotlin/Function3;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/CoroutineContext;", "context", "onCancellation", "tryResume", "(Lkotlin/Unit;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "resume", "(Lkotlin/Unit;Lkotlin/jvm/functions/Function3;)V", "exception", "tryResumeWithException", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "token", "completeResume", "(Ljava/lang/Object;)V", _UrlKt.FRAGMENT_ENCODE_SET, "cancel", "(Ljava/lang/Throwable;)Z", "Lkotlin/Function1;", "Lkotlinx/coroutines/CompletionHandler;", "handler", "invokeOnCancellation", "(Lkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/internal/Segment;", "segment", _UrlKt.FRAGMENT_ENCODE_SET, "index", "(Lkotlinx/coroutines/internal/Segment;I)V", "Lkotlinx/coroutines/CoroutineDispatcher;", "resumeUndispatched", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/Unit;)V", "Lkotlin/Result;", "result", "resumeWith", "Lkotlinx/coroutines/CancellableContinuationImpl;", "Ljava/lang/Object;", "isActive", "()Z", "isCompleted", "isCancelled", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMutex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Mutex.kt\nkotlinx/coroutines/sync/MutexImpl$CancellableContinuationWithOwner\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,314:1\n1#2:315\n*E\n"})
    public final class CancellableContinuationWithOwner implements CancellableContinuation<Unit>, Waiter {

        @JvmField
        public final CancellableContinuationImpl<Unit> cont;

        @JvmField
        public final Object owner;
        final /* synthetic */ MutexImpl this$0;

        /* renamed from: $r8$lambda$hZgjKV3c2GVq-TUQcoCzQE6L8n4, reason: not valid java name */
        public static Unit m3564$r8$lambda$hZgjKV3c2GVqTUQcoCzQE6L8n4(MutexImpl r0, CancellableContinuationWithOwner r1, Throwable r2, Unit r3, CoroutineContext r4) {
            MutexImpl.access$getOwner$volatile$FU().set(r0, r1.owner);
            r0.unlock(r1.owner);
            return Unit.INSTANCE;
        }

        /* renamed from: $r8$lambda$zaoas616xVD-Kfkb6aEv-1GpR5c, reason: not valid java name */
        public static Unit m3565$r8$lambda$zaoas616xVDKfkb6aEv1GpR5c(MutexImpl r0, CancellableContinuationWithOwner r1, Throwable r2) {
            r0.unlock(r1.owner);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CancellableContinuationWithOwner(MutexImpl r1, CancellableContinuationImpl<? super Unit> r2, Object r3) {
            this.this$0 = r1;
            this.cont = r2;
            this.owner = r3;
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public boolean cancel(Throwable r1) {
            return this.cont.cancel(r1);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public void completeResume(Object r1) {
            this.cont.completeResume(r1);
        }

        @Override // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.cont.getContext();
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public void invokeOnCancellation(Function1<? super Throwable, Unit> r1) {
            this.cont.invokeOnCancellation(r1);
        }

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment<?> r1, int r2) {
            this.cont.invokeOnCancellation(r1, r2);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public boolean isActive() {
            return this.cont.isActive();
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public boolean isCancelled() {
            return this.cont.isCancelled();
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public boolean isCompleted() {
            return this.cont.isCompleted();
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public /* bridge */ /* synthetic */ void resume(Object r1, Function3 r2) {
            resume((Unit) r1, r2);
        }

        public <R extends Unit> void resume(R r3, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r4) {
            MutexImpl.access$getOwner$volatile$FU().set(this.this$0, this.owner);
            this.cont.resume(r3, new MutexImpl$CancellableContinuationWithOwner$$ExternalSyntheticLambda1(this.this$0, this));
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public /* bridge */ /* synthetic */ void resumeUndispatched(CoroutineDispatcher r1, Unit r2) {
            resumeUndispatched2(r1, r2);
        }

        /* renamed from: resumeUndispatched, reason: avoid collision after fix types in other method */
        public void resumeUndispatched2(CoroutineDispatcher r1, Unit r2) {
            this.cont.resumeUndispatched(r1, r2);
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(Object r1) {
            this.cont.resumeWith(r1);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public /* bridge */ /* synthetic */ Object tryResume(Object r1, Object r2, Function3 r3) {
            return tryResume((Unit) r1, r2, r3);
        }

        public <R extends Unit> Object tryResume(R r3, Object r4, Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit> r5) {
            Object r32 = this.cont.tryResume(r3, r4, new MutexImpl$CancellableContinuationWithOwner$$ExternalSyntheticLambda0(this.this$0, this));
            if (r32 == null) goto L5;
            MutexImpl.access$getOwner$volatile$FU().set(this.this$0, this.owner);
        L5:
            return r32;
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public Object tryResumeWithException(Throwable r1) {
            return this.cont.tryResumeWithException(r1);
        }
    }

    /* renamed from: $r8$lambda$1-pjws3Fsh4zD3koAhUokg5YBU4, reason: not valid java name */
    public static Function3 m3562$r8$lambda$1pjws3Fsh4zD3koAhUokg5YBU4(MutexImpl r0, SelectInstance r1, Object r2, Object r3) {
        return new MutexImpl$$ExternalSyntheticLambda1(r0, r2);
    }

    /* renamed from: $r8$lambda$CAC1I9WfQiyU_gHVrS5dLdJ24-s, reason: not valid java name */
    public static /* synthetic */ Unit m3563$r8$lambda$CAC1I9WfQiyU_gHVrS5dLdJ24s(MutexImpl r0, Object r1, Throwable r2, Object r3, CoroutineContext r4) {
        return onSelectCancellationUnlockConstructor$lambda$0$0(r0, r1, r2, r3, r4);
    }

    static {
        owner$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "owner$volatile");
    }

    public MutexImpl(boolean r2) {
        super(1, r2 ? 1 : 0);
        if (r2 == false) goto L5;
        Symbol r22 = null;
    L6:
        this.owner$volatile = r22;
        this.onSelectCancellationUnlockConstructor = new MutexImpl$$ExternalSyntheticLambda0(this);
        return;
    L5:
        r22 = MutexKt.access$getNO_OWNER$p();
        goto L6
    }

    public static final /* synthetic */ AtomicReferenceFieldUpdater access$getOwner$volatile$FU() {
        return getOwner$volatile$FU();
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getOwner$volatile$FU() {
        return owner$volatile$FU;
    }

    private final int holdsLockImpl(Object r3) {
    L3:
        if (isLocked() == false) goto L4;
        Object r0 = getOwner$volatile$FU().get(this);
        if (r0 == MutexKt.access$getNO_OWNER$p()) goto L3;
        if (r0 != r3) goto L11;
        return 1;
    L11:
        return 2;
    L4:
        return 0;
    }

    public static /* synthetic */ Object lock$suspendImpl(MutexImpl r1, Object r2, Continuation<? super Unit> r3) {
        if (r1.tryLock(r2) == true) goto L5;
        Object r12 = r1.lockSuspend(r2, r3);
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        return r12;
    L10:
        return Unit.INSTANCE;
    L5:
        return Unit.INSTANCE;
    }

    private final Object lockSuspend(Object r3, Continuation<? super Unit> r4) {
        CancellableContinuationImpl r0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(r4));
        acquire(new CancellableContinuationWithOwner(this, r0, r3));     // Catch: Throwable -> L12
        Object r2 = r0.getResult();
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L8;
        DebugProbesKt.probeCoroutineSuspended(r4);
    L8:
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L11;
        return r2;
    L11:
        return Unit.INSTANCE;
    L12:
        th = move-exception;
        r0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        throw th;
    }

    private static final Unit onSelectCancellationUnlockConstructor$lambda$0$0(MutexImpl r0, Object r1, Throwable r2, Object r3, CoroutineContext r4) {
        r0.unlock(r1);
        return Unit.INSTANCE;
    }

    private final int tryLockImpl(Object r4) {
    L3:
        if (tryAcquire() == true) goto L4;
        if (r4 == null) goto L8;
        int r1 = holdsLockImpl(r4);
        if (r1 == 1) goto L14;
        if (r1 != 2) goto L3;
        return 1;
    L14:
        return 2;
    L8:
        return 1;
    L4:
        getOwner$volatile$FU().set(this, r4);
        return 0;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        if (getAvailablePermits() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public Object lock(Object r1, Continuation<? super Unit> r2) {
        return lock$suspendImpl(this, r1, r2);
    }

    public String toString() {
        return "Mutex@" + DebugStringsKt.getHexAddress(this) + "[isLocked=" + isLocked() + ",owner=" + getOwner$volatile$FU().get(this) + ']';
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(Object r2) {
        int r1 = tryLockImpl(r2);
        if (r1 == 0) goto L13;
        if (r1 != 1) goto L6;
        return false;
    L6:
        if (r1 == 2) goto L10;
        Segment$$ExternalSyntheticBUOutline1.m("unexpected");
        return false;
    L10:
        DurationKt$$ExternalSyntheticBUOutline0.m("This mutex is already locked by the specified owner: ", r2);
        return false;
    L13:
        return true;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(Object r4) {
    L3:
        if (isLocked() == false) goto L15;
        Object r0 = getOwner$volatile$FU().get(this);
        if (r0 == MutexKt.access$getNO_OWNER$p()) goto L3;
        if (r0 == r4) goto L12;
        if (r4 == null) goto L12;
        throw new IllegalStateException(("This mutex is locked by " + r0 + ", but " + r4 + " is expected").toString());
    L12:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(getOwner$volatile$FU(), this, r0, MutexKt.access$getNO_OWNER$p()) == false) goto L3;
        release();
        return;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("This mutex is not locked");
    }
}
