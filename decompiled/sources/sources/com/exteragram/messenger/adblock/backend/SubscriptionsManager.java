package com.exteragram.messenger.adblock.backend;

import android.content.SharedPreferences;
import android.util.Base64;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.url._UrlKt;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DispatchQueue;

/* loaded from: classes.dex */
public class SubscriptionsManager {
    private static SubscriptionsManager instance;
    private static final Pattern redirectPattern = null;
    private final OkHttpClient client;
    private final Object lock;
    private final SharedPreferences prefs;
    private final DispatchQueue queue;

    public static class FilterMetadata {
        public final long expires;
        public final String homepage;
        public final int rulesCount;
        public final String title;
        public final String url;

        /* renamed from: -$$Nest$mtoJson, reason: not valid java name */
        public static /* bridge */ /* synthetic */ JSONObject m1009$$Nest$mtoJson(FilterMetadata r0) {
            return r0.toJson();
        }

        /* renamed from: -$$Nest$smfromJson, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FilterMetadata m1010$$Nest$smfromJson(JSONObject r0) {
            return fromJson(r0);
        }

        private FilterMetadata(String r1, String r2, String r3, int r4, long r5) {
            this.url = r1;
            this.title = r2;
            this.homepage = r3;
            this.rulesCount = r4;
            this.expires = r5;
        }

        public /* synthetic */ FilterMetadata(String r1, String r2, String r3, int r4, long r5, SubscriptionsManagerIA r7) {
            this(r1, r2, r3, r4, r5);
        }

        private static FilterMetadata fromJson(JSONObject r7) {
            return new FilterMetadata(r7.getString("url"), r7.getString("title"), r7.getString("homepage"), r7.getInt("rulesCount"), r7.getLong("expires"));
        }

        private JSONObject toJson() {
            JSONObject r0 = new JSONObject();
            r0.put("url", this.url);
            r0.put("title", this.title);
            r0.put("homepage", this.homepage);
            r0.put("rulesCount", this.rulesCount);
            r0.put("expires", this.expires);
            return r0;
        }
    }

    /* renamed from: $r8$lambda$IRmt47v_HDFeJ0fY0-sq8SCLzqI, reason: not valid java name */
    public static /* synthetic */ void m1008$r8$lambda$IRmt47v_HDFeJ0fY0sq8SCLzqI(SubscriptionsManager r0, String[] r1, Runnable r2) {
        r0.lambda$update$0(r1, r2);
    }

    static {
        redirectPattern = Pattern.compile("!\\s*Redirect:\\s*(\\S+)");
    }

    public SubscriptionsManager() {
        this.lock = new Object();
        this.queue = new DispatchQueue("SubscriptionsManager");
        this.client = ExteraHttpClient.INSTANCE.getClient();
        this.prefs = PreferencesUtils.getPreferences("ublock_subscriptions");
    }

    private long calculateExpiration(String r7) {
        String r6 = extractMetadataValue(r7, "Expires");
        if (r6 != null) goto L19;
        long r62 = System.currentTimeMillis();
    L6:
        return r62 + 432000000;
    L19:
        int r72 = Integer.parseInt(r6.replaceAll("[^0-9]", _UrlKt.FRAGMENT_ENCODE_SET));     // Catch: NumberFormatException -> L18
        if (r6.contains("hour") == false) goto L12;
        return System.currentTimeMillis() + TimeUnit.HOURS.toMillis(r72);
    L12:
        if (r6.contains("day") == false) goto L17;
        return System.currentTimeMillis() + TimeUnit.DAYS.toMillis(r72);
    L17:
        return System.currentTimeMillis() + 432000000;
    L18:
        r62 = System.currentTimeMillis();
        goto L6
    }

    private int countRules(String r5) {
        String[] r4 = r5.split("\n");
        int r52 = r4.length;
        int r0 = 0;
        int r1 = 0;
    L3:
        if (r0 >= r52) goto L10;
        String r2 = r4[r0].trim();
        if (r2.isEmpty() == true) goto L9;
        if (r2.startsWith("!") == true) goto L9;
        r1 = r1 + 1;
    L9:
        r0 = r0 + 1;
        goto L3
    L10:
        return r1;
    }

