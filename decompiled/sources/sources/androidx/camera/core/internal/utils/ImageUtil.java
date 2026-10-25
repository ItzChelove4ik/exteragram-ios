package androidx.camera.core.internal.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Rational;
import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.utils.ExifData;
import androidx.camera.core.impl.utils.ExifOutputStream;
import androidx.core.util.Preconditions;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import com.sun.jna.Structure$$ExternalSyntheticBUOutline2;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class ImageUtil {

    public static final class CodecFailedException extends Exception {
        private final FailureType mFailureType;

        public enum FailureType extends Enum<FailureType> {
            private static final /* synthetic */ FailureType[] $VALUES = null;
            public static final FailureType DECODE_FAILED = null;
            public static final FailureType ENCODE_FAILED = null;
            public static final FailureType UNKNOWN = null;

            private static /* synthetic */ FailureType[] $values() {
                return new FailureType[]{ENCODE_FAILED, DECODE_FAILED, UNKNOWN};
            }

            static {
                ENCODE_FAILED = new FailureType("ENCODE_FAILED", 0);
                DECODE_FAILED = new FailureType("DECODE_FAILED", 1);
                UNKNOWN = new FailureType("UNKNOWN", 2);
                $VALUES = $values();
            }

            FailureType(String r1, int r2) {
            }

            public static FailureType valueOf(String r1) {
                return (FailureType) Enum.valueOf(FailureType.class, r1);
            }

            public static FailureType[] values() {
                return (FailureType[]) $VALUES.clone();
            }
        }

        public CodecFailedException(String r1, FailureType r2) {
            super(r1);
            this.mFailureType = r2;
        }
    }

    public static Bitmap createBitmapFromImageProxy(ImageProxy r2) {
        int r0 = r2.getFormat();
        if (r0 == 1) goto L18;
        if (r0 == 35) goto L16;
        if (r0 == 256) goto L14;
        if (r0 == 4101) goto L14;
        Structure$$ExternalSyntheticBUOutline2.m("Incorrect image format of the input image proxy: ", r2.getFormat(), ", only ImageFormat.YUV_420_888 and PixelFormat.RGBA_8888 are supported");
        return null;
    L14:
        return createBitmapFromJpegImage(r2);
    L16:
        return ImageProcessingUtil.convertYUVToBitmap(r2);
    L18:
        return createBitmapFromRgbaImage(r2);
    }

    private static Bitmap createBitmapFromJpegImage(ImageProxy r3) {
        byte[] r32 = jpegImageToJpegByteArray(r3);
        Bitmap r33 = BitmapFactory.decodeByteArray(r32, 0, r32.length, null);
        if (r33 == null) goto L5;
        return r33;
    L5:
        ByteString$$ExternalSyntheticBUOutline0.m("Decode jpeg byte array failed");
        return null;
    }

    private static Bitmap createBitmapFromRgbaImage(ImageProxy r3) {
        Bitmap r0 = Bitmap.createBitmap(r3.getWidth(), r3.getHeight(), Bitmap.Config.ARGB_8888);
        r3.getPlanes()[0].getBuffer().rewind();
        ImageProcessingUtil.copyByteBufferToBitmap(r0, r3.getPlanes()[0].getBuffer(), r3.getPlanes()[0].getRowStride());
        return r0;
    }

    public static ByteBuffer createDirectByteBuffer(Bitmap r2) {
        if (r2.getConfig() != Bitmap.Config.ARGB_8888) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "Only accept Bitmap with ARGB_8888 format for now.");
        ByteBuffer r02 = ByteBuffer.allocateDirect(r2.getAllocationByteCount());
        ImageProcessingUtil.copyBitmapToByteBuffer(r2, r02, r2.getRowBytes());
        r02.rewind();
        return r02;
    L5:
        r0 = false;
        goto L6
    }

    public static Rational getRotatedAspectRatio(int r1, Rational r2) {
        if (r1 == 90) goto L10;
        if (r1 == 270) goto L10;
        return new Rational(r2.getNumerator(), r2.getDenominator());
    L10:
        return inverseRational(r2);
    }

    private static Rational inverseRational(Rational r2) {
        if (r2 != null) goto L5;
        return r2;
    L5:
        return new Rational(r2.getDenominator(), r2.getNumerator());
    }

    public static boolean isJpegFormats(int r1) {
        if (r1 != 256) goto L5;
        return true;
    L5:
        if (r1 == 4101) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isRawFormats(int r1) {
        if (r1 != 32) goto L6;
        return true;
    L6:
        return false;
    }

    public static byte[] jpegImageToJpegByteArray(ImageProxy r1) {
        if (isJpegFormats(r1.getFormat()) == false) goto L6;
        ByteBuffer r12 = r1.getPlanes()[0].getBuffer();
        byte[] r0 = new byte[r12.capacity()];
        r12.rewind();
        r12.get(r0);
        return r0;
    L6:
        IntegerType$$ExternalSyntheticBUOutline0.m("Incorrect image format of the input image proxy: ", r1.getFormat());
        return null;
    }

    public static Bitmap rotateBitmap(Bitmap r7, int r8) {
        Matrix r5 = new Matrix();
        r5.postRotate(r8);
        return Bitmap.createBitmap(r7, 0, 0, r7.getWidth(), r7.getHeight(), r5, true);
    }

    public static byte[] yuvImageToJpegByteArray(ImageProxy r8, Rect r9, int r10, int r11) {
        if (r8.getFormat() != 35) goto L13;
        YuvImage r2 = new YuvImage(yuv_420_888toNv21(r8), 17, r8.getWidth(), r8.getHeight(), null);
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        ExifOutputStream r1 = new ExifOutputStream(r0, ExifData.create(r8, r11));
        if (r9 != null) goto L8;
        r9 = new Rect(0, 0, r8.getWidth(), r8.getHeight());
    L8:
        if (r2.compressToJpeg(r9, r10, r1) == false) goto L12;
        return r0.toByteArray();
    L12:
        throw new CodecFailedException("YuvImage failed to encode jpeg.", CodecFailedException.FailureType.ENCODE_FAILED);
    L13:
        IntegerType$$ExternalSyntheticBUOutline0.m("Incorrect image format of the input image proxy: ", r8.getFormat());
        return null;
    }

    public static byte[] yuv_420_888toNv21(ImageProxy r19) {
        ImageProxy.PlaneProxy r0 = r19.getPlanes()[0];
        ImageProxy.PlaneProxy r2 = r19.getPlanes()[1];
        ImageProxy.PlaneProxy r3 = r19.getPlanes()[2];
        ByteBuffer r5 = r0.getBuffer();
        ByteBuffer r6 = r2.getBuffer();
        ByteBuffer r7 = r3.getBuffer();
        r5.rewind();
        r6.rewind();
        r7.rewind();
        int r8 = r5.remaining();
        byte[] r9 = new byte[((r19.getWidth() * r19.getHeight()) / 2) + r8];
        int r10 = 0;
        int r11 = 0;
    L4:
        if (r10 >= r19.getHeight()) goto L6;
        r5.get(r9, r11, r19.getWidth());
        r11 = r11 + r19.getWidth();
        r5.position(Math.min(r8, (r5.position() - r19.getWidth()) + r0.getRowStride()));
        r10 = r10 + 1;
        goto L4
    L6:
        int r02 = r19.getHeight() / 2;
        int r52 = r19.getWidth() / 2;
        int r4 = r3.getRowStride();
        int r82 = r2.getRowStride();
        int r32 = r3.getPixelStride();
        int r22 = r2.getPixelStride();
        byte[] r102 = new byte[r4];
        byte[] r12 = new byte[r82];
        int r13 = 0;
    L7:
        if (r13 >= r02) goto L12;
        r7.get(r102, 0, Math.min(r4, r7.remaining()));
        r6.get(r12, 0, Math.min(r82, r6.remaining()));
        int r14 = 0;
        int r15 = 0;
        int r16 = 0;
    L9:
        if (r14 >= r52) goto L11;
        int r17 = r11 + 1;
        r9[r11] = r102[r15];
        r11 = r11 + 2;
        r9[r17] = r12[r16];
        r15 = r15 + r32;
        r16 = r16 + r22;
        r14 = r14 + 1;
        goto L9
    L11:
        r13 = r13 + 1;
        goto L7
    L12:
        return r9;
    }
}
