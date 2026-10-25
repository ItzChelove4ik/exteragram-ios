package androidx.camera.core.processing;

import androidx.camera.core.ImageProcessor$Request;
import androidx.camera.core.ImageProxy;

/* loaded from: classes3.dex */
public class ImageProcessorRequest implements ImageProcessor$Request {
    private final ImageProxy mImageProxy;
    private final int mOutputFormat;

    public ImageProcessorRequest(ImageProxy r1, int r2) {
        this.mImageProxy = r1;
        this.mOutputFormat = r2;
    }
}
