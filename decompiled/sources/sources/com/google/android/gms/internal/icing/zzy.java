package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzy implements Parcelable.Creator<zzx> {
    public zzy() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzx createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        zzi r8 = null;
        String r12 = null;
        zzg r13 = null;
        String r17 = null;
        long r9 = 0;
        int r11 = 0;
        boolean r14 = false;
        int r16 = 0;
        int r15 = -1;
    L4:
        if (r19.dataPosition() >= r1) goto L17;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L16;
            case 2: goto L15;
            case 3: goto L14;
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L11;
            case 7: goto L10;
            case 8: goto L9;
            case 9: goto L8;
            default: goto L7;
        };
    L8:
        r17 = SafeParcelReader.createString(r19, r2);
        goto L4
    L9:
        r16 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L10:
        r15 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L11:
        r14 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L12:
        r13 = (zzg) SafeParcelReader.createParcelable(r19, r2, zzg.CREATOR);
        goto L4
    L13:
        r12 = SafeParcelReader.createString(r19, r2);
        goto L4
    L14:
        r11 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L15:
        r9 = SafeParcelReader.readLong(r19, r2);
        goto L4
    L16:
        r8 = (zzi) SafeParcelReader.createParcelable(r19, r2, zzi.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L17:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new zzx(r8, r9, r11, r12, r13, r14, r15, r16, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzx[] newArray(int r1) {
        return new zzx[r1];
    }
}
