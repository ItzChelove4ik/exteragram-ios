package okhttp3.internal.cache;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.UnreadableResponseBodyKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.url._UrlKt;
import okio.Okio;
import okio.Sink;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001a\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0002R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor;", "Lokhttp3/Interceptor;", "cache", "Lokhttp3/Cache;", "<init>", "(Lokhttp3/Cache;)V", "getCache$okhttp", "()Lokhttp3/Cache;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "cacheWritingResponse", "cacheRequest", "Lokhttp3/internal/cache/CacheRequest;", PluginsConstants.RESPONSE, "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CacheInterceptor implements Interceptor {
    public static final Companion Companion = null;
    private final Cache cache;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\r"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "combine", "Lokhttp3/Headers;", "cachedHeaders", "networkHeaders", "isEndToEnd", _UrlKt.FRAGMENT_ENCODE_SET, "fieldName", _UrlKt.FRAGMENT_ENCODE_SET, "isContentSpecificHeader", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ Headers access$combine(Companion r0, Headers r1, Headers r2) {
            return r0.combine(r1, r2);
        }

        private final Headers combine(Headers r10, Headers r11) {
            Headers.Builder r0 = new Headers.Builder();
            int r1 = r10.size();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L17;
            String r4 = r10.name(r3);
            String r5 = r10.value(r3);
            if (StringsKt.equals("Warning", r4, true) == false) goto L10;
            if (StringsKt.startsWith$default(r5, "1", false, 2, null) == false) goto L10;
        L16:
            r3 = r3 + 1;
        L10:
            if (isContentSpecificHeader(r4) == false) goto L12;
        L15:
            r0.addLenient$okhttp(r4, r5);
            goto L16
        L12:
            if (isEndToEnd(r4) == false) goto L15;
            if (r11.get(r4) != null) goto L16;
        L17:
            int r102 = r11.size();
        L18:
            if (r2 >= r102) goto L26;
            String r12 = r11.name(r2);
            if (isContentSpecificHeader(r12) == true) goto L24;
            if (isEndToEnd(r12) == false) goto L24;
            r0.addLenient$okhttp(r12, r11.value(r2));
        L24:
            r2 = r2 + 1;
            goto L18
        L26:
            return r0.build();
        }

        private final boolean isContentSpecificHeader(String r2) {
            if (StringsKt.equals("Content-Length", r2, true) == false) goto L5;
        L11:
            return true;
        L5:
            if (StringsKt.equals("Content-Encoding", r2, true) == true) goto L11;
            if (StringsKt.equals("Content-Type", r2, true) == true) goto L11;
            return false;
        }

        private final boolean isEndToEnd(String r2) {
            if (StringsKt.equals("Connection", r2, true) == false) goto L5;
            return false;
        L5:
            if (StringsKt.equals("Keep-Alive", r2, true) == false) goto L7;
            return false;
        L7:
            if (StringsKt.equals("Proxy-Authenticate", r2, true) == false) goto L9;
            return false;
        L9:
            if (StringsKt.equals("Proxy-Authorization", r2, true) == false) goto L11;
            return false;
        L11:
            if (StringsKt.equals("TE", r2, true) == false) goto L13;
            return false;
        L13:
            if (StringsKt.equals("Trailers", r2, true) == false) goto L15;
            return false;
        L15:
            if (StringsKt.equals("Transfer-Encoding", r2, true) == false) goto L17;
            return false;
        L17:
            if (StringsKt.equals("Upgrade", r2, true) == true) goto L27;
            return true;
        L27:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CacheInterceptor(Cache r1) {
        this.cache = r1;
    }

    private final Response cacheWritingResponse(CacheRequest r5, Response r6) {
        if (r5 != null) goto L4;
        return r6;
    L4:
        Sink r4 = r5.body();
        CacheInterceptor$cacheWritingResponse$cacheWritingSource$1 r1 = new CacheInterceptor$cacheWritingResponse$cacheWritingSource$1(r6.body().source(), r5, Okio.buffer(r4));
        String r42 = Response.header$default(r6, "Content-Type", null, 2, null);
        long r2 = r6.body().contentLength();
        return r6.newBuilder().body(new RealResponseBody(r42, r2, Okio.buffer(r1))).build();
    }

    public final Cache getCache$okhttp() {
        return this.cache;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain r9) {
        Call r0 = r9.call();
        Cache r1 = this.cache;
        Response r2 = null;
        if (r1 == null) goto L5;
        Response r12 = r1.get$okhttp(CacheInterceptorKt.access$requestForCache(r9.request()));
    L6:
        CacheStrategy r3 = new CacheStrategy.Factory(System.currentTimeMillis(), r9.request(), r12).compute();
        Request r4 = r3.getNetworkRequest();
        Response r5 = r3.getCacheResponse();
        Cache r6 = this.cache;
        if (r6 == null) goto L10;
        r6.trackResponse$okhttp(r3);
    L10:
        if ((r0 instanceof RealCall) == false) goto L12;
        RealCall r32 = (RealCall) r0;
    L13:
        if (r32 == null) goto L16;
        EventListener r33 = r32.getEventListener$okhttp();
        if (r33 == null) goto L16;
    L17:
        if (r12 == null) goto L20;
        if (r5 != null) goto L20;
        _UtilCommonKt.closeQuietly(r12.body());
    L20:
        if (r4 != null) goto L24;
        if (r5 != null) goto L24;
        Response r8 = new Response.Builder().request(r9.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").sentRequestAtMillis(-1).receivedResponseAtMillis(System.currentTimeMillis()).build();
        r33.satisfactionFailure(r0, r8);
        return r8;
    L24:
        if (r4 != null) goto L27;
        Response r82 = r5.newBuilder().cacheResponse(UnreadableResponseBodyKt.stripBody(r5)).build();
        r33.cacheHit(r0, r82);
        return r82;
    L27:
        if (r5 == null) goto L30;
        r33.cacheConditionalHit(r0, r5);
    L67:
        Response r92 = r9.proceed(r4);     // Catch: Throwable -> L60
        if (r92 != null) goto L36;
        if (r12 == null) goto L36;
        _UtilCommonKt.closeQuietly(r12.body());
    L36:
        if (r5 == null) goto L43;
        if (r92 != null) goto L39;
    L42:
        _UtilCommonKt.closeQuietly(r5.body());
        goto L43
    L39:
        if (r92.code() != 304) goto L42;
        Response r13 = r5.newBuilder().headers(Companion.access$combine(Companion, r5.headers(), r92.headers())).sentRequestAtMillis(r92.sentRequestAtMillis()).receivedResponseAtMillis(r92.receivedResponseAtMillis()).cacheResponse(UnreadableResponseBodyKt.stripBody(r5)).networkResponse(UnreadableResponseBodyKt.stripBody(r92)).build();
        r92.body().close();
        this.cache.trackConditionalCacheHit$okhttp();
        this.cache.update$okhttp(r5, r13);
        r33.cacheHit(r0, r13);
        return r13;
    L43:
        Response.Builder r14 = r92.newBuilder();
        if (r5 == null) goto L46;
        r2 = UnreadableResponseBodyKt.stripBody(r5);
    L46:
        Response r93 = r14.cacheResponse(r2).networkResponse(UnreadableResponseBodyKt.stripBody(r92)).build();
        if (this.cache == null) goto L59;
        Request r15 = CacheInterceptorKt.access$requestForCache(r4);
        if (HttpHeaders.promisesBody(r93) == false) goto L57;
        if (CacheStrategy.Companion.isCacheable(r93, r15) == false) goto L57;
        Response r83 = cacheWritingResponse(this.cache.put$okhttp(r93.newBuilder().request(r15).build()), r93);
        if (r5 == null) goto L55;
        r33.cacheMiss(r0);
    L55:
        return r83;
    L57:
        if (HttpMethod.invalidatesCache(r4.method()) == false) goto L59;
        this.cache.remove$okhttp(r4);     // Catch: IOException -> L64
    L59:
        return r93;
    L60:
        th = move-exception;
        if (r12 == null) goto L63;
        _UtilCommonKt.closeQuietly(r12.body());
    L63:
        throw th;
    L30:
        if (this.cache == null) goto L67;
        r33.cacheMiss(r0);
    L16:
        r33 = EventListener.NONE;
        goto L17
    L12:
        r32 = null;
        goto L13
    L5:
        r12 = null;
        goto L6
    }
}
