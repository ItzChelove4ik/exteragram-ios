package androidx.core.view;

import android.os.Build;
import android.view.Display;

/* loaded from: classes3.dex */
public abstract class DisplayCompat {
    public static RoundedCornerCompat getRoundedCorner(Display r2, int r3) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return null;
    L5:
        return RoundedCornerCompat.toRoundedCornerCompat(r2.getRoundedCorner(r3));
    }
}
