package androidx.camera.core.internal.compat.workaround;

import android.media.MediaCodec;
import androidx.camera.core.Preview;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.SurfaceOrderQuirk;
import androidx.camera.core.streamsharing.StreamSharing;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class SurfaceSorter {
    private final boolean mHasQuirk;

    public static /* synthetic */ int $r8$lambda$UabBosrqJDhzqUepvZGts2v0jo8(SurfaceSorter r0, SessionConfig.OutputConfig r1, SessionConfig.OutputConfig r2) {
        r0.getClass();
        return r0.getSurfacePriority(r1.getSurface()) - r0.getSurfacePriority(r2.getSurface());
    }

    public SurfaceSorter() {
        if (DeviceQuirks.get(SurfaceOrderQuirk.class) == null) goto L5;
        boolean r0 = true;
    L6:
        this.mHasQuirk = r0;
        return;
    L5:
        r0 = false;
        goto L6
    }

    private int getSurfacePriority(DeferrableSurface r2) {
        if (r2.getContainerClass() != MediaCodec.class) goto L7;
        return 2;
    L7:
        if (r2.getContainerClass() != Preview.class) goto L9;
        return 0;
    L9:
        if (r2.getContainerClass() == StreamSharing.class) goto L15;
        return 1;
    L15:
        return 0;
    }

    public void sort(List<SessionConfig.OutputConfig> r2) {
        if (this.mHasQuirk == true) goto L5;
        return;
    L5:
        Collections.sort(r2, new SurfaceSorter$$ExternalSyntheticLambda0(this));
    }
}
