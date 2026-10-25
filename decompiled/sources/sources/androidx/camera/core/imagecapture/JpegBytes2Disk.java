package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.internal.compat.workaround.InvalidJpegDataParser;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes3.dex */
public class JpegBytes2Disk implements Operation<In, ImageCapture.OutputFileResults> {

    public static abstract class In {
        public abstract ImageCapture.OutputFileOptions getOutputFileOptions();

        public abstract Packet<byte[]> getPacket();
    }

    public JpegBytes2Disk() {
    }

    public static void writeBytesToFile(File r2, byte[] r3) {
        FileOutputStream r0 = new FileOutputStream(r2);     // Catch: IOException -> L12
        r0.write(r3, 0, new InvalidJpegDataParser().getValidDataLength(r3));     // Catch: Throwable -> L6
        r0.close();     // Catch: IOException -> L12
        return;
    L6:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L9
    L11:
        throw th;     // Catch: IOException -> L12
    L9:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L12
    L12:
        e = move-exception;
        throw new ImageCaptureException(1, "Failed to write to temp file", e);
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public ImageCapture.OutputFileResults apply2(In r3) {
        Packet<byte[]> r2 = r3.getPacket();
        r3.getOutputFileOptions();
        File r0 = FileUtil.createTempFile(null);
        writeBytesToFile(r0, r2.getData());
        Exif r1 = r2.getExif();
        Objects.requireNonNull(r1);
        FileUtil.updateFileExif(r0, r1, null, r2.getRotationDegrees());
        return new ImageCapture.OutputFileResults(FileUtil.moveFileToTarget(r0, null), 256);
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ ImageCapture.OutputFileResults apply(In r1) {
        return apply2(r1);
    }
}
