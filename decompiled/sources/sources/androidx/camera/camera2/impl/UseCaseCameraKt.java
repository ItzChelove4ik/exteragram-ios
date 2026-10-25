package androidx.camera.camera2.impl;

import androidx.camera.core.impl.Config;
import kotlin.Metadata;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"useCaseCameraIds", "Lkotlinx/atomicfu/AtomicInt;", "getUseCaseCameraIds", "()Lkotlinx/atomicfu/AtomicInt;", "defaultOptionPriority", "Landroidx/camera/core/impl/Config$OptionPriority;", "getDefaultOptionPriority", "()Landroidx/camera/core/impl/Config$OptionPriority;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class UseCaseCameraKt {
    private static final Config.OptionPriority defaultOptionPriority = null;
    private static final AtomicInt useCaseCameraIds = null;

    static {
        useCaseCameraIds = AtomicFU.atomic(0);
        defaultOptionPriority = Config.OptionPriority.OPTIONAL;
    }

    public static final Config.OptionPriority getDefaultOptionPriority() {
        return defaultOptionPriority;
    }

    public static final AtomicInt getUseCaseCameraIds() {
        return useCaseCameraIds;
    }
}
