package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItem;

/* loaded from: classes5.dex */
public final class zzdd extends DataBufferRef {
    private final int zza;

    public zzdd(DataHolder r1, int r2, int r3) {
        super(r1, r2);
        this.zza = r3;
    }

    public final DataItem getDataItem() {
        return new zzdk(this.mDataHolder, this.mDataRow, this.zza);
    }

    public final String toString() {
        if (getInteger("event_type") != 1) goto L6;
        String r0 = "changed";
    L10:
        return "DataEventRef{ type=" + r0 + ", dataitem=" + getDataItem().toString() + " }";
    L6:
        if (getInteger("event_type") != 2) goto L8;
        r0 = "deleted";
        goto L10
    L8:
        r0 = "unknown";
        goto L10
    }
}
