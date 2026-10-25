package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/compat/OpenCameraResult;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "cameraState", "Landroidx/camera/camera2/pipe/CameraError;", "errorCode", "<init>", "(Landroidx/camera/camera2/pipe/compat/AndroidCameraState;Landroidx/camera/camera2/pipe/CameraError;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "getCameraState", "()Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "Landroidx/camera/camera2/pipe/CameraError;", "getErrorCode-mVEW8x0", "()Landroidx/camera/camera2/pipe/CameraError;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OpenCameraResult {
    private final AndroidCameraState cameraState;
    private final CameraError errorCode;

    private OpenCameraResult(AndroidCameraState r1, CameraError r2) {
        this.cameraState = r1;
        this.errorCode = r2;
    }

    public /* synthetic */ OpenCameraResult(AndroidCameraState r2, CameraError r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 1) == 0) goto L6;
        r2 = null;
    L6:
        if ((r4 & 2) == 0) goto L8;
        r3 = null;
    L8:
        this(r2, r3, null);
    }

    public /* synthetic */ OpenCameraResult(AndroidCameraState r1, CameraError r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof OpenCameraResult) == true) goto L8;
        return false;
    L8:
        OpenCameraResult r52 = (OpenCameraResult) r5;
        if (Intrinsics.areEqual(this.cameraState, r52.cameraState) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.errorCode, r52.errorCode) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final AndroidCameraState getCameraState() {
        return this.cameraState;
    }

    /* renamed from: getErrorCode-mVEW8x0, reason: not valid java name */
    public final CameraError m465getErrorCodemVEW8x0() {
        return this.errorCode;
    }

    public int hashCode() {
        AndroidCameraState r0 = this.cameraState;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 * 31;
        CameraError r2 = this.errorCode;
        if (r2 == null) goto L11;
        r1 = CameraError.m179hashCodeimpl(r2.m182unboximpl());
    L11:
        return r03 + r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "OpenCameraResult(cameraState=" + this.cameraState + ", errorCode=" + this.errorCode + ')';
    }
}
