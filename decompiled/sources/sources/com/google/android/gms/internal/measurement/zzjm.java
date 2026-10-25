package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzjm implements Parcelable.Creator {
    public zzjm() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r13) {
        int r12 = SafeParcelReader.validateObjectHeader(r13);
        String r2 = null;
        byte[] r3 = null;
        byte[][] r4 = null;
        byte[][] r5 = null;
        byte[][] r6 = null;
        byte[][] r7 = null;
        int[] r8 = null;
        byte[][] r9 = null;
        int[] r10 = null;
        byte[][] r11 = null;
    L4:
        if (r13.dataPosition() >= r12) goto L18;
        int r0 = SafeParcelReader.readHeader(r13);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 2: goto L17;
            case 3: goto L16;
            case 4: goto L15;
            case 5: goto L14;
            case 6: goto L13;
            case 7: goto L12;
            case 8: goto L11;
            case 9: goto L10;
            case 10: goto L9;
            case 11: goto L8;
            default: goto L7;
        };
    L8:
        r11 = SafeParcelReader.createByteArrayArray(r13, r0);
        goto L4
    L9:
        r10 = SafeParcelReader.createIntArray(r13, r0);
        goto L4
    L10:
        r9 = SafeParcelReader.createByteArrayArray(r13, r0);
        goto L4
    L11:
        r8 = SafeParcelReader.createIntArray(r13, r0);
        goto L4
    L12:
        r7 = SafeParcelReader.createByteArrayArray(r13, r0);
        goto L4
    L13:
        r6 = SafeParcelReader.createByteArrayArray(r13, r0);
        goto L4
    L14:
        r5 = SafeParcelReader.createByteArrayArray(r13, r0);
        goto L4
    L15:
        r4 = SafeParcelReader.createByteArrayArray(r13, r0);
        goto L4
    L16:
        r3 = SafeParcelReader.createByteArray(r13, r0);
        goto L4
    L17:
        r2 = SafeParcelReader.createString(r13, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r13, r0);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r13, r12);
        return new zzjl(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzjl[r1];
    }
}
