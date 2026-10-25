package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaStatus;
import com.google.common.base.Predicate;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/* loaded from: classes3.dex */
public class DefaultHttpDataSource extends BaseDataSource implements DataSource {
    private final boolean allowCrossProtocolRedirects;
    private long bytesRead;
    private long bytesToRead;
    private final int connectTimeoutMillis;
    private HttpURLConnection connection;
    private final Predicate<String> contentTypePredicate;
    private final boolean crossProtocolRedirectsForceOriginal;
    private DataSpec dataSpec;
    private final HttpDataSource$RequestProperties defaultRequestProperties;
    private InputStream inputStream;
    private final boolean keepPostFor302Redirects;
    private final int readTimeoutMillis;
    private final HttpDataSource$RequestProperties requestProperties;
    private int responseCode;
    private boolean transferStarted;
    private final String userAgent;

    /* renamed from: androidx.media3.datasource.DefaultHttpDataSource$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Factory implements DataSource.Factory {
        private boolean allowCrossProtocolRedirects;
        private int connectTimeoutMs;
        private Predicate<String> contentTypePredicate;
        private boolean crossProtocolRedirectsForceOriginal;
        private final HttpDataSource$RequestProperties defaultRequestProperties;
        private boolean keepPostFor302Redirects;
        private int readTimeoutMs;
        private TransferListener transferListener;
        private String userAgent;

        public Factory() {
            this.defaultRequestProperties = new HttpDataSource$RequestProperties();
            this.connectTimeoutMs = 8000;
            this.readTimeoutMs = 8000;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public /* bridge */ /* synthetic */ DataSource createDataSource() {
            return createDataSource();
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public DefaultHttpDataSource createDataSource() {
            DefaultHttpDataSource r0 = new DefaultHttpDataSource(this.userAgent, this.connectTimeoutMs, this.readTimeoutMs, this.allowCrossProtocolRedirects, this.crossProtocolRedirectsForceOriginal, this.defaultRequestProperties, this.contentTypePredicate, this.keepPostFor302Redirects, null);
            TransferListener r10 = this.transferListener;
            if (r10 == null) goto L5;
            r0.addTransferListener(r10);
        L5:
            return r0;
        }

        public Factory setAllowCrossProtocolRedirects(boolean r1) {
            this.allowCrossProtocolRedirects = r1;
            return this;
        }

        public Factory setConnectTimeoutMs(int r1) {
            this.connectTimeoutMs = r1;
            return this;
        }

        public Factory setReadTimeoutMs(int r1) {
            this.readTimeoutMs = r1;
            return this;
        }

        public Factory setTransferListener(TransferListener r1) {
            this.transferListener = r1;
            return this;
        }

        public Factory setUserAgent(String r1) {
            this.userAgent = r1;
            return this;
        }
    }

    public static class NullFilteringHeadersMap extends ForwardingMap<String, List<String>> {
        private final Map<String, List<String>> headers;

        public static /* synthetic */ boolean $r8$lambda$9ALVjRIbcR8SPcAM7fjeypP5w2g(Map.Entry r0) {
            if (r0.getKey() == null) goto L6;
            return true;
        L6:
            return false;
        }

        /* renamed from: $r8$lambda$ibNQ3SjFWQ-IkYC2K_wdqfqmUMM, reason: not valid java name */
        public static /* synthetic */ boolean m762$r8$lambda$ibNQ3SjFWQIkYC2K_wdqfqmUMM(String r0) {
            if (r0 == null) goto L5;
            return true;
        L5:
            return false;
        }

