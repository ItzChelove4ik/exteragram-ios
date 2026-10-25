package org.telegram.utils.proxy;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.android.dx.rop.code.RegisterSpec;
import com.google.android.gms.cast.MediaStatus;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.IDN;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.ForegroundDetector;

/* loaded from: classes6.dex */
public final class WebProxyTransport implements ForegroundDetector.Listener {
    private static WebProxyTransport connectionTestInstance;
    private static WebProxyTransport instance;
    private static final Object staticLock = null;
    private final String address;
    private final String androidNonce;
    private final String bridgePath;
    private final String bridgeUrl;
    private boolean carrierConnected;
    private final ExecutorService carrierExecutor;
    private final String host;
    private final ExecutorService ioExecutor;
    private final Object lock;
    private final AtomicInteger nextStreamId;
    private final String origin;
    private final ArrayDeque<byte[]> outbound;
    private int outboundBytes;
    private final String path;
    private ReadyCallback readyCallback;
    private JavaScriptReplyProxy replyProxy;
    private boolean restartScheduled;
    private final String secret;
    private final ServerSocket serverSocket;
    private boolean stopped;
    private final Map<Integer, Stream> streams;
    private WebView webView;

    /* renamed from: org.telegram.utils.proxy.WebProxyTransport$1, reason: invalid class name */
    public class AnonymousClass1 extends WebViewClient {
        final /* synthetic */ WebProxyTransport this$0;

        public AnonymousClass1(WebProxyTransport r1) {
            this.this$0 = r1;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView r1, WebResourceRequest r2, WebResourceError r3) {
            if (r2.isForMainFrame() == false) goto L6;
            WebProxyTransport.m22250$$Nest$mfailWebView(this.this$0, r1);
            return;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView r1, WebResourceRequest r2, WebResourceResponse r3) {
            if (r2.isForMainFrame() == false) goto L6;
            WebProxyTransport.m22250$$Nest$mfailWebView(this.this$0, r1);
            return;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView r1, SslErrorHandler r2, SslError r3) {
            r2.cancel();
            WebProxyTransport.m22250$$Nest$mfailWebView(this.this$0, r1);
        }

