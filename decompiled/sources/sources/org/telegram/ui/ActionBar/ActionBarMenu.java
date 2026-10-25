package org.telegram.ui.ActionBar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes3.dex */
public class ActionBarMenu extends LinearLayout {
    public boolean drawBlur;
    private boolean glassMode;
    private ArrayList<Integer> ids;
    protected boolean isActionMode;
    private Runnable onLayoutListener;
    protected ActionBar parentActionBar;

    /* loaded from: classes6.dex */
    public static class LazyItem {
        Boolean allowCloseAnimation;
        float alpha;
        int backgroundColor;
        ActionBarMenuItem cell;
        CharSequence contentDescription;
        Drawable drawable;
        int icon;
        int id;
        Boolean isSearchField;
        ArrayList<Utilities.Callback<ActionBarMenuItem>> onViews;
        Boolean overrideMenuClick;
        ActionBarMenu parent;
        Theme.ResourcesProvider resourcesProvider;
        CharSequence searchFieldHint;
        ActionBarMenuItem.ActionBarMenuItemSearchListener searchListener;
        Object tag;
        CharSequence text;
        CharSequence title;
        int visibility;
        int width;

        public LazyItem(ActionBarMenu r2, int r3, int r4, CharSequence r5, int r6, Drawable r7, int r8, CharSequence r9, Theme.ResourcesProvider r10) {
            this.alpha = 1.0f;
            this.visibility = 8;
            this.parent = r2;
            this.id = r3;
            this.icon = r4;
            this.text = r5;
            this.backgroundColor = r6;
            this.drawable = r7;
            this.width = r8;
            this.title = r9;
            this.resourcesProvider = r10;
        }

        public void add() {
            if (this.cell != null) goto L48;
            int r0 = this.parent.getChildCount();
            int r2 = 0;
            if (ActionBarMenu.m5485$$Nest$fgetids(this.parent) == null) goto L16;
            int r1 = ActionBarMenu.m5485$$Nest$fgetids(this.parent).indexOf(Integer.valueOf(this.id));
            int r3 = 0;
        L9:
            if (r3 >= this.parent.getChildCount()) goto L16;
            Object r4 = this.parent.getChildAt(r3).getTag();
            if ((r4 instanceof Integer) == false) goto L15;
            if (ActionBarMenu.m5485$$Nest$fgetids(this.parent).indexOf((Integer) r4) <= r1) goto L15;
            int r5 = r3;
        L17:
            ActionBarMenuItem r02 = this.parent.addItemAt(r5, this.id, this.icon, this.text, this.backgroundColor, this.drawable, this.width, this.title, this.resourcesProvider);
            this.cell = r02;
            r02.setVisibility(this.visibility);
            CharSequence r03 = this.contentDescription;
            if (r03 == null) goto L20;
            this.cell.setContentDescription(r03);
        L20:
            Boolean r04 = this.allowCloseAnimation;
            if (r04 == null) goto L23;
            this.cell.setAllowCloseAnimation(r04.booleanValue());
        L23:
            Boolean r05 = this.overrideMenuClick;
            if (r05 == null) goto L26;
            this.cell.setOverrideMenuClick(r05.booleanValue());
        L26:
            Boolean r06 = this.isSearchField;
            if (r06 == null) goto L29;
            this.cell.setIsSearchField(r06.booleanValue());
        L29:
            ActionBarMenuItem.ActionBarMenuItemSearchListener r07 = this.searchListener;
            if (r07 == null) goto L32;
            this.cell.setActionBarMenuItemSearchListener(r07);
        L32:
            CharSequence r08 = this.searchFieldHint;
            if (r08 == null) goto L35;
            this.cell.setSearchFieldHint(r08);
        L35:
            this.cell.setAlpha(this.alpha);
            ArrayList<Utilities.Callback<ActionBarMenuItem>> r09 = this.onViews;
            if (r09 == null) goto L47;
            int r12 = r09.size();
        L38:
            if (r2 >= r12) goto L40;
            Utilities.Callback<ActionBarMenuItem> r32 = r09.get(r2);
            r2 = r2 + 1;
            r32.run(this.cell);
            goto L38
        L40:
            this.onViews = null;
            return;
        L47:
            return;
        L15:
            r3 = r3 + 1;
        L16:
            r5 = r0;
            goto L17
        }

