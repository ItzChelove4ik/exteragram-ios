package com.exteragram.messenger.proxy;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.exteragram.messenger.proxy.IpAddressInfoController;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import j$.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.utils.proxy.ProxySettings;

/* loaded from: classes.dex */
public final class ProxyController {
    private static final ProxyController INSTANCE = null;
    private SharedConfig.ProxyInfo currentProxy;
    private boolean loaded;
    private final HashSet<String> pinnedProxies;
    private final ArrayList<String> pinnedProxyOrder;
    private final ArrayList<SharedConfig.ProxyInfo> proxyList;
    private final HashMap<String, String> proxyNames;

    /* loaded from: classes4.dex */
    public enum PinOperationResult extends Enum<PinOperationResult> {
        private static final /* synthetic */ PinOperationResult[] $VALUES = null;
        public static final PinOperationResult CHANGED = null;
        public static final PinOperationResult LIMIT_REACHED = null;
        public static final PinOperationResult NO_CHANGE = null;

        private static /* synthetic */ PinOperationResult[] $values() {
            return new PinOperationResult[]{NO_CHANGE, CHANGED, LIMIT_REACHED};
        }

        static {
            NO_CHANGE = new PinOperationResult("NO_CHANGE", 0);
            CHANGED = new PinOperationResult("CHANGED", 1);
            LIMIT_REACHED = new PinOperationResult("LIMIT_REACHED", 2);
            $VALUES = $values();
        }

        PinOperationResult(String r1, int r2) {
        }

        public static PinOperationResult valueOf(String r1) {
            return (PinOperationResult) Enum.valueOf(PinOperationResult.class, r1);
        }

        public static PinOperationResult[] values() {
            return (PinOperationResult[]) $VALUES.clone();
        }
    }

    /* loaded from: classes4.dex */
    public interface ProxyCountryCallback {
        void onCountryResolved(String r1);
    }

    /* renamed from: $r8$lambda$8nqVbMH6tqZG4pxYVfVZ0Mk-G7U, reason: not valid java name */
    public static /* synthetic */ void m1721$r8$lambda$8nqVbMH6tqZG4pxYVfVZ0MkG7U(ProxyCountryCallback r0, String r1) {
        r0.onCountryResolved(r1);
    }

    /* renamed from: $r8$lambda$AmZUEYL-7cYteVYKP9bAMO4poLg, reason: not valid java name */
    public static /* synthetic */ void m1722$r8$lambda$AmZUEYL7cYteVYKP9bAMO4poLg(ProxyCountryCallback r0, String r1) {
        r0.onCountryResolved(r1);
    }

    /* renamed from: $r8$lambda$OcyeWGIuaV-SIcBNk9h1f4w0rFQ, reason: not valid java name */
    public static /* synthetic */ void m1723$r8$lambda$OcyeWGIuaVSIcBNk9h1f4w0rFQ(ProxyCountryCallback r0, String r1) {
        r0.onCountryResolved(r1);
    }

