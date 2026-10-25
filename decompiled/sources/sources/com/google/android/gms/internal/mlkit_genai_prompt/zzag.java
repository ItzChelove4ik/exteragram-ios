package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzag implements Parcelable.Creator {
    public zzag() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r24) {
        int r1 = SafeParcelReader.validateObjectHeader(r24);
        int r8 = 0;
        int r9 = 0;
        int r11 = 0;
        int r14 = 0;
        int r15 = 0;
        int r20 = 0;
        int r21 = 0;
        int r22 = 0;
        ArrayList r6 = null;
        IBinder r10 = null;
        IBinder r12 = null;
        ArrayList r13 = null;
        String r16 = null;
        String r17 = null;
        String r18 = null;
        String r19 = null;
        float r7 = 0.0f;
    L4:
        if (r24.dataPosition() >= r1) goto L25;
        int r2 = SafeParcelReader.readHeader(r24);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L24;
            case 2: goto L23;
            case 3: goto L22;
            case 4: goto L21;
            case 5: goto L20;
            case 6: goto L19;
            case 7: goto L18;
            case 8: goto L17;
            case 9: goto L16;
            case 10: goto L15;
            case 11: goto L14;
            case 12: goto L13;
            case 13: goto L12;
            case 14: goto L11;
            case 15: goto L10;
            case 16: goto L9;
            case 17: goto L8;
            default: goto L7;
        };
    L8:
        r22 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L9:
        r21 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L10:
        r20 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L11:
        r19 = SafeParcelReader.createString(r24, r2);
        goto L4
    L12:
        r18 = SafeParcelReader.createString(r24, r2);
        goto L4
    L13:
        r17 = SafeParcelReader.createString(r24, r2);
        goto L4
    L14:
        r16 = SafeParcelReader.createString(r24, r2);
        goto L4
    L15:
        r15 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L16:
        r14 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L17:
        r13 = SafeParcelReader.createTypedList(r24, r2, ParcelFileDescriptor.CREATOR);
        goto L4
    L18:
        r12 = SafeParcelReader.readIBinder(r24, r2);
        goto L4
    L19:
        r11 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L20:
        r10 = SafeParcelReader.readIBinder(r24, r2);
        goto L4
    L21:
        r9 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L22:
        r8 = SafeParcelReader.readInt(r24, r2);
        goto L4
    L23:
        r7 = SafeParcelReader.readFloat(r24, r2);
        goto L4
    L24:
        r6 = SafeParcelReader.createTypedList(r24, r2, zzaa.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r24, r2);
        goto L4
    L25:
        SafeParcelReader.ensureAtEnd(r24, r1);
        return new zzaf(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzaf[r1];
    }
}
