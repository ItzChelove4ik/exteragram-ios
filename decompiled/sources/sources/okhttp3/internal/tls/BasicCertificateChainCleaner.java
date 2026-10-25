package okhttp3.internal.tls;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\u0013\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/internal/tls/BasicCertificateChainCleaner;", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "<init>", "(Lokhttp3/internal/tls/TrustRootIndex;)V", "clean", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/security/cert/Certificate;", "chain", "hostname", _UrlKt.FRAGMENT_ENCODE_SET, "verifySignature", _UrlKt.FRAGMENT_ENCODE_SET, "toVerify", "Ljava/security/cert/X509Certificate;", "signingCert", "minIntermediates", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class BasicCertificateChainCleaner extends CertificateChainCleaner {
    public static final Companion Companion = null;
    private static final int MAX_SIGNERS = 9;
    private final TrustRootIndex trustRootIndex;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/tls/BasicCertificateChainCleaner$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MAX_SIGNERS", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public BasicCertificateChainCleaner(TrustRootIndex r1) {
        this.trustRootIndex = r1;
    }

    private final boolean verifySignature(X509Certificate r2, X509Certificate r3, int r4) {
        if (Intrinsics.areEqual(r2.getIssuerDN(), r3.getSubjectDN()) == true) goto L6;
        return false;
    L6:
        if (r3.getBasicConstraints() >= r4) goto L12;
        return false;
    L12:
        r2.verify(r3.getPublicKey());     // Catch: GeneralSecurityException -> L11
        return true;
    L11:
        return false;
    }

    @Override // okhttp3.internal.tls.CertificateChainCleaner
    public List<Certificate> clean(List<? extends Certificate> r8, String r9) {
        ArrayDeque r92 = new ArrayDeque(r8);
        ArrayList r82 = new ArrayList();
        r82.add(r92.removeFirst());
        int r0 = 0;
        boolean r1 = false;
    L4:
        if (r0 >= 9) goto L28;
        X509Certificate r2 = (X509Certificate) r82.get(r82.size() - 1);
        X509Certificate r4 = this.trustRootIndex.findByIssuerAndSignature(r2);
        if (r4 != null) goto L8;
        Iterator r42 = r92.iterator();
    L18:
        if (r42.hasNext() == false) goto L23;
        X509Certificate r5 = (X509Certificate) r42.next();
        if (verifySignature(r2, r5, r82.size() - 1) == false) goto L18;
        r42.remove();
        r82.add(r5);
    L22:
        r0 = r0 + 1;
        goto L4
    L23:
        if (r1 == false) goto L26;
    L24:
        return r82;
    L26:
        throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + r2);
    L8:
        if (r82.size() <= 1) goto L10;
    L11:
        r82.add(r4);
    L13:
        if (verifySignature(r4, r4, r82.size() - 2) == true) goto L24;
        r1 = true;
        goto L22
    L10:
        if (Intrinsics.areEqual(r2, r4) == true) goto L13;
    L28:
        throw new SSLPeerUnverifiedException("Certificate chain too long: " + r82);
    }

    public boolean equals(Object r3) {
        if (r3 != this) goto L6;
        return true;
    L6:
        if ((r3 instanceof BasicCertificateChainCleaner) == true) goto L8;
        return false;
    L8:
        if (Intrinsics.areEqual(((BasicCertificateChainCleaner) r3).trustRootIndex, this.trustRootIndex) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public int hashCode() {
        return this.trustRootIndex.hashCode();
    }
}
