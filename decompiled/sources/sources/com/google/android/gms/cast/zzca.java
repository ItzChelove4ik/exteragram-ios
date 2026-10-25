package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzca implements Parcelable.Creator {
    public zzca() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r18) {
        int r1 = SafeParcelReader.validateObjectHeader(r18);
        String r2 = null;
        String r10 = null;
        String r11 = null;
        String r12 = null;
        String r13 = null;
        ArrayList<String> r15 = null;
        int r9 = 0;
        int r14 = 0;
        long r7 = 0;
    L4:
        if (r18.dataPosition() >= r1) goto L17;
        int r3 = SafeParcelReader.readHeader(r18);
        switch(SafeParcelReader.getFieldId(r3)) {
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            case 8: goto L10;
            case 9: goto L9;
            case 10: goto L8;
            default: goto L7;
        };
    L8:
        r2 = SafeParcelReader.createString(r18, r3);
        goto L4
    L9:
        r15 = SafeParcelReader.createStringList(r18, r3);
        goto L4
    L10:
        r14 = SafeParcelReader.readInt(r18, r3);
        goto L4
    L11:
        r13 = SafeParcelReader.createString(r18, r3);
        goto L4
    L12:
        r12 = SafeParcelReader.createString(r18, r3);
        goto L4
    L13:
        r11 = SafeParcelReader.createString(r18, r3);
        goto L4
    L14:
        r10 = SafeParcelReader.createString(r18, r3);
        goto L4
    L15:
        r9 = SafeParcelReader.readInt(r18, r3);
        goto L4
    L16:
        r7 = SafeParcelReader.readLong(r18, r3);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r18, r3);
        goto L4
    L17:
        SafeParcelReader.ensureAtEnd(r18, r1);
        return new MediaTrack(r7, r9, r10, r11, r12, r13, r14, r15, CastUtils.jsonStringToJsonObject(r2));
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new MediaTrack[r1];
    }
}
