package com.google.android.gms.cast.framework.media;

import android.util.LruCache;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzk extends LruCache {
    final /* synthetic */ MediaQueue zza;

    public zzk(MediaQueue r1, int r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // android.util.LruCache
    public final /* bridge */ /* synthetic */ void entryRemoved(boolean r1, Object r2, Object r3, Object r4) {
        Integer r22 = (Integer) r2;
        MediaQueueItem r32 = (MediaQueueItem) r3;
        MediaQueueItem r42 = (MediaQueueItem) r4;
        if (r1 == true) goto L5;
        return;
    L5:
        List r0 = this.zza.zze;
        Preconditions.checkNotNull(r0);
        r0.add(r22);
    }
}
