package com.google.android.gms.cast;

import android.net.Network;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzr implements Parcelable.Creator {
    public zzr() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r27) {
        int r1 = SafeParcelReader.validateObjectHeader(r27);
        String r6 = null;
        String r7 = null;
        String r8 = null;
        String r9 = null;
        String r10 = null;
        ArrayList r12 = null;
        String r15 = null;
        String r16 = null;
        String r18 = null;
        byte[] r19 = null;
        String r20 = null;
        com.google.android.gms.cast.internal.zzaa r22 = null;
        Integer r23 = null;
        Boolean r24 = null;
        Network r25 = null;
        int r11 = 0;
        int r13 = 0;
        int r17 = 0;
        boolean r21 = false;
        int r14 = -1;
    L4:
        if (r27.dataPosition() >= r1) goto L28;
        int r2 = SafeParcelReader.readHeader(r27);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L27;
            case 3: goto L26;
            case 4: goto L25;
            case 5: goto L24;
            case 6: goto L23;
            case 7: goto L22;
            case 8: goto L21;
            case 9: goto L20;
            case 10: goto L19;
            case 11: goto L18;
            case 12: goto L17;
            case 13: goto L16;
            case 14: goto L15;
            case 15: goto L14;
            case 16: goto L13;
            case 17: goto L12;
            case 18: goto L11;
            case 19: goto L10;
            case 20: goto L9;
            case 21: goto L8;
            default: goto L7;
        };
    L8:
        r25 = (Network) SafeParcelReader.createParcelable(r27, r2, Network.CREATOR);
        goto L4
    L9:
        r24 = SafeParcelReader.readBooleanObject(r27, r2);
        goto L4
    L10:
        r23 = SafeParcelReader.readIntegerObject(r27, r2);
        goto L4
    L11:
        r22 = (com.google.android.gms.cast.internal.zzaa) SafeParcelReader.createParcelable(r27, r2, com.google.android.gms.cast.internal.zzaa.CREATOR);
        goto L4
    L12:
        r21 = SafeParcelReader.readBoolean(r27, r2);
        goto L4
    L13:
        r20 = SafeParcelReader.createString(r27, r2);
        goto L4
    L14:
        r19 = SafeParcelReader.createByteArray(r27, r2);
        goto L4
    L15:
        r18 = SafeParcelReader.createString(r27, r2);
        goto L4
    L16:
        r17 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L17:
        r16 = SafeParcelReader.createString(r27, r2);
        goto L4
    L18:
        r15 = SafeParcelReader.createString(r27, r2);
        goto L4
    L19:
        r14 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L20:
        r13 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L21:
        r12 = SafeParcelReader.createTypedList(r27, r2, WebImage.CREATOR);
        goto L4
    L22:
        r11 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L23:
        r10 = SafeParcelReader.createString(r27, r2);
        goto L4
    L24:
        r9 = SafeParcelReader.createString(r27, r2);
        goto L4
    L25:
        r8 = SafeParcelReader.createString(r27, r2);
        goto L4
    L26:
        r7 = SafeParcelReader.createString(r27, r2);
        goto L4
    L27:
        r6 = SafeParcelReader.createString(r27, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r27, r2);
        goto L4
    L28:
        SafeParcelReader.ensureAtEnd(r27, r1);
        return new CastDevice(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new CastDevice[r1];
    }
}
