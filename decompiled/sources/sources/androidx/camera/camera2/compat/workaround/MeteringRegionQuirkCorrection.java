package androidx.camera.camera2.compat.workaround;

import android.graphics.PointF;
import androidx.camera.core.MeteringPoint;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/compat/workaround/MeteringRegionQuirkCorrection;", "Landroidx/camera/camera2/compat/workaround/MeteringRegionCorrection;", "<init>", "()V", "getCorrectedPoint", "Landroid/graphics/PointF;", "meteringPoint", "Landroidx/camera/core/MeteringPoint;", "meteringMode", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MeteringRegionQuirkCorrection implements MeteringRegionCorrection {
    public static final MeteringRegionQuirkCorrection INSTANCE = null;

    static {
        INSTANCE = new MeteringRegionQuirkCorrection();
    }

    private MeteringRegionQuirkCorrection() {
    }

    @Override // androidx.camera.camera2.compat.workaround.MeteringRegionCorrection
    public PointF getCorrectedPoint(MeteringPoint r2, int r3) {
        if (r3 != 1) goto L7;
        return new PointF(1.0f - r2.getX(), r2.getY());
    L7:
        return new PointF(r2.getX(), r2.getY());
    }
}
