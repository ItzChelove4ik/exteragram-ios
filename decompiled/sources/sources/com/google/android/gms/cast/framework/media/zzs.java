package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzs implements Parcelable.Creator {
    public zzs() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r43) {
        int r1 = SafeParcelReader.validateObjectHeader(r43);
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
        int r15 = 0;
        int r16 = 0;
        int r17 = 0;
        int r18 = 0;
        int r19 = 0;
        int r20 = 0;
        int r21 = 0;
        int r22 = 0;
        int r23 = 0;
        int r24 = 0;
        int r25 = 0;
        int r26 = 0;
        int r27 = 0;
        int r28 = 0;
        int r29 = 0;
        int r30 = 0;
        int r31 = 0;
        int r32 = 0;
        int r33 = 0;
        int r34 = 0;
        int r35 = 0;
        int r36 = 0;
        int r37 = 0;
        int r38 = 0;
        boolean r40 = false;
        boolean r41 = false;
        ArrayList<String> r7 = null;
        int[] r8 = null;
        String r11 = null;
        IBinder r39 = null;
        long r9 = 0;
    L4:
        if (r43.dataPosition() >= r1) goto L42;
        int r2 = SafeParcelReader.readHeader(r43);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L41;
            case 3: goto L40;
            case 4: goto L39;
            case 5: goto L38;
            case 6: goto L37;
            case 7: goto L36;
            case 8: goto L35;
            case 9: goto L34;
            case 10: goto L33;
            case 11: goto L32;
            case 12: goto L31;
            case 13: goto L30;
            case 14: goto L29;
            case 15: goto L28;
            case 16: goto L27;
            case 17: goto L26;
            case 18: goto L25;
            case 19: goto L24;
            case 20: goto L23;
            case 21: goto L22;
            case 22: goto L21;
            case 23: goto L20;
            case 24: goto L19;
            case 25: goto L18;
            case 26: goto L17;
            case 27: goto L16;
            case 28: goto L15;
            case 29: goto L14;
            case 30: goto L13;
            case 31: goto L12;
            case 32: goto L11;
            case 33: goto L10;
            case 34: goto L9;
            case 35: goto L8;
            default: goto L7;
        };
    L8:
        r41 = SafeParcelReader.readBoolean(r43, r2);
        goto L4
    L9:
        r40 = SafeParcelReader.readBoolean(r43, r2);
        goto L4
    L10:
        r39 = SafeParcelReader.readIBinder(r43, r2);
        goto L4
    L11:
        r38 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L12:
        r37 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L13:
        r36 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L14:
        r35 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L15:
        r34 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L16:
        r33 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L17:
        r32 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L18:
        r31 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L19:
        r30 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L20:
        r29 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L21:
        r28 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L22:
        r27 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L23:
        r26 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L24:
        r25 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L25:
        r24 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L26:
        r23 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L27:
        r22 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L28:
        r21 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L29:
        r20 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L30:
        r19 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L31:
        r18 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L32:
        r17 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L33:
        r16 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L34:
        r15 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L35:
        r14 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L36:
        r13 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L37:
        r12 = SafeParcelReader.readInt(r43, r2);
        goto L4
    L38:
        r11 = SafeParcelReader.createString(r43, r2);
        goto L4
    L39:
        r9 = SafeParcelReader.readLong(r43, r2);
        goto L4
    L40:
        r8 = SafeParcelReader.createIntArray(r43, r2);
        goto L4
    L41:
        r7 = SafeParcelReader.createStringList(r43, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r43, r2);
        goto L4
    L42:
        SafeParcelReader.ensureAtEnd(r43, r1);
        return new NotificationOptions(r7, r8, r9, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new NotificationOptions[r1];
    }
}
