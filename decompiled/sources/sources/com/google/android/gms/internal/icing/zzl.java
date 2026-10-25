package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzl implements Parcelable.Creator<zzk> {
    public zzl() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzk createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        String r0 = null;
        byte[] r2 = null;
        int r3 = -1;
        zzs r1 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r0 = SafeParcelReader.createString(r8, r4);
        goto L4
    L8:
        if (r5 != 3) goto L10;
        r1 = (zzs) SafeParcelReader.createParcelable(r8, r4, zzs.CREATOR);
        goto L4
    L10:
        if (r5 != 4) goto L12;
        r3 = SafeParcelReader.readInt(r8, r4);
        goto L4
    L12:
        if (r5 != 5) goto L13;
        r2 = SafeParcelReader.createByteArray(r8, r4);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new zzk(r0, r1, r3, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzk[] newArray(int r1) {
        return new zzk[r1];
    }
}
