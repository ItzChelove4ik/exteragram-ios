package androidx.camera.camera2.pipe.compat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompat;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompatFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u0001BY\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J/\u0010 \u001a\u00020\u001f*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u001a2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b \u0010!J7\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0002¢\u0006\u0004\b$\u0010%J-\u0010'\u001a\u00020\u001f*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0002¢\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020+2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b,\u0010-J\u001d\u0010.\u001a\u00020\u001d2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002¢\u0006\u0004\b.\u0010/J\u001a\u00103\u001a\u0004\u0018\u0001002\u0006\u0010\u001c\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b1\u00102J\u001a\u00106\u001a\u0004\u0018\u0001042\u0006\u0010\u001c\u001a\u00020\u0017H\u0087@¢\u0006\u0004\b5\u00102J\u0015\u00107\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016¢\u0006\u0004\b7\u0010*J\u001b\u00109\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001708\u0018\u000108¢\u0006\u0004\b9\u0010:J\r\u0010;\u001a\u00020\u001f¢\u0006\u0004\b;\u0010<R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010=R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010>R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010?R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010@R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010=R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ER\u001e\u0010F\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00168\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bF\u0010GR$\u0010H\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001708\u0018\u0001088\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bH\u0010IR(\u0010L\u001a\u0016\u0012\u0004\u0012\u00020\u0017\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001000K0J8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\bL\u0010MR(\u0010N\u001a\u0016\u0012\u0004\u0012\u00020\u0017\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001040K0J8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\bN\u0010MR\u0014\u0010O\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010PR#\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00158\u0006¢\u0006\f\n\u0004\b&\u0010Q\u001a\u0004\bR\u0010\u0019R#\u0010X\u001a\n S*\u0004\u0018\u00010\r0\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W¨\u0006Y"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2DeviceCache;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljavax/inject/Provider;", "Landroid/hardware/camera2/CameraManager;", "cameraManager", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroid/content/Context;", "context", "Landroid/content/pm/PackageManager;", "packageManager", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "cameraErrorListener", "Landroidx/camera/featurecombinationquery/CameraDeviceSetupCompatFactory;", "cameraDeviceSetupCompatFactoryProvider", "Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;", "cameraPipeLifetime", "Lkotlinx/coroutines/Job;", "cameraPipeJob", "<init>", "(Ljavax/inject/Provider;Landroidx/camera/camera2/pipe/core/Threads;Landroid/content/Context;Landroid/content/pm/PackageManager;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Ljavax/inject/Provider;Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;Lkotlinx/coroutines/Job;)V", "Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "createCameraIdListFlow", "()Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/channels/ProducerScope;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "isAvailable", _UrlKt.FRAGMENT_ENCODE_SET, "onCameraAvailabilityChanged", "(Lkotlinx/coroutines/channels/ProducerScope;Ljava/lang/String;Z)V", "cachedCameraIds", "cameraIdsRead", "getUpdatedCameraIds", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "cameraIds", "sendCameraIdList", "(Lkotlinx/coroutines/channels/ProducerScope;Ljava/util/List;)V", "readCameraIds", "()Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "estimateMinInternalCameraCount", "(Landroid/content/pm/PackageManager;)I", "isValidCameraIds", "(Ljava/util/List;)Z", "Landroidx/camera/featurecombinationquery/CameraDeviceSetupCompat;", "getOrInitializeDeviceSetupCompat-0r8Bogc", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrInitializeDeviceSetupCompat", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceSetupWrapper;", "getOrInitializeDeviceSetupWrapper-0r8Bogc", "getOrInitializeDeviceSetupWrapper", "awaitCameraIds", _UrlKt.FRAGMENT_ENCODE_SET, "awaitConcurrentCameraIds", "()Ljava/util/Set;", "shutdown", "()V", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/pipe/core/Threads;", "Landroid/content/Context;", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "lock", "Ljava/lang/Object;", "openableCameras", "Ljava/util/List;", "concurrentCameras", "Ljava/util/Set;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/Deferred;", "cameraDeviceSetupCache", "Ljava/util/Map;", "camera2DeviceSetupWrapperCache", "minimumCameraCount", "I", "Lkotlinx/coroutines/flow/Flow;", "getCameraIds", "kotlin.jvm.PlatformType", "cameraDeviceSetupCompatFactory$delegate", "Lkotlin/Lazy;", "getCameraDeviceSetupCompatFactory", "()Landroidx/camera/featurecombinationquery/CameraDeviceSetupCompatFactory;", "cameraDeviceSetupCompatFactory", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2DeviceCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2DeviceCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCache\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 Channel.kt\nkotlinx/coroutines/channels/ChannelKt\n+ 7 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,391:1\n50#2,2:392\n50#2,2:401\n50#2,2:411\n59#2,2:416\n59#2,2:421\n50#2,2:423\n82#2,2:426\n75#2,2:429\n75#2,2:431\n75#2,2:433\n59#2,2:448\n71#2,2:450\n50#2,2:452\n75#2,2:454\n384#3,7:394\n384#3,7:404\n1#4:403\n1#4:445\n1740#5,3:413\n1761#5,3:418\n1563#5:456\n1634#5,3:457\n544#6:425\n545#6:428\n11546#7,9:435\n13472#7:444\n13473#7:446\n11555#7:447\n*S KotlinDebug\n*F\n+ 1 Camera2DeviceCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCache\n*L\n93#1:392,2\n133#1:401,2\n172#1:411,2\n247#1:416,2\n253#1:421,2\n282#1:423,2\n284#1:426,2\n297#1:429,2\n301#1:431,2\n309#1:433,2\n319#1:448,2\n321#1:450,2\n376#1:452,2\n379#1:454,2\n118#1:394,7\n149#1:404,7\n315#1:445\n246#1:413,3\n251#1:418,3\n383#1:456\n383#1:457,3\n283#1:425\n283#1:428\n315#1:435,9\n315#1:444\n315#1:446\n315#1:447\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2DeviceCache {
    private final Map<CameraId, Deferred<Camera2DeviceSetupWrapper>> camera2DeviceSetupWrapperCache;
    private final Map<CameraId, Deferred<CameraDeviceSetupCompat>> cameraDeviceSetupCache;
    private final Lazy cameraDeviceSetupCompatFactory$delegate;
    private final Provider<CameraDeviceSetupCompatFactory> cameraDeviceSetupCompatFactoryProvider;
    private final CameraErrorListener cameraErrorListener;
    private final Flow<List<CameraId>> cameraIds;
    private final Provider<CameraManager> cameraManager;
    private Set<? extends Set<CameraId>> concurrentCameras;
    private final Context context;
    private final Object lock;
    private final int minimumCameraCount;
    private List<CameraId> openableCameras;
    private final CoroutineScope scope;
    private final Threads threads;

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/channels/ProducerScope;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2DeviceCache$createCameraIdListFlow$1", f = "Camera2DeviceCache.kt", i = {}, l = {235}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nCamera2DeviceCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2DeviceCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCache$createCameraIdListFlow$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,391:1\n1#2:392\n*E\n"})
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2DeviceCache$createCameraIdListFlow$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super List<? extends CameraId>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ Camera2DeviceCache this$0;

        public static Unit $r8$lambda$iZGqJgau6RNOY255ntk5AHacrdw(CameraManager r0, Camera2DeviceCache$createCameraIdListFlow$1$callback$1 r1) {
            r0.unregisterAvailabilityCallback(r1);
            return Unit.INSTANCE;
        }

        public AnonymousClass1(Camera2DeviceCache r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super List<? extends CameraId>> r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(ProducerScope<? super List<CameraId>> r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r7);
        L22:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r7);
            ProducerScope r72 = (ProducerScope) this.L$0;
            Camera2DeviceCache$createCameraIdListFlow$1$callback$1 r12 = new Camera2DeviceCache$createCameraIdListFlow$1$callback$1(this.this$0, r72);
            CameraManager r3 = (CameraManager) Camera2DeviceCache.access$getCameraManager$p(this.this$0).get();
            r3.registerAvailabilityCallback(r12, Camera2DeviceCache.access$getThreads$p(this.this$0).getCamera2Handler());
            Object r4 = Camera2DeviceCache.access$getLock$p(this.this$0);
            Camera2DeviceCache r5 = this.this$0;
            monitor-enter(r4);
            List r52 = Camera2DeviceCache.access$getOpenableCameras$p(r5);     // Catch: Throwable -> L23
            monitor-exit(r4);
            Camera2DeviceCache r42 = this.this$0;
            if (r52 == null) goto L15;
            Camera2DeviceCache.access$sendCameraIdList(r42, r72, r52);
        L18:
            Camera2DeviceCache$createCameraIdListFlow$1$$ExternalSyntheticLambda0 r43 = new Camera2DeviceCache$createCameraIdListFlow$1$$ExternalSyntheticLambda0(r3, r12);
            this.label = 1;
            if (ProduceKt.awaitClose(r72, r43, this) != r0) goto L22;
            return r0;
        L15:
            List r44 = Camera2DeviceCache.access$readCameraIds(r42);
            if (r44 == null) goto L18;
            Camera2DeviceCache.access$sendCameraIdList(this.this$0, r72, r44);
        L23:
            th = move-exception;
            throw th;
        }
    }

    public static void $r8$lambda$N5l1V0_CmXGrpBQ_xaIGH_Usvdo(Camera2DeviceCache r2) {
        CoroutineScopeKt.cancel$default(r2.scope, null, 1, null);
    }

    public static CameraDeviceSetupCompatFactory $r8$lambda$h4V_MBgVJsazE4pMnpkyN4I9Lec(Camera2DeviceCache r0) {
        return r0.cameraDeviceSetupCompatFactoryProvider.get();
    }

    public Camera2DeviceCache(Provider<CameraManager> r8, Threads r9, Context r10, PackageManager r11, CameraErrorListener r12, Provider<CameraDeviceSetupCompatFactory> r13, CameraPipeLifetime r14, Job r15) {
        this.cameraManager = r8;
        this.threads = r9;
        this.context = r10;
        this.cameraErrorListener = r12;
        this.cameraDeviceSetupCompatFactoryProvider = r13;
        CoroutineScope r82 = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob(r15).plus(r9.getLightweightDispatcher()).plus(new CoroutineName("Camera2DeviceCache")));
        this.scope = r82;
        this.lock = new Object();
        this.cameraDeviceSetupCache = new LinkedHashMap();
        this.camera2DeviceSetupWrapperCache = new LinkedHashMap();
        this.minimumCameraCount = estimateMinInternalCameraCount(r11);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "Camera2DeviceCache: Expected minimum camera count = " + access$getMinimumCameraCount$p(this));
    L5:
        r14.addShutdownAction(CameraPipeLifetime.ShutdownType.SCOPE, new Camera2DeviceCache$$ExternalSyntheticLambda0(this));
        this.cameraIds = FlowKt.shareIn(FlowKt.distinctUntilChanged(createCameraIdListFlow()), r82, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0, 0, 3, null), 1);
        this.cameraDeviceSetupCompatFactory$delegate = LazyKt.lazy(new Camera2DeviceCache$$ExternalSyntheticLambda1(this));
    }

    public static final /* synthetic */ CameraDeviceSetupCompatFactory access$getCameraDeviceSetupCompatFactory(Camera2DeviceCache r0) {
        return r0.getCameraDeviceSetupCompatFactory();
    }

    public static final /* synthetic */ CameraErrorListener access$getCameraErrorListener$p(Camera2DeviceCache r0) {
        return r0.cameraErrorListener;
    }

    public static final /* synthetic */ Provider access$getCameraManager$p(Camera2DeviceCache r0) {
        return r0.cameraManager;
    }

    public static final /* synthetic */ Object access$getLock$p(Camera2DeviceCache r0) {
        return r0.lock;
    }

    public static final /* synthetic */ int access$getMinimumCameraCount$p(Camera2DeviceCache r0) {
        return r0.minimumCameraCount;
    }

    public static final /* synthetic */ List access$getOpenableCameras$p(Camera2DeviceCache r0) {
        return r0.openableCameras;
    }

    public static final /* synthetic */ Threads access$getThreads$p(Camera2DeviceCache r0) {
        return r0.threads;
    }

    public static final /* synthetic */ void access$onCameraAvailabilityChanged(Camera2DeviceCache r0, ProducerScope r1, String r2, boolean r3) {
        r0.onCameraAvailabilityChanged(r1, r2, r3);
    }

    public static final /* synthetic */ List access$readCameraIds(Camera2DeviceCache r0) {
        return r0.readCameraIds();
    }

    public static final /* synthetic */ void access$sendCameraIdList(Camera2DeviceCache r0, ProducerScope r1, List r2) {
        r0.sendCameraIdList(r1, r2);
    }

    private final Flow<List<CameraId>> createCameraIdListFlow() {
        return FlowKt.callbackFlow(new AnonymousClass1(this, null));
    }

    private final int estimateMinInternalCameraCount(PackageManager r2) {
        boolean r1 = r2.hasSystemFeature("android.hardware.camera");
        if (r2.hasSystemFeature("android.hardware.camera.front") == true) goto L5;
        return r1 ? 1 : 0;
    L5:
        return (r1 ? 1 : 0) + 1;
    }

    private final CameraDeviceSetupCompatFactory getCameraDeviceSetupCompatFactory() {
        return (CameraDeviceSetupCompatFactory) this.cameraDeviceSetupCompatFactory$delegate.getValue();
    }

    private final List<CameraId> getUpdatedCameraIds(List<CameraId> r1, List<CameraId> r2) {
        if (r2 != null) goto L4;
    L8:
        return r1;
    L4:
        if (isValidCameraIds(r2) == true) goto L7;
        if (r1 != null) goto L8;
    L7:
        return r2;
    }

    private final boolean isValidCameraIds(List<CameraId> r1) {
        if (r1.size() < this.minimumCameraCount) goto L6;
        return true;
    L6:
        return false;
    }

    private final void onCameraAvailabilityChanged(ProducerScope<? super List<CameraId>> r4, String r5, boolean r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
        List<CameraId> r1 = this.openableCameras;     // Catch: Throwable -> L46
        monitor-exit(r0);
        List<CameraId> r2 = null;
        if (r6 != true) goto L24;
        if (r1 == null) goto L21;
        List<CameraId> r62 = r1;
        if ((r62 instanceof Collection) == true) goto L12;
    L14:
        Iterator<T> r63 = r62.iterator();
    L16:
        if (r63.hasNext() == false) goto L21;
        if (Intrinsics.areEqual(((CameraId) r63.next()).m233unboximpl(), r5) == false) goto L16;
    L40:
        List<CameraId> r52 = getUpdatedCameraIds(r1, r2);
        if (r52 == null) goto L57;
        sendCameraIdList(r4, r52);
        return;
    L57:
        return;
    L12:
        if (r62.isEmpty() == false) goto L14;
    L21:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L23;
        android.util.Log.i("CXCP", "New camera " + r5 + " detected");
    L23:
        r2 = readCameraIds();
        goto L40
    L24:
        if (r6 == true) goto L44;
        if (r1 == null) goto L37;
        List<CameraId> r64 = r1;
        if ((r64 instanceof Collection) == true) goto L29;
    L31:
        Iterator<T> r65 = r64.iterator();
    L33:
        if (r65.hasNext() == false) goto L40;
        if (Intrinsics.areEqual(((CameraId) r65.next()).m233unboximpl(), r5) == false) goto L33;
    L29:
        if (r64.isEmpty() == false) goto L31;
    L37:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L39;
        android.util.Log.i("CXCP", "Unavailable camera " + r5 + " detected");
    L39:
        r2 = readCameraIds();
        goto L40
    L44:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L46:
        th = move-exception;
        throw th;
    }

    private final List<CameraId> readCameraIds() {
        String[] r0 = this.cameraManager.get().getCameraIdList();     // Catch: NullPointerException -> L30 ArrayIndexOutOfBoundsException -> L35 CameraAccessException -> L40
        ArrayList r2 = new ArrayList();
        int r3 = r0.length;
        int r4 = 0;
    L5:
        if (r4 >= r3) goto L14;
        String r5 = CameraId.m228constructorimpl(r0[r4]);
        if (r5 == null) goto L9;
        CameraId r52 = CameraId.m227boximpl(r5);
    L10:
        if (r52 == null) goto L12;
        r2.add(r52);
    L12:
        r4 = r4 + 1;
        goto L5
    L9:
        r52 = null;
        goto L10
    L14:
        if (isValidCameraIds(r2) == false) goto L27;
        Object r02 = this.lock;
        monitor-enter(r02);
        this.openableCameras = r2;     // Catch: Throwable -> L23
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L23
        monitor-exit(r02);
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L29;
        android.util.Log.i("CXCP", "Loaded CameraIdList " + r2);
        return r2;
    L29:
        return r2;
    L23:
        th = move-exception;
        throw th;
    L27:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L29;
        android.util.Log.w("CXCP", "Failed to query camera ID list: Invalid list returned: " + r2 + '.');
        goto L29
    L40:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L44;
        android.util.Log.w("CXCP", "Failed to query CameraManager#getCameraIdList!", e);
    L44:
        return null;
    L35:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L39;
        android.util.Log.w("CXCP", "Failed to query CameraManager#getCameraIdList!Unexpected ArrayIndexOutOfBoundsException thrown by framework.", e);
    L39:
        return null;
    L30:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L34;
        android.util.Log.w("CXCP", "Failed to query CameraManager#getCameraIdList!Null was returned by framework.", e);
    L34:
        return null;
    }

    private final void sendCameraIdList(ProducerScope<? super List<CameraId>> r4, List<CameraId> r5) {
        Log r3 = Log.INSTANCE;
        if (r3.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "Emitting camera ID list: " + r5);
    L5:
        Object r42 = ChannelsKt.trySendBlocking(r4, r5);
        if ((r42 instanceof ChannelResult.Failed) == false) goto L11;
        ChannelResult.m3537exceptionOrNullimpl(r42);
        if (r3.getERROR_LOGGABLE() == false) goto L12;
        android.util.Log.e("CXCP", "Failed to send camera ID list: " + r5 + '!');
        return;
    L12:
        return;
    }

    public final List<CameraId> awaitCameraIds() {
        Object r0 = this.lock;
        monitor-enter(r0);
        List<CameraId> r1 = this.openableCameras;     // Catch: Throwable -> L10
        monitor-exit(r0);
        if (r1 == null) goto L9;
        return r1;
    L9:
        return readCameraIds();
    L10:
        th = move-exception;
        throw th;
    }

    public final Set<Set<CameraId>> awaitConcurrentCameraIds() {
        if (Build.VERSION.SDK_INT < 30) goto L5;
        Object r1 = this.lock;
        monitor-enter(r1);
        Set r2 = this.concurrentCameras;     // Catch: Throwable -> L35
        monitor-exit(r1);
        if (r2 == null) goto L40;
        if (r2.isEmpty() == true) goto L40;
        return r2;
    L40:
        Set<Set<String>> r5 = Api30Compat.getConcurrentCameraIds(this.cameraManager.get());     // Catch: CameraAccessException -> L29
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L19;
        android.util.Log.d("CXCP", "Loaded ConcurrentCameraIdsSet " + r5);     // Catch: CameraAccessException -> L29
    L19:
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r5, 10));
        Iterator<T> r52 = r5.iterator();
    L21:
        if (r52.hasNext() == false) goto L28;
        Set r22 = (Set) r52.next();
        ArrayList r3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r22, 10));
        Iterator r23 = r22.iterator();
    L24:
        if (r23.hasNext() == false) goto L26;
        r3.add(CameraId.m227boximpl(CameraId.m228constructorimpl((String) r23.next())));
        goto L24
    L26:
        r0.add(CollectionsKt.toSet(r3));
        goto L21
    L28:
        return CollectionsKt.toSet(r0);
    L29:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L44;
        android.util.Log.w("CXCP", "Failed to query CameraManager#getConcurrentStreamingCameraIds", e);
        return null;
    L44:
        return null;
    L35:
        th = move-exception;
        throw th;
    L5:
        return SetsKt.emptySet();
    }

    public final Flow<List<CameraId>> getCameraIds() {
        return this.cameraIds;
    }

    /* renamed from: getOrInitializeDeviceSetupCompat-0r8Bogc, reason: not valid java name */
    public final Object m443getOrInitializeDeviceSetupCompat0r8Bogc(String r14, Continuation<? super CameraDeviceSetupCompat> r15) {
        if ((r15 instanceof Camera2DeviceCache$getOrInitializeDeviceSetupCompat$1) == false) goto L7;
        Camera2DeviceCache$getOrInitializeDeviceSetupCompat$1 r0 = (Camera2DeviceCache$getOrInitializeDeviceSetupCompat$1) r15;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r152 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Deferred<CameraDeviceSetupCompat> r142 = (Deferred) r0.L$1;
        String r02 = (String) r0.L$0;
        ResultKt.throwOnFailure(r152);
        Deferred<CameraDeviceSetupCompat> r22 = r142;
        r14 = r02;
    L29:
        CameraDeviceSetupCompat r153 = (CameraDeviceSetupCompat) r152;
        if (r153 == null) goto L32;
        return r153;
    L32:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L34;
        android.util.Log.d("CXCP", "Removing null CameraDeviceSetupCompat from cache for " + CameraId.m232toStringimpl(r14));
    L34:
        Object r13 = this.lock;
        monitor-enter(r13);
        this.cameraDeviceSetupCache.remove(CameraId.m227boximpl(r14), r22);     // Catch: Throwable -> L39
        monitor-exit(r13);
        return r153;
    L39:
        th = move-exception;
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r152);
        if (Build.VERSION.SDK_INT >= 35) goto L17;
        return null;
    L17:
        Object r154 = this.lock;
        monitor-enter(r154);
        Map<CameraId, Deferred<CameraDeviceSetupCompat>> r23 = this.cameraDeviceSetupCache;     // Catch: Throwable -> L22
        CameraId r5 = CameraId.m227boximpl(r14);     // Catch: Throwable -> L22
        Deferred<CameraDeviceSetupCompat> r6 = r23.get(r5);     // Catch: Throwable -> L22
        if (r6 != null) goto L24;
        r6 = BuildersKt.async$default(this.scope, this.threads.getBackgroundDispatcher(), null, new Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1(r14, this, null), 2, null);     // Catch: Throwable -> L22
        r23.put(r5, r6);     // Catch: Throwable -> L22
    L24:
        r22 = r6;     // Catch: Throwable -> L22
        monitor-exit(r154);
        r0.L$0 = r14;
        r0.L$1 = r22;
        r0.label = 1;
        r152 = r22.await(r0);
        if (r152 != r12) goto L29;
        return r12;
    L22:
        th = move-exception;
        throw th;
    L7:
        r0 = new Camera2DeviceCache$getOrInitializeDeviceSetupCompat$1(this, r15);
        goto L8
    }

    /* renamed from: getOrInitializeDeviceSetupWrapper-0r8Bogc, reason: not valid java name */
    public final Object m444getOrInitializeDeviceSetupWrapper0r8Bogc(String r14, Continuation<? super Camera2DeviceSetupWrapper> r15) {
        if ((r15 instanceof Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$1) == false) goto L7;
        Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$1 r0 = (Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$1) r15;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r152 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        Deferred<Camera2DeviceSetupWrapper> r142 = (Deferred) r0.L$1;
        String r02 = (String) r0.L$0;
        ResultKt.throwOnFailure(r152);
        Deferred<Camera2DeviceSetupWrapper> r22 = r142;
        r14 = r02;
    L26:
        Camera2DeviceSetupWrapper r153 = (Camera2DeviceSetupWrapper) r152;
        if (r153 == null) goto L29;
        return r153;
    L29:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L31;
        android.util.Log.d("CXCP", "Removing null camera2DeviceSetupWrapper from cache for " + CameraId.m232toStringimpl(r14));
    L31:
        Object r13 = this.lock;
        monitor-enter(r13);
        this.camera2DeviceSetupWrapperCache.remove(CameraId.m227boximpl(r14), r22);     // Catch: Throwable -> L36
        monitor-exit(r13);
        return r153;
    L36:
        th = move-exception;
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r152);
        Object r154 = this.lock;
        monitor-enter(r154);
        Map<CameraId, Deferred<Camera2DeviceSetupWrapper>> r23 = this.camera2DeviceSetupWrapperCache;     // Catch: Throwable -> L19
        CameraId r5 = CameraId.m227boximpl(r14);     // Catch: Throwable -> L19
        Deferred<Camera2DeviceSetupWrapper> r6 = r23.get(r5);     // Catch: Throwable -> L19
        if (r6 != null) goto L21;
        r6 = BuildersKt.async$default(this.scope, this.threads.getBackgroundDispatcher(), null, new Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1(r14, this, null), 2, null);     // Catch: Throwable -> L19
        r23.put(r5, r6);     // Catch: Throwable -> L19
    L21:
        r22 = r6;     // Catch: Throwable -> L19
        monitor-exit(r154);
        r0.L$0 = r14;
        r0.L$1 = r22;
        r0.label = 1;
        r152 = r22.await(r0);
        if (r152 != r12) goto L26;
        return r12;
    L19:
        th = move-exception;
        throw th;
    L7:
        r0 = new Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$1(this, r15);
        goto L8
    }

    public final void shutdown() {
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
    }
}
