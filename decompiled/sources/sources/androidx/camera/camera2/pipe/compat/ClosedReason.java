package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/pipe/compat/ClosedReason;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "APP_CLOSED", "APP_DISCONNECTED", "CAMERA2_CLOSED", "CAMERA2_DISCONNECTED", "CAMERA2_ERROR", "CAMERA2_EXCEPTION", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public enum ClosedReason extends Enum<ClosedReason> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ClosedReason[] $VALUES = null;
    public static final ClosedReason APP_CLOSED = null;
    public static final ClosedReason APP_DISCONNECTED = null;
    public static final ClosedReason CAMERA2_CLOSED = null;
    public static final ClosedReason CAMERA2_DISCONNECTED = null;
    public static final ClosedReason CAMERA2_ERROR = null;
    public static final ClosedReason CAMERA2_EXCEPTION = null;

    private static final /* synthetic */ ClosedReason[] $values() {
        return new ClosedReason[]{APP_CLOSED, APP_DISCONNECTED, CAMERA2_CLOSED, CAMERA2_DISCONNECTED, CAMERA2_ERROR, CAMERA2_EXCEPTION};
    }

    static {
        APP_CLOSED = new ClosedReason("APP_CLOSED", 0);
        APP_DISCONNECTED = new ClosedReason("APP_DISCONNECTED", 1);
        CAMERA2_CLOSED = new ClosedReason("CAMERA2_CLOSED", 2);
        CAMERA2_DISCONNECTED = new ClosedReason("CAMERA2_DISCONNECTED", 3);
        CAMERA2_ERROR = new ClosedReason("CAMERA2_ERROR", 4);
        CAMERA2_EXCEPTION = new ClosedReason("CAMERA2_EXCEPTION", 5);
        ClosedReason[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    ClosedReason(String r1, int r2) {
    }

    public static ClosedReason valueOf(String r1) {
        return (ClosedReason) Enum.valueOf(ClosedReason.class, r1);
    }

    public static ClosedReason[] values() {
        return (ClosedReason[]) $VALUES.clone();
    }
}
