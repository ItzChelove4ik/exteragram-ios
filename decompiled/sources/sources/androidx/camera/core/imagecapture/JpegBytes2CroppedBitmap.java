package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes3.dex */
final class JpegBytes2CroppedBitmap implements Operation<Packet<byte[]>, Packet<Bitmap>> {
    public JpegBytes2CroppedBitmap() {
    }

    private Bitmap createCroppedBitmap(byte[] r2, Rect r3) {
        BitmapRegionDecoder r1 = BitmapRegionDecoder.newInstance(r2, 0, r2.length, false);     // Catch: IOException -> L5
        return r1.decodeRegion(r3, new BitmapFactory.Options());
    L5:
        e = move-exception;
        throw new ImageCaptureException(1, "Failed to decode JPEG.", e);
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public Packet<Bitmap> apply2(Packet<byte[]> r9) {
        Rect r0 = r9.getCropRect();
        Bitmap r2 = createCroppedBitmap(r9.getData(), r0);
        Exif r3 = r9.getExif();
        Objects.requireNonNull(r3);
        return Packet.of(r2, r3, new Rect(0, 0, r2.getWidth(), r2.getHeight()), r9.getRotationDegrees(), TransformUtils.updateSensorToBufferTransform(r9.getSensorToBufferTransform(), r0), r9.getCameraCaptureResult());
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ Packet<Bitmap> apply(Packet<byte[]> r1) {
        return apply2(r1);
    }
}
