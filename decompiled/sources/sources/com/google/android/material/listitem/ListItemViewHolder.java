package com.google.android.material.listitem;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ListItemViewHolder extends RecyclerView.ViewHolder {
    private final ListItemLayout listItemLayout;

    public ListItemViewHolder(View r1) {
        super(r1);
        this.listItemLayout = findListItemLayout();
    }

    private ListItemLayout findListItemLayout() {
        View r0 = this.itemView;
        if ((r0 instanceof ListItemLayout) == false) goto L7;
        return (ListItemLayout) r0;
    L7:
        if ((r0 instanceof ViewGroup) == false) goto L15;
        int r02 = ((ViewGroup) r0).getChildCount();
        int r1 = 0;
    L9:
        if (r1 >= r02) goto L15;
        View r2 = ((ViewGroup) this.itemView).getChildAt(r1);
        if ((r2 instanceof ListItemLayout) == true) goto L13;
        r1 = r1 + 1;
        goto L9
    L13:
        return (ListItemLayout) r2;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("Didn't find ListItemLayout in root itemView or among itemView's children.");
        return null;
    }

    public void bind() {
        bind(getBindingAdapterPosition(), getBindingAdapter().getItemCount());
    }

    public void bind(int r1) {
        this.listItemLayout.updateAppearance(r1);
    }

    public void bind(int r2, int r3) {
        if (r2 == (-1)) goto L8;
        if (r3 == 0) goto L9;
        this.listItemLayout.updateAppearance(r2, r3);
        return;
    L9:
        return;
    }
}
