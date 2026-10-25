package androidx.camera.camera2.compat.workaround;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/compat/workaround/AutoFlashAEModeDisablerImpl;", "Landroidx/camera/camera2/compat/workaround/AutoFlashAEModeDisabler;", "<init>", "()V", "getCorrectedAeMode", _UrlKt.FRAGMENT_ENCODE_SET, "aeMode", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AutoFlashAEModeDisablerImpl implements AutoFlashAEModeDisabler {
    public static final AutoFlashAEModeDisablerImpl INSTANCE = null;

    static {
        INSTANCE = new AutoFlashAEModeDisablerImpl();
    }

    private AutoFlashAEModeDisablerImpl() {
    }

    @Override // androidx.camera.camera2.compat.workaround.AutoFlashAEModeDisabler
    public int getCorrectedAeMode(int r1) {
        if (r1 != 2) goto L6;
        return 1;
    L6:
        return r1;
    }
}
