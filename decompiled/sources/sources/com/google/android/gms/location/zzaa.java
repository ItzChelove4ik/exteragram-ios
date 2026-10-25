package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzaa implements Parcelable.Creator {
    public zzaa() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r6) {
        int r5 = SafeParcelReader.validateObjectHeader(r6);
        boolean r0 = false;
        int r1 = 1000;
    L4:
        if (r6.dataPosition() >= r5) goto L12;
        int r2 = SafeParcelReader.readHeader(r6);
        int r3 = SafeParcelReader.getFieldId(r2);
        if (r3 != 4) goto L8;
        r1 = SafeParcelReader.readInt(r6, r2);
        goto L4
    L8:
        if (r3 != 6) goto L9;
        r0 = SafeParcelReader.readBoolean(r6, r2);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r2);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r5);
        return new LocationAvailability(r1, r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LocationAvailability[r1];
    }
}
