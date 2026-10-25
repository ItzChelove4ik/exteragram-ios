package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzao implements Parcelable.Creator {
    public zzao() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        String r2 = null;
        String r3 = null;
        byte[] r4 = null;
        AuthenticatorAttestationResponse r5 = null;
        AuthenticatorAssertionResponse r6 = null;
        AuthenticatorErrorResponse r7 = null;
        AuthenticationExtensionsClientOutputs r8 = null;
        String r9 = null;
        String r10 = null;
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
        r10 = SafeParcelReader.createString(r12, r0);
        goto L4
    L9:
        r9 = SafeParcelReader.createString(r12, r0);
        goto L4
    L10:
        r8 = (AuthenticationExtensionsClientOutputs) SafeParcelReader.createParcelable(r12, r0, AuthenticationExtensionsClientOutputs.CREATOR);
        goto L4
    L11:
        r7 = (AuthenticatorErrorResponse) SafeParcelReader.createParcelable(r12, r0, AuthenticatorErrorResponse.CREATOR);
        goto L4
    L12:
        r6 = (AuthenticatorAssertionResponse) SafeParcelReader.createParcelable(r12, r0, AuthenticatorAssertionResponse.CREATOR);
        goto L4
    L13:
        r5 = (AuthenticatorAttestationResponse) SafeParcelReader.createParcelable(r12, r0, AuthenticatorAttestationResponse.CREATOR);
        goto L4
    L14:
        r4 = SafeParcelReader.createByteArray(r12, r0);
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
        return new PublicKeyCredential(r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new PublicKeyCredential[r1];
    }
}
