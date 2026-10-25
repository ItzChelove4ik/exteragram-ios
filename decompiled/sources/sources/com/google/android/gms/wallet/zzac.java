package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzac implements Parcelable.Creator {
    public zzac() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        CardRequirements r8 = null;
        ShippingAddressRequirements r10 = null;
        ArrayList<Integer> r11 = null;
        PaymentMethodTokenizationParameters r12 = null;
        TransactionInfo r13 = null;
        String r15 = null;
        byte[] r16 = null;
        Bundle r17 = null;
        boolean r14 = true;
        boolean r6 = false;
        boolean r7 = false;
        boolean r9 = false;
    L4:
        if (r19.dataPosition() >= r1) goto L20;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L19;
            case 2: goto L18;
            case 3: goto L17;
            case 4: goto L16;
            case 5: goto L15;
            case 6: goto L14;
            case 7: goto L13;
            case 8: goto L12;
            case 9: goto L11;
            case 10: goto L10;
            case 11: goto L9;
            case 12: goto L8;
            default: goto L7;
        };
    L8:
        r16 = SafeParcelReader.createByteArray(r19, r2);
        goto L4
    L9:
        r17 = SafeParcelReader.createBundle(r19, r2);
        goto L4
    L10:
        r15 = SafeParcelReader.createString(r19, r2);
        goto L4
    L11:
        r14 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L12:
        r13 = (TransactionInfo) SafeParcelReader.createParcelable(r19, r2, TransactionInfo.CREATOR);
        goto L4
    L13:
        r12 = (PaymentMethodTokenizationParameters) SafeParcelReader.createParcelable(r19, r2, PaymentMethodTokenizationParameters.CREATOR);
        goto L4
    L14:
        r11 = SafeParcelReader.createIntegerList(r19, r2);
        goto L4
    L15:
        r10 = (ShippingAddressRequirements) SafeParcelReader.createParcelable(r19, r2, ShippingAddressRequirements.CREATOR);
        goto L4
    L16:
        r9 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L17:
        r8 = (CardRequirements) SafeParcelReader.createParcelable(r19, r2, CardRequirements.CREATOR);
        goto L4
    L18:
        r7 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L19:
        r6 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L20:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new PaymentDataRequest(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new PaymentDataRequest[r1];
    }
}
