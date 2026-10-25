package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.ImageReaderProxys;
import androidx.camera.core.SafeCloseImageReaderProxy;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.util.Objects;

/* loaded from: classes3.dex */
public class JpegBytes2Image implements Operation<Packet<byte[]>, Packet<ImageProxy>> {
    public JpegBytes2Image() {
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public Packet<ImageProxy> apply2(Packet<byte[]> r8) {
        SafeCloseImageReaderProxy r7 = new SafeCloseImageReaderProxy(ImageReaderProxys.createIsolatedReader(r8.getSize().getWidth(), r8.getSize().getHeight(), 256, 2));
        ImageProxy r0 = ImageProcessingUtil.convertJpegBytesToImage(r7, r8.getData());
        r7.safeClose();
        Objects.requireNonNull(r0);
        Exif r2 = r8.getExif();
        Objects.requireNonNull(r2);
        return Packet.of(r0, r2, r8.getCropRect(), r8.getRotationDegrees(), r8.getSensorToBufferTransform(), r8.getCameraCaptureResult());
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ Packet<ImageProxy> apply(Packet<byte[]> r1) {
        return apply2(r1);
    }
}
