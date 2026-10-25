package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zaa;
    private ArrayList zab;

    public EntityBuffer(DataHolder r1) {
        super(r1);
        this.zaa = false;
    }

    private final void zab() {
        monitor-enter(this);
    L15:
        th = move-exception;
        throw th;
    L5:
        if (this.zaa == true) goto L21;
        int r3 = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getCount();     // Catch: Throwable -> L15
        ArrayList r4 = new ArrayList();     // Catch: Throwable -> L15
        this.zab = r4;     // Catch: Throwable -> L15
        if (r3 <= 0) goto L20;
        r4.add(0);     // Catch: Throwable -> L15
        String r42 = getPrimaryDataMarkerColumn();     // Catch: Throwable -> L15
        String r6 = this.mDataHolder.getString(r42, 0, this.mDataHolder.getWindowIndex(0));     // Catch: Throwable -> L15
        int r7 = 1;
    L9:
        if (r7 >= r3) goto L20;
        int r8 = this.mDataHolder.getWindowIndex(r7);     // Catch: Throwable -> L15
        String r9 = this.mDataHolder.getString(r42, r7, r8);     // Catch: Throwable -> L15
        if (r9 == null) goto L18;
        if (r9.equals(r6) == true) goto L17;
        this.zab.add(Integer.valueOf(r7));     // Catch: Throwable -> L15
        r6 = r9;
    L17:
        r7 = r7 + 1;     // Catch: Throwable -> L15
        goto L9
    L18:
        StringBuilder r62 = new StringBuilder((((String.valueOf(r42).length() + 42) + String.valueOf(r7).length()) + 14) + String.valueOf(r8).length());     // Catch: Throwable -> L15
        r62.append("Missing value for markerColumn: ");     // Catch: Throwable -> L15
        r62.append(r42);     // Catch: Throwable -> L15
        r62.append(", at row: ");     // Catch: Throwable -> L15
        r62.append(r7);     // Catch: Throwable -> L15
        r62.append(", for window: ");     // Catch: Throwable -> L15
        r62.append(r8);     // Catch: Throwable -> L15
        throw new NullPointerException(r62.toString());     // Catch: Throwable -> L15
    L20:
        this.zaa = true;     // Catch: Throwable -> L15
    L21:
        monitor-exit(this);     // Catch: Throwable -> L15
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public final T get(int r7) {
        zab();
        int r0 = zaa(r7);
        int r1 = 0;
        if (r7 < 0) goto L22;
        if (r7 == this.zab.size()) goto L22;
        if (r7 != (this.zab.size() - 1)) goto L11;
        int r2 = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getCount();
        int r3 = ((Integer) this.zab.get(r7)).intValue();
    L10:
        int r22 = r2 - r3;
        if (r22 != 1) goto L20;
        int r72 = zaa(r7);
        int r23 = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getWindowIndex(r72);
        String r4 = getChildDataMarkerColumn();
        if (r4 != null) goto L17;
    L19:
        r1 = 1;
        goto L22
    L17:
        if (this.mDataHolder.getString(r4, r72, r23) != null) goto L19;
    L20:
        r1 = r22;
        goto L22
    L11:
        r2 = ((Integer) this.zab.get(r7 + 1)).intValue();
        r3 = ((Integer) this.zab.get(r7)).intValue();
    L22:
        return getEntry(r0, r1);
    }

    public String getChildDataMarkerColumn() {
        return null;
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        zab();
        return this.zab.size();
    }

    public abstract T getEntry(int r1, int r2);

    public abstract String getPrimaryDataMarkerColumn();

    public final int zaa(int r3) {
        if (r3 >= 0) goto L4;
    L7:
        StringBuilder r1 = new StringBuilder(String.valueOf(r3).length() + 42);
        r1.append("Position ");
        r1.append(r3);
        r1.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(r1.toString());
    L4:
        if (r3 >= this.zab.size()) goto L7;
        return ((Integer) this.zab.get(r3)).intValue();
    }
}
