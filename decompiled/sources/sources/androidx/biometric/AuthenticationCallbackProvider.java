package androidx.biometric;

import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

/* loaded from: classes3.dex */
class AuthenticationCallbackProvider {
    private android.hardware.biometrics.BiometricPrompt$AuthenticationCallback mBiometricCallback;
    private FingerprintManagerCompat.AuthenticationCallback mFingerprintCallback;
    final Listener mListener;

    /* renamed from: androidx.biometric.AuthenticationCallbackProvider$1, reason: invalid class name */
    public class AnonymousClass1 extends FingerprintManagerCompat.AuthenticationCallback {
        final /* synthetic */ AuthenticationCallbackProvider this$0;

        public AnonymousClass1(AuthenticationCallbackProvider r1) {
            this.this$0 = r1;
        }
    }

    public static class Api28Impl {

        /* renamed from: androidx.biometric.AuthenticationCallbackProvider$Api28Impl$1, reason: invalid class name */
        public class AnonymousClass1 extends android.hardware.biometrics.BiometricPrompt$AuthenticationCallback {
            final /* synthetic */ Listener val$listener;

            public AnonymousClass1(Listener r1) {
                this.val$listener = r1;
            }

            public void onAuthenticationError(int r1, CharSequence r2) {
                this.val$listener.onError(r1, r2);
            }

            public void onAuthenticationFailed() {
                this.val$listener.onFailure();
            }

            public void onAuthenticationHelp(int r1, CharSequence r2) {
            }

            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult r5) {
                if (r5 == null) goto L4;
                BiometricPrompt.CryptoObject r0 = CryptoObjectUtils.unwrapFromBiometricPrompt(r5.getCryptoObject());
            L5:
                int r1 = Build.VERSION.SDK_INT;
                int r3 = -1;
                if (r1 < 30) goto L10;
                if (r5 == null) goto L13;
                r3 = Api30Impl.getAuthenticationType(r5);
            L13:
                BiometricPrompt.AuthenticationResult r52 = new BiometricPrompt.AuthenticationResult(r0, r3);
                this.val$listener.onSuccess(r52);
                return;
            L10:
                if (r1 == 29) goto L13;
                r3 = 2;
                goto L13
            L4:
                r0 = null;
                goto L5
            }
        }

        public static android.hardware.biometrics.BiometricPrompt$AuthenticationCallback createCallback(Listener r1) {
            return new AnonymousClass1(r1);
        }
    }

    public static class Api30Impl {
        public static int getAuthenticationType(BiometricPrompt.AuthenticationResult r0) {
            return r0.getAuthenticationType();
        }
    }

    public static class Listener {
        public Listener() {
        }

        public abstract void onError(int r1, CharSequence r2);

        public abstract void onFailure();

        public abstract void onSuccess(BiometricPrompt.AuthenticationResult r1);
    }

    public AuthenticationCallbackProvider(Listener r1) {
        this.mListener = r1;
    }

    public android.hardware.biometrics.BiometricPrompt$AuthenticationCallback getBiometricCallback() {
        if (this.mBiometricCallback != null) goto L6;
        this.mBiometricCallback = Api28Impl.createCallback(this.mListener);
    L6:
        return this.mBiometricCallback;
    }

    public FingerprintManagerCompat.AuthenticationCallback getFingerprintCallback() {
        if (this.mFingerprintCallback != null) goto L6;
        this.mFingerprintCallback = new AnonymousClass1(this);
    L6:
        return this.mFingerprintCallback;
    }
}
