package androidx.camera.camera2.pipe;

import android.os.Trace;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.config.CameraGraphConfigModule;
import androidx.camera.camera2.pipe.config.CameraPipeComponent;
import androidx.camera.camera2.pipe.core.Debug;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@kotlin.Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u00142\u0006\u0010\u0007\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010 \u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010\"\u001a\u00020.8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\"\u0010/¨\u00060"}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipeImpl;", "Landroidx/camera/camera2/pipe/CameraPipe;", "Landroidx/camera/camera2/pipe/config/CameraPipeComponent;", "component", "<init>", "(Landroidx/camera/camera2/pipe/config/CameraPipeComponent;)V", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "config", "Landroidx/camera/camera2/pipe/CameraGraphId;", "cameraGraphId", "Landroidx/camera/camera2/pipe/CameraGraph;", "createCameraGraphLocked", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/CameraGraphId;)Landroidx/camera/camera2/pipe/CameraGraph;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraBackend;", "getBackend", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;)Landroidx/camera/camera2/pipe/CameraBackend;", "createCameraGraph", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;)Landroidx/camera/camera2/pipe/CameraGraph;", "Landroidx/camera/camera2/pipe/CameraGraph$ConcurrentConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "createCameraGraphs", "(Landroidx/camera/camera2/pipe/CameraGraph$ConcurrentConfig;)Ljava/util/List;", "Landroidx/camera/camera2/pipe/CameraDevices;", "cameras", "()Landroidx/camera/camera2/pipe/CameraDevices;", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "cameraSurfaceManager", "()Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "Landroidx/camera/camera2/pipe/ConfigQueryResult;", "isConfigSupported-NpXggIU", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isConfigSupported", _UrlKt.FRAGMENT_ENCODE_SET, "shutdown", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/config/CameraPipeComponent;", _UrlKt.FRAGMENT_ENCODE_SET, "debugId", "I", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraPipe.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraPipe.kt\nandroidx/camera/camera2/pipe/CameraPipeImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,439:1\n1563#2:440\n1634#2,3:441\n1563#2:444\n1634#2,3:445\n1563#2:461\n1634#2,3:462\n1563#2:465\n1634#2,3:466\n48#3,2:448\n71#3,4:450\n50#3,3:454\n78#3,4:457\n48#3,2:469\n71#3,4:471\n50#3,3:475\n78#3,4:478\n71#4,2:482\n71#4,2:484\n*S KotlinDebug\n*F\n+ 1 CameraPipe.kt\nandroidx/camera/camera2/pipe/CameraPipeImpl\n*L\n280#1:440\n280#1:441,3\n284#1:444\n284#1:445,3\n317#1:461\n317#1:462,3\n321#1:465\n321#1:466,3\n292#1:448,2\n292#1:450,4\n292#1:454,3\n292#1:457,4\n332#1:469,2\n332#1:471,4\n332#1:475,3\n332#1:478,4\n415#1:482,2\n424#1:484,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraPipeImpl implements CameraPipe {
    private final CameraPipeComponent component;
    private final int debugId;
    private final Object lock;
    private boolean shutdown;

    public CameraPipeImpl(CameraPipeComponent r1) {
        this.component = r1;
        this.debugId = CameraPipeKt.getCameraPipeIds().incrementAndGet();
        this.lock = new Object();
    }

    public static final /* synthetic */ CameraPipeComponent access$getComponent$p(CameraPipeImpl r0) {
        return r0.component;
    }

    private final CameraGraph createCameraGraphLocked(CameraGraph.Config r3, CameraGraphId r4) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("CXCP#CameraGraph-" + CameraId.m232toStringimpl(r3.m200getCameraDz_R5H8()));     // Catch: Throwable -> L6
        CameraGraph r2 = access$getComponent$p(this).cameraGraphComponentBuilder().cameraGraphConfigModule(new CameraGraphConfigModule(r3, r4)).build().cameraGraph();     // Catch: Throwable -> L6
        Trace.endSection();
        return r2;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    private final CameraBackend getBackend(CameraGraph.Config r4) {
        Object r1 = this.lock;
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.shutdown == true) goto L23;
        CameraBackendFactory r2 = r4.getCustomCameraBackend();     // Catch: Throwable -> L9
        if (r2 == null) goto L11;
        CameraBackend r3 = r2.create(this.component.cameraContext());     // Catch: Throwable -> L9
    L20:
        monitor-exit(r1);
        return r3;
    L11:
        String r22 = r4.m201getCameraBackendIdAKmI2lo();     // Catch: Throwable -> L9
        CameraPipeComponent r32 = this.component;
        if (r22 == null) goto L19;
        r3 = r32.cameraBackends().mo158getSG3A4s8(r22);     // Catch: Throwable -> L9
        if (r3 != null) goto L20;
        throw new IllegalStateException(("Failed to initialize " + CameraBackendId.m156toStringimpl(r22) + " from " + r4).toString());     // Catch: Throwable -> L9
    L19:
        r3 = r32.cameraBackends().getDefault();     // Catch: Throwable -> L9
        goto L20
    L23:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L9
    }

    @Override // androidx.camera.camera2.pipe.CameraPipe
    public CameraSurfaceManager cameraSurfaceManager() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.shutdown == true) goto L12;
        CameraSurfaceManager r2 = this.component.cameraSurfaceManager();     // Catch: Throwable -> L9
        monitor-exit(r0);
        return r2;
    L12:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L9
    }

    @Override // androidx.camera.camera2.pipe.CameraPipe
    public CameraDevices cameras() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.shutdown == true) goto L12;
        CameraDevices r2 = this.component.cameras();     // Catch: Throwable -> L9
        monitor-exit(r0);
        return r2;
    L12:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L9
    }

    @Override // androidx.camera.camera2.pipe.CameraPipe
    public CameraGraph createCameraGraph(CameraGraph.Config r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.shutdown == true) goto L12;
        CameraGraph r2 = createCameraGraphLocked(r3, CameraGraphId.Companion.nextId());     // Catch: Throwable -> L9
        monitor-exit(r0);
        return r2;
    L12:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L9
    }

    @Override // androidx.camera.camera2.pipe.CameraPipe
    public List<CameraGraph> createCameraGraphs(CameraGraph.ConcurrentConfig r7) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.shutdown == true) goto L27;
        Map r1 = MapsKt.createMapBuilder();     // Catch: Throwable -> L10
        Iterator<CameraGraph.Config> r2 = r7.getGraphConfigs().iterator();     // Catch: Throwable -> L10
    L8:
        if (r2.hasNext() == false) goto L12;
        r1.put(r2.next(), CameraGraphId.Companion.nextId());     // Catch: Throwable -> L10
        goto L8
    L12:
        Map r12 = MapsKt.build(r1);     // Catch: Throwable -> L10
        List<CameraGraph.Config> r22 = r7.getGraphConfigs();     // Catch: Throwable -> L10
        ArrayList r3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r22, 10));     // Catch: Throwable -> L10
        Iterator<T> r23 = r22.iterator();     // Catch: Throwable -> L10
    L14:
        if (r23.hasNext() == false) goto L16;
        r3.add(CameraId.m227boximpl(((CameraGraph.Config) r23.next()).m200getCameraDz_R5H8()));     // Catch: Throwable -> L10
        goto L14
    L16:
        Set r24 = CollectionsKt.toSet(r3);     // Catch: Throwable -> L10
        ConcurrentCameraGraphs r32 = new ConcurrentCameraGraphs(CollectionsKt.toSet(r12.values()), r24);     // Catch: Throwable -> L10
        List<CameraGraph.Config> r72 = r7.getGraphConfigs();     // Catch: Throwable -> L10
        ArrayList r25 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r72, 10));     // Catch: Throwable -> L10
        Iterator<T> r73 = r72.iterator();     // Catch: Throwable -> L10
    L18:
        if (r73.hasNext() == false) goto L24;
        CameraGraph.Config r4 = (CameraGraph.Config) r73.next();     // Catch: Throwable -> L10
        r4.setConcurrentCameraGraphs$camera_camera2_pipe(r32);     // Catch: Throwable -> L10
        Object r5 = r12.get(r4);     // Catch: Throwable -> L10
        if (r5 == null) goto L23;
        r25.add(createCameraGraphLocked(r4, (CameraGraphId) r5));     // Catch: Throwable -> L10
        goto L18
    L23:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L10
    L24:
        monitor-exit(r0);
        return r25;
    L27:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L10
    }

    @Override // androidx.camera.camera2.pipe.CameraPipe
    /* renamed from: isConfigSupported-NpXggIU */
    public Object mo238isConfigSupportedNpXggIU(CameraGraph.Config r1, Continuation<? super ConfigQueryResult> r2) {
        CameraBackend r0 = getBackend(r1);
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L5:
        return r0.mo150isConfigSupportedNpXggIU(r1, r2);
    }

    @Override // androidx.camera.camera2.pipe.CameraPipe
    public void shutdown() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.shutdown == true) goto L12;
        this.component.cameraPipeLifetime().shutdown();     // Catch: Throwable -> L9
        this.shutdown = true;     // Catch: Throwable -> L9
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L12:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L9
    }

    public String toString() {
        return "CameraPipe-" + this.debugId;
    }
}
