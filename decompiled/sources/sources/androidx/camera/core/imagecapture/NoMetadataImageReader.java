package androidx.camera.core.imagecapture;

import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.SettableImageProxy;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.camera.core.streamsharing.VirtualCameraCaptureResult;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public class NoMetadataImageReader implements ImageReaderProxy {
    private ProcessingRequest mPendingRequest;
    private final ImageReaderProxy mWrappedImageReader;

    public static /* synthetic */ void $r8$lambda$LXqiiRPZEz2sAIbggei9CwACMP4(NoMetadataImageReader r0, ImageReaderProxy.OnImageAvailableListener r1, ImageReaderProxy r2) {
        r0.getClass();
        r1.onImageAvailable(r0);
    }

    public NoMetadataImageReader(ImageReaderProxy r1) {
        this.mWrappedImageReader = r1;
    }

    private ImageProxy createImageProxyWithEmptyMetadata(ImageProxy r7) {
        if (r7 != null) goto L5;
        return null;
    L5:
        TagBundle r1 = TagBundle.emptyBundle();
        this.mPendingRequest = null;
        return new SettableImageProxy(r7, new Size(r7.getWidth(), r7.getHeight()), new CameraCaptureResultImageInfo(new VirtualCameraCaptureResult(r1, r7.getImageInfo().getTimestamp())));
    }

    public void acceptProcessingRequest(ProcessingRequest r3) {
        Preconditions.checkState(true, "Pending request should be null");
        this.mPendingRequest = r3;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireLatestImage() {
        return createImageProxyWithEmptyMetadata(this.mWrappedImageReader.acquireLatestImage());
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireNextImage() {
        return createImageProxyWithEmptyMetadata(this.mWrappedImageReader.acquireNextImage());
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        this.mWrappedImageReader.clearOnImageAvailableListener();
    }

    public void clearProcessingRequest() {
        this.mPendingRequest = null;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        this.mWrappedImageReader.close();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        return this.mWrappedImageReader.getHeight();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        return this.mWrappedImageReader.getImageFormat();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        return this.mWrappedImageReader.getMaxImages();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public Surface getSurface() {
        return this.mWrappedImageReader.getSurface();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        return this.mWrappedImageReader.getWidth();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener r3, Executor r4) {
        this.mWrappedImageReader.setOnImageAvailableListener(new NoMetadataImageReader$$ExternalSyntheticLambda0(this, r3), r4);
    }
}
