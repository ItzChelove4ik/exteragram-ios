package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.identity.intents.model.UserAddress;

/* loaded from: classes5.dex */
public final class zzaa implements Parcelable.Creator {
    public zzaa() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r11) {
        int r10 = SafeParcelReader.validateObjectHeader(r11);
        String r2 = null;
        CardInfo r3 = null;
        UserAddress r4 = null;
        PaymentMethodToken r5 = null;
        String r6 = null;
        Bundle r7 = null;
        String r8 = null;
        Bundle r9 = null;
    L4:
        if (r11.dataPosition() >= r10) goto L16;
        int r0 = SafeParcelReader.readHeader(r11);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L15;
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L12;
            case 5: goto L11;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L8;
            default: goto L7;
        };
    L8:
        r9 = SafeParcelReader.createBundle(r11, r0);
        goto L4
    L9:
        r8 = SafeParcelReader.createString(r11, r0);
        goto L4
    L10:
        r7 = SafeParcelReader.createBundle(r11, r0);
        goto L4
    L11:
        r6 = SafeParcelReader.createString(r11, r0);
        goto L4
    L12:
        r5 = (PaymentMethodToken) SafeParcelReader.createParcelable(r11, r0, PaymentMethodToken.CREATOR);
        goto L4
    L13:
        r4 = (UserAddress) SafeParcelReader.createParcelable(r11, r0, UserAddress.CREATOR);
        goto L4
    L14:
        r3 = (CardInfo) SafeParcelReader.createParcelable(r11, r0, CardInfo.CREATOR);
        goto L4
    L15:
        r2 = SafeParcelReader.createString(r11, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r11, r0);
        goto L4
    L16:
        SafeParcelReader.ensureAtEnd(r11, r10);
        return new PaymentData(r2, r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new PaymentData[r1];
    }
}
