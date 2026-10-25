package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.fido.zzgx;

/* loaded from: classes4.dex */
public final class zzaj implements Parcelable.Creator {
    public zzaj() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        zzgx r1 = null;
        int r5 = 0;
        byte[] r2 = null;
        byte[] r3 = null;
        byte[] r4 = null;
    L4:
        if (r10.dataPosition() >= r9) goto L18;
        int r6 = SafeParcelReader.readHeader(r10);
        int r7 = SafeParcelReader.getFieldId(r6);
        if (r7 != 1) goto L8;
        r2 = SafeParcelReader.createByteArray(r10, r6);
        goto L4
    L8:
        if (r7 != 2) goto L10;
        r3 = SafeParcelReader.createByteArray(r10, r6);
        goto L4
    L10:
        if (r7 != 3) goto L12;
        r4 = SafeParcelReader.createByteArray(r10, r6);
        goto L4
    L12:
        if (r7 != 4) goto L13;
        r5 = SafeParcelReader.readInt(r10, r6);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r10, r6);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r10, r9);
        if (r2 != null) goto L21;
        zzgx r102 = null;
    L22:
        if (r3 != null) goto L24;
        zzgx r22 = null;
    L25:
        if (r4 == null) goto L29;
        r1 = zzgx.zzl(r4, 0, r4.length);
    L29:
        return new zzai(r102, r22, r1, r5);
    L24:
        r22 = zzgx.zzl(r3, 0, r3.length);
        goto L25
    L21:
        r102 = zzgx.zzl(r2, 0, r2.length);
        goto L22
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzai[r1];
    }
}
