package okhttp3;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.url._UrlKt;
import okio.ByteString;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 $2\u00020\u0001:\u0003\"#$B#\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J)\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00120\u0015H\u0000¢\u0006\u0002\b\u0017J)\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u0018\"\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u000f\u001a\u00020\u0010J\u0015\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006%"}, d2 = {"Lokhttp3/CertificatePinner;", _UrlKt.FRAGMENT_ENCODE_SET, "pins", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "<init>", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getPins", "()Ljava/util/Set;", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "check", _UrlKt.FRAGMENT_ENCODE_SET, "hostname", _UrlKt.FRAGMENT_ENCODE_SET, "peerCertificates", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/security/cert/Certificate;", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "Ljava/security/cert/X509Certificate;", "check$okhttp", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "findMatchingPins", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "Pin", "Builder", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCertificatePinner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CertificatePinner.kt\nokhttp3/CertificatePinner\n+ 2 -UtilCommon.kt\nokhttp3/internal/_UtilCommonKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,378:1\n360#2,8:379\n1563#3:387\n1634#3,3:388\n*S KotlinDebug\n*F\n+ 1 CertificatePinner.kt\nokhttp3/CertificatePinner\n*L\n224#1:379,8\n154#1:387\n154#1:388,3\n*E\n"})
/* loaded from: classes.dex */
public final class CertificatePinner {
    public static final Companion Companion = null;

    @JvmField
    public static final CertificatePinner DEFAULT = null;
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\f\"\u00020\u000b¢\u0006\u0002\u0010\rJ\u0006\u0010\u000e\u001a\u00020\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lokhttp3/CertificatePinner$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "pins", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/CertificatePinner$Pin;", "getPins", "()Ljava/util/List;", "add", "pattern", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", "build", "Lokhttp3/CertificatePinner;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Builder {
        private final List<Pin> pins;

        public Builder() {
            this.pins = new ArrayList();
        }

        public final Builder add(String r6, String... r7) {
            int r0 = r7.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            String r2 = r7[r1];
            this.pins.add(new Pin(r6, r2));
            r1 = r1 + 1;
            goto L3
        L5:
            return this;
        }

        public final CertificatePinner build() {
            return new CertificatePinner(CollectionsKt.toSet(this.pins), null, 2, null);
        }

        public final List<Pin> getPins() {
            return this.pins;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0007J\f\u0010\t\u001a\u00020\u0007*\u00020\bH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lokhttp3/CertificatePinner$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", PluginsConstants.Strategy.DEFAULT, "Lokhttp3/CertificatePinner;", "sha1Hash", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "sha256Hash", "pin", _UrlKt.FRAGMENT_ENCODE_SET, "certificate", "Ljava/security/cert/Certificate;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCertificatePinner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CertificatePinner.kt\nokhttp3/CertificatePinner$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,378:1\n1#2:379\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final String pin(Certificate r3) {
            if ((r3 instanceof X509Certificate) == true) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Certificate pinning requires X509 certificates");
            return null;
        L5:
            return "sha256/" + sha256Hash((X509Certificate) r3).base64();
        }

        @JvmStatic
        public final ByteString sha1Hash(X509Certificate r7) {
            return ByteString.Companion.of$default(ByteString.Companion, r7.getPublicKey().getEncoded(), 0, 0, 3, null).sha1();
        }

        @JvmStatic
        public final ByteString sha256Hash(X509Certificate r7) {
            return ByteString.Companion.of$default(ByteString.Companion, r7.getPublicKey().getEncoded(), 0, 0, 3, null).sha256();
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\u0013\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lokhttp3/CertificatePinner$Pin;", _UrlKt.FRAGMENT_ENCODE_SET, "pattern", _UrlKt.FRAGMENT_ENCODE_SET, "pin", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPattern", "()Ljava/lang/String;", "hashAlgorithm", "getHashAlgorithm", "hash", "Lokio/ByteString;", "getHash", "()Lokio/ByteString;", "matchesHostname", _UrlKt.FRAGMENT_ENCODE_SET, "hostname", "matchesCertificate", "certificate", "Ljava/security/cert/X509Certificate;", "toString", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Pin {
        private final ByteString hash;
        private final String hashAlgorithm;
        private final String pattern;

        public Pin(String r12, String r13) {
            if (StringsKt.startsWith$default(r12, "*.", false, 2, null) == false) goto L7;
            String r5 = r12;
            if (StringsKt.indexOf$default(r5, "*", 1, false, 4, null) != (-1)) goto L9;
        L14:
            String r122 = _HostnamesCommonKt.toCanonicalHost(r5);
            if (r122 == null) goto L34;
            this.pattern = r122;
            if (StringsKt.startsWith$default(r13, "sha1/", false, 2, null) == false) goto L25;
            this.hashAlgorithm = "sha1";
            ByteString r123 = ByteString.Companion.decodeBase64(r13.substring(5));
            if (r123 == null) goto L22;
            this.hash = r123;
            return;
        L22:
            f$$ExternalSyntheticBUOutline1.m("Invalid pin hash: ".concat(r13));
            throw null;
        L25:
            if (StringsKt.startsWith$default(r13, "sha256/", false, 2, null) == false) goto L32;
            this.hashAlgorithm = "sha256";
            ByteString r124 = ByteString.Companion.decodeBase64(r13.substring(7));
            if (r124 == null) goto L30;
            this.hash = r124;
            return;
        L30:
            f$$ExternalSyntheticBUOutline1.m("Invalid pin hash: ".concat(r13));
            throw null;
        L32:
            Native$$ExternalSyntheticBUOutline5.m("pins must start with 'sha256/' or 'sha1/': ", r13);
            throw null;
        L34:
            Native$$ExternalSyntheticBUOutline5.m("Invalid pattern: ", r5);
            throw null;
        L9:
            if (StringsKt.startsWith$default(r5, "**.", false, 2, null) == false) goto L13;
            if (StringsKt.indexOf$default(r5, "*", 2, false, 4, null) == (-1)) goto L14;
        L13:
            if (StringsKt.indexOf$default(r5, "*", 0, false, 6, null) == (-1)) goto L14;
            Options$Companion$$ExternalSyntheticBUOutline0.m("Unexpected pattern: ", r5);
            throw null;
        L7:
            r5 = r12;
            goto L9
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Pin) == true) goto L8;
            return false;
        L8:
            Pin r52 = (Pin) r5;
            if (Intrinsics.areEqual(this.pattern, r52.pattern) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.hashAlgorithm, r52.hashAlgorithm) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.hash, r52.hash) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public final ByteString getHash() {
            return this.hash;
        }

        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        public final String getPattern() {
            return this.pattern;
        }

        public int hashCode() {
            return (((this.pattern.hashCode() * 31) + this.hashAlgorithm.hashCode()) * 31) + this.hash.hashCode();
        }

        public final boolean matchesCertificate(X509Certificate r3) {
            String r0 = this.hashAlgorithm;
            if (Intrinsics.areEqual(r0, "sha256") == false) goto L7;
            return Intrinsics.areEqual(this.hash, CertificatePinner.Companion.sha256Hash(r3));
        L7:
            if (Intrinsics.areEqual(r0, "sha1") == true) goto L9;
            return false;
        L9:
            return Intrinsics.areEqual(this.hash, CertificatePinner.Companion.sha1Hash(r3));
        }

        public final boolean matchesHostname(String r15) {
            boolean r0 = StringsKt.startsWith$default(this.pattern, "**.", false, 2, null);
            String r1 = this.pattern;
            if (r0 == false) goto L11;
            int r10 = r1.length() - 3;
            int r02 = r15.length() - r10;
            if (StringsKt.regionMatches$default(r15, r15.length() - r10, this.pattern, 3, r10, false, 16, null) == false) goto L10;
            if (r02 != 0) goto L8;
        L9:
            return true;
        L8:
            if (r15.charAt(r02 - 1) == '.') goto L9;
        L10:
            return false;
        L11:
            boolean r152 = StringsKt.startsWith$default(r1, "*.", false, 2, null);
            String r03 = this.pattern;
            if (r152 == false) goto L20;
            int r102 = r03.length() - 1;
            int r153 = r15.length() - r102;
            if (StringsKt.regionMatches$default(r15, r15.length() - r102, this.pattern, 1, r102, false, 16, null) == true) goto L16;
        L18:
            return false;
        L16:
            if (StringsKt.lastIndexOf$default(r15, '.', r153 - 1, false, 4, null) != (-1)) goto L18;
            return true;
        L20:
            return Intrinsics.areEqual(r15, r03);
        }

        public String toString() {
            return this.hashAlgorithm + '/' + this.hash.base64();
        }
    }

    public static List $r8$lambda$RSwGGZvOCYXTltt0QzhfCcMJ_AM(CertificatePinner r0, List r1, String r2) {
        CertificateChainCleaner r02 = r0.certificateChainCleaner;
        if (r02 == null) goto L8;
        List<Certificate> r03 = r02.clean(r1, r2);
        if (r03 == null) goto L8;
        r1 = r03;
    L8:
        List r12 = r1;
        ArrayList r04 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r12, 10));
        Iterator r13 = r12.iterator();
    L10:
        if (r13.hasNext() == false) goto L12;
        r04.add((X509Certificate) ((Certificate) r13.next()));
        goto L10
    L12:
        return r04;
    }

    static {
        Companion = new Companion(null);
        DEFAULT = new Builder().build();
    }

    public CertificatePinner(Set<Pin> r1, CertificateChainCleaner r2) {
        this.pins = r1;
        this.certificateChainCleaner = r2;
    }

    public /* synthetic */ CertificatePinner(Set r1, CertificateChainCleaner r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    @JvmStatic
    public static final String pin(Certificate r1) {
        return Companion.pin(r1);
    }

    @JvmStatic
    public static final ByteString sha1Hash(X509Certificate r1) {
        return Companion.sha1Hash(r1);
    }

    @JvmStatic
    public static final ByteString sha256Hash(X509Certificate r1) {
        return Companion.sha256Hash(r1);
    }

    public final void check(String r2, List<? extends Certificate> r3) {
        check$okhttp(r2, new CertificatePinner$$ExternalSyntheticLambda0(this, r3, r2));
    }

    @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(expression = "check(hostname, peerCertificates.toList())", imports = {}))
    public final void check(String r1, Certificate... r2) {
        check(r1, ArraysKt.toList(r2));
    }

    public final void check$okhttp(String r9, Function0<? extends List<? extends X509Certificate>> r10) {
        List<Pin> r8 = findMatchingPins(r9);
        if (r8.isEmpty() == true) goto L24;
        List<? extends X509Certificate> r102 = r10.invoke();
        Iterator<? extends X509Certificate> r0 = r102.iterator();
    L7:
        if (r0.hasNext() == false) goto L27;
        X509Certificate r1 = r0.next();
        Iterator<Pin> r2 = r8.iterator();
        ByteString r3 = null;
        ByteString r4 = null;
    L10:
        if (r2.hasNext() == false) goto L7;
        Pin r5 = r2.next();
        String r6 = r5.getHashAlgorithm();
        if (Intrinsics.areEqual(r6, "sha256") == true) goto L13;
        if (Intrinsics.areEqual(r6, "sha1") == false) goto L26;
        if (r4 != null) goto L23;
        r4 = Companion.sha1Hash(r1);
    L23:
        if (Intrinsics.areEqual(r5.getHash(), r4) == false) goto L10;
        return;
    L26:
        throw new AssertionError("unsupported hashAlgorithm: " + r5.getHashAlgorithm());
    L13:
        if (r3 != null) goto L16;
        r3 = Companion.sha256Hash(r1);
    L16:
        if (Intrinsics.areEqual(r5.getHash(), r3) == false) goto L10;
        return;
    L27:
        StringBuilder r02 = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
        Iterator<? extends X509Certificate> r103 = r102.iterator();
    L29:
        if (r103.hasNext() == false) goto L31;
        X509Certificate r12 = r103.next();
        r02.append("\n    ");
        r02.append(Companion.pin(r12));
        r02.append(": ");
        r02.append(r12.getSubjectDN().getName());
        goto L29
    L31:
        r02.append("\n  Pinned certificates for ");
        r02.append(r9);
        r02.append(":");
        Iterator<Pin> r82 = r8.iterator();
    L33:
        if (r82.hasNext() == false) goto L36;
        Pin r92 = r82.next();
        r02.append("\n    ");
        r02.append(r92);
        goto L33
    L36:
        throw new SSLPeerUnverifiedException(r02.toString());
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof CertificatePinner) == false) goto L10;
        CertificatePinner r32 = (CertificatePinner) r3;
        if (Intrinsics.areEqual(r32.pins, this.pins) == true) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(r32.certificateChainCleaner, this.certificateChainCleaner) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public final List<Pin> findMatchingPins(String r4) {
        Set<Pin> r3 = this.pins;
        List<Pin> r0 = CollectionsKt.emptyList();
        Iterator<T> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L11;
        Object r1 = r32.next();
        if (((Pin) r1).matchesHostname(r4) == false) goto L4;
        if (r0.isEmpty() == false) goto L10;
        r0 = new ArrayList();
    L10:
        TypeIntrinsics.asMutableList(r0).add(r1);
        goto L4
    L11:
        return r0;
    }

    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public final Set<Pin> getPins() {
        return this.pins;
    }

    public int hashCode() {
        int r1 = (1517 + this.pins.hashCode()) * 41;
        CertificateChainCleaner r2 = this.certificateChainCleaner;
        if (r2 == null) goto L5;
        int r22 = r2.hashCode();
    L7:
        return r1 + r22;
    L5:
        r22 = 0;
        goto L7
    }

    public final CertificatePinner withCertificateChainCleaner$okhttp(CertificateChainCleaner r2) {
        if (Intrinsics.areEqual(this.certificateChainCleaner, r2) == false) goto L6;
        return this;
    L6:
        return new CertificatePinner(this.pins, r2);
    }
}
