package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.fido.zzgx;

/* loaded from: classes4.dex */
public final class zzi implements Parcelable.Creator {
    public zzi() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        zzgx r0 = null;
        byte[] r2 = null;
        boolean r3 = false;
    L4:
        if (r8.dataPosition() >= r7) goto L12;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r3 = SafeParcelReader.readBoolean(r8, r4);
        goto L4
    L8:
        if (r5 != 2) goto L9;
        r2 = SafeParcelReader.createByteArray(r8, r4);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r8, r7);
        if (r2 == null) goto L17;
        r0 = zzgx.zzl(r2, 0, r2.length);
    L17:
        return new zzh(r3, r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzh[r1];
    }
}
