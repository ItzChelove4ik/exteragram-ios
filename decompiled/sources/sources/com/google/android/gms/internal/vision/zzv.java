package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzv implements Parcelable.Creator<zzs> {
    public zzv() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzs createFromParcel(Parcel r11) {
        int r10 = SafeParcelReader.validateObjectHeader(r11);
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r9 = 0;
        long r7 = 0;
    L4:
        if (r11.dataPosition() >= r10) goto L21;
        int r0 = SafeParcelReader.readHeader(r11);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 2) goto L8;
        r4 = SafeParcelReader.readInt(r11, r0);
        goto L4
    L8:
        if (r1 != 3) goto L10;
        r5 = SafeParcelReader.readInt(r11, r0);
        goto L4
    L10:
        if (r1 != 4) goto L12;
        r6 = SafeParcelReader.readInt(r11, r0);
        goto L4
    L12:
        if (r1 != 5) goto L14;
        r7 = SafeParcelReader.readLong(r11, r0);
        goto L4
    L14:
        if (r1 != 6) goto L15;
        r9 = SafeParcelReader.readInt(r11, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r11, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r11, r10);
        return new zzs(r4, r5, r6, r7, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzs[] newArray(int r1) {
        return new zzs[r1];
    }
}
