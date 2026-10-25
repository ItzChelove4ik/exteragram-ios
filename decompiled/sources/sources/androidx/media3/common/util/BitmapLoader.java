package androidx.media3.common.util;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes3.dex */
public interface BitmapLoader {
    ListenableFuture<Bitmap> decodeBitmap(byte[] r1);

    ListenableFuture<Bitmap> loadBitmap(Uri r1);

    default ListenableFuture<Bitmap> loadBitmapFromMetadata(MediaMetadata r2) {
        byte[] r0 = r2.artworkData;
        if (r0 != null) goto L5;
        Uri r22 = r2.artworkUri;
        if (r22 != null) goto L9;
        return null;
    L9:
        return loadBitmap(r22);
    L5:
        return decodeBitmap(r0);
    }
}
