package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzm implements Parcelable.Creator {
    public zzm() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r18) {
        int r1 = SafeParcelReader.validateObjectHeader(r18);
        int r5 = 0;
        byte r12 = 0;
        byte r13 = 0;
        byte r14 = 0;
        byte r15 = 0;
        String r6 = null;
        String r7 = null;
        String r8 = null;
        String r9 = null;
        String r10 = null;
        String r11 = null;
        String r16 = null;
    L4:
        if (r18.dataPosition() >= r1) goto L20;
        int r2 = SafeParcelReader.readHeader(r18);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L19;
            case 3: goto L18;
            case 4: goto L17;
            case 5: goto L16;
            case 6: goto L15;
            case 7: goto L14;
            case 8: goto L13;
            case 9: goto L12;
            case 10: goto L11;
            case 11: goto L10;
            case 12: goto L9;
            case 13: goto L8;
            default: goto L7;
        };
    L8:
        r16 = SafeParcelReader.createString(r18, r2);
        goto L4
    L9:
        r15 = SafeParcelReader.readByte(r18, r2);
        goto L4
    L10:
        r14 = SafeParcelReader.readByte(r18, r2);
        goto L4
    L11:
        r13 = SafeParcelReader.readByte(r18, r2);
        goto L4
    L12:
        r12 = SafeParcelReader.readByte(r18, r2);
        goto L4
    L13:
        r11 = SafeParcelReader.createString(r18, r2);
        goto L4
    L14:
        r10 = SafeParcelReader.createString(r18, r2);
        goto L4
    L15:
        r9 = SafeParcelReader.createString(r18, r2);
        goto L4
    L16:
        r8 = SafeParcelReader.createString(r18, r2);
        goto L4
    L17:
        r7 = SafeParcelReader.createString(r18, r2);
        goto L4
    L18:
        r6 = SafeParcelReader.createString(r18, r2);
        goto L4
    L19:
        r5 = SafeParcelReader.readInt(r18, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r18, r2);
        goto L4
    L20:
        SafeParcelReader.ensureAtEnd(r18, r1);
        return new zzl(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzl[r1];
    }
}
