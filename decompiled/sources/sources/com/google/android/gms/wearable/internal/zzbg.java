package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzbg implements Parcelable.Creator {
    public zzbg() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        zzbq r0 = null;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 2) goto L8;
        r0 = (zzbq) SafeParcelReader.createParcelable(r8, r4, zzbq.CREATOR);
        goto L4
    L8:
        if (r5 != 3) goto L10;
        r1 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L10:
        if (r5 != 4) goto L12;
        r2 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L12:
        if (r5 != 5) goto L13;
        r3 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zzbf(r0, r1, r2, r3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzbf[r1];
    }
}
