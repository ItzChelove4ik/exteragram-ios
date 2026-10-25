package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zab implements Parcelable.Creator {
    public zab() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        String r6 = null;
        String r7 = null;
        String r8 = null;
        String r9 = null;
        Uri r10 = null;
        String r11 = null;
        String r14 = null;
        ArrayList r15 = null;
        String r16 = null;
        String r17 = null;
        long r12 = 0;
    L4:
        if (r19.dataPosition() >= r1) goto L19;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
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
        r17 = SafeParcelReader.createString(r19, r2);
        goto L4
    L9:
        r16 = SafeParcelReader.createString(r19, r2);
        goto L4
    L10:
        r15 = SafeParcelReader.createTypedList(r19, r2, Scope.CREATOR);
        goto L4
    L11:
        r14 = SafeParcelReader.createString(r19, r2);
        goto L4
    L12:
        r12 = SafeParcelReader.readLong(r19, r2);
        goto L4
    L13:
        r11 = SafeParcelReader.createString(r19, r2);
        goto L4
    L14:
        r10 = (Uri) SafeParcelReader.createParcelable(r19, r2, Uri.CREATOR);
        goto L4
    L15:
        r9 = SafeParcelReader.createString(r19, r2);
        goto L4
    L16:
        r8 = SafeParcelReader.createString(r19, r2);
        goto L4
    L17:
        r7 = SafeParcelReader.createString(r19, r2);
        goto L4
    L18:
        r6 = SafeParcelReader.createString(r19, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L19:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new GoogleSignInAccount(r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new GoogleSignInAccount[r1];
    }
}
