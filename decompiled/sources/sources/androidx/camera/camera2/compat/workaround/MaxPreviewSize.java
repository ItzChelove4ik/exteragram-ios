package androidx.camera.camera2.compat.workaround;

import android.util.Size;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.ExtraCroppingQuirk;
import androidx.camera.core.impl.SurfaceConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/workaround/MaxPreviewSize;", _UrlKt.FRAGMENT_ENCODE_SET, "extraCroppingQuirk", "Landroidx/camera/camera2/compat/quirk/ExtraCroppingQuirk;", "<init>", "(Landroidx/camera/camera2/compat/quirk/ExtraCroppingQuirk;)V", "getMaxPreviewResolution", "Landroid/util/Size;", "defaultMaxPreviewResolution", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MaxPreviewSize {
    private final ExtraCroppingQuirk extraCroppingQuirk;

    public MaxPreviewSize(ExtraCroppingQuirk r1) {
        this.extraCroppingQuirk = r1;
    }

    public /* synthetic */ MaxPreviewSize(ExtraCroppingQuirk r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = (ExtraCroppingQuirk) DeviceQuirks.INSTANCE.get(ExtraCroppingQuirk.class);
    L5:
        this(r1);
    }

    public final Size getMaxPreviewResolution(Size r4) {
        ExtraCroppingQuirk r3 = this.extraCroppingQuirk;
        if (r3 == null) goto L7;
        Size r32 = r3.getVerifiedResolution(SurfaceConfig.ConfigType.PRIV);
        if (r32 == null) goto L7;
        if ((r32.getWidth() * r32.getHeight()) <= (r4.getWidth() * r4.getHeight())) goto L11;
        return r32;
    L11:
        return r4;
    L7:
        return r4;
    }
}
