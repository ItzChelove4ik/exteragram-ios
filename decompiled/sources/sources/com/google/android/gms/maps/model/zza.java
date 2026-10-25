package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zza implements Parcelable.Creator {
    public zza() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        float r0 = 0.0f;
        float r2 = 0.0f;
        LatLng r3 = null;
        float r1 = 0.0f;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 2) goto L8;
        r3 = (LatLng) SafeParcelReader.createParcelable(r8, r4, LatLng.CREATOR);
        goto L4
    L8:
        if (r5 != 3) goto L10;
        r0 = SafeParcelReader.readFloat(r8, r4);
        goto L4
    L10:
        if (r5 != 4) goto L12;
        r1 = SafeParcelReader.readFloat(r8, r4);
        goto L4
    L12:
        if (r5 != 5) goto L13;
        r2 = SafeParcelReader.readFloat(r8, r4);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new CameraPosition(r3, r0, r1, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new CameraPosition[r1];
    }
}
