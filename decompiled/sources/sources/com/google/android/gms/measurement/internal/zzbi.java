package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzbi implements Parcelable.Creator {
    public zzbi() {
    }

    public static void zza(zzbh r4, Parcel r5, int r6) {
        String r0 = r4.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, r0, false);
        SafeParcelWriter.writeParcelable(r5, 3, r4.zzb, r6, false);
        SafeParcelWriter.writeString(r5, 4, r4.zzc, false);
        SafeParcelWriter.writeLong(r5, 5, r4.zzd);
        SafeParcelWriter.writeLong(r5, 6, r4.zze);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r12) {
        int r11 = SafeParcelReader.validateObjectHeader(r12);
        long r7 = 0;
        long r9 = 0;
        String r4 = null;
        zzbf r5 = null;
        String r6 = null;
    L4:
        if (r12.dataPosition() >= r11) goto L21;
        int r0 = SafeParcelReader.readHeader(r12);
        int r1 = SafeParcelReader.getFieldId(r0);
        if (r1 != 2) goto L8;
        r4 = SafeParcelReader.createString(r12, r0);
        goto L4
    L8:
        if (r1 != 3) goto L10;
        r5 = (zzbf) SafeParcelReader.createParcelable(r12, r0, zzbf.CREATOR);
        goto L4
    L10:
        if (r1 != 4) goto L12;
        r6 = SafeParcelReader.createString(r12, r0);
        goto L4
    L12:
        if (r1 != 5) goto L14;
        r7 = SafeParcelReader.readLong(r12, r0);
        goto L4
    L14:
        if (r1 != 6) goto L15;
        r9 = SafeParcelReader.readLong(r12, r0);
        goto L4
    L15:
        SafeParcelReader.skipUnknownField(r12, r0);
        goto L4
    L21:
        SafeParcelReader.ensureAtEnd(r12, r11);
        return new zzbh(r4, r5, r6, r7, r9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzbh[r1];
    }
}
