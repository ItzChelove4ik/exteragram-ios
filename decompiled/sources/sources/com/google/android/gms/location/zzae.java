package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zzd;

/* loaded from: classes5.dex */
public final class zzae implements Parcelable.Creator {
    public zzae() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r41) {
        int r1 = SafeParcelReader.validateObjectHeader(r41);
        WorkSource r38 = new WorkSource();
        zzd r39 = null;
        boolean r32 = false;
        int r35 = 0;
        int r36 = 0;
        boolean r37 = false;
        long r33 = -1;
        float r31 = 0.0f;
        int r30 = Integer.MAX_VALUE;
        long r26 = Long.MAX_VALUE;
        long r28 = Long.MAX_VALUE;
        long r24 = 0;
        long r22 = 600000;
        long r20 = 3600000;
        int r19 = 102;
    L4:
        if (r41.dataPosition() >= r1) goto L23;
        int r2 = SafeParcelReader.readHeader(r41);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L22;
            case 2: goto L21;
            case 3: goto L20;
            case 4: goto L7;
            case 5: goto L19;
            case 6: goto L18;
            case 7: goto L17;
            case 8: goto L16;
            case 9: goto L15;
            case 10: goto L14;
            case 11: goto L13;
            case 12: goto L12;
            case 13: goto L11;
            case 14: goto L7;
            case 15: goto L10;
            case 16: goto L9;
            case 17: goto L8;
            default: goto L7;
        };
    L8:
        r39 = (zzd) SafeParcelReader.createParcelable(r41, r2, zzd.CREATOR);
        goto L4
    L9:
        r38 = (WorkSource) SafeParcelReader.createParcelable(r41, r2, WorkSource.CREATOR);
        goto L4
    L10:
        r37 = SafeParcelReader.readBoolean(r41, r2);
        goto L4
    L11:
        r36 = SafeParcelReader.readInt(r41, r2);
        goto L4
    L12:
        r35 = SafeParcelReader.readInt(r41, r2);
        goto L4
    L13:
        r33 = SafeParcelReader.readLong(r41, r2);
        goto L4
    L14:
        r28 = SafeParcelReader.readLong(r41, r2);
        goto L4
    L15:
        r32 = SafeParcelReader.readBoolean(r41, r2);
        goto L4
    L16:
        r24 = SafeParcelReader.readLong(r41, r2);
        goto L4
    L17:
        r31 = SafeParcelReader.readFloat(r41, r2);
        goto L4
    L18:
        r30 = SafeParcelReader.readInt(r41, r2);
        goto L4
    L19:
        r26 = SafeParcelReader.readLong(r41, r2);
        goto L4
    L20:
        r22 = SafeParcelReader.readLong(r41, r2);
        goto L4
    L21:
        r20 = SafeParcelReader.readLong(r41, r2);
        goto L4
    L22:
        r19 = SafeParcelReader.readInt(r41, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r41, r2);
        goto L4
    L23:
        SafeParcelReader.ensureAtEnd(r41, r1);
        return new LocationRequest(r19, r20, r22, r24, r26, r28, r30, r31, r32, r33, r35, r36, r37, r38, r39);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LocationRequest[r1];
    }
}
