package androidx.camera.camera2.impl;

import androidx.camera.camera2.adapter.EvCompValue;
import androidx.camera.camera2.compat.EvCompCompat;
import androidx.camera.core.CameraControl;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00132\u0006\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R$\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001d\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R(\u0010*\u001a\u0004\u0018\u00010#2\b\u0010\u0017\u001a\u0004\u0018\u00010#8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006+"}, d2 = {"Landroidx/camera/camera2/impl/EvCompControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "Landroidx/camera/camera2/compat/EvCompCompat;", "compat", "<init>", "(Landroidx/camera/camera2/compat/EvCompCompat;)V", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "createFailureResult", "(Ljava/lang/Exception;)Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "reset", "()V", "exposureIndex", _UrlKt.FRAGMENT_ENCODE_SET, "cancelPreviousTask", "Lkotlinx/coroutines/Deferred;", "updateAsync", "(IZ)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/compat/EvCompCompat;", "value", "evCompIndex", "I", "setEvCompIndex", "(I)V", "Landroidx/camera/camera2/adapter/EvCompValue;", "exposureState", "Landroidx/camera/camera2/adapter/EvCompValue;", "getExposureState", "()Landroidx/camera/camera2/adapter/EvCompValue;", "setExposureState", "(Landroidx/camera/camera2/adapter/EvCompValue;)V", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "_requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "requestControl", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEvCompControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EvCompControl.kt\nandroidx/camera/camera2/impl/EvCompControl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,104:1\n1#2:105\n*E\n"})
/* loaded from: classes3.dex */
public final class EvCompControl implements UseCaseCameraControl {
    private UseCaseCameraRequestControl _requestControl;
    private final EvCompCompat compat;
    private int evCompIndex;
    private EvCompValue exposureState;

    public EvCompControl(EvCompCompat r5) {
        this.compat = r5;
        this.exposureState = new EvCompValue(r5.getSupported(), this.evCompIndex, r5.getRange(), r5.getStep());
    }

    private final CompletableDeferred<Integer> createFailureResult(Exception r2) {
        CompletableDeferred<Integer> r1 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        r1.completeExceptionally(r2);
        return r1;
    }

    private final void setEvCompIndex(int r2) {
        this.evCompIndex = r2;
        this.exposureState = this.exposureState.updateIndex$camera_camera2(r2);
    }

    public static /* synthetic */ Deferred updateAsync$default(EvCompControl r0, int r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = true;
    L6:
        return r0.updateAsync(r1, r2);
    }

    public UseCaseCameraRequestControl getRequestControl() {
        return this._requestControl;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        setEvCompIndex(0);
        updateAsync$default(this, 0, false, 2, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r2) {
        this._requestControl = r2;
        updateAsync(this.evCompIndex, false);
    }

    public final Deferred<Integer> updateAsync(int r3, boolean r4) {
        if (this.compat.getSupported() == true) goto L7;
        return createFailureResult(new IllegalArgumentException("ExposureCompensation is not supported"));
    L7:
        if (this.compat.getRange().contains(Integer.valueOf(r3)) == false) goto L9;
        UseCaseCameraRequestControl r0 = getRequestControl();
        if (r0 == null) goto L16;
        setEvCompIndex(r3);
        Deferred<Integer> r32 = this.compat.applyAsync(r3, r0, r4);
        if (r32 == null) goto L16;
        return r32;
    L16:
        CameraControl.OperationCanceledException r33 = new CameraControl.OperationCanceledException("Camera is not active.");
        this.compat.stopRunningTask(r33);
        return createFailureResult(r33);
    L9:
        return createFailureResult(new IllegalArgumentException("Requested ExposureCompensation " + r3 + " is not within valid range [" + this.compat.getRange().getUpper() + " .. " + this.compat.getRange().getLower() + ']'));
    }
}
