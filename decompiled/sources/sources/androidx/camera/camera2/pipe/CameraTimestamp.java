package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\f\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/pipe/CameraTimestamp;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(J)Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(J)I", "hashCode", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public abstract class CameraTimestamp {
    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m242constructorimpl(long r0) {
        return r0;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m243equalsimpl0(long r0, long r2) {
        if (r0 != r2) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m244hashCodeimpl(long r0) {
        return Long.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m245toStringimpl(long r2) {
        return "CameraTimestamp(value=" + r2 + ')';
    }
}
