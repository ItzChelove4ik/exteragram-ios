package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zza implements Parcelable.Creator {
    public zza() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        PendingIntent r5 = null;
        String r6 = null;
        Integer r7 = null;
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r9.dataPosition() >= r8) goto L21;
        int r0 = SafeParcelReader.readHeader(r9);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r3 = SafeParcelReader.readInt(r9, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r4 = SafeParcelReader.readInt(r9, r0);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r5 = (PendingIntent) SafeParcelReader.createParcelable(r9, r0, PendingIntent.CREATOR);
        goto L4
    L12:
        if (r1 != 4) goto L14;
        r6 = SafeParcelReader.createString(r9, r0);
        goto L4
    L14:
        if (r1 != 5) goto L15;
        r7 = SafeParcelReader.readIntegerObject(r9, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new ConnectionResult(r3, r4, r5, r6, r7);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new ConnectionResult[r1];
    }
}
