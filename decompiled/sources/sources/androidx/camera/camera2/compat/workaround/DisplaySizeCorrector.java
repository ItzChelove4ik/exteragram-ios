package androidx.camera.camera2.compat.workaround;

import android.util.Size;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.SmallDisplaySizeQuirk;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/compat/workaround/DisplaySizeCorrector;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "smallDisplaySizeQuirk", "Landroidx/camera/camera2/compat/quirk/SmallDisplaySizeQuirk;", "displaySize", "Landroid/util/Size;", "getDisplaySize", "()Landroid/util/Size;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DisplaySizeCorrector {
    private final SmallDisplaySizeQuirk smallDisplaySizeQuirk;

    public DisplaySizeCorrector() {
        this.smallDisplaySizeQuirk = (SmallDisplaySizeQuirk) DeviceQuirks.INSTANCE.get(SmallDisplaySizeQuirk.class);
    }

    public final Size getDisplaySize() {
        SmallDisplaySizeQuirk r0 = this.smallDisplaySizeQuirk;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getDisplaySize();
    }
}
