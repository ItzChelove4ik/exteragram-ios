package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzcz implements Parcelable.Creator {
    public zzcz() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        long r16 = 0;
        String r10 = null;
        ArrayList r15 = null;
        double r13 = 0.0d;
        int r9 = 0;
        boolean r11 = false;
        int r12 = 0;
    L4:
        if (r19.dataPosition() >= r1) goto L15;
        int r2 = SafeParcelReader.readHeader(r19);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        r16 = SafeParcelReader.readLong(r19, r2);
        goto L4
    L9:
        r15 = SafeParcelReader.createTypedList(r19, r2, zzcy.CREATOR);
        goto L4
    L10:
        r13 = SafeParcelReader.readDouble(r19, r2);
        goto L4
    L11:
        r12 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L12:
        r11 = SafeParcelReader.readBoolean(r19, r2);
        goto L4
    L13:
        r10 = SafeParcelReader.createString(r19, r2);
        goto L4
    L14:
        r9 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new zzcy(r9, r10, r11, r12, r13, r15, r16);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzcy[r1];
    }
}
