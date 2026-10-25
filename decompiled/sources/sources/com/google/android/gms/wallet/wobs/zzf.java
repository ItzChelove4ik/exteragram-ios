package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzf implements Parcelable.Creator {
    public zzf() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        ArrayList r0 = ArrayUtils.newArrayList();
        String r1 = null;
        String r2 = null;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 2) goto L8;
        r1 = SafeParcelReader.createString(r7, r3);
        goto L4
    L8:
        if (r4 != 3) goto L10;
        r2 = SafeParcelReader.createString(r7, r3);
        goto L4
    L10:
        if (r4 != 4) goto L11;
        r0 = SafeParcelReader.createTypedList(r7, r3, LabelValue.CREATOR);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new LabelValueRow(r1, r2, r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LabelValueRow[r1];
    }
}
