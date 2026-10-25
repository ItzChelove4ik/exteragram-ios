package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzp implements Parcelable.Creator {
    public zzp() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r27) {
        int r1 = SafeParcelReader.validateObjectHeader(r27);
        LatLng r8 = null;
        String r9 = null;
        String r10 = null;
        IBinder r11 = null;
        IBinder r23 = null;
        String r25 = null;
        boolean r14 = false;
        boolean r15 = false;
        boolean r16 = false;
        int r22 = 0;
        int r24 = 0;
        float r12 = 0.0f;
        float r13 = 0.0f;
        float r17 = 0.0f;
        float r19 = 0.0f;
        float r21 = 0.0f;
        float r20 = 1.0f;
        float r18 = 0.5f;
    L4:
        if (r27.dataPosition() >= r1) goto L26;
        int r2 = SafeParcelReader.readHeader(r27);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L25;
            case 3: goto L24;
            case 4: goto L23;
            case 5: goto L22;
            case 6: goto L21;
            case 7: goto L20;
            case 8: goto L19;
            case 9: goto L18;
            case 10: goto L17;
            case 11: goto L16;
            case 12: goto L15;
            case 13: goto L14;
            case 14: goto L13;
            case 15: goto L12;
            case 16: goto L7;
            case 17: goto L11;
            case 18: goto L10;
            case 19: goto L9;
            case 20: goto L8;
            default: goto L7;
        };
    L8:
        r25 = SafeParcelReader.createString(r27, r2);
        goto L4
    L9:
        r24 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L10:
        r23 = SafeParcelReader.readIBinder(r27, r2);
        goto L4
    L11:
        r22 = SafeParcelReader.readInt(r27, r2);
        goto L4
    L12:
        r21 = SafeParcelReader.readFloat(r27, r2);
        goto L4
    L13:
        r20 = SafeParcelReader.readFloat(r27, r2);
        goto L4
    L14:
        r19 = SafeParcelReader.readFloat(r27, r2);
        goto L4
    L15:
        r18 = SafeParcelReader.readFloat(r27, r2);
        goto L4
    L16:
        r17 = SafeParcelReader.readFloat(r27, r2);
        goto L4
    L17:
        r16 = SafeParcelReader.readBoolean(r27, r2);
        goto L4
    L18:
        r15 = SafeParcelReader.readBoolean(r27, r2);
        goto L4
    L19:
        r14 = SafeParcelReader.readBoolean(r27, r2);
        goto L4
    L20:
        r13 = SafeParcelReader.readFloat(r27, r2);
        goto L4
    L21:
        r12 = SafeParcelReader.readFloat(r27, r2);
        goto L4
    L22:
        r11 = SafeParcelReader.readIBinder(r27, r2);
        goto L4
    L23:
        r10 = SafeParcelReader.createString(r27, r2);
        goto L4
    L24:
        r9 = SafeParcelReader.createString(r27, r2);
        goto L4
    L25:
        r8 = (LatLng) SafeParcelReader.createParcelable(r27, r2, LatLng.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r27, r2);
        goto L4
    L26:
        SafeParcelReader.ensureAtEnd(r27, r1);
        return new MarkerOptions(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MarkerOptions[r1];
    }
}
