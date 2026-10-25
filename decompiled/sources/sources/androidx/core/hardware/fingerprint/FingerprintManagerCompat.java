package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.os.Handler;
import androidx.core.os.CancellationSignal;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@Deprecated
/* loaded from: classes3.dex */
public class FingerprintManagerCompat {

    public static abstract class AuthenticationCallback {
        public AuthenticationCallback() {
        }
    }

    public static class CryptoObject {
        private final Cipher mCipher;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(Signature r1) {
            this.mSignature = r1;
            this.mCipher = null;
            this.mMac = null;
        }

        public CryptoObject(Cipher r1) {
            this.mCipher = r1;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(Mac r1) {
            this.mMac = r1;
            this.mCipher = null;
            this.mSignature = null;
        }
    }

    private FingerprintManagerCompat() {
    }

    public static FingerprintManagerCompat from(Context r0) {
        return new FingerprintManagerCompat();
    }

    @Deprecated
    public void authenticate(CryptoObject r1, int r2, CancellationSignal r3, AuthenticationCallback r4, Handler r5) {
    }

    public boolean hasEnrolledFingerprints() {
        return false;
    }

    public boolean isHardwareDetected() {
        return false;
    }
}
