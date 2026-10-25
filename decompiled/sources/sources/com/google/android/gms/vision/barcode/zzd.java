package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode;

/* loaded from: classes5.dex */
public final class zzd implements Parcelable.Creator<Barcode.CalendarDateTime> {
    public zzd() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.CalendarDateTime createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        boolean r9 = false;
        String r10 = null;
    L4:
        if (r12.dataPosition() >= r11) goto L16;
        int r0 = SafeParcelReader.readHeader(r12);
        switch(SafeParcelReader.getFieldId(r0)) {
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
        r10 = SafeParcelReader.createString(r12, r0);
        goto L4
    L9:
        r9 = SafeParcelReader.readBoolean(r12, r0);
        goto L4
    L10:
        r8 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L11:
        r7 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L12:
        r6 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L13:
        r5 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L14:
        r4 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L15:
        r3 = SafeParcelReader.readInt(r12, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r12, r0);
        goto L4
    L16:
        SafeParcelReader.ensureAtEnd(r12, r11);
        return new Barcode.CalendarDateTime(r3, r4, r5, r6, r7, r8, r9, r10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.CalendarDateTime[] newArray(int r1) {
        return new Barcode.CalendarDateTime[r1];
    }
}
