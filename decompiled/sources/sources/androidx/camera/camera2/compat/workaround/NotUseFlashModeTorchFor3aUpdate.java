package androidx.camera.camera2.compat.workaround;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/compat/workaround/NotUseFlashModeTorchFor3aUpdate;", "Landroidx/camera/camera2/compat/workaround/UseFlashModeTorchFor3aUpdate;", "<init>", "()V", "shouldUseFlashModeTorch", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NotUseFlashModeTorchFor3aUpdate implements UseFlashModeTorchFor3aUpdate {
    public static final NotUseFlashModeTorchFor3aUpdate INSTANCE = null;

    static {
        INSTANCE = new NotUseFlashModeTorchFor3aUpdate();
    }

    private NotUseFlashModeTorchFor3aUpdate() {
    }

    @Override // androidx.camera.camera2.compat.workaround.UseFlashModeTorchFor3aUpdate
    public boolean shouldUseFlashModeTorch() {
        return false;
    }
}
