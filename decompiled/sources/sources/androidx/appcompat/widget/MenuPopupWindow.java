package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener {
    private static Method sSetTouchModalMethod;
    private MenuItemHoverListener mHoverListener;

    public static class Api23Impl {
        public static void setEnterTransition(PopupWindow r0, Transition r1) {
            r0.setEnterTransition(r1);
        }

        public static void setExitTransition(PopupWindow r0, Transition r1) {
            r0.setExitTransition(r1);
        }
    }

    public static class Api29Impl {
        public static void setTouchModal(PopupWindow r0, boolean r1) {
            r0.setTouchModal(r1);
        }
    }

    public static class MenuDropDownListView extends DropDownListView {
        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public MenuDropDownListView(Context r3, boolean r4) {
            super(r3, r4);
            if (1 != r3.getResources().getConfiguration().getLayoutDirection()) goto L6;
            this.mAdvanceKey = 21;
            this.mRetreatKey = 22;
            return;
        L6:
            this.mAdvanceKey = 22;
            this.mRetreatKey = 21;
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // androidx.appcompat.widget.DropDownListView
        public /* bridge */ /* synthetic */ int measureHeightOfChildrenCompat(int r1, int r2, int r3, int r4, int r5) {
            return super.measureHeightOfChildrenCompat(r1, r2, r3, r4, r5);
        }

        @Override // androidx.appcompat.widget.DropDownListView
        public /* bridge */ /* synthetic */ boolean onForwardedEvent(MotionEvent r1, int r2) {
            return super.onForwardedEvent(r1, r2);
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.view.View
        public boolean onHoverEvent(MotionEvent r5) {
            if (this.mHoverListener == null) goto L27;
            ListAdapter r0 = getAdapter();
            if ((r0 instanceof HeaderViewListAdapter) == false) goto L7;
            HeaderViewListAdapter r02 = (HeaderViewListAdapter) r0;
            int r1 = r02.getHeadersCount();
            MenuAdapter r03 = (MenuAdapter) r02.getWrappedAdapter();
        L9:
            if (r5.getAction() == 10) goto L17;
            int r2 = pointToPosition((int) r5.getX(), (int) r5.getY());
            if (r2 == (-1)) goto L17;
            int r22 = r2 - r1;
            if (r22 < 0) goto L17;
            if (r22 >= r03.getCount()) goto L17;
            MenuItemImpl r12 = r03.getItem(r22);
        L18:
            MenuItem r23 = this.mHoveredMenuItem;
            if (r23 == r12) goto L27;
            MenuBuilder r04 = r03.getAdapterMenu();
            if (r23 == null) goto L23;
            this.mHoverListener.onItemHoverExit(r04, r23);
        L23:
            this.mHoveredMenuItem = r12;
            if (r12 == null) goto L27;
            this.mHoverListener.onItemHoverEnter(r04, r12);
        L17:
            r12 = null;
            goto L18
        L7:
            r03 = (MenuAdapter) r0;
            r1 = 0;
        L27:
            return super.onHoverEvent(r5);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int r5, KeyEvent r6) {
            ListMenuItemView r0 = (ListMenuItemView) getSelectedView();
            if (r0 != null) goto L5;
        L12:
            if (r0 == null) goto L22;
            if (r5 != this.mRetreatKey) goto L22;
            setSelection(-1);
            ListAdapter r4 = getAdapter();
            if ((r4 instanceof HeaderViewListAdapter) == false) goto L18;
            MenuAdapter r42 = (MenuAdapter) ((HeaderViewListAdapter) r4).getWrappedAdapter();
        L19:
            r42.getAdapterMenu().close(false);
            return true;
        L18:
            r42 = (MenuAdapter) r4;
        L22:
            return super.onKeyDown(r5, r6);
        L5:
            if (r5 != this.mAdvanceKey) goto L12;
            if (r0.isEnabled() == true) goto L9;
        L11:
            return true;
        L9:
            if (r0.getItemData().hasSubMenu() == false) goto L11;
            performItemClick(r0, getSelectedItemPosition(), getSelectedItemId());
            goto L11
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent r1) {
            return super.onTouchEvent(r1);
        }

        public void setHoverListener(MenuItemHoverListener r1) {
            this.mHoverListener = r1;
        }

        @Override // androidx.appcompat.widget.DropDownListView, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable r1) {
            super.setSelector(r1);
        }
    }

    static {
    L6:
        Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        return;
    L3:
        if (Build.VERSION.SDK_INT > 28) goto L10;
        sSetTouchModalMethod = PopupWindow.class.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});     // Catch: NoSuchMethodException -> L6
        return;
    }

    public MenuPopupWindow(Context r1, AttributeSet r2, int r3, int r4) {
        super(r1, r2, r3, r4);
    }

    @Override // androidx.appcompat.widget.ListPopupWindow
    public DropDownListView createDropDownListView(Context r2, boolean r3) {
        MenuDropDownListView r0 = new MenuDropDownListView(r2, r3);
        r0.setHoverListener(this);
        return r0;
    }

    @Override // androidx.appcompat.widget.MenuItemHoverListener
    public void onItemHoverEnter(MenuBuilder r1, MenuItem r2) {
        MenuItemHoverListener r0 = this.mHoverListener;
        if (r0 == null) goto L6;
        r0.onItemHoverEnter(r1, r2);
        return;
    }

    @Override // androidx.appcompat.widget.MenuItemHoverListener
    public void onItemHoverExit(MenuBuilder r1, MenuItem r2) {
        MenuItemHoverListener r0 = this.mHoverListener;
        if (r0 == null) goto L6;
        r0.onItemHoverExit(r1, r2);
        return;
    }

    public void setEnterTransition(Object r1) {
        Api23Impl.setEnterTransition(this.mPopup, (Transition) r1);
    }

    public void setExitTransition(Object r1) {
        Api23Impl.setExitTransition(this.mPopup, (Transition) r1);
    }

    public void setHoverListener(MenuItemHoverListener r1) {
        this.mHoverListener = r1;
    }

    public void setTouchModal(boolean r4) {
        if (Build.VERSION.SDK_INT > 28) goto L10;
        Method r0 = sSetTouchModalMethod;
        if (r0 == null) goto L14;
        r0.invoke(this.mPopup, new Object[]{Boolean.valueOf(r4)});     // Catch: Exception -> L8
        return;
    L8:
        Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
        return;
    L14:
        return;
    L10:
        Api29Impl.setTouchModal(this.mPopup, r4);
    }
}
