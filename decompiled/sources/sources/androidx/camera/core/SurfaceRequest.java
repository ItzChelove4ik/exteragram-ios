package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public final class SurfaceRequest {
    public static final Range<Integer> FRAME_RATE_RANGE_UNSPECIFIED = null;
    private final CameraInternal mCamera;
    private final DynamicRange mDynamicRange;
    private final Range<Integer> mExpectedFrameRate;
    private final DeferrableSurface mInternalDeferrableSurface;
    private final boolean mIsPrimary;
    private final Object mLock;
    private final CallbackToFutureAdapter.Completer<Void> mRequestCancellationCompleter;
    private final Size mResolution;
    private final ListenableFuture<Void> mSessionStatusFuture;
    private final int mSessionType;
    private final CallbackToFutureAdapter.Completer<Surface> mSurfaceCompleter;
    final ListenableFuture<Surface> mSurfaceFuture;
    private final CallbackToFutureAdapter.Completer<Void> mSurfaceRecreationCompleter;
    private TransformationInfo mTransformationInfo;
    private Executor mTransformationInfoExecutor;
    private TransformationInfoListener mTransformationInfoListener;

    /* renamed from: androidx.camera.core.SurfaceRequest$1, reason: invalid class name */
    public class AnonymousClass1 implements FutureCallback<Void> {
        final /* synthetic */ SurfaceRequest this$0;
        final /* synthetic */ CallbackToFutureAdapter.Completer val$requestCancellationCompleter;
        final /* synthetic */ ListenableFuture val$requestCancellationFuture;

        public AnonymousClass1(SurfaceRequest r1, CallbackToFutureAdapter.Completer r2, ListenableFuture r3) {
            this.this$0 = r1;
            this.val$requestCancellationCompleter = r2;
            this.val$requestCancellationFuture = r3;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable r1) {
            if ((r1 instanceof RequestCancelledException) == false) goto L6;
            Preconditions.checkState(this.val$requestCancellationFuture.cancel(false));
            return;
        L6:
            Preconditions.checkState(this.val$requestCancellationCompleter.set(null));
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(Void r1) {
            onSuccess2(r1);
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(Void r1) {
            Preconditions.checkState(this.val$requestCancellationCompleter.set(null));
        }
    }

    /* renamed from: androidx.camera.core.SurfaceRequest$2, reason: invalid class name */
    public class AnonymousClass2 extends DeferrableSurface {
        final /* synthetic */ SurfaceRequest this$0;

        public AnonymousClass2(SurfaceRequest r1, Size r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.camera.core.impl.DeferrableSurface
        public ListenableFuture<Surface> provideSurface() {
            return this.this$0.mSurfaceFuture;
        }
    }

    /* renamed from: androidx.camera.core.SurfaceRequest$3, reason: invalid class name */
    public class AnonymousClass3 implements FutureCallback<Surface> {
        final /* synthetic */ SurfaceRequest this$0;
        final /* synthetic */ CallbackToFutureAdapter.Completer val$sessionStatusCompleter;
        final /* synthetic */ String val$surfaceRequestString;
        final /* synthetic */ ListenableFuture val$terminationFuture;

        public AnonymousClass3(SurfaceRequest r1, ListenableFuture r2, CallbackToFutureAdapter.Completer r3, String r4) {
            this.this$0 = r1;
            this.val$terminationFuture = r2;
            this.val$sessionStatusCompleter = r3;
            this.val$surfaceRequestString = r4;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable r4) {
            boolean r0 = r4 instanceof CancellationException;
            CallbackToFutureAdapter.Completer r1 = this.val$sessionStatusCompleter;
            if (r0 == false) goto L6;
            Preconditions.checkState(r1.setException(new RequestCancelledException(this.val$surfaceRequestString + " cancelled.", r4)));
            return;
        L6:
            r1.set(null);
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(Surface r1) {
            Futures.propagate(this.val$terminationFuture, this.val$sessionStatusCompleter);
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(Surface r1) {
            onSuccess2(r1);
        }
    }

    /* renamed from: androidx.camera.core.SurfaceRequest$4, reason: invalid class name */
    public class AnonymousClass4 implements FutureCallback<Void> {
        final /* synthetic */ SurfaceRequest this$0;
        final /* synthetic */ Consumer val$resultListener;
        final /* synthetic */ Surface val$surface;

        public AnonymousClass4(SurfaceRequest r1, Consumer r2, Surface r3) {
            this.this$0 = r1;
            this.val$resultListener = r2;
            this.val$surface = r3;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable r4) {
            Preconditions.checkState(r4 instanceof RequestCancelledException, "Camera surface session should only fail with request cancellation. Instead failed due to:\n" + r4);
            this.val$resultListener.accept(Result.of(1, this.val$surface));
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(Void r1) {
            onSuccess2(r1);
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(Void r2) {
            this.val$resultListener.accept(Result.of(0, this.val$surface));
        }
    }

    /* renamed from: androidx.camera.core.SurfaceRequest$5, reason: invalid class name */
    public class AnonymousClass5 implements FutureCallback<Void> {
        final /* synthetic */ SurfaceRequest this$0;
        final /* synthetic */ Runnable val$runnable;

        public AnonymousClass5(SurfaceRequest r1, Runnable r2) {
            this.this$0 = r1;
            this.val$runnable = r2;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable r1) {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public /* bridge */ /* synthetic */ void onSuccess(Void r1) {
            onSuccess2(r1);
        }

        /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
        public void onSuccess2(Void r1) {
            this.val$runnable.run();
        }
    }

    public static final class RequestCancelledException extends RuntimeException {
        public RequestCancelledException(String r1, Throwable r2) {
            super(r1, r2);
        }
    }

    public static abstract class Result {
        public Result() {
        }

        public static Result of(int r1, Surface r2) {
            return new AutoValue_SurfaceRequest_Result(r1, r2);
        }

        public abstract int getResultCode();

        public abstract Surface getSurface();
    }

    public static abstract class TransformationInfo {
        public TransformationInfo() {
        }

        public static TransformationInfo of(Rect r7, int r8, int r9, boolean r10, Matrix r11, boolean r12) {
            return new AutoValue_SurfaceRequest_TransformationInfo(r7, r8, r9, r10, r11, r12);
        }

        public abstract Rect getCropRect();

        public abstract int getRotationDegrees();

        public abstract Matrix getSensorToBufferTransform();

        public abstract int getTargetRotation();

        public abstract boolean hasCameraTransform();

        public abstract boolean isMirroring();
    }

    public interface TransformationInfoListener {
        void onTransformationInfoUpdate(TransformationInfo r1);
    }

    /* renamed from: $r8$lambda$KNGGnEiW12GWMzWpgmzm9z6-pio, reason: not valid java name */
    public static /* synthetic */ Object m587$r8$lambda$KNGGnEiW12GWMzWpgmzm9z6pio(AtomicReference r0, String r1, CallbackToFutureAdapter.Completer r2) {
        r0.set(r2);
        return r1 + "-cancellation";
    }

    public static /* synthetic */ void $r8$lambda$M2tbU8TrMTQzqYWo8zAkaDeBs7A(TransformationInfoListener r0, TransformationInfo r1) {
        r0.onTransformationInfoUpdate(r1);
    }

    public static /* synthetic */ Object $r8$lambda$PdTxhok2MCr5fAnjoMvzFjww1Gw(AtomicReference r0, String r1, CallbackToFutureAdapter.Completer r2) {
        r0.set(r2);
        return r1 + "-Surface";
    }

    public static /* synthetic */ void $r8$lambda$Pr87hwxhzyKKen00tfFTcKgpB14(SurfaceRequest r1) {
        r1.mSurfaceFuture.cancel(true);
    }

    public static /* synthetic */ void $r8$lambda$gnTcRTvMq8APepfC1y2c2wsfrgo(Consumer r1, Surface r2) {
        r1.accept(Result.of(3, r2));
    }

    /* renamed from: $r8$lambda$lW3msuxNLPi-8yuGJSbbC4iUWKA, reason: not valid java name */
    public static /* synthetic */ void m588$r8$lambda$lW3msuxNLPi8yuGJSbbC4iUWKA(Consumer r1, Surface r2) {
        r1.accept(Result.of(2, r2));
    }

    public static /* synthetic */ Object $r8$lambda$rYZkifWp9Ieh38jTetbEotRLXFQ(SurfaceRequest r0, AtomicReference r1, CallbackToFutureAdapter.Completer r2) {
        r0.getClass();
        r1.set(r2);
        return "SurfaceRequest-surface-recreation(" + r0.hashCode() + ")";
    }

    public static /* synthetic */ void $r8$lambda$saccxOP2WYkrqHx2sKIVeLUcN40(TransformationInfoListener r0, TransformationInfo r1) {
        r0.onTransformationInfoUpdate(r1);
    }

    public static /* synthetic */ void $r8$lambda$tchDN8zIi6IhgOlSWZQyc2_Qvng(Consumer r1, Surface r2) {
        r1.accept(Result.of(4, r2));
    }

    public static /* synthetic */ Object $r8$lambda$uGrSSZ1g66FkCiLouPIMRcy7vLg(AtomicReference r0, String r1, CallbackToFutureAdapter.Completer r2) {
        r0.set(r2);
        return r1 + "-status";
    }

    static {
        FRAME_RATE_RANGE_UNSPECIFIED = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
    }

    public SurfaceRequest(Size r3, CameraInternal r4, boolean r5, DynamicRange r6, int r7, Range<Integer> r8, Runnable r9) {
        this.mLock = new Object();
        this.mResolution = r3;
        this.mCamera = r4;
        this.mIsPrimary = r5;
        Preconditions.checkArgument(r6.isFullySpecified(), "SurfaceRequest's DynamicRange must always be fully specified.");
        this.mDynamicRange = r6;
        this.mSessionType = r7;
        this.mExpectedFrameRate = r8;
        String r42 = "SurfaceRequest[size: " + r3 + ", id: " + hashCode() + "]";
        AtomicReference r52 = new AtomicReference(null);
        ListenableFuture r72 = CallbackToFutureAdapter.getFuture(new SurfaceRequest$$ExternalSyntheticLambda1(r52, r42));
        CallbackToFutureAdapter.Completer<Void> r53 = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) r52.get());
        this.mRequestCancellationCompleter = r53;
        AtomicReference r82 = new AtomicReference(null);
        ListenableFuture<Void> r0 = CallbackToFutureAdapter.getFuture(new SurfaceRequest$$ExternalSyntheticLambda2(r82, r42));
        this.mSessionStatusFuture = r0;
        Futures.addCallback(r0, new AnonymousClass1(this, r53, r72), CameraXExecutors.directExecutor());
        CallbackToFutureAdapter.Completer r54 = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) r82.get());
        AtomicReference r73 = new AtomicReference(null);
        ListenableFuture<Surface> r62 = CallbackToFutureAdapter.getFuture(new SurfaceRequest$$ExternalSyntheticLambda3(r73, r42));
        this.mSurfaceFuture = r62;
        this.mSurfaceCompleter = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) r73.get());
        AnonymousClass2 r74 = new AnonymousClass2(this, r3, 34);
        this.mInternalDeferrableSurface = r74;
        ListenableFuture<Void> r32 = r74.getTerminationFuture();
        Futures.addCallback(r62, new AnonymousClass3(this, r32, r54, r42), CameraXExecutors.directExecutor());
        r32.addListener(new SurfaceRequest$$ExternalSyntheticLambda4(this), CameraXExecutors.directExecutor());
        this.mSurfaceRecreationCompleter = initialSurfaceRecreationCompleter(CameraXExecutors.directExecutor(), r9);
    }

    private CallbackToFutureAdapter.Completer<Void> initialSurfaceRecreationCompleter(Executor r4, Runnable r5) {
        AtomicReference r0 = new AtomicReference(null);
        Futures.addCallback(CallbackToFutureAdapter.getFuture(new SurfaceRequest$$ExternalSyntheticLambda9(this, r0)), new AnonymousClass5(this, r5), r4);
        return (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) r0.get());
    }

    public void clearTransformationInfoListener() {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mTransformationInfoListener = null;     // Catch: Throwable -> L8
        this.mTransformationInfoExecutor = null;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public DeferrableSurface getDeferrableSurface() {
        return this.mInternalDeferrableSurface;
    }

    public DynamicRange getDynamicRange() {
        return this.mDynamicRange;
    }

    public Size getResolution() {
        return this.mResolution;
    }

    public boolean isPrimary() {
        return this.mIsPrimary;
    }

    public void provideSurface(Surface r3, Executor r4, Consumer<Result> r5) {
        if (r3.isValid() == true) goto L7;
        r4.execute(new SurfaceRequest$$ExternalSyntheticLambda6(r5, r3));
        return;
    L7:
        if (this.mSurfaceCompleter.set(r3) == false) goto L9;
    L16:
        Futures.addCallback(this.mSessionStatusFuture, new AnonymousClass4(this, r5, r3), r4);
        return;
    L9:
        if (this.mSurfaceFuture.isCancelled() == true) goto L16;
        Preconditions.checkState(this.mSurfaceFuture.isDone());
        this.mSurfaceFuture.get();     // Catch: Throwable -> L14
        r4.execute(new SurfaceRequest$$ExternalSyntheticLambda7(r5, r3));     // Catch: Throwable -> L14
        return;
    L14:
        r4.execute(new SurfaceRequest$$ExternalSyntheticLambda8(r5, r3));
    }

    public void setTransformationInfoListener(Executor r2, TransformationInfoListener r3) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mTransformationInfoListener = r3;     // Catch: Throwable -> L9
        this.mTransformationInfoExecutor = r2;     // Catch: Throwable -> L9
        TransformationInfo r1 = this.mTransformationInfo;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r1 == null) goto L14;
        r2.execute(new SurfaceRequest$$ExternalSyntheticLambda5(r3, r1));
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public void updateTransformationInfo(TransformationInfo r3) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mTransformationInfo = r3;     // Catch: Throwable -> L10
        TransformationInfoListener r1 = this.mTransformationInfoListener;     // Catch: Throwable -> L10
        Executor r2 = this.mTransformationInfoExecutor;     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        if (r1 == null) goto L15;
        if (r2 == null) goto L16;
        r2.execute(new SurfaceRequest$$ExternalSyntheticLambda0(r1, r3));
        return;
    L16:
        return;
    L15:
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public boolean willNotProvideSurface() {
        return this.mSurfaceCompleter.setException(new DeferrableSurface.SurfaceUnavailableException("Surface request will not complete."));
    }
}
