package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes5.dex */
public final class zzac implements Parcelable.Creator {
    public zzac() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r27) {
        int r1 = SafeParcelReader.validateObjectHeader(r27);
        int r8 = 0;
        int r25 = 0;
        CameraPosition r9 = null;
        Float r19 = null;
        Float r20 = null;
        LatLngBounds r21 = null;
        Integer r23 = null;
        String r24 = null;
        byte r6 = -1;
        byte r7 = -1;
        byte r10 = -1;
        byte r11 = -1;
        byte r12 = -1;
        byte r13 = -1;
        byte r14 = -1;
        byte r15 = -1;
        byte r16 = -1;
        byte r17 = -1;
        byte r18 = -1;
        byte r22 = -1;
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
            case 13: goto L7;
            case 14: goto L16;
            case 15: goto L15;
            case 16: goto L14;
            case 17: goto L13;
            case 18: goto L12;
            case 19: goto L11;
            case 20: goto L10;
            case 21: goto L9;
            case 22: goto L7;
            case 23: goto L8;
            default: goto L7;
        };
    L8:
        r25 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L9:
        r24 = SafeParcelReader.createString(r27, r2);
        goto L4
    L10:
        r23 = SafeParcelReader.readIntegerObject(r27, r2);
        goto L4
    L11:
        r22 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L12:
        r21 = (LatLngBounds) SafeParcelReader.createParcelable(r27, r2, LatLngBounds.CREATOR);
        goto L4
    L13:
        r20 = SafeParcelReader.readFloatObject(r27, r2);
        goto L4
    L14:
        r19 = SafeParcelReader.readFloatObject(r27, r2);
        goto L4
    L15:
        r18 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L16:
        r17 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L17:
        r16 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L18:
        r15 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L19:
        r14 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L20:
        r13 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L21:
        r12 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L22:
        r11 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L23:
        r10 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L24:
        r9 = (CameraPosition) SafeParcelReader.createParcelable(r27, r2, CameraPosition.CREATOR);
        goto L4
    L25:
        r8 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L26:
        r7 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L27:
        r6 = SafeParcelReader.readByte(r27, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r27, r2);
        goto L4
    L28:
        SafeParcelReader.ensureAtEnd(r27, r1);
        return new GoogleMapOptions(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new GoogleMapOptions[r1];
    }
}
