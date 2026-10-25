package com.google.android.gms.internal.icing;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzh implements Parcelable.Creator<zzg> {
    public zzh() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzg createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        zzk[] r0 = null;
        Account r2 = null;
        boolean r3 = false;
        String r1 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r0 = (zzk[]) SafeParcelReader.createTypedArray(r8, r4, zzk.CREATOR);
        goto L4
    L8:
        if (r5 != 2) goto L10;
        r1 = SafeParcelReader.createString(r8, r4);
        goto L4
    L10:
        if (r5 != 3) goto L12;
        r3 = SafeParcelReader.readBoolean(r8, r4);
        goto L4
    L12:
        if (r5 != 4) goto L13;
        r2 = (Account) SafeParcelReader.createParcelable(r8, r4, Account.CREATOR);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zzg(r0, r1, r3, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzg[] newArray(int r1) {
        return new zzg[r1];
    }
}
