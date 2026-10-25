package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class zzd implements Parcelable.Creator {
    public zzd() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r18) {
        int r1 = SafeParcelReader.validateObjectHeader(r18);
        String r5 = null;
        String r6 = null;
        String r11 = null;
        String r13 = null;
        String r14 = null;
        ArrayList<String> r16 = null;
        int r7 = 0;
        int r8 = 0;
        boolean r9 = false;
        boolean r10 = false;
        boolean r12 = false;
        int r15 = 0;
    L4:
        if (r18.dataPosition() >= r1) goto L20;
        int r2 = SafeParcelReader.readHeader(r18);
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
        r16 = SafeParcelReader.createStringList(r18, r2);
        goto L4
    L9:
        r15 = SafeParcelReader.readInt(r18, r2);
        goto L4
    L10:
        r14 = SafeParcelReader.createString(r18, r2);
        goto L4
    L11:
        r13 = SafeParcelReader.createString(r18, r2);
        goto L4
    L12:
        r12 = SafeParcelReader.readBoolean(r18, r2);
        goto L4
    L13:
        r11 = SafeParcelReader.createString(r18, r2);
        goto L4
    L14:
        r10 = SafeParcelReader.readBoolean(r18, r2);
        goto L4
    L15:
        r9 = SafeParcelReader.readBoolean(r18, r2);
        goto L4
    L16:
        r8 = SafeParcelReader.readInt(r18, r2);
        goto L4
    L17:
        r7 = SafeParcelReader.readInt(r18, r2);
        goto L4
    L18:
        r6 = SafeParcelReader.createString(r18, r2);
        goto L4
    L19:
        r5 = SafeParcelReader.createString(r18, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r18, r2);
        goto L4
    L20:
        SafeParcelReader.ensureAtEnd(r18, r1);
        return new ConnectionConfiguration(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new ConnectionConfiguration[r1];
    }
}
