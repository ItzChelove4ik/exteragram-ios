package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzd implements Parcelable.Creator<zzc> {
    public zzd() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzc createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        boolean r4 = false;
        long r5 = 0;
        long r7 = 0;
    L4:
        if (r10.dataPosition() >= r9) goto L15;
        int r0 = SafeParcelReader.readHeader(r10);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r4 = SafeParcelReader.readBoolean(r10, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r7 = SafeParcelReader.readLong(r10, r0);
        goto L4
    L10:
        if (r1 != 3) goto L11;
        r5 = SafeParcelReader.readLong(r10, r0);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new zzc(r4, r5, r7);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzc[] newArray(int r1) {
        return new zzc[r1];
    }
}
