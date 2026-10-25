package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzad implements Parcelable.Creator {
    public zzad() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r15) {
        int r14 = SafeParcelReader.validateObjectHeader(r15);
        double r5 = 0.0d;
        double r12 = 0.0d;
        ApplicationMetadata r9 = null;
        com.google.android.gms.cast.zzao r11 = null;
        boolean r7 = false;
        int r8 = 0;
        int r10 = 0;
    L4:
        if (r15.dataPosition() >= r14) goto L15;
        int r0 = SafeParcelReader.readHeader(r15);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L12;
            case 5: goto L11;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L8;
            default: goto L7;
        };
    L8:
        r12 = SafeParcelReader.readDouble(r15, r0);
        goto L4
    L9:
        r11 = (com.google.android.gms.cast.zzao) SafeParcelReader.createParcelable(r15, r0, com.google.android.gms.cast.zzao.CREATOR);
        goto L4
    L10:
        r10 = SafeParcelReader.readInt(r15, r0);
        goto L4
    L11:
        r9 = (ApplicationMetadata) SafeParcelReader.createParcelable(r15, r0, ApplicationMetadata.CREATOR);
        goto L4
    L12:
        r8 = SafeParcelReader.readInt(r15, r0);
        goto L4
    L13:
        r7 = SafeParcelReader.readBoolean(r15, r0);
        goto L4
    L14:
        r5 = SafeParcelReader.readDouble(r15, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r15, r0);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r15, r14);
        return new zzac(r5, r7, r8, r9, r10, r11, r12);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzac[r1];
    }
}
