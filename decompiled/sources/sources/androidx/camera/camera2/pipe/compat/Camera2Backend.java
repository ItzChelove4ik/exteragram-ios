package androidx.camera.camera2.pipe.compat;

import android.content.Context;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.os.Build;
import android.util.Size;
import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackendId;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.ConfigQueryResult;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.SurfaceTracker;
import androidx.camera.camera2.pipe.compat.AndroidOutputConfiguration;
import androidx.camera.camera2.pipe.compat.Camera2CameraController;
import androidx.camera.camera2.pipe.config.Camera2ControllerComponent;
import androidx.camera.camera2.pipe.config.Camera2ControllerConfig;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.GraphListener;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.flow.Flow;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B;\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\b\u0001\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0013H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u001a\u0018\u00010\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010!\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001f\u0010 J\u0018\u0010%\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\u0011H\u0096@¢\u0006\u0004\b#\u0010$J\u0015\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016¢\u0006\u0004\b(\u0010)J?\u00105\u001a\u0002042\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010/\u001a\u00020.2\u0006\u00101\u001a\u0002002\u0006\u00103\u001a\u000202H\u0016¢\u0006\u0004\b5\u00106J\u0017\u00108\u001a\u00020'2\u0006\u00107\u001a\u000204H\u0016¢\u0006\u0004\b8\u00109R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010:R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010;R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010<R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010=R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010>R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010?R\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010BR\u001a\u0010D\u001a\b\u0012\u0004\u0012\u0002040C8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010I\u001a\u00020F8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR \u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00130J8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010L¨\u0006N"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2Backend;", "Landroidx/camera/camera2/pipe/CameraBackend;", "Landroidx/camera/camera2/pipe/compat/Camera2CameraController$ShutdownListener;", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCache;", "camera2DeviceCache", "Landroidx/camera/camera2/pipe/compat/Camera2MetadataCache;", "camera2MetadataCache", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceManager;", "camera2DeviceManager", "Landroidx/camera/camera2/pipe/config/Camera2ControllerComponent$Builder;", "camera2CameraControllerComponent", "Landroid/content/Context;", "cameraPipeContext", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/compat/Camera2DeviceCache;Landroidx/camera/camera2/pipe/compat/Camera2MetadataCache;Landroidx/camera/camera2/pipe/compat/Camera2DeviceManager;Landroidx/camera/camera2/pipe/config/Camera2ControllerComponent$Builder;Landroid/content/Context;)V", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "graphConfig", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/OutputConfiguration;", "buildOutputConfiguration", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;)Ljava/util/List;", "Landroidx/camera/camera2/pipe/CameraId;", "awaitCameraIds", "()Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "awaitConcurrentCameraIds", "()Ljava/util/Set;", "cameraId", "Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata-EfqyGwQ", "(Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata", "Landroidx/camera/camera2/pipe/ConfigQueryResult;", "isConfigSupported-NpXggIU", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isConfigSupported", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "shutdownAsync", "()Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/CameraContext;", "cameraContext", "Landroidx/camera/camera2/pipe/CameraGraphId;", "graphId", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", "Landroidx/camera/camera2/pipe/StreamGraph;", "streamGraph", "Landroidx/camera/camera2/pipe/SurfaceTracker;", "surfaceTracker", "Landroidx/camera/camera2/pipe/CameraController;", "createCameraController", "(Landroidx/camera/camera2/pipe/CameraContext;Landroidx/camera/camera2/pipe/CameraGraphId;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/graph/GraphListener;Landroidx/camera/camera2/pipe/StreamGraph;Landroidx/camera/camera2/pipe/SurfaceTracker;)Landroidx/camera/camera2/pipe/CameraController;", "cameraController", "onControllerClosed", "(Landroidx/camera/camera2/pipe/CameraController;)V", "Landroidx/camera/camera2/pipe/core/Threads;", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCache;", "Landroidx/camera/camera2/pipe/compat/Camera2MetadataCache;", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceManager;", "Landroidx/camera/camera2/pipe/config/Camera2ControllerComponent$Builder;", "Landroid/content/Context;", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "activeCameraControllers", "Ljava/util/Set;", "Landroidx/camera/camera2/pipe/CameraBackendId;", "getId-QwmhuAM", "()Ljava/lang/String;", "id", "Lkotlinx/coroutines/flow/Flow;", "getCameraIds", "()Lkotlinx/coroutines/flow/Flow;", "cameraIds", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2Backend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2Backend.kt\nandroidx/camera/camera2/pipe/compat/Camera2Backend\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,239:1\n59#2,2:240\n50#2,2:243\n50#2,2:245\n1#3:242\n*S KotlinDebug\n*F\n+ 1 Camera2Backend.kt\nandroidx/camera/camera2/pipe/compat/Camera2Backend\n*L\n117#1:240,2\n184#1:243,2\n235#1:245,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2Backend implements CameraBackend, Camera2CameraController.ShutdownListener {
    private final Set<CameraController> activeCameraControllers;
    private final Camera2ControllerComponent.Builder camera2CameraControllerComponent;
    private final Camera2DeviceCache camera2DeviceCache;
    private final Camera2DeviceManager camera2DeviceManager;
    private final Camera2MetadataCache camera2MetadataCache;
    private final Context cameraPipeContext;
    private final Object lock;
    private final Threads threads;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2Backend$shutdownAsync$2", f = "Camera2Backend.kt", i = {0}, l = {190, 196}, m = "invokeSuspend", n = {"controller"}, s = {"L$1"}, v = 1)
    @SourceDebugExtension({"SMAP\nCamera2Backend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2Backend.kt\nandroidx/camera/camera2/pipe/compat/Camera2Backend$shutdownAsync$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,239:1\n1#2:240\n50#3,2:241\n71#3,2:243\n50#3,2:245\n*S KotlinDebug\n*F\n+ 1 Camera2Backend.kt\nandroidx/camera/camera2/pipe/compat/Camera2Backend$shutdownAsync$2\n*L\n189#1:241,2\n191#1:243,2\n195#1:245,2\n*E\n"})
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2Backend$shutdownAsync$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ Camera2Backend this$0;

        public AnonymousClass2(Camera2Backend r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x006e -> B:23:0x0071). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r9) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L10;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            ResultKt.throwOnFailure(r9);
        L35:
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            CameraController r12 = (CameraController) this.L$1;
            Iterator r5 = (Iterator) this.L$0;
            ResultKt.throwOnFailure(r9);
        L24:
            if (((Boolean) r9).booleanValue() == false) goto L26;
        L16:
            if (r5.hasNext() == false) goto L29;
            r12 = (CameraController) r5.next();
            if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L20;
            android.util.Log.d("CXCP", "Camera2Backend#shutdownAsync: Awaiting closure from " + r12);
        L20:
            this.L$0 = r5;
            this.L$1 = r12;
            this.label = 1;
            r9 = r12.awaitClosed(this);
            if (r9 != r0) goto L24;
        L33:
            return r0;
        L29:
            if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L31;
            android.util.Log.d("CXCP", "Camera2Backend#shutdownAsync: Closing all cameras (if any)");
        L31:
            Deferred<Unit> r92 = Camera2Backend.access$getCamera2DeviceManager$p(this.this$0).closeAll(true);
            this.L$0 = null;
            this.L$1 = null;
            this.label = 2;
            if (r92.await(this) != r0) goto L35;
        L26:
            if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L16;
            android.util.Log.w("CXCP", "Failed to await closure from " + r12 + '!');
            goto L16
        L10:
            ResultKt.throwOnFailure(r9);
            Object r93 = Camera2Backend.access$getLock$p(this.this$0);
            Camera2Backend r13 = this.this$0;
            monitor-enter(r93);
            Set r14 = Camera2Backend.access$getActiveCameraControllers$p(r13);     // Catch: Throwable -> L36
            monitor-exit(r93);
            r5 = r14.iterator();
        L36:
            th = move-exception;
            throw th;
        }
    }

    public Camera2Backend(Threads r1, Camera2DeviceCache r2, Camera2MetadataCache r3, Camera2DeviceManager r4, Camera2ControllerComponent.Builder r5, Context r6) {
        this.threads = r1;
        this.camera2DeviceCache = r2;
        this.camera2MetadataCache = r3;
        this.camera2DeviceManager = r4;
        this.camera2CameraControllerComponent = r5;
        this.cameraPipeContext = r6;
        this.lock = new Object();
        this.activeCameraControllers = new LinkedHashSet();
    }

    public static final /* synthetic */ Set access$getActiveCameraControllers$p(Camera2Backend r0) {
        return r0.activeCameraControllers;
    }

    public static final /* synthetic */ Camera2DeviceManager access$getCamera2DeviceManager$p(Camera2Backend r0) {
        return r0.camera2DeviceManager;
    }

    public static final /* synthetic */ Object access$getLock$p(Camera2Backend r0) {
        return r0.lock;
    }

    private final List<OutputConfiguration> buildOutputConfiguration(CameraGraph.Config r20) {
        LinkedHashSet r0 = new LinkedHashSet();
        Iterator<CameraStream.Config> r1 = r20.getStreams().iterator();
    L4:
        if (r1.hasNext() == false) goto L22;
        Iterator<OutputStream.Config> r2 = r1.next().getOutputs().iterator();
    L7:
        if (r2.hasNext() == false) goto L4;
        OutputStream.Config r3 = r2.next();
        AndroidOutputConfiguration.Companion r4 = AndroidOutputConfiguration.Companion;
        Integer r6 = Integer.valueOf(r3.m318getFormat8FPWQzE());
        OutputStream.OutputType r7 = OutputStream.OutputType.Companion.getSURFACE_DEFERRED_FOR_QUERY_ONLY$camera_camera2_pipe();
        OutputStream.MirrorMode r8 = r3.m319getMirrorModedO1_9xk();
        r3.m322getTimestampBasepcPfPbY();
        OutputStream.DynamicRangeProfile r10 = r3.m317getDynamicRangeProfileOoVcG5w();
        OutputStream.StreamUseCase r11 = r3.m320getStreamUseCase8x2ez34();
        List<Object> r12 = r3.getSensorPixelModes();
        Size r13 = r3.getSize();
        String r5 = r3.m316getCamera1LO98Z0();
        String r9 = r20.m200getCameraDz_R5H8();
        if (r5 != null) goto L11;
        boolean r52 = false;
    L12:
        if (r52 == true) goto L15;
        String r32 = r3.m316getCamera1LO98Z0();
    L16:
        OutputConfigurationWrapper r33 = AndroidOutputConfiguration.Companion.m421creategWWoySg$default(r4, null, r6, r7, r8, null, r10, r11, r12, r13, false, 0, r32, 1536, null);
        if (r33 == null) goto L7;
        OutputConfiguration r34 = (OutputConfiguration) r33.unwrapAs(Reflection.getOrCreateKotlinClass(OutputConfiguration.class));
        if (r34 == null) goto L7;
        r0.add(r34);
        goto L7
    L15:
        r32 = null;
        goto L16
    L11:
        r52 = CameraId.m230equalsimpl0(r5, r9);
        goto L12
    L22:
        return CollectionsKt.toList(r0);
    }

    @Override // androidx.camera.camera2.pipe.CameraBackend
    public List<CameraId> awaitCameraIds() {
        return this.camera2DeviceCache.awaitCameraIds();
    }

    @Override // androidx.camera.camera2.pipe.CameraBackend
    /* renamed from: awaitCameraMetadata-EfqyGwQ */
    public CameraMetadata mo148awaitCameraMetadataEfqyGwQ(String r1) {
        return this.camera2MetadataCache.mo456awaitCameraMetadataEfqyGwQ(r1);
    }

    @Override // androidx.camera.camera2.pipe.CameraBackend
    public Set<Set<CameraId>> awaitConcurrentCameraIds() {
        return this.camera2DeviceCache.awaitConcurrentCameraIds();
    }

    @Override // androidx.camera.camera2.pipe.CameraBackend
    public CameraController createCameraController(CameraContext r9, CameraGraphId r10, CameraGraph.Config r11, GraphListener r12, StreamGraph r13, SurfaceTracker r14) {
        CameraController r8 = this.camera2CameraControllerComponent.camera2ControllerConfig(new Camera2ControllerConfig(this, r10, r11, r12, (StreamGraphImpl) r13, r14, this)).build().cameraController();
        Object r92 = this.lock;
        monitor-enter(r92);
        this.activeCameraControllers.add(r8);     // Catch: Throwable -> L7
        monitor-exit(r92);
        return r8;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CameraBackend
    public Flow<List<CameraId>> getCameraIds() {
        return this.camera2DeviceCache.getCameraIds();
    }

    @Override // androidx.camera.camera2.pipe.CameraBackend
    /* renamed from: getId-QwmhuAM */
    public String mo149getIdQwmhuAM() {
        return CameraBackendId.m152constructorimpl("CXCP-Camera2");
    }

    @Override // androidx.camera.camera2.pipe.CameraBackend
    /* renamed from: isConfigSupported-NpXggIU */
    public Object mo150isConfigSupportedNpXggIU(CameraGraph.Config r9, Continuation<? super ConfigQueryResult> r10) {
        if ((r10 instanceof Camera2Backend$isConfigSupported$1) == false) goto L7;
        Camera2Backend$isConfigSupported$1 r0 = (Camera2Backend$isConfigSupported$1) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        Integer r4 = null;
        int r5 = 1;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        SessionConfiguration r8 = Camera2Backend$$ExternalSyntheticApiModelOutline0.m(r0.L$2);
        CameraDeviceSetupCompat r92 = (CameraDeviceSetupCompat) r0.L$1;
        CameraGraph.Config r02 = (CameraGraph.Config) r0.L$0;
        ResultKt.throwOnFailure(r102);
    L41:
        Camera2DeviceSetupWrapper r103 = (Camera2DeviceSetupWrapper) r102;
        if (r103 == null) goto L44;
        CaptureRequest.Builder r104 = r103.createCaptureRequest(r02.m205getSessionTemplatefGx8uWA());
    L45:
        if (r104 == null) goto L56;
        Iterator<Map.Entry<?, Object>> r03 = r02.getSessionParameters().entrySet().iterator();
    L48:
        if (r03.hasNext() == false) goto L55;
        Map.Entry<?, Object> r13 = r03.next();
        Object r22 = r13.getKey();
        Object r14 = r13.getValue();
        if ((r22 instanceof CaptureRequest.Key) == false) goto L52;
        CaptureRequest.Key r23 = (CaptureRequest.Key) r22;
    L53:
        if (r23 == null) goto L48;
        r104.set(r23, r14);
        goto L48
    L52:
        r23 = null;
        goto L53
    L55:
        Api28Compat.setSessionParameters(r8, r104.build());
    L56:
        if (r92 == null) goto L60;
        CameraDeviceSetupCompat.SupportQueryResult r82 = r92.isSessionConfigurationSupported(r8);
        if (r82 == null) goto L60;
        r4 = Boxing.boxInt(r82.getSupported());
    L60:
        if (r4 == null) goto L64;
        return ConfigQueryResult.m246boximpl(ConfigQueryResult.m247constructorimpl(r4.intValue()));
    L64:
        return ConfigQueryResult.m246boximpl(ConfigQueryResult.Companion.m254getUNKNOWNXp6DSB4());
    L44:
        r104 = null;
        goto L45
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        r9 = (CameraGraph.Config) r0.L$0;
        ResultKt.throwOnFailure(r102);
    L23:
        CameraDeviceSetupCompat r105 = (CameraDeviceSetupCompat) r102;
        int r24 = r9.m204getSessionMode2uNL3no();
        CameraGraph.OperatingMode.Companion r6 = CameraGraph.OperatingMode.Companion;
        if (CameraGraph.OperatingMode.m216equalsimpl0(r24, r6.m222getNORMAL2uNL3no()) == false) goto L27;
        r5 = 0;
    L37:
        SessionConfiguration r25 = Api35Compat.newSessionConfiguration(r5, buildOutputConfiguration(r9));
        Camera2DeviceCache r83 = this.camera2DeviceCache;
        String r52 = r9.m200getCameraDz_R5H8();
        r0.L$0 = r9;
        r0.L$1 = r105;
        r0.L$2 = r25;
        r0.label = 2;
        Object r84 = r83.m444getOrInitializeDeviceSetupWrapper0r8Bogc(r52, r0);
        if (r84 != r12) goto L40;
    L39:
        return r12;
    L40:
        r02 = r9;
        r92 = r105;
        r102 = r84;
        r8 = r25;
        goto L41
    L27:
        if (CameraGraph.OperatingMode.m216equalsimpl0(r24, r6.m221getHIGH_SPEED2uNL3no()) == true) goto L37;
        if (CameraGraph.OperatingMode.m216equalsimpl0(r24, r6.m220getEXTENSION2uNL3no()) == true) goto L32;
        r5 = r9.m204getSessionMode2uNL3no();
        goto L37
    L32:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L35;
        android.util.Log.i("CXCP", "Unsupported session mode: " + CameraGraph.OperatingMode.m218toStringimpl(r9.m204getSessionMode2uNL3no()));
    L35:
        return ConfigQueryResult.m246boximpl(ConfigQueryResult.Companion.m254getUNKNOWNXp6DSB4());
    L16:
        ResultKt.throwOnFailure(r102);
        if (Build.VERSION.SDK_INT < 35) goto L19;
        Camera2DeviceCache r106 = this.camera2DeviceCache;
        String r26 = r9.m200getCameraDz_R5H8();
        r0.L$0 = r9;
        r0.label = 1;
        r102 = r106.m443getOrInitializeDeviceSetupCompat0r8Bogc(r26, r0);
        if (r102 != r12) goto L23;
    L19:
        return ConfigQueryResult.m246boximpl(ConfigQueryResult.Companion.m254getUNKNOWNXp6DSB4());
    L7:
        r0 = new Camera2Backend$isConfigSupported$1(this, r10);
        goto L8
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2CameraController.ShutdownListener
    public void onControllerClosed(CameraController r4) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", r4 + " finalized");
    L5:
        Object r0 = this.lock;
        monitor-enter(r0);
        this.activeCameraControllers.remove(r4);     // Catch: Throwable -> L10
        monitor-exit(r0);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CameraBackend
    public Deferred<Unit> shutdownAsync() {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "Camera2Backend#shutdownAsync");
    L5:
        this.camera2DeviceCache.shutdown();
        return BuildersKt.async$default(this.threads.getCameraPipeScope(), null, null, new AnonymousClass2(this, null), 3, null);
    }
}
