package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0012\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0016"}, d2 = {"Landroidx/camera/camera2/pipe/RequestNumber;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(J)Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(J)I", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(JLjava/lang/Object;)Z", "equals", "J", "getValue", "()J", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class RequestNumber {
    private final long value;

    private /* synthetic */ RequestNumber(long r1) {
        this.value = r1;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ RequestNumber m364boximpl(long r1) {
        return new RequestNumber(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m365constructorimpl(long r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m366equalsimpl(long r4, Object r6) {
        if ((r6 instanceof RequestNumber) == true) goto L6;
        return false;
    L6:
        if (r4 == ((RequestNumber) r6).m369unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m367hashCodeimpl(long r0) {
        return Long.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m368toStringimpl(long r2) {
        return "RequestNumber(value=" + r2 + ')';
    }

    public boolean equals(Object r3) {
        return m366equalsimpl(this.value, r3);
    }

    public int hashCode() {
        return m367hashCodeimpl(this.value);
    }

    public String toString() {
        return m368toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m369unboximpl() {
        return this.value;
    }
}
