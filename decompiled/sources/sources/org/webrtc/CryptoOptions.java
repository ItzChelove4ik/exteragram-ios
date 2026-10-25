package org.webrtc;

/* loaded from: classes6.dex */
public final class CryptoOptions {
    private final SFrame sframe;
    private final Srtp srtp;

    public static class Builder {
        private boolean enableAes128Sha1_32CryptoCipher;
        private boolean enableEncryptedRtpHeaderExtensions;
        private boolean enableGcmCryptoSuites;
        private boolean requireFrameEncryption;

        private Builder() {
        }

        public /* synthetic */ Builder(CryptoOptionsIA r1) {
            this();
        }

        public CryptoOptions createCryptoOptions() {
            return new CryptoOptions(this.enableGcmCryptoSuites, this.enableAes128Sha1_32CryptoCipher, this.enableEncryptedRtpHeaderExtensions, this.requireFrameEncryption, null);
        }

        public Builder setEnableAes128Sha1_32CryptoCipher(boolean r1) {
            this.enableAes128Sha1_32CryptoCipher = r1;
            return this;
        }

        public Builder setEnableEncryptedRtpHeaderExtensions(boolean r1) {
            this.enableEncryptedRtpHeaderExtensions = r1;
            return this;
        }

        public Builder setEnableGcmCryptoSuites(boolean r1) {
            this.enableGcmCryptoSuites = r1;
            return this;
        }

        public Builder setRequireFrameEncryption(boolean r1) {
            this.requireFrameEncryption = r1;
            return this;
        }
    }

    public final class SFrame {
        private final boolean requireFrameEncryption;
        final /* synthetic */ CryptoOptions this$0;

        private SFrame(CryptoOptions r1, boolean r2) {
            this.this$0 = r1;
            this.requireFrameEncryption = r2;
        }

        public /* synthetic */ SFrame(CryptoOptions r1, boolean r2, CryptoOptionsIA r3) {
            this(r1, r2);
        }

        @CalledByNative("SFrame")
        public boolean getRequireFrameEncryption() {
            return this.requireFrameEncryption;
        }
    }

    public final class Srtp {
        private final boolean enableAes128Sha1_32CryptoCipher;
        private final boolean enableEncryptedRtpHeaderExtensions;
        private final boolean enableGcmCryptoSuites;
        final /* synthetic */ CryptoOptions this$0;

        private Srtp(CryptoOptions r1, boolean r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.enableGcmCryptoSuites = r2;
            this.enableAes128Sha1_32CryptoCipher = r3;
            this.enableEncryptedRtpHeaderExtensions = r4;
        }

        public /* synthetic */ Srtp(CryptoOptions r1, boolean r2, boolean r3, boolean r4, CryptoOptionsIA r5) {
            this(r1, r2, r3, r4);
        }

        @CalledByNative("Srtp")
        public boolean getEnableAes128Sha1_32CryptoCipher() {
            return this.enableAes128Sha1_32CryptoCipher;
        }

        @CalledByNative("Srtp")
        public boolean getEnableEncryptedRtpHeaderExtensions() {
            return this.enableEncryptedRtpHeaderExtensions;
        }

        @CalledByNative("Srtp")
        public boolean getEnableGcmCryptoSuites() {
            return this.enableGcmCryptoSuites;
        }
    }

    private CryptoOptions(boolean r7, boolean r8, boolean r9, boolean r10) {
        this.srtp = new Srtp(this, r7, r8, r9, null);
        this.sframe = new SFrame(this, r10, null);
    }

    public /* synthetic */ CryptoOptions(boolean r1, boolean r2, boolean r3, boolean r4, CryptoOptionsIA r5) {
        this(r1, r2, r3, r4);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @CalledByNative
    public SFrame getSFrame() {
        return this.sframe;
    }

    @CalledByNative
    public Srtp getSrtp() {
        return this.srtp;
    }
}
