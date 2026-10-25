package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zzd;

/* loaded from: classes5.dex */
public final class zzz implements Parcelable.Creator {
    public zzz() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r11) {
        int r10 = SafeParcelReader.validateObjectHeader(r11);
        zzd r9 = null;
        int r7 = 0;
        boolean r8 = false;
        long r5 = Long.MAX_VALUE;
    L4:
        if (r11.dataPosition() >= r10) goto L18;
        int r0 = SafeParcelReader.readHeader(r11);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r5 = SafeParcelReader.readLong(r11, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r7 = SafeParcelReader.readInt(r11, r0);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r8 = SafeParcelReader.readBoolean(r11, r0);
        goto L4
    L12:
        if (r1 != 5) goto L13;
        r9 = (zzd) SafeParcelReader.createParcelable(r11, r0, zzd.CREATOR);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r11, r0);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r11, r10);
        return new LastLocationRequest(r5, r7, r8, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LastLocationRequest[r1];
    }
}
