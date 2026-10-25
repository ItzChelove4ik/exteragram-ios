package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzbu implements Parcelable.Creator {
    public zzbu() {
    }

    public static final zzbt zza(Parcel r88) {
        int r1 = SafeParcelReader.validateObjectHeader(r88);
        byte[] r47 = null;
        int[] r66 = null;
        byte[] r69 = null;
        byte[] r73 = null;
        long[] r75 = null;
        byte[] r86 = null;
        byte[] r87 = null;
        long r16 = 0;
        long r21 = 0;
        long r23 = 0;
        long r25 = 0;
        long r27 = 0;
        long r29 = 0;
        long r31 = 0;
        long r39 = 0;
        long r50 = 0;
        long r52 = 0;
        long r62 = 0;
        long r64 = 0;
        long r71 = 0;
        long r76 = 0;
        long r78 = 0;
        long r82 = 0;
        long r84 = 0;
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
        int r15 = 0;
        int r18 = 0;
        int r19 = 0;
        int r20 = 0;
        int r33 = 0;
        int r34 = 0;
        int r35 = 0;
        int r36 = 0;
        boolean r37 = false;
        int r38 = 0;
        int r45 = 0;
        int r48 = 0;
        int r49 = 0;
        int r55 = 0;
        int r56 = 0;
        int r57 = 0;
        int r58 = 0;
        int r59 = 0;
        int r61 = 0;
        int r67 = 0;
        int r74 = 0;
        int r80 = 0;
        int r81 = 0;
        int r54 = -1;
        int r60 = -1;
        int r70 = -1;
        String r68 = "unknown";
        float r46 = 0.0f;
        double r41 = 0.0d;
        double r43 = 0.0d;
    L4:
        if (r88.dataPosition() >= r1) goto L65;
        int r2 = SafeParcelReader.readHeader(r88);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L64;
            case 2: goto L63;
            case 3: goto L62;
            case 4: goto L61;
            case 5: goto L60;
            case 6: goto L59;
            case 7: goto L58;
            case 8: goto L57;
            case 9: goto L56;
            case 10: goto L55;
            case 11: goto L54;
            case 12: goto L53;
            case 13: goto L52;
            case 14: goto L51;
            case 15: goto L50;
            case 16: goto L49;
            case 17: goto L48;
            case 18: goto L47;
            case 19: goto L46;
            case 20: goto L45;
            case 21: goto L44;
            case 22: goto L43;
            case 23: goto L42;
            case 24: goto L41;
            case 25: goto L40;
            case 26: goto L39;
            case 27: goto L38;
            case 28: goto L37;
            case 29: goto L36;
            case 30: goto L35;
            case 31: goto L34;
            case 32: goto L33;
            case 33: goto L32;
            case 34: goto L31;
            case 35: goto L30;
            case 36: goto L29;
            case 37: goto L28;
            case 38: goto L27;
            case 39: goto L26;
            case 40: goto L25;
            case 41: goto L24;
            case 42: goto L23;
            case 43: goto L22;
            case 44: goto L21;
            case 45: goto L20;
            case 46: goto L19;
            case 47: goto L18;
            case 48: goto L17;
            case 49: goto L7;
            case 50: goto L16;
            case 51: goto L15;
            case 52: goto L14;
            case 53: goto L13;
            case 54: goto L12;
            case 55: goto L11;
            case 56: goto L10;
            case 57: goto L9;
            case 58: goto L8;
            default: goto L7;
        };
    L8:
        r87 = SafeParcelReader.createByteArray(r88, r2);
        goto L4
    L9:
        r86 = SafeParcelReader.createByteArray(r88, r2);
        goto L4
    L10:
        r84 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L11:
        r82 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L12:
        r81 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L13:
        r80 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L14:
        r78 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L15:
        r76 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L16:
        r75 = SafeParcelReader.createLongArray(r88, r2);
        goto L4
    L17:
        r74 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L18:
        r73 = SafeParcelReader.createByteArray(r88, r2);
        goto L4
    L19:
        r71 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L20:
        r70 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L21:
        r69 = SafeParcelReader.createByteArray(r88, r2);
        goto L4
    L22:
        r68 = SafeParcelReader.createString(r88, r2);
        goto L4
    L23:
        r67 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L24:
        r66 = SafeParcelReader.createIntArray(r88, r2);
        goto L4
    L25:
        r64 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L26:
        r62 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L27:
        r61 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L28:
        r60 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L29:
        r59 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L30:
        r58 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L31:
        r57 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L32:
        r56 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L33:
        r55 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L34:
        r54 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L35:
        r52 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L36:
        r50 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L37:
        r49 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L38:
        r48 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L39:
        r47 = SafeParcelReader.createByteArray(r88, r2);
        goto L4
    L40:
        r46 = SafeParcelReader.readFloat(r88, r2);
        goto L4
    L41:
        r45 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L42:
        r43 = SafeParcelReader.readDouble(r88, r2);
        goto L4
    L43:
        r41 = SafeParcelReader.readDouble(r88, r2);
        goto L4
    L44:
        r39 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L45:
        r38 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L46:
        r37 = SafeParcelReader.readBoolean(r88, r2);
        goto L4
    L47:
        r36 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L48:
        r35 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L49:
        r34 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L50:
        r33 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L51:
        r31 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L52:
        r29 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L53:
        r27 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L54:
        r25 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L55:
        r23 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L56:
        r21 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L57:
        r20 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L58:
        r19 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L59:
        r18 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L60:
        r16 = SafeParcelReader.readLong(r88, r2);
        goto L4
    L61:
        r15 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L62:
        r14 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L63:
        r13 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L64:
        r12 = SafeParcelReader.readInt(r88, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r88, r2);
        goto L4
    L65:
        SafeParcelReader.ensureAtEnd(r88, r1);
        return new zzbt(r12, r13, r14, r15, r16, r18, r19, r20, r21, r23, r25, r27, r29, r31, r33, r34, r35, r36, r37, r38, r39, r41, r43, r45, r46, r47, r48, r49, r50, r52, r54, r55, r56, r57, r58, r59, r60, r61, r62, r64, r66, r67, r68, r69, r70, r71, r73, r74, r75, r76, r78, r80, r81, r82, r84, r86, r87);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
        return zza(r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzbt[r1];
    }
}
