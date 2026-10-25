package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzd implements Parcelable.Creator {
    public zzd() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r15) {
        int r14 = SafeParcelReader.validateObjectHeader(r15);
        FidoAppIdExtension r2 = null;
        zzs r3 = null;
        UserVerificationMethodExtension r4 = null;
        zzz r5 = null;
        zzab r6 = null;
        zzad r7 = null;
        zzu r8 = null;
        zzag r9 = null;
        GoogleThirdPartyPaymentExtension r10 = null;
        zzak r11 = null;
        zzaw r12 = null;
        zzai r13 = null;
    L4:
        if (r15.dataPosition() >= r14) goto L20;
        int r0 = SafeParcelReader.readHeader(r15);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 2: goto L19;
            case 3: goto L18;
            case 4: goto L17;
            case 5: goto L16;
            case 6: goto L15;
            case 7: goto L14;
            case 8: goto L13;
            case 9: goto L12;
            case 10: goto L11;
            case 11: goto L10;
            case 12: goto L9;
            case 13: goto L8;
            default: goto L7;
        };
    L8:
        r13 = (zzai) SafeParcelReader.createParcelable(r15, r0, zzai.CREATOR);
        goto L4
    L9:
        r12 = (zzaw) SafeParcelReader.createParcelable(r15, r0, zzaw.CREATOR);
        goto L4
    L10:
        r11 = (zzak) SafeParcelReader.createParcelable(r15, r0, zzak.CREATOR);
        goto L4
    L11:
        r10 = (GoogleThirdPartyPaymentExtension) SafeParcelReader.createParcelable(r15, r0, GoogleThirdPartyPaymentExtension.CREATOR);
        goto L4
    L12:
        r9 = (zzag) SafeParcelReader.createParcelable(r15, r0, zzag.CREATOR);
        goto L4
    L13:
        r8 = (zzu) SafeParcelReader.createParcelable(r15, r0, zzu.CREATOR);
        goto L4
    L14:
        r7 = (zzad) SafeParcelReader.createParcelable(r15, r0, zzad.CREATOR);
        goto L4
    L15:
        r6 = (zzab) SafeParcelReader.createParcelable(r15, r0, zzab.CREATOR);
        goto L4
    L16:
        r5 = (zzz) SafeParcelReader.createParcelable(r15, r0, zzz.CREATOR);
        goto L4
    L17:
        r4 = (UserVerificationMethodExtension) SafeParcelReader.createParcelable(r15, r0, UserVerificationMethodExtension.CREATOR);
        goto L4
    L18:
        r3 = (zzs) SafeParcelReader.createParcelable(r15, r0, zzs.CREATOR);
        goto L4
    L19:
        r2 = (FidoAppIdExtension) SafeParcelReader.createParcelable(r15, r0, FidoAppIdExtension.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r15, r0);
        goto L4
    L20:
        SafeParcelReader.ensureAtEnd(r15, r14);
        return new AuthenticationExtensions(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new AuthenticationExtensions[r1];
    }
}
