package androidx.biometric;

import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.security.identity.IdentityCredential;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* loaded from: classes3.dex */
abstract class CryptoObjectUtils {

    public static class Api23Impl {
        public static KeyGenParameterSpec buildKeyGenParameterSpec(KeyGenParameterSpec.Builder r0) {
            return r0.build();
        }

        public static KeyGenParameterSpec.Builder createKeyGenParameterSpecBuilder(String r1, int r2) {
            return new KeyGenParameterSpec.Builder(r1, r2);
        }

        public static void initKeyGenerator(KeyGenerator r0, KeyGenParameterSpec r1) {
            r0.init(r1);
        }

        public static void setBlockModeCBC(KeyGenParameterSpec.Builder r1) {
            r1.setBlockModes(new String[]{"CBC"});
        }

        public static void setEncryptionPaddingPKCS7(KeyGenParameterSpec.Builder r1) {
            r1.setEncryptionPaddings(new String[]{"PKCS7Padding"});
        }
    }

    public static class Api28Impl {
        public static BiometricPrompt.CryptoObject create(Signature r1) {
            return new BiometricPrompt.CryptoObject(r1);
        }

        public static BiometricPrompt.CryptoObject create(Cipher r1) {
            return new BiometricPrompt.CryptoObject(r1);
        }

        public static BiometricPrompt.CryptoObject create(Mac r1) {
            return new BiometricPrompt.CryptoObject(r1);
        }

        public static Cipher getCipher(BiometricPrompt.CryptoObject r0) {
            return r0.getCipher();
        }

        public static Mac getMac(BiometricPrompt.CryptoObject r0) {
            return r0.getMac();
        }

        public static Signature getSignature(BiometricPrompt.CryptoObject r0) {
            return r0.getSignature();
        }
    }

    public static class Api30Impl {
        public static BiometricPrompt.CryptoObject create(IdentityCredential r1) {
            return new BiometricPrompt.CryptoObject(r1);
        }

        public static IdentityCredential getIdentityCredential(BiometricPrompt.CryptoObject r0) {
            return r0.getIdentityCredential();
        }
    }

    public static BiometricPrompt.CryptoObject createFakeCryptoObject() {
        KeyStore r3 = KeyStore.getInstance("AndroidKeyStore");     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        r3.load(null);     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        KeyGenParameterSpec.Builder r4 = Api23Impl.createKeyGenParameterSpecBuilder("androidxBiometric", 3);     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        Api23Impl.setBlockModeCBC(r4);     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        Api23Impl.setEncryptionPaddingPKCS7(r4);     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        KeyGenerator r1 = KeyGenerator.getInstance("AES", "AndroidKeyStore");     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        Api23Impl.initKeyGenerator(r1, Api23Impl.buildKeyGenParameterSpec(r4));     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        r1.generateKey();     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        SecretKey r0 = (SecretKey) r3.getKey("androidxBiometric", null);     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        Cipher r12 = Cipher.getInstance("AES/CBC/PKCS7Padding");     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        r12.init(1, r0);     // Catch: NoSuchProviderException -> L5 IOException -> L7 UnrecoverableKeyException -> L9 Throwable -> L11 InvalidKeyException -> L13 KeyStoreException -> L15 CertificateException -> L17 NoSuchAlgorithmException -> L19 NoSuchPaddingException -> L21
        return new BiometricPrompt.CryptoObject(r12);
    L11:
        e = move-exception;
        Log.w("CryptoObjectUtils", "Failed to create fake crypto object.", e);
        return null;
    }

    public static BiometricPrompt.CryptoObject unwrapFromBiometricPrompt(BiometricPrompt.CryptoObject r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        Cipher r1 = Api28Impl.getCipher(r3);
        if (r1 != null) goto L8;
        Signature r12 = Api28Impl.getSignature(r3);
        if (r12 != null) goto L12;
        Mac r13 = Api28Impl.getMac(r3);
        if (r13 == null) goto L18;
        return new BiometricPrompt.CryptoObject(r13);
    L18:
        if (Build.VERSION.SDK_INT < 30) goto L23;
        IdentityCredential r32 = Api30Impl.getIdentityCredential(r3);
        if (r32 != null) goto L22;
        return null;
    L22:
        return new BiometricPrompt.CryptoObject(r32);
    L23:
        return null;
    L12:
        return new BiometricPrompt.CryptoObject(r12);
    L8:
        return new BiometricPrompt.CryptoObject(r1);
    }

    public static BiometricPrompt.CryptoObject wrapForBiometricPrompt(BiometricPrompt.CryptoObject r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        Cipher r1 = r3.getCipher();
        if (r1 != null) goto L8;
        Signature r12 = r3.getSignature();
        if (r12 != null) goto L12;
        Mac r13 = r3.getMac();
        if (r13 == null) goto L18;
        return Api28Impl.create(r13);
    L18:
        if (Build.VERSION.SDK_INT < 30) goto L23;
        IdentityCredential r32 = r3.getIdentityCredential();
        if (r32 == null) goto L23;
        return Api30Impl.create(r32);
    L23:
        return null;
    L12:
        return Api28Impl.create(r12);
    L8:
        return Api28Impl.create(r1);
    }

    public static FingerprintManagerCompat.CryptoObject wrapForFingerprintManager(BiometricPrompt.CryptoObject r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        Cipher r1 = r3.getCipher();
        if (r1 != null) goto L8;
        Signature r12 = r3.getSignature();
        if (r12 != null) goto L12;
        Mac r13 = r3.getMac();
        if (r13 == null) goto L18;
        return new FingerprintManagerCompat.CryptoObject(r13);
    L18:
        if (Build.VERSION.SDK_INT >= 30) goto L20;
    L22:
        return null;
    L20:
        if (r3.getIdentityCredential() == null) goto L22;
        Log.e("CryptoObjectUtils", "Identity credential is not supported by FingerprintManager.");
        goto L22
    L12:
        return new FingerprintManagerCompat.CryptoObject(r12);
    L8:
        return new FingerprintManagerCompat.CryptoObject(r1);
    }
}
