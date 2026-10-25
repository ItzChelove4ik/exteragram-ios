package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.fido.zzgx;

/* loaded from: classes4.dex */
public final class zzg implements Parcelable.Creator {
    public zzg() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        zzgx r0 = null;
        byte[] r1 = null;
        byte[] r2 = null;
    L4:
        if (r7.dataPosition() >= r6) goto L12;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 1) goto L8;
        r1 = SafeParcelReader.createByteArray(r7, r3);
        goto L4
    L8:
        if (r4 != 2) goto L9;
        r2 = SafeParcelReader.createByteArray(r7, r3);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r7, r6);
        if (r1 != null) goto L15;
        zzgx r12 = null;
    L16:
        if (r2 == null) goto L20;
        r0 = zzgx.zzl(r2, 0, r2.length);
    L20:
        return new zzf(r12, r0);
    L15:
        r12 = zzgx.zzl(r1, 0, r1.length);
        goto L16
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzf[r1];
    }
}
