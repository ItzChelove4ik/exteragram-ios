package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;

/* loaded from: classes3.dex */
public final class SettableImageProxy extends ForwardingImageProxy {
    private Rect mCropRect;
    private final int mHeight;
    private final ImageInfo mImageInfo;
    private final Object mLock;
    private final int mWidth;

    public SettableImageProxy(ImageProxy r1, Size r2, ImageInfo r3) {
        super(r1);
        this.mLock = new Object();
        if (r2 != null) goto L5;
        this.mWidth = super.getWidth();
        this.mHeight = super.getHeight();
    L6:
        this.mImageInfo = r3;
        return;
    L5:
        this.mWidth = r2.getWidth();
        this.mHeight = r2.getHeight();
        goto L6
    }

    public SettableImageProxy(ImageProxy r2, ImageInfo r3) {
        this(r2, null, r3);
    }

    @Override // androidx.camera.core.ForwardingImageProxy, androidx.camera.core.ImageProxy
    public int getHeight() {
        return this.mHeight;
    }

    @Override // androidx.camera.core.ForwardingImageProxy, androidx.camera.core.ImageProxy
    public ImageInfo getImageInfo() {
        return this.mImageInfo;
    }

    @Override // androidx.camera.core.ForwardingImageProxy, androidx.camera.core.ImageProxy
    public int getWidth() {
        return this.mWidth;
    }

    @Override // androidx.camera.core.ForwardingImageProxy, androidx.camera.core.ImageProxy
    public void setCropRect(Rect r4) {
        if (r4 == null) goto L7;
        Rect r0 = new Rect(r4);
        if (r0.intersect(0, 0, getWidth(), getHeight()) == true) goto L6;
        r0.setEmpty();
    L6:
        r4 = r0;
    L7:
        Object r02 = this.mLock;
        monitor-enter(r02);
        this.mCropRect = r4;     // Catch: Throwable -> L12
        monitor-exit(r02);     // Catch: Throwable -> L12
        return;
    L12:
        th = move-exception;
        throw th;
    }
}
