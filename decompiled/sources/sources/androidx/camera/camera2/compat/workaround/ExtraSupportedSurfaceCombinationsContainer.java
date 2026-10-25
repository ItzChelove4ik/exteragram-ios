package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.ExtraSupportedSurfaceCombinationsQuirk;
import androidx.camera.core.impl.SurfaceCombination;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0086\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/compat/workaround/ExtraSupportedSurfaceCombinationsContainer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "quirk", "Landroidx/camera/camera2/compat/quirk/ExtraSupportedSurfaceCombinationsQuirk;", "get", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SurfaceCombination;", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExtraSupportedSurfaceCombinationsContainer {
    private final ExtraSupportedSurfaceCombinationsQuirk quirk;

    public ExtraSupportedSurfaceCombinationsContainer() {
        this.quirk = (ExtraSupportedSurfaceCombinationsQuirk) DeviceQuirks.INSTANCE.get(ExtraSupportedSurfaceCombinationsQuirk.class);
    }

    public final List<SurfaceCombination> get(String r1) {
        ExtraSupportedSurfaceCombinationsQuirk r0 = this.quirk;
        if (r0 == null) goto L9;
        List<SurfaceCombination> r02 = r0.getExtraSupportedSurfaceCombinations(r1);
        if (r02 == null) goto L9;
        return r02;
    L9:
        return CollectionsKt.emptyList();
    }
}
