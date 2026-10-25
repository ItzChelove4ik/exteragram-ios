package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzv implements Parcelable.Creator<zzu> {
    public zzv() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzu createFromParcel(Parcel r5) {
        int r4 = SafeParcelReader.validateObjectHeader(r5);
        boolean r0 = false;
    L4:
        if (r5.dataPosition() >= r4) goto L9;
        int r1 = SafeParcelReader.readHeader(r5);
        if (SafeParcelReader.getFieldId(r1) != 1) goto L7;
        r0 = SafeParcelReader.readBoolean(r5, r1);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r5, r1);
        goto L4
    L9:
        SafeParcelReader.ensureAtEnd(r5, r4);
        return new zzu(r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzu[] newArray(int r1) {
        return new zzu[r1];
    }
}
