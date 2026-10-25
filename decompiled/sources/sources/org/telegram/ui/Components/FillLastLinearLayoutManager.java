package org.telegram.ui.Components;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes7.dex */
public class FillLastLinearLayoutManager extends LinearLayoutManager {
    private int additionalHeight;
    private boolean bind;
    private boolean canScrollVertically;
    boolean fixedLastItemHeight;
    private SparseArray<RecyclerView.ViewHolder> heights;
    private int lastItemHeight;
    private int listHeight;
    private RecyclerView listView;
    private int listWidth;
    private int minimumHeight;
    private boolean setMeassuredHeightToLastItem;
    private boolean skipFirstItem;

    public FillLastLinearLayoutManager(Context r1, int r2, RecyclerView r3) {
        super(r1);
        this.heights = new SparseArray();
        this.lastItemHeight = -1;
        this.bind = true;
        this.canScrollVertically = true;
        this.setMeassuredHeightToLastItem = true;
        this.listView = r3;
        this.additionalHeight = r2;
    }

    public FillLastLinearLayoutManager(Context r1, int r2, boolean r3, int r4, RecyclerView r5) {
        super(r1, r2, r3);
        this.heights = new SparseArray();
        this.lastItemHeight = -1;
        this.bind = true;
        this.canScrollVertically = true;
        this.setMeassuredHeightToLastItem = true;
        this.listView = r5;
        this.additionalHeight = r4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void calcLastItemHeight() {
        if (this.listHeight <= 0) goto L40;
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        if (r0 != null) goto L8;
        return;
    L8:
        int r1 = r0.getItemCount() - 1;
        int r3 = 0;
        int r4 = 0;
        int r2 = this.skipFirstItem;
    L9:
        if (r2 >= r1) goto L29;
        int r5 = r0.getItemViewType(r2);
        RecyclerView.ViewHolder r6 = this.heights.get(r5, null);
        if (r6 != null) goto L16;
        r6 = r0.createViewHolder(this.listView, r5);
        this.heights.put(r5, r6);
        if (r6.itemView.getLayoutParams() != null) goto L16;
        r6.itemView.setLayoutParams(generateDefaultLayoutParams());
    L16:
        if (this.bind == false) goto L18;
        r0.onBindViewHolder(r6, r2);
    L18:
        RecyclerView.LayoutParams r52 = (RecyclerView.LayoutParams) r6.itemView.getLayoutParams();
        r6.itemView.measure(RecyclerView.LayoutManager.getChildMeasureSpec(this.listWidth, getWidthMode(), ((getPaddingLeft() + getPaddingRight()) + ((ViewGroup.MarginLayoutParams) r52).leftMargin) + ((ViewGroup.MarginLayoutParams) r52).rightMargin, ((ViewGroup.MarginLayoutParams) r52).width, canScrollHorizontally()), RecyclerView.LayoutManager.getChildMeasureSpec(this.listHeight, getHeightMode(), ((getPaddingTop() + getPaddingBottom()) + ((ViewGroup.MarginLayoutParams) r52).topMargin) + ((ViewGroup.MarginLayoutParams) r52).bottomMargin, ((ViewGroup.MarginLayoutParams) r52).height, canScrollVertically()));
        r3 = r3 + r6.itemView.getMeasuredHeight();
        if (r2 != 0) goto L21;
        r4 = r6.itemView.getMeasuredHeight();
    L21:
        boolean r53 = this.fixedLastItemHeight;
        int r62 = this.listHeight;
        if (r53 == true) goto L24;
        if (r3 >= r62) goto L29;
    L28:
        r2 = r2 + 1;
        goto L9
    L24:
        if (r3 < (r62 + r4)) goto L28;
    L29:
        boolean r02 = this.fixedLastItemHeight;
        int r12 = this.minimumHeight;
        if (r02 == false) goto L33;
        this.lastItemHeight = Math.max(r12, r4 + (((this.listHeight - r3) - this.additionalHeight) - this.listView.getPaddingBottom()));
        return;
    L33:
        this.lastItemHeight = Math.max(r12, ((this.listHeight - r3) - this.additionalHeight) - this.listView.getPaddingBottom());
        return;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.canScrollVertically;
    }

    public int getLastItemHeight() {
        return this.lastItemHeight;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void measureChildWithMargins(View r2, int r3, int r4) {
        if (this.setMeassuredHeightToLastItem == true) goto L5;
    L7:
        super.measureChildWithMargins(r2, 0, 0);
        return;
    L5:
        if (this.listView.findContainingViewHolder(r2).getAdapterPosition() != (getItemCount() - 1)) goto L7;
        ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) r2.getLayoutParams())).height = Math.max(this.lastItemHeight, 0);
        goto L7
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter r2, RecyclerView.Adapter r3) {
        this.heights.clear();
        calcLastItemHeight();
        super.onAdapterChanged(r2, r3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView r1, int r2, int r3) {
        super.onItemsAdded(r1, r2, r3);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView r2) {
        this.heights.clear();
        calcLastItemHeight();
        super.onItemsChanged(r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView r1, int r2, int r3, int r4) {
        super.onItemsMoved(r1, r2, r3, r4);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView r1, int r2, int r3) {
        super.onItemsRemoved(r1, r2, r3);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView r1, int r2, int r3) {
        super.onItemsUpdated(r1, r2, r3);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView r1, int r2, int r3, Object r4) {
        super.onItemsUpdated(r1, r2, r3, r4);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler r3, RecyclerView.State r4, int r5, int r6) {
        int r0 = this.listHeight;
        this.listWidth = View.MeasureSpec.getSize(r5);
        int r1 = View.MeasureSpec.getSize(r6);
        this.listHeight = r1;
        if (r0 == r1) goto L5;
        calcLastItemHeight();
    L5:
        super.onMeasure(r3, r4, r5, r6);
    }

    public void setAdditionalHeight(int r1) {
        this.additionalHeight = r1;
        calcLastItemHeight();
    }

    public void setBind(boolean r1) {
        this.bind = r1;
    }

    public void setFixedLastItemHeight() {
        this.fixedLastItemHeight = true;
    }

    public void setMinimumLastViewHeight(int r1) {
        this.minimumHeight = r1;
    }

    public void setSkipFirstItem() {
        this.skipFirstItem = true;
    }
}
