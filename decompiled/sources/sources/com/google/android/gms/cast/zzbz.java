package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzbz implements Parcelable.Creator {
    public zzbz() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r36) {
        int r1 = SafeParcelReader.validateObjectHeader(r36);
        MediaInfo r9 = null;
        long[] r24 = null;
        String r27 = null;
        ArrayList r29 = null;
        AdBreakStatus r31 = null;
        VideoInfo r32 = null;
        MediaLiveSeekableRange r33 = null;
        MediaQueueData r34 = null;
        int r12 = 0;
        int r15 = 0;
        int r16 = 0;
        boolean r23 = false;
        int r25 = 0;
        int r26 = 0;
        int r28 = 0;
        boolean r30 = false;
        double r13 = 0.0d;
        double r21 = 0.0d;
        long r10 = 0;
        long r17 = 0;
        long r19 = 0;
    L4:
        if (r36.dataPosition() >= r1) goto L29;
        int r2 = SafeParcelReader.readHeader(r36);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L28;
            case 3: goto L27;
            case 4: goto L26;
            case 5: goto L25;
            case 6: goto L24;
            case 7: goto L23;
            case 8: goto L22;
            case 9: goto L21;
            case 10: goto L20;
            case 11: goto L19;
            case 12: goto L18;
            case 13: goto L17;
            case 14: goto L16;
            case 15: goto L15;
            case 16: goto L14;
            case 17: goto L13;
            case 18: goto L12;
            case 19: goto L11;
            case 20: goto L10;
            case 21: goto L9;
            case 22: goto L8;
            default: goto L7;
        };
    L8:
        r34 = (MediaQueueData) SafeParcelReader.createParcelable(r36, r2, MediaQueueData.CREATOR);
        goto L4
    L9:
        r33 = (MediaLiveSeekableRange) SafeParcelReader.createParcelable(r36, r2, MediaLiveSeekableRange.CREATOR);
        goto L4
    L10:
        r32 = (VideoInfo) SafeParcelReader.createParcelable(r36, r2, VideoInfo.CREATOR);
        goto L4
    L11:
        r31 = (AdBreakStatus) SafeParcelReader.createParcelable(r36, r2, AdBreakStatus.CREATOR);
        goto L4
    L12:
        r30 = SafeParcelReader.readBoolean(r36, r2);
        goto L4
    L13:
        r29 = SafeParcelReader.createTypedList(r36, r2, MediaQueueItem.CREATOR);
        goto L4
    L14:
        r28 = SafeParcelReader.readInt(r36, r2);
        goto L4
    L15:
        r27 = SafeParcelReader.createString(r36, r2);
        goto L4
    L16:
        r26 = SafeParcelReader.readInt(r36, r2);
        goto L4
    L17:
        r25 = SafeParcelReader.readInt(r36, r2);
        goto L4
    L18:
        r24 = SafeParcelReader.createLongArray(r36, r2);
        goto L4
    L19:
        r23 = SafeParcelReader.readBoolean(r36, r2);
        goto L4
    L20:
        r21 = SafeParcelReader.readDouble(r36, r2);
        goto L4
    L21:
        r19 = SafeParcelReader.readLong(r36, r2);
        goto L4
    L22:
        r17 = SafeParcelReader.readLong(r36, r2);
        goto L4
    L23:
        r16 = SafeParcelReader.readInt(r36, r2);
        goto L4
    L24:
        r15 = SafeParcelReader.readInt(r36, r2);
        goto L4
    L25:
        r13 = SafeParcelReader.readDouble(r36, r2);
        goto L4
    L26:
        r12 = SafeParcelReader.readInt(r36, r2);
        goto L4
    L27:
        r10 = SafeParcelReader.readLong(r36, r2);
        goto L4
    L28:
        r9 = (MediaInfo) SafeParcelReader.createParcelable(r36, r2, MediaInfo.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r36, r2);
        goto L4
    L29:
        SafeParcelReader.ensureAtEnd(r36, r1);
        return new MediaStatus(r9, r10, r12, r13, r15, r16, r17, r19, r21, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MediaStatus[r1];
    }
}
