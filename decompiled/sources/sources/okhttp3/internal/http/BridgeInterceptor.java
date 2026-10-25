package okhttp3.internal.http;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;
import okio.GzipSource;
import okio.Okio;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/http/BridgeInterceptor;", "Lokhttp3/Interceptor;", "cookieJar", "Lokhttp3/CookieJar;", "<init>", "(Lokhttp3/CookieJar;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "cookieHeader", _UrlKt.FRAGMENT_ENCODE_SET, "cookies", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Cookie;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBridgeInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BridgeInterceptor.kt\nokhttp3/internal/http/BridgeInterceptor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,125:1\n1878#2,3:126\n*S KotlinDebug\n*F\n+ 1 BridgeInterceptor.kt\nokhttp3/internal/http/BridgeInterceptor\n*L\n119#1:126,3\n*E\n"})
/* loaded from: classes.dex */
public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar r1) {
        this.cookieJar = r1;
    }

    private final String cookieHeader(List<Cookie> r4) {
        StringBuilder r3 = new StringBuilder();
        Iterator<T> r42 = r4.iterator();
        int r0 = 0;
    L4:
        if (r42.hasNext() == false) goto L13;
        Object r1 = r42.next();
        int r2 = r0 + 1;
        if (r0 >= 0) goto L8;
        CollectionsKt.throwIndexOverflow();
    L8:
        Cookie r12 = (Cookie) r1;
        if (r0 <= 0) goto L11;
        r3.append("; ");
    L11:
        r3.append(r12.name());
        r3.append(SignatureVisitor.INSTANCEOF);
        r3.append(r12.value());
        r0 = r2;
        goto L4
    L13:
        return r3.toString();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain r13) {
        Request r0 = r13.request();
        Request.Builder r1 = r0.newBuilder();
        RequestBody r2 = r0.body();
        if (r2 == null) goto L11;
        MediaType r7 = r2.contentType();
        if (r7 == null) goto L7;
        r1.header("Content-Type", r7.toString());
    L7:
        long r72 = r2.contentLength();
        if (r72 == (-1)) goto L10;
        r1.header("Content-Length", String.valueOf(r72));
        r1.removeHeader("Transfer-Encoding");
        goto L11
    L10:
        r1.header("Transfer-Encoding", "chunked");
        r1.removeHeader("Content-Length");
    L11:
        boolean r8 = false;
        if (r0.header("Host") != null) goto L15;
        r1.header("Host", _UtilJvmKt.toHostHeader$default(r0.url(), false, 1, null));
    L15:
        if (r0.header("Connection") != null) goto L18;
        r1.header("Connection", "Keep-Alive");
    L18:
        if (r0.header("Accept-Encoding") == null) goto L20;
    L22:
        List<Cookie> r22 = this.cookieJar.loadForRequest(r0.url());
        if (r22.isEmpty() == true) goto L26;
        r1.header("Cookie", cookieHeader(r22));
    L26:
        if (r0.header("User-Agent") != null) goto L28;
        r1.header("User-Agent", _UtilCommonKt.USER_AGENT);
    L28:
        Request r02 = r1.build();
        Response r132 = r13.proceed(r02);
        HttpHeaders.receiveHeaders(this.cookieJar, r02.url(), r132.headers());
        Response.Builder r12 = r132.newBuilder().request(r02);
        if (r8 == false) goto L38;
        if (StringsKt.equals("gzip", Response.header$default(r132, "Content-Encoding", null, 2, null), true) == false) goto L38;
        if (HttpHeaders.promisesBody(r132) == false) goto L38;
        ResponseBody r23 = r132.body();
        if (r23 == null) goto L38;
        GzipSource r73 = new GzipSource(r23.source());
        r12.headers(r132.headers().newBuilder().removeAll("Content-Encoding").removeAll("Content-Length").build());
        r12.body(new RealResponseBody(Response.header$default(r132, "Content-Type", null, 2, null), -1, Okio.buffer(r73)));
    L38:
        return r12.build();
    L20:
        if (r0.header("Range") != null) goto L22;
        r1.header("Accept-Encoding", "gzip");
        r8 = true;
        goto L22
    }
}
