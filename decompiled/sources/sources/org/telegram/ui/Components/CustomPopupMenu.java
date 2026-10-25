package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class CustomPopupMenu {
    boolean isShowing;
    ActionBarPopupWindow.ActionBarPopupWindowLayout popupLayout;
    ActionBarPopupWindow popupWindow;

    public static /* synthetic */ void $r8$lambda$8hbg6RhuOAWi4Rdw3g6vXBjV1JI(CustomPopupMenu r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ boolean $r8$lambda$8twGlW1tAntSeYVCmLf585qj50M(CustomPopupMenu r0, View r1, MotionEvent r2) {
        return r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fVb5N9e5hcPA9cFi8bp3K03Nv9s(CustomPopupMenu r0, KeyEvent r1) {
        r0.lambda$new$1(r1);
    }

    public CustomPopupMenu(Context r3, Theme.ResourcesProvider r4, boolean r5) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r3, R.drawable.popup_fixed_alert2, r4, r5 ? 1 : 0);
        this.popupLayout = r0;
        r0.setAnimationEnabled(false);
        this.popupLayout.setOnTouchListener(new CustomPopupMenu$$ExternalSyntheticLambda0(this));
        this.popupLayout.setDispatchKeyEventListener(new CustomPopupMenu$$ExternalSyntheticLambda1(this));
        this.popupLayout.setShownFromBottom(false);
        onCreate(this.popupLayout);
        ActionBarPopupWindow r42 = new ActionBarPopupWindow(this.popupLayout, -2, -2);
        this.popupWindow = r42;
        r42.setAnimationEnabled(false);
        this.popupWindow.setAnimationStyle(R.style.PopupContextAnimation2);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setClippingEnabled(true);
        this.popupWindow.setInputMethodMode(2);
        this.popupWindow.setSoftInputMode(0);
        this.popupWindow.getContentView().setFocusableInTouchMode(true);
        if (AndroidUtilities.isAccessibilityTouchExplorationEnabled() == false) goto L5;
        this.popupWindow.setFocusable(true);
    L5:
        this.popupWindow.setOnDismissListener(new CustomPopupMenu$$ExternalSyntheticLambda2(this));
    }

    private /* synthetic */ boolean lambda$new$0(View r3, MotionEvent r4) {
        if (r4.getActionMasked() != 1) goto L13;
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.isShowing() == false) goto L15;
        Rect r02 = AndroidUtilities.rectTmp2;
        r3.getHitRect(r02);
        if (r02.contains((int) r4.getX(), (int) r4.getY()) == true) goto L16;
        this.popupWindow.dismiss();
        return false;
    L16:
        return false;
    L15:
        return false;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$new$1(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.popupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        this.popupWindow.dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$new$2() {
        onDismissed();
        this.isShowing = false;
    }

    public void dismiss() {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 == null) goto L6;
        r0.dismiss();
        return;
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public abstract void onCreate(ActionBarPopupWindow.ActionBarPopupWindowLayout r1);

    public abstract void onDismissed();

    public void show(View r2, int r3, int r4) {
        this.isShowing = true;
        this.popupWindow.showAsDropDown(r2, r3, r4);
    }
}
