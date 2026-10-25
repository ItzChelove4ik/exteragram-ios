package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.GravityCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int ITEM_LAYOUT = 0;
    private View mAnchorView;
    private final View.OnAttachStateChangeListener mAttachStateChangeListener;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener;
    private final int mMenuMaxWidth;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List<MenuBuilder> mPendingMenus;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private int mRawDropDownGravity;
    boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    final List<CascadingMenuInfo> mShowingMenus;
    View mShownAnchorView;
    final Handler mSubMenuHoverHandler;
    ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;

    /* renamed from: androidx.appcompat.view.menu.CascadingMenuPopup$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ CascadingMenuPopup this$0;

        public AnonymousClass1(CascadingMenuPopup r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (this.this$0.isShowing() == true) goto L5;
            return;
        L5:
            if (this.this$0.mShowingMenus.size() > 0) goto L7;
            return;
        L7:
            if (this.this$0.mShowingMenus.get(0).window.isModal() == true) goto L22;
            View r0 = this.this$0.mShownAnchorView;
            if (r0 != null) goto L11;
        L17:
            this.this$0.dismiss();
            return;
        L11:
            if (r0.isShown() == false) goto L17;
            Iterator<CascadingMenuInfo> r2 = this.this$0.mShowingMenus.iterator();
        L15:
            if (r2.hasNext() == false) goto L23;
            r2.next().window.show();
            goto L15
        L23:
            return;
        }
    }

    /* renamed from: androidx.appcompat.view.menu.CascadingMenuPopup$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnAttachStateChangeListener {
        final /* synthetic */ CascadingMenuPopup this$0;

        public AnonymousClass2(CascadingMenuPopup r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r3) {
            ViewTreeObserver r0 = this.this$0.mTreeObserver;
            if (r0 != null) goto L5;
        L8:
            r3.removeOnAttachStateChangeListener(this);
            return;
        L5:
            if (r0.isAlive() == true) goto L7;
            CascadingMenuPopup r02 = this.this$0;
            r02.mTreeObserver = r3.getViewTreeObserver();
        L7:
            CascadingMenuPopup r03 = this.this$0;
            r03.mTreeObserver.removeGlobalOnLayoutListener(r03.mGlobalLayoutListener);
            goto L8
        }
    }

    /* renamed from: androidx.appcompat.view.menu.CascadingMenuPopup$3, reason: invalid class name */
    public class AnonymousClass3 implements MenuItemHoverListener {
        final /* synthetic */ CascadingMenuPopup this$0;

        /* renamed from: androidx.appcompat.view.menu.CascadingMenuPopup$3$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass3 this$1;
            final /* synthetic */ MenuItem val$item;
            final /* synthetic */ MenuBuilder val$menu;
            final /* synthetic */ CascadingMenuInfo val$nextInfo;

            public AnonymousClass1(AnonymousClass3 r1, CascadingMenuInfo r2, MenuItem r3, MenuBuilder r4) {
                this.this$1 = r1;
                this.val$nextInfo = r2;
                this.val$item = r3;
                this.val$menu = r4;
            }

            @Override // java.lang.Runnable
            public void run() {
                CascadingMenuInfo r0 = this.val$nextInfo;
                if (r0 == null) goto L6;
                this.this$1.this$0.mShouldCloseImmediately = true;
                r0.menu.close(false);
                this.this$1.this$0.mShouldCloseImmediately = false;
            L6:
                if (this.val$item.isEnabled() == true) goto L8;
                return;
            L8:
                if (this.val$item.hasSubMenu() == false) goto L12;
                this.val$menu.performItemAction(this.val$item, 4);
                return;
            }
        }

        public AnonymousClass3(CascadingMenuPopup r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public void onItemHoverEnter(MenuBuilder r6, MenuItem r7) {
            CascadingMenuInfo r1 = null;
            this.this$0.mSubMenuHoverHandler.removeCallbacksAndMessages(null);
            int r0 = this.this$0.mShowingMenus.size();
            int r2 = 0;
        L4:
            if (r2 >= r0) goto L9;
            if (r6 == this.this$0.mShowingMenus.get(r2).menu) goto L10;
            r2 = r2 + 1;
        L10:
            if (r2 != (-1)) goto L12;
            return;
        L12:
            int r22 = r2 + 1;
            if (r22 >= this.this$0.mShowingMenus.size()) goto L15;
            r1 = this.this$0.mShowingMenus.get(r22);
        L15:
            this.this$0.mSubMenuHoverHandler.postAtTime(new AnonymousClass1(this, r1, r7, r6), r6, SystemClock.uptimeMillis() + 200);
            return;
        L9:
            r2 = -1;
            goto L10
        }

        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public void onItemHoverExit(MenuBuilder r1, MenuItem r2) {
            this.this$0.mSubMenuHoverHandler.removeCallbacksAndMessages(r1);
        }
    }

    public static class CascadingMenuInfo {
        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(MenuPopupWindow r1, MenuBuilder r2, int r3) {
            this.window = r1;
            this.menu = r2;
            this.position = r3;
        }

        public ListView getListView() {
            return this.window.getListView();
        }
    }

    static {
        ITEM_LAYOUT = R$layout.abc_cascading_menu_item_layout;
    }

    public CascadingMenuPopup(Context r2, View r3, int r4, int r5, boolean r6) {
        this.mPendingMenus = new ArrayList();
        this.mShowingMenus = new ArrayList();
        this.mGlobalLayoutListener = new AnonymousClass1(this);
        this.mAttachStateChangeListener = new AnonymousClass2(this);
        this.mMenuItemHoverListener = new AnonymousClass3(this);
        this.mRawDropDownGravity = 0;
        this.mDropDownGravity = 0;
        this.mContext = r2;
        this.mAnchorView = r3;
        this.mPopupStyleAttr = r4;
        this.mPopupStyleRes = r5;
        this.mOverflowOnly = r6;
        this.mForceShowIcon = false;
        this.mLastPosition = getInitialMenuPosition();
        Resources r22 = r2.getResources();
        this.mMenuMaxWidth = Math.max(r22.getDisplayMetrics().widthPixels / 2, r22.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.mSubMenuHoverHandler = new Handler();
    }

    private MenuPopupWindow createPopupWindow() {
        MenuPopupWindow r0 = new MenuPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
        r0.setHoverListener(this.mMenuItemHoverListener);
        r0.setOnItemClickListener(this);
        r0.setOnDismissListener(this);
        r0.setAnchorView(this.mAnchorView);
        r0.setDropDownGravity(this.mDropDownGravity);
        r0.setModal(true);
        r0.setInputMethodMode(2);
        return r0;
    }

    private int findIndexOfAddedMenu(MenuBuilder r4) {
        int r0 = this.mShowingMenus.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (r4 == this.mShowingMenus.get(r1).menu) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    private MenuItem findMenuItemForSubmenu(MenuBuilder r4, MenuBuilder r5) {
        int r3 = r4.size();
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L10;
        MenuItem r1 = r4.getItem(r0);
        if (r1.hasSubMenu() == false) goto L9;
        if (r5 != r1.getSubMenu()) goto L9;
        return r1;
    L9:
        r0 = r0 + 1;
        goto L3
    L10:
        return null;
    }

    private View findParentViewForSubmenu(CascadingMenuInfo r7, MenuBuilder r8) {
        MenuItem r6 = findMenuItemForSubmenu(r7.menu, r8);
        if (r6 != null) goto L5;
        return null;
    L5:
        ListView r72 = r7.getListView();
        ListAdapter r0 = r72.getAdapter();
        int r2 = 0;
        if ((r0 instanceof HeaderViewListAdapter) == false) goto L8;
        HeaderViewListAdapter r02 = (HeaderViewListAdapter) r0;
        int r1 = r02.getHeadersCount();
        MenuAdapter r03 = (MenuAdapter) r02.getWrappedAdapter();
    L9:
        int r3 = r03.getCount();
    L11:
        if (r2 >= r3) goto L16;
        if (r6 == r03.getItem(r2)) goto L17;
        r2 = r2 + 1;
    L17:
        if (r2 != (-1)) goto L19;
        return null;
    L19:
        int r22 = (r2 + r1) - r72.getFirstVisiblePosition();
        if (r22 >= 0) goto L22;
    L26:
        return null;
    L22:
        if (r22 >= r72.getChildCount()) goto L26;
        return r72.getChildAt(r22);
    L16:
        r2 = -1;
        goto L17
    L8:
        r03 = (MenuAdapter) r0;
        r1 = 0;
        goto L9
    }

    private int getInitialMenuPosition() {
        if (this.mAnchorView.getLayoutDirection() != 1) goto L6;
        return 0;
    L6:
        return 1;
    }

    private int getNextMenuPosition(int r6) {
        List<CascadingMenuInfo> r0 = this.mShowingMenus;
        ListView r02 = r0.get(r0.size() - 1).getListView();
        int[] r1 = new int[2];
        r02.getLocationOnScreen(r1);
        Rect r3 = new Rect();
        this.mShownAnchorView.getWindowVisibleDisplayFrame(r3);
        if (this.mLastPosition != 1) goto L9;
        if (((r1[0] + r02.getWidth()) + r6) <= r3.right) goto L7;
        return 0;
    L7:
        return 1;
    L9:
        if ((r1[0] - r6) >= 0) goto L11;
        return 1;
    L11:
        return 0;
    }

    private void showMenu(MenuBuilder r15) {
        LayoutInflater r0 = LayoutInflater.from(this.mContext);
        MenuAdapter r1 = new MenuAdapter(r15, r0, this.mOverflowOnly, ITEM_LAYOUT);
        if (isShowing() == true) goto L8;
        if (this.mForceShowIcon == false) goto L8;
        r1.setForceShowIcon(true);
    L10:
        int r2 = MenuPopup.measureIndividualMenuWidth(r1, null, this.mContext, this.mMenuMaxWidth);
        MenuPopupWindow r4 = createPopupWindow();
        r4.setAdapter(r1);
        r4.setContentWidth(r2);
        r4.setDropDownGravity(this.mDropDownGravity);
        if (this.mShowingMenus.size() <= 0) goto L13;
        List<CascadingMenuInfo> r12 = this.mShowingMenus;
        CascadingMenuInfo r13 = r12.get(r12.size() - 1);
        View r6 = findParentViewForSubmenu(r13, r15);
    L15:
        if (r6 == null) goto L37;
        r4.setTouchModal(false);
        r4.setEnterTransition(null);
        int r8 = getNextMenuPosition(r2);
        if (r8 != 1) goto L19;
        boolean r9 = true;
    L20:
        this.mLastPosition = r8;
        if (Build.VERSION.SDK_INT < 26) goto L23;
        r4.setAnchorView(r6);
        int r82 = 0;
        int r122 = 0;
    L28:
        if ((this.mDropDownGravity & 5) != 5) goto L33;
        if (r9 == false) goto L31;
    L30:
        int r123 = r122 + r2;
    L35:
        r4.setHorizontalOffset(r123);
        r4.setOverlapAnchor(true);
        r4.setVerticalOffset(r82);
    L43:
        this.mShowingMenus.add(new CascadingMenuInfo(r4, r15, this.mLastPosition));
        r4.show();
        ListView r22 = r4.getListView();
        r22.setOnKeyListener(this);
        if (r13 == null) goto L46;
        return;
    L46:
        if (this.mShowTitle == true) goto L48;
        return;
    L48:
        if (r15.getHeaderTitle() == null) goto L53;
        FrameLayout r14 = (FrameLayout) r0.inflate(R$layout.abc_popup_menu_header_item_layout, r22, false);
        TextView r02 = (TextView) r14.findViewById(R.id.title);
        r14.setEnabled(false);
        r02.setText(r15.getHeaderTitle());
        r22.addHeaderView(r14, null, false);
        r4.show();
        return;
    L53:
        return;
    L31:
        r2 = r6.getWidth();
    L32:
        r123 = r122 - r2;
        goto L35
    L33:
        if (r9 == false) goto L32;
        r2 = r6.getWidth();
        goto L30
    L23:
        int[] r10 = new int[2];
        this.mAnchorView.getLocationOnScreen(r10);
        int[] r83 = new int[2];
        r6.getLocationOnScreen(r83);
        if ((this.mDropDownGravity & 7) != 5) goto L26;
        r10[0] = r10[0] + this.mAnchorView.getWidth();
        r83[0] = r83[0] + r6.getWidth();
    L26:
        r122 = r83[0] - r10[0];
        r82 = r83[1] - r10[1];
        goto L28
    L19:
        r9 = false;
        goto L20
    L37:
        if (this.mHasXOffset == false) goto L40;
        r4.setHorizontalOffset(this.mXOffset);
    L40:
        if (this.mHasYOffset == false) goto L42;
        r4.setVerticalOffset(this.mYOffset);
    L42:
        r4.setEpicenterBounds(getEpicenterBounds());
        goto L43
    L13:
        r13 = null;
        r6 = null;
    L8:
        if (isShowing() == false) goto L10;
        r1.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(r15));
        goto L10
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void addMenu(MenuBuilder r2) {
        r2.addMenuPresenter(this, this.mContext);
        if (isShowing() == false) goto L6;
        showMenu(r2);
        return;
    L6:
        this.mPendingMenus.add(r2);
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public boolean closeMenuOnSubMenuOpened() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        int r0 = this.mShowingMenus.size();
        if (r0 <= 0) goto L10;
        CascadingMenuInfo[] r3 = (CascadingMenuInfo[]) this.mShowingMenus.toArray(new CascadingMenuInfo[r0]);
        int r02 = r0 - 1;
    L5:
        if (r02 < 0) goto L14;
        CascadingMenuInfo r1 = r3[r02];
        if (r1.window.isShowing() == false) goto L9;
        r1.window.dismiss();
    L9:
        r02 = r02 - 1;
        goto L5
    L14:
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        if (this.mShowingMenus.isEmpty() == false) goto L7;
        return null;
    L7:
        return this.mShowingMenus.get(r1.size() - 1).getListView();
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        if (this.mShowingMenus.size() > 0) goto L5;
    L8:
        return false;
    L5:
        if (this.mShowingMenus.get(0).window.isShowing() == false) goto L8;
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder r6, boolean r7) {
        int r0 = findIndexOfAddedMenu(r6);
        if (r0 < 0) goto L31;
        int r1 = r0 + 1;
        if (r1 >= this.mShowingMenus.size()) goto L8;
        this.mShowingMenus.get(r1).menu.close(false);
    L8:
        CascadingMenuInfo r02 = this.mShowingMenus.remove(r0);
        r02.menu.removeMenuPresenter(this);
        if (this.mShouldCloseImmediately == false) goto L11;
        r02.window.setExitTransition(null);
        r02.window.setAnimationStyle(0);
    L11:
        r02.window.dismiss();
        int r03 = this.mShowingMenus.size();
        if (r03 <= 0) goto L14;
        this.mLastPosition = this.mShowingMenus.get(r03 - 1).position;
    L15:
        if (r03 != 0) goto L27;
        dismiss();
        MenuPresenter.Callback r72 = this.mPresenterCallback;
        if (r72 == null) goto L19;
        r72.onCloseMenu(r6, true);
    L19:
        ViewTreeObserver r62 = this.mTreeObserver;
        if (r62 != null) goto L22;
    L25:
        this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
        this.mOnDismissListener.onDismiss();
        return;
    L22:
        if (r62.isAlive() == false) goto L24;
        this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
    L24:
        this.mTreeObserver = null;
        goto L25
    L27:
        if (r7 == false) goto L30;
        this.mShowingMenus.get(0).menu.close(false);
        return;
    L30:
        return;
    L14:
        this.mLastPosition = getInitialMenuPosition();
        goto L15
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        int r0 = this.mShowingMenus.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        CascadingMenuInfo r3 = this.mShowingMenus.get(r2);
        if (r3.window.isShowing() == false) goto L9;
        r2 = r2 + 1;
    L9:
        if (r3 == null) goto L14;
        r3.menu.close(false);
        return;
    L14:
        return;
    L8:
        r3 = null;
        goto L9
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View r1, int r2, KeyEvent r3) {
        if (r3.getAction() == 1) goto L5;
        return false;
    L5:
        if (r2 != 82) goto L10;
        dismiss();
        return true;
    L10:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable r1) {
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder r5) {
        Iterator<CascadingMenuInfo> r0 = this.mShowingMenus.iterator();
    L4:
        if (r0.hasNext() == false) goto L10;
        CascadingMenuInfo r1 = r0.next();
        if (r5 != r1.menu) goto L4;
        r1.getListView().requestFocus();
        return true;
    L10:
        if (r5.hasVisibleItems() == false) goto L15;
        addMenu(r5);
        MenuPresenter.Callback r4 = this.mPresenterCallback;
        if (r4 == null) goto L14;
        r4.onOpenSubMenu(r5);
    L14:
        return true;
    L15:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setAnchorView(View r2) {
        if (this.mAnchorView == r2) goto L6;
        this.mAnchorView = r2;
        this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, r2.getLayoutDirection());
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback r1) {
        this.mPresenterCallback = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setForceShowIcon(boolean r1) {
        this.mForceShowIcon = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setGravity(int r2) {
        if (this.mRawDropDownGravity == r2) goto L6;
        this.mRawDropDownGravity = r2;
        this.mDropDownGravity = GravityCompat.getAbsoluteGravity(r2, this.mAnchorView.getLayoutDirection());
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setHorizontalOffset(int r2) {
        this.mHasXOffset = true;
        this.mXOffset = r2;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setOnDismissListener(PopupWindow.OnDismissListener r1) {
        this.mOnDismissListener = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setShowTitle(boolean r1) {
        this.mShowTitle = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setVerticalOffset(int r2) {
        this.mHasYOffset = true;
        this.mYOffset = r2;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if (isShowing() == true) goto L22;
        Iterator<MenuBuilder> r0 = this.mPendingMenus.iterator();
    L7:
        if (r0.hasNext() == false) goto L9;
        showMenu(r0.next());
        goto L7
    L9:
        this.mPendingMenus.clear();
        View r02 = this.mAnchorView;
        this.mShownAnchorView = r02;
        if (r02 != null) goto L12;
        return;
    L12:
        if (this.mTreeObserver != null) goto L14;
        boolean r1 = true;
    L15:
        ViewTreeObserver r03 = r02.getViewTreeObserver();
        this.mTreeObserver = r03;
        if (r1 == false) goto L18;
        r03.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
    L18:
        this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
        return;
    L14:
        r1 = false;
        goto L15
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean r1) {
        Iterator<CascadingMenuInfo> r0 = this.mShowingMenus.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        MenuPopup.toMenuAdapter(r0.next().getListView().getAdapter()).notifyDataSetChanged();
        goto L4
    }
}
