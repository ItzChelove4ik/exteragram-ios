package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzan implements Parcelable.Creator {
    public zzan() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r18) {
        int r1 = SafeParcelReader.validateObjectHeader(r18);
        PublicKeyCredentialRpEntity r4 = null;
        PublicKeyCredentialUserEntity r5 = null;
        byte[] r6 = null;
        ArrayList r7 = null;
        Double r8 = null;
        ArrayList r9 = null;
        AuthenticatorSelectionCriteria r10 = null;
        Integer r11 = null;
        TokenBinding r12 = null;
        String r13 = null;
        AuthenticationExtensions r14 = null;
        String r15 = null;
        ResultReceiver r16 = null;
    L4:
        if (r18.dataPosition() >= r1) goto L21;
        int r2 = SafeParcelReader.readHeader(r18);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L20;
            case 3: goto L19;
            case 4: goto L18;
            case 5: goto L17;
            case 6: goto L16;
            case 7: goto L15;
            case 8: goto L14;
            case 9: goto L13;
            case 10: goto L12;
            case 11: goto L11;
            case 12: goto L10;
            case 13: goto L9;
            case 14: goto L8;
            default: goto L7;
        };
    L8:
        r16 = (ResultReceiver) SafeParcelReader.createParcelable(r18, r2, ResultReceiver.CREATOR);
        goto L4
    L9:
        r15 = SafeParcelReader.createString(r18, r2);
        goto L4
    L10:
        r14 = (AuthenticationExtensions) SafeParcelReader.createParcelable(r18, r2, AuthenticationExtensions.CREATOR);
        goto L4
    L11:
        r13 = SafeParcelReader.createString(r18, r2);
        goto L4
    L12:
        r12 = (TokenBinding) SafeParcelReader.createParcelable(r18, r2, TokenBinding.CREATOR);
        goto L4
    L13:
        r11 = SafeParcelReader.readIntegerObject(r18, r2);
        goto L4
    L14:
        r10 = (AuthenticatorSelectionCriteria) SafeParcelReader.createParcelable(r18, r2, AuthenticatorSelectionCriteria.CREATOR);
        goto L4
    L15:
        r9 = SafeParcelReader.createTypedList(r18, r2, PublicKeyCredentialDescriptor.CREATOR);
        goto L4
    L16:
        r8 = SafeParcelReader.readDoubleObject(r18, r2);
        goto L4
    L17:
        r7 = SafeParcelReader.createTypedList(r18, r2, PublicKeyCredentialParameters.CREATOR);
        goto L4
    L18:
        r6 = SafeParcelReader.createByteArray(r18, r2);
        goto L4
    L19:
        r5 = (PublicKeyCredentialUserEntity) SafeParcelReader.createParcelable(r18, r2, PublicKeyCredentialUserEntity.CREATOR);
        goto L4
    L20:
        r4 = (PublicKeyCredentialRpEntity) SafeParcelReader.createParcelable(r18, r2, PublicKeyCredentialRpEntity.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r18, r2);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r18, r1);
        return new PublicKeyCredentialCreationOptions(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new PublicKeyCredentialCreationOptions[r1];
    }
}
