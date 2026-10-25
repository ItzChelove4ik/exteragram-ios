package androidx.camera.camera2.pipe;

import androidx.camera.camera2.pipe.CameraPipe;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;

@kotlin.Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"cameraPipeIds", "Lkotlinx/atomicfu/AtomicInt;", "getCameraPipeIds", "()Lkotlinx/atomicfu/AtomicInt;", "CameraPipe", "Landroidx/camera/camera2/pipe/CameraPipe;", "config", "Landroidx/camera/camera2/pipe/CameraPipe$Config;", "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CameraPipeKt {
    private static final AtomicInt cameraPipeIds = null;

    static {
        cameraPipeIds = AtomicFU.atomic(0);
    }

    public static final CameraPipe CameraPipe(CameraPipe.Config r1) {
        return CameraPipe.Companion.create(r1);
    }

    public static final AtomicInt getCameraPipeIds() {
        return cameraPipeIds;
    }
}
