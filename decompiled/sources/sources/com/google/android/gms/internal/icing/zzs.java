package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = null;
    public final String zza;
    public final String zzb;
    public final boolean zzc;
    public final int zzd;
    public final boolean zze;
    public final String zzf;
    public final zzm[] zzg;
    public final String zzh;
    public final zzu zzi;

    static {
        CREATOR = new zzt();
    }

    public zzs(String r1, String r2, boolean r3, int r4, boolean r5, String r6, zzm[] r7, String r8, zzu r9) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzs) == true) goto L8;
        return false;
    L8:
        zzs r52 = (zzs) r5;
        if (this.zzc == r52.zzc) goto L11;
    L27:
        return false;
    L11:
        if (this.zzd != r52.zzd) goto L27;
        if (this.zze != r52.zze) goto L27;
        if (Objects.equal(this.zza, r52.zza) == false) goto L27;
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L27;
        if (Objects.equal(this.zzf, r52.zzf) == false) goto L27;
        if (Objects.equal(this.zzh, r52.zzh) == false) goto L27;
        if (Objects.equal(this.zzi, r52.zzi) == false) goto L27;
        if (Arrays.equals(this.zzg, r52.zzg) == false) goto L27;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, Boolean.valueOf(this.zzc), Integer.valueOf(this.zzd), Boolean.valueOf(this.zze), this.zzf, Integer.valueOf(Arrays.hashCode(this.zzg)), this.zzh, this.zzi});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, this.zza, false);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(r5, 3, this.zzc);
        SafeParcelWriter.writeInt(r5, 4, this.zzd);
        SafeParcelWriter.writeBoolean(r5, 5, this.zze);
        SafeParcelWriter.writeString(r5, 6, this.zzf, false);
        SafeParcelWriter.writeTypedArray(r5, 7, this.zzg, r6, false);
        SafeParcelWriter.writeString(r5, 11, this.zzh, false);
        SafeParcelWriter.writeParcelable(r5, 12, this.zzi, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
