package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzdj implements Parcelable.Creator {
    public zzdj() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        Uri r0 = null;
        Bundle r1 = null;
        byte[] r2 = null;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 2) goto L8;
        r0 = (Uri) SafeParcelReader.createParcelable(r7, r3, Uri.CREATOR);
        goto L4
    L8:
        if (r4 != 4) goto L10;
        r1 = SafeParcelReader.createBundle(r7, r3);
        goto L4
    L10:
        if (r4 != 5) goto L11;
        r2 = SafeParcelReader.createByteArray(r7, r3);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new zzdi(r0, r1, r2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzdi[r1];
    }
}
