package com.exteragram.messenger.ai.network;

import android.content.SharedPreferences;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import com.exteragram.messenger.utils.network.RemoteUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import okhttp3.Dns;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes4.dex */
public class ProxyDns implements Dns {
    public static final Dns INSTANCE = null;
    private volatile String cachedUrl;
    private SharedPreferences.OnSharedPreferenceChangeListener changeListener;
    private final OkHttpClient client;
    private volatile boolean listenerInitialized;

    public static /* synthetic */ void $r8$lambda$X00bfXMTjeZLXg65l1Y72GNZ6EE(ProxyDns r0, SharedPreferences r1, String r2) {
        r0.lambda$initializeListener$0(r1, r2);
    }

    static {
        INSTANCE = new ProxyDns();
    }

    private ProxyDns() {
        this.listenerInitialized = false;
        OkHttpClient.Builder r0 = ExteraHttpClient.INSTANCE.getClient().newBuilder();
        TimeUnit r3 = TimeUnit.SECONDS;
        this.client = r0.connectTimeout(5, r3).readTimeout(5, r3).build();
    }

    private byte[] buildDnsQuery(String r8) {
        ByteArrayOutputStream r7 = new ByteArrayOutputStream();
        DataOutputStream r0 = new DataOutputStream(r7);
        r0.writeShort(4660);     // Catch: IOException -> L9
        r0.writeShort(256);     // Catch: IOException -> L9
        r0.writeShort(1);     // Catch: IOException -> L9
        r0.writeShort(0);     // Catch: IOException -> L9
        r0.writeShort(0);     // Catch: IOException -> L9
        r0.writeShort(0);     // Catch: IOException -> L9
        String[] r82 = r8.split("\\.");     // Catch: IOException -> L9
        int r3 = r82.length;     // Catch: IOException -> L9
        int r4 = 0;
    L4:
        if (r4 >= r3) goto L6;
        String r5 = r82[r4];     // Catch: IOException -> L9
        r0.writeByte(r5.length());     // Catch: IOException -> L9
        r0.writeBytes(r5);     // Catch: IOException -> L9
        r4 = r4 + 1;     // Catch: IOException -> L9
        goto L4
    L6:
        r0.writeByte(0);     // Catch: IOException -> L9
        r0.writeShort(1);     // Catch: IOException -> L9
        r0.writeShort(1);     // Catch: IOException -> L9
    L8:
        return r7.toByteArray();
    }

    private String getUrl() {
        if (this.listenerInitialized == true) goto L5;
        initializeListener();
    L5:
        String r0 = this.cachedUrl;
        if (r0 != null) goto L9;
        String r02 = RemoteUtils.getStringConfigValue("dns_url", "https://dns.comss.one/dns-query");
        this.cachedUrl = r02;
        return r02;
    L9:
        return r0;
    }

    private void initializeListener() {
        if (this.listenerInitialized == true) goto L8;
        RemoteUtils.initCached();
        if (RemoteUtils.sharedPreferences == null) goto L9;
        ProxyDns$$ExternalSyntheticLambda0 r0 = new ProxyDns$$ExternalSyntheticLambda0(this);
        this.changeListener = r0;
        RemoteUtils.sharedPreferences.registerOnSharedPreferenceChangeListener(r0);
        this.listenerInitialized = true;
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$initializeListener$0(SharedPreferences r1, String r2) {
        if ("dns_url".equals(r2) == false) goto L6;
        this.cachedUrl = null;
        return;
    }

    private List<String> parseDnsResponse(byte[] r8) {
        ByteBuffer r82 = ByteBuffer.wrap(r8);
        r82.getShort();
        r82.getShort();
        short r0 = r82.getShort();
        short r1 = r82.getShort();
        r82.getShort();
        r82.getShort();
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        skipDomainName(r82);
        r82.getShort();
        r82.getShort();
        r3 = r3 + 1;
        goto L3
    L5:
        ArrayList r02 = new ArrayList();
        int r32 = 0;
    L6:
        if (r32 >= r1) goto L19;
        skipDomainName(r82);
        short r4 = r82.getShort();
        r82.getShort();
        r82.getInt();
        short r5 = r82.getShort();
        if (r4 != 1) goto L17;
        StringBuilder r42 = new StringBuilder();
        int r52 = 0;
    L11:
        if (r52 >= 4) goto L16;
        r42.append(r82.get() & UByte.MAX_VALUE);
        if (r52 >= 3) goto L15;
        r42.append(".");
    L15:
        r52 = r52 + 1;
        goto L11
    L16:
        r02.add(r42.toString());
    L18:
        r32 = r32 + 1;
        goto L6
    L17:
        r82.position(r82.position() + r5);
        goto L18
    L19:
        return r02;
    }

    private List<String> resolveDns(String r4) {
        RequestBody r42 = RequestBody.create(buildDnsQuery(r4), MediaType.parse("application/dns-message"));
        Request r43 = new Request.Builder().url(getUrl()).post(r42).addHeader("Accept", "application/dns-message").build();
        Response r44 = this.client.newCall(r43).execute();     // Catch: IOException -> L23
    L11:
        th = move-exception;
        if (r44 != null) goto L24;
    L20:
        throw th;     // Catch: IOException -> L23
    L24:
        r44.close();     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L23
        goto L20
    L5:
        if (r44.isSuccessful() == true) goto L7;
    L13:
        r44.close();     // Catch: IOException -> L23
        return null;
    L7:
        if (r44.body() == null) goto L13;
        List<String> r3 = parseDnsResponse(r44.body().bytes());     // Catch: Throwable -> L11
        r44.close();     // Catch: IOException -> L23
        return r3;
    L29:
        return null;
    }

    private void skipDomainName(ByteBuffer r3) {
    L2:
        byte r2 = r3.get();
        int r0 = r2 & UByte.MAX_VALUE;
        if ((r2 & 192) == 192) goto L4;
        if (r0 == 0) goto L7;
        r3.position(r3.position() + r0);
        goto L2
    L7:
        return;
    L4:
        r3.get();
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String r3) {
        List<String> r2 = resolveDns(r3);     // Catch: Exception -> L14
        if (r2 == null) goto L13;
        if (r2.isEmpty() == true) goto L13;
        ArrayList r0 = new ArrayList(r2.size());     // Catch: Exception -> L14
        Iterator<String> r22 = r2.iterator();     // Catch: Exception -> L14
    L9:
        if (r22.hasNext() == false) goto L11;
        r0.add(InetAddress.getByName(r22.next()));     // Catch: Exception -> L14
        goto L9
    L11:
        return r0;
    L13:
        return Dns.SYSTEM.lookup(r3);
    L15:
        return Dns.SYSTEM.lookup(r3);
    }
}
