package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzbt implements Parcelable.Creator {
    public zzbt() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r24) {
        int r1 = SafeParcelReader.validateObjectHeader(r24);
        long r11 = 0;
        long r21 = 0;
        MediaInfo r8 = null;
        MediaQueueData r9 = null;
        Boolean r10 = null;
        long[] r15 = null;
        String r16 = null;
        String r17 = null;
        String r18 = null;
        String r19 = null;
        String r20 = null;
        double r13 = 0.0d;
    L4:
        if (r24.dataPosition() >= r1) goto L20;
        int r2 = SafeParcelReader.readHeader(r24);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L19;
            case 3: goto L18;
            case 4: goto L17;
            case 5: goto L16;
            case 6: goto L15;
            case 7: goto L14;
            case 8: goto L13;
            case 9: goto L12;
            case 10: goto L11;
            case 11: goto L10;
            case 12: goto L9;
            case 13: goto L8;
            default: goto L7;
        };
    L8:
        r21 = SafeParcelReader.readLong(r24, r2);
        goto L4
    L9:
        r20 = SafeParcelReader.createString(r24, r2);
        goto L4
    L10:
        r19 = SafeParcelReader.createString(r24, r2);
        goto L4
    L11:
        r18 = SafeParcelReader.createString(r24, r2);
        goto L4
    L12:
        r17 = SafeParcelReader.createString(r24, r2);
        goto L4
    L13:
        r16 = SafeParcelReader.createString(r24, r2);
        goto L4
    L14:
        r15 = SafeParcelReader.createLongArray(r24, r2);
        goto L4
    L15:
        r13 = SafeParcelReader.readDouble(r24, r2);
        goto L4
    L16:
        r11 = SafeParcelReader.readLong(r24, r2);
        goto L4
    L17:
        r10 = SafeParcelReader.readBooleanObject(r24, r2);
        goto L4
    L18:
        r9 = (MediaQueueData) SafeParcelReader.createParcelable(r24, r2, MediaQueueData.CREATOR);
        goto L4
    L19:
        r8 = (MediaInfo) SafeParcelReader.createParcelable(r24, r2, MediaInfo.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r24, r2);
        goto L4
    L20:
        SafeParcelReader.ensureAtEnd(r24, r1);
        return new MediaLoadRequestData(r8, r9, r10, r11, r13, r15, r16, r17, r18, r19, r20, r21);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MediaLoadRequestData[r1];
    }
}
