package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zad implements Parcelable.Creator {
    public zad() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r14) {
        int r13 = SafeParcelReader.validateObjectHeader(r14);
        ArrayList r4 = null;
        Account r5 = null;
        String r9 = null;
        String r10 = null;
        ArrayList r11 = null;
        String r12 = null;
        int r3 = 0;
        boolean r6 = false;
        boolean r7 = false;
        boolean r8 = false;
    L4:
        if (r14.dataPosition() >= r13) goto L18;
        int r0 = SafeParcelReader.readHeader(r14);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L17;
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            case 8: goto L10;
            case 9: goto L9;
            case 10: goto L8;
            default: goto L7;
        };
    L8:
        r12 = SafeParcelReader.createString(r14, r0);
        goto L4
    L9:
        r11 = SafeParcelReader.createTypedList(r14, r0, GoogleSignInOptionsExtensionParcelable.CREATOR);
        goto L4
    L10:
        r10 = SafeParcelReader.createString(r14, r0);
        goto L4
    L11:
        r9 = SafeParcelReader.createString(r14, r0);
        goto L4
    L12:
        r8 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L13:
        r7 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L14:
        r6 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L15:
        r5 = (Account) SafeParcelReader.createParcelable(r14, r0, Account.CREATOR);
        goto L4
    L16:
        r4 = SafeParcelReader.createTypedList(r14, r0, Scope.CREATOR);
        goto L4
    L17:
        r3 = SafeParcelReader.readInt(r14, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r14, r0);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r14, r13);
        return new GoogleSignInOptions(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new GoogleSignInOptions[r1];
    }
}
