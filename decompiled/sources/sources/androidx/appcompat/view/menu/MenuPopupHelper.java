package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.GravityCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class MenuPopupHelper {
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final PopupWindow.OnDismissListener mInternalOnDismissListener;
    private final MenuBuilder mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private MenuPopup mPopup;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;

    /* renamed from: androidx.appcompat.view.menu.MenuPopupHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements PopupWindow.OnDismissListener {
        final /* synthetic */ MenuPopupHelper this$0;

        public AnonymousClass1(MenuPopupHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            this.this$0.onDismiss();
        }
    }

    public MenuPopupHelper(Context r8, MenuBuilder r9, View r10, boolean r11, int r12) {
        this(r8, r9, r10, r11, r12, 0);
    }

    public MenuPopupHelper(Context r2, MenuBuilder r3, View r4, boolean r5, int r6, int r7) {
        this.mDropDownGravity = PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
        this.mInternalOnDismissListener = new AnonymousClass1(this);
        this.mContext = r2;
        this.mMenu = r3;
        this.mAnchorView = r4;
        this.mOverflowOnly = r5;
        this.mPopupStyleAttr = r6;
        this.mPopupStyleRes = r7;
    }

    private MenuPopup createPopup() {
        Display r0 = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        Point r1 = new Point();
        r0.getRealSize(r1);
        if (Math.min(r1.x, r1.y) < this.mContext.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width)) goto L5;
        MenuPopup r2 = new CascadingMenuPopup(this.mContext, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly);
    L6:
        r2.addMenu(this.mMenu);
        r2.setOnDismissListener(this.mInternalOnDismissListener);
        r2.setAnchorView(this.mAnchorView);
        r2.setCallback(this.mPresenterCallback);
        r2.setForceShowIcon(this.mForceShowIcon);
        r2.setGravity(this.mDropDownGravity);
        return r2;
    L5:
        r2 = new StandardMenuPopup(this.mContext, this.mMenu, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly);
        goto L6
    }

    private void showPopup(int r3, int r4, boolean r5, boolean r6) {
        MenuPopup r0 = getPopup();
        r0.setShowTitle(r6);
        if (r5 == true) goto L5;
    L8:
        r0.show();
        return;
    L5:
        if ((GravityCompat.getAbsoluteGravity(this.mDropDownGravity, this.mAnchorView.getLayoutDirection()) & 7) != 5) goto L7;
        r3 = r3 - this.mAnchorView.getWidth();
    L7:
        r0.setHorizontalOffset(r3);
        r0.setVerticalOffset(r4);
        int r2 = (int) ((this.mContext.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
        r0.setEpicenterBounds(new Rect(r3 - r2, r4 - r2, r3 + r2, r4 + r2));
        goto L8
    }

    public void dismiss() {
        if (isShowing() == false) goto L6;
        this.mPopup.dismiss();
        return;
    }

    public MenuPopup getPopup() {
        if (this.mPopup != null) goto L6;
        this.mPopup = createPopup();
    L6:
        return this.mPopup;
    }

    public boolean isShowing() {
        MenuPopup r0 = this.mPopup;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isShowing() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void onDismiss() {
        this.mPopup = null;
        PopupWindow.OnDismissListener r1 = this.mOnDismissListener;
        if (r1 == null) goto L6;
        r1.onDismiss();
        return;
    }

    public void setAnchorView(View r1) {
        this.mAnchorView = r1;
    }

    public void setForceShowIcon(boolean r1) {
        this.mForceShowIcon = r1;
        MenuPopup r0 = this.mPopup;
        if (r0 == null) goto L6;
        r0.setForceShowIcon(r1);
        return;
    }

    public void setGravity(int r1) {
        this.mDropDownGravity = r1;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener r1) {
        this.mOnDismissListener = r1;
    }

    public void setPresenterCallback(MenuPresenter.Callback r1) {
        this.mPresenterCallback = r1;
        MenuPopup r0 = this.mPopup;
        if (r0 == null) goto L6;
        r0.setCallback(r1);
        return;
    }

    public void show() {
        if (tryShow() == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("MenuPopupHelper cannot be used without an anchor");
    }

    public boolean tryShow() {
        if (isShowing() == false) goto L6;
        return true;
    L6:
        if (this.mAnchorView != null) goto L8;
        return false;
    L8:
        showPopup(0, 0, false, false);
        return true;
    }

    public boolean tryShow(int r3, int r4) {
        if (isShowing() == false) goto L6;
        return true;
    L6:
        if (this.mAnchorView != null) goto L9;
        return false;
    L9:
        showPopup(r3, r4, true, true);
        return true;
    }
}
