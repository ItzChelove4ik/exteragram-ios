package org.telegram.ui.Components.ListView;

import androidx.recyclerview.widget.DiffUtil;
import java.util.ArrayList;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes3.dex */
public abstract class AdapterWithDiffUtils extends RecyclerListView.SelectionAdapter {
    DiffUtilsCallback callback;

    public class DiffUtilsCallback extends DiffUtil.Callback {
        ArrayList<? extends Item> newItems;
        ArrayList<? extends Item> oldItems;
        final /* synthetic */ AdapterWithDiffUtils this$0;

        private DiffUtilsCallback(AdapterWithDiffUtils r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ DiffUtilsCallback(AdapterWithDiffUtils r1, AdapterWithDiffUtilsIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r3, int r4) {
            if (this.oldItems.get(r3).compareContents(this.newItems.get(r4)) == true) goto L7;
            return false;
        L7:
            return this.this$0.areVisualsTheSame(r3, r4, this.oldItems, this.newItems);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r2, int r3) {
            return this.oldItems.get(r2).compare(this.newItems.get(r3));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return this.newItems.size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.oldItems.size();
        }

        public void setItems(ArrayList<? extends Item> r1, ArrayList<? extends Item> r2) {
            this.oldItems = r1;
            this.newItems = r2;
        }
    }

    public static abstract class Item {
        public boolean selectable;
        public int viewType;

        public Item(int r1, boolean r2) {
            this.viewType = r1;
            this.selectable = r2;
        }

        public boolean compare(Item r3) {
            if (this.viewType == r3.viewType) goto L7;
            return false;
        L7:
            return equals(r3);
        }

        public boolean compareContents(Item r3) {
            if (this.viewType == r3.viewType) goto L7;
            return false;
        L7:
            return contentsEquals(r3);
        }

        public boolean contentsEquals(Item r1) {
            return false;
        }
    }

    public AdapterWithDiffUtils() {
        this.callback = new DiffUtilsCallback(this, null);
    }

    public boolean areVisualsTheSame(int r1, int r2, ArrayList<? extends Item> r3, ArrayList<? extends Item> r4) {
        return true;
    }

    public void setItems(ArrayList<? extends Item> r2, ArrayList<? extends Item> r3) {
        if (r3 != null) goto L4;
        r3 = new ArrayList();
    L4:
        this.callback.setItems(r2, r3);
        DiffUtil.calculateDiff(this.callback).dispatchUpdatesTo(this);
    }
}
