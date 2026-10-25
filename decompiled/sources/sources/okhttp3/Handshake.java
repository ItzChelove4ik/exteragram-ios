package okhttp3;

import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 '2\u00020\u0001:\u0001'B;\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0013J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u0014J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\b\u0015J\u000f\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007¢\u0006\u0002\b\u0019J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\b\u001aJ\u000f\u0010\u001b\u001a\u0004\u0018\u00010\u0017H\u0007¢\u0006\u0002\b\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000eR\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00078GX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u00178G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u00178G¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0018R\u0018\u0010$\u001a\u00020#*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006("}, d2 = {"Lokhttp3/Handshake;", _UrlKt.FRAGMENT_ENCODE_SET, "tlsVersion", "Lokhttp3/TlsVersion;", "cipherSuite", "Lokhttp3/CipherSuite;", "localCertificates", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/security/cert/Certificate;", "peerCertificatesFn", "Lkotlin/Function0;", "<init>", "(Lokhttp3/TlsVersion;Lokhttp3/CipherSuite;Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "()Lokhttp3/TlsVersion;", "()Lokhttp3/CipherSuite;", "()Ljava/util/List;", "peerCertificates", "peerCertificates$delegate", "Lkotlin/Lazy;", "-deprecated_tlsVersion", "-deprecated_cipherSuite", "-deprecated_peerCertificates", "peerPrincipal", "Ljava/security/Principal;", "()Ljava/security/Principal;", "-deprecated_peerPrincipal", "-deprecated_localCertificates", "localPrincipal", "-deprecated_localPrincipal", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "name", "getName", "(Ljava/security/cert/Certificate;)Ljava/lang/String;", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHandshake.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Handshake.kt\nokhttp3/Handshake\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,201:1\n1563#2:202\n1634#2,3:203\n1563#2:206\n1634#2,3:207\n*S KotlinDebug\n*F\n+ 1 Handshake.kt\nokhttp3/Handshake\n*L\n131#1:202\n131#1:203,3\n136#1:206\n136#1:207,3\n*E\n"})
/* loaded from: classes.dex */
public final class Handshake {
    public static final Companion Companion = null;
    private final CipherSuite cipherSuite;
    private final List<Certificate> localCertificates;
    private final Lazy peerCertificates$delegate;
    private final TlsVersion tlsVersion;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007¢\u0006\u0002\b\u0007J\u0015\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\tJ4\u0010\u0007\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0007¨\u0006\u0012"}, d2 = {"Lokhttp3/Handshake$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "handshake", "Lokhttp3/Handshake;", "Ljavax/net/ssl/SSLSession;", "get", "sslSession", "-deprecated_get", "tlsVersion", "Lokhttp3/TlsVersion;", "cipherSuite", "Lokhttp3/CipherSuite;", "peerCertificates", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/security/cert/Certificate;", "localCertificates", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHandshake.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Handshake.kt\nokhttp3/Handshake$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,201:1\n1#2:202\n*E\n"})
    public static final class Companion {
        /* renamed from: $r8$lambda$F-DAdzZ0-GIq_4EBIY5iXePxZEk, reason: not valid java name */
        public static List m3649$r8$lambda$FDAdzZ0GIq_4EBIY5iXePxZEk(List r0) {
            return r0;
        }

        public static List $r8$lambda$RewCqWe1pMEXUZjNBn5RyHT4nM0(List r0) {
            return r0;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "sslSession.handshake()", imports = {}))
        @JvmName(name = "-deprecated_get")
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final Handshake m3650deprecated_get(SSLSession r1) {
            return get(r1);
        }

        @JvmStatic
        @JvmName(name = "get")
        public final Handshake get(SSLSession r5) {
            String r4 = r5.getCipherSuite();
            if (r4 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("cipherSuite == null");
            return null;
        L5:
            if (Intrinsics.areEqual(r4, "TLS_NULL_WITH_NULL_NULL") == false) goto L7;
        L22:
            Model$$ExternalSyntheticBUOutline0.m("cipherSuite == ".concat(r4));
            return null;
        L7:
            if (Intrinsics.areEqual(r4, "SSL_NULL_WITH_NULL_NULL") == true) goto L22;
            CipherSuite r42 = CipherSuite.Companion.forJavaName(r4);
            String r1 = r5.getProtocol();
            if (r1 != null) goto L11;
            Segment$$ExternalSyntheticBUOutline1.m("tlsVersion == null");
            return null;
        L11:
            if (Intrinsics.areEqual("NONE", r1) == true) goto L18;
            TlsVersion r0 = TlsVersion.Companion.forJavaName(r1);
            List r12 = _UtilJvmKt.toImmutableList(r5.getPeerCertificates());     // Catch: SSLPeerUnverifiedException -> L15
        L17:
            return new Handshake(r0, r42, _UtilJvmKt.toImmutableList(r5.getLocalCertificates()), new Handshake$Companion$$ExternalSyntheticLambda1(r12));
        L15:
            r12 = CollectionsKt.emptyList();
            goto L17
        L18:
            Model$$ExternalSyntheticBUOutline0.m("tlsVersion == NONE");
            return null;
        }

        @JvmStatic
        public final Handshake get(TlsVersion r2, CipherSuite r3, List<? extends Certificate> r4, List<? extends Certificate> r5) {
            List r1 = _UtilJvmKt.toImmutableList(r4);
            return new Handshake(r2, r3, _UtilJvmKt.toImmutableList(r5), new Handshake$Companion$$ExternalSyntheticLambda0(r1));
        }
    }

    public static List $r8$lambda$ylNSEJzJ6KhhUA02we3dSIu3FaU(Function0 r0) {
        return (List) r0.invoke();
    L5:
        return CollectionsKt.emptyList();
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Handshake(TlsVersion r1, CipherSuite r2, List<? extends Certificate> r3, Function0<? extends List<? extends Certificate>> r4) {
        this.tlsVersion = r1;
        this.cipherSuite = r2;
        this.localCertificates = r3;
        this.peerCertificates$delegate = LazyKt.lazy(new Handshake$$ExternalSyntheticLambda0(r4));
    }

    @JvmStatic
    @JvmName(name = "get")
    public static final Handshake get(SSLSession r1) {
        return Companion.get(r1);
    }

    @JvmStatic
    public static final Handshake get(TlsVersion r1, CipherSuite r2, List<? extends Certificate> r3, List<? extends Certificate> r4) {
        return Companion.get(r1, r2, r3, r4);
    }

    private final String getName(Certificate r1) {
        if ((r1 instanceof X509Certificate) == false) goto L7;
        return ((X509Certificate) r1).getSubjectDN().toString();
    L7:
        return r1.getType();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cipherSuite", imports = {}))
    @JvmName(name = "-deprecated_cipherSuite")
    /* renamed from: -deprecated_cipherSuite, reason: not valid java name */
    public final CipherSuite m3643deprecated_cipherSuite() {
        return this.cipherSuite;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "localCertificates", imports = {}))
    @JvmName(name = "-deprecated_localCertificates")
    /* renamed from: -deprecated_localCertificates, reason: not valid java name */
    public final List<Certificate> m3644deprecated_localCertificates() {
        return this.localCertificates;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "localPrincipal", imports = {}))
    @JvmName(name = "-deprecated_localPrincipal")
    /* renamed from: -deprecated_localPrincipal, reason: not valid java name */
    public final Principal m3645deprecated_localPrincipal() {
        return localPrincipal();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "peerCertificates", imports = {}))
    @JvmName(name = "-deprecated_peerCertificates")
    /* renamed from: -deprecated_peerCertificates, reason: not valid java name */
    public final List<Certificate> m3646deprecated_peerCertificates() {
        return peerCertificates();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "peerPrincipal", imports = {}))
    @JvmName(name = "-deprecated_peerPrincipal")
    /* renamed from: -deprecated_peerPrincipal, reason: not valid java name */
    public final Principal m3647deprecated_peerPrincipal() {
        return peerPrincipal();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "tlsVersion", imports = {}))
    @JvmName(name = "-deprecated_tlsVersion")
    /* renamed from: -deprecated_tlsVersion, reason: not valid java name */
    public final TlsVersion m3648deprecated_tlsVersion() {
        return this.tlsVersion;
    }

    @JvmName(name = "cipherSuite")
    public final CipherSuite cipherSuite() {
        return this.cipherSuite;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof Handshake) == false) goto L14;
        Handshake r32 = (Handshake) r3;
        if (r32.tlsVersion == this.tlsVersion) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(r32.cipherSuite, this.cipherSuite) == true) goto L9;
        return false;
    L9:
        if (Intrinsics.areEqual(r32.peerCertificates(), peerCertificates()) == true) goto L11;
        return false;
    L11:
        if (Intrinsics.areEqual(r32.localCertificates, this.localCertificates) == false) goto L19;
        return true;
    L19:
        return false;
    L14:
        return false;
    }

    public int hashCode() {
        return ((((((527 + this.tlsVersion.hashCode()) * 31) + this.cipherSuite.hashCode()) * 31) + peerCertificates().hashCode()) * 31) + this.localCertificates.hashCode();
    }

    @JvmName(name = "localCertificates")
    public final List<Certificate> localCertificates() {
        return this.localCertificates;
    }

    @JvmName(name = "localPrincipal")
    public final Principal localPrincipal() {
        Object r2 = CollectionsKt.firstOrNull(this.localCertificates);
        if ((r2 instanceof X509Certificate) == false) goto L5;
        X509Certificate r22 = (X509Certificate) r2;
    L6:
        if (r22 != null) goto L8;
        return null;
    L8:
        return r22.getSubjectX500Principal();
    L5:
        r22 = null;
        goto L6
    }

    @JvmName(name = "peerCertificates")
    public final List<Certificate> peerCertificates() {
        return (List) this.peerCertificates$delegate.getValue();
    }

    @JvmName(name = "peerPrincipal")
    public final Principal peerPrincipal() {
        Object r2 = CollectionsKt.firstOrNull(peerCertificates());
        if ((r2 instanceof X509Certificate) == false) goto L5;
        X509Certificate r22 = (X509Certificate) r2;
    L6:
        if (r22 != null) goto L8;
        return null;
    L8:
        return r22.getSubjectX500Principal();
    L5:
        r22 = null;
        goto L6
    }

    @JvmName(name = "tlsVersion")
    public final TlsVersion tlsVersion() {
        return this.tlsVersion;
    }

    public String toString() {
        List<Certificate> r0 = peerCertificates();
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        Iterator<T> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        r1.add(getName((Certificate) r02.next()));
        goto L4
    L6:
        String r03 = r1.toString();
        StringBuilder r12 = new StringBuilder("Handshake{tlsVersion=");
        r12.append(this.tlsVersion);
        r12.append(" cipherSuite=");
        r12.append(this.cipherSuite);
        r12.append(" peerCertificates=");
        r12.append(r03);
        r12.append(" localCertificates=");
        List<Certificate> r04 = this.localCertificates;
        ArrayList r3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r04, 10));
        Iterator<T> r05 = r04.iterator();
    L8:
        if (r05.hasNext() == false) goto L10;
        r3.add(getName((Certificate) r05.next()));
        goto L8
    L10:
        r12.append(r3);
        r12.append('}');
        return r12.toString();
    }
}
