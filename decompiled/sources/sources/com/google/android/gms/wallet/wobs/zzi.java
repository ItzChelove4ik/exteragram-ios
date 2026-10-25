package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzi implements Parcelable.Creator {
    public zzi() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        int r17 = -1;
        long r15 = 0;
        String r11 = null;
        String r14 = null;
        double r12 = 0.0d;
        int r10 = 0;
    L4:
        if (r19.dataPosition() >= r1) goto L14;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        r17 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L9:
        r15 = SafeParcelReader.readLong(r19, r2);
        goto L4
    L10:
        r14 = SafeParcelReader.createString(r19, r2);
        goto L4
    L11:
        r12 = SafeParcelReader.readDouble(r19, r2);
        goto L4
    L12:
        r11 = SafeParcelReader.createString(r19, r2);
        goto L4
    L13:
        r10 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new LoyaltyPointsBalance(r10, r11, r12, r14, r15, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LoyaltyPointsBalance[r1];
    }
}
