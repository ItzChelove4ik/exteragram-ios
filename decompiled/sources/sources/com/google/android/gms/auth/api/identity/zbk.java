package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zbk implements Parcelable.Creator {
    public zbk() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r11) {
        int r10 = SafeParcelReader.validateObjectHeader(r11);
        String r3 = null;
        String r4 = null;
        String r5 = null;
        String r6 = null;
        ArrayList r9 = null;
        boolean r7 = false;
        int r8 = 0;
    L4:
        if (r11.dataPosition() >= r10) goto L15;
        int r0 = SafeParcelReader.readHeader(r11);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        r9 = SafeParcelReader.createTypedList(r11, r0, zbf.CREATOR);
        goto L4
    L9:
        r8 = SafeParcelReader.readInt(r11, r0);
        goto L4
    L10:
        r7 = SafeParcelReader.readBoolean(r11, r0);
        goto L4
    L11:
        r6 = SafeParcelReader.createString(r11, r0);
        goto L4
    L12:
        r5 = SafeParcelReader.createString(r11, r0);
        goto L4
    L13:
        r4 = SafeParcelReader.createString(r11, r0);
        goto L4
    L14:
        r3 = SafeParcelReader.createString(r11, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r11, r0);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r11, r10);
        return new GetSignInIntentRequest(r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new GetSignInIntentRequest[r1];
    }
}
