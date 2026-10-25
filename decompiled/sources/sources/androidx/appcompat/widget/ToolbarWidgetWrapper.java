package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$id;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;

/* loaded from: classes3.dex */
public class ToolbarWidgetWrapper implements DecorToolbar {
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private CharSequence mSubtitle;
    private View mTabView;
    CharSequence mTitle;
    private boolean mTitleSet;
    Toolbar mToolbar;
    Window.Callback mWindowCallback;

    /* renamed from: androidx.appcompat.widget.ToolbarWidgetWrapper$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final ActionMenuItem mNavItem;
        final /* synthetic */ ToolbarWidgetWrapper this$0;

        public AnonymousClass1(ToolbarWidgetWrapper r8) {
            this.this$0 = r8;
            this.mNavItem = new ActionMenuItem(r8.mToolbar.getContext(), 0, R.id.home, 0, 0, r8.mTitle);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r2) {
            ToolbarWidgetWrapper r22 = this.this$0;
            Window.Callback r0 = r22.mWindowCallback;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r22.mMenuPrepared == false) goto L9;
            r0.onMenuItemSelected(0, this.mNavItem);
            return;
        }
    }

    /* renamed from: androidx.appcompat.widget.ToolbarWidgetWrapper$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPropertyAnimatorListenerAdapter {
        private boolean mCanceled;
        final /* synthetic */ ToolbarWidgetWrapper this$0;
        final /* synthetic */ int val$visibility;

        public AnonymousClass2(ToolbarWidgetWrapper r1, int r2) {
            this.this$0 = r1;
            this.val$visibility = r2;
            this.mCanceled = false;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(View r1) {
            this.mCanceled = true;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View r1) {
            if (this.mCanceled == true) goto L6;
            this.this$0.mToolbar.setVisibility(this.val$visibility);
            return;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View r1) {
            this.this$0.mToolbar.setVisibility(0);
        }
    }

    public ToolbarWidgetWrapper(Toolbar r3, boolean r4) {
        this(r3, r4, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    public ToolbarWidgetWrapper(Toolbar r4, boolean r5, int r6, int r7) {
        this.mNavigationMode = 0;
        this.mDefaultNavigationContentDescription = 0;
        this.mToolbar = r4;
        this.mTitle = r4.getTitle();
        this.mSubtitle = r4.getSubtitle();
        if (this.mTitle == null) goto L5;
        boolean r0 = true;
    L6:
        this.mTitleSet = r0;
        this.mNavIcon = r4.getNavigationIcon();
        TintTypedArray r42 = TintTypedArray.obtainStyledAttributes(r4.getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.mDefaultNavigationIcon = r42.getDrawable(R$styleable.ActionBar_homeAsUpIndicator);
        if (r5 == false) goto L44;
        CharSequence r52 = r42.getText(R$styleable.ActionBar_title);
        if (TextUtils.isEmpty(r52) == true) goto L11;
        setTitle(r52);
    L11:
        CharSequence r53 = r42.getText(R$styleable.ActionBar_subtitle);
        if (TextUtils.isEmpty(r53) == true) goto L14;
        setSubtitle(r53);
    L14:
        Drawable r54 = r42.getDrawable(R$styleable.ActionBar_logo);
        if (r54 == null) goto L17;
        setLogo(r54);
    L17:
        Drawable r55 = r42.getDrawable(R$styleable.ActionBar_icon);
        if (r55 == null) goto L21;
        setIcon(r55);
    L21:
        if (this.mNavIcon != null) goto L25;
        Drawable r56 = this.mDefaultNavigationIcon;
        if (r56 == null) goto L25;
        setNavigationIcon(r56);
    L25:
        setDisplayOptions(r42.getInt(R$styleable.ActionBar_displayOptions, 0));
        int r57 = r42.getResourceId(R$styleable.ActionBar_customNavigationLayout, 0);
        if (r57 == 0) goto L28;
        setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(r57, this.mToolbar, false));
        setDisplayOptions(this.mDisplayOpts | 16);
    L28:
        int r58 = r42.getLayoutDimension(R$styleable.ActionBar_height, 0);
        if (r58 <= 0) goto L31;
        ViewGroup.LayoutParams r02 = this.mToolbar.getLayoutParams();
        r02.height = r58;
        this.mToolbar.setLayoutParams(r02);
    L31:
        int r59 = r42.getDimensionPixelOffset(R$styleable.ActionBar_contentInsetStart, -1);
        int r03 = r42.getDimensionPixelOffset(R$styleable.ActionBar_contentInsetEnd, -1);
        if (r59 >= 0) goto L34;
        if (r03 >= 0) goto L34;
    L35:
        int r510 = r42.getResourceId(R$styleable.ActionBar_titleTextStyle, 0);
        if (r510 == 0) goto L38;
        Toolbar r04 = this.mToolbar;
        r04.setTitleTextAppearance(r04.getContext(), r510);
    L38:
        int r511 = r42.getResourceId(R$styleable.ActionBar_subtitleTextStyle, 0);
        if (r511 == 0) goto L41;
        Toolbar r05 = this.mToolbar;
        r05.setSubtitleTextAppearance(r05.getContext(), r511);
    L41:
        int r512 = r42.getResourceId(R$styleable.ActionBar_popupTheme, 0);
        if (r512 == 0) goto L45;
        this.mToolbar.setPopupTheme(r512);
    L45:
        r42.recycle();
        setDefaultNavigationContentDescription(r6);
        this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
        this.mToolbar.setNavigationOnClickListener(new AnonymousClass1(this));
        return;
    L34:
        this.mToolbar.setContentInsetsRelative(Math.max(r59, 0), Math.max(r03, 0));
        goto L35
    L44:
        this.mDisplayOpts = detectDisplayOptions();
        goto L45
    L5:
        r0 = false;
        goto L6
    }

    private int detectDisplayOptions() {
        if (this.mToolbar.getNavigationIcon() == null) goto L6;
        this.mDefaultNavigationIcon = this.mToolbar.getNavigationIcon();
        return 15;
    L6:
        return 11;
    }

    private void setTitleInt(CharSequence r2) {
        this.mTitle = r2;
        if ((this.mDisplayOpts & 8) == 0) goto L8;
        this.mToolbar.setTitle(r2);
        if (this.mTitleSet == false) goto L9;
        ViewCompat.setAccessibilityPaneTitle(this.mToolbar.getRootView(), r2);
        return;
    L9:
        return;
    }

    private void updateHomeAccessibility() {
        if ((this.mDisplayOpts & 4) == 0) goto L10;
        boolean r0 = TextUtils.isEmpty(this.mHomeDescription);
        Toolbar r1 = this.mToolbar;
        if (r0 == false) goto L8;
        r1.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
        return;
    L8:
        r1.setNavigationContentDescription(this.mHomeDescription);
        return;
    }

    private void updateNavigationIcon() {
        int r0 = this.mDisplayOpts & 4;
        Toolbar r1 = this.mToolbar;
        if (r0 == 0) goto L10;
        Drawable r02 = this.mNavIcon;
        if (r02 != null) goto L8;
        r02 = this.mDefaultNavigationIcon;
    L8:
        r1.setNavigationIcon(r02);
        return;
    L10:
        r1.setNavigationIcon(null);
    }

    private void updateToolbarLogo() {
        int r0 = this.mDisplayOpts;
        if ((r0 & 2) != 0) goto L5;
        Drawable r02 = null;
    L12:
        this.mToolbar.setLogo(r02);
        return;
    L5:
        if ((r0 & 1) == 0) goto L10;
        r02 = this.mLogo;
        if (r02 != null) goto L12;
        r02 = this.mIcon;
        goto L12
    L10:
        r02 = this.mIcon;
        goto L12
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean canShowOverflowMenu() {
        return this.mToolbar.canShowOverflowMenu();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void dismissPopupMenus() {
        this.mToolbar.dismissPopupMenus();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public Context getContext() {
        return this.mToolbar.getContext();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public Menu getMenu() {
        return this.mToolbar.getMenu();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public ViewGroup getViewGroup() {
        return this.mToolbar;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean hideOverflowMenu() {
        return this.mToolbar.hideOverflowMenu();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void initIndeterminateProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void initProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean isOverflowMenuShowPending() {
        return this.mToolbar.isOverflowMenuShowPending();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setCollapsible(boolean r1) {
        this.mToolbar.setCollapsible(r1);
    }

    public void setCustomView(View r3) {
        View r0 = this.mCustomView;
        if (r0 != null) goto L5;
    L7:
        this.mCustomView = r3;
        if (r3 != null) goto L10;
        return;
    L10:
        if ((this.mDisplayOpts & 16) == 0) goto L14;
        this.mToolbar.addView(r3);
        return;
    L14:
        return;
    L5:
        if ((this.mDisplayOpts & 16) == 0) goto L7;
        this.mToolbar.removeView(r0);
        goto L7
    }

    public void setDefaultNavigationContentDescription(int r2) {
        if (r2 == this.mDefaultNavigationContentDescription) goto L10;
        this.mDefaultNavigationContentDescription = r2;
        if (TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription()) == false) goto L9;
        setNavigationContentDescription(this.mDefaultNavigationContentDescription);
        return;
    L9:
        return;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setDisplayOptions(int r4) {
        int r0 = this.mDisplayOpts ^ r4;
        this.mDisplayOpts = r4;
        if (r0 != 0) goto L5;
        return;
    L5:
        if ((r0 & 4) == 0) goto L11;
        if ((r4 & 4) == 0) goto L9;
        updateHomeAccessibility();
    L9:
        updateNavigationIcon();
    L11:
        if ((r0 & 3) == 0) goto L14;
        updateToolbarLogo();
    L14:
        if ((r0 & 8) == 0) goto L20;
        int r1 = r4 & 8;
        Toolbar r2 = this.mToolbar;
        if (r1 == 0) goto L18;
        r2.setTitle(this.mTitle);
        this.mToolbar.setSubtitle(this.mSubtitle);
        goto L20
    L18:
        r2.setTitle(null);
        this.mToolbar.setSubtitle(null);
    L20:
        if ((r0 & 16) == 0) goto L30;
        View r02 = this.mCustomView;
        if (r02 == null) goto L31;
        int r42 = r4 & 16;
        Toolbar r3 = this.mToolbar;
        if (r42 == 0) goto L27;
        r3.addView(r02);
        return;
    L27:
        r3.removeView(r02);
        return;
    L31:
        return;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setEmbeddedTabView(ScrollingTabContainerView r3) {
        View r0 = this.mTabView;
        if (r0 == null) goto L7;
        ViewParent r02 = r0.getParent();
        Toolbar r1 = this.mToolbar;
        if (r02 != r1) goto L7;
        r1.removeView(this.mTabView);
    L7:
        this.mTabView = r3;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setHomeButtonEnabled(boolean r1) {
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setIcon(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setIcon(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setIcon(Drawable r1) {
        this.mIcon = r1;
        updateToolbarLogo();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setLogo(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setLogo(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setLogo(Drawable r1) {
        this.mLogo = r1;
        updateToolbarLogo();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setMenu(Menu r3, MenuPresenter.Callback r4) {
        if (this.mActionMenuPresenter != null) goto L5;
        ActionMenuPresenter r0 = new ActionMenuPresenter(this.mToolbar.getContext());
        this.mActionMenuPresenter = r0;
        r0.setId(R$id.action_menu_presenter);
    L5:
        this.mActionMenuPresenter.setCallback(r4);
        this.mToolbar.setMenu((MenuBuilder) r3, this.mActionMenuPresenter);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setMenuCallbacks(MenuPresenter.Callback r1, MenuBuilder.Callback r2) {
        this.mToolbar.setMenuCallbacks(r1, r2);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    public void setNavigationContentDescription(int r2) {
        if (r2 != 0) goto L4;
        String r22 = null;
    L5:
        setNavigationContentDescription(r22);
        return;
    L4:
        r22 = getContext().getString(r2);
        goto L5
    }

    public void setNavigationContentDescription(CharSequence r1) {
        this.mHomeDescription = r1;
        updateHomeAccessibility();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setNavigationIcon(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setNavigationIcon(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setNavigationIcon(Drawable r1) {
        this.mNavIcon = r1;
        updateNavigationIcon();
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setSubtitle(CharSequence r2) {
        this.mSubtitle = r2;
        if ((this.mDisplayOpts & 8) == 0) goto L6;
        this.mToolbar.setSubtitle(r2);
        return;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setTitle(CharSequence r2) {
        this.mTitleSet = true;
        setTitleInt(r2);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setVisibility(int r1) {
        this.mToolbar.setVisibility(r1);
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setWindowCallback(Window.Callback r1) {
        this.mWindowCallback = r1;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public void setWindowTitle(CharSequence r2) {
        if (this.mTitleSet == true) goto L6;
        setTitleInt(r2);
        return;
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int r3, long r4) {
        ViewPropertyAnimatorCompat r0 = ViewCompat.animate(this.mToolbar);
        if (r3 != 0) goto L5;
        float r1 = 1.0f;
    L7:
        return r0.alpha(r1).setDuration(r4).setListener(new AnonymousClass2(this, r3));
    L5:
        r1 = 0.0f;
        goto L7
    }

    @Override // androidx.appcompat.widget.DecorToolbar
    public boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }
}
