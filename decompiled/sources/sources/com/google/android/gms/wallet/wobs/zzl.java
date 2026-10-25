package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzl implements Parcelable.Creator {
    public zzl() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        long r0 = 0;
        long r2 = 0;
    L4:
        if (r8.dataPosition() >= r7) goto L12;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 2) goto L8;
        r0 = SafeParcelReader.readLong(r8, r4);
        goto L4
    L8:
        if (r5 != 3) goto L9;
        r2 = SafeParcelReader.readLong(r8, r4);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new TimeInterval(r0, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new TimeInterval[r1];
    }
}
