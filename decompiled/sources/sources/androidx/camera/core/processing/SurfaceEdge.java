package androidx.camera.core.processing;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class SurfaceEdge {
    private final Rect mCropRect;
    private final int mFormat;
    private final boolean mHasCameraTransform;
    private boolean mHasConsumer;
    private boolean mIsClosed;
    private final boolean mMirroring;
    private final Set<Runnable> mOnInvalidatedListeners;
    private SurfaceRequest mProviderSurfaceRequest;
    private int mRotationDegrees;
    private final Matrix mSensorToBufferTransform;
    private SettableSurface mSettableSurface;
    private final StreamSpec mStreamSpec;
    private int mTargetRotation;
    private final int mTargets;
    private final List<Consumer<SurfaceRequest.TransformationInfo>> mTransformationUpdatesListeners;

    public static class SettableSurface extends DeferrableSurface {
        CallbackToFutureAdapter.Completer<Surface> mCompleter;
        private SurfaceOutputImpl mConsumer;
        private DeferrableSurface mProvider;
        final ListenableFuture<Surface> mSurfaceFuture;

        /* renamed from: $r8$lambda$MbwWKPmNY-V-cJmML8QCwwbJ_Io, reason: not valid java name */
        public static /* synthetic */ void m614$r8$lambda$MbwWKPmNYVcJmML8QCwwbJ_Io(SettableSurface r1) {
            SurfaceOutputImpl r0 = r1.mConsumer;
            if (r0 == null) goto L6;
            r0.requestClose();
        L6:
            if (r1.mProvider != null) goto L8;
            r1.mCompleter.setCancelled();
        L8:
            r1.mProvider = null;
        }

        /* renamed from: $r8$lambda$VdiGildDqQ-QQLDZiSabHKVcb7o, reason: not valid java name */
        public static /* synthetic */ Object m615$r8$lambda$VdiGildDqQQQLDZiSabHKVcb7o(SettableSurface r1, CallbackToFutureAdapter.Completer r2) {
            r1.mCompleter = r2;
            return "SettableFuture hashCode: " + r1.hashCode();
        }

        public SettableSurface(Size r1, int r2) {
            super(r1, r2);
            this.mSurfaceFuture = CallbackToFutureAdapter.getFuture(new SurfaceEdge$SettableSurface$$ExternalSyntheticLambda1(this));
        }

        public boolean canSetProvider() {
            Threads.checkMainThread();
            if (this.mProvider == null) goto L5;
            return false;
        L5:
            if (isClosed() == true) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.camera.core.impl.DeferrableSurface
        public void close() {
            super.close();
            Threads.runOnMain(new SurfaceEdge$SettableSurface$$ExternalSyntheticLambda0(this));
        }

        @Override // androidx.camera.core.impl.DeferrableSurface
        public ListenableFuture<Surface> provideSurface() {
            return this.mSurfaceFuture;
        }

        public void setConsumer(SurfaceOutputImpl r3) {
            if (this.mConsumer != null) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkState(r0, "Consumer can only be linked once.");
            this.mConsumer = r3;
            return;
        L5:
            r0 = false;
            goto L6
        }

        public boolean setProvider(DeferrableSurface r6, Runnable r7) {
            Threads.checkMainThread();
            Preconditions.checkNotNull(r6);
            DeferrableSurface r0 = this.mProvider;
            boolean r1 = false;
            if (r0 != r6) goto L6;
            return false;
        L6:
            if (r0 != null) goto L8;
            boolean r02 = true;
        L9:
            Preconditions.checkState(r02, "A different provider has been set. To change the provider, call SurfaceEdge#invalidate before calling SurfaceEdge#setProvider");
            Preconditions.checkArgument(getPrescribedSize().equals(r6.getPrescribedSize()), String.format("The provider's size(%s) must match the parent(%s)", new Object[]{getPrescribedSize(), r6.getPrescribedSize()}));
            if (getPrescribedStreamFormat() != r6.getPrescribedStreamFormat()) goto L12;
            r1 = true;
        L12:
            Preconditions.checkArgument(r1, String.format("The provider's format(%s) must match the parent(%s)", new Object[]{Integer.valueOf(getPrescribedStreamFormat()), Integer.valueOf(r6.getPrescribedStreamFormat())}));
            Preconditions.checkState(!isClosed(), "The parent is closed. Call SurfaceEdge#invalidate() before setting a new provider.");
            this.mProvider = r6;
            Futures.propagate(r6.getSurface(), this.mCompleter);
            r6.incrementUseCount();
            getTerminationFuture().addListener(new SurfaceEdge$SettableSurface$$ExternalSyntheticLambda2(r6), CameraXExecutors.directExecutor());
            r6.getCloseFuture().addListener(r7, CameraXExecutors.mainThreadExecutor());
            return true;
        L8:
            r02 = false;
            goto L9
        }
    }

    /* renamed from: $r8$lambda$OUGeMRYPeF_WOgV3sPe-eq2_z7c, reason: not valid java name */
    public static /* synthetic */ void m612$r8$lambda$OUGeMRYPeF_WOgV3sPeeq2_z7c(SurfaceEdge r2) {
        r2.getClass();
        CameraXExecutors.mainThreadExecutor().execute(new SurfaceEdge$$ExternalSyntheticLambda5(r2));
    }

    /* renamed from: $r8$lambda$QHdR7T3HmMsEDLpE-k-RmCFFpf4, reason: not valid java name */
    public static /* synthetic */ void m613$r8$lambda$QHdR7T3HmMsEDLpEkRmCFFpf4(SurfaceEdge r1) {
        if (r1.mIsClosed == true) goto L6;
        r1.invalidate();
        return;
    }

    public static /* synthetic */ void $r8$lambda$acvOh1hWo1mq4ltOJwRynCvDZVQ(SurfaceEdge r2, int r3, int r4) {
        boolean r1 = true;
        if (r2.mRotationDegrees == r3) goto L5;
        r2.mRotationDegrees = r3;
        boolean r32 = true;
    L7:
        if (r2.mTargetRotation == r4) goto L9;
        r2.mTargetRotation = r4;
    L10:
        if (r1 == false) goto L13;
        r2.notifyTransformationInfoUpdate();
        return;
    L13:
        return;
    L9:
        r1 = r32;
        goto L10
    L5:
        r32 = false;
        goto L7
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$fmIccKEiiPDQZ5pv_vs05SRNksM(SurfaceEdge r8, SettableSurface r9, int r10, SurfaceOutput.CameraInputInfo r11, SurfaceOutput.CameraInputInfo r12, Surface r13) {
        r8.getClass();
        Preconditions.checkNotNull(r13);
        r9.incrementUseCount();     // Catch: DeferrableSurface.SurfaceClosedException -> L6
        SurfaceOutputImpl r0 = new SurfaceOutputImpl(r13, r8.getTargets(), r10, r8.mStreamSpec.getResolution(), r11, r12, r8.mSensorToBufferTransform);
        r0.getCloseFuture().addListener(new SurfaceEdge$$ExternalSyntheticLambda6(r9), CameraXExecutors.directExecutor());
        r9.setConsumer(r0);
        return Futures.immediateFuture(r0);
    L6:
        e = move-exception;
        return Futures.immediateFailedFuture(e);
    }

    public SurfaceEdge(int r3, int r4, StreamSpec r5, Matrix r6, boolean r7, Rect r8, int r9, int r10, boolean r11) {
        this.mHasConsumer = false;
        this.mOnInvalidatedListeners = new HashSet();
        this.mIsClosed = false;
        this.mTransformationUpdatesListeners = new ArrayList();
        this.mTargets = r3;
        this.mFormat = r4;
        this.mStreamSpec = r5;
        this.mSensorToBufferTransform = r6;
        this.mHasCameraTransform = r7;
        this.mCropRect = r8;
        this.mRotationDegrees = r9;
        this.mTargetRotation = r10;
        this.mMirroring = r11;
        this.mSettableSurface = new SettableSurface(r5.getResolution(), r4);
    }

    private void checkAndSetHasConsumer() {
        Preconditions.checkState(!this.mHasConsumer, "Consumer can only be linked once.");
        this.mHasConsumer = true;
    }

    private void checkNotClosed() {
        Preconditions.checkState(!this.mIsClosed, "Edge is already closed.");
    }

    private void notifyTransformationInfoUpdate() {
        Threads.checkMainThread();
        SurfaceRequest.TransformationInfo r0 = SurfaceRequest.TransformationInfo.of(this.mCropRect, this.mRotationDegrees, this.mTargetRotation, hasCameraTransform(), this.mSensorToBufferTransform, this.mMirroring);
        SurfaceRequest r1 = this.mProviderSurfaceRequest;
        if (r1 == null) goto L5;
        r1.updateTransformationInfo(r0);
    L5:
        Iterator<Consumer<SurfaceRequest.TransformationInfo>> r6 = this.mTransformationUpdatesListeners.iterator();
    L7:
        if (r6.hasNext() == false) goto L9;
        r6.next().accept(r0);
        goto L7
    }

    public void addOnInvalidatedListener(Runnable r1) {
        Threads.checkMainThread();
        checkNotClosed();
        this.mOnInvalidatedListeners.add(r1);
    }

    public void addTransformationUpdateListener(Consumer<SurfaceRequest.TransformationInfo> r1) {
        Preconditions.checkNotNull(r1);
        this.mTransformationUpdatesListeners.add(r1);
    }

    public final void close() {
        Threads.checkMainThread();
        this.mSettableSurface.close();
        this.mIsClosed = true;
        this.mTransformationUpdatesListeners.clear();
        this.mOnInvalidatedListeners.clear();
    }

    public ListenableFuture<SurfaceOutput> createSurfaceOutputFuture(int r8, SurfaceOutput.CameraInputInfo r9, SurfaceOutput.CameraInputInfo r10) {
        Threads.checkMainThread();
        checkNotClosed();
        checkAndSetHasConsumer();
        SettableSurface r2 = this.mSettableSurface;
        return Futures.transformAsync(r2.getSurface(), new SurfaceEdge$$ExternalSyntheticLambda3(this, r2, r8, r9, r10), CameraXExecutors.mainThreadExecutor());
    }

    public SurfaceRequest createSurfaceRequest(CameraInternal r2) {
        return createSurfaceRequest(r2, true);
    }

    public SurfaceRequest createSurfaceRequest(CameraInternal r10, boolean r11) {
        Threads.checkMainThread();
        checkNotClosed();
        SurfaceRequest r1 = new SurfaceRequest(this.mStreamSpec.getResolution(), r10, r11, this.mStreamSpec.getDynamicRange(), this.mStreamSpec.getSessionType(), this.mStreamSpec.getExpectedFrameRateRange(), new SurfaceEdge$$ExternalSyntheticLambda0(this));
        DeferrableSurface r102 = r1.getDeferrableSurface();     // Catch: RuntimeException -> L7 DeferrableSurface.SurfaceClosedException -> L13
        SettableSurface r112 = this.mSettableSurface;     // Catch: RuntimeException -> L7 DeferrableSurface.SurfaceClosedException -> L13
        Objects.requireNonNull(r112);     // Catch: RuntimeException -> L7 DeferrableSurface.SurfaceClosedException -> L13
        if (r112.setProvider(r102, new SurfaceEdge$$ExternalSyntheticLambda1(r112)) == false) goto L9;
        ListenableFuture<Void> r113 = r112.getTerminationFuture();     // Catch: RuntimeException -> L7 DeferrableSurface.SurfaceClosedException -> L13
        Objects.requireNonNull(r102);     // Catch: RuntimeException -> L7 DeferrableSurface.SurfaceClosedException -> L13
        r113.addListener(new SurfaceEdge$$ExternalSyntheticLambda2(r102), CameraXExecutors.directExecutor());     // Catch: RuntimeException -> L7 DeferrableSurface.SurfaceClosedException -> L13
    L9:
        this.mProviderSurfaceRequest = r1;
        notifyTransformationInfoUpdate();
        return r1;
    L13:
        e = move-exception;
        throw new AssertionError("Surface is somehow already closed", e);
    L7:
        e = move-exception;
        r1.willNotProvideSurface();
        throw e;
    }

    public final void disconnect() {
        Threads.checkMainThread();
        checkNotClosed();
        this.mSettableSurface.close();
    }

    public Rect getCropRect() {
        return this.mCropRect;
    }

    public DeferrableSurface getDeferrableSurface() {
        Threads.checkMainThread();
        checkNotClosed();
        checkAndSetHasConsumer();
        return this.mSettableSurface;
    }

    public int getRotationDegrees() {
        return this.mRotationDegrees;
    }

    public Matrix getSensorToBufferTransform() {
        return this.mSensorToBufferTransform;
    }

    public StreamSpec getStreamSpec() {
        return this.mStreamSpec;
    }

    public int getTargets() {
        return this.mTargets;
    }

    public boolean hasCameraTransform() {
        return this.mHasCameraTransform;
    }

    public void invalidate() {
        Threads.checkMainThread();
        checkNotClosed();
        if (this.mSettableSurface.canSetProvider() == true) goto L9;
        this.mHasConsumer = false;
        this.mSettableSurface.close();
        this.mSettableSurface = new SettableSurface(this.mStreamSpec.getResolution(), this.mFormat);
        Iterator<Runnable> r3 = this.mOnInvalidatedListeners.iterator();
    L7:
        if (r3.hasNext() == false) goto L11;
        r3.next().run();
        goto L7
    L11:
        return;
    }

    public boolean isMirroring() {
        return this.mMirroring;
    }

    public void setProvider(DeferrableSurface r2) {
        Threads.checkMainThread();
        checkNotClosed();
        SettableSurface r1 = this.mSettableSurface;
        Objects.requireNonNull(r1);
        r1.setProvider(r2, new SurfaceEdge$$ExternalSyntheticLambda1(r1));
    }

    public String toString() {
        return "SurfaceEdge{targets=" + this.mTargets + ", format=" + this.mFormat + ", resolution=" + this.mStreamSpec.getResolution() + ", cropRect=" + this.mCropRect + ", rotationDegrees=" + this.mRotationDegrees + ", mirroring=" + this.mMirroring + ", sensorToBufferTransform= " + this.mSensorToBufferTransform + ", rotationInTransform= " + TransformUtils.getRotationDegrees(this.mSensorToBufferTransform) + ", isMirrorInTransform= " + TransformUtils.isMirrored(this.mSensorToBufferTransform) + ", isClosed=" + this.mIsClosed + '}';
    }

    public void updateTransformation(int r2, int r3) {
        Threads.runOnMain(new SurfaceEdge$$ExternalSyntheticLambda4(this, r2, r3));
    }
}