        public NullFilteringHeadersMap(Map<String, List<String>> r1) {
            this.headers = r1;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public boolean containsKey(Object r1) {
            if (r1 != null) goto L4;
            return false;
        L4:
            if (super.containsKey(r1) == false) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // java.util.Map
        public boolean containsValue(Object r1) {
            return super.standardContainsValue(r1);
        }

        @Override // com.google.common.collect.ForwardingObject
        public /* bridge */ /* synthetic */ Object delegate() {
            return delegate();
        }

        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Map<String, List<String>> delegate() {
            return this.headers;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<Map.Entry<String, List<String>>> entrySet() {
            return Sets.filter(super.entrySet(), new DefaultHttpDataSource$NullFilteringHeadersMap$$ExternalSyntheticLambda0());
        }

        @Override // java.util.Map
        public boolean equals(Object r1) {
            if (r1 != null) goto L4;
            return false;
        L4:
            if (super.standardEquals(r1) == false) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public /* bridge */ /* synthetic */ Object get(Object r1) {
            return get(r1);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public List<String> get(Object r1) {
            if (r1 != null) goto L6;
            return null;
        L6:
            return (List) super.get(r1);
        }

        @Override // java.util.Map
        public int hashCode() {
            return super.standardHashCode();
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public boolean isEmpty() {
            if (super.isEmpty() == false) goto L5;
        L11:
            return true;
        L5:
            if (super.size() == 1) goto L7;
            return false;
        L7:
            if (super.containsKey(null) == true) goto L11;
            return false;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<String> keySet() {
            return Sets.filter(super.keySet(), new DefaultHttpDataSource$NullFilteringHeadersMap$$ExternalSyntheticLambda1());
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public int size() {
            return super.size() - (super.containsKey(null) ? 1 : 0);
        }
    }

    private DefaultHttpDataSource(String r2, int r3, int r4, boolean r5, boolean r6, HttpDataSource$RequestProperties r7, Predicate<String> r8, boolean r9) {
        super(true);
        this.userAgent = r2;
        this.connectTimeoutMillis = r3;
        this.readTimeoutMillis = r4;
        this.allowCrossProtocolRedirects = r5;
        this.crossProtocolRedirectsForceOriginal = r6;
        if (r5 == false) goto L8;
        if (r6 == false) goto L8;
        f$$ExternalSyntheticBUOutline1.m("crossProtocolRedirectsForceOriginal should not be set if allowCrossProtocolRedirects is true");
        throw null;
    L8:
        this.defaultRequestProperties = r7;
        this.contentTypePredicate = r8;
        this.requestProperties = new HttpDataSource$RequestProperties();
        this.keepPostFor302Redirects = r9;
    }

    public /* synthetic */ DefaultHttpDataSource(String r1, int r2, int r3, boolean r4, boolean r5, HttpDataSource$RequestProperties r6, Predicate r7, boolean r8, AnonymousClass1 r9) {
        this(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    private void closeConnectionQuietly() {
        HttpURLConnection r2 = this.connection;
        if (r2 == null) goto L11;
        r2.disconnect();     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
        return;
    }

    private URL handleRedirect(URL r5, String r6, DataSpec r7) {
        if (r6 == null) goto L30;
        URL r2 = new URL(r5, r6);     // Catch: MalformedURLException -> L26
        String r62 = r2.getProtocol();
        if ("https".equals(r62) == true) goto L13;
        if ("http".equals(r62) == true) goto L13;
        throw new HttpDataSource$HttpDataSourceException("Unsupported protocol redirect: " + r62, r7, CastStatusCodes.INVALID_REQUEST, 1);
    L13:
        if (this.allowCrossProtocolRedirects == false) goto L15;
    L25:
        return r2;
    L15:
        if (r62.equals(r5.getProtocol()) == true) goto L25;
        if (this.crossProtocolRedirectsForceOriginal == false) goto L24;
        return new URL(r2.toString().replaceFirst(r62, r5.getProtocol()));
    L20:
        e = move-exception;
        throw new HttpDataSource$HttpDataSourceException(e, r7, CastStatusCodes.INVALID_REQUEST, 1);
    L24:
        throw new HttpDataSource$HttpDataSourceException("Disallowed cross-protocol redirect (" + r5.getProtocol() + " to " + r62 + ")", r7, CastStatusCodes.INVALID_REQUEST, 1);
    L26:
        e = move-exception;
        throw new HttpDataSource$HttpDataSourceException(e, r7, CastStatusCodes.INVALID_REQUEST, 1);
    L30:
        throw new HttpDataSource$HttpDataSourceException("Null location redirect", r7, CastStatusCodes.INVALID_REQUEST, 1);
    }

    private static boolean isCompressed(HttpURLConnection r1) {
        return "gzip".equalsIgnoreCase(r1.getHeaderField("Content-Encoding"));
    }

    private HttpURLConnection makeConnection(DataSpec r17) {
        URL r1 = new URL(r17.uri.toString());
        int r2 = r17.httpMethod;
        byte[] r3 = r17.httpBody;
        long r4 = r17.position;
        long r6 = r17.length;
        int r12 = 1;
        boolean r8 = r17.isFlagSet(1);
        if (this.allowCrossProtocolRedirects == false) goto L5;
    L10:
        int r0 = 0;
    L11:
        int r13 = r0 + 1;
        if (r0 > 20) goto L48;
        HttpURLConnection r9 = makeConnection(r1, r2, r3, r4, r6, r8, false, r17.httpRequestHeaders);
        int r10 = r9.getResponseCode();
        String r14 = r9.getHeaderField("Location");
        if (r2 == r12) goto L18;
        if (r2 == 3) goto L18;
    L30:
        if (r2 != 2) goto L44;
        if (r10 != 300) goto L34;
    L38:
        r9.disconnect();
        if (this.keepPostFor302Redirects == false) goto L42;
        if (r10 != 302) goto L42;
    L43:
        r1 = handleRedirect(r1, r14, r17);
    L46:
        r0 = r13;
        r12 = 1;
    L42:
        r3 = null;
        r2 = 1;
        goto L43
    L34:
        if (r10 == 301) goto L38;
        if (r10 == 302) goto L38;
        if (r10 == 303) goto L38;
    L44:
        return r9;
    L18:
        if (r10 != 300) goto L20;
    L45:
        r9.disconnect();
        r1 = handleRedirect(r1, r14, r17);
        goto L46
    L20:
        if (r10 == 301) goto L45;
        if (r10 == 302) goto L45;
        if (r10 == 303) goto L45;
        if (r10 == 307) goto L45;
        if (r10 != 308) goto L30;
    L48:
        throw new HttpDataSource$HttpDataSourceException(new NoRouteToHostException("Too many redirects: " + r13), r17, CastStatusCodes.INVALID_REQUEST, 1);
    L5:
        if (this.crossProtocolRedirectsForceOriginal == true) goto L10;
        if (this.keepPostFor302Redirects == true) goto L10;
        return makeConnection(r1, r2, r3, r4, r6, r8, true, r17.httpRequestHeaders);
    }

    private HttpURLConnection makeConnection(URL r3, int r4, byte[] r5, long r6, long r8, boolean r10, boolean r11, Map<String, String> r12) {
        HttpURLConnection r32 = openConnection(r3);
        r32.setConnectTimeout(this.connectTimeoutMillis);
        r32.setReadTimeout(this.readTimeoutMillis);
        HashMap r0 = new HashMap();
        HttpDataSource$RequestProperties r1 = this.defaultRequestProperties;
        if (r1 == null) goto L5;
        r0.putAll(r1.getSnapshot());
    L5:
        r0.putAll(this.requestProperties.getSnapshot());
        r0.putAll(r12);
        Iterator r122 = r0.entrySet().iterator();
    L7:
        if (r122.hasNext() == false) goto L9;
        Map.Entry r02 = (Map.Entry) r122.next();
        r32.setRequestProperty((String) r02.getKey(), (String) r02.getValue());
        goto L7
    L9:
        String r62 = HttpUtil.buildRangeRequestHeader(r6, r8);
        if (r62 == null) goto L12;
        r32.setRequestProperty("Range", r62);
    L12:
        String r2 = this.userAgent;
        if (r2 == null) goto L15;
        r32.setRequestProperty("User-Agent", r2);
    L15:
        if (r10 == false) goto L17;
        String r22 = "gzip";
    L18:
        r32.setRequestProperty("Accept-Encoding", r22);
        r32.setInstanceFollowRedirects(r11);
        if (r5 == null) goto L21;
        boolean r23 = true;
    L22:
        r32.setDoOutput(r23);
        r32.setRequestMethod(DataSpec.getStringForHttpMethod(r4));
        if (r5 == null) goto L26;
        r32.setFixedLengthStreamingMode(r5.length);
        r32.connect();
        OutputStream r24 = r32.getOutputStream();
        r24.write(r5);
        r24.close();
        return r32;
    L26:
        r32.connect();
        return r32;
    L21:
        r23 = false;
        goto L22
    L17:
        r22 = "identity";
        goto L18
    }

    private int readInternal(byte[] r7, int r8, int r9) {
        if (r9 != 0) goto L5;
        return 0;
    L5:
        long r0 = this.bytesToRead;
        if (r0 == (-1)) goto L11;
        long r02 = r0 - this.bytesRead;
        if (r02 != 0) goto L10;
        return -1;
    L10:
        r9 = (int) Math.min(r9, r02);
    L11:
        int r72 = ((InputStream) Util.castNonNull(this.inputStream)).read(r7, r8, r9);
        if (r72 != (-1)) goto L14;
        return -1;
    L14:
        this.bytesRead += r72;
        bytesTransferred(r72);
        return r72;
    }

    private void skipFully(long r7, DataSpec r9) {
        if (r7 == 0) goto L17;
        byte[] r2 = new byte[4096];
    L7:
        if (r7 <= 0) goto L21;
        int r3 = ((InputStream) Util.castNonNull(this.inputStream)).read(r2, 0, (int) Math.min(r7, MediaStatus.COMMAND_EDIT_TRACKS));
        if (Thread.currentThread().isInterrupted() == true) goto L16;
        if (r3 == (-1)) goto L14;
        r7 = r7 - r3;
        bytesTransferred(r3);
        goto L7
    L14:
        throw new HttpDataSource$HttpDataSourceException(r9, 2008, 1);
    L16:
        throw new HttpDataSource$HttpDataSourceException(new InterruptedIOException(), r9, CastStatusCodes.AUTHENTICATION_FAILED, 1);
    L21:
        return;
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        InputStream r2 = this.inputStream;     // Catch: Throwable -> L7
        if (r2 != null) goto L23;
    L12:
        this.inputStream = null;
        closeConnectionQuietly();
        if (this.transferStarted == false) goto L15;
        this.transferStarted = false;
        transferEnded();
    L15:
        this.connection = null;
        this.dataSpec = null;
        return;
    L23:
        r2.close();     // Catch: Throwable -> L7 IOException -> L9
    L9:
        e = move-exception;
        throw new HttpDataSource$HttpDataSourceException(e, (DataSpec) Util.castNonNull(this.dataSpec), CastStatusCodes.AUTHENTICATION_FAILED, 3);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        this.inputStream = null;
        closeConnectionQuietly();
        if (this.transferStarted == false) goto L20;
        this.transferStarted = false;
        transferEnded();
    L20:
        this.connection = null;
        this.dataSpec = null;
        throw th;
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        HttpURLConnection r1 = this.connection;
        if (r1 != null) goto L7;
        return ImmutableMap.of();
    L7:
        return new NullFilteringHeadersMap(r1.getHeaderFields());
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        HttpURLConnection r0 = this.connection;
        if (r0 != null) goto L5;
        DataSpec r1 = this.dataSpec;
        if (r1 != null) goto L9;
        return null;
    L9:
        return r1.uri;
    L5:
        return Uri.parse(r0.getURL().toString());
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r15) {
        this.dataSpec = r15;
        long r0 = 0;
        this.bytesRead = 0;
        this.bytesToRead = 0;
        transferInitializing(r15);
        HttpURLConnection r3 = makeConnection(r15);     // Catch: IOException -> L72
        this.connection = r3;     // Catch: IOException -> L72
        this.responseCode = r3.getResponseCode();     // Catch: IOException -> L72
        String r7 = r3.getResponseMessage();     // Catch: IOException -> L72
        int r4 = this.responseCode;
        long r8 = -1;
        if (r4 < 200) goto L51;
        if (r4 > 299) goto L51;
        String r42 = r3.getContentType();
        Predicate<String> r72 = this.contentTypePredicate;
        if (r72 == null) goto L17;
        if (r72.apply(r42) == true) goto L17;
        closeConnectionQuietly();
        throw new HttpDataSource$InvalidContentTypeException(r42, r15);
    L17:
        if (this.responseCode != 200) goto L21;
        long r6 = r15.position;
        if (r6 == 0) goto L21;
        r0 = r6;
    L21:
        boolean r43 = isCompressed(r3);
        if (r43 == true) goto L30;
        long r62 = r15.length;
        if (r62 == (-1)) goto L26;
        this.bytesToRead = r62;
    L80:
        this.inputStream = r3.getInputStream();     // Catch: IOException -> L36
        if (r43 == false) goto L38;
        this.inputStream = new GZIPInputStream(this.inputStream);     // Catch: IOException -> L36
    L38:
        this.transferStarted = true;
        transferStarted(r15);
        skipFully(r0, r15);     // Catch: IOException -> L42
        return this.bytesToRead;
    L42:
        e = move-exception;
        closeConnectionQuietly();
        if ((e instanceof HttpDataSource$HttpDataSourceException) == false) goto L48;
        throw ((HttpDataSource$HttpDataSourceException) e);
    L48:
        throw new HttpDataSource$HttpDataSourceException(e, r15, CastStatusCodes.AUTHENTICATION_FAILED, 1);
    L36:
        e = move-exception;
        closeConnectionQuietly();
        throw new HttpDataSource$HttpDataSourceException(e, r15, CastStatusCodes.AUTHENTICATION_FAILED, 1);
    L26:
        long r5 = HttpUtil.getContentLength(r3.getHeaderField("Content-Length"), r3.getHeaderField("Content-Range"));
        if (r5 == (-1)) goto L29;
        r8 = r5 - r0;
    L29:
        this.bytesToRead = r8;
        goto L80
    L30:
        this.bytesToRead = r15.length;
    L51:
        Map<String, List<String>> r9 = r3.getHeaderFields();
        if (this.responseCode != 416) goto L59;
        long r44 = HttpUtil.getDocumentSize(r3.getHeaderField("Content-Range"));
        if (r15.position != r44) goto L59;
        this.transferStarted = true;
        transferStarted(r15);
        long r14 = r15.length;
        if (r14 == (-1)) goto L58;
        return r14;
    L58:
        return 0;
    L59:
        InputStream r02 = r3.getErrorStream();
        if (r02 == null) goto L62;
        byte[] r03 = ByteStreams.toByteArray(r02);     // Catch: IOException -> L64
    L75:
    L63:
        byte[] r11 = r03;
        closeConnectionQuietly();
        if (this.responseCode != 416) goto L69;
        DataSourceException r04 = new DataSourceException(2008);
    L68:
        DataSourceException r82 = r04;
        throw new HttpDataSource$InvalidResponseCodeException(this.responseCode, r7, r82, r9, r15, r11);
    L69:
        r04 = null;
        goto L68
    L62:
        r03 = Util.EMPTY_BYTE_ARRAY;     // Catch: IOException -> L64
    L64:
        r03 = Util.EMPTY_BYTE_ARRAY;
    L72:
        e = move-exception;
        closeConnectionQuietly();
        throw HttpDataSource$HttpDataSourceException.createForIOException(e, r15, 1);
    }

    public HttpURLConnection openConnection(URL r1) {
        return (HttpURLConnection) r1.openConnection();
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r1, int r2, int r3) {
        return readInternal(r1, r2, r3);
    L4:
        e = move-exception;
        throw HttpDataSource$HttpDataSourceException.createForIOException(e, (DataSpec) Util.castNonNull(this.dataSpec), 2);
    }
}
