package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087@\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/ConfigQueryResult;", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "(I)I", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class ConfigQueryResult {
    public static final Companion Companion = null;
    private static final int SUPPORTED = 0;
    private static final int UNKNOWN = 0;
    private static final int UNSUPPORTED = 0;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/ConfigQueryResult$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/ConfigQueryResult;", "UNKNOWN", "I", "getUNKNOWN-Xp6DSB4", "()I", "SUPPORTED", "getSUPPORTED-Xp6DSB4", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getSUPPORTED-Xp6DSB4, reason: not valid java name */
        public final int m253getSUPPORTEDXp6DSB4() {
            return ConfigQueryResult.access$getSUPPORTED$cp();
        }

        /* renamed from: getUNKNOWN-Xp6DSB4, reason: not valid java name */
        public final int m254getUNKNOWNXp6DSB4() {
            return ConfigQueryResult.access$getUNKNOWN$cp();
        }
    }

    static {
        Companion = new Companion(null);
        UNKNOWN = m247constructorimpl(0);
        SUPPORTED = m247constructorimpl(1);
        UNSUPPORTED = m247constructorimpl(2);
    }

    private /* synthetic */ ConfigQueryResult(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getSUPPORTED$cp() {
        return SUPPORTED;
    }

    public static final /* synthetic */ int access$getUNKNOWN$cp() {
        return UNKNOWN;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ConfigQueryResult m246boximpl(int r1) {
        return new ConfigQueryResult(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m247constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m248equalsimpl(int r2, Object r3) {
        if ((r3 instanceof ConfigQueryResult) == true) goto L6;
        return false;
    L6:
        if (r2 == ((ConfigQueryResult) r3).m252unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m249equalsimpl0(int r0, int r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m250hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m251toStringimpl(int r1) {
        if (m249equalsimpl0(r1, SUPPORTED) == false) goto L7;
        return "SUPPORTED";
    L7:
        if (m249equalsimpl0(r1, UNSUPPORTED) == false) goto L10;
        return "UNSUPPORTED";
    L10:
        return "UNKNOWN";
    }

    public boolean equals(Object r1) {
        return m248equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m250hashCodeimpl(this.value);
    }

    public String toString() {
        return m251toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m252unboximpl() {
        return this.value;
    }
}
