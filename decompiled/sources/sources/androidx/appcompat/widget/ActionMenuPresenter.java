package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ActionProvider;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
    private final SparseBooleanArray mActionButtonGroups;
    ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    OverflowMenuButton mOverflowButton;
    OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback;
    OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    public class ActionButtonSubmenu extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter this$0;

        public ActionButtonSubmenu(ActionMenuPresenter r7, Context r8, SubMenuBuilder r9, View r10) {
            this.this$0 = r7;
            super(r8, r9, r10, false, R$attr.actionOverflowMenuStyle);
            if (((MenuItemImpl) r9.getItem()).isActionButton() == true) goto L8;
            View r6 = r7.mOverflowButton;
            if (r6 != null) goto L7;
            r6 = (View) ActionMenuPresenter.access$200(r7);
        L7:
            setAnchorView(r6);
        L8:
            setPresenterCallback(r7.mPopupPresenterCallback);
        }

        @Override // androidx.appcompat.view.menu.MenuPopupHelper
        public void onDismiss() {
            ActionMenuPresenter r0 = this.this$0;
            r0.mActionButtonPopup = null;
            r0.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    public class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
        final /* synthetic */ ActionMenuPresenter this$0;

        public ActionMenuPopupCallback(ActionMenuPresenter r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.PopupCallback
        public ShowableListMenu getPopup() {
            ActionButtonSubmenu r0 = this.this$0.mActionButtonPopup;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getPopup();
        }
    }

    public class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;
        final /* synthetic */ ActionMenuPresenter this$0;

        public OpenOverflowRunnable(ActionMenuPresenter r1, OverflowPopup r2) {
            this.this$0 = r1;
            this.mPopup = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.access$400(this.this$0) == null) goto L5;
            ActionMenuPresenter.access$500(this.this$0).changeMenuMode();
        L5:
            View r0 = (View) ActionMenuPresenter.access$600(this.this$0);
            if (r0 != null) goto L8;
        L12:
            this.this$0.mPostedOpenRunnable = null;
            return;
        L8:
            if (r0.getWindowToken() == null) goto L12;
            if (this.mPopup.tryShow() == false) goto L12;
            ActionMenuPresenter r02 = this.this$0;
            r02.mOverflowPopup = this.mPopup;
            goto L12
        }
    }

    public class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
        final /* synthetic */ ActionMenuPresenter this$0;

        /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$OverflowMenuButton$1, reason: invalid class name */
        public class AnonymousClass1 extends ForwardingListener {
            final /* synthetic */ OverflowMenuButton this$1;
            final /* synthetic */ ActionMenuPresenter val$this$0;

            public AnonymousClass1(OverflowMenuButton r1, View r2, ActionMenuPresenter r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // androidx.appcompat.widget.ForwardingListener
            public ShowableListMenu getPopup() {
                OverflowPopup r0 = this.this$1.this$0.mOverflowPopup;
                if (r0 != null) goto L7;
                return null;
            L7:
                return r0.getPopup();
            }

            @Override // androidx.appcompat.widget.ForwardingListener
            public boolean onForwardingStarted() {
                this.this$1.this$0.showOverflowMenu();
                return true;
            }

            @Override // androidx.appcompat.widget.ForwardingListener
            public boolean onForwardingStopped() {
                ActionMenuPresenter r1 = this.this$1.this$0;
                if (r1.mPostedOpenRunnable == null) goto L6;
                return false;
            L6:
                r1.hideOverflowMenu();
                return true;
            }
        }

        public OverflowMenuButton(ActionMenuPresenter r3, Context r4) {
            this.this$0 = r3;
            super(r4, null, R$attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            TooltipCompat.setTooltipText(this, getContentDescription());
            setOnTouchListener(new AnonymousClass1(this, this, r3));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
        public boolean needsDividerAfter() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.ActionMenuChildView
        public boolean needsDividerBefore() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick() == false) goto L5;
            return true;
        L5:
            playSoundEffect(0);
            this.this$0.showOverflowMenu();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int r4, int r5, int r6, int r7) {
            boolean r42 = super.setFrame(r4, r5, r6, r7);
            Drawable r52 = getDrawable();
            Drawable r62 = getBackground();
            if (r52 == null) goto L6;
            if (r62 == null) goto L6;
            int r53 = getWidth();
            int r72 = getHeight();
            int r0 = Math.max(r53, r72) / 2;
            int r1 = getPaddingLeft() - getPaddingRight();
            int r54 = (r53 + r1) / 2;
            int r73 = (r72 + (getPaddingTop() - getPaddingBottom())) / 2;
            DrawableCompat.setHotspotBounds(r62, r54 - r0, r73 - r0, r54 + r0, r73 + r0);
        L6:
            return r42;
        }
    }

    public class OverflowPopup extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter this$0;

        public OverflowPopup(ActionMenuPresenter r7, Context r8, MenuBuilder r9, View r10, boolean r11) {
            this.this$0 = r7;
            super(r8, r9, r10, r11, R$attr.actionOverflowMenuStyle);
            setGravity(8388613);
            setPresenterCallback(r7.mPopupPresenterCallback);
        }

        @Override // androidx.appcompat.view.menu.MenuPopupHelper
        public void onDismiss() {
            if (ActionMenuPresenter.access$000(this.this$0) == null) goto L5;
            ActionMenuPresenter.access$100(this.this$0).close();
        L5:
            this.this$0.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    public class PopupPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ ActionMenuPresenter this$0;

        public PopupPresenterCallback(ActionMenuPresenter r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder r3, boolean r4) {
            if ((r3 instanceof SubMenuBuilder) == false) goto L5;
            r3.getRootMenu().close(false);
        L5:
            MenuPresenter.Callback r2 = this.this$0.getCallback();
            if (r2 == null) goto L9;
            r2.onCloseMenu(r3, r4);
            return;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder r4) {
            if (r4 != ActionMenuPresenter.access$300(this.this$0)) goto L5;
            return false;
        L5:
            ActionMenuPresenter r0 = this.this$0;
            r0.mOpenSubMenuId = ((SubMenuBuilder) r4).getItem().getItemId();
            MenuPresenter.Callback r3 = this.this$0.getCallback();
            if (r3 != null) goto L8;
            return false;
        L8:
            return r3.onOpenSubMenu(r4);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        public int openSubMenuId;

        /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r1) {
                return new SavedState(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState() {
        }

        public SavedState(Parcel r1) {
            this.openSubMenuId = r1.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            r1.writeInt(this.openSubMenuId);
        }
    }

    public ActionMenuPresenter(Context r3) {
        super(r3, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new PopupPresenterCallback(this);
    }

    public static /* synthetic */ MenuBuilder access$000(ActionMenuPresenter r0) {
        return r0.mMenu;
    }

    public static /* synthetic */ MenuBuilder access$100(ActionMenuPresenter r0) {
        return r0.mMenu;
    }

    public static /* synthetic */ MenuView access$200(ActionMenuPresenter r0) {
        return r0.mMenuView;
    }

    public static /* synthetic */ MenuBuilder access$300(ActionMenuPresenter r0) {
        return r0.mMenu;
    }

    public static /* synthetic */ MenuBuilder access$400(ActionMenuPresenter r0) {
        return r0.mMenu;
    }

    public static /* synthetic */ MenuBuilder access$500(ActionMenuPresenter r0) {
        return r0.mMenu;
    }

    public static /* synthetic */ MenuView access$600(ActionMenuPresenter r0) {
        return r0.mMenuView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View findViewForItem(MenuItem r6) {
        ViewGroup r5 = (ViewGroup) this.mMenuView;
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L13;
        View r3 = r5.getChildAt(r2);
        if ((r3 instanceof MenuView.ItemView) == false) goto L12;
        if (((MenuView.ItemView) r3).getItemData() != r6) goto L12;
        return r3;
    L12:
        r2 = r2 + 1;
        goto L6
    L13:
        return null;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public void bindItemView(MenuItemImpl r2, MenuView.ItemView r3) {
        r3.initialize(r2, 0);
        ActionMenuItemView r32 = (ActionMenuItemView) r3;
        r32.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.mPopupCallback != null) goto L5;
        this.mPopupCallback = new ActionMenuPopupCallback(this);
    L5:
        r32.setPopupCallback(this.mPopupCallback);
    }

    public boolean dismissPopupMenus() {
        boolean r0 = hideOverflowMenu();
        return hideSubMenus() | r0;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public boolean filterLeftoverView(ViewGroup r3, int r4) {
        if (r3.getChildAt(r4) != this.mOverflowButton) goto L7;
        return false;
    L7:
        return super.filterLeftoverView(r3, r4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        ActionMenuPresenter r0 = this;
        MenuBuilder r1 = r0.mMenu;
        View r2 = null;
        ?? r3 = 0;
        if (r1 == null) goto L5;
        ArrayList<MenuItemImpl> r12 = r1.getVisibleItems();
        int r4 = r12.size();
    L6:
        int r5 = r0.mMaxItems;
        int r6 = r0.mActionItemWidthLimit;
        int r7 = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup r8 = (ViewGroup) r0.mMenuView;
        int r9 = 0;
        boolean r10 = false;
        int r11 = 0;
        int r122 = 0;
    L7:
        if (r9 >= r4) goto L22;
        MenuItemImpl r14 = r12.get(r9);
        if (r14.requiresActionButton() == false) goto L12;
        r11 = r11 + 1;
    L16:
        if (r0.mExpandedActionViewsExclusive == false) goto L20;
        if (r14.isActionViewExpanded() == false) goto L20;
        r5 = 0;
    L20:
        r9 = r9 + 1;
        goto L7
    L12:
        if (r14.requestsActionButton() == false) goto L14;
        r122 = r122 + 1;
        goto L16
    L14:
        r10 = true;
        goto L16
    L22:
        if (r0.mReserveOverflow == false) goto L27;
        if (r10 == false) goto L25;
    L26:
        r5 = r5 - 1;
        goto L27
    L25:
        if ((r122 + r11) > r5) goto L26;
    L27:
        int r52 = r5 - r11;
        SparseBooleanArray r92 = r0.mActionButtonGroups;
        r92.clear();
        if (r0.mStrictWidthLimit == false) goto L30;
        int r102 = r0.mMinCellSize;
        int r112 = r6 / r102;
        int r103 = r102 + ((r6 % r102) / r112);
    L31:
        int r123 = 0;
        int r142 = 0;
    L32:
        if (r123 >= r4) goto L96;
        MenuItemImpl r15 = r12.get(r123);
        if (r15.requiresActionButton() == false) goto L47;
        View r13 = r0.getItemView(r15, r2, r8);
        if (r0.mStrictWidthLimit == false) goto L38;
        r112 = r112 - ActionMenuView.measureChildForCells(r13, r103, r112, r7, r3);
    L39:
        int r22 = r13.getMeasuredWidth();
        r6 = r6 - r22;
        if (r142 != 0) goto L42;
        r142 = r22;
    L42:
        int r23 = r15.getGroupId();
        if (r23 == 0) goto L45;
        r92.put(r23, true);
    L45:
        r15.setIsActionButton(true);
        boolean r02 = r3;
        int r17 = r4;
    L95:
        r123 = r123 + 1;
        r3 = r02;
        r4 = r17;
        r2 = null;
        r0 = this;
        goto L32
    L38:
        r13.measure(r7, r7);
        goto L39
    L47:
        if (r15.requestsActionButton() == false) goto L94;
        int r24 = r15.getGroupId();
        boolean r132 = r92.get(r24);
        if (r52 > 0) goto L51;
        if (r132 == true) goto L51;
    L56:
        boolean r32 = false;
    L57:
        boolean r18 = r32;
        r17 = r4;
        if (r32 == false) goto L78;
        View r42 = r0.getItemView(r15, null, r8);
        if (r0.mStrictWidthLimit == false) goto L65;
        int r19 = ActionMenuView.measureChildForCells(r42, r103, r112, r7, 0);
        r112 = r112 - r19;
        if (r19 != 0) goto L64;
        r18 = false;
    L64:
        boolean r33 = r18;
        int r43 = r42.getMeasuredWidth();
        r6 = r6 - r43;
        if (r142 != 0) goto L70;
        r142 = r43;
    L70:
        if (r0.mStrictWidthLimit == false) goto L76;
        if (r6 < 0) goto L73;
    L72:
        boolean r44 = true;
    L74:
        r32 = r33 & r44;
    L73:
        r44 = false;
        goto L74
    L76:
        if ((r6 + r142) <= 0) goto L73;
    L65:
        r42.measure(r7, r7);
    L78:
        if (r32 == false) goto L81;
        if (r24 == 0) goto L81;
        r92.put(r24, true);
    L91:
        if (r32 == false) goto L93;
        r52 = r52 - 1;
    L93:
        r15.setIsActionButton(r32);
        r02 = false;
    L81:
        if (r132 == false) goto L91;
        r92.put(r24, false);
        int r45 = 0;
    L83:
        if (r45 >= r123) goto L91;
        MenuItemImpl r133 = r12.get(r45);
        if (r133.getGroupId() != r24) goto L90;
        if (r133.isActionButton() == false) goto L89;
        r52 = r52 + 1;
    L89:
        r133.setIsActionButton(false);
    L90:
        r45 = r45 + 1;
    L51:
        if (r6 <= 0) goto L56;
        if (r0.mStrictWidthLimit == false) goto L55;
        if (r112 <= 0) goto L56;
    L55:
        r32 = true;
        goto L57
    L94:
        r02 = r3;
        r17 = r4;
        r15.setIsActionButton(r02);
        goto L95
    L96:
        return true;
    L30:
        r103 = 0;
        r112 = 0;
        goto L31
    L5:
        r12 = null;
        r4 = 0;
        goto L6
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public View getItemView(MenuItemImpl r3, View r4, ViewGroup r5) {
        View r0 = r3.getActionView();
        if (r0 != null) goto L5;
    L6:
        r0 = super.getItemView(r3, r4, r5);
    L8:
        if (r3.isActionViewExpanded() == false) goto L10;
        int r2 = 8;
    L11:
        r0.setVisibility(r2);
        ActionMenuView r52 = (ActionMenuView) r5;
        ViewGroup.LayoutParams r22 = r0.getLayoutParams();
        if (r52.checkLayoutParams(r22) == true) goto L14;
        r0.setLayoutParams(r52.generateLayoutParams(r22));
    L14:
        return r0;
    L10:
        r2 = 0;
        goto L11
    L5:
        if (r3.hasCollapsibleActionView() == false) goto L8;
        goto L6
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public MenuView getMenuView(ViewGroup r2) {
        MenuView r0 = this.mMenuView;
        MenuView r22 = super.getMenuView(r2);
        if (r0 == r22) goto L5;
        ((ActionMenuView) r22).setPresenter(this);
    L5:
        return r22;
    }

    public Drawable getOverflowIcon() {
        OverflowMenuButton r0 = this.mOverflowButton;
        if (r0 == null) goto L7;
        return r0.getDrawable();
    L7:
        if (this.mPendingOverflowIconSet == true) goto L9;
        return null;
    L9:
        return this.mPendingOverflowIcon;
    }

    public boolean hideOverflowMenu() {
        OpenOverflowRunnable r0 = this.mPostedOpenRunnable;
        if (r0 == null) goto L8;
        Object r2 = this.mMenuView;
        if (r2 == null) goto L8;
        ((View) r2).removeCallbacks(r0);
        this.mPostedOpenRunnable = null;
        return true;
    L8:
        OverflowPopup r3 = this.mOverflowPopup;
        if (r3 == null) goto L12;
        r3.dismiss();
        return true;
    L12:
        return false;
    }

    public boolean hideSubMenus() {
        ActionButtonSubmenu r0 = this.mActionButtonPopup;
        if (r0 == null) goto L6;
        r0.dismiss();
        return true;
    L6:
        return false;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context r5, MenuBuilder r6) {
        super.initForMenu(r5, r6);
        Resources r62 = r5.getResources();
        ActionBarPolicy r52 = ActionBarPolicy.get(r5);
        if (this.mReserveOverflowSet == true) goto L6;
        this.mReserveOverflow = r52.showsOverflowMenuButton();
    L6:
        if (this.mWidthLimitSet == true) goto L9;
        this.mWidthLimit = r52.getEmbeddedMenuWidthLimit();
    L9:
        if (this.mMaxItemsSet == true) goto L11;
        this.mMaxItems = r52.getMaxActionButtons();
    L11:
        int r53 = this.mWidthLimit;
        if (this.mReserveOverflow == true) goto L14;
        this.mOverflowButton = null;
    L21:
        this.mActionItemWidthLimit = r53;
        this.mMinCellSize = (int) (r62.getDisplayMetrics().density * 56.0f);
        return;
    L14:
        if (this.mOverflowButton != null) goto L19;
        OverflowMenuButton r0 = new OverflowMenuButton(this, this.mSystemContext);
        this.mOverflowButton = r0;
        if (this.mPendingOverflowIconSet == false) goto L18;
        r0.setImageDrawable(this.mPendingOverflowIcon);
        this.mPendingOverflowIcon = null;
        this.mPendingOverflowIconSet = false;
    L18:
        int r02 = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mOverflowButton.measure(r02, r02);
    L19:
        r53 = r53 - this.mOverflowButton.getMeasuredWidth();
        goto L21
    }

    public boolean isOverflowMenuShowPending() {
        if (this.mPostedOpenRunnable == null) goto L5;
        return true;
    L5:
        if (isOverflowMenuShowing() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isOverflowMenuShowing() {
        OverflowPopup r0 = this.mOverflowPopup;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isShowing() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder r1, boolean r2) {
        dismissPopupMenus();
        super.onCloseMenu(r1, r2);
    }

    public void onConfigurationChanged(Configuration r1) {
        if (this.mMaxItemsSet == true) goto L5;
        this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
    L5:
        MenuBuilder r0 = this.mMenu;
        if (r0 == null) goto L9;
        r0.onItemsChanged(true);
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == false) goto L13;
        int r22 = ((SavedState) r2).openSubMenuId;
        if (r22 <= 0) goto L11;
        MenuItem r23 = this.mMenu.findItem(r22);
        if (r23 == null) goto L12;
        onSubMenuSelected((SubMenuBuilder) r23.getSubMenu());
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        SavedState r0 = new SavedState();
        r0.openSubMenuId = this.mOpenSubMenuId;
        return r0;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder r8) {
        boolean r1 = false;
        if (r8.hasVisibleItems() == true) goto L5;
        return false;
    L5:
        SubMenuBuilder r0 = r8;
    L7:
        if (r0.getParentMenu() == this.mMenu) goto L9;
        r0 = (SubMenuBuilder) r0.getParentMenu();
        goto L7
    L9:
        View r02 = findViewForItem(r0.getItem());
        if (r02 != null) goto L12;
        return false;
    L12:
        this.mOpenSubMenuId = r8.getItem().getItemId();
        int r2 = r8.size();
        int r3 = 0;
    L14:
        if (r3 >= r2) goto L21;
        MenuItem r5 = r8.getItem(r3);
        if (r5.isVisible() == false) goto L20;
        if (r5.getIcon() == null) goto L20;
        r1 = true;
    L20:
        r3 = r3 + 1;
    L21:
        ActionButtonSubmenu r22 = new ActionButtonSubmenu(this, this.mContext, r8, r02);
        this.mActionButtonPopup = r22;
        r22.setForceShowIcon(r1);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(r8);
        return true;
    }

    @Override // androidx.core.view.ActionProvider.SubUiVisibilityListener
    public void onSubUiVisibilityChanged(boolean r1) {
        if (r1 == false) goto L5;
        super.onSubMenuSelected(null);
        return;
    L5:
        MenuBuilder r0 = this.mMenu;
        if (r0 == null) goto L9;
        r0.close(false);
        return;
    }

    public void setExpandedActionViewsExclusive(boolean r1) {
        this.mExpandedActionViewsExclusive = r1;
    }

    public void setMenuView(ActionMenuView r1) {
        this.mMenuView = r1;
        r1.initialize(this.mMenu);
    }

    public void setOverflowIcon(Drawable r2) {
        OverflowMenuButton r0 = this.mOverflowButton;
        if (r0 == null) goto L6;
        r0.setImageDrawable(r2);
        return;
    L6:
        this.mPendingOverflowIconSet = true;
        this.mPendingOverflowIcon = r2;
    }

    public void setReserveOverflow(boolean r1) {
        this.mReserveOverflow = r1;
        this.mReserveOverflowSet = true;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter
    public boolean shouldIncludeItem(int r1, MenuItemImpl r2) {
        return r2.isActionButton();
    }

    public boolean showOverflowMenu() {
        if (this.mReserveOverflow == true) goto L5;
        return false;
    L5:
        if (isOverflowMenuShowing() == true) goto L18;
        MenuBuilder r0 = this.mMenu;
        if (r0 != null) goto L9;
        return false;
    L9:
        if (this.mMenuView != null) goto L11;
        return false;
    L11:
        if (this.mPostedOpenRunnable == null) goto L13;
        return false;
    L13:
        if (r0.getNonActionItems().isEmpty() == true) goto L22;
        OpenOverflowRunnable r7 = new OpenOverflowRunnable(this, new OverflowPopup(this, this.mContext, this.mMenu, this.mOverflowButton, true));
        this.mPostedOpenRunnable = r7;
        ((View) this.mMenuView).post(r7);
        return true;
    L22:
        return false;
    L18:
        return false;
    }

    @Override // androidx.appcompat.view.menu.BaseMenuPresenter, androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean r5) {
        super.updateMenuView(r5);
        ((View) this.mMenuView).requestLayout();
        MenuBuilder r52 = this.mMenu;
        boolean r0 = false;
        if (r52 == null) goto L10;
        ArrayList<MenuItemImpl> r53 = r52.getActionItems();
        int r1 = r53.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        ActionProvider r3 = r53.get(r2).getSupportActionProvider();
        if (r3 == null) goto L9;
        r3.setSubUiVisibilityListener(this);
    L9:
        r2 = r2 + 1;
    L10:
        MenuBuilder r54 = this.mMenu;
        if (r54 == null) goto L13;
        ArrayList<MenuItemImpl> r55 = r54.getNonActionItems();
    L15:
        if (this.mReserveOverflow == false) goto L22;
        if (r55 == null) goto L22;
        int r12 = r55.size();
        if (r12 != 1) goto L20;
        r0 = !r55.get(0).isActionViewExpanded();
        goto L22
    L20:
        if (r12 <= 0) goto L22;
        r0 = true;
    L22:
        OverflowMenuButton r56 = this.mOverflowButton;
        if (r0 == false) goto L31;
        if (r56 != null) goto L26;
        this.mOverflowButton = new OverflowMenuButton(this, this.mSystemContext);
    L26:
        ViewGroup r57 = (ViewGroup) this.mOverflowButton.getParent();
        if (r57 == this.mMenuView) goto L35;
        if (r57 == null) goto L30;
        r57.removeView(this.mOverflowButton);
    L30:
        ActionMenuView r58 = (ActionMenuView) this.mMenuView;
        r58.addView(this.mOverflowButton, r58.generateOverflowButtonLayoutParams());
    L35:
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
        return;
    L31:
        if (r56 == null) goto L35;
        Object r59 = r56.getParent();
        Object r02 = this.mMenuView;
        if (r59 != r02) goto L35;
        ((ViewGroup) r02).removeView(this.mOverflowButton);
        goto L35
    L13:
        r55 = null;
        goto L15
    }
}
