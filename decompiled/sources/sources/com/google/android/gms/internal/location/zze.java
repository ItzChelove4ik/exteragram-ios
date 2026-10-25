package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zze implements Parcelable.Creator {
    public zze() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        String r4 = null;
        String r5 = null;
        String r6 = null;
        ArrayList r7 = null;
        zzd r8 = null;
        int r3 = 0;
    L4:
        if (r10.dataPosition() >= r9) goto L24;
        int r0 = SafeParcelReader.readHeader(r10);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 1) goto L8;
        r3 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L8:
        if (r1 != 3) goto L10;
        r4 = SafeParcelReader.createString(r10, r0);
        goto L4
    L10:
        if (r1 != 4) goto L12;
        r5 = SafeParcelReader.createString(r10, r0);
        goto L4
    L12:
        if (r1 != 6) goto L14;
        r6 = SafeParcelReader.createString(r10, r0);
        goto L4
    L14:
        if (r1 != 7) goto L16;
        r8 = (zzd) SafeParcelReader.createParcelable(r10, r0, zzd.CREATOR);
        goto L4
    L16:
        if (r1 != 8) goto L17;
        r7 = SafeParcelReader.createTypedList(r10, r0, Feature.CREATOR);
        goto L4
    L17:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L24:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new zzd(r3, r4, r5, r6, r7, r8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzd[r1];
    }
}
