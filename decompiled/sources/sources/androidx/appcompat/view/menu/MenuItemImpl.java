package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.widget.LinearLayout;
import androidx.appcompat.R$string;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider;
import c.f$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class MenuItemImpl implements SupportMenuItem {
    private ActionProvider mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private int mFlags;
    private final int mGroup;
    private boolean mHasIconTint;
    private boolean mHasIconTintMode;
    private Drawable mIconDrawable;
    private int mIconResId;
    private ColorStateList mIconTintList;
    private PorterDuff.Mode mIconTintMode;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded;
    private Runnable mItemCallback;
    MenuBuilder mMenu;
    private ContextMenu.ContextMenuInfo mMenuInfo;
    private boolean mNeedToApplyIconTint;
    private MenuItem.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private int mShowAsAction;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    /* renamed from: androidx.appcompat.view.menu.MenuItemImpl$1, reason: invalid class name */
    public class AnonymousClass1 implements ActionProvider.VisibilityListener {
        final /* synthetic */ MenuItemImpl this$0;

        public AnonymousClass1(MenuItemImpl r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean r1) {
            MenuItemImpl r0 = this.this$0;
            r0.mMenu.onItemVisibleChanged(r0);
        }
    }

    public MenuItemImpl(MenuBuilder r3, int r4, int r5, int r6, int r7, CharSequence r8, int r9) {
        this.mShortcutNumericModifiers = 4096;
        this.mShortcutAlphabeticModifiers = 4096;
        this.mIconResId = 0;
        this.mIconTintList = null;
        this.mIconTintMode = null;
        this.mHasIconTint = false;
        this.mHasIconTintMode = false;
        this.mNeedToApplyIconTint = false;
        this.mFlags = 16;
        this.mIsActionViewExpanded = false;
        this.mMenu = r3;
        this.mId = r5;
        this.mGroup = r4;
        this.mCategoryOrder = r6;
        this.mOrdering = r7;
        this.mTitle = r8;
        this.mShowAsAction = r9;
    }

    private static void appendModifier(StringBuilder r0, int r1, int r2, String r3) {
        if ((r1 & r2) != r2) goto L6;
        r0.append(r3);
        return;
    }

    private Drawable applyIconTintIfNecessary(Drawable r2) {
        if (r2 != null) goto L4;
    L16:
        return r2;
    L4:
        if (this.mNeedToApplyIconTint == false) goto L16;
        if (this.mHasIconTint == false) goto L8;
    L9:
        r2 = DrawableCompat.wrap(r2).mutate();
        if (this.mHasIconTint == false) goto L13;
        DrawableCompat.setTintList(r2, this.mIconTintList);
    L13:
        if (this.mHasIconTintMode == false) goto L15;
        DrawableCompat.setTintMode(r2, this.mIconTintMode);
    L15:
        this.mNeedToApplyIconTint = false;
        goto L16
    L8:
        if (this.mHasIconTintMode == false) goto L16;
        goto L9
    }

    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.mShowAsAction & 8) != 0) goto L6;
        return false;
    L6:
        if (this.mActionView != null) goto L9;
        return true;
    L9:
        MenuItem.OnActionExpandListener r0 = this.mOnActionExpandListener;
        if (r0 == null) goto L16;
        if (r0.onMenuItemActionCollapse(this) == true) goto L16;
        return false;
    L16:
        return this.mMenu.collapseItemActionView(this);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean expandActionView() {
        if (hasCollapsibleActionView() == true) goto L5;
        return false;
    L5:
        MenuItem.OnActionExpandListener r0 = this.mOnActionExpandListener;
        if (r0 == null) goto L12;
        if (r0.onMenuItemActionExpand(this) == true) goto L12;
        return false;
    L12:
        return this.mMenu.expandItemActionView(this);
    }

    @Override // android.view.MenuItem
    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public View getActionView() {
        View r0 = this.mActionView;
        if (r0 == null) goto L5;
        return r0;
    L5:
        ActionProvider r02 = this.mActionProvider;
        if (r02 == null) goto L9;
        View r03 = r02.onCreateActionView(this);
        this.mActionView = r03;
        return r03;
    L9:
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.mGroup;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable r0 = this.mIconDrawable;
        if (r0 == null) goto L7;
        return applyIconTintIfNecessary(r0);
    L7:
        if (this.mIconResId == 0) goto L10;
        Drawable r02 = AppCompatResources.getDrawable(this.mMenu.getContext(), this.mIconResId);
        this.mIconResId = 0;
        this.mIconDrawable = r02;
        return applyIconTintIfNecessary(r02);
    L10:
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.mIconTintList;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.mIconTintMode;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.mIntent;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.mCategoryOrder;
    }

    public int getOrdering() {
        return this.mOrdering;
    }

    public char getShortcut() {
        if (this.mMenu.isQwertyMode() == false) goto L7;
        return this.mShortcutAlphabeticChar;
    L7:
        return this.mShortcutNumericChar;
    }

    public String getShortcutLabel() {
        char r0 = getShortcut();
        if (r0 != 0) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        Resources r1 = this.mMenu.getContext().getResources();
        StringBuilder r2 = new StringBuilder();
        if (ViewConfiguration.get(this.mMenu.getContext()).hasPermanentMenuKey() == false) goto L10;
        r2.append(r1.getString(R$string.abc_prepend_shortcut_label));
    L10:
        if (this.mMenu.isQwertyMode() == false) goto L12;
        int r5 = this.mShortcutAlphabeticModifiers;
    L13:
        appendModifier(r2, r5, 65536, r1.getString(R$string.abc_menu_meta_shortcut_label));
        appendModifier(r2, r5, 4096, r1.getString(R$string.abc_menu_ctrl_shortcut_label));
        appendModifier(r2, r5, 2, r1.getString(R$string.abc_menu_alt_shortcut_label));
        appendModifier(r2, r5, 1, r1.getString(R$string.abc_menu_shift_shortcut_label));
        appendModifier(r2, r5, 4, r1.getString(R$string.abc_menu_sym_shortcut_label));
        appendModifier(r2, r5, 8, r1.getString(R$string.abc_menu_function_shortcut_label));
        if (r0 != '\b') goto L16;
        r2.append(r1.getString(R$string.abc_menu_delete_shortcut_label));
    L24:
        return r2.toString();
    L16:
        if (r0 != '\n') goto L18;
        r2.append(r1.getString(R$string.abc_menu_enter_shortcut_label));
        goto L24
    L18:
        if (r0 == ' ') goto L20;
        r2.append(r0);
        goto L24
    L20:
        r2.append(r1.getString(R$string.abc_menu_space_shortcut_label));
        goto L24
    L12:
        r5 = this.mShortcutNumericModifiers;
        goto L13
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence r0 = this.mTitleCondensed;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return this.mTitle;
    }

    public CharSequence getTitleForItemView(MenuView.ItemView r1) {
        if (r1 == null) goto L8;
        if (r1.prefersCondensedTitle() == false) goto L8;
        return getTitleCondensed();
    L8:
        return getTitle();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public boolean hasCollapsibleActionView() {
        if ((this.mShowAsAction & 8) != 0) goto L5;
    L13:
        return false;
    L5:
        if (this.mActionView != null) goto L10;
        ActionProvider r0 = this.mActionProvider;
        if (r0 == null) goto L10;
        this.mActionView = r0.onCreateActionView(this);
    L10:
        if (this.mActionView == null) goto L13;
        return true;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        if (this.mSubMenu == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean invoke() {
        MenuItem.OnMenuItemClickListener r0 = this.mClickListener;
        if (r0 != null) goto L5;
    L7:
        MenuBuilder r02 = this.mMenu;
        if (r02.dispatchMenuItemSelected(r02, this) == false) goto L10;
        return true;
    L10:
        Runnable r03 = this.mItemCallback;
        if (r03 == null) goto L15;
        r03.run();
        return true;
    L15:
        if (this.mIntent != null) goto L27;
    L20:
        ActionProvider r4 = this.mActionProvider;
        if (r4 != null) goto L23;
        return false;
    L23:
        if (r4.onPerformDefaultAction() == false) goto L29;
        return true;
    L29:
        return false;
    L27:
        this.mMenu.getContext().startActivity(this.mIntent);     // Catch: ActivityNotFoundException -> L18
        return true;
    L18:
        e = move-exception;
        Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
        goto L20
    L5:
        if (r0.onMenuItemClick(this) == false) goto L7;
        return true;
    }

    public boolean isActionButton() {
        if ((this.mFlags & 32) != 32) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        if ((this.mFlags & 1) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        if ((this.mFlags & 2) != 2) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        if ((this.mFlags & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isExclusiveCheckable() {
        if ((this.mFlags & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        ActionProvider r0 = this.mActionProvider;
        if (r0 == null) goto L13;
        if (r0.overridesItemVisibility() == false) goto L13;
        if ((this.mFlags & 8) == 0) goto L9;
    L11:
        return false;
    L9:
        if (this.mActionProvider.isVisible() == false) goto L11;
        return true;
    L13:
        if ((this.mFlags & 8) != 0) goto L15;
        return true;
    L15:
        return false;
    }

    public boolean requestsActionButton() {
        if ((this.mShowAsAction & 1) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean requiresActionButton() {
        if ((this.mShowAsAction & 2) != 2) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(android.view.ActionProvider r1) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setActionView(int r1) {
        return setActionView(r1);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setActionView(View r1) {
        return setActionView(r1);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setActionView(int r4) {
        Context r0 = this.mMenu.getContext();
        setActionView(LayoutInflater.from(r0).inflate(r4, new LinearLayout(r0), false));
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setActionView(View r3) {
        this.mActionView = r3;
        this.mActionProvider = null;
        if (r3 != null) goto L5;
    L9:
        this.mMenu.onItemActionRequestChanged(this);
        return this;
    L5:
        if (r3.getId() != (-1)) goto L9;
        int r0 = this.mId;
        if (r0 <= 0) goto L9;
        r3.setId(r0);
        goto L9
    }

    public void setActionViewExpanded(boolean r1) {
        this.mIsActionViewExpanded = r1;
        this.mMenu.onItemsChanged(false);
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char r2) {
        if (this.mShortcutAlphabeticChar != r2) goto L5;
        return this;
    L5:
        this.mShortcutAlphabeticChar = Character.toLowerCase(r2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char r2, int r3) {
        if (this.mShortcutAlphabeticChar == r2) goto L5;
    L7:
        this.mShortcutAlphabeticChar = Character.toLowerCase(r2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(r3);
        this.mMenu.onItemsChanged(false);
        return this;
    L5:
        if (this.mShortcutAlphabeticModifiers != r3) goto L7;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean r3) {
        int r0 = this.mFlags;
        int r32 = r3 ? 1 : 0;
        int r33 = r32 | (r0 & (-2));
        this.mFlags = r33;
        if (r0 == r33) goto L5;
        this.mMenu.onItemsChanged(false);
    L5:
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean r2) {
        if ((this.mFlags & 4) == 0) goto L6;
        this.mMenu.setExclusiveItemChecked(this);
        return this;
    L6:
        setCheckedInt(r2);
        return this;
    }

    public void setCheckedInt(boolean r4) {
        int r0 = this.mFlags;
        int r1 = r0 & (-3);
        if (r4 == false) goto L5;
        int r42 = 2;
    L6:
        int r43 = r42 | r1;
        this.mFlags = r43;
        if (r0 == r43) goto L10;
        this.mMenu.onItemsChanged(false);
        return;
    L10:
        return;
    L5:
        r42 = 0;
        goto L6
    }

    @Override // android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence r1) {
        return setContentDescription(r1);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setContentDescription(CharSequence r2) {
        this.mContentDescription = r2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean r2) {
        int r0 = this.mFlags;
        if (r2 == false) goto L5;
        this.mFlags = r0 | 16;
    L6:
        this.mMenu.onItemsChanged(false);
        return this;
    L5:
        this.mFlags = r0 & (-17);
        goto L6
    }

    public void setExclusiveCheckable(boolean r2) {
        int r0 = this.mFlags & (-5);
        if (r2 == false) goto L5;
        int r22 = 4;
    L6:
        this.mFlags = r22 | r0;
        return;
    L5:
        r22 = 0;
        goto L6
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int r2) {
        this.mIconDrawable = null;
        this.mIconResId = r2;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable r2) {
        this.mIconResId = 0;
        this.mIconDrawable = r2;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList r2) {
        this.mIconTintList = r2;
        this.mHasIconTint = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode r2) {
        this.mIconTintMode = r2;
        this.mHasIconTintMode = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent r1) {
        this.mIntent = r1;
        return this;
    }

    public void setIsActionButton(boolean r2) {
        int r0 = this.mFlags;
        if (r2 == false) goto L6;
        this.mFlags = r0 | 32;
        return;
    L6:
        this.mFlags = r0 & (-33);
    }

    public void setMenuInfo(ContextMenu.ContextMenuInfo r1) {
        this.mMenuInfo = r1;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char r2) {
        if (this.mShortcutNumericChar != r2) goto L5;
        return this;
    L5:
        this.mShortcutNumericChar = r2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setNumericShortcut(char r2, int r3) {
        if (this.mShortcutNumericChar == r2) goto L5;
    L7:
        this.mShortcutNumericChar = r2;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(r3);
        this.mMenu.onItemsChanged(false);
        return this;
    L5:
        if (this.mShortcutNumericModifiers != r3) goto L7;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener r1) {
        this.mOnActionExpandListener = r1;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener r1) {
        this.mClickListener = r1;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char r1, char r2) {
        this.mShortcutNumericChar = r1;
        this.mShortcutAlphabeticChar = Character.toLowerCase(r2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setShortcut(char r1, char r2, int r3, int r4) {
        this.mShortcutNumericChar = r1;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(r3);
        this.mShortcutAlphabeticChar = Character.toLowerCase(r2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(r4);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public void setShowAsAction(int r3) {
        int r0 = r3 & 3;
        if (r0 != 0) goto L5;
    L11:
        this.mShowAsAction = r3;
        this.mMenu.onItemActionRequestChanged(this);
        return;
    L5:
        if (r0 == 1) goto L11;
        if (r0 == 2) goto L11;
        f$$ExternalSyntheticBUOutline1.m("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setShowAsActionFlags(int r1) {
        return setShowAsActionFlags(r1);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setShowAsActionFlags(int r1) {
        setShowAsAction(r1);
        return this;
    }

    public void setSubMenu(SubMenuBuilder r1) {
        this.mSubMenu = r1;
        r1.setHeaderTitle(getTitle());
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem setSupportActionProvider(ActionProvider r2) {
        ActionProvider r0 = this.mActionProvider;
        if (r0 == null) goto L5;
        r0.reset();
    L5:
        this.mActionView = null;
        this.mActionProvider = r2;
        this.mMenu.onItemsChanged(true);
        ActionProvider r22 = this.mActionProvider;
        if (r22 == null) goto L8;
        r22.setVisibilityListener(new AnonymousClass1(this));
    L8:
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int r2) {
        return setTitle(this.mMenu.getContext().getString(r2));
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence r3) {
        this.mTitle = r3;
        this.mMenu.onItemsChanged(false);
        SubMenuBuilder r0 = this.mSubMenu;
        if (r0 == null) goto L5;
        r0.setHeaderTitle(r3);
    L5:
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence r2) {
        this.mTitleCondensed = r2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence r1) {
        return setTooltipText(r1);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setTooltipText(CharSequence r2) {
        this.mTooltipText = r2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean r1) {
        if (setVisibleInt(r1) == false) goto L5;
        this.mMenu.onItemVisibleChanged(this);
    L5:
        return this;
    }

    public boolean setVisibleInt(boolean r4) {
        int r0 = this.mFlags;
        int r1 = r0 & (-9);
        if (r4 == false) goto L5;
        int r42 = 0;
    L6:
        int r43 = r42 | r1;
        this.mFlags = r43;
        if (r0 == r43) goto L10;
        return true;
    L10:
        return false;
    L5:
        r42 = 8;
        goto L6
    }

    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }

    public boolean shouldShowShortcut() {
        if (this.mMenu.isShortcutsVisible() == true) goto L5;
        return false;
    L5:
        if (getShortcut() == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean showsTextAsAction() {
        if ((this.mShowAsAction & 4) != 4) goto L6;
        return true;
    L6:
        return false;
    }

    public String toString() {
        CharSequence r0 = this.mTitle;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.toString();
    }
}
