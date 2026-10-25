package androidx.datastore.core;

import androidx.datastore.core.Message;
import androidx.datastore.core.UpdatingDataContextElement;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 V*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002VWBn\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012?\b\u0002\u0010\u0005\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00070\u0006\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u000e\u00103\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104JG\u00105\u001a\u0002H6\"\u0004\b\u0001\u001062\u0006\u00107\u001a\u0002082\u001c\u00109\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H60\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0:H\u0082@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010;J\u001c\u0010<\u001a\u00020\r2\f\u0010=\u001a\b\u0012\u0004\u0012\u00028\u000002H\u0082@¢\u0006\u0002\u0010>J\u000e\u0010?\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104J\u000e\u0010@\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104J\u001c\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000B2\u0006\u0010C\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJ\u000e\u0010E\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u00104J\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00028\u00000G2\u0006\u00107\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJ\u001c\u0010H\u001a\b\u0012\u0004\u0012\u00028\u00000B2\u0006\u0010C\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJI\u0010I\u001a\u00028\u000021\u0010J\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(K\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00072\u0006\u0010L\u001a\u00020MH\u0082@¢\u0006\u0002\u0010NJA\u0010O\u001a\u00028\u000021\u0010J\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(K\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007H\u0096@¢\u0006\u0002\u0010PJ \u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00028\u00002\u0006\u0010S\u001a\u000208H\u0080@¢\u0006\u0004\bT\u0010UR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010&\u001a\f0'R\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)8@X\u0080\u0084\u0002¢\u0006\f\u001a\u0004\b,\u0010-*\u0004\b*\u0010+R\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000)0/X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Landroidx/datastore/core/DataStoreImpl;", "T", "Landroidx/datastore/core/DataStore;", "storage", "Landroidx/datastore/core/Storage;", "initTasksList", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", "name", "api", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "corruptionHandler", "Landroidx/datastore/core/CorruptionHandler;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/datastore/core/Storage;Ljava/util/List;Landroidx/datastore/core/CorruptionHandler;Lkotlinx/coroutines/CoroutineScope;)V", "collectorCounter", _UrlKt.FRAGMENT_ENCODE_SET, "collectorJob", "Lkotlinx/coroutines/Job;", "collectorMutex", "Lkotlinx/coroutines/sync/Mutex;", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "coordinator$delegate", "Lkotlin/Lazy;", "data", "Lkotlinx/coroutines/flow/Flow;", "getData", "()Lkotlinx/coroutines/flow/Flow;", "inMemoryCache", "Landroidx/datastore/core/DataStoreInMemoryCache;", "readAndInit", "Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "storageConnection", "Landroidx/datastore/core/StorageConnection;", "getStorageConnection$datastore_core_release$delegate", "(Landroidx/datastore/core/DataStoreImpl;)Ljava/lang/Object;", "getStorageConnection$datastore_core_release", "()Landroidx/datastore/core/StorageConnection;", "storageConnectionDelegate", "Lkotlin/Lazy;", "writeActor", "Landroidx/datastore/core/SimpleActor;", "Landroidx/datastore/core/Message$Update;", "decrementCollector", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doWithWriteFileLock", "R", "hasWriteFileLock", _UrlKt.FRAGMENT_ENCODE_SET, "block", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleUpdate", PluginsConstants.UPDATE, "(Landroidx/datastore/core/Message$Update;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementCollector", "readAndInitOrPropagateAndThrowFailure", "readDataAndUpdateCache", "Landroidx/datastore/core/State;", "requireLock", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDataFromFileOrDefault", "readDataOrHandleCorruption", "Landroidx/datastore/core/Data;", "readState", "transformAndWrite", "transform", "t", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateData", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeData", "newData", "updateCache", "writeData$datastore_core_release", "(Ljava/lang/Object;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "InitDataStore", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDataStoreImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataStoreImpl.kt\nandroidx/datastore/core/DataStoreImpl\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,538:1\n120#2,10:539\n120#2,10:549\n*S KotlinDebug\n*F\n+ 1 DataStoreImpl.kt\nandroidx/datastore/core/DataStoreImpl\n*L\n130#1:539,10\n148#1:549,10\n*E\n"})
/* loaded from: classes.dex */
public final class DataStoreImpl<T> implements DataStore<T> {
    public static final Companion Companion = null;
    private int collectorCounter;
    private Job collectorJob;
    private final Mutex collectorMutex;
    private final Lazy coordinator$delegate;
    private final CorruptionHandler<T> corruptionHandler;
    private final Flow<T> data;
    private final DataStoreInMemoryCache<T> inMemoryCache;
    private final DataStoreImpl<T>.InitDataStore readAndInit;
    private final CoroutineScope scope;
    private final Storage<T> storage;
    private final Lazy<StorageConnection<T>> storageConnectionDelegate;
    private final SimpleActor<Message.Update<T>> writeActor;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/datastore/core/DataStoreImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "BUG_MESSAGE", _UrlKt.FRAGMENT_ENCODE_SET, "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001BD\u0012=\u0010\u0002\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00040\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u000e\u001a\u00020\nH\u0094@¢\u0006\u0002\u0010\u000fRG\u0010\r\u001a;\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "Landroidx/datastore/core/RunOnce;", "initTasksList", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", "name", "api", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Landroidx/datastore/core/DataStoreImpl;Ljava/util/List;)V", "initTasks", "doRun", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class InitDataStore extends RunOnce {
        private List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasks;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public InitDataStore(DataStoreImpl r1, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> r2) {
            this.this$0 = r1;
            this.initTasks = CollectionsKt.toList(r2);
        }

        public static final /* synthetic */ List access$getInitTasks$p(InitDataStore r0) {
            return r0.initTasks;
        }

        public static final /* synthetic */ void access$setInitTasks$p(InitDataStore r0, List r1) {
            r0.initTasks = r1;
        }

        @Override // androidx.datastore.core.RunOnce
        public Object doRun(Continuation<? super Unit> r7) {
            if ((r7 instanceof DataStoreImpl$InitDataStore$doRun$1) == false) goto L7;
            DataStoreImpl$InitDataStore$doRun$1 r0 = (DataStoreImpl$InitDataStore$doRun$1) r7;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r72 = r0.result;
            Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = r0.label;
            if (r2 == 0) goto L16;
            if (r2 == 1) goto L15;
            if (r2 != 2) goto L13;
            InitDataStore this = (InitDataStore) r0.L$0;
            ResultKt.throwOnFailure(r72);
        L24:
            Data r73 = (Data) r72;
        L29:
            DataStoreImpl.access$getInMemoryCache$p(this.this$0).tryUpdate(r73);
            return Unit.INSTANCE;
        L13:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L15:
            this = (InitDataStore) r0.L$0;
            ResultKt.throwOnFailure(r72);
        L28:
            r73 = (Data) r72;
            goto L29
        L16:
            ResultKt.throwOnFailure(r72);
            List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> r74 = this.initTasks;
            if (r74 != null) goto L19;
        L25:
            DataStoreImpl<T> r75 = this.this$0;
            r0.L$0 = this;
            r0.label = 1;
            r72 = DataStoreImpl.access$readDataOrHandleCorruption(r75, false, r0);
            if (r72 != r12) goto L28;
        L27:
            return r12;
        L19:
            if (r74.isEmpty() == true) goto L25;
            InterProcessCoordinator r76 = DataStoreImpl.access$getCoordinator(this.this$0);
            DataStoreImpl$InitDataStore$doRun$initData$1 r22 = new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this, null);
            r0.L$0 = this;
            r0.label = 2;
            r72 = r76.lock(r22, r0);
            if (r72 != r12) goto L24;
        L7:
            r0 = new DataStoreImpl$InitDataStore$doRun$1(this, r7);
            goto L8
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 0}, l = {544}, m = "decrementCollector", n = {"this", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$decrementCollector$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public AnonymousClass1(DataStoreImpl<T> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return DataStoreImpl.access$decrementCollector(this.this$0, this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "R", "T"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$doWithWriteFileLock$3", f = "DataStoreImpl.kt", i = {}, l = {416}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.datastore.core.DataStoreImpl$doWithWriteFileLock$3, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass3<R> extends SuspendLambda implements Function1<Continuation<? super R>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Function1<? super Continuation<? super R>, ? extends Object> r1, Continuation<? super AnonymousClass3> r2) {
            this.$block = r1;
            super(1, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r2) {
            return new AnonymousClass3(this.$block, r2);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object r1) {
            return invoke((Continuation) r1);
        }

        public final Object invoke(Continuation<? super R> r1) {
            return ((AnonymousClass3) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r4);
            return r4;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r4);
            Function1<Continuation<? super R>, Object> r42 = this.$block;
            this.label = 1;
            Object r3 = r42.invoke(this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {1, 1}, l = {237, 243, 246}, m = "handleUpdate", n = {PluginsConstants.UPDATE, "$this$handleUpdate_u24lambda_u242"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$handleUpdate$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public C01041(DataStoreImpl<T> r1, Continuation<? super C01041> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return DataStoreImpl.access$handleUpdate(this.this$0, null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 0}, l = {544}, m = "incrementCollector", n = {"this", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$incrementCollector$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01051 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public C01051(DataStoreImpl<T> r1, Continuation<? super C01051> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return DataStoreImpl.access$incrementCollector(this.this$0, this);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 1, 1}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_DISCONNECTED}, m = "readAndInitOrPropagateAndThrowFailure", n = {"this", "this", "preReadVersion"}, s = {"L$0", "L$0", "I$0"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01061 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public C01061(DataStoreImpl<T> r1, Continuation<? super C01061> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return DataStoreImpl.access$readAndInitOrPropagateAndThrowFailure(this.this$0, this);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 0, 0, 1, 2}, l = {287, 296, 304}, m = "readDataAndUpdateCache", n = {"this", "currentState", "requireLock", "this", "this"}, s = {"L$0", "L$1", "Z$0", "L$0", "L$0"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01071 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public C01071(DataStoreImpl<T> r1, Continuation<? super C01071> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return DataStoreImpl.access$readDataAndUpdateCache(this.this$0, false, this);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Landroidx/datastore/core/State;", "T", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3", f = "DataStoreImpl.kt", i = {}, l = {298, 300}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes3.dex */
    public static final class C01083 extends SuspendLambda implements Function1<Continuation<? super Pair<? extends State<T>, ? extends Boolean>>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public C01083(DataStoreImpl<T> r1, Continuation<? super C01083> r2) {
            this.this$0 = r1;
            super(1, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r2) {
            return new C01083(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object r1) {
            return invoke((Continuation) r1);
        }

        public final Object invoke(Continuation<? super Pair<? extends State<T>, Boolean>> r1) {
            return ((C01083) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L13;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            Throwable r5 = (Throwable) this.L$0;
            ResultKt.throwOnFailure(r6);
        L23:
            State r62 = new ReadException(r5, ((Number) r6).intValue());
        L25:
            return TuplesKt.to(r62, Boxing.boxBoolean(true));
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r6);     // Catch: Throwable -> L11
        L17:
            r62 = (State) r6;     // Catch: Throwable -> L11
            goto L25
        L13:
            ResultKt.throwOnFailure(r6);
            DataStoreImpl<T> r63 = this.this$0;     // Catch: Throwable -> L11
            this.label = 1;     // Catch: Throwable -> L11
            r6 = DataStoreImpl.access$readDataOrHandleCorruption(r63, true, this);     // Catch: Throwable -> L11
            if (r6 != r0) goto L17;
        L21:
            return r0;
        L11:
            th = move-exception;
            InterProcessCoordinator r12 = DataStoreImpl.access$getCoordinator(this.this$0);
            this.L$0 = th;
            this.label = 2;
            Object r52 = r12.getVersion(this);
            if (r52 == r0) goto L21;
            r6 = r52;
            r5 = th;
            goto L23
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0005\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Landroidx/datastore/core/State;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "locked"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4", f = "DataStoreImpl.kt", i = {0, 1}, l = {306, 309}, m = "invokeSuspend", n = {"locked", "locked"}, s = {"Z$0", "Z$0"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass4 extends SuspendLambda implements Function2<Boolean, Continuation<? super Pair<? extends State<T>, ? extends Boolean>>, Object> {
        final /* synthetic */ int $cachedVersion;
        Object L$0;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public AnonymousClass4(DataStoreImpl<T> r1, int r2, Continuation<? super AnonymousClass4> r3) {
            this.this$0 = r1;
            this.$cachedVersion = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            AnonymousClass4 r0 = new AnonymousClass4(this.this$0, this.$cachedVersion, r4);
            r0.Z$0 = ((Boolean) r3).booleanValue();
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Boolean r1, Object r2) {
            return invoke(r1.booleanValue(), (Continuation) r2);
        }

        public final Object invoke(boolean r1, Continuation<? super Pair<? extends State<T>, Boolean>> r2) {
            return ((AnonymousClass4) create(Boolean.valueOf(r1), r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v12 */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v6 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            boolean r1 = this.label;
            if (r1 == 0) goto L14;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            boolean r02 = this.Z$0;
            Throwable r5 = (Throwable) this.L$0;
            ResultKt.throwOnFailure(r6);
        L25:
            int r62 = ((Number) r6).intValue();
            boolean r12 = r02;
        L27:
            State r63 = new ReadException(r5, r62);
            boolean r13 = r12;
        L29:
            return TuplesKt.to(r63, Boxing.boxBoolean(r13));
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            boolean r14 = this.Z$0;
            ResultKt.throwOnFailure(r6);     // Catch: Throwable -> L12
            r1 = r14;
        L18:
            r63 = (State) r6;     // Catch: Throwable -> L12
            r13 = r1;
            goto L29
        L14:
            ResultKt.throwOnFailure(r6);
            boolean r15 = this.Z$0;
            DataStoreImpl<T> r64 = this.this$0;     // Catch: Throwable -> L12
            this.Z$0 = r15;     // Catch: Throwable -> L12
            this.label = 1;     // Catch: Throwable -> L12
            r6 = DataStoreImpl.access$readDataOrHandleCorruption(r64, r15, this);     // Catch: Throwable -> L12
            r1 = r15;
            if (r6 != r0) goto L18;
        L23:
            return r0;
        L12:
            th = move-exception;
            if (r1 == 0) goto L26;
            InterProcessCoordinator r3 = DataStoreImpl.access$getCoordinator(this.this$0);
            this.L$0 = th;
            this.Z$0 = r1;
            this.label = 2;
            Object r52 = r3.getVersion(this);
            if (r52 == r0) goto L23;
            r6 = r52;
            r5 = th;
            r02 = r1 ? 1 : 0;
            goto L25
        L26:
            r62 = this.$cachedVersion;
            r5 = th;
            r12 = r1;
            goto L27
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl", f = "DataStoreImpl.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5}, l = {365, 366, 368, 369, 380, 384}, m = "readDataOrHandleCorruption", n = {"this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "hasWriteFileLock", "this", "ex", "newData", "hasWriteFileLock", "ex", "newData", "version"}, s = {"L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01091 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public C01091(DataStoreImpl<T> r1, Continuation<? super C01091> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return DataStoreImpl.access$readDataOrHandleCorruption(this.this$0, false, this);
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/Data;", "T", "locked", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2", f = "DataStoreImpl.kt", i = {0, 1}, l = {370, 371}, m = "invokeSuspend", n = {"locked", "data"}, s = {"Z$0", "L$0"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<Boolean, Continuation<? super Data<T>>, Object> {
        final /* synthetic */ int $preLockVersion;
        Object L$0;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public AnonymousClass2(DataStoreImpl<T> r1, int r2, Continuation<? super AnonymousClass2> r3) {
            this.this$0 = r1;
            this.$preLockVersion = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            AnonymousClass2 r0 = new AnonymousClass2(this.this$0, this.$preLockVersion, r4);
            r0.Z$0 = ((Boolean) r3).booleanValue();
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Boolean r1, Object r2) {
            return invoke(r1.booleanValue(), (Continuation) r2);
        }

        public final Object invoke(boolean r1, Continuation<? super Data<T>> r2) {
            return ((AnonymousClass2) create(Boolean.valueOf(r1), r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L10;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            Object r5 = this.L$0;
            ResultKt.throwOnFailure(r6);
        L18:
            int r62 = ((Number) r6).intValue();
        L21:
            if (r5 == null) goto L23;
            int r12 = r5.hashCode();
        L25:
            return new Data(r5, r12, r62);
        L23:
            r12 = 0;
            goto L25
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            boolean r13 = this.Z$0;
            ResultKt.throwOnFailure(r6);
        L13:
            if (r13 == false) goto L19;
            InterProcessCoordinator r14 = DataStoreImpl.access$getCoordinator(this.this$0);
            this.L$0 = r6;
            this.label = 2;
            Object r52 = r14.getVersion(this);
            if (r52 != r0) goto L17;
        L16:
            return r0;
        L17:
            Object r4 = r6;
            r6 = r52;
            r5 = r4;
            goto L18
        L19:
            Object r42 = r6;
            r62 = this.$preLockVersion;
            r5 = r42;
            goto L21
        L10:
            ResultKt.throwOnFailure(r6);
            r13 = this.Z$0;
            DataStoreImpl<T> r63 = this.this$0;
            this.Z$0 = r13;
            this.label = 1;
            r6 = DataStoreImpl.access$readDataFromFileOrDefault(r63, this);
            if (r6 != r0) goto L13;
            goto L13
        }
    }

    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3", f = "DataStoreImpl.kt", i = {}, l = {387, 388, 390}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes3.dex */
    public static final class C01103 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<T> $newData;
        final /* synthetic */ Ref.IntRef $version;
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public C01103(Ref.ObjectRef<T> r1, DataStoreImpl<T> r2, Ref.IntRef r3, Continuation<? super C01103> r4) {
            this.$newData = r1;
            this.this$0 = r2;
            this.$version = r3;
            super(1, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r4) {
            return new C01103(this.$newData, this.this$0, this.$version, r4);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Unit> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super Unit> r1) {
            return ((C01103) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L16;
            if (r1 == 1) goto L13;
            if (r1 == 2) goto L10;
            if (r1 != 3) goto L8;
            Ref.IntRef r6 = (Ref.IntRef) this.L$0;
            ResultKt.throwOnFailure(r7);
        L29:
            r6.element = ((Number) r7).intValue();
        L31:
            return Unit.INSTANCE;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L10:
            Ref.IntRef r12 = (Ref.IntRef) this.L$0;
            ResultKt.throwOnFailure(r7);     // Catch: CorruptionException -> L25
        L23:
            r12.element = ((Number) r7).intValue();     // Catch: CorruptionException -> L25
            goto L31
        L13:
            Ref.ObjectRef<T> r13 = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(r7);     // Catch: CorruptionException -> L25
        L20:
            r13.element = (T) r7;     // Catch: CorruptionException -> L25
            r12 = this.$version;     // Catch: CorruptionException -> L25
            InterProcessCoordinator r72 = DataStoreImpl.access$getCoordinator(this.this$0);     // Catch: CorruptionException -> L25
            this.L$0 = r12;     // Catch: CorruptionException -> L25
            this.label = 2;     // Catch: CorruptionException -> L25
            r7 = (T) r72.getVersion(this);
            if (r7 != r0) goto L23;
        L27:
            return r0;
        L16:
            ResultKt.throwOnFailure(r7);
            r13 = this.$newData;     // Catch: CorruptionException -> L25
            DataStoreImpl<T> r73 = this.this$0;     // Catch: CorruptionException -> L25
            this.L$0 = r13;     // Catch: CorruptionException -> L25
            this.label = 1;     // Catch: CorruptionException -> L25
            r7 = (T) DataStoreImpl.access$readDataFromFileOrDefault(r73, this);
            if (r7 != r0) goto L20;
        L25:
            Ref.IntRef r74 = this.$version;
            DataStoreImpl<T> r14 = this.this$0;
            T r3 = this.$newData.element;
            this.L$0 = r74;
            this.label = 3;
            Object r62 = r14.writeData$datastore_core_release(r3, true, this);
            if (r62 == r0) goto L27;
            r7 = (T) r62;
            r6 = r74;
            goto L29
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/State;", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readState$2", f = "DataStoreImpl.kt", i = {}, l = {Opcodes.MUL_INT_LIT8, Opcodes.USHR_INT_LIT8}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.datastore.core.DataStoreImpl$readState$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01112 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super State<T>>, Object> {
        final /* synthetic */ boolean $requireLock;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public C01112(DataStoreImpl<T> r1, boolean r2, Continuation<? super C01112> r3) {
            this.this$0 = r1;
            this.$requireLock = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new C01112(this.this$0, this.$requireLock, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super State<T>> r2) {
            return ((C01112) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L11;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            ResultKt.throwOnFailure(r5);
        L22:
            return (State) r5;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r5);     // Catch: Throwable -> L23
        L18:
            DataStoreImpl<T> r52 = this.this$0;
            boolean r12 = this.$requireLock;
            this.label = 2;
            r5 = DataStoreImpl.access$readDataAndUpdateCache(r52, r12, this);
            if (r5 != r0) goto L22;
        L20:
            return r0;
        L11:
            ResultKt.throwOnFailure(r5);
            boolean r53 = DataStoreImpl.access$getInMemoryCache$p(this.this$0).getCurrentState() instanceof Final;
            DataStoreImpl<T> r13 = this.this$0;
            if (r53 == true) goto L14;
            this.label = 1;     // Catch: Throwable -> L23
            if (DataStoreImpl.access$readAndInitOrPropagateAndThrowFailure(r13, this) != r0) goto L18;
        L14:
            return DataStoreImpl.access$getInMemoryCache$p(r13).getCurrentState();
        L23:
            th = move-exception;
            return new ReadException(th, -1);
        }
    }

    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\u008a@"}, d2 = {"<anonymous>", "T"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$transformAndWrite$2", f = "DataStoreImpl.kt", i = {1, 2}, l = {330, MediaError.DetailedErrorCode.SMOOTH_NETWORK, 337}, m = "invokeSuspend", n = {"curData", "newData"}, s = {"L$0", "L$0"})
    /* renamed from: androidx.datastore.core.DataStoreImpl$transformAndWrite$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01122 extends SuspendLambda implements Function1<Continuation<? super T>, Object> {
        final /* synthetic */ CoroutineContext $callerContext;
        final /* synthetic */ Function2<T, Continuation<? super T>, Object> $transform;
        Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public C01122(DataStoreImpl<T> r1, CoroutineContext r2, Function2<? super T, ? super Continuation<? super T>, ? extends Object> r3, Continuation<? super C01122> r4) {
            this.this$0 = r1;
            this.$callerContext = r2;
            this.$transform = r3;
            super(1, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r4) {
            return new C01122(this.this$0, this.$callerContext, this.$transform, r4);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object r1) {
            return invoke((Continuation) r1);
        }

        public final Object invoke(Continuation<? super T> r1) {
            return ((C01122) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r9) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L13;
            if (r1 == 1) goto L12;
            if (r1 == 2) goto L11;
            if (r1 != 3) goto L9;
            Object r8 = this.L$0;
            ResultKt.throwOnFailure(r9);
            return r8;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            Data r12 = (Data) this.L$0;
            ResultKt.throwOnFailure(r9);
        L19:
            r12.checkHashCode();
            if (Intrinsics.areEqual(r12.getValue(), r9) == true) goto L24;
            DataStoreImpl r13 = (DataStoreImpl<T>) this.this$0;
            this.L$0 = r9;
            this.label = 3;
            if (r13.writeData$datastore_core_release(r9, true, this) != r0) goto L24;
        L23:
            return r0;
        L24:
            return r9;
        L12:
            ResultKt.throwOnFailure(r9);
        L16:
            r12 = (Data) r9;
            CoroutineContext r92 = this.$callerContext;
            DataStoreImpl$transformAndWrite$2$newData$1 r6 = new DataStoreImpl$transformAndWrite$2$newData$1(this.$transform, r12, null);
            this.L$0 = r12;
            this.label = 2;
            r9 = BuildersKt.withContext(r92, r6, this);
            if (r9 != r0) goto L19;
        L13:
            ResultKt.throwOnFailure(r9);
            DataStoreImpl<T> r93 = this.this$0;
            this.label = 1;
            r9 = DataStoreImpl.access$readDataOrHandleCorruption(r93, true, this);
            if (r9 != r0) goto L16;
            goto L16
        }
    }

    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$updateData$2", f = "DataStoreImpl.kt", i = {}, l = {169}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.datastore.core.DataStoreImpl$updateData$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01132 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function2<T, Continuation<? super T>, Object> $transform;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ DataStoreImpl<T> this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public C01132(DataStoreImpl<T> r1, Function2<? super T, ? super Continuation<? super T>, ? extends Object> r2, Continuation<? super C01132> r3) {
            this.this$0 = r1;
            this.$transform = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            C01132 r0 = new C01132(this.this$0, this.$transform, r4);
            r0.L$0 = r3;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
            return ((C01132) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r7);
            return r7;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r7);
            CoroutineScope r72 = (CoroutineScope) this.L$0;
            CompletableDeferred r12 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
            State<T> r3 = DataStoreImpl.access$getInMemoryCache$p(this.this$0).getCurrentState();
            Message.Update r4 = new Message.Update(this.$transform, r12, r3, r72.getCoroutineContext());
            DataStoreImpl.access$getWriteActor$p(this.this$0).offer(r4);
            this.label = 1;
            Object r6 = r12.await(this);
            if (r6 != r0) goto L12;
            return r0;
        L12:
            return r6;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public DataStoreImpl(Storage<T> r3, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> r4, CorruptionHandler<T> r5, CoroutineScope r6) {
        this.storage = r3;
        this.corruptionHandler = r5;
        this.scope = r6;
        this.data = FlowKt.flow(new DataStoreImpl$data$1(this, null));
        this.collectorMutex = MutexKt.Mutex$default(false, 1, null);
        this.inMemoryCache = new DataStoreInMemoryCache();
        this.readAndInit = new InitDataStore(this, r4);
        this.storageConnectionDelegate = LazyKt.lazy(new DataStoreImpl$storageConnectionDelegate$1(this));
        this.coordinator$delegate = LazyKt.lazy(new DataStoreImpl$coordinator$2(this));
        this.writeActor = new SimpleActor(r6, new DataStoreImpl$writeActor$1(this), DataStoreImpl$writeActor$2.INSTANCE, new DataStoreImpl$writeActor$3(this, null));
    }

    public static final /* synthetic */ Object access$decrementCollector(DataStoreImpl r0, Continuation r1) {
        return r0.decrementCollector(r1);
    }

    public static final /* synthetic */ InterProcessCoordinator access$getCoordinator(DataStoreImpl r0) {
        return r0.getCoordinator();
    }

    public static final /* synthetic */ DataStoreInMemoryCache access$getInMemoryCache$p(DataStoreImpl r0) {
        return r0.inMemoryCache;
    }

    public static final /* synthetic */ InitDataStore access$getReadAndInit$p(DataStoreImpl r0) {
        return r0.readAndInit;
    }

    public static final /* synthetic */ Storage access$getStorage$p(DataStoreImpl r0) {
        return r0.storage;
    }

    public static final /* synthetic */ Lazy access$getStorageConnectionDelegate$p(DataStoreImpl r0) {
        return r0.storageConnectionDelegate;
    }

    public static final /* synthetic */ SimpleActor access$getWriteActor$p(DataStoreImpl r0) {
        return r0.writeActor;
    }

    public static final /* synthetic */ Object access$handleUpdate(DataStoreImpl r0, Message.Update r1, Continuation r2) {
        return r0.handleUpdate(r1, r2);
    }

    public static final /* synthetic */ Object access$incrementCollector(DataStoreImpl r0, Continuation r1) {
        return r0.incrementCollector(r1);
    }

    public static final /* synthetic */ Object access$readAndInitOrPropagateAndThrowFailure(DataStoreImpl r0, Continuation r1) {
        return r0.readAndInitOrPropagateAndThrowFailure(r1);
    }

    public static final /* synthetic */ Object access$readDataAndUpdateCache(DataStoreImpl r0, boolean r1, Continuation r2) {
        return r0.readDataAndUpdateCache(r1, r2);
    }

    public static final /* synthetic */ Object access$readDataFromFileOrDefault(DataStoreImpl r0, Continuation r1) {
        return r0.readDataFromFileOrDefault(r1);
    }

    public static final /* synthetic */ Object access$readDataOrHandleCorruption(DataStoreImpl r0, boolean r1, Continuation r2) {
        return r0.readDataOrHandleCorruption(r1, r2);
    }

    public static final /* synthetic */ Object access$readState(DataStoreImpl r0, boolean r1, Continuation r2) {
        return r0.readState(r1, r2);
    }

    private final Object decrementCollector(Continuation<? super Unit> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Mutex r5 = (Mutex) r0.L$1;
        DataStoreImpl<T> r02 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r62);
        Mutex r63 = r5;
        DataStoreImpl<T> this = r02;
    L30:
        int r03 = this.collectorCounter - 1;
        this.collectorCounter = r03;     // Catch: Throwable -> L22
        if (r03 != 0) goto L25;
        Job r04 = this.collectorJob;     // Catch: Throwable -> L22
        if (r04 == null) goto L24;
        Job.DefaultImpls.cancel$default(r04, null, 1, null);     // Catch: Throwable -> L22
    L24:
        this.collectorJob = null;     // Catch: Throwable -> L22
    L25:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L22
        r63.unlock(null);
        return Unit.INSTANCE;
    L22:
        th = move-exception;
        r63.unlock(null);
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        r63 = this.collectorMutex;
        r0.L$0 = this;
        r0.L$1 = r63;
        r0.label = 1;
        if (r63.lock(null, r0) != r12) goto L30;
        return r12;
    L7:
        r0 = new AnonymousClass1(this, r6);
        goto L8
    }

    private final <R> Object doWithWriteFileLock(boolean r2, Function1<? super Continuation<? super R>, ? extends Object> r3, Continuation<? super R> r4) {
        if (r2 == false) goto L6;
        return r3.invoke(r4);
    L6:
        return getCoordinator().lock(new AnonymousClass3(r3, null), r4);
    }

    private final InterProcessCoordinator getCoordinator() {
        return (InterProcessCoordinator) this.coordinator$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [androidx.datastore.core.DataStoreImpl, androidx.datastore.core.DataStoreImpl<T>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v14, types: [androidx.datastore.core.DataStoreImpl] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v5 */
    private final Object handleUpdate(Message.Update<T> r10, Continuation<? super Unit> r11) {
        if ((r11 instanceof C01041) == false) goto L7;
        C01041 r0 = (C01041) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        boolean r6 = true;
        if (r2 == 0) goto L23;
        if (r2 == 1) goto L13;
        if (r2 == 2) goto L20;
        if (r2 == 3) goto L13;
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        CompletableDeferred<T> r9 = (CompletableDeferred) r0.L$2;
        DataStoreImpl r102 = (DataStoreImpl) r0.L$1;
        Message.Update<T> r22 = (Message.Update) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L16
        CompletableDeferred<T> r113 = r9;
        boolean r92 = (DataStoreImpl<T>) r102;
        r10 = r22;
        ?? r93 = r92;
    L42:
        Function2<T, Continuation<? super T>, Object> r23 = r10.getTransform();     // Catch: Throwable -> L30
        CoroutineContext r103 = r10.getCallerContext();     // Catch: Throwable -> L30
        r0.L$0 = r113;     // Catch: Throwable -> L30
        r0.L$1 = null;     // Catch: Throwable -> L30
        r0.L$2 = null;     // Catch: Throwable -> L30
        r0.label = 3;     // Catch: Throwable -> L30
        Object r94 = r93.transformAndWrite(r23, r103, r0);     // Catch: Throwable -> L30
        if (r94 == r12) goto L44;
    L29:
        CompletableDeferred<T> r8 = r113;
        r112 = r94;
        DataStoreImpl<T> this = (DataStoreImpl<T>) r8;
    L45:
        Object r104 = Result.m2018constructorimpl(r112);     // Catch: Throwable -> L16
        CompletableDeferred r95 = this;
    L56:
        CompletableDeferredKt.completeWith(r95, r104);
        return Unit.INSTANCE;
    L44:
        return r12;
    L13:
        boolean r96 = (DataStoreImpl<T>) ((CompletableDeferred) r0.L$0);
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L16
        this = r96;
        goto L45
    L23:
        ResultKt.throwOnFailure(r112);
        r113 = r10.getAck();
        Result.Companion r24 = Result.Companion;     // Catch: Throwable -> L30
        State<T> r25 = this.inMemoryCache.getCurrentState();     // Catch: Throwable -> L30
        if ((r25 instanceof Data) == false) goto L33;
        Function2<T, Continuation<? super T>, Object> r26 = r10.getTransform();     // Catch: Throwable -> L30
        CoroutineContext r105 = r10.getCallerContext();     // Catch: Throwable -> L30
        r0.L$0 = r113;     // Catch: Throwable -> L30
        r0.label = 1;     // Catch: Throwable -> L30
        r94 = transformAndWrite(r26, r105, r0);     // Catch: Throwable -> L30
        if (r94 != r12) goto L29;
    L33:
        if ((r25 instanceof ReadException) == true) goto L36;
        r6 = r25 instanceof UnInitialized;     // Catch: Throwable -> L30
    L36:
        if (r6 == false) goto L50;
        if (r25 != r10.getLastState()) goto L48;
        r0.L$0 = r10;     // Catch: Throwable -> L30
        r0.L$1 = this;     // Catch: Throwable -> L30
        r0.L$2 = r113;     // Catch: Throwable -> L30
        r0.label = 2;     // Catch: Throwable -> L30
        Object r27 = readAndInitOrPropagateAndThrowFailure(r0);     // Catch: Throwable -> L30
        this = this;
        if (r27 != r12) goto L42;
    L48:
        throw ((ReadException) r25).getReadException();     // Catch: Throwable -> L30
    L50:
        if ((r25 instanceof Final) == false) goto L54;
        throw ((Final) r25).getFinalException();     // Catch: Throwable -> L30
    L54:
        throw new NoWhenBranchMatchedException();     // Catch: Throwable -> L30
    L30:
        th = th;
        this = r113;
    L55:
        Result.Companion r114 = Result.Companion;
        r104 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        r95 = this;
    L16:
        th = th;
    L7:
        r0 = new C01041(this, r11);
        goto L8
    }

    private final Object incrementCollector(Continuation<? super Unit> r12) {
        if ((r12 instanceof C01051) == false) goto L7;
        C01051 r0 = (C01051) r12;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r122 = r0.result;
        Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Mutex r11 = (Mutex) r0.L$1;
        DataStoreImpl<T> r02 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r122);
        Mutex r123 = r11;
        DataStoreImpl<T> this = r02;
    L27:
        int r03 = this.collectorCounter + 1;     // Catch: Throwable -> L20
        this.collectorCounter = r03;     // Catch: Throwable -> L20
        if (r03 != 1) goto L22;
        this.collectorJob = BuildersKt.launch$default(this.scope, null, null, new DataStoreImpl$incrementCollector$2$1(this, null), 3, null);     // Catch: Throwable -> L20
    L22:
        Unit r112 = Unit.INSTANCE;     // Catch: Throwable -> L20
        r123.unlock(null);
        return Unit.INSTANCE;
    L20:
        th = move-exception;
        r123.unlock(null);
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r122);
        r123 = this.collectorMutex;
        r0.L$0 = this;
        r0.L$1 = r123;
        r0.label = 1;
        if (r123.lock(null, r0) != r13) goto L27;
        return r13;
    L7:
        r0 = new C01051(this, r12);
        goto L8
    }

    private final Object readAndInitOrPropagateAndThrowFailure(Continuation<? super Unit> r7) {
        if ((r7 instanceof C01061) == false) goto L7;
        C01061 r0 = (C01061) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L20;
        if (r2 == 1) goto L19;
        if (r2 != 2) goto L17;
        int r6 = r0.I$0;
        DataStoreImpl<T> r02 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r72);     // Catch: Throwable -> L15
    L28:
        return Unit.INSTANCE;
    L15:
        Throwable th = th;
    L31:
        r02.inMemoryCache.tryUpdate(new ReadException(th, r6));
        throw th;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        DataStoreImpl<T> this = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r72);
    L23:
        int r73 = ((Number) r72).intValue();
        DataStoreImpl<T>.InitDataStore r22 = this.readAndInit;     // Catch: Throwable -> L29
        r0.L$0 = this;     // Catch: Throwable -> L29
        r0.I$0 = r73;     // Catch: Throwable -> L29
        r0.label = 2;     // Catch: Throwable -> L29
        if (r22.runIfNeeded(r0) != r12) goto L28;
    L26:
        return r12;
    L29:
        th = move-exception;
        r02 = this;
        r6 = r73;
        th = th;
        goto L31
    L20:
        ResultKt.throwOnFailure(r72);
        InterProcessCoordinator r74 = getCoordinator();
        r0.L$0 = this;
        r0.label = 1;
        r72 = r74.getVersion(r0);
        if (r72 != r12) goto L23;
    L7:
        r0 = new C01061(this, r7);
        goto L8
    }

    private final Object readDataAndUpdateCache(boolean r10, Continuation<? super State<T>> r11) {
        if ((r11 instanceof C01071) == false) goto L7;
        C01071 r0 = (C01071) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 == 1) goto L17;
        if (r2 == 2) goto L16;
        if (r2 != 3) goto L14;
        DataStoreImpl<T> r9 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r112);
    L41:
        Pair r113 = (Pair) r112;
    L42:
        State<T> r102 = (State) r113.component1();
        if (((Boolean) r113.component2()).booleanValue() == false) goto L45;
        r9.inMemoryCache.tryUpdate(r102);
    L45:
        return r102;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        r9 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r112);
    L36:
        r113 = (Pair) r112;
        goto L42
    L17:
        r10 = r0.Z$0;
        State<T> r92 = (State) r0.L$1;
        DataStoreImpl<T> r22 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r112);
    L24:
        int r114 = ((Number) r112).intValue();
        boolean r5 = r92 instanceof Data;
        if (r5 == false) goto L27;
        int r7 = r92.getVersion();
    L28:
        if (r5 == false) goto L31;
        if (r114 != r7) goto L31;
        return r92;
    L31:
        if (r10 == false) goto L37;
        InterProcessCoordinator r93 = r22.getCoordinator();
        C01083 r103 = new C01083(r22, null);
        r0.L$0 = r22;
        r0.L$1 = null;
        r0.label = 2;
        r112 = r93.lock(r103, r0);
        if (r112 == r12) goto L39;
        r9 = r22;
    L39:
        return r12;
    L37:
        InterProcessCoordinator r94 = r22.getCoordinator();
        AnonymousClass4 r104 = new AnonymousClass4(r22, r7, null);
        r0.L$0 = r22;
        r0.L$1 = null;
        r0.label = 3;
        r112 = r94.tryLock(r104, r0);
        if (r112 == r12) goto L39;
        r9 = r22;
        goto L41
    L27:
        r7 = -1;
        goto L28
    L18:
        ResultKt.throwOnFailure(r112);
        State<T> r115 = this.inMemoryCache.getCurrentState();
        if ((r115 instanceof UnInitialized) == true) goto L46;
        InterProcessCoordinator r23 = getCoordinator();
        r0.L$0 = this;
        r0.L$1 = r115;
        r0.Z$0 = r10;
        r0.label = 1;
        Object r24 = r23.getVersion(r0);
        if (r24 == r12) goto L39;
        r22 = this;
        r92 = r115;
        r112 = r24;
        goto L24
    L46:
        Segment$$ExternalSyntheticBUOutline1.m("This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542");
        return null;
    L7:
        r0 = new C01071(this, r11);
        goto L8
    }

    private final Object readDataFromFileOrDefault(Continuation<? super T> r1) {
        return StorageConnectionKt.readData(getStorageConnection$datastore_core_release(), r1);
    }

    private final Object readDataOrHandleCorruption(boolean r9, Continuation<? super Data<T>> r10) {
        if ((r10 instanceof C01091) == false) goto L7;
        C01091 r0 = (C01091) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = (T) r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r3 = 0;
        switch(r0.label) {
            case 0: goto L34;
            case 1: goto L31;
            case 2: goto L26;
            case 3: goto L23;
            case 4: goto L18;
            case 5: goto L17;
            case 6: goto L12;
            default: goto L10;
        };
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L12:
        Ref.IntRef r8 = (Ref.IntRef) r0.L$2;
        Ref.ObjectRef r92 = (Ref.ObjectRef) r0.L$1;
        CorruptionException r02 = (CorruptionException) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: Throwable -> L15
    L65:
        T r93 = r92.element;
        if (r93 == null) goto L69;
        r3 = r93.hashCode();
    L69:
        return new Data(r93, r3, r8.element);
    L15:
        th = th;
    L72:
        ExceptionsKt.addSuppressed(r02, th);
        throw r02;
    L17:
        boolean r82 = r0.Z$0;
        Ref.ObjectRef r94 = (Ref.ObjectRef) r0.L$3;
        Ref.ObjectRef r2 = (Ref.ObjectRef) r0.L$2;
        CorruptionException r5 = (CorruptionException) r0.L$1;
        DataStoreImpl<T> r6 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r102);
    L60:
        r94.element = (T) r102;
        Ref.IntRef r95 = new Ref.IntRef();
        C01103 r103 = new C01103(r2, r6, r95, null);     // Catch: Throwable -> L70
        r0.L$0 = r5;     // Catch: Throwable -> L70
        r0.L$1 = r2;     // Catch: Throwable -> L70
        r0.L$2 = r95;     // Catch: Throwable -> L70
        r0.L$3 = null;     // Catch: Throwable -> L70
        r0.label = 6;     // Catch: Throwable -> L70
        if (r6.doWithWriteFileLock(r82, r103, r0) != r12) goto L64;
    L63:
        return r12;
    L64:
        r8 = r95;
        r92 = r2;
    L70:
        th = th;
        r02 = r5;
        goto L72
    L18:
        boolean r96 = r0.Z$0;
        DataStoreImpl r83 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: CorruptionException -> L21
    L55:
        return (Data) r102;
    L23:
        r9 = r0.Z$0;
        DataStoreImpl<T> this = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: CorruptionException -> L21
    L51:
        int r104 = ((Number) r102).intValue();     // Catch: CorruptionException -> L21
        InterProcessCoordinator r22 = this.getCoordinator();     // Catch: CorruptionException -> L21
        AnonymousClass2 r52 = new AnonymousClass2(this, r104, null);     // Catch: CorruptionException -> L21
        r0.L$0 = this;     // Catch: CorruptionException -> L21
        r0.Z$0 = r9;     // Catch: CorruptionException -> L21
        r0.label = 4;     // Catch: CorruptionException -> L21
        r102 = (T) r22.tryLock(r52, r0);
        if (r102 != r12) goto L55;
    L26:
        int r84 = r0.I$0;
        r9 = r0.Z$0;
        Object r23 = r0.L$1;
        DataStoreImpl<T> r53 = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: CorruptionException -> L29
    L46:
        return new Data(r23, r84, ((Number) r102).intValue());
    L29:
        e = e;
        this = r53;
    L56:
        r2 = new Ref.ObjectRef();
        CorruptionHandler<T> r54 = this.corruptionHandler;
        r0.L$0 = this;
        r0.L$1 = e;
        r0.L$2 = r2;
        r0.L$3 = r2;
        r0.Z$0 = r9;
        r0.label = 5;
        Object r55 = r54.handleCorruption(e, r0);
        if (r55 == r12) goto L63;
        r5 = e;
        r102 = (T) r55;
        r6 = this;
        r82 = r9;
        r94 = r2;
        goto L60
    L31:
        r9 = r0.Z$0;
        this = (DataStoreImpl) r0.L$0;
        ResultKt.throwOnFailure(r102);     // Catch: CorruptionException -> L21
    L39:
        if (r102 == null) goto L41;
        int r24 = r102.hashCode();     // Catch: CorruptionException -> L21
    L42:
        InterProcessCoordinator r56 = this.getCoordinator();     // Catch: CorruptionException -> L21
        r0.L$0 = this;     // Catch: CorruptionException -> L21
        r0.L$1 = r102;     // Catch: CorruptionException -> L21
        r0.Z$0 = r9;     // Catch: CorruptionException -> L21
        r0.I$0 = r24;     // Catch: CorruptionException -> L21
        r0.label = 2;     // Catch: CorruptionException -> L21
        Object r57 = r56.getVersion(r0);     // Catch: CorruptionException -> L21
        if (r57 == r12) goto L63;
        r53 = this;
        r84 = r24;
        r23 = r102;
        r102 = (T) r57;
        goto L46
    L41:
        r24 = 0;
        goto L42
    L34:
        ResultKt.throwOnFailure(r102);
        if (r9 == false) goto L48;
        r0.L$0 = this;     // Catch: CorruptionException -> L21
        r0.Z$0 = r9;     // Catch: CorruptionException -> L21
        r0.label = 1;     // Catch: CorruptionException -> L21
        r102 = (T) readDataFromFileOrDefault(r0);
        if (r102 != r12) goto L39;
    L48:
        InterProcessCoordinator r105 = getCoordinator();     // Catch: CorruptionException -> L21
        r0.L$0 = this;     // Catch: CorruptionException -> L21
        r0.Z$0 = r9;     // Catch: CorruptionException -> L21
        r0.label = 3;     // Catch: CorruptionException -> L21
        r102 = (T) r105.getVersion(r0);
        if (r102 != r12) goto L51;
    L21:
        e = e;
    L7:
        r0 = new C01091(this, r10);
        goto L8
    }

    private final Object readState(boolean r4, Continuation<? super State<T>> r5) {
        return BuildersKt.withContext(this.scope.getCoroutineContext(), new C01112(this, r4, null), r5);
    }

    private final Object transformAndWrite(Function2<? super T, ? super Continuation<? super T>, ? extends Object> r4, CoroutineContext r5, Continuation<? super T> r6) {
        return getCoordinator().lock(new C01122(this, r5, r4, null), r6);
    }

    @Override // androidx.datastore.core.DataStore
    public Flow<T> getData() {
        return this.data;
    }

    public final StorageConnection<T> getStorageConnection$datastore_core_release() {
        return this.storageConnectionDelegate.getValue();
    }

    @Override // androidx.datastore.core.DataStore
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> r4, Continuation<? super T> r5) {
        UpdatingDataContextElement r0 = (UpdatingDataContextElement) r5.getContext().get(UpdatingDataContextElement.Companion.Key.INSTANCE);
        if (r0 == null) goto L6;
        r0.checkNotUpdating(this);
    L6:
        return BuildersKt.withContext(new UpdatingDataContextElement(r0, this), new C01132(this, r4, null), r5);
    }

    public final Object writeData$datastore_core_release(T r11, boolean r12, Continuation<? super Integer> r13) {
        if ((r13 instanceof DataStoreImpl$writeData$1) == false) goto L7;
        DataStoreImpl$writeData$1 r0 = (DataStoreImpl$writeData$1) r13;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r132 = r0.result;
        Object r14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Ref.IntRef r10 = (Ref.IntRef) r0.L$0;
        ResultKt.throwOnFailure(r132);
    L19:
        return Boxing.boxInt(r10.element);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r132);
        Ref.IntRef r5 = new Ref.IntRef();
        StorageConnection<T> r133 = getStorageConnection$datastore_core_release();
        DataStoreImpl$writeData$2 r4 = new DataStoreImpl$writeData$2(r5, this, r11, r12, null);
        r0.L$0 = r5;
        r0.label = 1;
        if (r133.writeScope(r4, r0) != r14) goto L17;
        return r14;
    L17:
        r10 = r5;
    L7:
        r0 = new DataStoreImpl$writeData$1(this, r13);
        goto L8
    }
}
