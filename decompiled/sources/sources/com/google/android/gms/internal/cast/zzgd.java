package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzgd implements Parcelable.Creator {
    public zzgd() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        int r3 = 0;
        boolean r4 = false;
        int r6 = 0;
        boolean r8 = false;
        ArrayList<String> r5 = null;
        String r7 = null;
    L4:
        if (r10.dataPosition() >= r9) goto L14;
        int r0 = SafeParcelReader.readHeader(r10);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        r8 = SafeParcelReader.readBoolean(r10, r0);
        goto L4
    L9:
        r7 = SafeParcelReader.createString(r10, r0);
        goto L4
    L10:
        r6 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L11:
        r5 = SafeParcelReader.createStringList(r10, r0);
        goto L4
    L12:
        r4 = SafeParcelReader.readBoolean(r10, r0);
        goto L4
    L13:
        r3 = SafeParcelReader.readInt(r10, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new zzgc(r3, r4, r5, r6, r7, r8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzgc[r1];
    }
}
