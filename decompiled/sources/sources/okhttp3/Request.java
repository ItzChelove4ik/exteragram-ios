package okhttp3;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.internal.EmptyTags;
import okhttp3.internal.IsProbablyUtf8Kt;
import okhttp3.internal.Tags;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.http.GzipRequestBody;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u00015B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B1\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0004\u0010\u000eJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001e\u001a\u00020\u000bJ\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001f2\u0006\u0010\u001e\u001a\u00020\u000bJ\u001e\u0010 \u001a\u0004\u0018\u0001H!\"\n\b\u0000\u0010!\u0018\u0001*\u00020\u0001H\u0087\b¢\u0006\u0004\b\"\u0010#J%\u0010 \u001a\u0004\u0018\u0001H!\"\b\b\u0000\u0010!*\u00020\u00012\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H!0%¢\u0006\u0002\u0010&J\b\u0010 \u001a\u0004\u0018\u00010\u0001J#\u0010 \u001a\u0004\u0018\u0001H!\"\u0004\b\u0000\u0010!2\u000e\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u0002H!0'¢\u0006\u0002\u0010(J\u0006\u0010)\u001a\u00020\u0003J\r\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\b,J\r\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0002\b-J\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b.J\u000f\u0010\f\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0002\b/J\r\u0010*\u001a\u00020\u0019H\u0007¢\u0006\u0002\b0J\b\u00101\u001a\u00020\u000bH\u0016J\u0012\u00102\u001a\u00020\u000b2\b\b\u0002\u00103\u001a\u00020\u001bH\u0007J\f\u00104\u001a\u00020\u000b*\u00020\u000bH\u0002R\u0013\u0010\u0006\u001a\u00020\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR\u0013\u0010\n\u001a\u00020\u000b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0010R\u0013\u0010\b\u001a\u00020\t8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0015\u0010\f\u001a\u0004\u0018\u00010\r8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0012R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0011\u0010*\u001a\u00020\u00198G¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u00066"}, d2 = {"Lokhttp3/Request;", _UrlKt.FRAGMENT_ENCODE_SET, "builder", "Lokhttp3/Request$Builder;", "<init>", "(Lokhttp3/Request$Builder;)V", "url", "Lokhttp3/HttpUrl;", "headers", "Lokhttp3/Headers;", "method", _UrlKt.FRAGMENT_ENCODE_SET, "body", "Lokhttp3/RequestBody;", "(Lokhttp3/HttpUrl;Lokhttp3/Headers;Ljava/lang/String;Lokhttp3/RequestBody;)V", "()Lokhttp3/HttpUrl;", "()Ljava/lang/String;", "()Lokhttp3/Headers;", "()Lokhttp3/RequestBody;", "cacheUrlOverride", "tags", "Lokhttp3/internal/Tags;", "getTags$okhttp", "()Lokhttp3/internal/Tags;", "lazyCacheControl", "Lokhttp3/CacheControl;", "isHttps", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", PluginsConstants.Settings.TYPE_HEADER, "name", _UrlKt.FRAGMENT_ENCODE_SET, "tag", "T", "reifiedTag", "()Ljava/lang/Object;", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "newBuilder", "cacheControl", "()Lokhttp3/CacheControl;", "-deprecated_url", "-deprecated_method", "-deprecated_headers", "-deprecated_body", "-deprecated_cacheControl", "toString", "toCurl", "includeBody", "shellEscape", "Builder", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Request.kt\nokhttp3/Request\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,495:1\n92#1:497\n1#2:496\n1878#3,3:498\n*S KotlinDebug\n*F\n+ 1 Request.kt\nokhttp3/Request\n*L\n107#1:497\n180#1:498,3\n*E\n"})
/* loaded from: classes.dex */
public final class Request {
    private final RequestBody body;
    private final HttpUrl cacheUrlOverride;
    private final Headers headers;
    private CacheControl lazyCacheControl;
    private final String method;
    private final Tags tags;
    private final HttpUrl url;

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u000eH\u0016J\u0010\u0010(\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000eH\u0002J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020)H\u0016J\u0018\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000eH\u0016J\u0018\u0010-\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000eH\u0016J\u0010\u0010.\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020/H\u0016J\u0010\u00100\u001a\u00020\u00002\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u0000H\u0016J\b\u00103\u001a\u00020\u0000H\u0016J\u0010\u00104\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0014\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0017J\u0010\u00106\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u00107\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u00108\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u00109\u001a\u00020\u0000\"\n\b\u0000\u0010:\u0018\u0001*\u00020\u00012\b\u00109\u001a\u0004\u0018\u0001H:H\u0087\b¢\u0006\u0004\b;\u0010<J-\u00109\u001a\u00020\u0000\"\b\b\u0000\u0010:*\u00020\u00012\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H:0>2\b\u00109\u001a\u0004\u0018\u0001H:¢\u0006\u0002\u0010?J\u0012\u00109\u001a\u00020\u00002\b\u00109\u001a\u0004\u0018\u00010\u0001H\u0016J-\u00109\u001a\u00020\u0000\"\u0004\b\u0000\u0010:2\u000e\u0010=\u001a\n\u0012\u0006\b\u0000\u0012\u0002H:0@2\b\u00109\u001a\u0004\u0018\u0001H:H\u0016¢\u0006\u0002\u0010AJ\u0010\u0010\u001f\u001a\u00020\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010\bJ\u0006\u0010B\u001a\u00020\u0000J\b\u0010C\u001a\u00020\u0005H\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\n\"\u0004\b!\u0010\fR\u001a\u0010\"\u001a\u00020#X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006D"}, d2 = {"Lokhttp3/Request$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", PluginsConstants.REQUEST, "Lokhttp3/Request;", "(Lokhttp3/Request;)V", "url", "Lokhttp3/HttpUrl;", "getUrl$okhttp", "()Lokhttp3/HttpUrl;", "setUrl$okhttp", "(Lokhttp3/HttpUrl;)V", "method", _UrlKt.FRAGMENT_ENCODE_SET, "getMethod$okhttp", "()Ljava/lang/String;", "setMethod$okhttp", "(Ljava/lang/String;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "body", "Lokhttp3/RequestBody;", "getBody$okhttp", "()Lokhttp3/RequestBody;", "setBody$okhttp", "(Lokhttp3/RequestBody;)V", "cacheUrlOverride", "getCacheUrlOverride$okhttp", "setCacheUrlOverride$okhttp", "tags", "Lokhttp3/internal/Tags;", "getTags$okhttp", "()Lokhttp3/internal/Tags;", "setTags$okhttp", "(Lokhttp3/internal/Tags;)V", "canonicalUrl", "Ljava/net/URL;", PluginsConstants.Settings.TYPE_HEADER, "name", "value", "addHeader", "removeHeader", "Lokhttp3/Headers;", "cacheControl", "Lokhttp3/CacheControl;", "get", "head", "post", "delete", "put", "patch", "query", "tag", "T", "reifiedTag", "(Ljava/lang/Object;)Lokhttp3/Request$Builder;", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "Ljava/lang/Class;", "(Ljava/lang/Class;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "gzip", "build", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Builder {
        private RequestBody body;
        private HttpUrl cacheUrlOverride;
        private Headers.Builder headers;
        private String method;
        private Tags tags;
        private HttpUrl url;

        public Builder() {
            this.tags = EmptyTags.INSTANCE;
            this.method = "GET";
            this.headers = new Headers.Builder();
        }

        public Builder(Request r2) {
            this.tags = EmptyTags.INSTANCE;
            this.url = r2.url();
            this.method = r2.method();
            this.body = r2.body();
            this.tags = r2.getTags$okhttp();
            this.headers = r2.headers().newBuilder();
            this.cacheUrlOverride = r2.cacheUrlOverride();
        }

        private final String canonicalUrl(String r2) {
            if (StringsKt.startsWith(r2, "ws:", true) == false) goto L7;
            return "http:".concat(r2.substring(3));
        L7:
            if (StringsKt.startsWith(r2, "wss:", true) == true) goto L9;
            return r2;
        L9:
            return "https:".concat(r2.substring(4));
        }

        public static /* synthetic */ Builder delete$default(Builder r0, RequestBody r1, int r2, Object r3) {
            if (r3 == null) goto L4;
            ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: delete");
            return null;
        L4:
            if ((r2 & 1) == 0) goto L7;
            r1 = RequestBody.EMPTY;
        L7:
            return r0.delete(r1);
        }

        public Builder addHeader(String r2, String r3) {
            this.headers.add(r2, r3);
            return this;
        }

        public Request build() {
            return new Request(this);
        }

        public Builder cacheControl(CacheControl r3) {
            String r32 = r3.toString();
            if (r32.length() != 0) goto L7;
            return removeHeader("Cache-Control");
        L7:
            return header("Cache-Control", r32);
        }

        public final Builder cacheUrlOverride(HttpUrl r1) {
            this.cacheUrlOverride = r1;
            return this;
        }

        @JvmOverloads
        public final Builder delete() {
            return delete$default(this, null, 1, null);
        }

        @JvmOverloads
        public Builder delete(RequestBody r2) {
            return method("DELETE", r2);
        }

        public Builder get() {
            return method("GET", null);
        }

        public final RequestBody getBody$okhttp() {
            return this.body;
        }

        public final HttpUrl getCacheUrlOverride$okhttp() {
            return this.cacheUrlOverride;
        }

        public final Headers.Builder getHeaders$okhttp() {
            return this.headers;
        }

        public final String getMethod$okhttp() {
            return this.method;
        }

        public final Tags getTags$okhttp() {
            return this.tags;
        }

        public final HttpUrl getUrl$okhttp() {
            return this.url;
        }

        public final Builder gzip() {
            RequestBody r0 = this.body;
            if (r0 == null) goto L10;
            String r2 = this.headers.get("Content-Encoding");
            if (r2 != null) goto L8;
            this.headers.add("Content-Encoding", "gzip");
            this.body = new GzipRequestBody(r0);
            return this;
        L8:
            OkHttpClient$$ExternalSyntheticBUOutline0.m("Content-Encoding already set: ", r2);
            return null;
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("cannot gzip a request that has no body");
            return null;
        }

        public Builder head() {
            return method("HEAD", null);
        }

        public Builder header(String r2, String r3) {
            this.headers.set(r2, r3);
            return this;
        }

        public Builder headers(Headers r1) {
            this.headers = r1.newBuilder();
            return this;
        }

        public Builder method(String r3, RequestBody r4) {
            if (r3.length() > 0) goto L5;
            f$$ExternalSyntheticBUOutline1.m("method.isEmpty() == true");
            return null;
        L5:
            if (r4 != null) goto L12;
            if (HttpMethod.requiresRequestBody(r3) == false) goto L13;
            Request$Builder$$ExternalSyntheticBUOutline0.m("method ", r3, " must have a request body.");
            return null;
        L13:
            this.method = r3;
            this.body = r4;
            return this;
        L12:
            if (HttpMethod.permitsRequestBody(r3) == true) goto L13;
            Request$Builder$$ExternalSyntheticBUOutline0.m("method ", r3, " must not have a request body.");
            return null;
        }

        public Builder patch(RequestBody r2) {
            return method("PATCH", r2);
        }

        public Builder post(RequestBody r2) {
            return method("POST", r2);
        }

        public Builder put(RequestBody r2) {
            return method("PUT", r2);
        }

        public Builder query(RequestBody r2) {
            return method("QUERY", r2);
        }

        @JvmName(name = "reifiedTag")
        public final /* synthetic */ <T> Builder reifiedTag(T r3) {
            Intrinsics.reifiedOperationMarker(4, "T");
            return tag(Reflection.getOrCreateKotlinClass(Object.class), r3);
        }

        public Builder removeHeader(String r2) {
            this.headers.removeAll(r2);
            return this;
        }

        public final void setBody$okhttp(RequestBody r1) {
            this.body = r1;
        }

        public final void setCacheUrlOverride$okhttp(HttpUrl r1) {
            this.cacheUrlOverride = r1;
        }

        public final void setHeaders$okhttp(Headers.Builder r1) {
            this.headers = r1;
        }

        public final void setMethod$okhttp(String r1) {
            this.method = r1;
        }

        public final void setTags$okhttp(Tags r1) {
            this.tags = r1;
        }

        public final void setUrl$okhttp(HttpUrl r1) {
            this.url = r1;
        }

        public <T> Builder tag(Class<? super T> r1, T r2) {
            return tag(JvmClassMappingKt.getKotlinClass(r1), r2);
        }

        public Builder tag(Object r2) {
            return tag(Reflection.getOrCreateKotlinClass(Object.class), r2);
        }

        public final <T> Builder tag(KClass<T> r2, T r3) {
            this.tags = this.tags.plus(r2, r3);
            return this;
        }

        public Builder url(String r2) {
            return url(HttpUrl.Companion.get(canonicalUrl(r2)));
        }

        public Builder url(URL r2) {
            return url(HttpUrl.Companion.get(r2.toString()));
        }

        public Builder url(HttpUrl r1) {
            this.url = r1;
            return this;
        }
    }

    public Request(HttpUrl r2, Headers r3, String r4, RequestBody r5) {
        Builder r22 = new Builder().url(r2).headers(r3);
        if (Intrinsics.areEqual(r4, WebViewProviderFactoryBoundaryInterface.MULTI_COOKIE_VALUE_SEPARATOR) == false) goto L8;
        if (r5 == null) goto L7;
        r4 = "POST";
        goto L8
    L7:
        r4 = "GET";
    L8:
        this(r22.method(r4, r5));
    }

    public /* synthetic */ Request(HttpUrl r1, Headers r2, String r3, RequestBody r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 2) == 0) goto L6;
        r2 = Headers.Companion.of(new String[0]);
    L6:
        if ((r5 & 4) == 0) goto L9;
        r3 = WebViewProviderFactoryBoundaryInterface.MULTI_COOKIE_VALUE_SEPARATOR;
    L9:
        if ((r5 & 8) == 0) goto L11;
        r4 = null;
    L11:
        this(r1, r2, r3, r4);
    }

    public Request(Builder r2) {
        HttpUrl r0 = r2.getUrl$okhttp();
        if (r0 == null) goto L6;
        this.url = r0;
        this.method = r2.getMethod$okhttp();
        this.headers = r2.getHeaders$okhttp().build();
        this.body = r2.getBody$okhttp();
        this.cacheUrlOverride = r2.getCacheUrlOverride$okhttp();
        this.tags = r2.getTags$okhttp();
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("url == null");
        throw null;
    }

    private final String shellEscape(String r8) {
        return "'" + StringsKt.replace$default(r8, "'", "'\\''", false, 4, null) + '\'';
    }

    public static /* synthetic */ String toCurl$default(Request r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = true;
    L6:
        return r0.toCurl(r1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}))
    @JvmName(name = "-deprecated_body")
    /* renamed from: -deprecated_body, reason: not valid java name */
    public final RequestBody m3721deprecated_body() {
        return this.body;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheControl", imports = {}))
    @JvmName(name = "-deprecated_cacheControl")
    /* renamed from: -deprecated_cacheControl, reason: not valid java name */
    public final CacheControl m3722deprecated_cacheControl() {
        return cacheControl();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    @JvmName(name = "-deprecated_headers")
    /* renamed from: -deprecated_headers, reason: not valid java name */
    public final Headers m3723deprecated_headers() {
        return this.headers;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "method", imports = {}))
    @JvmName(name = "-deprecated_method")
    /* renamed from: -deprecated_method, reason: not valid java name */
    public final String m3724deprecated_method() {
        return this.method;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "url", imports = {}))
    @JvmName(name = "-deprecated_url")
    /* renamed from: -deprecated_url, reason: not valid java name */
    public final HttpUrl m3725deprecated_url() {
        return this.url;
    }

    @JvmName(name = "body")
    public final RequestBody body() {
        return this.body;
    }

    @JvmName(name = "cacheControl")
    public final CacheControl cacheControl() {
        CacheControl r0 = this.lazyCacheControl;
        if (r0 != null) goto L6;
        CacheControl r02 = CacheControl.Companion.parse(this.headers);
        this.lazyCacheControl = r02;
        return r02;
    L6:
        return r0;
    }

    @JvmName(name = "cacheUrlOverride")
    public final HttpUrl cacheUrlOverride() {
        return this.cacheUrlOverride;
    }

    public final Tags getTags$okhttp() {
        return this.tags;
    }

    public final String header(String r1) {
        return this.headers.get(r1);
    }

    public final List<String> headers(String r1) {
        return this.headers.values(r1);
    }

    @JvmName(name = "headers")
    public final Headers headers() {
        return this.headers;
    }

    public final boolean isHttps() {
        return this.url.isHttps();
    }

    @JvmName(name = "method")
    public final String method() {
        return this.method;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    @JvmName(name = "reifiedTag")
    public final /* synthetic */ <T> T reifiedTag() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) tag(Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final Object tag() {
        return tag(Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final <T> T tag(Class<? extends T> r1) {
        return (T) tag(JvmClassMappingKt.getKotlinClass(r1));
    }

    public final <T> T tag(KClass<T> r2) {
        return (T) JvmClassMappingKt.getJavaClass(r2).cast(this.tags.get(r2));
    }

    @JvmOverloads
    public final String toCurl() {
        return toCurl$default(this, false, 1, null);
    }

    @JvmOverloads
    public final String toCurl(boolean r10) {
        StringBuilder r0 = new StringBuilder();
        r0.append("curl " + shellEscape(this.url.toString()));
        RequestBody r1 = this.body;
        if (r1 == null) goto L7;
        MediaType r12 = r1.contentType();
        if (r12 == null) goto L7;
        String r13 = r12.toString();
    L8:
        if (r10 == true) goto L10;
    L12:
        String r3 = "GET";
    L14:
        if (Intrinsics.areEqual(this.method, r3) == true) goto L16;
        r0.append(" \\\n  -X " + shellEscape(this.method));
    L16:
        Iterator<Pair<? extends String, ? extends String>> r32 = this.headers.iterator();
    L18:
        if (r32.hasNext() == false) goto L24;
        Pair<? extends String, ? extends String> r4 = r32.next();
        String r7 = r4.component1();
        String r42 = r4.component2();
        if (r13 == null) goto L23;
        if (StringsKt.equals(r7, "Content-Type", true) == true) goto L18;
    L23:
        StringBuilder r6 = new StringBuilder(" \\\n  -H ");
        r6.append(shellEscape(r7 + ": " + r42));
        r0.append(r6.toString());
        goto L18
    L24:
        if (r13 == null) goto L26;
        r0.append(" \\\n  -H " + shellEscape("Content-Type: ".concat(r13)));
    L26:
        if (r10 == false) goto L34;
        if (this.body == null) goto L34;
        Buffer r102 = new Buffer();
        this.body.writeTo(r102);
        if (IsProbablyUtf8Kt.isProbablyUtf8$default(r102, 0, 1, null) == false) goto L32;
        r0.append(" \\\n  --data " + shellEscape(r102.readUtf8()));
        goto L34
    L32:
        r0.append(" \\\n  --data-binary " + shellEscape(r102.readByteString().hex()));
    L34:
        return r0.toString();
    L10:
        if (this.body == null) goto L12;
        r3 = "POST";
    L7:
        r13 = null;
        goto L8
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(32);
        r0.append("Request{method=");
        r0.append(this.method);
        r0.append(", url=");
        r0.append(this.url);
        if (this.headers.size() == 0) goto L19;
        r0.append(", headers=[");
        Iterator<Pair<? extends String, ? extends String>> r1 = this.headers.iterator();
        int r2 = 0;
    L6:
        if (r1.hasNext() == false) goto L17;
        Pair<? extends String, ? extends String> r3 = r1.next();
        int r4 = r2 + 1;
        if (r2 >= 0) goto L10;
        CollectionsKt.throwIndexOverflow();
    L10:
        Pair<? extends String, ? extends String> r32 = r3;
        String r5 = r32.component1();
        String r33 = r32.component2();
        if (r2 <= 0) goto L13;
        r0.append(", ");
    L13:
        r0.append(r5);
        r0.append(':');
        if (_UtilCommonKt.isSensitiveHeader(r5) == false) goto L16;
        r33 = "██";
    L16:
        r0.append(r33);
        r2 = r4;
        goto L6
    L17:
        r0.append(']');
    L19:
        if (Intrinsics.areEqual(this.tags, EmptyTags.INSTANCE) == true) goto L21;
        r0.append(", tags=");
        r0.append(this.tags);
    L21:
        r0.append('}');
        return r0.toString();
    }

    @JvmName(name = "url")
    public final HttpUrl url() {
        return this.url;
    }
}
