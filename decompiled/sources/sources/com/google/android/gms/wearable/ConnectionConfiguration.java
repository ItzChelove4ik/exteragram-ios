package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes5.dex */
public class ConnectionConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ConnectionConfiguration> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final int zzc;
    private final int zzd;
    private final boolean zze;
    private volatile boolean zzf;
    private volatile String zzg;
    private boolean zzh;
    private String zzi;
    private String zzj;
    private int zzk;
    private List zzl;

    static {
        CREATOR = new zzd();
    }

    public ConnectionConfiguration(String r1, String r2, int r3, int r4, boolean r5, boolean r6, String r7, boolean r8, String r9, String r10, int r11, List r12) {
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
        this.zzk = r11;
        this.zzl = r12;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof ConnectionConfiguration) == true) goto L5;
        return false;
    L5:
        ConnectionConfiguration r42 = (ConnectionConfiguration) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L19:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L19;
        if (Objects.equal(Integer.valueOf(this.zzc), Integer.valueOf(r42.zzc)) == false) goto L19;
        if (Objects.equal(Integer.valueOf(this.zzd), Integer.valueOf(r42.zzd)) == false) goto L19;
        if (Objects.equal(Boolean.valueOf(this.zze), Boolean.valueOf(r42.zze)) == false) goto L19;
        if (Objects.equal(Boolean.valueOf(this.zzh), Boolean.valueOf(r42.zzh)) == false) goto L19;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Boolean.valueOf(this.zze), Boolean.valueOf(this.zzh)});
    }

    public final String toString() {
        return "ConnectionConfiguration[ Name=" + this.zza + ", Address=" + this.zzb + ", Type=" + this.zzc + ", Role=" + this.zzd + ", Enabled=" + this.zze + ", IsConnected=" + this.zzf + ", PeerNodeId=" + this.zzg + ", BtlePriority=" + this.zzh + ", NodeId=" + this.zzi + ", PackageName=" + this.zzj + ", ConnectionRetryStrategy=" + this.zzk + ", allowedConfigPackages=" + this.zzl + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zza, false);
        SafeParcelWriter.writeString(r4, 3, this.zzb, false);
        SafeParcelWriter.writeInt(r4, 4, this.zzc);
        SafeParcelWriter.writeInt(r4, 5, this.zzd);
        SafeParcelWriter.writeBoolean(r4, 6, this.zze);
        SafeParcelWriter.writeBoolean(r4, 7, this.zzf);
        SafeParcelWriter.writeString(r4, 8, this.zzg, false);
        SafeParcelWriter.writeBoolean(r4, 9, this.zzh);
        SafeParcelWriter.writeString(r4, 10, this.zzi, false);
        SafeParcelWriter.writeString(r4, 11, this.zzj, false);
        SafeParcelWriter.writeInt(r4, 12, this.zzk);
        SafeParcelWriter.writeStringList(r4, 13, this.zzl, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
