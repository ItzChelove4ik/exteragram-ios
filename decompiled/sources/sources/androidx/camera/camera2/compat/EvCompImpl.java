package androidx.camera.camera2.compat;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.util.Range;
import android.util.Rational;
import androidx.camera.camera2.adapter.CoroutineAdaptersKt;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.ComboRequestListener;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.core.CameraControl;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J&\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0 2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Landroidx/camera/camera2/compat/EvCompImpl;", "Landroidx/camera/camera2/compat/EvCompCompat;", "cameraProperties", "Landroidx/camera/camera2/impl/CameraProperties;", "threads", "Landroidx/camera/camera2/impl/UseCaseThreads;", "comboRequestListener", "Landroidx/camera/camera2/impl/ComboRequestListener;", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/impl/ComboRequestListener;)V", "range", "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "getRange", "()Landroid/util/Range;", "supported", _UrlKt.FRAGMENT_ENCODE_SET, "getSupported", "()Z", "step", "Landroid/util/Rational;", "getStep", "()Landroid/util/Rational;", "updateSignal", "Lkotlinx/coroutines/CompletableDeferred;", "updateListener", "Landroidx/camera/camera2/pipe/Request$Listener;", "stopRunningTask", _UrlKt.FRAGMENT_ENCODE_SET, "throwable", _UrlKt.FRAGMENT_ENCODE_SET, "applyAsync", "Lkotlinx/coroutines/Deferred;", "evCompIndex", "requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "cancelPreviousTask", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EvCompImpl implements EvCompCompat {
    private final CameraProperties cameraProperties;
    private final ComboRequestListener comboRequestListener;
    private final Range<Integer> range;
    private final Rational step;
    private final boolean supported;
    private final UseCaseThreads threads;
    private Request.Listener updateListener;
    private CompletableDeferred<Integer> updateSignal;

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"androidx/camera/camera2/compat/EvCompImpl$applyAsync$3", "Landroidx/camera/camera2/pipe/Request$Listener;", "onComplete", _UrlKt.FRAGMENT_ENCODE_SET, "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "result", "Landroidx/camera/camera2/pipe/FrameInfo;", "onComplete-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.camera.camera2.compat.EvCompImpl$applyAsync$3, reason: invalid class name */
    public static final class AnonymousClass3 implements Request.Listener {
        final /* synthetic */ int $evCompIndex;
        final /* synthetic */ CompletableDeferred<Integer> $signal;

        public AnonymousClass3(int r1, CompletableDeferred<Integer> r2) {
            this.$evCompIndex = r1;
            this.$signal = r2;
        }

        @Override // androidx.camera.camera2.pipe.Request.Listener
        /* renamed from: onComplete-CcXjc1I */
        public void mo13onCompleteCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
            Integer r12 = (Integer) r4.getMetadata().get(CaptureResult.CONTROL_AE_STATE);
            Integer r22 = (Integer) r4.getMetadata().get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION);
            if (r12 == null) goto L16;
            if (r22 == null) goto L16;
            int r13 = r12.intValue();
            if (r13 != 2) goto L8;
        L12:
            int r14 = this.$evCompIndex;
            if (r22.intValue() != r14) goto L21;
            this.$signal.complete(Integer.valueOf(this.$evCompIndex));
            return;
        L21:
            return;
        L8:
            if (r13 == 3) goto L12;
            if (r13 == 4) goto L12;
            return;
        L16:
            if (r22 == null) goto L22;
            int r15 = this.$evCompIndex;
            if (r22.intValue() != r15) goto L23;
            this.$signal.complete(Integer.valueOf(this.$evCompIndex));
            return;
        L23:
            return;
        }
    }

    /* renamed from: $r8$lambda$YC4JQEnY8FmJcoebMHC7Y-8Vs38, reason: not valid java name */
    public static /* synthetic */ Unit m27$r8$lambda$YC4JQEnY8FmJcoebMHC7Y8Vs38(EvCompImpl r0, AnonymousClass3 r1, Throwable r2) {
        return applyAsync$lambda$2$0(r0, r1, r2);
    }

    public EvCompImpl(CameraProperties r2, UseCaseThreads r3, ComboRequestListener r4) {
        this.cameraProperties = r2;
        this.threads = r3;
        this.comboRequestListener = r4;
        this.range = (Range) r2.getMetadata().getOrDefault(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE, EvCompCompatKt.getEMPTY_RANGE());
        Integer r32 = (Integer) getRange().getUpper();
        if (r32 != null) goto L6;
    L7:
        Integer r33 = (Integer) getRange().getLower();
        if (r33 != null) goto L11;
    L12:
        boolean r34 = true;
    L14:
        this.supported = r34;
        if (getSupported() == true) goto L17;
        Rational r22 = Rational.ZERO;
    L18:
        this.step = r22;
        return;
    L17:
        r22 = (Rational) r2.getMetadata().get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
        goto L18
    L11:
        if (r33.intValue() != 0) goto L12;
    L13:
        r34 = false;
        goto L14
    L6:
        if (r32.intValue() == 0) goto L13;
        goto L7
    }

    private static final Unit applyAsync$lambda$2$0(EvCompImpl r0, AnonymousClass3 r1, Throwable r2) {
        r0.comboRequestListener.removeListener(r1);
        return Unit.INSTANCE;
    }

    @Override // androidx.camera.camera2.compat.EvCompCompat
    public Deferred<Integer> applyAsync(int r8, UseCaseCameraRequestControl r9, boolean r10) {
        CompletableDeferred<Integer> r0 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        CompletableDeferred<Integer> r2 = this.updateSignal;
        if (r2 == null) goto L7;
        if (r10 == false) goto L6;
        r2.completeExceptionally(new CameraControl.OperationCanceledException("Cancelled by another setExposureCompensationIndex()"));
        goto L7
    L6:
        CoroutineAdaptersKt.propagateTo(r0, r2);
    L7:
        this.updateSignal = r0;
        Request.Listener r102 = this.updateListener;
        if (r102 == null) goto L10;
        this.comboRequestListener.removeListener(r102);
        this.updateListener = null;
    L10:
        UseCaseCameraRequestControl.setParametersAsync$default(r9, MapsKt.mapOf(TuplesKt.to(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(r8))), null, null, 6, null);
        AnonymousClass3 r92 = new AnonymousClass3(r8, r0);
        this.comboRequestListener.addListener(r92, this.threads.getSequentialExecutor());
        r0.invokeOnCompletion(new EvCompImpl$$ExternalSyntheticLambda0(this, r92));
        this.updateListener = r92;
        return r0;
    }

    @Override // androidx.camera.camera2.compat.EvCompCompat
    public Range<Integer> getRange() {
        return this.range;
    }

    @Override // androidx.camera.camera2.compat.EvCompCompat
    public Rational getStep() {
        return this.step;
    }

    @Override // androidx.camera.camera2.compat.EvCompCompat
    public boolean getSupported() {
        return this.supported;
    }

    @Override // androidx.camera.camera2.compat.EvCompCompat
    public void stopRunningTask(Throwable r1) {
        CompletableDeferred<Integer> r0 = this.updateSignal;
        if (r0 == null) goto L6;
        r0.completeExceptionally(r1);
        return;
    }
}
