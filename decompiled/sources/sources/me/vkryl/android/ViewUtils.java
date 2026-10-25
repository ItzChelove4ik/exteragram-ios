package me.vkryl.android;

import android.view.View;

/* loaded from: classes5.dex */
public abstract class ViewUtils {
    public static void hapticVibrate(View r1, boolean r2, boolean r3) {
        if (r1 == null) goto L11;
        int r0 = 0;
        if (r2 == false) goto L6;
        int r22 = 0;
    L7:
        if (r3 == false) goto L9;
        r0 = 2;
    L9:
        r1.performHapticFeedback(r22, r0);
        return;
    L6:
        r22 = 3;
        goto L7
    }

    public static void onClick(View r1) {
        if (r1 == null) goto L5;
        r1.playSoundEffect(0);
        return;
    }
}
