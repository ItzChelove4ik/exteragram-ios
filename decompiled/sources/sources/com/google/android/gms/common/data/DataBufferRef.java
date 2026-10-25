package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public abstract class DataBufferRef {
    protected final DataHolder mDataHolder;
    protected int mDataRow;
    private int zaa;

    public DataBufferRef(DataHolder r1, int r2) {
        this.mDataHolder = (DataHolder) Preconditions.checkNotNull(r1);
        zaa(r2);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof DataBufferRef) == false) goto L12;
        DataBufferRef r42 = (DataBufferRef) r4;
        if (Objects.equal(Integer.valueOf(r42.mDataRow), Integer.valueOf(this.mDataRow)) == false) goto L12;
        if (Objects.equal(Integer.valueOf(r42.zaa), Integer.valueOf(this.zaa)) == false) goto L12;
        if (r42.mDataHolder != this.mDataHolder) goto L12;
        return true;
    L12:
        return false;
    }

    public byte[] getByteArray(String r3) {
        int r0 = this.mDataRow;
        int r1 = this.zaa;
        return this.mDataHolder.getByteArray(r3, r0, r1);
    }

    public int getInteger(String r3) {
        int r0 = this.mDataRow;
        int r1 = this.zaa;
        return this.mDataHolder.getInteger(r3, r0, r1);
    }

    public String getString(String r3) {
        int r0 = this.mDataRow;
        int r1 = this.zaa;
        return this.mDataHolder.getString(r3, r0, r1);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.mDataRow), Integer.valueOf(this.zaa), this.mDataHolder});
    }

    public final void zaa(int r3) {
        boolean r0 = false;
        if (r3 >= 0) goto L5;
    L7:
        Preconditions.checkState(r0);
        this.mDataRow = r3;
        this.zaa = this.mDataHolder.getWindowIndex(r3);
        return;
    L5:
        if (r3 >= this.mDataHolder.getCount()) goto L7;
        r0 = true;
        goto L7
    }
}
