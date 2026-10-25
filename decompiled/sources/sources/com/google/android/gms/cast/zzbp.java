package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzbp implements Parcelable.Creator {
    public zzbp() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        String r0 = null;
        boolean r2 = false;
        boolean r3 = false;
        CredentialsData r1 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 2) goto L8;
        r2 = SafeParcelReader.readBoolean(r8, r4);
        goto L4
    L8:
        if (r5 != 3) goto L10;
        r0 = SafeParcelReader.createString(r8, r4);
        goto L4
    L10:
        if (r5 != 4) goto L12;
        r3 = SafeParcelReader.readBoolean(r8, r4);
        goto L4
    L12:
        if (r5 != 5) goto L13;
        r1 = (CredentialsData) SafeParcelReader.createParcelable(r8, r4, CredentialsData.CREATOR);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new LaunchOptions(r2, r0, r3, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LaunchOptions[r1];
    }
}
