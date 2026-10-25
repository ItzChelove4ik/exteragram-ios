package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zbn implements Parcelable.Creator {
    public zbn() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        byte[] r0 = null;
        boolean r2 = false;
        String r1 = null;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 1) goto L8;
        r2 = SafeParcelReader.readBoolean(r7, r3);
        goto L4
    L8:
        if (r4 != 2) goto L10;
        r0 = SafeParcelReader.createByteArray(r7, r3);
        goto L4
    L10:
        if (r4 != 3) goto L11;
        r1 = SafeParcelReader.createString(r7, r3);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new BeginSignInRequest.PasskeysRequestOptions(r2, r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new BeginSignInRequest.PasskeysRequestOptions[r1];
    }
}
