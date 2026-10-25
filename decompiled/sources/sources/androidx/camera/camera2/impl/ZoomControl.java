package androidx.camera.camera2.impl;

import androidx.camera.camera2.adapter.CoroutineAdaptersKt;
import androidx.camera.camera2.adapter.ZoomValue;
import androidx.camera.camera2.compat.ZoomCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u0012J/\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0019R\u0017\u0010\u001a\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u001b\u0010%\u001a\u00020 8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R!\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\"\u001a\u0004\b(\u0010)R\u0016\u0010+\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u001e\u00101\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u0001008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u0006038F¢\u0006\u0006\u001a\u0004\b4\u00105R(\u0010;\u001a\u0004\u0018\u00010-2\b\u0010\u0007\u001a\u0004\u0018\u00010-8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006<"}, d2 = {"Landroidx/camera/camera2/impl/ZoomControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "Landroidx/camera/camera2/compat/ZoomCompat;", "zoomCompat", "<init>", "(Landroidx/camera/camera2/compat/ZoomCompat;)V", "Landroidx/camera/core/ZoomState;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "setZoomState", "(Landroidx/camera/core/ZoomState;)V", "reset", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "zoomRatio", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "setZoomRatio", "(F)Lcom/google/common/util/concurrent/ListenableFuture;", "zoomState", _UrlKt.FRAGMENT_ENCODE_SET, "cancelPreviousTask", "shouldUpdateParameters", "applyZoomState", "(Landroidx/camera/core/ZoomState;ZZ)Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/camera2/compat/ZoomCompat;", "minZoomRatio", "F", "getMinZoomRatio", "()F", "maxZoomRatio", "getMaxZoomRatio", "Landroidx/camera/camera2/adapter/ZoomValue;", "defaultZoomState$delegate", "Lkotlin/Lazy;", "getDefaultZoomState", "()Landroidx/camera/camera2/adapter/ZoomValue;", "defaultZoomState", "Landroidx/lifecycle/MutableLiveData;", "_zoomState$delegate", "get_zoomState", "()Landroidx/lifecycle/MutableLiveData;", "_zoomState", "isInitialized", "Z", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "_requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "Lkotlinx/coroutines/CompletableDeferred;", "updateSignal", "Lkotlinx/coroutines/CompletableDeferred;", "Landroidx/lifecycle/LiveData;", "getZoomStateLiveData", "()Landroidx/lifecycle/LiveData;", "zoomStateLiveData", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "requestControl", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ZoomControl implements UseCaseCameraControl {
    private UseCaseCameraRequestControl _requestControl;
    private final Lazy _zoomState$delegate;
    private final Lazy defaultZoomState$delegate;
    private boolean isInitialized;
    private final float maxZoomRatio;
    private final float minZoomRatio;
    private CompletableDeferred<Unit> updateSignal;
    private final ZoomCompat zoomCompat;

    public static ZoomValue $r8$lambda$BWS4onPFqj89d9rEsJDFdaHue3k(ZoomControl r3) {
        return new ZoomValue(1.0f, r3.minZoomRatio, r3.maxZoomRatio);
    }

    public static MutableLiveData $r8$lambda$H_4sfzaMwmusxsDJ6U5XALRUdv4(ZoomControl r1) {
        return new MutableLiveData(r1.getDefaultZoomState());
    }

    public ZoomControl(ZoomCompat r2) {
        this.zoomCompat = r2;
        this.minZoomRatio = r2.getMinZoomRatio();
        this.maxZoomRatio = r2.getMaxZoomRatio();
        this.defaultZoomState$delegate = LazyKt.lazy(new ZoomControl$$ExternalSyntheticLambda0(this));
        this._zoomState$delegate = LazyKt.lazy(new ZoomControl$$ExternalSyntheticLambda1(this));
    }

    public static /* synthetic */ ListenableFuture applyZoomState$default(ZoomControl r1, ZoomState r2, boolean r3, boolean r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = true;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = true;
    L9:
        return r1.applyZoomState(r2, r3, r4);
    }

    private final MutableLiveData<ZoomState> get_zoomState() {
        return (MutableLiveData) this._zoomState$delegate.getValue();
    }

    private final void setZoomState(ZoomState r2) {
        if (Threads.isMainThread() == false) goto L6;
        get_zoomState().setValue(r2);
        return;
    L6:
        get_zoomState().postValue(r2);
    }

    public final ListenableFuture<Void> applyZoomState(ZoomState r6, boolean r7, boolean r8) {
        CompletableDeferred<Unit> r2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        CompletableDeferred<Unit> r3 = this.updateSignal;
        if (r3 == null) goto L7;
        if (r7 == false) goto L6;
        r3.completeExceptionally(new CameraControl.OperationCanceledException("Cancelled due to another zoom value being set."));
        goto L7
    L6:
        CoroutineAdaptersKt.propagateTo(r2, r3);
    L7:
        this.updateSignal = r2;
        setZoomState(r6);
        UseCaseCameraRequestControl r72 = getRequestControl();
        if (r72 == null) goto L14;
        float r62 = r6.getZoomRatio();
        ZoomCompat r5 = this.zoomCompat;
        if (r8 == false) goto L12;
        Deferred<Unit> r52 = r5.applyAsync(r62, r72);
    L13:
        CoroutineAdaptersKt.propagateTo(r52, r2);
    L16:
        return Futures.nonCancellationPropagating(CoroutineAdaptersKt.asListenableFuture$default(r2, null, 1, null));
    L12:
        r52 = r5.resetAsync(r72);
        goto L13
    L14:
        r2.completeExceptionally(new CameraControl.OperationCanceledException("Camera is not active."));
        goto L16
    }

    public final ZoomValue getDefaultZoomState() {
        return (ZoomValue) this.defaultZoomState$delegate.getValue();
    }

    public UseCaseCameraRequestControl getRequestControl() {
        return this._requestControl;
    }

    public final LiveData<ZoomState> getZoomStateLiveData() {
        return get_zoomState();
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        applyZoomState$default(this, getDefaultZoomState(), false, false, 6, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r5) {
        this._requestControl = r5;
        ZoomState r52 = get_zoomState().getValue();
        if (r52 != null) goto L6;
        r52 = getDefaultZoomState();
    L6:
        if (this.isInitialized == false) goto L8;
    L10:
        boolean r0 = true;
    L11:
        applyZoomState(r52, false, r0);
        this.isInitialized = true;
        return;
    L8:
        if (r52.getZoomRatio() != 1.0f) goto L10;
        r0 = false;
        goto L11
    }

    public final ListenableFuture<Void> setZoomRatio(float r10) {
        float r0 = this.maxZoomRatio;
        if (r10 > r0) goto L10;
        float r1 = this.minZoomRatio;
        if (r10 < r1) goto L10;
        return applyZoomState$default(this, new ZoomValue(r10, r1, r0), false, false, 6, null);
    L10:
        return Futures.immediateFailedFuture(new IllegalArgumentException("Requested zoomRatio " + r10 + " is not within valid range [" + this.minZoomRatio + ", " + this.maxZoomRatio + ']'));
    }
}
