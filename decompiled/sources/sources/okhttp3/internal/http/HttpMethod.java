package okhttp3.internal.http;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0007¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/http/HttpMethod;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "invalidatesCache", _UrlKt.FRAGMENT_ENCODE_SET, "method", _UrlKt.FRAGMENT_ENCODE_SET, "requiresRequestBody", "permitsRequestBody", "redirectsWithBody", "redirectsToGet", "isCacheable", "requestMethod", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HttpMethod {
    public static final HttpMethod INSTANCE = null;

    static {
        INSTANCE = new HttpMethod();
    }

    private HttpMethod() {
    }

    @JvmStatic
    public static final boolean invalidatesCache(String r1) {
        if (Intrinsics.areEqual(r1, "POST") == false) goto L5;
        return true;
    L5:
        if (Intrinsics.areEqual(r1, "PATCH") == false) goto L7;
        return true;
    L7:
        if (Intrinsics.areEqual(r1, "PUT") == false) goto L9;
        return true;
    L9:
        if (Intrinsics.areEqual(r1, "DELETE") == false) goto L11;
        return true;
    L11:
        if (Intrinsics.areEqual(r1, "MOVE") == true) goto L20;
        return false;
    L20:
        return true;
    }

    @JvmStatic
    public static final boolean permitsRequestBody(String r1) {
        if (Intrinsics.areEqual(r1, "GET") == false) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(r1, "HEAD") == true) goto L10;
        return true;
    L10:
        return false;
    }

    @JvmStatic
    public static final boolean requiresRequestBody(String r1) {
        if (Intrinsics.areEqual(r1, "POST") == false) goto L5;
        return true;
    L5:
        if (Intrinsics.areEqual(r1, "PUT") == false) goto L7;
        return true;
    L7:
        if (Intrinsics.areEqual(r1, "PATCH") == false) goto L9;
        return true;
    L9:
        if (Intrinsics.areEqual(r1, "PROPPATCH") == false) goto L11;
        return true;
    L11:
        if (Intrinsics.areEqual(r1, "QUERY") == false) goto L13;
        return true;
    L13:
        if (Intrinsics.areEqual(r1, "REPORT") == true) goto L23;
        return false;
    L23:
        return true;
    }

    public final boolean isCacheable(String r1) {
        if (Intrinsics.areEqual(r1, "GET") == false) goto L5;
        return true;
    L5:
        if (Intrinsics.areEqual(r1, "QUERY") == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final boolean redirectsToGet(String r1) {
        return !Intrinsics.areEqual(r1, "PROPFIND");
    }

    public final boolean redirectsWithBody(String r1) {
        return Intrinsics.areEqual(r1, "PROPFIND");
    }
}
