package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.DurationNs;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0080\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b#\u0010$R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010(\u001a\u0004\b)\u0010*R\u0019\u0010\f\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\f\u0010%\u001a\u0004\b+\u0010'R\u0019\u0010\r\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\r\u0010%\u001a\u0004\b,\u0010'R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u000e\u0010%\u001a\u0004\b-\u0010'R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010.\u001a\u0004\b/\u00100¨\u00061"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraStateClosed;", "Landroidx/camera/camera2/pipe/compat/CameraState;", "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", "Landroidx/camera/camera2/pipe/compat/ClosedReason;", "cameraClosedReason", _UrlKt.FRAGMENT_ENCODE_SET, "cameraRetryCount", "Landroidx/camera/camera2/pipe/core/DurationNs;", "cameraRetryDurationNs", _UrlKt.FRAGMENT_ENCODE_SET, "cameraException", "cameraOpenDurationNs", "cameraActiveDurationNs", "cameraClosingDurationNs", "Landroidx/camera/camera2/pipe/CameraError;", "cameraErrorCode", "<init>", "(Ljava/lang/String;Landroidx/camera/camera2/pipe/compat/ClosedReason;Ljava/lang/Integer;Landroidx/camera/camera2/pipe/core/DurationNs;Ljava/lang/Throwable;Landroidx/camera/camera2/pipe/core/DurationNs;Landroidx/camera/camera2/pipe/core/DurationNs;Landroidx/camera/camera2/pipe/core/DurationNs;Landroidx/camera/camera2/pipe/CameraError;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getCameraId-Dz_R5H8", "Landroidx/camera/camera2/pipe/compat/ClosedReason;", "getCameraClosedReason", "()Landroidx/camera/camera2/pipe/compat/ClosedReason;", "Ljava/lang/Integer;", "getCameraRetryCount", "()Ljava/lang/Integer;", "Landroidx/camera/camera2/pipe/core/DurationNs;", "getCameraRetryDurationNs-QWez1Bs", "()Landroidx/camera/camera2/pipe/core/DurationNs;", "Ljava/lang/Throwable;", "getCameraException", "()Ljava/lang/Throwable;", "getCameraOpenDurationNs-QWez1Bs", "getCameraActiveDurationNs-QWez1Bs", "getCameraClosingDurationNs-QWez1Bs", "Landroidx/camera/camera2/pipe/CameraError;", "getCameraErrorCode-mVEW8x0", "()Landroidx/camera/camera2/pipe/CameraError;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraStateClosed extends CameraState {
    private final DurationNs cameraActiveDurationNs;
    private final ClosedReason cameraClosedReason;
    private final DurationNs cameraClosingDurationNs;
    private final CameraError cameraErrorCode;
    private final Throwable cameraException;
    private final String cameraId;
    private final DurationNs cameraOpenDurationNs;
    private final Integer cameraRetryCount;
    private final DurationNs cameraRetryDurationNs;

    private CameraStateClosed(String r2, ClosedReason r3, Integer r4, DurationNs r5, Throwable r6, DurationNs r7, DurationNs r8, DurationNs r9, CameraError r10) {
        super(null);
        this.cameraId = r2;
        this.cameraClosedReason = r3;
        this.cameraRetryCount = r4;
        this.cameraRetryDurationNs = r5;
        this.cameraException = r6;
        this.cameraOpenDurationNs = r7;
        this.cameraActiveDurationNs = r8;
        this.cameraClosingDurationNs = r9;
        this.cameraErrorCode = r10;
    }

    public /* synthetic */ CameraStateClosed(String r2, ClosedReason r3, Integer r4, DurationNs r5, Throwable r6, DurationNs r7, DurationNs r8, DurationNs r9, CameraError r10, int r11, DefaultConstructorMarker r12) {
        if ((r11 & 4) == 0) goto L6;
        r4 = null;
    L6:
        if ((r11 & 8) == 0) goto L9;
        r5 = null;
    L9:
        if ((r11 & 16) == 0) goto L12;
        r6 = null;
    L12:
        if ((r11 & 32) == 0) goto L15;
        r7 = null;
    L15:
        if ((r11 & 64) == 0) goto L18;
        r8 = null;
    L18:
        if ((r11 & 128) == 0) goto L21;
        r9 = null;
    L21:
        if ((r11 & 256) == 0) goto L23;
        r10 = null;
    L23:
        this(r2, r3, r4, r5, r6, r7, r8, r9, r10, null);
    }

    public /* synthetic */ CameraStateClosed(String r1, ClosedReason r2, Integer r3, DurationNs r4, Throwable r5, DurationNs r6, DurationNs r7, DurationNs r8, CameraError r9, DefaultConstructorMarker r10) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CameraStateClosed) == true) goto L8;
        return false;
    L8:
        CameraStateClosed r52 = (CameraStateClosed) r5;
        if (CameraId.m230equalsimpl0(this.cameraId, r52.cameraId) == true) goto L12;
        return false;
    L12:
        if (this.cameraClosedReason == r52.cameraClosedReason) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.cameraRetryCount, r52.cameraRetryCount) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.cameraRetryDurationNs, r52.cameraRetryDurationNs) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.cameraException, r52.cameraException) == true) goto L24;
        return false;
    L24:
        if (Intrinsics.areEqual(this.cameraOpenDurationNs, r52.cameraOpenDurationNs) == true) goto L27;
        return false;
    L27:
        if (Intrinsics.areEqual(this.cameraActiveDurationNs, r52.cameraActiveDurationNs) == true) goto L30;
        return false;
    L30:
        if (Intrinsics.areEqual(this.cameraClosingDurationNs, r52.cameraClosingDurationNs) == true) goto L33;
        return false;
    L33:
        if (Intrinsics.areEqual(this.cameraErrorCode, r52.cameraErrorCode) == true) goto L35;
        return false;
    L35:
        return true;
    }

    /* renamed from: getCameraErrorCode-mVEW8x0, reason: not valid java name */
    public final CameraError m462getCameraErrorCodemVEW8x0() {
        return this.cameraErrorCode;
    }

    public int hashCode() {
        int r0 = ((CameraId.m231hashCodeimpl(this.cameraId) * 31) + this.cameraClosedReason.hashCode()) * 31;
        Integer r1 = this.cameraRetryCount;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (r0 + r12) * 31;
        DurationNs r13 = this.cameraRetryDurationNs;
        if (r13 != null) goto L9;
        int r14 = 0;
    L10:
        int r03 = (r02 + r14) * 31;
        Throwable r15 = this.cameraException;
        if (r15 != null) goto L13;
        int r16 = 0;
    L14:
        int r04 = (r03 + r16) * 31;
        DurationNs r17 = this.cameraOpenDurationNs;
        if (r17 != null) goto L17;
        int r18 = 0;
    L18:
        int r05 = (r04 + r18) * 31;
        DurationNs r19 = this.cameraActiveDurationNs;
        if (r19 != null) goto L21;
        int r110 = 0;
    L22:
        int r06 = (r05 + r110) * 31;
        DurationNs r111 = this.cameraClosingDurationNs;
        if (r111 != null) goto L25;
        int r112 = 0;
    L26:
        int r07 = (r06 + r112) * 31;
        CameraError r5 = this.cameraErrorCode;
        if (r5 == null) goto L31;
        r2 = CameraError.m179hashCodeimpl(r5.m182unboximpl());
    L31:
        return r07 + r2;
    L25:
        r112 = DurationNs.m500hashCodeimpl(r111.m502unboximpl());
        goto L26
    L21:
        r110 = DurationNs.m500hashCodeimpl(r19.m502unboximpl());
        goto L22
    L17:
        r18 = DurationNs.m500hashCodeimpl(r17.m502unboximpl());
        goto L18
    L13:
        r16 = r15.hashCode();
        goto L14
    L9:
        r14 = DurationNs.m500hashCodeimpl(r13.m502unboximpl());
        goto L10
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public String toString() {
        return "CameraStateClosed(cameraId=" + CameraId.m232toStringimpl(this.cameraId) + ", cameraClosedReason=" + this.cameraClosedReason + ", cameraRetryCount=" + this.cameraRetryCount + ", cameraRetryDurationNs=" + this.cameraRetryDurationNs + ", cameraException=" + this.cameraException + ", cameraOpenDurationNs=" + this.cameraOpenDurationNs + ", cameraActiveDurationNs=" + this.cameraActiveDurationNs + ", cameraClosingDurationNs=" + this.cameraClosingDurationNs + ", cameraErrorCode=" + this.cameraErrorCode + ')';
    }
}
