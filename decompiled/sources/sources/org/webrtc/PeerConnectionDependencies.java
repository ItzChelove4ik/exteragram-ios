package org.webrtc;

import org.webrtc.PeerConnection;

/* loaded from: classes6.dex */
public final class PeerConnectionDependencies {
    private final PeerConnection.Observer observer;
    private final SSLCertificateVerifier sslCertificateVerifier;

    public static class Builder {
        private PeerConnection.Observer observer;
        private SSLCertificateVerifier sslCertificateVerifier;

        private Builder(PeerConnection.Observer r1) {
            this.observer = r1;
        }

        public /* synthetic */ Builder(PeerConnection.Observer r1, PeerConnectionDependenciesIA r2) {
            this(r1);
        }

        public PeerConnectionDependencies createPeerConnectionDependencies() {
            return new PeerConnectionDependencies(this.observer, this.sslCertificateVerifier, null);
        }

        public Builder setSSLCertificateVerifier(SSLCertificateVerifier r1) {
            this.sslCertificateVerifier = r1;
            return this;
        }
    }

    private PeerConnectionDependencies(PeerConnection.Observer r1, SSLCertificateVerifier r2) {
        this.observer = r1;
        this.sslCertificateVerifier = r2;
    }

    public /* synthetic */ PeerConnectionDependencies(PeerConnection.Observer r1, SSLCertificateVerifier r2, PeerConnectionDependenciesIA r3) {
        this(r1, r2);
    }

    public static Builder builder(PeerConnection.Observer r2) {
        return new Builder(r2, null);
    }

    public PeerConnection.Observer getObserver() {
        return this.observer;
    }

    public SSLCertificateVerifier getSSLCertificateVerifier() {
        return this.sslCertificateVerifier;
    }
}
