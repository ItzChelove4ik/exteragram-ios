package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public final class zzji implements Parcelable.Creator {
    public zzji() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r13) {
        int r12 = SafeParcelReader.validateObjectHeader(r13);
        long r10 = 0;
        String r5 = null;
        String r6 = null;
        zzjf[] r7 = null;
        byte[] r9 = null;
        boolean r8 = false;
    L4:
        if (r13.dataPosition() >= r12) goto L14;
        int r0 = SafeParcelReader.readHeader(r13);
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
        r10 = SafeParcelReader.readLong(r13, r0);
        goto L4
    L9:
        r9 = SafeParcelReader.createByteArray(r13, r0);
        goto L4
    L10:
        r8 = SafeParcelReader.readBoolean(r13, r0);
        goto L4
    L11:
        r7 = (zzjf[]) SafeParcelReader.createTypedArray(r13, r0, zzjf.CREATOR);
        goto L4
    L12:
        r6 = SafeParcelReader.createString(r13, r0);
        goto L4
    L13:
        r5 = SafeParcelReader.createString(r13, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r13, r0);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r13, r12);
        return new zzjh(r5, r6, r7, r8, r9, r10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzjh[r1];
    }
}
