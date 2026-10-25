package org.webrtc;

import org.webrtc.PeerConnection;

/* loaded from: classes6.dex */
public class RtcCertificatePem {
    private static final long DEFAULT_EXPIRY = 2592000;
    public final String certificate;
    public final String privateKey;

    @CalledByNative
    public RtcCertificatePem(String r1, String r2) {
        this.privateKey = r1;
        this.certificate = r2;
    }

    public static RtcCertificatePem generateCertificate() {
        return nativeGenerateCertificate(PeerConnection.KeyType.ECDSA, DEFAULT_EXPIRY);
    }

    public static RtcCertificatePem generateCertificate(long r1) {
        return nativeGenerateCertificate(PeerConnection.KeyType.ECDSA, r1);
    }

    public static RtcCertificatePem generateCertificate(PeerConnection.KeyType r2) {
        return nativeGenerateCertificate(r2, DEFAULT_EXPIRY);
    }

    public static RtcCertificatePem generateCertificate(PeerConnection.KeyType r0, long r1) {
        return nativeGenerateCertificate(r0, r1);
    }

    private static native RtcCertificatePem nativeGenerateCertificate(PeerConnection.KeyType r0, long r1);

    @CalledByNative
    public String getCertificate() {
        return this.certificate;
    }

    @CalledByNative
    public String getPrivateKey() {
        return this.privateKey;
    }
}
