package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/Lock3ABehavior;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class Lock3ABehavior {
    private static final int AFTER_CURRENT_SCAN = 0;
    private static final int AFTER_NEW_SCAN = 0;
    public static final Companion Companion = null;
    private static final int IMMEDIATE = 0;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/pipe/Lock3ABehavior$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "IMMEDIATE", "Landroidx/camera/camera2/pipe/Lock3ABehavior;", "getIMMEDIATE-hRqSH3k", "()I", "I", "AFTER_CURRENT_SCAN", "getAFTER_CURRENT_SCAN-hRqSH3k", "AFTER_NEW_SCAN", "getAFTER_NEW_SCAN-hRqSH3k", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getAFTER_CURRENT_SCAN-hRqSH3k, reason: not valid java name */
        public final int m287getAFTER_CURRENT_SCANhRqSH3k() {
            return Lock3ABehavior.access$getAFTER_CURRENT_SCAN$cp();
        }

        /* renamed from: getAFTER_NEW_SCAN-hRqSH3k, reason: not valid java name */
        public final int m288getAFTER_NEW_SCANhRqSH3k() {
            return Lock3ABehavior.access$getAFTER_NEW_SCAN$cp();
        }

        /* renamed from: getIMMEDIATE-hRqSH3k, reason: not valid java name */
        public final int m289getIMMEDIATEhRqSH3k() {
            return Lock3ABehavior.access$getIMMEDIATE$cp();
        }
    }

    static {
        Companion = new Companion(null);
        IMMEDIATE = m281constructorimpl(1);
        AFTER_CURRENT_SCAN = m281constructorimpl(2);
        AFTER_NEW_SCAN = m281constructorimpl(3);
    }

    private /* synthetic */ Lock3ABehavior(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getAFTER_CURRENT_SCAN$cp() {
        return AFTER_CURRENT_SCAN;
    }

    public static final /* synthetic */ int access$getAFTER_NEW_SCAN$cp() {
        return AFTER_NEW_SCAN;
    }

    public static final /* synthetic */ int access$getIMMEDIATE$cp() {
        return IMMEDIATE;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Lock3ABehavior m280boximpl(int r1) {
        return new Lock3ABehavior(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    private static int m281constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m282equalsimpl(int r2, Object r3) {
        if ((r3 instanceof Lock3ABehavior) == true) goto L6;
        return false;
    L6:
        if (r2 == ((Lock3ABehavior) r3).m286unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m283equalsimpl0(int r0, int r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m284hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m285toStringimpl(int r2) {
        return "Lock3ABehavior(value=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m282equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m284hashCodeimpl(this.value);
    }

    public String toString() {
        return m285toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m286unboximpl() {
        return this.value;
    }
}
