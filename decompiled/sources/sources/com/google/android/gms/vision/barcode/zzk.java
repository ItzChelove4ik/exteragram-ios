package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode;

/* loaded from: classes5.dex */
public final class zzk implements Parcelable.Creator<Barcode.Sms> {
    public zzk() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.Sms createFromParcel(Parcel r6) {
        int r5 = SafeParcelReader.validateObjectHeader(r6);
        String r0 = null;
        String r1 = null;
    L4:
        if (r6.dataPosition() >= r5) goto L12;
        int r2 = SafeParcelReader.readHeader(r6);
        int r3 = SafeParcelReader.getFieldId(r2);
        if (r3 != 2) goto L8;
        r0 = SafeParcelReader.createString(r6, r2);
        goto L4
    L8:
        if (r3 != 3) goto L9;
        r1 = SafeParcelReader.createString(r6, r2);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r2);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r5);
        return new Barcode.Sms(r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.Sms[] newArray(int r1) {
        return new Barcode.Sms[r1];
    }
}
