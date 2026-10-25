package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R$styleable;
import androidx.core.widget.PopupWindowCompat;

/* loaded from: classes3.dex */
class AppCompatPopupWindow extends PopupWindow {
    private static final boolean COMPAT_OVERLAP_ANCHOR = false;
    private boolean mOverlapAnchor;

    static {
    }

    public AppCompatPopupWindow(Context r1, AttributeSet r2, int r3, int r4) {
        super(r1, r2, r3, r4);
        init(r1, r2, r3, r4);
    }

    private void init(Context r2, AttributeSet r3, int r4, int r5) {
        TintTypedArray r22 = TintTypedArray.obtainStyledAttributes(r2, r3, R$styleable.PopupWindow, r4, r5);
        if (r22.hasValue(R$styleable.PopupWindow_overlapAnchor) == false) goto L5;
        setSupportOverlapAnchor(r22.getBoolean(R$styleable.PopupWindow_overlapAnchor, false));
    L5:
        setBackgroundDrawable(r22.getDrawable(R$styleable.PopupWindow_android_popupBackground));
        r22.recycle();
    }

    private void setSupportOverlapAnchor(boolean r2) {
        if (COMPAT_OVERLAP_ANCHOR == false) goto L6;
        this.mOverlapAnchor = r2;
        return;
    L6:
        PopupWindowCompat.setOverlapAnchor(this, r2);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View r2, int r3, int r4) {
        if (COMPAT_OVERLAP_ANCHOR == true) goto L5;
    L7:
        super.showAsDropDown(r2, r3, r4);
        return;
    L5:
        if (this.mOverlapAnchor == false) goto L7;
        r4 = r4 - r2.getHeight();
        goto L7
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View r2, int r3, int r4, int r5) {
        if (COMPAT_OVERLAP_ANCHOR == true) goto L5;
    L7:
        super.showAsDropDown(r2, r3, r4, r5);
        return;
    L5:
        if (this.mOverlapAnchor == false) goto L7;
        r4 = r4 - r2.getHeight();
        goto L7
    }

    @Override // android.widget.PopupWindow
    public void update(View r7, int r8, int r9, int r10, int r11) {
        if (COMPAT_OVERLAP_ANCHOR == true) goto L5;
    L7:
        super.update(r7, r8, r9, r10, r11);
        return;
    L5:
        if (this.mOverlapAnchor == false) goto L7;
        r9 = r9 - r7.getHeight();
        goto L7
    }
}
