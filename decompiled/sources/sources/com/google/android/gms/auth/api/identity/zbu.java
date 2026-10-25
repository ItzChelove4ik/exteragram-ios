package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;

/* loaded from: classes4.dex */
public final class zbu implements Parcelable.Creator {
    public zbu() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        String r2 = null;
        String r3 = null;
        String r4 = null;
        String r5 = null;
        Uri r6 = null;
        String r7 = null;
        String r8 = null;
        String r9 = null;
        PublicKeyCredential r10 = null;
    L4:
        if (r12.dataPosition() >= r11) goto L17;
        int r0 = SafeParcelReader.readHeader(r12);
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
        r10 = (PublicKeyCredential) SafeParcelReader.createParcelable(r12, r0, PublicKeyCredential.CREATOR);
        goto L4
    L9:
        r9 = SafeParcelReader.createString(r12, r0);
        goto L4
    L10:
        r8 = SafeParcelReader.createString(r12, r0);
        goto L4
    L11:
        r7 = SafeParcelReader.createString(r12, r0);
        goto L4
    L12:
        r6 = (Uri) SafeParcelReader.createParcelable(r12, r0, Uri.CREATOR);
        goto L4
    L13:
        r5 = SafeParcelReader.createString(r12, r0);
        goto L4
    L14:
        r4 = SafeParcelReader.createString(r12, r0);
        goto L4
    L15:
        r3 = SafeParcelReader.createString(r12, r0);
        goto L4
    L16:
        r2 = SafeParcelReader.createString(r12, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r12, r0);
        goto L4
    L17:
        SafeParcelReader.ensureAtEnd(r12, r11);
        return new SignInCredential(r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new SignInCredential[r1];
    }
}
