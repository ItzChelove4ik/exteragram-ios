package com.google.android.material.color;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;

/* loaded from: classes5.dex */
public final class ThemeUtils {
    private ThemeUtils() {
    }

    public static void applyThemeOverlay(Context r2, int r3) {
        r2.getTheme().applyStyle(r3, true);
        if ((r2 instanceof Activity) == false) goto L8;
        Resources.Theme r22 = getWindowDecorViewTheme((Activity) r2);
        if (r22 == null) goto L9;
        r22.applyStyle(r3, true);
        return;
    L9:
        return;
    }

    private static Resources.Theme getWindowDecorViewTheme(Activity r0) {
        Window r02 = r0.getWindow();
        if (r02 == null) goto L10;
        View r03 = r02.peekDecorView();
        if (r03 == null) goto L12;
        Context r04 = r03.getContext();
        if (r04 != null) goto L9;
        return null;
    L9:
        return r04.getTheme();
    L12:
        return null;
    L10:
        return null;
    }
}
