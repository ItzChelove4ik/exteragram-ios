package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes5.dex */
public final class zzh implements Parcelable.Creator<ExperimentTokens> {
    public zzh() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ExperimentTokens createFromParcel(Parcel r11) {
        int r10 = SafeParcelReader.validateObjectHeader(r11);
        String r2 = null;
        byte[] r3 = null;
        byte[][] r4 = null;
        byte[][] r5 = null;
        byte[][] r6 = null;
        byte[][] r7 = null;
        int[] r8 = null;
        byte[][] r9 = null;
    L4:
        if (r11.dataPosition() >= r10) goto L16;
        int r0 = SafeParcelReader.readHeader(r11);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 2: goto L15;
            case 3: goto L14;
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L11;
            case 7: goto L10;
            case 8: goto L9;
            case 9: goto L8;
            default: goto L7;
        };
    L8:
        r9 = SafeParcelReader.createByteArrayArray(r11, r0);
        goto L4
    L9:
        r8 = SafeParcelReader.createIntArray(r11, r0);
        goto L4
    L10:
        r7 = SafeParcelReader.createByteArrayArray(r11, r0);
        goto L4
    L11:
        r6 = SafeParcelReader.createByteArrayArray(r11, r0);
        goto L4
    L12:
        r5 = SafeParcelReader.createByteArrayArray(r11, r0);
        goto L4
    L13:
        r4 = SafeParcelReader.createByteArrayArray(r11, r0);
        goto L4
    L14:
        r3 = SafeParcelReader.createByteArray(r11, r0);
        goto L4
    L15:
        r2 = SafeParcelReader.createString(r11, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r11, r0);
        goto L4
    L16:
        SafeParcelReader.ensureAtEnd(r11, r10);
        return new ExperimentTokens(r2, r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ExperimentTokens[] newArray(int r1) {
        return new ExperimentTokens[r1];
    }
}
