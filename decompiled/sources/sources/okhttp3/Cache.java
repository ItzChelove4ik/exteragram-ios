package okhttp3;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.FileSystem;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Path;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Sink;
import okio.Source;
import org.vosk.Model$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010)\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 L2\u00020\u00012\u00020\u0002:\u0004IJKLB)\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB!\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\rB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u000e\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\u000fJ\u0017\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0000¢\u0006\u0002\b'J\u0017\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020$H\u0000¢\u0006\u0002\b+J\u0015\u0010,\u001a\u00020-2\u0006\u0010%\u001a\u00020&H\u0000¢\u0006\u0002\b.J\u001d\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020$2\u0006\u00101\u001a\u00020$H\u0000¢\u0006\u0002\b2J\u0016\u00103\u001a\u00020-2\f\u00104\u001a\b\u0018\u000105R\u00020\u0011H\u0002J\u0006\u00106\u001a\u00020-J\u0006\u00107\u001a\u00020-J\u0006\u00108\u001a\u00020-J\f\u00109\u001a\b\u0012\u0004\u0012\u00020;0:J\u0006\u0010\u001a\u001a\u00020\u0015J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010<\u001a\u00020\u0006J\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010=\u001a\u00020-H\u0016J\b\u0010>\u001a\u00020-H\u0016J\r\u0010\u0003\u001a\u00020\u000eH\u0007¢\u0006\u0002\bBJ\u0015\u0010C\u001a\u00020-2\u0006\u0010D\u001a\u00020EH\u0000¢\u0006\u0002\bFJ\r\u0010G\u001a\u00020-H\u0000¢\u0006\u0002\bHJ\u0006\u0010\u001d\u001a\u00020\u0015J\u0006\u0010\u001e\u001a\u00020\u0015J\u0006\u0010\u001f\u001a\u00020\u0015R\u0014\u0010\u0010\u001a\u00020\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u000e\u0010\u001d\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b \u0010\"R\u0011\u0010\u0003\u001a\u00020\u000e8G¢\u0006\u0006\u001a\u0004\b\u0003\u0010?R\u0011\u0010@\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b@\u0010A¨\u0006M"}, d2 = {"Lokhttp3/Cache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "directory", "Lokio/Path;", "maxSize", _UrlKt.FRAGMENT_ENCODE_SET, "fileSystem", "Lokio/FileSystem;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "<init>", "(Lokio/Path;JLokio/FileSystem;Lokhttp3/internal/concurrent/TaskRunner;)V", "(Lokio/FileSystem;Lokio/Path;J)V", "Ljava/io/File;", "(Ljava/io/File;J)V", "cache", "Lokhttp3/internal/cache/DiskLruCache;", "getCache$okhttp", "()Lokhttp3/internal/cache/DiskLruCache;", "writeSuccessCount", _UrlKt.FRAGMENT_ENCODE_SET, "getWriteSuccessCount$okhttp", "()I", "setWriteSuccessCount$okhttp", "(I)V", "writeAbortCount", "getWriteAbortCount$okhttp", "setWriteAbortCount$okhttp", "networkCount", "hitCount", "requestCount", "isClosed", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "get", "Lokhttp3/Response;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "get$okhttp", "put", "Lokhttp3/internal/cache/CacheRequest;", PluginsConstants.RESPONSE, "put$okhttp", "remove", _UrlKt.FRAGMENT_ENCODE_SET, "remove$okhttp", PluginsConstants.UPDATE, "cached", "network", "update$okhttp", "abortQuietly", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "initialize", "delete", "evictAll", "urls", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "size", "flush", "close", "()Ljava/io/File;", "directoryPath", "()Lokio/Path;", "-deprecated_directory", "trackResponse", "cacheStrategy", "Lokhttp3/internal/cache/CacheStrategy;", "trackResponse$okhttp", "trackConditionalCacheHit", "trackConditionalCacheHit$okhttp", "RealCacheRequest", "Entry", "CacheResponseBody", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Cache implements Closeable, Flushable {
    public static final Companion Companion = null;
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 201105;
    private final DiskLruCache cache;
    private int hitCount;
    private int networkCount;
    private int requestCount;
    private int writeAbortCount;
    private int writeSuccessCount;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0005\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0007\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/Cache$CacheResponseBody;", "Lokhttp3/ResponseBody;", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "contentType", _UrlKt.FRAGMENT_ENCODE_SET, "contentLength", "<init>", "(Lokhttp3/internal/cache/DiskLruCache$Snapshot;Ljava/lang/String;Ljava/lang/String;)V", "getSnapshot", "()Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "bodySource", "Lokio/BufferedSource;", "Lokhttp3/MediaType;", _UrlKt.FRAGMENT_ENCODE_SET, "source", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CacheResponseBody extends ResponseBody {
        private final BufferedSource bodySource;
        private final String contentLength;
        private final String contentType;
        private final DiskLruCache.Snapshot snapshot;

        @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"okhttp3/Cache$CacheResponseBody$1", "Lokio/ForwardingSource;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
        /* renamed from: okhttp3.Cache$CacheResponseBody$1, reason: invalid class name */
        public static final class AnonymousClass1 extends ForwardingSource {
            final /* synthetic */ CacheResponseBody this$0;

            public AnonymousClass1(Source r1, CacheResponseBody r2) {
                this.this$0 = r2;
                super(r1);
            }

            @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                this.this$0.getSnapshot().close();
                super.close();
            }
        }

        public CacheResponseBody(DiskLruCache.Snapshot r1, String r2, String r3) {
            this.snapshot = r1;
            this.contentType = r2;
            this.contentLength = r3;
            this.bodySource = Okio.buffer(new AnonymousClass1(r1.getSource(1), this));
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            String r2 = this.contentLength;
            if (r2 != null) goto L5;
            return -1;
        L5:
            return _UtilCommonKt.toLongOrDefault(r2, -1);
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            String r1 = this.contentType;
            if (r1 != null) goto L5;
            return null;
        L5:
            return MediaType.Companion.parse(r1);
        }

        public final DiskLruCache.Snapshot getSnapshot() {
            return this.snapshot;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return this.bodySource;
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0015\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\n\u0010\u0019\u001a\u00020\u0012*\u00020\u0014J\u0012\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b*\u00020\u0016H\u0002J\n\u0010\u001c\u001a\u00020\u0016*\u00020\u0014J\u0018\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lokhttp3/Cache$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "VERSION", _UrlKt.FRAGMENT_ENCODE_SET, "ENTRY_METADATA", "ENTRY_BODY", "ENTRY_COUNT", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "url", "Lokhttp3/HttpUrl;", "readInt", "source", "Lokio/BufferedSource;", "readInt$okhttp", "varyMatches", _UrlKt.FRAGMENT_ENCODE_SET, "cachedResponse", "Lokhttp3/Response;", "cachedRequest", "Lokhttp3/Headers;", "newRequest", "Lokhttp3/Request;", "hasVaryAll", "varyFields", _UrlKt.FRAGMENT_ENCODE_SET, "varyHeaders", "requestHeaders", "responseHeaders", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cache.kt\nokhttp3/Cache$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,838:1\n2746#2,3:839\n*S KotlinDebug\n*F\n+ 1 Cache.kt\nokhttp3/Cache$Companion\n*L\n777#1:839,3\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final Set<String> varyFields(Headers r13) {
            int r12 = r13.size();
            TreeSet r0 = null;
            int r2 = 0;
        L3:
            if (r2 >= r12) goto L15;
            if (StringsKt.equals("Vary", r13.name(r2), true) == false) goto L14;
            String r6 = r13.value(r2);
            if (r0 != null) goto L10;
            r0 = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        L10:
            Iterator r3 = StringsKt.split$default(r6, new char[]{','}, false, 0, 6, null).iterator();
        L12:
            if (r3.hasNext() == false) goto L14;
            r0.add(StringsKt.trim((String) r3.next()).toString());
        L14:
            r2 = r2 + 1;
            goto L3
        L15:
            if (r0 == null) goto L17;
            return r0;
        L17:
            return SetsKt.emptySet();
        }

        private final Headers varyHeaders(Headers r5, Headers r6) {
            Set<String> r4 = varyFields(r6);
            if (r4.isEmpty() == true) goto L5;
            Headers.Builder r62 = new Headers.Builder();
            int r0 = r5.size();
            int r1 = 0;
        L7:
            if (r1 >= r0) goto L13;
            String r2 = r5.name(r1);
            if (r4.contains(r2) == false) goto L11;
            r62.add(r2, r5.value(r1));
        L11:
            r1 = r1 + 1;
            goto L7
        L13:
            return r62.build();
        L5:
            return Headers.EMPTY;
        }

        public final boolean hasVaryAll(Response r1) {
            return varyFields(r1.headers()).contains("*");
        }

        @JvmStatic
        public final String key(HttpUrl r1) {
            return ByteString.Companion.encodeUtf8(r1.toString()).md5().hex();
        }

        public final int readInt$okhttp(BufferedSource r5) {
            long r0 = r5.readDecimalLong();     // Catch: NumberFormatException -> L13
            String r52 = r5.readUtf8LineStrict();     // Catch: NumberFormatException -> L13
            if (r0 < 0) goto L12;
            if (r0 > 2147483647L) goto L12;
            if (r52.length() > 0) goto L12;
            return (int) r0;
        L12:
            throw new IOException("expected an int but was \"" + r0 + r52 + Typography.quote);     // Catch: NumberFormatException -> L13
        L13:
            e = move-exception;
            Model$$ExternalSyntheticBUOutline0.m(e.getMessage());
            return 0;
        }

        public final Headers varyHeaders(Response r2) {
            return varyHeaders(r2.networkResponse().request().headers(), r2.headers());
        }

        public final boolean varyMatches(Response r3, Headers r4, Request r5) {
            Set<String> r2 = varyFields(r3.headers());
            if (r2 != null) goto L5;
        L7:
            Iterator<T> r22 = r2.iterator();
        L9:
            if (r22.hasNext() == false) goto L14;
            String r0 = (String) r22.next();
            if (Intrinsics.areEqual(r4.values(r0), r5.headers(r0)) == true) goto L9;
            return false;
        L14:
            return true;
        L5:
            if (r2.isEmpty() == false) goto L7;
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 .2\u00020\u0001:\u0001.B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\u0012\u0010\u001a\u001a\u00020\u001b2\n\u0010\u001c\u001a\u00060\u001dR\u00020\u001eJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#H\u0002J\u001e\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0006\u001a\u00020\u0007J\u0012\u0010\u0006\u001a\u00020\u00072\n\u0010,\u001a\u00060-R\u00020\u001eR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lokhttp3/Cache$Entry;", _UrlKt.FRAGMENT_ENCODE_SET, "rawSource", "Lokio/Source;", "<init>", "(Lokio/Source;)V", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "url", "Lokhttp3/HttpUrl;", "varyHeaders", "Lokhttp3/Headers;", "requestMethod", _UrlKt.FRAGMENT_ENCODE_SET, "protocol", "Lokhttp3/Protocol;", "code", _UrlKt.FRAGMENT_ENCODE_SET, "message", "responseHeaders", "handshake", "Lokhttp3/Handshake;", "sentRequestMillis", _UrlKt.FRAGMENT_ENCODE_SET, "receivedResponseMillis", "writeTo", _UrlKt.FRAGMENT_ENCODE_SET, "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "readCertificateList", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/security/cert/Certificate;", "source", "Lokio/BufferedSource;", "writeCertList", "sink", "Lokio/BufferedSink;", "certificates", "matches", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Lokhttp3/Request;", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Entry {
        public static final Companion Companion = null;
        private static final String RECEIVED_MILLIS = null;
        private static final String SENT_MILLIS = null;
        private final int code;
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final long receivedResponseMillis;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final long sentRequestMillis;
        private final HttpUrl url;
        private final Headers varyHeaders;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lokhttp3/Cache$Entry$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SENT_MILLIS", _UrlKt.FRAGMENT_ENCODE_SET, "RECEIVED_MILLIS", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }
        }

        static {
            Companion = new Companion(null);
            StringBuilder r0 = new StringBuilder();
            Platform.Companion r1 = Platform.Companion;
            r0.append(r1.get().getPrefix());
            r0.append("-Sent-Millis");
            SENT_MILLIS = r0.toString();
            RECEIVED_MILLIS = r1.get().getPrefix() + "-Received-Millis";
        }

        public Entry(Response r3) {
            this.url = r3.request().url();
            this.varyHeaders = Cache.Companion.varyHeaders(r3);
            this.requestMethod = r3.request().method();
            this.protocol = r3.protocol();
            this.code = r3.code();
            this.message = r3.message();
            this.responseHeaders = r3.headers();
            this.handshake = r3.handshake();
            this.sentRequestMillis = r3.sentRequestAtMillis();
            this.receivedResponseMillis = r3.receivedResponseAtMillis();
        }

        public Entry(Source r9) {
            BufferedSource r1 = Okio.buffer(r9);     // Catch: Throwable -> L8
            String r2 = r1.readUtf8LineStrict();     // Catch: Throwable -> L8
            HttpUrl r3 = HttpUrl.Companion.parse(r2);     // Catch: Throwable -> L8
            if (r3 == null) goto L35;
            this.url = r3;     // Catch: Throwable -> L8
            this.requestMethod = r1.readUtf8LineStrict();     // Catch: Throwable -> L8
            Headers.Builder r0 = new Headers.Builder();     // Catch: Throwable -> L8
            int r22 = Cache.Companion.readInt$okhttp(r1);     // Catch: Throwable -> L8
            int r32 = 0;
            int r4 = 0;
        L6:
            if (r4 >= r22) goto L10;
            r0.addLenient$okhttp(r1.readUtf8LineStrict());     // Catch: Throwable -> L8
            r4 = r4 + 1;     // Catch: Throwable -> L8
            goto L6
        L10:
            this.varyHeaders = r0.build();     // Catch: Throwable -> L8
            StatusLine r02 = StatusLine.Companion.parse(r1.readUtf8LineStrict());     // Catch: Throwable -> L8
            this.protocol = r02.protocol;     // Catch: Throwable -> L8
            this.code = r02.code;     // Catch: Throwable -> L8
            this.message = r02.message;     // Catch: Throwable -> L8
            Headers.Builder r03 = new Headers.Builder();     // Catch: Throwable -> L8
            int r23 = Cache.Companion.readInt$okhttp(r1);     // Catch: Throwable -> L8
        L11:
            if (r32 >= r23) goto L13;
            r03.addLenient$okhttp(r1.readUtf8LineStrict());     // Catch: Throwable -> L8
            r32 = r32 + 1;     // Catch: Throwable -> L8
            goto L11
        L13:
            String r24 = SENT_MILLIS;     // Catch: Throwable -> L8
            String r33 = r03.get(r24);     // Catch: Throwable -> L8
            String r42 = RECEIVED_MILLIS;     // Catch: Throwable -> L8
            String r5 = r03.get(r42);     // Catch: Throwable -> L8
            r03.removeAll(r24);     // Catch: Throwable -> L8
            r03.removeAll(r42);     // Catch: Throwable -> L8
            long r6 = 0;
            if (r33 == null) goto L16;
            long r25 = Long.parseLong(r33);     // Catch: Throwable -> L8
        L17:
            this.sentRequestMillis = r25;     // Catch: Throwable -> L8
            if (r5 == null) goto L20;
            r6 = Long.parseLong(r5);     // Catch: Throwable -> L8
        L20:
            this.receivedResponseMillis = r6;     // Catch: Throwable -> L8
            this.responseHeaders = r03.build();     // Catch: Throwable -> L8
            if (this.url.isHttps() == false) goto L31;
            String r04 = r1.readUtf8LineStrict();     // Catch: Throwable -> L8
            if (r04.length() > 0) goto L30;
            CipherSuite r05 = CipherSuite.Companion.forJavaName(r1.readUtf8LineStrict());     // Catch: Throwable -> L8
            List<Certificate> r34 = readCertificateList(r1);     // Catch: Throwable -> L8
            List<Certificate> r43 = readCertificateList(r1);     // Catch: Throwable -> L8
            if (r1.exhausted() == true) goto L27;
            TlsVersion r12 = TlsVersion.Companion.forJavaName(r1.readUtf8LineStrict());     // Catch: Throwable -> L8
        L28:
            this.handshake = Handshake.Companion.get(r12, r05, r34, r43);     // Catch: Throwable -> L8
        L32:
            Unit r8 = Unit.INSTANCE;     // Catch: Throwable -> L8
            CloseableKt.closeFinally(r9, null);
            return;
        L27:
            r12 = TlsVersion.SSL_3_0;     // Catch: Throwable -> L8
            goto L28
        L30:
            throw new IOException("expected \"\" but was \"" + r04 + Typography.quote);     // Catch: Throwable -> L8
        L31:
            this.handshake = null;     // Catch: Throwable -> L8
            goto L32
        L16:
            r25 = 0;
            goto L17
        L35:
            IOException r82 = new IOException("Cache corruption for " + r2);     // Catch: Throwable -> L8
            Platform.Companion.get().log("cache corruption", 5, r82);     // Catch: Throwable -> L8
            throw r82;     // Catch: Throwable -> L8
        L8:
            th = move-exception;
            throw th;     // Catch: Throwable -> L38
        L38:
            th = move-exception;
            CloseableKt.closeFinally(r9, th);
            throw th;
        }

        private final List<Certificate> readCertificateList(BufferedSource r7) {
            int r6 = Cache.Companion.readInt$okhttp(r7);
            if (r6 == (-1)) goto L5;
            CertificateFactory r0 = CertificateFactory.getInstance("X.509");     // Catch: CertificateException -> L14
            ArrayList r1 = new ArrayList(r6);     // Catch: CertificateException -> L14
            int r2 = 0;
        L7:
            if (r2 >= r6) goto L13;
            String r3 = r7.readUtf8LineStrict();     // Catch: CertificateException -> L14
            Buffer r4 = new Buffer();     // Catch: CertificateException -> L14
            ByteString r32 = ByteString.Companion.decodeBase64(r3);     // Catch: CertificateException -> L14
            if (r32 == null) goto L12;
            r4.write(r32);     // Catch: CertificateException -> L14
            r1.add(r0.generateCertificate(r4.inputStream()));     // Catch: CertificateException -> L14
            r2 = r2 + 1;     // Catch: CertificateException -> L14
            goto L7
        L12:
            throw new IOException("Corrupt certificate in cache entry");     // Catch: CertificateException -> L14
        L13:
            return r1;
        L14:
            e = move-exception;
            Model$$ExternalSyntheticBUOutline0.m(e.getMessage());
            return null;
        L5:
            return CollectionsKt.emptyList();
        }

        private final void writeCertList(BufferedSink r8, List<? extends Certificate> r9) {
            r8.writeDecimalLong(r9.size()).writeByte(10);     // Catch: CertificateEncodingException -> L8
            Iterator<? extends Certificate> r7 = r9.iterator();     // Catch: CertificateEncodingException -> L8
        L3:
            if (r7.hasNext() == false) goto L7;
            r8.writeUtf8(ByteString.Companion.of$default(ByteString.Companion, r7.next().getEncoded(), 0, 0, 3, null).base64()).writeByte(10);     // Catch: CertificateEncodingException -> L8
            goto L3
        L7:
            return;
        L8:
            e = move-exception;
            Model$$ExternalSyntheticBUOutline0.m(e.getMessage());
        }

        public final boolean matches(Request r3, Response r4) {
            if (Intrinsics.areEqual(this.url, r3.url()) == true) goto L5;
            return false;
        L5:
            if (Intrinsics.areEqual(this.requestMethod, r3.method()) == true) goto L7;
            return false;
        L7:
            if (Cache.Companion.varyMatches(r4, this.varyHeaders, r3) == false) goto L13;
            return true;
        L13:
            return false;
        }

        public final Response response(DiskLruCache.Snapshot r10) {
            String r0 = this.responseHeaders.get("Content-Type");
            String r1 = this.responseHeaders.get("Content-Length");
            Request r2 = new Request(this.url, this.varyHeaders, this.requestMethod, null, 8, null);
            return new Response.Builder().request(r2).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(r10, r0, r1)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
        }

        public final void writeTo(DiskLruCache.Editor r8) {
            int r0 = 0;
            BufferedSink r82 = Okio.buffer(r8.newSink(0));
            r82.writeUtf8(this.url.toString()).writeByte(10);     // Catch: Throwable -> L8
            r82.writeUtf8(this.requestMethod).writeByte(10);     // Catch: Throwable -> L8
            r82.writeDecimalLong(this.varyHeaders.size()).writeByte(10);     // Catch: Throwable -> L8
            int r1 = this.varyHeaders.size();     // Catch: Throwable -> L8
            int r3 = 0;
        L6:
            if (r3 >= r1) goto L10;
            r82.writeUtf8(this.varyHeaders.name(r3)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(r3)).writeByte(10);     // Catch: Throwable -> L8
            r3 = r3 + 1;     // Catch: Throwable -> L8
            goto L6
        L10:
            r82.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString()).writeByte(10);     // Catch: Throwable -> L8
            r82.writeDecimalLong(this.responseHeaders.size() + 2).writeByte(10);     // Catch: Throwable -> L8
            int r12 = this.responseHeaders.size();     // Catch: Throwable -> L8
        L11:
            if (r0 >= r12) goto L13;
            r82.writeUtf8(this.responseHeaders.name(r0)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(r0)).writeByte(10);     // Catch: Throwable -> L8
            r0 = r0 + 1;     // Catch: Throwable -> L8
            goto L11
        L13:
            r82.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);     // Catch: Throwable -> L8
            r82.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);     // Catch: Throwable -> L8
            if (this.url.isHttps() == false) goto L16;
            r82.writeByte(10);     // Catch: Throwable -> L8
            r82.writeUtf8(this.handshake.cipherSuite().javaName()).writeByte(10);     // Catch: Throwable -> L8
            writeCertList(r82, this.handshake.peerCertificates());     // Catch: Throwable -> L8
            writeCertList(r82, this.handshake.localCertificates());     // Catch: Throwable -> L8
            r82.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);     // Catch: Throwable -> L8
        L16:
            Unit r7 = Unit.INSTANCE;     // Catch: Throwable -> L8
            CloseableKt.closeFinally(r82, null);
            return;
        L8:
            th = move-exception;
            throw th;     // Catch: Throwable -> L20
        L20:
            th = move-exception;
            CloseableKt.closeFinally(r82, th);
            throw th;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u0012\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lokhttp3/Cache$RealCacheRequest;", "Lokhttp3/internal/cache/CacheRequest;", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "<init>", "(Lokhttp3/Cache;Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "cacheOut", "Lokio/Sink;", "body", "done", _UrlKt.FRAGMENT_ENCODE_SET, "getDone", "()Z", "setDone", "(Z)V", "abort", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class RealCacheRequest implements CacheRequest {
        private final Sink body;
        private final Sink cacheOut;
        private boolean done;
        private final DiskLruCache.Editor editor;
        final /* synthetic */ Cache this$0;

        @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"okhttp3/Cache$RealCacheRequest$1", "Lokio/ForwardingSink;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
        /* renamed from: okhttp3.Cache$RealCacheRequest$1, reason: invalid class name */
        public static final class AnonymousClass1 extends ForwardingSink {
            final /* synthetic */ Cache this$0;
            final /* synthetic */ RealCacheRequest this$1;

            public AnonymousClass1(Cache r1, RealCacheRequest r2, Sink r3) {
                this.this$0 = r1;
                this.this$1 = r2;
                super(r3);
            }

            @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                Cache r0 = this.this$0;
                RealCacheRequest r1 = this.this$1;
                monitor-enter(r0);
            L13:
                th = move-exception;
                throw th;
            L5:
                if (r1.getDone() == false) goto L9;
                monitor-exit(r0);
                return;
            L9:
                r1.setDone(true);     // Catch: Throwable -> L13
                r0.setWriteSuccessCount$okhttp(r0.getWriteSuccessCount$okhttp() + 1);     // Catch: Throwable -> L13
                monitor-exit(r0);
                super.close();
                RealCacheRequest.access$getEditor$p(this.this$1).commit();
            }
        }

        public RealCacheRequest(Cache r2, DiskLruCache.Editor r3) {
            this.this$0 = r2;
            this.editor = r3;
            Sink r32 = r3.newSink(1);
            this.cacheOut = r32;
            this.body = new AnonymousClass1(r2, this, r32);
        }

        public static final /* synthetic */ DiskLruCache.Editor access$getEditor$p(RealCacheRequest r0) {
            return r0.editor;
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public void abort() {
            Cache r0 = this.this$0;
            monitor-enter(r0);
        L14:
            th = move-exception;
            throw th;
        L5:
            if (this.done == false) goto L9;
            monitor-exit(r0);
            return;
        L9:
            this.done = true;     // Catch: Throwable -> L14
            r0.setWriteAbortCount$okhttp(r0.getWriteAbortCount$okhttp() + 1);     // Catch: Throwable -> L14
            monitor-exit(r0);
            _UtilCommonKt.closeQuietly(this.cacheOut);
            this.editor.abort();     // Catch: IOException -> L17
            return;
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public Sink body() {
            return this.body;
        }

        public final boolean getDone() {
            return this.done;
        }

        public final void setDone(boolean r1) {
            this.done = r1;
        }
    }

    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010)\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\t\u001a\u00020\bH\u0096\u0002J\t\u0010\n\u001a\u00020\u0002H\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u00060\u0004R\u00020\u00050\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"okhttp3/Cache$urls$1", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "delegate", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "nextUrl", "canRemove", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", "next", "remove", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cache.kt\nokhttp3/Cache$urls$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,838:1\n1#2:839\n*E\n"})
    /* renamed from: okhttp3.Cache$urls$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<String>, KMutableIterator {
        private boolean canRemove;
        private final Iterator<DiskLruCache.Snapshot> delegate;
        private String nextUrl;

        public AnonymousClass1(Cache r1) {
            this.delegate = r1.getCache$okhttp().snapshots();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextUrl == null) goto L5;
            return true;
        L5:
            this.canRemove = false;
        L7:
            if (this.delegate.hasNext() == false) goto L18;
            DiskLruCache.Snapshot r2 = this.delegate.next();     // Catch: IOException -> L19
            this.nextUrl = Okio.buffer(r2.getSource(0)).readUtf8LineStrict();     // Catch: Throwable -> L13
            goto L7
        L11:
            CloseableKt.closeFinally(r2, null);     // Catch: IOException -> L19
            return true;
        L13:
            th = move-exception;
            throw th;     // Catch: Throwable -> L15
        L15:
            th = move-exception;
            CloseableKt.closeFinally(r2, th);     // Catch: IOException -> L19
            throw th;     // Catch: IOException -> L19
        L18:
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ String next() {
            return next2();
        }

        @Override // java.util.Iterator
        /* renamed from: next, reason: avoid collision after fix types in other method */
        public String next2() {
            if (hasNext() == false) goto L6;
            String r0 = this.nextUrl;
            this.nextUrl = null;
            this.canRemove = true;
            return r0;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.canRemove == false) goto L6;
            this.delegate.remove();
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("remove() before next()");
        }
    }

    static {
        Companion = new Companion(null);
    }

    public Cache(File r6, long r7) {
        this(FileSystem.SYSTEM, Path.Companion.get$default(Path.Companion, r6, false, 1, null), r7);
    }

    public Cache(FileSystem r7, Path r8, long r9) {
        this(r8, r9, r7, TaskRunner.INSTANCE);
    }

    public Cache(Path r9, long r10, FileSystem r12, TaskRunner r13) {
        this.cache = new DiskLruCache(r12, r9, VERSION, 2, r10, r13);
    }

    private final void abortQuietly(DiskLruCache.Editor r1) {
        if (r1 == null) goto L8;
        r1.abort();     // Catch: IOException -> L5
        return;
    L9:
        return;
    }

    @JvmStatic
    public static final String key(HttpUrl r1) {
        return Companion.key(r1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "directory", imports = {}))
    @JvmName(name = "-deprecated_directory")
    /* renamed from: -deprecated_directory, reason: not valid java name */
    public final File m3610deprecated_directory() {
        return this.cache.getDirectory().toFile();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.cache.close();
    }

    public final void delete() {
        this.cache.delete();
    }

    @JvmName(name = "directory")
    public final File directory() {
        return this.cache.getDirectory().toFile();
    }

    @JvmName(name = "directoryPath")
    public final Path directoryPath() {
        return this.cache.getDirectory();
    }

    public final void evictAll() {
        this.cache.evictAll();
    }

    @Override // java.io.Flushable
    public void flush() {
        this.cache.flush();
    }

    public final Response get$okhttp(Request r4) {
        String r0 = Companion.key(r4.url());
        DiskLruCache.Snapshot r3 = this.cache.get(r0);     // Catch: IOException -> L14
        if (r3 != null) goto L17;
        return null;
    L17:
        Entry r02 = new Entry(r3.getSource(0));     // Catch: IOException -> L12
        Response r32 = r02.response(r3);
        if (r02.matches(r4, r32) == true) goto L11;
        _UtilCommonKt.closeQuietly(r32.body());
        return null;
    L11:
        return r32;
    L12:
        _UtilCommonKt.closeQuietly(r3);
    L13:
        return null;
    }

    public final DiskLruCache getCache$okhttp() {
        return this.cache;
    }

    public final int getWriteAbortCount$okhttp() {
        return this.writeAbortCount;
    }

    public final int getWriteSuccessCount$okhttp() {
        return this.writeSuccessCount;
    }

    public final synchronized int hitCount() {
        monitor-enter(this);
        int r0 = this.hitCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final void initialize() {
        this.cache.initialize();
    }

    public final boolean isClosed() {
        return this.cache.isClosed();
    }

    public final long maxSize() {
        return this.cache.getMaxSize();
    }

    public final synchronized int networkCount() {
        monitor-enter(this);
        int r0 = this.networkCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final CacheRequest put$okhttp(Response r10) {
        String r0 = r10.request().method();
        if (HttpMethod.invalidatesCache(r10.request().method()) == false) goto L7;
        remove$okhttp(r10.request());     // Catch: IOException -> L22
    L5:
        return null;
    L7:
        if (Intrinsics.areEqual(r0, "GET") == true) goto L9;
        return null;
    L9:
        Companion r02 = Companion;
        if (r02.hasVaryAll(r10) == false) goto L12;
        return null;
    L12:
        Entry r1 = new Entry(r10);
        DiskLruCache.Editor r102 = DiskLruCache.edit$default(this.cache, r02.key(r10.request().url()), 0, 2, null);     // Catch: IOException -> L18
        if (r102 != null) goto L23;
        return null;
    L23:
        r1.writeTo(r102);     // Catch: IOException -> L21
        return new RealCacheRequest(this, r102);
    L19:
        abortQuietly(r102);
        return null;
    L18:
        r102 = null;
        goto L19
    }

    public final void remove$okhttp(Request r2) {
        this.cache.remove(Companion.key(r2.url()));
    }

    public final synchronized int requestCount() {
        monitor-enter(this);
        int r0 = this.requestCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final void setWriteAbortCount$okhttp(int r1) {
        this.writeAbortCount = r1;
    }

    public final void setWriteSuccessCount$okhttp(int r1) {
        this.writeSuccessCount = r1;
    }

    public final long size() {
        return this.cache.size();
    }

    public final synchronized void trackConditionalCacheHit$okhttp() {
        monitor-enter(this);
        this.hitCount++;
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized void trackResponse$okhttp(CacheStrategy r2) {
        monitor-enter(this);
        this.requestCount++;
        if (r2.getNetworkRequest() == null) goto L9;
        this.networkCount++;
    L11:
        monitor-exit(this);
        return;
    L9:
        if (r2.getCacheResponse() == null) goto L11;
        this.hitCount++;
    L6:
        th = move-exception;
        throw th;
    }

    public final void update$okhttp(Response r2, Response r3) {
        Entry r0 = new Entry(r3);
        DiskLruCache.Editor r22 = ((CacheResponseBody) r2.body()).getSnapshot().edit();     // Catch: IOException -> L8
        if (r22 != null) goto L14;
        return;
    L14:
        r0.writeTo(r22);     // Catch: IOException -> L11
        r22.commit();     // Catch: IOException -> L11
        return;
    L9:
        abortQuietly(r22);
        return;
    L8:
        r22 = null;
        goto L9
    }

    public final Iterator<String> urls() {
        return new AnonymousClass1(this);
    }

    public final synchronized int writeAbortCount() {
        monitor-enter(this);
        int r0 = this.writeAbortCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized int writeSuccessCount() {
        monitor-enter(this);
        int r0 = this.writeSuccessCount;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }
}
