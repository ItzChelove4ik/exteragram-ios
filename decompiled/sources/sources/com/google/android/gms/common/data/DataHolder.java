package com.google.android.gms.common.data;

import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;

@KeepName
/* loaded from: classes4.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = null;
    private static final Builder zak = null;
    final int zaa;
    Bundle zab;
    int[] zac;
    int zad;
    boolean zae;
    private final String[] zaf;
    private final CursorWindow[] zag;
    private final int zah;
    private final Bundle zai;
    private boolean zaj;

    public static class Builder {
        private final String[] zaa;
        private final ArrayList zab;
        private final HashMap zac;

        public /* synthetic */ Builder(String[] r1, String r2, byte[] r3) {
            this.zaa = (String[]) Preconditions.checkNotNull(r1);
            this.zab = new ArrayList();
            this.zac = new HashMap();
        }
    }

    static {
        CREATOR = new zad();
        zak = new zab(new String[0], null);
    }

    public DataHolder(int r2, String[] r3, CursorWindow[] r4, int r5, Bundle r6) {
        this.zae = false;
        this.zaj = true;
        this.zaa = r2;
        this.zaf = r3;
        this.zag = r4;
        this.zah = r5;
        this.zai = r6;
    }

    private final void zaf(String r2, int r3) {
        Bundle r0 = this.zab;
        if (r0 != null) goto L5;
    L16:
        f$$ExternalSyntheticBUOutline1.m("No such column: ".concat(String.valueOf(r2)));
        return;
    L5:
        if (r0.containsKey(r2) == false) goto L16;
        if (isClosed() == true) goto L14;
        if (r3 < 0) goto L13;
        if (r3 >= this.zad) goto L13;
        return;
    L13:
        throw new CursorIndexOutOfBoundsException(r3, this.zad);
    L14:
        f$$ExternalSyntheticBUOutline1.m("Buffer is closed.");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L4:
        if (this.zae == true) goto L11;
        this.zae = true;     // Catch: Throwable -> L9
        int r0 = 0;
    L6:
        CursorWindow[] r1 = this.zag;     // Catch: Throwable -> L9
        if (r0 >= r1.length) goto L11;
        r1[r0].close();     // Catch: Throwable -> L9
        r0 = r0 + 1;     // Catch: Throwable -> L9
    L11:
        monitor-exit(this);     // Catch: Throwable -> L9
    }

    public final void finalize() {
    L11:
        th = move-exception;
        super.finalize();
        throw th;
    L4:
        if (this.zaj == true) goto L6;
    L13:
        super.finalize();
        return;
    L6:
        if (this.zag.length <= 0) goto L13;
        if (isClosed() == true) goto L13;
        close();     // Catch: Throwable -> L11
        String r3 = toString();     // Catch: Throwable -> L11
        StringBuilder r5 = new StringBuilder(String.valueOf(r3).length() + 178);     // Catch: Throwable -> L11
        r5.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");     // Catch: Throwable -> L11
        r5.append(r3);     // Catch: Throwable -> L11
        r5.append(")");     // Catch: Throwable -> L11
        Log.e("DataBuffer", r5.toString());     // Catch: Throwable -> L11
        goto L13
    }

    public byte[] getByteArray(String r2, int r3, int r4) {
        zaf(r2, r3);
        return this.zag[r4].getBlob(r3, this.zab.getInt(r2));
    }

    public int getCount() {
        return this.zad;
    }

    public int getInteger(String r2, int r3, int r4) {
        zaf(r2, r3);
        return this.zag[r4].getInt(r3, this.zab.getInt(r2));
    }

    public Bundle getMetadata() {
        return this.zai;
    }

    public int getStatusCode() {
        return this.zah;
    }

    public String getString(String r2, int r3, int r4) {
        zaf(r2, r3);
        return this.zag[r4].getString(r3, this.zab.getInt(r2));
    }

    public int getWindowIndex(int r4) {
        int r0 = 0;
        if (r4 >= 0) goto L5;
    L7:
        boolean r1 = false;
    L8:
        Preconditions.checkState(r1);
    L9:
        int[] r12 = this.zac;
        int r2 = r12.length;
        if (r0 >= r2) goto L15;
        if (r4 < r12[r0]) goto L13;
        r0 = r0 + 1;
        goto L9
    L13:
        r0 = r0 - 1;
    L15:
        if (r0 == r2) goto L17;
        return r0;
    L17:
        return r0 - 1;
    L5:
        if (r4 >= this.zad) goto L7;
        r1 = true;
        goto L8
    }

    public boolean isClosed() {
        monitor-enter(this);
        boolean r0 = this.zae;     // Catch: Throwable -> L6
        monitor-exit(this);     // Catch: Throwable -> L6
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r6, int r7) {
        String[] r0 = this.zaf;
        int r1 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeStringArray(r6, 1, r0, false);
        SafeParcelWriter.writeTypedArray(r6, 2, this.zag, r7, false);
        SafeParcelWriter.writeInt(r6, 3, getStatusCode());
        SafeParcelWriter.writeBundle(r6, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(r6, 1000, this.zaa);
        SafeParcelWriter.finishObjectHeader(r6, r1);
        if ((r7 & 1) == 0) goto L6;
        close();
        return;
    }

    public final void zaa() {
        this.zab = new Bundle();
        int r0 = 0;
        int r1 = 0;
    L3:
        String[] r2 = this.zaf;
        if (r1 >= r2.length) goto L6;
        this.zab.putInt(r2[r1], r1);
        r1 = r1 + 1;
        goto L3
    L6:
        CursorWindow[] r12 = this.zag;
        this.zac = new int[r12.length];
        int r22 = 0;
    L8:
        if (r0 >= r12.length) goto L10;
        this.zac[r0] = r22;
        r22 = r22 + (r12[r0].getNumRows() - (r22 - r12[r0].getStartPosition()));
        r0 = r0 + 1;
        goto L8
    L10:
        this.zad = r22;
    }
}
