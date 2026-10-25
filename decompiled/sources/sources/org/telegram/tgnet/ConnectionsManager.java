package org.telegram.tgnet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.InstallSourceInfo;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.Keep;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import com.exteragram.messenger.feed.FeedRequestNormalizer;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.hooks.PluginsHooks;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BaseController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.CaptchaController;
import org.telegram.messenger.EmuDetector;
import org.telegram.messenger.FileLoadOperation;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileUploadOperation;
import org.telegram.messenger.KeepAliveJob;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.StatsController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.utils.proxy.ProxySettings;
import org.telegram.utils.proxy.WebProxyConnectionTester;
import org.telegram.utils.proxy.WebProxyTransport;

/* loaded from: classes.dex */
public class ConnectionsManager extends BaseController {
    private static final int CORE_POOL_SIZE = 0;
    public static final int CPU_COUNT = 0;
    public static final int ConnectionStateConnected = 3;
    public static final int ConnectionStateConnecting = 1;
    public static final int ConnectionStateConnectingToProxy = 4;
    public static final int ConnectionStateUpdating = 5;
    public static final int ConnectionStateWaitingForNetwork = 2;
    public static final int ConnectionTypeDownload = 2;
    public static final int ConnectionTypeDownload2 = 65538;
    public static final int ConnectionTypeGeneric = 1;
    public static final int ConnectionTypePush = 8;
    public static final int ConnectionTypeUpload = 4;
    private static final String DEBUG_DNS_CONFIG_KEY = "ipconfigv3_override";
    private static final String DEBUG_DNS_PREFS = "debugdnsconfig";
    public static final int DEFAULT_DATACENTER_ID = Integer.MAX_VALUE;
    public static final Executor DNS_THREAD_POOL_EXECUTOR = null;
    public static final int FileTypeAudio = 50331648;
    public static final int FileTypeFile = 67108864;
    public static final int FileTypePhoto = 16777216;
    public static final int FileTypeVideo = 33554432;
    private static final ConnectionsManager[] Instance = null;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final int MAXIMUM_POOL_SIZE = 0;
    public static final int RequestFlagCanCompress = 4;
    public static final int RequestFlagDoNotWaitFloodWait = 1024;
    public static final int RequestFlagEnableUnauthorized = 1;
    public static final int RequestFlagFailOnServerErrors = 2;
    public static final int RequestFlagFailOnServerErrorsExceptFloodWait = 65536;
    public static final int RequestFlagForceDownload = 32;
    public static final int RequestFlagInvokeAfter = 64;
    public static final int RequestFlagListenAfterCancel = 2048;
    public static final int RequestFlagNeedQuickAck = 128;
    public static final int RequestFlagTryDifferentDc = 16;
    public static final int RequestFlagWithoutLogin = 8;
    public static final byte USE_IPV4_IPV6_RANDOM = 2;
    public static final byte USE_IPV4_ONLY = 0;
    public static final byte USE_IPV6_ONLY = 1;
    private static AsyncTask currentTask;
    private static HashMap<String, ResolvedDomain> dnsCache;
    private static int lastClassGuid;
    private static long lastDnsRequestTime;
    public static long lastPremiumFloodWaitShown;
    private static HashMap<String, ResolveHostByNameTask> resolvingHostnameTasks;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = null;
    private static final ThreadFactory sThreadFactory = null;
    private boolean appPaused;
    private int appResumeCount;
    private int connectionState;
    private boolean forceTryIpV6;
    private final PluginsHooks hooks;
    private boolean isUpdating;
    private long lastPauseTime;
    private AtomicInteger lastRequestToken;
    private final ConcurrentHashMap<Integer, RequestCallbacks> requestCallbacks;

    /* renamed from: org.telegram.tgnet.ConnectionsManager$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        private final AtomicInteger mCount;

        public AnonymousClass1() {
            this.mCount = new AtomicInteger(1);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r4) {
            return new Thread(r4, "DnsAsyncTask #" + this.mCount.getAndIncrement());
        }
    }

    /* loaded from: classes6.dex */
    public static class GoogleDnsLoadTask extends AsyncTask<Void, Void, NativeByteBuffer> {
        private int currentAccount;
        private int responseDate;

        /* renamed from: $r8$lambda$PAYja4HI-qyEnV6SlE8rimysgEM, reason: not valid java name */
        public static /* synthetic */ void m5395$r8$lambda$PAYja4HIqyEnV6SlE8rimysgEM(GoogleDnsLoadTask r0, NativeByteBuffer r1) {
            r0.lambda$onPostExecute$1(r1);
        }

        public static /* synthetic */ int $r8$lambda$Pij8WGD9QMO3VZ5JwyajiFAbKoo(String r0, String r1) {
            int r02 = r0.length();
            int r12 = r1.length();
            if (r02 <= r12) goto L6;
            return -1;
        L6:
            if (r02 >= r12) goto L9;
            return 1;
        L9:
            return 0;
        }

        public GoogleDnsLoadTask(int r1) {
            this.currentAccount = r1;
        }

