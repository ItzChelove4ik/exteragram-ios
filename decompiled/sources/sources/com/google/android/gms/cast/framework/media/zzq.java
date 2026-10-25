package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzq implements Parcelable.Creator {
    public zzq() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        String r0 = null;
        int r2 = 0;
        String r1 = null;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 2) goto L8;
        r0 = SafeParcelReader.createString(r7, r3);
        goto L4
    L8:
        if (r4 != 3) goto L10;
        r2 = SafeParcelReader.readInt(r7, r3);
        goto L4
    L10:
        if (r4 != 4) goto L11;
        r1 = SafeParcelReader.createString(r7, r3);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new NotificationAction(r0, r2, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new NotificationAction[r1];
    }
}