        public ActionBarMenuItem createView() {
            add();
            return this.cell;
        }

        public Object getTag() {
            return this.tag;
        }

        public int getVisibility() {
            return this.visibility;
        }

        public void onView(Utilities.Callback<ActionBarMenuItem> r2) {
            ActionBarMenuItem r0 = this.cell;
            if (r0 == null) goto L7;
            r2.run(r0);
            return;
        L7:
            if (this.onViews != null) goto L9;
            this.onViews = new ArrayList();
        L9:
            this.onViews.add(r2);
        }

        public void setAllowCloseAnimation(boolean r1) {
            Boolean r12 = Boolean.valueOf(r1);
            this.allowCloseAnimation = r12;
            ActionBarMenuItem r0 = this.cell;
            if (r0 == null) goto L6;
            r0.setAllowCloseAnimation(r12.booleanValue());
            return;
        }

        public void setAlpha(float r1) {
            this.alpha = r1;
            ActionBarMenuItem r0 = this.cell;
            if (r0 == null) goto L6;
            r0.setAlpha(r1);
            return;
        }

        public void setContentDescription(CharSequence r1) {
            this.contentDescription = r1;
            ActionBarMenuItem r0 = this.cell;
            if (r0 == null) goto L6;
            r0.setContentDescription(r1);
            return;
        }

        public void setOverrideMenuClick(boolean r2) {
            this.overrideMenuClick = Boolean.valueOf(r2);
            ActionBarMenuItem r1 = this.cell;
            if (r1 == null) goto L6;
            r1.setOverrideMenuClick(r2);
            return;
        }

        public void setTag(Object r1) {
            this.tag = r1;
        }

        public void setVisibility(int r2) {
            if (this.visibility == r2) goto L11;
            this.visibility = r2;
            if (r2 != 0) goto L7;
            add();
        L7:
            ActionBarMenuItem r1 = this.cell;
            if (r1 == null) goto L12;
            r1.setVisibility(r2);
            return;
        L12:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$4RMHCpsgx9xh3XpeQKYbGnqJwB4(ActionBarMenu r0, View r1) {
        r0.lambda$addItemAt$0(r1);
    }

    /* renamed from: -$$Nest$fgetids, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5485$$Nest$fgetids(ActionBarMenu r0) {
        return r0.ids;
    }

    public ActionBarMenu(Context r1, ActionBar r2) {
        super(r1);
        this.drawBlur = true;
        setOrientation(0);
        this.parentActionBar = r2;
    }

    private int getMeasuredWidthWithMargins(View r2) {
        int r1 = r2.getMeasuredWidth();
        ViewGroup.LayoutParams r22 = r2.getLayoutParams();
        if ((r22 instanceof ViewGroup.MarginLayoutParams) == false) goto L6;
        ViewGroup.MarginLayoutParams r23 = (ViewGroup.MarginLayoutParams) r22;
        return r1 + (r23.leftMargin + r23.rightMargin);
    L6:
        return r1;
    }

    private /* synthetic */ void lambda$addItemAt$0(View r3) {
        ActionBarMenuItem r0 = (ActionBarMenuItem) r3;
        if (r0.hasSubMenu() == false) goto L9;
        if (this.parentActionBar.actionBarMenuOnItemClick.canOpenMenu() == false) goto L14;
        r0.toggleSubMenu();
        return;
    L14:
        return;
    L9:
        if (r0.isSearchField() == false) goto L12;
        this.parentActionBar.onSearchFieldVisibilityChanged(r0.toggleSearch(true));
        return;
    L12:
        onItemClick(((Integer) r3.getTag()).intValue());
    }

    public ActionBarMenuItem addItem(int r3, int r4) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L5;
        int r02 = r1.itemsActionModeBackgroundColor;
    L7:
        return addItem(r3, r4, r02, null);
    L5:
        r02 = r1.itemsBackgroundColor;
        goto L7
    }

    public ActionBarMenuItem addItem(int r11, int r12, int r13, Theme.ResourcesProvider r14) {
        return addItem(r11, r12, null, r13, null, AndroidUtilities.dp(48.0f), null, r14);
    }

    public ActionBarMenuItem addItem(int r10, int r11, CharSequence r12, int r13, Drawable r14, int r15, CharSequence r16) {
        return addItem(r10, r11, r12, r13, r14, r15, r16, null);
    }

