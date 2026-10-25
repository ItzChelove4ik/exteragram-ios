package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzby implements Parcelable.Creator {
    public zzby() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        MediaInfo r7 = null;
        long[] r16 = null;
        String r17 = null;
        double r10 = 0.0d;
        double r12 = 0.0d;
        double r14 = 0.0d;
        int r8 = 0;
        boolean r9 = false;
    L4:
        if (r19.dataPosition() >= r1) goto L16;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L15;
            case 3: goto L14;
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L11;
            case 7: goto L10;
            case 8: goto L9;
            case 9: goto L8;
            default: goto L7;
        };
    L8:
        r17 = SafeParcelReader.createString(r19, r2);
        goto L4
    L9:
        r16 = SafeParcelReader.createLongArray(r19, r2);
        goto L4
    L10:
        r14 = SafeParcelReader.readDouble(r19, r2);
        goto L4
    L11:
        r12 = SafeParcelReader.readDouble(r19, r2);
        goto L4
    L12:
        r10 = SafeParcelReader.readDouble(r19, r2);
        goto L4
    L13:
        r9 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L14:
        r8 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L15:
        r7 = (MediaInfo) SafeParcelReader.createParcelable(r19, r2, MediaInfo.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L16:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new MediaQueueItem(r7, r8, r9, r10, r12, r14, r16, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MediaQueueItem[r1];
    }
}
