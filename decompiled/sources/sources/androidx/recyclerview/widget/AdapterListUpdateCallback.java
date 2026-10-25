package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public final class AdapterListUpdateCallback implements ListUpdateCallback {
    private final RecyclerView.Adapter mAdapter;

    public AdapterListUpdateCallback(RecyclerView.Adapter r1) {
        this.mAdapter = r1;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int r1, int r2, Object r3) {
        this.mAdapter.notifyItemRangeChanged(r1, r2, r3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int r1, int r2) {
        this.mAdapter.notifyItemRangeInserted(r1, r2);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int r1, int r2) {
        this.mAdapter.notifyItemMoved(r1, r2);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int r1, int r2) {
        this.mAdapter.notifyItemRangeRemoved(r1, r2);
    }
}
