package androidx.biometric;

import android.content.Context;
import android.util.Log;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
abstract class ErrorUtils {
    public static String getFingerprintErrorString(Context r2, int r3) {
        if (r2 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (r3 == 1) goto L21;
        if (r3 == 7) goto L19;
        switch(r3) {
            case 9: goto L19;
            case 10: goto L17;
            case 11: goto L15;
            case 12: goto L13;
            default: goto L10;
        };
    L10:
        Log.e("BiometricUtils", "Unknown error code: " + r3);
        return r2.getString(R$string.default_error_msg);
    L13:
        return r2.getString(R$string.fingerprint_error_hw_not_present);
    L15:
        return r2.getString(R$string.fingerprint_error_no_fingerprints);
    L17:
        return r2.getString(R$string.fingerprint_error_user_canceled);
    L19:
        return r2.getString(R$string.fingerprint_error_lockout);
    L21:
        return r2.getString(R$string.fingerprint_error_hw_not_available);
    }

    public static boolean isKnownError(int r0) {
        switch(r0) {
            case 1: goto L5;
            case 2: goto L5;
            case 3: goto L5;
            case 4: goto L5;
            case 5: goto L5;
            case 6: goto L3;
            case 7: goto L5;
            case 8: goto L5;
            case 9: goto L5;
            case 10: goto L5;
            case 11: goto L5;
            case 12: goto L5;
            case 13: goto L5;
            case 14: goto L5;
            case 15: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        return true;
    }

    public static boolean isLockoutError(int r1) {
        if (r1 != 7) goto L5;
        return true;
    L5:
        if (r1 == 9) goto L11;
        return false;
    L11:
        return true;
    }
}
