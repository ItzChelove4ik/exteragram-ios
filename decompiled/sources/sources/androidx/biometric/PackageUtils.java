package androidx.biometric;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: classes3.dex */
abstract class PackageUtils {

    public static class Api23Impl {
        public static boolean hasSystemFeatureFingerprint(PackageManager r1) {
            return r1.hasSystemFeature("android.hardware.fingerprint");
        }
    }

    public static boolean hasSystemFeatureFingerprint(Context r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.getPackageManager() != null) goto L6;
        return false;
    L6:
        if (Api23Impl.hasSystemFeatureFingerprint(r1.getPackageManager()) == false) goto L12;
        return true;
    L12:
        return false;
    }
}
