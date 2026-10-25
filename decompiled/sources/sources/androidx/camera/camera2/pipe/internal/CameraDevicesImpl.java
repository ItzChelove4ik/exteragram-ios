package androidx.camera.camera2.pipe.internal;

import android.os.Trace;
import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackendId;
import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u000b\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ%\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0018\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0015\u0018\u00010\u00152\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u001d\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0019\u001a\u00020\u000e2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001e¨\u0006\u001f"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraDevicesImpl;", "Landroidx/camera/camera2/pipe/CameraDevices;", "Landroidx/camera/camera2/pipe/CameraBackends;", "cameraBackends", "<init>", "(Landroidx/camera/camera2/pipe/CameraBackends;)V", "Landroidx/camera/camera2/pipe/CameraBackendId;", "cameraBackendId", "Landroidx/camera/camera2/pipe/CameraBackend;", "getCameraBackend-SeavPBo", "(Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraBackend;", "getCameraBackend", "Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "cameraIdsFlow-SeavPBo", "(Ljava/lang/String;)Lkotlinx/coroutines/flow/Flow;", "cameraIdsFlow", "awaitCameraIds-SeavPBo", "(Ljava/lang/String;)Ljava/util/List;", "awaitCameraIds", _UrlKt.FRAGMENT_ENCODE_SET, "awaitConcurrentCameraIds-SeavPBo", "(Ljava/lang/String;)Ljava/util/Set;", "awaitConcurrentCameraIds", "cameraId", "Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata-FpsL5FU", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata", "Landroidx/camera/camera2/pipe/CameraBackends;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraDevicesImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraDevicesImpl.kt\nandroidx/camera/camera2/pipe/internal/CameraDevicesImpl\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,159:1\n71#2,2:160\n71#2,2:162\n71#2,2:164\n71#2,2:166\n48#3,2:168\n71#3,4:170\n50#3:174\n52#3:176\n78#3,4:177\n1#4:175\n*S KotlinDebug\n*F\n+ 1 CameraDevicesImpl.kt\nandroidx/camera/camera2/pipe/internal/CameraDevicesImpl\n*L\n74#1:160,2\n83#1:162,2\n107#1:164,2\n119#1:166,2\n153#1:168,2\n153#1:170,4\n153#1:174\n153#1:176\n153#1:177,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraDevicesImpl implements CameraDevices {
    private final CameraBackends cameraBackends;

    public CameraDevicesImpl(CameraBackends r1) {
        this.cameraBackends = r1;
    }

    public static final /* synthetic */ CameraBackends access$getCameraBackends$p(CameraDevicesImpl r0) {
        return r0.cameraBackends;
    }

    /* renamed from: getCameraBackend-SeavPBo, reason: not valid java name */
    private final CameraBackend m552getCameraBackendSeavPBo(String r3) {
        Debug r1 = Debug.INSTANCE;
        Trace.beginSection("getCameraBackend");     // Catch: Throwable -> L12
        if (r3 != null) goto L6;
        r3 = access$getCameraBackends$p(this).getDefault().mo149getIdQwmhuAM();     // Catch: Throwable -> L12
    L6:
        CameraBackend r2 = access$getCameraBackends$p(this).mo158getSG3A4s8(r3);     // Catch: Throwable -> L12
        if (r2 == null) goto L11;
        Trace.endSection();
        return r2;
    L11:
        throw new IllegalStateException(("Failed to load CameraBackend " + CameraBackendId.m156toStringimpl(r3)).toString());     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CameraDevices
    /* renamed from: awaitCameraIds-SeavPBo */
    public List<CameraId> mo171awaitCameraIdsSeavPBo(String r3) {
        CameraBackend r2 = m552getCameraBackendSeavPBo(r3);
        List<CameraId> r32 = r2.awaitCameraIds();
        if (r32 == null) goto L5;
    L7:
        return r32;
    L5:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L7;
        android.util.Log.w("CXCP", "Failed to load cameraIds from " + CameraBackendId.m156toStringimpl(r2.mo149getIdQwmhuAM()));
        goto L7
    }

    @Override // androidx.camera.camera2.pipe.CameraDevices
    /* renamed from: awaitCameraMetadata-FpsL5FU */
    public CameraMetadata mo172awaitCameraMetadataFpsL5FU(String r3, String r4) {
        CameraBackend r2 = m552getCameraBackendSeavPBo(r4);
        CameraMetadata r42 = r2.mo148awaitCameraMetadataEfqyGwQ(r3);
        if (r42 == null) goto L5;
    L7:
        return r42;
    L5:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L7;
        android.util.Log.w("CXCP", "Failed to load metadata for " + CameraId.m232toStringimpl(r3) + " from " + CameraBackendId.m156toStringimpl(r2.mo149getIdQwmhuAM()));
        goto L7
    }

    @Override // androidx.camera.camera2.pipe.CameraDevices
    /* renamed from: awaitConcurrentCameraIds-SeavPBo */
    public Set<Set<CameraId>> mo173awaitConcurrentCameraIdsSeavPBo(String r1) {
        return m552getCameraBackendSeavPBo(r1).awaitConcurrentCameraIds();
    }

    @Override // androidx.camera.camera2.pipe.CameraDevices
    /* renamed from: cameraIdsFlow-SeavPBo */
    public Flow<List<CameraId>> mo174cameraIdsFlowSeavPBo(String r1) {
        return m552getCameraBackendSeavPBo(r1).getCameraIds();
    }
}
