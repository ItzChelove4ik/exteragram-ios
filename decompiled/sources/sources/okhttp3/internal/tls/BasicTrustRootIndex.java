package okhttp3.internal.tls;

import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lokhttp3/internal/tls/BasicTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "caCerts", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/security/cert/X509Certificate;", "<init>", "([Ljava/security/cert/X509Certificate;)V", "subjectToCaCerts", _UrlKt.FRAGMENT_ENCODE_SET, "Ljavax/security/auth/x500/X500Principal;", _UrlKt.FRAGMENT_ENCODE_SET, "findByIssuerAndSignature", "cert", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBasicTrustRootIndex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BasicTrustRootIndex.kt\nokhttp3/internal/tls/BasicTrustRootIndex\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,55:1\n382#2,7:56\n295#3,2:63\n*S KotlinDebug\n*F\n+ 1 BasicTrustRootIndex.kt\nokhttp3/internal/tls/BasicTrustRootIndex\n*L\n30#1:56,7\n39#1:63,2\n*E\n"})
/* loaded from: classes5.dex */
public final class BasicTrustRootIndex implements TrustRootIndex {
    private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts;

    public BasicTrustRootIndex(X509Certificate... r7) {
        LinkedHashMap r0 = new LinkedHashMap();
        int r1 = r7.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        X509Certificate r3 = r7[r2];
        X500Principal r4 = r3.getSubjectX500Principal();
        Object r5 = r0.get(r4);
        if (r5 != null) goto L7;
        r5 = new LinkedHashSet();
        r0.put(r4, r5);
    L7:
        ((Set) r5).add(r3);
        r2 = r2 + 1;
        goto L3
    L8:
        this.subjectToCaCerts = r0;
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L4;
        return true;
    L4:
        if ((r2 instanceof BasicTrustRootIndex) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(((BasicTrustRootIndex) r2).subjectToCaCerts, this.subjectToCaCerts) == true) goto L13;
        return false;
    L13:
        return true;
    }

    @Override // okhttp3.internal.tls.TrustRootIndex
    public X509Certificate findByIssuerAndSignature(X509Certificate r4) {
        X500Principal r0 = r4.getIssuerX500Principal();
        Set<X509Certificate> r3 = this.subjectToCaCerts.get(r0);
        Object r02 = null;
        if (r3 != null) goto L5;
        return null;
    L5:
        Iterator<T> r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L12;
        Object r1 = r32.next();
        r4.verify(((X509Certificate) r1).getPublicKey());     // Catch: Exception -> L13
        r02 = r1;
    L12:
        return (X509Certificate) r02;
    }

    public int hashCode() {
        return this.subjectToCaCerts.hashCode();
    }
}
