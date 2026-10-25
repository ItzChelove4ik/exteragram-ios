package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

/* loaded from: classes3.dex */
public class Bitmap2JpegBytes implements Operation<In, Packet<byte[]>> {

    public static class Api34Impl {
        public static boolean hasGainmap(Bitmap r0) {
            return r0.hasGainmap();
        }
    }

    public static abstract class In {
        public In() {
        }

        public static In of(Packet<Bitmap> r1, int r2) {
            return new AutoValue_Bitmap2JpegBytes_In(r1, r2);
        }

        public abstract int getJpegQuality();

        public abstract Packet<Bitmap> getPacket();
    }

    public Bitmap2JpegBytes() {
    }

    private static int getOutputFormat(Bitmap r2) {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return 256;
    L5:
        if (Api34Impl.hasGainmap(r2) == false) goto L10;
        return 4101;
    L10:
        return 256;
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public Packet<byte[]> apply2(In r12) {
        Packet<Bitmap> r11 = r12.getPacket();
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        r11.getData().compress(Bitmap.CompressFormat.JPEG, r12.getJpegQuality(), r0);
        byte[] r3 = r0.toByteArray();
        Exif r4 = r11.getExif();
        Objects.requireNonNull(r4);
        return Packet.of(r3, r4, getOutputFormat(r11.getData()), r11.getSize(), r11.getCropRect(), r11.getRotationDegrees(), r11.getSensorToBufferTransform(), r11.getCameraCaptureResult());
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ Packet<byte[]> apply(In r1) {
        return apply2(r1);
    }
}
