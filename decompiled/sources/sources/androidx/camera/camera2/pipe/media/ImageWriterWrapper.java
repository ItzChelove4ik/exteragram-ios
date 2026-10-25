package androidx.camera.camera2.pipe.media;

import androidx.camera.camera2.pipe.UnsafeWrapper;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/media/ImageWriterWrapper;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Landroidx/camera/camera2/pipe/media/ImageWrapper;", "image", _UrlKt.FRAGMENT_ENCODE_SET, "queueInputImage", "(Landroidx/camera/camera2/pipe/media/ImageWrapper;)Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ImageWriterWrapper extends UnsafeWrapper, AutoCloseable {
    boolean queueInputImage(ImageWrapper r1);
}
