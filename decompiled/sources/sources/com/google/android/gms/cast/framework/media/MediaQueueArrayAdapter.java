package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.MediaQueue;

/* loaded from: classes4.dex */
public abstract class MediaQueueArrayAdapter extends ArrayAdapter<MediaQueueItem> {
    private final MediaQueue zza;
    private final MediaQueue.Callback zzb;

    public MediaQueueArrayAdapter(MediaQueue r1, Context r2, int r3) {
        super(r2, r3);
        this.zza = r1;
        zzo r22 = new zzo(this, null);
        this.zzb = r22;
        r1.registerCallback(r22);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    public void dispose() {
        this.zza.unregisterCallback(this.zzb);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.zza.getItemCount();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public MediaQueueItem getItem(int r1) {
        return this.zza.getItemAtIndex(r1);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final /* bridge */ /* synthetic */ Object getItem(int r1) {
        return getItem(r1);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int r1) {
        return this.zza.itemIdAtIndex(r1);
    }

    public MediaQueue getMediaQueue() {
        return this.zza;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean isEmpty() {
        if (this.zza.getItemCount() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int r2) {
        if (this.zza.getItemAtIndex(r2, false) == null) goto L6;
        return true;
    L6:
        return false;
    }
}
