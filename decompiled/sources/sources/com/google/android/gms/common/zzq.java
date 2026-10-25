package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzq implements Parcelable.Creator {
    public zzq() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r11) {
        int r10 = SafeParcelReader.validateObjectHeader(r11);
        boolean r4 = false;
        boolean r5 = false;
        boolean r7 = false;
        boolean r8 = false;
        boolean r9 = false;
        String r3 = null;
        IBinder r6 = null;
    L4:
        if (r11.dataPosition() >= r10) goto L15;
        int r0 = SafeParcelReader.readHeader(r11);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L7;
            case 8: goto L8;
            default: goto L7;
        };
    L8:
        r9 = SafeParcelReader.readBoolean(r11, r0);
        goto L4
    L9:
        r8 = SafeParcelReader.readBoolean(r11, r0);
        goto L4
    L10:
        r7 = SafeParcelReader.readBoolean(r11, r0);
        goto L4
    L11:
        r6 = SafeParcelReader.readIBinder(r11, r0);
        goto L4
    L12:
        r5 = SafeParcelReader.readBoolean(r11, r0);
        goto L4
    L13:
        r4 = SafeParcelReader.readBoolean(r11, r0);
        goto L4
    L14:
        r3 = SafeParcelReader.createString(r11, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r11, r0);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r11, r10);
        return new zzp(r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzp[r1];
    }
}
