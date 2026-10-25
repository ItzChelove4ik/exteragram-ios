package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzg implements Parcelable.Creator {
    public zzg() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        boolean r0 = false;
        boolean r4 = true;
        ArrayList<Integer> r3 = null;
        int r2 = 0;
    L4:
        if (r9.dataPosition() >= r8) goto L18;
        int r5 = SafeParcelReader.readHeader(r9);
        int r6 = SafeParcelReader.getFieldId(r5);
        if (r6 != 1) goto L8;
        r3 = SafeParcelReader.createIntegerList(r9, r5);
        goto L4
    L8:
        if (r6 != 2) goto L10;
        r4 = SafeParcelReader.readBoolean(r9, r5);
        goto L4
    L10:
        if (r6 != 3) goto L12;
        r0 = SafeParcelReader.readBoolean(r9, r5);
        goto L4
    L12:
        if (r6 != 4) goto L13;
        r2 = SafeParcelReader.readInt(r9, r5);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r9, r5);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new CardRequirements(r3, r4, r0, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new CardRequirements[r1];
    }
}
