package com.exteragram.messenger.utils.system;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

/* loaded from: classes4.dex */
public abstract class VibratorUtils {
    private static final Vibrator vibrator = null;

    static {
        vibrator = (Vibrator) ApplicationLoader.applicationContext.getSystemService("vibrator");
    }

    public static void disableHapticFeedback(View r2) {
        if (r2 == null) goto L12;
        int r0 = 0;
        r2.setHapticFeedbackEnabled(false);
        if ((r2 instanceof ViewGroup) == false) goto L10;
        ViewGroup r22 = (ViewGroup) r2;
    L8:
        if (r0 >= r22.getChildCount()) goto L13;
        disableHapticFeedback(r22.getChildAt(r0));
        r0 = r0 + 1;
        goto L8
    L13:
        return;
    L10:
        return;
    }

    public static int getType(int r1) {
        if (ExteraConfig.getInAppVibration() == false) goto L5;
        return r1;
    L5:
        return -1;
    }

    private static boolean isVibrationAllowed() {
        if (ExteraConfig.getInAppVibration() == true) goto L5;
        return false;
    L5:
        if (vibrator.hasVibrator() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static void vibrate() {
        vibrate(200);
    }

    public static void vibrate(long r2) {
        if (isVibrationAllowed() == true) goto L6;
        return;
    L6:
        if (Build.VERSION.SDK_INT >= 26) goto L17;
        vibrator.vibrate(r2);     // Catch: Exception -> L14
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
        return;
    L17:
        VibrationEffect r22 = VibrationEffect.createOneShot(r2, -1);     // Catch: Exception -> L10
        vibrator.vibrate(r22);     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
    }

    public static void vibrateEffect(VibrationEffect r2) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return;
    L5:
        if (isVibrationAllowed() == false) goto L20;
        vibrator.cancel();     // Catch: Exception -> L8
    L15:
        vibrator.vibrate(r2);     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L15
    }
}
