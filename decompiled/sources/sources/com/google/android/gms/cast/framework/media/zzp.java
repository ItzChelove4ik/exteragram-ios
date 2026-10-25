package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.MediaQueue;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzp extends MediaQueue.Callback {
    final /* synthetic */ MediaQueueRecyclerViewAdapter zza;

    public /* synthetic */ zzp(MediaQueueRecyclerViewAdapter r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsInsertedInRange(int r1, int r2) {
        this.zza.notifyItemRangeInserted(r1, r2);
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsReloaded() {
        this.zza.notifyDataSetChanged();
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsRemovedAtIndexes(int[] r5) {
        int r0 = r5.length;
        if (r0 > 1) goto L8;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L7;
        int r2 = r5[r1];
        this.zza.notifyItemRemoved(r2);
        r1 = r1 + 1;
        goto L5
    L7:
        return;
    L8:
        this.zza.notifyDataSetChanged();
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsReorderedAtIndexes(List<Integer> r1, int r2) {
        this.zza.notifyDataSetChanged();
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void itemsUpdatedAtIndexes(int[] r5) {
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        int r2 = r5[r1];
        this.zza.notifyItemChanged(r2);
        r1 = r1 + 1;
        goto L3
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void mediaQueueChanged() {
    }

    @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
    public final void mediaQueueWillChange() {
    }
}
