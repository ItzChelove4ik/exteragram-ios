package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/* loaded from: classes4.dex */
public final class zzig extends zzfv implements zzdp {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final String zze = "zzig";
    private final Context zzf;
    private final Optional zzg;

    static {
    }

    public zzig(zzdi r1, zzdq r2, zzdy r3) {
        super(r1, r2, r3);
        this.zzf = r1.zzg();
        this.zzg = Optional.empty();
    }

    public static zzig zzd(zzij r3) {
        return new zzig(r3.zza(), r3.zzc(), r3.zzb());
    }

    public static /* synthetic */ zzho zzw(zzw r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return new zzhb(zzml.zzq((List) r1.zza().stream().map(zzie.zza).collect(zzml.zzj())));
    }

    public static /* synthetic */ void zzx(zzcg r2) {
        r2.zzb();     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        Log.e(zze, "Failed to close all file descriptors", e);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzfv
    public final /* bridge */ /* synthetic */ Object zze(zzan r2) {
        this.zzg.isPresent();
        return r2.zzi(this.zza.zzj());
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzfv
    public final /* bridge */ /* synthetic */ zzav zzf(Object r3, Object r4, zzgw r5) {
        zzbi r32 = (zzbi) r3;
        zzhy r42 = (zzhy) r4;
        if (r42.zza().stream().anyMatch(zzif.zza) == true) goto L5;
    L8:
        zzcg r43 = r42.zzy(this.zzf, this.zzb);
        return r32.zzf(r43, new zzid(this, r5, r43));
    L5:
        if (this.zza.zzd() == 12) goto L8;
        Log.w(zze, "Audio input is only supported for LEGION features");
        r5.zzb(3);
        return new zzgv();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzfv
    public final /* bridge */ /* synthetic */ zzav zzg(Object r1, zzbn r2) {
        zzbi r12 = (zzbi) r1;
        if (r12.zzd() > 0) goto L7;
        Log.w(zze, "Ignoring prepareInferenceEngine because service is older than V2");
        r2.zzc(3);
        return new zzgv();
    L7:
        return r12.zze(r2);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzfv
    public final /* bridge */ /* synthetic */ zzav zzh(Object r2, boolean r3, Object r4, zzbp r5) {
        zzhy r22 = (zzhy) r2;
        zzbi r42 = (zzbi) r4;
        if (r42.zzd() >= 2) goto L7;
        Log.w(zze, "Ignoring getTokenInfo because service is older than V3");
        r5.zzc(16);
        return new zzgv();
    L7:
        return r42.zzg(r22.zzy(this.zzf, this.zzb), true, r5);
    }
}
