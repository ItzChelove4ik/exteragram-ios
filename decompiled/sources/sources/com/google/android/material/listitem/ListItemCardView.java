package com.google.android.material.listitem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes5.dex */
public class ListItemCardView extends MaterialCardView implements SwipeableListItem {
    private static final int[] SWIPED_STATE_SET = null;
    private boolean isSwiped;
    private final LinkedHashSet<SwipeCallback> swipeCallbacks;
    private boolean swipeEnabled;
    private final int swipeMaxOvershoot;

    public static abstract class SwipeCallback {
        public SwipeCallback() {
        }

        public abstract void onSwipe(int r1);

        public abstract <T extends View & RevealableListItem> void onSwipeStateChanged(int r1, T r2, int r3);
    }

    static {
        SWIPED_STATE_SET = new int[]{R.attr.state_swiped};
    }

    public ListItemCardView(Context r2) {
        this(r2, null);
    }

    public ListItemCardView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.listItemCardViewStyle);
    }

    public ListItemCardView(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, R.style.Widget_Material3_ListItemCardView);
    }

    public ListItemCardView(Context r8, AttributeSet r9, int r10, int r11) {
        super(MaterialThemeOverlay.wrap(r8, r9, r10, r11), r9, r10);
        this.isSwiped = false;
        this.swipeCallbacks = new LinkedHashSet();
        Context r1 = getContext();
        this.swipeMaxOvershoot = getResources().getDimensionPixelSize(R.dimen.m3_list_max_swipe_overshoot);
        TintTypedArray r82 = ThemeEnforcement.obtainTintedStyledAttributes(r1, r9, R.styleable.ListItemCardView, r10, r11, new int[0]);
        this.swipeEnabled = r82.getBoolean(R.styleable.ListItemCardView_swipeEnabled, true);
        r82.recycle();
    }

    public void addSwipeCallback(SwipeCallback r1) {
        this.swipeCallbacks.add(r1);
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public int getSwipeMaxOvershoot() {
        return this.swipeMaxOvershoot;
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public boolean isSwipeEnabled() {
        return this.swipeEnabled;
    }

    @Override // com.google.android.material.card.MaterialCardView, android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int r1) {
        int[] r12 = super.onCreateDrawableState(r1 + 1);
        if (this.isSwiped == false) goto L5;
        View.mergeDrawableStates(r12, SWIPED_STATE_SET);
    L5:
        return r12;
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public void onSwipe(int r2) {
        Iterator<SwipeCallback> r1 = this.swipeCallbacks.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onSwipe(r2);
        goto L4
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public <T extends View & RevealableListItem> void onSwipeStateChanged(int r2, T r3, int r4) {
        if (r2 == 3) goto L5;
        boolean r0 = true;
    L6:
        this.isSwiped = r0;
        refreshDrawableState();
        Iterator<SwipeCallback> r1 = this.swipeCallbacks.iterator();
    L8:
        if (r1.hasNext() == false) goto L10;
        r1.next().onSwipeStateChanged(r2, r3, r4);
        goto L8
    L10:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void removeSwipeCallback(SwipeCallback r1) {
        this.swipeCallbacks.remove(r1);
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public void setSwipeEnabled(boolean r1) {
        this.swipeEnabled = r1;
    }
}
