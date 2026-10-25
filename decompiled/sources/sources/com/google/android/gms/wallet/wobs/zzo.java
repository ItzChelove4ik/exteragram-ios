package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzo implements Parcelable.Creator {
    public zzo() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        String r2 = null;
        String r3 = null;
        TimeInterval r4 = null;
        UriData r5 = null;
        UriData r6 = null;
    L4:
        if (r9.dataPosition() >= r8) goto L21;
        int r0 = SafeParcelReader.readHeader(r9);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 2) goto L8;
        r2 = SafeParcelReader.createString(r9, r0);
        goto L4
    L8:
        if (r1 != 3) goto L10;
        r3 = SafeParcelReader.createString(r9, r0);
        goto L4
    L10:
        if (r1 != 4) goto L12;
        r4 = (TimeInterval) SafeParcelReader.createParcelable(r9, r0, TimeInterval.CREATOR);
        goto L4
    L12:
        if (r1 != 5) goto L14;
        r5 = (UriData) SafeParcelReader.createParcelable(r9, r0, UriData.CREATOR);
        goto L4
    L14:
        if (r1 != 6) goto L15;
        r6 = (UriData) SafeParcelReader.createParcelable(r9, r0, UriData.CREATOR);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new WalletObjectMessage(r2, r3, r4, r5, r6);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new WalletObjectMessage[r1];
    }
}
