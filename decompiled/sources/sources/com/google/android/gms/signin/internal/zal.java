package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zay;

/* loaded from: classes5.dex */
public final class zal implements Parcelable.Creator {
    public zal() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        ConnectionResult r0 = null;
        int r2 = 0;
        zay r1 = null;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 1) goto L8;
        r2 = SafeParcelReader.readInt(r7, r3);
        goto L4
    L8:
        if (r4 != 2) goto L10;
        r0 = (ConnectionResult) SafeParcelReader.createParcelable(r7, r3, ConnectionResult.CREATOR);
        goto L4
    L10:
        if (r4 != 3) goto L11;
        r1 = (zay) SafeParcelReader.createParcelable(r7, r3, zay.CREATOR);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new zak(r2, r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zak[r1];
    }
}
