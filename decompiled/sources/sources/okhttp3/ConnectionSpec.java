package okhttp3;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Internal;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 '2\u00020\u0001:\u0002&'B9\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007¢\u0006\u0002\b\u0013J\u0015\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0010H\u0007¢\u0006\u0002\b\u0016J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0017J\u001d\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u001dJ\u0018\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0002J\u000e\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u001bJ\u0013\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0007H\u0016R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00108G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0012R\u0019\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00108G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012¨\u0006("}, d2 = {"Lokhttp3/ConnectionSpec;", _UrlKt.FRAGMENT_ENCODE_SET, "isTls", _UrlKt.FRAGMENT_ENCODE_SET, "supportsTlsExtensions", "cipherSuitesAsString", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "tlsVersionsAsString", "<init>", "(ZZ[Ljava/lang/String;[Ljava/lang/String;)V", "()Z", "getCipherSuitesAsString$okhttp", "()[Ljava/lang/String;", "[Ljava/lang/String;", "cipherSuites", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/CipherSuite;", "()Ljava/util/List;", "-deprecated_cipherSuites", "tlsVersions", "Lokhttp3/TlsVersion;", "-deprecated_tlsVersions", "-deprecated_supportsTlsExtensions", "apply", _UrlKt.FRAGMENT_ENCODE_SET, "sslSocket", "Ljavax/net/ssl/SSLSocket;", "isFallback", "apply$okhttp", "supportedSpec", "isCompatible", "socket", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "Builder", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConnectionSpec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionSpec.kt\nokhttp3/ConnectionSpec\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,384:1\n11561#2:385\n11896#2,3:386\n11561#2:389\n11896#2,3:390\n37#3,2:393\n37#3,2:395\n37#3,2:397\n*S KotlinDebug\n*F\n+ 1 ConnectionSpec.kt\nokhttp3/ConnectionSpec\n*L\n60#1:385\n60#1:386,3\n78#1:389\n78#1:390,3\n349#1:393,2\n361#1:395,2\n374#1:397,2\n*E\n"})
/* loaded from: classes.dex */
public final class ConnectionSpec {
    private static final List<CipherSuite> APPROVED_CIPHER_SUITES = null;

    @JvmField
    public static final ConnectionSpec CLEARTEXT = null;

    @JvmField
    public static final ConnectionSpec COMPATIBLE_TLS = null;
    public static final Companion Companion = null;

    @JvmField
    public static final ConnectionSpec MODERN_TLS = null;
    private static final List<CipherSuite> RESTRICTED_CIPHER_SUITES = null;

    @JvmField
    public static final ConnectionSpec RESTRICTED_TLS = null;
    private final String[] cipherSuitesAsString;
    private final boolean isTls;
    private final boolean supportsTlsExtensions;
    private final String[] tlsVersionsAsString;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\u0006\u0010\u001a\u001a\u00020\u0000J\u001f\u0010\f\u001a\u00020\u00002\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\r\"\u00020\u001b¢\u0006\u0002\u0010\u001cJ\u001f\u0010\f\u001a\u00020\u00002\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000e¢\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\u0000J\u001f\u0010\u0014\u001a\u00020\u00002\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\r\"\u00020\u001f¢\u0006\u0002\u0010 J\u001f\u0010\u0014\u001a\u00020\u00002\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000e¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0003H\u0007J\u0006\u0010!\u001a\u00020\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0005R$\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0017\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\n\"\u0004\b\u0019\u0010\u0005¨\u0006\""}, d2 = {"Lokhttp3/ConnectionSpec$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "tls", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Z)V", "connectionSpec", "Lokhttp3/ConnectionSpec;", "(Lokhttp3/ConnectionSpec;)V", "getTls$okhttp", "()Z", "setTls$okhttp", "cipherSuites", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getCipherSuites$okhttp", "()[Ljava/lang/String;", "setCipherSuites$okhttp", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "tlsVersions", "getTlsVersions$okhttp", "setTlsVersions$okhttp", "supportsTlsExtensions", "getSupportsTlsExtensions$okhttp", "setSupportsTlsExtensions$okhttp", "allEnabledCipherSuites", "Lokhttp3/CipherSuite;", "([Lokhttp3/CipherSuite;)Lokhttp3/ConnectionSpec$Builder;", "([Ljava/lang/String;)Lokhttp3/ConnectionSpec$Builder;", "allEnabledTlsVersions", "Lokhttp3/TlsVersion;", "([Lokhttp3/TlsVersion;)Lokhttp3/ConnectionSpec$Builder;", "build", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nConnectionSpec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionSpec.kt\nokhttp3/ConnectionSpec$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,384:1\n1#2:385\n11561#3:386\n11896#3,3:387\n11561#3:392\n11896#3,3:393\n37#4,2:390\n37#4,2:396\n*S KotlinDebug\n*F\n+ 1 ConnectionSpec.kt\nokhttp3/ConnectionSpec$Builder\n*L\n247#1:386\n247#1:387,3\n269#1:392\n269#1:393,3\n247#1:390,2\n269#1:396,2\n*E\n"})
    public static final class Builder {
        private String[] cipherSuites;
        private boolean supportsTlsExtensions;
        private boolean tls;
        private String[] tlsVersions;

        public Builder(ConnectionSpec r2) {
            this.tls = r2.isTls();
            this.cipherSuites = r2.getCipherSuitesAsString$okhttp();
            this.tlsVersions = ConnectionSpec.access$getTlsVersionsAsString$p(r2);
            this.supportsTlsExtensions = r2.supportsTlsExtensions();
        }

        public Builder(boolean r1) {
            this.tls = r1;
        }

        public final Builder allEnabledCipherSuites() {
            if (this.tls == false) goto L6;
            this.cipherSuites = null;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("no cipher suites for cleartext connections");
            return null;
        }

        public final Builder allEnabledTlsVersions() {
            if (this.tls == false) goto L6;
            this.tlsVersions = null;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("no TLS versions for cleartext connections");
            return null;
        }

        public final ConnectionSpec build() {
            return new ConnectionSpec(this.tls, this.supportsTlsExtensions, this.cipherSuites, this.tlsVersions);
        }

        public final Builder cipherSuites(String... r3) {
            if (this.tls == true) goto L5;
            f$$ExternalSyntheticBUOutline1.m("no cipher suites for cleartext connections");
            return null;
        L5:
            if (r3.length != 0) goto L7;
            boolean r0 = true;
        L8:
            if (r0 == true) goto L11;
            this.cipherSuites = (String[]) Arrays.copyOf(r3, r3.length);
            return this;
        L11:
            f$$ExternalSyntheticBUOutline1.m("At least one cipher suite is required");
            return null;
        L7:
            r0 = false;
            goto L8
        }

        public final Builder cipherSuites(CipherSuite... r6) {
            if (this.tls == false) goto L9;
            ArrayList r0 = new ArrayList(r6.length);
            int r1 = r6.length;
            int r3 = 0;
        L5:
            if (r3 >= r1) goto L7;
            r0.add(r6[r3].javaName());
            r3 = r3 + 1;
            goto L5
        L7:
            String[] r62 = (String[]) r0.toArray(new String[0]);
            return cipherSuites((String[]) Arrays.copyOf(r62, r62.length));
        L9:
            f$$ExternalSyntheticBUOutline1.m("no cipher suites for cleartext connections");
            return null;
        }

        public final String[] getCipherSuites$okhttp() {
            return this.cipherSuites;
        }

        public final boolean getSupportsTlsExtensions$okhttp() {
            return this.supportsTlsExtensions;
        }

        public final boolean getTls$okhttp() {
            return this.tls;
        }

        public final String[] getTlsVersions$okhttp() {
            return this.tlsVersions;
        }

        public final void setCipherSuites$okhttp(String[] r1) {
            this.cipherSuites = r1;
        }

        public final void setSupportsTlsExtensions$okhttp(boolean r1) {
            this.supportsTlsExtensions = r1;
        }

        public final void setTls$okhttp(boolean r1) {
            this.tls = r1;
        }

        public final void setTlsVersions$okhttp(String[] r1) {
            this.tlsVersions = r1;
        }

        @Deprecated(message = "since OkHttp 3.13 all TLS-connections are expected to support TLS extensions.\nIn a future release setting this to true will be unnecessary and setting it to false\nwill have no effect.")
        public final Builder supportsTlsExtensions(boolean r2) {
            if (this.tls == false) goto L6;
            this.supportsTlsExtensions = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("no TLS extensions for cleartext connections");
            return null;
        }

        public final Builder tlsVersions(String... r3) {
            if (this.tls == true) goto L5;
            f$$ExternalSyntheticBUOutline1.m("no TLS versions for cleartext connections");
            return null;
        L5:
            if (r3.length != 0) goto L7;
            boolean r0 = true;
        L8:
            if (r0 == true) goto L11;
            this.tlsVersions = (String[]) Arrays.copyOf(r3, r3.length);
            return this;
        L11:
            f$$ExternalSyntheticBUOutline1.m("At least one TLS version is required");
            return null;
        L7:
            r0 = false;
            goto L8
        }

        public final Builder tlsVersions(TlsVersion... r6) {
            if (this.tls == false) goto L9;
            ArrayList r0 = new ArrayList(r6.length);
            int r1 = r6.length;
            int r3 = 0;
        L5:
            if (r3 >= r1) goto L7;
            r0.add(r6[r3].javaName());
            r3 = r3 + 1;
            goto L5
        L7:
            String[] r62 = (String[]) r0.toArray(new String[0]);
            return tlsVersions((String[]) Arrays.copyOf(r62, r62.length));
        L9:
            f$$ExternalSyntheticBUOutline1.m("no TLS versions for cleartext connections");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/ConnectionSpec$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "RESTRICTED_CIPHER_SUITES", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/CipherSuite;", "APPROVED_CIPHER_SUITES", "RESTRICTED_TLS", "Lokhttp3/ConnectionSpec;", "MODERN_TLS", "COMPATIBLE_TLS", "CLEARTEXT", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        CipherSuite r2 = CipherSuite.TLS_AES_128_GCM_SHA256;
        CipherSuite r3 = CipherSuite.TLS_AES_256_GCM_SHA384;
        CipherSuite r4 = CipherSuite.TLS_CHACHA20_POLY1305_SHA256;
        CipherSuite r5 = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
        CipherSuite r6 = CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
        CipherSuite r7 = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
        CipherSuite r8 = CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
        CipherSuite r9 = CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256;
        CipherSuite r10 = CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256;
        List<CipherSuite> r0 = CollectionsKt.listOf(new CipherSuite[]{r2, r3, r4, r5, r6, r7, r8, r9, r10});
        RESTRICTED_CIPHER_SUITES = r0;
        List<CipherSuite> r1 = CollectionsKt.listOf(new CipherSuite[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA});
        APPROVED_CIPHER_SUITES = r1;
        Builder r22 = new Builder(true);
        CipherSuite[] r02 = (CipherSuite[]) r0.toArray(new CipherSuite[0]);
        Builder r03 = r22.cipherSuites((CipherSuite[]) Arrays.copyOf(r02, r02.length));
        TlsVersion r23 = TlsVersion.TLS_1_3;
        TlsVersion r52 = TlsVersion.TLS_1_2;
        RESTRICTED_TLS = r03.tlsVersions(new TlsVersion[]{r23, r52}).supportsTlsExtensions(true).build();
        Builder r04 = new Builder(true);
        CipherSuite[] r62 = (CipherSuite[]) r1.toArray(new CipherSuite[0]);
        MODERN_TLS = r04.cipherSuites((CipherSuite[]) Arrays.copyOf(r62, r62.length)).tlsVersions(new TlsVersion[]{r23, r52}).supportsTlsExtensions(true).build();
        Builder r05 = new Builder(true);
        CipherSuite[] r12 = (CipherSuite[]) r1.toArray(new CipherSuite[0]);
        COMPATIBLE_TLS = r05.cipherSuites((CipherSuite[]) Arrays.copyOf(r12, r12.length)).tlsVersions(new TlsVersion[]{r23, r52, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0}).supportsTlsExtensions(true).build();
        CLEARTEXT = new Builder(false).build();
    }

    public ConnectionSpec(boolean r1, boolean r2, String[] r3, String[] r4) {
        this.isTls = r1;
        this.supportsTlsExtensions = r2;
        this.cipherSuitesAsString = r3;
        this.tlsVersionsAsString = r4;
    }

    public static final /* synthetic */ String[] access$getTlsVersionsAsString$p(ConnectionSpec r0) {
        return r0.tlsVersionsAsString;
    }

    private final ConnectionSpec supportedSpec(SSLSocket r5, boolean r6) {
        String[] r0 = Internal.effectiveCipherSuites(this, r5.getEnabledCipherSuites());
        if (this.tlsVersionsAsString == null) goto L5;
        String[] r1 = _UtilCommonKt.intersect(r5.getEnabledProtocols(), this.tlsVersionsAsString, ComparisonsKt.naturalOrder());
    L6:
        String[] r52 = r5.getSupportedCipherSuites();
        int r2 = _UtilCommonKt.indexOf(r52, "TLS_FALLBACK_SCSV", CipherSuite.Companion.getORDER_BY_NAME$okhttp());
        if (r6 == false) goto L12;
        if (r2 == (-1)) goto L12;
        r0 = _UtilCommonKt.concat(r0, r52[r2]);
    L12:
        return new Builder(this).cipherSuites((String[]) Arrays.copyOf(r0, r0.length)).tlsVersions((String[]) Arrays.copyOf(r1, r1.length)).build();
    L5:
        r1 = r5.getEnabledProtocols();
        goto L6
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cipherSuites", imports = {}))
    @JvmName(name = "-deprecated_cipherSuites")
    /* renamed from: -deprecated_cipherSuites, reason: not valid java name */
    public final List<CipherSuite> m3629deprecated_cipherSuites() {
        return cipherSuites();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "supportsTlsExtensions", imports = {}))
    @JvmName(name = "-deprecated_supportsTlsExtensions")
    /* renamed from: -deprecated_supportsTlsExtensions, reason: not valid java name */
    public final boolean m3630deprecated_supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "tlsVersions", imports = {}))
    @JvmName(name = "-deprecated_tlsVersions")
    /* renamed from: -deprecated_tlsVersions, reason: not valid java name */
    public final List<TlsVersion> m3631deprecated_tlsVersions() {
        return tlsVersions();
    }

    public final void apply$okhttp(SSLSocket r1, boolean r2) {
        ConnectionSpec r0 = supportedSpec(r1, r2);
        if (r0.tlsVersions() == null) goto L6;
        r1.setEnabledProtocols(r0.tlsVersionsAsString);
    L6:
        if (r0.cipherSuites() == null) goto L9;
        r1.setEnabledCipherSuites(r0.cipherSuitesAsString);
        return;
    }

    @JvmName(name = "cipherSuites")
    public final List<CipherSuite> cipherSuites() {
        String[] r5 = this.cipherSuitesAsString;
        if (r5 == null) goto L8;
        ArrayList r0 = new ArrayList(r5.length);
        int r1 = r5.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        String r3 = r5[r2];
        r0.add(CipherSuite.Companion.forJavaName(r3));
        r2 = r2 + 1;
        goto L5
    L7:
        return r0;
    L8:
        return null;
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof ConnectionSpec) == true) goto L6;
        return false;
    L6:
        if (r5 != this) goto L8;
        return true;
    L8:
        boolean r2 = this.isTls;
        ConnectionSpec r52 = (ConnectionSpec) r5;
        if (r2 == r52.isTls) goto L11;
        return false;
    L11:
        if (r2 == true) goto L13;
    L21:
        return true;
    L13:
        if (Arrays.equals(this.cipherSuitesAsString, r52.cipherSuitesAsString) == true) goto L16;
        return false;
    L16:
        if (Arrays.equals(this.tlsVersionsAsString, r52.tlsVersionsAsString) == true) goto L19;
        return false;
    L19:
        if (this.supportsTlsExtensions == r52.supportsTlsExtensions) goto L21;
        return false;
    }

    public final String[] getCipherSuitesAsString$okhttp() {
        return this.cipherSuitesAsString;
    }

    public int hashCode() {
        if (this.isTls == false) goto L13;
        String[] r0 = this.cipherSuitesAsString;
        int r1 = 0;
        if (r0 == null) goto L7;
        int r02 = Arrays.hashCode(r0);
    L8:
        int r2 = (527 + r02) * 31;
        String[] r03 = this.tlsVersionsAsString;
        if (r03 == null) goto L12;
        r1 = Arrays.hashCode(r03);
    L12:
        return ((r2 + r1) * 31) + (!this.supportsTlsExtensions ? 1 : 0);
    L7:
        r02 = 0;
        goto L8
    L13:
        return 17;
    }

    public final boolean isCompatible(SSLSocket r5) {
        if (this.isTls == true) goto L5;
        return false;
    L5:
        String[] r0 = this.tlsVersionsAsString;
        if (r0 != null) goto L8;
    L10:
        String[] r4 = this.cipherSuitesAsString;
        if (r4 != null) goto L13;
        return true;
    L13:
        if (_UtilCommonKt.hasIntersection(r4, r5.getEnabledCipherSuites(), CipherSuite.Companion.getORDER_BY_NAME$okhttp()) == true) goto L17;
        return false;
    L17:
        return true;
    L8:
        if (_UtilCommonKt.hasIntersection(r0, r5.getEnabledProtocols(), ComparisonsKt.naturalOrder()) == true) goto L10;
        return false;
    }

    @JvmName(name = "isTls")
    public final boolean isTls() {
        return this.isTls;
    }

    @JvmName(name = "supportsTlsExtensions")
    public final boolean supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    @JvmName(name = "tlsVersions")
    public final List<TlsVersion> tlsVersions() {
        String[] r5 = this.tlsVersionsAsString;
        if (r5 == null) goto L8;
        ArrayList r0 = new ArrayList(r5.length);
        int r1 = r5.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        String r3 = r5[r2];
        r0.add(TlsVersion.Companion.forJavaName(r3));
        r2 = r2 + 1;
        goto L5
    L7:
        return r0;
    L8:
        return null;
    }

    public String toString() {
        if (this.isTls == true) goto L7;
        return "ConnectionSpec()";
    L7:
        return "ConnectionSpec(cipherSuites=" + Objects.toString(cipherSuites(), "[all enabled]") + ", tlsVersions=" + Objects.toString(tlsVersions(), "[all enabled]") + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ')';
    }
}
