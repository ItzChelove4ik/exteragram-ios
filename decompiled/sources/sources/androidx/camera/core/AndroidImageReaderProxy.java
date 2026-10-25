package androidx.camera.core;

import android.media.Image;
import android.media.ImageReader;
import android.view.Surface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
class AndroidImageReaderProxy implements ImageReaderProxy {
    private final ImageReader mImageReader;
    private boolean mIsImageAvailableListenerCleared;
    private final Object mLock;

    public static /* synthetic */ void $r8$lambda$0d9VKBtme9NUYt20OFXADDntWdE(AndroidImageReaderProxy r1, Executor r2, ImageReaderProxy.OnImageAvailableListener r3, ImageReader r4) {
        Object r42 = r1.mLock;
        monitor-enter(r42);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r1.mIsImageAvailableListenerCleared == true) goto L9;
        r2.execute(new AndroidImageReaderProxy$$ExternalSyntheticLambda1(r1, r3));     // Catch: Throwable -> L7
    L9:
        monitor-exit(r42);     // Catch: Throwable -> L7
    }

    /* renamed from: $r8$lambda$VU285rPvGaxlLdZyr-pjv6_gV1k, reason: not valid java name */
    public static /* synthetic */ void m581$r8$lambda$VU285rPvGaxlLdZyrpjv6_gV1k(AndroidImageReaderProxy r0, ImageReaderProxy.OnImageAvailableListener r1) {
        r0.getClass();
        r1.onImageAvailable(r0);
    }

    public AndroidImageReaderProxy(ImageReader r2) {
        this.mLock = new Object();
        this.mIsImageAvailableListenerCleared = true;
        this.mImageReader = r2;
    }

    private boolean isImageReaderContextNotInitializedException(RuntimeException r1) {
        return "ImageReaderContext is not initialized".equals(r1.getMessage());
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireLatestImage() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Image r3 = this.mImageReader.acquireLatestImage();     // Catch: Throwable -> L7 RuntimeException -> L9
    L13:
        if (r3 != null) goto L16;
        monitor-exit(r0);     // Catch: Throwable -> L7
        return null;
    L16:
        AndroidImageProxy r1 = new AndroidImageProxy(r3);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L9:
        e = move-exception;
        if (this.isImageReaderContextNotInitializedException(e) == false) goto L19;
        r3 = null;
        goto L13
    L19:
        throw e;     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireNextImage() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Image r3 = this.mImageReader.acquireNextImage();     // Catch: Throwable -> L7 RuntimeException -> L9
    L13:
        if (r3 != null) goto L16;
        monitor-exit(r0);     // Catch: Throwable -> L7
        return null;
    L16:
        AndroidImageProxy r1 = new AndroidImageProxy(r3);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L9:
        e = move-exception;
        if (this.isImageReaderContextNotInitializedException(e) == false) goto L19;
        r3 = null;
        goto L13
    L19:
        throw e;     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mIsImageAvailableListenerCleared = true;     // Catch: Throwable -> L8
        this.mImageReader.setOnImageAvailableListener(null, null);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mImageReader.close();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        int r1 = this.mImageReader.getHeight();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        int r1 = this.mImageReader.getImageFormat();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        int r1 = this.mImageReader.getMaxImages();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public Surface getSurface() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Surface r1 = this.mImageReader.getSurface();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        int r1 = this.mImageReader.getWidth();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener r3, Executor r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mIsImageAvailableListenerCleared = false;     // Catch: Throwable -> L8
        AndroidImageReaderProxy$$ExternalSyntheticLambda0 r1 = new AndroidImageReaderProxy$$ExternalSyntheticLambda0(this, r4, r3);     // Catch: Throwable -> L8
        this.mImageReader.setOnImageAvailableListener(r1, MainThreadAsyncHandler.getInstance());     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
