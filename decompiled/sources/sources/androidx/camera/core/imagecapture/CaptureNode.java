package androidx.camera.core.imagecapture;

import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.ImageReaderProxyProvider;
import androidx.camera.core.ImageReaderProxys;
import androidx.camera.core.Logger;
import androidx.camera.core.MetadataImageReader;
import androidx.camera.core.SafeCloseImageReaderProxy;
import androidx.camera.core.imagecapture.ProcessingNode;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureCallbacks;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.processing.Edge;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
class CaptureNode {
    ProcessingRequest mCurrentRequest;
    private In mInputEdge;
    private NoMetadataImageReader mNoMetadataImageReader;
    private ProcessingNode.In mOutputEdge;
    SafeCloseImageReaderProxy mSafeCloseImageReaderForPostview;
    SafeCloseImageReaderProxy mSafeCloseImageReaderProxy;
    SafeCloseImageReaderProxy mSecondarySafeCloseImageReaderProxy;

    /* renamed from: androidx.camera.core.imagecapture.CaptureNode$1, reason: invalid class name */
    public class AnonymousClass1 extends CameraCaptureCallback {
        final /* synthetic */ CaptureNode this$0;

        /* renamed from: $r8$lambda$9Z-85vUMCoUYHuaiE1yU-NMe4EM, reason: not valid java name */
        public static /* synthetic */ void m590$r8$lambda$9Z85vUMCoUYHuaiE1yUNMe4EM(AnonymousClass1 r0) {
            ProcessingRequest r02 = r0.this$0.mCurrentRequest;
        }

        public static /* synthetic */ void $r8$lambda$MGCZJvSMnFbJoxIZMXUjnrGd84Q(AnonymousClass1 r0, int r1) {
            ProcessingRequest r02 = r0.this$0.mCurrentRequest;
        }

