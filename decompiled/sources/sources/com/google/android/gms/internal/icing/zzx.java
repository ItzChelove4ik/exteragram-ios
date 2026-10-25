package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class zzx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzx> CREATOR = null;
    final zzi zza;
    final long zzb;
    int zzc;
    public final String zzd;
    final zzg zze;
    final boolean zzf;
    int zzg;
    int zzh;
    public final String zzi;

    static {
        CREATOR = new zzy();
    }

    public zzx(zzi r1, long r2, int r4, String r5, zzg r6, boolean r7, int r8, int r9, String r10) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi = r10;
    }

    public final String toString() {
        return String.format(Locale.US, "UsageInfo[documentId=%s, timestamp=%d, usageType=%d, status=%d]", new Object[]{this.zza, Long.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzh)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r7, int r8) {
        int r0 = SafeParcelWriter.beginObjectHeader(r7);
        SafeParcelWriter.writeParcelable(r7, 1, this.zza, r8, false);
        SafeParcelWriter.writeLong(r7, 2, this.zzb);
        SafeParcelWriter.writeInt(r7, 3, this.zzc);
        SafeParcelWriter.writeString(r7, 4, this.zzd, false);
        SafeParcelWriter.writeParcelable(r7, 5, this.zze, r8, false);
        SafeParcelWriter.writeBoolean(r7, 6, this.zzf);
        SafeParcelWriter.writeInt(r7, 7, this.zzg);
        SafeParcelWriter.writeInt(r7, 8, this.zzh);
        SafeParcelWriter.writeString(r7, 9, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(r7, r0);
    }
}
