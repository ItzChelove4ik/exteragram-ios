package fi.iki.elonen;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;
import j$.net.URLDecoder;
import j$.util.DesugarTimeZone;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.SSLException;
import okhttp3.internal.http.HttpStatusCodesKt;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class NanoHTTPD {
    private static final Pattern CONTENT_DISPOSITION_ATTRIBUTE_PATTERN = null;
    private static final Pattern CONTENT_DISPOSITION_PATTERN = null;
    private static final Pattern CONTENT_TYPE_PATTERN = null;
    private static final Logger LOG = null;
    protected AsyncRunner asyncRunner;
    private final String hostname;
    private final int myPort;
    private volatile ServerSocket myServerSocket;
    private Thread myThread;
    private ServerSocketFactory serverSocketFactory;
    private TempFileManagerFactory tempFileManagerFactory;

    /* renamed from: fi.iki.elonen.NanoHTTPD$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public interface AsyncRunner {
        void closeAll();

        void closed(ClientHandler r1);

        void exec(ClientHandler r1);
    }

    public class ClientHandler implements Runnable {
        private final Socket acceptSocket;
        private final InputStream inputStream;
        final /* synthetic */ NanoHTTPD this$0;

        public ClientHandler(NanoHTTPD r1, InputStream r2, Socket r3) {
            this.this$0 = r1;
            this.inputStream = r2;
            this.acceptSocket = r3;
        }

        public void close() {
            NanoHTTPD.access$000(this.inputStream);
            NanoHTTPD.access$000(this.acceptSocket);
        }

        @Override // java.lang.Runnable
        public void run() {
            OutputStream r1 = null;
            OutputStream r6 = this.acceptSocket.getOutputStream();     // Catch: Throwable -> L16 Exception -> L18
            TempFileManager r4 = NanoHTTPD.access$100(this.this$0).create();     // Catch: Throwable -> L9 Exception -> L11
            HTTPSession r2 = new HTTPSession(this.this$0, r4, this.inputStream, r6, this.acceptSocket.getInetAddress());     // Catch: Throwable -> L9 Exception -> L11
        L5:
            if (this.acceptSocket.isClosed() == true) goto L13;
            r2.execute();     // Catch: Throwable -> L9 Exception -> L11
            goto L5
        L13:
            NanoHTTPD.access$000(r6);
        L14:
            NanoHTTPD.access$000(this.inputStream);
            NanoHTTPD.access$000(this.acceptSocket);
            this.this$0.asyncRunner.closed(this);
            return;
        L11:
            e = e;
            r1 = r6;
        L20:
            if ((e instanceof SocketException) == false) goto L24;
            if ("NanoHttpd Shutdown".equals(e.getMessage()) == false) goto L24;
        L26:
            NanoHTTPD.access$000(r1);
        L24:
            if ((e instanceof SocketTimeoutException) == true) goto L26;
            NanoHTTPD.access$200().log(Level.SEVERE, "Communication with the client broken, or an bug in the handler code", e);     // Catch: Throwable -> L16
        L9:
            th = th;
            r1 = r6;
        L27:
            NanoHTTPD.access$000(r1);
            NanoHTTPD.access$000(this.inputStream);
            NanoHTTPD.access$000(this.acceptSocket);
            this.this$0.asyncRunner.closed(this);
            throw th;
        L18:
            e = e;
        L16:
            th = th;
            goto L27
        }
    }

    public static class ContentType {
        private static final Pattern BOUNDARY_PATTERN = null;
        private static final Pattern CHARSET_PATTERN = null;
        private static final Pattern MIME_PATTERN = null;
        private final String boundary;
        private final String contentType;
        private final String contentTypeHeader;
        private final String encoding;

        static {
            MIME_PATTERN = Pattern.compile("[ |\t]*([^/^ ^;^,]+/[^ ^;^,]+)", 2);
            CHARSET_PATTERN = Pattern.compile("[ |\t]*(charset)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?", 2);
            BOUNDARY_PATTERN = Pattern.compile("[ |\t]*(boundary)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?", 2);
        }

        public ContentType(String r6) {
            this.contentTypeHeader = r6;
            if (r6 == null) goto L5;
            this.contentType = getDetailFromContentHeader(r6, MIME_PATTERN, _UrlKt.FRAGMENT_ENCODE_SET, 1);
            this.encoding = getDetailFromContentHeader(r6, CHARSET_PATTERN, null, 2);
        L7:
            if ("multipart/form-data".equalsIgnoreCase(this.contentType) == false) goto L10;
            this.boundary = getDetailFromContentHeader(r6, BOUNDARY_PATTERN, null, 2);
            return;
        L10:
            this.boundary = null;
            return;
        L5:
            this.contentType = _UrlKt.FRAGMENT_ENCODE_SET;
            this.encoding = "UTF-8";
            goto L7
        }

        private String getDetailFromContentHeader(String r1, Pattern r2, String r3, int r4) {
            Matcher r0 = r2.matcher(r1);
            if (r0.find() == true) goto L5;
            return r3;
        L5:
            return r0.group(r4);
        }

        public String getContentTypeHeader() {
            return this.contentTypeHeader;
        }

        public String getEncoding() {
            String r0 = this.encoding;
            if (r0 != null) goto L6;
            return "US-ASCII";
        L6:
            return r0;
        }

        public ContentType tryUTF8() {
            if (this.encoding == null) goto L5;
            return this;
        L5:
            return new ContentType(this.contentTypeHeader + "; charset=UTF-8");
        }
    }

    public class CookieHandler implements Iterable<String> {
        private final HashMap<String, String> cookies;
        private final ArrayList<Object> queue;
        final /* synthetic */ NanoHTTPD this$0;

        public CookieHandler(NanoHTTPD r7, Map<String, String> r8) {
            this.this$0 = r7;
            this.cookies = new HashMap();
            this.queue = new ArrayList();
            String r72 = r8.get("cookie");
            if (r72 == null) goto L10;
            String[] r73 = r72.split(";");
            int r82 = r73.length;
            int r1 = 0;
        L5:
            if (r1 >= r82) goto L14;
            String[] r2 = r73[r1].trim().split("=");
            if (r2.length != 2) goto L9;
            this.cookies.put(r2[0], r2[1]);
        L9:
            r1 = r1 + 1;
            goto L5
        L14:
            return;
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            return this.cookies.keySet().iterator();
        }

        public void unloadQueue(Response r1) {
            Iterator<Object> r0 = this.queue.iterator();
            if (r0.hasNext() == true) goto L5;
            return;
        L5:
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.next());
            throw null;
        }
    }

    public static class DefaultAsyncRunner implements AsyncRunner {
        private long requestCount;
        private final List<ClientHandler> running;

        public DefaultAsyncRunner() {
            this.running = Collections.synchronizedList(new ArrayList());
        }

        @Override // fi.iki.elonen.NanoHTTPD.AsyncRunner
        public void closeAll() {
            ArrayList r0 = new ArrayList(this.running);
            int r3 = r0.size();
            int r1 = 0;
        L3:
            if (r1 >= r3) goto L5;
            Object r2 = r0.get(r1);
            r1 = r1 + 1;
            ((ClientHandler) r2).close();
            goto L3
        }

        @Override // fi.iki.elonen.NanoHTTPD.AsyncRunner
        public void closed(ClientHandler r1) {
            this.running.remove(r1);
        }

        @Override // fi.iki.elonen.NanoHTTPD.AsyncRunner
        public void exec(ClientHandler r5) {
            this.requestCount++;
            Thread r0 = new Thread(r5);
            r0.setDaemon(true);
            r0.setName("NanoHttpd Request Processor (#" + this.requestCount + ")");
            this.running.add(r5);
            r0.start();
        }
    }

    public static class DefaultServerSocketFactory implements ServerSocketFactory {
        public DefaultServerSocketFactory() {
        }

        @Override // fi.iki.elonen.NanoHTTPD.ServerSocketFactory
        public ServerSocket create() {
            return new ServerSocket();
        }
    }

    public static class DefaultTempFileManager implements TempFileManager {
        private final List<Object> tempFiles;
        private final File tmpdir;

        public DefaultTempFileManager() {
            File r0 = new File(System.getProperty("java.io.tmpdir"));
            this.tmpdir = r0;
            if (r0.exists() == true) goto L5;
            r0.mkdirs();
        L5:
            this.tempFiles = new ArrayList();
        }

        @Override // fi.iki.elonen.NanoHTTPD.TempFileManager
        public void clear() {
            Iterator<Object> r0 = this.tempFiles.iterator();
        L4:
            if (r0.hasNext() == false) goto L5;
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.next());
            throw null;     // Catch: Exception -> L9
        L9:
            e = move-exception;
            NanoHTTPD.access$200().log(Level.WARNING, "could not delete file ", e);
            goto L4
        L5:
            this.tempFiles.clear();
        }
    }

    public class DefaultTempFileManagerFactory implements TempFileManagerFactory {
        final /* synthetic */ NanoHTTPD this$0;

        private DefaultTempFileManagerFactory(NanoHTTPD r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ DefaultTempFileManagerFactory(NanoHTTPD r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // fi.iki.elonen.NanoHTTPD.TempFileManagerFactory
        public TempFileManager create() {
            return new DefaultTempFileManager();
        }
    }

    public class HTTPSession implements IHTTPSession {
        private CookieHandler cookies;
        private Map<String, String> headers;
        private final BufferedInputStream inputStream;
        private Method method;
        private final OutputStream outputStream;
        private Map<String, List<String>> parms;
        private String protocolVersion;
        private String queryParameterString;
        private String remoteHostname;
        private String remoteIp;
        private int rlen;
        private int splitbyte;
        private final TempFileManager tempFileManager;
        final /* synthetic */ NanoHTTPD this$0;
        private String uri;

        public HTTPSession(NanoHTTPD r1, TempFileManager r2, InputStream r3, OutputStream r4, InetAddress r5) {
            this.this$0 = r1;
            this.tempFileManager = r2;
            this.inputStream = new BufferedInputStream(r3, 8192);
            this.outputStream = r4;
            if (r5.isLoopbackAddress() == false) goto L5;
        L8:
            String r12 = "127.0.0.1";
        L9:
            this.remoteIp = r12;
            if (r5.isLoopbackAddress() == false) goto L12;
        L15:
            String r13 = "localhost";
        L16:
            this.remoteHostname = r13;
            this.headers = new HashMap();
            return;
        L12:
            if (r5.isAnyLocalAddress() == true) goto L15;
            r13 = r5.getHostName().toString();
            goto L16
        L5:
            if (r5.isAnyLocalAddress() == true) goto L8;
            r12 = r5.getHostAddress().toString();
            goto L9
        }

        private void decodeHeader(BufferedReader r6, Map<String, String> r7, Map<String, List<String>> r8, Map<String, String> r9) {
            String r0 = r6.readLine();     // Catch: IOException -> L31
            if (r0 != null) goto L5;
            return;
        L5:
            StringTokenizer r1 = new StringTokenizer(r0);     // Catch: IOException -> L31
            if (r1.hasMoreTokens() == false) goto L30;
            r7.put("method", r1.nextToken());     // Catch: IOException -> L31
            if (r1.hasMoreTokens() == false) goto L28;
            String r02 = r1.nextToken();     // Catch: IOException -> L31
            int r2 = r02.indexOf(63);     // Catch: IOException -> L31
            if (r2 < 0) goto L12;
            decodeParms(r02.substring(r2 + 1), r8);     // Catch: IOException -> L31
            String r82 = NanoHTTPD.decodePercent(r02.substring(0, r2));     // Catch: IOException -> L31
        L14:
            if (r1.hasMoreTokens() == false) goto L16;
            this.protocolVersion = r1.nextToken();     // Catch: IOException -> L31
        L17:
            String r5 = r6.readLine();     // Catch: IOException -> L31
        L18:
            if (r5 == null) goto L25;
            if (r5.trim().isEmpty() == true) goto L25;
            int r03 = r5.indexOf(58);     // Catch: IOException -> L31
            if (r03 < 0) goto L24;
            r9.put(r5.substring(0, r03).trim().toLowerCase(Locale.US), r5.substring(r03 + 1).trim());     // Catch: IOException -> L31
        L24:
            r5 = r6.readLine();     // Catch: IOException -> L31
        L25:
            r7.put("uri", r82);     // Catch: IOException -> L31
            return;
        L16:
            this.protocolVersion = "HTTP/1.1";     // Catch: IOException -> L31
            NanoHTTPD.access$200().log(Level.FINE, "no protocol version specified, strange. Assuming HTTP/1.1.");     // Catch: IOException -> L31
            goto L17
        L12:
            r82 = NanoHTTPD.decodePercent(r02);     // Catch: IOException -> L31
            goto L14
        L28:
            throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");     // Catch: IOException -> L31
        L30:
            throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");     // Catch: IOException -> L31
        L31:
            e = move-exception;
            throw new ResponseException(Response.Status.INTERNAL_ERROR, "SERVER INTERNAL ERROR: IOException: " + e.getMessage(), e);
        }

        private void decodeParms(String r4, Map<String, List<String>> r5) {
            if (r4 != null) goto L6;
            this.queryParameterString = _UrlKt.FRAGMENT_ENCODE_SET;
            return;
        L6:
            this.queryParameterString = r4;
            StringTokenizer r3 = new StringTokenizer(r4, "&");
        L8:
            if (r3.hasMoreTokens() == false) goto L17;
            String r42 = r3.nextToken();
            int r1 = r42.indexOf(61);
            if (r1 < 0) goto L12;
            String r2 = NanoHTTPD.decodePercent(r42.substring(0, r1)).trim();
            String r43 = NanoHTTPD.decodePercent(r42.substring(r1 + 1));
        L13:
            List<String> r12 = r5.get(r2);
            if (r12 != null) goto L16;
            r12 = new ArrayList();
            r5.put(r2, r12);
        L16:
            r12.add(r43);
            goto L8
        L12:
            r2 = NanoHTTPD.decodePercent(r42).trim();
            r43 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L13
        }

        private int findHeaderEnd(byte[] r8, int r9) {
            int r0 = 0;
        L3:
            int r1 = r0 + 1;
            if (r1 >= r9) goto L23;
            byte r2 = r8[r0];
            if (r2 == 13) goto L8;
        L17:
            if (r2 != 10) goto L22;
            if (r8[r1] != 10) goto L22;
            return r0 + 2;
        L22:
            r0 = r1;
            goto L3
        L8:
            if (r8[r1] != 10) goto L17;
            int r5 = r0 + 3;
            if (r5 >= r9) goto L17;
            if (r8[r0 + 2] != 13) goto L17;
            if (r8[r5] != 10) goto L17;
            return r0 + 4;
        L23:
            return 0;
        }

        public void execute() {
            byte[] r5 = new byte[8192];     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            boolean r6 = false;
            this.splitbyte = 0;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.rlen = 0;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.inputStream.mark(8192);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            int r3 = this.inputStream.read(r5, 0, 8192);     // Catch: Throwable -> L12 ResponseException -> L14 SSLException -> L65 IOException -> L67
        L6:
            if (r3 == (-1)) goto L63;
        L7:
            if (r3 <= 0) goto L25;
            int r7 = this.rlen + r3;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.rlen = r7;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            int r32 = findHeaderEnd(r5, r7);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.splitbyte = r32;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            if (r32 > 0) goto L25;
            BufferedInputStream r33 = this.inputStream;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            int r72 = this.rlen;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            r3 = r33.read(r5, r72, 8192 - r72);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L25:
            if (this.splitbyte >= this.rlen) goto L27;
            this.inputStream.reset();     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.inputStream.skip(this.splitbyte);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L27:
            this.parms = new HashMap();     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            Map<String, String> r34 = this.headers;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            if (r34 != null) goto L30;
            this.headers = new HashMap();     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L31:
            BufferedReader r35 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(r5, 0, this.rlen)));     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            HashMap r52 = new HashMap();     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            decodeHeader(r35, r52, this.parms, this.headers);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            String r36 = this.remoteIp;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            if (r36 == null) goto L34;
            this.headers.put("remote-addr", r36);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.headers.put("http-client-ip", this.remoteIp);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L34:
            Method r37 = Method.lookup(r52.get("method"));     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.method = r37;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            if (r37 == null) goto L62;
            this.uri = r52.get("uri");     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.cookies = new CookieHandler(this.this$0, this.headers);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            String r0 = this.headers.get("connection");     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            if ("HTTP/1.1".equals(this.protocolVersion) == false) goto L42;
            if (r0 != null) goto L40;
        L41:
            boolean r02 = true;
        L43:
            Response r4 = this.this$0.serve(this);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            if (r4 == null) goto L60;
            String r38 = this.headers.get("accept-encoding");     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            this.cookies.unloadQueue(r4);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            r4.setRequestMethod(this.method);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            if (this.this$0.useGzipWhenAccepted(r4) == false) goto L51;
            if (r38 == null) goto L51;
            if (r38.contains("gzip") == false) goto L51;
            r6 = true;
        L51:
            r4.setGzipEncoding(r6);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            r4.setKeepAlive(r02);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            r4.send(this.outputStream);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            if (r02 == false) goto L58;
            if (r4.isCloseConnection() == true) goto L58;
            NanoHTTPD.access$000(r4);
            this.tempFileManager.clear();
            return;
        L58:
            throw new SocketException("NanoHttpd Shutdown");     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L60:
            throw new ResponseException(Response.Status.INTERNAL_ERROR, "SERVER INTERNAL ERROR: Serve() returned a null response.");     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L40:
            if (r0.matches("(?i).*close.*") == false) goto L41;
        L42:
            r02 = false;
            goto L43
        L62:
            throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Syntax error. HTTP verb " + r52.get("method") + " unhandled.");     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L30:
            r34.clear();     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            goto L31
        L63:
            NanoHTTPD.access$000(this.inputStream);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            NanoHTTPD.access$000(this.outputStream);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            throw new SocketException("NanoHttpd Shutdown");     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L65:
            e = move-exception;
            throw e;     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L67:
            NanoHTTPD.access$000(this.inputStream);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            NanoHTTPD.access$000(this.outputStream);     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
            throw new SocketException("NanoHttpd Shutdown");     // Catch: Throwable -> L12 ResponseException -> L14 IOException -> L16 SSLException -> L18 SocketTimeoutException -> L20 SocketException -> L22
        L14:
            e = move-exception;
            NanoHTTPD.newFixedLengthResponse(e.getStatus(), "text/plain", e.getMessage()).send(this.outputStream);     // Catch: Throwable -> L12
            NanoHTTPD.access$000(this.outputStream);     // Catch: Throwable -> L12
        L71:
            NanoHTTPD.access$000(null);
            this.tempFileManager.clear();
            return;
        L22:
            e = move-exception;
            throw e;     // Catch: Throwable -> L12
        L20:
            e = move-exception;
            throw e;     // Catch: Throwable -> L12
        L18:
            e = move-exception;
            NanoHTTPD.newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", "SSL PROTOCOL FAILURE: " + e.getMessage()).send(this.outputStream);     // Catch: Throwable -> L12
            NanoHTTPD.access$000(this.outputStream);     // Catch: Throwable -> L12
        L16:
            e = move-exception;
            NanoHTTPD.newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", "SERVER INTERNAL ERROR: IOException: " + e.getMessage()).send(this.outputStream);     // Catch: Throwable -> L12
            NanoHTTPD.access$000(this.outputStream);     // Catch: Throwable -> L12
        L12:
            th = move-exception;
            NanoHTTPD.access$000(null);
            this.tempFileManager.clear();
            throw th;
        }

        @Override // fi.iki.elonen.NanoHTTPD.IHTTPSession
        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        @Override // fi.iki.elonen.NanoHTTPD.IHTTPSession
        public final Method getMethod() {
            return this.method;
        }

        @Override // fi.iki.elonen.NanoHTTPD.IHTTPSession
        public final String getUri() {
            return this.uri;
        }
    }

    public interface IHTTPSession {
        Map<String, String> getHeaders();

        Method getMethod();

        String getUri();
    }

    public enum Method extends Enum<Method> {
        private static final /* synthetic */ Method[] $VALUES = null;
        public static final Method CONNECT = null;
        public static final Method COPY = null;
        public static final Method DELETE = null;
        public static final Method GET = null;
        public static final Method HEAD = null;
        public static final Method LOCK = null;
        public static final Method MKCOL = null;
        public static final Method MOVE = null;
        public static final Method OPTIONS = null;
        public static final Method PATCH = null;
        public static final Method POST = null;
        public static final Method PROPFIND = null;
        public static final Method PROPPATCH = null;
        public static final Method PUT = null;
        public static final Method TRACE = null;
        public static final Method UNLOCK = null;

        static {
            Method r1 = new Method("GET", 0);
            GET = r1;
            Method r2 = new Method("PUT", 1);
            PUT = r2;
            Method r3 = new Method("POST", 2);
            POST = r3;
            Method r4 = new Method("DELETE", 3);
            DELETE = r4;
            Method r5 = new Method("HEAD", 4);
            HEAD = r5;
            Method r6 = new Method("OPTIONS", 5);
            OPTIONS = r6;
            Method r7 = new Method("TRACE", 6);
            TRACE = r7;
            Method r8 = new Method("CONNECT", 7);
            CONNECT = r8;
            Method r9 = new Method("PATCH", 8);
            PATCH = r9;
            Method r10 = new Method("PROPFIND", 9);
            PROPFIND = r10;
            Method r11 = new Method("PROPPATCH", 10);
            PROPPATCH = r11;
            Method r12 = new Method("MKCOL", 11);
            MKCOL = r12;
            Method r13 = new Method("MOVE", 12);
            MOVE = r13;
            Method r14 = new Method("COPY", 13);
            COPY = r14;
            Method r15 = new Method("LOCK", 14);
            LOCK = r15;
            Method r0 = new Method("UNLOCK", 15);
            UNLOCK = r0;
            $VALUES = new Method[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0};
        }

        Method(String r1, int r2) {
        }

        public static Method lookup(String r1) {
            if (r1 != null) goto L8;
            return null;
        L8:
            return valueOf(r1);
        L7:
            return null;
        }

        public static Method valueOf(String r1) {
            return (Method) Enum.valueOf(Method.class, r1);
        }

        public static Method[] values() {
            return (Method[]) $VALUES.clone();
        }
    }

    public static class Response implements Closeable {
        private boolean chunkedTransfer;
        private long contentLength;
        private InputStream data;
        private boolean encodeAsGzip;
        private final Map<String, String> header;
        private boolean keepAlive;
        private final Map<String, String> lowerCaseHeader;
        private String mimeType;
        private Method requestMethod;
        private IStatus status;

        /* renamed from: fi.iki.elonen.NanoHTTPD$Response$1, reason: invalid class name */
        public class AnonymousClass1 extends HashMap<String, String> {
            final /* synthetic */ Response this$0;

            public AnonymousClass1(Response r1) {
                this.this$0 = r1;
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public /* bridge */ /* synthetic */ Object put(Object r1, Object r2) {
                return put((String) r1, (String) r2);
            }

            public String put(String r3, String r4) {
                Map r0 = Response.access$600(this.this$0);
                if (r3 != null) goto L5;
                String r1 = r3;
            L6:
                r0.put(r1, r4);
                return (String) super.put(r3, r4);
            L5:
                r1 = r3.toLowerCase();
                goto L6
            }
        }

        public static class ChunkedOutputStream extends FilterOutputStream {
            public ChunkedOutputStream(OutputStream r1) {
                super(r1);
            }

            public void finish() {
                ((FilterOutputStream) this).out.write("0\r\n\r\n".getBytes());
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int r4) {
                write(new byte[]{(byte) r4}, 0, 1);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] r3) {
                write(r3, 0, r3.length);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] r4, int r5, int r6) {
                if (r6 != 0) goto L4;
                return;
            L4:
                ((FilterOutputStream) this).out.write(String.format("%x\r\n", new Object[]{Integer.valueOf(r6)}).getBytes());
                ((FilterOutputStream) this).out.write(r4, r5, r6);
                ((FilterOutputStream) this).out.write("\r\n".getBytes());
            }
        }

        public interface IStatus {
            String getDescription();
        }

        public enum Status extends Enum<Status> implements IStatus {
            private static final /* synthetic */ Status[] $VALUES = null;
            public static final Status ACCEPTED = null;
            public static final Status BAD_REQUEST = null;
            public static final Status CONFLICT = null;
            public static final Status CREATED = null;
            public static final Status EXPECTATION_FAILED = null;
            public static final Status FORBIDDEN = null;

            @Deprecated
            public static final Status FOUND = null;
            public static final Status GONE = null;
            public static final Status INTERNAL_ERROR = null;
            public static final Status LENGTH_REQUIRED = null;
            public static final Status METHOD_NOT_ALLOWED = null;
            public static final Status MULTI_STATUS = null;
            public static final Status NOT_ACCEPTABLE = null;
            public static final Status NOT_FOUND = null;
            public static final Status NOT_IMPLEMENTED = null;
            public static final Status NOT_MODIFIED = null;
            public static final Status NO_CONTENT = null;
            public static final Status OK = null;
            public static final Status PARTIAL_CONTENT = null;
            public static final Status PAYLOAD_TOO_LARGE = null;
            public static final Status PRECONDITION_FAILED = null;
            public static final Status RANGE_NOT_SATISFIABLE = null;
            public static final Status REDIRECT = null;
            public static final Status REDIRECT_SEE_OTHER = null;
            public static final Status REQUEST_TIMEOUT = null;
            public static final Status SERVICE_UNAVAILABLE = null;
            public static final Status SWITCH_PROTOCOL = null;
            public static final Status TEMPORARY_REDIRECT = null;
            public static final Status TOO_MANY_REQUESTS = null;
            public static final Status UNAUTHORIZED = null;
            public static final Status UNSUPPORTED_HTTP_VERSION = null;
            public static final Status UNSUPPORTED_MEDIA_TYPE = null;
            private final String description;
            private final int requestStatus;

            static {
                Status r1 = new Status("SWITCH_PROTOCOL", 0, 101, "Switching Protocols");
                SWITCH_PROTOCOL = r1;
                Status r2 = new Status("OK", 1, 200, "OK");
                OK = r2;
                Status r3 = new Status("CREATED", 2, 201, "Created");
                CREATED = r3;
                Status r4 = new Status("ACCEPTED", 3, 202, "Accepted");
                ACCEPTED = r4;
                Status r5 = new Status("NO_CONTENT", 4, Opcodes.SUB_DOUBLE_2ADDR, "No Content");
                NO_CONTENT = r5;
                Status r6 = new Status("PARTIAL_CONTENT", 5, Opcodes.DIV_DOUBLE_2ADDR, "Partial Content");
                PARTIAL_CONTENT = r6;
                Status r7 = new Status("MULTI_STATUS", 6, Opcodes.REM_DOUBLE_2ADDR, "Multi-Status");
                MULTI_STATUS = r7;
                Status r8 = new Status("REDIRECT", 7, MediaError.DetailedErrorCode.SEGMENT_NETWORK, "Moved Permanently");
                REDIRECT = r8;
                Status r9 = new Status("FOUND", 8, 302, "Found");
                FOUND = r9;
                Status r10 = new Status("REDIRECT_SEE_OTHER", 9, 303, "See Other");
                REDIRECT_SEE_OTHER = r10;
                Status r11 = new Status("NOT_MODIFIED", 10, 304, "Not Modified");
                NOT_MODIFIED = r11;
                Status r12 = new Status("TEMPORARY_REDIRECT", 11, HttpStatusCodesKt.HTTP_TEMP_REDIRECT, "Temporary Redirect");
                TEMPORARY_REDIRECT = r12;
                Status r13 = new Status("BAD_REQUEST", 12, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, "Bad Request");
                BAD_REQUEST = r13;
                Status r14 = new Status("UNAUTHORIZED", 13, 401, "Unauthorized");
                UNAUTHORIZED = r14;
                Status r15 = new Status("FORBIDDEN", 14, 403, "Forbidden");
                FORBIDDEN = r15;
                Status r0 = new Status("NOT_FOUND", 15, 404, "Not Found");
                NOT_FOUND = r0;
                Status r16 = new Status("METHOD_NOT_ALLOWED", 16, 405, "Method Not Allowed");
                METHOD_NOT_ALLOWED = r16;
                Status r02 = new Status("NOT_ACCEPTABLE", 17, 406, "Not Acceptable");
                NOT_ACCEPTABLE = r02;
                Status r17 = new Status("REQUEST_TIMEOUT", 18, 408, "Request Timeout");
                REQUEST_TIMEOUT = r17;
                Status r03 = new Status("CONFLICT", 19, 409, "Conflict");
                CONFLICT = r03;
                Status r18 = new Status("GONE", 20, 410, "Gone");
                GONE = r18;
                Status r04 = new Status("LENGTH_REQUIRED", 21, MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER, "Length Required");
                LENGTH_REQUIRED = r04;
                Status r19 = new Status("PRECONDITION_FAILED", 22, MediaError.DetailedErrorCode.HLS_MANIFEST_PLAYLIST, "Precondition Failed");
                PRECONDITION_FAILED = r19;
                Status r05 = new Status("PAYLOAD_TOO_LARGE", 23, 413, "Payload Too Large");
                PAYLOAD_TOO_LARGE = r05;
                Status r110 = new Status("UNSUPPORTED_MEDIA_TYPE", 24, 415, "Unsupported Media Type");
                UNSUPPORTED_MEDIA_TYPE = r110;
                Status r06 = new Status("RANGE_NOT_SATISFIABLE", 25, 416, "Requested Range Not Satisfiable");
                RANGE_NOT_SATISFIABLE = r06;
                Status r111 = new Status("EXPECTATION_FAILED", 26, 417, "Expectation Failed");
                EXPECTATION_FAILED = r111;
                Status r07 = new Status("TOO_MANY_REQUESTS", 27, 429, "Too Many Requests");
                TOO_MANY_REQUESTS = r07;
                Status r112 = new Status("INTERNAL_ERROR", 28, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, "Internal Server Error");
                INTERNAL_ERROR = r112;
                Status r08 = new Status("NOT_IMPLEMENTED", 29, 501, "Not Implemented");
                NOT_IMPLEMENTED = r08;
                Status r113 = new Status("SERVICE_UNAVAILABLE", 30, 503, "Service Unavailable");
                SERVICE_UNAVAILABLE = r113;
                Status r09 = new Status("UNSUPPORTED_HTTP_VERSION", 31, 505, "HTTP Version Not Supported");
                UNSUPPORTED_HTTP_VERSION = r09;
                $VALUES = new Status[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r02, r17, r03, r18, r04, r19, r05, r110, r06, r111, r07, r112, r08, r113, r09};
            }

            Status(String r1, int r2, int r3, String r4) {
                this.requestStatus = r3;
                this.description = r4;
            }

            public static Status valueOf(String r1) {
                return (Status) Enum.valueOf(Status.class, r1);
            }

            public static Status[] values() {
                return (Status[]) $VALUES.clone();
            }

            @Override // fi.iki.elonen.NanoHTTPD.Response.IStatus
            public String getDescription() {
                return _UrlKt.FRAGMENT_ENCODE_SET + this.requestStatus + " " + this.description;
            }
        }

        public Response(IStatus r2, String r3, InputStream r4, long r5) {
            this.header = new AnonymousClass1(this);
            this.lowerCaseHeader = new HashMap();
            this.status = r2;
            this.mimeType = r3;
            boolean r0 = false;
            if (r4 != null) goto L5;
            this.data = new ByteArrayInputStream(new byte[0]);
            this.contentLength = 0;
        L7:
            if (this.contentLength >= 0) goto L9;
            r0 = true;
        L9:
            this.chunkedTransfer = r0;
            this.keepAlive = true;
            return;
        L5:
            this.data = r4;
            this.contentLength = r5;
            goto L7
        }

        public static /* synthetic */ Map access$600(Response r0) {
            return r0.lowerCaseHeader;
        }

        private void sendBody(OutputStream r7, long r8) {
            byte[] r0 = new byte[16384];
            if (r8 != (-1)) goto L5;
            boolean r1 = true;
        L7:
            if (r8 > 0) goto L9;
            if (r1 == true) goto L9;
            return;
        L9:
            long r3 = 16384;
            if (r1 == true) goto L13;
            r3 = Math.min(r8, 16384);
        L13:
            int r32 = this.data.read(r0, 0, (int) r3);
            if (r32 <= 0) goto L26;
            r7.write(r0, 0, r32);
            if (r1 == true) goto L7;
            r8 = r8 - r32;
            goto L7
        L26:
            return;
        L5:
            r1 = false;
            goto L7
        }

        private void sendBodyWithCorrectEncoding(OutputStream r3, long r4) {
            if (this.encodeAsGzip == false) goto L6;
            GZIPOutputStream r42 = new GZIPOutputStream(r3);
            sendBody(r42, -1);
            r42.finish();
            return;
        L6:
            sendBody(r3, r4);
        }

        private void sendBodyWithCorrectTransferAndEncoding(OutputStream r3, long r4) {
            if (this.requestMethod != Method.HEAD) goto L5;
        L8:
            sendBodyWithCorrectEncoding(r3, r4);
            return;
        L5:
            if (this.chunkedTransfer == false) goto L8;
            ChunkedOutputStream r42 = new ChunkedOutputStream(r3);
            sendBodyWithCorrectEncoding(r42, -1);
            r42.finish();
        }

        public void addHeader(String r1, String r2) {
            this.header.put(r1, r2);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            InputStream r0 = this.data;
            if (r0 == null) goto L6;
            r0.close();
            return;
        }

        public String getHeader(String r1) {
            return this.lowerCaseHeader.get(r1.toLowerCase());
        }

        public String getMimeType() {
            return this.mimeType;
        }

        public boolean isCloseConnection() {
            return "close".equals(getHeader("connection"));
        }

        public void printHeader(PrintWriter r1, String r2, String r3) {
            r1.append(r2).append(": ").append(r3).append("\r\n");
        }

        public void send(OutputStream r7) {
            SimpleDateFormat r0 = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            r0.setTimeZone(DesugarTimeZone.getTimeZone("GMT"));
        L44:
            e = move-exception;
            NanoHTTPD.access$200().log(Level.SEVERE, "Could not send response to the client", e);
            return;
        L4:
            if (this.status == null) goto L43;
            PrintWriter r1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(r7, new ContentType(this.mimeType).getEncoding())), false);     // Catch: IOException -> L44
            r1.append("HTTP/1.1 ").append(this.status.getDescription()).append(" \r\n");     // Catch: IOException -> L44
            String r2 = this.mimeType;     // Catch: IOException -> L44
            if (r2 == null) goto L9;
            printHeader(r1, "Content-Type", r2);     // Catch: IOException -> L44
        L9:
            if (getHeader("date") != null) goto L11;
            printHeader(r1, "Date", r0.format(new Date()));     // Catch: IOException -> L44
        L11:
            Iterator<Map.Entry<String, String>> r02 = this.header.entrySet().iterator();     // Catch: IOException -> L44
        L13:
            if (r02.hasNext() == false) goto L16;
            Map.Entry<String, String> r22 = r02.next();     // Catch: IOException -> L44
            printHeader(r1, r22.getKey(), r22.getValue());     // Catch: IOException -> L44
            goto L13
        L16:
            if (getHeader("connection") != null) goto L23;
            if (this.keepAlive == false) goto L20;
            String r23 = "keep-alive";
        L21:
            printHeader(r1, "Connection", r23);     // Catch: IOException -> L44
            goto L23
        L20:
            r23 = "close";
        L23:
            if (getHeader("content-length") == null) goto L26;
            this.encodeAsGzip = false;     // Catch: IOException -> L44
        L26:
            if (this.encodeAsGzip == false) goto L29;
            printHeader(r1, "Content-Encoding", "gzip");     // Catch: IOException -> L44
            setChunkedTransfer(true);     // Catch: IOException -> L44
        L29:
            if (this.data == null) goto L31;
            long r24 = this.contentLength;     // Catch: IOException -> L44
        L33:
            if (this.requestMethod == Method.HEAD) goto L38;
            if (this.chunkedTransfer == false) goto L38;
            printHeader(r1, "Transfer-Encoding", "chunked");     // Catch: IOException -> L44
        L40:
            r1.append("\r\n");     // Catch: IOException -> L44
            r1.flush();     // Catch: IOException -> L44
            sendBodyWithCorrectTransferAndEncoding(r7, r24);     // Catch: IOException -> L44
            r7.flush();     // Catch: IOException -> L44
            NanoHTTPD.access$000(this.data);     // Catch: IOException -> L44
            return;
        L38:
            if (this.encodeAsGzip == true) goto L40;
            r24 = sendContentLengthHeaderIfNotAlreadyPresent(r1, r24);     // Catch: IOException -> L44
            goto L40
        L31:
            r24 = 0;
            goto L33
        L43:
            throw new Error("sendResponse(): Status can't be null.");     // Catch: IOException -> L44
        }

        public long sendContentLengthHeaderIfNotAlreadyPresent(PrintWriter r3, long r4) {
            String r2 = getHeader("content-length");
            if (r2 != null) goto L9;
        L7:
            r3.print("Content-Length: " + r4 + "\r\n");
            return r4;
        L9:
            r4 = Long.parseLong(r2);     // Catch: NumberFormatException -> L6
        L6:
            NanoHTTPD.access$200().severe("content-length was no number ".concat(r2));
            goto L7
        }

        public void setChunkedTransfer(boolean r1) {
            this.chunkedTransfer = r1;
        }

        public void setGzipEncoding(boolean r1) {
            this.encodeAsGzip = r1;
        }

        public void setKeepAlive(boolean r1) {
            this.keepAlive = r1;
        }

        public void setRequestMethod(Method r1) {
            this.requestMethod = r1;
        }
    }

    public static final class ResponseException extends Exception {
        private final Response.Status status;

        public ResponseException(Response.Status r1, String r2) {
            super(r2);
            this.status = r1;
        }

        public ResponseException(Response.Status r1, String r2, Exception r3) {
            super(r2, r3);
            this.status = r1;
        }

        public Response.Status getStatus() {
            return this.status;
        }
    }

    public class ServerRunnable implements Runnable {
        private IOException bindException;
        private boolean hasBinded;
        final /* synthetic */ NanoHTTPD this$0;
        private final int timeout;

        public ServerRunnable(NanoHTTPD r1, int r2) {
            this.this$0 = r1;
            this.hasBinded = false;
            this.timeout = r2;
        }

        public static /* synthetic */ boolean access$1100(ServerRunnable r0) {
            return r0.hasBinded;
        }

        public static /* synthetic */ IOException access$1200(ServerRunnable r0) {
            return r0.bindException;
        }

        @Override // java.lang.Runnable
        public void run() {
            ServerSocket r0 = NanoHTTPD.access$900(this.this$0);     // Catch: IOException -> L5
            if (NanoHTTPD.access$700(this.this$0) == null) goto L7;
            InetSocketAddress r1 = new InetSocketAddress(NanoHTTPD.access$700(this.this$0), NanoHTTPD.access$800(this.this$0));     // Catch: IOException -> L5
        L8:
            r0.bind(r1);     // Catch: IOException -> L5
            this.hasBinded = true;     // Catch: IOException -> L5
        L22:
            Socket r02 = NanoHTTPD.access$900(this.this$0).accept();     // Catch: IOException -> L12
            int r12 = this.timeout;     // Catch: IOException -> L12
            if (r12 <= 0) goto L14;
            r02.setSoTimeout(r12);     // Catch: IOException -> L12
        L14:
            InputStream r13 = r02.getInputStream();     // Catch: IOException -> L12
            NanoHTTPD r2 = this.this$0;     // Catch: IOException -> L12
            r2.asyncRunner.exec(r2.createClientHandler(r02, r13));     // Catch: IOException -> L12
        L18:
            if (NanoHTTPD.access$900(this.this$0).isClosed() == false) goto L22;
            return;
        L12:
            e = move-exception;
            NanoHTTPD.access$200().log(Level.FINE, "Communication with the client broken", e);
            goto L18
        L7:
            r1 = new InetSocketAddress(NanoHTTPD.access$800(this.this$0));     // Catch: IOException -> L5
        L5:
            e = move-exception;
            this.bindException = e;
        }
    }

    public interface ServerSocketFactory {
        ServerSocket create();
    }

    public interface TempFileManager {
        void clear();
    }

    public interface TempFileManagerFactory {
        TempFileManager create();
    }

    static {
        CONTENT_DISPOSITION_PATTERN = Pattern.compile("([ |\t]*Content-Disposition[ |\t]*:)(.*)", 2);
        CONTENT_TYPE_PATTERN = Pattern.compile("([ |\t]*content-type[ |\t]*:)(.*)", 2);
        CONTENT_DISPOSITION_ATTRIBUTE_PATTERN = Pattern.compile("[ |\t]*([a-zA-Z]*)[ |\t]*=[ |\t]*['|\"]([^\"^']*)['|\"]");
        LOG = Logger.getLogger(NanoHTTPD.class.getName());
    }

    public NanoHTTPD(int r2) {
        this(null, r2);
    }

    public NanoHTTPD(String r2, int r3) {
        this.serverSocketFactory = new DefaultServerSocketFactory();
        this.hostname = r2;
        this.myPort = r3;
        setTempFileManagerFactory(new DefaultTempFileManagerFactory(this, null));
        setAsyncRunner(new DefaultAsyncRunner());
    }

    public static /* synthetic */ void access$000(Object r0) {
        safeClose(r0);
    }

    public static /* synthetic */ TempFileManagerFactory access$100(NanoHTTPD r0) {
        return r0.tempFileManagerFactory;
    }

    public static /* synthetic */ Logger access$200() {
        return LOG;
    }

    public static /* synthetic */ String access$700(NanoHTTPD r0) {
        return r0.hostname;
    }

    public static /* synthetic */ int access$800(NanoHTTPD r0) {
        return r0.myPort;
    }

    public static /* synthetic */ ServerSocket access$900(NanoHTTPD r0) {
        return r0.myServerSocket;
    }

    public static String decodePercent(String r3) {
        return URLDecoder.decode(r3, "UTF8");
    L4:
        e = move-exception;
        LOG.log(Level.WARNING, "Encoding not supported, ignored", e);
        return null;
    }

    public static Response newFixedLengthResponse(Response.IStatus r6, String r7, InputStream r8, long r9) {
        return new Response(r6, r7, r8, r9);
    }

    public static Response newFixedLengthResponse(Response.IStatus r4, String r5, String r6) {
        ContentType r0 = new ContentType(r5);
        if (r6 == null) goto L5;
    L9:
        e = move-exception;
        LOG.log(Level.SEVERE, "encoding problem, responding nothing", e);
        byte[] r52 = new byte[0];
    L15:
        return newFixedLengthResponse(r4, r0.getContentTypeHeader(), new ByteArrayInputStream(r52), r52.length);
    L7:
        if (Charset.forName(r0.getEncoding()).newEncoder().canEncode(r6) == true) goto L11;
        r0 = r0.tryUTF8();     // Catch: UnsupportedEncodingException -> L9
    L11:
        r52 = r6.getBytes(r0.getEncoding());     // Catch: UnsupportedEncodingException -> L9
        goto L15
    L5:
        return newFixedLengthResponse(r4, r5, new ByteArrayInputStream(new byte[0]), 0);
    }

    private static final void safeClose(Object r3) {
        if (r3 == null) goto L21;
    L17:
        e = move-exception;
        LOG.log(Level.SEVERE, "Could not close", e);
        return;
    L4:
        if ((r3 instanceof Closeable) == false) goto L8;
        ((Closeable) r3).close();     // Catch: IOException -> L17
        return;
    L8:
        if ((r3 instanceof Socket) == false) goto L12;
        ((Socket) r3).close();     // Catch: IOException -> L17
        return;
    L12:
        if ((r3 instanceof ServerSocket) == false) goto L16;
        ((ServerSocket) r3).close();     // Catch: IOException -> L17
        return;
    L16:
        throw new IllegalArgumentException("Unknown object to close");     // Catch: IOException -> L17
    }

    public ClientHandler createClientHandler(Socket r2, InputStream r3) {
        return new ClientHandler(this, r3, r2);
    }

    public ServerRunnable createServerRunnable(int r2) {
        return new ServerRunnable(this, r2);
    }

    public ServerSocketFactory getServerSocketFactory() {
        return this.serverSocketFactory;
    }

    public abstract Response serve(IHTTPSession r1);

    public void setAsyncRunner(AsyncRunner r1) {
        this.asyncRunner = r1;
    }

    public void setTempFileManagerFactory(TempFileManagerFactory r1) {
        this.tempFileManagerFactory = r1;
    }

    public void start(int r3, boolean r4) {
        this.myServerSocket = getServerSocketFactory().create();
        this.myServerSocket.setReuseAddress(true);
        ServerRunnable r32 = createServerRunnable(r3);
        Thread r0 = new Thread(r32);
        this.myThread = r0;
        r0.setDaemon(r4);
        this.myThread.setName("NanoHttpd Main Listener");
        this.myThread.start();
    L4:
        if (ServerRunnable.access$1100(r32) == true) goto L11;
        if (ServerRunnable.access$1200(r32) != null) goto L11;
        Thread.sleep(10);     // Catch: Throwable -> L15
    L11:
        if (ServerRunnable.access$1200(r32) != null) goto L14;
        return;
    L14:
        throw ServerRunnable.access$1200(r32);
    }

    public void stop() {
        safeClose(this.myServerSocket);     // Catch: Exception -> L6
        this.asyncRunner.closeAll();     // Catch: Exception -> L6
        Thread r3 = this.myThread;     // Catch: Exception -> L6
        if (r3 == null) goto L11;
        r3.join();     // Catch: Exception -> L6
        return;
    L11:
        return;
    L6:
        e = move-exception;
        LOG.log(Level.SEVERE, "Could not stop all connections", e);
    }

    public boolean useGzipWhenAccepted(Response r2) {
        if (r2.getMimeType() != null) goto L5;
        return false;
    L5:
        if (r2.getMimeType().toLowerCase().contains("text/") == false) goto L7;
        return true;
    L7:
        if (r2.getMimeType().toLowerCase().contains("/json") == false) goto L13;
        return true;
    L13:
        return false;
    }
}
