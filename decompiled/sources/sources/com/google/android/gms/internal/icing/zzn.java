package com.google.android.gms.internal.icing;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzn implements Parcelable.Creator<zzm> {
    public zzn() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzm createFromParcel(Parcel r6) {
        int r5 = SafeParcelReader.validateObjectHeader(r6);
        int r0 = 0;
        Bundle r1 = null;
    L4:
        if (r6.dataPosition() >= r5) goto L12;
        int r2 = SafeParcelReader.readHeader(r6);
        int r3 = SafeParcelReader.getFieldId(r2);
        if (r3 != 1) goto L8;
        r0 = SafeParcelReader.readInt(r6, r2);
        goto L4
    L8:
        if (r3 != 2) goto L9;
        r1 = SafeParcelReader.createBundle(r6, r2);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r2);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r5);
        return new zzm(r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzm[] newArray(int r1) {
        return new zzm[r1];
    }
}
