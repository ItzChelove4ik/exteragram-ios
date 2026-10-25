package androidx.camera.camera2.impl;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"CHECK_FLASH_REQUIRED_TIMEOUT_IN_NS", _UrlKt.FRAGMENT_ENCODE_SET, "CHECK_3A_TIMEOUT_IN_NS", "CHECK_3A_WITH_FLASH_TIMEOUT_IN_NS", "CHECK_3A_WITH_SCREEN_FLASH_TIMEOUT_IN_NS", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CapturePipelineKt {
    private static final long CHECK_3A_TIMEOUT_IN_NS = 0;
    private static final long CHECK_3A_WITH_FLASH_TIMEOUT_IN_NS = 0;
    private static final long CHECK_3A_WITH_SCREEN_FLASH_TIMEOUT_IN_NS = 0;
    private static final long CHECK_FLASH_REQUIRED_TIMEOUT_IN_NS = 0;

    static {
        CHECK_FLASH_REQUIRED_TIMEOUT_IN_NS = 1000000000;
        CHECK_3A_TIMEOUT_IN_NS = 1000000000;
        CHECK_3A_WITH_FLASH_TIMEOUT_IN_NS = 5000000000L;
        CHECK_3A_WITH_SCREEN_FLASH_TIMEOUT_IN_NS = 2000000000;
    }

    public static final /* synthetic */ long access$getCHECK_3A_TIMEOUT_IN_NS$p() {
        return CHECK_3A_TIMEOUT_IN_NS;
    }

    public static final /* synthetic */ long access$getCHECK_3A_WITH_FLASH_TIMEOUT_IN_NS$p() {
        return CHECK_3A_WITH_FLASH_TIMEOUT_IN_NS;
    }

    public static final /* synthetic */ long access$getCHECK_3A_WITH_SCREEN_FLASH_TIMEOUT_IN_NS$p() {
        return CHECK_3A_WITH_SCREEN_FLASH_TIMEOUT_IN_NS;
    }

    public static final /* synthetic */ long access$getCHECK_FLASH_REQUIRED_TIMEOUT_IN_NS$p() {
        return CHECK_FLASH_REQUIRED_TIMEOUT_IN_NS;
    }
}
