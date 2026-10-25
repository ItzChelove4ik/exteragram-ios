package androidx.camera.camera2.pipe.graph;

import android.view.Surface;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.SurfaceTracker;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.camera2.pipe.media.ImageSource;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b\u000f\u0010\u0010J\"\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0015H\u0086\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020\u001bH\u0016J\b\u0010!\u001a\u00020\u001bH\u0016J\b\u0010\"\u001a\u00020\u001bH\u0016J\r\u0010#\u001a\u00020\u001bH\u0000¢\u0006\u0002\b$J\u0014\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00150\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00150\u00148\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R \u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u0015\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00148\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00020\u00188\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/camera/camera2/pipe/graph/SurfaceGraph;", "Landroidx/camera/camera2/pipe/SurfaceTracker;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "streamGraphImpl", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "cameraController", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/pipe/CameraController;", "surfaceManager", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "imageSources", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroidx/camera/camera2/pipe/media/ImageSource;", "<init>", "(Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Ljavax/inject/Provider;Landroidx/camera/camera2/pipe/CameraSurfaceManager;Ljava/util/Map;)V", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Surface;", "surfaceUsageMap", "shouldRegisterSurfaces", _UrlKt.FRAGMENT_ENCODE_SET, "closed", "set", _UrlKt.FRAGMENT_ENCODE_SET, "streamId", "surface", "set-NYG5g8E", "(ILandroid/view/Surface;)V", "unregisterAllSurfaces", "registerAllSurfaces", "close", "maybeUpdateSurfaces", "maybeUpdateSurfaces$camera_camera2_pipe", "buildSurfaceMap", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSurfaceGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SurfaceGraph.kt\nandroidx/camera/camera2/pipe/graph/SurfaceGraph\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,185:1\n415#2:186\n1252#3,4:187\n71#4,2:191\n59#4,2:193\n1#5:195\n*S KotlinDebug\n*F\n+ 1 SurfaceGraph.kt\nandroidx/camera/camera2/pipe/graph/SurfaceGraph\n*L\n49#1:186\n49#1:187,4\n71#1:191,2\n76#1:193,2\n*E\n"})
/* loaded from: classes3.dex */
public final class SurfaceGraph implements SurfaceTracker, AutoCloseable {
    private final Provider<CameraController> cameraController;
    private boolean closed;
    private final Map<StreamId, ImageSource> imageSources;
    private final Object lock;
    private boolean shouldRegisterSurfaces;
    private final StreamGraphImpl streamGraphImpl;
    private final CameraSurfaceManager surfaceManager;
    private final Map<StreamId, Surface> surfaceMap;
    private final Map<Surface, AutoCloseable> surfaceUsageMap;

    /* JADX WARN: Multi-variable type inference failed */
    public SurfaceGraph(StreamGraphImpl r1, Provider<CameraController> r2, CameraSurfaceManager r3, Map<StreamId, ? extends ImageSource> r4) {
        this.streamGraphImpl = r1;
        this.cameraController = r2;
        this.surfaceManager = r3;
        this.imageSources = r4;
        this.lock = new Object();
        LinkedHashMap r12 = new LinkedHashMap();
        Iterator r22 = r4.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        Map.Entry r32 = (Map.Entry) r22.next();
        r12.put(r32.getKey(), ((ImageSource) r32.getValue()).getSurface());
        goto L4
    L6:
        this.surfaceMap = r12;
        this.surfaceUsageMap = new LinkedHashMap();
        this.shouldRegisterSurfaces = true;
    }

