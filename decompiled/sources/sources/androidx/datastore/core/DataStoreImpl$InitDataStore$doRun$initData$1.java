package androidx.datastore.core;

import androidx.datastore.core.DataStoreImpl;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/Data;", "T"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1", f = "DataStoreImpl.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {437, 458, 546, 468}, m = "invokeSuspend", n = {"updateLock", "initializationComplete", "currentData", "updateLock", "initializationComplete", "currentData", "api", "initializationComplete", "currentData", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
@SourceDebugExtension({"SMAP\nDataStoreImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataStoreImpl.kt\nandroidx/datastore/core/DataStoreImpl$InitDataStore$doRun$initData$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,538:1\n1855#2,2:539\n120#3,10:541\n*S KotlinDebug\n*F\n+ 1 DataStoreImpl.kt\nandroidx/datastore/core/DataStoreImpl$InitDataStore$doRun$initData$1\n*L\n458#1:539,2\n461#1:541,10\n*E\n"})
/* loaded from: classes.dex */
public final class DataStoreImpl$InitDataStore$doRun$initData$1<T> extends SuspendLambda implements Function1<Continuation<? super Data<T>>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;
    final /* synthetic */ DataStoreImpl<T>.InitDataStore this$1;

    public DataStoreImpl$InitDataStore$doRun$initData$1(DataStoreImpl<T> r1, DataStoreImpl<T>.InitDataStore r2, Continuation<? super DataStoreImpl$InitDataStore$doRun$initData$1> r3) {
        this.this$0 = r1;
        this.this$1 = r2;
        super(1, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r3) {
        return new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this.this$1, r3);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return invoke((Continuation) r1);
    }

    public final Object invoke(Continuation<? super Data<T>> r1) {
        return ((DataStoreImpl$InitDataStore$doRun$initData$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        int r2 = 0;
        if (r1 == 0) goto L14;
        if (r1 == 1) goto L13;
        if (r1 == 2) goto L12;
        if (r1 == 3) goto L11;
        if (r1 != 4) goto L9;
        int r02 = this.I$0;
        Object r12 = this.L$0;
        ResultKt.throwOnFailure(r13);
    L41:
        return new Data(r12, r02, ((Number) r13).intValue());
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        Mutex r14 = (Mutex) this.L$2;
        Ref.ObjectRef r4 = (Ref.ObjectRef) this.L$1;
        Ref.BooleanRef r5 = (Ref.BooleanRef) this.L$0;
        ResultKt.throwOnFailure(r13);
    L45:
        r5.element = true;     // Catch: Throwable -> L42
        Unit r132 = Unit.INSTANCE;     // Catch: Throwable -> L42
        r14.unlock(null);
        T r133 = r4.element;
        if (r133 == null) goto L36;
        r2 = r133.hashCode();
    L36:
        InterProcessCoordinator r15 = DataStoreImpl.access$getCoordinator(this.this$0);
        this.L$0 = r133;
        this.L$1 = null;
        this.L$2 = null;
        this.I$0 = r2;
        this.label = 4;
        Object r122 = r15.getVersion(this);
        if (r122 != r0) goto L39;
    L38:
        return r0;
    L39:
        r13 = r122;
        r12 = r133;
        r02 = r2;
    L42:
        th = move-exception;
        r14.unlock(null);
        throw th;
    L12:
        Iterator<T> r16 = (Iterator) this.L$4;
        DataStoreImpl$InitDataStore$doRun$initData$1$api$1 r8 = (DataStoreImpl$InitDataStore$doRun$initData$1$api$1) this.L$3;
        Ref.ObjectRef r9 = (Ref.ObjectRef) this.L$2;
        Ref.BooleanRef r10 = (Ref.BooleanRef) this.L$1;
        Mutex r11 = (Mutex) this.L$0;
        ResultKt.throwOnFailure(r13);
    L22:
        if (r16.hasNext() == false) goto L26;
        Function2 r134 = (Function2) r16.next();
        this.L$0 = r11;
        this.L$1 = r10;
        this.L$2 = r9;
        this.L$3 = r8;
        this.L$4 = r16;
        this.label = 2;
        if (r134.invoke(r8, this) != r0) goto L22;
    L26:
        Ref.ObjectRef r82 = r9;
        r5 = r10;
        r14 = r11;
    L28:
        DataStoreImpl.InitDataStore.access$setInitTasks$p(this.this$1, null);
        this.L$0 = r5;
        this.L$1 = r82;
        this.L$2 = r14;
        this.L$3 = null;
        this.L$4 = null;
        this.label = 3;
        if (r14.lock(null, this) == r0) goto L38;
        r4 = r82;
        goto L45
    L13:
        Ref.ObjectRef r17 = (Ref.ObjectRef) this.L$3;
        r82 = (Ref.ObjectRef) this.L$2;
        Ref.BooleanRef r92 = (Ref.BooleanRef) this.L$1;
        Mutex r102 = (Mutex) this.L$0;
        ResultKt.throwOnFailure(r13);
    L18:
        r17.element = (T) ((Data) r13).getValue();
        DataStoreImpl$InitDataStore$doRun$initData$1$api$1 r135 = new DataStoreImpl$InitDataStore$doRun$initData$1$api$1(r102, r92, r82, this.this$0);
        List r18 = DataStoreImpl.InitDataStore.access$getInitTasks$p(this.this$1);
        if (r18 == null) goto L27;
        r16 = r18.iterator();
        r11 = r102;
        r10 = r92;
        r9 = r82;
        r8 = r135;
        goto L22
    L27:
        r5 = r92;
        r14 = r102;
        goto L28
    L14:
        ResultKt.throwOnFailure(r13);
        r102 = MutexKt.Mutex$default(false, 1, null);
        r92 = new Ref.BooleanRef();
        r17 = new Ref.ObjectRef();
        DataStoreImpl<T> r136 = this.this$0;
        this.L$0 = r102;
        this.L$1 = r92;
        this.L$2 = r17;
        this.L$3 = r17;
        this.label = 1;
        r13 = DataStoreImpl.access$readDataOrHandleCorruption(r136, true, this);
        if (r13 == r0) goto L38;
        r82 = r17;
        goto L18
    }
}
