package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzag implements Parcelable.Creator {
    public zzag() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        long r4 = 0;
        long r7 = 0;
        int r6 = 0;
    L4:
        if (r10.dataPosition() >= r9) goto L15;
        int r0 = SafeParcelReader.readHeader(r10);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r4 = SafeParcelReader.readLong(r10, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r6 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L10:
        if (r1 != 3) goto L11;
        r7 = SafeParcelReader.readLong(r10, r0);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new zzaf(r4, r6, r7);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzaf[r1];
    }
}
