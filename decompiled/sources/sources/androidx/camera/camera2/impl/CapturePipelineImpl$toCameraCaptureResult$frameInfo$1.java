package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.RequestMetadata;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\n\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0014\u001a\u00020\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\u00020\u00188\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"androidx/camera/camera2/impl/CapturePipelineImpl$toCameraCaptureResult$frameInfo$1", "Landroidx/camera/camera2/pipe/FrameInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/FrameMetadata;", "frameMetadata", "Landroidx/camera/camera2/pipe/FrameMetadata;", "metadata", "getMetadata", "()Landroidx/camera/camera2/pipe/FrameMetadata;", "Landroidx/camera/camera2/pipe/CameraId;", "camera", "Ljava/lang/String;", "getCamera-Dz_R5H8", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/FrameNumber;", "frameNumber", "J", "getFrameNumber-Ugla2oM", "()J", "Landroidx/camera/camera2/pipe/RequestMetadata;", "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "getRequestMetadata", "()Landroidx/camera/camera2/pipe/RequestMetadata;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$toCameraCaptureResult$frameInfo$1 implements FrameInfo {
    private final String camera;
    private final FrameMetadata frameMetadata;
    private final long frameNumber;
    private final FrameMetadata metadata;
    private final RequestMetadata requestMetadata;

    public CapturePipelineImpl$toCameraCaptureResult$frameInfo$1(FrameMetadata r3, CapturePipelineImpl r4) {
        this.frameMetadata = r3;
        this.metadata = r3;
        this.camera = r3.mo265getCameraDz_R5H8();
        this.frameNumber = r3.mo266getFrameNumberUgla2oM();
        this.requestMetadata = CapturePipelineImpl.access$getEmptyRequestMetadata$p(r4);
    }

    @Override // androidx.camera.camera2.pipe.FrameInfo
    public FrameMetadata getMetadata() {
        return this.metadata;
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r1) {
        return null;
    }
}
