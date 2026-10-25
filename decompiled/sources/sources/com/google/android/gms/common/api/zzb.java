package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzb implements Parcelable.Creator {
    public zzb() {
    }

    public static final ApiMetadata zza(Parcel r6) {
        int r0 = SafeParcelReader.validateObjectHeader(r6);
        boolean r1 = false;
        ComplianceOptions r2 = null;
    L4:
        if (r6.dataPosition() >= r0) goto L12;
        int r3 = SafeParcelReader.readHeader(r6);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 1) goto L8;
        r2 = (ComplianceOptions) SafeParcelReader.createParcelable(r6, r3, ComplianceOptions.CREATOR);
        goto L4
    L8:
        if (r4 != 2) goto L9;
        r1 = SafeParcelReader.readBoolean(r6, r3);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r3);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r0);
        return new ApiMetadata(r2, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
        return zza(r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new ApiMetadata[r1];
    }
}
