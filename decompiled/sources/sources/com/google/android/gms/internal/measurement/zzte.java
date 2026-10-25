package com.google.android.gms.internal.measurement;

import android.net.Uri;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/* loaded from: classes4.dex */
final class zzte extends zztr {
    private final Uri zza;
    private final zzafc zzb;
    private final Optional zzc;
    private final ImmutableList zzd;
    private final zzuj zze;
    private final boolean zzf;

    public /* synthetic */ zzte(Uri r1, zzafc r2, Optional r3, ImmutableList r4, zzuj r5, boolean r6, boolean r7, byte[] r8) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zztr) == false) goto L21;
        zztr r52 = (zztr) r5;
        if (this.zza.equals(r52.zza()) == false) goto L21;
        if (this.zzb.equals(r52.zzb()) == false) goto L21;
        if (this.zzc.equals(r52.zzc()) == false) goto L21;
        if (this.zzd.equals(r52.zzd()) == false) goto L21;
        if (this.zze.equals(r52.zze()) == false) goto L21;
        if (this.zzf != r52.zzf()) goto L21;
        r52.zzg();
        return true;
    L21:
        return false;
    }

    public final int hashCode() {
        int r0 = ((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode();
        if (true == this.zzf) goto L5;
        int r4 = 1237;
    L7:
        return ((r4 ^ (r0 * 1000003)) * 1000003) ^ 1237;
    L5:
        r4 = 1231;
        goto L7
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r2 = this.zzb.toString();
        int r3 = r2.length();
        zzuj r4 = this.zze;
        ImmutableList r5 = this.zzd;
        String r6 = String.valueOf(this.zzc);
        String r52 = String.valueOf(r5);
        String r42 = r4.toString();
        int r7 = r6.length();
        int r8 = r52.length();
        int r9 = r42.length();
        boolean r11 = this.zzf;
        int r12 = ((((((((r1 + 34) + r3) + 10) + r7) + 13) + r8) + 16) + r9) + 32;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r11).length()) + 22);
        r32.append("ProtoDataStoreConfig{uri=");
        r32.append(r0);
        r32.append(", schema=");
        r32.append(r2);
        r32.append(", handler=");
        r32.append(r6);
        r32.append(", migrations=");
        r32.append(r52);
        r32.append(", variantConfig=");
        r32.append(r42);
        r32.append(", useGeneratedExtensionRegistry=");
        r32.append(r11);
        r32.append(", enableTracing=false}");
        return r32.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zztr
    public final Uri zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zztr
    public final zzafc zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zztr
    public final Optional zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zztr
    public final ImmutableList zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zztr
    public final zzuj zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.measurement.zztr
    public final boolean zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zztr
    public final boolean zzg() {
        return false;
    }
}
