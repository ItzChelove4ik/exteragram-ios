package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzd implements Parcelable.Creator<FaceParcel> {
    public zzd() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FaceParcel createFromParcel(Parcel r24) {
        int r1 = SafeParcelReader.validateObjectHeader(r24);
        int r8 = 0;
        int r9 = 0;
        float r10 = 0.0f;
        float r11 = 0.0f;
        float r12 = 0.0f;
        float r13 = 0.0f;
        float r18 = 0.0f;
        float r19 = 0.0f;
        float r20 = 0.0f;
        float r14 = Float.MAX_VALUE;
        float r15 = Float.MAX_VALUE;
        float r16 = Float.MAX_VALUE;
        LandmarkParcel[] r17 = null;
        zza[] r21 = null;
        float r22 = -1.0f;
    L4:
        if (r24.dataPosition() >= r1) goto L23;
        int r2 = SafeParcelReader.readHeader(r24);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L22;
            case 2: goto L21;
            case 3: goto L20;
            case 4: goto L19;
            case 5: goto L18;
            case 6: goto L17;
            case 7: goto L16;
            case 8: goto L15;
            case 9: goto L14;
            case 10: goto L13;
            case 11: goto L12;
            case 12: goto L11;
            case 13: goto L10;
            case 14: goto L9;
            case 15: goto L8;
            default: goto L7;
        };
    L8:
        r22 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L9:
        r16 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L10:
        r21 = (zza[]) SafeParcelReader.createTypedArray(r24, r2, zza.CREATOR);
        goto L4
    L11:
        r20 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L12:
        r19 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L13:
        r18 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L14:
        r17 = (LandmarkParcel[]) SafeParcelReader.createTypedArray(r24, r2, LandmarkParcel.CREATOR);
        goto L4
    L15:
        r15 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L16:
        r14 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L17:
        r13 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L18:
        r12 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L19:
        r11 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L20:
        r10 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L21:
        r9 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L22:
        r8 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r24, r2);
        goto L4
    L23:
        SafeParcelReader.ensureAtEnd(r24, r1);
        return new FaceParcel(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FaceParcel[] newArray(int r1) {
        return new FaceParcel[r1];
    }
}
