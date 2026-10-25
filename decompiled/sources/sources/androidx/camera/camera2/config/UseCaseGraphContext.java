package androidx.camera.camera2.config;

import androidx.camera.camera2.adapter.CameraStateAdapter;
import androidx.camera.camera2.adapter.GraphStateToCameraStateAdapter;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.impl.DeferrableSurface;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001BW\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\u0002\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0011¢\u0006\u0004\b\u0019\u0010\u0013R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001aR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001bR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001cR&\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u001aR\"\u0010\u001f\u001a\u0010\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00030\u00030\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R'\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r0\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010 \u001a\u0004\b\"\u0010#R\u001b\u0010)\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\u001a\u0004\b%\u0010&*\u0004\b'\u0010(¨\u0006*"}, d2 = {"Landroidx/camera/camera2/config/UseCaseGraphContext;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljavax/inject/Provider;", "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraphProvider", "Landroidx/camera/camera2/adapter/CameraStateAdapter;", "cameraStateAdapter", "Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;", "graphStateToCameraStateAdapter", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream$Config;", "Landroidx/camera/core/impl/DeferrableSurface;", "streamConfigMapProvider", "Landroidx/camera/camera2/pipe/StreamId;", "defaultSurfaceToStreamMap", "<init>", "(Ljavax/inject/Provider;Landroidx/camera/camera2/adapter/CameraStateAdapter;Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;Ljavax/inject/Provider;Ljava/util/Map;)V", _UrlKt.FRAGMENT_ENCODE_SET, "closeGraph", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "deferrableSurfaces", _UrlKt.FRAGMENT_ENCODE_SET, "getStreamIdsFromSurfaces", "(Ljava/util/Collection;)Ljava/util/Set;", "configureCameraStateListener", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/adapter/CameraStateAdapter;", "Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;", "Lkotlin/Lazy;", "kotlin.jvm.PlatformType", "_graph", "Lkotlin/Lazy;", "surfaceToStreamMap$delegate", "getSurfaceToStreamMap", "()Ljava/util/Map;", "surfaceToStreamMap", "getGraph", "()Landroidx/camera/camera2/pipe/CameraGraph;", "getGraph$delegate", "(Landroidx/camera/camera2/config/UseCaseGraphContext;)Ljava/lang/Object;", "graph", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseCameraConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,261:1\n1869#2:262\n1870#2:264\n1#3:263\n216#4,2:265\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n*L\n230#1:262\n230#1:264\n213#1:265,2\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseGraphContext {
    private final Lazy<CameraGraph> _graph;
    private final Provider<CameraGraph> cameraGraphProvider;
    private final CameraStateAdapter cameraStateAdapter;
    private final GraphStateToCameraStateAdapter graphStateToCameraStateAdapter;
    private final Provider<Map<CameraStream.Config, DeferrableSurface>> streamConfigMapProvider;
    private final Lazy surfaceToStreamMap$delegate;

    public static Map $r8$lambda$F3E2j3PZZTemDi6FKnCj8rRWPTg(Map r4, UseCaseGraphContext r5) {
        if (r4 != null) goto L16;
        LinkedHashMap r42 = new LinkedHashMap();
        Iterator<Map.Entry<CameraStream.Config, DeferrableSurface>> r0 = r5.streamConfigMapProvider.get().entrySet().iterator();
    L5:
        if (r0.hasNext() == false) goto L10;
        Map.Entry<CameraStream.Config, DeferrableSurface> r1 = r0.next();
        CameraStream.Config r2 = r1.getKey();
        DeferrableSurface r12 = r1.getValue();
        CameraStream r22 = r5.getGraph().getStreams().get(r2);
        if (r22 == null) goto L5;
        r42.put(r12, StreamId.m401boximpl(r22.m241getIdptHMqGs()));
        goto L5
    L10:
        return MapsKt.toMap(r42);
    L16:
        return r4;
    }

    /* renamed from: $r8$lambda$FT3TLK_GDnmekd6PPvl-VIFH_J8, reason: not valid java name */
    public static CameraGraph m40$r8$lambda$FT3TLK_GDnmekd6PPvlVIFH_J8(UseCaseGraphContext r0) {
        return r0.cameraGraphProvider.get();
    }

    public UseCaseGraphContext(Provider<CameraGraph> r1, CameraStateAdapter r2, GraphStateToCameraStateAdapter r3, Provider<Map<CameraStream.Config, DeferrableSurface>> r4, Map<DeferrableSurface, StreamId> r5) {
        this.cameraGraphProvider = r1;
        this.cameraStateAdapter = r2;
        this.graphStateToCameraStateAdapter = r3;
        this.streamConfigMapProvider = r4;
        this._graph = LazyKt.lazy(new UseCaseGraphContext$$ExternalSyntheticLambda3(this));
        this.surfaceToStreamMap$delegate = LazyKt.lazy(new UseCaseGraphContext$$ExternalSyntheticLambda4(r5, this));
    }

    public /* synthetic */ UseCaseGraphContext(Provider r7, CameraStateAdapter r8, GraphStateToCameraStateAdapter r9, Provider r10, Map r11, int r12, DefaultConstructorMarker r13) {
        if ((r12 & 16) == 0) goto L5;
        r11 = null;
    L5:
        this(r7, r8, r9, r10, r11);
    }

    public final void closeGraph() {
        if (this._graph.isInitialized() == false) goto L6;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(getGraph());
        return;
    }

    public final void configureCameraStateListener() {
        this.graphStateToCameraStateAdapter.setCameraGraph(getGraph());
        this.cameraStateAdapter.onGraphUpdated(getGraph());
    }

    public final CameraGraph getGraph() {
        return this._graph.getValue();
    }

    public final Set<StreamId> getStreamIdsFromSurfaces(Collection<? extends DeferrableSurface> r4) {
        LinkedHashSet r0 = new LinkedHashSet();
        Iterator<T> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L8;
        DeferrableSurface r1 = (DeferrableSurface) r42.next();
        StreamId r12 = getSurfaceToStreamMap().get(r1);
        if (r12 == null) goto L4;
        r0.add(StreamId.m401boximpl(r12.m407unboximpl()));
        goto L4
    L8:
        return r0;
    }

    public final Map<DeferrableSurface, StreamId> getSurfaceToStreamMap() {
        return (Map) this.surfaceToStreamMap$delegate.getValue();
    }
}