    private String extractMetadataValue(String r2, String r3) {
        Matcher r1 = Pattern.compile("!\\s*" + r3 + ":\\s*(.+)").matcher(r2);
        if (r1.find() == true) goto L5;
        return null;
    L5:
        return r1.group(1).trim();
    }

    private String extractRedirect(String r1) {
        Matcher r0 = redirectPattern.matcher(r1);
        if (r0.find() == true) goto L5;
        return null;
    L5:
        return r0.group(1);
    }

    private boolean fetchSubscription(String r10, int r11) {
        Response r3 = this.client.newCall(new Request.Builder().url(r10).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4 Safari/605.1.15").build()).execute();     // Catch: Exception -> L56
    L24:
        th = move-exception;
        if (r3 != null) goto L57;
    L55:
        throw th;     // Catch: Exception -> L56
    L57:
        r3.close();     // Catch: Throwable -> L53
    L53:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L56
        goto L55
    L5:
        if (r3.isSuccessful() == true) goto L8;
    L6:
        r3.close();     // Catch: Exception -> L56
        return false;
    L8:
        String r4 = r3.body().string();     // Catch: Throwable -> L24
        String r5 = extractRedirect(r4);     // Catch: Throwable -> L24
        if (r5 != null) goto L11;
        FilterMetadata r112 = parseMetadata(r10, r4);     // Catch: Throwable -> L24
        File r1 = getFileForUrl(r10);     // Catch: Throwable -> L24
        File r52 = new File(r1.getPath() + ".tmp");     // Catch: Throwable -> L24
        FileOutputStream r7 = new FileOutputStream(r52);     // Catch: Throwable -> L24 IOException -> L47
        r7.write(r4.getBytes(StandardCharsets.UTF_8));     // Catch: Throwable -> L41
        r7.close();     // Catch: Throwable -> L24 IOException -> L47
        if (r52.renameTo(r1) == true) goto L33;
        r52.delete();     // Catch: Throwable -> L24
        goto L6
    L33:
        Object r12 = this.lock;     // Catch: Throwable -> L24
        monitor-enter(r12);     // Catch: Throwable -> L24
        SharedPreferences.Editor r9 = this.prefs.edit();     // Catch: Throwable -> L38
        r9.putString("metadata_" + r10, FilterMetadata.m1009$$Nest$mtoJson(r112).toString());     // Catch: Throwable -> L38
        r9.apply();     // Catch: Throwable -> L38
        monitor-exit(r12);     // Catch: Throwable -> L38
    L19:
        r3.close();     // Catch: Exception -> L56
        return true;
    L38:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24
    L41:
        th = move-exception;
        r7.close();     // Catch: Throwable -> L44
    L46:
        throw th;     // Catch: Throwable -> L24 IOException -> L47
    L44:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L24 IOException -> L47
    L47:
        r52.delete();     // Catch: Throwable -> L24
        r3.close();     // Catch: Exception -> L56
        return false;
    L11:
        if (r11 >= 3) goto L6;
        if (fetchSubscription(r5, r11 + 1) == false) goto L6;
        unsubscribe(r10);     // Catch: Throwable -> L24
        Object r113 = this.lock;     // Catch: Throwable -> L24
        monitor-enter(r113);     // Catch: Throwable -> L24
        this.prefs.edit().putString("redirect_" + r10, r5).apply();     // Catch: Throwable -> L21
        monitor-exit(r113);     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24
    L56:
        return false;
    }

    private File getFileForUrl(String r3) {
        File r2 = new File(ApplicationLoader.applicationContext.getFilesDir(), "adblock");
        if (r2.exists() == true) goto L6;
        r2.mkdirs();
    L6:
        return new File(r2, Base64.encodeToString(r3.getBytes(StandardCharsets.UTF_8), 10) + ".txt");
    }

    public static SubscriptionsManager getInstance() {
        if (instance != null) goto L6;
        instance = new SubscriptionsManager();
    L6:
        return instance;
    }

    private /* synthetic */ void lambda$update$0(String[] r10, Runnable r11) {
        LinkedHashSet r0 = new LinkedHashSet();
        long r1 = System.currentTimeMillis();
        Iterator<FilterMetadata> r3 = getSubscriptions().iterator();
    L4:
        if (r3.hasNext() == false) goto L10;
        FilterMetadata r4 = r3.next();
        if (r1 >= r4.expires) goto L9;
        if (getFileForUrl(r4.url).exists() == true) goto L4;
    L9:
        r0.add(r4.url);
        goto L4
    L10:
        Object r12 = this.lock;
        monitor-enter(r12);
        int r2 = r10.length;     // Catch: Throwable -> L19
        int r42 = 0;
    L13:
        if (r42 >= r2) goto L22;
        String r5 = r10[r42];     // Catch: Throwable -> L19
        if (this.prefs.contains("metadata_" + r5) == true) goto L21;
        if (this.prefs.contains("redirect_" + r5) == true) goto L21;
        r0.add(r5);     // Catch: Throwable -> L19
    L21:
        r42 = r42 + 1;     // Catch: Throwable -> L19
        goto L13
    L22:
        monitor-exit(r12);     // Catch: Throwable -> L19
        Iterator r102 = r0.iterator();
    L25:
        if (r102.hasNext() == false) goto L27;
        fetchSubscription((String) r102.next(), 0);
        goto L25
    L27:
        if (r11 == null) goto L45;
        r11.run();
        return;
    L45:
        return;
    L19:
        th = move-exception;
        throw th;
    }

    private FilterMetadata parseMetadata(String r11, String r12) {
        String r0 = extractMetadataValue(r12, "Title");
        String r1 = extractMetadataValue(r12, "Homepage");
        int r6 = countRules(r12);
        long r7 = calculateExpiration(r12);
        if (r0 == null) goto L5;
    L4:
        String r4 = r0;
        if (r1 != null) goto L10;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        return new FilterMetadata(r11, r4, r1, r6, r7, null);
    L5:
        r0 = "Unnamed list";
        goto L4
    }

    private void unsubscribe(String r5) {
        Object r1 = this.lock;
        monitor-enter(r1);
        SharedPreferences.Editor r2 = this.prefs.edit();     // Catch: Throwable -> L10
        r2.remove("metadata_" + r5);     // Catch: Throwable -> L10
        r2.apply();     // Catch: Throwable -> L10
        monitor-exit(r1);     // Catch: Throwable -> L10
        File r4 = getFileForUrl(r5);
        if (r4.exists() == false) goto L15;
        r4.delete();
        return;
    L15:
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public List<String> getSubscriptionFilePaths() {
        ArrayList r0 = new ArrayList();
        Iterator<FilterMetadata> r1 = getSubscriptions().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        File r2 = getFileForUrl(r1.next().url);
        if (r2.exists() == false) goto L4;
        r0.add(r2.getAbsolutePath());
        goto L4
    L8:
        return r0;
    }

    public List<FilterMetadata> getSubscriptions() {
        ArrayList r0 = new ArrayList();
        Object r1 = this.lock;
        monitor-enter(r1);
        Iterator<String> r2 = this.prefs.getAll().keySet().iterator();     // Catch: Throwable -> L13
    L6:
        if (r2.hasNext() == false) goto L15;
        String r3 = r2.next();     // Catch: Throwable -> L13
        if (r3.startsWith("metadata_") == false) goto L6;
        String r32 = this.prefs.getString(r3, null);     // Catch: JSONException -> L20 Throwable -> L13
        if (r32 == null) goto L6;
        r0.add(FilterMetadata.m1010$$Nest$smfromJson(new JSONObject(r32)));     // Catch: JSONException -> L20 Throwable -> L13
        goto L6
    L15:
        monitor-exit(r1);     // Catch: Throwable -> L13
        return Collections.unmodifiableList(r0);
    L13:
        th = move-exception;
        throw th;
    }

    public boolean hasFilters() {
        return !getSubscriptionFilePaths().isEmpty();
    }

    public void update(String[] r3, Runnable r4) {
        this.queue.postRunnable(new SubscriptionsManager$$ExternalSyntheticLambda0(this, r3, r4));
    }
}
