package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode;

/* loaded from: classes5.dex */
public final class zzi implements Parcelable.Creator<Barcode.PersonName> {
    public zzi() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.PersonName createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        String r2 = null;
        String r3 = null;
        String r4 = null;
        String r5 = null;
        String r6 = null;
        String r7 = null;
        String r8 = null;
    L4:
        if (r10.dataPosition() >= r9) goto L15;
        int r0 = SafeParcelReader.readHeader(r10);
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
        r8 = SafeParcelReader.createString(r10, r0);
        goto L4
    L9:
        r7 = SafeParcelReader.createString(r10, r0);
        goto L4
    L10:
        r6 = SafeParcelReader.createString(r10, r0);
        goto L4
    L11:
        r5 = SafeParcelReader.createString(r10, r0);
        goto L4
    L12:
        r4 = SafeParcelReader.createString(r10, r0);
        goto L4
    L13:
        r3 = SafeParcelReader.createString(r10, r0);
        goto L4
    L14:
        r2 = SafeParcelReader.createString(r10, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new Barcode.PersonName(r2, r3, r4, r5, r6, r7, r8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.PersonName[] newArray(int r1) {
        return new Barcode.PersonName[r1];
    }
}
