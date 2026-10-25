package androidx.room;

import androidx.room.ObservedTableStates;
import androidx.room.Transactor;
import com.google.android.gms.cast.MediaError;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.http.HttpStatusCodesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "connection", "Landroidx/room/Transactor;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker$syncTriggers$2$1", f = "InvalidationTracker.kt", i = {0, 1, 1}, l = {HttpStatusCodesKt.HTTP_TEMP_REDIRECT, MediaError.DetailedErrorCode.HLS_NETWORK_KEY_LOAD}, m = "invokeSuspend", n = {"connection", "this_$iv", "$this$withLock$iv$iv"}, s = {"L$0", "L$0", "L$1"})
@SourceDebugExtension({"SMAP\nInvalidationTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/TriggerBasedInvalidationTracker$syncTriggers$2$1\n+ 2 InvalidationTracker.kt\nandroidx/room/ObservedTableStates\n+ 3 ReentrantLock.kt\nandroidx/room/concurrent/ReentrantLockKt\n*L\n1#1,640:1\n526#2:641\n527#2,21:645\n549#2,8:667\n28#3,3:642\n32#3:666\n*S KotlinDebug\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/TriggerBasedInvalidationTracker$syncTriggers$2$1\n*L\n313#1:641\n313#1:645,21\n313#1:667,8\n313#1:642,3\n313#1:666\n*E\n"})
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$syncTriggers$2$1 extends SuspendLambda implements Function2<Transactor, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ TriggerBasedInvalidationTracker this$0;

    public TriggerBasedInvalidationTracker$syncTriggers$2$1(TriggerBasedInvalidationTracker r1, Continuation<? super TriggerBasedInvalidationTracker$syncTriggers$2$1> r2) {
        this.this$0 = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        TriggerBasedInvalidationTracker$syncTriggers$2$1 r0 = new TriggerBasedInvalidationTracker$syncTriggers$2$1(this.this$0, r3);
        r0.L$0 = r2;
        return r0;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Transactor r1, Continuation<? super Unit> r2) {
        return ((TriggerBasedInvalidationTracker$syncTriggers$2$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Transactor r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r21) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.label;
        boolean r6 = true;
        if (r2 == 0) goto L14;
        if (r2 == 1) goto L13;
        if (r2 != 2) goto L11;
        ReentrantLock r12 = (ReentrantLock) this.L$1;
        ObservedTableStates r22 = (ObservedTableStates) this.L$0;
        ResultKt.throwOnFailure(r21);     // Catch: Throwable -> L9
    L54:
        ReentrantLock r9 = r12;
        ObservedTableStates r7 = r22;
    L61:
        ObservedTableStates.access$setInProgressSync$p(r7, false);     // Catch: Throwable -> L64
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L64
        r9.unlock();
        return Unit.INSTANCE;
    L9:
        th = th;
    L77:
        ObservedTableStates.access$setInProgressSync$p(r22, false);     // Catch: Throwable -> L59
        throw th;     // Catch: Throwable -> L59
    L59:
        th = th;
        r9 = r12;
    L68:
        r9.unlock();
        throw th;
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L13:
        Transactor r23 = (Transactor) this.L$0;
        ResultKt.throwOnFailure(r21);
        Object r72 = r21;
    L18:
        if (((Boolean) r72).booleanValue() == true) goto L20;
        r7 = TriggerBasedInvalidationTracker.access$getObservedTableStates$p(this.this$0);
        TriggerBasedInvalidationTracker r8 = this.this$0;
        r9 = ObservedTableStates.access$getOnSyncLock$p(r7);
        r9.lock();
        ObservedTableStates.access$setInProgressSync$p(r7, true);     // Catch: Throwable -> L64
        ReentrantLock r10 = ObservedTableStates.access$getLock$p(r7);     // Catch: Throwable -> L64
        r10.lock();     // Catch: Throwable -> L64
    L38:
        th = move-exception;
        r10.unlock();     // Catch: Throwable -> L64
        throw th;     // Catch: Throwable -> L64
    L24:
        if (ObservedTableStates.access$getNeedsSync$p(r7) == true) goto L26;
    L25:
        ObservedTableStates.ObserveOp[] r122 = null;
    L45:
        r10.unlock();     // Catch: Throwable -> L64
        if (r122 == null) goto L61;
    L55:
        th = th;
        r22 = r7;
        r12 = r9;
        goto L77
    L48:
        if (r122.length == 0) goto L61;
        Transactor.SQLiteTransactionType r62 = Transactor.SQLiteTransactionType.IMMEDIATE;     // Catch: Throwable -> L55
        TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1 r102 = new TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1(r122, r8, r23, null);     // Catch: Throwable -> L55
        this.L$0 = r7;     // Catch: Throwable -> L55
        this.L$1 = r9;     // Catch: Throwable -> L55
        this.label = 2;     // Catch: Throwable -> L55
        if (r23.withTransaction(r62, r102, this) != r1) goto L53;
    L52:
        return r1;
    L53:
        r22 = r7;
        r12 = r9;
        goto L54
    L26:
        ObservedTableStates.access$setNeedsSync$p(r7, false);     // Catch: Throwable -> L38
        int r11 = ObservedTableStates.access$getTableObserversCount$p(r7).length;     // Catch: Throwable -> L38
        r122 = new ObservedTableStates.ObserveOp[r11];     // Catch: Throwable -> L38
        int r13 = 0;
        boolean r14 = false;
    L27:
        if (r13 >= r11) goto L44;
        if (ObservedTableStates.access$getTableObserversCount$p(r7)[r13] <= 0) goto L31;
        boolean r15 = r6;
    L33:
        if (r15 == ObservedTableStates.access$getTableObservedState$p(r7)[r13]) goto L41;
        ObservedTableStates.access$getTableObservedState$p(r7)[r13] = r15;     // Catch: Throwable -> L38
        if (r15 == false) goto L40;
        ObservedTableStates.ObserveOp r63 = ObservedTableStates.ObserveOp.ADD;     // Catch: Throwable -> L38
    L37:
        r14 = true;
    L42:
        r122[r13] = r63;     // Catch: Throwable -> L38
        r13 = r13 + 1;
        r6 = true;
        goto L27
    L40:
        r63 = ObservedTableStates.ObserveOp.REMOVE;     // Catch: Throwable -> L38
        goto L37
    L41:
        r63 = ObservedTableStates.ObserveOp.NO_OP;     // Catch: Throwable -> L38
        goto L42
    L31:
        r15 = false;
        goto L33
    L44:
        if (r14 == false) goto L25;
    L64:
        th = th;
        goto L68
    L20:
        return Unit.INSTANCE;
    L14:
        ResultKt.throwOnFailure(r21);
        r23 = (Transactor) this.L$0;
        this.L$0 = r23;
        this.label = 1;
        r72 = r23.inTransaction(this);
        if (r72 != r1) goto L18;
        goto L52
    }
}
