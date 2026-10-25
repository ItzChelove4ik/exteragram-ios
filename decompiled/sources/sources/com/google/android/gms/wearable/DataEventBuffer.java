package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.wearable.internal.zzdd;

/* loaded from: classes5.dex */
public class DataEventBuffer extends EntityBuffer<Object> implements Result {
    private final Status zza;

    public DataEventBuffer(DataHolder r2) {
        super(r2);
        this.zza = new Status(r2.getStatusCode());
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    public final /* bridge */ /* synthetic */ Object getEntry(int r2, int r3) {
        return new zzdd(this.mDataHolder, r2, r3);
    }

    @Override // com.google.android.gms.common.data.EntityBuffer
    public final String getPrimaryDataMarkerColumn() {
        return "path";
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zza;
    }
}
