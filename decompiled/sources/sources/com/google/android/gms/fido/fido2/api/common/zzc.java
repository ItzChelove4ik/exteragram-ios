package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzc implements Parcelable.Creator {
    public zzc() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        UvmEntries r2 = null;
        zzf r3 = null;
        AuthenticationExtensionsCredPropsOutputs r4 = null;
        zzh r5 = null;
        String r6 = null;
    L4:
        if (r9.dataPosition() >= r8) goto L21;
        int r0 = SafeParcelReader.readHeader(r9);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r2 = (UvmEntries) SafeParcelReader.createParcelable(r9, r0, UvmEntries.CREATOR);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r3 = (zzf) SafeParcelReader.createParcelable(r9, r0, zzf.CREATOR);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r4 = (AuthenticationExtensionsCredPropsOutputs) SafeParcelReader.createParcelable(r9, r0, AuthenticationExtensionsCredPropsOutputs.CREATOR);
        goto L4
    L12:
        if (r1 != 4) goto L14;
        r5 = (zzh) SafeParcelReader.createParcelable(r9, r0, zzh.CREATOR);
        goto L4
    L14:
        if (r1 != 5) goto L15;
        r6 = SafeParcelReader.createString(r9, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new AuthenticationExtensionsClientOutputs(r2, r3, r4, r5, r6);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new AuthenticationExtensionsClientOutputs[r1];
    }
}
