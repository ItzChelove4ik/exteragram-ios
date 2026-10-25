package androidx.media3.session;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class CacheBitmapLoader implements BitmapLoader {
    private final BitmapLoader bitmapLoader;
    private BitmapLoadRequest lastBitmapLoadRequest;

    public static class BitmapLoadRequest {
        private final byte[] data;
        private final ListenableFuture<Bitmap> future;
        private final Uri uri;

        public BitmapLoadRequest(Uri r2, ListenableFuture<Bitmap> r3) {
            this.data = null;
            this.uri = r2;
            this.future = r3;
        }

        public BitmapLoadRequest(byte[] r1, ListenableFuture<Bitmap> r2) {
            this.data = r1;
            this.uri = null;
            this.future = r2;
        }

        public ListenableFuture<Bitmap> getFuture() {
            return (ListenableFuture) Assertions.checkStateNotNull(this.future);
        }

        public boolean matches(Uri r1) {
            Uri r0 = this.uri;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.equals(r1) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean matches(byte[] r1) {
            byte[] r0 = this.data;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (Arrays.equals(r0, r1) == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public CacheBitmapLoader(BitmapLoader r1) {
        this.bitmapLoader = r1;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> decodeBitmap(byte[] r3) {
        BitmapLoadRequest r0 = this.lastBitmapLoadRequest;
        if (r0 != null) goto L5;
    L8:
        ListenableFuture<Bitmap> r02 = this.bitmapLoader.decodeBitmap(r3);
        this.lastBitmapLoadRequest = new BitmapLoadRequest(r3, r02);
        return r02;
    L5:
        if (r0.matches(r3) == false) goto L8;
        return this.lastBitmapLoadRequest.getFuture();
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> loadBitmap(Uri r3) {
        BitmapLoadRequest r0 = this.lastBitmapLoadRequest;
        if (r0 != null) goto L5;
    L8:
        ListenableFuture<Bitmap> r02 = this.bitmapLoader.loadBitmap(r3);
        this.lastBitmapLoadRequest = new BitmapLoadRequest(r3, r02);
        return r02;
    L5:
        if (r0.matches(r3) == false) goto L8;
        return this.lastBitmapLoadRequest.getFuture();
    }
}
