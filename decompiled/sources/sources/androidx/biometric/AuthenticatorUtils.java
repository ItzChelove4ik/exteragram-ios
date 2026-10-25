package androidx.biometric;

import android.os.Build;
import androidx.biometric.BiometricPrompt;

/* loaded from: classes3.dex */
abstract class AuthenticatorUtils {
    public static String convertToString(int r1) {
        if (r1 != 15) goto L5;
        return "BIOMETRIC_STRONG";
    L5:
        if (r1 != 255) goto L7;
        return "BIOMETRIC_WEAK";
    L7:
        if (r1 != 32768) goto L9;
        return "DEVICE_CREDENTIAL";
    L9:
        if (r1 != 32783) goto L11;
        return "BIOMETRIC_STRONG | DEVICE_CREDENTIAL";
    L11:
        if (r1 != 33023) goto L13;
        return "BIOMETRIC_WEAK | DEVICE_CREDENTIAL";
    L13:
        return String.valueOf(r1);
    }

    public static int getConsolidatedAuthenticators(BiometricPrompt.PromptInfo r1, BiometricPrompt.CryptoObject r2) {
        if (r1.getAllowedAuthenticators() != 0) goto L5;
        if (r2 == null) goto L8;
        int r22 = 15;
    L10:
        if (r1.isDeviceCredentialAllowed() == true) goto L12;
        return r22;
    L12:
        return 32768 | r22;
    L8:
        r22 = 255;
        goto L10
    L5:
        return r1.getAllowedAuthenticators();
    }

    public static boolean isDeviceCredentialAllowed(int r1) {
        if ((r1 & 32768) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isSomeBiometricAllowed(int r0) {
        if ((r0 & 32767) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isSupportedCombination(int r3) {
        if (r3 != 15) goto L5;
    L26:
        return true;
    L5:
        if (r3 == 255) goto L26;
        if (r3 == 32768) goto L23;
        if (r3 != 32783) goto L11;
        int r32 = Build.VERSION.SDK_INT;
        if (r32 >= 28) goto L18;
    L21:
        return true;
    L18:
        if (r32 > 29) goto L21;
        return false;
    L11:
        if (r3 == 33023) goto L26;
        if (r3 != 0) goto L14;
        return true;
    L14:
        return false;
    L23:
        if (Build.VERSION.SDK_INT < 30) goto L25;
        return true;
    L25:
        return false;
    }

    public static boolean isWeakBiometricAllowed(int r1) {
        if ((r1 & 255) != 255) goto L6;
        return true;
    L6:
        return false;
    }
}
