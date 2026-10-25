package androidx.biometric;

import android.app.KeyguardManager;
import android.content.Context;

/* loaded from: classes3.dex */
abstract class KeyguardUtils {

    public static class Api23Impl {
        public static KeyguardManager getKeyguardManager(Context r1) {
            return (KeyguardManager) r1.getSystemService(KeyguardManager.class);
        }

        public static boolean isDeviceSecure(KeyguardManager r0) {
            return r0.isDeviceSecure();
        }
    }

    public static KeyguardManager getKeyguardManager(Context r0) {
        return Api23Impl.getKeyguardManager(r0);
    }

    public static boolean isDeviceSecuredWithCredential(Context r0) {
        KeyguardManager r02 = getKeyguardManager(r0);
        if (r02 != null) goto L7;
        return false;
    L7:
        return Api23Impl.isDeviceSecure(r02);
    }
}
