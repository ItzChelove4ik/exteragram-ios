package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\b`\u0018\u00002\u00020\u0001J'\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H&¢\u0006\u0004\b\b\u0010\tJ'\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0011H&¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH&¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0011H&¢\u0006\u0004\b\u001b\u0010\u001cø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CaptureCallback;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest;", "captureRequest", _UrlKt.FRAGMENT_ENCODE_SET, "captureFrameNumber", "captureTimestamp", _UrlKt.FRAGMENT_ENCODE_SET, "onCaptureStarted", "(Landroid/hardware/camera2/CaptureRequest;JJ)V", "Landroid/hardware/camera2/TotalCaptureResult;", "captureResult", "Landroidx/camera/camera2/pipe/FrameNumber;", "frameNumber", "onCaptureCompleted-rmrZIYk", "(Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;J)V", "onCaptureCompleted", _UrlKt.FRAGMENT_ENCODE_SET, "progress", "onCaptureProcessProgressed", "(Landroid/hardware/camera2/CaptureRequest;I)V", "onCaptureFailed-RuT0dZU", "(Landroid/hardware/camera2/CaptureRequest;J)V", "onCaptureFailed", "captureSequenceId", "onCaptureSequenceCompleted", "(IJ)V", "onCaptureSequenceAborted", "(I)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Camera2CaptureCallback {
    /* renamed from: onCaptureCompleted-rmrZIYk, reason: not valid java name */
    void mo437onCaptureCompletedrmrZIYk(CaptureRequest r1, TotalCaptureResult r2, long r3);

    /* renamed from: onCaptureFailed-RuT0dZU, reason: not valid java name */
    void mo438onCaptureFailedRuT0dZU(CaptureRequest r1, long r2);

    void onCaptureProcessProgressed(CaptureRequest r1, int r2);

    void onCaptureSequenceAborted(int r1);

    void onCaptureSequenceCompleted(int r1, long r2);

    void onCaptureStarted(CaptureRequest r1, long r2, long r4);
}
