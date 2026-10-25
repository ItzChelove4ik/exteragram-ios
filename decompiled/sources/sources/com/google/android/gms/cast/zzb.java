package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzb implements Parcelable.Creator {
    public zzb() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r15) {
        int r14 = SafeParcelReader.validateObjectHeader(r15);
        boolean r10 = false;
        boolean r12 = false;
        boolean r13 = false;
        String r7 = null;
        String[] r11 = null;
        long r5 = 0;
        long r8 = 0;
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
        r13 = SafeParcelReader.readBoolean(r15, r0);
        goto L4
    L9:
        r12 = SafeParcelReader.readBoolean(r15, r0);
        goto L4
    L10:
        r11 = SafeParcelReader.createStringArray(r15, r0);
        goto L4
    L11:
        r10 = SafeParcelReader.readBoolean(r15, r0);
        goto L4
    L12:
        r8 = SafeParcelReader.readLong(r15, r0);
        goto L4
    L13:
        r7 = SafeParcelReader.createString(r15, r0);
        goto L4
    L14:
        r5 = SafeParcelReader.readLong(r15, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r15, r0);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r15, r14);
        return new AdBreakInfo(r5, r7, r8, r10, r11, r12, r13);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new AdBreakInfo[r1];
    }
}
