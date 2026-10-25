package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzah extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzah> CREATOR = null;
    public String zza;
    public String zzb;
    public zzpl zzc;
    public long zzd;
    public boolean zze;
    public String zzf;
    public final zzbh zzg;
    public long zzh;
    public zzbh zzi;
    public final long zzj;
    public final zzbh zzk;

    static {
        CREATOR = new zzai();
    }

    public zzah(zzah r3) {
        Preconditions.checkNotNull(r3);
        this.zza = r3.zza;
        this.zzb = r3.zzb;
        this.zzc = r3.zzc;
        this.zzd = r3.zzd;
        this.zze = r3.zze;
        this.zzf = r3.zzf;
        this.zzg = r3.zzg;
        this.zzh = r3.zzh;
        this.zzi = r3.zzi;
        this.zzj = r3.zzj;
        this.zzk = r3.zzk;
    }

    public zzah(String r1, String r2, zzpl r3, long r4, boolean r6, String r7, zzbh r8, long r9, zzbh r11, long r12, zzbh r14) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi = r11;
        this.zzj = r12;
        this.zzk = r14;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r7, int r8) {
        int r0 = SafeParcelWriter.beginObjectHeader(r7);
        SafeParcelWriter.writeString(r7, 2, this.zza, false);
        SafeParcelWriter.writeString(r7, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(r7, 4, this.zzc, r8, false);
        SafeParcelWriter.writeLong(r7, 5, this.zzd);
        SafeParcelWriter.writeBoolean(r7, 6, this.zze);
        SafeParcelWriter.writeString(r7, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(r7, 8, this.zzg, r8, false);
        SafeParcelWriter.writeLong(r7, 9, this.zzh);
        SafeParcelWriter.writeParcelable(r7, 10, this.zzi, r8, false);
        SafeParcelWriter.writeLong(r7, 11, this.zzj);
        SafeParcelWriter.writeParcelable(r7, 12, this.zzk, r8, false);
        SafeParcelWriter.finishObjectHeader(r7, r0);
    }
}
