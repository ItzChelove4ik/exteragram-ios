package okhttp3.internal.tls;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal.url._UrlKt;
import okio.Utf8;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\f\u0010\u0013\u001a\u00020\n*\u00020\nH\u0002J\f\u0010\u0014\u001a\u00020\b*\u00020\nH\u0002J\u001c\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\nH\u0002J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "<init>", "()V", "ALT_DNS_NAME", _UrlKt.FRAGMENT_ENCODE_SET, "ALT_IPA_NAME", "verify", _UrlKt.FRAGMENT_ENCODE_SET, "host", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Ljavax/net/ssl/SSLSession;", "certificate", "Ljava/security/cert/X509Certificate;", "verifyIpAddress", "ipAddress", "verifyHostname", "hostname", "asciiToLowercase", "isAscii", "pattern", "allSubjectAltNames", _UrlKt.FRAGMENT_ENCODE_SET, "getSubjectAltNames", "type", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOkHostnameVerifier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHostnameVerifier.kt\nokhttp3/internal/tls/OkHostnameVerifier\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,224:1\n1761#2,3:225\n1761#2,3:228\n*S KotlinDebug\n*F\n+ 1 OkHostnameVerifier.kt\nokhttp3/internal/tls/OkHostnameVerifier\n*L\n69#1:225,3\n80#1:228,3\n*E\n"})
/* loaded from: classes.dex */
public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE = null;

    static {
        INSTANCE = new OkHostnameVerifier();
    }

    private OkHostnameVerifier() {
    }

    private final String asciiToLowercase(String r1) {
        if (isAscii(r1) == true) goto L5;
        return r1;
    L5:
        return r1.toLowerCase(Locale.US);
    }

    private final List<String> getSubjectAltNames(X509Certificate r4, int r5) {
        Collection<List<?>> r3 = r4.getSubjectAlternativeNames();     // Catch: CertificateParsingException -> L22
        if (r3 == null) goto L5;
        ArrayList r42 = new ArrayList();     // Catch: CertificateParsingException -> L22
        Iterator<List<?>> r32 = r3.iterator();     // Catch: CertificateParsingException -> L22
    L8:
        if (r32.hasNext() == false) goto L21;
        List<?> r0 = r32.next();     // Catch: CertificateParsingException -> L22
        if (r0 == null) goto L8;
        if (r0.size() < 2) goto L8;
        if (Intrinsics.areEqual(r0.get(0), Integer.valueOf(r5)) == false) goto L8;
        Object r02 = r0.get(1);     // Catch: CertificateParsingException -> L22
        if (r02 == null) goto L8;
        r42.add((String) r02);     // Catch: CertificateParsingException -> L22
        goto L8
    L21:
        return r42;
    L5:
        return CollectionsKt.emptyList();
    L23:
        return CollectionsKt.emptyList();
    }

    private final boolean isAscii(String r4) {
        if (r4.length() != ((int) Utf8.size$default(r4, 0, 0, 3, null))) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean verifyHostname(String r14, String r15) {
        if (r14 != null) goto L5;
    L51:
        return false;
    L5:
        if (r14.length() == 0) goto L51;
        if (StringsKt.startsWith$default(r14, ".", false, 2, null) == true) goto L51;
        if (StringsKt.endsWith$default(r14, "..", false, 2, null) == true) goto L51;
        if (r15 == null) goto L51;
        if (r15.length() == 0) goto L51;
        if (StringsKt.startsWith$default(r15, ".", false, 2, null) == true) goto L51;
        if (StringsKt.endsWith$default(r15, "..", false, 2, null) == true) goto L51;
        if (StringsKt.endsWith$default(r14, ".", false, 2, null) == true) goto L24;
        r14 = r14 + '.';
    L24:
        String r6 = r14;
        if (StringsKt.endsWith$default(r15, ".", false, 2, null) == true) goto L27;
        r15 = r15 + '.';
    L27:
        String r7 = asciiToLowercase(r15);
        if (StringsKt.contains$default(r7, "*", false, 2, null) == true) goto L32;
        return Intrinsics.areEqual(r6, r7);
    L32:
        if (StringsKt.startsWith$default(r7, "*.", false, 2, null) == false) goto L51;
        if (StringsKt.indexOf$default(r7, '*', 1, false, 4, null) != (-1)) goto L51;
        if (r6.length() >= r7.length()) goto L40;
        return false;
    L40:
        if (Intrinsics.areEqual("*.", r7) == false) goto L42;
        return false;
    L42:
        String r142 = r7.substring(1);
        if (StringsKt.endsWith$default(r6, r142, false, 2, null) == true) goto L45;
        return false;
    L45:
        int r1 = r6.length() - r142.length();
        if (r1 > 0) goto L48;
    L50:
        return true;
    L48:
        if (StringsKt.lastIndexOf$default(r6, '.', r1 - 1, false, 4, null) == (-1)) goto L50;
        return false;
    }

    private final boolean verifyHostname(String r3, X509Certificate r4) {
        String r32 = asciiToLowercase(r3);
        List<String> r2 = getSubjectAltNames(r4, 2);
        if ((r2 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r22 = r2.iterator();
    L9:
        if (r22.hasNext() == false) goto L14;
        String r42 = (String) r22.next();
        if (INSTANCE.verifyHostname(r32, r42) == false) goto L9;
        return true;
    L14:
        return false;
    L5:
        if (r2.isEmpty() == false) goto L7;
        return false;
    }

    private final boolean verifyIpAddress(String r2, X509Certificate r3) {
        String r22 = _HostnamesCommonKt.toCanonicalHost(r2);
        List<String> r1 = getSubjectAltNames(r3, 7);
        if ((r1 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r12 = r1.iterator();
    L9:
        if (r12.hasNext() == false) goto L14;
        if (Intrinsics.areEqual(r22, _HostnamesCommonKt.toCanonicalHost((String) r12.next())) == false) goto L9;
        return true;
    L14:
        return false;
    L5:
        if (r1.isEmpty() == false) goto L7;
        return false;
    }

    public final List<String> allSubjectAltNames(X509Certificate r3) {
        List<String> r0 = getSubjectAltNames(r3, 7);
        return CollectionsKt.plus(r0, getSubjectAltNames(r3, 2));
    }

    public final boolean verify(String r2, X509Certificate r3) {
        if (_HostnamesCommonKt.canParseAsIpAddress(r2) == false) goto L7;
        return verifyIpAddress(r2, r3);
    L7:
        return verifyHostname(r2, r3);
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String r3, SSLSession r4) {
        if (isAscii(r3) == true) goto L8;
        return false;
    L8:
        return verify(r3, (X509Certificate) r4.getPeerCertificates()[0]);
    L10:
        return false;
    }
}
