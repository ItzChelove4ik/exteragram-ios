package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzgn implements Parcelable.Creator {
    public zzgn() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        String r0 = null;
        int r2 = 0;
        boolean r3 = false;
        String r1 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 2) goto L8;
        r0 = SafeParcelReader.createString(r8, r4);
        goto L4
    L8:
        if (r5 != 3) goto L10;
        r1 = SafeParcelReader.createString(r8, r4);
        goto L4
    L10:
        if (r5 != 4) goto L12;
        r2 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L12:
        if (r5 != 5) goto L13;
        r3 = SafeParcelReader.readBoolean(r8, r4);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zzgm(r0, r1, r2, r3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzgm[r1];
    }
}
