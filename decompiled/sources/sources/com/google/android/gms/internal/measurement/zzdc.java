package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes.dex */
public final class zzdc implements Parcelable.Creator {
    public zzdc() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r13) {
        int r12 = SafeParcelReader.validateObjectHeader(r13);
        Bundle r10 = null;
        String r11 = null;
        boolean r9 = false;
        long r5 = 0;
        long r7 = 0;
    L4:
        if (r13.dataPosition() >= r12) goto L21;
        int r0 = SafeParcelReader.readHeader(r13);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r5 = SafeParcelReader.readLong(r13, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r7 = SafeParcelReader.readLong(r13, r0);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r9 = SafeParcelReader.readBoolean(r13, r0);
        goto L4
    L12:
        if (r1 != 7) goto L14;
        r10 = SafeParcelReader.createBundle(r13, r0);
        goto L4
    L14:
        if (r1 != 8) goto L15;
        r11 = SafeParcelReader.createString(r13, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r13, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r13, r12);
        return new zzdb(r5, r7, r9, r10, r11);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzdb[r1];
    }
}
