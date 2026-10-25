package androidx.camera.core;

import android.view.Surface;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.impl.ImageReaderProxy;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public class SafeCloseImageReaderProxy implements ImageReaderProxy {
    private ForwardingImageProxy.OnImageCloseListener mForwardingImageCloseListener;
    private final ForwardingImageProxy.OnImageCloseListener mImageCloseListener;
    private final ImageReaderProxy mImageReaderProxy;
    private boolean mIsClosed;
    private final Object mLock;
    private int mOutstandingImages;
    private final Surface mSurface;

    public static /* synthetic */ void $r8$lambda$Y9AMDHHHqfIdIOm9bxU2nToQD6w(SafeCloseImageReaderProxy r0, ImageReaderProxy.OnImageAvailableListener r1, ImageReaderProxy r2) {
        r0.getClass();
        r1.onImageAvailable(r0);
    }

    /* renamed from: $r8$lambda$fWkUGcq782Tl0_CTDjYLKng-eLY, reason: not valid java name */
    public static /* synthetic */ void m586$r8$lambda$fWkUGcq782Tl0_CTDjYLKngeLY(SafeCloseImageReaderProxy r3, ImageProxy r4) {
        Object r0 = r3.mLock;
        monitor-enter(r0);
        int r1 = r3.mOutstandingImages - 1;
        r3.mOutstandingImages = r1;     // Catch: Throwable -> L8
        if (r3.mIsClosed == false) goto L10;
        if (r1 != 0) goto L10;
        r3.close();     // Catch: Throwable -> L8
    L10:
        ForwardingImageProxy.OnImageCloseListener r32 = r3.mForwardingImageCloseListener;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        if (r32 == null) goto L19;
        r32.onImageClose(r4);
        return;
    L19:
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public SafeCloseImageReaderProxy(ImageReaderProxy r2) {
        this.mLock = new Object();
        this.mOutstandingImages = 0;
        this.mIsClosed = false;
        this.mImageCloseListener = new SafeCloseImageReaderProxy$$ExternalSyntheticLambda1(this);
        this.mImageReaderProxy = r2;
        this.mSurface = r2.getSurface();
    }

    private ImageProxy wrapImageProxy(ImageProxy r2) {
        if (r2 == null) goto L5;
        this.mOutstandingImages++;
        SingleCloseImageProxy r0 = new SingleCloseImageProxy(r2);
        r0.addOnImageCloseListener(this.mImageCloseListener);
        return r0;
    L5:
        return null;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireLatestImage() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        ImageProxy r2 = wrapImageProxy(this.mImageReaderProxy.acquireLatestImage());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireNextImage() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        ImageProxy r2 = wrapImageProxy(this.mImageReaderProxy.acquireNextImage());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mImageReaderProxy.clearOnImageAvailableListener();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Surface r1 = this.mSurface;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.release();     // Catch: Throwable -> L7
    L9:
        this.mImageReaderProxy.close();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public int getCapacity() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        int r1 = this.mImageReaderProxy.getMaxImages() - this.mOutstandingImages;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        int r1 = this.mImageReaderProxy.getHeight();     // Catch: Throwable -> L7
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
        int r1 = this.mImageReaderProxy.getImageFormat();     // Catch: Throwable -> L7
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
        int r1 = this.mImageReaderProxy.getMaxImages();     // Catch: Throwable -> L7
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
        Surface r1 = this.mImageReaderProxy.getSurface();     // Catch: Throwable -> L7
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
        int r1 = this.mImageReaderProxy.getWidth();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void safeClose() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mIsClosed = true;     // Catch: Throwable -> L8
        this.mImageReaderProxy.clearOnImageAvailableListener();     // Catch: Throwable -> L8
        if (this.mOutstandingImages != 0) goto L10;
        close();     // Catch: Throwable -> L8
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener r4, Executor r5) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mImageReaderProxy.setOnImageAvailableListener(new SafeCloseImageReaderProxy$$ExternalSyntheticLambda0(this, r4), r5);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setOnImageCloseListener(ForwardingImageProxy.OnImageCloseListener r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mForwardingImageCloseListener = r2;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
