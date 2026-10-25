package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzdf implements Parcelable.Creator {
    public zzdf() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r19) {
        int r1 = SafeParcelReader.validateObjectHeader(r19);
        String r14 = null;
        String r17 = null;
        int r7 = 0;
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
        int r11 = 0;
        int r12 = 0;
        int r13 = 0;
        int r15 = 0;
        int r16 = 0;
        float r6 = 0.0f;
    L4:
        if (r19.dataPosition() >= r1) goto L20;
        int r2 = SafeParcelReader.readHeader(r19);
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
        r17 = SafeParcelReader.createString(r19, r2);
        goto L4
    L9:
        r16 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L10:
        r15 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L11:
        r14 = SafeParcelReader.createString(r19, r2);
        goto L4
    L12:
        r13 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L13:
        r12 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L14:
        r11 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L15:
        r10 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L16:
        r9 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L17:
        r8 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L18:
        r7 = SafeParcelReader.readInt(r19, r2);
        goto L4
    L19:
        r6 = SafeParcelReader.readFloat(r19, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r19, r2);
        goto L4
    L20:
        SafeParcelReader.ensureAtEnd(r19, r1);
        return new TextTrackStyle(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new TextTrackStyle[r1];
    }
}
