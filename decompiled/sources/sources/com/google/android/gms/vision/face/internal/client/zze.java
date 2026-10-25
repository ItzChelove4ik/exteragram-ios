package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zze implements Parcelable.Creator<zzf> {
    public zze() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzf createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        boolean r6 = false;
        boolean r7 = false;
        float r8 = -1.0f;
    L4:
        if (r10.dataPosition() >= r9) goto L14;
        int r0 = SafeParcelReader.readHeader(r10);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        r8 = SafeParcelReader.readFloat(r10, r0);
        goto L4
    L9:
        r7 = SafeParcelReader.readBoolean(r10, r0);
        goto L4
    L10:
        r6 = SafeParcelReader.readBoolean(r10, r0);
        goto L4
    L11:
        r5 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L12:
        r4 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L13:
        r3 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new zzf(r3, r4, r5, r6, r7, r8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzf[] newArray(int r1) {
        return new zzf[r1];
    }
}
