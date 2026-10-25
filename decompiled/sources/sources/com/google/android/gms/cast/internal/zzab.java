package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzab implements Parcelable.Creator {
    public zzab() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r14) {
        int r13 = SafeParcelReader.validateObjectHeader(r14);
        int r3 = 0;
        boolean r4 = false;
        boolean r5 = false;
        boolean r11 = false;
        boolean r12 = false;
        String r6 = null;
        String r7 = null;
        String r8 = null;
        String r9 = null;
        String r10 = null;
    L4:
        if (r14.dataPosition() >= r13) goto L18;
        int r0 = SafeParcelReader.readHeader(r14);
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
        r12 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L9:
        r11 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L10:
        r10 = SafeParcelReader.createString(r14, r0);
        goto L4
    L11:
        r9 = SafeParcelReader.createString(r14, r0);
        goto L4
    L12:
        r8 = SafeParcelReader.createString(r14, r0);
        goto L4
    L13:
        r7 = SafeParcelReader.createString(r14, r0);
        goto L4
    L14:
        r6 = SafeParcelReader.createString(r14, r0);
        goto L4
    L15:
        r5 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L16:
        r4 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L17:
        r3 = SafeParcelReader.readInt(r14, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r14, r0);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r14, r13);
        return new zzaa(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzaa[r1];
    }
}
