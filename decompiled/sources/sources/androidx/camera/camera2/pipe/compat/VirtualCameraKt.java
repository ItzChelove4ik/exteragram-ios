package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"virtualCameraDebugIds", "Lkotlinx/atomicfu/AtomicInt;", "getVirtualCameraDebugIds", "()Lkotlinx/atomicfu/AtomicInt;", "androidCameraDebugIds", "getAndroidCameraDebugIds", "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class VirtualCameraKt {
    private static final AtomicInt androidCameraDebugIds = null;
    private static final AtomicInt virtualCameraDebugIds = null;

    static {
        virtualCameraDebugIds = AtomicFU.atomic(0);
        androidCameraDebugIds = AtomicFU.atomic(0);
    }

    public static final AtomicInt getAndroidCameraDebugIds() {
        return androidCameraDebugIds;
    }

    public static final AtomicInt getVirtualCameraDebugIds() {
        return virtualCameraDebugIds;
    }
}
