package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;

/* loaded from: classes7.dex */
public abstract class ViewHelper {
    public static void setPadding(View r0, float r1, float r2, float r3, float r4) {
        r0.setPadding(AndroidUtilities.dp(r1), AndroidUtilities.dp(r2), AndroidUtilities.dp(r3), AndroidUtilities.dp(r4));
    }

    public static void setPaddingRelative(View r2, float r3, float r4, float r5, float r6) {
        boolean r0 = LocaleController.isRTL;
        if (r0 == false) goto L5;
        float r1 = r5;
    L6:
        if (r0 == true) goto L9;
        r3 = r5;
    L9:
        setPadding(r2, r1, r4, r3, r6);
        return;
    L5:
        r1 = r3;
        goto L6
    }
}
