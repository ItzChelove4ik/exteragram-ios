package androidx.camera.core;

import android.media.ImageReader;
import android.util.LongSparseArray;
import android.view.Surface;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public class MetadataImageReader implements ImageReaderProxy, ForwardingImageProxy.OnImageCloseListener {
    private final List<ImageProxy> mAcquiredImageProxies;
    private CameraCaptureCallback mCameraCaptureCallback;
    private boolean mClosed;
    private Executor mExecutor;
    private int mImageProxiesIndex;
    private final ImageReaderProxy mImageReaderProxy;
    ImageReaderProxy.OnImageAvailableListener mListener;
    private final Object mLock;
    private final List<ImageProxy> mMatchedImageProxies;
    private final LongSparseArray<ImageInfo> mPendingImageInfos;
    private final LongSparseArray<ImageProxy> mPendingImages;
    private ImageReaderProxy.OnImageAvailableListener mTransformedListener;
    private int mUnAcquiredAvailableImageCount;

    /* renamed from: androidx.camera.core.MetadataImageReader$1, reason: invalid class name */
    public class AnonymousClass1 extends CameraCaptureCallback {
        final /* synthetic */ MetadataImageReader this$0;

        public AnonymousClass1(MetadataImageReader r1) {
            this.this$0 = r1;
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(int r1, CameraCaptureResult r2) {
            super.onCaptureCompleted(r1, r2);
            this.this$0.resultIncoming(r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$OjJ1V45j4ZKbpW9cCAbCbNa1Ia4(MetadataImageReader r0, ImageReaderProxy.OnImageAvailableListener r1) {
        r0.getClass();
        r1.onImageAvailable(r0);
    }

    public static /* synthetic */ void $r8$lambda$uxDvm7kLxw0WlB3lkvXpuw_QwbI(MetadataImageReader r2, ImageReaderProxy r3) {
        Object r0 = r2.mLock;
        monitor-enter(r0);
        r2.mUnAcquiredAvailableImageCount++;
        monitor-exit(r0);     // Catch: Throwable -> L8
        r2.imageIncoming(r3);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public MetadataImageReader(int r1, int r2, int r3, int r4) {
        this(createImageReaderProxy(r1, r2, r3, r4));
    }

    public MetadataImageReader(ImageReaderProxy r3) {
        this.mLock = new Object();
        this.mCameraCaptureCallback = new AnonymousClass1(this);
        this.mUnAcquiredAvailableImageCount = 0;
        this.mTransformedListener = new MetadataImageReader$$ExternalSyntheticLambda0(this);
        this.mClosed = false;
        this.mPendingImageInfos = new LongSparseArray();
        this.mPendingImages = new LongSparseArray();
        this.mAcquiredImageProxies = new ArrayList();
        this.mImageReaderProxy = r3;
        this.mImageProxiesIndex = 0;
        this.mMatchedImageProxies = new ArrayList(getMaxImages());
    }

    private static ImageReaderProxy createImageReaderProxy(int r1, int r2, int r3, int r4) {
        return new AndroidImageReaderProxy(ImageReader.newInstance(r1, r2, r3, r4));
    }

    private void dequeImageProxy(ImageProxy r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        int r1 = this.mMatchedImageProxies.indexOf(r4);     // Catch: Throwable -> L9
        if (r1 < 0) goto L11;
        this.mMatchedImageProxies.remove(r1);     // Catch: Throwable -> L9
        int r2 = this.mImageProxiesIndex;     // Catch: Throwable -> L9
        if (r1 > r2) goto L11;
        this.mImageProxiesIndex = r2 - 1;     // Catch: Throwable -> L9
    L11:
        this.mAcquiredImageProxies.remove(r4);     // Catch: Throwable -> L9
        if (this.mUnAcquiredAvailableImageCount <= 0) goto L14;
        imageIncoming(this.mImageReaderProxy);     // Catch: Throwable -> L9
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private void enqueueImageProxy(SettableImageProxy r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mMatchedImageProxies.size() >= getMaxImages()) goto L9;
        r4.addOnImageCloseListener(this);     // Catch: Throwable -> L7
        this.mMatchedImageProxies.add(r4);     // Catch: Throwable -> L7
        ImageReaderProxy.OnImageAvailableListener r42 = this.mListener;     // Catch: Throwable -> L7
        Executor r1 = this.mExecutor;     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        if (r42 == null) goto L21;
        if (r1 == null) goto L15;
        r1.execute(new MetadataImageReader$$ExternalSyntheticLambda1(this, r42));
        return;
    L15:
        r42.onImageAvailable(this);
        return;
    L21:
        return;
    L9:
        Logger.d("TAG", "Maximum image number reached.");     // Catch: Throwable -> L7
        r4.close();     // Catch: Throwable -> L7
        r42 = null;
        r1 = null;
        goto L10
    }

    private void matchImages() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        int r1 = this.mPendingImageInfos.size() - 1;
    L5:
        if (r1 < 0) goto L12;
        ImageInfo r2 = this.mPendingImageInfos.valueAt(r1);     // Catch: Throwable -> L9
        long r3 = r2.getTimestamp();     // Catch: Throwable -> L9
        ImageProxy r5 = this.mPendingImages.get(r3);     // Catch: Throwable -> L9
        if (r5 == null) goto L11;
        this.mPendingImages.remove(r3);     // Catch: Throwable -> L9
        this.mPendingImageInfos.removeAt(r1);     // Catch: Throwable -> L9
        enqueueImageProxy(new SettableImageProxy(r5, r2));     // Catch: Throwable -> L9
    L11:
        r1 = r1 - 1;
        goto L5
    L12:
        removeStaleData();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private void removeStaleData() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L16:
        th = move-exception;
        throw th;
    L5:
        if (this.mPendingImages.size() != 0) goto L7;
    L27:
        monitor-exit(r0);     // Catch: Throwable -> L16
        return;
    L7:
        if (this.mPendingImageInfos.size() == 0) goto L27;
        long r3 = this.mPendingImages.keyAt(0);     // Catch: Throwable -> L16
        Long r1 = Long.valueOf(r3);     // Catch: Throwable -> L16
        long r5 = this.mPendingImageInfos.keyAt(0);     // Catch: Throwable -> L16
        Preconditions.checkArgument(!Long.valueOf(r5).equals(r1));     // Catch: Throwable -> L16
        if (r5 <= r3) goto L19;
        int r12 = this.mPendingImages.size() - 1;
    L12:
        if (r12 < 0) goto L25;
        if (this.mPendingImages.keyAt(r12) >= r5) goto L18;
        this.mPendingImages.valueAt(r12).close();     // Catch: Throwable -> L16
        this.mPendingImages.removeAt(r12);     // Catch: Throwable -> L16
    L18:
        r12 = r12 - 1;
    L25:
        monitor-exit(r0);     // Catch: Throwable -> L16
        return;
    L19:
        int r13 = this.mPendingImageInfos.size() - 1;
    L20:
        if (r13 < 0) goto L25;
        if (this.mPendingImageInfos.keyAt(r13) >= r3) goto L24;
        this.mPendingImageInfos.removeAt(r13);     // Catch: Throwable -> L16
    L24:
        r13 = r13 - 1;
        goto L20
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireLatestImage() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mMatchedImageProxies.isEmpty() == false) goto L12;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L12:
        if (this.mImageProxiesIndex >= this.mMatchedImageProxies.size()) goto L27;
        ArrayList r1 = new ArrayList();     // Catch: Throwable -> L9
        int r2 = 0;
        int r3 = 0;
    L15:
        if (r3 >= (this.mMatchedImageProxies.size() - 1)) goto L20;
        if (this.mAcquiredImageProxies.contains(this.mMatchedImageProxies.get(r3)) == true) goto L19;
        r1.add(this.mMatchedImageProxies.get(r3));     // Catch: Throwable -> L9
    L19:
        r3 = r3 + 1;     // Catch: Throwable -> L9
        goto L15
    L20:
        int r32 = r1.size();     // Catch: Throwable -> L9
    L21:
        if (r2 >= r32) goto L23;
        Object r4 = r1.get(r2);     // Catch: Throwable -> L9
        r2 = r2 + 1;     // Catch: Throwable -> L9
        ((ImageProxy) r4).close();     // Catch: Throwable -> L9
        goto L21
    L23:
        int r12 = this.mMatchedImageProxies.size();     // Catch: Throwable -> L9
        List<ImageProxy> r33 = this.mMatchedImageProxies;     // Catch: Throwable -> L9
        this.mImageProxiesIndex = r12;     // Catch: Throwable -> L9
        ImageProxy r13 = r33.get(r12 - 1);     // Catch: Throwable -> L9
        this.mAcquiredImageProxies.add(r13);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r13;
    L27:
        throw new IllegalStateException("Maximum image number reached.");     // Catch: Throwable -> L9
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireNextImage() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mMatchedImageProxies.isEmpty() == false) goto L12;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L12:
        if (this.mImageProxiesIndex >= this.mMatchedImageProxies.size()) goto L17;
        List<ImageProxy> r1 = this.mMatchedImageProxies;     // Catch: Throwable -> L9
        int r2 = this.mImageProxiesIndex;     // Catch: Throwable -> L9
        this.mImageProxiesIndex = r2 + 1;     // Catch: Throwable -> L9
        ImageProxy r12 = r1.get(r2);     // Catch: Throwable -> L9
        this.mAcquiredImageProxies.add(r12);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L17:
        throw new IllegalStateException("Maximum image number reached.");     // Catch: Throwable -> L9
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mImageReaderProxy.clearOnImageAvailableListener();     // Catch: Throwable -> L7
        this.mListener = null;     // Catch: Throwable -> L7
        this.mExecutor = null;     // Catch: Throwable -> L7
        this.mUnAcquiredAvailableImageCount = 0;     // Catch: Throwable -> L7
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
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.mClosed == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        ArrayList r1 = new ArrayList(this.mMatchedImageProxies);     // Catch: Throwable -> L8
        int r2 = r1.size();     // Catch: Throwable -> L8
        int r3 = 0;
    L11:
        if (r3 >= r2) goto L13;
        Object r4 = r1.get(r3);     // Catch: Throwable -> L8
        r3 = r3 + 1;     // Catch: Throwable -> L8
        ((ImageProxy) r4).close();     // Catch: Throwable -> L8
        goto L11
    L13:
        this.mMatchedImageProxies.clear();     // Catch: Throwable -> L8
        this.mImageReaderProxy.close();     // Catch: Throwable -> L8
        this.mClosed = true;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }

    public CameraCaptureCallback getCameraCaptureCallback() {
        return this.mCameraCaptureCallback;
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

    public void imageIncoming(ImageReaderProxy r7) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.mClosed == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        int r1 = this.mPendingImages.size() + this.mMatchedImageProxies.size();     // Catch: Throwable -> L8
        if (r1 < r7.getMaxImages()) goto L34;
        Logger.d("MetadataImageReader", "Skip to acquire the next image because the acquired image count has reached the max images count.");     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L34:
        ImageProxy r2 = r7.acquireNextImage();     // Catch: Throwable -> L19 IllegalStateException -> L21
        if (r2 == null) goto L24;
        this.mUnAcquiredAvailableImageCount--;
        r1 = r1 + 1;     // Catch: Throwable -> L8
        this.mPendingImages.put(r2.getImageInfo().getTimestamp(), r2);     // Catch: Throwable -> L8
        matchImages();     // Catch: Throwable -> L8
    L24:
        if (r2 == null) goto L29;
        if (this.mUnAcquiredAvailableImageCount <= 0) goto L29;
        if (r1 < r7.getMaxImages()) goto L34;
    L29:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L19:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L21:
        e = move-exception;
        Logger.d("MetadataImageReader", "Failed to acquire next image.", e);     // Catch: Throwable -> L19
        r2 = null;
        goto L24
    }

    @Override // androidx.camera.core.ForwardingImageProxy.OnImageCloseListener
    public void onImageClose(ImageProxy r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        dequeImageProxy(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void resultIncoming(CameraCaptureResult r6) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.mClosed == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.mPendingImageInfos.put(r6.getTimestamp(), new CameraCaptureResultImageInfo(r6));     // Catch: Throwable -> L8
        matchImages();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener r2, Executor r3) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mListener = (ImageReaderProxy.OnImageAvailableListener) Preconditions.checkNotNull(r2);     // Catch: Throwable -> L7
        this.mExecutor = (Executor) Preconditions.checkNotNull(r3);     // Catch: Throwable -> L7
        this.mImageReaderProxy.setOnImageAvailableListener(this.mTransformedListener, r3);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