        public AnonymousClass1(CaptureNode r1) {
            this.this$0 = r1;
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureProcessProgressed(int r2, int r3) {
            CameraXExecutors.mainThreadExecutor().execute(new CaptureNode$1$$ExternalSyntheticLambda0(this, r3));
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureStarted(int r2) {
            CameraXExecutors.mainThreadExecutor().execute(new CaptureNode$1$$ExternalSyntheticLambda1(this));
        }
    }

    /* renamed from: androidx.camera.core.imagecapture.CaptureNode$2, reason: invalid class name */
    public class AnonymousClass2 implements FutureCallback<Void> {
        final /* synthetic */ CaptureNode this$0;
        final /* synthetic */ ProcessingRequest val$request;

        public AnonymousClass2(CaptureNode r1, ProcessingRequest r2) {
            this.this$0 = r1;
            this.val$request = r2;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable r2) {
            Threads.checkMainThread();
            if (this.val$request != this.this$0.mCurrentRequest) goto L9;
            Logger.w("CaptureNode", "request aborted, id=" + this.this$0.mCurrentRequest.getRequestId());
            if (CaptureNode.access$000(this.this$0) == null) goto L7;
            CaptureNode.access$000(this.this$0).clearProcessingRequest();
        L7:
            this.this$0.mCurrentRequest = null;
            return;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(Void r1) {
            onSuccess2(r1);
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(Void r1) {
        }
    }

    public static abstract class In {
        private CameraCaptureCallback mCameraCaptureCallback;
        private DeferrableSurface mPostviewSurface;
        private CameraCaptureCallback mSecondaryCameraCaptureCallback;
        private DeferrableSurface mSecondarySurface;
        private DeferrableSurface mSurface;

        /* renamed from: androidx.camera.core.imagecapture.CaptureNode$In$1, reason: invalid class name */
        public class AnonymousClass1 extends CameraCaptureCallback {
            final /* synthetic */ In this$0;

            public AnonymousClass1(In r1) {
                this.this$0 = r1;
            }
        }

        public In() {
            this.mCameraCaptureCallback = new AnonymousClass1(this);
            this.mPostviewSurface = null;
        }

        public static In of(Size r9, int r10, List<Integer> r11, boolean r12, ImageReaderProxyProvider r13, PostviewSettings r14) {
            return new AutoValue_CaptureNode_In(r9, r10, r11, r12, r13, r14, new Edge(), new Edge());
        }

        public abstract Edge<TakePictureManager.CaptureError> getErrorEdge();

        public abstract ImageReaderProxyProvider getImageReaderProxyProvider();

        public abstract int getInputFormat();

        public abstract List<Integer> getOutputFormats();

        public abstract PostviewSettings getPostviewSettings();

        public DeferrableSurface getPostviewSurface() {
            return this.mPostviewSurface;
        }

        public abstract Edge<ProcessingRequest> getRequestEdge();

        public DeferrableSurface getSecondarySurface() {
            return this.mSecondarySurface;
        }

        public abstract Size getSize();

        public DeferrableSurface getSurface() {
            DeferrableSurface r0 = this.mSurface;
            Objects.requireNonNull(r0);
            return r0;
        }

        public abstract boolean isVirtualCamera();

        public void setCameraCaptureCallback(CameraCaptureCallback r1) {
            this.mCameraCaptureCallback = r1;
        }

        public void setPostviewSurface(Surface r2, Size r3, int r4) {
            this.mPostviewSurface = new ImmediateSurface(r2, r3, r4);
        }

        public void setSecondaryCameraCaptureCallback(CameraCaptureCallback r1) {
            this.mSecondaryCameraCaptureCallback = r1;
        }

        public void setSecondarySurface(Surface r4) {
            if (this.mSecondarySurface != null) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkState(r0, "The secondary surface is already set.");
            this.mSecondarySurface = new ImmediateSurface(r4, getSize(), getInputFormat());
            return;
        L5:
            r0 = false;
            goto L6
        }

        public void setSurface(Surface r4) {
            if (this.mSurface != null) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkState(r0, "The surface is already set.");
            this.mSurface = new ImmediateSurface(r4, getSize(), getInputFormat());
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$7C7m1vQE0Vvq_cf9LPRtus1dPGk(CaptureNode r3, ImageReaderProxy r4) {
        r3.getClass();
        ImageProxy r42 = r4.acquireLatestImage();     // Catch: IllegalStateException -> L13
        StringBuilder r2 = new StringBuilder("OnImageAvailableListener: mCurrentRequest ID = ");     // Catch: IllegalStateException -> L13
        ProcessingRequest r0 = r3.mCurrentRequest;     // Catch: IllegalStateException -> L13
        r2.append(null);     // Catch: IllegalStateException -> L13
        r2.append(", image.isNull = ");     // Catch: IllegalStateException -> L13
        if (r42 != null) goto L6;
        boolean r02 = true;
    L7:
        r2.append(r02);     // Catch: IllegalStateException -> L13
        Logger.d("CaptureNode", r2.toString());     // Catch: IllegalStateException -> L13
        if (r42 == null) goto L11;
        r3.onImageProxyAvailable(r42);     // Catch: IllegalStateException -> L13
        return;
    L11:
        ProcessingRequest r32 = r3.mCurrentRequest;     // Catch: IllegalStateException -> L13
        return;
    L6:
        r02 = false;
    L13:
        ProcessingRequest r33 = r3.mCurrentRequest;
    }

    /* renamed from: $r8$lambda$7aA-5yFpboCKw0n21h0OvTjZyL0, reason: not valid java name */
    public static /* synthetic */ void m589$r8$lambda$7aA5yFpboCKw0n21h0OvTjZyL0(CaptureNode r0, ProcessingRequest r1) {
        r0.onRequestAvailable(r1);
        r0.mNoMetadataImageReader.acceptProcessingRequest(r1);
    }

    public static /* synthetic */ void $r8$lambda$Nz2GtlCHGMBw1M1ufLbha9kw6C4(SafeCloseImageReaderProxy r0) {
        r0.safeClose();
    }

    public static /* synthetic */ void $r8$lambda$kl76V5Ze8dYKadnZQeFK_044WOE(SafeCloseImageReaderProxy r0) {
        if (r0 == null) goto L5;
        r0.safeClose();
        return;
    }

    public static /* synthetic */ void $r8$lambda$pMuiQYF7TOgEFV3jPwxF5CXMq8Y(CaptureNode r1, ImageReaderProxy r2) {
        r1.getClass();
        ImageProxy r22 = r2.acquireLatestImage();     // Catch: IllegalStateException -> L7
        if (r22 == null) goto L12;
        r1.propagatePostviewImage(r22);     // Catch: IllegalStateException -> L7
        return;
    L12:
        return;
    L7:
        e = move-exception;
        Logger.e("CaptureNode", "Failed to acquire latest image of postview", e);
    }

    public static /* synthetic */ void $r8$lambda$rWWtbZD2YG5XEuVxruKY108_pIc(SafeCloseImageReaderProxy r0) {
        if (r0 == null) goto L5;
        r0.safeClose();
        return;
    }

    public CaptureNode() {
        this.mCurrentRequest = null;
        this.mNoMetadataImageReader = null;
    }

    public static /* synthetic */ NoMetadataImageReader access$000(CaptureNode r0) {
        return r0.mNoMetadataImageReader;
    }

    private static ImageReaderProxy createImageReaderProxy(ImageReaderProxyProvider r7, int r8, int r9, int r10) {
        if (r7 == null) goto L6;
        return r7.newInstance(r8, r9, r10, 4, 0);
    L6:
        return ImageReaderProxys.createIsolatedReader(r8, r9, r10, 4);
    }

    private void matchAndPropagateImage(ImageProxy r3) {
        Threads.checkMainThread();
        ProcessingNode.In r0 = this.mOutputEdge;
        Objects.requireNonNull(r0);
        r0.getEdge().accept(ProcessingNode.InputPacket.of(this.mCurrentRequest, r3));
        ProcessingRequest r32 = this.mCurrentRequest;
        In r2 = this.mInputEdge;
        if (r2 == null) goto L5;
        r2.getOutputFormats().size();
    L5:
        r32.onImageCaptured();
    }

    private void propagatePostviewImage(ImageProxy r2) {
        Logger.w("CaptureNode", "Postview image is closed due to request completed or aborted");
        r2.close();
    }

    private void releaseInputResources(In r2, SafeCloseImageReaderProxy r3, SafeCloseImageReaderProxy r4, SafeCloseImageReaderProxy r5) {
        r2.getSurface().close();
        r2.getSurface().getTerminationFuture().addListener(new CaptureNode$$ExternalSyntheticLambda4(r3), CameraXExecutors.mainThreadExecutor());
        if (r2.getPostviewSurface() == null) goto L6;
        r2.getPostviewSurface().close();
        r2.getPostviewSurface().getTerminationFuture().addListener(new CaptureNode$$ExternalSyntheticLambda5(r5), CameraXExecutors.mainThreadExecutor());
    L6:
        if (r2.getOutputFormats().size() > 1) goto L8;
        return;
    L8:
        if (r2.getSecondarySurface() == null) goto L12;
        r2.getSecondarySurface().close();
        r2.getSecondarySurface().getTerminationFuture().addListener(new CaptureNode$$ExternalSyntheticLambda6(r4), CameraXExecutors.mainThreadExecutor());
        return;
    }

    private void setOnImageAvailableListener(ImageReaderProxy r2) {
        r2.setOnImageAvailableListener(new CaptureNode$$ExternalSyntheticLambda7(this), CameraXExecutors.mainThreadExecutor());
    }

    public int getCapacity() {
        Threads.checkMainThread();
        if (this.mSafeCloseImageReaderProxy == null) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0, "The ImageReader is not initialized.");
        return this.mSafeCloseImageReaderProxy.getCapacity();
    L5:
        r0 = false;
        goto L6
    }

    public void onImageProxyAvailable(ImageProxy r3) {
        Threads.checkMainThread();
        Logger.w("CaptureNode", "Discarding ImageProxy which was inadvertently acquired: " + r3);
        r3.close();
    }

    public void onRequestAvailable(ProcessingRequest r5) {
        Threads.checkMainThread();
        boolean r1 = false;
        if (r5.getStageIds().size() != 1) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0, "only one capture stage is supported.");
        if (getCapacity() <= 0) goto L9;
        r1 = true;
    L9:
        Preconditions.checkState(r1, "Too many acquire images. Close image to be able to process next.");
        this.mCurrentRequest = r5;
        Futures.addCallback(r5.getCaptureFuture(), new AnonymousClass2(this, r5), CameraXExecutors.directExecutor());
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void release() {
        Threads.checkMainThread();
        In r0 = this.mInputEdge;
        Objects.requireNonNull(r0);
        SafeCloseImageReaderProxy r1 = this.mSafeCloseImageReaderProxy;
        Objects.requireNonNull(r1);
        releaseInputResources(r0, r1, this.mSecondarySafeCloseImageReaderProxy, this.mSafeCloseImageReaderForPostview);
    }

    public void sendCaptureError(TakePictureManager.CaptureError r1) {
        Threads.checkMainThread();
    }

    public void setOnImageCloseListener(ForwardingImageProxy.OnImageCloseListener r3) {
        Threads.checkMainThread();
        if (this.mSafeCloseImageReaderProxy == null) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0, "The ImageReader is not initialized.");
        this.mSafeCloseImageReaderProxy.setOnImageCloseListener(r3);
        return;
    L5:
        r0 = false;
        goto L6
    }

    public ProcessingNode.In transform(In r13) {
        if (this.mInputEdge == null) goto L5;
    L7:
        boolean r0 = false;
    L8:
        Preconditions.checkState(r0, "CaptureNode does not support recreation yet.");
        this.mInputEdge = r13;
        Size r02 = r13.getSize();
        int r3 = r13.getInputFormat();
        boolean r4 = r13.isVirtualCamera();
        CameraCaptureCallback r5 = new AnonymousClass1(this);
        if (r13.getOutputFormats().size() <= 1) goto L11;
        boolean r6 = true;
    L12:
        CameraCaptureCallback r7 = null;
        if (r4 == true) goto L19;
        r13.getImageReaderProxyProvider();
        if (r6 == false) goto L17;
        MetadataImageReader r32 = new MetadataImageReader(r02.getWidth(), r02.getHeight(), 256, 4);
        CameraCaptureCallback r72 = CameraCaptureCallbacks.createComboCallback(new CameraCaptureCallback[]{r5, r32.getCameraCaptureCallback()});
        MetadataImageReader r9 = new MetadataImageReader(r02.getWidth(), r02.getHeight(), 32, 4);
        CameraCaptureCallback[] r42 = {r5, r9.getCameraCaptureCallback()};
        r5 = r72;
        r7 = CameraCaptureCallbacks.createComboCallback(r42);
        MetadataImageReader r33 = r32;
    L18:
        Consumer<ProcessingRequest> r03 = new CaptureNode$$ExternalSyntheticLambda0(this);
        ImageReaderProxy r34 = r33;
    L20:
        r13.setCameraCaptureCallback(r5);
        if (r6 == false) goto L24;
        if (r7 == null) goto L24;
        r13.setSecondaryCameraCaptureCallback(r7);
    L24:
        Surface r1 = r34.getSurface();
        Objects.requireNonNull(r1);
        r13.setSurface(r1);
        this.mSafeCloseImageReaderProxy = new SafeCloseImageReaderProxy(r34);
        setOnImageAvailableListener(r34);
        r13.getPostviewSettings();
        if (r6 == false) goto L28;
        if (r9 == null) goto L28;
        r13.setSecondarySurface(r9.getSurface());
        this.mSecondarySafeCloseImageReaderProxy = new SafeCloseImageReaderProxy(r9);
        setOnImageAvailableListener(r9);
    L28:
        r13.getRequestEdge().setListener(r03);
        r13.getErrorEdge().setListener(new CaptureNode$$ExternalSyntheticLambda3(this));
        ProcessingNode.In r132 = ProcessingNode.In.of(r13.getInputFormat(), r13.getOutputFormats());
        this.mOutputEdge = r132;
        return r132;
    L17:
        MetadataImageReader r92 = new MetadataImageReader(r02.getWidth(), r02.getHeight(), r3, 4);
        r5 = CameraCaptureCallbacks.createComboCallback(new CameraCaptureCallback[]{r5, r92.getCameraCaptureCallback()});
        r33 = r92;
        r9 = null;
        goto L18
    L19:
        r13.getImageReaderProxyProvider();
        NoMetadataImageReader r12 = new NoMetadataImageReader(createImageReaderProxy(null, r02.getWidth(), r02.getHeight(), r3));
        this.mNoMetadataImageReader = r12;
        r03 = new CaptureNode$$ExternalSyntheticLambda1(this);
        r34 = r12;
        r9 = null;
        goto L20
    L11:
        r6 = false;
        goto L12
    L5:
        if (this.mSafeCloseImageReaderProxy != null) goto L7;
        r0 = true;
        goto L8
    }
}
