package org.telegram.ui.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class PaddedListAdapter extends RecyclerListView.SelectionAdapter {
    private final int PADDING_VIEW_TYPE;
    private int lastPadding;
    private RecyclerView.AdapterDataObserver mDataObserver;
    private Integer padding;
    public View paddingView;
    public boolean paddingViewAttached;
    private RecyclerListView.SelectionAdapter wrappedAdapter;

    /* renamed from: org.telegram.ui.Adapters.PaddedListAdapter$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ PaddedListAdapter this$0;

        public AnonymousClass1(PaddedListAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.this$0.paddingViewAttached = true;
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.this$0.paddingViewAttached = false;
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            int r32 = ((View) getParent()).getMeasuredHeight();
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(PaddedListAdapter.m5945$$Nest$mgetPadding(this.this$0, r32), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Adapters.PaddedListAdapter$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.AdapterDataObserver {
        final /* synthetic */ PaddedListAdapter this$0;

        public AnonymousClass2(PaddedListAdapter r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            super.onChanged();
            this.this$0.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int r1, int r2) {
            super.onItemRangeChanged(r1, r2);
            this.this$0.notifyItemRangeChanged(r1 + 1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int r1, int r2) {
            super.onItemRangeInserted(r1, r2);
            this.this$0.notifyItemRangeInserted(r1 + 1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int r1, int r2, int r3) {
            super.onItemRangeMoved(r1, r2, r3);
            this.this$0.notifyItemRangeChanged(r1 + 1, (r2 + 1) + r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int r1, int r2) {
            super.onItemRangeRemoved(r1, r2);
            this.this$0.notifyItemRangeRemoved(r1 + 1, r2);
        }
    }

    /* renamed from: -$$Nest$mgetPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5945$$Nest$mgetPadding(PaddedListAdapter r0, int r1) {
        return r0.getPadding(r1);
    }

    public PaddedListAdapter(RecyclerListView.SelectionAdapter r2) {
        this.PADDING_VIEW_TYPE = -983904;
        this.padding = null;
        this.paddingViewAttached = false;
        AnonymousClass2 r0 = new AnonymousClass2(this);
        this.mDataObserver = r0;
        this.wrappedAdapter = r2;
        r2.registerAdapterDataObserver(r0);
    }

    private int getPadding(int r1) {
        Integer r12 = this.padding;
        if (r12 == null) goto L6;
        int r13 = r12.intValue();
        this.lastPadding = r13;
        return r13;
    L6:
        this.lastPadding = 0;
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.wrappedAdapter.getItemCount() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r1) {
        if (r1 != 0) goto L6;
        return -983904;
    L6:
        return this.wrappedAdapter.getItemViewType(r1 - 1);
    }

    public int getPadding() {
        return this.lastPadding;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r2) {
        if (r2.getAdapterPosition() != 0) goto L7;
        return false;
    L7:
        return this.wrappedAdapter.isEnabled(r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        if (r2 <= 0) goto L5;
        this.wrappedAdapter.onBindViewHolder(r1, r2 - 1);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
        if (r3 != (-983904)) goto L7;
        AnonymousClass1 r0 = new AnonymousClass1(this, r2.getContext());
        this.paddingView = r0;
        return new RecyclerListView.Holder(r0);
    L7:
        return this.wrappedAdapter.onCreateViewHolder(r2, r3);
    }

    public void setPadding(int r1) {
        this.padding = Integer.valueOf(r1);
        View r0 = this.paddingView;
        if (r0 == null) goto L6;
        r0.requestLayout();
        return;
    }
}
