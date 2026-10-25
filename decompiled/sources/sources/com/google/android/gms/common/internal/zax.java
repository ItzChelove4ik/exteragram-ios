package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zax implements Parcelable.Creator {
    public zax() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        Account r0 = null;
        int r2 = 0;
        int r3 = 0;
        GoogleSignInAccount r1 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r2 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L8:
        if (r5 != 2) goto L10;
        r0 = (Account) SafeParcelReader.createParcelable(r8, r4, Account.CREATOR);
        goto L4
    L10:
        if (r5 != 3) goto L12;
        r3 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L12:
        if (r5 != 4) goto L13;
        r1 = (GoogleSignInAccount) SafeParcelReader.createParcelable(r8, r4, GoogleSignInAccount.CREATOR);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zaw(r2, r0, r3, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zaw[r1];
    }
}
