package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = null;
    public final String zza;
    public final String zzb;
    public final zziv zzc;
    public final String zzd;
    public final String zze;
    public final Float zzf;
    public final zzs zzg;

    static {
        CREATOR = new zzp();
    }

    public zzo(String r1, String r2, zziv r3, String r4, String r5, Float r6, zzs r7) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L25:
        return false;
    L8:
        if (zzo.class != r5.getClass()) goto L25;
        zzo r52 = (zzo) r5;
        if (zzn.zza(this.zza, r52.zza) == false) goto L25;
        if (zzn.zza(this.zzb, r52.zzb) == false) goto L25;
        if (zzn.zza(this.zzc, r52.zzc) == false) goto L25;
        if (zzn.zza(this.zzd, r52.zzd) == false) goto L25;
        if (zzn.zza(this.zze, r52.zze) == false) goto L25;
        if (zzn.zza(this.zzf, r52.zzf) == false) goto L25;
        if (zzn.zza(this.zzg, r52.zzg) == false) goto L25;
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg});
    }

    public final String toString() {
        return "AppParcelable{title='" + this.zzb + "', developerName='" + this.zzd + "', formattedPrice='" + this.zze + "', starRating=" + this.zzf + ", wearDetails=" + String.valueOf(this.zzg) + ", deepLinkUri='" + this.zza + "', icon=" + String.valueOf(this.zzc) + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, this.zza, false);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeString(r5, 4, this.zzd, false);
        SafeParcelWriter.writeString(r5, 5, this.zze, false);
        SafeParcelWriter.writeFloatObject(r5, 6, this.zzf, false);
        SafeParcelWriter.writeParcelable(r5, 7, this.zzg, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
