package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.Image;
import androidx.camera.core.ImageInfo;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.ExifData;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.Packet;
import androidx.core.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class RgbaImageProxy implements ImageProxy {
    private final Rect mCropRect;
    private final int mHeight;
    private final ImageInfo mImageInfo;
    private final Object mLock;
    ImageProxy.PlaneProxy[] mPlaneProxy;
    private final int mWidth;

    /* renamed from: androidx.camera.core.imagecapture.RgbaImageProxy$1, reason: invalid class name */
    public class AnonymousClass1 implements ImageProxy.PlaneProxy {
        final /* synthetic */ ByteBuffer val$byteBuffer;
        final /* synthetic */ int val$pixelStride;
        final /* synthetic */ int val$rowStride;

        public AnonymousClass1(int r1, int r2, ByteBuffer r3) {
            this.val$rowStride = r1;
            this.val$pixelStride = r2;
            this.val$byteBuffer = r3;
        }

        @Override // androidx.camera.core.ImageProxy.PlaneProxy
        public ByteBuffer getBuffer() {
            return this.val$byteBuffer;
        }

        @Override // androidx.camera.core.ImageProxy.PlaneProxy
        public int getPixelStride() {
            return this.val$pixelStride;
        }

        @Override // androidx.camera.core.ImageProxy.PlaneProxy
        public int getRowStride() {
            return this.val$rowStride;
        }
    }

    /* renamed from: androidx.camera.core.imagecapture.RgbaImageProxy$2, reason: invalid class name */
    public class AnonymousClass2 implements ImageInfo {
        final /* synthetic */ int val$rotationDegrees;
        final /* synthetic */ Matrix val$sensorToBuffer;
        final /* synthetic */ long val$timestamp;

        public AnonymousClass2(long r1, int r3, Matrix r4) {
            this.val$timestamp = r1;
            this.val$rotationDegrees = r3;
            this.val$sensorToBuffer = r4;
        }

        @Override // androidx.camera.core.ImageInfo
        public TagBundle getTagBundle() {
            throw new UnsupportedOperationException("Custom ImageProxy does not contain TagBundle");
        }

        @Override // androidx.camera.core.ImageInfo
        public long getTimestamp() {
            return this.val$timestamp;
        }

        @Override // androidx.camera.core.ImageInfo
        public void populateExifData(ExifData.Builder r1) {
            throw new UnsupportedOperationException("Custom ImageProxy does not contain Exif data.");
        }
    }

    public RgbaImageProxy(Bitmap r11, Rect r12, int r13, Matrix r14, long r15) {
        this(ImageUtil.createDirectByteBuffer(r11), 4, r11.getWidth(), r11.getHeight(), r12, r13, r14, r15);
    }

    public RgbaImageProxy(Packet<Bitmap> r9) {
        this(r9.getData(), r9.getCropRect(), r9.getRotationDegrees(), r9.getSensorToBufferTransform(), r9.getCameraCaptureResult().getTimestamp());
    }

    public RgbaImageProxy(ByteBuffer r2, int r3, int r4, int r5, Rect r6, int r7, Matrix r8, long r9) {
        this.mLock = new Object();
        this.mWidth = r4;
        this.mHeight = r5;
        this.mCropRect = r6;
        this.mImageInfo = createImageInfo(r9, r7, r8);
        r2.rewind();
        this.mPlaneProxy = new ImageProxy.PlaneProxy[]{createPlaneProxy(r2, r4 * r3, r3)};
    }

    private void checkNotClosed() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.mPlaneProxy == null) goto L7;
        boolean r2 = true;
    L8:
        Preconditions.checkState(r2, "The image is closed.");     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        r2 = false;
        goto L8
    }

    private static ImageInfo createImageInfo(long r1, int r3, Matrix r4) {
        return new AnonymousClass2(r1, r3, r4);
    }

    private static ImageProxy.PlaneProxy createPlaneProxy(ByteBuffer r1, int r2, int r3) {
        return new AnonymousClass1(r2, r3, r1);
    }

    @Override // androidx.camera.core.ImageProxy, java.lang.AutoCloseable
    public void close() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        checkNotClosed();     // Catch: Throwable -> L7
        this.mPlaneProxy = null;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.ImageProxy
    public int getFormat() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        checkNotClosed();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return 1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.ImageProxy
    public int getHeight() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        checkNotClosed();     // Catch: Throwable -> L7
        int r1 = this.mHeight;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.ImageProxy
    public Image getImage() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        checkNotClosed();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return null;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.ImageProxy
    public ImageInfo getImageInfo() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        checkNotClosed();     // Catch: Throwable -> L7
        ImageInfo r1 = this.mImageInfo;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.ImageProxy
    public ImageProxy.PlaneProxy[] getPlanes() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        checkNotClosed();     // Catch: Throwable -> L7
        ImageProxy.PlaneProxy[] r1 = this.mPlaneProxy;     // Catch: Throwable -> L7
        Objects.requireNonNull(r1);     // Catch: Throwable -> L7
        ImageProxy.PlaneProxy[] r12 = r1;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.ImageProxy
    public int getWidth() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        checkNotClosed();     // Catch: Throwable -> L7
        int r1 = this.mWidth;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.ImageProxy
    public void setCropRect(Rect r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        checkNotClosed();     // Catch: Throwable -> L7
        if (r2 == null) goto L9;
        this.mCropRect.set(r2);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
