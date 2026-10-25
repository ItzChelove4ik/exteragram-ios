package androidx.camera.core.imagecapture;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.ProcessingNode;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import androidx.core.util.Preconditions;
import java.io.IOException;

/* loaded from: classes3.dex */
final class ProcessingInput2Packet implements Operation<ProcessingNode.InputPacket, Packet<ImageProxy>> {
    public ProcessingInput2Packet() {
    }

    private static Packet<ImageProxy> createPacket(ProcessingRequest r6, Exif r7, ImageProxy r8) {
        return Packet.of(r8, r7, r6.getCropRect(), r6.getRotationDegrees(), r6.getSensorToBufferTransform(), getCameraCaptureResult(r8));
    }

    private static Packet<ImageProxy> createPacketWithHalRotation(ProcessingRequest r9, Exif r10, ImageProxy r11) {
        Size r0 = new Size(r11.getWidth(), r11.getHeight());
        int r1 = r9.getRotationDegrees() - r10.getRotation();
        Size r4 = getRotatedSize(r1, r0);
        Matrix r02 = TransformUtils.getRectToRect(new RectF(0.0f, 0.0f, r0.getWidth(), r0.getHeight()), new RectF(0.0f, 0.0f, r4.getWidth(), r4.getHeight()), r1);
        return Packet.of(r11, r10, r4, getUpdatedCropRect(r9.getCropRect(), r02), r10.getRotation(), getUpdatedTransform(r9.getSensorToBufferTransform(), r02), getCameraCaptureResult(r11));
    }

    private static CameraCaptureResult getCameraCaptureResult(ImageProxy r1) {
        if ((r1.getImageInfo() instanceof CameraCaptureResultImageInfo) == false) goto L7;
        return ((CameraCaptureResultImageInfo) r1.getImageInfo()).getCameraCaptureResult();
    L7:
        return CameraCaptureResult.EmptyCameraCaptureResult.create();
    }

    private static Size getRotatedSize(int r1, Size r2) {
        if (TransformUtils.is90or270(TransformUtils.within360(r1)) == true) goto L5;
        return r2;
    L5:
        return new Size(r2.getHeight(), r2.getWidth());
    }

    private static Rect getUpdatedCropRect(Rect r1, Matrix r2) {
        RectF r0 = new RectF(r1);
        r2.mapRect(r0);
        r0.sort();
        Rect r12 = new Rect();
        r0.round(r12);
        return r12;
    }

    private static Matrix getUpdatedTransform(Matrix r1, Matrix r2) {
        Matrix r0 = new Matrix(r1);
        r0.postConcat(r2);
        return r0;
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public Packet<ImageProxy> apply2(ProcessingNode.InputPacket r4) {
        ImageProxy r3 = r4.getImageProxy();
        ProcessingRequest r42 = r4.getProcessingRequest();
        if (ImageUtil.isJpegFormats(r3.getFormat()) == true) goto L16;
        Exif r0 = null;
    L11:
        if (ImagePipeline.EXIF_ROTATION_AVAILABILITY.shouldUseExifOrientation(r3) == false) goto L15;
        Preconditions.checkNotNull(r0, "JPEG image must have exif.");
        return createPacketWithHalRotation(r42, r0, r3);
    L15:
        return createPacket(r42, r0, r3);
    L16:
        r0 = Exif.createFromImageProxy(r3);     // Catch: IOException -> L6
        r3.getPlanes()[0].getBuffer().rewind();     // Catch: IOException -> L6
    L6:
        e = move-exception;
        throw new ImageCaptureException(1, "Failed to extract EXIF data.", e);
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ Packet<ImageProxy> apply(ProcessingNode.InputPacket r1) {
        return apply2(r1);
    }
}
