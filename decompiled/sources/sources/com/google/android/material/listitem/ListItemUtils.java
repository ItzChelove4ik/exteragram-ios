package com.google.android.material.listitem;

import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;

/* loaded from: classes5.dex */
class ListItemUtils {
    private ListItemUtils() {
    }

    public static boolean isRightAligned(View r2) {
        if ((r2.getLayoutParams() instanceof FrameLayout.LayoutParams) == false) goto L7;
        int r0 = ((FrameLayout.LayoutParams) r2.getLayoutParams()).gravity;
        if (r0 == (-1)) goto L7;
    L9:
        if ((GravityCompat.getAbsoluteGravity(r0, r2.getLayoutDirection()) & 7) != 5) goto L12;
        return true;
    L12:
        return false;
    L7:
        r0 = 8388613;
        goto L9
    }
}
