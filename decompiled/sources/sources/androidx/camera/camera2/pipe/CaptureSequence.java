package androidx.camera.camera2.pipe;

import androidx.camera.camera2.pipe.Request;
import java.util.List;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\bg\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0001\u0015R\u0014\u0010\u0006\u001a\u00020\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\nR\u001c\u0010\u0014\u001a\u00020\u000f8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CaptureSequence;", "TCaptureRequest", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getRepeating", "()Z", "repeating", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/RequestMetadata;", "getCaptureMetadataList", "()Ljava/util/List;", "captureMetadataList", "Landroidx/camera/camera2/pipe/Request$Listener;", "getListeners", "listeners", _UrlKt.FRAGMENT_ENCODE_SET, "getSequenceNumber", "()I", "setSequenceNumber", "(I)V", "sequenceNumber", "CaptureSequenceListener", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CaptureSequence<TCaptureRequest> {

    @kotlin.Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CaptureSequence$CaptureSequenceListener;", _UrlKt.FRAGMENT_ENCODE_SET, "onCaptureSequenceComplete", _UrlKt.FRAGMENT_ENCODE_SET, "captureSequence", "Landroidx/camera/camera2/pipe/CaptureSequence;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface CaptureSequenceListener {
        void onCaptureSequenceComplete(CaptureSequence<?> r1);
    }

    List<RequestMetadata> getCaptureMetadataList();

    List<Request.Listener> getListeners();

    boolean getRepeating();

    void setSequenceNumber(int r1);
}
