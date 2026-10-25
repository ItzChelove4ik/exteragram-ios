package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class zzac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzac> CREATOR = null;
    private double zza;
    private boolean zzb;
    private int zzc;
    private ApplicationMetadata zzd;
    private int zze;
    private com.google.android.gms.cast.zzao zzf;
    private double zzg;

    static {
        CREATOR = new zzad();
    }

    public zzac() {
        this(Double.NaN, false, -1, null, -1, null, Double.NaN);
    }

    public zzac(double r1, boolean r3, int r4, ApplicationMetadata r5, int r6, com.google.android.gms.cast.zzao r7, double r8) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
    }

    public final boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzac) == true) goto L8;
        return false;
    L8:
        zzac r82 = (zzac) r8;
        if (this.zza == r82.zza) goto L11;
    L23:
        return false;
    L11:
        if (this.zzb != r82.zzb) goto L23;
        if (this.zzc != r82.zzc) goto L23;
        if (CastUtils.zza(this.zzd, r82.zzd) == false) goto L23;
        if (this.zze != r82.zze) goto L23;
        com.google.android.gms.cast.zzao r1 = this.zzf;
        if (CastUtils.zza(r1, r1) == false) goto L23;
        if (this.zzg != r82.zzg) goto L23;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Double.valueOf(this.zza), Boolean.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd, Integer.valueOf(this.zze), this.zzf, Double.valueOf(this.zzg)});
    }

    public final String toString() {
        return String.format(Locale.ROOT, "volume=%f", new Object[]{Double.valueOf(this.zza)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeDouble(r5, 2, this.zza);
        SafeParcelWriter.writeBoolean(r5, 3, this.zzb);
        SafeParcelWriter.writeInt(r5, 4, this.zzc);
        SafeParcelWriter.writeParcelable(r5, 5, this.zzd, r6, false);
        SafeParcelWriter.writeInt(r5, 6, this.zze);
        SafeParcelWriter.writeParcelable(r5, 7, this.zzf, r6, false);
        SafeParcelWriter.writeDouble(r5, 8, this.zzg);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final double zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zzc;
    }

    public final int zzd() {
        return this.zze;
    }

    public final ApplicationMetadata zze() {
        return this.zzd;
    }

    public final com.google.android.gms.cast.zzao zzf() {
        return this.zzf;
    }

    public final double zzg() {
        return this.zzg;
    }
}
