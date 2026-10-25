package androidx.camera.core;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.Surface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.core.util.Preconditions;
import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;
import java.util.Locale;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public final class ImageProcessingUtil {
    private static int sImageCount;

    public enum Result extends Enum<Result> {
        private static final /* synthetic */ Result[] $VALUES = null;
        public static final Result ERROR_CONVERSION = null;
        public static final Result SUCCESS = null;
        public static final Result UNKNOWN = null;

        private static /* synthetic */ Result[] $values() {
            return new Result[]{UNKNOWN, SUCCESS, ERROR_CONVERSION};
        }

        static {
            UNKNOWN = new Result("UNKNOWN", 0);
            SUCCESS = new Result("SUCCESS", 1);
            ERROR_CONVERSION = new Result("ERROR_CONVERSION", 2);
            $VALUES = $values();
        }

        Result(String r1, int r2) {
        }

        public static Result valueOf(String r1) {
            return (Result) Enum.valueOf(Result.class, r1);
        }

        public static Result[] values() {
            return (Result[]) $VALUES.clone();
        }
    }

    public static /* synthetic */ void $r8$lambda$axt99y5JtrDZQNQirPB4gEFLUhQ(ImageProxy r0, ImageProxy r1, ImageProxy r2) {
        if (r0 == null) goto L6;
        if (r1 == null) goto L7;
        r1.close();
        return;
    L7:
        return;
    }

    static {
        System.loadLibrary("image_processing_util_jni");
    }

    public static ImageProxy convertJpegBytesToImage(ImageReaderProxy r2, byte[] r3) {
        if (r2.getImageFormat() != 256) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0);
        Preconditions.checkNotNull(r3);
        Surface r02 = r2.getSurface();
        Preconditions.checkNotNull(r02);
        if (nativeWriteJpegToSurface(r3, r02) == 0) goto L10;
        Logger.e("ImageProcessingUtil", "Failed to enqueue JPEG image.");
        return null;
    L10:
        ImageProxy r22 = r2.acquireLatestImage();
        if (r22 != null) goto L13;
        Logger.e("ImageProcessingUtil", "Failed to get acquire JPEG image.");
    L13:
        return r22;
    L5:
        r0 = false;
        goto L6
    }

    public static Bitmap convertYUVToBitmap(ImageProxy r15) {
        if (r15.getFormat() != 35) goto L9;
        int r13 = r15.getWidth();
        int r14 = r15.getHeight();
        int r4 = r15.getPlanes()[0].getRowStride();
        int r6 = r15.getPlanes()[1].getRowStride();
        int r8 = r15.getPlanes()[2].getRowStride();
        int r9 = r15.getPlanes()[0].getPixelStride();
        int r10 = r15.getPlanes()[1].getPixelStride();
        Bitmap r11 = Bitmap.createBitmap(r15.getWidth(), r15.getHeight(), Bitmap.Config.ARGB_8888);
        int r12 = r11.getRowBytes();
        if (nativeConvertAndroid420ToBitmap(r15.getPlanes()[0].getBuffer(), r4, r15.getPlanes()[1].getBuffer(), r6, r15.getPlanes()[2].getBuffer(), r8, r9, r10, r11, r12, r13, r14) != 0) goto L7;
        return r11;
    L7:
        ByteString$$ExternalSyntheticBUOutline0.m("YUV to RGB conversion failed");
        return null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("Input image format must be YUV_420_888");
        return null;
    }

    public static ImageProxy convertYUVToRGB(ImageProxy r5, ImageReaderProxy r6, ByteBuffer r7, int r8, boolean r9) {
        if (isSupportedYUVFormat(r5) == true) goto L6;
        Logger.e("ImageProcessingUtil", "Unsupported format for YUV to RGB");
        return null;
    L6:
        long r3 = System.currentTimeMillis();
        if (isSupportedRotationDegrees(r8) == true) goto L11;
        Logger.e("ImageProcessingUtil", "Unsupported rotation degrees for rotate RGB");
        return null;
    L11:
        if (convertYUVToRGBInternal(r5, r6.getSurface(), r7, r8, r9) != Result.ERROR_CONVERSION) goto L15;
        Logger.e("ImageProcessingUtil", "YUV to RGB conversion failure");
        return null;
    L15:
        if (Log.isLoggable("MH", 3) == false) goto L17;
        Logger.d("ImageProcessingUtil", String.format(Locale.US, "Image processing performance profiling, duration: [%d], image count: %d", new Object[]{Long.valueOf(System.currentTimeMillis() - r3), Integer.valueOf(sImageCount)}));
        sImageCount++;
    L17:
        ImageProxy r62 = r6.acquireLatestImage();
        if (r62 != null) goto L21;
        Logger.e("ImageProcessingUtil", "YUV to RGB acquireLatestImage failure");
        return null;
    L21:
        SingleCloseImageProxy r72 = new SingleCloseImageProxy(r62);
        r72.addOnImageCloseListener(new ImageProcessingUtil$$ExternalSyntheticLambda0(r62, r5));
        return r72;
    }

    private static Result convertYUVToRGBInternal(ImageProxy r17, Surface r18, ByteBuffer r19, int r20, boolean r21) {
        int r11 = r17.getWidth();
        int r12 = r17.getHeight();
        int r2 = r17.getPlanes()[0].getRowStride();
        int r4 = r17.getPlanes()[1].getRowStride();
        int r6 = r17.getPlanes()[2].getRowStride();
        int r7 = r17.getPlanes()[0].getPixelStride();
        int r8 = r17.getPlanes()[1].getPixelStride();
        if (r21 == false) goto L5;
        int r13 = r7;
    L6:
        if (r21 == false) goto L8;
        int r14 = r8;
    L9:
        if (r21 == false) goto L11;
        int r15 = r8;
    L13:
        if (nativeConvertAndroid420ToABGR(r17.getPlanes()[0].getBuffer(), r2, r17.getPlanes()[1].getBuffer(), r4, r17.getPlanes()[2].getBuffer(), r6, r7, r8, r18, r19, r11, r12, r13, r14, r15, r20) == 0) goto L17;
        return Result.ERROR_CONVERSION;
    L17:
        return Result.SUCCESS;
    L11:
        r15 = 0;
        goto L13
    L8:
        r14 = 0;
        goto L9
    L5:
        r13 = 0;
        goto L6
    }

    public static void copyBitmapToByteBuffer(Bitmap r7, ByteBuffer r8, int r9) {
        nativeCopyBetweenByteBufferAndBitmap(r7, r8, r7.getRowBytes(), r9, r7.getWidth(), r7.getHeight(), false);
    }

    public static void copyByteBufferToBitmap(Bitmap r7, ByteBuffer r8, int r9) {
        nativeCopyBetweenByteBufferAndBitmap(r7, r8, r9, r7.getRowBytes(), r7.getWidth(), r7.getHeight(), true);
    }

    private static boolean isSupportedRotationDegrees(int r1) {
        if (r1 != 0) goto L4;
        return true;
    L4:
        if (r1 != 90) goto L6;
        return true;
    L6:
        if (r1 != 180) goto L8;
        return true;
    L8:
        if (r1 == 270) goto L16;
        return false;
    L16:
        return true;
    }

    private static boolean isSupportedYUVFormat(ImageProxy r2) {
        if (r2.getFormat() == 35) goto L5;
        return false;
    L5:
        if (r2.getPlanes().length != 3) goto L10;
        return true;
    L10:
        return false;
    }

    private static native int nativeConvertAndroid420ToABGR(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, int r6, int r7, Surface r8, ByteBuffer r9, int r10, int r11, int r12, int r13, int r14, int r15);

    private static native int nativeConvertAndroid420ToBitmap(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, int r6, int r7, Bitmap r8, int r9, int r10, int r11);

    private static native int nativeCopyBetweenByteBufferAndBitmap(Bitmap r0, ByteBuffer r1, int r2, int r3, int r4, int r5, boolean r6);

    public static native int nativeGetYUVImageVUOff(ByteBuffer r0, ByteBuffer r1);

    public static native ByteBuffer nativeNewDirectByteBuffer(ByteBuffer r0, int r1, int r2);

    private static native int nativeRotateYUV(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, int r6, ByteBuffer r7, int r8, int r9, ByteBuffer r10, int r11, int r12, ByteBuffer r13, int r14, int r15, ByteBuffer r16, ByteBuffer r17, ByteBuffer r18, int r19, int r20, int r21);

    private static native int nativeShiftPixel(ByteBuffer r0, int r1, ByteBuffer r2, int r3, ByteBuffer r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12);

    private static native int nativeWriteJpegToSurface(byte[] r0, Surface r1);

    public static boolean writeJpegBytesToSurface(Surface r0, byte[] r1) {
        Preconditions.checkNotNull(r1);
        Preconditions.checkNotNull(r0);
        if (nativeWriteJpegToSurface(r1, r0) == 0) goto L6;
        Logger.e("ImageProcessingUtil", "Failed to enqueue JPEG image.");
        return false;
    L6:
        return true;
    }
}