    private final Map<StreamId, Surface> buildSurfaceMap() {
        Object r0 = this.lock;
        monitor-enter(r0);
        LinkedHashMap r1 = new LinkedHashMap();     // Catch: Throwable -> L17
        Iterator<StreamGraphImpl.OutputConfig> r2 = this.streamGraphImpl.getOutputConfigs$camera_camera2_pipe().iterator();     // Catch: Throwable -> L17
    L6:
        if (r2.hasNext() == false) goto L21;
        StreamGraphImpl.OutputConfig r3 = r2.next();     // Catch: Throwable -> L17
        Iterator<CameraStream> r4 = r3.getStreamBuilder$camera_camera2_pipe().iterator();     // Catch: Throwable -> L17
    L9:
        if (r4.hasNext() == false) goto L6;
        CameraStream r5 = r4.next();     // Catch: Throwable -> L17
        Surface r6 = this.surfaceMap.get(StreamId.m401boximpl(r5.m241getIdptHMqGs()));     // Catch: Throwable -> L17
        if (r6 == null) goto L13;
        r1.put(StreamId.m401boximpl(r5.m241getIdptHMqGs()), r6);     // Catch: Throwable -> L17
        goto L9
    L13:
        if (r3.getDeferrable() == true) goto L9;
        Map<StreamId, Surface> r8 = MapsKt.emptyMap();     // Catch: Throwable -> L17
        monitor-exit(r0);
        return r8;
    L21:
        monitor-exit(r0);
        return r1;
    L17:
        th = move-exception;
        throw th;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L16:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == false) goto L9;
        monitor-exit(r0);
        return;
    L9:
        this.closed = true;     // Catch: Throwable -> L16
        this.surfaceMap.clear();     // Catch: Throwable -> L16
        List r1 = CollectionsKt.toList(this.surfaceUsageMap.values());     // Catch: Throwable -> L16
        this.surfaceUsageMap.clear();     // Catch: Throwable -> L16
        monitor-exit(r0);
        Iterator r2 = r1.iterator();
    L13:
        if (r2.hasNext() == false) goto L15;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m((AutoCloseable) r2.next());
        goto L13
    }

    public final void maybeUpdateSurfaces$camera_camera2_pipe() {
        Map<StreamId, Surface> r0 = buildSurfaceMap();
        if (r0.isEmpty() == false) goto L5;
        return;
    L5:
        this.cameraController.get().updateSurfaceMap(r0);
    }

    @Override // androidx.camera.camera2.pipe.SurfaceTracker
    public void registerAllSurfaces() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == true) goto L16;
        Iterator<Surface> r1 = this.surfaceMap.values().iterator();     // Catch: Throwable -> L10
    L8:
        if (r1.hasNext() == false) goto L12;
        Surface r2 = r1.next();     // Catch: Throwable -> L10
        AutoCloseable r3 = this.surfaceManager.registerSurface$camera_camera2_pipe(r2);     // Catch: Throwable -> L10
        this.surfaceUsageMap.put(r2, r3);     // Catch: Throwable -> L10
        goto L8
    L12:
        this.shouldRegisterSurfaces = true;     // Catch: Throwable -> L10
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r0);
        return;
    L16:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L10
    }

    /* renamed from: set-NYG5g8E, reason: not valid java name */
    public final void m551setNYG5g8E(int r7, Surface r8) {
        if (this.imageSources.keySet().contains(StreamId.m401boximpl(r7)) == true) goto L47;
        Object r4 = this.lock;
        monitor-enter(r4);
    L13:
        th = move-exception;
        throw th;
    L7:
        if (this.closed == false) goto L18;
        if (r8 != null) goto L10;
    L15:
        monitor-exit(r4);
        return;
    L10:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L15;
        android.util.Log.w("CXCP", "Refusing to configure " + StreamId.m406toStringimpl(r7) + " with " + r8 + " after close!");     // Catch: Throwable -> L13
        goto L15
    L18:
        if (Log.INSTANCE.getINFO_LOGGABLE() == true) goto L20;
    L24:
        Map<StreamId, Surface> r1 = this.surfaceMap;
        if (r8 != null) goto L30;
        Surface r72 = r1.remove(StreamId.m401boximpl(r7));     // Catch: Throwable -> L13
        if (this.shouldRegisterSurfaces == false) goto L39;
        if (r72 == null) goto L39;
        AutoCloseable r73 = this.surfaceUsageMap.remove(r72);     // Catch: Throwable -> L13
    L40:
        monitor-exit(r4);
        maybeUpdateSurfaces$camera_camera2_pipe();
        if (r73 == null) goto L50;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r73);
        return;
    L50:
        return;
    L39:
        r73 = null;
        goto L40
    L30:
        Surface r12 = r1.get(StreamId.m401boximpl(r7));     // Catch: Throwable -> L13
        this.surfaceMap.put(StreamId.m401boximpl(r7), r8);     // Catch: Throwable -> L13
        if (this.shouldRegisterSurfaces == false) goto L39;
        if (Intrinsics.areEqual(r12, r8) == true) goto L39;
        if (this.surfaceUsageMap.containsKey(r8) == true) goto L38;
        r73 = (AutoCloseable) TypeIntrinsics.asMutableMap(this.surfaceUsageMap).remove(r12);     // Catch: Throwable -> L13
        this.surfaceUsageMap.put(r8, this.surfaceManager.registerSurface$camera_camera2_pipe(r8));     // Catch: Throwable -> L13
        goto L40
    L38:
        throw new IllegalStateException(("Surface (" + r8 + ") is already in use!").toString());     // Catch: Throwable -> L13
    L20:
        if (r8 == null) goto L22;
        String r13 = "Configured " + StreamId.m406toStringimpl(r7) + " with " + r8;     // Catch: Throwable -> L13
    L23:
        android.util.Log.i("CXCP", r13);     // Catch: Throwable -> L13
        goto L24
    L22:
        r13 = "Removed surface for " + StreamId.m406toStringimpl(r7);     // Catch: Throwable -> L13
        goto L23
    L47:
        StringBuilder r82 = new StringBuilder("Cannot configure surface for ");
        r82.append(StreamId.m406toStringimpl(r7));
        SurfaceGraph$$ExternalSyntheticBUOutline0.m(r82, ", it is permanently assigned to ", this.imageSources.get(StreamId.m401boximpl(r7)));
    }

    @Override // androidx.camera.camera2.pipe.SurfaceTracker
    public void unregisterAllSurfaces() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.shouldRegisterSurfaces = false;     // Catch: Throwable -> L12
        List r1 = CollectionsKt.toList(this.surfaceUsageMap.values());     // Catch: Throwable -> L12
        this.surfaceUsageMap.clear();     // Catch: Throwable -> L12
        monitor-exit(r0);
        Iterator r2 = r1.iterator();
    L9:
        if (r2.hasNext() == false) goto L11;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m((AutoCloseable) r2.next());
        goto L9
    L11:
        return;
    L12:
        th = move-exception;
        throw th;
    }
}
