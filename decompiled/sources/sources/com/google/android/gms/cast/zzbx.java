package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzbx implements Parcelable.Creator {
    public zzbx() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        int r9 = 0;
        int r12 = 0;
        int r14 = 0;
        boolean r17 = false;
        long r15 = 0;
        String r7 = null;
        String r8 = null;
        String r10 = null;
        MediaQueueContainerMetadata r11 = null;
        ArrayList r13 = null;
    L4:
        if (r19.dataPosition() >= r1) goto L18;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L17;
            case 3: goto L16;
            case 4: goto L15;
            case 5: goto L14;
            case 6: goto L13;
            case 7: goto L12;
            case 8: goto L11;
            case 9: goto L10;
            case 10: goto L9;
            case 11: goto L8;
            default: goto L7;
        };
    L8:
        r17 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L9:
        r15 = SafeParcelReader.readLong(r19, r2);
        goto L4
    L10:
        r14 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L11:
        r13 = SafeParcelReader.createTypedList(r19, r2, MediaQueueItem.CREATOR);
        goto L4
    L12:
        r12 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L13:
        r11 = (MediaQueueContainerMetadata) SafeParcelReader.createParcelable(r19, r2, MediaQueueContainerMetadata.CREATOR);
        goto L4
    L14:
        r10 = SafeParcelReader.createString(r19, r2);
        goto L4
    L15:
        r9 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L16:
        r8 = SafeParcelReader.createString(r19, r2);
        goto L4
    L17:
        r7 = SafeParcelReader.createString(r19, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new MediaQueueData(r7, r8, r9, r10, r11, r12, r13, r14, r15, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MediaQueueData[r1];
    }
}
