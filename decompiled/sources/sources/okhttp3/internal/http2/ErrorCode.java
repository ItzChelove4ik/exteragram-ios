package okhttp3.internal.http2;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0081\u0002\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/http2/ErrorCode;", _UrlKt.FRAGMENT_ENCODE_SET, "httpCode", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;II)V", "getHttpCode", "()I", "NO_ERROR", "PROTOCOL_ERROR", "INTERNAL_ERROR", "FLOW_CONTROL_ERROR", "SETTINGS_TIMEOUT", "STREAM_CLOSED", "FRAME_SIZE_ERROR", "REFUSED_STREAM", PluginsConstants.Strategy.CANCEL, "COMPRESSION_ERROR", "CONNECT_ERROR", "ENHANCE_YOUR_CALM", "INADEQUATE_SECURITY", "HTTP_1_1_REQUIRED", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum ErrorCode extends Enum<ErrorCode> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ErrorCode[] $VALUES = null;
    public static final ErrorCode CANCEL = null;
    public static final ErrorCode COMPRESSION_ERROR = null;
    public static final ErrorCode CONNECT_ERROR = null;
    public static final Companion Companion = null;
    public static final ErrorCode ENHANCE_YOUR_CALM = null;
    public static final ErrorCode FLOW_CONTROL_ERROR = null;
    public static final ErrorCode FRAME_SIZE_ERROR = null;
    public static final ErrorCode HTTP_1_1_REQUIRED = null;
    public static final ErrorCode INADEQUATE_SECURITY = null;
    public static final ErrorCode INTERNAL_ERROR = null;
    public static final ErrorCode NO_ERROR = null;
    public static final ErrorCode PROTOCOL_ERROR = null;
    public static final ErrorCode REFUSED_STREAM = null;
    public static final ErrorCode SETTINGS_TIMEOUT = null;
    public static final ErrorCode STREAM_CLOSED = null;
    private final int httpCode;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lokhttp3/internal/http2/ErrorCode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromHttp2", "Lokhttp3/internal/http2/ErrorCode;", "code", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nErrorCode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ErrorCode.kt\nokhttp3/internal/http2/ErrorCode$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,56:1\n1#2:57\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final ErrorCode fromHttp2(int r5) {
            ErrorCode[] r4 = ErrorCode.values();
            int r0 = r4.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            ErrorCode r2 = r4[r1];
            if (r2.getHttpCode() == r5) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return r2;
        L8:
            return null;
        }
    }

    private static final /* synthetic */ ErrorCode[] $values() {
        return new ErrorCode[]{NO_ERROR, PROTOCOL_ERROR, INTERNAL_ERROR, FLOW_CONTROL_ERROR, SETTINGS_TIMEOUT, STREAM_CLOSED, FRAME_SIZE_ERROR, REFUSED_STREAM, CANCEL, COMPRESSION_ERROR, CONNECT_ERROR, ENHANCE_YOUR_CALM, INADEQUATE_SECURITY, HTTP_1_1_REQUIRED};
    }

    static {
        NO_ERROR = new ErrorCode("NO_ERROR", 0, 0);
        PROTOCOL_ERROR = new ErrorCode("PROTOCOL_ERROR", 1, 1);
        INTERNAL_ERROR = new ErrorCode("INTERNAL_ERROR", 2, 2);
        FLOW_CONTROL_ERROR = new ErrorCode("FLOW_CONTROL_ERROR", 3, 3);
        SETTINGS_TIMEOUT = new ErrorCode("SETTINGS_TIMEOUT", 4, 4);
        STREAM_CLOSED = new ErrorCode("STREAM_CLOSED", 5, 5);
        FRAME_SIZE_ERROR = new ErrorCode("FRAME_SIZE_ERROR", 6, 6);
        REFUSED_STREAM = new ErrorCode("REFUSED_STREAM", 7, 7);
        CANCEL = new ErrorCode(PluginsConstants.Strategy.CANCEL, 8, 8);
        COMPRESSION_ERROR = new ErrorCode("COMPRESSION_ERROR", 9, 9);
        CONNECT_ERROR = new ErrorCode("CONNECT_ERROR", 10, 10);
        ENHANCE_YOUR_CALM = new ErrorCode("ENHANCE_YOUR_CALM", 11, 11);
        INADEQUATE_SECURITY = new ErrorCode("INADEQUATE_SECURITY", 12, 12);
        HTTP_1_1_REQUIRED = new ErrorCode("HTTP_1_1_REQUIRED", 13, 13);
        ErrorCode[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    ErrorCode(String r1, int r2, int r3) {
        this.httpCode = r3;
    }

    public static EnumEntries<ErrorCode> getEntries() {
        return $ENTRIES;
    }

    public static ErrorCode valueOf(String r1) {
        return (ErrorCode) Enum.valueOf(ErrorCode.class, r1);
    }

    public static ErrorCode[] values() {
        return (ErrorCode[]) $VALUES.clone();
    }

    public final int getHttpCode() {
        return this.httpCode;
    }
}
