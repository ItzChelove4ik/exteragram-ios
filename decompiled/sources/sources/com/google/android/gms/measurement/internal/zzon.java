package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzon implements Parcelable.Creator {
    public zzon() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r15) {
        int r14 = SafeParcelReader.validateObjectHeader(r15);
        byte[] r7 = null;
        String r8 = null;
        Bundle r9 = null;
        String r13 = null;
        long r5 = 0;
        long r11 = 0;
        int r10 = 0;
    L4:
        if (r15.dataPosition() >= r14) goto L15;
        int r0 = SafeParcelReader.readHeader(r15);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        r13 = SafeParcelReader.createString(r15, r0);
        goto L4
    L9:
        r11 = SafeParcelReader.readLong(r15, r0);
        goto L4
    L10:
        r10 = SafeParcelReader.readInt(r15, r0);
        goto L4
    L11:
        r9 = SafeParcelReader.createBundle(r15, r0);
        goto L4
    L12:
        r8 = SafeParcelReader.createString(r15, r0);
        goto L4
    L13:
        r7 = SafeParcelReader.createByteArray(r15, r0);
        goto L4
    L14:
        r5 = SafeParcelReader.readLong(r15, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r15, r0);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r15, r14);
        return new zzom(r5, r7, r8, r9, r10, r11, r13);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzom[r1];
    }
}
