package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzai implements Parcelable.Creator {
    public zzai() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r22) {
        int r1 = SafeParcelReader.validateObjectHeader(r22);
        String r7 = null;
        String r8 = null;
        zzpl r9 = null;
        String r13 = null;
        zzbh r14 = null;
        zzbh r17 = null;
        zzbh r20 = null;
        long r10 = 0;
        long r15 = 0;
        long r18 = 0;
        boolean r12 = false;
    L4:
        if (r22.dataPosition() >= r1) goto L19;
        int r2 = SafeParcelReader.readHeader(r22);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L18;
            case 3: goto L17;
            case 4: goto L16;
            case 5: goto L15;
            case 6: goto L14;
            case 7: goto L13;
            case 8: goto L12;
            case 9: goto L11;
            case 10: goto L10;
            case 11: goto L9;
            case 12: goto L8;
            default: goto L7;
        };
    L8:
        r20 = (zzbh) SafeParcelReader.createParcelable(r22, r2, zzbh.CREATOR);
        goto L4
    L9:
        r18 = SafeParcelReader.readLong(r22, r2);
        goto L4
    L10:
        r17 = (zzbh) SafeParcelReader.createParcelable(r22, r2, zzbh.CREATOR);
        goto L4
    L11:
        r15 = SafeParcelReader.readLong(r22, r2);
        goto L4
    L12:
        r14 = (zzbh) SafeParcelReader.createParcelable(r22, r2, zzbh.CREATOR);
        goto L4
    L13:
        r13 = SafeParcelReader.createString(r22, r2);
        goto L4
    L14:
        r12 = SafeParcelReader.readBoolean(r22, r2);
        goto L4
    L15:
        r10 = SafeParcelReader.readLong(r22, r2);
        goto L4
    L16:
        r9 = (zzpl) SafeParcelReader.createParcelable(r22, r2, zzpl.CREATOR);
        goto L4
    L17:
        r8 = SafeParcelReader.createString(r22, r2);
        goto L4
    L18:
        r7 = SafeParcelReader.createString(r22, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r22, r2);
        goto L4
    L19:
        SafeParcelReader.ensureAtEnd(r22, r1);
        return new zzah(r7, r8, r9, r10, r12, r13, r14, r15, r17, r18, r20);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzah[r1];
    }
}
