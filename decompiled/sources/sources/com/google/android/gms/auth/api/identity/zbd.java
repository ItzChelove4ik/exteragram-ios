package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zbd implements Parcelable.Creator {
    public zbd() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        boolean r6 = false;
        int r7 = 0;
        boolean r10 = false;
        BeginSignInRequest.PasswordRequestOptions r3 = null;
        BeginSignInRequest.GoogleIdTokenRequestOptions r4 = null;
        String r5 = null;
        BeginSignInRequest.PasskeysRequestOptions r8 = null;
        BeginSignInRequest.PasskeyJsonRequestOptions r9 = null;
    L4:
        if (r12.dataPosition() >= r11) goto L16;
        int r0 = SafeParcelReader.readHeader(r12);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L15;
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L12;
            case 5: goto L11;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L8;
            default: goto L7;
        };
    L8:
        r10 = SafeParcelReader.readBoolean(r12, r0);
        goto L4
    L9:
        r9 = (BeginSignInRequest.PasskeyJsonRequestOptions) SafeParcelReader.createParcelable(r12, r0, BeginSignInRequest.PasskeyJsonRequestOptions.CREATOR);
        goto L4
    L10:
        r8 = (BeginSignInRequest.PasskeysRequestOptions) SafeParcelReader.createParcelable(r12, r0, BeginSignInRequest.PasskeysRequestOptions.CREATOR);
        goto L4
    L11:
        r7 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L12:
        r6 = SafeParcelReader.readBoolean(r12, r0);
        goto L4
    L13:
        r5 = SafeParcelReader.createString(r12, r0);
        goto L4
    L14:
        r4 = (BeginSignInRequest.GoogleIdTokenRequestOptions) SafeParcelReader.createParcelable(r12, r0, BeginSignInRequest.GoogleIdTokenRequestOptions.CREATOR);
        goto L4
    L15:
        r3 = (BeginSignInRequest.PasswordRequestOptions) SafeParcelReader.createParcelable(r12, r0, BeginSignInRequest.PasswordRequestOptions.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r12, r0);
        goto L4
    L16:
        SafeParcelReader.ensureAtEnd(r12, r11);
        return new BeginSignInRequest(r3, r4, r5, r6, r7, r8, r9, r10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new BeginSignInRequest[r1];
    }
}
