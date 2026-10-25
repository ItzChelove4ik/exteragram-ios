package androidx.camera.camera2.pipe;

import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/RequestFailure;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", _UrlKt.FRAGMENT_ENCODE_SET, "getReason", "()I", "reason", _UrlKt.FRAGMENT_ENCODE_SET, "getWasImageCaptured", "()Z", "wasImageCaptured", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface RequestFailure extends UnsafeWrapper {
    int getReason();

    boolean getWasImageCaptured();
}
