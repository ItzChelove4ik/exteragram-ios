package androidx.appcompat.widget;

import android.os.Build;
import android.view.View;

/* loaded from: classes3.dex */
public abstract class TooltipCompat {

    public static class Api26Impl {
        public static void setTooltipText(View r0, CharSequence r1) {
            r0.setTooltipText(r1);
        }
    }

    public static void setTooltipText(View r2, CharSequence r3) {
        if (Build.VERSION.SDK_INT < 26) goto L6;
        Api26Impl.setTooltipText(r2, r3);
        return;
    L6:
        TooltipCompatHandler.setTooltipText(r2, r3);
    }
}
