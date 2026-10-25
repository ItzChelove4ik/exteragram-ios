package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.nio.charset.StandardCharsets;

/* loaded from: classes4.dex */
public final class zzavd implements zzauu {
    private final zzanv zza;
    private zzasz zzb;

    private zzavd(zzanv r1, int r2) {
        this.zzb = new zzasz();
        this.zza = r1;
        zzavl.zza();
    }

    public static zzauu zze(zzanv r2) {
        return new zzavd(r2, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzauu
    public final byte[] zza(int r3, boolean r4) {
        int r42 = r3 ^ 1;
        zzasz r0 = this.zzb;
        if (1 == r42) goto L5;
        boolean r43 = false;
    L6:
        r0.zzi(Boolean.valueOf(r43));
        this.zzb.zzg(Boolean.FALSE);
        zzata r44 = this.zzb.zzl();
        zzanv r2 = this.zza;
        r2.zza(r44);
        zzavl.zza();
        if (r3 == 0) goto L9;
        zzanw r22 = r2.zzg();
        zzpz r32 = new zzpz();
        zzalp.zza.configure(r32);
        return r32.zza().zza(r22);
    L9:
        return new JsonDataEncoderBuilder().configureWith(zzalp.zza).ignoreNullValues(true).build().encode(r2.zzg()).getBytes(StandardCharsets.UTF_8);
    L5:
        r43 = true;
        goto L6
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzauu
    public final zzauu zzb(zzasz r1) {
        this.zzb = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzauu
    public final zzauu zzc(zzanu r2) {
        this.zza.zzb(r2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzauu
    public final String zzd() {
        zzata r1 = this.zza.zzg().zza();
        if (r1 != null) goto L5;
        return "NA";
    L5:
        if (zzlb.zzc(r1.zzd()) == false) goto L7;
        return "NA";
    L7:
        return (String) Preconditions.checkNotNull(r1.zzd());
    }
}
