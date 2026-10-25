package com.exteragram.messenger.proxy;

import android.net.Uri;
import android.os.SystemClock;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import com.sun.jna.Callback;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;

@Metadata(d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001%\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002+,B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u00042\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH\u0007¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0011\u001a\u00020\u00042\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u000f\"\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ!\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*¨\u0006-"}, d2 = {"Lcom/exteragram/messenger/proxy/IpAddressInfoController;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "url", "extractIpAddress", "(Ljava/lang/String;)Ljava/lang/String;", "ipAddress", "Lorg/telegram/messenger/Utilities$Callback;", "Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;", Callback.METHOD_NAME, "Lokhttp3/Call;", "requestIpAddressInfo", "(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)Lokhttp3/Call;", _UrlKt.FRAGMENT_ENCODE_SET, "values", "joinInfo", "([Ljava/lang/String;)Ljava/lang/String;", "host", _UrlKt.FRAGMENT_ENCODE_SET, "isIpAddress", "(Ljava/lang/String;)Z", "Lcom/exteragram/messenger/proxy/IpAddressInfoController$CacheEntry;", "getCachedInfo", "(Ljava/lang/String;)Lcom/exteragram/messenger/proxy/IpAddressInfoController$CacheEntry;", "info", _UrlKt.FRAGMENT_ENCODE_SET, "putCachedInfo", "(Ljava/lang/String;Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;)V", PluginsConstants.RESPONSE, "parseIpAddressInfo", "(Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;", "Lkotlin/text/Regex;", "ipv4Regex", "Lkotlin/text/Regex;", "ipv6CharsRegex", "com/exteragram/messenger/proxy/IpAddressInfoController$cache$1", "cache", "Lcom/exteragram/messenger/proxy/IpAddressInfoController$cache$1;", "Lokhttp3/OkHttpClient;", "httpClient", "Lokhttp3/OkHttpClient;", "IpAddressInfo", "CacheEntry", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIpAddressInfoController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IpAddressInfoController.kt\ncom/exteragram/messenger/proxy/IpAddressInfoController\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,231:1\n29#2:232\n3938#3:233\n4474#3,2:234\n1786#4,3:236\n*S KotlinDebug\n*F\n+ 1 IpAddressInfoController.kt\ncom/exteragram/messenger/proxy/IpAddressInfoController\n*L\n76#1:232\n169#1:233\n169#1:234,2\n182#1:236,3\n*E\n"})
/* loaded from: classes4.dex */
public final class IpAddressInfoController {
    public static final IpAddressInfoController INSTANCE = null;
    private static final IpAddressInfoController$cache$1 cache = null;
    private static final OkHttpClient httpClient = null;
    private static final Regex ipv4Regex = null;
    private static final Regex ipv6CharsRegex = null;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/exteragram/messenger/proxy/IpAddressInfoController$CacheEntry;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;", "info", _UrlKt.FRAGMENT_ENCODE_SET, "time", "ttl", "<init>", "(Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;JJ)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;", "getInfo", "()Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;", "J", "getTime", "()J", "getTtl", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CacheEntry {
        private final IpAddressInfo info;
        private final long time;
        private final long ttl;

        public CacheEntry(IpAddressInfo r1, long r2, long r4) {
            this.info = r1;
            this.time = r2;
            this.ttl = r4;
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof CacheEntry) == true) goto L8;
            return false;
        L8:
            CacheEntry r82 = (CacheEntry) r8;
            if (Intrinsics.areEqual(this.info, r82.info) == true) goto L12;
            return false;
        L12:
            if (this.time == r82.time) goto L15;
            return false;
        L15:
            if (this.ttl == r82.ttl) goto L17;
            return false;
        L17:
            return true;
        }

        public final IpAddressInfo getInfo() {
            return this.info;
        }

        public final long getTime() {
            return this.time;
        }

        public final long getTtl() {
            return this.ttl;
        }

        public int hashCode() {
            IpAddressInfo r0 = this.info;
            if (r0 != null) goto L5;
            int r02 = 0;
        L7:
            return (((r02 * 31) + Long.hashCode(this.time)) * 31) + Long.hashCode(this.ttl);
        L5:
            r02 = r0.hashCode();
            goto L7
        }

        public String toString() {
            return "CacheEntry(info=" + this.info + ", time=" + this.time + ", ttl=" + this.ttl + ")";
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0017R\u0014\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017R\u0014\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0017R\u0014\u0010\b\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0017R\u0014\u0010\t\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0017R\u0014\u0010\n\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0017R\u0014\u0010\u000b\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/exteragram/messenger/proxy/IpAddressInfoController$IpAddressInfo;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "ip", "country", "countryCode", "region", "city", "isp", "organization", "domain", "timezone", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class IpAddressInfo {

        @JvmField
        public final String city;

        @JvmField
        public final String country;

        @JvmField
        public final String countryCode;

        @JvmField
        public final String domain;

        @JvmField
        public final String ip;

        @JvmField
        public final String isp;

        @JvmField
        public final String organization;

        @JvmField
        public final String region;

        @JvmField
        public final String timezone;

        public IpAddressInfo(String r1, String r2, String r3, String r4, String r5, String r6, String r7, String r8, String r9) {
            this.ip = r1;
            this.country = r2;
            this.countryCode = r3;
            this.region = r4;
            this.city = r5;
            this.isp = r6;
            this.organization = r7;
            this.domain = r8;
            this.timezone = r9;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof IpAddressInfo) == true) goto L8;
            return false;
        L8:
            IpAddressInfo r52 = (IpAddressInfo) r5;
            if (Intrinsics.areEqual(this.ip, r52.ip) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.country, r52.country) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.countryCode, r52.countryCode) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.region, r52.region) == true) goto L21;
            return false;
        L21:
            if (Intrinsics.areEqual(this.city, r52.city) == true) goto L24;
            return false;
        L24:
            if (Intrinsics.areEqual(this.isp, r52.isp) == true) goto L27;
            return false;
        L27:
            if (Intrinsics.areEqual(this.organization, r52.organization) == true) goto L30;
            return false;
        L30:
            if (Intrinsics.areEqual(this.domain, r52.domain) == true) goto L33;
            return false;
        L33:
            if (Intrinsics.areEqual(this.timezone, r52.timezone) == true) goto L35;
            return false;
        L35:
            return true;
        }

        public int hashCode() {
            return (((((((((((((((this.ip.hashCode() * 31) + this.country.hashCode()) * 31) + this.countryCode.hashCode()) * 31) + this.region.hashCode()) * 31) + this.city.hashCode()) * 31) + this.isp.hashCode()) * 31) + this.organization.hashCode()) * 31) + this.domain.hashCode()) * 31) + this.timezone.hashCode();
        }

        public String toString() {
            return "IpAddressInfo(ip=" + this.ip + ", country=" + this.country + ", countryCode=" + this.countryCode + ", region=" + this.region + ", city=" + this.city + ", isp=" + this.isp + ", organization=" + this.organization + ", domain=" + this.domain + ", timezone=" + this.timezone + ")";
        }
    }

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/exteragram/messenger/proxy/IpAddressInfoController$requestIpAddressInfo$1", "Lokhttp3/Callback;", "onFailure", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.proxy.IpAddressInfoController$requestIpAddressInfo$1, reason: invalid class name */
    public static final class AnonymousClass1 implements okhttp3.Callback {
        final /* synthetic */ Utilities.Callback<IpAddressInfo> $callback;
        final /* synthetic */ String $ipAddress;

        public AnonymousClass1(String r1, Utilities.Callback<IpAddressInfo> r2) {
            this.$ipAddress = r1;
            this.$callback = r2;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call r2, IOException r3) {
            if (r2.isCanceled() == false) goto L5;
            return;
        L5:
            FileLog.e(r3);
            IpAddressInfoController.access$putCachedInfo(IpAddressInfoController.INSTANCE, this.$ipAddress, null);
            this.$callback.run(null);
        }

        @Override // okhttp3.Callback
        public void onResponse(Call r5, Response r6) {
            /*  JADX ERROR: Simple mode code generation failed
                java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
                	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
                	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
                	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
                	at java.base/java.util.Objects.checkIndex(Objects.java:385)
                	at java.base/java.util.ArrayList.get(ArrayList.java:427)
                	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:361)
                	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                */
            /*
                this = this;
                java.lang.String r0 = r4.$ipAddress
                org.telegram.messenger.Utilities$Callback<com.exteragram.messenger.proxy.IpAddressInfoController$IpAddressInfo> r4 = r4.$callback
                r1 = 0
                boolean r2 = r6.isSuccessful()     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1c
                if (r2 == 0) goto L20
                com.exteragram.messenger.proxy.IpAddressInfoController r2 = com.exteragram.messenger.proxy.IpAddressInfoController.INSTANCE     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1c
                okhttp3.ResponseBody r3 = r6.body()     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1c
                java.lang.String r3 = r3.string()     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1c
                com.exteragram.messenger.proxy.IpAddressInfoController$IpAddressInfo r2 = com.exteragram.messenger.proxy.IpAddressInfoController.access$parseIpAddressInfo(r2, r0, r3)     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1c
                goto L21
            L1a:
                r4 = move-exception
                goto L35
            L1c:
                r2 = move-exception
                org.telegram.messenger.FileLog.e(r2)     // Catch: java.lang.Throwable -> L1a
            L20:
                r2 = r1
            L21:
                boolean r5 = r5.isCanceled()     // Catch: java.lang.Throwable -> L1a
                if (r5 != 0) goto L2f
                com.exteragram.messenger.proxy.IpAddressInfoController r5 = com.exteragram.messenger.proxy.IpAddressInfoController.INSTANCE     // Catch: java.lang.Throwable -> L1a
                com.exteragram.messenger.proxy.IpAddressInfoController.access$putCachedInfo(r5, r0, r2)     // Catch: java.lang.Throwable -> L1a
                r4.run(r2)     // Catch: java.lang.Throwable -> L1a
            L2f:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L1a
                kotlin.io.CloseableKt.closeFinally(r6, r1)
                return
            L35:
                throw r4     // Catch: java.lang.Throwable -> L36
            L36:
                r5 = move-exception
                kotlin.io.CloseableKt.closeFinally(r6, r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.exteragram.messenger.proxy.IpAddressInfoController.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
        }
    }

    static {
        INSTANCE = new IpAddressInfoController();
        ipv4Regex = new Regex("^\\d{1,3}(?:\\.\\d{1,3}){3}$");
        ipv6CharsRegex = new Regex("^[0-9a-f:.]+$", RegexOption.IGNORE_CASE);
        cache = new IpAddressInfoController$cache$1();
        OkHttpClient.Builder r0 = ExteraHttpClient.INSTANCE.getClient().newBuilder();
        TimeUnit r3 = TimeUnit.SECONDS;
        httpClient = r0.connectTimeout(4, r3).readTimeout(4, r3).writeTimeout(4, r3).build();
    }

    private IpAddressInfoController() {
    }

    public static final /* synthetic */ IpAddressInfo access$parseIpAddressInfo(IpAddressInfoController r0, String r1, String r2) {
        return r0.parseIpAddressInfo(r1, r2);
    }

    public static final /* synthetic */ void access$putCachedInfo(IpAddressInfoController r0, String r1, IpAddressInfo r2) {
        r0.putCachedInfo(r1, r2);
    }

    @JvmStatic
    public static final String extractIpAddress(String r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        if (r5.length() != 0) goto L47;
        return null;
    L47:
        String r4 = Uri.parse(r5).getHost();     // Catch: Exception -> L14
        if (r4 == null) goto L11;
    L17:
        if (r4 != null) goto L19;
        return null;
    L19:
        if (r4.length() != 0) goto L22;
        return null;
    L22:
        if (StringsKt.startsWith$default(r4, "[", false, 2, null) == false) goto L29;
        if (StringsKt.endsWith$default(r4, "]", false, 2, null) == false) goto L29;
        if (r4.length() <= 2) goto L29;
        r4 = r4.substring(1, r4.length() - 1);
    L29:
        if (INSTANCE.isIpAddress(r4) == true) goto L49;
        return null;
    L49:
        InetAddress r52 = InetAddress.getByName(r4);     // Catch: Exception -> L44
        if (r52.isAnyLocalAddress() == false) goto L34;
        return null;
    L34:
        if (r52.isLoopbackAddress() == false) goto L36;
        return null;
    L36:
        if (r52.isLinkLocalAddress() == false) goto L38;
        return null;
    L38:
        if (r52.isSiteLocalAddress() == false) goto L40;
        return null;
    L40:
        if (r52.isMulticastAddress() == true) goto L57;
        return r52.getHostAddress();
    L57:
        return null;
    L44:
        e = move-exception;
        FileLog.e(e);
        return null;
    L11:
        if (StringsKt.contains$default(r5, "://", false, 2, null) == true) goto L16;
        r4 = Uri.parse("http://" + r5).getHost();     // Catch: Exception -> L14
    L16:
        r4 = null;
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L16
    }

    private final CacheEntry getCachedInfo(String r7) {
        IpAddressInfoController$cache$1 r6 = cache;
        monitor-enter(r6);
        CacheEntry r0 = (CacheEntry) r6.get(r7);     // Catch: Throwable -> L14
        if (r0 != null) goto L8;
    L6:
        r0 = null;
    L12:
        monitor-exit(r6);
        return r0;
    L8:
        if ((SystemClock.elapsedRealtime() - r0.getTime()) <= r0.getTtl()) goto L12;
        r6.remove(r7);     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }

    private final boolean isIpAddress(String r8) {
        if (r8 != null) goto L5;
    L29:
        return false;
    L5:
        if (r8.length() == 0) goto L29;
        if (StringsKt.indexOf$default(r8, ':', 0, false, 6, null) < 0) goto L12;
        return ipv6CharsRegex.matches(r8);
    L12:
        if (ipv4Regex.matches(r8) == true) goto L14;
        return false;
    L14:
        List r0 = StringsKt.split$default(r8, new char[]{'.'}, false, 0, 6, null);
        if ((r0 instanceof Collection) == true) goto L17;
    L19:
        Iterator r02 = r0.iterator();
    L21:
        if (r02.hasNext() == false) goto L28;
        int r1 = Utilities.parseInt((String) r02.next());
        if (r1 < 0) goto L27;
        if (r1 < 256) goto L21;
    L27:
        return false;
    L28:
        return true;
    L17:
        if (r0.isEmpty() == false) goto L19;
        return true;
    }

    @JvmStatic
    public static final String joinInfo(String... r9) {
        ArrayList r0 = new ArrayList();
        int r1 = r9.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L15;
        String r4 = r9[r3];
        if (r4 != null) goto L7;
    L10:
        boolean r5 = true;
    L11:
        if (r5 == true) goto L13;
        r0.add(r4);
    L13:
        r3 = r3 + 1;
        goto L3
    L7:
        if (r4.length() == 0) goto L10;
        r5 = false;
        goto L11
    L15:
        return CollectionsKt.joinToString$default(r0, ", ", null, null, 0, null, null, 62, null);
    }

    private final IpAddressInfo parseIpAddressInfo(String r12, String r13) {
        JSONObject r11 = new JSONObject(r13);
        if (r11.optBoolean("success", false) == true) goto L6;
        return null;
    L6:
        JSONObject r132 = r11.optJSONObject("connection");
        JSONObject r0 = r11.optJSONObject("timezone");
        String r2 = r11.optString("ip", r12);
        String r122 = r11.optString("country", _UrlKt.FRAGMENT_ENCODE_SET);
        String r4 = r11.optString("country_code", _UrlKt.FRAGMENT_ENCODE_SET);
        String r5 = r11.optString("region", _UrlKt.FRAGMENT_ENCODE_SET);
        String r6 = r11.optString("city", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r132 == null) goto L12;
        String r112 = r132.optString("isp", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r112 == null) goto L12;
        String r7 = r112;
    L13:
        if (r132 == null) goto L18;
        String r113 = r132.optString("org", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r113 == null) goto L18;
        String r8 = r113;
    L19:
        if (r132 == null) goto L24;
        String r114 = r132.optString("domain", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r114 == null) goto L24;
        String r9 = r114;
    L25:
        if (r0 == null) goto L31;
        String r115 = r0.optString("id", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r115 == null) goto L31;
        String r10 = r115;
    L33:
        return new IpAddressInfo(r2, r122, r4, r5, r6, r7, r8, r9, r10);
    L31:
        r10 = _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        r8 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    }

    private final void putCachedInfo(String r7, IpAddressInfo r8) {
        IpAddressInfoController$cache$1 r6 = cache;
        monitor-enter(r6);
        long r2 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L12
        if (r8 == null) goto L8;
        long r4 = DurationKt.MILLIS_IN_DAY;
    L9:
        r6.put(r7, new CacheEntry(r8, r2, r4));     // Catch: Throwable -> L12
        Unit r72 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r6);
        return;
    L8:
        r4 = 300000;
    L12:
        th = move-exception;
        throw th;
    }

    @JvmStatic
    public static final Call requestIpAddressInfo(String r3, Utilities.Callback<IpAddressInfo> r4) {
        CacheEntry r0 = INSTANCE.getCachedInfo(r3);
        if (r0 == null) goto L6;
        r4.run(r0.getInfo());
        return null;
    L6:
        HttpUrl r02 = HttpUrl.Companion.parse("https://ipwho.is");
        if (r02 == null) goto L13;
        HttpUrl.Builder r03 = r02.newBuilder();
        if (r03 == null) goto L13;
        HttpUrl.Builder r04 = r03.addPathSegment(r3);
        if (r04 == null) goto L13;
        HttpUrl r05 = r04.build();
    L14:
        if (r05 != null) goto L17;
        r4.run(null);
        return null;
    L17:
        Call r06 = httpClient.newCall(new Request.Builder().url(r05).build());
        r06.enqueue(new AnonymousClass1(r3, r4));
        return r06;
    L13:
        r05 = null;
        goto L14
    }
}