        private /* synthetic */ void lambda$onPostExecute$1(NativeByteBuffer r4) {
            FileLog.d("3. currentTask = null, result = " + r4);
            ConnectionsManager.m5394$$Nest$sfputcurrentTask(null);
            if (r4 == null) goto L7;
            int r0 = this.currentAccount;
            ConnectionsManager.native_applyDnsConfig(r0, r4.address, AccountInstance.getInstance(r0).getUserConfig().getClientPhone(), this.responseDate);
            return;
        L7:
            if (BuildVars.LOGS_ENABLED == false) goto L9;
            FileLog.d("failed to get google result");
            FileLog.d("start mozilla task");
        L9:
            MozillaDnsLoadTask r42 = new MozillaDnsLoadTask(this.currentAccount);
            r42.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
            FileLog.d("4. currentTask = mozilla");
            ConnectionsManager.m5394$$Nest$sfputcurrentTask(r42);
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ NativeByteBuffer doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public NativeByteBuffer doInBackground2(Void... r10) {
        L6:
            th = th;
            ByteArrayOutputStream r0 = null;
            InputStream r1 = null;
        L78:
            FileLog.e(th);     // Catch: Throwable -> L54
            if (r1 != null) goto L66;
        L51:
            if (r0 != null) goto L74;
        L53:
            return null;
        L74:
            r0.close();     // Catch: Exception -> L64
            goto L53
        L66:
            r1.close();     // Catch: Throwable -> L49
        L49:
            th = move-exception;
            FileLog.e(th);
        L54:
            th = move-exception;
            if (r1 != null) goto L70;
        L60:
            if (r0 == null) goto L93;
            r0.close();     // Catch: Exception -> L65
            throw th;
        L94:
            throw th;
        L93:
            throw th;
        L70:
            r1.close();     // Catch: Throwable -> L58
        L58:
            th = move-exception;
            FileLog.e(th);
            goto L60
        L4:
            if (ConnectionsManager.native_isTestBackend(this.currentAccount) == 0) goto L8;
            String r02 = "tapv3.stel.com";
        L9:
            int r12 = Utilities.random.nextInt(116) + 13;     // Catch: Throwable -> L6
            StringBuilder r2 = new StringBuilder(r12);     // Catch: Throwable -> L6
            int r4 = 0;
        L10:
            if (r4 >= r12) goto L12;
            r2.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(Utilities.random.nextInt(62)));     // Catch: Throwable -> L6
            r4 = r4 + 1;     // Catch: Throwable -> L6
            goto L10
        L12:
            URLConnection r03 = new URL("https://dns.google.com/resolve?name=" + r02 + "&type=ANY&random_padding=" + r2).openConnection();     // Catch: Throwable -> L6
            r03.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");     // Catch: Throwable -> L6
            r03.setConnectTimeout(5000);     // Catch: Throwable -> L6
            r03.setReadTimeout(5000);     // Catch: Throwable -> L6
            r03.connect();     // Catch: Throwable -> L6
            r1 = r03.getInputStream();     // Catch: Throwable -> L6
            this.responseDate = (int) (r03.getDate() / 1000);     // Catch: Throwable -> L43
            r0 = new ByteArrayOutputStream();     // Catch: Throwable -> L43
            byte[] r22 = new byte[32768];     // Catch: Throwable -> L22
        L17:
            if (isCancelled() == true) goto L24;
            int r42 = r1.read(r22);     // Catch: Throwable -> L22
            if (r42 <= 0) goto L24;
            r0.write(r22, 0, r42);     // Catch: Throwable -> L22
        L24:
            JSONArray r9 = new JSONObject(new String(r0.toByteArray())).getJSONArray("Answer");     // Catch: Throwable -> L22
            int r23 = r9.length();     // Catch: Throwable -> L22
            ArrayList r43 = new ArrayList(r23);     // Catch: Throwable -> L22
            int r5 = 0;
        L25:
            if (r5 >= r23) goto L31;
            JSONObject r6 = r9.getJSONObject(r5);     // Catch: Throwable -> L22
            if (r6.getInt("type") != 16) goto L30;
            r43.add(r6.getString("data"));     // Catch: Throwable -> L22
        L30:
            r5 = r5 + 1;     // Catch: Throwable -> L22
            goto L25
        L31:
            Collections.sort(r43, new ConnectionsManager$GoogleDnsLoadTask$$ExternalSyntheticLambda0());     // Catch: Throwable -> L22
            StringBuilder r92 = new StringBuilder();     // Catch: Throwable -> L22
            int r24 = 0;
        L33:
            if (r24 >= r43.size()) goto L35;
            r92.append(((String) r43.get(r24)).replace("\"", _UrlKt.FRAGMENT_ENCODE_SET));     // Catch: Throwable -> L22
            r24 = r24 + 1;     // Catch: Throwable -> L22
            goto L33
        L35:
            byte[] r93 = Base64.decode(r92.toString(), 0);     // Catch: Throwable -> L22
            NativeByteBuffer r25 = new NativeByteBuffer(r93.length);     // Catch: Throwable -> L22
            r25.writeBytes(r93);     // Catch: Throwable -> L22
            if (r1 != null) goto L84;
        L80:
            r0.close();     // Catch: Exception -> L63
        L42:
            return r25;
        L84:
            r1.close();     // Catch: Throwable -> L39
        L39:
            th = move-exception;
            FileLog.e(th);
        L22:
            th = th;
        L43:
            th = th;
            r0 = null;
            goto L78
        L8:
            r02 = AccountInstance.getInstance(this.currentAccount).getMessagesController().dcDomainName;     // Catch: Throwable -> L6
            goto L9
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(NativeByteBuffer r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(NativeByteBuffer r3) {
            Utilities.stageQueue.postRunnable(new ConnectionsManager$GoogleDnsLoadTask$$ExternalSyntheticLambda1(this, r3));
        }
    }

    public interface INativeTlTest {
        boolean test(long r1);
    }

    /* loaded from: classes6.dex */
    public static class MozillaDnsLoadTask extends AsyncTask<Void, Void, NativeByteBuffer> {
        private int currentAccount;
        private int responseDate;

        public static /* synthetic */ int $r8$lambda$nE8DlHt4ROLdC70y2dCAIoRiwis(String r0, String r1) {
            int r02 = r0.length();
            int r12 = r1.length();
            if (r02 <= r12) goto L6;
            return -1;
        L6:
            if (r02 >= r12) goto L9;
            return 1;
        L9:
            return 0;
        }

        public static /* synthetic */ void $r8$lambda$xmSo69YYzHrFq5jbCQxziqojwoQ(MozillaDnsLoadTask r0, NativeByteBuffer r1) {
            r0.lambda$onPostExecute$1(r1);
        }

        public MozillaDnsLoadTask(int r1) {
            this.currentAccount = r1;
        }

        private /* synthetic */ void lambda$onPostExecute$1(NativeByteBuffer r4) {
            FileLog.d("5. currentTask = null");
            ConnectionsManager.m5394$$Nest$sfputcurrentTask(null);
            if (r4 == null) goto L7;
            int r0 = this.currentAccount;
            ConnectionsManager.native_applyDnsConfig(r0, r4.address, AccountInstance.getInstance(r0).getUserConfig().getClientPhone(), this.responseDate);
            return;
        L7:
            if (BuildVars.LOGS_ENABLED == false) goto L10;
            FileLog.d("failed to get mozilla txt result");
            return;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ NativeByteBuffer doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public NativeByteBuffer doInBackground2(Void... r10) {
        L6:
            th = th;
            ByteArrayOutputStream r0 = null;
            InputStream r1 = null;
        L66:
            FileLog.e(th);     // Catch: Throwable -> L54
            if (r1 != null) goto L72;
        L51:
            if (r0 != null) goto L84;
        L53:
            return null;
        L84:
            r0.close();     // Catch: Exception -> L64
            goto L53
        L72:
            r1.close();     // Catch: Throwable -> L49
        L49:
            th = move-exception;
            FileLog.e(th);
        L54:
            th = move-exception;
            if (r1 != null) goto L76;
        L60:
            if (r0 == null) goto L93;
            r0.close();     // Catch: Exception -> L65
            throw th;
        L94:
            throw th;
        L93:
            throw th;
        L76:
            r1.close();     // Catch: Throwable -> L58
        L58:
            th = move-exception;
            FileLog.e(th);
            goto L60
        L4:
            if (ConnectionsManager.native_isTestBackend(this.currentAccount) == 0) goto L8;
            String r02 = "tapv3.stel.com";
        L9:
            int r12 = Utilities.random.nextInt(116) + 13;     // Catch: Throwable -> L6
            StringBuilder r2 = new StringBuilder(r12);     // Catch: Throwable -> L6
            int r4 = 0;
        L10:
            if (r4 >= r12) goto L12;
            r2.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(Utilities.random.nextInt(62)));     // Catch: Throwable -> L6
            r4 = r4 + 1;     // Catch: Throwable -> L6
            goto L10
        L12:
            URLConnection r03 = new URL("https://mozilla.cloudflare-dns.com/dns-query?name=" + r02 + "&type=TXT&random_padding=" + r2).openConnection();     // Catch: Throwable -> L6
            r03.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");     // Catch: Throwable -> L6
            r03.addRequestProperty("accept", "application/dns-json");     // Catch: Throwable -> L6
            r03.setConnectTimeout(5000);     // Catch: Throwable -> L6
            r03.setReadTimeout(5000);     // Catch: Throwable -> L6
            r03.connect();     // Catch: Throwable -> L6
            r1 = r03.getInputStream();     // Catch: Throwable -> L6
            this.responseDate = (int) (r03.getDate() / 1000);     // Catch: Throwable -> L43
            r0 = new ByteArrayOutputStream();     // Catch: Throwable -> L43
            byte[] r22 = new byte[32768];     // Catch: Throwable -> L22
        L17:
            if (isCancelled() == true) goto L24;
            int r42 = r1.read(r22);     // Catch: Throwable -> L22
            if (r42 <= 0) goto L24;
            r0.write(r22, 0, r42);     // Catch: Throwable -> L22
        L24:
            JSONArray r9 = new JSONObject(new String(r0.toByteArray())).getJSONArray("Answer");     // Catch: Throwable -> L22
            int r23 = r9.length();     // Catch: Throwable -> L22
            ArrayList r43 = new ArrayList(r23);     // Catch: Throwable -> L22
            int r5 = 0;
        L25:
            if (r5 >= r23) goto L31;
            JSONObject r6 = r9.getJSONObject(r5);     // Catch: Throwable -> L22
            if (r6.getInt("type") != 16) goto L30;
            r43.add(r6.getString("data"));     // Catch: Throwable -> L22
        L30:
            r5 = r5 + 1;     // Catch: Throwable -> L22
            goto L25
        L31:
            Collections.sort(r43, new ConnectionsManager$MozillaDnsLoadTask$$ExternalSyntheticLambda0());     // Catch: Throwable -> L22
            StringBuilder r92 = new StringBuilder();     // Catch: Throwable -> L22
            int r24 = 0;
        L33:
            if (r24 >= r43.size()) goto L35;
            r92.append(((String) r43.get(r24)).replace("\"", _UrlKt.FRAGMENT_ENCODE_SET));     // Catch: Throwable -> L22
            r24 = r24 + 1;     // Catch: Throwable -> L22
            goto L33
        L35:
            byte[] r93 = Base64.decode(r92.toString(), 0);     // Catch: Throwable -> L22
            NativeByteBuffer r25 = new NativeByteBuffer(r93.length);     // Catch: Throwable -> L22
            r25.writeBytes(r93);     // Catch: Throwable -> L22
            if (r1 != null) goto L70;
        L68:
            r0.close();     // Catch: Exception -> L63
        L42:
            return r25;
        L70:
            r1.close();     // Catch: Throwable -> L39
        L39:
            th = move-exception;
            FileLog.e(th);
        L22:
            th = th;
        L43:
            th = th;
            r0 = null;
            goto L66
        L8:
            r02 = AccountInstance.getInstance(this.currentAccount).getMessagesController().dcDomainName;     // Catch: Throwable -> L6
            goto L9
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(NativeByteBuffer r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(NativeByteBuffer r3) {
            Utilities.stageQueue.postRunnable(new ConnectionsManager$MozillaDnsLoadTask$$ExternalSyntheticLambda1(this, r3));
        }
    }

    public static class RequestCallbacks {
        public Runnable onCancelled;
        public RequestDelegateInternal onComplete;
        public QuickAckDelegate onQuickAck;
        public WriteToSocketDelegate onWriteToSocket;

        public RequestCallbacks(RequestDelegateInternal r1, QuickAckDelegate r2, WriteToSocketDelegate r3) {
            this.onComplete = r1;
            this.onQuickAck = r2;
            this.onWriteToSocket = r3;
        }
    }

    /* loaded from: classes6.dex */
    public static class ResolveHostByNameTask extends AsyncTask<Void, Void, ResolvedDomain> {
        private ArrayList<Long> addresses;
        private String currentHostName;

        public ResolveHostByNameTask(String r2) {
            this.addresses = new ArrayList();
            this.currentHostName = r2;
        }

        public void addAddress(long r3) {
            if (this.addresses.contains(Long.valueOf(r3)) == false) goto L5;
            return;
        L5:
            this.addresses.add(Long.valueOf(r3));
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ ResolvedDomain doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public ResolvedDomain doInBackground2(Void... r9) {
            URLConnection r0 = new URL("https://www.google.com/resolve?name=" + this.currentHostName + "&type=A").openConnection();     // Catch: Throwable -> L34
            r0.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");     // Catch: Throwable -> L34
            r0.addRequestProperty("Host", "dns.google.com");     // Catch: Throwable -> L34
            r0.setConnectTimeout(1000);     // Catch: Throwable -> L34
            r0.setReadTimeout(CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L34
            r0.connect();     // Catch: Throwable -> L34
            InputStream r02 = r0.getInputStream();     // Catch: Throwable -> L34
            ByteArrayOutputStream r2 = new ByteArrayOutputStream();     // Catch: Throwable -> L32
            byte[] r3 = new byte[32768];     // Catch: Throwable -> L10
        L7:
            int r4 = r02.read(r3);     // Catch: Throwable -> L10
            int r5 = 0;
            if (r4 <= 0) goto L12;
            r2.write(r3, 0, r4);     // Catch: Throwable -> L10
            goto L7
        L12:
            JSONObject r32 = new JSONObject(new String(r2.toByteArray()));     // Catch: Throwable -> L10
            if (r32.has("Answer") == false) goto L66;
            JSONArray r92 = r32.getJSONArray("Answer");     // Catch: Throwable -> L10
            int r33 = r92.length();     // Catch: Throwable -> L10
            if (r33 <= 0) goto L66;
            ArrayList r42 = new ArrayList(r33);     // Catch: Throwable -> L10
        L17:
            if (r5 >= r33) goto L19;
            r42.add(r92.getJSONObject(r5).getString("data"));     // Catch: Throwable -> L10
            r5 = r5 + 1;     // Catch: Throwable -> L10
            goto L17
        L19:
            ResolvedDomain r93 = new ResolvedDomain(r42, SystemClock.elapsedRealtime());     // Catch: Throwable -> L10
            r02.close();     // Catch: Throwable -> L22
        L62:
            r2.close();     // Catch: Exception -> L58
        L25:
            return r93;
        L22:
            th = move-exception;
            FileLog.e(th);
        L66:
            r02.close();     // Catch: Throwable -> L28
        L76:
            r2.close();     // Catch: Exception -> L59
        L48:
            return null;
        L28:
            th = move-exception;
            FileLog.e(th);
        L10:
            th = th;
        L70:
            FileLog.e(th);     // Catch: Throwable -> L49
            if (r02 != null) goto L80;
        L42:
            if (r2 == null) goto L86;
            r2.close();     // Catch: Exception -> L60
        L86:
            InetAddress r8 = InetAddress.getByName(this.currentHostName);     // Catch: Exception -> L46
            ArrayList r94 = new ArrayList(1);     // Catch: Exception -> L46
            r94.add(r8.getHostAddress());     // Catch: Exception -> L46
            return new ResolvedDomain(r94, SystemClock.elapsedRealtime());
        L46:
            e = move-exception;
            FileLog.e(e);
            goto L48
        L80:
            r02.close();     // Catch: Throwable -> L40
        L40:
            th = move-exception;
            FileLog.e(th);
        L49:
            th = move-exception;
            if (r02 != null) goto L84;
        L55:
            if (r2 == null) goto L90;
            r2.close();     // Catch: Exception -> L61
            throw th;
        L91:
            throw th;
        L90:
            throw th;
        L84:
            r02.close();     // Catch: Throwable -> L53
        L53:
            th = move-exception;
            FileLog.e(th);
        L32:
            th = th;
            r2 = null;
        L34:
            th = th;
            r02 = null;
            r2 = null;
            goto L70
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(ResolvedDomain r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(ResolvedDomain r7) {
            int r0 = 0;
            if (r7 == null) goto L7;
            ConnectionsManager.m5392$$Nest$sfgetdnsCache().put(this.currentHostName, r7);
            int r1 = this.addresses.size();
        L5:
            if (r0 >= r1) goto L10;
            ConnectionsManager.native_onHostNameResolved(this.currentHostName, this.addresses.get(r0).longValue(), r7.getAddress());
            r0 = r0 + 1;
        L10:
            ConnectionsManager.m5393$$Nest$sfgetresolvingHostnameTasks().remove(this.currentHostName);
            return;
        L7:
            int r72 = this.addresses.size();
        L8:
            if (r0 >= r72) goto L10;
            ConnectionsManager.native_onHostNameResolved(this.currentHostName, this.addresses.get(r0).longValue(), _UrlKt.FRAGMENT_ENCODE_SET);
            r0 = r0 + 1;
            goto L8
        }
    }

    /* loaded from: classes6.dex */
    public static class ResolvedDomain {
        public ArrayList<String> addresses;
        long ttl;

        public ResolvedDomain(ArrayList<String> r1, long r2) {
            this.addresses = r1;
            this.ttl = r2;
        }

        public String getAddress() {
            ArrayList<String> r2 = this.addresses;
            return r2.get(Utilities.random.nextInt(r2.size()));
        }
    }

    /* renamed from: $r8$lambda$-mWBBmL9miKALQKetM44WK_EVas, reason: not valid java name */
    public static /* synthetic */ void m5377$r8$lambda$mWBBmL9miKALQKetM44WK_EVas(int r4, boolean r5, int r6) {
        FileLog.d("13. currentTask == " + currentTask);
        if (currentTask != null) goto L30;
        if (r4 == 0) goto L6;
    L7:
        if (r5 == false) goto L30;
        lastDnsRequestTime = System.currentTimeMillis();
        if (r4 != 0) goto L18;
        if (applyStoredDnsConfigOverride(r6) == false) goto L18;
        if (BuildVars.LOGS_ENABLED == false) goto L33;
        FileLog.d("applied debug ipconfigv3 override");
        return;
    L33:
        return;
    L18:
        if (r4 != 2) goto L25;
        if (BuildVars.LOGS_ENABLED == false) goto L22;
        FileLog.d("start mozilla txt task");
    L22:
        MozillaDnsLoadTask r42 = new MozillaDnsLoadTask(r6);
        r42.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        FileLog.d("9. currentTask = mozilla");
        currentTask = r42;
        return;
    L25:
        if (BuildVars.LOGS_ENABLED == false) goto L27;
        FileLog.d("start google txt task");
    L27:
        GoogleDnsLoadTask r43 = new GoogleDnsLoadTask(r6);
        r43.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        FileLog.d("11. currentTask = dnstxt");
        currentTask = r43;
        return;
    L6:
        if (Math.abs(lastDnsRequestTime - System.currentTimeMillis()) >= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L7;
    L30:
        if (BuildVars.LOGS_ENABLED == false) goto L34;
        FileLog.d("don't start task, current task = " + currentTask + " next task = " + r4 + " time diff = " + Math.abs(lastDnsRequestTime - System.currentTimeMillis()) + " network = " + ApplicationLoader.isNetworkOnline());
        return;
    }

    /* renamed from: $r8$lambda$0TA9VKkX8yo-iPM-orUdqP1vgI4, reason: not valid java name */
    public static /* synthetic */ void m5378$r8$lambda$0TA9VKkX8yoiPMorUdqP1vgI4(int r1, boolean r2, int r3) {
        if (UserConfig.selectedAccount == r1) goto L5;
        return;
    L5:
        AndroidUtilities.runOnUIThread(new ConnectionsManager$$ExternalSyntheticLambda22(r2, r1, r3));
    }

    /* renamed from: $r8$lambda$0ugsk_wgEzlBNBkFOs1HHiU-0gE, reason: not valid java name */
    public static /* synthetic */ void m5379$r8$lambda$0ugsk_wgEzlBNBkFOs1HHiU0gE(ConnectionsManager r0, int r1, int r2) {
        r0.lambda$discardConnection$0(r1, r2);
    }

    /* renamed from: $r8$lambda$5CxBz6DPyQrpMKFxOOS5plyR-SI, reason: not valid java name */
    public static /* synthetic */ void m5380$r8$lambda$5CxBz6DPyQrpMKFxOOS5plyRSI(ConnectionsManager r0, ProxySettings r1, int r2, RequestTimeDelegate r3) {
        r0.checkWebProxyInternal(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$60ImW-esLOOpedPbBSgNTbSmlkU, reason: not valid java name */
    public static /* synthetic */ void m5381$r8$lambda$60ImWesLOOpedPbBSgNTbSmlkU(ConnectionsManager r0, RequestDelegate r1, TLObject r2, TLRPC.TL_error r3, RequestDelegateTimestamp r4, long r5) {
        r0.lambda$sendRequestInternal$8(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$6zNj5fPTduGwiMltIf87TS7chpo(int r4) {
        AccountInstance r42 = AccountInstance.getInstance(r4);
        if (r42.getUserConfig().getClientUserId() == 0) goto L6;
        r42.getUserConfig().clearConfig();
        r42.getMessagesController().performLogout(0);
        return;
    }

    /* renamed from: $r8$lambda$8FCsY-UCUxpuzn6ChrudLBlWobk, reason: not valid java name */
    public static /* synthetic */ void m5382$r8$lambda$8FCsYUCUxpuzn6ChrudLBlWobk(int r1, TLObject r2) {
        AccountInstance.getInstance(r1).getMessagesController().processUpdates((TLRPC.Updates) r2, false);
    }

    /* renamed from: $r8$lambda$8rHrGzkyrl-iJMokXSq2auMo358, reason: not valid java name */
    public static /* synthetic */ void m5383$r8$lambda$8rHrGzkyrliJMokXSq2auMo358(Executor r1, Utilities.Callback2 r2, TLObject r3, TLRPC.TL_error r4) {
        if (r1 == null) goto L5;
        r1.execute(new ConnectionsManager$$ExternalSyntheticLambda23(r2, r3, r4));
        return;
    L5:
        r2.run(r3, r4);
    }

    /* renamed from: $r8$lambda$DQkJ4R8Z-Vlkc6W0RKG0wRs2Crs, reason: not valid java name */
    public static /* synthetic */ void m5384$r8$lambda$DQkJ4R8ZVlkc6W0RKG0wRs2Crs(ConnectionsManager r0, boolean r1) {
        r0.lambda$setIsUpdating$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$GGKwml_eIqeLtPvKRWnNFWMNGNI(ConnectionsManager r0, TLObject r1, RequestDelegate r2, RequestDelegateTimestamp r3, QuickAckDelegate r4, WriteToSocketDelegate r5, int r6, int r7, int r8, boolean r9, int r10) {
        r0.lambda$sendRequest$6(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: $r8$lambda$M6M-3sMK7a08kPvg9-UWzD48uYs, reason: not valid java name */
    public static /* synthetic */ void m5385$r8$lambda$M6M3sMK7a08kPvg9UWzD48uYs(Utilities.Callback2 r0, TLObject r1, TLRPC.TL_error r2) {
        r0.run(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NOozgHsa9sqzpsfE6REfn3eo18I(Runnable r1) {
        Utilities.stageQueue.postRunnable(r1);
    }

    /* renamed from: $r8$lambda$OsVhJml-Vuw5MPOICbebkjbrW_0, reason: not valid java name */
    public static /* synthetic */ void m5386$r8$lambda$OsVhJmlVuw5MPOICbebkjbrW_0(ConnectionsManager r0, TLObject r1, int r2, long r3, RequestDelegate r5, RequestDelegateTimestamp r6, QuickAckDelegate r7, WriteToSocketDelegate r8, int r9, int r10, boolean r11, int r12, String r13, long r14, int r16, String r17, int r18, long r19, long r21, int r23) {
        r0.lambda$sendRequestInternal$9(r1, r2, r3, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r16, r17, r18, r19, r21, r23);
    }

    public static /* synthetic */ void $r8$lambda$Sg5DcBMKwjAtRl518M8DOXl0A0E(int r0) {
        AccountInstance.getInstance(r0).getMessagesController().getDifference();
    }

    public static /* synthetic */ void $r8$lambda$YC3WzcJH0aIAt1sjqSzzEIVVXfE(Utilities.Callback2 r0, TLRPC.Updates r1, TLRPC.TL_error r2) {
        r0.run(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_BD8EUfYxjZmTPFVJB1zWlgkSEA(String r5, long r6) {
        ResolvedDomain r0 = dnsCache.get(r5);
        if (r0 != null) goto L5;
    L8:
        ResolveHostByNameTask r02 = resolvingHostnameTasks.get(r5);
        if (r02 != null) goto L16;
        r02 = new ResolveHostByNameTask(r5);
        r02.executeOnExecutor(DNS_THREAD_POOL_EXECUTOR, new Void[]{null, null, null});     // Catch: Throwable -> L13
        resolvingHostnameTasks.put(r5, r02);
    L13:
        th = move-exception;
        FileLog.e(th);
        native_onHostNameResolved(r5, r6, _UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L16:
        r02.addAddress(r6);
        return;
    L5:
        if ((SystemClock.elapsedRealtime() - r0.ttl) >= 300000) goto L8;
        native_onHostNameResolved(r5, r6, r0.getAddress());
    }

    public static /* synthetic */ void $r8$lambda$aUSjcyL9xvFAocqQyf8K3anKZ8s(ConnectionsManager r0, Runnable r1, int r2, boolean r3) {
        r0.lambda$cancelRequest$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$dIQoTwnKyQyK2n-soItOK2xRnag, reason: not valid java name */
    public static /* synthetic */ void m5387$r8$lambda$dIQoTwnKyQyK2nsoItOK2xRnag(ConnectionsManager r0, int r1) {
        r0.lambda$failNotRunningRequest$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$e_ePAvFupTBLYpu2k55etfxOA4U(RequestDelegate r3, RequestDelegateTimestamp r4) {
        if (r3 == null) goto L6;
        r3.run(null, null);
        return;
    L6:
        if (r4 == null) goto L9;
        r4.run(null, null, 0);
        return;
    }

    /* renamed from: $r8$lambda$h6ki4c1-w9MuvCF5XO1v1UnDbFk, reason: not valid java name */
    public static /* synthetic */ void m5388$r8$lambda$h6ki4c1w9MuvCF5XO1v1UnDbFk() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needShowAlert, new Object[]{3});
    }

    /* renamed from: $r8$lambda$iG_9ceLKhXA-5fRJYjJeo73HQl0, reason: not valid java name */
    public static /* synthetic */ void m5389$r8$lambda$iG_9ceLKhXA5fRJYjJeo73HQl0(boolean r2, int r3, int r4) {
        if (r2 == false) goto L7;
        FileUploadOperation r22 = FileLoader.getInstance(r3).findUploadOperationByRequestToken(r4);
        if (r22 == null) goto L10;
        boolean r42 = !r22.caughtPremiumFloodWait;
        r22.caughtPremiumFloodWait = true;
    L11:
        if (r42 == false) goto L14;
        NotificationCenter.getInstance(r3).postNotificationName(NotificationCenter.premiumFloodWaitReceived, new Object[0]);
        return;
    L14:
        return;
    L10:
        r42 = false;
        goto L11
    L7:
        FileLoadOperation r23 = FileLoader.getInstance(r3).findLoadOperationByRequestToken(r4);
        if (r23 == null) goto L10;
        r42 = !r23.caughtPremiumFloodWait;
        r23.caughtPremiumFloodWait = true;
        goto L11
    }

    public static /* synthetic */ void $r8$lambda$iTP5Ih6rmDStD2br9OAWVG8MR_U(int r0, TLRPC.TL_config r1) {
        AccountInstance.getInstance(r0).getMessagesController().updateConfig(r1);
    }

    /* renamed from: $r8$lambda$lTlJusPE9E-DJXS3zXZ345UtmOE, reason: not valid java name */
    public static /* synthetic */ void m5390$r8$lambda$lTlJusPE9EDJXS3zXZ345UtmOE(ConnectionsManager r0, Executor r1, Utilities.Callback2 r2, TLRPC.Updates r3, TLRPC.TL_error r4) {
        r0.lambda$sendRequestTypedAndProcessUpdates$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$nygMewsF3WVa1Qvc8iWc0c60MaE(int r3, int r4) {
        boolean r0 = ApplicationLoader.isNetworkOnline();
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda2(r3, r0, r4));
    }

    public static /* synthetic */ void $r8$lambda$vesrs7ui0NfbkH31kWTCwAVPf64(int r1, int r2) {
        getInstance(r1).connectionState = r2;
        AccountInstance.getInstance(r1).getNotificationCenter().postNotificationName(NotificationCenter.didUpdateConnectionState, new Object[0]);
    }

    /* renamed from: $r8$lambda$vjaFK3U-fOWktVlL0go8KKOmvTE, reason: not valid java name */
    public static /* synthetic */ void m5391$r8$lambda$vjaFK3UfOWktVlL0go8KKOmvTE(int r0) {
        AccountInstance.getInstance(r0).getMessagesController().updateTimerProc();
    }

    public static /* synthetic */ void $r8$lambda$zltMngyUnZJ53gd3up7mmFVgTDc(ConnectionsManager r0, int r1) {
        r0.lambda$cancelRequestsForGuid$12(r1);
    }

    /* renamed from: -$$Nest$sfgetdnsCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m5392$$Nest$sfgetdnsCache() {
        return dnsCache;
    }

    /* renamed from: -$$Nest$sfgetresolvingHostnameTasks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m5393$$Nest$sfgetresolvingHostnameTasks() {
        return resolvingHostnameTasks;
    }

    /* renamed from: -$$Nest$sfputcurrentTask, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5394$$Nest$sfputcurrentTask(AsyncTask r0) {
        currentTask = r0;
    }

    static {
        resolvingHostnameTasks = new HashMap();
        int r0 = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = r0;
        int r4 = Math.max(2, Math.min(r0 - 1, 4));
        CORE_POOL_SIZE = r4;
        int r5 = (r0 * 2) + 1;
        MAXIMUM_POOL_SIZE = r5;
        LinkedBlockingQueue r9 = new LinkedBlockingQueue(128);
        sPoolWorkQueue = r9;
        AnonymousClass1 r10 = new AnonymousClass1();
        sThreadFactory = r10;
        ThreadPoolExecutor r3 = new ThreadPoolExecutor(r4, r5, 30, TimeUnit.SECONDS, r9, r10);
        r3.allowCoreThreadTimeOut(true);
        DNS_THREAD_POOL_EXECUTOR = r3;
        dnsCache = new HashMap();
        lastClassGuid = 1;
        Instance = new ConnectionsManager[16];
        lastPremiumFloodWaitShown = 0;
    }

    public ConnectionsManager(int r19) {
        super(r19);
        this.hooks = PluginsController.getInstance();
        this.lastPauseTime = System.currentTimeMillis();
        this.appPaused = true;
        this.lastRequestToken = new AtomicInteger(1);
        this.requestCallbacks = new ConcurrentHashMap();
        this.connectionState = native_getConnectionState(this.currentAccount);
        File r8 = ApplicationLoader.getFilesDirFixed();
        if (r19 == 0) goto L5;
        File r9 = new File(r8, "account" + r19);
        r9.mkdirs();
        r8 = r9;
    L5:
        String r92 = r8.toString();
        boolean r17 = isPushConnectionEnabled();
        boolean r1 = false;
        String r82 = LocaleController.getSystemLocaleStringIso639().toLowerCase();     // Catch: Exception -> L14
        String r10 = LocaleController.getLocaleStringIso639().toLowerCase();     // Catch: Exception -> L14
        String r11 = Build.MANUFACTURER + Build.MODEL;     // Catch: Exception -> L14
        PackageInfo r12 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Exception -> L14
        String r122 = r12.versionName + " (" + r12.versionCode + ")";     // Catch: Exception -> L14
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L10;
        r122 = r122.concat(" pbeta");     // Catch: Exception -> L14
    L12:
        String r5 = "SDK " + Build.VERSION.SDK_INT;     // Catch: Exception -> L14
    L13:
        String r7 = r10;
        if (r82.trim().length() != 0) goto L19;
        r82 = "en";
    L19:
        if (r11.trim().length() != 0) goto L21;
        String r4 = "Android unknown";
    L23:
        if (r122.trim().length() != 0) goto L25;
        String r6 = "App version unknown";
    L27:
        if (r5.trim().length() != 0) goto L29;
        r5 = "SDK Unknown";
    L29:
        getUserConfig().loadConfig();
        String r112 = getRegId();
        String r123 = AndroidUtilities.getCertificateSHA256Fingerprint();
        int r13 = (TimeZone.getDefault().getRawOffset() + TimeZone.getDefault().getDSTSavings()) / 1000;
        if (this.currentAccount != 0) goto L32;
        SharedPreferences r2 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
    L33:
        this.forceTryIpV6 = r2.getBoolean("forceTryIpV6", false);
        if (getUserConfig().getCurrentUser() == null) goto L36;
        r1 = getUserConfig().getCurrentUser().premium;
    L36:
        init(SharedConfig.buildVersion(), TLRPC.LAYER, BuildVars.getExteraAppId(), r4, r5, r6, r7, r82, r92, FileLog.getNetworkLogPath(), r112, r123, r13, getUserConfig().getClientUserId(), r1, r17);
        return;
    L32:
        r2 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig" + this.currentAccount, 0);
        goto L33
    L25:
        r6 = r122;
        goto L27
    L21:
        r4 = r11;
        goto L23
    L10:
        if (BuildVars.DEBUG_VERSION == false) goto L12;
        r122 = r122.concat(" beta");     // Catch: Exception -> L14
    L14:
        r5 = "SDK " + Build.VERSION.SDK_INT;
        r10 = _UrlKt.FRAGMENT_ENCODE_SET;
        r122 = "App version unknown";
        r11 = "Android unknown";
        r82 = "en";
        goto L13
    }

    private static boolean applyDnsConfigString(int r4, String r5, int r6) {
        if (TextUtils.isEmpty(r5) == false) goto L11;
        return false;
    L11:
        byte[] r52 = Base64.decode(r5, 0);     // Catch: Throwable -> L8
        NativeByteBuffer r0 = new NativeByteBuffer(r52.length);     // Catch: Throwable -> L8
        r0.writeBytes(r52);     // Catch: Throwable -> L8
        native_applyDnsConfig(r4, r0.address, AccountInstance.getInstance(r4).getUserConfig().getClientPhone(), r6);     // Catch: Throwable -> L8
        return true;
    L8:
        th = move-exception;
        FileLog.e(th);
        return false;
    }

    private static boolean applyStoredDnsConfigOverride(int r2) {
        return applyDnsConfigString(r2, getDebugDnsConfigOverride(), 0);
    }

    private void checkWebProxyInternal(ProxySettings r8, int r9, RequestTimeDelegate r10) {
        native_checkProxy(this.currentAccount, "127.0.0.1", r9, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, r8.getSecret(), r10);
    }

    public static int generateClassGuid() {
        int r0 = lastClassGuid;
        lastClassGuid = r0 + 1;
        return r0;
    }

    public static String getDebugDnsConfigOverride() {
        return getDebugDnsPreferences().getString(DEBUG_DNS_CONFIG_KEY, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    private static SharedPreferences getDebugDnsPreferences() {
        return ApplicationLoader.applicationContext.getSharedPreferences(DEBUG_DNS_PREFS, 0);
    }

    public static void getHostByName(String r1, long r2) {
        AndroidUtilities.runOnUIThread(new ConnectionsManager$$ExternalSyntheticLambda3(r1, r2));
    }

    public static int getInitFlags() {
        if (EmuDetector.with(ApplicationLoader.applicationContext).detect() == true) goto L5;
        return 0;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("detected emu");
        return 1024;
    L11:
        return 1024;
    }

    public static ConnectionsManager getInstance(int r3) {
        ConnectionsManager[] r0 = Instance;
        ConnectionsManager r1 = r0[r3];
        if (r1 == null) goto L5;
        return r1;
    L5:
        monitor-enter(ConnectionsManager.class);
        ConnectionsManager r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new ConnectionsManager(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(ConnectionsManager.class);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    private String getRegId() {
        String r0 = SharedConfig.pushString;
        if (TextUtils.isEmpty(r0) == false) goto L8;
        if (TextUtils.isEmpty(SharedConfig.pushStringStatus) == true) goto L8;
        r0 = SharedConfig.pushStringStatus;
    L8:
        if (TextUtils.isEmpty(r0) == false) goto L11;
        String r2 = "__FIREBASE_GENERATING_SINCE_" + getCurrentTime() + "__";
        SharedConfig.pushStringStatus = r2;
        return r2;
    L11:
        return r0;
    }

    private /* synthetic */ void lambda$cancelRequest$11(Runnable r2, int r3, boolean r4) {
        if (r2 == null) goto L4;
        listenCancel(r3, new ConnectionsManager$$ExternalSyntheticLambda1(r2));
    L4:
        native_cancelRequest(this.currentAccount, r3, r4);
    }

    private /* synthetic */ void lambda$cancelRequestsForGuid$12(int r1) {
        native_cancelRequestsForGuid(this.currentAccount, r1);
    }

    private /* synthetic */ void lambda$discardConnection$0(int r1, int r2) {
        native_discardConnection(this.currentAccount, r1, r2);
    }

    private /* synthetic */ void lambda$failNotRunningRequest$1(int r1) {
        native_failNotRunningRequest(this.currentAccount, r1);
    }

    private /* synthetic */ void lambda$sendRequest$6(TLObject r1, RequestDelegate r2, RequestDelegateTimestamp r3, QuickAckDelegate r4, WriteToSocketDelegate r5, int r6, int r7, int r8, boolean r9, int r10) {
        sendRequestInternal(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    private /* synthetic */ void lambda$sendRequestInternal$8(RequestDelegate r1, TLObject r2, TLRPC.TL_error r3, RequestDelegateTimestamp r4, long r5) {
        if (r1 == null) goto L4;
        r1.run(r2, r3);
    L9:
        if (r2 == null) goto L12;
        r2.freeResources();
        return;
    L12:
        return;
    L4:
        if (r4 == null) goto L7;
        r4.run(r2, r3, r5);
        goto L9
    L7:
        if ((r2 instanceof TLRPC.Updates) == false) goto L9;
        KeepAliveJob.finishJob();
        AccountInstance.getInstance(this.currentAccount).getMessagesController().processUpdates((TLRPC.Updates) r2, false);
        goto L9
    }

    private /* synthetic */ void lambda$sendRequestInternal$9(TLObject r19, int r20, long r21, RequestDelegate r23, RequestDelegateTimestamp r24, QuickAckDelegate r25, WriteToSocketDelegate r26, int r27, int r28, boolean r29, int r30, String r31, long r32, int r34, String r35, int r36, long r37, long r39, int r41) {
        TLRPC.TL_error r11 = null;
        if (r32 == 0) goto L14;
        NativeByteBuffer r2 = NativeByteBuffer.wrap(r32);     // Catch: Exception -> L54
        r2.setDataSourceType(TLDataSourceType.NETWORK);     // Catch: Exception -> L54
        r2.reused = true;     // Catch: Exception -> L54
        int r3 = r2.limit();     // Catch: Exception -> L54
        TLObject r22 = r19.deserializeResponse(r2, r2.readInt32(true), true);     // Catch: Exception -> L7
    L23:
        if ((r20 & 2) == 0) goto L28;
        if (VideoPlayer.activePlayers.isEmpty() == false) goto L28;
        DefaultBandwidthMeter.getSingletonInstance(ApplicationLoader.applicationContext).onTransfer(r3, Math.max(0, (System.currentTimeMillis() - r21) - native_getCurrentPingTime(this.currentAccount)));     // Catch: Exception -> L54
    L28:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L42;
        if (getUserConfig().isClientActivated() == true) goto L42;
        if (r11 == null) goto L42;
        if (r11.code != 400) goto L42;
        if (Objects.equals(r11.text, "CONNECTION_NOT_INITED") == false) goto L42;
        if (BuildVars.LOGS_ENABLED == false) goto L39;
        FileLog.d("Cleanup keys for " + this.currentAccount + " because of CONNECTION_NOT_INITED");     // Catch: Exception -> L54
    L39:
        cleanup(true);     // Catch: Exception -> L54
        sendRequest(r19, r23, r24, r25, r26, r27, r28, r20, r29);     // Catch: Exception -> L54
        return;
    L42:
        if (r22 == null) goto L45;
        r22.networkType = r36;     // Catch: Exception -> L54
    L45:
        if (BuildVars.LOGS_ENABLED == false) goto L51;
        StringBuilder r0 = new StringBuilder("java received ");     // Catch: Exception -> L54
        r0.append(r22);     // Catch: Exception -> L54
        if (r11 == null) goto L49;
        String r1 = " error = " + r11;     // Catch: Exception -> L54
    L50:
        r0.append(r1);     // Catch: Exception -> L54
        r0.append(" messageId = 0x");     // Catch: Exception -> L54
        r0.append(Long.toHexString(r39));     // Catch: Exception -> L54
        FileLog.d(r0.toString());     // Catch: Exception -> L54
        TLRPC.TL_error r33 = r11;
        FileLog.dumpResponseAndRequest(this.currentAccount, r19, r22, r33, r39, r21, r30);     // Catch: Exception -> L54
    L52:
        PluginsHooks.PostRequestResult r02 = this.hooks.executePostRequestHook(r31, this.currentAccount, r22, r33);     // Catch: Exception -> L54
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda25(this, r23, r02.getResponse(), r02.getError(), r24, r37));     // Catch: Exception -> L54
        return;
    L49:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L50
    L51:
        r33 = r11;
    L7:
        e = move-exception;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L12;
        FileLog.fatal(e);     // Catch: Exception -> L54
        return;
    L12:
        throw e;     // Catch: Exception -> L54
    L14:
        if (r35 == null) goto L21;
        TLRPC.TL_error r12 = new TLRPC.TL_error();     // Catch: Exception -> L54
        r12.code = r34;     // Catch: Exception -> L54
        r12.text = r35;     // Catch: Exception -> L54
        if (BuildVars.LOGS_ENABLED == true) goto L18;
    L20:
        r3 = 0;
        r22 = null;
        r11 = r12;
        goto L23
    L18:
        if (r34 == (-2000)) goto L20;
        FileLog.e(r19 + " got error " + r12.code + " " + r12.text);     // Catch: Exception -> L54
        goto L20
    L21:
        r3 = 0;
        r22 = null;
    L54:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$sendRequestTypedAndProcessUpdates$5(Executor r2, Utilities.Callback2 r3, TLRPC.Updates r4, TLRPC.TL_error r5) {
        if (r4 == null) goto L4;
        getMessagesController().processUpdates(r4, false);
    L4:
        if (r2 == null) goto L7;
        r2.execute(new ConnectionsManager$$ExternalSyntheticLambda9(r3, r4, r5));
        return;
    L7:
        r3.run(r4, r5);
    }

    private /* synthetic */ void lambda$setIsUpdating$23(boolean r2) {
        if (this.isUpdating == r2) goto L10;
        this.isUpdating = r2;
        if (this.connectionState != 3) goto L9;
        AccountInstance.getInstance(this.currentAccount).getNotificationCenter().postNotificationName(NotificationCenter.didUpdateConnectionState, new Object[0]);
        return;
    L9:
        return;
    }

    private void listen(int r2, RequestDelegateInternal r3, QuickAckDelegate r4, WriteToSocketDelegate r5) {
        this.requestCallbacks.put(Integer.valueOf(r2), new RequestCallbacks(r3, r4, r5));
    }

    private void listenCancel(int r1, Runnable r2) {
        RequestCallbacks r0 = this.requestCallbacks.get(Integer.valueOf(r1));
        if (r0 == null) goto L6;
        r0.onCancelled = r2;
        return;
    }

    public static native byte[] nativeTestGenerateClientHello(String r0);

    public static native void native_applyDatacenterAddress(int r0, int r1, String r2, int r3);

    public static native void native_applyDnsConfig(int r0, long r1, String r3, int r4);

    public static native void native_bindRequestToGuid(int r0, int r1, int r2);

    public static native void native_cancelRequest(int r0, int r1, boolean r2);

    public static native void native_cancelRequestsForGuid(int r0, int r1);

    public static native long native_checkProxy(int r0, String r1, int r2, String r3, String r4, String r5, RequestTimeDelegate r6);

    public static native void native_cleanUp(int r0, boolean r1);

    public static native void native_discardConnection(int r0, int r1, int r2);

    public static native void native_failNotRunningRequest(int r0, int r1);

    public static native int native_getConnectionState(int r0);

    public static native long native_getCurrentAuthKeyId(int r0);

    public static native int native_getCurrentDatacenterId(int r0);

    public static native int native_getCurrentPingTime(int r0);

    public static native int native_getCurrentTime(int r0);

    public static native long native_getCurrentTimeMillis(int r0);

    public static native int native_getTimeDifference(int r0);

    public static native void native_init(int r0, int r1, int r2, int r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, String r14, int r15, long r16, boolean r18, boolean r19, boolean r20, int r21, int r22);

    public static native boolean native_isGoodPrime(byte[] r0, int r1);

    public static native int native_isTestBackend(int r0);

    public static native void native_moveDatacenter(int r0, int r1);

    public static native void native_onHostNameResolved(String r0, long r1, String r3);

    public static native void native_pauseNetwork(int r0);

    public static native void native_receivedCaptchaResult(int r0, int[] r1, String r2);

    public static native void native_receivedIntegrityCheckClassic(int r0, int r1, String r2, String r3);

    public static native void native_resumeNetwork(int r0, boolean r1);

    public static native void native_sendRequest(int r0, long r1, int r3, int r4, int r5, boolean r6, int r7);

    public static native void native_setIpStrategy(int r0, byte r1);

    public static native void native_setJava(boolean r0);

    public static native void native_setLangCode(int r0, String r1);

    public static native void native_setNetworkAvailable(int r0, boolean r1, int r2, boolean r3);

    public static native void native_setProxySettings(int r0, String r1, int r2, String r3, String r4, String r5);

    public static native void native_setPushConnectionEnabled(int r0, boolean r1);

    public static native void native_setRegId(int r0, String r1);

    public static native void native_setSystemLangCode(int r0, String r1);

    public static native void native_setUserId(int r0, long r1);

    public static native void native_switchBackend(int r0, boolean r1);

    public static native boolean native_test_AuthAuthorization(long r0);

    public static native void native_updateDcSettings(int r0);

    public static void onBytesReceived(int r3, int r4, int r5) {
        StatsController.getInstance(r5).incrementReceivedBytesCount(r4, 6, r3);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static void onBytesSent(int r3, int r4, int r5) {
        AccountInstance.getInstance(r5).getStatsController().incrementSentBytesCount(r4, 6, r3);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    @Keep
    public static void onCaptchaCheck(int r0, int r1, String r2, String r3) {
        CaptchaController.request(r0, r1, r2, r3);
    }

    public static void onConnectionStateChanged(int r1, int r2) {
        AndroidUtilities.runOnUIThread(new ConnectionsManager$$ExternalSyntheticLambda10(r2, r1));
    }

    @Keep
    public static void onIntegrityCheckClassic(int r0, int r1, String r2, String r3) {
        native_receivedIntegrityCheckClassic(r0, r1, r3, "PLAYINTEGRITY_FAILED_EXCEPTION");
    }

    public static void onInternalPushReceived(int r0) {
        KeepAliveJob.startJob();
    }

    public static void onLogout(int r1) {
        AndroidUtilities.runOnUIThread(new ConnectionsManager$$ExternalSyntheticLambda8(r1));
    }

    @Keep
    public static void onPremiumFloodWait(int r1, int r2, boolean r3) {
        AndroidUtilities.runOnUIThread(new ConnectionsManager$$ExternalSyntheticLambda13(r1, r3, r2));
    }

    public static void onProxyError() {
        AndroidUtilities.runOnUIThread(new ConnectionsManager$$ExternalSyntheticLambda16());
    }

    public static void onRequestClear(int r2, int r3, boolean r4) {
        ConnectionsManager r22 = getInstance(r2);
        if (r22 == null) goto L18;
        RequestCallbacks r0 = r22.requestCallbacks.get(Integer.valueOf(r3));
        if (r4 == false) goto L13;
        if (r0 == null) goto L16;
        Runnable r42 = r0.onCancelled;
        if (r42 == null) goto L11;
        r42.run();
    L11:
        r22.requestCallbacks.remove(Integer.valueOf(r3));
        return;
    L16:
        return;
    L13:
        if (r0 == null) goto L17;
        r22.requestCallbacks.remove(Integer.valueOf(r3));
        return;
    L17:
        return;
    }

    public static void onRequestComplete(int r12, int r13, long r14, int r16, String r17, int r18, long r19, long r21, int r23) {
        ConnectionsManager r122 = getInstance(r12);
        if (r122 == null) goto L13;
        RequestCallbacks r0 = r122.requestCallbacks.get(Integer.valueOf(r13));
        r122.requestCallbacks.remove(Integer.valueOf(r13));
        if (r0 == null) goto L11;
        RequestDelegateInternal r1 = r0.onComplete;
        if (r1 == null) goto L12;
        r1.run(r14, r16, r17, r18, r19, r21, r23);
        return;
    L12:
        return;
    L11:
        return;
    }

    public static void onRequestNewServerIpAndPort(int r2, int r3) {
        Utilities.globalQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda24(r2, r3));
    }

    public static void onRequestQuickAck(int r0, int r1) {
        ConnectionsManager r02 = getInstance(r0);
        if (r02 == null) goto L13;
        RequestCallbacks r03 = r02.requestCallbacks.get(Integer.valueOf(r1));
        if (r03 == null) goto L11;
        QuickAckDelegate r04 = r03.onQuickAck;
        if (r04 == null) goto L12;
        r04.run();
        return;
    L12:
        return;
    L11:
        return;
    }

    public static void onRequestWriteToSocket(int r0, int r1) {
        ConnectionsManager r02 = getInstance(r0);
        if (r02 == null) goto L13;
        RequestCallbacks r03 = r02.requestCallbacks.get(Integer.valueOf(r1));
        if (r03 == null) goto L11;
        WriteToSocketDelegate r04 = r03.onWriteToSocket;
        if (r04 == null) goto L12;
        r04.run();
        return;
    L12:
        return;
    L11:
        return;
    }

    public static void onSessionCreated(int r2) {
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda4(r2));
    }

    public static void onUnparsedMessageReceived(long r3, int r5, long r6) {
        NativeByteBuffer r32 = NativeByteBuffer.wrap(r3);     // Catch: Exception -> L14
        r32.setDataSourceType(TLDataSourceType.NETWORK);     // Catch: Exception -> L14
        r32.reused = true;     // Catch: Exception -> L14
        int r1 = r32.readInt32(true);     // Catch: Exception -> L14
        TLObject r33 = TLClassStore.Instance().TLdeserialize(r32, r1, true);     // Catch: Exception -> L14
        FileLog.dumpUnparsedMessage(r33, r6, r5);     // Catch: Exception -> L14
        if ((r33 instanceof TLRPC.Updates) == false) goto L11;
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d("java received " + r33);     // Catch: Exception -> L14
    L8:
        KeepAliveJob.finishJob();     // Catch: Exception -> L14
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda5(r5, r33));     // Catch: Exception -> L14
        return;
    L11:
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        FileLog.d(String.format("java received unknown constructor 0x%x", new Object[]{Integer.valueOf(r1)}));     // Catch: Exception -> L14
        return;
    L18:
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
    }

    public static void onUpdate(int r2) {
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda0(r2));
    }

    public static void onUpdateConfig(long r1, int r3) {
        NativeByteBuffer r12 = NativeByteBuffer.wrap(r1);     // Catch: Exception -> L6
        r12.reused = true;     // Catch: Exception -> L6
        TLRPC.TL_config r13 = TLRPC.TL_config.TLdeserialize(r12, r12.readInt32(true), true);     // Catch: Exception -> L6
        if (r13 == null) goto L11;
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda18(r3, r13));     // Catch: Exception -> L6
        return;
    L11:
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }

    private void sendRequestInternal(TLObject r17, RequestDelegate r18, RequestDelegateTimestamp r19, QuickAckDelegate r20, WriteToSocketDelegate r21, int r22, int r23, int r24, boolean r25, int r26) {
        if (BuildVars.LOGS_ENABLED == false) goto L26;
        FileLog.d("send request " + r17 + " with token = " + r26);
    L26:
        String r14 = r17.getClass().getSimpleName();     // Catch: Exception -> L23
        TLObject r0 = this.hooks.executePreRequestHook(r14, this.currentAccount, r17);     // Catch: Exception -> L23
        if (r0 == null) goto L8;
        TLObject r02 = FeedRequestNormalizer.normalize(this.currentAccount, r0);     // Catch: Exception -> L23
        NativeByteBuffer r15 = new NativeByteBuffer(r02.getObjectSize());     // Catch: Exception -> L23
        r02.serializeToStream(r15);     // Catch: Exception -> L23
        r02.freeResources();     // Catch: Exception -> L23
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L17;
        if (BuildVars.LOGS_ENABLED == false) goto L17;
    L18:
        long r3 = System.currentTimeMillis();     // Catch: Exception -> L23
    L21:
        listen(r26, new ConnectionsManager$$ExternalSyntheticLambda15(this, r02, r24, r3, r18, r19, r20, r21, r22, r23, r25, r26, r14), r20, r21);     // Catch: Exception -> L23
        native_sendRequest(this.currentAccount, r15.address, r22, r23, r24, r25, r26);     // Catch: Exception -> L23
        return;
    L17:
        if ((r24 & 2) != 0) goto L18;
        r3 = 0;
        goto L21
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        FileLog.d("Request cancelled by pre-request hook: ".concat(r14));     // Catch: Exception -> L23
    L10:
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda14(r18, r19));     // Catch: Exception -> L23
        return;
    L23:
        e = move-exception;
        FileLog.e(e);
    }

    public static boolean setAndApplyDebugDnsConfigOverride(int r0, String r1) {
        setDebugDnsConfigOverride(r1);
        return applyStoredDnsConfigOverride(r0);
    }

    public static void setDebugDnsConfigOverride(String r3) {
        SharedPreferences.Editor r0 = getDebugDnsPreferences().edit();
        if (TextUtils.isEmpty(r3) == false) goto L5;
        r0.remove(DEBUG_DNS_CONFIG_KEY);
    L6:
        r0.apply();
        return;
    L5:
        r0.putString(DEBUG_DNS_CONFIG_KEY, r3.trim());
        goto L6
    }

    public static void setLangCode(String r2) {
        String r22 = r2.replace('_', SignatureVisitor.SUPER).toLowerCase();
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        native_setLangCode(r0, r22);
        r0 = r0 + 1;
        goto L4
    }

    public static void setProxySettings(boolean r13, ProxySettings r14) {
        if (r13 == false) goto L16;
        if (r14 == null) goto L16;
        if (r14.isValid() == false) goto L16;
        String r2 = r14.getAddress();
        int r3 = r14.getPort();
        String r4 = r14.getUser();
        String r5 = r14.getPassword();
        String r6 = r14.getSecret();
        if (r14.getType() != ProxySettings.Type.WEB) goto L15;
        int r22 = WebProxyTransport.start(r2, r6);
        if (r22 == 0) goto L12;
    L11:
        int r32 = r22;
        String r10 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r11 = r10;
        String r8 = "127.0.0.1";
        int r9 = r32;
    L14:
        String r12 = r6;
    L17:
        int r23 = 0;
    L19:
        if (r23 >= 16) goto L30;
        if (r13 == false) goto L25;
        if (r14 == null) goto L25;
        if (r14.isValid() == false) goto L25;
        native_setProxySettings(r23, r8, r9, r10, r11, r12);
    L26:
        AccountInstance r0 = AccountInstance.getInstance(r23);
        if (r0.getUserConfig().isClientActivated() == false) goto L29;
        r0.getMessagesController().checkPromoInfo(true);
    L29:
        r23 = r23 + 1;
    L25:
        native_setProxySettings(r23, _UrlKt.FRAGMENT_ENCODE_SET, 1080, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
        goto L26
    L30:
        return;
    L12:
        r22 = 9;
        goto L11
    L15:
        WebProxyTransport.stop();
        r8 = r2;
        r9 = r3;
        r10 = r4;
        r11 = r5;
    L16:
        WebProxyTransport.stop();
        r8 = _UrlKt.FRAGMENT_ENCODE_SET;
        r10 = r8;
        r11 = r10;
        r12 = r11;
        r9 = 0;
        goto L17
    }

    public static void setRegId(String r0, int r1, String r2) {
        if (TextUtils.isEmpty(r0) == true) goto L5;
    L7:
        int r22 = 0;
        if (TextUtils.isEmpty(r0) == false) goto L11;
        r0 = "__FIREBASE_GENERATING_SINCE_" + getInstance(0).getCurrentTime() + "__";
        SharedConfig.pushStringStatus = r0;
    L11:
        if (r22 >= 16) goto L13;
        native_setRegId(r22, r0);
        r22 = r22 + 1;
        goto L11
    L13:
        return;
    L5:
        if (TextUtils.isEmpty(r2) == true) goto L7;
        r0 = r2;
        goto L7
    }

    public static void setSystemLangCode(String r2) {
        String r22 = r2.replace('_', SignatureVisitor.SUPER).toLowerCase();
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        native_setSystemLangCode(r0, r22);
        r0 = r0 + 1;
        goto L4
    }

    public static boolean testNativeTlScheme(NativeByteBuffer r2, INativeTlTest r3) {
        return r3.test(r2.address);
    }

    public void applyDatacenterAddress(int r1, String r2, int r3) {
        native_applyDatacenterAddress(this.currentAccount, r1, r2, r3);
    }

    public void bindRequestToGuid(int r1, int r2) {
        if (r2 != 0) goto L4;
        return;
    L4:
        native_bindRequestToGuid(this.currentAccount, r1, r2);
    }

    public void cancelRequest(int r2, boolean r3) {
        cancelRequest(r2, r3, null);
    }

    public void cancelRequest(int r3, boolean r4, Runnable r5) {
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda12(this, r5, r3, r4));
    }

    public void cancelRequestsForGuid(int r3) {
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda6(this, r3));
    }

    public void checkConnection() {
        byte r0 = getIpStrategy();
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("selected ip strategy " + r0);
    L5:
        native_setIpStrategy(this.currentAccount, r0);
        native_setNetworkAvailable(this.currentAccount, ApplicationLoader.isNetworkOnline(), ApplicationLoader.getCurrentNetworkType(), ApplicationLoader.isConnectionSlow());
    }

    public long checkProxy(ProxySettings r12, RequestTimeDelegate r13) {
        if (r12 != null) goto L5;
    L13:
        return 0;
    L5:
        if (r12.isValid() == false) goto L13;
        if (r12.getType() != ProxySettings.Type.WEB) goto L12;
        WebProxyConnectionTester.getInstance().checkProxy(r12, r13, new ConnectionsManager$$ExternalSyntheticLambda26(this));
        return 0;
    L12:
        return native_checkProxy(this.currentAccount, r12.getAddress(), r12.getPort(), r12.getUser(), r12.getPassword(), r12.getSecret(), r13);
    }

    public void cleanup(boolean r1) {
        native_cleanUp(this.currentAccount, r1);
    }

    public void discardConnection(int r3, int r4) {
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda17(this, r3, r4));
    }

    public void failNotRunningRequest(int r3) {
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda11(this, r3));
    }

    public int getConnectionState() {
        int r0 = this.connectionState;
        if (r0 == 3) goto L5;
    L8:
        return r0;
    L5:
        if (this.isUpdating == false) goto L8;
        return 5;
    }

    public long getCurrentAuthKeyId() {
        return native_getCurrentAuthKeyId(this.currentAccount);
    }

    public int getCurrentDatacenterId() {
        return native_getCurrentDatacenterId(this.currentAccount);
    }

    public int getCurrentTime() {
        return native_getCurrentTime(this.currentAccount);
    }

    public long getCurrentTimeMillis() {
        return native_getCurrentTimeMillis(this.currentAccount);
    }

    @SuppressLint({"NewApi"})
    public byte getIpStrategy() {
        if (BuildVars.LOGS_ENABLED == false) goto L80;
        Enumeration<NetworkInterface> r0 = NetworkInterface.getNetworkInterfaces();     // Catch: Throwable -> L17
    L5:
        if (r0.hasMoreElements() == false) goto L80;
        NetworkInterface r2 = r0.nextElement();     // Catch: Throwable -> L17
        if (r2.isUp() == false) goto L5;
        if (r2.isLoopback() == true) goto L5;
        if (r2.getInterfaceAddresses().isEmpty() == true) goto L5;
        if (BuildVars.LOGS_ENABLED == false) goto L19;
        FileLog.d("valid interface: " + r2);     // Catch: Throwable -> L17
    L19:
        List<InterfaceAddress> r22 = r2.getInterfaceAddresses();     // Catch: Throwable -> L17
        int r3 = 0;
    L21:
        if (r3 >= r22.size()) goto L5;
        InetAddress r4 = r22.get(r3).getAddress();     // Catch: Throwable -> L17
        if (BuildVars.LOGS_ENABLED == false) goto L26;
        FileLog.d("address: " + r4.getHostAddress());     // Catch: Throwable -> L17
    L26:
        if (r4.isLinkLocalAddress() == true) goto L35;
        if (r4.isLoopbackAddress() == true) goto L35;
        if (r4.isMulticastAddress() == true) goto L35;
        if (BuildVars.LOGS_ENABLED == false) goto L35;
        FileLog.d("address is good");     // Catch: Throwable -> L17
    L35:
        r3 = r3 + 1;
    L17:
        th = move-exception;
        FileLog.e(th);
    L80:
        Enumeration<NetworkInterface> r02 = NetworkInterface.getNetworkInterfaces();     // Catch: Throwable -> L64
        boolean r23 = false;
        boolean r32 = false;
        boolean r42 = false;
    L39:
        if (r02.hasMoreElements() == false) goto L67;
        NetworkInterface r5 = r02.nextElement();     // Catch: Throwable -> L64
        if (r5.isUp() == false) goto L39;
        if (r5.isLoopback() == true) goto L39;
        List<InterfaceAddress> r52 = r5.getInterfaceAddresses();     // Catch: Throwable -> L64
        int r7 = 0;
    L47:
        if (r7 >= r52.size()) goto L39;
        InetAddress r8 = r52.get(r7).getAddress();     // Catch: Throwable -> L64
        if (r8.isLinkLocalAddress() == true) goto L66;
        if (r8.isLoopbackAddress() == true) goto L66;
        if (r8.isMulticastAddress() == true) goto L66;
        if ((r8 instanceof Inet6Address) == false) goto L59;
        r23 = true;
        goto L66
    L59:
        if ((r8 instanceof Inet4Address) == false) goto L66;
        if (r8.getHostAddress().startsWith("192.0.0.") == true) goto L63;
        r42 = true;
        goto L66
    L63:
        r32 = true;
    L66:
        r7 = r7 + 1;     // Catch: Throwable -> L64
        goto L47
    L67:
        if (r23 == false) goto L77;
        if (this.forceTryIpV6 == false) goto L71;
        return 1;
    L71:
        if (r32 == false) goto L74;
        return 2;
    L74:
        if (r42 == true) goto L77;
        return 1;
    L77:
        return 0;
    L64:
        th = move-exception;
        FileLog.e(th);
        goto L77
    }

    public long getPauseTime() {
        return this.lastPauseTime;
    }

    public int getTimeDifference() {
        return native_getTimeDifference(this.currentAccount);
    }

    public void init(int r28, int r29, int r30, String r31, String r32, String r33, String r34, String r35, String r36, String r37, String r38, String r39, int r40, long r41, boolean r43, boolean r44) {
        SharedPreferences r2 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        ProxySettings r3 = ProxySettings.fromSharedPreferences(r2);
        if (r2.getBoolean("proxy_enabled", false) == true) goto L5;
    L41:
        Context r22 = ApplicationLoader.applicationContext;     // Catch: Throwable -> L36
        if (Build.VERSION.SDK_INT < 30) goto L23;
        InstallSourceInfo r23 = r22.getPackageManager().getInstallSourceInfo(r22.getPackageName());     // Catch: Throwable -> L36
        if (r23 == null) goto L22;
        String r310 = r23.getInitiatingPackageName();     // Catch: Throwable -> L36
        if (r310 == null) goto L39;
    L24:
        if (r310 != null) goto L26;
        String r17 = _UrlKt.FRAGMENT_ENCODE_SET;
    L37:
        String r24 = ApplicationLoader.applicationContext.getPackageName();     // Catch: Throwable -> L29
    L30:
        if (r24 != null) goto L32;
        String r18 = _UrlKt.FRAGMENT_ENCODE_SET;
    L33:
        native_init(this.currentAccount, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r17, r18, r40, r41, r43, r44, ApplicationLoader.isNetworkOnline(), ApplicationLoader.getCurrentNetworkType(), SharedConfig.measureDevicePerformanceClass());
        checkConnection();
        return;
    L32:
        r18 = r24;
    L29:
        r24 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L30
    L26:
        r17 = r310;
        goto L37
    L39:
        r310 = r23.getInstallingPackageName();     // Catch: Throwable -> L35
        goto L24
    L23:
        r310 = r22.getPackageManager().getInstallerPackageName(r22.getPackageName());     // Catch: Throwable -> L36
    L22:
        r310 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L24
    L5:
        if (r3.isValid() == false) goto L41;
        if (r3.getType() != ProxySettings.Type.WEB) goto L13;
        int r25 = WebProxyTransport.start(r3.getAddress(), r3.getSecret());
        int r4 = this.currentAccount;
        if (r25 == 0) goto L11;
    L10:
        int r6 = r25;
        native_setProxySettings(r4, "127.0.0.1", r6, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, r3.getSecret());
        goto L41
    L11:
        r25 = 9;
        goto L10
    L13:
        native_setProxySettings(this.currentAccount, r3.getAddress(), r3.getPort(), r3.getUser(), r3.getPassword(), r3.getSecret());
        goto L41
    }

    public boolean isPushConnectionEnabled() {
        SharedPreferences r2 = MessagesController.getGlobalNotificationsSettings();
        if (r2.contains("pushConnection") == false) goto L7;
        return r2.getBoolean("pushConnection", true);
    L7:
        return MessagesController.getMainSettings(UserConfig.selectedAccount).getBoolean("backgroundConnection", false);
    }

    public boolean isTestBackend() {
        if (native_isTestBackend(this.currentAccount) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void resumeNetworkMaybe() {
        native_resumeNetwork(this.currentAccount, true);
    }

    public int sendRequest(TLObject r3, RequestDelegate r4) {
        return sendRequest(r3, r4, null, 0);
    }

    public int sendRequest(TLObject r11, RequestDelegate r12, int r13) {
        return sendRequest(r11, r12, null, null, null, r13, Integer.MAX_VALUE, 1, true);
    }

    public int sendRequest(TLObject r11, RequestDelegate r12, int r13, int r14) {
        return sendRequest(r11, r12, null, null, null, r13, Integer.MAX_VALUE, r14, true);
    }

    public int sendRequest(TLObject r11, RequestDelegate r12, QuickAckDelegate r13, int r14) {
        return sendRequest(r11, r12, null, r13, null, r14, Integer.MAX_VALUE, 1, true);
    }

    public int sendRequest(TLObject r11, RequestDelegate r12, QuickAckDelegate r13, WriteToSocketDelegate r14, int r15, int r16, int r17, boolean r18) {
        return sendRequest(r11, r12, null, r13, r14, r15, r16, r17, r18);
    }

    public int sendRequest(TLObject r14, RequestDelegate r15, RequestDelegateTimestamp r16, QuickAckDelegate r17, WriteToSocketDelegate r18, int r19, int r20, int r21, boolean r22) {
        int r12 = this.lastRequestToken.getAndIncrement();
        Utilities.stageQueue.postRunnable(new ConnectionsManager$$ExternalSyntheticLambda21(this, r14, r15, r16, r17, r18, r19, r20, r21, r22, r12));
        return r12;
    }

    public int sendRequest(TLObject r11, RequestDelegateTimestamp r12, int r13, int r14, int r15) {
        return sendRequest(r11, null, r12, null, null, r13, r15, r14, true);
    }

    public int sendRequestSync(TLObject r12, RequestDelegate r13, QuickAckDelegate r14, WriteToSocketDelegate r15, int r16, int r17, int r18, boolean r19) {
        int r10 = this.lastRequestToken.getAndIncrement();
        sendRequestInternal(r12, r13, null, r14, r15, r16, r17, r18, r19, r10);
        return r10;
    }

    public <T extends TLObject> int sendRequestTyped(TLMethod<T> r7, Executor r8, Utilities.Callback2<T, TLRPC.TL_error> r9) {
        return sendRequestTyped(r7, r8, r9, Integer.MAX_VALUE, 0);
    }

    public <T extends TLObject> int sendRequestTyped(TLMethod<T> r7, Executor r8, Utilities.Callback2<T, TLRPC.TL_error> r9, int r10) {
        return sendRequestTyped(r7, r8, r9, Integer.MAX_VALUE, r10);
    }

    public <T extends TLObject> int sendRequestTyped(TLMethod<T> r11, Executor r12, Utilities.Callback2<T, TLRPC.TL_error> r13, int r14, int r15) {
        return sendRequest(r11, new ConnectionsManager$$ExternalSyntheticLambda7(r12, r13), null, null, null, r15, r14, 1, true);
    }

    public <T extends TLObject> int sendRequestTyped(TLMethod<T> r2, Utilities.Callback2<T, TLRPC.TL_error> r3) {
        return sendRequestTyped(r2, null, r3);
    }

    public int sendRequestTypedAndProcessUpdates(TLMethod<TLRPC.Updates> r7, Executor r8, Utilities.Callback2<TLRPC.Updates, TLRPC.TL_error> r9) {
        return sendRequestTypedAndProcessUpdates(r7, r8, r9, Integer.MAX_VALUE, 0);
    }

    public int sendRequestTypedAndProcessUpdates(TLMethod<TLRPC.Updates> r2, Executor r3, Utilities.Callback2<TLRPC.Updates, TLRPC.TL_error> r4, int r5, int r6) {
        return sendRequestTyped(r2, null, new ConnectionsManager$$ExternalSyntheticLambda19(this, r3, r4), r5, r6);
    }

    public void setAppPaused(boolean r6, boolean r7) {
        if (r7 == true) goto L18;
        this.appPaused = r6;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("app paused = " + r6);
    L7:
        int r72 = this.appResumeCount;
        if (r6 == false) goto L10;
        this.appResumeCount = r72 - 1;
    L12:
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.d("app resume count " + this.appResumeCount);
    L15:
        if (this.appResumeCount >= 0) goto L18;
        this.appResumeCount = 0;
        goto L18
    L10:
        this.appResumeCount = r72 + 1;
    L18:
        if (this.appResumeCount != 0) goto L25;
        if (this.lastPauseTime != 0) goto L22;
        this.lastPauseTime = System.currentTimeMillis();
    L22:
        native_pauseNetwork(this.currentAccount);
        return;
    L25:
        if (this.appPaused == false) goto L28;
        return;
    L28:
        if (BuildVars.LOGS_ENABLED == false) goto L31;
        FileLog.d("reset app pause time");
    L31:
        if (this.lastPauseTime != 0) goto L33;
    L35:
        this.lastPauseTime = 0;
        native_resumeNetwork(this.currentAccount, false);
        return;
    L33:
        if ((System.currentTimeMillis() - this.lastPauseTime) <= 5000) goto L35;
        getContactsController().checkContacts();
        goto L35
    }

    public void setDefaultDatacenterId(int r1) {
        native_moveDatacenter(this.currentAccount, r1);
    }

    public void setForceTryIpV6(boolean r2) {
        if (this.forceTryIpV6 == r2) goto L6;
        this.forceTryIpV6 = r2;
        checkConnection();
        return;
    }

    public void setIsUpdating(boolean r2) {
        AndroidUtilities.runOnUIThread(new ConnectionsManager$$ExternalSyntheticLambda20(this, r2));
    }

    public void setPushConnectionEnabled(boolean r1) {
        native_setPushConnectionEnabled(this.currentAccount, r1);
    }

    public void setUserId(long r1) {
        native_setUserId(this.currentAccount, r1);
    }

    public void switchBackend(boolean r3) {
        MessagesController.getGlobalMainSettings().edit().remove("language_showed2").apply();
        native_switchBackend(this.currentAccount, r3);
    }

    public void updateDcSettings() {
        native_updateDcSettings(this.currentAccount);
    }
}
