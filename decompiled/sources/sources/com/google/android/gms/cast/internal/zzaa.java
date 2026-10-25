package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzaa extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaa> CREATOR = null;
    private final int zza;
    private final boolean zzb;
    private final boolean zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final boolean zzi;
    private final boolean zzj;

    static {
        CREATOR = new zzab();
    }

    public zzaa(int r1, boolean r2, boolean r3, String r4, String r5, String r6, String r7, String r8, boolean r9, boolean r10) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzaa) == true) goto L8;
        return false;
    L8:
        zzaa r52 = (zzaa) r5;
        if (this.zza == r52.zza) goto L11;
    L29:
        return false;
    L11:
        if (this.zzb != r52.zzb) goto L29;
        if (this.zzc != r52.zzc) goto L29;
        if (TextUtils.equals(this.zzd, r52.zzd) == false) goto L29;
        if (TextUtils.equals(this.zze, r52.zze) == false) goto L29;
        if (TextUtils.equals(this.zzf, r52.zzf) == false) goto L29;
        if (TextUtils.equals(this.zzg, r52.zzg) == false) goto L29;
        if (TextUtils.equals(this.zzh, r52.zzh) == false) goto L29;
        if (this.zzi != r52.zzi) goto L29;
        if (this.zzj != r52.zzj) goto L29;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zza), Boolean.valueOf(this.zzb), Boolean.valueOf(this.zzc), this.zzd, this.zze, this.zzf, this.zzg, this.zzh, Boolean.valueOf(this.zzi), Boolean.valueOf(this.zzj)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, this.zza);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzb);
        SafeParcelWriter.writeBoolean(r4, 4, this.zzc);
        SafeParcelWriter.writeString(r4, 5, this.zzd, false);
        SafeParcelWriter.writeString(r4, 6, this.zze, false);
        SafeParcelWriter.writeString(r4, 7, this.zzf, false);
        SafeParcelWriter.writeString(r4, 8, this.zzg, false);
        SafeParcelWriter.writeString(r4, 9, this.zzh, false);
        SafeParcelWriter.writeBoolean(r4, 10, this.zzi);
        SafeParcelWriter.writeBoolean(r4, 11, this.zzj);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final String zza() {
        return this.zzd;
    }

    public final String zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final String zze() {
        return this.zzh;
    }
}
