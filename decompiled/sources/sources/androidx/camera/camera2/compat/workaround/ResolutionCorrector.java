package androidx.camera.camera2.compat.workaround;

import android.util.Size;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.ExtraCroppingQuirk;
import androidx.camera.core.impl.SurfaceConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/compat/workaround/ResolutionCorrector;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "extraCroppingQuirk", "Landroidx/camera/camera2/compat/quirk/ExtraCroppingQuirk;", "insertOrPrioritize", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Size;", "configType", "Landroidx/camera/core/impl/SurfaceConfig$ConfigType;", "supportedResolutions", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ResolutionCorrector {
    private final ExtraCroppingQuirk extraCroppingQuirk;

    public ResolutionCorrector() {
        this.extraCroppingQuirk = (ExtraCroppingQuirk) DeviceQuirks.INSTANCE.get(ExtraCroppingQuirk.class);
    }

    public final List<Size> insertOrPrioritize(SurfaceConfig.ConfigType r3, List<Size> r4) {
        ExtraCroppingQuirk r2 = this.extraCroppingQuirk;
        if (r2 == null) goto L7;
        Size r22 = r2.getVerifiedResolution(r3);
        if (r22 == null) goto L7;
        ArrayList r32 = new ArrayList();
        r32.add(r22);
        Iterator<Size> r42 = r4.iterator();
    L10:
        if (r42.hasNext() == false) goto L14;
        Size r0 = r42.next();
        if (Intrinsics.areEqual(r0, r22) == true) goto L10;
        r32.add(r0);
        goto L10
    L14:
        return r32;
    L7:
        return r4;
    }
}
