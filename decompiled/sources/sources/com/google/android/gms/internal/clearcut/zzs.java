package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzs implements Parcelable.Creator<zzr> {
    public zzs() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr createFromParcel(Parcel r14) {
        int r13 = SafeParcelReader.validateObjectHeader(r14);
        String r4 = null;
        String r7 = null;
        String r8 = null;
        String r10 = null;
        int r5 = 0;
        int r6 = 0;
        boolean r11 = false;
        int r12 = 0;
        boolean r9 = true;
    L4:
        if (r14.dataPosition() >= r13) goto L17;
        int r0 = SafeParcelReader.readHeader(r14);
        switch(SafeParcelReader.getFieldId(r0)) {
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
        r12 = SafeParcelReader.readInt(r14, r0);
        goto L4
    L9:
        r11 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L10:
        r10 = SafeParcelReader.createString(r14, r0);
        goto L4
    L11:
        r9 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L12:
        r8 = SafeParcelReader.createString(r14, r0);
        goto L4
    L13:
        r7 = SafeParcelReader.createString(r14, r0);
        goto L4
    L14:
        r6 = SafeParcelReader.readInt(r14, r0);
        goto L4
    L15:
        r5 = SafeParcelReader.readInt(r14, r0);
        goto L4
    L16:
        r4 = SafeParcelReader.createString(r14, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r14, r0);
        goto L4
    L17:
        SafeParcelReader.ensureAtEnd(r14, r13);
        return new zzr(r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr[] newArray(int r1) {
        return new zzr[r1];
    }
}
