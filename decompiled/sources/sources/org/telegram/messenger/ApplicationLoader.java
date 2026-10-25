package org.telegram.messenger;

import a.b;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import androidx.work.Configuration;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ProxyDisableCondition;
import com.exteragram.messenger.appicons.AppIconController;
import com.exteragram.messenger.debug.FreezeMonitor;
import com.exteragram.messenger.debug.HeapMonitor;
import com.exteragram.messenger.debug.LoadMonitor;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.utils.NativeCrashHandler;
import com.exteragram.messenger.updater.UpdateAppAlertDialog;
import com.exteragram.messenger.updater.UpdateLayout;
import com.exteragram.messenger.updater.UpdaterUtils;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dev.exterahook.runtime.bridge.HookBridgeProvider;
import java.io.File;
import java.lang.Thread;
import java.util.Iterator;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.PushListenerController;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ForegroundDetector;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.IUpdateLayout;
import org.telegram.utils.proxy.ProxySettings;

/* loaded from: classes.dex */
public class ApplicationLoader extends Application implements Configuration.Provider {
    private static final String PREF_PROXY_AUTO_DISABLED = "proxy_auto_disabled_by_vpn";

    @SuppressLint({"StaticFieldLeak"})
    public static volatile Context applicationContext = null;
    public static volatile Handler applicationHandler = null;
    private static volatile boolean applicationInited = false;
    public static ApplicationLoader applicationLoaderInstance = null;
    public static boolean canDrawOverlays = false;
    private static ConnectivityManager connectivityManager = null;
    public static volatile NetworkInfo currentNetworkInfo = null;
    private static volatile boolean exteraHookEmbeddedConfigured = false;
    public static volatile boolean externalInterfacePaused = true;
    private static FirebaseAnalytics firebaseAnalytics = null;
    private static FirebaseCrashlytics firebaseCrashlytics = null;
    public static volatile boolean isScreenOn = false;
    private static int lastKnownNetworkType = -1;
    private static long lastNetworkCheck = -1;
    private static long lastNetworkCheckTypeTime = 0;
    private static ILocationServiceProvider locationServiceProvider = null;
    public static volatile boolean mainInterfacePaused = true;
    public static volatile boolean mainInterfacePausedStageQueue = true;
    public static volatile long mainInterfacePausedStageQueueTime = 0;
    public static volatile boolean mainInterfaceStopped = true;
    private static IMapsProvider mapsProvider;
    private static volatile ConnectivityManager.NetworkCallback networkCallback;
    private static PushListenerController.IPushListenerServiceProvider pushProvider;
    public static long startTime;
    private final Runnable debugEverySecondChecks;

    /* renamed from: org.telegram.messenger.ApplicationLoader$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        public AnonymousClass1() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            ApplicationLoader.currentNetworkInfo = ApplicationLoader.m3887$$Nest$sfgetconnectivityManager().getActiveNetworkInfo();     // Catch: Throwable -> L8
        L3:
            ApplicationLoader.checkProxyForNetworkState();
            boolean r0 = ApplicationLoader.isConnectionSlow();
            int r12 = 0;
        L5:
            if (r12 >= 16) goto L7;
            ConnectionsManager.getInstance(r12).checkConnection();
            FileLoader.getInstance(r12).onNetworkChanged(r0);
            r12 = r12 + 1;
            goto L5
        }
    }

    /* renamed from: org.telegram.messenger.ApplicationLoader$2, reason: invalid class name */
    public class AnonymousClass2 extends ForegroundDetector {
        final /* synthetic */ ApplicationLoader this$0;

        public AnonymousClass2(ApplicationLoader r1, Application r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ForegroundDetector, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity r2) {
            boolean r0 = isBackground();
            super.onActivityStarted(r2);
            if (r0 == false) goto L6;
            ApplicationLoader.m3889$$Nest$smensureCurrentNetworkGet(true);
            return;
        }
    }

