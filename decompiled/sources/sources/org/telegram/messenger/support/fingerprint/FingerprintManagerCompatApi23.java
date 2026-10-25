package org.telegram.messenger.support.fingerprint;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import org.telegram.messenger.FileLog;

@TargetApi(23)
/* loaded from: classes3.dex */
public abstract class FingerprintManagerCompatApi23 {
    private static FingerprintManager getFingerprintManager(Context r1) {
        return (FingerprintManager) r1.getSystemService("fingerprint");
    }

    public static boolean hasEnrolledFingerprints(Context r1) {
        FingerprintManager r12 = getFingerprintManager(r1);     // Catch: Exception -> L8
        if (r12 != null) goto L6;
        return false;
    L6:
        return r12.hasEnrolledFingerprints();
    L8:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static boolean isHardwareDetected(Context r1) {
        FingerprintManager r12 = getFingerprintManager(r1);     // Catch: Exception -> L8
        if (r12 != null) goto L6;
        return false;
    L6:
        return r12.isHardwareDetected();
    L8:
        e = move-exception;
        FileLog.e(e);
        return false;
    }
}