    public ActionBarMenuItem addItem(int r11, int r12, CharSequence r13, int r14, Drawable r15, int r16, CharSequence r17, Theme.ResourcesProvider r18) {
        if (this.ids != null) goto L5;
        this.ids = new ArrayList();
    L5:
        this.ids.add(Integer.valueOf(r11));
        return addItemAt(-1, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    public ActionBarMenuItem addItem(int r3, int r4, Theme.ResourcesProvider r5) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L5;
        int r02 = r1.itemsActionModeBackgroundColor;
    L7:
        return addItem(r3, r4, r02, r5);
    L5:
        r02 = r1.itemsBackgroundColor;
        goto L7
    }

    public ActionBarMenuItem addItem(int r2, Drawable r3) {
        return addItem(r2, r3, null);
    }

    public ActionBarMenuItem addItem(int r11, Drawable r12, Theme.ResourcesProvider r13) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L6;
        int r02 = r1.itemsActionModeBackgroundColor;
    L5:
        int r5 = r02;
        return addItem(r11, 0, null, r5, r12, AndroidUtilities.dp(48.0f), null, r13);
    L6:
        r02 = r1.itemsBackgroundColor;
        goto L5
    }

    public ActionBarMenuItem addItem(int r10, CharSequence r11) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L6;
        int r02 = r1.itemsActionModeBackgroundColor;
    L8:
        return addItem(r10, 0, r11, r02, null, 0, r11);
    L6:
        r02 = r1.itemsBackgroundColor;
        goto L8
    }

    public ActionBarMenuItem addItemAt(int r12, int r13, int r14, CharSequence r15, int r16, Drawable r17, int r18, CharSequence r19, Theme.ResourcesProvider r20) {
        Context r5 = getContext();
        boolean r6 = this.isActionMode;
        ActionBar r7 = this.parentActionBar;
        if (r6 == false) goto L6;
        int r62 = r7.itemsActionModeColor;
    L5:
        int r8 = r62;
        if (r15 == null) goto L10;
        boolean r63 = true;
    L11:
        ActionBarMenuItem r4 = new ActionBarMenuItem(r5, this, r16, r8, r63, r20);
        r4.setTag(Integer.valueOf(r13));
        if (r15 == null) goto L18;
        r4.textView.setText(r15);
        if (r18 == 0) goto L16;
        int r0 = r18;
    L17:
        LinearLayout.LayoutParams r142 = new LinearLayout.LayoutParams(r0, -1);
        int r132 = AndroidUtilities.dp(14.0f);
        r142.rightMargin = r132;
        r142.leftMargin = r132;
        addView(r4, r12, r142);
    L26:
        r4.setOnClickListener(new ActionBarMenu$$ExternalSyntheticLambda0(this));
        if (r19 == null) goto L29;
        r4.setContentDescription(r19);
    L29:
        return r4;
    L16:
        r0 = -2;
        goto L17
    L18:
        if (r17 == null) goto L23;
        boolean r143 = r17 instanceof RLottieDrawable;
        RLottieImageView r02 = r4.iconView;
        if (r143 == false) goto L22;
        r02.setAnimation((RLottieDrawable) r17);
    L25:
        addView(r4, r12, new LinearLayout.LayoutParams(r18, -1));
        goto L26
    L22:
        r02.setImageDrawable(r17);
        goto L25
    L23:
        if (r14 == 0) goto L25;
        r4.iconView.setImageResource(r14);
        goto L25
    L10:
        r63 = false;
        goto L11
    L6:
        r62 = r7.itemsColor;
        goto L5
    }

