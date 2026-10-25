package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzbq implements Parcelable.Creator {
    public zzbq() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r11) {
        int r10 = SafeParcelReader.validateObjectHeader(r11);
        String r0 = null;
        long r1 = 0;
        String r4 = null;
        Integer r7 = null;
        String r8 = null;
    L3:
        long r5 = r1;
    L5:
        if (r11.dataPosition() >= r10) goto L22;
        int r12 = SafeParcelReader.readHeader(r11);
        int r2 = SafeParcelReader.getFieldId(r12);
        if (r2 != 2) goto L9;
        r4 = SafeParcelReader.createString(r11, r12);
        goto L5
    L9:
        if (r2 == 3) goto L20;
        if (r2 != 4) goto L13;
        r7 = SafeParcelReader.readIntegerObject(r11, r12);
        goto L5
    L13:
        if (r2 != 5) goto L15;
        r8 = SafeParcelReader.createString(r11, r12);
        goto L5
    L15:
        if (r2 != 6) goto L16;
        r0 = SafeParcelReader.createString(r11, r12);
        goto L5
    L16:
        SafeParcelReader.skipUnknownField(r11, r12);
        goto L5
    L20:
        r1 = SafeParcelReader.readLong(r11, r12);
        goto L3
    L22:
        SafeParcelReader.ensureAtEnd(r11, r10);
        return new MediaError(r4, r5, r7, r8, CastUtils.jsonStringToJsonObject(r0));
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MediaError[r1];
    }
}
