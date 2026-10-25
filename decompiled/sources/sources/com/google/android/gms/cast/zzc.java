package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzc implements Parcelable.Creator {
    public zzc() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r13) {
        int r12 = SafeParcelReader.validateObjectHeader(r13);
        long r4 = 0;
        long r6 = 0;
        long r10 = 0;
        String r8 = null;
        String r9 = null;
    L4:
        if (r13.dataPosition() >= r12) goto L21;
        int r0 = SafeParcelReader.readHeader(r13);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 2) goto L8;
        r4 = SafeParcelReader.readLong(r13, r0);
        goto L4
    L8:
        if (r1 != 3) goto L10;
        r6 = SafeParcelReader.readLong(r13, r0);
        goto L4
    L10:
        if (r1 != 4) goto L12;
        r8 = SafeParcelReader.createString(r13, r0);
        goto L4
    L12:
        if (r1 != 5) goto L14;
        r9 = SafeParcelReader.createString(r13, r0);
        goto L4
    L14:
        if (r1 != 6) goto L15;
        r10 = SafeParcelReader.readLong(r13, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r13, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r13, r12);
        return new AdBreakStatus(r4, r6, r8, r9, r10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new AdBreakStatus[r1];
    }
}
