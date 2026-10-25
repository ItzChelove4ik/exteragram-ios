package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzch implements Parcelable.Creator {
    public zzch() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r31) {
        int r1 = SafeParcelReader.validateObjectHeader(r31);
        ArrayList r7 = null;
        ArrayList<String> r10 = null;
        IBinder r13 = null;
        IBinder r16 = null;
        String r19 = null;
        Bundle r20 = null;
        zzcw r22 = null;
        zzcb r24 = null;
        zzcp r27 = null;
        zzdf r29 = null;
        int r9 = 0;
        int r11 = 0;
        int r12 = 0;
        boolean r14 = false;
        int r15 = 0;
        int r17 = 0;
        int r18 = 0;
        int r21 = 0;
        int r23 = 0;
        int r25 = 0;
        boolean r28 = false;
        float r26 = 1.0f;
        float r8 = 0.0f;
    L4:
        if (r31.dataPosition() >= r1) goto L31;
        int r2 = SafeParcelReader.readHeader(r31);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L30;
            case 2: goto L29;
            case 3: goto L28;
            case 4: goto L27;
            case 5: goto L26;
            case 6: goto L25;
            case 7: goto L24;
            case 8: goto L23;
            case 9: goto L22;
            case 10: goto L21;
            case 11: goto L20;
            case 12: goto L19;
            case 13: goto L18;
            case 14: goto L17;
            case 15: goto L16;
            case 16: goto L15;
            case 17: goto L14;
            case 18: goto L13;
            case 19: goto L12;
            case 20: goto L11;
            case 21: goto L10;
            case 22: goto L9;
            case 23: goto L8;
            default: goto L7;
        };
    L8:
        r29 = (zzdf) SafeParcelReader.createParcelable(r31, r2, zzdf.CREATOR);
        goto L4
    L9:
        r28 = SafeParcelReader.readBoolean(r31, r2);
        goto L4
    L10:
        r27 = (zzcp) SafeParcelReader.createParcelable(r31, r2, zzcp.CREATOR);
        goto L4
    L11:
        r26 = SafeParcelReader.readFloat(r31, r2);
        goto L4
    L12:
        r25 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L13:
        r24 = (zzcb) SafeParcelReader.createParcelable(r31, r2, zzcb.CREATOR);
        goto L4
    L14:
        r23 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L15:
        r22 = (zzcw) SafeParcelReader.createParcelable(r31, r2, zzcw.CREATOR);
        goto L4
    L16:
        r21 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L17:
        r20 = SafeParcelReader.createBundle(r31, r2);
        goto L4
    L18:
        r19 = SafeParcelReader.createString(r31, r2);
        goto L4
    L19:
        r18 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L20:
        r17 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L21:
        r16 = SafeParcelReader.readIBinder(r31, r2);
        goto L4
    L22:
        r15 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L23:
        r14 = SafeParcelReader.readBoolean(r31, r2);
        goto L4
    L24:
        r13 = SafeParcelReader.readIBinder(r31, r2);
        goto L4
    L25:
        r12 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L26:
        r11 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L27:
        r10 = SafeParcelReader.createStringList(r31, r2);
        goto L4
    L28:
        r9 = SafeParcelReader.readInt(r31, r2);
        goto L4
    L29:
        r8 = SafeParcelReader.readFloat(r31, r2);
        goto L4
    L30:
        r7 = SafeParcelReader.createTypedList(r31, r2, zzbz.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r31, r2);
        goto L4
    L31:
        SafeParcelReader.ensureAtEnd(r31, r1);
        return new zzcg(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzcg[r1];
    }
}
