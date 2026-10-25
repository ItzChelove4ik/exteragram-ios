package androidx.camera.camera2.pipe;

import android.hardware.camera2.params.MeteringRectangle;
import java.util.List;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@kotlin.Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001Jo\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH&¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH&¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0014\u0010\u0015ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CameraControls3A;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/AeMode;", "aeMode", "Landroidx/camera/camera2/pipe/AfMode;", "afMode", "Landroidx/camera/camera2/pipe/AwbMode;", "awbMode", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;", "update3A-ydBZfZg", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lkotlinx/coroutines/Deferred;", "update3A", "setTorchOn", "()Lkotlinx/coroutines/Deferred;", "setTorchOff-NqN7i0k", "(Landroidx/camera/camera2/pipe/AeMode;)Lkotlinx/coroutines/Deferred;", "setTorchOff", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraControls3A {
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: update3A-ydBZfZg$default, reason: not valid java name */
    static /* synthetic */ Deferred m164update3AydBZfZg$default(CameraControls3A r1, AeMode r2, AfMode r3, AwbMode r4, List r5, List r6, List r7, int r8, Object r9) {
        if (r9 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: update3A-ydBZfZg");
        return null;
    L4:
        if ((r8 & 1) == 0) goto L7;
        r2 = null;
    L7:
        if ((r8 & 2) == 0) goto L10;
        r3 = null;
    L10:
        if ((r8 & 4) == 0) goto L13;
        r4 = null;
    L13:
        if ((r8 & 8) == 0) goto L16;
        r5 = null;
    L16:
        if ((r8 & 16) == 0) goto L19;
        r6 = null;
    L19:
        if ((r8 & 32) == 0) goto L22;
        r7 = null;
    L22:
        return r1.mo166update3AydBZfZg(r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: setTorchOff-NqN7i0k, reason: not valid java name */
    Deferred<Result3A> mo165setTorchOffNqN7i0k(AeMode r1);

    Deferred<Result3A> setTorchOn();

    /* renamed from: update3A-ydBZfZg, reason: not valid java name */
    Deferred<Result3A> mo166update3AydBZfZg(AeMode r1, AfMode r2, AwbMode r3, List<MeteringRectangle> r4, List<MeteringRectangle> r5, List<MeteringRectangle> r6);
}
