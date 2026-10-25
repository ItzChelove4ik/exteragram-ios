package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.TintTypedArray;

/* loaded from: classes3.dex */
public final class ExpandedMenuView extends ListView implements MenuBuilder.ItemInvoker, MenuView, AdapterView.OnItemClickListener {
    private static final int[] TINT_ATTRS = null;
    private int mAnimations;
    private MenuBuilder mMenu;

    static {
        TINT_ATTRS = new int[]{R.attr.background, R.attr.divider};
    }

    public ExpandedMenuView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.listViewStyle);
    }

    public ExpandedMenuView(Context r3, AttributeSet r4, int r5) {
        super(r3, r4);
        setOnItemClickListener(this);
        TintTypedArray r32 = TintTypedArray.obtainStyledAttributes(r3, r4, TINT_ATTRS, r5, 0);
        if (r32.hasValue(0) == false) goto L6;
        setBackgroundDrawable(r32.getDrawable(0));
    L6:
        if (r32.hasValue(1) == false) goto L8;
        setDivider(r32.getDrawable(1));
    L8:
        r32.recycle();
    }

    public int getWindowAnimations() {
        return this.mAnimations;
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder r1) {
        this.mMenu = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.ItemInvoker
    public boolean invokeItem(MenuItemImpl r2) {
        return this.mMenu.performItemAction(r2, 0);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView r1, View r2, int r3, long r4) {
        invokeItem((MenuItemImpl) getAdapter().getItem(r3));
    }
}
