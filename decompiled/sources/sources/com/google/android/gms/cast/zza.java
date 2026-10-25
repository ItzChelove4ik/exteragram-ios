package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zza implements Parcelable.Creator {
    public zza() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r21) {
        int r1 = SafeParcelReader.validateObjectHeader(r21);
        String r6 = null;
        String r7 = null;
        String r10 = null;
        String r11 = null;
        String r12 = null;
        String r13 = null;
        String r14 = null;
        String r15 = null;
        String r18 = null;
        VastAdsRequest r19 = null;
        long r8 = 0;
        long r16 = 0;
    L4:
        if (r21.dataPosition() >= r1) goto L20;
        int r2 = SafeParcelReader.readHeader(r21);
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
        r19 = (VastAdsRequest) SafeParcelReader.createParcelable(r21, r2, VastAdsRequest.CREATOR);
        goto L4
    L9:
        r18 = SafeParcelReader.createString(r21, r2);
        goto L4
    L10:
        r16 = SafeParcelReader.readLong(r21, r2);
        goto L4
    L11:
        r15 = SafeParcelReader.createString(r21, r2);
        goto L4
    L12:
        r14 = SafeParcelReader.createString(r21, r2);
        goto L4
    L13:
        r13 = SafeParcelReader.createString(r21, r2);
        goto L4
    L14:
        r12 = SafeParcelReader.createString(r21, r2);
        goto L4
    L15:
        r11 = SafeParcelReader.createString(r21, r2);
        goto L4
    L16:
        r10 = SafeParcelReader.createString(r21, r2);
        goto L4
    L17:
        r8 = SafeParcelReader.readLong(r21, r2);
        goto L4
    L18:
        r7 = SafeParcelReader.createString(r21, r2);
        goto L4
    L19:
        r6 = SafeParcelReader.createString(r21, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r21, r2);
        goto L4
    L20:
        SafeParcelReader.ensureAtEnd(r21, r1);
        return new AdBreakClipInfo(r6, r7, r8, r10, r11, r12, r13, r14, r15, r16, r18, r19);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new AdBreakClipInfo[r1];
    }
}
