package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;

/* loaded from: classes.dex */
public abstract class WindowCompat {

    /* loaded from: classes3.dex */
    public static class Api16Impl {
        public static void setDecorFitsSystemWindows(Window r1, boolean r2) {
            View r12 = r1.getDecorView();
            int r0 = r12.getSystemUiVisibility();
            if (r2 == false) goto L5;
            int r22 = r0 & (-1793);
        L6:
            r12.setSystemUiVisibility(r22);
            return;
        L5:
            r22 = r0 | 1792;
            goto L6
        }
    }

    /* loaded from: classes3.dex */
    public static class Api30Impl {
        public static void setDecorFitsSystemWindows(Window r2, boolean r3) {
            View r0 = r2.getDecorView();
            int r1 = r0.getSystemUiVisibility();
            if (r3 == false) goto L5;
            int r12 = r1 & (-257);
        L6:
            r0.setSystemUiVisibility(r12);
            r2.setDecorFitsSystemWindows(r3);
            return;
        L5:
            r12 = r1 | 256;
            goto L6
        }
    }

    public static class Api35Impl {
        public static void setDecorFitsSystemWindows(Window r0, boolean r1) {
            r0.setDecorFitsSystemWindows(r1);
        }
    }

    public static WindowInsetsControllerCompat getInsetsController(Window r1, View r2) {
        return new WindowInsetsControllerCompat(r1, r2);
    }

    public static void setDecorFitsSystemWindows(Window r2, boolean r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 35) goto L7;
        Api35Impl.setDecorFitsSystemWindows(r2, r3);
        return;
    L7:
        if (r0 < 30) goto L10;
        Api30Impl.setDecorFitsSystemWindows(r2, r3);
        return;
    L10:
        Api16Impl.setDecorFitsSystemWindows(r2, r3);
    }
}
