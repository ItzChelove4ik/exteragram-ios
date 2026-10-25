package androidx.biometric;

import android.content.Context;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.util.Log;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class BiometricManager {
    private final android.hardware.biometrics.BiometricManager mBiometricManager;
    private final FingerprintManagerCompat mFingerprintManager;
    private final Injector mInjector;

    public static class Api29Impl {
        public static int canAuthenticate(android.hardware.biometrics.BiometricManager r0) {
            return r0.canAuthenticate();
        }

        public static android.hardware.biometrics.BiometricManager create(Context r1) {
            return (android.hardware.biometrics.BiometricManager) r1.getSystemService(android.hardware.biometrics.BiometricManager.class);
        }

        public static Method getCanAuthenticateWithCryptoMethod() {
            return android.hardware.biometrics.BiometricManager.class.getMethod("canAuthenticate", new Class[]{BiometricPrompt.CryptoObject.class});
        L4:
            return null;
        }
    }

    public static class Api30Impl {
        public static int canAuthenticate(android.hardware.biometrics.BiometricManager r0, int r1) {
            return r0.canAuthenticate(r1);
        }
    }

    public static class DefaultInjector implements Injector {
        private final Context mContext;

        public DefaultInjector(Context r1) {
            this.mContext = r1.getApplicationContext();
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public android.hardware.biometrics.BiometricManager getBiometricManager() {
            return Api29Impl.create(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public FingerprintManagerCompat getFingerprintManager() {
            return FingerprintManagerCompat.from(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isDeviceSecurable() {
            if (KeyguardUtils.getKeyguardManager(this.mContext) == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isDeviceSecuredWithCredential() {
            return KeyguardUtils.isDeviceSecuredWithCredential(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isFingerprintHardwarePresent() {
            return PackageUtils.hasSystemFeatureFingerprint(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isStrongBiometricGuaranteed() {
            return DeviceUtils.canAssumeStrongBiometrics(this.mContext, Build.MODEL);
        }
    }

    public interface Injector {
        android.hardware.biometrics.BiometricManager getBiometricManager();

        FingerprintManagerCompat getFingerprintManager();

        boolean isDeviceSecurable();

        boolean isDeviceSecuredWithCredential();

        boolean isFingerprintHardwarePresent();

        boolean isStrongBiometricGuaranteed();
    }

    public BiometricManager(Injector r5) {
        this.mInjector = r5;
        int r0 = Build.VERSION.SDK_INT;
        FingerprintManagerCompat r1 = null;
        if (r0 < 29) goto L5;
        android.hardware.biometrics.BiometricManager r3 = r5.getBiometricManager();
    L6:
        this.mBiometricManager = r3;
        if (r0 > 29) goto L9;
        r1 = r5.getFingerprintManager();
    L9:
        this.mFingerprintManager = r1;
        return;
    L5:
        r3 = null;
        goto L6
    }

    private int canAuthenticateCompat(int r4) {
        if (AuthenticatorUtils.isSupportedCombination(r4) == true) goto L7;
        return -2;
    L7:
        if (r4 != 0) goto L10;
        return 12;
    L10:
        if (this.mInjector.isDeviceSecurable() == true) goto L13;
        return 12;
    L13:
        if (AuthenticatorUtils.isDeviceCredentialAllowed(r4) == true) goto L15;
        int r1 = Build.VERSION.SDK_INT;
        if (r1 != 29) goto L29;
        if (AuthenticatorUtils.isWeakBiometricAllowed(r4) == false) goto L27;
        return canAuthenticateWithWeakBiometricOnApi29();
    L27:
        return canAuthenticateWithStrongBiometricOnApi29();
    L29:
        if (r1 != 28) goto L36;
        if (this.mInjector.isFingerprintHardwarePresent() == true) goto L33;
        return 12;
    L33:
        return canAuthenticateWithFingerprintOrUnknownBiometric();
    L36:
        return canAuthenticateWithFingerprint();
    L15:
        if (this.mInjector.isDeviceSecuredWithCredential() == false) goto L18;
        return 0;
    L18:
        return 11;
    }

    private int canAuthenticateWithFingerprint() {
        FingerprintManagerCompat r0 = this.mFingerprintManager;
        if (r0 != null) goto L7;
        Log.e("BiometricManager", "Failure in canAuthenticate(). FingerprintManager was null.");
        return 1;
    L7:
        if (r0.isHardwareDetected() == true) goto L11;
        return 12;
    L11:
        if (this.mFingerprintManager.hasEnrolledFingerprints() == true) goto L14;
        return 11;
    L14:
        return 0;
    }

    private int canAuthenticateWithFingerprintOrUnknownBiometric() {
        if (this.mInjector.isDeviceSecuredWithCredential() == true) goto L7;
        return canAuthenticateWithFingerprint();
    L7:
        if (canAuthenticateWithFingerprint() != 0) goto L10;
        return 0;
    L10:
        return -1;
    }

    private int canAuthenticateWithStrongBiometricOnApi29() {
        Method r1 = Api29Impl.getCanAuthenticateWithCryptoMethod();
        if (r1 == null) goto L19;
        BiometricPrompt.CryptoObject r2 = CryptoObjectUtils.wrapForBiometricPrompt(CryptoObjectUtils.createFakeCryptoObject());
        if (r2 == null) goto L19;
        Object r12 = r1.invoke(this.mBiometricManager, new Object[]{r2});     // Catch: InvocationTargetException -> L10 IllegalArgumentException -> L12 IllegalAccessException -> L14
        if ((r12 instanceof Integer) == true) goto L9;
        Log.w("BiometricManager", "Invalid return type for canAuthenticate(CryptoObject).");     // Catch: InvocationTargetException -> L10 IllegalArgumentException -> L12 IllegalAccessException -> L14
        goto L19
    L9:
        return ((Integer) r12).intValue();
    L14:
        e = e;
    L18:
        Log.w("BiometricManager", "Failed to invoke canAuthenticate(CryptoObject).", e);
    L12:
        e = e;
    L10:
        e = e;
    L19:
        int r0 = canAuthenticateWithWeakBiometricOnApi29();
        if (this.mInjector.isStrongBiometricGuaranteed() == true) goto L25;
        if (r0 != 0) goto L25;
        return canAuthenticateWithFingerprintOrUnknownBiometric();
    L25:
        return r0;
    }

    private int canAuthenticateWithWeakBiometricOnApi29() {
        android.hardware.biometrics.BiometricManager r1 = this.mBiometricManager;
        if (r1 != null) goto L7;
        Log.e("BiometricManager", "Failure in canAuthenticate(). BiometricManager was null.");
        return 1;
    L7:
        return Api29Impl.canAuthenticate(r1);
    }

    public static BiometricManager from(Context r2) {
        return new BiometricManager(new DefaultInjector(r2));
    }

    public int canAuthenticate(int r3) {
        if (Build.VERSION.SDK_INT < 30) goto L11;
        android.hardware.biometrics.BiometricManager r2 = this.mBiometricManager;
        if (r2 != null) goto L9;
        Log.e("BiometricManager", "Failure in canAuthenticate(). BiometricManager was null.");
        return 1;
    L9:
        return Api30Impl.canAuthenticate(r2, r3);
    L11:
        return canAuthenticateCompat(r3);
    }
}
