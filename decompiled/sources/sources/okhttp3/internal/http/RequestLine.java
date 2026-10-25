package okhttp3.internal.http;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.net.Proxy;
import kotlin.Metadata;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/http/RequestLine;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "get", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Lokhttp3/Request;", "proxyType", "Ljava/net/Proxy$Type;", "includeAuthorityInRequestLine", _UrlKt.FRAGMENT_ENCODE_SET, "requestPath", "url", "Lokhttp3/HttpUrl;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RequestLine {
    public static final RequestLine INSTANCE = null;

    static {
        INSTANCE = new RequestLine();
    }

    private RequestLine() {
    }

    private final boolean includeAuthorityInRequestLine(Request r1, Proxy.Type r2) {
        if (r1.isHttps() == false) goto L5;
        return false;
    L5:
        if (r2 != Proxy.Type.HTTP) goto L10;
        return true;
    L10:
        return false;
    }

    public final String get(Request r2, Proxy.Type r3) {
        StringBuilder r1 = new StringBuilder();
        r1.append(r2.method());
        r1.append(' ');
        RequestLine r0 = INSTANCE;
        if (r0.includeAuthorityInRequestLine(r2, r3) == false) goto L5;
        r1.append(r2.url());
    L6:
        r1.append(" HTTP/1.1");
        return r1.toString();
    L5:
        r1.append(r0.requestPath(r2.url()));
        goto L6
    }

    public final String requestPath(HttpUrl r2) {
        String r1 = r2.encodedPath();
        String r22 = r2.encodedQuery();
        if (r22 != null) goto L5;
        return r1;
    L5:
        return r1 + '?' + r22;
    }
}
