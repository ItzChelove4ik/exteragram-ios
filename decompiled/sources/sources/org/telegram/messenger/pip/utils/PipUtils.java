package org.telegram.messenger.pip.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import androidx.core.math.MathUtils;
import com.google.android.gms.cast.CastStatusCodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.pip.PipSource;

/* loaded from: classes.dex */
public abstract class PipUtils {
    private static final int[] tmpCords = null;

    static {
        tmpCords = new int[2];
    }

    public static void applyPictureInPictureParams(Activity r2, PipSource r3) {
        if (Build.VERSION.SDK_INT < 26) goto L9;
        if (r3 == null) goto L7;
        AndroidUtilities.setPictureInPictureParams(r2, r3.buildPictureInPictureParams());
        return;
    L7:
        AndroidUtilities.resetPictureInPictureParams(r2);
        return;
    }

    public static boolean checkAnyPipPermissions(Context r0) {
        if (checkPermissions(r0) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static int checkPermissions(Context r2) {
        if (AndroidUtilities.checkInlinePermissions(r2) == false) goto L7;
        return 2;
    L7:
        if (Build.VERSION.SDK_INT >= 26) goto L9;
        return -1;
    L9:
        if (AndroidUtilities.checkPipPermissions(r2) == false) goto L12;
        return 1;
    L12:
        return -2;
    }

    public static WindowManager.LayoutParams createWindowLayoutParams(Context r2, boolean r3) {
        WindowManager.LayoutParams r0 = new WindowManager.LayoutParams();
        r0.gravity = 51;
        r0.format = -3;
        r0.type = getWindowLayoutParamsType(r2, r3);
        r0.flags = 520;
        return r0;
    }

    public static void getPipSourceRectHintPosition(Activity r8, View r9, Rect r10) {
        int[] r0 = tmpCords;
        r9.getLocationOnScreen(r0);
        int r2 = r0[0];
        int r4 = r0[1];
        View r82 = r8.getWindow().getDecorView();
        r82.getLocationOnScreen(r0);
        int r22 = r2 - r0[0];
        int r42 = r4 - r0[1];
        int r5 = r9.getWidth() + r22;
        int r92 = r9.getHeight() + r42;
        int r6 = r0[0];
        int r23 = MathUtils.clamp(r22, r6, r82.getWidth() + r6);
        int r62 = r0[1];
        int r43 = MathUtils.clamp(r42, r62, r82.getHeight() + r62);
        int r1 = r0[0];
        int r12 = MathUtils.clamp(r5, r1, r82.getWidth() + r1);
        int r02 = r0[1];
        r10.set(r23, r43, r12, MathUtils.clamp(r92, r02, r82.getHeight() + r02));
    }

    public static int getWindowLayoutParamsType(Context r0, boolean r1) {
        if (r1 == false) goto L4;
        return 2;
    L4:
        if (AndroidUtilities.checkInlinePermissions(r0) == true) goto L6;
        return 2;
    L6:
        if (Build.VERSION.SDK_INT < 26) goto L9;
        return 2038;
    L9:
        return CastStatusCodes.NOT_ALLOWED;
    }

    public static boolean useAutoEnterInPictureInPictureMode() {
        if (Build.VERSION.SDK_INT < 31) goto L6;
        return true;
    L6:
        return false;
    }
}
