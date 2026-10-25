package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuPopupWindow;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
final class StandardMenuPopup extends MenuPopup implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener {
    private static final int ITEM_LAYOUT = 0;
    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private final View.OnAttachStateChangeListener mAttachStateChangeListener;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity;
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasContentWidth;
    private final MenuBuilder mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    final MenuPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private boolean mShowTitle;
    View mShownAnchorView;
    ViewTreeObserver mTreeObserver;
    private boolean mWasDismissed;

    /* renamed from: androidx.appcompat.view.menu.StandardMenuPopup$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ StandardMenuPopup this$0;

        public AnonymousClass1(StandardMenuPopup r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (this.this$0.isShowing() == true) goto L5;
            return;
        L5:
            if (this.this$0.mPopup.isModal() == true) goto L16;
            View r0 = this.this$0.mShownAnchorView;
            if (r0 != null) goto L9;
        L13:
            this.this$0.dismiss();
            return;
        L9:
            if (r0.isShown() == false) goto L13;
            this.this$0.mPopup.show();
            return;
        }
    }

    /* renamed from: androidx.appcompat.view.menu.StandardMenuPopup$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnAttachStateChangeListener {
        final /* synthetic */ StandardMenuPopup this$0;

        public AnonymousClass2(StandardMenuPopup r1) {
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
            StandardMenuPopup r02 = this.this$0;
            r02.mTreeObserver = r3.getViewTreeObserver();
        L7:
            StandardMenuPopup r03 = this.this$0;
            r03.mTreeObserver.removeGlobalOnLayoutListener(r03.mGlobalLayoutListener);
            goto L8
        }
    }

    static {
        ITEM_LAYOUT = R$layout.abc_popup_menu_item_layout;
    }

    public StandardMenuPopup(Context r4, MenuBuilder r5, View r6, int r7, int r8, boolean r9) {
        this.mGlobalLayoutListener = new AnonymousClass1(this);
        this.mAttachStateChangeListener = new AnonymousClass2(this);
        this.mDropDownGravity = 0;
        this.mContext = r4;
        this.mMenu = r5;
        this.mOverflowOnly = r9;
        this.mAdapter = new MenuAdapter(r5, LayoutInflater.from(r4), r9, ITEM_LAYOUT);
        this.mPopupStyleAttr = r7;
        this.mPopupStyleRes = r8;
        Resources r92 = r4.getResources();
        this.mPopupMaxWidth = Math.max(r92.getDisplayMetrics().widthPixels / 2, r92.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.mAnchorView = r6;
        this.mPopup = new MenuPopupWindow(r4, null, r7, r8);
        r5.addMenuPresenter(this, r4);
    }

    private boolean tryShow() {
        if (isShowing() == false) goto L6;
        return true;
    L6:
        if (this.mWasDismissed == true) goto L30;
        View r0 = this.mAnchorView;
        if (r0 == null) goto L30;
        this.mShownAnchorView = r0;
        this.mPopup.setOnDismissListener(this);
        this.mPopup.setOnItemClickListener(this);
        this.mPopup.setModal(true);
        View r02 = this.mShownAnchorView;
        if (this.mTreeObserver != null) goto L13;
        boolean r3 = true;
    L14:
        ViewTreeObserver r4 = r02.getViewTreeObserver();
        this.mTreeObserver = r4;
        if (r3 == false) goto L17;
        r4.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
    L17:
        r02.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
        this.mPopup.setAnchorView(r02);
        this.mPopup.setDropDownGravity(this.mDropDownGravity);
        if (this.mHasContentWidth == true) goto L20;
        this.mContentWidth = MenuPopup.measureIndividualMenuWidth(this.mAdapter, null, this.mContext, this.mPopupMaxWidth);
        this.mHasContentWidth = true;
    L20:
        this.mPopup.setContentWidth(this.mContentWidth);
        this.mPopup.setInputMethodMode(2);
        this.mPopup.setEpicenterBounds(getEpicenterBounds());
        this.mPopup.show();
        ListView r03 = this.mPopup.getListView();
        r03.setOnKeyListener(this);
        if (this.mShowTitle == true) goto L23;
    L28:
        this.mPopup.setAdapter(this.mAdapter);
        this.mPopup.show();
        return true;
    L23:
        if (this.mMenu.getHeaderTitle() == null) goto L28;
        FrameLayout r42 = (FrameLayout) LayoutInflater.from(this.mContext).inflate(R$layout.abc_popup_menu_header_item_layout, r03, false);
        TextView r5 = (TextView) r42.findViewById(R.id.title);
        if (r5 == null) goto L27;
        r5.setText(this.mMenu.getHeaderTitle());
    L27:
        r42.setEnabled(false);
        r03.addHeaderView(r42, null, false);
        goto L28
    L13:
        r3 = false;
    L30:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void addMenu(MenuBuilder r1) {
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        if (isShowing() == false) goto L6;
        this.mPopup.dismiss();
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.mPopup.getListView();
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        if (this.mWasDismissed == false) goto L5;
        return false;
    L5:
        if (this.mPopup.isShowing() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder r2, boolean r3) {
        if (r2 != this.mMenu) goto L10;
        dismiss();
        MenuPresenter.Callback r1 = this.mPresenterCallback;
        if (r1 == null) goto L9;
        r1.onCloseMenu(r2, r3);
        return;
    L9:
        return;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.mWasDismissed = true;
        this.mMenu.close();
        ViewTreeObserver r0 = this.mTreeObserver;
        if (r0 != null) goto L5;
    L8:
        this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
        PopupWindow.OnDismissListener r2 = this.mOnDismissListener;
        if (r2 == null) goto L12;
        r2.onDismiss();
        return;
    L12:
        return;
    L5:
        if (r0.isAlive() == true) goto L7;
        this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
    L7:
        this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
        this.mTreeObserver = null;
        goto L8
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
    public boolean onSubMenuSelected(SubMenuBuilder r10) {
        if (r10.hasVisibleItems() == false) goto L14;
        MenuPopupHelper r2 = new MenuPopupHelper(this.mContext, r10, this.mShownAnchorView, this.mOverflowOnly, this.mPopupStyleAttr, this.mPopupStyleRes);
        r2.setPresenterCallback(this.mPresenterCallback);
        r2.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(r10));
        r2.setOnDismissListener(this.mOnDismissListener);
        this.mOnDismissListener = null;
        this.mMenu.close(false);
        int r102 = this.mPopup.getHorizontalOffset();
        int r0 = this.mPopup.getVerticalOffset();
        if ((Gravity.getAbsoluteGravity(this.mDropDownGravity, this.mAnchorView.getLayoutDirection()) & 7) != 5) goto L8;
        r102 = r102 + this.mAnchorView.getWidth();
    L8:
        if (r2.tryShow(r102, r0) == false) goto L14;
        MenuPresenter.Callback r9 = this.mPresenterCallback;
        if (r9 == null) goto L15;
        r9.onOpenSubMenu(r10);
        return true;
    L15:
        return true;
    L14:
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setAnchorView(View r1) {
        this.mAnchorView = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback r1) {
        this.mPresenterCallback = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setForceShowIcon(boolean r1) {
        this.mAdapter.setForceShowIcon(r1);
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setGravity(int r1) {
        this.mDropDownGravity = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setHorizontalOffset(int r1) {
        this.mPopup.setHorizontalOffset(r1);
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
    public void setVerticalOffset(int r1) {
        this.mPopup.setVerticalOffset(r1);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if (tryShow() == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("StandardMenuPopup cannot be used without an anchor");
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean r1) {
        this.mHasContentWidth = false;
        MenuAdapter r0 = this.mAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
