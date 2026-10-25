package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenuItem;

/* loaded from: classes3.dex */
public class ActionMenuItem implements SupportMenuItem {
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private Context mContext;
    private int mFlags;
    private final int mGroup;
    private boolean mHasIconTint;
    private boolean mHasIconTintMode;
    private Drawable mIconDrawable;
    private ColorStateList mIconTintList;
    private PorterDuff.Mode mIconTintMode;
    private final int mId;
    private Intent mIntent;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    public ActionMenuItem(Context r1, int r2, int r3, int r4, int r5, CharSequence r6) {
        this.mShortcutNumericModifiers = 4096;
        this.mShortcutAlphabeticModifiers = 4096;
        this.mIconTintList = null;
        this.mIconTintMode = null;
        this.mHasIconTint = false;
        this.mHasIconTintMode = false;
        this.mFlags = 16;
        this.mContext = r1;
        this.mId = r3;
        this.mGroup = r2;
        this.mOrdering = r5;
        this.mTitle = r6;
    }

    private void applyIconTint() {
        Drawable r0 = this.mIconDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mHasIconTint == false) goto L7;
    L8:
        Drawable r02 = DrawableCompat.wrap(r0);
        this.mIconDrawable = r02;
        Drawable r03 = r02.mutate();
        this.mIconDrawable = r03;
        if (this.mHasIconTint == false) goto L12;
        DrawableCompat.setTintList(r03, this.mIconTintList);
    L12:
        if (this.mHasIconTintMode == false) goto L17;
        DrawableCompat.setTintMode(this.mIconDrawable, this.mIconTintMode);
        return;
    L17:
        return;
    L7:
        if (this.mHasIconTintMode == true) goto L8;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public View getActionView() {
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
        return this.mIconDrawable;
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
    public int getItemId() {
        return this.mId;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
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
        return this.mOrdering;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public androidx.core.view.ActionProvider getSupportActionProvider() {
        return null;
    }

    @Override // android.view.MenuItem
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

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        if ((this.mFlags & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        if ((this.mFlags & 2) == 0) goto L6;
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

    @Override // android.view.MenuItem
    public boolean isVisible() {
        if ((this.mFlags & 8) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider r1) {
        throw new UnsupportedOperationException();
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
    public SupportMenuItem setActionView(int r1) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setActionView(View r1) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char r1) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(r1);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char r1, int r2) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(r1);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean r2) {
        int r0 = this.mFlags & (-2);
        this.mFlags = (r2 ? 1 : 0) | r0;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean r2) {
        int r0 = this.mFlags & (-3);
        if (r2 == false) goto L5;
        int r22 = 2;
    L6:
        this.mFlags = r22 | r0;
        return this;
    L5:
        r22 = 0;
        goto L6
    }

    @Override // android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence r1) {
        return setContentDescription(r1);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setContentDescription(CharSequence r1) {
        this.mContentDescription = r1;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean r2) {
        int r0 = this.mFlags & (-17);
        if (r2 == false) goto L5;
        int r22 = 16;
    L6:
        this.mFlags = r22 | r0;
        return this;
    L5:
        r22 = 0;
        goto L6
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int r2) {
        this.mIconDrawable = ContextCompat.getDrawable(this.mContext, r2);
        applyIconTint();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable r1) {
        this.mIconDrawable = r1;
        applyIconTint();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList r1) {
        this.mIconTintList = r1;
        this.mHasIconTint = true;
        applyIconTint();
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode r1) {
        this.mIconTintMode = r1;
        this.mHasIconTintMode = true;
        applyIconTint();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent r1) {
        this.mIntent = r1;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char r1) {
        this.mShortcutNumericChar = r1;
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setNumericShortcut(char r1, int r2) {
        this.mShortcutNumericChar = r1;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener r1) {
        throw new UnsupportedOperationException();
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
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public MenuItem setShortcut(char r1, char r2, int r3, int r4) {
        this.mShortcutNumericChar = r1;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(r3);
        this.mShortcutAlphabeticChar = Character.toLowerCase(r2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(r4);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public void setShowAsAction(int r1) {
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

    @Override // androidx.core.internal.view.SupportMenuItem
    public SupportMenuItem setSupportActionProvider(androidx.core.view.ActionProvider r1) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int r2) {
        this.mTitle = this.mContext.getResources().getString(r2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence r1) {
        this.mTitle = r1;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence r1) {
        this.mTitleCondensed = r1;
        return this;
    }

    @Override // android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence r1) {
        return setTooltipText(r1);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public SupportMenuItem setTooltipText(CharSequence r1) {
        this.mTooltipText = r1;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean r3) {
        int r1 = 8;
        int r0 = this.mFlags & 8;
        if (r3 == false) goto L5;
        r1 = 0;
    L5:
        this.mFlags = r0 | r1;
        return this;
    }
}
