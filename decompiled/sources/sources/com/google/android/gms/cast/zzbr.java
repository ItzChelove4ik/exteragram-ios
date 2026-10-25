package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzbr implements Parcelable.Creator {
    public zzbr() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r27) {
        int r1 = SafeParcelReader.validateObjectHeader(r27);
        String r7 = null;
        String r9 = null;
        MediaMetadata r10 = null;
        ArrayList r13 = null;
        TextTrackStyle r14 = null;
        String r15 = null;
        ArrayList r16 = null;
        ArrayList r17 = null;
        String r18 = null;
        VastAdsRequest r19 = null;
        String r22 = null;
        String r23 = null;
        String r24 = null;
        String r25 = null;
        long r11 = 0;
        long r20 = 0;
        int r8 = 0;
    L4:
        if (r27.dataPosition() >= r1) goto L25;
        int r2 = SafeParcelReader.readHeader(r27);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L24;
            case 3: goto L23;
            case 4: goto L22;
            case 5: goto L21;
            case 6: goto L20;
            case 7: goto L19;
            case 8: goto L18;
            case 9: goto L17;
            case 10: goto L16;
            case 11: goto L15;
            case 12: goto L14;
            case 13: goto L13;
            case 14: goto L12;
            case 15: goto L11;
            case 16: goto L10;
            case 17: goto L9;
            case 18: goto L8;
            default: goto L7;
        };
    L8:
        r25 = SafeParcelReader.createString(r27, r2);
        goto L4
    L9:
        r24 = SafeParcelReader.createString(r27, r2);
        goto L4
    L10:
        r23 = SafeParcelReader.createString(r27, r2);
        goto L4
    L11:
        r22 = SafeParcelReader.createString(r27, r2);
        goto L4
    L12:
        r20 = SafeParcelReader.readLong(r27, r2);
        goto L4
    L13:
        r19 = (VastAdsRequest) SafeParcelReader.createParcelable(r27, r2, VastAdsRequest.CREATOR);
        goto L4
    L14:
        r18 = SafeParcelReader.createString(r27, r2);
        goto L4
    L15:
        r17 = SafeParcelReader.createTypedList(r27, r2, AdBreakClipInfo.CREATOR);
        goto L4
    L16:
        r16 = SafeParcelReader.createTypedList(r27, r2, AdBreakInfo.CREATOR);
        goto L4
    L17:
        r15 = SafeParcelReader.createString(r27, r2);
        goto L4
    L18:
        r14 = (TextTrackStyle) SafeParcelReader.createParcelable(r27, r2, TextTrackStyle.CREATOR);
        goto L4
    L19:
        r13 = SafeParcelReader.createTypedList(r27, r2, MediaTrack.CREATOR);
        goto L4
    L20:
        r11 = SafeParcelReader.readLong(r27, r2);
        goto L4
    L21:
        r10 = (MediaMetadata) SafeParcelReader.createParcelable(r27, r2, MediaMetadata.CREATOR);
        goto L4
    L22:
        r9 = SafeParcelReader.createString(r27, r2);
        goto L4
    L23:
        r8 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L24:
        r7 = SafeParcelReader.createString(r27, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r27, r2);
        goto L4
    L25:
        SafeParcelReader.ensureAtEnd(r27, r1);
        return new MediaInfo(r7, r8, r9, r10, r11, r13, r14, r15, r16, r17, r18, r19, r20, r22, r23, r24, r25);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MediaInfo[r1];
    }
}
