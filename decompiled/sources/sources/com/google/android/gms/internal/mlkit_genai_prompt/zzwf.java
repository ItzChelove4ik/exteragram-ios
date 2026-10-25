package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import com.google.mlkit.genai.prompt.PromptPrefix;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzwf implements zzaty {
    private final zzaro zza;
    private final zzavc zzb;

    public zzwf() {
        zzarm r0 = new zzarm();
        r0.zza(zzarn.zzc);
        this.zza = r0.zzb();
        this.zzb = zzavk.zza("genai-prompt");
    }

    private static final int zze(CreateCachedContextRequest r0) {
        PromptPrefix r02 = r0.getPromptPrefix();
        if (r02 == null) goto L8;
        String r03 = r02.getTextString();
        if (r03 != null) goto L7;
        return 0;
    L7:
        return r03.length();
    L8:
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final void zza() {
        zzanv r0 = new zzanv();
        zzarg r1 = new zzarg();
        r1.zza(this.zza);
        r0.zzd(r1.zzb());
        zzauu r02 = zzavd.zze(r0);
        this.zzb.zza(r02, zzanu.zzeW);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final void zzb(int r4, int r5) {
        zzanv r0 = new zzanv();
        zzari r1 = new zzari();
        r1.zza(this.zza);
        zzars r2 = new zzars();
        r2.zza(Integer.valueOf(r4));
        r2.zzb(zzaty.zzk(r5));
        r1.zzc(r2.zzh());
        r0.zzc(r1.zzd());
        zzauu r42 = zzavd.zze(r0);
        this.zzb.zza(r42, zzanu.zzeV);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final void zzc() {
        zzanv r0 = new zzanv();
        zzarc r1 = new zzarc();
        r1.zza(this.zza);
        r0.zze(r1.zzb());
        zzauu r02 = zzavd.zze(r0);
        this.zzb.zza(r02, zzanu.zzeX);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaty
    public final /* bridge */ /* synthetic */ void zzd(zzars r5, Object r6, Object r7) {
        CreateCachedContextRequest r62 = (CreateCachedContextRequest) r6;
        ((Boolean) r7).getClass();
        ArrayList r72 = new ArrayList();
        zzaqz r0 = new zzaqz();
        r0.zza(zzara.zzb);
        r0.zzb(Long.valueOf(zze(r62)));
        PromptPrefix r1 = r62.getPromptPrefix();
        if (r1 == null) goto L7;
        String r12 = r1.getTextString();
        if (r12 == null) goto L7;
        int r13 = r12.length();
    L8:
        zzasg r3 = new zzasg();
        Integer r14 = Integer.valueOf(r13);
        r3.zza(r14);
        r3.zzb(r14);
        r0.zzc(r3.zzc());
        r72.add(r0.zzd());
        zzavc r02 = this.zzb;
        zzanv r15 = new zzanv();
        zzari r32 = new zzari();
        r32.zza(this.zza);
        zzark r4 = new zzark();
        r4.zzf(zzml.zzq(r72));
        r32.zzb(r4.zzg());
        r5.zzd(Integer.valueOf(zze(r62)));
        r5.zze(0);
        r32.zzc(r5.zzh());
        r15.zzc(r32.zzd());
        r02.zza(zzavd.zze(r15), zzanu.zzeV);
        return;
    L7:
        r13 = 0;
        goto L8
    }
}
