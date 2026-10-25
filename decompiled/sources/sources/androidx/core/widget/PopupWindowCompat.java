package androidx.core.widget;

import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes3.dex */
public abstract class PopupWindowCompat {
    public static void setOverlapAnchor(PopupWindow r0, boolean r1) {
        r0.setOverlapAnchor(r1);
    }

    public static void setWindowLayoutType(PopupWindow r0, int r1) {
        r0.setWindowLayoutType(r1);
    }

    @Deprecated
    public static void showAsDropDown(PopupWindow r0, View r1, int r2, int r3, int r4) {
        r0.showAsDropDown(r1, r2, r3, r4);
    }
}
