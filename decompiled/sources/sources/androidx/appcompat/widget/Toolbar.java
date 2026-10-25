package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.time.DurationKt;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class Toolbar extends ViewGroup implements MenuHost {
    private static final String TAG = "Toolbar";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private OnBackInvokedCallback mBackInvokedCallback;
    private boolean mBackInvokedCallbackEnabled;
    private OnBackInvokedDispatcher mBackInvokedDispatcher;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    MenuBuilder.Callback mMenuBuilderCallback;
    final MenuHostHelper mMenuHostHelper;
    ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private ArrayList<MenuItem> mProvidedMenuItems;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private ColorStateList mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private ColorStateList mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;

    /* renamed from: androidx.appcompat.widget.Toolbar$1, reason: invalid class name */
    public class AnonymousClass1 implements ActionMenuView.OnMenuItemClickListener {
        final /* synthetic */ Toolbar this$0;

        public AnonymousClass1(Toolbar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem r2) {
            if (this.this$0.mMenuHostHelper.onMenuItemSelected(r2) == false) goto L6;
            return true;
        L6:
            OnMenuItemClickListener r1 = this.this$0.mOnMenuItemClickListener;
            if (r1 != null) goto L9;
            return false;
        L9:
            return r1.onMenuItemClick(r2);
        }
    }

    /* renamed from: androidx.appcompat.widget.Toolbar$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ Toolbar this$0;

        public AnonymousClass2(Toolbar r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.showOverflowMenu();
        }
    }

    /* renamed from: androidx.appcompat.widget.Toolbar$3, reason: invalid class name */
    public class AnonymousClass3 implements MenuBuilder.Callback {
        final /* synthetic */ Toolbar this$0;

        public AnonymousClass3(Toolbar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder r1, MenuItem r2) {
            MenuBuilder.Callback r0 = this.this$0.mMenuBuilderCallback;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.onMenuItemSelected(r1, r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder r2) {
            if (this.this$0.mMenuView.isOverflowMenuShowing() == true) goto L5;
            this.this$0.mMenuHostHelper.onPrepareMenu(r2);
        L5:
            MenuBuilder.Callback r1 = this.this$0.mMenuBuilderCallback;
            if (r1 == null) goto L9;
            r1.onMenuModeChange(r2);
            return;
        }
    }

    /* renamed from: androidx.appcompat.widget.Toolbar$4, reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        final /* synthetic */ Toolbar this$0;

        public AnonymousClass4(Toolbar r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            this.this$0.collapseActionView();
        }
    }

    public static class Api33Impl {
        public static OnBackInvokedDispatcher findOnBackInvokedDispatcher(View r0) {
            return r0.findOnBackInvokedDispatcher();
        }

        public static OnBackInvokedCallback newOnBackInvokedCallback(Runnable r1) {
            Objects.requireNonNull(r1);
            return new Toolbar$Api33Impl$$ExternalSyntheticLambda0(r1);
        }

        public static void tryRegisterOnBackInvokedCallback(Object r1, Object r2) {
            ((OnBackInvokedDispatcher) r1).registerOnBackInvokedCallback(DurationKt.NANOS_IN_MILLIS, (OnBackInvokedCallback) r2);
        }

        public static void tryUnregisterOnBackInvokedCallback(Object r0, Object r1) {
            ((OnBackInvokedDispatcher) r0).unregisterOnBackInvokedCallback((OnBackInvokedCallback) r1);
        }
    }

    public class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;
        final /* synthetic */ Toolbar this$0;

        public ExpandedActionViewMenuPresenter(Toolbar r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public boolean collapseItemActionView(MenuBuilder r2, MenuItemImpl r3) {
            KeyEvent.Callback r22 = this.this$0.mExpandedActionView;
            if ((r22 instanceof CollapsibleActionView) == false) goto L5;
            ((CollapsibleActionView) r22).onActionViewCollapsed();
        L5:
            Toolbar r23 = this.this$0;
            r23.removeView(r23.mExpandedActionView);
            Toolbar r24 = this.this$0;
            r24.removeView(r24.mCollapseButtonView);
            Toolbar r25 = this.this$0;
            r25.mExpandedActionView = null;
            r25.addChildrenForExpandedActionView();
            this.mCurrentExpandedItem = null;
            this.this$0.requestLayout();
            r3.setActionViewExpanded(false);
            this.this$0.updateBackInvokedCallbackState();
            return true;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public boolean expandItemActionView(MenuBuilder r4, MenuItemImpl r5) {
            this.this$0.ensureCollapseButtonView();
            ViewParent r42 = this.this$0.mCollapseButtonView.getParent();
            Toolbar r0 = this.this$0;
            if (r42 != r0) goto L5;
        L8:
            Toolbar r43 = this.this$0;
            r43.mExpandedActionView = r5.getActionView();
            this.mCurrentExpandedItem = r5;
            ViewParent r44 = this.this$0.mExpandedActionView.getParent();
            Toolbar r02 = this.this$0;
            if (r44 != r02) goto L11;
        L14:
            this.this$0.removeChildrenForExpandedActionView();
            this.this$0.requestLayout();
            r5.setActionViewExpanded(true);
            KeyEvent.Callback r52 = this.this$0.mExpandedActionView;
            if ((r52 instanceof CollapsibleActionView) == false) goto L17;
            ((CollapsibleActionView) r52).onActionViewExpanded();
        L17:
            this.this$0.updateBackInvokedCallbackState();
            return true;
        L11:
            if ((r44 instanceof ViewGroup) == false) goto L13;
            ((ViewGroup) r44).removeView(r02.mExpandedActionView);
        L13:
            LayoutParams r45 = this.this$0.generateDefaultLayoutParams();
            Toolbar r03 = this.this$0;
            r45.gravity = (r03.mButtonGravity & 112) | PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
            r45.mViewType = 2;
            r03.mExpandedActionView.setLayoutParams(r45);
            Toolbar r46 = this.this$0;
            r46.addView(r46.mExpandedActionView);
            goto L14
        L5:
            if ((r42 instanceof ViewGroup) == false) goto L7;
            ((ViewGroup) r42).removeView(r0.mCollapseButtonView);
        L7:
            Toolbar r47 = this.this$0;
            r47.addView(r47.mCollapseButtonView);
            goto L8
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public boolean flagActionItems() {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public int getId() {
            return 0;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public void initForMenu(Context r2, MenuBuilder r3) {
            MenuBuilder r22 = this.mMenu;
            if (r22 == null) goto L7;
            MenuItemImpl r0 = this.mCurrentExpandedItem;
            if (r0 == null) goto L7;
            r22.collapseItemActionView(r0);
        L7:
            this.mMenu = r3;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public void onCloseMenu(MenuBuilder r1, boolean r2) {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public void onRestoreInstanceState(Parcelable r1) {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public Parcelable onSaveInstanceState() {
            return null;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public boolean onSubMenuSelected(SubMenuBuilder r1) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter
        public void updateMenuView(boolean r4) {
            if (this.mCurrentExpandedItem == null) goto L16;
            MenuBuilder r42 = this.mMenu;
            if (r42 == null) goto L12;
            int r43 = r42.size();
            int r0 = 0;
        L7:
            if (r0 >= r43) goto L12;
            if (this.mMenu.getItem(r0) == this.mCurrentExpandedItem) goto L10;
            r0 = r0 + 1;
            goto L7
        L10:
            return;
        L12:
            collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
            return;
        }
    }

    public static class LayoutParams extends ActionBar.LayoutParams {
        int mViewType;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.mViewType = 0;
            this.gravity = NavigationBarView.ITEM_GRAVITY_START_CENTER;
        }

        public LayoutParams(Context r1, AttributeSet r2) {
            super(r1, r2);
            this.mViewType = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
            this.mViewType = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r2) {
            super(r2);
            this.mViewType = 0;
            copyMarginsFromCompat(r2);
        }

        public LayoutParams(ActionBar.LayoutParams r1) {
            super(r1);
            this.mViewType = 0;
        }

        public LayoutParams(LayoutParams r2) {
            super(r2);
            this.mViewType = 0;
            this.mViewType = r2.mViewType;
        }

        public void copyMarginsFromCompat(ViewGroup.MarginLayoutParams r2) {
            ((ViewGroup.MarginLayoutParams) this).leftMargin = r2.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = r2.topMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = r2.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = r2.bottomMargin;
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem r1);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        int expandedMenuItemId;
        boolean isOverflowOpen;

        /* renamed from: androidx.appcompat.widget.Toolbar$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
            public AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r2) {
                return new SavedState(r2, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return new SavedState(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return createFromParcel(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            this.expandedMenuItemId = r1.readInt();
            if (r1.readInt() == 0) goto L5;
            boolean r12 = true;
        L6:
            this.isOverflowOpen = r12;
            return;
        L5:
            r12 = false;
            goto L6
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeInt(this.expandedMenuItemId);
            r1.writeInt(this.isOverflowOpen ? 1 : 0);
        }
    }

    public Toolbar(Context r2) {
        this(r2, null);
    }

    public Toolbar(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.toolbarStyle);
    }

    public Toolbar(Context r11, AttributeSet r12, int r13) {
        super(r11, r12, r13);
        this.mGravity = NavigationBarView.ITEM_GRAVITY_START_CENTER;
        this.mTempViews = new ArrayList();
        this.mHiddenViews = new ArrayList();
        this.mTempMargins = new int[2];
        this.mMenuHostHelper = new MenuHostHelper(new Toolbar$$ExternalSyntheticLambda1(this));
        this.mProvidedMenuItems = new ArrayList();
        this.mMenuViewItemClickListener = new AnonymousClass1(this);
        this.mShowOverflowMenuRunnable = new AnonymousClass2(this);
        TintTypedArray r0 = TintTypedArray.obtainStyledAttributes(getContext(), r12, R$styleable.Toolbar, r13, 0);
        ViewCompat.saveAttributeDataForStyleable(this, r11, R$styleable.Toolbar, r12, r0.getWrappedTypeArray(), r13, 0);
        this.mTitleTextAppearance = r0.getResourceId(R$styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = r0.getResourceId(R$styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = r0.getInteger(R$styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = r0.getInteger(R$styleable.Toolbar_buttonGravity, 48);
        int r10 = r0.getDimensionPixelOffset(R$styleable.Toolbar_titleMargin, 0);
        if (r0.hasValue(R$styleable.Toolbar_titleMargins) == false) goto L5;
        r10 = r0.getDimensionPixelOffset(R$styleable.Toolbar_titleMargins, r10);
    L5:
        this.mTitleMarginBottom = r10;
        this.mTitleMarginTop = r10;
        this.mTitleMarginEnd = r10;
        this.mTitleMarginStart = r10;
        int r102 = r0.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginStart, -1);
        if (r102 < 0) goto L8;
        this.mTitleMarginStart = r102;
    L8:
        int r103 = r0.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginEnd, -1);
        if (r103 < 0) goto L11;
        this.mTitleMarginEnd = r103;
    L11:
        int r104 = r0.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginTop, -1);
        if (r104 < 0) goto L14;
        this.mTitleMarginTop = r104;
    L14:
        int r105 = r0.getDimensionPixelOffset(R$styleable.Toolbar_titleMarginBottom, -1);
        if (r105 < 0) goto L17;
        this.mTitleMarginBottom = r105;
    L17:
        this.mMaxButtonHeight = r0.getDimensionPixelSize(R$styleable.Toolbar_maxButtonHeight, -1);
        int r106 = r0.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int r122 = r0.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int r132 = r0.getDimensionPixelSize(R$styleable.Toolbar_contentInsetLeft, 0);
        int r1 = r0.getDimensionPixelSize(R$styleable.Toolbar_contentInsetRight, 0);
        ensureContentInsets();
        this.mContentInsets.setAbsolute(r132, r1);
        if (r106 != Integer.MIN_VALUE) goto L20;
        if (r122 != Integer.MIN_VALUE) goto L20;
    L21:
        this.mContentInsetStartWithNavigation = r0.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = r0.getDimensionPixelOffset(R$styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.mCollapseIcon = r0.getDrawable(R$styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = r0.getText(R$styleable.Toolbar_collapseContentDescription);
        CharSequence r107 = r0.getText(R$styleable.Toolbar_title);
        if (TextUtils.isEmpty(r107) == true) goto L24;
        setTitle(r107);
    L24:
        CharSequence r108 = r0.getText(R$styleable.Toolbar_subtitle);
        if (TextUtils.isEmpty(r108) == true) goto L27;
        setSubtitle(r108);
    L27:
        this.mPopupContext = getContext();
        setPopupTheme(r0.getResourceId(R$styleable.Toolbar_popupTheme, 0));
        Drawable r109 = r0.getDrawable(R$styleable.Toolbar_navigationIcon);
        if (r109 == null) goto L30;
        setNavigationIcon(r109);
    L30:
        CharSequence r1010 = r0.getText(R$styleable.Toolbar_navigationContentDescription);
        if (TextUtils.isEmpty(r1010) == true) goto L33;
        setNavigationContentDescription(r1010);
    L33:
        Drawable r1011 = r0.getDrawable(R$styleable.Toolbar_logo);
        if (r1011 == null) goto L36;
        setLogo(r1011);
    L36:
        CharSequence r1012 = r0.getText(R$styleable.Toolbar_logoDescription);
        if (TextUtils.isEmpty(r1012) == true) goto L40;
        setLogoDescription(r1012);
    L40:
        if (r0.hasValue(R$styleable.Toolbar_titleTextColor) == false) goto L43;
        setTitleTextColor(r0.getColorStateList(R$styleable.Toolbar_titleTextColor));
    L43:
        if (r0.hasValue(R$styleable.Toolbar_subtitleTextColor) == false) goto L46;
        setSubtitleTextColor(r0.getColorStateList(R$styleable.Toolbar_subtitleTextColor));
    L46:
        if (r0.hasValue(R$styleable.Toolbar_menu) == false) goto L48;
        inflateMenu(r0.getResourceId(R$styleable.Toolbar_menu, 0));
    L48:
        r0.recycle();
        return;
    L20:
        this.mContentInsets.setRelative(r106, r122);
        goto L21
    }

    private void addCustomViewsWithGravity(List<View> r6, int r7) {
        int r1 = 0;
        if (getLayoutDirection() != 1) goto L5;
        boolean r0 = true;
    L6:
        int r3 = getChildCount();
        int r72 = GravityCompat.getAbsoluteGravity(r7, getLayoutDirection());
        r6.clear();
        if (r0 == false) goto L18;
        int r32 = r3 - 1;
    L9:
        if (r32 < 0) goto L27;
        View r02 = getChildAt(r32);
        LayoutParams r12 = (LayoutParams) r02.getLayoutParams();
        if (r12.mViewType != 0) goto L17;
        if (shouldLayout(r02) == false) goto L17;
        if (getChildHorizontalGravity(r12.gravity) != r72) goto L17;
        r6.add(r02);
    L17:
        r32 = r32 - 1;
        goto L9
    L27:
        return;
    L18:
        if (r1 >= r3) goto L38;
        View r03 = getChildAt(r1);
        LayoutParams r2 = (LayoutParams) r03.getLayoutParams();
        if (r2.mViewType != 0) goto L26;
        if (shouldLayout(r03) == false) goto L26;
        if (getChildHorizontalGravity(r2.gravity) != r72) goto L26;
        r6.add(r03);
    L26:
        r1 = r1 + 1;
        goto L18
    L38:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void addSystemView(View r3, boolean r4) {
        ViewGroup.LayoutParams r0 = r3.getLayoutParams();
        if (r0 != null) goto L6;
        LayoutParams r02 = generateDefaultLayoutParams();
    L9:
        r02.mViewType = 1;
        if (r4 == true) goto L12;
    L15:
        addView(r3, r02);
        return;
    L12:
        if (this.mExpandedActionView == null) goto L15;
        r3.setLayoutParams(r02);
        this.mHiddenViews.add(r3);
        return;
    L6:
        if (checkLayoutParams(r0) == true) goto L8;
        r02 = generateLayoutParams(r0);
        goto L9
    L8:
        r02 = (LayoutParams) r0;
        goto L9
    }

    private void ensureContentInsets() {
        if (this.mContentInsets != null) goto L6;
        this.mContentInsets = new RtlSpacingHelper();
        return;
    }

    private void ensureLogoView() {
        if (this.mLogoView != null) goto L6;
        this.mLogoView = new AppCompatImageView(getContext());
        return;
    }

    private void ensureMenu() {
        ensureMenuView();
        if (this.mMenuView.peekMenu() != null) goto L9;
        MenuBuilder r0 = (MenuBuilder) this.mMenuView.getMenu();
        if (this.mExpandedMenuPresenter != null) goto L7;
        this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(this);
    L7:
        this.mMenuView.setExpandedActionViewsExclusive(true);
        r0.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        updateBackInvokedCallbackState();
        return;
    }

    private void ensureMenuView() {
        if (this.mMenuView != null) goto L6;
        ActionMenuView r0 = new ActionMenuView(getContext());
        this.mMenuView = r0;
        r0.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
        this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, new AnonymousClass3(this));
        LayoutParams r02 = generateDefaultLayoutParams();
        r02.gravity = (this.mButtonGravity & 112) | 8388613;
        this.mMenuView.setLayoutParams(r02);
        addSystemView(this.mMenuView, false);
        return;
    }

    private void ensureNavButtonView() {
        if (this.mNavButtonView != null) goto L6;
        this.mNavButtonView = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
        LayoutParams r0 = generateDefaultLayoutParams();
        r0.gravity = (this.mButtonGravity & 112) | PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
        this.mNavButtonView.setLayoutParams(r0);
        return;
    }

    private int getChildHorizontalGravity(int r4) {
        int r3 = getLayoutDirection();
        int r42 = GravityCompat.getAbsoluteGravity(r4, r3) & 7;
        if (r42 != 1) goto L5;
    L11:
        return r42;
    L5:
        if (r42 == 3) goto L11;
        if (r42 == 5) goto L11;
        if (r3 != 1) goto L10;
        return 5;
    L10:
        return 3;
    }

    private int getChildTop(View r6, int r7) {
        LayoutParams r0 = (LayoutParams) r6.getLayoutParams();
        int r62 = r6.getMeasuredHeight();
        if (r7 <= 0) goto L5;
        int r72 = (r62 - r7) / 2;
    L6:
        int r2 = getChildVerticalGravity(r0.gravity);
        if (r2 == 48) goto L21;
        if (r2 == 80) goto L19;
        int r73 = getPaddingTop();
        int r22 = getPaddingBottom();
        int r5 = getHeight();
        int r3 = (((r5 - r73) - r22) - r62) / 2;
        int r4 = ((ViewGroup.MarginLayoutParams) r0).topMargin;
        if (r3 >= r4) goto L13;
        r3 = r4;
    L17:
        return r73 + r3;
    L13:
        int r52 = (((r5 - r22) - r62) - r3) - r73;
        int r63 = ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        if (r52 >= r63) goto L17;
        r3 = Math.max(0, r3 - (r63 - r52));
        goto L17
    L19:
        return (((getHeight() - getPaddingBottom()) - r62) - ((ViewGroup.MarginLayoutParams) r0).bottomMargin) - r72;
    L21:
        return getPaddingTop() - r72;
    L5:
        r72 = 0;
        goto L6
    }

    private int getChildVerticalGravity(int r2) {
        int r22 = r2 & 112;
        if (r22 != 16) goto L5;
    L10:
        return r22;
    L5:
        if (r22 == 48) goto L10;
        if (r22 == 80) goto L10;
        return this.mGravity & 112;
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> r0 = new ArrayList();
        Menu r3 = getMenu();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L6;
        r0.add(r3.getItem(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private int getHorizontalMargins(View r1) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r1.getLayoutParams();
        return r0.getMarginStart() + r0.getMarginEnd();
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(getContext());
    }

    private int getVerticalMargins(View r1) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r1.getLayoutParams();
        return r0.topMargin + r0.bottomMargin;
    }

    private int getViewListMeasuredWidth(List<View> r8, int[] r9) {
        int r0 = r9[0];
        int r92 = r9[1];
        int r1 = r8.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r1) goto L5;
        View r4 = r8.get(r2);
        LayoutParams r5 = (LayoutParams) r4.getLayoutParams();
        int r6 = ((ViewGroup.MarginLayoutParams) r5).leftMargin - r0;
        int r02 = ((ViewGroup.MarginLayoutParams) r5).rightMargin - r92;
        int r93 = Math.max(0, r6);
        int r52 = Math.max(0, r02);
        int r62 = Math.max(0, -r6);
        int r03 = Math.max(0, -r02);
        r3 = r3 + ((r93 + r4.getMeasuredWidth()) + r52);
        r2 = r2 + 1;
        r92 = r03;
        r0 = r62;
        goto L3
    L5:
        return r3;
    }

    private boolean isChildOrHidden(View r2) {
        if (r2.getParent() != this) goto L5;
        return true;
    L5:
        if (this.mHiddenViews.contains(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private int layoutChildLeft(View r5, int r6, int[] r7, int r8) {
        LayoutParams r0 = (LayoutParams) r5.getLayoutParams();
        int r1 = ((ViewGroup.MarginLayoutParams) r0).leftMargin - r7[0];
        int r62 = r6 + Math.max(0, r1);
        r7[0] = Math.max(0, -r1);
        int r4 = getChildTop(r5, r8);
        int r72 = r5.getMeasuredWidth();
        r5.layout(r62, r4, r62 + r72, r5.getMeasuredHeight() + r4);
        return r62 + (r72 + ((ViewGroup.MarginLayoutParams) r0).rightMargin);
    }

    private int layoutChildRight(View r6, int r7, int[] r8, int r9) {
        LayoutParams r0 = (LayoutParams) r6.getLayoutParams();
        int r1 = ((ViewGroup.MarginLayoutParams) r0).rightMargin - r8[1];
        int r72 = r7 - Math.max(0, r1);
        r8[1] = Math.max(0, -r1);
        int r5 = getChildTop(r6, r9);
        int r82 = r6.getMeasuredWidth();
        r6.layout(r72 - r82, r5, r72, r6.getMeasuredHeight() + r5);
        return r72 - (r82 + ((ViewGroup.MarginLayoutParams) r0).leftMargin);
    }

    private int measureChildCollapseMargins(View r8, int r9, int r10, int r11, int r12, int[] r13) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r8.getLayoutParams();
        int r1 = r0.leftMargin - r13[0];
        int r3 = r0.rightMargin - r13[1];
        int r5 = Math.max(0, r1) + Math.max(0, r3);
        r13[0] = Math.max(0, -r1);
        r13[1] = Math.max(0, -r3);
        r8.measure(ViewGroup.getChildMeasureSpec(r9, ((getPaddingLeft() + getPaddingRight()) + r5) + r10, r0.width), ViewGroup.getChildMeasureSpec(r11, (((getPaddingTop() + getPaddingBottom()) + r0.topMargin) + r0.bottomMargin) + r12, r0.height));
        return r8.getMeasuredWidth() + r5;
    }

    private void measureChildConstrained(View r4, int r5, int r6, int r7, int r8, int r9) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r4.getLayoutParams();
        int r52 = ViewGroup.getChildMeasureSpec(r5, (((getPaddingLeft() + getPaddingRight()) + r0.leftMargin) + r0.rightMargin) + r6, r0.width);
        int r3 = ViewGroup.getChildMeasureSpec(r7, (((getPaddingTop() + getPaddingBottom()) + r0.topMargin) + r0.bottomMargin) + r8, r0.height);
        int r62 = View.MeasureSpec.getMode(r3);
        if (r62 == 1073741824) goto L8;
        if (r9 < 0) goto L8;
        if (r62 == 0) goto L7;
        r9 = Math.min(View.MeasureSpec.getSize(r3), r9);
    L7:
        r3 = View.MeasureSpec.makeMeasureSpec(r9, TLObject.FLAG_30);
    L8:
        r4.measure(r52, r3);
    }

    private void onCreateMenu() {
        Menu r0 = getMenu();
        ArrayList<MenuItem> r1 = getCurrentMenuItems();
        this.mMenuHostHelper.onCreateMenu(r0, getMenuInflater());
        ArrayList<MenuItem> r02 = getCurrentMenuItems();
        r02.removeAll(r1);
        this.mProvidedMenuItems = r02;
    }

    private void postShowOverflowMenu() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    private boolean shouldCollapse() {
        if (this.mCollapsible == true) goto L5;
        return false;
    L5:
        int r0 = getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L15;
        View r3 = getChildAt(r2);
        if (shouldLayout(r3) == false) goto L14;
        if (r3.getMeasuredWidth() <= 0) goto L14;
        if (r3.getMeasuredHeight() <= 0) goto L14;
        return false;
    L14:
        r2 = r2 + 1;
        goto L6
    L15:
        return true;
    }

    private boolean shouldLayout(View r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.getParent() == this) goto L6;
        return false;
    L6:
        if (r2.getVisibility() == 8) goto L12;
        return true;
    L12:
        return false;
    }

    public void addChildrenForExpandedActionView() {
        int r0 = this.mHiddenViews.size();
    L3:
        r0 = r0 - 1;
        ArrayList<View> r1 = this.mHiddenViews;
        if (r0 < 0) goto L6;
        addView(r1.get(r0));
        goto L3
    L6:
        r1.clear();
    }

    @Override // androidx.core.view.MenuHost
    public void addMenuProvider(MenuProvider r1) {
        this.mMenuHostHelper.addMenuProvider(r1);
    }

    public void addMenuProvider(MenuProvider r1, LifecycleOwner r2) {
        this.mMenuHostHelper.addMenuProvider(r1, r2);
    }

    @SuppressLint({"LambdaLast"})
    public void addMenuProvider(MenuProvider r1, LifecycleOwner r2, Lifecycle.State r3) {
        this.mMenuHostHelper.addMenuProvider(r1, r2, r3);
    }

    public boolean canShowOverflowMenu() {
        if (getVisibility() != 0) goto L10;
        ActionMenuView r1 = this.mMenuView;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isOverflowReserved() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        if (super.checkLayoutParams(r1) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof LayoutParams) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void collapseActionView() {
        ExpandedActionViewMenuPresenter r0 = this.mExpandedMenuPresenter;
        if (r0 != null) goto L5;
        MenuItemImpl r02 = null;
    L6:
        if (r02 == null) goto L9;
        r02.collapseActionView();
        return;
    L9:
        return;
    L5:
        r02 = r0.mCurrentExpandedItem;
        goto L6
    }

    public void dismissPopupMenus() {
        ActionMenuView r0 = this.mMenuView;
        if (r0 == null) goto L6;
        r0.dismissPopupMenus();
        return;
    }

    public void ensureCollapseButtonView() {
        if (this.mCollapseButtonView != null) goto L6;
        AppCompatImageButton r0 = new AppCompatImageButton(getContext(), null, R$attr.toolbarNavigationButtonStyle);
        this.mCollapseButtonView = r0;
        r0.setImageDrawable(this.mCollapseIcon);
        this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
        LayoutParams r02 = generateDefaultLayoutParams();
        r02.gravity = (this.mButtonGravity & 112) | PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
        r02.mViewType = 2;
        this.mCollapseButtonView.setLayoutParams(r02);
        this.mCollapseButtonView.setOnClickListener(new AnonymousClass4(this));
        return;
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        if ((r1 instanceof LayoutParams) == false) goto L7;
        return new LayoutParams((LayoutParams) r1);
    L7:
        if ((r1 instanceof ActionBar.LayoutParams) == false) goto L11;
        return new LayoutParams((ActionBar.LayoutParams) r1);
    L11:
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L15;
        return new LayoutParams((ViewGroup.MarginLayoutParams) r1);
    L15:
        return new LayoutParams(r1);
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton r0 = this.mCollapseButtonView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getContentDescription();
    }

    public Drawable getCollapseIcon() {
        ImageButton r0 = this.mCollapseButtonView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getDrawable();
    }

    public int getContentInsetEnd() {
        RtlSpacingHelper r0 = this.mContentInsets;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getEnd();
    }

    public int getContentInsetEndWithActions() {
        int r0 = this.mContentInsetEndWithActions;
        if (r0 == Integer.MIN_VALUE) goto L6;
        return r0;
    L6:
        return getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        RtlSpacingHelper r0 = this.mContentInsets;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getLeft();
    }

    public int getContentInsetRight() {
        RtlSpacingHelper r0 = this.mContentInsets;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getRight();
    }

    public int getContentInsetStart() {
        RtlSpacingHelper r0 = this.mContentInsets;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getStart();
    }

    public int getContentInsetStartWithNavigation() {
        int r0 = this.mContentInsetStartWithNavigation;
        if (r0 == Integer.MIN_VALUE) goto L6;
        return r0;
    L6:
        return getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        ActionMenuView r0 = this.mMenuView;
        if (r0 == null) goto L11;
        MenuBuilder r02 = r0.peekMenu();
        if (r02 == null) goto L11;
        if (r02.hasVisibleItems() == false) goto L11;
        return Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
    L11:
        return getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        if (getLayoutDirection() != 1) goto L7;
        return getCurrentContentInsetEnd();
    L7:
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (getLayoutDirection() != 1) goto L7;
        return getCurrentContentInsetStart();
    L7:
        return getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() == null) goto L7;
        return Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0));
    L7:
        return getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView r0 = this.mLogoView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getDrawable();
    }

    public CharSequence getLogoDescription() {
        ImageView r0 = this.mLogoView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getContentDescription();
    }

    public Menu getMenu() {
        ensureMenu();
        return this.mMenuView.getMenu();
    }

    public View getNavButtonView() {
        return this.mNavButtonView;
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton r0 = this.mNavButtonView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getContentDescription();
    }

    public Drawable getNavigationIcon() {
        ImageButton r0 = this.mNavButtonView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getDrawable();
    }

    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    public Drawable getOverflowIcon() {
        ensureMenu();
        return this.mMenuView.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.mPopupContext;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public final TextView getSubtitleTextView() {
        return this.mSubtitleTextView;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    public final TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    public DecorToolbar getWrapper() {
        if (this.mWrapper != null) goto L6;
        this.mWrapper = new ToolbarWidgetWrapper(this, true);
    L6:
        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        ExpandedActionViewMenuPresenter r0 = this.mExpandedMenuPresenter;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.mCurrentExpandedItem == null) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hideOverflowMenu() {
        ActionMenuView r0 = this.mMenuView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.hideOverflowMenu() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void inflateMenu(int r2) {
        getMenuInflater().inflate(r2, getMenu());
    }

    public void invalidateMenu() {
        ArrayList<MenuItem> r0 = this.mProvidedMenuItems;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        MenuItem r3 = r0.get(r2);
        r2 = r2 + 1;
        getMenu().removeItem(r3.getItemId());
        goto L3
    L5:
        onCreateMenu();
    }

    public boolean isBackInvokedCallbackEnabled() {
        return this.mBackInvokedCallbackEnabled;
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuView r0 = this.mMenuView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isOverflowMenuShowPending() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuView r0 = this.mMenuView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isOverflowMenuShowing() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isTitleTruncated() {
        TextView r4 = this.mTitleTextView;
        if (r4 != null) goto L5;
        return false;
    L5:
        Layout r42 = r4.getLayout();
        if (r42 != null) goto L8;
        return false;
    L8:
        int r1 = r42.getLineCount();
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L15;
        if (r42.getEllipsisCount(r2) > 0) goto L12;
        r2 = r2 + 1;
        goto L9
    L12:
        return true;
    L15:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateBackInvokedCallbackState();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
        updateBackInvokedCallbackState();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent r6) {
        int r0 = r6.getActionMasked();
        if (r0 != 9) goto L6;
        this.mEatingHover = false;
    L6:
        if (this.mEatingHover == true) goto L12;
        boolean r62 = super.onHoverEvent(r6);
        if (r0 != 9) goto L12;
        if (r62 == true) goto L12;
        this.mEatingHover = true;
    L12:
        if (r0 != 10) goto L14;
    L15:
        this.mEatingHover = false;
    L16:
        return true;
    L14:
        if (r0 != 3) goto L16;
        goto L15
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        if (getLayoutDirection() != 1) goto L5;
        boolean r1 = true;
    L6:
        int r4 = getWidth();
        int r5 = getHeight();
        int r6 = getPaddingLeft();
        int r7 = getPaddingRight();
        int r8 = getPaddingTop();
        int r9 = getPaddingBottom();
        int r10 = r4 - r7;
        int[] r11 = this.mTempMargins;
        r11[1] = 0;
        r11[0] = 0;
        int r12 = ViewCompat.getMinimumHeight(this);
        if (r12 < 0) goto L9;
        int r122 = Math.min(r12, r24 - r22);
    L11:
        if (shouldLayout(this.mNavButtonView) == false) goto L17;
        View r13 = this.mNavButtonView;
        if (r1 == false) goto L15;
        int r14 = layoutChildRight(r13, r10, r11, r122);
        int r132 = r6;
    L19:
        if (shouldLayout(this.mCollapseButtonView) == false) goto L25;
        View r15 = this.mCollapseButtonView;
        if (r1 == false) goto L23;
        r14 = layoutChildRight(r15, r14, r11, r122);
        goto L25
    L23:
        r132 = layoutChildLeft(r15, r132, r11, r122);
    L25:
        if (shouldLayout(this.mMenuView) == false) goto L30;
        View r152 = this.mMenuView;
        if (r1 == false) goto L29;
        r132 = layoutChildLeft(r152, r132, r11, r122);
        goto L30
    L29:
        r14 = layoutChildRight(r152, r14, r11, r122);
    L30:
        int r153 = getCurrentContentInsetLeft();
        int r16 = getCurrentContentInsetRight();
        r11[0] = Math.max(0, r153 - r132);
        r11[1] = Math.max(0, r16 - (r10 - r14));
        int r3 = Math.max(r132, r153);
        int r102 = Math.min(r14, r10 - r16);
        if (shouldLayout(this.mExpandedActionView) == false) goto L37;
        View r133 = this.mExpandedActionView;
        if (r1 == false) goto L35;
        r102 = layoutChildRight(r133, r102, r11, r122);
        goto L37
    L35:
        r3 = layoutChildLeft(r133, r3, r11, r122);
    L37:
        if (shouldLayout(this.mLogoView) == false) goto L42;
        View r134 = this.mLogoView;
        if (r1 == false) goto L41;
        r102 = layoutChildRight(r134, r102, r11, r122);
        goto L42
    L41:
        r3 = layoutChildLeft(r134, r3, r11, r122);
    L42:
        boolean r135 = shouldLayout(this.mTitleTextView);
        boolean r142 = shouldLayout(this.mSubtitleTextView);
        if (r135 == false) goto L45;
        LayoutParams r154 = (LayoutParams) this.mTitleTextView.getLayoutParams();
        boolean r232 = r1;
        int r17 = ((ViewGroup.MarginLayoutParams) r154).bottomMargin + (((ViewGroup.MarginLayoutParams) r154).topMargin + this.mTitleTextView.getMeasuredHeight());
    L46:
        if (r142 == false) goto L49;
        LayoutParams r2 = (LayoutParams) this.mSubtitleTextView.getLayoutParams();
        r17 = r17 + ((((ViewGroup.MarginLayoutParams) r2).topMargin + this.mSubtitleTextView.getMeasuredHeight()) + ((ViewGroup.MarginLayoutParams) r2).bottomMargin);
    L49:
        if (r135 == true) goto L53;
        if (r142 == true) goto L53;
    L52:
        int r18 = 0;
    L108:
        addCustomViewsWithGravity(this.mTempViews, 3);
        int r25 = this.mTempViews.size();
        int r52 = r3;
        int r32 = r18;
    L109:
        ArrayList<View> r82 = this.mTempViews;
        if (r32 >= r25) goto L112;
        r52 = layoutChildLeft(r82.get(r32), r52, r11, r122);
        r32 = r32 + 1;
        goto L109
    L112:
        addCustomViewsWithGravity(r82, 5);
        int r26 = this.mTempViews.size();
        int r33 = r18;
    L113:
        ArrayList<View> r83 = this.mTempViews;
        if (r33 >= r26) goto L116;
        r102 = layoutChildRight(r83.get(r33), r102, r11, r122);
        r33 = r33 + 1;
        goto L113
    L116:
        addCustomViewsWithGravity(r83, 1);
        int r27 = getViewListMeasuredWidth(this.mTempViews, r11);
        int r62 = (r6 + (((r4 - r6) - r7) / 2)) - (r27 / 2);
        int r28 = r27 + r62;
        if (r62 < r52) goto L122;
        if (r28 <= r102) goto L121;
        r52 = r62 - (r28 - r102);
        goto L122
    L121:
        r52 = r62;
    L122:
        int r29 = this.mTempViews.size();
    L123:
        ArrayList<View> r34 = this.mTempViews;
        if (r18 >= r29) goto L126;
        r52 = layoutChildLeft(r34.get(r18), r52, r11, r122);
        r18 = r18 + 1;
        goto L123
    L126:
        r34.clear();
        return;
    L53:
        if (r135 == false) goto L55;
        TextView r210 = this.mTitleTextView;
    L56:
        if (r142 == false) goto L58;
        TextView r155 = this.mSubtitleTextView;
    L59:
        LayoutParams r211 = (LayoutParams) r210.getLayoutParams();
        LayoutParams r156 = (LayoutParams) r155.getLayoutParams();
        int r222 = r17;
        if (r135 == true) goto L62;
    L63:
        if (r142 == true) goto L65;
    L67:
        boolean r242 = false;
    L68:
        int r19 = this.mGravity & 112;
        int r162 = r3;
        if (r19 != 48) goto L71;
        int r84 = (getPaddingTop() + ((ViewGroup.MarginLayoutParams) r211).topMargin) + this.mTitleMarginTop;
    L81:
        if (r232 == false) goto L95;
        if (r242 == false) goto L84;
        int r110 = this.mTitleMarginStart;
    L85:
        int r111 = r110 - r11[1];
        r102 = r102 - Math.max(0, r111);
        r11[1] = Math.max(0, -r111);
        if (r135 == false) goto L88;
        LayoutParams r112 = (LayoutParams) this.mTitleTextView.getLayoutParams();
        int r212 = r102 - this.mTitleTextView.getMeasuredWidth();
        int r35 = this.mTitleTextView.getMeasuredHeight() + r84;
        this.mTitleTextView.layout(r212, r84, r102, r35);
        int r213 = r212 - this.mTitleMarginEnd;
        r84 = r35 + ((ViewGroup.MarginLayoutParams) r112).bottomMargin;
    L89:
        if (r142 == false) goto L91;
        int r85 = r84 + ((ViewGroup.MarginLayoutParams) ((LayoutParams) this.mSubtitleTextView.getLayoutParams())).topMargin;
        this.mSubtitleTextView.layout(r102 - this.mSubtitleTextView.getMeasuredWidth(), r85, r102, this.mSubtitleTextView.getMeasuredHeight() + r85);
        int r113 = r102 - this.mTitleMarginEnd;
    L92:
        if (r242 == false) goto L94;
        r102 = Math.min(r213, r113);
    L94:
        r3 = r162;
        goto L52
    L91:
        r113 = r102;
        goto L92
    L88:
        r213 = r102;
        goto L89
    L84:
        r110 = 0;
        goto L85
    L95:
        if (r242 == false) goto L98;
        int r214 = this.mTitleMarginStart;
    L97:
        r18 = 0;
        int r215 = r214 - r11[0];
        r3 = r162 + Math.max(0, r215);
        r11[0] = Math.max(0, -r215);
        if (r135 == false) goto L102;
        LayoutParams r216 = (LayoutParams) this.mTitleTextView.getLayoutParams();
        int r53 = this.mTitleTextView.getMeasuredWidth() + r3;
        int r92 = this.mTitleTextView.getMeasuredHeight() + r84;
        this.mTitleTextView.layout(r3, r84, r53, r92);
        int r54 = r53 + this.mTitleMarginEnd;
        r84 = r92 + ((ViewGroup.MarginLayoutParams) r216).bottomMargin;
    L103:
        if (r142 == false) goto L105;
        int r86 = r84 + ((ViewGroup.MarginLayoutParams) ((LayoutParams) this.mSubtitleTextView.getLayoutParams())).topMargin;
        int r217 = this.mSubtitleTextView.getMeasuredWidth() + r3;
        this.mSubtitleTextView.layout(r3, r86, r217, this.mSubtitleTextView.getMeasuredHeight() + r86);
        int r218 = r217 + this.mTitleMarginEnd;
    L106:
        if (r242 == false) goto L108;
        r3 = Math.max(r54, r218);
        goto L108
    L105:
        r218 = r3;
        goto L106
    L102:
        r54 = r3;
        goto L103
    L98:
        r214 = 0;
        goto L97
    L71:
        if (r19 == 80) goto L79;
        int r114 = (((r5 - r8) - r9) - r222) / 2;
        int r36 = ((ViewGroup.MarginLayoutParams) r211).topMargin;
        int r37 = this.mTitleMarginTop;
        if (r114 >= (r36 + r37)) goto L75;
        r114 = r36 + r37;
    L78:
        r84 = r8 + r114;
        goto L81
    L75:
        int r55 = (((r5 - r9) - r222) - r114) - r8;
        int r219 = ((ViewGroup.MarginLayoutParams) r211).bottomMargin;
        int r38 = this.mTitleMarginBottom;
        if (r55 >= (r219 + r38)) goto L78;
        r114 = Math.max(0, r114 - ((((ViewGroup.MarginLayoutParams) r156).bottomMargin + r38) - r55));
        goto L78
    L79:
        r84 = (((r5 - r9) - ((ViewGroup.MarginLayoutParams) r156).bottomMargin) - this.mTitleMarginBottom) - r222;
        goto L81
    L65:
        if (this.mSubtitleTextView.getMeasuredWidth() <= 0) goto L67;
    L66:
        r242 = true;
        goto L68
    L62:
        if (this.mTitleTextView.getMeasuredWidth() > 0) goto L66;
    L58:
        r155 = this.mTitleTextView;
        goto L59
    L55:
        r210 = this.mSubtitleTextView;
        goto L56
    L45:
        r232 = r1;
        r17 = 0;
        goto L46
    L15:
        r132 = layoutChildLeft(r13, r6, r11, r122);
    L16:
        r14 = r10;
        goto L19
    L17:
        r132 = r6;
        goto L16
    L9:
        r122 = 0;
        goto L11
    L5:
        r1 = false;
        goto L6
    }

    @Override // android.view.View
    public void onMeasure(int r15, int r16) {
        int[] r7 = this.mTempMargins;
        boolean r8 = ViewUtils.isLayoutRtl(this);
        int r9 = !r8 ? 1 : 0;
        int r10 = 0;
        if (shouldLayout(this.mNavButtonView) == false) goto L5;
        measureChildConstrained(this.mNavButtonView, r15, 0, r16, 0, this.mMaxButtonHeight);
        int r1 = this.mNavButtonView.getMeasuredWidth() + getHorizontalMargins(this.mNavButtonView);
        int r11 = Math.max(0, this.mNavButtonView.getMeasuredHeight() + getVerticalMargins(this.mNavButtonView));
        int r12 = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
    L7:
        if (shouldLayout(this.mCollapseButtonView) == false) goto L9;
        measureChildConstrained(this.mCollapseButtonView, r15, 0, r16, 0, this.mMaxButtonHeight);
        r1 = this.mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(this.mCollapseButtonView);
        r11 = Math.max(r11, this.mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(this.mCollapseButtonView));
        r12 = View.combineMeasuredStates(r12, this.mCollapseButtonView.getMeasuredState());
    L9:
        int r2 = getCurrentContentInsetStart();
        int r3 = Math.max(r2, r1);
        r7[r8 ? 1 : 0] = Math.max(0, r2 - r1);
        if (shouldLayout(this.mMenuView) == false) goto L12;
        measureChildConstrained(this.mMenuView, r15, r3, r16, 0, this.mMaxButtonHeight);
        int r13 = this.mMenuView.getMeasuredWidth() + getHorizontalMargins(this.mMenuView);
        r11 = Math.max(r11, this.mMenuView.getMeasuredHeight() + getVerticalMargins(this.mMenuView));
        r12 = View.combineMeasuredStates(r12, this.mMenuView.getMeasuredState());
    L13:
        int r22 = getCurrentContentInsetEnd();
        int r32 = r3 + Math.max(r22, r13);
        r7[r9] = Math.max(0, r22 - r13);
        if (shouldLayout(this.mExpandedActionView) == false) goto L16;
        int[] r6 = r7;
        r32 = r32 + measureChildCollapseMargins(this.mExpandedActionView, r15, r32, r16, 0, r6);
        r11 = Math.max(r11, this.mExpandedActionView.getMeasuredHeight() + getVerticalMargins(this.mExpandedActionView));
        r12 = View.combineMeasuredStates(r12, this.mExpandedActionView.getMeasuredState());
    L18:
        if (shouldLayout(this.mLogoView) == false) goto L20;
        r32 = r32 + measureChildCollapseMargins(this.mLogoView, r15, r32, r16, 0, r6);
        r11 = Math.max(r11, this.mLogoView.getMeasuredHeight() + getVerticalMargins(this.mLogoView));
        r12 = View.combineMeasuredStates(r12, this.mLogoView.getMeasuredState());
    L20:
        int r72 = getChildCount();
        int r82 = 0;
    L21:
        if (r82 >= r72) goto L30;
        View r14 = getChildAt(r82);
        if (((LayoutParams) r14.getLayoutParams()).mViewType != 0) goto L28;
        if (shouldLayout(r14) == false) goto L28;
        r32 = r32 + measureChildCollapseMargins(r14, r15, r32, r16, 0, r6);
        int r23 = Math.max(r11, r14.getMeasuredHeight() + getVerticalMargins(r14));
        r12 = View.combineMeasuredStates(r12, r14.getMeasuredState());
        r11 = r23;
    L29:
        r82 = r82 + 1;
    L28:
        r32 = r32;
        goto L29
    L30:
        int r92 = r32;
        int r5 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int r73 = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (shouldLayout(this.mTitleTextView) == false) goto L33;
        measureChildCollapseMargins(this.mTitleTextView, r15, r92 + r73, r16, r5, r6);
        int r17 = this.mTitleTextView.getMeasuredWidth() + getHorizontalMargins(this.mTitleTextView);
        int r24 = this.mTitleTextView.getMeasuredHeight() + getVerticalMargins(this.mTitleTextView);
        int r83 = r17;
        int r132 = View.combineMeasuredStates(r12, this.mTitleTextView.getMeasuredState());
        int r122 = r24;
    L35:
        if (shouldLayout(this.mSubtitleTextView) == false) goto L37;
        r83 = Math.max(r83, measureChildCollapseMargins(this.mSubtitleTextView, r15, r92 + r73, r16, r5 + r122, r6));
        r122 = r122 + (this.mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(this.mSubtitleTextView));
        r132 = View.combineMeasuredStates(r132, this.mSubtitleTextView.getMeasuredState());
    L37:
        int r18 = Math.max(r11, r122);
        int r33 = (r92 + r83) + (getPaddingLeft() + getPaddingRight());
        int r19 = r18 + (getPaddingTop() + getPaddingBottom());
        int r25 = View.resolveSizeAndState(Math.max(r33, getSuggestedMinimumWidth()), r15, (-16777216) & r132);
        int r110 = View.resolveSizeAndState(Math.max(r19, getSuggestedMinimumHeight()), r16, r132 << 16);
        if (shouldCollapse() == true) goto L41;
        r10 = r110;
    L41:
        setMeasuredDimension(r25, r10);
        return;
    L33:
        r83 = 0;
        r132 = r12;
        r122 = 0;
        goto L35
    L16:
        r6 = r7;
        goto L18
    L12:
        r13 = 0;
        goto L13
    L5:
        r1 = 0;
        r11 = 0;
        r12 = 0;
        goto L7
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r4) {
        if ((r4 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r4);
        return;
    L6:
        SavedState r42 = (SavedState) r4;
        super.onRestoreInstanceState(r42.getSuperState());
        ActionMenuView r0 = this.mMenuView;
        if (r0 == null) goto L9;
        MenuBuilder r02 = r0.peekMenu();
    L10:
        int r1 = r42.expandedMenuItemId;
        if (r1 == 0) goto L19;
        if (this.mExpandedMenuPresenter == null) goto L19;
        if (r02 == null) goto L19;
        MenuItem r03 = r02.findItem(r1);
        if (r03 == null) goto L19;
        r03.expandActionView();
    L19:
        if (r42.isOverflowOpen == false) goto L22;
        postShowOverflowMenu();
        return;
    L22:
        return;
    L9:
        r02 = null;
        goto L10
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int r2) {
        super.onRtlPropertiesChanged(r2);
        ensureContentInsets();
        RtlSpacingHelper r1 = this.mContentInsets;
        boolean r0 = true;
        if (r2 == 1) goto L6;
        r0 = false;
    L6:
        r1.setDirection(r0);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r0 = new SavedState(super.onSaveInstanceState());
        ExpandedActionViewMenuPresenter r1 = this.mExpandedMenuPresenter;
        if (r1 == null) goto L7;
        MenuItemImpl r12 = r1.mCurrentExpandedItem;
        if (r12 == null) goto L7;
        r0.expandedMenuItemId = r12.getItemId();
    L7:
        r0.isOverflowOpen = isOverflowMenuShowing();
        return r0;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r5) {
        int r0 = r5.getActionMasked();
        if (r0 != 0) goto L6;
        this.mEatingTouch = false;
    L6:
        if (this.mEatingTouch == true) goto L11;
        boolean r52 = super.onTouchEvent(r5);
        if (r0 != 0) goto L11;
        if (r52 == true) goto L11;
        this.mEatingTouch = true;
    L11:
        if (r0 != 1) goto L13;
    L14:
        this.mEatingTouch = false;
    L15:
        return true;
    L13:
        if (r0 != 3) goto L15;
        goto L14
    }

    public void removeChildrenForExpandedActionView() {
        int r0 = getChildCount() - 1;
    L3:
        if (r0 < 0) goto L10;
        View r1 = getChildAt(r0);
        if (((LayoutParams) r1.getLayoutParams()).mViewType == 2) goto L9;
        if (r1 == this.mMenuView) goto L9;
        removeViewAt(r0);
        this.mHiddenViews.add(r1);
    L9:
        r0 = r0 - 1;
        goto L3
    }

    @Override // androidx.core.view.MenuHost
    public void removeMenuProvider(MenuProvider r1) {
        this.mMenuHostHelper.removeMenuProvider(r1);
    }

    public void setBackInvokedCallbackEnabled(boolean r2) {
        if (this.mBackInvokedCallbackEnabled == r2) goto L6;
        this.mBackInvokedCallbackEnabled = r2;
        updateBackInvokedCallbackState();
        return;
    }

    public void setCollapseContentDescription(int r2) {
        if (r2 == 0) goto L4;
        CharSequence r22 = getContext().getText(r2);
    L5:
        setCollapseContentDescription(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setCollapseContentDescription(CharSequence r2) {
        if (TextUtils.isEmpty(r2) == true) goto L5;
        ensureCollapseButtonView();
    L5:
        ImageButton r1 = this.mCollapseButtonView;
        if (r1 == null) goto L9;
        r1.setContentDescription(r2);
        return;
    }

    public void setCollapseIcon(int r2) {
        setCollapseIcon(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setCollapseIcon(Drawable r1) {
        if (r1 == null) goto L5;
        ensureCollapseButtonView();
        this.mCollapseButtonView.setImageDrawable(r1);
        return;
    L5:
        ImageButton r12 = this.mCollapseButtonView;
        if (r12 == null) goto L9;
        r12.setImageDrawable(this.mCollapseIcon);
        return;
    }

    public void setCollapsible(boolean r1) {
        this.mCollapsible = r1;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int r2) {
        if (r2 >= 0) goto L5;
        r2 = Integer.MIN_VALUE;
    L5:
        if (r2 == this.mContentInsetEndWithActions) goto L10;
        this.mContentInsetEndWithActions = r2;
        if (getNavigationIcon() == null) goto L11;
        requestLayout();
        return;
    L11:
        return;
    }

    public void setContentInsetStartWithNavigation(int r2) {
        if (r2 >= 0) goto L5;
        r2 = Integer.MIN_VALUE;
    L5:
        if (r2 == this.mContentInsetStartWithNavigation) goto L10;
        this.mContentInsetStartWithNavigation = r2;
        if (getNavigationIcon() == null) goto L11;
        requestLayout();
        return;
    L11:
        return;
    }

    public void setContentInsetsAbsolute(int r1, int r2) {
        ensureContentInsets();
        this.mContentInsets.setAbsolute(r1, r2);
    }

    public void setContentInsetsRelative(int r1, int r2) {
        ensureContentInsets();
        this.mContentInsets.setRelative(r1, r2);
    }

    public void setLogo(int r2) {
        setLogo(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setLogo(Drawable r3) {
        if (r3 == null) goto L6;
        ensureLogoView();
        if (isChildOrHidden(this.mLogoView) == true) goto L11;
        addSystemView(this.mLogoView, true);
    L11:
        ImageView r2 = this.mLogoView;
        if (r2 == null) goto L15;
        r2.setImageDrawable(r3);
        return;
    L15:
        return;
    L6:
        ImageView r0 = this.mLogoView;
        if (r0 == null) goto L11;
        if (isChildOrHidden(r0) == false) goto L11;
        removeView(this.mLogoView);
        this.mHiddenViews.remove(this.mLogoView);
        goto L11
    }

    public void setLogoDescription(int r2) {
        setLogoDescription(getContext().getText(r2));
    }

    public void setLogoDescription(CharSequence r2) {
        if (TextUtils.isEmpty(r2) == true) goto L5;
        ensureLogoView();
    L5:
        ImageView r1 = this.mLogoView;
        if (r1 == null) goto L9;
        r1.setContentDescription(r2);
        return;
    }

    public void setMenu(MenuBuilder r4, ActionMenuPresenter r5) {
        if (r4 == null) goto L4;
    L6:
        ensureMenuView();
        MenuBuilder r0 = this.mMenuView.peekMenu();
        if (r0 != r4) goto L9;
        return;
    L9:
        if (r0 == null) goto L12;
        r0.removeMenuPresenter(this.mOuterActionMenuPresenter);
        r0.removeMenuPresenter(this.mExpandedMenuPresenter);
    L12:
        if (this.mExpandedMenuPresenter != null) goto L14;
        this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(this);
    L14:
        r5.setExpandedActionViewsExclusive(true);
        Context r1 = this.mPopupContext;
        if (r4 == null) goto L17;
        r4.addMenuPresenter(r5, r1);
        r4.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
    L18:
        this.mMenuView.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setPresenter(r5);
        this.mOuterActionMenuPresenter = r5;
        updateBackInvokedCallbackState();
        return;
    L17:
        r5.initForMenu(r1, null);
        this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
        r5.updateMenuView(true);
        this.mExpandedMenuPresenter.updateMenuView(true);
        goto L18
    L4:
        if (this.mMenuView != null) goto L6;
    }

    public void setMenuCallbacks(MenuPresenter.Callback r1, MenuBuilder.Callback r2) {
        this.mActionMenuPresenterCallback = r1;
        this.mMenuBuilderCallback = r2;
        ActionMenuView r0 = this.mMenuView;
        if (r0 == null) goto L6;
        r0.setMenuCallbacks(r1, r2);
        return;
    }

    public void setNavigationContentDescription(int r2) {
        if (r2 == 0) goto L4;
        CharSequence r22 = getContext().getText(r2);
    L5:
        setNavigationContentDescription(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setNavigationContentDescription(CharSequence r2) {
        if (TextUtils.isEmpty(r2) == true) goto L5;
        ensureNavButtonView();
    L5:
        ImageButton r0 = this.mNavButtonView;
        if (r0 == null) goto L9;
        r0.setContentDescription(r2);
        TooltipCompat.setTooltipText(this.mNavButtonView, r2);
        return;
    }

    public void setNavigationIcon(int r2) {
        setNavigationIcon(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setNavigationIcon(Drawable r3) {
        if (r3 == null) goto L6;
        ensureNavButtonView();
        if (isChildOrHidden(this.mNavButtonView) == true) goto L11;
        addSystemView(this.mNavButtonView, true);
    L11:
        ImageButton r2 = this.mNavButtonView;
        if (r2 == null) goto L15;
        r2.setImageDrawable(r3);
        return;
    L15:
        return;
    L6:
        ImageButton r0 = this.mNavButtonView;
        if (r0 == null) goto L11;
        if (isChildOrHidden(r0) == false) goto L11;
        removeView(this.mNavButtonView);
        this.mHiddenViews.remove(this.mNavButtonView);
        goto L11
    }

    public void setNavigationOnClickListener(View.OnClickListener r1) {
        ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(r1);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener r1) {
        this.mOnMenuItemClickListener = r1;
    }

    public void setOverflowIcon(Drawable r1) {
        ensureMenu();
        this.mMenuView.setOverflowIcon(r1);
    }

    public void setPopupTheme(int r3) {
        if (this.mPopupTheme == r3) goto L10;
        this.mPopupTheme = r3;
        if (r3 != 0) goto L8;
        this.mPopupContext = getContext();
        return;
    L8:
        this.mPopupContext = new ContextThemeWrapper(getContext(), r3);
        return;
    }

    public void setSubtitle(int r2) {
        setSubtitle(getContext().getText(r2));
    }

    public void setSubtitle(CharSequence r4) {
        boolean r0 = TextUtils.isEmpty(r4);
        TextView r1 = this.mSubtitleTextView;
        if (r0 == true) goto L14;
        if (r1 != null) goto L12;
        Context r02 = getContext();
        AppCompatTextView r12 = new AppCompatTextView(r02);
        this.mSubtitleTextView = r12;
        r12.setSingleLine();
        this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        int r13 = this.mSubtitleTextAppearance;
        if (r13 == 0) goto L8;
        this.mSubtitleTextView.setTextAppearance(r02, r13);
    L8:
        ColorStateList r03 = this.mSubtitleTextColor;
        if (r03 == null) goto L12;
        this.mSubtitleTextView.setTextColor(r03);
    L12:
        if (isChildOrHidden(this.mSubtitleTextView) == true) goto L18;
        addSystemView(this.mSubtitleTextView, true);
    L18:
        TextView r04 = this.mSubtitleTextView;
        if (r04 == null) goto L21;
        r04.setText(r4);
    L21:
        this.mSubtitleText = r4;
        return;
    L14:
        if (r1 == null) goto L18;
        if (isChildOrHidden(r1) == false) goto L18;
        removeView(this.mSubtitleTextView);
        this.mHiddenViews.remove(this.mSubtitleTextView);
        goto L18
    }

    public void setSubtitleTextAppearance(Context r1, int r2) {
        this.mSubtitleTextAppearance = r2;
        TextView r0 = this.mSubtitleTextView;
        if (r0 == null) goto L6;
        r0.setTextAppearance(r1, r2);
        return;
    }

    public void setSubtitleTextColor(int r1) {
        setSubtitleTextColor(ColorStateList.valueOf(r1));
    }

    public void setSubtitleTextColor(ColorStateList r1) {
        this.mSubtitleTextColor = r1;
        TextView r0 = this.mSubtitleTextView;
        if (r0 == null) goto L6;
        r0.setTextColor(r1);
        return;
    }

    public void setTitle(int r2) {
        setTitle(getContext().getText(r2));
    }

    public void setTitle(CharSequence r4) {
        boolean r0 = TextUtils.isEmpty(r4);
        TextView r1 = this.mTitleTextView;
        if (r0 == true) goto L14;
        if (r1 != null) goto L12;
        Context r02 = getContext();
        AppCompatTextView r12 = new AppCompatTextView(r02);
        this.mTitleTextView = r12;
        r12.setSingleLine();
        this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        int r13 = this.mTitleTextAppearance;
        if (r13 == 0) goto L8;
        this.mTitleTextView.setTextAppearance(r02, r13);
    L8:
        ColorStateList r03 = this.mTitleTextColor;
        if (r03 == null) goto L12;
        this.mTitleTextView.setTextColor(r03);
    L12:
        if (isChildOrHidden(this.mTitleTextView) == true) goto L18;
        addSystemView(this.mTitleTextView, true);
    L18:
        TextView r04 = this.mTitleTextView;
        if (r04 == null) goto L21;
        r04.setText(r4);
    L21:
        this.mTitleText = r4;
        return;
    L14:
        if (r1 == null) goto L18;
        if (isChildOrHidden(r1) == false) goto L18;
        removeView(this.mTitleTextView);
        this.mHiddenViews.remove(this.mTitleTextView);
        goto L18
    }

    public void setTitleMargin(int r1, int r2, int r3, int r4) {
        this.mTitleMarginStart = r1;
        this.mTitleMarginTop = r2;
        this.mTitleMarginEnd = r3;
        this.mTitleMarginBottom = r4;
        requestLayout();
    }

    public void setTitleMarginBottom(int r1) {
        this.mTitleMarginBottom = r1;
        requestLayout();
    }

    public void setTitleMarginEnd(int r1) {
        this.mTitleMarginEnd = r1;
        requestLayout();
    }

    public void setTitleMarginStart(int r1) {
        this.mTitleMarginStart = r1;
        requestLayout();
    }

    public void setTitleMarginTop(int r1) {
        this.mTitleMarginTop = r1;
        requestLayout();
    }

    public void setTitleTextAppearance(Context r1, int r2) {
        this.mTitleTextAppearance = r2;
        TextView r0 = this.mTitleTextView;
        if (r0 == null) goto L6;
        r0.setTextAppearance(r1, r2);
        return;
    }

    public void setTitleTextColor(int r1) {
        setTitleTextColor(ColorStateList.valueOf(r1));
    }

    public void setTitleTextColor(ColorStateList r1) {
        this.mTitleTextColor = r1;
        TextView r0 = this.mTitleTextView;
        if (r0 == null) goto L6;
        r0.setTextColor(r1);
        return;
    }

    public boolean showOverflowMenu() {
        ActionMenuView r0 = this.mMenuView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.showOverflowMenu() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void updateBackInvokedCallbackState() {
        if (Build.VERSION.SDK_INT < 33) goto L26;
        OnBackInvokedDispatcher r0 = Api33Impl.findOnBackInvokedDispatcher(this);
        if (hasExpandedActionView() == false) goto L12;
        if (r0 == null) goto L12;
        if (isAttachedToWindow() == false) goto L12;
        if (this.mBackInvokedCallbackEnabled == false) goto L12;
        boolean r1 = true;
    L13:
        if (r1 == true) goto L15;
    L21:
        if (r1 == true) goto L27;
        OnBackInvokedDispatcher r02 = this.mBackInvokedDispatcher;
        if (r02 == null) goto L28;
        Api33Impl.tryUnregisterOnBackInvokedCallback(r02, this.mBackInvokedCallback);
        this.mBackInvokedDispatcher = null;
        return;
    L28:
        return;
    L27:
        return;
    L15:
        if (this.mBackInvokedDispatcher != null) goto L21;
        if (this.mBackInvokedCallback != null) goto L19;
        this.mBackInvokedCallback = Api33Impl.newOnBackInvokedCallback(new Toolbar$$ExternalSyntheticLambda0(this));
    L19:
        Api33Impl.tryRegisterOnBackInvokedCallback(r0, this.mBackInvokedCallback);
        this.mBackInvokedDispatcher = r0;
        return;
    L12:
        r1 = false;
        goto L13
    }
}
