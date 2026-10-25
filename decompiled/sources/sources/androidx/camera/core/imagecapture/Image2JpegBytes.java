package androidx.camera.core.imagecapture;

import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.internal.compat.workaround.JpegMetadataCorrector;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.io.ByteArrayInputStream;
import java.util.Objects;

/* loaded from: classes3.dex */
final class Image2JpegBytes implements Operation<In, Packet<byte[]>> {
    private final JpegMetadataCorrector mJpegMetadataCorrector;

    public static abstract class In {
        public In() {
        }

        public static In of(Packet<ImageProxy> r1, int r2) {
            return new AutoValue_Image2JpegBytes_In(r1, r2);
        }

        public abstract int getJpegQuality();

        public abstract Packet<ImageProxy> getPacket();
    }

    public Image2JpegBytes(Quirks r2) {
        this.mJpegMetadataCorrector = new JpegMetadataCorrector(r2);
    }

    private static Exif extractExif(byte[] r3) {
        return Exif.createFromInputStream(new ByteArrayInputStream(r3));
    L4:
        e = move-exception;
        throw new ImageCaptureException(0, "Failed to extract Exif from YUV-generated JPEG", e);
    }

    private Packet<byte[]> processJpegImage(In r10, int r11) {
        Packet<ImageProxy> r102 = r10.getPacket();
        byte[] r1 = this.mJpegMetadataCorrector.jpegImageToJpegByteArray(r102.getData());
        Exif r2 = r102.getExif();
        Objects.requireNonNull(r2);
        return Packet.of(r1, r2, r11, r102.getSize(), r102.getCropRect(), r102.getRotationDegrees(), r102.getSensorToBufferTransform(), r102.getCameraCaptureResult());
    }

    private Packet<byte[]> processYuvImage(In r12) {
        Packet<ImageProxy> r11 = r12.getPacket();
        ImageProxy r0 = r11.getData();
        Rect r1 = r11.getCropRect();
        byte[] r3 = ImageUtil.yuvImageToJpegByteArray(r0, r1, r12.getJpegQuality(), r11.getRotationDegrees());     // Catch: ImageUtil.CodecFailedException -> L6
        return Packet.of(r3, extractExif(r3), 256, new Size(r1.width(), r1.height()), new Rect(0, 0, r1.width(), r1.height()), r11.getRotationDegrees(), TransformUtils.updateSensorToBufferTransform(r11.getSensorToBufferTransform(), r1), r11.getCameraCaptureResult());
    L6:
        e = move-exception;
        throw new ImageCaptureException(1, "Failed to encode the image to JPEG.", e);
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public Packet<byte[]> apply2(In r4) {
        int r1 = r4.getPacket().getFormat();     // Catch: Throwable -> L12
        if (r1 != 35) goto L6;
        Packet<byte[]> r3 = processYuvImage(r4);     // Catch: Throwable -> L12
    L15:
        r4.getPacket().getData().close();
        return r3;
    L6:
        if (r1 != 256) goto L8;
    L14:
        r3 = processJpegImage(r4, r1);     // Catch: Throwable -> L12
        goto L15
    L8:
        if (r1 == 4101) goto L14;
        throw new IllegalArgumentException("Unexpected format: " + r1);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        r4.getPacket().getData().close();
        throw th;
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ Packet<byte[]> apply(In r1) {
        return apply2(r1);
    }
}