    public ActionBarMenuItem addItemWithWidth(int r10, int r11, int r12) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L6;
        int r02 = r1.itemsActionModeBackgroundColor;
    L8:
        return addItem(r10, r11, null, r02, null, r12, null);
    L6:
        r02 = r1.itemsBackgroundColor;
        goto L8
    }

    public ActionBarMenuItem addItemWithWidth(int r10, int r11, int r12, CharSequence r13) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L6;
        int r02 = r1.itemsActionModeBackgroundColor;
    L8:
        return addItem(r10, r11, null, r02, null, r12, r13);
    L6:
        r02 = r1.itemsBackgroundColor;
        goto L8
    }

    public ActionBarMenuItem addItemWithWidth(int r10, Drawable r11, int r12, CharSequence r13) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L6;
        int r02 = r1.itemsActionModeBackgroundColor;
    L8:
        return addItem(r10, 0, null, r02, r11, r12, r13);
    L6:
        r02 = r1.itemsBackgroundColor;
        goto L8
    }

    public void clearItems() {
        ArrayList<Integer> r0 = this.ids;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        removeAllViews();
    }

    public void closeSearchField(boolean r6) {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L23;
        View r3 = getChildAt(r2);
        if ((r3 instanceof ActionBarMenuItem) == false) goto L16;
        ActionBarMenuItem r32 = (ActionBarMenuItem) r3;
        if (r32.isSearchField() == false) goto L16;
        if (r32.isSearchFieldVisible() == false) goto L16;
        ActionBarMenuItem.ActionBarMenuItemSearchListener r02 = r32.listener;
        if (r02 != null) goto L13;
    L14:
        this.parentActionBar.onSearchFieldVisibilityChanged(false);
        r32.toggleSearch(r6);
        return;
    L13:
        if (r02.canCollapseSearch() == true) goto L14;
        return;
    L16:
        r2 = r2 + 1;
        goto L3
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r1) {
        return super.dispatchTouchEvent(r1);
    }

    public ActionBarMenuItem getItem(int r1) {
        View r0 = findViewWithTag(Integer.valueOf(r1));
        if ((r0 instanceof ActionBarMenuItem) == true) goto L5;
        return null;
    L5:
        return (ActionBarMenuItem) r0;
    }

    public int getItemsMeasuredWidth(boolean r7) {
        int r0 = getChildCount();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L15;
        View r3 = getChildAt(r1);
        if (r7 == true) goto L12;
        if (r3.getAlpha() == 0.0f) goto L14;
        if (r3.getVisibility() == 0) goto L12;
    L14:
        r1 = r1 + 1;
    L12:
        if ((r3 instanceof ActionBarMenuItem) == false) goto L14;
        r2 = r2 + r3.getMeasuredWidth();
        goto L14
    L15:
        return r2;
    }

    public int getItemsWidth() {
        int r0 = getChildCount();
        float r1 = Float.POSITIVE_INFINITY;
        float r2 = Float.NEGATIVE_INFINITY;
        int r4 = 0;
        boolean r5 = false;
    L3:
        if (r4 >= r0) goto L10;
        View r6 = getChildAt(r4);
        if ((r6 instanceof ActionBarMenuItem) == false) goto L9;
        if (r6.getVisibility() != 0) goto L9;
        float r52 = r6.getX();
        r1 = Math.min(r1, r52);
        r2 = Math.max(r2, r6.getWidth() + r52);
        r5 = true;
    L9:
        r4 = r4 + 1;
        goto L3
    L10:
        if (r5 == true) goto L12;
        return 0;
    L12:
        return (int) (r2 - r1);
    }

    public int getVisibleItemsCount() {
        int r0 = getChildCount();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L10;
        View r3 = getChildAt(r1);
        if ((r3 instanceof ActionBarMenuItem) == false) goto L9;
        if (((ActionBarMenuItem) r3).isVisibleForCenterTitle() == false) goto L9;
        r2 = r2 + 1;
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return r2;
    }

    public int getVisibleItemsMeasuredWidth() {
        int r0 = getChildCount();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L10;
        View r3 = getChildAt(r1);
        if ((r3 instanceof ActionBarMenuItem) == false) goto L9;
        if (r3.getVisibility() == 8) goto L9;
        r2 = r2 + r3.getMeasuredWidth();
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return r2;
    }

    public int getVisibleItemsMeasuredWidthForCenterTitle() {
        int r0 = getChildCount();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L15;
        View r3 = getChildAt(r1);
        if (r3.getVisibility() != 0) goto L14;
        if ((r3 instanceof ActionBarMenuItem) == true) goto L10;
        int r32 = getMeasuredWidthWithMargins(r3);
    L12:
        r2 = r2 + r32;
        goto L14
    L10:
        if (((ActionBarMenuItem) r3).isVisibleForCenterTitle() == false) goto L14;
        r32 = getMeasuredWidthWithMargins(r3);
    L14:
        r1 = r1 + 1;
        goto L3
    L15:
        return r2;
    }

    public int getVisibleItemsMeasuredWidthWithAlpha() {
        int r0 = getChildCount();
        float r1 = 0.0f;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        View r3 = getChildAt(r2);
        if ((r3 instanceof ActionBarMenuItem) == false) goto L9;
        if (r3.getVisibility() != 0) goto L9;
        r1 = r1 + (r3.getMeasuredWidth() * r3.getAlpha());
    L9:
        r2 = r2 + 1;
        goto L3
    L11:
        return (int) r1;
    }

    public void hideAllPopupMenus() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L7;
        ((ActionBarMenuItem) r2).closeSubMenu();
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public LazyItem lazilyAddItem(int r11, int r12, CharSequence r13, int r14, Drawable r15, int r16, CharSequence r17, Theme.ResourcesProvider r18) {
        if (this.ids != null) goto L5;
        this.ids = new ArrayList();
    L5:
        this.ids.add(Integer.valueOf(r11));
        return new LazyItem(this, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    public LazyItem lazilyAddItem(int r11, int r12, Theme.ResourcesProvider r13) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L6;
        int r02 = r1.itemsActionModeBackgroundColor;
    L5:
        int r5 = r02;
        return lazilyAddItem(r11, r12, null, r5, null, AndroidUtilities.dp(48.0f), null, r13);
    L6:
        r02 = r1.itemsBackgroundColor;
        goto L5
    }

    public LazyItem lazilyAddItem(int r11, Drawable r12, Theme.ResourcesProvider r13) {
        boolean r0 = this.isActionMode;
        ActionBar r1 = this.parentActionBar;
        if (r0 == false) goto L6;
        int r02 = r1.itemsActionModeBackgroundColor;
    L5:
        int r5 = r02;
        return lazilyAddItem(r11, 0, null, r5, r12, AndroidUtilities.dp(48.0f), null, r13);
    L6:
        r02 = r1.itemsBackgroundColor;
        goto L5
    }

    public void onItemClick(int r1) {
        ActionBar.ActionBarMenuOnItemClick r0 = this.parentActionBar.actionBarMenuOnItemClick;
        if (r0 == null) goto L6;
        r0.onItemClick(r1);
        return;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        Runnable r12 = this.onLayoutListener;
        if (r12 == null) goto L5;
        r12.run();
    L5:
        ActionBar r0 = this.parentActionBar;
        if (r0 == null) goto L9;
        r0.checkMenuItemsWidth();
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        if (this.glassMode == false) goto L12;
        int r0 = getChildCount();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L12;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L11;
        ViewGroup.LayoutParams r22 = r2.getLayoutParams();
        if ((r22 instanceof ViewGroup.MarginLayoutParams) == false) goto L11;
        ViewGroup.MarginLayoutParams r23 = (ViewGroup.MarginLayoutParams) r22;
        r23.leftMargin = -AndroidUtilities.dp(5.0f);
        r23.rightMargin = -AndroidUtilities.dp(5.0f);
    L11:
        r1 = r1 + 1;
    L12:
        super.onMeasure(r6, r7);
    }

    public void onMenuButtonPressed() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L18;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L17;
        ActionBarMenuItem r22 = (ActionBarMenuItem) r2;
        if (r22.getVisibility() != 0) goto L17;
        if (r22.hasSubMenu() == true) goto L11;
        if (r22.overrideMenuClick == false) goto L17;
        onItemClick(((Integer) r22.getTag()).intValue());
        return;
    L11:
        r22.toggleSubMenu();
        return;
    L17:
        r1 = r1 + 1;
        goto L3
    }

    public void onSearchPressed() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L9;
        ActionBarMenuItem r22 = (ActionBarMenuItem) r2;
        if (r22.isSearchField() == false) goto L9;
        r22.onSearchPressed();
    L9:
        r1 = r1 + 1;
        goto L3
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        return super.onTouchEvent(r1);
    }

    public void openSearchField(boolean r5, boolean r6, String r7, boolean r8) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L13;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L12;
        ActionBarMenuItem r22 = (ActionBarMenuItem) r2;
        if (r22.isSearchField() == false) goto L12;
        if (r5 == false) goto L10;
        this.parentActionBar.onSearchFieldVisibilityChanged(r22.toggleSearch(r6));
    L10:
        r22.setSearchFieldText(r7, r8);
        r22.getSearchField().setSelection(r7.length());
        return;
    L12:
        r1 = r1 + 1;
        goto L3
    }

    public void redrawPopup(int r5) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L7;
        ((ActionBarMenuItem) r2).redrawPopup(r5);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public boolean searchFieldVisible() {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L13;
        View r3 = getChildAt(r2);
        if ((r3 instanceof ActionBarMenuItem) == false) goto L12;
        ActionBarMenuItem r32 = (ActionBarMenuItem) r3;
        if (r32.getSearchContainer() == null) goto L12;
        if (r32.getSearchContainer().getVisibility() != 0) goto L12;
        return true;
    L12:
        r2 = r2 + 1;
        goto L3
    L13:
        return false;
    }

    @Override // android.view.View
    public void setEnabled(boolean r4) {
        super.setEnabled(r4);
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        getChildAt(r1).setEnabled(r4);
        r1 = r1 + 1;
        goto L3
    }

    public void setFilter(FiltersView.MediaFilterData r5) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L10;
        ActionBarMenuItem r22 = (ActionBarMenuItem) r2;
        if (r22.isSearchField() == false) goto L10;
        r22.addSearchFilter(r5);
        return;
    L10:
        r1 = r1 + 1;
        goto L3
    }

    public void setGlassMode(boolean r1) {
        this.glassMode = r1;
    }

    public void setItemVisibility(int r1, int r2) {
        ActionBarMenuItem r0 = getItem(r1);
        if (r0 == null) goto L6;
        r0.setVisibility(r2);
        return;
    }

    public void setOnLayoutListener(Runnable r1) {
        this.onLayoutListener = r1;
    }

    public void setPopupItemsColor(int r5, boolean r6) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L7;
        ((ActionBarMenuItem) r2).setPopupItemsColor(r5, r6);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public void setPopupItemsSelectorColor(int r5) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L7;
        ((ActionBarMenuItem) r2).setPopupItemsSelectorColor(r5);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public void setSearchCursorColor(int r5) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L10;
        ActionBarMenuItem r22 = (ActionBarMenuItem) r2;
        if (r22.isSearchField() == false) goto L10;
        r22.getSearchField().setCursorColor(r5);
        return;
    L10:
        r1 = r1 + 1;
        goto L3
    }

    public void setSearchFieldText(String r6) {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        View r3 = getChildAt(r2);
        if ((r3 instanceof ActionBarMenuItem) == false) goto L9;
        ActionBarMenuItem r32 = (ActionBarMenuItem) r3;
        if (r32.isSearchField() == false) goto L9;
        r32.setSearchFieldText(r6, false);
        r32.getSearchField().setSelection(r6.length());
    L9:
        r2 = r2 + 1;
        goto L3
    }

    public void setSearchTextColor(int r5, boolean r6) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L13;
        ActionBarMenuItem r22 = (ActionBarMenuItem) r2;
        if (r22.isSearchField() == false) goto L13;
        if (r6 == false) goto L11;
        r22.getSearchField().setHintTextColor(r5);
        return;
    L11:
        r22.getSearchField().setTextColor(r5);
        return;
    L13:
        r1 = r1 + 1;
        goto L3
    }

    public void translateXItems(float r5) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L7;
        ((ActionBarMenuItem) r2).setTransitionOffset(r5);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public void updateItemsBackgroundColor() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L11;
        boolean r3 = this.isActionMode;
        ActionBar r4 = this.parentActionBar;
        if (r3 == false) goto L9;
        int r32 = r4.itemsActionModeBackgroundColor;
    L10:
        r2.setBackgroundDrawable(Theme.createSelectorDrawable(r32));
        goto L11
    L9:
        r32 = r4.itemsBackgroundColor;
    L11:
        r1 = r1 + 1;
        goto L3
    }

    public void updateItemsColor() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        View r2 = getChildAt(r1);
        if ((r2 instanceof ActionBarMenuItem) == false) goto L11;
        ActionBarMenuItem r22 = (ActionBarMenuItem) r2;
        boolean r3 = this.isActionMode;
        ActionBar r4 = this.parentActionBar;
        if (r3 == false) goto L9;
        int r32 = r4.itemsActionModeColor;
    L10:
        r22.setIconColor(r32);
        goto L11
    L9:
        r32 = r4.itemsColor;
    L11:
        r1 = r1 + 1;
        goto L3
    }
}
