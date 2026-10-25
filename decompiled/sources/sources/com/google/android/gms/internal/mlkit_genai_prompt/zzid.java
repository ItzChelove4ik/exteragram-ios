package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzid extends zzbg {
    final /* synthetic */ zzgw zza;
    final /* synthetic */ zzcg zzb;

    public zzid(zzig r1, zzgw r2, zzcg r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbh
    public final void zzb(zzci r14) {
        int r0 = zzml.$r8$clinit;
        zzmh r02 = new zzmh();
        zzml r1 = r14.zza();
        int r2 = r1.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L17;
        zzcd r5 = (zzcd) r1.get(r4);
        ArrayList r6 = new ArrayList();
        List r7 = r5.zzd();
        if (r7 == null) goto L16;
        Iterator r72 = r7.iterator();
    L8:
        if (r72.hasNext() == false) goto L16;
        zzda r8 = (zzda) r72.next();
        zzmn r9 = new zzmn();
        zzmo r10 = r8.zzb();
        if (r10 == null) goto L15;
        zzns r102 = r10.zze().zza();
    L13:
        if (r102.hasNext() == false) goto L15;
        Map.Entry r11 = (Map.Entry) r102.next();
        r9.zza((String) r11.getKey(), zziw.zzh((zzcy) r11.getValue()));
    L15:
        zzix r103 = zziy.zzc();
        r103.zza(r8.zza());
        r103.zzb(r9.zzc());
        r6.add(r103.zzc());
    L16:
        r02.zzd(zzhv.zzf(r5.zza(), r5.zzb(), r5.zza, zzig.zzw(r5.zzc()), r6));
        r4 = r4 + 1;
        goto L3
    L17:
        zzmh r12 = new zzmh();
        zzml r22 = r14.zzd();
        if (r22 == null) goto L22;
        int r42 = r22.size();
    L20:
        if (r3 >= r42) goto L22;
        zzcd r52 = (zzcd) r22.get(r3);
        r12.zzd(zzhv.zzf(r52.zza(), r52.zzb(), r52.zza, zzig.zzw(r52.zzc()), zzml.zzk()));
        r3 = r3 + 1;
    L22:
        zzml r03 = r02.zze();
        zzbt r23 = r14.zzb();
        zzck r142 = r14.zzc();
        int r32 = zzig.$r8$clinit;
        if (r142 != null) goto L25;
        zzha r143 = null;
    L26:
        this.zza.zza(new zzhl(zzml.zzq(r03), r23, r143, zzml.zzq(r12.zze())));
        zzig.zzx(this.zzb);
        return;
    L25:
        zzgz r33 = zzha.zzb();
        r33.zza(r142.zza());
        r143 = r33.zzb();
        goto L26
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzbh
    public final void zzc(zzaj r3) {
        this.zza.zzc(r3.zza(), r3.zzb());
        zzig.zzx(this.zzb);
    }
}
