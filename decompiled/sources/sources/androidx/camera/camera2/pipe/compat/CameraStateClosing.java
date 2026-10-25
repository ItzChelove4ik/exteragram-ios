package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0080\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraStateClosing;", "Landroidx/camera/camera2/pipe/compat/CameraState;", "Landroidx/camera/camera2/pipe/CameraError;", "cameraErrorCode", "<init>", "(Landroidx/camera/camera2/pipe/CameraError;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/CameraError;", "getCameraErrorCode-mVEW8x0", "()Landroidx/camera/camera2/pipe/CameraError;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraStateClosing extends CameraState {
    private final CameraError cameraErrorCode;

    private CameraStateClosing(CameraError r2) {
        super(null);
        this.cameraErrorCode = r2;
    }

    public /* synthetic */ CameraStateClosing(CameraError r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = null;
    L5:
        this(r1, null);
    }

    public /* synthetic */ CameraStateClosing(CameraError r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof CameraStateClosing) == true) goto L9;
        return false;
    L9:
        if (Intrinsics.areEqual(this.cameraErrorCode, ((CameraStateClosing) r4).cameraErrorCode) == true) goto L11;
        return false;
    L11:
        return true;
    }

    /* renamed from: getCameraErrorCode-mVEW8x0, reason: not valid java name */
    public final CameraError m463getCameraErrorCodemVEW8x0() {
        return this.cameraErrorCode;
    }

    public int hashCode() {
        CameraError r0 = this.cameraErrorCode;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return CameraError.m179hashCodeimpl(r0.m182unboximpl());
    }

    public String toString() {
        return "CameraStateClosing(cameraErrorCode=" + this.cameraErrorCode + ')';
    }
}
