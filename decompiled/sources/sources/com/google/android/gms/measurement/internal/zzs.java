package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzs implements Parcelable.Creator {
    public zzs() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r54) {
        int r1 = SafeParcelReader.validateObjectHeader(r54);
        long r16 = 0;
        long r18 = 0;
        long r26 = 0;
        long r32 = 0;
        long r39 = 0;
        long r44 = 0;
        long r48 = 0;
        long r51 = 0;
        boolean r22 = false;
        int r28 = 0;
        boolean r30 = false;
        boolean r38 = false;
        int r43 = 0;
        int r50 = 0;
        String r35 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r36 = r35;
        String r42 = r36;
        String r47 = r42;
        String r12 = null;
        String r13 = null;
        String r14 = null;
        String r15 = null;
        String r20 = null;
        String r25 = null;
        Boolean r31 = null;
        ArrayList<String> r34 = null;
        String r37 = null;
        String r46 = null;
        int r41 = 100;
        boolean r21 = true;
        boolean r29 = true;
        long r23 = -2147483648L;
    L4:
        if (r54.dataPosition() >= r1) goto L40;
        int r2 = SafeParcelReader.readHeader(r54);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 2: goto L39;
            case 3: goto L38;
            case 4: goto L37;
            case 5: goto L36;
            case 6: goto L35;
            case 7: goto L34;
            case 8: goto L33;
            case 9: goto L32;
            case 10: goto L31;
            case 11: goto L30;
            case 12: goto L29;
            case 13: goto L7;
            case 14: goto L28;
            case 15: goto L27;
            case 16: goto L26;
            case 17: goto L7;
            case 18: goto L25;
            case 19: goto L7;
            case 20: goto L7;
            case 21: goto L24;
            case 22: goto L23;
            case 23: goto L22;
            case 24: goto L7;
            case 25: goto L21;
            case 26: goto L20;
            case 27: goto L19;
            case 28: goto L18;
            case 29: goto L17;
            case 30: goto L16;
            case 31: goto L15;
            case 32: goto L14;
            case 33: goto L7;
            case 34: goto L13;
            case 35: goto L12;
            case 36: goto L11;
            case 37: goto L10;
            case 38: goto L9;
            case 39: goto L8;
            default: goto L7;
        };
    L8:
        r51 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L9:
        r50 = SafeParcelReader.readInt(r54, r2);
        goto L4
    L10:
        r48 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L11:
        r47 = SafeParcelReader.createString(r54, r2);
        goto L4
    L12:
        r46 = SafeParcelReader.createString(r54, r2);
        goto L4
    L13:
        r44 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L14:
        r43 = SafeParcelReader.readInt(r54, r2);
        goto L4
    L15:
        r42 = SafeParcelReader.createString(r54, r2);
        goto L4
    L16:
        r41 = SafeParcelReader.readInt(r54, r2);
        goto L4
    L17:
        r39 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L18:
        r38 = SafeParcelReader.readBoolean(r54, r2);
        goto L4
    L19:
        r37 = SafeParcelReader.createString(r54, r2);
        goto L4
    L20:
        r36 = SafeParcelReader.createString(r54, r2);
        goto L4
    L21:
        r35 = SafeParcelReader.createString(r54, r2);
        goto L4
    L22:
        r34 = SafeParcelReader.createStringList(r54, r2);
        goto L4
    L23:
        r32 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L24:
        r31 = SafeParcelReader.readBooleanObject(r54, r2);
        goto L4
    L25:
        r30 = SafeParcelReader.readBoolean(r54, r2);
        goto L4
    L26:
        r29 = SafeParcelReader.readBoolean(r54, r2);
        goto L4
    L27:
        r28 = SafeParcelReader.readInt(r54, r2);
        goto L4
    L28:
        r26 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L29:
        r25 = SafeParcelReader.createString(r54, r2);
        goto L4
    L30:
        r23 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L31:
        r22 = SafeParcelReader.readBoolean(r54, r2);
        goto L4
    L32:
        r21 = SafeParcelReader.readBoolean(r54, r2);
        goto L4
    L33:
        r20 = SafeParcelReader.createString(r54, r2);
        goto L4
    L34:
        r18 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L35:
        r16 = SafeParcelReader.readLong(r54, r2);
        goto L4
    L36:
        r15 = SafeParcelReader.createString(r54, r2);
        goto L4
    L37:
        r14 = SafeParcelReader.createString(r54, r2);
        goto L4
    L38:
        r13 = SafeParcelReader.createString(r54, r2);
        goto L4
    L39:
        r12 = SafeParcelReader.createString(r54, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r54, r2);
        goto L4
    L40:
        SafeParcelReader.ensureAtEnd(r54, r1);
        return new zzr(r12, r13, r14, r15, r16, r18, r20, r21, r22, r23, r25, r26, r28, r29, r30, r31, r32, r34, r35, r36, r37, r38, r39, r41, r42, r43, r44, r46, r47, r48, r50, r51);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzr[r1];
    }
}
