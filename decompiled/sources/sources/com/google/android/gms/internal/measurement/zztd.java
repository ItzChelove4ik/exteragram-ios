package com.google.android.gms.internal.measurement;

import android.net.Uri;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zztd extends zztq {
    private Uri zza;
    private zzafc zzb;
    private Optional zzc;
    private ImmutableList zzd;
    private zzuj zze;
    private boolean zzf;
    private byte zzg;

    public zztd() {
        this.zzc = Optional.absent();
    }

    @Override // com.google.android.gms.internal.measurement.zztq
    public final zztq zza(Uri r1) {
        if (r1 == null) goto L5;
        this.zza = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null uri");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zztq
    public final zztq zzb(zzafc r1) {
        if (r1 == null) goto L5;
        this.zzb = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null schema");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zztq
    public final zztq zzc(zztf r1) {
        this.zzc = Optional.of(r1);
        return this;
    }

    public final zztq zzd(zzuj r1) {
        this.zze = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zztq
    public final zztq zze(boolean r2) {
        this.zzf = true;
        this.zzg = (byte) (1 | this.zzg);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zztq
    public final zztq zzf(boolean r1) {
        this.zzg = (byte) (this.zzg | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zztq
    public final zztr zzg() {
        if (this.zzd != null) goto L6;
        this.zzd = ImmutableList.of();
    L6:
        if (this.zzg != 3) goto L16;
        Uri r3 = this.zza;
        if (r3 == null) goto L16;
        zzafc r4 = this.zzb;
        if (r4 == null) goto L16;
        zzuj r7 = this.zze;
        if (r7 == null) goto L16;
        return new zzte(r3, r4, this.zzc, this.zzd, r7, this.zzf, false, null);
    L16:
        StringBuilder r0 = new StringBuilder();
        if (this.zza != null) goto L20;
        r0.append(" uri");
    L20:
        if (this.zzb != null) goto L23;
        r0.append(" schema");
    L23:
        if (this.zze != null) goto L26;
        r0.append(" variantConfig");
    L26:
        if ((this.zzg & 1) != 0) goto L29;
        r0.append(" useGeneratedExtensionRegistry");
    L29:
        if ((this.zzg & 2) != 0) goto L31;
        r0.append(" enableTracing");
    L31:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0.toString()));
        return null;
    }
}
