package androidx.camera.camera2.adapter;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Log;
import android.util.Size;
import androidx.camera.camera2.compat.StreamConfigurationMapCompat;
import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.OutputSizesCorrector;
import androidx.camera.camera2.config.CameraAppComponent;
import androidx.camera.camera2.config.CameraModule;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.FeatureCombinationQueryImpl;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.DoNotDisturbException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.Logger;
import androidx.camera.core.featuregroup.impl.FeatureCombinationQuery;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraUpdateException;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.StreamUseCase;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceStreamSpecQueryResult;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import androidx.core.util.Preconditions;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0014H\u0016J\"\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0014H\u0002J0\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0015\u0010!\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\bH\u0001¢\u0006\u0002\b#Jd\u0010$\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00142\u001c\u0010(\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030)\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00140\u000f2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\"H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/camera/camera2/adapter/CameraSurfaceAdapter;", "Landroidx/camera/core/impl/CameraDeviceSurfaceManager;", "context", "Landroid/content/Context;", "cameraComponent", _UrlKt.FRAGMENT_ENCODE_SET, "availableCameraIds", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroid/content/Context;Ljava/lang/Object;Ljava/util/Set;)V", "component", "Landroidx/camera/camera2/config/CameraAppComponent;", "lock", "supportedSurfaceCombinationMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/adapter/SupportedSurfaceCombination;", "onCamerasUpdated", _UrlKt.FRAGMENT_ENCODE_SET, "cameraIds", _UrlKt.FRAGMENT_ENCODE_SET, "buildSurfaceCombinations", "cameraIdsToBuild", "transformSurfaceConfig", "Landroidx/camera/core/impl/SurfaceConfig;", "cameraMode", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", "imageFormat", "size", "Landroid/util/Size;", "streamUseCase", "Landroidx/camera/core/impl/StreamUseCase;", "checkIfSupportedCombinationExist", _UrlKt.FRAGMENT_ENCODE_SET, "checkIfSupportedCombinationExist$camera_camera2", "getSuggestedStreamSpecs", "Landroidx/camera/core/impl/SurfaceStreamSpecQueryResult;", "existingSurfaces", "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "newUseCaseConfigsSupportedSizeMap", "Landroidx/camera/core/impl/UseCaseConfig;", "videoStabilization", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "hasVideoCapture", "isFeatureComboInvocation", "findMaxSupportedFrameRate", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraSurfaceAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraSurfaceAdapter.kt\nandroidx/camera/camera2/adapter/CameraSurfaceAdapter\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,263:1\n85#2,4:264\n85#2,4:268\n1#3:272\n*S KotlinDebug\n*F\n+ 1 CameraSurfaceAdapter.kt\nandroidx/camera/camera2/adapter/CameraSurfaceAdapter\n*L\n88#1:264,4\n110#1:268,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraSurfaceAdapter implements CameraDeviceSurfaceManager {
    private final CameraAppComponent component;
    private final Context context;
    private final Object lock;
    private Map<String, SupportedSurfaceCombination> supportedSurfaceCombinationMap;

    public CameraSurfaceAdapter(Context r1, Object r2, Set<String> r3) {
        this.context = r1;
        this.component = (CameraAppComponent) r2;
        this.lock = new Object();
        this.supportedSurfaceCombinationMap = MapsKt.emptyMap();
        onCamerasUpdated(CollectionsKt.toList(r3));     // Catch: CameraUpdateException -> L5
        return;
    L5:
        e = move-exception;
        throw new InitializationException(e);
    }

    private final Map<String, SupportedSurfaceCombination> buildSurfaceCombinations(List<String> r10) {
        LinkedHashMap r0 = new LinkedHashMap();
        if (r10.isEmpty() == false) goto L24;
    L17:
        return r0;
    L24:
        Iterator<String> r102 = r10.iterator();     // Catch: Exception -> L18 DoNotDisturbException -> L21
    L6:
        if (r102.hasNext() == false) goto L17;
        String r1 = r102.next();     // Catch: Exception -> L18 DoNotDisturbException -> L21
        CameraMetadata r2 = CameraDevices.m168awaitCameraMetadataFpsL5FU$default(this.component.getCameraDevices(), CameraId.m228constructorimpl(r1), null, 2, null);     // Catch: Exception -> L18 DoNotDisturbException -> L21
        if (r2 == null) goto L6;
        StreamConfigurationMap r3 = (StreamConfigurationMap) r2.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);     // Catch: Exception -> L18 DoNotDisturbException -> L21
        CameraQuirks r4 = new CameraQuirks(r2, new StreamConfigurationMapCompat(r3, new OutputSizesCorrector(r2, r3)));     // Catch: Exception -> L18 DoNotDisturbException -> L21
        Context r5 = this.context;     // Catch: Exception -> L18 DoNotDisturbException -> L21
        EncoderProfilesProvider r6 = CameraModule.Companion.provideEncoderProfilesProvider(r1, r4);     // Catch: Exception -> L18 DoNotDisturbException -> L21
        if (Build.VERSION.SDK_INT < 35) goto L14;
        FeatureCombinationQuery r7 = new FeatureCombinationQueryImpl(r2, this.component.getCameraPipe(), r4);     // Catch: Exception -> L18 DoNotDisturbException -> L21
    L15:
        r0.put(r1, new SupportedSurfaceCombination(r5, r2, r6, r7));     // Catch: Exception -> L18 DoNotDisturbException -> L21
        goto L6
    L14:
        r7 = FeatureCombinationQuery.NO_OP_FEATURE_COMBINATION_QUERY;     // Catch: Exception -> L18 DoNotDisturbException -> L21
        goto L15
    L21:
        e = move-exception;
        throw new CameraUpdateException("Failed to query camera metadata", e);
    L18:
        e = move-exception;
        throw new CameraUpdateException("Failed to build surface combinations", e);
    }

    public final boolean checkIfSupportedCombinationExist$camera_camera2(String r1) {
        return this.supportedSurfaceCombinationMap.containsKey(r1);
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager
    public SurfaceStreamSpecQueryResult getSuggestedStreamSpecs(int r11, String r12, List<? extends AttachedSurfaceInfo> r13, Map<UseCaseConfig<?>, ? extends List<Size>> r14, VideoStabilization r15, boolean r16, boolean r17, boolean r18) {
        Preconditions.checkArgument(checkIfSupportedCombinationExist$camera_camera2(r12), "No such camera id in supported combination list: " + r12);
        Object r1 = this.lock;
        monitor-enter(r1);
        SupportedSurfaceCombination r2 = this.supportedSurfaceCombinationMap.get(r12);     // Catch: Throwable -> L11
        monitor-exit(r1);
        if (r2 != null) goto L8;
        Native$$ExternalSyntheticBUOutline5.m("No such camera id in supported combination list: ", r12);
        return null;
    L8:
        return r2.getSuggestedStreamSpecifications(r11, r13, r14, r15, r16, r17, r18);
    L11:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.InternalCameraPresenceListener
    public void onCamerasUpdated(List<String> r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
        List<String> r1 = CollectionsKt.minus(r6, this.supportedSurfaceCombinationMap.keySet());     // Catch: Throwable -> L29
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L29
        monitor-exit(r0);
        if (r1.isEmpty() == true) goto L11;
        Camera2Logger r02 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L11;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Creating new surface combinations for: " + r1);
    L11:
        Map<String, SupportedSurfaceCombination> r03 = buildSurfaceCombinations(r1);
        Object r12 = this.lock;
        monitor-enter(r12);
        LinkedHashMap r22 = new LinkedHashMap();     // Catch: Throwable -> L19
        Iterator<String> r62 = r6.iterator();     // Catch: Throwable -> L19
    L15:
        if (r62.hasNext() == false) goto L21;
        String r3 = r62.next();     // Catch: Throwable -> L19
        if (this.supportedSurfaceCombinationMap.containsKey(r3) == false) goto L15;
        r22.put(r3, this.supportedSurfaceCombinationMap.get(r3));     // Catch: Throwable -> L19
        goto L15
    L21:
        r22.putAll(r03);     // Catch: Throwable -> L19
        this.supportedSurfaceCombinationMap = r22;     // Catch: Throwable -> L19
        Camera2Logger r5 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L19
        if (Logger.isDebugEnabled("CXCP") == false) goto L24;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Committed new surface combination map. Total cameras: " + r22.size());     // Catch: Throwable -> L19
    L24:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L19
        monitor-exit(r12);
        return;
    L19:
        th = move-exception;
        throw th;
    L29:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager
    public SurfaceConfig transformSurfaceConfig(int r4, String r5, int r6, Size r7, StreamUseCase r8) {
        Preconditions.checkArgument(checkIfSupportedCombinationExist$camera_camera2(r5), "No such camera id in supported combination list: " + r5);
        Object r0 = this.lock;
        monitor-enter(r0);
        SupportedSurfaceCombination r3 = this.supportedSurfaceCombinationMap.get(r5);     // Catch: Throwable -> L11
        monitor-exit(r0);
        if (r3 != null) goto L8;
        Native$$ExternalSyntheticBUOutline5.m("No such camera id in supported combination list: ", r5);
        return null;
    L8:
        return r3.transformSurfaceConfig(r4, r6, r7, r8);
    L11:
        th = move-exception;
        throw th;
    }
}
