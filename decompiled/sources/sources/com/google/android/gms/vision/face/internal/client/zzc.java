package com.google.android.gms.vision.face.internal.client;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzc implements Parcelable.Creator<zza> {
    public zzc() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zza createFromParcel(Parcel r6) {
        int r5 = SafeParcelReader.validateObjectHeader(r6);
        PointF[] r0 = null;
        int r1 = 0;
    L4:
        if (r6.dataPosition() >= r5) goto L12;
        int r2 = SafeParcelReader.readHeader(r6);
        int r3 = SafeParcelReader.getFieldId(r2);
        if (r3 != 2) goto L8;
        r0 = (PointF[]) SafeParcelReader.createTypedArray(r6, r2, PointF.CREATOR);
        goto L4
    L8:
        if (r3 != 3) goto L9;
        r1 = SafeParcelReader.readInt(r6, r2);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r2);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r5);
        return new zza(r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zza[] newArray(int r1) {
        return new zza[r1];
    }
}
