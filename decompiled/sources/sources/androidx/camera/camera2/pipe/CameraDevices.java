package androidx.camera.camera2.pipe;

import java.util.List;
import java.util.Set;
import kotlinx.coroutines.flow.Flow;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@kotlin.Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\n\u0010\u000bJ)\u0010\u0010\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\r\u0018\u00010\r2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u00062\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0013\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/CameraDevices;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraBackendId;", "cameraBackendId", "Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "cameraIdsFlow-SeavPBo", "(Ljava/lang/String;)Lkotlinx/coroutines/flow/Flow;", "cameraIdsFlow", "awaitCameraIds-SeavPBo", "(Ljava/lang/String;)Ljava/util/List;", "awaitCameraIds", _UrlKt.FRAGMENT_ENCODE_SET, "awaitConcurrentCameraIds-SeavPBo", "(Ljava/lang/String;)Ljava/util/Set;", "awaitConcurrentCameraIds", "cameraId", "Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata-FpsL5FU", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraDevices {
    /* renamed from: awaitCameraIds-SeavPBo$default, reason: not valid java name */
    static /* synthetic */ List m167awaitCameraIdsSeavPBo$default(CameraDevices r0, String r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: awaitCameraIds-SeavPBo");
        return null;
    L4:
        if ((r2 & 1) == 0) goto L7;
        r1 = null;
    L7:
        return r0.mo171awaitCameraIdsSeavPBo(r1);
    }

    /* renamed from: awaitCameraMetadata-FpsL5FU$default, reason: not valid java name */
    static /* synthetic */ CameraMetadata m168awaitCameraMetadataFpsL5FU$default(CameraDevices r0, String r1, String r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: awaitCameraMetadata-FpsL5FU");
        return null;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = null;
    L7:
        return r0.mo172awaitCameraMetadataFpsL5FU(r1, r2);
    }

    /* renamed from: awaitConcurrentCameraIds-SeavPBo$default, reason: not valid java name */
    static /* synthetic */ Set m169awaitConcurrentCameraIdsSeavPBo$default(CameraDevices r0, String r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: awaitConcurrentCameraIds-SeavPBo");
        return null;
    L4:
        if ((r2 & 1) == 0) goto L7;
        r1 = null;
    L7:
        return r0.mo173awaitConcurrentCameraIdsSeavPBo(r1);
    }

    /* renamed from: cameraIdsFlow-SeavPBo$default, reason: not valid java name */
    static /* synthetic */ Flow m170cameraIdsFlowSeavPBo$default(CameraDevices r0, String r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: cameraIdsFlow-SeavPBo");
        return null;
    L4:
        if ((r2 & 1) == 0) goto L7;
        r1 = null;
    L7:
        return r0.mo174cameraIdsFlowSeavPBo(r1);
    }

    /* renamed from: awaitCameraIds-SeavPBo, reason: not valid java name */
    List<CameraId> mo171awaitCameraIdsSeavPBo(String r1);

    /* renamed from: awaitCameraMetadata-FpsL5FU, reason: not valid java name */
    CameraMetadata mo172awaitCameraMetadataFpsL5FU(String r1, String r2);

    /* renamed from: awaitConcurrentCameraIds-SeavPBo, reason: not valid java name */
    Set<Set<CameraId>> mo173awaitConcurrentCameraIdsSeavPBo(String r1);

    /* renamed from: cameraIdsFlow-SeavPBo, reason: not valid java name */
    Flow<List<CameraId>> mo174cameraIdsFlowSeavPBo(String r1);
}
