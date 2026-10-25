package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStatus;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class OutputStatus {
    private static final int AVAILABLE = 0;
    public static final Companion Companion = null;
    private static final int ERROR_OUTPUT_ABORTED = 0;
    private static final int ERROR_OUTPUT_DROPPED = 0;
    private static final int ERROR_OUTPUT_FAILED = 0;
    private static final int ERROR_OUTPUT_MISSING = 0;
    private static final int PENDING = 0;
    private static final int UNAVAILABLE = 0;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\b¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStatus$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/OutputStatus;", "AVAILABLE", "I", "getAVAILABLE-U7r42EA", "()I", "UNAVAILABLE", "getUNAVAILABLE-U7r42EA", "ERROR_OUTPUT_FAILED", "getERROR_OUTPUT_FAILED-U7r42EA", "ERROR_OUTPUT_ABORTED", "getERROR_OUTPUT_ABORTED-U7r42EA", "ERROR_OUTPUT_MISSING", "getERROR_OUTPUT_MISSING-U7r42EA", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getAVAILABLE-U7r42EA, reason: not valid java name */
        public final int m303getAVAILABLEU7r42EA() {
            return OutputStatus.access$getAVAILABLE$cp();
        }

        /* renamed from: getERROR_OUTPUT_ABORTED-U7r42EA, reason: not valid java name */
        public final int m304getERROR_OUTPUT_ABORTEDU7r42EA() {
            return OutputStatus.access$getERROR_OUTPUT_ABORTED$cp();
        }

        /* renamed from: getERROR_OUTPUT_FAILED-U7r42EA, reason: not valid java name */
        public final int m305getERROR_OUTPUT_FAILEDU7r42EA() {
            return OutputStatus.access$getERROR_OUTPUT_FAILED$cp();
        }

        /* renamed from: getERROR_OUTPUT_MISSING-U7r42EA, reason: not valid java name */
        public final int m306getERROR_OUTPUT_MISSINGU7r42EA() {
            return OutputStatus.access$getERROR_OUTPUT_MISSING$cp();
        }

        /* renamed from: getUNAVAILABLE-U7r42EA, reason: not valid java name */
        public final int m307getUNAVAILABLEU7r42EA() {
            return OutputStatus.access$getUNAVAILABLE$cp();
        }
    }

    static {
        Companion = new Companion(null);
        PENDING = m298constructorimpl(0);
        AVAILABLE = m298constructorimpl(1);
        UNAVAILABLE = m298constructorimpl(2);
        ERROR_OUTPUT_FAILED = m298constructorimpl(10);
        ERROR_OUTPUT_ABORTED = m298constructorimpl(11);
        ERROR_OUTPUT_MISSING = m298constructorimpl(12);
        ERROR_OUTPUT_DROPPED = m298constructorimpl(13);
    }

    private /* synthetic */ OutputStatus(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getAVAILABLE$cp() {
        return AVAILABLE;
    }

    public static final /* synthetic */ int access$getERROR_OUTPUT_ABORTED$cp() {
        return ERROR_OUTPUT_ABORTED;
    }

    public static final /* synthetic */ int access$getERROR_OUTPUT_FAILED$cp() {
        return ERROR_OUTPUT_FAILED;
    }

    public static final /* synthetic */ int access$getERROR_OUTPUT_MISSING$cp() {
        return ERROR_OUTPUT_MISSING;
    }

    public static final /* synthetic */ int access$getUNAVAILABLE$cp() {
        return UNAVAILABLE;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ OutputStatus m297boximpl(int r1) {
        return new OutputStatus(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m298constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m299equalsimpl(int r2, Object r3) {
        if ((r3 instanceof OutputStatus) == true) goto L6;
        return false;
    L6:
        if (r2 == ((OutputStatus) r3).m302unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m300hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m301toStringimpl(int r2) {
        if (r2 != 0) goto L4;
        return "PENDING";
    L4:
        if (r2 != 1) goto L6;
        return "AVAILABLE";
    L6:
        if (r2 == 2) goto L18;
        switch(r2) {
            case 10: goto L16;
            case 11: goto L14;
            case 12: goto L12;
            case 13: goto L10;
            default: goto L9;
        };
    L10:
        return "ERROR_OUTPUT_DROPPED";
    L12:
        return "ERROR_OUTPUT_MISSING";
    L14:
        return "ERROR_OUTPUT_ABORTED";
    L16:
        return "ERROR_OUTPUT_FAILED";
    L9:
        return "OutputStatus(value=" + r2 + ')';
    L18:
        return "UNAVAILABLE";
    }

    public boolean equals(Object r1) {
        return m299equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m300hashCodeimpl(this.value);
    }

    public String toString() {
        return m301toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m302unboximpl() {
        return this.value;
    }
}
