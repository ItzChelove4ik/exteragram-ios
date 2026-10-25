package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestFailure;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.core.ImageCaptureException;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlinx.coroutines.CompletableDeferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J'\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"androidx/camera/camera2/impl/CapturePipelineImpl$submitRequestInternal$requests$1$1", "Landroidx/camera/camera2/pipe/Request$Listener;", "onAborted", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/Request;", "onTotalCaptureResult", "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "totalCaptureResult", "Landroidx/camera/camera2/pipe/FrameInfo;", "onTotalCaptureResult-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "onFailed", "requestFailure", "Landroidx/camera/camera2/pipe/RequestFailure;", "onFailed-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/RequestFailure;)V", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$submitRequestInternal$requests$1$1 implements Request.Listener {
    final /* synthetic */ CompletableDeferred<Void> $completeSignal;

    public CapturePipelineImpl$submitRequestInternal$requests$1$1(CompletableDeferred<Void> r1) {
        this.$completeSignal = r1;
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onAborted(Request r4) {
        this.$completeSignal.completeExceptionally(new ImageCaptureException(3, "Capture request is cancelled because camera is closed", null));
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onFailed-CcXjc1I */
    public void mo14onFailedCcXjc1I(RequestMetadata r1, long r2, RequestFailure r4) {
        this.$completeSignal.completeExceptionally(new ImageCaptureException(2, "Capture request failed with reason " + r4.getReason(), null));
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onTotalCaptureResult-CcXjc1I */
    public void mo15onTotalCaptureResultCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
        this.$completeSignal.complete(null);
    }
}
