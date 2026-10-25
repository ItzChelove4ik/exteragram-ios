package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.sync.Mutex;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001JA\u0010\u0002\u001a\u00028\u000021\u0010\u0003\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004H\u0096@¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"androidx/datastore/core/DataStoreImpl$InitDataStore$doRun$initData$1$api$1", "Landroidx/datastore/core/InitializerApi;", "updateData", "transform", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "t", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDataStoreImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataStoreImpl.kt\nandroidx/datastore/core/DataStoreImpl$InitDataStore$doRun$initData$1$api$1\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,538:1\n120#2,10:539\n*S KotlinDebug\n*F\n+ 1 DataStoreImpl.kt\nandroidx/datastore/core/DataStoreImpl$InitDataStore$doRun$initData$1$api$1\n*L\n441#1:539,10\n*E\n"})
/* loaded from: classes.dex */
public final class DataStoreImpl$InitDataStore$doRun$initData$1$api$1<T> implements InitializerApi<T> {
    final /* synthetic */ Ref.ObjectRef<T> $currentData;
    final /* synthetic */ Ref.BooleanRef $initializationComplete;
    final /* synthetic */ Mutex $updateLock;
    final /* synthetic */ DataStoreImpl<T> this$0;

    public DataStoreImpl$InitDataStore$doRun$initData$1$api$1(Mutex r1, Ref.BooleanRef r2, Ref.ObjectRef<T> r3, DataStoreImpl<T> r4) {
        this.$updateLock = r1;
        this.$initializationComplete = r2;
        this.$currentData = r3;
        this.this$0 = r4;
    }

    @Override // androidx.datastore.core.InitializerApi
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> r9, Continuation<? super T> r10) {
        if ((r10 instanceof DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1) == false) goto L7;
        DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1 r0 = (DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L26;
        if (r2 == 1) goto L25;
        if (r2 == 2) goto L20;
        if (r2 != 3) goto L18;
        T r8 = (T) r0.L$2;
        Ref.ObjectRef<T> r92 = (Ref.ObjectRef) r0.L$1;
        Mutex r02 = (Mutex) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L16
    L41:
        r92.element = r8;     // Catch: Throwable -> L16
    L43:
        T r82 = r92.element;     // Catch: Throwable -> L16
        r02.unlock(null);
        return r82;
    L16:
        th = th;
    L50:
        r02.unlock(null);
        throw th;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        DataStoreImpl r83 = (DataStoreImpl) r0.L$2;
        r92 = (Ref.ObjectRef) r0.L$1;
        Mutex r22 = (Mutex) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L23
        DataStoreImpl r84 = r83;
    L36:
        if (Intrinsics.areEqual(r102, r92.element) == true) goto L42;
        r0.L$0 = r22;     // Catch: Throwable -> L23
        r0.L$1 = r92;     // Catch: Throwable -> L23
        r0.L$2 = r102;     // Catch: Throwable -> L23
        r0.label = 3;     // Catch: Throwable -> L23
        if (r84.writeData$datastore_core_release(r102, false, r0) != r12) goto L40;
    L39:
        return r12;
    L40:
        r8 = (T) r102;
        r02 = r22;
        goto L41
    L42:
        r02 = r22;
    L23:
        th = th;
        r02 = r22;
        goto L50
    L25:
        DataStoreImpl r85 = (DataStoreImpl<T>) ((DataStoreImpl) r0.L$4);
        Ref.ObjectRef<T> r93 = (Ref.ObjectRef) r0.L$3;
        Ref.BooleanRef r23 = (Ref.BooleanRef) r0.L$2;
        Mutex r5 = (Mutex) r0.L$1;
        Function2<? super T, ? super Continuation<? super T>, ? extends Object> r7 = (Function2) r0.L$0;
        ResultKt.throwOnFailure(r102);
        Ref.ObjectRef<T> r72 = r93;
        r9 = r7;
        Mutex r103 = r5;
        DataStoreImpl r86 = r85;
    L52:
    L46:
        th = th;
        r02 = r103;
        goto L50
    L30:
        if (r23.element == true) goto L49;
        T r24 = r72.element;     // Catch: Throwable -> L46
        r0.L$0 = r103;     // Catch: Throwable -> L46
        r0.L$1 = r72;     // Catch: Throwable -> L46
        r0.L$2 = r86;     // Catch: Throwable -> L46
        r0.L$3 = null;     // Catch: Throwable -> L46
        r0.L$4 = null;     // Catch: Throwable -> L46
        r0.label = 2;     // Catch: Throwable -> L46
        Object r94 = r9.invoke(r24, r0);     // Catch: Throwable -> L46
        if (r94 == r12) goto L39;
        r22 = r103;
        r102 = r94;
        r92 = r72;
        r84 = r86;
        goto L36
    L49:
        throw new IllegalStateException("InitializerApi.updateData should not be called after initialization is complete.");     // Catch: Throwable -> L46
    L26:
        ResultKt.throwOnFailure(r102);
        r103 = this.$updateLock;
        r23 = this.$initializationComplete;
        r72 = this.$currentData;
        DataStoreImpl r87 = (DataStoreImpl<T>) this.this$0;
        r0.L$0 = r9;
        r0.L$1 = r103;
        r0.L$2 = r23;
        r0.L$3 = r72;
        r0.L$4 = r87;
        r0.label = 1;
        r86 = r87;
        if (r103.lock(null, r0) != r12) goto L52;
    L7:
        r0 = new DataStoreImpl$InitDataStore$doRun$initData$1$api$1$updateData$1(this, r10);
        goto L8
    }
}
