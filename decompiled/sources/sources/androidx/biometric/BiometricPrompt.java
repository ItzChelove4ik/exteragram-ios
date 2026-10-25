package androidx.biometric;

import android.annotation.SuppressLint;
import android.os.Build;
import android.security.identity.IdentityCredential;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import c.f$$ExternalSyntheticBUOutline1;
import java.security.Signature;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class BiometricPrompt {
    private FragmentManager mClientFragmentManager;

    public static abstract class AuthenticationCallback {
        public AuthenticationCallback() {
        }

        public void onAuthenticationError(int r1, CharSequence r2) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationSucceeded(AuthenticationResult r1) {
        }
    }

    public static class AuthenticationResult {
        private final int mAuthenticationType;
        private final CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject r1, int r2) {
            this.mCryptoObject = r1;
            this.mAuthenticationType = r2;
        }

        public int getAuthenticationType() {
            return this.mAuthenticationType;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    public static class CryptoObject {
        private final Cipher mCipher;
        private final IdentityCredential mIdentityCredential;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(IdentityCredential r2) {
            this.mSignature = null;
            this.mCipher = null;
            this.mMac = null;
            this.mIdentityCredential = r2;
        }

        public CryptoObject(Signature r1) {
            this.mSignature = r1;
            this.mCipher = null;
            this.mMac = null;
            this.mIdentityCredential = null;
        }

        public CryptoObject(Cipher r2) {
            this.mSignature = null;
            this.mCipher = r2;
            this.mMac = null;
            this.mIdentityCredential = null;
        }

        public CryptoObject(Mac r2) {
            this.mSignature = null;
            this.mCipher = null;
            this.mMac = r2;
            this.mIdentityCredential = null;
        }

        public Cipher getCipher() {
            return this.mCipher;
        }

        public IdentityCredential getIdentityCredential() {
            return this.mIdentityCredential;
        }

        public Mac getMac() {
            return this.mMac;
        }

        public Signature getSignature() {
            return this.mSignature;
        }
    }

    public static class PromptInfo {
        private final int mAllowedAuthenticators;
        private final CharSequence mDescription;
        private final boolean mIsConfirmationRequired;
        private final boolean mIsDeviceCredentialAllowed;
        private final CharSequence mNegativeButtonText;
        private final CharSequence mSubtitle;
        private final CharSequence mTitle;

        public static class Builder {
            private int mAllowedAuthenticators;
            private CharSequence mDescription;
            private boolean mIsConfirmationRequired;
            private boolean mIsDeviceCredentialAllowed;
            private CharSequence mNegativeButtonText;
            private CharSequence mSubtitle;
            private CharSequence mTitle;

            public Builder() {
                this.mTitle = null;
                this.mSubtitle = null;
                this.mDescription = null;
                this.mNegativeButtonText = null;
                this.mIsConfirmationRequired = true;
                this.mIsDeviceCredentialAllowed = false;
                this.mAllowedAuthenticators = 0;
            }

            public PromptInfo build() {
                if (TextUtils.isEmpty(this.mTitle) == false) goto L5;
                f$$ExternalSyntheticBUOutline1.m("Title must be set and non-empty.");
                return null;
            L5:
                if (AuthenticatorUtils.isSupportedCombination(this.mAllowedAuthenticators) == false) goto L25;
                int r0 = this.mAllowedAuthenticators;
                if (r0 == 0) goto L9;
                boolean r02 = AuthenticatorUtils.isDeviceCredentialAllowed(r0);
            L11:
                if (TextUtils.isEmpty(this.mNegativeButtonText) == false) goto L17;
                if (r02 == true) goto L17;
                f$$ExternalSyntheticBUOutline1.m("Negative text must be set and non-empty.");
                return null;
            L17:
                if (TextUtils.isEmpty(this.mNegativeButtonText) == true) goto L23;
                if (r02 == false) goto L23;
                f$$ExternalSyntheticBUOutline1.m("Negative text must not be set if device credential authentication is allowed.");
                return null;
            L23:
                return new PromptInfo(this.mTitle, this.mSubtitle, this.mDescription, this.mNegativeButtonText, this.mIsConfirmationRequired, this.mIsDeviceCredentialAllowed, this.mAllowedAuthenticators);
            L9:
                r02 = this.mIsDeviceCredentialAllowed;
                goto L11
            L25:
                throw new IllegalArgumentException("Authenticator combination is unsupported on API " + Build.VERSION.SDK_INT + ": " + AuthenticatorUtils.convertToString(this.mAllowedAuthenticators));
            }

            public Builder setAllowedAuthenticators(int r1) {
                this.mAllowedAuthenticators = r1;
                return this;
            }

            public Builder setDescription(CharSequence r1) {
                this.mDescription = r1;
                return this;
            }

            public Builder setNegativeButtonText(CharSequence r1) {
                this.mNegativeButtonText = r1;
                return this;
            }

            public Builder setTitle(CharSequence r1) {
                this.mTitle = r1;
                return this;
            }
        }

        public PromptInfo(CharSequence r1, CharSequence r2, CharSequence r3, CharSequence r4, boolean r5, boolean r6, int r7) {
            this.mTitle = r1;
            this.mSubtitle = r2;
            this.mDescription = r3;
            this.mNegativeButtonText = r4;
            this.mIsConfirmationRequired = r5;
            this.mIsDeviceCredentialAllowed = r6;
            this.mAllowedAuthenticators = r7;
        }

        public int getAllowedAuthenticators() {
            return this.mAllowedAuthenticators;
        }

        public CharSequence getDescription() {
            return this.mDescription;
        }

        public CharSequence getNegativeButtonText() {
            CharSequence r0 = this.mNegativeButtonText;
            if (r0 == null) goto L5;
            return r0;
        L5:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public CharSequence getSubtitle() {
            return this.mSubtitle;
        }

        public CharSequence getTitle() {
            return this.mTitle;
        }

        public boolean isConfirmationRequired() {
            return this.mIsConfirmationRequired;
        }

        @Deprecated
        public boolean isDeviceCredentialAllowed() {
            return this.mIsDeviceCredentialAllowed;
        }
    }

    @SuppressLint({"LambdaLast"})
    public BiometricPrompt(FragmentActivity r2, Executor r3, AuthenticationCallback r4) {
        if (r2 == null) goto L12;
        if (r3 == null) goto L10;
        if (r4 == null) goto L8;
        init(r2.getSupportFragmentManager(), getViewModel(r2), r3, r4);
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("AuthenticationCallback must not be null.");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Executor must not be null.");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline1.m("FragmentActivity must not be null.");
        throw null;
    }

    private void authenticateInternal(PromptInfo r3, CryptoObject r4) {
        FragmentManager r0 = this.mClientFragmentManager;
        if (r0 != null) goto L7;
        Log.e("BiometricPromptCompat", "Unable to start authentication. Client fragment manager was null.");
        return;
    L7:
        if (r0.isStateSaved() == false) goto L10;
        Log.e("BiometricPromptCompat", "Unable to start authentication. Called after onSaveInstanceState().");
        return;
    L10:
        findOrAddBiometricFragment(this.mClientFragmentManager).authenticate(r3, r4);
    }

    private static BiometricFragment findBiometricFragment(FragmentManager r1) {
        return (BiometricFragment) r1.findFragmentByTag("androidx.biometric.BiometricFragment");
    }

    private static BiometricFragment findOrAddBiometricFragment(FragmentManager r3) {
        BiometricFragment r0 = findBiometricFragment(r3);
        if (r0 != null) goto L6;
        BiometricFragment r02 = BiometricFragment.newInstance();
        r3.beginTransaction().add(r02, "androidx.biometric.BiometricFragment").commitAllowingStateLoss();
        r3.executePendingTransactions();
        return r02;
    L6:
        return r0;
    }

    private static BiometricViewModel getViewModel(FragmentActivity r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        return (BiometricViewModel) new ViewModelProvider(r1).get(BiometricViewModel.class);
    }

    private void init(FragmentManager r1, BiometricViewModel r2, Executor r3, AuthenticationCallback r4) {
        this.mClientFragmentManager = r1;
        if (r2 == null) goto L8;
        if (r3 == null) goto L6;
        r2.setClientExecutor(r3);
    L6:
        r2.setClientCallback(r4);
        return;
    }

    public void authenticate(PromptInfo r2) {
        if (r2 == null) goto L5;
        authenticateInternal(r2, null);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("PromptInfo cannot be null.");
    }

    public void authenticate(PromptInfo r4, CryptoObject r5) {
        if (r4 == null) goto L19;
        if (r5 == null) goto L17;
        int r0 = AuthenticatorUtils.getConsolidatedAuthenticators(r4, r5);
        if (AuthenticatorUtils.isWeakBiometricAllowed(r0) == false) goto L7;
        f$$ExternalSyntheticBUOutline1.m("Crypto-based authentication is not supported for Class 2 (Weak) biometrics.");
        return;
    L7:
        if (Build.VERSION.SDK_INT < 30) goto L9;
    L13:
        authenticateInternal(r4, r5);
        return;
    L9:
        if (AuthenticatorUtils.isDeviceCredentialAllowed(r0) == false) goto L13;
        f$$ExternalSyntheticBUOutline1.m("Crypto-based authentication is not supported for device credential prior to API 30.");
        return;
    L17:
        f$$ExternalSyntheticBUOutline1.m("CryptoObject cannot be null.");
        return;
    L19:
        f$$ExternalSyntheticBUOutline1.m("PromptInfo cannot be null.");
    }
}
