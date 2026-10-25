package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zaa implements Parcelable.Creator {
    public zaa() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        Bundle r0 = null;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 1) goto L8;
        r1 = SafeParcelReader.readInt(r7, r3);
        goto L4
    L8:
        if (r4 != 2) goto L10;
        r2 = SafeParcelReader.readInt(r7, r3);
        goto L4
    L10:
        if (r4 != 3) goto L11;
        r0 = SafeParcelReader.createBundle(r7, r3);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new GoogleSignInOptionsExtensionParcelable(r1, r2, r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new GoogleSignInOptionsExtensionParcelable[r1];
    }
}
