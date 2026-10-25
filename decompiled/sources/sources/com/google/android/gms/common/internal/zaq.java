package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zaq implements Parcelable.Creator {
    public zaq() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r20) {
        int r1 = SafeParcelReader.validateObjectHeader(r20);
        int r18 = -1;
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
        int r17 = 0;
        String r15 = null;
        String r16 = null;
        long r11 = 0;
        long r13 = 0;
    L4:
        if (r20.dataPosition() >= r1) goto L17;
        int r2 = SafeParcelReader.readHeader(r20);
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
        r18 = SafeParcelReader.readInt(r20, r2);
        goto L4
    L9:
        r17 = SafeParcelReader.readInt(r20, r2);
        goto L4
    L10:
        r16 = SafeParcelReader.createString(r20, r2);
        goto L4
    L11:
        r15 = SafeParcelReader.createString(r20, r2);
        goto L4
    L12:
        r13 = SafeParcelReader.readLong(r20, r2);
        goto L4
    L13:
        r11 = SafeParcelReader.readLong(r20, r2);
        goto L4
    L14:
        r10 = SafeParcelReader.readInt(r20, r2);
        goto L4
    L15:
        r9 = SafeParcelReader.readInt(r20, r2);
        goto L4
    L16:
        r8 = SafeParcelReader.readInt(r20, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r20, r2);
        goto L4
    L17:
        SafeParcelReader.ensureAtEnd(r20, r1);
        return new MethodInvocation(r8, r9, r10, r11, r13, r15, r16, r17, r18);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MethodInvocation[r1];
    }
}
