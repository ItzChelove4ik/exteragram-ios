package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzeq extends zzaw {
    final /* synthetic */ zzeo zza;
    final /* synthetic */ zzgw zzb;

    public zzeq(zzer r1, zzeo r2, zzgw r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzax
    public final void zzb(zzah r9) {
        zzeo r0 = this.zza;
        if (r0.zzo() != 1) goto L5;
    L12:
        zzbt r02 = r9.zzb();
        int r92 = r9.zzc();
        zzeh r1 = new zzeh(zzml.zzk(), r02, r92);
        this.zzb.zza(r1);
        return;
    L5:
        if (r0.zzo() == 2) goto L12;
        int r03 = zzml.$r8$clinit;
        zzmh r04 = new zzmh();
        zzml r12 = r9.zza();
        int r2 = r12.size();
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L10;
        zzac r4 = (zzac) r12.get(r3);
        r04.zzd(new zzee(r4.zza(), r4.zzb(), r4.zza));
        r3 = r3 + 1;
        goto L8
    L10:
        zzml r05 = r04.zze();
        zzbt r93 = r9.zzb();
        zzeh r13 = new zzeh(zzml.zzq(r05), r93, -1);
        this.zzb.zza(r13);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzax
    public final void zzc(int r1) {
        this.zzb.zzb(r1);
    }
}
