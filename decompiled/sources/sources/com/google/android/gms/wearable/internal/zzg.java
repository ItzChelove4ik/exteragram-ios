package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzg implements Parcelable.Creator {
    public zzg() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r5) {
        int r4 = SafeParcelReader.validateObjectHeader(r5);
        int r0 = 0;
    L4:
        if (r5.dataPosition() >= r4) goto L9;
        int r1 = SafeParcelReader.readHeader(r5);
        if (SafeParcelReader.getFieldId(r1) != 2) goto L7;
        r0 = SafeParcelReader.readInt(r5, r1);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r5, r1);
        goto L4
    L9:
        SafeParcelReader.ensureAtEnd(r5, r4);
        return new zzf(r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzf[r1];
    }
}
