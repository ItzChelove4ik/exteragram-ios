package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzah implements Parcelable.Creator {
    public zzah() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r6) {
        int r5 = SafeParcelReader.validateObjectHeader(r6);
        Status r0 = null;
        LocationSettingsStates r1 = null;
    L4:
        if (r6.dataPosition() >= r5) goto L12;
        int r2 = SafeParcelReader.readHeader(r6);
        int r3 = SafeParcelReader.getFieldId(r2);
        if (r3 != 1) goto L8;
        r0 = (Status) SafeParcelReader.createParcelable(r6, r2, Status.CREATOR);
        goto L4
    L8:
        if (r3 != 2) goto L9;
        r1 = (LocationSettingsStates) SafeParcelReader.createParcelable(r6, r2, LocationSettingsStates.CREATOR);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r2);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r5);
        return new LocationSettingsResult(r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LocationSettingsResult[r1];
    }
}
