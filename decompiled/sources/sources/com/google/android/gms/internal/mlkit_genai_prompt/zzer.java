package com.google.android.gms.internal.mlkit_genai_prompt;

import java.io.IOException;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzer extends zzfv implements zzdp {
    static {
    }

    private zzer(zzdi r1, zzdq r2, zzdy r3) {
        super(r1, r2, r3);
    }

    public static zzer zzd(zzeu r3) {
        return new zzer(r3.zza(), r3.zzc(), r3.zzb());
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzfv
    public final /* bridge */ /* synthetic */ Object zze(zzan r1) {
        return r1.zzj(this.zza.zzj());
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzfv
    public final /* synthetic */ zzav zzf(Object r6, Object r7, zzgw r8) {
        zzay r62 = (zzay) r6;
        zzeo r72 = (zzeo) r7;
        zzae r0 = zzaf.zza();
        int r1 = r72.zzo() - 1;
        if (r1 == 0) goto L17;
        if (r1 != 1) goto L6;
        r0.zzj("cortana_type_prefix_caching");
        r0.zzk(r72.zzl());
    L18:
        r0.zzf(new zzel(r72, this.zzb));
        r0.zza(zzml.zzk());
        r0.zzb(0.0f);
        r0.zzc(r72.zze());
        r0.zzd(r72.zzf());
        r0.zze(r72.zzg());
        r0.zzh(0);
        r0.zzi(0);
        r0.zzm(0);
        r0.zzg(zzml.zzk());
        r0.zzn(1);
        zzaf r02 = r0.zzo();
        zzav r5 = r62.zzd(r02, new zzeq(this, r72, r8));
        r02.zzb();     // Catch: IOException -> L21
        return r5;
    L21:
        r5.zzd();
        r8.zzb(14);
        return r5;
    L6:
        if (r1 != 2) goto L8;
        r0.zzj("cortana_type_inference_with_session_state_caching");
        goto L18
    L8:
        if (r1 != 3) goto L10;
        r0.zzj("cortana_type_inference_with_prefix_caching");
        goto L18
    L10:
        if (r1 != 4) goto L12;
        zzla.zza(false, "When inference with cached state, there must be positive tokens been processed before");
        zzla.zza(false, "Messages must be non-null for INFERENCE_WITH_BLOCK_ATTENTION if cached state is used");
        r0.zzj("cortana_type_inference_with_block_attention");
        r0.zzl(0);
        goto L18
    L12:
        ByteString$$ExternalSyntheticBUOutline0.m("Unsupported Cortana type in CortanaRequest: ".concat(zzen.zza(r72.zzo())));
        return null;
    L17:
        r0.zzj("cortana_type_block_attention");
        r0.zzk(r72.zzl());
        r0.zzl(0);
        goto L18
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzfv
    public final /* synthetic */ zzav zzg(Object r1, zzbn r2) {
        return ((zzay) r1).zze(r2);
    }
}
