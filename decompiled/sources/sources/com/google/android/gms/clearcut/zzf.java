package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;

/* loaded from: classes4.dex */
public final class zzf implements Parcelable.Creator<zze> {
    public zzf() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zze createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        zzr r3 = null;
        byte[] r4 = null;
        int[] r5 = null;
        String[] r6 = null;
        int[] r7 = null;
        byte[][] r8 = null;
        ExperimentTokens[] r10 = null;
        boolean r9 = true;
    L4:
        if (r12.dataPosition() >= r11) goto L16;
        int r0 = SafeParcelReader.readHeader(r12);
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
        r10 = (ExperimentTokens[]) SafeParcelReader.createTypedArray(r12, r0, ExperimentTokens.CREATOR);
        goto L4
    L9:
        r9 = SafeParcelReader.readBoolean(r12, r0);
        goto L4
    L10:
        r8 = SafeParcelReader.createByteArrayArray(r12, r0);
        goto L4
    L11:
        r7 = SafeParcelReader.createIntArray(r12, r0);
        goto L4
    L12:
        r6 = SafeParcelReader.createStringArray(r12, r0);
        goto L4
    L13:
        r5 = SafeParcelReader.createIntArray(r12, r0);
        goto L4
    L14:
        r4 = SafeParcelReader.createByteArray(r12, r0);
        goto L4
    L15:
        r3 = (zzr) SafeParcelReader.createParcelable(r12, r0, zzr.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r12, r0);
        goto L4
    L16:
        SafeParcelReader.ensureAtEnd(r12, r11);
        return new zze(r3, r4, r5, r6, r7, r8, r9, r10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zze[] newArray(int r1) {
        return new zze[r1];
    }
}
