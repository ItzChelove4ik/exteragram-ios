package com.exteragram.messenger.adblock.backend;

import android.content.SharedPreferences;
import android.util.Base64;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public class ScriptletsManager {
    private static final Map<String, ScriptletInfo> SCRIPTLETS_MAP = null;
    private static ScriptletsManager instance;
    private final OkHttpClient client;
    private final Object lock;
    private final SharedPreferences prefs;
    private final DispatchQueue queue;

    /* renamed from: com.exteragram.messenger.adblock.backend.ScriptletsManager$1, reason: invalid class name */
    public class AnonymousClass1 extends HashMap<String, ScriptletInfo> {
        public AnonymousClass1() {
            put("1x1.gif", new ScriptletInfo("1x1-transparent.gif"));
            put("2x2.png", new ScriptletInfo("2x2-transparent.png"));
            put("3x2.png", new ScriptletInfo("3x2-transparent.png"));
            put("32x32.png", new ScriptletInfo("32x32-transparent.png"));
            put("amazon_ads.js", new ScriptletInfo("amazon-adsystem.com/aax2/amzn_ads.js"));
            put("amazon_apstag.js", new ScriptletInfo(null));
            put("ampproject_v0.js", new ScriptletInfo("ampproject.org/v0.js"));
            put("chartbeat.js", new ScriptletInfo("static.chartbeat.com/chartbeat.js"));
            put("doubleclick_instream_ad_status.js", new ScriptletInfo("doubleclick.net/instream/ad_status.js"));
            put("empty", new ScriptletInfo(null));
            put("fingerprint2.js", new ScriptletInfo(null));
            put("fingerprint3.js", new ScriptletInfo(null));
            put("google-analytics_analytics.js", new ScriptletInfo(new String[]{"google-analytics.com/analytics.js", "googletagmanager_gtm.js", "googletagmanager.com/gtm.js"}));
            put("google-analytics_cx_api.js", new ScriptletInfo("google-analytics.com/cx/api.js"));
            put("google-analytics_ga.js", new ScriptletInfo("google-analytics.com/ga.js"));
            put("google-analytics_inpage_linkid.js", new ScriptletInfo("google-analytics.com/inpage_linkid.js"));
            put("google-ima.js", new ScriptletInfo("google-ima3"));
            put("googlesyndication_adsbygoogle.js", new ScriptletInfo(new String[]{"googlesyndication.com/adsbygoogle.js", "googlesyndication-adsbygoogle"}));
            put("googletagservices_gpt.js", new ScriptletInfo(new String[]{"googletagservices.com/gpt.js", "googletagservices-gpt"}));
            put("hd-main.js", new ScriptletInfo(null));
            put("nobab2.js", new ScriptletInfo(null));
            put("noeval.js", new ScriptletInfo(null));
            put("noeval-silent.js", new ScriptletInfo("silent-noeval.js"));
            put("nofab.js", new ScriptletInfo("fuckadblock.js-3.2.0"));
            put("noop-0.1s.mp3", new ScriptletInfo(new String[]{"noopmp3-0.1s", "abp-resource:blank-mp3"}));
            put("noop-0.5s.mp3", new ScriptletInfo(null));
            put("noop-1s.mp4", new ScriptletInfo(new String[]{"noopmp4-1s", "abp-resource:blank-mp4"}));
            put("noop.css", new ScriptletInfo(null));
            put("noop.html", new ScriptletInfo("noopframe"));
            put("noop.js", new ScriptletInfo(new String[]{"noopjs", "abp-resource:blank-js"}));
            put("noop.json", new ScriptletInfo(new String[]{"noopjson"}));
            put("noop.txt", new ScriptletInfo("nooptext"));
            put("noop-vast2.xml", new ScriptletInfo("noopvast-2.0"));
            put("noop-vast3.xml", new ScriptletInfo("noopvast-3.0"));
            put("noop-vast4.xml", new ScriptletInfo("noopvast-4.0"));
            put("noop-vmap1.xml", new ScriptletInfo(new String[]{"noop-vmap1.0.xml", "noopvmap-1.0"}));
            put("outbrain-widget.js", new ScriptletInfo("widgets.outbrain.com/outbrain.js"));
            put("popads.js", new ScriptletInfo(new String[]{"popads.net.js", "prevent-popads-net.js"}));
            put("popads-dummy.js", new ScriptletInfo(null));
            put("prebid-ads.js", new ScriptletInfo(null));
            put("scorecardresearch_beacon.js", new ScriptletInfo("scorecardresearch.com/beacon.js"));
            put("sensors-analytics.js", new ScriptletInfo(null));
            put("nitropay_ads.js", new ScriptletInfo(null));
        }
    }

    public static class Scriptlet {
        public final List<String> aliases;
        public final String content;
        public final String filename;

        private Scriptlet(String r1, List<String> r2, String r3) {
            this.filename = r1;
            this.aliases = r2;
            this.content = r3;
        }

        public /* synthetic */ Scriptlet(String r1, List r2, String r3, ScriptletsManagerIA r4) {
            this(r1, r2, r3);
        }
    }

    public static class ScriptletInfo {
        final Object alias;

        public ScriptletInfo(Object r1) {
            this.alias = r1;
        }
    }

    public static /* synthetic */ void $r8$lambda$7FMBwQ17heqJ6aKfwlFCy3qfSzk(ScriptletsManager r0, Utilities.Callback r1) {
        r0.lambda$download$0(r1);
    }

    static {
        SCRIPTLETS_MAP = new AnonymousClass1();
    }

    public ScriptletsManager() {
        this.lock = new Object();
        this.queue = new DispatchQueue("ScriptletsManager");
        this.client = ExteraHttpClient.INSTANCE.getClient();
        this.prefs = PreferencesUtils.getPreferences("ublock_scriptlets");
    }

    private boolean downloadScriptlet(String r9, ScriptletInfo r10) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.prefs.contains(r9) == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return true;
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        Response r02 = this.client.newCall(new Request.Builder().url("https://raw.githubusercontent.com/gorhill/uBlock/master/src/web_accessible_resources/" + r9).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4 Safari/605.1.15").build()).execute();     // Catch: IOException -> L49
    L42:
        th = move-exception;
        if (r02 != null) goto L57;
    L48:
        throw th;     // Catch: IOException -> L49
    L57:
        r02.close();     // Catch: Throwable -> L46
    L46:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L49
        goto L48
    L15:
        if (r02.code() != 404) goto L19;
    L16:
        r02.close();     // Catch: IOException -> L49
        return true;
    L19:
        if (r02.isSuccessful() == true) goto L22;
        r02.close();     // Catch: IOException -> L49
        return false;
    L22:
        String r3 = Base64.encodeToString(r02.body().bytes(), 2);     // Catch: Throwable -> L42
        Object r4 = this.lock;     // Catch: Throwable -> L42
        monitor-enter(r4);     // Catch: Throwable -> L42
        SharedPreferences.Editor r8 = this.prefs.edit();     // Catch: Throwable -> L29
        r8.putString(r9, r3);     // Catch: Throwable -> L29
        if (r10.alias == null) goto L37;
        JSONArray r32 = new JSONArray();     // Catch: Throwable -> L29
        Object r102 = r10.alias;     // Catch: Throwable -> L29
        if ((r102 instanceof String) == false) goto L32;
        r32.put(r102);     // Catch: Throwable -> L29
    L36:
        r8.putString(r9 + "_aliases", r32.toString());     // Catch: Throwable -> L29
        goto L37
    L32:
        if ((r102 instanceof String[]) == false) goto L36;
        String[] r103 = (String[]) r102;     // Catch: Throwable -> L29
        int r5 = r103.length;     // Catch: Throwable -> L29
        int r6 = 0;
    L34:
        if (r6 >= r5) goto L36;
        r32.put(r103[r6]);     // Catch: Throwable -> L29
        r6 = r6 + 1;     // Catch: Throwable -> L29
    L37:
        r8.apply();     // Catch: Throwable -> L29
        monitor-exit(r4);     // Catch: Throwable -> L29
    L29:
        th = move-exception;
        throw th;     // Catch: Throwable -> L42
    L49:
        return false;
    }

    public static String getExtension(String r2) {
        if (r2.endsWith(".css") == false) goto L6;
        return ".css";
    L6:
        if (r2.endsWith(".gif") == false) goto L9;
        return ".gif";
    L9:
        if (r2.endsWith(".html") == false) goto L12;
        return ".html";
    L12:
        if (r2.endsWith(".js") == false) goto L15;
        return ".js";
    L15:
        if (r2.endsWith(".json") == false) goto L18;
        return ".json";
    L18:
        if (r2.endsWith(".mp3") == false) goto L21;
        return ".mp3";
    L21:
        if (r2.endsWith(".mp4") == false) goto L24;
        return ".mp4";
    L24:
        if (r2.endsWith(".png") == false) goto L27;
        return ".png";
    L27:
        if (r2.endsWith(".txt") == false) goto L30;
        return ".txt";
    L30:
        if (r2.endsWith(".xml") == false) goto L32;
        return ".xml";
    L32:
        return ".txt";
    }

    public static ScriptletsManager getInstance() {
        if (instance != null) goto L6;
        instance = new ScriptletsManager();
    L6:
        return instance;
    }

    private /* synthetic */ void lambda$download$0(Utilities.Callback r5) {
        Iterator<Map.Entry<String, ScriptletInfo>> r0 = SCRIPTLETS_MAP.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        Map.Entry<String, ScriptletInfo> r1 = r0.next();
        if (downloadScriptlet(r1.getKey(), r1.getValue()) == true) goto L4;
        boolean r02 = false;
    L9:
        if (r02 == false) goto L18;
        Object r12 = this.lock;
        monitor-enter(r12);
        this.prefs.edit().putBoolean("__downloaded", true).apply();     // Catch: Throwable -> L15
        monitor-exit(r12);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;
    L18:
        if (r5 == null) goto L26;
        r5.run(Boolean.valueOf(r02));
        return;
    L26:
        return;
    L8:
        r02 = true;
        goto L9
    }

    public void download(Utilities.Callback<Boolean> r3) {
        this.queue.postRunnable(new ScriptletsManager$$ExternalSyntheticLambda0(this, r3));
    }

    public boolean isDownloaded() {
        Object r0 = this.lock;
        monitor-enter(r0);
        boolean r3 = this.prefs.getBoolean("__downloaded", false);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r3;
    L7:
        th = move-exception;
        throw th;
    }

    public Collection<Scriptlet> iterScriptlets() {
        ArrayList r0 = new ArrayList();
        Object r1 = this.lock;
        monitor-enter(r1);
        Iterator<Map.Entry<String, ScriptletInfo>> r2 = SCRIPTLETS_MAP.entrySet().iterator();     // Catch: Throwable -> L16
    L6:
        if (r2.hasNext() == false) goto L19;
        String r3 = r2.next().getKey();     // Catch: Throwable -> L16
        String r4 = this.prefs.getString(r3, null);     // Catch: Throwable -> L16
        if (r4 == null) goto L6;
        ArrayList r6 = new ArrayList();     // Catch: Throwable -> L16
        String r7 = this.prefs.getString(r3 + "_aliases", null);     // Catch: Throwable -> L16
        if (r7 != null) goto L27;
    L18:
        r0.add(new Scriptlet(r3, r6, r4, null));     // Catch: Throwable -> L16
        goto L6
    L27:
        JSONArray r8 = new JSONArray(r7);     // Catch: Throwable -> L16 JSONException -> L24
        int r72 = 0;
    L12:
        if (r72 >= r8.length()) goto L18;
        r6.add(r8.getString(r72));     // Catch: Throwable -> L16 JSONException -> L24
        r72 = r72 + 1;
        goto L12
    L19:
        monitor-exit(r1);     // Catch: Throwable -> L16
        return Collections.unmodifiableList(r0);
    L16:
        th = move-exception;
        throw th;
    }
}