    /* renamed from: org.telegram.messenger.ApplicationLoader$3, reason: invalid class name */
    public class AnonymousClass3 extends ConnectivityManager.NetworkCallback {
        public AnonymousClass3() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network r1) {
            ApplicationLoader.m3888$$Nest$sfputlastKnownNetworkType(-1);
            ApplicationLoader.checkProxyForNetworkState();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network r1, NetworkCapabilities r2) {
            ApplicationLoader.m3888$$Nest$sfputlastKnownNetworkType(-1);
            ApplicationLoader.checkProxyForNetworkState();
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network r1) {
            ApplicationLoader.m3888$$Nest$sfputlastKnownNetworkType(-1);
            ApplicationLoader.checkProxyForNetworkState();
        }
    }

    public static /* synthetic */ void $r8$lambda$60kBNSRsGKm6hKC56PPkkqhDIgg() {
        if (getPushProvider().hasServices() == false) goto L7;
        getPushProvider().onRequestPushToken();
        return;
    L7:
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        FileLog.d("No valid " + getPushProvider().getLogTitle() + " APK found.");
    L9:
        SharedConfig.pushStringStatus = "__NO_GOOGLE_PLAY_SERVICES__";
        PushListenerController.sendRegistrationToServer(getPushProvider().getPushType(), null);
    }

    public static /* synthetic */ void $r8$lambda$A8bx0TIbbUp9lwRY3zA5jD82x2o() {
        NotificationCenter.sanitize();
    }

    public static /* synthetic */ void $r8$lambda$AVsx0zaH_CcvPxMfM110FSkszWQ() {
        AndroidUtilities.runOnUIThread(new ApplicationLoader$$ExternalSyntheticLambda2());
    }

    public static /* synthetic */ void $r8$lambda$NY8LiAdXEiNm7oQtKqIv0_Zxl0c(Thread.UncaughtExceptionHandler r6, Thread r7, Throwable r8) {
        String r0 = Log.getStackTraceString(r8);
    L11:
        e = move-exception;
        FileLog.e(e);
    L18:
        FileLog.e(r0);
        if (r6 == null) goto L29;
        r6.uncaughtException(r7, r8);
        return;
    L29:
        return;
    L4:
        if (ExteraConfig.getPluginsEngine() == false) goto L15;
        SharedPreferences.Editor r1 = PluginsController.getInstance().getPreferences().edit();     // Catch: Exception -> L11
        r1.putBoolean("had_crash", true).apply();     // Catch: Exception -> L11
        Iterator<String> r2 = PluginsController.getInstance().getPlugins().keySet().iterator();     // Catch: Exception -> L11
        String r3 = null;
    L7:
        if (r2.hasNext() == false) goto L13;
        String r4 = r2.next();     // Catch: Exception -> L11
        if (r0.contains(r4) == false) goto L7;
        r3 = r4;
        goto L7
    L13:
        if (r3 == null) goto L15;
        r1.putString("crashed_plugin_id", r3).apply();     // Catch: Exception -> L11
        FileLog.e("Plugin crash detected for plugin: " + r3);     // Catch: Exception -> L11
    L15:
        ((ClipboardManager) applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", r0));     // Catch: Exception -> L11
        goto L18
    }

    public static /* synthetic */ void $r8$lambda$zO3ZcwENG7ZE5pVo8byxytV4NFU(ApplicationLoader r0) {
        r0.lambda$initFirebase$3();
    }

    /* renamed from: -$$Nest$sfgetconnectivityManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConnectivityManager m3887$$Nest$sfgetconnectivityManager() {
        return connectivityManager;
    }

    /* renamed from: -$$Nest$sfputlastKnownNetworkType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3888$$Nest$sfputlastKnownNetworkType(int r0) {
        lastKnownNetworkType = r0;
    }

    /* renamed from: -$$Nest$smensureCurrentNetworkGet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3889$$Nest$smensureCurrentNetworkGet(boolean r0) {
        ensureCurrentNetworkGet(r0);
    }

    static {
    }

    public ApplicationLoader() {
        this.debugEverySecondChecks = new ApplicationLoader$$ExternalSyntheticLambda0();
    }

    private boolean checkPlayServices() {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) != 0) goto L6;
        return true;
    L6:
        return false;
    L8:
        e = move-exception;
        FileLog.e(e);
        return true;
    }

    public static void checkProxyForNetworkState() {
        if (applicationContext == null) goto L45;
        SharedPreferences r1 = MessagesController.getGlobalMainSettings();     // Catch: Throwable -> L36
        boolean r3 = r1.getBoolean(PREF_PROXY_AUTO_DISABLED, false);     // Catch: Throwable -> L36
        if (ExteraConfig.isProxyDisabledOn(ProxyDisableCondition.VPN) == false) goto L11;
        if (isVpnEnabled() == false) goto L11;
    L18:
        if (r3 == true) goto L40;
        boolean r32 = r1.getBoolean("proxy_enabled", false);     // Catch: Throwable -> L36
        String r4 = r1.getString("proxy_ip", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L36
        if (r32 == true) goto L23;
        return;
    L23:
        if (TextUtils.isEmpty(r4) == true) goto L42;
        r1.edit().putBoolean(PREF_PROXY_AUTO_DISABLED, true).putBoolean("proxy_enabled", false).apply();     // Catch: Throwable -> L36
        ConnectionsManager.setProxySettings(false, null);     // Catch: Throwable -> L36
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.proxySettingsChanged, new Object[0]);     // Catch: Throwable -> L36
        return;
    L42:
        return;
    L40:
        return;
    L11:
        if (ExteraConfig.isProxyDisabledOn(ProxyDisableCondition.MOBILE_DATA) == false) goto L15;
        if (hasTransport(0) == true) goto L18;
    L15:
        if (ExteraConfig.isProxyDisabledOn(ProxyDisableCondition.WIFI) == true) goto L17;
    L27:
        if (r3 == false) goto L43;
        String r33 = r1.getString("proxy_ip", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L36
        SharedPreferences.Editor r0 = r1.edit().putBoolean(PREF_PROXY_AUTO_DISABLED, false);     // Catch: Throwable -> L36
        if (TextUtils.isEmpty(r33) == true) goto L32;
        r0.putBoolean("proxy_enabled", true);     // Catch: Throwable -> L36
    L32:
        r0.apply();     // Catch: Throwable -> L36
        if (TextUtils.isEmpty(r33) == true) goto L44;
        ConnectionsManager.setProxySettings(true, ProxySettings.fromSharedPreferences(r1));     // Catch: Throwable -> L36
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.proxySettingsChanged, new Object[0]);     // Catch: Throwable -> L36
        return;
    L44:
        return;
    L43:
        return;
    L17:
        if (hasTransport(1) == false) goto L27;
    L36:
        th = move-exception;
        FileLog.e(th);
        return;
    }

    private static void configureEmbeddedExteraHook() {
        if (exteraHookEmbeddedConfigured == false) goto L6;
        return;
    L6:
        monitor-enter(ApplicationLoader.class);
    L11:
        th = move-exception;
        throw th;
    L8:
        if (exteraHookEmbeddedConfigured == false) goto L21;
        monitor-exit(ApplicationLoader.class);     // Catch: Throwable -> L11
        return;
    L21:
        b r2 = HookBridgeProvider.Companion;     // Catch: Throwable -> L16
        HookBridgeProvider.class.getMethod("useEmbeddedAsDefault", null).invoke(null, null);     // Catch: Throwable -> L16
        exteraHookEmbeddedConfigured = true;     // Catch: Throwable -> L16
        monitor-exit(ApplicationLoader.class);     // Catch: Throwable -> L11
        return;
    L16:
        th = move-exception;
        throw new RuntimeException("Failed to configure embedded exteraHook runtime", th);     // Catch: Throwable -> L11
    }

    private static void ensureCurrentNetworkGet() {
        long r0 = System.currentTimeMillis();
        if ((r0 - lastNetworkCheck) <= 5000) goto L5;
        boolean r2 = true;
    L6:
        ensureCurrentNetworkGet(r2);
        lastNetworkCheck = r0;
        return;
    L5:
        r2 = false;
        goto L6
    }

    private static void ensureCurrentNetworkGet(boolean r1) {
        if (r1 == false) goto L4;
    L13:
        if (connectivityManager != null) goto L8;
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");     // Catch: Throwable -> L12
    L8:
        currentNetworkInfo = connectivityManager.getActiveNetworkInfo();     // Catch: Throwable -> L12
        if (networkCallback != null) goto L16;
        networkCallback = new AnonymousClass3();     // Catch: Throwable -> L12
        connectivityManager.registerDefaultNetworkCallback(networkCallback);     // Catch: Throwable -> L12
        return;
    L16:
        return;
    L17:
        return;
    L4:
        if (currentNetworkInfo == null) goto L13;
    }

    public static String getApplicationId() {
        return BuildConfig.APPLICATION_ID;
    }

    public static int getAutodownloadNetworkType() {
        ensureCurrentNetworkGet(false);     // Catch: Exception -> L15
        if (currentNetworkInfo != null) goto L7;
        return 0;
    L7:
        if (currentNetworkInfo.getType() != 1) goto L9;
    L17:
        int r1 = lastKnownNetworkType;     // Catch: Exception -> L15
        if (r1 == 0) goto L21;
        if (r1 == 1) goto L21;
    L25:
        if (connectivityManager.isActiveNetworkMetered() == false) goto L27;
        lastKnownNetworkType = 0;     // Catch: Exception -> L15
    L28:
        lastNetworkCheckTypeTime = System.currentTimeMillis();     // Catch: Exception -> L15
        return lastKnownNetworkType;
    L27:
        lastKnownNetworkType = 1;     // Catch: Exception -> L15
    L21:
        if ((System.currentTimeMillis() - lastNetworkCheckTypeTime) >= 5000) goto L25;
        return lastKnownNetworkType;
    L9:
        if (currentNetworkInfo.getType() == 9) goto L17;
        if (currentNetworkInfo.isRoaming() == false) goto L31;
        return 2;
    L31:
        return 0;
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L31
    }

    public static int getCurrentNetworkType() {
        if (isConnectedOrConnectingToWiFi() == false) goto L7;
        return 1;
    L7:
        if (isRoaming() == false) goto L10;
        return 2;
    L10:
        return 0;
    }

    public static File getFilesDirFixed() {
        int r0 = 0;
    L4:
        if (r0 >= 10) goto L14;
        File r1 = applicationContext.getFilesDir();
        if (r1 != null) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L14:
        File r12 = new File(applicationContext.getApplicationInfo().dataDir, "files");     // Catch: Exception -> L11
        r12.mkdirs();     // Catch: Exception -> L11
        return r12;
    L11:
        e = move-exception;
        FileLog.e(e);
        return new File("/data/data/com.exteragram.messenger/files");
    }

    public static File getFilesDirFixed(String r2) {
        File r1 = new File(getFilesDirFixed(), r2);     // Catch: Exception -> L4
        r1.mkdirs();     // Catch: Exception -> L4
        return r1;
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static FirebaseAnalytics getFirebaseAnalytics() {
        return firebaseAnalytics;
    }

    public static FirebaseCrashlytics getFirebaseCrashlytics() {
        return firebaseCrashlytics;
    }

    public static ILocationServiceProvider getLocationServiceProvider() {
        if (locationServiceProvider != null) goto L6;
        ILocationServiceProvider r0 = applicationLoaderInstance.onCreateLocationServiceProvider();
        locationServiceProvider = r0;
        r0.init(applicationContext);
    L6:
        return locationServiceProvider;
    }

    public static IMapsProvider getMapsProvider() {
        if (mapsProvider != null) goto L6;
        mapsProvider = applicationLoaderInstance.onCreateMapsProvider();
    L6:
        return mapsProvider;
    }

    public static PushListenerController.IPushListenerServiceProvider getPushProvider() {
        if (pushProvider != null) goto L6;
        pushProvider = applicationLoaderInstance.onCreatePushProvider();
    L6:
        return pushProvider;
    }

    private static boolean hasTransport(int r3) {
        if (connectivityManager != null) goto L6;
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");     // Catch: Throwable -> L19
    L6:
        ConnectivityManager r1 = connectivityManager;     // Catch: Throwable -> L19
        if (r1 != null) goto L9;
        return false;
    L9:
        Network r12 = r1.getActiveNetwork();     // Catch: Throwable -> L19
        if (r12 != null) goto L12;
        return false;
    L12:
        NetworkCapabilities r13 = connectivityManager.getNetworkCapabilities(r12);     // Catch: Throwable -> L19
        if (r13 == null) goto L18;
        if (r13.hasTransport(r3) == false) goto L18;
        return true;
    L18:
        return false;
    }

    private void initFirebase() {
        AndroidUtilities.runOnUIThread(new ApplicationLoader$$ExternalSyntheticLambda1(this));
    }

    private void initPushServices() {
        AndroidUtilities.runOnUIThread(new ApplicationLoader$$ExternalSyntheticLambda5(), 1000);
    }

    public static boolean isAndroidTestEnvironment() {
        return false;
    }

    public static boolean isConnectedOrConnectingToWiFi() {
        ensureCurrentNetworkGet(false);     // Catch: Exception -> L10
        if (currentNetworkInfo != null) goto L6;
    L20:
        return false;
    L6:
        if (currentNetworkInfo.getType() != 1) goto L8;
    L12:
        NetworkInfo.State r1 = currentNetworkInfo.getState();     // Catch: Exception -> L10
        if (r1 != NetworkInfo.State.CONNECTED) goto L15;
    L18:
        return true;
    L15:
        if (r1 == NetworkInfo.State.CONNECTING) goto L18;
        if (r1 != NetworkInfo.State.SUSPENDED) goto L20;
    L8:
        if (currentNetworkInfo.getType() != 9) goto L20;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L20
    }

    public static boolean isConnectedToWiFi() {
        ensureCurrentNetworkGet(false);     // Catch: Exception -> L10
        if (currentNetworkInfo != null) goto L6;
    L16:
        return false;
    L6:
        if (currentNetworkInfo.getType() != 1) goto L8;
    L12:
        if (currentNetworkInfo.getState() != NetworkInfo.State.CONNECTED) goto L16;
        return true;
    L8:
        if (currentNetworkInfo.getType() != 9) goto L16;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L16
    }

    public static boolean isConnectionSlow() {
        ensureCurrentNetworkGet(false);     // Catch: Throwable -> L21
        if (currentNetworkInfo == null) goto L20;
        if (currentNetworkInfo.getType() != 0) goto L20;
        int r1 = currentNetworkInfo.getSubtype();     // Catch: Throwable -> L21
        if (r1 != 1) goto L11;
    L19:
        return true;
    L11:
        if (r1 == 2) goto L19;
        if (r1 == 4) goto L19;
        if (r1 == 7) goto L19;
        if (r1 == 11) goto L19;
    L20:
        return false;
    }

    public static boolean isNetworkOnline() {
        boolean r0 = isNetworkOnlineRealtime();
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
    L7:
        return r0;
    L5:
        if (r0 == isNetworkOnlineFast()) goto L7;
        FileLog.d("network online mismatch");
        goto L7
    }

    public static boolean isNetworkOnlineFast() {
        ensureCurrentNetworkGet(false);     // Catch: Exception -> L16
        if (currentNetworkInfo != null) goto L7;
        return true;
    L7:
        if (currentNetworkInfo.isConnectedOrConnecting() == false) goto L9;
    L24:
        return true;
    L9:
        if (currentNetworkInfo.isAvailable() == true) goto L24;
        NetworkInfo r2 = connectivityManager.getNetworkInfo(0);     // Catch: Exception -> L16
        if (r2 != null) goto L14;
    L18:
        NetworkInfo r22 = connectivityManager.getNetworkInfo(1);     // Catch: Exception -> L16
        if (r22 == null) goto L23;
        if (r22.isConnectedOrConnecting() == false) goto L23;
        return true;
    L23:
        return false;
    L14:
        if (r2.isConnectedOrConnecting() == false) goto L18;
        return true;
    L16:
        e = move-exception;
        FileLog.e(e);
        return true;
    }

    public static boolean isNetworkOnlineRealtime() {
        ConnectivityManager r1 = (ConnectivityManager) applicationContext.getSystemService("connectivity");     // Catch: Exception -> L10
        NetworkInfo r2 = r1.getActiveNetworkInfo();     // Catch: Exception -> L10
        if (r2 != null) goto L6;
    L13:
        NetworkInfo r3 = r1.getNetworkInfo(0);     // Catch: Exception -> L10
        if (r3 != null) goto L16;
    L18:
        NetworkInfo r12 = r1.getNetworkInfo(1);     // Catch: Exception -> L10
        if (r12 == null) goto L23;
        if (r12.isConnectedOrConnecting() == false) goto L23;
        return true;
    L23:
        return false;
    L16:
        if (r3.isConnectedOrConnecting() == false) goto L18;
        return true;
    L6:
        if (r2.isConnectedOrConnecting() == false) goto L8;
    L12:
        return true;
    L8:
        if (r2.isAvailable() == false) goto L13;
    L10:
        e = move-exception;
        FileLog.e(e);
        return true;
    }

    public static boolean isRoaming() {
        ensureCurrentNetworkGet(false);     // Catch: Exception -> L9
        if (currentNetworkInfo == null) goto L11;
        if (currentNetworkInfo.isRoaming() == false) goto L11;
        return true;
    L11:
        return false;
    L9:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    private static boolean isVpnEnabled() {
        if (connectivityManager != null) goto L6;
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");     // Catch: Throwable -> L23
    L6:
        ConnectivityManager r1 = connectivityManager;     // Catch: Throwable -> L23
        if (r1 != null) goto L9;
        return false;
    L9:
        Network r12 = r1.getActiveNetwork();     // Catch: Throwable -> L23
        if (r12 == null) goto L16;
        NetworkCapabilities r13 = connectivityManager.getNetworkCapabilities(r12);     // Catch: Throwable -> L23
        if (r13 == null) goto L16;
        if (r13.hasTransport(4) == false) goto L16;
        return true;
    L16:
        ensureCurrentNetworkGet(false);     // Catch: Throwable -> L23
        if (currentNetworkInfo == null) goto L22;
        if (currentNetworkInfo.getType() != 17) goto L22;
        return true;
    L22:
        return false;
    }

    private /* synthetic */ void lambda$initFirebase$3() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        FirebaseCrashlytics r2 = FirebaseCrashlytics.getInstance();
        firebaseCrashlytics = r2;
        if (BuildVars.IS_LITE_VERSION == false) goto L5;
        String r0 = "lite";
    L6:
        r2.setCustomKey("app_flavor", r0);
        firebaseAnalytics.setAnalyticsCollectionEnabled(ExteraConfig.getUseGoogleAnalytics());
        firebaseCrashlytics.setCrashlyticsCollectionEnabled(ExteraConfig.getUseGoogleCrashlytics());
        return;
    L5:
        r0 = BuildConfig.FLAVOR_appType;
        goto L6
    }

    public static void postInitApplication() {
        if (applicationInited == false) goto L5;
        return;
    L5:
        if (applicationContext == null) goto L59;
        applicationInited = true;
        NativeLoader.initNativeLibs(applicationContext);
        LocaleController.getInstance();     // Catch: Exception -> L10
    L12:
        int r3 = 0;
        IconManager.INSTANCE.initialize(false);
        connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");     // Catch: Exception -> L15
        applicationContext.registerReceiver(new AnonymousClass1(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));     // Catch: Exception -> L15
    L46:
        IntentFilter r2 = new IntentFilter("android.intent.action.SCREEN_ON");     // Catch: Exception -> L19
        r2.addAction("android.intent.action.SCREEN_OFF");     // Catch: Exception -> L19
        applicationContext.registerReceiver(new ScreenReceiver(), r2);     // Catch: Exception -> L19
    L50:
        isScreenOn = ((PowerManager) applicationContext.getSystemService("power")).isScreenOn();     // Catch: Exception -> L25
        if (BuildVars.LOGS_ENABLED == false) goto L27;
        FileLog.d("screen state = " + isScreenOn);     // Catch: Exception -> L25
    L27:
        SharedConfig.loadConfig();
        SharedPrefsHelper.init(applicationContext);
        int r0 = 0;
    L29:
        if (r0 >= 16) goto L38;
        UserConfig.getInstance(r0).loadConfig();
        MessagesController.getInstance(r0);
        if (r0 != 0) goto L33;
        SharedConfig.pushStringStatus = "__FIREBASE_GENERATING_SINCE_" + ConnectionsManager.getInstance(r0).getCurrentTime() + "__";
    L34:
        TLRPC.User r22 = UserConfig.getInstance(r0).getCurrentUser();
        if (r22 == null) goto L37;
        MessagesController.getInstance(r0).putUser(r22, true);
        SendMessagesHelper.getInstance(r0).checkUnsentMessages();
    L37:
        r0 = r0 + 1;
        goto L29
    L33:
        ConnectionsManager.getInstance(r0);
        goto L34
    L38:
        checkProxyForNetworkState();
        ((ApplicationLoader) applicationContext).initPushServices();
        if (BuildVars.LOGS_ENABLED == false) goto L41;
        FileLog.d("app initied");
    L41:
        MediaController.getInstance();
    L42:
        if (r3 >= 16) goto L44;
        ContactsController.getInstance(r3).checkAppAccount();
        DownloadController.getInstance(r3);
        r3 = r3 + 1;
        goto L42
    L44:
        BillingController.getInstance().startConnection();
        return;
    L25:
        e = move-exception;
        e.printStackTrace();
    L19:
        e = move-exception;
        e.printStackTrace();
    L15:
        e = move-exception;
        e.printStackTrace();
    L10:
        e = move-exception;
        e.printStackTrace();
        goto L12
    }

    public static void startPushService() {
        SharedPreferences r0 = MessagesController.getGlobalNotificationsSettings();
        if (r0.contains("pushService") == false) goto L5;
        boolean r02 = r0.getBoolean("pushService", true);
    L7:
        if (r02 == true) goto L13;
        applicationContext.stopService(new Intent(applicationContext, NotificationsService.class));
        return;
    L13:
        applicationContext.startService(new Intent(applicationContext, NotificationsService.class));     // Catch: Throwable -> L12
        return;
    L15:
        return;
    L5:
        r02 = MessagesController.getMainSettings(UserConfig.selectedAccount).getBoolean("keepAliveService", false);
        goto L7
    }

    public void addItemOptions(ItemOptions r1) {
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context r1) {
        super.attachBaseContext(r1);
    }

    public void cancelDownloadingUpdate() {
    }

    public boolean checkApkInstallPermissions(Context r2) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return true;
    L5:
        if (applicationContext.getPackageManager().canRequestPackageInstalls() == true) goto L10;
        AlertsCreator.createApkRestrictedDialog(r2, null).show();
        return false;
    L10:
        return true;
    }

    public boolean checkRequestPermissionResult(int r1, String[] r2, int[] r3) {
        return false;
    }

    public void checkUpdate(boolean r1, Runnable r2) {
    }

    public boolean consumePush(int r1, JSONObject r2) {
        return false;
    }

    public void downloadUpdate() {
    }

    public File getDownloadedUpdateFile() {
        return null;
    }

    public float getDownloadingUpdateProgress() {
        return 0.0f;
    }

    public BetaUpdate getUpdate() {
        return null;
    }

    @Override // androidx.work.Configuration.Provider
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder().build();
    }

    public boolean isAndroidTestEnv() {
        return false;
    }

    public boolean isBeta() {
        return false;
    }

    public boolean isCustomUpdate() {
        return false;
    }

    public boolean isDownloadingUpdate() {
        return false;
    }

    public boolean isStandalone() {
        return false;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(android.content.res.Configuration r1) {
        super.onConfigurationChanged(r1);
        LocaleController.getInstance().onDeviceConfigurationChange(r1);     // Catch: Exception -> L5
        AndroidUtilities.checkDisplaySize(applicationContext, r1);     // Catch: Exception -> L5
        VideoCapturerDevice.checkScreenCapturerSize();     // Catch: Exception -> L5
        AndroidUtilities.resetTabletFlag();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        e.printStackTrace();
    }

    @Override // android.app.Application
    public void onCreate() {
        applicationLoaderInstance = this;
        applicationContext = getApplicationContext();     // Catch: Throwable -> L37
    L4:
        super.onCreate();
        String r3 = AndroidUtilities.getHelloWorld();
        if (BuildVars.LOGS_ENABLED == false) goto L21;
        FileLog.d(r3);
        StringBuilder r32 = new StringBuilder("app start time = ");
        long r6 = SystemClock.elapsedRealtime();
        startTime = r6;
        r32.append(r6);
        FileLog.d(r32.toString());
        PackageInfo r33 = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);     // Catch: Exception -> L13
        int r4 = r33.versionCode % 10;     // Catch: Exception -> L13
        if (r4 != 1) goto L11;
    L15:
        String r1 = "store bundled " + Build.CPU_ABI + " " + Build.CPU_ABI2;     // Catch: Exception -> L13
    L16:
        FileLog.d("buildVersion = ".concat(String.format(Locale.US, "v%s (%d[%d]) %s", new Object[]{r33.versionName, Integer.valueOf(r33.versionCode / 10), Integer.valueOf(r33.versionCode % 10), r1})));     // Catch: Exception -> L13
    L19:
        FileLog.d("device = manufacturer=" + Build.MANUFACTURER + ", device=" + Build.DEVICE + ", model=" + Build.MODEL + ", product=" + Build.PRODUCT);
        goto L21
    L11:
        if (r4 == 2) goto L15;
        r1 = "universal " + Build.CPU_ABI + " " + Build.CPU_ABI2;     // Catch: Exception -> L13
    L13:
        e = move-exception;
        FileLog.e(e);
    L21:
        if (applicationContext != null) goto L23;
        applicationContext = getApplicationContext();
    L23:
        NativeLoader.initNativeLibs(applicationContext);
        NativeCrashHandler.init(NativeCrashHandler.getCrashFlagPath());
        ConnectionsManager.native_setJava(false);     // Catch: UnsatisfiedLinkError -> L35
        configureEmbeddedExteraHook();
        PluginsController.applyArtOpts();
        new AnonymousClass2(this, this);
        if (BuildVars.LOGS_ENABLED == false) goto L29;
        FileLog.d("load libs time = " + (SystemClock.elapsedRealtime() - startTime));
    L29:
        if (BuildVars.DEBUG_VERSION == false) goto L31;
    L32:
        Thread.setDefaultUncaughtExceptionHandler(new ApplicationLoader$$ExternalSyntheticLambda3(Thread.getDefaultUncaughtExceptionHandler()));
    L33:
        applicationHandler = new Handler(applicationContext.getMainLooper());
        RemoteUtils.initCached();
        AndroidUtilities.runOnUIThread(new ApplicationLoader$$ExternalSyntheticLambda4());
        AppIconController.fixLauncherIconIfNeeded();
        ProxyRotationController.init();
        ProxyPingController.init();
        HeapMonitor.init();
        LoadMonitor.init();
        FreezeMonitor.init();
        ((ApplicationLoader) applicationContext).initFirebase();
        return;
    L31:
        if (ExteraConfig.getPluginsEngine() == false) goto L33;
    L35:
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("can't load native libraries ", Build.CPU_ABI, " lookup folder ", NativeLoader.getAbiFolder());
    }

    public ILocationServiceProvider onCreateLocationServiceProvider() {
        return new GoogleLocationProvider();
    }

    public IMapsProvider onCreateMapsProvider() {
        return new GoogleMapsProvider();
    }

    public PushListenerController.IPushListenerServiceProvider onCreatePushProvider() {
        return PushListenerController.GooglePushListenerServiceProvider.INSTANCE;
    }

    public boolean onPause() {
        return false;
    }

    public void onResume() {
    }

    public boolean onSuggestionClick(String r1) {
        return false;
    }

    public boolean onSuggestionFill(String r1, CharSequence[] r2, boolean[] r3) {
        return false;
    }

    public boolean openApkInstall(Activity r1, TLRPC.Document r2) {
        UpdaterUtils.installUpdate(r1, r2);
        return true;
    }

    public BaseFragment openSettings(int r1) {
        return null;
    }

    public TLRPC.Update parseTLUpdate(int r1) {
        return null;
    }

    public void processUpdate(int r1, TLRPC.Update r2) {
    }

    public boolean showCustomUpdateAppPopup(Context r1, BetaUpdate r2, int r3) {
        return false;
    }

    public boolean showUpdateAppPopup(Activity r1, TLRPC.TL_help_appUpdate r2, int r3) {
        new UpdateAppAlertDialog(r1, r2, r3).show();     // Catch: Exception -> L4
        return true;
    L4:
        e = move-exception;
        FileLog.e(e);
        return true;
    }

    public IUpdateLayout takeUpdateLayout(Activity r1, ViewGroup r2) {
        return new UpdateLayout(r1, r2);
    }
}
