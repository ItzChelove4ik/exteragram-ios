package okhttp3.internal.cache;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.http.DateFormattingKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0002\f\rB\u001d\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy;", _UrlKt.FRAGMENT_ENCODE_SET, "networkRequest", "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "<init>", "(Lokhttp3/Request;Lokhttp3/Response;)V", "getNetworkRequest", "()Lokhttp3/Request;", "getCacheResponse", "()Lokhttp3/Response;", "Factory", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CacheStrategy {
    public static final Companion Companion = null;
    private final Response cacheResponse;
    private final Request networkRequest;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isCacheable", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.RESPONSE, "Lokhttp3/Response;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isCacheable(Response r4, Request r5) {
            int r3 = r4.code();
            if (r3 == 200) goto L34;
            if (r3 == 410) goto L34;
            if (r3 == 414) goto L34;
            if (r3 == 501) goto L34;
            if (r3 == 203) goto L34;
            if (r3 == 204) goto L34;
            if (r3 == 307) goto L25;
            if (r3 == 308) goto L34;
            if (r3 == 404) goto L34;
            if (r3 == 405) goto L34;
            switch(r3) {
                case 300: goto L34;
                case 301: goto L34;
                case 302: goto L25;
                default: goto L23;
            };
        L23:
            return false;
        L25:
            if (Response.header$default(r4, "Expires", null, 2, null) != null) goto L34;
            if (r4.cacheControl().maxAgeSeconds() != (-1)) goto L34;
            if (r4.cacheControl().isPublic() == true) goto L34;
            if (r4.cacheControl().isPrivate() == true) goto L34;
            return false;
        L34:
            if (r4.cacheControl().noStore() == false) goto L36;
        L39:
            return false;
        L36:
            if (r5.cacheControl().noStore() == true) goto L39;
            return true;
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u0003H\u0002J\b\u0010\u001e\u001a\u00020\u0003H\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Factory;", _UrlKt.FRAGMENT_ENCODE_SET, "nowMillis", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "<init>", "(JLokhttp3/Request;Lokhttp3/Response;)V", "getRequest$okhttp", "()Lokhttp3/Request;", "servedDate", "Ljava/util/Date;", "servedDateString", _UrlKt.FRAGMENT_ENCODE_SET, "lastModified", "lastModifiedString", "expires", "sentRequestMillis", "receivedResponseMillis", "etag", "ageSeconds", _UrlKt.FRAGMENT_ENCODE_SET, "isFreshnessLifetimeHeuristic", _UrlKt.FRAGMENT_ENCODE_SET, "compute", "Lokhttp3/internal/cache/CacheStrategy;", "computeCandidate", "computeFreshnessLifetime", "cacheResponseAge", "hasConditions", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Factory {
        private int ageSeconds;
        private final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        private final long nowMillis;
        private long receivedResponseMillis;
        private final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(long r5, Request r7, Response r8) {
            this.nowMillis = r5;
            this.request = r7;
            this.cacheResponse = r8;
            this.ageSeconds = -1;
            if (r8 == null) goto L22;
            this.sentRequestMillis = r8.sentRequestAtMillis();
            this.receivedResponseMillis = r8.receivedResponseAtMillis();
            Headers r6 = r8.headers();
            int r72 = r6.size();
            int r82 = 0;
        L5:
            if (r82 >= r72) goto L30;
            String r0 = r6.name(r82);
            String r1 = r6.value(r82);
            if (StringsKt.equals(r0, "Date", true) == false) goto L10;
            this.servedDate = DateFormattingKt.toHttpDateOrNull(r1);
            this.servedDateString = r1;
        L21:
            r82 = r82 + 1;
            goto L5
        L10:
            if (StringsKt.equals(r0, "Expires", true) == false) goto L13;
            this.expires = DateFormattingKt.toHttpDateOrNull(r1);
            goto L21
        L13:
            if (StringsKt.equals(r0, "Last-Modified", true) == false) goto L16;
            this.lastModified = DateFormattingKt.toHttpDateOrNull(r1);
            this.lastModifiedString = r1;
            goto L21
        L16:
            if (StringsKt.equals(r0, "ETag", true) == false) goto L19;
            this.etag = r1;
            goto L21
        L19:
            if (StringsKt.equals(r0, "Age", true) == false) goto L21;
            this.ageSeconds = _UtilCommonKt.toNonNegativeInt(r1, -1);
            goto L21
        L30:
            return;
        }

        private final long cacheResponseAge() {
            Date r0 = this.servedDate;
            if (r0 == null) goto L5;
            long r3 = Math.max(0, this.receivedResponseMillis - r0.getTime());
        L6:
            int r02 = this.ageSeconds;
            if (r02 == (-1)) goto L10;
            r3 = Math.max(r3, TimeUnit.SECONDS.toMillis(r02));
        L10:
            return (r3 + Math.max(0, this.receivedResponseMillis - this.sentRequestMillis)) + Math.max(0, this.nowMillis - this.receivedResponseMillis);
        L5:
            r3 = 0;
            goto L6
        }

        private final CacheStrategy computeCandidate() {
            if (this.cacheResponse != null) goto L7;
            return new CacheStrategy(this.request, null);
        L7:
            if (this.request.isHttps() == false) goto L13;
            if (this.cacheResponse.handshake() != null) goto L13;
            return new CacheStrategy(this.request, null);
        L13:
            if (CacheStrategy.Companion.isCacheable(this.cacheResponse, this.request) == false) goto L15;
            CacheControl r0 = this.request.cacheControl();
            if (r0.noCache() == true) goto L62;
            if (hasConditions(this.request) == true) goto L62;
            CacheControl r2 = this.cacheResponse.cacheControl();
            long r3 = cacheResponseAge();
            long r5 = computeFreshnessLifetime();
            int r7 = r0.maxAgeSeconds();
            TimeUnit r8 = TimeUnit.SECONDS;
            if (r7 == (-1)) goto L24;
            r5 = Math.min(r5, r8.toMillis(r0.maxAgeSeconds()));
        L24:
            long r10 = 0;
            if (r0.minFreshSeconds() == (-1)) goto L27;
            long r12 = r8.toMillis(r0.minFreshSeconds());
        L29:
            if (r2.mustRevalidate() == true) goto L34;
            if (r0.maxStaleSeconds() == (-1)) goto L34;
            r10 = r8.toMillis(r0.maxStaleSeconds());
        L34:
            if (r2.noCache() == true) goto L47;
            long r122 = r12 + r3;
            if (r122 >= (r10 + r5)) goto L47;
            Response.Builder r02 = this.cacheResponse.newBuilder();
            if (r122 < r5) goto L41;
            r02.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
        L41:
            if (r3 <= DurationKt.MILLIS_IN_DAY) goto L46;
            if (isFreshnessLifetimeHeuristic() == false) goto L46;
            r02.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
        L46:
            return new CacheStrategy(null, r02.build());
        L47:
            String r03 = this.etag;
            if (r03 == null) goto L51;
            String r1 = "If-None-Match";
        L57:
            Headers.Builder r22 = this.request.headers().newBuilder();
            r22.addLenient$okhttp(r1, r03);
            return new CacheStrategy(this.request.newBuilder().headers(r22.build()).build(), this.cacheResponse);
        L51:
            if (this.lastModified == null) goto L55;
            r03 = this.lastModifiedString;
        L53:
            r1 = "If-Modified-Since";
            goto L57
        L55:
            if (this.servedDate == null) goto L60;
            r03 = this.servedDateString;
            goto L53
        L60:
            return new CacheStrategy(this.request, null);
        L27:
            r12 = 0;
        L62:
            return new CacheStrategy(this.request, null);
        L15:
            return new CacheStrategy(this.request, null);
        }

        private final long computeFreshnessLifetime() {
            CacheControl r0 = this.cacheResponse.cacheControl();
            if (r0.maxAgeSeconds() != (-1)) goto L5;
            Date r02 = this.expires;
            if (r02 == null) goto L17;
            Date r3 = this.servedDate;
            if (r3 == null) goto L11;
            long r32 = r3.getTime();
        L12:
            long r5 = r02.getTime() - r32;
            if (r5 <= 0) goto L15;
            return r5;
        L15:
            return 0;
        L11:
            r32 = this.receivedResponseMillis;
            goto L12
        L17:
            if (this.lastModified != null) goto L19;
        L28:
            return 0;
        L19:
            if (this.cacheResponse.request().url().query() != null) goto L28;
            Date r03 = this.servedDate;
            if (r03 == null) goto L23;
            long r33 = r03.getTime();
        L24:
            long r34 = r33 - this.lastModified.getTime();
            if (r34 <= 0) goto L28;
            return r34 / 10;
        L23:
            r33 = this.sentRequestMillis;
            goto L24
        L5:
            return TimeUnit.SECONDS.toMillis(r0.maxAgeSeconds());
        }

        private final boolean hasConditions(Request r1) {
            if (r1.header("If-Modified-Since") == null) goto L5;
            return true;
        L5:
            if (r1.header("If-None-Match") != null) goto L11;
            return false;
        L11:
            return true;
        }

        private final boolean isFreshnessLifetimeHeuristic() {
            if (this.cacheResponse.cacheControl().maxAgeSeconds() == (-1)) goto L5;
            return false;
        L5:
            if (this.expires != null) goto L10;
            return true;
        L10:
            return false;
        }

        public final CacheStrategy compute() {
            CacheStrategy r0 = computeCandidate();
            if (r0.getNetworkRequest() != null) goto L5;
        L8:
            return r0;
        L5:
            if (this.request.cacheControl().onlyIfCached() == false) goto L8;
            return new CacheStrategy(null, null);
        }

        public final Request getRequest$okhttp() {
            return this.request;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CacheStrategy(Request r1, Response r2) {
        this.networkRequest = r1;
        this.cacheResponse = r2;
    }

    public final Response getCacheResponse() {
        return this.cacheResponse;
    }

    public final Request getNetworkRequest() {
        return this.networkRequest;
    }
}
