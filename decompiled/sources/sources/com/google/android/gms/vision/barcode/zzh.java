package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode;

/* loaded from: classes5.dex */
public final class zzh implements Parcelable.Creator<Barcode.DriverLicense> {
    public zzh() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.DriverLicense createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        String r4 = null;
        String r5 = null;
        String r6 = null;
        String r7 = null;
        String r8 = null;
        String r9 = null;
        String r10 = null;
        String r11 = null;
        String r12 = null;
        String r13 = null;
        String r14 = null;
        String r15 = null;
        String r16 = null;
        String r17 = null;
    L4:
        if (r19.dataPosition() >= r1) goto L22;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L21;
            case 3: goto L20;
            case 4: goto L19;
            case 5: goto L18;
            case 6: goto L17;
            case 7: goto L16;
            case 8: goto L15;
            case 9: goto L14;
            case 10: goto L13;
            case 11: goto L12;
            case 12: goto L11;
            case 13: goto L10;
            case 14: goto L9;
            case 15: goto L8;
            default: goto L7;
        };
    L8:
        r17 = SafeParcelReader.createString(r19, r2);
        goto L4
    L9:
        r16 = SafeParcelReader.createString(r19, r2);
        goto L4
    L10:
        r15 = SafeParcelReader.createString(r19, r2);
        goto L4
    L11:
        r14 = SafeParcelReader.createString(r19, r2);
        goto L4
    L12:
        r13 = SafeParcelReader.createString(r19, r2);
        goto L4
    L13:
        r12 = SafeParcelReader.createString(r19, r2);
        goto L4
    L14:
        r11 = SafeParcelReader.createString(r19, r2);
        goto L4
    L15:
        r10 = SafeParcelReader.createString(r19, r2);
        goto L4
    L16:
        r9 = SafeParcelReader.createString(r19, r2);
        goto L4
    L17:
        r8 = SafeParcelReader.createString(r19, r2);
        goto L4
    L18:
        r7 = SafeParcelReader.createString(r19, r2);
        goto L4
    L19:
        r6 = SafeParcelReader.createString(r19, r2);
        goto L4
    L20:
        r5 = SafeParcelReader.createString(r19, r2);
        goto L4
    L21:
        r4 = SafeParcelReader.createString(r19, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L22:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new Barcode.DriverLicense(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.DriverLicense[] newArray(int r1) {
        return new Barcode.DriverLicense[r1];
    }
}
