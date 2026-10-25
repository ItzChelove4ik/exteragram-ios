package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zaz implements Parcelable.Creator {
    public zaz() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        int r3 = 0;
        boolean r6 = false;
        boolean r7 = false;
        IBinder r4 = null;
        ConnectionResult r5 = null;
    L4:
        if (r9.dataPosition() >= r8) goto L21;
        int r0 = SafeParcelReader.readHeader(r9);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r3 = SafeParcelReader.readInt(r9, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r4 = SafeParcelReader.readIBinder(r9, r0);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r5 = (ConnectionResult) SafeParcelReader.createParcelable(r9, r0, ConnectionResult.CREATOR);
        goto L4
    L12:
        if (r1 != 4) goto L14;
        r6 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L14:
        if (r1 != 5) goto L15;
        r7 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new zay(r3, r4, r5, r6, r7);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zay[r1];
    }
}
