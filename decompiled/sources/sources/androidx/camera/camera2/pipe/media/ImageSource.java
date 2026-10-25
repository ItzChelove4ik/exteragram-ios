package androidx.camera.camera2.pipe.media;

import android.view.Surface;
import androidx.camera.camera2.pipe.UnsafeWrapper;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003R\u0014\u0010\u0007\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\r\u001a\u0004\u0018\u00010\b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u000e8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/media/ImageSource;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Landroid/view/Surface;", "getSurface", "()Landroid/view/Surface;", "surface", "Landroidx/camera/camera2/pipe/media/ImageListener;", "getImageListener", "()Landroidx/camera/camera2/pipe/media/ImageListener;", "setImageListener", "(Landroidx/camera/camera2/pipe/media/ImageListener;)V", "imageListener", "Landroidx/camera/camera2/pipe/media/ExpectedOutputsListener;", "getExpectedOutputsListener", "()Landroidx/camera/camera2/pipe/media/ExpectedOutputsListener;", "setExpectedOutputsListener", "(Landroidx/camera/camera2/pipe/media/ExpectedOutputsListener;)V", "expectedOutputsListener", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ImageSource extends UnsafeWrapper, AutoCloseable {
    Surface getSurface();

    void setExpectedOutputsListener(ExpectedOutputsListener r1);

    void setImageListener(ImageListener r1);
}
