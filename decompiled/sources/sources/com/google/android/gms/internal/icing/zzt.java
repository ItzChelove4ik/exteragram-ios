package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzt implements Parcelable.Creator<zzs> {
    public zzt() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzs createFromParcel(Parcel r14) {
        int r13 = SafeParcelReader.validateObjectHeader(r14);
        String r4 = null;
        String r5 = null;
        String r9 = null;
        zzm[] r10 = null;
        String r11 = null;
        zzu r12 = null;
        boolean r6 = false;
        boolean r8 = false;
        int r7 = 1;
    L4:
        if (r14.dataPosition() >= r13) goto L20;
        int r0 = SafeParcelReader.readHeader(r14);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 11) goto L8;
        r11 = SafeParcelReader.createString(r14, r0);
        goto L4
    L8:
        if (r1 != 12) goto L9;
        r12 = (zzu) SafeParcelReader.createParcelable(r14, r0, zzu.CREATOR);
        goto L4
    L9:
        switch(r1) {
            case 1: goto L17;
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            default: goto L10;
        };
    L11:
        r10 = (zzm[]) SafeParcelReader.createTypedArray(r14, r0, zzm.CREATOR);
        goto L4
    L12:
        r9 = SafeParcelReader.createString(r14, r0);
        goto L4
    L13:
        r8 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L14:
        r7 = SafeParcelReader.readInt(r14, r0);
        goto L4
    L15:
        r6 = SafeParcelReader.readBoolean(r14, r0);
        goto L4
    L16:
        r5 = SafeParcelReader.createString(r14, r0);
        goto L4
    L17:
        r4 = SafeParcelReader.createString(r14, r0);
        goto L4
    L10:
        SafeParcelReader.skipUnknownField(r14, r0);
        goto L4
    L20:
        SafeParcelReader.ensureAtEnd(r14, r13);
        return new zzs(r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzs[] newArray(int r1) {
        return new zzs[r1];
    }
}
