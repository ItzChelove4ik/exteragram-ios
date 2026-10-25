package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.media.Image;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class ImageUtils {
    private static final GmsLogger zza = null;
    private static final ImageUtils zzb = null;

    static {
        zza = new GmsLogger("MLKitImageUtils", _UrlKt.FRAGMENT_ENCODE_SET);
        zzb = new ImageUtils();
    }

    private ImageUtils() {
    }

    public static ImageUtils getInstance() {
        return zzb;
    }

    public IObjectWrapper getImageDataWrapper(InputImage r3) {
        int r2 = r3.getFormat();
        if (r2 == (-1)) goto L18;
        if (r2 == 17) goto L16;
        if (r2 == 35) goto L14;
        if (r2 == 842094169) goto L16;
        throw new MlKitException("Unsupported image format: " + r3.getFormat(), 3);
    L14:
        return ObjectWrapper.wrap(r3.getMediaImage());
    L16:
        return ObjectWrapper.wrap((ByteBuffer) Preconditions.checkNotNull(r3.getByteBuffer()));
    L18:
        return ObjectWrapper.wrap((Bitmap) Preconditions.checkNotNull(r3.getBitmapInternal()));
    }

    public int getMobileVisionImageFormat(InputImage r1) {
        return r1.getFormat();
    }

    public int getMobileVisionImageSize(InputImage r3) {
        if (r3.getFormat() != (-1)) goto L7;
        return ((Bitmap) Preconditions.checkNotNull(r3.getBitmapInternal())).getAllocationByteCount();
    L7:
        if (r3.getFormat() == 17) goto L17;
        if (r3.getFormat() == 842094169) goto L17;
        if (r3.getFormat() == 35) goto L15;
        return 0;
    L15:
        return (((Image.Plane[]) Preconditions.checkNotNull(r3.getPlanes()))[0].getBuffer().limit() * 3) / 2;
    L17:
        return ((ByteBuffer) Preconditions.checkNotNull(r3.getByteBuffer())).limit();
    }
}
