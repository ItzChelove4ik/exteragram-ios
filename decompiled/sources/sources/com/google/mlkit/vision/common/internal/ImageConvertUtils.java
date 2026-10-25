package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class ImageConvertUtils {
    private static final ImageConvertUtils zza = null;

    static {
        zza = new ImageConvertUtils();
    }

    private ImageConvertUtils() {
    }

    public static ImageConvertUtils getInstance() {
        return zza;
    }

    public static Bitmap yv12ToBitmap(ByteBuffer r1, int r2, int r3, int r4) {
        byte[] r12 = zzb(yv12ToNv21Buffer(r1, true).array(), r2, r3);
        Bitmap r13 = BitmapFactory.decodeByteArray(r12, 0, r12.length);
        return zza(r13, r4, r13.getWidth(), r13.getHeight());
    }

    public static ByteBuffer yv12ToNv21Buffer(ByteBuffer r6, boolean r7) {
        r6.rewind();
        int r0 = r6.limit();
        int r1 = r0 / 6;
        if (r7 == false) goto L5;
        ByteBuffer r72 = ByteBuffer.allocate(r0);
    L6:
        int r02 = 0;
        int r2 = 0;
    L7:
        int r3 = r1 * 4;
        if (r2 >= r3) goto L11;
        r72.put(r2, r6.get(r2));
        r2 = r2 + 1;
    L11:
        if (r02 >= (r1 + r1)) goto L13;
        r72.put(r3 + r02, r6.get((((r02 % 2) * r1) + r3) + (r02 / 2)));
        r02 = r02 + 1;
        goto L11
    L13:
        return r72;
    L5:
        r72 = ByteBuffer.allocateDirect(r0);
        goto L6
    }

    public static Bitmap zza(Bitmap r7, int r8, int r9, int r10) {
        if (r8 == 0) goto L4;
        Matrix r5 = new Matrix();
        r5.postRotate(r8);
        return Bitmap.createBitmap(r7, 0, 0, r9, r10, r5, true);
    L4:
        return Bitmap.createBitmap(r7, 0, 0, r9, r10);
    }

    private static byte[] zzb(byte[] r8, int r9, int r10) {
        YuvImage r2 = new YuvImage(r8, 17, r9, r10, null);
        ByteArrayOutputStream r82 = new ByteArrayOutputStream();     // Catch: IOException -> L15
        r2.compressToJpeg(new Rect(0, 0, r9, r10), 100, r82);     // Catch: Throwable -> L7
        byte[] r92 = r82.toByteArray();     // Catch: Throwable -> L7
        r82.close();     // Catch: IOException -> L15
        return r92;
    L7:
        th = move-exception;
        r82.close();     // Catch: Throwable -> L11
        throw th;     // Catch: IOException -> L15
    L11:
        th = move-exception;
        Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class}).invoke(th, new Object[]{th});     // Catch: Exception -> L18
        throw th;     // Catch: IOException -> L15
    L27:
        throw th;     // Catch: IOException -> L15
    L15:
        e = move-exception;
        Log.w("ImageConvertUtils", "Error closing ByteArrayOutputStream");
        throw new MlKitException("Image conversion error from NV21 format", 13, e);
    }

    private static final void zzc(Image.Plane r7, int r8, int r9, byte[] r10, int r11, int r12) {
        ByteBuffer r0 = r7.getBuffer();
        r0.rewind();
        int r1 = ((r0.limit() + r7.getRowStride()) - 1) / r7.getRowStride();
        if (r1 == 0) goto L11;
        int r82 = r8 / (r9 / r1);
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r1) goto L14;
        int r4 = 0;
        int r5 = r3;
    L8:
        if (r4 >= r82) goto L10;
        r10[r11] = r0.get(r5);
        r11 = r11 + r12;
        r5 = r5 + r7.getPixelStride();
        r4 = r4 + 1;
        goto L8
    L10:
        r3 = r3 + r7.getRowStride();
        r2 = r2 + 1;
        goto L6
    L14:
        return;
    }

    public byte[] byteBufferToByteArray(ByteBuffer r3) {
        if (r3.hasArray() == true) goto L5;
    L8:
        r3.rewind();
        int r2 = r3.limit();
        byte[] r0 = new byte[r2];
        r3.get(r0, 0, r2);
        return r0;
    L5:
        if (r3.arrayOffset() != 0) goto L8;
        return r3.array();
    }

    public Bitmap convertToUpRightBitmap(InputImage r4) {
        int r0 = r4.getFormat();
        if (r0 == (-1)) goto L19;
        if (r0 == 17) goto L17;
        if (r0 == 35) goto L15;
        if (r0 != 842094169) goto L13;
        return yv12ToBitmap((ByteBuffer) Preconditions.checkNotNull(r4.getByteBuffer()), r4.getWidth(), r4.getHeight(), r4.getRotationDegrees());
    L13:
        throw new MlKitException("Unsupported image format", 13);
    L15:
        return nv21ToBitmap(yuv420ThreePlanesToNV21((Image.Plane[]) Preconditions.checkNotNull(r4.getPlanes()), r4.getWidth(), r4.getHeight()), r4.getWidth(), r4.getHeight(), r4.getRotationDegrees());
    L17:
        return nv21ToBitmap((ByteBuffer) Preconditions.checkNotNull(r4.getByteBuffer()), r4.getWidth(), r4.getHeight(), r4.getRotationDegrees());
    L19:
        return zza((Bitmap) Preconditions.checkNotNull(r4.getBitmapInternal()), r4.getRotationDegrees(), r4.getWidth(), r4.getHeight());
    }

    public Bitmap nv21ToBitmap(ByteBuffer r1, int r2, int r3, int r4) {
        byte[] r0 = zzb(byteBufferToByteArray(r1), r2, r3);
        Bitmap r02 = BitmapFactory.decodeByteArray(r0, 0, r0.length);
        return zza(r02, r4, r02.getWidth(), r02.getHeight());
    }

    public ByteBuffer yuv420ThreePlanesToNV21(Image.Plane[] r12, int r13, int r14) {
        int r4 = r13 * r14;
        int r11 = r4 / 4;
        byte[] r8 = new byte[(r11 + r11) + r4];
        ByteBuffer r0 = r12[1].getBuffer();
        ByteBuffer r2 = r12[2].getBuffer();
        int r3 = r2.position();
        int r5 = r0.limit();
        r2.position(r3 + 1);
        r0.limit(r5 - 1);
        int r7 = (r4 + r4) / 4;
        if (r2.remaining() == (r7 - 2)) goto L5;
    L7:
        boolean r6 = false;
    L8:
        r2.position(r3);
        r0.limit(r5);
        if (r6 == false) goto L11;
        r12[0].getBuffer().get(r8, 0, r4);
        ByteBuffer r132 = r12[1].getBuffer();
        r12[2].getBuffer().get(r8, r4, 1);
        r132.get(r8, r4 + 1, r7 - 1);
    L13:
        return ByteBuffer.wrap(r8);
    L11:
        zzc(r12[0], r13, r14, r8, 0, 1);
        zzc(r12[1], r13, r14, r8, r4 + 1, 2);
        zzc(r12[2], r13, r14, r8, r4, 2);
        goto L13
    L5:
        if (r2.compareTo(r0) != 0) goto L7;
        r6 = true;
        goto L8
    }
}
