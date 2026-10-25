package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.ImageReaderProxys;
import androidx.camera.core.SafeCloseImageReaderProxy;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class Image2Bitmap implements Operation<Packet<ImageProxy>, Bitmap> {
    public Image2Bitmap() {
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public Bitmap apply2(Packet<ImageProxy> r10) {
        SafeCloseImageReaderProxy r3 = null;
        int r4 = r10.getFormat();     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        if (r4 != 35) goto L30;
        ImageProxy r0 = r10.getData();     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        if ((r10.getRotationDegrees() % 180) == 0) goto L8;
        boolean r42 = true;
    L9:
        if (r42 == false) goto L15;
        int r6 = r0.getHeight();     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
    L16:
        if (r42 == false) goto L18;
        int r43 = r0.getWidth();     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
    L19:
        SafeCloseImageReaderProxy r7 = new SafeCloseImageReaderProxy(ImageReaderProxys.createIsolatedReader(r6, r43, 1, 2));     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        ImageProxy r44 = ImageProcessingUtil.convertYUVToRGB(r0, r7, ByteBuffer.allocateDirect((r0.getWidth() * r0.getHeight()) * 4), r10.getRotationDegrees(), false);     // Catch: Throwable -> L23 UnsupportedOperationException -> L25
        r0.close();     // Catch: Throwable -> L23 UnsupportedOperationException -> L25
        if (r44 == null) goto L28;
        Bitmap r02 = ImageUtil.createBitmapFromImageProxy(r44);     // Catch: Throwable -> L23 UnsupportedOperationException -> L25
        r44.close();     // Catch: Throwable -> L23 UnsupportedOperationException -> L25
        r3 = r7;
    L37:
        if (r3 == null) goto L39;
        r3.close();
    L39:
        return r02;
    L28:
        throw new ImageCaptureException(0, "Can't covert YUV to RGB", null);     // Catch: Throwable -> L23 UnsupportedOperationException -> L25
    L25:
        e = e;
    L41:
        if (r10.getFormat() != 35) goto L43;
        String r102 = "YUV";
    L45:
        throw new ImageCaptureException(0, "Can't convert " + r102 + " to bitmap", e);     // Catch: Throwable -> L11
    L43:
        r102 = "JPEG";
    L23:
        th = th;
        r3 = r7;
    L46:
        if (r3 == null) goto L48;
        r3.close();
    L48:
        throw th;
    L18:
        r43 = r0.getHeight();     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        goto L19
    L15:
        r6 = r0.getWidth();     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        goto L16
    L8:
        r42 = false;
        goto L9
    L30:
        if (r4 != 256) goto L32;
    L36:
        ImageProxy r03 = r10.getData();     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        Bitmap r45 = ImageUtil.createBitmapFromImageProxy(r03);     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        r03.close();     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        r02 = ImageUtil.rotateBitmap(r45, r10.getRotationDegrees());     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
        goto L37
    L32:
        if (r4 == 4101) goto L36;
        throw new IllegalArgumentException("Invalid postview image format : " + r10.getFormat());     // Catch: Throwable -> L11 UnsupportedOperationException -> L13
    L11:
        th = th;
    L13:
        e = e;
        goto L41
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ Bitmap apply(Packet<ImageProxy> r1) {
        return apply2(r1);
    }
}
