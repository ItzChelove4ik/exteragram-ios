package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.vision.barcode.Barcode;

/* loaded from: classes5.dex */
public final class zzj implements Parcelable.Creator<Barcode.GeoPoint> {
    public zzj() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.GeoPoint createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        double r0 = 0.0d;
        double r2 = 0.0d;
    L4:
        if (r8.dataPosition() >= r7) goto L12;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 2) goto L8;
        r0 = SafeParcelReader.readDouble(r8, r4);
        goto L4
    L8:
        if (r5 != 3) goto L9;
        r2 = SafeParcelReader.readDouble(r8, r4);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new Barcode.GeoPoint(r0, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Barcode.GeoPoint[] newArray(int r1) {
        return new Barcode.GeoPoint[r1];
    }
}
