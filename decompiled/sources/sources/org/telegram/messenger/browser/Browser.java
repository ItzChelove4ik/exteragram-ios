package org.telegram.messenger.browser;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import j$.net.URLEncoder;
import java.lang.ref.WeakReference;
import java.net.IDN;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.CustomTabsCopyReceiver;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.ShareBroadcastReceiver;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.customtabs.CustomTabsCallback;
import org.telegram.messenger.support.customtabs.CustomTabsClient;
import org.telegram.messenger.support.customtabs.CustomTabsIntent;
import org.telegram.messenger.support.customtabs.CustomTabsServiceConnection;
import org.telegram.messenger.support.customtabs.CustomTabsSession;
import org.telegram.messenger.support.customtabsclient.shared.CustomTabsHelper;
import org.telegram.messenger.support.customtabsclient.shared.ServiceConnection;
import org.telegram.messenger.support.customtabsclient.shared.ServiceConnectionCallback;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheetTabs;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public abstract class Browser {
    private static WeakReference<Activity> currentCustomTabsActivity;
    private static CustomTabsClient customTabsClient;
    private static WeakReference<CustomTabsSession> customTabsCurrentSession;
    private static String customTabsPackageToBind;
    private static CustomTabsServiceConnection customTabsServiceConnection;
    private static CustomTabsSession customTabsSession;
    private static Pattern domainPattern;

    /* renamed from: org.telegram.messenger.browser.Browser$1, reason: invalid class name */
    public class AnonymousClass1 implements ServiceConnectionCallback {
        public static /* synthetic */ void $r8$lambda$rUgatcyp1W2XzxLWM1eipaytXso(CustomTabsClient r2) {
            r2.warmup(0);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        public AnonymousClass1() {
        }

        @Override // org.telegram.messenger.support.customtabsclient.shared.ServiceConnectionCallback
        public void onServiceConnected(CustomTabsClient r2) {
            Browser.m5055$$Nest$sfputcustomTabsClient(r2);
            if (MessagesController.getInstance(UserConfig.selectedAccount).isWebBrowserUseCustomTabs() == false) goto L7;
            if (r2 == null) goto L8;
            Utilities.externalNetworkQueue.postRunnable(new Browser$1$$ExternalSyntheticLambda0(r2));
            return;
        L8:
            return;
        }

        @Override // org.telegram.messenger.support.customtabsclient.shared.ServiceConnectionCallback
        public void onServiceDisconnected() {
            Browser.m5055$$Nest$sfputcustomTabsClient(null);
        }
    }

    /* loaded from: classes5.dex */
    public static class NavigationCallback extends CustomTabsCallback {
        private NavigationCallback() {
        }

        public /* synthetic */ NavigationCallback(BrowserIA r1) {
            this();
        }

        @Override // org.telegram.messenger.support.customtabs.CustomTabsCallback
        public void onNavigationEvent(int r1, Bundle r2) {
        }
    }

    public static class Progress {
        private Runnable onCancelListener;
        private Runnable onEndListener;
        private Runnable onInitListener;

        public Progress() {
        }

        public Progress(Runnable r1, Runnable r2) {
            this.onInitListener = r1;
            this.onEndListener = r2;
        }

        public void cancel() {
            cancel(false);
        }

        public void cancel(boolean r2) {
            Runnable r0 = this.onCancelListener;
            if (r0 == null) goto L5;
            r0.run();
        L5:
            end(r2);
        }

        public void end() {
            end(false);
        }

        public void end(boolean r1) {
            Runnable r0 = this.onEndListener;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        public void init() {
            Runnable r0 = this.onInitListener;
            if (r0 == null) goto L6;
            r0.run();
            this.onInitListener = null;
            return;
        }

        public Progress onCancel(Runnable r1) {
            this.onCancelListener = r1;
            return this;
        }

        public Progress onEnd(Runnable r1) {
            this.onEndListener = r1;
            return this;
        }
    }

    public static /* synthetic */ void $r8$lambda$7LZ2gk4KysH4NPPJAO6oZpWQocA(AlertDialog[] r3, int r4) {
        AlertDialog r1 = r3[0];
        if (r1 == null) goto L10;
        r1.setOnCancelListener(new Browser$$ExternalSyntheticLambda2(r4));     // Catch: Exception -> L7
        r3[0].show();     // Catch: Exception -> L7
        return;
    L11:
        return;
    }

    public static /* synthetic */ void $r8$lambda$I69t2FOnvIYhXPGI7rWZYPnBJms(Progress r0, AlertDialog[] r1, int r2, Uri r3, Context r4, boolean r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new Browser$$ExternalSyntheticLambda3(r0, r1, r6, r2, r3, r4, r5));
    }

    public static /* synthetic */ void $r8$lambda$REtii7x6WMTm_2I5mwrfC_izKqk(int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(UserConfig.selectedAccount).cancelRequest(r1, true);
    }

    /* renamed from: $r8$lambda$r0CCM-iFnrGCwmVQpGqtZgRrTxo, reason: not valid java name */
    public static /* synthetic */ void m5054$r8$lambda$r0CCMiFnrGCwmVQpGqtZgRrTxo(Progress r1, AlertDialog[] r2, TLObject r3, int r4, Uri r5, Context r6, boolean r7) {
        if (r1 == null) goto L19;
        r1.end();
    L8:
        if ((r3 instanceof TL_account.webPagePreview) == false) goto L16;
        TL_account.webPagePreview r32 = (TL_account.webPagePreview) r3;
        MessagesController.getInstance(r4).putUsers(r32.users, false);
        MessagesController.getInstance(r4).putChats(r32.chats, false);
        TLRPC.MessageMedia r12 = r32.media;
        if ((r12 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L16;
        TLRPC.TL_messageMediaWebPage r13 = (TLRPC.TL_messageMediaWebPage) r12;
        TLRPC.WebPage r22 = r13.webpage;
        if ((r22 instanceof TLRPC.TL_webPage) == false) goto L16;
        if (r22.cached_page == null) goto L16;
        NotificationCenter.getInstance(r4).postNotificationName(NotificationCenter.openArticle, new Object[]{r13.webpage, r5.toString()});
        return;
    L16:
        openUrl(r6, r5, r7, false);
        return;
    L19:
        r2[0].dismiss();     // Catch: Throwable -> L18
    L6:
        r2[0] = null;
        goto L8
    }

    /* renamed from: -$$Nest$sfputcustomTabsClient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5055$$Nest$sfputcustomTabsClient(CustomTabsClient r0) {
        customTabsClient = r0;
    }

    public static String IDN_toUnicode(String r2) {
        r2 = IDN.toASCII(r2, 1);     // Catch: Exception -> L5
    L8:
        if (isPunycodeAllowed(r2) == true) goto L15;
        return r2;
    L15:
        return IDN.toUnicode(r2, 1);
    L12:
        e = move-exception;
        FileLog.e(e);
        return r2;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public static void bindCustomTabsService(Activity r3) {
        WeakReference<Activity> r0 = currentCustomTabsActivity;
        if (r0 != null) goto L5;
        Activity r02 = null;
    L6:
        if (r02 == null) goto L10;
        if (r02 == r3) goto L10;
        unbindCustomTabsService(r02);
    L10:
        if (customTabsClient != null) goto L29;
        currentCustomTabsActivity = new WeakReference(r3);
    L22:
        e = move-exception;
        FileLog.e(e);
        return;
    L14:
        if (TextUtils.isEmpty(customTabsPackageToBind) == false) goto L18;
        String r03 = CustomTabsHelper.getPackageNameToUse(r3);     // Catch: Exception -> L22
        customTabsPackageToBind = r03;     // Catch: Exception -> L22
        if (r03 != null) goto L18;
        return;
    L18:
        ServiceConnection r04 = new ServiceConnection(new AnonymousClass1());     // Catch: Exception -> L22
        customTabsServiceConnection = r04;     // Catch: Exception -> L22
        if (CustomTabsClient.bindCustomTabsService(r3, customTabsPackageToBind, r04) == true) goto L28;
        customTabsServiceConnection = null;     // Catch: Exception -> L22
        return;
    L28:
        return;
    L29:
        return;
    L5:
        r02 = r0.get();
        goto L6
    }

    public static String extractUsername(String r3) {
        if (r3 != null) goto L5;
    L27:
        return null;
    L5:
        if (TextUtils.isEmpty(r3) == true) goto L27;
        if (r3.startsWith("@") == false) goto L12;
        return r3.substring(1);
    L12:
        if (r3.startsWith("t.me/") == false) goto L16;
        return r3.substring(5);
    L16:
        if (r3.startsWith("http://t.me/") == false) goto L20;
        return r3.substring(12);
    L20:
        if (r3.startsWith("https://t.me/") == true) goto L22;
        Matcher r32 = LaunchActivity.PREFIX_T_ME_PATTERN.matcher(r3);
        if (r32.find() == false) goto L27;
        return r32.group(1);
    L22:
        return r3.substring(13);
    }

    public static String getBrowserPackageName(String r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        char r2 = 65535;
        switch(r3.hashCode()) {
            case -1973822757: goto L93;
            case -1451156338: goto L89;
            case -1361128838: goto L85;
            case -1270430916: goto L81;
            case -1249474382: goto L77;
            case -1051190859: goto L73;
            case -849452327: goto L69;
            case -329108395: goto L65;
            case -220816629: goto L61;
            case -61272559: goto L57;
            case 3726: goto L53;
            case 115031: goto L49;
            case 3108285: goto L45;
            case 3292336: goto L41;
            case 93998208: goto L37;
            case 105948115: goto L33;
            case 469285011: goto L29;
            case 557649660: goto L25;
            case 696911194: goto L21;
            case 1117815790: goto L17;
            case 1201385193: goto L13;
            case 1864941562: goto L9;
            default: goto L96;
        };
    L96:
        switch(r2) {
            case 0: goto L120;
            case 1: goto L118;
            case 2: goto L118;
            case 3: goto L116;
            case 4: goto L114;
            case 5: goto L112;
            case 6: goto L110;
            case 7: goto L108;
            case 8: goto L106;
            case 9: goto L104;
            case 10: goto L102;
            case 11: goto L114;
            case 12: goto L116;
            case 13: goto L106;
            case 14: goto L120;
            case 15: goto L100;
            case 16: goto L98;
            case 17: goto L110;
            case 18: goto L112;
            case 19: goto L98;
            case 20: goto L102;
            case 21: goto L108;
            default: goto L97;
        };
    L97:
        return null;
    L98:
        return "com.vivaldi.browser";
    L100:
        return "com.opera.browser";
    L102:
        return "com.UCMobile.intl";
    L104:
        return "com.opera.mini.native";
    L106:
        return "com.kiwibrowser.browser";
    L108:
        return "com.sec.android.app.sbrowser";
    L110:
        return "org.mozilla.firefox";
    L112:
        return "com.duckduckgo.mobile.android";
    L114:
        return "org.torproject.torbrowser";
    L116:
        return "com.microsoft.emmx";
    L118:
        return "com.android.chrome";
    L120:
        return "com.brave.browser";
    L9:
        if (r3.equals("samsung") == false) goto L96;
        r2 = 21;
        goto L96
    L13:
        if (r3.equals("uc-browser") == false) goto L96;
        r2 = 20;
        goto L96
    L17:
        if (r3.equals("vivaldi-browser") == false) goto L96;
        r2 = 19;
        goto L96
    L21:
        if (r3.equals("duckduckgo") == false) goto L96;
        r2 = 18;
        goto L96
    L25:
        if (r3.equals("mozilla-firefox") == false) goto L96;
        r2 = 17;
        goto L96
    L29:
        if (r3.equals("vivaldi") == false) goto L96;
        r2 = 16;
        goto L96
    L33:
        if (r3.equals("opera") == false) goto L96;
        r2 = 15;
        goto L96
    L37:
        if (r3.equals("brave") == false) goto L96;
        r2 = 14;
        goto L96
    L41:
        if (r3.equals("kiwi") == false) goto L96;
        r2 = '\r';
        goto L96
    L45:
        if (r3.equals("edge") == false) goto L96;
        r2 = '\f';
        goto L96
    L49:
        if (r3.equals("tor") == false) goto L96;
        r2 = 11;
        goto L96
    L53:
        if (r3.equals("uc") == false) goto L96;
        r2 = '\n';
        goto L96
    L57:
        if (r3.equals("opera-mini") == false) goto L96;
        r2 = '\t';
        goto L96
    L61:
        if (r3.equals("kiwi-browser") == false) goto L96;
        r2 = '\b';
        goto L96
    L65:
        if (r3.equals("samsung-browser") == false) goto L96;
        r2 = 7;
        goto L96
    L69:
        if (r3.equals("firefox") == false) goto L96;
        r2 = 6;
        goto L96
    L73:
        if (r3.equals("duckduckgo-browser") == false) goto L96;
        r2 = 5;
        goto L96
    L77:
        if (r3.equals("tor-browser") == false) goto L96;
        r2 = 4;
        goto L96
    L81:
        if (r3.equals("microsoft-edge") == false) goto L96;
        r2 = 3;
        goto L96
    L85:
        if (r3.equals("chrome") == false) goto L96;
        r2 = 2;
        goto L96
    L89:
        if (r3.equals("google-chrome") == false) goto L96;
        r2 = 1;
        goto L96
    L93:
        if (r3.equals("brave-browser") == false) goto L96;
        r2 = 0;
        goto L96
    }

    private static CustomTabsSession getSession() {
        CustomTabsClient r0 = customTabsClient;
        if (r0 != null) goto L6;
        customTabsSession = null;
    L9:
        return customTabsSession;
    L6:
        if (customTabsSession != null) goto L9;
        CustomTabsSession r02 = r0.newSession(new NavigationCallback(null));
        customTabsSession = r02;
        setCurrentSession(r02);
        goto L9
    }

    public static boolean hasAppToOpen(Context r8, String r9) {
        if (r9 != null) goto L5;
        return false;
    L5:
        List<ResolveInfo> r2 = null;
        List<ResolveInfo> r3 = r8.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com")), 0);     // Catch: Exception -> L54
        if (r3 == null) goto L18;
        if (r3.isEmpty() == true) goto L18;
        String[] r4 = new String[r3.size()];     // Catch: Exception -> L54
        int r5 = 0;
    L57:
        if (r5 >= r3.size()) goto L55;
        r4[r5] = r3.get(r5).activityInfo.packageName;     // Catch: Exception -> L52
        if (BuildVars.LOGS_ENABLED == false) goto L17;
        FileLog.d("default browser name = " + r4[r5]);     // Catch: Exception -> L52
    L17:
        r5 = r5 + 1;
    L55:
        r2 = r8.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(r9)), 0);     // Catch: Exception -> L53
        if (r4 == null) goto L33;
        int r82 = 0;
    L24:
        if (r82 >= r2.size()) goto L41;
        int r0 = 0;
    L27:
        if (r0 >= r4.length) goto L32;
        if (r4[r0].equals(r2.get(r82).activityInfo.packageName) == true) goto L30;
        r0 = r0 + 1;     // Catch: Exception -> L53
        goto L27
    L30:
        r2.remove(r82);     // Catch: Exception -> L53
        r82 = r82 - 1;
    L32:
        r82 = r82 + 1;     // Catch: Exception -> L53
    L41:
        if (BuildVars.LOGS_ENABLED == false) goto L47;
        int r83 = 0;
    L43:
        if (r83 >= r2.size()) goto L47;
        FileLog.d("device has " + r2.get(r83).activityInfo.packageName + " to open " + r9);     // Catch: Exception -> L53
        r83 = r83 + 1;
        goto L43
    L33:
        int r84 = 0;
    L35:
        if (r84 >= r2.size()) goto L41;
        if (isBrowserPackageName(r2.get(r84).activityInfo.packageName.toLowerCase()) == false) goto L39;
        r2.remove(r84);     // Catch: Exception -> L53
        r84 = r84 - 1;
    L39:
        r84 = r84 + 1;     // Catch: Exception -> L53
    L47:
        if (r2 != null) goto L49;
        return false;
    L49:
        if (r2.isEmpty() == false) goto L51;
        return false;
    L51:
        return true;
    L18:
        r4 = null;
        goto L55
    }

    public static boolean isBrowserPackageName(String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.contains("browser") == false) goto L6;
        return true;
    L6:
        if (r1.contains("chrome") == false) goto L8;
        return true;
    L8:
        if (r1.contains("firefox") == false) goto L10;
        return true;
    L10:
        if ("com.microsoft.emmx".equals(r1) == false) goto L12;
        return true;
    L12:
        if ("com.opera.mini.native".equals(r1) == false) goto L14;
        return true;
    L14:
        if ("com.duckduckgo.mobile.android".equals(r1) == false) goto L16;
        return true;
    L16:
        if ("com.UCMobile.intl".equals(r1) == false) goto L27;
        return true;
    L27:
        return false;
    }

    public static boolean isInternalUri(Uri r6, boolean r7, boolean[] r8) {
        String r0 = AndroidUtilities.getHostAuthority(r6);
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == null) goto L5;
        String r02 = r0.toLowerCase();
    L7:
        if (MessagesController.getInstance(UserConfig.selectedAccount).authDomains.contains(r02) == false) goto L11;
        if (r8 == null) goto L10;
        r8[0] = true;
    L10:
        return false;
    L11:
        Matcher r2 = LaunchActivity.PREFIX_T_ME_PATTERN.matcher(r02);
        if (r2.find() == false) goto L26;
        StringBuilder r03 = new StringBuilder("https://t.me/");
        r03.append(r2.group(1));
        if (TextUtils.isEmpty(r6.getPath()) == false) goto L16;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        r03.append(r22);
        if (TextUtils.isEmpty(r6.getQuery()) == false) goto L20;
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        r03.append(r62);
        r6 = Uri.parse(r03.toString());
        String r04 = r6.getHost();
        if (r04 == null) goto L24;
        r1 = r04.toLowerCase();
    L24:
        r02 = r1;
        goto L26
    L20:
        r62 = "?" + r6.getQuery();
        goto L21
    L16:
        r22 = "/" + r6.getPath();
    L26:
        if ("ton".equals(r6.getScheme()) == false) goto L32;
        if (ApplicationLoader.applicationContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", r6), 0).isEmpty() == true) goto L30;
        return false;
    L30:
        return true;
    L32:
        if ("tg".equals(r6.getScheme()) == false) goto L35;
        return true;
    L35:
        if ("telegram.dog".equals(r02) == false) goto L58;
        String r63 = r6.getPath();
        if (r63 != null) goto L39;
    L91:
        return false;
    L39:
        if (r63.length() <= 1) goto L91;
        if (r7 == false) goto L42;
        return true;
    L42:
        String r64 = r63.substring(1).toLowerCase();
        if (r64.startsWith("blog") == false) goto L45;
    L54:
        if (r8 == null) goto L56;
        r8[0] = true;
    L56:
        return false;
    L45:
        if (r64.equals("iv") == true) goto L54;
        if (r64.startsWith("faq") == true) goto L54;
        if (r64.equals("apps") == true) goto L54;
        if (r64.startsWith("s/") == true) goto L54;
        return true;
    L58:
        if ("telegram.me".equals(r02) == false) goto L60;
    L77:
        String r65 = r6.getPath();
        if (r65 == null) goto L91;
        if (r65.length() <= 1) goto L91;
        if (r7 == false) goto L83;
        return true;
    L83:
        String r66 = r65.substring(1).toLowerCase();
        if (r66.equals("iv") == false) goto L86;
    L89:
        if (r8 == null) goto L91;
        r8[0] = true;
        goto L91
    L86:
        if (r66.startsWith("s/") == true) goto L89;
        return true;
    L60:
        if ("t.me".equals(r02) == true) goto L77;
        if ("telegram.org".equals(r02) == true) goto L65;
    L69:
        if (r7 == false) goto L91;
        if (r02.endsWith("telegram.org") == false) goto L73;
    L76:
        return true;
    L73:
        if (r02.endsWith("telegra.ph") == true) goto L76;
        if (r02.endsWith("telesco.pe") == false) goto L91;
    L65:
        if (r6.getPath() == null) goto L69;
        if (r6.getPath().startsWith("/blog/") == false) goto L69;
        return true;
    L5:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }

    public static boolean isInternalUri(Uri r1, boolean[] r2) {
        return isInternalUri(r1, false, r2);
    }

    public static boolean isInternalUrl(String r0, boolean r1, boolean[] r2) {
        return isInternalUri(Uri.parse(r0), r1, r2);
    }

    public static boolean isInternalUrl(String r1, boolean[] r2) {
        return isInternalUri(Uri.parse(r1), false, r2);
    }

    public static boolean isPassportUrl(String r2) {
        if (r2 != null) goto L19;
        return false;
    L19:
        String r22 = r2.toLowerCase();     // Catch: Throwable -> L18
        if (r22.startsWith("tg:passport") == false) goto L8;
        return true;
    L8:
        if (r22.startsWith("tg://passport") == false) goto L10;
        return true;
    L10:
        if (r22.startsWith("tg:secureid") == false) goto L12;
        return true;
    L12:
        if (r22.contains("resolve") == false) goto L17;
        if (r22.contains("domain=telegrampassport") == false) goto L17;
        return true;
    L17:
        return false;
    }

    public static boolean isPunycodeAllowed(String r2) {
        if (r2 != null) goto L5;
        return true;
    L5:
        String[] r22 = r2.split("\\.");
        if (r22.length > 0) goto L9;
        return true;
    L9:
        return r22[r22.length - 1].startsWith("xn--");
    }

    public static boolean isTMe(String r1) {
        String r0 = MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix;     // Catch: Exception -> L4
        return TextUtils.equals(AndroidUtilities.getHostAuthority(r1), r0);
    L4:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static boolean isTelegraphUrl(String r1, boolean r2) {
        return isTelegraphUrl(r1, r2, false);
    }

    public static boolean isTelegraphUrl(String r1, boolean r2, boolean r3) {
        if (r2 == true) goto L4;
        StringBuilder r22 = new StringBuilder("^(https");
        if (r3 == false) goto L17;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        r22.append(r32);
        r22.append("://)?(te\\.?legra\\.ph|graph\\.org)(/.*|$)");
        return r1.matches(r22.toString());
    L17:
        r32 = "?";
        goto L18
    L4:
        if (r1.equals("telegra.ph") == false) goto L6;
        return true;
    L6:
        if (r1.equals("te.legra.ph") == false) goto L8;
        return true;
    L8:
        if (r1.equals("graph.org") == true) goto L21;
        return false;
    L21:
        return true;
    }

    public static boolean isTonsite(String r3) {
        String r1 = AndroidUtilities.getHostAuthority(r3, true);
        if (r1 != null) goto L5;
    L9:
        Uri r32 = Uri.parse(r3);
        if (r32.getScheme() != null) goto L12;
        return false;
    L12:
        if (r32.getScheme().equalsIgnoreCase("tonsite") == false) goto L16;
        return true;
    L16:
        return false;
    L5:
        if (r1.endsWith(".ton") == false) goto L7;
    L8:
        return true;
    L7:
        if (r1.endsWith(".adnl") == false) goto L9;
        goto L8
    }

    public static boolean isTonsitePunycode(String r3) {
        if (domainPattern != null) goto L5;
        domainPattern = Pattern.compile("^[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9\\-\\_]+$");
    L5:
        String r1 = AndroidUtilities.getHostAuthority(r3, true);
        if (r1 != null) goto L8;
    L14:
        Uri r32 = Uri.parse(r3);
        if (r32.getScheme() != null) goto L17;
        return false;
    L17:
        if (r32.getScheme().equalsIgnoreCase("tonsite") == false) goto L21;
        boolean r33 = domainPattern.matcher(r32.getScheme()).matches();
    L13:
        return !r33;
    L21:
        return false;
    L8:
        if (r1.endsWith(".ton") == false) goto L10;
    L11:
        r33 = domainPattern.matcher(r1).matches();
        goto L13
    L10:
        if (r1.endsWith(".adnl") == false) goto L14;
        goto L11
    }

    public static boolean openAsInternalIntent(Context r2, String r3) {
        return openAsInternalIntent(r2, r3, false, false, null);
    }

    public static boolean openAsInternalIntent(Context r4, String r5, boolean r6, boolean r7, Progress r8) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if ((AndroidUtilities.findActivity(r4) instanceof LaunchActivity) == false) goto L8;
        LaunchActivity r1 = (LaunchActivity) AndroidUtilities.findActivity(r4);
    L10:
        if (r1 != null) goto L12;
        return false;
    L12:
        Intent r0 = new Intent("android.intent.action.VIEW", Uri.parse(r5));
        r0.setComponent(new ComponentName(r4.getPackageName(), LaunchActivity.class.getName()));
        r0.putExtra("create_new_tab", true);
        r0.putExtra("com.android.browser.application_id", r4.getPackageName());
        r0.putExtra("force_not_internal_apps", r6);
        r0.putExtra("force_request", r7);
        r1.onNewIntent(r0, r8);
        return true;
    L8:
        r1 = LaunchActivity.instance;
        if (r1 != null) goto L10;
        return false;
    }

    public static boolean openInExternalApp(Context r5, String r6, boolean r7) {
        if (r6 != null) goto L55;
        return false;
    L55:
    L15:
        e = move-exception;
        FileLog.e(e);
    L54:
        return false;
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L54
    L6:
        if (isTonsite(r6) == false) goto L8;
    L51:
        return false;
    L8:
        if (isInternalUrl(r6, null) == true) goto L51;
        Uri r62 = Uri.parse(r6);     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        if (r62.getScheme() != null) goto L17;
        String r2 = "https";
    L19:
        if (r62.getHost() == null) goto L21;
        String r3 = r62.getHost().toLowerCase();     // Catch: Exception -> L13 ActivityNotFoundException -> L15
    L23:
        if (TextUtils.isEmpty(r62.getPath()) == false) goto L25;
        String r4 = "/";
    L26:
        String r63 = replace(r62, r2, null, r3, r4);     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        Uri r1 = Uri.parse(r63);     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        if (r63.startsWith("intent://") == false) goto L29;
    L34:
        boolean r22 = true;
    L35:
        if (r22 == false) goto L38;
        if (r7 == true) goto L38;
        return false;
    L38:
        if (r22 == false) goto L40;
        Intent r72 = Intent.parseUri(r1.toString(), 1);     // Catch: Exception -> L13 ActivityNotFoundException -> L15
    L41:
        if (r22 == false) goto L43;
    L45:
        if (r22 == false) goto L47;
    L49:
        r5.startActivity(r72);     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        return true;
    L47:
        if (hasAppToOpen(r5, r63) == true) goto L49;
        return false;
    L43:
        if (Build.VERSION.SDK_INT < 30) goto L45;
        r72.addCategory("android.intent.category.BROWSABLE");     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        r72.addCategory("android.intent.category.DEFAULT");     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        r72.addFlags(268435456);     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        r72.addFlags(1024);     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        goto L49
    L40:
        r72 = new Intent("android.intent.action.VIEW", r1);     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        goto L41
    L29:
        if (r1.getScheme() != null) goto L31;
    L33:
        r22 = false;
        goto L35
    L31:
        if (r1.getScheme().equalsIgnoreCase("intent") == false) goto L33;
    L25:
        r4 = r62.getPath();     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        goto L26
    L21:
        r3 = r62.getHost();     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        goto L23
    L17:
        r2 = r62.getScheme();     // Catch: Exception -> L13 ActivityNotFoundException -> L15
        goto L19
    }

    public static boolean openInExternalBrowser(Context r1, String r2, boolean r3) {
        return openInExternalBrowser(r1, r2, r3, null);
    }

    public static boolean openInExternalBrowser(Context r4, String r5, boolean r6, String r7) {
        if (r5 != null) goto L26;
        return false;
    L26:
        Uri r52 = Uri.parse(r5);     // Catch: Exception -> L10
        if (r52.getScheme() != null) goto L8;
    L12:
        boolean r1 = false;
    L13:
        if (r1 == false) goto L16;
        if (r6 == true) goto L16;
        return false;
    L16:
        if (r1 == false) goto L18;
        Intent r53 = Intent.parseUri(r52.toString(), 1);     // Catch: Exception -> L10
    L20:
        if (TextUtils.isEmpty(r7) == true) goto L22;
        r53.setPackage(r7);     // Catch: Exception -> L10
    L22:
        r53.putExtra("create_new_tab", true);     // Catch: Exception -> L10
        r53.putExtra("com.android.browser.application_id", r4.getPackageName());     // Catch: Exception -> L10
        r4.startActivity(r53);     // Catch: Exception -> L10
        return true;
    L18:
        r53 = new Intent("android.intent.action.VIEW", r52);     // Catch: Exception -> L10
        goto L20
    L8:
        if (r52.getScheme().equalsIgnoreCase("intent") == false) goto L12;
        r1 = true;
    L10:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static boolean openInTelegramBrowser(Context r2, String r3, Progress r4) {
        LaunchActivity r22 = LaunchActivity.instance;
        if (r22 == null) goto L9;
        BottomSheetTabs r23 = r22.getBottomSheetTabs();
        if (r23 == null) goto L9;
        if (r23.tryReopenTab(r3) == null) goto L9;
        return true;
    L9:
        BaseFragment r24 = LaunchActivity.getSafeLastFragment();
        if (r24 != null) goto L12;
    L15:
        if (r24 == null) goto L20;
        if ((r24.getParentLayout() instanceof ActionBarLayout) == false) goto L20;
        r24 = ((ActionBarLayout) r24.getParentLayout()).getSheetFragment();
    L20:
        if (r24 != null) goto L22;
        return false;
    L22:
        r24.createArticleViewer(false).open(r3, r4);
        return true;
    L12:
        if (r24.getArticleViewer() == null) goto L15;
        r24.getArticleViewer().open(r3, r4);
        return true;
    }

    public static void openUrl(Context r1, Uri r2) {
        openUrl(r1, r2, true);
    }

    public static void openUrl(Context r1, Uri r2, boolean r3) {
        openUrl(r1, r2, r3, true);
    }

    public static void openUrl(Context r10, Uri r11, boolean r12, boolean r13) {
        openUrl(r10, r11, r12, r13, false, null, null, false, true, false);
    }

    public static void openUrl(Context r10, Uri r11, boolean r12, boolean r13, Progress r14) {
        openUrl(r10, r11, r12, r13, false, r14, null, false, true, false);
    }

    public static void openUrl(Context r16, Uri r17, boolean r18, boolean r19, boolean r20, Progress r21, String r22, boolean r23, boolean r24, boolean r25) {
        if (r16 == null) goto L163;
        if (r17 == null) goto L169;
        int r3 = UserConfig.selectedAccount;
        boolean[] r11 = {false};
        boolean r13 = isInternalUri(r17, r11);
        String r14 = getBrowserPackageName(r22);
        if (r14 == null) goto L9;
        boolean r0 = false;
        boolean r6 = false;
    L10:
        if (r0 == true) goto L157;
    L33:
        Progress r1 = r21;
    L34:
        boolean r2 = false;
        boolean r10 = r13;
    L149:
    L39:
        e = e;
        Uri r12 = r17;
    L99:
        FileLog.e(e);
    L100:
        if (r24 == false) goto L117;
    L140:
        e = move-exception;
        FileLog.e(e);
        return;
    L102:
        if (BubbleActivity.instance != null) goto L117;
        if (r12 == null) goto L117;
        if (MessagesController.getInstance(r3).isWebBrowserOpenInApp(r12.toString()) == false) goto L117;
        if (TextUtils.isEmpty(r14) == false) goto L117;
        if (r12.getScheme() != null) goto L111;
    L118:
        r2 = true;
    L121:
        if (r12.getScheme() == null) goto L123;
        r12.getScheme().equalsIgnoreCase("intent");     // Catch: Exception -> L140
    L123:
        if (r10 == false) goto L127;
        LaunchActivity r02 = LaunchActivity.instance;     // Catch: Exception -> L140
        if (r02 == null) goto L127;
        openAsInternalIntent(r02, r12.toString(), r20, r25, r1);     // Catch: Exception -> L140
        return;
    L127:
        if (r2 == true) goto L129;
        openInExternalBrowser(r16, r12.toString(), r23, r14);     // Catch: Exception -> L140
        return;
    L129:
        if (openInExternalApp(r16, r12.toString(), r23) == false) goto L131;
        return;
    L131:
        if (r12.getScheme() != null) goto L133;
    L137:
        openInTelegramBrowser(r16, r12.toString(), r1);     // Catch: Exception -> L140
        return;
    L133:
        if (r12.getScheme().equalsIgnoreCase("intent") == false) goto L137;
        String r03 = Intent.parseUri(r12.toString(), 1).getStringExtra("browser_fallback_url");     // Catch: Exception -> L140
        if (TextUtils.isEmpty(r03) == true) goto L137;
        r12 = Uri.parse(r03);     // Catch: Exception -> L140
        goto L137
    L111:
        if ("https".equals(r12.getScheme()) == true) goto L118;
        if ("http".equals(r12.getScheme()) == true) goto L118;
        if ("tonsite".equals(r12.getScheme()) == true) goto L118;
    L117:
        if (isTonsite(r12.toString()) == false) goto L121;
    L36:
        if (r17.getScheme() == null) goto L41;
        String r04 = r17.getScheme().toLowerCase();     // Catch: Exception -> L39
    L38:
        String r4 = r04;
        if (r4 == null) goto L47;
        if (r4.contains(".") == false) goto L47;
        return;
    L47:
        if ("http".equals(r4) == false) goto L49;
    L161:
        r12 = r17.normalizeScheme();     // Catch: Exception -> L52
    L159:
        String r05 = AndroidUtilities.getHostAuthority(r12.toString().toLowerCase());     // Catch: Exception -> L98
        if (AccountInstance.getInstance(r3).getMessagesController().autologinDomains.contains(r05) == true) goto L147;
        boolean r182 = r2;
    L65:
        if (r6 == false) goto L70;
        if (r12 == null) goto L72;
        if (MessagesController.getInstance(r3).isWebBrowserOpenInApp(r12.toString()) == true) goto L70;
    L72:
        if (MessagesController.getInstance(r3).isWebBrowserUseCustomTabs() == false) goto L70;
        if (r10 == true) goto L70;
        if (r4.equals("tel") == true) goto L70;
        if (isTonsite(r12.toString()) == true) goto L70;
        if (r11[r182 ? 1 : 0] == true) goto L89;
    L86:
        e = e;
        r2 = false;
        goto L99
    L81:
        if (openInExternalApp(r16, r12.toString(), r182 ? 1 : 0) == false) goto L89;
        if (hasAppToOpen(r16, r12.toString()) == false) goto L89;
        r2 = false;
    L89:
        if (MessagesController.getInstance(r3).authDomains.contains(r05) == false) goto L91;
        Intent r06 = new Intent("android.intent.action.VIEW", r12);     // Catch: Exception -> L86
        r06.addFlags(268435456);     // Catch: Exception -> L86
        ApplicationLoader.applicationContext.startActivity(r06);     // Catch: Exception -> L86
        return;
    L91:
        Intent r07 = new Intent(ApplicationLoader.applicationContext, ShareBroadcastReceiver.class);     // Catch: Exception -> L86
        r07.setAction("android.intent.action.SEND");     // Catch: Exception -> L86
        PendingIntent r42 = PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 0, new Intent(ApplicationLoader.applicationContext, CustomTabsCopyReceiver.class), 201326592);     // Catch: Exception -> L98
        CustomTabsIntent.Builder r62 = new CustomTabsIntent.Builder(getSession());     // Catch: Exception -> L86
        r62.addMenuItem(LocaleController.getString(R.string.CopyLink), r42);     // Catch: Exception -> L86
        r62.setToolbarColor(Theme.getColor(Theme.key_actionBarBrowser));     // Catch: Exception -> L86
        r62.setShowTitle(true);     // Catch: Exception -> L86
        r2 = false;
        r62.setActionButton(BitmapFactory.decodeResource(r16.getResources(), R.drawable.msg_filled_shareout), LocaleController.getString(R.string.ShareFile), PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 0, r07, 33554432), true);     // Catch: Exception -> L98
        CustomTabsIntent r08 = r62.build();     // Catch: Exception -> L98
        r08.setUseNewTask();     // Catch: Exception -> L98
        r08.launchUrl(r16, r12);     // Catch: Exception -> L98
        return;
    L60:
        e = e;
        r2 = r182;
    L70:
        r2 = r182 ? 1 : 0;
        goto L100
    L147:
        r182 = r2;
        r12 = r12.buildUpon().appendQueryParameter("autologin_token", URLEncoder.encode(AccountInstance.getInstance(UserConfig.selectedAccount).getMessagesController().autologinToken, "UTF-8")).build();     // Catch: Exception -> L60
    L62:
        e = e;
    L98:
        e = e;
        goto L99
    L52:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L39
    L54:
        r12 = r17;
        goto L159
    L49:
        if ("https".equals(r4) == false) goto L54;
    L41:
        r04 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L38
    L157:
        String r09 = AndroidUtilities.getHostAuthority(r17);     // Catch: Exception -> L145
        if (UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser() == null) goto L33;
        if (isTelegraphUrl(r09, true) == false) goto L16;
    L23:
        AlertDialog[] r26 = {new AlertDialog(r16, 3)};     // Catch: Exception -> L145
        TL_account.getWebPagePreview r15 = new TL_account.getWebPagePreview();     // Catch: Exception -> L145
        r15.message = r17.toString();     // Catch: Exception -> L145
        r1 = r21;
        int r010 = ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r15, new Browser$$ExternalSyntheticLambda0(r1, r26, r3, r17, r16, r6));     // Catch: Exception -> L144
        if (r1 == null) goto L29;
        r1.init();     // Catch: Exception -> L144
        return;
    L29:
        Browser$$ExternalSyntheticLambda1 r43 = new Browser$$ExternalSyntheticLambda1(r26, r010);     // Catch: Exception -> L144
        r2 = false;
        r10 = r13;
        AndroidUtilities.runOnUIThread(r43, 1000);     // Catch: Exception -> L143
        return;
    L16:
        if ("telegram.org".equalsIgnoreCase(r09) == false) goto L33;
        if (r17.toString().toLowerCase().contains("telegram.org/faq") == true) goto L23;
        if (r17.toString().toLowerCase().contains("telegram.org/privacy") == true) goto L23;
        if (r17.toString().toLowerCase().contains("telegram.org/blog") == false) goto L33;
    L9:
        r6 = r18;
        r0 = r19;
        goto L10
    L169:
        return;
    }

    public static void openUrl(Context r1, String r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        openUrl(r1, Uri.parse(r2), true);
    }

    public static void openUrl(Context r0, String r1, boolean r2) {
        if (r0 == null) goto L7;
        if (r1 == null) goto L8;
        openUrl(r0, Uri.parse(r1), r2);
        return;
    L8:
        return;
    }

    public static void openUrl(Context r0, String r1, boolean r2, boolean r3) {
        openUrl(r0, Uri.parse(r1), r2, r3);
    }

    public static void openUrlInSystemBrowser(Context r10, String r11) {
        if (r11 != null) goto L4;
        return;
    L4:
        openUrl(r10, Uri.parse(r11), false, true, false, null, null, false, false, false);
    }

    public static String replace(Uri r2, String r3, String r4, String r5, String r6) {
        StringBuilder r0 = new StringBuilder();
        if (r3 != null) goto L5;
        r3 = r2.getScheme();
    L5:
        if (r3 == null) goto L8;
        r0.append(r3);
        r0.append("://");
    L8:
        if (r4 != null) goto L13;
        if (r2.getUserInfo() == null) goto L15;
        r0.append(r2.getUserInfo());
        r0.append("@");
    L15:
        if (r5 == null) goto L17;
        r0.append(r5);
    L21:
        if (r2.getPort() == (-1)) goto L23;
        r0.append(":");
        r0.append(r2.getPort());
    L23:
        if (r6 == null) goto L26;
        r0.append(r6);
    L29:
        if (r2.getQuery() == null) goto L32;
        r0.append("?");
        r0.append(r2.getQuery());
    L32:
        if (r2.getFragment() == null) goto L35;
        r0.append("#");
        r0.append(r2.getFragment());
    L35:
        return r0.toString();
    L26:
        if (r2.getPath() == null) goto L29;
        r0.append(r2.getPath());
        goto L29
    L17:
        if (r2.getHost() == null) goto L21;
        r0.append(r2.getHost());
        goto L21
    L13:
        if (TextUtils.isEmpty(r4) == true) goto L15;
        r0.append(r4);
        r0.append("@");
        goto L15
    }

    public static String replaceHostname(Uri r1, String r2, String r3) {
        return replace(r1, r3, null, r2, null);
    }

    private static void setCurrentSession(CustomTabsSession r1) {
        customTabsCurrentSession = new WeakReference(r1);
    }

    public static void unbindCustomTabsService(Activity r2) {
        if (customTabsServiceConnection != null) goto L5;
        return;
    L5:
        WeakReference<Activity> r0 = currentCustomTabsActivity;
        if (r0 != null) goto L8;
        Activity r02 = null;
    L9:
        if (r02 != r2) goto L15;
        currentCustomTabsActivity.clear();
    L15:
        r2.unbindService(customTabsServiceConnection);     // Catch: Exception -> L14
    L12:
        customTabsClient = null;
        customTabsSession = null;
        return;
    L8:
        r02 = r0.get();
        goto L9
    }

    public static boolean urlMustNotHaveConfirmation(String r3) {
        if (isTelegraphUrl(r3, false, true) == false) goto L5;
    L12:
        return true;
    L5:
        if (r3.matches("^(https://)?t\\.me/iv\\??(/.*|$)") == true) goto L12;
        if (r3.matches("^(https://)?telegram\\.org/(blog|tour)(/.*|$)") == true) goto L12;
        if (r3.matches("^(https://)?fragment\\.com(/.*|$)") == true) goto L12;
        return false;
    }
}
