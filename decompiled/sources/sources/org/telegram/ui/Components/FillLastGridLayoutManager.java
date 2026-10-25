package org.telegram.ui.Components;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes7.dex */
public class FillLastGridLayoutManager extends GridLayoutManager {
    private int additionalHeight;
    private boolean bind;
    private boolean canScrollVertically;
    private SparseArray<RecyclerView.ViewHolder> heights;
    protected int lastItemHeight;
    private int listHeight;
    private RecyclerView listView;
    private int listWidth;

    public FillLastGridLayoutManager(Context r1, int r2, int r3, RecyclerView r4) {
        super(r1, r2);
        this.heights = new SparseArray();
        this.lastItemHeight = -1;
        this.bind = true;
        this.canScrollVertically = true;
        this.listView = r4;
        this.additionalHeight = r3;
    }

    public FillLastGridLayoutManager(Context r1, int r2, int r3, boolean r4, int r5, RecyclerView r6) {
        super(r1, r2, r3, r4);
        this.heights = new SparseArray();
        this.lastItemHeight = -1;
        this.bind = true;
        this.canScrollVertically = true;
        this.listView = r6;
        this.additionalHeight = r5;
    }

    public void calcLastItemHeight() {
        if (this.listHeight > 0) goto L5;
        return;
    L5:
        if (shouldCalcLastItemHeight() == false) goto L38;
        RecyclerView.Adapter r1 = this.listView.getAdapter();
        if (r1 == null) goto L39;
        int r2 = getSpanCount();
        boolean r4 = true;
        int r3 = r1.getItemCount() - 1;
        GridLayoutManager.SpanSizeLookup r5 = getSpanSizeLookup();
        boolean r9 = true;
        int r7 = 0;
        int r8 = 0;
        int r10 = 0;
    L11:
        if (r7 >= r3) goto L31;
        int r11 = r5.getSpanSize(r7);
        r8 = r8 + r11;
        if (r11 == r2) goto L15;
        if (r8 > r2) goto L15;
    L16:
        if (r9 == false) goto L30;
        int r92 = r1.getItemViewType(r7);
        RecyclerView.ViewHolder r112 = this.heights.get(r92, null);
        if (r112 != null) goto L24;
        r112 = r1.createViewHolder(this.listView, r92);
        this.heights.put(r92, r112);
        if (r112.itemView.getLayoutParams() != null) goto L24;
        r112.itemView.setLayoutParams(generateDefaultLayoutParams());
    L24:
        if (this.bind == false) goto L26;
        r1.onBindViewHolder(r112, r7);
    L26:
        RecyclerView.LayoutParams r93 = (RecyclerView.LayoutParams) r112.itemView.getLayoutParams();
        r112.itemView.measure(RecyclerView.LayoutManager.getChildMeasureSpec(this.listWidth, getWidthMode(), ((getPaddingLeft() + getPaddingRight()) + ((ViewGroup.MarginLayoutParams) r93).leftMargin) + ((ViewGroup.MarginLayoutParams) r93).rightMargin, ((ViewGroup.MarginLayoutParams) r93).width, canScrollHorizontally()), RecyclerView.LayoutManager.getChildMeasureSpec(this.listHeight, getHeightMode(), ((getPaddingTop() + getPaddingBottom()) + ((ViewGroup.MarginLayoutParams) r93).topMargin) + ((ViewGroup.MarginLayoutParams) r93).bottomMargin, ((ViewGroup.MarginLayoutParams) r93).height, canScrollVertically()));
        r10 = r10 + r112.itemView.getMeasuredHeight();
        if (r10 >= ((this.listHeight - this.additionalHeight) - this.listView.getPaddingBottom())) goto L31;
        r9 = false;
    L30:
        r7 = r7 + 1;
        r4 = true;
    L15:
        r9 = r4;
        r8 = r11;
    L31:
        this.lastItemHeight = Math.max(0, ((this.listHeight - r10) - this.additionalHeight) - this.listView.getPaddingBottom());
        return;
    L39:
        return;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.canScrollVertically;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager
    public void measureChild(View r4, int r5, boolean r6) {
        if (this.listView.findContainingViewHolder(r4).getAdapterPosition() != (getItemCount() - 1)) goto L5;
        ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) r4.getLayoutParams())).height = Math.max(this.lastItemHeight, 0);
    L5:
        super.measureChild(r4, r5, r6);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter r2, RecyclerView.Adapter r3) {
        this.heights.clear();
        calcLastItemHeight();
        super.onAdapterChanged(r2, r3);
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView r1, int r2, int r3) {
        super.onItemsAdded(r1, r2, r3);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView r2) {
        this.heights.clear();
        calcLastItemHeight();
        super.onItemsChanged(r2);
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView r1, int r2, int r3, int r4) {
        super.onItemsMoved(r1, r2, r3, r4);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView r1, int r2, int r3) {
        super.onItemsRemoved(r1, r2, r3);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView r1, int r2, int r3) {
        super.onItemsUpdated(r1, r2, r3);
        calcLastItemHeight();
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
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

    public void setBind(boolean r1) {
        this.bind = r1;
    }

    public void setCanScrollVertically(boolean r1) {
        this.canScrollVertically = r1;
    }

    public boolean shouldCalcLastItemHeight() {
        return true;
    }
}
