package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zad implements Parcelable.Creator {
    public zad() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        String[] r4 = null;
        CursorWindow[] r5 = null;
        Bundle r7 = null;
        int r3 = 0;
        int r6 = 0;
    L4:
        if (r9.dataPosition() >= r8) goto L21;
        int r0 = SafeParcelReader.readHeader(r9);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r4 = SafeParcelReader.createStringArray(r9, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r5 = (CursorWindow[]) SafeParcelReader.createTypedArray(r9, r0, CursorWindow.CREATOR);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r6 = SafeParcelReader.readInt(r9, r0);
        goto L4
    L12:
        if (r1 != 4) goto L14;
        r7 = SafeParcelReader.createBundle(r9, r0);
        goto L4
    L14:
        if (r1 != 1000) goto L15;
        r3 = SafeParcelReader.readInt(r9, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r9, r8);
        DataHolder r2 = new DataHolder(r3, r4, r5, r6, r7);
        r2.zaa();
        return r2;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new DataHolder[r1];
    }
}
