package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0015\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\b\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0016"}, d2 = {"Landroidx/camera/camera2/pipe/RequestTemplate;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "getName-impl", "name", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class RequestTemplate {
    private final int value;

    private /* synthetic */ RequestTemplate(int r1) {
        this.value = r1;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ RequestTemplate m370boximpl(int r1) {
        return new RequestTemplate(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m371constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m372equalsimpl(int r2, Object r3) {
        if ((r3 instanceof RequestTemplate) == true) goto L6;
        return false;
    L6:
        if (r2 == ((RequestTemplate) r3).m377unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m373equalsimpl0(int r0, int r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: getName-impl, reason: not valid java name */
    public static final String m374getNameimpl(int r2) {
        switch(r2) {
            case 1: goto L15;
            case 2: goto L13;
            case 3: goto L11;
            case 4: goto L9;
            case 5: goto L7;
            case 6: goto L5;
            default: goto L4;
        };
    L5:
        return "TEMPLATE_MANUAL";
    L7:
        return "TEMPLATE_ZERO_SHUTTER_LAG";
    L9:
        return "TEMPLATE_VIDEO_SNAPSHOT";
    L11:
        return "TEMPLATE_RECORD";
    L13:
        return "TEMPLATE_STILL_CAPTURE";
    L15:
        return "TEMPLATE_PREVIEW";
    L4:
        return "UNKNOWN-" + r2;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m375hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m376toStringimpl(int r2) {
        return "RequestTemplate(value=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m372equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m375hashCodeimpl(this.value);
    }

    public String toString() {
        return m376toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m377unboximpl() {
        return this.value;
    }
}
