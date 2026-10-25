package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zbl implements Parcelable.Creator {
    public zbl() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r13) {
        int r12 = SafeParcelReader.validateObjectHeader(r13);
        String r4 = null;
        String r5 = null;
        String r7 = null;
        ArrayList<String> r8 = null;
        ArrayList r10 = null;
        String r11 = null;
        boolean r3 = false;
        boolean r6 = false;
        boolean r9 = false;
    L4:
        if (r13.dataPosition() >= r12) goto L17;
        int r0 = SafeParcelReader.readHeader(r13);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L16;
            case 2: goto L15;
            case 3: goto L14;
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L11;
            case 7: goto L10;
            case 8: goto L9;
            case 9: goto L8;
            default: goto L7;
        };
    L8:
        r11 = SafeParcelReader.createString(r13, r0);
        goto L4
    L9:
        r10 = SafeParcelReader.createTypedList(r13, r0, zbf.CREATOR);
        goto L4
    L10:
        r9 = SafeParcelReader.readBoolean(r13, r0);
        goto L4
    L11:
        r8 = SafeParcelReader.createStringList(r13, r0);
        goto L4
    L12:
        r7 = SafeParcelReader.createString(r13, r0);
        goto L4
    L13:
        r6 = SafeParcelReader.readBoolean(r13, r0);
        goto L4
    L14:
        r5 = SafeParcelReader.createString(r13, r0);
        goto L4
    L15:
        r4 = SafeParcelReader.createString(r13, r0);
        goto L4
    L16:
        r3 = SafeParcelReader.readBoolean(r13, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r13, r0);
        goto L4
    L17:
        SafeParcelReader.ensureAtEnd(r13, r12);
        return new BeginSignInRequest.GoogleIdTokenRequestOptions(r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new BeginSignInRequest.GoogleIdTokenRequestOptions[r1];
    }
}
