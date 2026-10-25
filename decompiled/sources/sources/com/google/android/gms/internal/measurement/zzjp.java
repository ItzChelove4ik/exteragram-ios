package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzjp implements Parcelable.Creator {
    public zzjp() {
    }

    public static boolean zza(int r0) {
        if (r0 != 0) goto L5;
        return true;
    L5:
        return false;
    }

    public static boolean zzb(Object r0) {
        if (r0 != null) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r21) {
        int r1 = SafeParcelReader.validateObjectHeader(r21);
        boolean r12 = false;
        int r17 = 0;
        int r18 = 0;
        int r19 = 0;
        String r9 = null;
        String r15 = null;
        byte[] r16 = null;
        double r13 = 0.0d;
        long r10 = 0;
    L4:
        if (r21.dataPosition() >= r1) goto L17;
        int r2 = SafeParcelReader.readHeader(r21);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            case 8: goto L10;
            case 9: goto L9;
            case 10: goto L8;
            default: goto L7;
        };
    L8:
        r19 = SafeParcelReader.readInt(r21, r2);
        goto L4
    L9:
        r18 = SafeParcelReader.readInt(r21, r2);
        goto L4
    L10:
        r17 = SafeParcelReader.readInt(r21, r2);
        goto L4
    L11:
        r16 = SafeParcelReader.createByteArray(r21, r2);
        goto L4
    L12:
        r15 = SafeParcelReader.createString(r21, r2);
        goto L4
    L13:
        r13 = SafeParcelReader.readDouble(r21, r2);
        goto L4
    L14:
        r12 = SafeParcelReader.readBoolean(r21, r2);
        goto L4
    L15:
        r10 = SafeParcelReader.readLong(r21, r2);
        goto L4
    L16:
        r9 = SafeParcelReader.createString(r21, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r21, r2);
        goto L4
    L17:
        SafeParcelReader.ensureAtEnd(r21, r1);
        return new zzjo(r9, r10, r12, r13, r15, r16, r17, r18, r19);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzjo[r1];
    }
}
