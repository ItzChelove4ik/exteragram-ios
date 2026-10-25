package androidx.camera.core.processing;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.core.util.Preconditions;

/* loaded from: classes3.dex */
public abstract class Packet<T> {
    public Packet() {
    }

    public static Packet<Bitmap> of(Bitmap r9, Exif r10, Rect r11, int r12, Matrix r13, CameraCaptureResult r14) {
        return new AutoValue_Packet(r9, r10, 42, new Size(r9.getWidth(), r9.getHeight()), r11, r12, r13, r14);
    }

    public static Packet<ImageProxy> of(ImageProxy r7, Exif r8, Rect r9, int r10, Matrix r11, CameraCaptureResult r12) {
        return of(r7, r8, new Size(r7.getWidth(), r7.getHeight()), r9, r10, r11, r12);
    }

    public static Packet<ImageProxy> of(ImageProxy r10, Exif r11, Size r12, Rect r13, int r14, Matrix r15, CameraCaptureResult r16) {
        if (ImageUtil.isJpegFormats(r10.getFormat()) == false) goto L6;
        Preconditions.checkNotNull(r11, "JPEG image must have Exif.");
    L6:
        return new AutoValue_Packet(r10, r11, r10.getFormat(), r12, r13, r14, r15, r16);
    }

    public static Packet<byte[]> of(byte[] r9, Exif r10, int r11, Size r12, Rect r13, int r14, Matrix r15, CameraCaptureResult r16) {
        return new AutoValue_Packet(r9, r10, r11, r12, r13, r14, r15, r16);
    }

    public abstract CameraCaptureResult getCameraCaptureResult();

    public abstract Rect getCropRect();

    public abstract T getData();

    public abstract Exif getExif();

    public abstract int getFormat();

    public abstract int getRotationDegrees();

    public abstract Matrix getSensorToBufferTransform();

    public abstract Size getSize();
}
