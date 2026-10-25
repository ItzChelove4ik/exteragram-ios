package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class NavigationMenuView extends RecyclerView implements MenuView {
    public NavigationMenuView(Context r2) {
        this(r2, null);
    }

    public NavigationMenuView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public NavigationMenuView(Context r2, AttributeSet r3, int r4) {
        super(r2, r3, r4);
        setLayoutManager(new LinearLayoutManager(r2, 1, false));
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder r1) {
    }
}
