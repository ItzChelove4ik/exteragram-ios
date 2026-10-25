package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.identity.intents.model.UserAddress;

/* loaded from: classes5.dex */
public final class zzl implements Parcelable.Creator {
    public zzl() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r14) {
        int r13 = SafeParcelReader.validateObjectHeader(r14);
        String r2 = null;
        String r3 = null;
        zzaj r4 = null;
        String r5 = null;
        zza r6 = null;
        zza r7 = null;
        String[] r8 = null;
        UserAddress r9 = null;
        UserAddress r10 = null;
        InstrumentInfo[] r11 = null;
        PaymentMethodToken r12 = null;
    L4:
        if (r14.dataPosition() >= r13) goto L19;
        int r0 = SafeParcelReader.readHeader(r14);
        switch(SafeParcelReader.getFieldId(r0)) {
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
        r12 = (PaymentMethodToken) SafeParcelReader.createParcelable(r14, r0, PaymentMethodToken.CREATOR);
        goto L4
    L9:
        r11 = (InstrumentInfo[]) SafeParcelReader.createTypedArray(r14, r0, InstrumentInfo.CREATOR);
        goto L4
    L10:
        r10 = (UserAddress) SafeParcelReader.createParcelable(r14, r0, UserAddress.CREATOR);
        goto L4
    L11:
        r9 = (UserAddress) SafeParcelReader.createParcelable(r14, r0, UserAddress.CREATOR);
        goto L4
    L12:
        r8 = SafeParcelReader.createStringArray(r14, r0);
        goto L4
    L13:
        r7 = (zza) SafeParcelReader.createParcelable(r14, r0, zza.CREATOR);
        goto L4
    L14:
        r6 = (zza) SafeParcelReader.createParcelable(r14, r0, zza.CREATOR);
        goto L4
    L15:
        r5 = SafeParcelReader.createString(r14, r0);
        goto L4
    L16:
        r4 = (zzaj) SafeParcelReader.createParcelable(r14, r0, zzaj.CREATOR);
        goto L4
    L17:
        r3 = SafeParcelReader.createString(r14, r0);
        goto L4
    L18:
        r2 = SafeParcelReader.createString(r14, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r14, r0);
        goto L4
    L19:
        SafeParcelReader.ensureAtEnd(r14, r13);
        return new FullWallet(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new FullWallet[r1];
    }
}
