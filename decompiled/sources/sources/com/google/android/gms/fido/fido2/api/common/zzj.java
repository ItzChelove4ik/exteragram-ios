package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzj implements Parcelable.Creator {
    public zzj() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        byte[] r2 = null;
        byte[] r3 = null;
        byte[] r4 = null;
        byte[] r5 = null;
        byte[] r6 = null;
    L4:
        if (r9.dataPosition() >= r8) goto L21;
        int r0 = SafeParcelReader.readHeader(r9);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 2) goto L8;
        r2 = SafeParcelReader.createByteArray(r9, r0);
        goto L4
    L8:
        if (r1 != 3) goto L10;
        r3 = SafeParcelReader.createByteArray(r9, r0);
        goto L4
    L10:
        if (r1 != 4) goto L12;
        r4 = SafeParcelReader.createByteArray(r9, r0);
        goto L4
    L12:
        if (r1 != 5) goto L14;
        r5 = SafeParcelReader.createByteArray(r9, r0);
        goto L4
    L14:
        if (r1 != 6) goto L15;
        r6 = SafeParcelReader.createByteArray(r9, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new AuthenticatorAssertionResponse(r2, r3, r4, r5, r6);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new AuthenticatorAssertionResponse[r1];
    }
}
