package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzb implements Parcelable.Creator {
    public zzb() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r11) {
        int r10 = SafeParcelReader.validateObjectHeader(r11);
        int r6 = 0;
        boolean r9 = false;
        long r7 = -1;
        String r5 = null;
    L4:
        if (r11.dataPosition() >= r10) goto L18;
        int r0 = SafeParcelReader.readHeader(r11);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r5 = SafeParcelReader.createString(r11, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r6 = SafeParcelReader.readInt(r11, r0);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r7 = SafeParcelReader.readLong(r11, r0);
        goto L4
    L12:
        if (r1 != 4) goto L13;
        r9 = SafeParcelReader.readBoolean(r11, r0);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r11, r0);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r11, r10);
        return new Feature(r5, r6, r7, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new Feature[r1];
    }
}
