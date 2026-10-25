package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0006\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/CameraBackendId;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "toString-impl", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(Ljava/lang/String;)I", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "equals", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class CameraBackendId {
    private final String value;

    private /* synthetic */ CameraBackendId(String r1) {
        this.value = r1;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ CameraBackendId m151boximpl(String r1) {
        return new CameraBackendId(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static String m152constructorimpl(String r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m153equalsimpl(String r2, Object r3) {
        if ((r3 instanceof CameraBackendId) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(r2, ((CameraBackendId) r3).m157unboximpl()) == true) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m154equalsimpl0(String r0, String r1) {
        return Intrinsics.areEqual(r0, r1);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m155hashCodeimpl(String r0) {
        return r0.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m156toStringimpl(String r2) {
        return "CameraBackendId(value=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m153equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m155hashCodeimpl(this.value);
    }

    public String toString() {
        return m156toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m157unboximpl() {
        return this.value;
    }
}
