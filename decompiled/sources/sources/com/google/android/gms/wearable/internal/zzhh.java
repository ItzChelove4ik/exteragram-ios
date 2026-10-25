package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzhh implements Parcelable.Creator {
    public zzhh() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        int r0 = 0;
        long r1 = 0;
        ArrayList r3 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L15;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 2) goto L8;
        r0 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L8:
        if (r5 != 3) goto L10;
        r1 = SafeParcelReader.readLong(r8, r4);
        goto L4
    L10:
        if (r5 != 4) goto L11;
        r3 = SafeParcelReader.createTypedList(r8, r4, zzgq.CREATOR);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zzhg(r0, r1, r3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzhg[r1];
    }
}
