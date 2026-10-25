package androidx.camera.camera2.adapter;

import android.content.Context;
import android.os.Trace;
import android.util.Log;
import androidx.camera.camera2.config.CameraAppComponent;
import androidx.camera.camera2.config.CameraAppConfig;
import androidx.camera.camera2.config.CameraConfig;
import androidx.camera.camera2.config.DaggerCameraAppComponent;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraInteropStateCallbackRepository;
import androidx.camera.camera2.internal.CameraCompatibilityFilter;
import androidx.camera.camera2.internal.CameraSelectionOptimizer;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.SystemTimeSource;
import androidx.camera.camera2.pipe.core.Timestamps;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.Logger;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraThreadConfig;
import androidx.camera.core.impl.CameraUpdateException;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.internal.StreamSpecsCalculator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BG\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0016\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020 0(H\u0016J\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020 0(2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020 0(H\u0016J\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020 0(H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020 H\u0016J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0016J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020\"H\u0016J\u0014\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030(02H\u0016J\b\u00104\u001a\u00020&H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Landroidx/camera/camera2/adapter/CameraFactoryAdapter;", "Landroidx/camera/core/impl/CameraFactory;", "Landroidx/camera/core/impl/CameraFactory$Interrogator;", "lazyCameraPipe", "Lkotlin/Lazy;", "Landroidx/camera/camera2/pipe/CameraPipe;", "context", "Landroid/content/Context;", "threadConfig", "Landroidx/camera/core/impl/CameraThreadConfig;", "camera2InteropCallbacks", "Landroidx/camera/camera2/impl/CameraInteropStateCallbackRepository;", "availableCamerasSelector", "Landroidx/camera/core/CameraSelector;", "streamSpecsCalculator", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "<init>", "(Lkotlin/Lazy;Landroid/content/Context;Landroidx/camera/core/impl/CameraThreadConfig;Landroidx/camera/camera2/impl/CameraInteropStateCallbackRepository;Landroidx/camera/core/CameraSelector;Landroidx/camera/core/internal/StreamSpecsCalculator;Landroidx/camera/core/CameraXConfig;)V", "cameraCoordinator", "Landroidx/camera/camera2/adapter/CameraCoordinatorAdapter;", "pipeCameraPresenceObservable", "Landroidx/camera/camera2/adapter/PipeCameraPresenceSource;", "appComponent", "Landroidx/camera/camera2/config/CameraAppComponent;", "getAppComponent", "()Landroidx/camera/camera2/config/CameraAppComponent;", "appComponent$delegate", "Lkotlin/Lazy;", "availableCameraIds", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "lock", _UrlKt.FRAGMENT_ENCODE_SET, "isShutdown", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onCameraIdsUpdated", _UrlKt.FRAGMENT_ENCODE_SET, "cameraIds", _UrlKt.FRAGMENT_ENCODE_SET, "getAvailableCameraIds", "calculateAvailableCameraIds", "getCamera", "Landroidx/camera/core/impl/CameraInternal;", "cameraId", "getCameraCoordinator", "Landroidx/camera/core/concurrent/CameraCoordinator;", "getCameraManager", "getCameraPresenceSource", "Landroidx/camera/core/impl/Observable;", "Landroidx/camera/core/CameraIdentifier;", "shutdown", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraFactoryAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraFactoryAdapter.kt\nandroidx/camera/camera2/adapter/CameraFactoryAdapter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 4 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 5 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n+ 6 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n*L\n1#1,202:1\n1563#2:203\n1634#2,3:204\n85#3,4:207\n85#3,2:216\n88#3:223\n71#4,4:211\n78#4,4:224\n70#5:215\n83#5:218\n70#5:219\n74#5,2:221\n29#6:220\n*S KotlinDebug\n*F\n+ 1 CameraFactoryAdapter.kt\nandroidx/camera/camera2/adapter/CameraFactoryAdapter\n*L\n93#1:203\n93#1:204,3\n119#1:207,4\n81#1:216,2\n81#1:223\n65#1:211,4\n84#1:224,4\n67#1:215\n82#1:218\n82#1:219\n82#1:221,2\n82#1:220\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraFactoryAdapter implements CameraFactory, CameraFactory.Interrogator {
    private final Lazy appComponent$delegate;
    private Set<String> availableCameraIds;
    private final CameraSelector availableCamerasSelector;
    private final CameraCoordinatorAdapter cameraCoordinator;
    private final CameraXConfig cameraXConfig;
    private final AtomicBoolean isShutdown;
    private final Lazy<CameraPipe> lazyCameraPipe;
    private final Object lock;
    private final PipeCameraPresenceSource pipeCameraPresenceObservable;
    private final StreamSpecsCalculator streamSpecsCalculator;

    public static CameraAppComponent $r8$lambda$XdM0ky82a6o_01qy6QMeRMWkwsQ(Context r11, CameraThreadConfig r12, CameraFactoryAdapter r13, CameraInteropStateCallbackRepository r14) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("CameraFactoryAdapter#appComponent");
        SystemTimeSource r02 = new SystemTimeSource();
        Timestamps r1 = Timestamps.INSTANCE;
        long r15 = r02.mo506nowvQl9yQU();
        CameraAppComponent r112 = DaggerCameraAppComponent.builder().config(new CameraAppConfig(r11, r12, r13.lazyCameraPipe.getValue(), r14, r13.cameraCoordinator, r13.cameraXConfig)).build();
        Camera2Logger r122 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Created CameraFactoryAdapter in ".concat(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(DurationNs.m498constructorimpl(r02.mo506nowvQl9yQU() - r15) / 1000000.0d)}, 1))));
    L5:
        Trace.endSection();
        return r112;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CameraFactoryAdapter(Lazy<? extends CameraPipe> r1, Context r2, CameraThreadConfig r3, CameraInteropStateCallbackRepository r4, CameraSelector r5, StreamSpecsCalculator r6, CameraXConfig r7) {
        this.lazyCameraPipe = r1;
        this.availableCamerasSelector = r5;
        this.streamSpecsCalculator = r6;
        this.cameraXConfig = r7;
        this.cameraCoordinator = new CameraCoordinatorAdapter((CameraPipe) r1.getValue(), ((CameraPipe) r1.getValue()).cameras());
        this.appComponent$delegate = LazyKt.lazy(new CameraFactoryAdapter$$ExternalSyntheticLambda0(r2, r3, this, r4));
        this.availableCameraIds = SetsKt.emptySet();
        this.lock = new Object();
        this.isShutdown = new AtomicBoolean(false);
        List r12 = CameraDevices.m167awaitCameraIdsSeavPBo$default(getAppComponent().getCameraDevices(), null, 1, null);
        if (r12 == null) goto L8;
        List r13 = r12;
        List<String> r62 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r13, 10));
        Iterator r14 = r13.iterator();
    L6:
        if (r14.hasNext() == false) goto L9;
        r62.add(((CameraId) r14.next()).m233unboximpl());
    L9:
        this.pipeCameraPresenceObservable = new PipeCameraPresenceSource(CameraDevices.m170cameraIdsFlowSeavPBo$default(this.lazyCameraPipe.getValue().cameras(), null, 1, null), CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(r3.getCameraExecutor())), r62, r2);
        onCameraIdsUpdated(r62);
        return;
    L8:
        r62 = CollectionsKt.emptyList();
        goto L9
    }

    public static final /* synthetic */ Set access$getAvailableCameraIds$p(CameraFactoryAdapter r0) {
        return r0.availableCameraIds;
    }

    private final Set<String> calculateAvailableCameraIds(List<String> r5) {
        List<String> r52 = CameraSelectionOptimizer.Companion.getSelectedAvailableCameraIds(getAppComponent(), this.availableCamerasSelector, CollectionsKt.toList(r5), this.streamSpecsCalculator);
        return new LinkedHashSet(CameraCompatibilityFilter.getBackwardCompatibleCameraIds(getAppComponent().getCameraDevices(), r52));
    }

    private final CameraAppComponent getAppComponent() {
        return (CameraAppComponent) this.appComponent$delegate.getValue();
    }

    @Override // androidx.camera.core.impl.CameraFactory.Interrogator
    public List<String> getAvailableCameraIds(List<String> r2) {
        if (this.isShutdown.get() == false) goto L7;
        return CollectionsKt.emptyList();
    L7:
        return CollectionsKt.toList(calculateAvailableCameraIds(r2));
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public Set<String> getAvailableCameraIds() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.isShutdown.get() == false) goto L11;
        Set<String> r2 = SetsKt.emptySet();     // Catch: Throwable -> L9
        monitor-exit(r0);
        return r2;
    L11:
        LinkedHashSet r1 = new LinkedHashSet(this.availableCameraIds);     // Catch: Throwable -> L9
        monitor-exit(r0);
        return r1;
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public CameraInternal getCamera(String r4) {
        if (this.isShutdown.get() == true) goto L7;
        return getAppComponent().cameraBuilder().config(new CameraConfig(CameraId.m228constructorimpl(r4), null)).streamSpecsCalculator(this.streamSpecsCalculator).build().getCameraInternal();
    L7:
        throw new CameraUpdateException("CameraFactory has been shut down.");
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public CameraCoordinator getCameraCoordinator() {
        return this.cameraCoordinator;
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public Object getCameraManager() {
        return getAppComponent();
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public Observable<List<CameraIdentifier>> getCameraPresenceSource() {
        return this.pipeCameraPresenceObservable;
    }

    @Override // androidx.camera.core.impl.CameraPresenceMonitor
    public void onCameraIdsUpdated(List<String> r5) {
        if (this.isShutdown.get() == false) goto L5;
        return;
    L5:
        Set<String> r52 = calculateAvailableCameraIds(r5);
        Object r1 = this.lock;
        monitor-enter(r1);
    L18:
        th = move-exception;
        throw th;
    L8:
        if (this.isShutdown.get() == false) goto L12;
        monitor-exit(r1);
        return;
    L12:
        if (Intrinsics.areEqual(this.availableCameraIds, r52) == false) goto L15;
        monitor-exit(r1);
        return;
    L15:
        Camera2Logger r2 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L18
        if (Logger.isDebugEnabled("CXCP") == false) goto L20;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Updated available camera list: " + access$getAvailableCameraIds$p(this) + " -> " + r52);     // Catch: Throwable -> L18
    L20:
        this.availableCameraIds = r52;     // Catch: Throwable -> L18
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L18
        monitor-exit(r1);
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public void shutdown() {
        if (this.isShutdown.getAndSet(true) == true) goto L10;
        this.cameraCoordinator.shutdown();
        this.pipeCameraPresenceObservable.stopMonitoring();
        if (this.lazyCameraPipe.isInitialized() == false) goto L9;
        this.lazyCameraPipe.getValue().shutdown();
        return;
    L9:
        return;
    }
}