    /* renamed from: $r8$lambda$TdY6Cr8_tKxnkeT37UhQ-TXIvZA, reason: not valid java name */
    public static /* synthetic */ int m1724$r8$lambda$TdY6Cr8_tKxnkeT37UhQTXIvZA(ProxyController r0, SharedConfig.ProxyInfo r1, boolean r2, ArrayList r3, SharedConfig.ProxyInfo r4, SharedConfig.ProxyInfo r5) {
        return r0.lambda$sortProxyList$6(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$o2u5Mt7BsHFzVYQuxEPhAre7_kE(String r1, ProxyCountryCallback r2, String r3) {
        String r12 = resolveProxyIpAddress(r1);
        if (TextUtils.isEmpty(r12) == false) goto L6;
        AndroidUtilities.runOnUIThread(new ProxyController$$ExternalSyntheticLambda4(r2, r3));
        return;
    L6:
        IpAddressInfoController.requestIpAddressInfo(r12, new ProxyController$$ExternalSyntheticLambda5(r3, r2));
    }

    public static /* synthetic */ void $r8$lambda$oRbqtd6yYrrPwcGrsMaifSCNU90(String r0, ProxyCountryCallback r1, IpAddressInfoController.IpAddressInfo r2) {
        if (r2 == null) goto L4;
        r0 = getCountryDisplayName(r2.countryCode);
    L4:
        AndroidUtilities.runOnUIThread(new ProxyController$$ExternalSyntheticLambda6(r1, r0));
    }

    public static /* synthetic */ int $r8$lambda$pKO6UU3ex6y24YckVjP_LJNaPTI(ProxyController r0, SharedConfig.ProxyInfo r1, SharedConfig.ProxyInfo r2) {
        return r0.lambda$saveProxyList$0(r1, r2);
    }

    static {
        INSTANCE = new ProxyController();
    }

    private ProxyController() {
        this.proxyList = new ArrayList();
        this.pinnedProxies = new HashSet();
        this.pinnedProxyOrder = new ArrayList();
        this.proxyNames = new HashMap();
    }

    private static ProxySettings buildSettings(String r2, int r3, String r4, String r5, String r6) {
        ProxySettings.Builder r0 = ProxySettings.builder();
        if (TextUtils.isEmpty(r6) == false) goto L5;
        ProxySettings.Type r1 = ProxySettings.Type.SOCKS5;
    L7:
        return r0.setType(r1).setAddress(r2).setPort(r3).setUser(r4).setPassword(r5).setSecret(r6).build();
    L5:
        r1 = ProxySettings.Type.MTPROTO;
        goto L7
    }

    private static String decode(String r2) {
        return new String(Base64.decode(r2, 2), StandardCharsets.UTF_8);
    }

    private static String encode(String r1) {
        if (r1 != null) goto L6;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return Base64.encodeToString(r1.getBytes(StandardCharsets.UTF_8), 2);
    }

    private void ensureLoaded() {
        if (this.loaded == true) goto L6;
        load();
        return;
    }

    private static String getCountryDisplayName(String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        Locale r0 = LocaleController.getInstance().getCurrentLocale();
        if (r0 != null) goto L8;
        r0 = Locale.getDefault();
    L8:
        String r42 = new Locale(_UrlKt.FRAGMENT_ENCODE_SET, r4.toUpperCase(Locale.US)).getDisplayCountry(r0);
        if (TextUtils.isEmpty(r42) == true) goto L11;
        return r42;
    L11:
        return LocaleController.getString(R.string.Unknown);
    }

    public static ProxyController getInstance() {
        return INSTANCE;
    }

    private static String getProxyCountryKey(SharedConfig.ProxyInfo r0) {
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return normalizeProxyCountryKey(r0.settings.getAddress());
    }

    private String getProxyKey(SharedConfig.ProxyInfo r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (TextUtils.isEmpty(r1.settings.getLink()) == false) goto L7;
        return null;
    L7:
        return r1.settings.getLink();
    }

    private static boolean isTelegramProxy(SharedConfig.ProxyInfo r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (TextUtils.isEmpty(r0.settings.getSecret()) == true) goto L9;
        return true;
    L9:
        return false;
    }

    private /* synthetic */ int lambda$saveProxyList$0(SharedConfig.ProxyInfo r10, SharedConfig.ProxyInfo r11) {
        SharedConfig.ProxyInfo r9 = this.currentProxy;
        long r0 = 0;
        if (r9 != r10) goto L5;
        long r4 = -200000;
    L7:
        if (r10.available == true) goto L9;
        r4 = r4 + 100000;
    L9:
        if (r9 != r11) goto L12;
        r0 = -200000;
    L12:
        if (r11.available == true) goto L15;
        r0 = r0 + 100000;
    L15:
        return Long.compare(r10.ping + r4, r11.ping + r0);
    L5:
        r4 = 0;
        goto L7
    }

    private /* synthetic */ int lambda$sortProxyList$6(SharedConfig.ProxyInfo r9, boolean r10, ArrayList r11, SharedConfig.ProxyInfo r12, SharedConfig.ProxyInfo r13) {
        int r0 = getPinnedIndex(r12);
        int r8 = getPinnedIndex(r13);
        boolean r1 = false;
        if (r0 < 0) goto L5;
        boolean r3 = true;
    L6:
        if (r8 < 0) goto L8;
        r1 = true;
    L8:
        if (r3 == r1) goto L13;
        if (r3 == false) goto L12;
        return -1;
    L12:
        return 1;
    L13:
        if (r3 == true) goto L15;
        long r02 = 0;
        if (r9 != r12) goto L19;
        long r4 = -200000;
    L21:
        if (r12.available == true) goto L23;
        r4 = r4 + 100000;
    L23:
        if (r9 != r13) goto L26;
        r02 = -200000;
    L26:
        if (r13.available == true) goto L29;
        r02 = r02 + 100000;
    L29:
        if (r10 == false) goto L32;
        if (r12 == r9) goto L32;
        long r42 = r11.indexOf(r12) * NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L33:
        if (r10 == false) goto L36;
        if (r13 == r9) goto L36;
        long r82 = r11.indexOf(r13) * NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L38:
        return Long.compare(r42, r82);
    L36:
        r82 = r13.ping + r02;
    L32:
        r42 = r4 + r12.ping;
        goto L33
    L19:
        r4 = 0;
        goto L21
    L15:
        return Integer.compare(r0, r8);
    L5:
        r3 = false;
        goto L6
    }

    private void load() {
        this.proxyList.clear();
        this.currentProxy = null;
        this.pinnedProxies.clear();
        this.pinnedProxyOrder.clear();
        this.proxyNames.clear();
        SharedPreferences r1 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        ProxySettings r2 = ProxySettings.fromSharedPreferences(r1);
        boolean r4 = r2.isValid();
        String r5 = r1.getString("proxy_list", null);
        if (TextUtils.isEmpty(r5) == true) goto L62;
        SerializedData r6 = new SerializedData(Base64.decode(r5, 0));
        int r52 = r6.readInt32(false);
        if (r52 != (-1)) goto L51;
        byte r53 = r6.readByte(false);
        if (r53 != 5) goto L21;
        int r54 = r6.readInt32(false);
        int r8 = 0;
    L9:
        if (r8 >= r54) goto L60;
        SharedConfig.ProxyInfo r9 = readProxyInfo(r6);
        r9.ping = r6.readInt64(false);
        r9.availableCheckTime = SharedConfig.ProxyInfo.normalizeAvailableCheckTime(r6.readInt64(false));
        if (r6.readBool(false) == false) goto L13;
        r9.settings = ProxySettings.builder().setType(ProxySettings.Type.WEB).setAddress(r9.settings.getAddress()).setPort(r9.settings.getPort()).setSecret(r9.settings.getSecret()).build();
    L13:
        this.proxyList.add(0, r9);
        if (this.currentProxy != null) goto L19;
        if (r4 == false) goto L19;
        if (r2.equals(r9.settings) == false) goto L19;
        this.currentProxy = r9;
    L19:
        r8 = r8 + 1;
    L60:
        boolean r92 = false;
    L61:
        r6.cleanup();
    L64:
        if (this.currentProxy != null) goto L67;
        if (r4 == false) goto L67;
        SharedConfig.ProxyInfo r42 = new SharedConfig.ProxyInfo(r2);
        this.currentProxy = r42;
        this.proxyList.add(0, r42);
    L67:
        String r22 = r1.getString("proxy_pinned_order", r1.getString("proxy_pinned_links_order", null));
        if (TextUtils.isEmpty(r22) == true) goto L77;
        String[] r23 = r22.split("\\n");
        int r43 = r23.length;
        int r62 = 0;
    L70:
        if (r62 >= r43) goto L77;
        String r82 = r23[r62];
        if (TextUtils.isEmpty(r82) == true) goto L76;
        if (this.pinnedProxies.add(r82) == false) goto L76;
        this.pinnedProxyOrder.add(r82);
    L76:
        r62 = r62 + 1;
    L77:
        Set<String> r24 = r1.getStringSet("proxy_pinned", r1.getStringSet("proxy_pinned_links", null));
        if (r24 == null) goto L87;
        Iterator<String> r25 = r24.iterator();
    L81:
        if (r25.hasNext() == false) goto L87;
        String r44 = r25.next();
        if (TextUtils.isEmpty(r44) == true) goto L81;
        if (this.pinnedProxies.add(r44) == false) goto L81;
        this.pinnedProxyOrder.add(r44);
    L87:
        String r0 = r1.getString("proxy_names", null);
        if (TextUtils.isEmpty(r0) == true) goto L105;
        String[] r02 = r0.split("\\n");
        int r12 = r02.length;
        int r26 = 0;
    L90:
        if (r26 >= r12) goto L105;
        String r45 = r02[r26];
        if (TextUtils.isEmpty(r45) == true) goto L104;
        int r55 = r45.indexOf(9);
        if (r55 <= 0) goto L104;
        if (r55 >= (r45.length() - 1)) goto L104;
        String r63 = decode(r45.substring(0, r55));
        String r46 = decode(r45.substring(r55 + 1));
        if (TextUtils.isEmpty(r63) == true) goto L104;
        if (TextUtils.isEmpty(r46) == true) goto L104;
        this.proxyNames.put(r63, r46);
    L104:
        r26 = r26 + 1;
    L105:
        this.loaded = true;
        if (r92 == false) goto L108;
        save();
    L108:
        syncProxyList();
        return;
    L21:
        if (r53 != 3) goto L37;
        int r56 = r6.readInt32(false);
        int r83 = 0;
        r92 = false;
    L23:
        if (r83 >= r56) goto L61;
        String r10 = r6.readString(false);
        SharedConfig.ProxyInfo r11 = readProxyInfo(r6);
        r11.ping = r6.readInt64(false);
        r11.availableCheckTime = SharedConfig.ProxyInfo.normalizeAvailableCheckTime(r6.readInt64(false));
        this.proxyList.add(0, r11);
        String r122 = getProxyKey(r11);
        String r102 = normalizeName(r10);
        if (TextUtils.isEmpty(r122) == true) goto L30;
        if (TextUtils.isEmpty(r102) == true) goto L30;
        this.proxyNames.put(r122, r102);
        r92 = true;
    L30:
        if (this.currentProxy != null) goto L35;
        if (r4 == false) goto L35;
        if (r2.equals(r11.settings) == false) goto L35;
        this.currentProxy = r11;
    L35:
        r83 = r83 + 1;
        goto L23
    L37:
        if (r53 != 4) goto L39;
    L42:
        int r57 = r6.readInt32(false);
        int r84 = 0;
    L43:
        if (r84 >= r57) goto L60;
        SharedConfig.ProxyInfo r93 = readProxyInfo(r6);
        r93.ping = r6.readInt64(false);
        r93.availableCheckTime = SharedConfig.ProxyInfo.normalizeAvailableCheckTime(r6.readInt64(false));
        this.proxyList.add(0, r93);
        if (this.currentProxy != null) goto L50;
        if (r4 == false) goto L50;
        if (r2.equals(r93.settings) == false) goto L50;
        this.currentProxy = r93;
    L50:
        r84 = r84 + 1;
        goto L43
    L39:
        if (r53 == 2) goto L42;
        FileLog.e("Unknown proxy schema version: " + r53);
        goto L60
    L51:
        int r85 = 0;
    L52:
        if (r85 >= r52) goto L60;
        SharedConfig.ProxyInfo r94 = readProxyInfo(r6);
        this.proxyList.add(0, r94);
        if (this.currentProxy != null) goto L59;
        if (r4 == false) goto L59;
        if (r2.equals(r94.settings) == false) goto L59;
        this.currentProxy = r94;
    L59:
        r85 = r85 + 1;
        goto L52
    L62:
        r92 = false;
        goto L64
    }

    private static String normalizeName(String r0) {
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r0.trim();
    }

    private static String normalizeProxyCountryKey(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        String r22 = r2.trim();
        if (r22.startsWith("[") == false) goto L14;
        if (r22.endsWith("]") == false) goto L14;
        if (r22.length() <= 2) goto L14;
        r22 = r22.substring(1, r22.length() - 1);
    L14:
        return r22.toLowerCase(Locale.US);
    }

    private static SharedConfig.ProxyInfo readProxyInfo(SerializedData r5) {
        return new SharedConfig.ProxyInfo(buildSettings(r5.readString(false), r5.readInt32(false), r5.readString(false), r5.readString(false), r5.readString(false)));
    }

    private static String resolveProxyIpAddress(String r2) {
        InetAddress r22 = InetAddress.getByName(IDN.toASCII(r2));     // Catch: Exception -> L16
        if (r22.isAnyLocalAddress() == false) goto L7;
    L15:
        return null;
    L7:
        if (r22.isLoopbackAddress() == true) goto L15;
        if (r22.isLinkLocalAddress() == true) goto L15;
        if (r22.isSiteLocalAddress() == true) goto L15;
        return r22.getHostAddress();
    L16:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private void save() {
        SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
        r0.putStringSet("proxy_pinned", new HashSet(this.pinnedProxies));
        if (this.pinnedProxyOrder.isEmpty() == false) goto L5;
        r0.remove("proxy_pinned_order");
    L6:
        r0.remove("proxy_pinned_links");
        r0.remove("proxy_pinned_links_order");
        if (this.proxyNames.isEmpty() == false) goto L9;
        r0.remove("proxy_names");
    L14:
        r0.apply();
        return;
    L9:
        ArrayList r1 = new ArrayList(this.proxyNames.size());
        Iterator<Map.Entry<String, String>> r7 = this.proxyNames.entrySet().iterator();
    L11:
        if (r7.hasNext() == false) goto L13;
        Map.Entry<String, String> r4 = r7.next();
        r1.add(encode(r4.getKey()) + "\t" + encode(r4.getValue()));
        goto L11
    L13:
        r0.putString("proxy_names", TextUtils.join("\n", r1));
        goto L14
    L5:
        r0.putString("proxy_pinned_order", TextUtils.join("\n", this.pinnedProxyOrder));
        goto L6
    }

    private void syncProxyList() {
        SharedConfig.proxyList.clear();
        SharedConfig.proxyList.addAll(this.proxyList);
        SharedConfig.currentProxy = this.currentProxy;
    }

    public synchronized SharedConfig.ProxyInfo addProxy(SharedConfig.ProxyInfo r7) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L12
        int r0 = this.proxyList.size();     // Catch: Throwable -> L12
        int r2 = 0;
    L5:
        ArrayList<SharedConfig.ProxyInfo> r3 = this.proxyList;
        if (r2 >= r0) goto L14;
        SharedConfig.ProxyInfo r32 = r3.get(r2);     // Catch: Throwable -> L12
        if (r7.settings.equals(r32.settings) == true) goto L9;
        r2 = r2 + 1;
        goto L5
    L9:
        monitor-exit(this);
        return r32;
    L14:
        r3.add(0, r7);     // Catch: Throwable -> L12
        syncProxyList();     // Catch: Throwable -> L12
        saveProxyList();     // Catch: Throwable -> L12
        monitor-exit(this);
        return r7;
    L12:
        th = move-exception;
        throw th;
    }

    public synchronized PinOperationResult applySelectedPinAction(List<SharedConfig.ProxyInfo> r7) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L8
        int r0 = getSelectedPinAction(r7);     // Catch: Throwable -> L8
        if (r0 != 0) goto L10;
        PinOperationResult r72 = PinOperationResult.NO_CHANGE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r72;
    L10:
        boolean r1 = false;
        if (r0 != 1) goto L32;
        ArrayList r02 = new ArrayList();     // Catch: Throwable -> L8
        Iterator<SharedConfig.ProxyInfo> r73 = r7.iterator();     // Catch: Throwable -> L8
    L14:
        if (r73.hasNext() == false) goto L23;
        String r3 = getProxyKey(r73.next());     // Catch: Throwable -> L8
        if (TextUtils.isEmpty(r3) == true) goto L14;
        if (this.pinnedProxies.contains(r3) == true) goto L14;
        if (r02.contains(r3) == true) goto L14;
        r02.add(r3);     // Catch: Throwable -> L8
        goto L14
    L23:
        if ((this.pinnedProxyOrder.size() + r02.size()) <= 10) goto L27;
        PinOperationResult r74 = PinOperationResult.LIMIT_REACHED;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r74;
    L27:
        int r75 = r02.size();     // Catch: Throwable -> L8
        int r32 = 0;
    L28:
        if (r32 >= r75) goto L44;
        Object r4 = r02.get(r32);     // Catch: Throwable -> L8
        r32 = r32 + 1;     // Catch: Throwable -> L8
        String r42 = (String) r4;     // Catch: Throwable -> L8
        if (this.pinnedProxies.add(r42) == false) goto L28;
        this.pinnedProxyOrder.add(r42);     // Catch: Throwable -> L8
        r1 = true;
    L44:
        if (r1 == false) goto L48;
        save();     // Catch: Throwable -> L8
        PinOperationResult r76 = PinOperationResult.CHANGED;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r76;
    L48:
        PinOperationResult r77 = PinOperationResult.NO_CHANGE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r77;
    L32:
        Iterator<SharedConfig.ProxyInfo> r78 = r7.iterator();     // Catch: Throwable -> L8
    L34:
        if (r78.hasNext() == false) goto L44;
        String r03 = getProxyKey(r78.next());     // Catch: Throwable -> L8
        if (TextUtils.isEmpty(r03) == true) goto L34;
        if (this.pinnedProxies.remove(r03) == false) goto L42;
        r1 = true;
    L42:
        if (this.pinnedProxyOrder.remove(r03) == false) goto L34;
        r1 = true;
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized String buildShareLink(SharedConfig.ProxyInfo r2) {
        monitor-enter(this);
        String r22 = buildShareLink(r2, null);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized String buildShareLink(SharedConfig.ProxyInfo r3, String r4) {
        monitor-enter(this);
        if (r3 == null) goto L5;
        String r0 = r3.settings.getLink();     // Catch: Throwable -> L7
        String r42 = normalizeName(r4);     // Catch: Throwable -> L7
        if (TextUtils.isEmpty(r42) == false) goto L13;
        r42 = getName(r3);     // Catch: Throwable -> L7
    L13:
        if (TextUtils.isEmpty(r42) == false) goto L23;
        monitor-exit(this);
        return r0;
    L23:
        String r32 = r0 + "&title=" + URLEncoder.encode(r42, "UTF-8");     // Catch: Throwable -> L7 UnsupportedEncodingException -> L19
        monitor-exit(this);
        return r32;
    L20:
        return r0;
    L7:
        th = move-exception;
        throw th;
    L5:
        monitor-exit(this);
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public synchronized void clearAll() {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L11
        if (this.pinnedProxies.isEmpty() == true) goto L6;
    L13:
        this.pinnedProxies.clear();     // Catch: Throwable -> L11
        this.pinnedProxyOrder.clear();     // Catch: Throwable -> L11
        this.proxyNames.clear();     // Catch: Throwable -> L11
        save();     // Catch: Throwable -> L11
        monitor-exit(this);
        return;
    L6:
        if (this.pinnedProxyOrder.isEmpty() == false) goto L13;
        if (this.proxyNames.isEmpty() == false) goto L13;
        monitor-exit(this);
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public synchronized void deleteProxy(SharedConfig.ProxyInfo r6) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L9
        String r0 = getProxyKey(r6);     // Catch: Throwable -> L9
        SharedConfig.ProxyInfo r1 = this.currentProxy;     // Catch: Throwable -> L9
        if (r1 == r6) goto L11;
        if (r0 != null) goto L7;
    L14:
        this.proxyList.remove(r6);     // Catch: Throwable -> L9
        removeProxy(r6);     // Catch: Throwable -> L9
        syncProxyList();     // Catch: Throwable -> L9
        saveProxyList();     // Catch: Throwable -> L9
        monitor-exit(this);
        return;
    L7:
        if (r0.equals(getProxyKey(r1)) == false) goto L14;
    L11:
        this.currentProxy = null;     // Catch: Throwable -> L9
        SharedConfig.currentProxy = null;     // Catch: Throwable -> L9
        SharedPreferences r12 = MessagesController.getGlobalMainSettings();     // Catch: Throwable -> L9
        boolean r2 = r12.getBoolean("proxy_enabled", false);     // Catch: Throwable -> L9
        SharedPreferences.Editor r13 = r12.edit();     // Catch: Throwable -> L9
        ProxySettings.EMPTY.toSharedPreferences(r13);     // Catch: Throwable -> L9
        r13.putBoolean("proxy_enabled", false);     // Catch: Throwable -> L9
        r13.apply();     // Catch: Throwable -> L9
        if (r2 == false) goto L14;
        ConnectionsManager.setProxySettings(false, null);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public synchronized SharedConfig.ProxyInfo getCurrentProxy() {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L6
        SharedConfig.ProxyInfo r0 = this.currentProxy;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized String getDisplayName(SharedConfig.ProxyInfo r3) {
        monitor-enter(this);
        if (r3 == null) goto L5;
        String r0 = getName(r3);     // Catch: Throwable -> L7
        if (TextUtils.isEmpty(r0) == true) goto L14;
        monitor-exit(this);
        return r0;
    L14:
        if (r3.settings.getType() != ProxySettings.Type.WEB) goto L16;
        String r32 = r3.settings.getAddress();     // Catch: Throwable -> L7
    L17:
        monitor-exit(this);
        return r32;
    L16:
        r32 = r3.settings.getAddress() + ":" + r3.settings.getPort();     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    L5:
        monitor-exit(this);
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public int getMaxPinnedProxies() {
        return 10;
    }

    public synchronized String getName(String r2) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L8
        if (TextUtils.isEmpty(r2) == true) goto L6;
        String r22 = this.proxyNames.get(r2);     // Catch: Throwable -> L8
        if (r22 != null) goto L14;
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L14:
        monitor-exit(this);
        return r22;
    L6:
        monitor-exit(this);
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized String getName(SharedConfig.ProxyInfo r1) {
        monitor-enter(this);
        if (r1 != null) goto L12;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        monitor-exit(this);
        return r12;
    L12:
        r12 = getName(getProxyKey(r1));     // Catch: Throwable -> L5
    L5:
        th = move-exception;
        throw th;
    }

    public synchronized int getPinnedCount() {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L6
        int r0 = this.pinnedProxyOrder.size();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized int getPinnedIndex(SharedConfig.ProxyInfo r3) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L14
        int r0 = -1;
        if (r3 != null) goto L8;
        monitor-exit(this);
        return -1;
    L8:
        String r32 = getProxyKey(r3);     // Catch: Throwable -> L14
        if (TextUtils.isEmpty(r32) == true) goto L12;
        r0 = this.pinnedProxyOrder.indexOf(r32);     // Catch: Throwable -> L14
    L12:
        monitor-exit(this);
        return r0;
    L14:
        th = move-exception;
        throw th;
    }

    public synchronized ArrayList<SharedConfig.ProxyInfo> getProxyList() {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L6
        ArrayList<SharedConfig.ProxyInfo> r0 = new ArrayList(this.proxyList);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized String getProxyTypeName(SharedConfig.ProxyInfo r3) {
        monitor-enter(this);
        if (r3 == null) goto L12;
    L9:
        th = move-exception;
        throw th;
    L5:
        if (r3.settings.getType() != ProxySettings.Type.WEB) goto L12;
        String r32 = LocaleController.getString(R.string.UseProxyWeb);     // Catch: Throwable -> L9
        monitor-exit(this);
        return r32;
    L12:
        if (isTelegramProxy(r3) == false) goto L15;
        int r33 = R.string.UseProxyTelegram;     // Catch: Throwable -> L9
    L14:
        String r34 = LocaleController.getString(r33);     // Catch: Throwable -> L9
        monitor-exit(this);
        return r34;
    L15:
        r33 = R.string.UseProxySocks5;     // Catch: Throwable -> L9
        goto L14
    }

    public synchronized int getSelectedPinAction(List<SharedConfig.ProxyInfo> r6) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L14
        Iterator<SharedConfig.ProxyInfo> r62 = r6.iterator();     // Catch: Throwable -> L14
        boolean r1 = false;
        boolean r2 = false;
    L4:
        if (r62.hasNext() == false) goto L16;
        if (isPinned(r62.next()) == false) goto L9;
        r1 = true;
    L10:
        if (r1 == false) goto L4;
        if (r2 == false) goto L4;
        monitor-exit(this);
        return 0;
    L9:
        r2 = true;
        goto L10
    L16:
        if (r1 == false) goto L20;
        monitor-exit(this);
        return 2;
    L20:
        if (r2 == false) goto L23;
        monitor-exit(this);
        return 1;
    L23:
        monitor-exit(this);
        return 0;
    L14:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isPinned(String r2) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L8
        if (TextUtils.isEmpty(r2) == true) goto L10;
        if (this.pinnedProxies.contains(r2) == false) goto L10;
        boolean r22 = true;
    L11:
        monitor-exit(this);
        return r22;
    L10:
        r22 = false;
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isPinned(SharedConfig.ProxyInfo r1) {
        monitor-enter(this);
        if (r1 != null) goto L13;
    L10:
        boolean r12 = false;
    L11:
        monitor-exit(this);
        return r12;
    L13:
        if (isPinned(getProxyKey(r1)) == false) goto L10;
        r12 = true;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized void loadProxyList() {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void moveMetadata(String r4, String r5) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L13
        if (TextUtils.isEmpty(r4) == false) goto L6;
    L27:
        monitor-exit(this);
        return;
    L6:
        if (TextUtils.isEmpty(r5) == true) goto L27;
        if (TextUtils.equals(r4, r5) == true) goto L27;
        String r0 = this.proxyNames.remove(r4);     // Catch: Throwable -> L13
        boolean r2 = true;
        if (TextUtils.isEmpty(r0) == true) goto L15;
        this.proxyNames.put(r5, r0);     // Catch: Throwable -> L13
        boolean r02 = true;
    L16:
        int r1 = this.pinnedProxyOrder.indexOf(r4);     // Catch: Throwable -> L13
        if (r1 < 0) goto L22;
        this.pinnedProxyOrder.remove(r1);     // Catch: Throwable -> L13
        this.pinnedProxies.remove(r4);     // Catch: Throwable -> L13
        if (this.pinnedProxyOrder.contains(r5) == true) goto L21;
        ArrayList<String> r42 = this.pinnedProxyOrder;     // Catch: Throwable -> L13
        r42.add(Math.min(r1, r42.size()), r5);     // Catch: Throwable -> L13
    L21:
        this.pinnedProxies.add(r5);     // Catch: Throwable -> L13
    L23:
        if (r2 == false) goto L25;
        save();     // Catch: Throwable -> L13
    L25:
        monitor-exit(this);
        return;
    L22:
        r2 = r02;
        goto L23
    L15:
        r02 = false;
    L13:
        th = move-exception;
        throw th;
    }

    public synchronized boolean movePinnedProxy(SharedConfig.ProxyInfo r4, SharedConfig.ProxyInfo r5) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L23
        String r42 = getProxyKey(r4);     // Catch: Throwable -> L23
        String r52 = getProxyKey(r5);     // Catch: Throwable -> L23
        if (TextUtils.isEmpty(r42) == false) goto L6;
    L29:
        monitor-exit(this);
        return false;
    L6:
        if (TextUtils.isEmpty(r52) == true) goto L29;
        if (TextUtils.equals(r42, r52) == true) goto L29;
        if (this.pinnedProxies.contains(r42) == true) goto L13;
    L27:
        monitor-exit(this);
        return false;
    L13:
        if (this.pinnedProxies.contains(r52) == false) goto L27;
        int r0 = this.pinnedProxyOrder.indexOf(r42);     // Catch: Throwable -> L23
        int r53 = this.pinnedProxyOrder.indexOf(r52);     // Catch: Throwable -> L23
        if (r0 < 0) goto L25;
        if (r53 < 0) goto L25;
        this.pinnedProxyOrder.remove(r0);     // Catch: Throwable -> L23
        this.pinnedProxyOrder.add(r53, r42);     // Catch: Throwable -> L23
        save();     // Catch: Throwable -> L23
        monitor-exit(this);
        return true;
    L25:
        monitor-exit(this);
        return false;
    L23:
        th = move-exception;
        throw th;
    }

    public synchronized void removeProxy(SharedConfig.ProxyInfo r3) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L15
        String r32 = getProxyKey(r3);     // Catch: Throwable -> L15
        if (TextUtils.isEmpty(r32) == false) goto L8;
        monitor-exit(this);
        return;
    L8:
        if (this.proxyNames.remove(r32) == null) goto L10;
        boolean r0 = true;
    L11:
        boolean r02 = r0 | this.pinnedProxies.remove(r32);     // Catch: Throwable -> L15
        if ((this.pinnedProxyOrder.remove(r32) | r02) == false) goto L17;
        save();     // Catch: Throwable -> L15
    L17:
        monitor-exit(this);
        return;
    L10:
        r0 = false;
    L15:
        th = move-exception;
        throw th;
    }

    public void requestProxyCountry(SharedConfig.ProxyInfo r3, ProxyCountryCallback r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        String r2 = LocaleController.getString(R.string.Unknown);
        String r32 = getProxyCountryKey(r3);
        if (TextUtils.isEmpty(r32) == false) goto L8;
        AndroidUtilities.runOnUIThread(new ProxyController$$ExternalSyntheticLambda2(r4, r2));
        return;
    L8:
        Utilities.globalQueue.postRunnable(new ProxyController$$ExternalSyntheticLambda3(r32, r4, r2));
    }

    public synchronized SharedConfig.ProxyInfo saveProxy(SharedConfig.ProxyInfo r2, String r3, String r4) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L8
        if (TextUtils.isEmpty(r3) == false) goto L6;
    L10:
        SharedConfig.ProxyInfo r22 = addProxy(r2);     // Catch: Throwable -> L8
        if (r4 == null) goto L13;
        setName(r22, r4);     // Catch: Throwable -> L8
    L13:
        monitor-exit(this);
        return r22;
    L6:
        if (TextUtils.equals(r3, r2.settings.getLink()) == true) goto L10;
        moveMetadata(r3, r2.settings.getLink());     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized void saveProxyList() {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L10
        ArrayList r0 = new ArrayList(this.proxyList);     // Catch: Throwable -> L10
        Collections.sort(r0, new ProxyController$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L10
        SerializedData r1 = new SerializedData();     // Catch: Throwable -> L10
        r1.writeInt32(-1);     // Catch: Throwable -> L10
        r1.writeByte(5);     // Catch: Throwable -> L10
        int r2 = r0.size();     // Catch: Throwable -> L10
        r1.writeInt32(r2);     // Catch: Throwable -> L10
        int r22 = r2 - 1;     // Catch: Throwable -> L10
    L4:
        boolean r4 = false;
        if (r22 < 0) goto L12;
        SharedConfig.ProxyInfo r5 = (SharedConfig.ProxyInfo) r0.get(r22);     // Catch: Throwable -> L10
        r1.writeString(r5.settings.getAddress());     // Catch: Throwable -> L10
        r1.writeInt32(r5.settings.getPort());     // Catch: Throwable -> L10
        r1.writeString(r5.settings.getUser());     // Catch: Throwable -> L10
        r1.writeString(r5.settings.getPassword());     // Catch: Throwable -> L10
        r1.writeString(r5.settings.getSecret());     // Catch: Throwable -> L10
        r1.writeInt64(r5.ping);     // Catch: Throwable -> L10
        r1.writeInt64(r5.availableCheckTime);     // Catch: Throwable -> L10
        if (r5.settings.getType() != ProxySettings.Type.WEB) goto L9;
        r4 = true;
    L9:
        r1.writeBool(r4);     // Catch: Throwable -> L10
        r22 = r22 - 1;
        goto L4
    L12:
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putString("proxy_list", Base64.encodeToString(r1.toByteArray(), 2)).apply();     // Catch: Throwable -> L10
        r1.cleanup();     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public synchronized void setCurrentProxy(SharedConfig.ProxyInfo r1) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L6
        this.currentProxy = r1;     // Catch: Throwable -> L6
        SharedConfig.currentProxy = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void setName(String r4, String r5) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L14
        if (TextUtils.isEmpty(r4) == false) goto L7;
        monitor-exit(this);
        return;
    L7:
        String r52 = normalizeName(r5);     // Catch: Throwable -> L14
        boolean r0 = TextUtils.isEmpty(r52);     // Catch: Throwable -> L14
        HashMap<String, String> r1 = this.proxyNames;
        boolean r2 = true;
        if (r0 == true) goto L11;
        r2 = true ^ TextUtils.equals(r1.put(r4, r52), r52);     // Catch: Throwable -> L14
    L17:
        if (r2 == false) goto L19;
        save();     // Catch: Throwable -> L14
    L19:
        monitor-exit(this);
        return;
    L11:
        if (r1.remove(r4) != null) goto L17;
        r2 = false;
    L14:
        th = move-exception;
        throw th;
    }

    public synchronized void setName(SharedConfig.ProxyInfo r1, String r2) {
        monitor-enter(this);
        setName(getProxyKey(r1), r2);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void sortProxyList(List<SharedConfig.ProxyInfo> r3, boolean r4, SharedConfig.ProxyInfo r5) {
        monitor-enter(this);
        ensureLoaded();     // Catch: Throwable -> L6
        Collections.sort(r3, new ProxyController$$ExternalSyntheticLambda1(this, r5, r4, new ArrayList(r3)));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
