package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzs implements Parcelable.Creator {
    public zzs() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        long r9 = -1;
        boolean r5 = false;
        int r7 = 0;
        int r8 = 0;
        String r6 = null;
    L4:
        if (r12.dataPosition() >= r11) goto L21;
        int r0 = SafeParcelReader.readHeader(r12);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r5 = SafeParcelReader.readBoolean(r12, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r6 = SafeParcelReader.createString(r12, r0);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r7 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L12:
        if (r1 != 4) goto L14;
        r8 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L14:
        if (r1 != 5) goto L15;
        r9 = SafeParcelReader.readLong(r12, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r12, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r12, r11);
        return new zzr(r5, r6, r7, r8, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzr[r1];
    }
}
