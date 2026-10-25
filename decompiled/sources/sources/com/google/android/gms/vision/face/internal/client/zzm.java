package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzm implements Parcelable.Creator<LandmarkParcel> {
    public zzm() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LandmarkParcel createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        int r0 = 0;
        float r2 = 0.0f;
        float r3 = 0.0f;
        int r1 = 0;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r0 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L8:
        if (r5 != 2) goto L10;
        r2 = SafeParcelReader.readFloat(r8, r4);
        goto L4
    L10:
        if (r5 != 3) goto L12;
        r3 = SafeParcelReader.readFloat(r8, r4);
        goto L4
    L12:
        if (r5 != 4) goto L13;
        r1 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new LandmarkParcel(r0, r2, r3, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LandmarkParcel[] newArray(int r1) {
        return new LandmarkParcel[r1];
    }
}
