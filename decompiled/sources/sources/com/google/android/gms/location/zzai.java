package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzai implements Parcelable.Creator {
    public zzai() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        boolean r2 = false;
        boolean r3 = false;
        boolean r4 = false;
        boolean r5 = false;
        boolean r6 = false;
        boolean r7 = false;
    L4:
        if (r9.dataPosition() >= r8) goto L14;
        int r0 = SafeParcelReader.readHeader(r9);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L13;
            case 2: goto L12;
            case 3: goto L11;
            case 4: goto L10;
            case 5: goto L9;
            case 6: goto L8;
            default: goto L7;
        };
    L8:
        r7 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L9:
        r6 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L10:
        r5 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L11:
        r4 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L12:
        r3 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L13:
        r2 = SafeParcelReader.readBoolean(r9, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new LocationSettingsStates(r2, r3, r4, r5, r6, r7);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new LocationSettingsStates[r1];
    }
}
