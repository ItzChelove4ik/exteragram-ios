package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzag implements Parcelable.Creator {
    public zzag() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        int r2 = 0;
        boolean r3 = false;
        boolean r4 = false;
        int r5 = 0;
        int r6 = 0;
    L4:
        if (r9.dataPosition() >= r8) goto L21;
        int r0 = SafeParcelReader.readHeader(r9);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r2 = SafeParcelReader.readInt(r9, r0);
        goto L4
    L8:
        if (r1 != 2) goto L10;
        r3 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L10:
        if (r1 != 3) goto L12;
        r4 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L12:
        if (r1 != 4) goto L14;
        r5 = SafeParcelReader.readInt(r9, r0);
        goto L4
    L14:
        if (r1 != 5) goto L15;
        r6 = SafeParcelReader.readInt(r9, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new RootTelemetryConfiguration(r2, r3, r4, r5, r6);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new RootTelemetryConfiguration[r1];
    }
}
