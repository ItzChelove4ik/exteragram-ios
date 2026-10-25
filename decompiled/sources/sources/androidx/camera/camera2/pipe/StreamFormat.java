package androidx.camera.camera2.pipe;

import androidx.mediarouter.media.GlobalMediaRouter;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.CharsKt;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\b\u0087@\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0015\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\b\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/pipe/StreamFormat;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "getName-impl", "name", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class StreamFormat {
    public static final Companion Companion = null;
    private static final int DEPTH16 = 0;
    private static final int DEPTH_JPEG = 0;
    private static final int DEPTH_POINT_CLOUD = 0;
    private static final int FLEX_RGBA_8888 = 0;
    private static final int FLEX_RGB_888 = 0;
    private static final int HEIC = 0;
    private static final int JPEG = 0;
    private static final int JPEG_R = 0;
    private static final int NV16 = 0;
    private static final int NV21 = 0;
    private static final int PRIVATE = 0;
    private static final int RAW10 = 0;
    private static final int RAW12 = 0;
    private static final int RAW_DEPTH = 0;
    private static final int RAW_PRIVATE = 0;
    private static final int RAW_SENSOR = 0;
    private static final int RGB_565 = 0;
    private static final int UNKNOWN = 0;
    private static final int Y12 = 0;
    private static final int Y16 = 0;
    private static final int Y8 = 0;
    private static final int YCBCR_P010 = 0;
    private static final int YUV_420_888 = 0;
    private static final int YUV_422_888 = 0;
    private static final int YUV_444_888 = 0;
    private static final int YUY2 = 0;
    private static final int YV12 = 0;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/StreamFormat$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/StreamFormat;", "UNKNOWN", "I", "getUNKNOWN-8FPWQzE", "()I", "PRIVATE", "getPRIVATE-8FPWQzE", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getPRIVATE-8FPWQzE, reason: not valid java name */
        public final int m397getPRIVATE8FPWQzE() {
            return StreamFormat.access$getPRIVATE$cp();
        }

        /* renamed from: getUNKNOWN-8FPWQzE, reason: not valid java name */
        public final int m398getUNKNOWN8FPWQzE() {
            return StreamFormat.access$getUNKNOWN$cp();
        }
    }

    static {
        Companion = new Companion(null);
        UNKNOWN = m390constructorimpl(0);
        PRIVATE = m390constructorimpl(34);
        DEPTH16 = m390constructorimpl(1144402265);
        DEPTH_JPEG = m390constructorimpl(1768253795);
        DEPTH_POINT_CLOUD = m390constructorimpl(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED);
        FLEX_RGB_888 = m390constructorimpl(41);
        FLEX_RGBA_8888 = m390constructorimpl(42);
        HEIC = m390constructorimpl(1212500294);
        JPEG = m390constructorimpl(256);
        JPEG_R = m390constructorimpl(4101);
        NV16 = m390constructorimpl(16);
        NV21 = m390constructorimpl(17);
        RAW10 = m390constructorimpl(37);
        RAW12 = m390constructorimpl(38);
        RAW_DEPTH = m390constructorimpl(4098);
        RAW_PRIVATE = m390constructorimpl(36);
        RAW_SENSOR = m390constructorimpl(32);
        RGB_565 = m390constructorimpl(4);
        Y12 = m390constructorimpl(842094169);
        Y16 = m390constructorimpl(540422489);
        Y8 = m390constructorimpl(538982489);
        YCBCR_P010 = m390constructorimpl(54);
        YUV_420_888 = m390constructorimpl(35);
        YUV_422_888 = m390constructorimpl(39);
        YUV_444_888 = m390constructorimpl(40);
        YUY2 = m390constructorimpl(20);
        YV12 = m390constructorimpl(842094169);
    }

    private /* synthetic */ StreamFormat(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getPRIVATE$cp() {
        return PRIVATE;
    }

    public static final /* synthetic */ int access$getUNKNOWN$cp() {
        return UNKNOWN;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ StreamFormat m389boximpl(int r1) {
        return new StreamFormat(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m390constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m391equalsimpl(int r2, Object r3) {
        if ((r3 instanceof StreamFormat) == true) goto L6;
        return false;
    L6:
        if (r2 == ((StreamFormat) r3).m396unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m392equalsimpl0(int r0, int r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: getName-impl, reason: not valid java name */
    public static final String m393getNameimpl(int r2) {
        if (m392equalsimpl0(r2, UNKNOWN) == false) goto L7;
        return "UNKNOWN";
    L7:
        if (m392equalsimpl0(r2, PRIVATE) == false) goto L11;
        return "PRIVATE";
    L11:
        if (m392equalsimpl0(r2, DEPTH16) == false) goto L15;
        return "DEPTH16";
    L15:
        if (m392equalsimpl0(r2, DEPTH_JPEG) == false) goto L19;
        return "DEPTH_JPEG";
    L19:
        if (m392equalsimpl0(r2, DEPTH_POINT_CLOUD) == false) goto L23;
        return "DEPTH_POINT_CLOUD";
    L23:
        if (m392equalsimpl0(r2, FLEX_RGB_888) == false) goto L27;
        return "FLEX_RGB_888";
    L27:
        if (m392equalsimpl0(r2, FLEX_RGBA_8888) == false) goto L31;
        return "FLEX_RGBA_8888";
    L31:
        if (m392equalsimpl0(r2, HEIC) == false) goto L35;
        return "HEIC";
    L35:
        if (m392equalsimpl0(r2, JPEG) == false) goto L39;
        return "JPEG";
    L39:
        if (m392equalsimpl0(r2, JPEG_R) == false) goto L43;
        return "JPEG_R";
    L43:
        if (m392equalsimpl0(r2, NV16) == false) goto L47;
        return "NV16";
    L47:
        if (m392equalsimpl0(r2, NV21) == false) goto L51;
        return "NV21";
    L51:
        if (m392equalsimpl0(r2, RAW10) == false) goto L55;
        return "RAW10";
    L55:
        if (m392equalsimpl0(r2, RAW12) == false) goto L59;
        return "RAW12";
    L59:
        if (m392equalsimpl0(r2, RAW_DEPTH) == false) goto L63;
        return "RAW_DEPTH";
    L63:
        if (m392equalsimpl0(r2, RAW_PRIVATE) == false) goto L67;
        return "RAW_PRIVATE";
    L67:
        if (m392equalsimpl0(r2, RAW_SENSOR) == false) goto L71;
        return "RAW_SENSOR";
    L71:
        if (m392equalsimpl0(r2, RGB_565) == false) goto L75;
        return "RGB_565";
    L75:
        if (m392equalsimpl0(r2, Y12) == false) goto L79;
        return "Y12";
    L79:
        if (m392equalsimpl0(r2, Y16) == false) goto L83;
        return "Y16";
    L83:
        if (m392equalsimpl0(r2, Y8) == false) goto L87;
        return "Y8";
    L87:
        if (m392equalsimpl0(r2, YCBCR_P010) == false) goto L91;
        return "YCBCR_P010";
    L91:
        if (m392equalsimpl0(r2, YUV_420_888) == false) goto L95;
        return "YUV_420_888";
    L95:
        if (m392equalsimpl0(r2, YUV_422_888) == false) goto L99;
        return "YUV_422_888";
    L99:
        if (m392equalsimpl0(r2, YUV_444_888) == false) goto L103;
        return "YUV_444_888";
    L103:
        if (m392equalsimpl0(r2, YUY2) == false) goto L107;
        return "YUY2";
    L107:
        if (m392equalsimpl0(r2, YV12) == false) goto L111;
        return "YV12";
    L111:
        return "UNKNOWN(" + Integer.toString(r2, CharsKt.checkRadix(16)) + ')';
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m394hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m395toStringimpl(int r2) {
        return "StreamFormat(" + m393getNameimpl(r2) + ')';
    }

    public boolean equals(Object r1) {
        return m391equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m394hashCodeimpl(this.value);
    }

    public String toString() {
        return m395toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m396unboximpl() {
        return this.value;
    }
}
