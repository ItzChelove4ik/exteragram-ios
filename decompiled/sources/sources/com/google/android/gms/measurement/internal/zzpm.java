package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzpm implements Parcelable.Creator {
    public zzpm() {
    }

    public static void zza(zzpl r5, Parcel r6, int r7) {
        int r72 = r5.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeInt(r6, 1, r72);
        SafeParcelWriter.writeString(r6, 2, r5.zzb, false);
        SafeParcelWriter.writeLong(r6, 3, r5.zzc);
        SafeParcelWriter.writeLongObject(r6, 4, r5.zzd, false);
        SafeParcelWriter.writeFloatObject(r6, 5, null, false);
        SafeParcelWriter.writeString(r6, 6, r5.zze, false);
        SafeParcelWriter.writeString(r6, 7, r5.zzf, false);
        SafeParcelWriter.writeDoubleObject(r6, 8, r5.zzg, false);
        SafeParcelWriter.finishObjectHeader(r6, r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r15) {
        int r14 = SafeParcelReader.validateObjectHeader(r15);
        String r6 = null;
        Long r9 = null;
        Float r10 = null;
        String r11 = null;
        String r12 = null;
        Double r13 = null;
        long r7 = 0;
        int r5 = 0;
    L4:
        if (r15.dataPosition() >= r14) goto L16;
        int r0 = SafeParcelReader.readHeader(r15);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L15;
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L12;
            case 5: goto L11;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L8;
            default: goto L7;
        };
    L8:
        r13 = SafeParcelReader.readDoubleObject(r15, r0);
        goto L4
    L9:
        r12 = SafeParcelReader.createString(r15, r0);
        goto L4
    L10:
        r11 = SafeParcelReader.createString(r15, r0);
        goto L4
    L11:
        r10 = SafeParcelReader.readFloatObject(r15, r0);
        goto L4
    L12:
        r9 = SafeParcelReader.readLongObject(r15, r0);
        goto L4
    L13:
        r7 = SafeParcelReader.readLong(r15, r0);
        goto L4
    L14:
        r6 = SafeParcelReader.createString(r15, r0);
        goto L4
    L15:
        r5 = SafeParcelReader.readInt(r15, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r15, r0);
        goto L4
    L16:
        SafeParcelReader.ensureAtEnd(r15, r14);
        return new zzpl(r5, r6, r7, r9, r10, r11, r12, r13);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzpl[r1];
    }
}
