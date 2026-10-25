package androidx.camera.camera2.adapter;

import kotlin.Metadata;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"cameraAdapterIds", "Lkotlinx/atomicfu/AtomicInt;", "getCameraAdapterIds", "()Lkotlinx/atomicfu/AtomicInt;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CameraInternalAdapterKt {
    private static final AtomicInt cameraAdapterIds = null;

    static {
        cameraAdapterIds = AtomicFU.atomic(0);
    }

    public static final AtomicInt getCameraAdapterIds() {
        return cameraAdapterIds;
    }
}
