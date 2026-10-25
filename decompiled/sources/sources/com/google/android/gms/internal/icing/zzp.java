package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzp implements Parcelable.Creator<zzo> {
    public zzp() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzo createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        Status r0 = null;
        ArrayList r1 = null;
        String[] r2 = null;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 1) goto L8;
        r0 = (Status) SafeParcelReader.createParcelable(r7, r3, Status.CREATOR);
        goto L4
    L8:
        if (r4 != 2) goto L10;
        r1 = SafeParcelReader.createTypedList(r7, r3, zzx.CREATOR);
        goto L4
    L10:
        if (r4 != 3) goto L11;
        r2 = SafeParcelReader.createStringArray(r7, r3);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new zzo(r0, r1, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzo[] newArray(int r1) {
        return new zzo[r1];
    }
}
