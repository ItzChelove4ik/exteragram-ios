package okhttp3.internal.http;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.net.ProtocolException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u0007H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/internal/http/StatusLine;", _UrlKt.FRAGMENT_ENCODE_SET, "protocol", "Lokhttp3/Protocol;", "code", _UrlKt.FRAGMENT_ENCODE_SET, "message", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/Protocol;ILjava/lang/String;)V", "toString", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class StatusLine {
    public static final Companion Companion = null;

    @JvmField
    public final int code;

    @JvmField
    public final String message;

    @JvmField
    public final Protocol protocol;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/http/StatusLine$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "get", "Lokhttp3/internal/http/StatusLine;", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "parse", "statusLine", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final StatusLine get(Response r3) {
            return new StatusLine(r3.protocol(), r3.code(), r3.message());
        }

        public final StatusLine parse(String r8) {
            if (StringsKt.startsWith$default(r8, "HTTP/1.", false, 2, null) == false) goto L19;
            int r0 = 9;
            if (r8.length() < 9) goto L17;
            if (r8.charAt(8) != ' ') goto L17;
            int r7 = r8.charAt(7) - '0';
            if (r7 != 0) goto L11;
            Protocol r72 = Protocol.HTTP_1_0;
        L24:
            int r2 = r0 + 3;
            if (r8.length() < r2) goto L41;
            Integer r1 = StringsKt.toIntOrNull(r8.substring(r0, r2));
            if (r1 == null) goto L39;
            int r12 = r1.intValue();
            if (r8.length() > r2) goto L31;
            String r82 = _UrlKt.FRAGMENT_ENCODE_SET;
        L37:
            return new StatusLine(r72, r12, r82);
        L31:
            if (r8.charAt(r2) != ' ') goto L34;
            r82 = r8.substring(r0 + 4);
            goto L37
        L34:
            throw new ProtocolException("Unexpected status line: ".concat(r8));
        L39:
            throw new ProtocolException("Unexpected status line: ".concat(r8));
        L41:
            throw new ProtocolException("Unexpected status line: ".concat(r8));
        L11:
            if (r7 != 1) goto L14;
            r72 = Protocol.HTTP_1_1;
            goto L24
        L14:
            throw new ProtocolException("Unexpected status line: ".concat(r8));
        L17:
            throw new ProtocolException("Unexpected status line: ".concat(r8));
        L19:
            if (StringsKt.startsWith$default(r8, "ICY ", false, 2, null) == false) goto L22;
            r72 = Protocol.HTTP_1_0;
            r0 = 4;
            goto L24
        L22:
            if (StringsKt.startsWith$default(r8, "SOURCETABLE ", false, 2, null) == false) goto L43;
            r72 = Protocol.HTTP_1_1;
            r0 = 12;
            goto L24
        L43:
            throw new ProtocolException("Unexpected status line: " + r8);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public StatusLine(Protocol r1, int r2, String r3) {
        this.protocol = r1;
        this.code = r2;
        this.message = r3;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        if (this.protocol != Protocol.HTTP_1_0) goto L5;
        r0.append("HTTP/1.0");
    L6:
        r0.append(' ');
        r0.append(this.code);
        r0.append(' ');
        r0.append(this.message);
        return r0.toString();
    L5:
        r0.append("HTTP/1.1");
        goto L6
    }
}
