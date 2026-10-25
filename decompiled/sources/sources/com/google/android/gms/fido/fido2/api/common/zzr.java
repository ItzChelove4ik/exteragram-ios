package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzr implements Parcelable.Creator {
    public zzr() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        byte[] r6 = null;
        byte[] r7 = null;
        byte[] r8 = null;
        long r4 = 0;
    L4:
        if (r10.dataPosition() >= r9) goto L18;
        int r0 = SafeParcelReader.readHeader(r10);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r4 = SafeParcelReader.readLong(r10, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r6 = SafeParcelReader.createByteArray(r10, r0);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r7 = SafeParcelReader.createByteArray(r10, r0);
        goto L4
    L12:
        if (r1 != 4) goto L13;
        r8 = SafeParcelReader.createByteArray(r10, r0);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new zzq(r4, r6, r7, r8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzq[r1];
    }
}