        @Override // android.webkit.WebViewClient
        public boolean onRenderProcessGone(WebView r1, RenderProcessGoneDetail r2) {
            WebProxyTransport.m22250$$Nest$mfailWebView(this.this$0, r1);
            return true;
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView r2, WebResourceRequest r3) {
            Uri r22 = r3.getUrl();
            if ("http".equalsIgnoreCase(r22.getScheme()) == true) goto L7;
            if ("https".equalsIgnoreCase(r22.getScheme()) == true) goto L7;
            return null;
        L7:
            if (WebProxyTransport.m22251$$Nest$misAllowedNetworkRequest(this.this$0, r22) == false) goto L9;
            return null;
        L9:
            return new WebResourceResponse("text/plain", "UTF-8", new ByteArrayInputStream(new byte[0]));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView r1, WebResourceRequest r2) {
            if (r2.isForMainFrame() == true) goto L5;
            return true;
        L5:
            if (WebProxyTransport.m22252$$Nest$misBridgeNavigation(this.this$0, r2.getUrl()) == false) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static final class Address {
        private final String host;
        private final String path;
        private final String value;

        /* renamed from: -$$Nest$fgethost, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22253$$Nest$fgethost(Address r0) {
            return r0.host;
        }

        /* renamed from: -$$Nest$fgetpath, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22254$$Nest$fgetpath(Address r0) {
            return r0.path;
        }

        /* renamed from: -$$Nest$fgetvalue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m22255$$Nest$fgetvalue(Address r0) {
            return r0.value;
        }

        private Address(String r2, String r3) {
            this.host = r2;
            this.path = r3;
            if (r3.isEmpty() == true) goto L6;
            r2 = r2 + "/" + r3;
        L6:
            this.value = r2;
        }

        public /* synthetic */ Address(String r1, String r2, WebProxyTransportIA r3) {
            this(r1, r2);
        }
    }

    public interface ReadyCallback {
        void onReady();
    }

    public static final class Stream {
        private final int id;
        private boolean opened;
        private long receiveWindow;
        private long sendWindow;
        private final Socket socket;

        /* renamed from: -$$Nest$fgetid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m22256$$Nest$fgetid(Stream r0) {
            return r0.id;
        }

        /* renamed from: -$$Nest$fgetopened, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m22257$$Nest$fgetopened(Stream r0) {
            return r0.opened;
        }

        /* renamed from: -$$Nest$fgetreceiveWindow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m22258$$Nest$fgetreceiveWindow(Stream r2) {
            return r2.receiveWindow;
        }

        /* renamed from: -$$Nest$fgetsendWindow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m22259$$Nest$fgetsendWindow(Stream r2) {
            return r2.sendWindow;
        }

        /* renamed from: -$$Nest$fgetsocket, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Socket m22260$$Nest$fgetsocket(Stream r0) {
            return r0.socket;
        }

        /* renamed from: -$$Nest$fputopened, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22261$$Nest$fputopened(Stream r0, boolean r1) {
            r0.opened = r1;
        }

        /* renamed from: -$$Nest$fputreceiveWindow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22262$$Nest$fputreceiveWindow(Stream r0, long r1) {
            r0.receiveWindow = r1;
        }

        /* renamed from: -$$Nest$fputsendWindow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22263$$Nest$fputsendWindow(Stream r0, long r1) {
            r0.sendWindow = r1;
        }

        private Stream(int r3, Socket r4) {
            this.sendWindow = 4194304;
            this.receiveWindow = 4194304;
            this.id = r3;
            this.socket = r4;
        }

        public /* synthetic */ Stream(int r1, Socket r2, WebProxyTransportIA r3) {
            this(r1, r2);
        }
    }

    /* renamed from: $r8$lambda$7c6xDs-p285ya7HtCzYwYzQ8xcc, reason: not valid java name */
    public static /* synthetic */ void m22246$r8$lambda$7c6xDsp285ya7HtCzYwYzQ8xcc(WebProxyTransport r0, WebView r1, WebMessageCompat r2, Uri r3, boolean r4, JavaScriptReplyProxy r5) {
        r0.onWebMessage(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ int $r8$lambda$BXafk1NbrkLlFNU1AKiN047Ptyw(int r2) {
        if (r2 < 16777215) goto L6;
        return 1;
    L6:
        return r2 + 1;
    }

    /* renamed from: $r8$lambda$OafH-_ZHsFga34Cl6E6IY54SB04, reason: not valid java name */
    public static /* synthetic */ void m22247$r8$lambda$OafH_ZHsFga34Cl6E6IY54SB04(WebProxyTransport r0) {
        r0.drainOutbound();
    }

    public static /* synthetic */ void $r8$lambda$R0PF751RokEJyumMOKT2Iq5o0zo(WebProxyTransport r0) {
        r0.lambda$failCarrier$4();
    }

    public static /* synthetic */ void $r8$lambda$RC9hMGcOexZfSHIimGPiMfX37cc(WebProxyTransport r0, Stream r1) {
        r0.lambda$acceptLoop$1(r1);
    }

    /* renamed from: $r8$lambda$iDj0f6b9HqC1X1XUL6A-bQ_VvzU, reason: not valid java name */
    public static /* synthetic */ void m22248$r8$lambda$iDj0f6b9HqC1X1XUL6AbQ_VvzU(WebProxyTransport r0) {
        r0.createWebView();
    }

    public static /* synthetic */ void $r8$lambda$jBgePuFx1gWtE3jfj388ww3JlWo(WebProxyTransport r0, byte[] r1) {
        r0.lambda$onWebMessage$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$oj7CwJCzcQESg1sRgOQT7sNLNC8(WebProxyTransport r0) {
        r0.lambda$stopInternal$0();
    }

    /* renamed from: $r8$lambda$xcOgU7AP9Ij-UQAVTMD6bQOcxcQ, reason: not valid java name */
    public static /* synthetic */ void m22249$r8$lambda$xcOgU7AP9IjUQAVTMD6bQOcxcQ(WebProxyTransport r0) {
        r0.acceptLoop();
    }

    /* renamed from: -$$Nest$mfailWebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22250$$Nest$mfailWebView(WebProxyTransport r0, WebView r1) {
        r0.failWebView(r1);
    }

    /* renamed from: -$$Nest$misAllowedNetworkRequest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22251$$Nest$misAllowedNetworkRequest(WebProxyTransport r0, Uri r1) {
        return r0.isAllowedNetworkRequest(r1);
    }

    /* renamed from: -$$Nest$misBridgeNavigation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22252$$Nest$misBridgeNavigation(WebProxyTransport r0, Uri r1) {
        return r0.isBridgeNavigation(r1);
    }

    static {
        staticLock = new Object();
    }

    private WebProxyTransport(Address r6, String r7, byte[] r8) {
        this.lock = new Object();
        this.ioExecutor = Executors.newCachedThreadPool();
        this.carrierExecutor = Executors.newSingleThreadExecutor();
        this.nextStreamId = new AtomicInteger(1);
        this.streams = new HashMap();
        this.outbound = new ArrayDeque();
        this.address = Address.m22255$$Nest$fgetvalue(r6);
        String r0 = Address.m22253$$Nest$fgethost(r6);
        this.host = r0;
        String r62 = Address.m22254$$Nest$fgetpath(r6);
        this.path = r62;
        String r2 = "/";
        if (r62.isEmpty() == true) goto L6;
        r2 = "/" + r62 + "/";
    L6:
        this.bridgePath = r2;
        this.secret = r7;
        String r72 = "https://" + r0;
        this.origin = r72;
        String r1 = randomToken(32);
        this.androidNonce = r1;
        if (r62.isEmpty() == false) goto L9;
        String r63 = "tdesktop-web-proxy-bridge-v1\n" + r0;
    L10:
        Mac r3 = Mac.getInstance("HmacSHA256");
        r3.init(new SecretKeySpec(r8, "HmacSHA256"));
        this.bridgeUrl = r72 + r2 + "?bridge=" + Base64.encodeToString(r3.doFinal(r63.getBytes(StandardCharsets.UTF_8)), 11) + "#android=" + r1;
        this.serverSocket = new ServerSocket(0, 64, InetAddress.getByName("127.0.0.1"));
        return;
    L9:
        r63 = "tdesktop-web-proxy-bridge-v2\n" + r0 + "\n" + r62;
        goto L10
    }

    private void acceptLoop() {
    L37:
        Socket r0 = this.serverSocket.accept();     // Catch: Exception -> L17
        r0.setTcpNoDelay(true);     // Catch: Exception -> L17
        Object r2 = this.lock;     // Catch: Exception -> L17
        monitor-enter(r2);     // Catch: Exception -> L17
        if (this.stopped == true) goto L19;
        if (this.streams.size() >= 64) goto L19;
        int r3 = allocateStreamId();     // Catch: Throwable -> L12
        Stream r4 = new Stream(r3, r0, null);     // Catch: Throwable -> L12
        this.streams.put(Integer.valueOf(r3), r4);     // Catch: Throwable -> L12
        if (this.carrierConnected == false) goto L14;
        Stream.m22261$$Nest$fputopened(r4, true);     // Catch: Throwable -> L12
        sendFrame(1, r3, null);     // Catch: Throwable -> L12
    L14:
        monitor-exit(r2);     // Catch: Throwable -> L12
        this.ioExecutor.execute(new WebProxyTransport$$ExternalSyntheticLambda4(this, r4));     // Catch: Exception -> L17
    L19:
        closeSocket(r0);     // Catch: Throwable -> L12
        monitor-exit(r2);     // Catch: Throwable -> L12
        goto L37
    L12:
        th = move-exception;
        throw th;     // Catch: Exception -> L17
    L17:
        e = move-exception;
        monitor-enter(this.lock);
    L30:
        th = move-exception;
        throw th;
    L27:
        if (this.stopped == false) goto L33;
        return;
    L33:
        FileLog.e(e);
        failCarrier();
    }

    private int allocateStreamId() {
    L2:
        int r0 = this.nextStreamId.getAndUpdate(new WebProxyTransport$$ExternalSyntheticLambda8());
        if (r0 == 0) goto L2;
        if (this.streams.containsKey(Integer.valueOf(r0)) == true) goto L2;
        return r0;
    }

    private static void closeSocket(Socket r0) {
        r0.close();     // Catch: Exception -> L4
        return;
    }

    private void closeStream(Stream r4, boolean r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.streams.get(Integer.valueOf(Stream.m22256$$Nest$fgetid(r4))) == r4) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.streams.remove(Integer.valueOf(Stream.m22256$$Nest$fgetid(r4)));     // Catch: Throwable -> L8
        if (r5 == true) goto L13;
    L17:
        boolean r52 = false;
    L18:
        this.lock.notifyAll();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        closeSocket(Stream.m22260$$Nest$fgetsocket(r4));
        if (r52 == false) goto L27;
        sendFrame(3, Stream.m22256$$Nest$fgetid(r4), null);
        return;
    L27:
        return;
    L13:
        if (this.carrierConnected == false) goto L17;
        if (Stream.m22257$$Nest$fgetopened(r4) == false) goto L17;
        r52 = true;
        goto L18
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void createWebView() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L16:
        th = move-exception;
        throw th;
    L5:
        if (this.stopped == false) goto L7;
    L30:
        monitor-exit(r0);     // Catch: Throwable -> L16
        return;
    L7:
        if (this.webView != null) goto L30;
        ForegroundDetector r1 = ForegroundDetector.getInstance();     // Catch: Throwable -> L16
        if (r1 != null) goto L12;
    L18:
        this.restartScheduled = false;     // Catch: Throwable -> L16
        monitor-exit(r0);     // Catch: Throwable -> L16
        destroyWebView();
        WebView r02 = new WebView(ApplicationLoader.applicationContext);     // Catch: Exception -> L27
        this.webView = r02;
        r02.setBackgroundColor(0);
        WebSettings r3 = r02.getSettings();
        r3.setJavaScriptEnabled(true);
        r3.setDomStorageEnabled(false);
        r3.setDatabaseEnabled(false);
        r3.setAllowFileAccess(false);
        r3.setAllowContentAccess(false);
        r3.setCacheMode(2);
        r3.setJavaScriptCanOpenWindowsAutomatically(false);
        r3.setSupportMultipleWindows(false);
        r3.setGeolocationEnabled(false);
        r3.setMediaPlaybackRequiresUserGesture(true);
        r3.setMixedContentMode(1);
        if (Build.VERSION.SDK_INT < 26) goto L25;
        r3.setSafeBrowsingEnabled(true);
    L25:
        r02.setWebViewClient(new AnonymousClass1(this));
        HashSet r12 = new HashSet();
        r12.add(this.origin);
        WebViewCompat.addWebMessageListener(r02, "TelegramWebProxy", r12, new WebProxyTransport$$ExternalSyntheticLambda3(this));
        r02.loadUrl(this.bridgeUrl);
        return;
    L27:
        e = move-exception;
        FileLog.e(e);
        failCarrier();
        return;
    L12:
        if (r1.isBackground() == false) goto L18;
        this.restartScheduled = true;     // Catch: Throwable -> L16
        monitor-exit(r0);     // Catch: Throwable -> L16
    }

    private static byte[] decodeSecret(String r8) {
        if (r8 != null) goto L5;
        return null;
    L5:
        String r82 = r8.trim();
        if (r82.length() == 32) goto L10;
        if (r82.length() == 34) goto L10;
    L26:
        byte[] r2 = Base64.decode(r82, 10);     // Catch: Exception -> L25
    L17:
        if (r2.length != 16) goto L19;
    L24:
        return r2;
    L19:
        if (r2.length == 17) goto L21;
    L23:
        return null;
    L21:
        if ((r2[0] & UByte.MAX_VALUE) != 221) goto L23;
    L25:
        return null;
    L10:
        if (r82.matches("[0-9a-fA-F]+") == false) goto L26;
        int r1 = r82.length() / 2;
        r2 = new byte[r1];
        int r5 = 0;
    L12:
        if (r5 >= r1) goto L17;
        int r6 = r5 * 2;
        r2[r5] = (byte) Integer.parseInt(r82.substring(r6, r6 + 2), 16);
        r5 = r5 + 1;
        goto L12
    }

    private void destroyWebView() {
        WebView r0 = this.webView;
        this.webView = null;
        this.replyProxy = null;
        if (r0 == null) goto L11;
        r0.stopLoading();     // Catch: Exception -> L6
        r0.loadUrl("about:blank");     // Catch: Exception -> L6
        r0.removeAllViews();     // Catch: Exception -> L6
        r0.destroy();     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private void drainOutbound() {
    L2:
        Object r0 = this.lock;
        monitor-enter(r0);
        JavaScriptReplyProxy r1 = this.replyProxy;     // Catch: Throwable -> L17
        if (this.stopped == true) goto L19;
        if (r1 == null) goto L19;
        if (this.outbound.isEmpty() == true) goto L19;
        byte[] r2 = this.outbound.removeFirst();     // Catch: Throwable -> L17
        this.outboundBytes -= r2.length;
        monitor-exit(r0);     // Catch: Throwable -> L17
        r1.postMessage(r2);     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        failCarrier();
        return;
    L19:
        monitor-exit(r0);     // Catch: Throwable -> L17
        return;
    L17:
        th = move-exception;
        throw th;
    }

    private void failCarrier() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L16:
        th = move-exception;
        throw th;
    L5:
        if (this.stopped == false) goto L7;
    L18:
        monitor-exit(r0);     // Catch: Throwable -> L16
        return;
    L7:
        if (this.restartScheduled == true) goto L18;
        this.restartScheduled = true;     // Catch: Throwable -> L16
        int r1 = 0;
        this.carrierConnected = false;     // Catch: Throwable -> L16
        this.replyProxy = null;     // Catch: Throwable -> L16
        this.outbound.clear();     // Catch: Throwable -> L16
        this.outboundBytes = 0;     // Catch: Throwable -> L16
        ArrayList r2 = new ArrayList(this.streams.values());     // Catch: Throwable -> L16
        this.streams.clear();     // Catch: Throwable -> L16
        this.lock.notifyAll();     // Catch: Throwable -> L16
        monitor-exit(r0);     // Catch: Throwable -> L16
        int r02 = r2.size();
    L12:
        if (r1 >= r02) goto L14;
        Object r3 = r2.get(r1);
        r1 = r1 + 1;
        closeSocket(Stream.m22260$$Nest$fgetsocket((Stream) r3));
        goto L12
    L14:
        AndroidUtilities.runOnUIThread(new WebProxyTransport$$ExternalSyntheticLambda6(this));
    }

    private void failWebView(WebView r2) {
        if (r2 != this.webView) goto L6;
        failCarrier();
        return;
    }

    private void handleControl(String r5, JavaScriptReplyProxy r6) {
        JSONObject r0 = new JSONObject(r5);     // Catch: Exception -> L31
        String r52 = r0.optString("t");     // Catch: Exception -> L31
        if ("tproxy-android-init".equals(r52) == false) goto L26;
        if (r0.optInt(RegisterSpec.PREFIX) != 1) goto L26;
        if (this.androidNonce.equals(r0.optString("nonce")) == false) goto L26;
        Object r53 = this.lock;     // Catch: Exception -> L31
        monitor-enter(r53);     // Catch: Exception -> L31
    L19:
        th = move-exception;
        throw th;     // Catch: Exception -> L31
    L11:
        if (this.stopped == false) goto L13;
    L21:
        monitor-exit(r53);     // Catch: Throwable -> L19
        return;
    L13:
        if (this.replyProxy != null) goto L21;
        this.replyProxy = r6;     // Catch: Throwable -> L19
        monitor-exit(r53);     // Catch: Throwable -> L19
        sendFrame(16, 0, new byte[]{1});     // Catch: Exception -> L31
        return;
    L26:
        if ("close".equals(r52) == false) goto L28;
    L29:
        failCarrier();     // Catch: Exception -> L31
        return;
    L28:
        if ("failed".equals(r0.optString("state")) == true) goto L29;
        return;
    }

    private boolean isAllowedNetworkRequest(Uri r4) {
        String r0 = r4.getPath();
        if ("https".equalsIgnoreCase(r4.getScheme()) == true) goto L5;
        return false;
    L5:
        if (this.host.equalsIgnoreCase(r4.getHost()) == true) goto L7;
        return false;
    L7:
        if (r4.getUserInfo() == null) goto L9;
        return false;
    L9:
        if (r4.getPort() != (-1)) goto L11;
    L12:
        if (r0 != null) goto L14;
        return false;
    L14:
        if (r0.startsWith(this.bridgePath) == false) goto L23;
        return true;
    L23:
        return false;
    L11:
        if (r4.getPort() == 443) goto L12;
        return false;
    }

    private boolean isBridgeNavigation(Uri r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (this.bridgeUrl.equals(r1.toString()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean isCurrentBridgeDocument(WebView r4) {
        String r42 = r4.getUrl();
        if (r42 != null) goto L5;
        return false;
    L5:
        Uri r43 = Uri.parse(r42);
        if ("https".equalsIgnoreCase(r43.getScheme()) == true) goto L8;
    L17:
        return false;
    L8:
        if (this.host.equalsIgnoreCase(r43.getHost()) == false) goto L17;
        if (r43.getUserInfo() != null) goto L17;
        if (r43.getPort() != (-1)) goto L17;
        if (this.bridgePath.equals(r43.getPath()) == false) goto L17;
        return true;
    }

    public static boolean isSupported() {
    L9:
        th = move-exception;
        FileLog.e(th);
        return false;
    L4:
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER") == false) goto L11;
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_ARRAY_BUFFER") == false) goto L11;
        return true;
    L11:
        return false;
    }

    private static boolean isValidPath(String r8) {
        if (r8.length() <= 128) goto L6;
        return false;
    L6:
        if (r8.isEmpty() == false) goto L8;
        return true;
    L8:
        String[] r82 = r8.split("/", -1);
        int r0 = r82.length;
        int r3 = 0;
    L9:
        if (r3 >= r0) goto L38;
        String r4 = r82[r3];
        if (r4.isEmpty() == true) goto L37;
        if (Character.isLetterOrDigit(r4.charAt(0)) == false) goto L37;
        int r5 = 0;
    L17:
        if (r5 >= r4.length()) goto L36;
        char r6 = r4.charAt(r5);
        if (r6 < 'A') goto L23;
        if (r6 > 'Z') goto L23;
    L35:
        r5 = r5 + 1;
    L23:
        if (r6 < 'a') goto L27;
        if (r6 <= 'z') goto L35;
    L27:
        if (r6 < '0') goto L31;
        if (r6 <= '9') goto L35;
    L31:
        if (r6 == '_') goto L35;
        if (r6 == '-') goto L35;
        return false;
    L36:
        r3 = r3 + 1;
    L37:
        return false;
    L38:
        return true;
    }

    public static boolean isValidSecret(String r0) {
        if (decodeSecret(r0) == null) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$acceptLoop$1(Stream r1) {
        readLoop(r1);
    }

    private /* synthetic */ void lambda$failCarrier$4() {
        destroyWebView();
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.stopped == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        AndroidUtilities.runOnUIThread(new WebProxyTransport$$ExternalSyntheticLambda2(this), 1000);
    }

    private /* synthetic */ void lambda$onWebMessage$3(byte[] r1) {
        processFrames(r1);
    }

    private /* synthetic */ void lambda$stopInternal$0() {
        JavaScriptReplyProxy r0 = this.replyProxy;
        if (r0 != null) goto L8;
    L5:
        destroyWebView();
        return;
    L8:
        r0.postMessage("{\"t\":\"close\"}");     // Catch: Exception -> L7
        goto L5
    }

    private static Address normalizeAddress(String r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        int r1 = r3.indexOf(47);
        if (r1 < 0) goto L8;
        String r2 = r3.substring(0, r1);
    L9:
        String r22 = normalizeHost(r2);
        if (r1 < 0) goto L12;
        String r32 = r3.substring(r1 + 1);
    L14:
        if (TextUtils.isEmpty(r22) == false) goto L16;
    L20:
        return null;
    L16:
        if (isValidPath(r32) == false) goto L20;
        return new Address(r22, r32, null);
    L12:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L8:
        r2 = r3;
        goto L9
    }

    public static String normalizeHost(String r7) {
        if (r7 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r72 = r7.trim();
        int r2 = 0;
        if (r72.endsWith(".") == false) goto L35;
        r72 = r72.substring(0, r72.length() - 1);
    L35:
        String r73 = IDN.toASCII(r72, 2).toLowerCase(Locale.US);     // Catch: Exception -> L34
        if (r73.length() > 253) goto L33;
        if (r73.indexOf(46) <= 0) goto L33;
        if (r73.contains(":") == true) goto L33;
        if (r73.matches("[0-9.]+") == true) goto L33;
        String[] r1 = r73.split("\\.", -1);
        int r3 = r1.length;
    L20:
        if (r2 >= r3) goto L32;
        String r4 = r1[r2];
        if (r4.isEmpty() == true) goto L31;
        if (r4.length() > 63) goto L31;
        if (r4.startsWith("-") == true) goto L31;
        if (r4.endsWith("-") == true) goto L31;
        r2 = r2 + 1;
    L31:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L32:
        return r73;
    L33:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private void onWebMessage(WebView r2, WebMessageCompat r3, Uri r4, boolean r5, JavaScriptReplyProxy r6) {
        if (r2 != this.webView) goto L34;
        if (r5 == false) goto L36;
        if (r4 != null) goto L7;
        return;
    L7:
        if (this.origin.equals(r4.toString()) == true) goto L9;
        return;
    L9:
        if (isCurrentBridgeDocument(r2) == true) goto L12;
        return;
    L12:
        if (r3.getType() != 0) goto L16;
        handleControl(r3.getData(), r6);
        return;
    L16:
        if (r3.getType() != 1) goto L39;
        Object r22 = this.lock;
        monitor-enter(r22);
    L28:
        th = move-exception;
        throw th;
    L20:
        if (this.stopped == true) goto L30;
        JavaScriptReplyProxy r42 = this.replyProxy;     // Catch: Throwable -> L28
        if (r42 == null) goto L30;
        if (r42 != r6) goto L30;
        monitor-exit(r22);     // Catch: Throwable -> L28
        byte[] r23 = r3.getArrayBuffer();
        this.carrierExecutor.execute(new WebProxyTransport$$ExternalSyntheticLambda5(this, r23));
        return;
    L30:
        monitor-exit(r22);     // Catch: Throwable -> L28
        return;
    L39:
        return;
    L36:
        return;
    }

    private boolean processFrame(int r10, int r11, byte[] r12) {
        boolean r0 = true;
        int r1 = 0;
        if (r11 == 0) goto L5;
        Object r2 = this.lock;
        monitor-enter(r2);
        Stream r112 = this.streams.get(Integer.valueOf(r11));     // Catch: Throwable -> L50
        if (r112 != null) goto L52;
        if (r10 == 2) goto L48;
        if (r10 == 4) goto L48;
        if (r10 == 3) goto L48;
        r0 = false;
    L48:
        monitor-exit(r2);     // Catch: Throwable -> L50
        return r0;
    L52:
        monitor-exit(r2);     // Catch: Throwable -> L50
        if (r10 == 2) goto L55;
        if (r10 == 4) goto L84;
    L101:
        if (r10 == 3) goto L103;
    L106:
        return false;
    L103:
        if (r12.length != 0) goto L106;
        closeStream(r112, false);
        return true;
    L84:
        if (r12.length != 4) goto L101;
        long r22 = ByteBuffer.wrap(r12).getInt() & 4294967295L;
        if (r22 != 0) goto L88;
        return false;
    L88:
        Object r6 = this.lock;
        monitor-enter(r6);
    L94:
        th = move-exception;
        throw th;
    L91:
        if (Stream.m22259$$Nest$fgetsendWindow(r112) <= (4294967295L - r22)) goto L96;
        monitor-exit(r6);     // Catch: Throwable -> L94
        return false;
    L96:
        Stream.m22263$$Nest$fputsendWindow(r112, Stream.m22259$$Nest$fgetsendWindow(r112) + r22);     // Catch: Throwable -> L94
        this.lock.notifyAll();     // Catch: Throwable -> L94
        monitor-exit(r6);     // Catch: Throwable -> L94
        return true;
    L55:
        if (r12.length != 0) goto L57;
        return false;
    L57:
        Object r23 = this.lock;
        monitor-enter(r23);
    L63:
        th = move-exception;
        throw th;
    L60:
        if (Stream.m22258$$Nest$fgetreceiveWindow(r112) >= r12.length) goto L65;
        monitor-exit(r23);     // Catch: Throwable -> L63
        return false;
    L65:
        Stream.m22262$$Nest$fputreceiveWindow(r112, Stream.m22258$$Nest$fgetreceiveWindow(r112) - r12.length);     // Catch: Throwable -> L63
        monitor-exit(r23);     // Catch: Throwable -> L63
        Stream.m22260$$Nest$fgetsocket(r112).getOutputStream().write(r12);     // Catch: Exception -> L78
        Object r102 = this.lock;     // Catch: Exception -> L78
        monitor-enter(r102);     // Catch: Exception -> L78
    L72:
        th = move-exception;
        throw th;     // Catch: Exception -> L78
    L70:
        if (this.streams.get(Integer.valueOf(Stream.m22256$$Nest$fgetid(r112))) != r112) goto L74;
        Stream.m22262$$Nest$fputreceiveWindow(r112, Stream.m22258$$Nest$fgetreceiveWindow(r112) + r12.length);     // Catch: Throwable -> L72
        sendFrame(4, Stream.m22256$$Nest$fgetid(r112), uint32(r12.length));     // Catch: Throwable -> L72
    L74:
        monitor-exit(r102);     // Catch: Throwable -> L72
    L79:
        return true;
    L78:
        closeStream(r112, true);
    L50:
        th = move-exception;
        throw th;
    L5:
        if (r10 != 17) goto L30;
        if (r12.length != 0) goto L30;
        Object r113 = this.lock;
        monitor-enter(r113);
    L18:
        th = move-exception;
        throw th;
    L11:
        if (this.stopped == false) goto L13;
    L25:
        monitor-exit(r113);     // Catch: Throwable -> L18
        return false;
    L13:
        if (this.carrierConnected == true) goto L25;
        this.carrierConnected = true;     // Catch: Throwable -> L18
        ArrayList r103 = new ArrayList(this.streams.values());     // Catch: Throwable -> L18
        int r122 = r103.size();     // Catch: Throwable -> L18
    L16:
        if (r1 >= r122) goto L20;
        Object r24 = r103.get(r1);     // Catch: Throwable -> L18
        r1 = r1 + 1;     // Catch: Throwable -> L18
        Stream r25 = (Stream) r24;     // Catch: Throwable -> L18
        Stream.m22261$$Nest$fputopened(r25, true);     // Catch: Throwable -> L18
        sendFrame(1, Stream.m22256$$Nest$fgetid(r25), null);     // Catch: Throwable -> L18
        goto L16
    L20:
        ReadyCallback r104 = this.readyCallback;     // Catch: Throwable -> L18
        this.lock.notifyAll();     // Catch: Throwable -> L18
        monitor-exit(r113);     // Catch: Throwable -> L18
        if (r104 == null) goto L24;
        AndroidUtilities.runOnUIThread(new WebProxyTransport$$ExternalSyntheticLambda9(r104));
    L24:
        return true;
    L30:
        if (r10 != 5) goto L36;
        if (r12.length > 64) goto L36;
        sendFrame(6, 0, r12);
        return true;
    L36:
        if (r10 != 31) goto L38;
        int r9 = r12.length;
    L38:
        return false;
    }

    private void processFrames(byte[] r13) {
        int r1 = 0;
    L4:
        if (r1 >= r13.length) goto L26;
        if ((r13.length - r1) < 8) goto L7;
        int r2 = r13[r1] & UByte.MAX_VALUE;
        int r4 = (((r13[r1 + 1] & UByte.MAX_VALUE) << 16) | ((r13[r1 + 2] & UByte.MAX_VALUE) << 8)) | (r13[r1 + 3] & UByte.MAX_VALUE);
        long r5 = ((((r13[r1 + 4] & 255) << 24) | ((r13[r1 + 5] & 255) << 16)) | ((r13[r1 + 6] & 255) << 8)) | (r13[r1 + 7] & 255);
        int r12 = r1 + 8;
        long r7 = r12 + r5;
        if (r5 > 1048576) goto L19;
        if (r7 > r13.length) goto L19;
        int r3 = (int) r5;
        byte[] r52 = new byte[r3];
        System.arraycopy(r13, r12, r52, 0, r3);
        if (processFrame(r2, r4, r52) == false) goto L16;
        r1 = (int) r7;
        goto L4
    L16:
        failCarrier();
        return;
    L19:
        failCarrier();
        return;
    L7:
        failCarrier();
        return;
    }

    private static String randomToken(int r1) {
        byte[] r12 = new byte[r1];
        new SecureRandom().nextBytes(r12);
        return Base64.encodeToString(r12, 11);
    }

    private void readLoop(Stream r11) {
        byte[] r0 = new byte[65536];
        InputStream r2 = Stream.m22260$$Nest$fgetsocket(r11).getInputStream();     // Catch: Exception -> L56
    L4:
        Object r3 = this.lock;     // Catch: Exception -> L56
        monitor-enter(r3);     // Catch: Exception -> L56
    L60:
    L17:
        th = move-exception;
        throw th;     // Catch: Exception -> L56
    L7:
        if (this.stopped == true) goto L21;
        if (this.streams.get(Integer.valueOf(Stream.m22256$$Nest$fgetid(r11))) != r11) goto L21;
        if (this.carrierConnected == false) goto L19;
        if (Stream.m22257$$Nest$fgetopened(r11) == false) goto L19;
        if (Stream.m22259$$Nest$fgetsendWindow(r11) != 0) goto L21;
    L19:
        this.lock.wait();     // Catch: Throwable -> L17
    L21:
        if (this.stopped == true) goto L52;
        if (this.streams.get(Integer.valueOf(Stream.m22256$$Nest$fgetid(r11))) != r11) goto L52;
        int r4 = (int) Math.min(MediaStatus.COMMAND_FOLLOW, Stream.m22259$$Nest$fgetsendWindow(r11));     // Catch: Throwable -> L17
        monitor-exit(r3);     // Catch: Throwable -> L17
        int r42 = r2.read(r0, 0, r4);     // Catch: Exception -> L56
        if (r42 < 0) goto L30;
        if (r42 == 0) goto L4;
        byte[] r5 = new byte[r42];     // Catch: Exception -> L56
        System.arraycopy(r0, 0, r5, 0, r42);     // Catch: Exception -> L56
        Object r32 = this.lock;     // Catch: Exception -> L56
        monitor-enter(r32);     // Catch: Exception -> L56
    L46:
        th = move-exception;
        throw th;     // Catch: Exception -> L56
    L37:
        if (this.stopped == true) goto L48;
        if (this.streams.get(Integer.valueOf(Stream.m22256$$Nest$fgetid(r11))) != r11) goto L48;
        if (this.carrierConnected == false) goto L48;
        Stream.m22263$$Nest$fputsendWindow(r11, Stream.m22259$$Nest$fgetsendWindow(r11) - r42);     // Catch: Throwable -> L46
        sendFrame(2, Stream.m22256$$Nest$fgetid(r11), r5);     // Catch: Throwable -> L46
        monitor-exit(r32);     // Catch: Throwable -> L46
    L48:
        monitor-exit(r32);     // Catch: Throwable -> L46
        return;
    L30:
        closeStream(r11, true);     // Catch: Exception -> L56
        return;
    L52:
        monitor-exit(r3);     // Catch: Throwable -> L17
        return;
    L56:
        closeStream(r11, true);
    }

    private void sendFrame(int r3, int r4, byte[] r5) {
        if (r5 != null) goto L4;
        r5 = new byte[0];
    L4:
        byte[] r32 = ByteBuffer.allocate(r5.length + 8).put((byte) r3).put((byte) (r4 >> 16)).put((byte) (r4 >> 8)).put((byte) r4).putInt(r5.length).put(r5).array();
        Object r42 = this.lock;
        monitor-enter(r42);
    L17:
        th = move-exception;
        throw th;
    L7:
        if (this.stopped == false) goto L9;
    L19:
        failCarrier();     // Catch: Throwable -> L17
        monitor-exit(r42);     // Catch: Throwable -> L17
        return;
    L9:
        if (this.outbound.size() >= 8192) goto L19;
        if (this.outboundBytes > (67108864 - r32.length)) goto L19;
        this.outbound.add(r32);     // Catch: Throwable -> L17
        this.outboundBytes += r32.length;
        monitor-exit(r42);     // Catch: Throwable -> L17
        AndroidUtilities.runOnUIThread(new WebProxyTransport$$ExternalSyntheticLambda7(this));
    }

    public static int start(String r5, String r6) {
        Address r52 = normalizeAddress(r5);
        byte[] r0 = decodeSecret(r6);
        if (r52 == null) goto L35;
        if (r0 == null) goto L35;
        if (isSupported() == false) goto L35;
        Object r2 = staticLock;
        monitor-enter(r2);
        WebProxyTransport r3 = instance;     // Catch: Throwable -> L19
        if (r3 != null) goto L13;
    L21:
        WebProxyTransport r32 = instance;     // Catch: Throwable -> L19
        if (r32 == null) goto L36;
        r32.stopInternal();     // Catch: Throwable -> L19
        instance = null;     // Catch: Throwable -> L19
    L36:
        WebProxyTransport r33 = new WebProxyTransport(r52, r6, r0);     // Catch: Throwable -> L19 Exception -> L27
        instance = r33;     // Catch: Throwable -> L19 Exception -> L27
        r33.startInternal();     // Catch: Throwable -> L19 Exception -> L27
        int r53 = instance.serverSocket.getLocalPort();     // Catch: Throwable -> L19 Exception -> L27
        monitor-exit(r2);     // Catch: Throwable -> L19
        return r53;
    L27:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L19
        WebProxyTransport r54 = instance;     // Catch: Throwable -> L19
        if (r54 == null) goto L32;
        r54.stopInternal();     // Catch: Throwable -> L19
        instance = null;     // Catch: Throwable -> L19
    L32:
        return 0;
    L13:
        if (r3.address.equals(Address.m22255$$Nest$fgetvalue(r52)) == false) goto L21;
        if (instance.secret.equals(r6) == false) goto L21;
        int r55 = instance.serverSocket.getLocalPort();     // Catch: Throwable -> L19
        monitor-exit(r2);     // Catch: Throwable -> L19
        return r55;
    L19:
        th = move-exception;
        throw th;
    L35:
        return 0;
    }

    public static int startConnectionCheck(String r5, String r6, ReadyCallback r7) {
        Address r52 = normalizeAddress(r5);
        byte[] r0 = decodeSecret(r6);
        if (r52 == null) goto L27;
        if (r0 == null) goto L27;
        if (isSupported() == false) goto L27;
        Object r2 = staticLock;
        monitor-enter(r2);
        WebProxyTransport r3 = connectionTestInstance;     // Catch: Throwable -> L14
        if (r3 == null) goto L29;
        r3.stopInternal();     // Catch: Throwable -> L14
        connectionTestInstance = null;     // Catch: Throwable -> L14
    L29:
        WebProxyTransport r32 = new WebProxyTransport(r52, r6, r0);     // Catch: Throwable -> L14 Exception -> L19
        connectionTestInstance = r32;     // Catch: Throwable -> L14 Exception -> L19
        r32.readyCallback = r7;     // Catch: Throwable -> L14 Exception -> L19
        r32.startInternal();     // Catch: Throwable -> L14 Exception -> L19
        int r53 = connectionTestInstance.serverSocket.getLocalPort();     // Catch: Throwable -> L14 Exception -> L19
        monitor-exit(r2);     // Catch: Throwable -> L14
        return r53;
    L19:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L14
        WebProxyTransport r54 = connectionTestInstance;     // Catch: Throwable -> L14
        if (r54 == null) goto L24;
        r54.stopInternal();     // Catch: Throwable -> L14
        connectionTestInstance = null;     // Catch: Throwable -> L14
    L24:
        return 0;
    L14:
        th = move-exception;
        throw th;
    L27:
        return 0;
    }

    private void startInternal() {
        ForegroundDetector r0 = ForegroundDetector.getInstance();
        if (r0 == null) goto L5;
        r0.addListener(this);
    L5:
        this.ioExecutor.execute(new WebProxyTransport$$ExternalSyntheticLambda1(this));
        AndroidUtilities.runOnUIThread(new WebProxyTransport$$ExternalSyntheticLambda2(this));
    }

    public static void stop() {
        Object r0 = staticLock;
        monitor-enter(r0);
        WebProxyTransport r1 = instance;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.stopInternal();     // Catch: Throwable -> L7
        instance = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static void stopConnectionCheck() {
        Object r0 = staticLock;
        monitor-enter(r0);
        WebProxyTransport r1 = connectionTestInstance;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.stopInternal();     // Catch: Throwable -> L7
        connectionTestInstance = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void stopInternal() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.stopped == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.stopped = true;     // Catch: Throwable -> L8
        int r1 = 0;
        this.carrierConnected = false;     // Catch: Throwable -> L8
        this.restartScheduled = false;     // Catch: Throwable -> L8
        ArrayList r2 = new ArrayList(this.streams.values());     // Catch: Throwable -> L8
        this.streams.clear();     // Catch: Throwable -> L8
        this.outbound.clear();     // Catch: Throwable -> L8
        this.outboundBytes = 0;     // Catch: Throwable -> L8
        this.lock.notifyAll();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.serverSocket.close();     // Catch: Exception -> L23
    L13:
        ForegroundDetector r02 = ForegroundDetector.getInstance();
        if (r02 == null) goto L16;
        r02.removeListener(this);
    L16:
        int r03 = r2.size();
    L17:
        if (r1 >= r03) goto L19;
        Object r3 = r2.get(r1);
        r1 = r1 + 1;
        closeSocket(Stream.m22260$$Nest$fgetsocket((Stream) r3));
        goto L17
    L19:
        AndroidUtilities.runOnUIThread(new WebProxyTransport$$ExternalSyntheticLambda0(this));
        this.ioExecutor.shutdownNow();
        this.carrierExecutor.shutdownNow();
    }

    private static byte[] uint32(int r1) {
        return ByteBuffer.allocate(4).putInt(r1).array();
    }

    @Override // org.telegram.ui.Components.ForegroundDetector.Listener
    public void onBecameBackground() {
    }

    @Override // org.telegram.ui.Components.ForegroundDetector.Listener
    public void onBecameForeground() {
        AndroidUtilities.runOnUIThread(new WebProxyTransport$$ExternalSyntheticLambda2(this));
    }
}
