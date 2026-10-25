package androidx.biometric;

import android.content.Context;
import android.os.Build;

/* loaded from: classes3.dex */
abstract class DeviceUtils {
    public static boolean canAssumeStrongBiometrics(Context r2, String r3) {
        if (Build.VERSION.SDK_INT < 30) goto L7;
        return false;
    L7:
        return isModelInList(r2, r3, R$array.assume_strong_biometrics_models);
    }

    private static boolean isModelInList(Context r3, String r4, int r5) {
        if (r4 != null) goto L5;
        return false;
    L5:
        String[] r32 = r3.getResources().getStringArray(r5);
        int r52 = r32.length;
        int r1 = 0;
    L6:
        if (r1 >= r52) goto L12;
        if (r4.equals(r32[r1]) == true) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private static boolean isModelInPrefixList(Context r3, String r4, int r5) {
        if (r4 != null) goto L5;
        return false;
    L5:
        String[] r32 = r3.getResources().getStringArray(r5);
        int r52 = r32.length;
        int r1 = 0;
    L6:
        if (r1 >= r52) goto L12;
        if (r4.startsWith(r32[r1]) == true) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private static boolean isVendorInList(Context r3, String r4, int r5) {
        if (r4 != null) goto L5;
        return false;
    L5:
        String[] r32 = r3.getResources().getStringArray(r5);
        int r52 = r32.length;
        int r1 = 0;
    L6:
        if (r1 >= r52) goto L12;
        if (r4.equalsIgnoreCase(r32[r1]) == true) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public static boolean shouldDelayShowingPrompt(Context r2, String r3) {
        if (Build.VERSION.SDK_INT == 29) goto L7;
        return false;
    L7:
        return isModelInList(r2, r3, R$array.delay_showing_prompt_models);
    }

    public static boolean shouldHideFingerprintDialog(Context r2, String r3) {
        if (Build.VERSION.SDK_INT == 28) goto L7;
        return false;
    L7:
        return isModelInPrefixList(r2, r3, R$array.hide_fingerprint_instantly_prefixes);
    }

    public static boolean shouldUseFingerprintForCrypto(Context r3, String r4, String r5) {
        if (Build.VERSION.SDK_INT == 28) goto L6;
        return false;
    L6:
        if (isVendorInList(r3, r4, R$array.crypto_fingerprint_fallback_vendors) == false) goto L8;
        return true;
    L8:
        if (isModelInPrefixList(r3, r5, R$array.crypto_fingerprint_fallback_prefixes) == true) goto L13;
        return false;
    L13:
        return true;
    }
}
