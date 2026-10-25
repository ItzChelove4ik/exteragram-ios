package com.google.android.gms.internal.measurement;

import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
final /* synthetic */ class zzmm implements Continuation {
    public /* synthetic */ zzmm(zzmn r1) {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task r12) {
        zzjh r11 = (zzjh) r12.getResult();
        zzmf r122 = zzmg.zzh();
        r122.zza(r11.zza);
        r122.zzc(r11.zzc);
        r122.zzf(r11.zzf);
        r122.zzg(r11.zzg);
        byte[] r0 = r11.zzb;
        if (r0 == null) goto L5;
        r122.zzb(zzacr.zzj(r0, 0, r0.length));
    L5:
        zzjf[] r112 = r11.zzd;
        int r02 = r112.length;
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L35;
        zzjf r3 = r112[r2];
        zzjo[] r4 = r3.zzb;
        int r5 = r4.length;
        int r6 = 0;
    L8:
        if (r6 >= r5) goto L27;
        zzjo r7 = r4[r6];
        int r8 = r7.zzg;
        if (r8 != 1) goto L12;
        zzmh r82 = zzmi.zzh();
        r82.zza(r7.zza);
        r82.zzb(r7.zza());
        zzadu r72 = r82.zzbd();
    L26:
        r122.zzd((zzmi) r72);
        r6 = r6 + 1;
        goto L8
    L12:
        if (r8 != 2) goto L14;
        zzmh r83 = zzmi.zzh();
        r83.zza(r7.zza);
        r83.zzc(r7.zzb());
        r72 = r83.zzbd();
        goto L26
    L14:
        if (r8 != 3) goto L16;
        zzmh r84 = zzmi.zzh();
        r84.zza(r7.zza);
        r84.zzd(r7.zzc());
        r72 = r84.zzbd();
        goto L26
    L16:
        if (r8 != 4) goto L18;
        zzmh r85 = zzmi.zzh();
        r85.zza(r7.zza);
        r85.zze(r7.zzd());
        r72 = r85.zzbd();
        goto L26
    L18:
        if (r8 != 5) goto L20;
        zzmh r86 = zzmi.zzh();
        r86.zza(r7.zza);
        byte[] r73 = r7.zze();
        zzacr r9 = zzacr.zza;
        r86.zzf(zzacr.zzj(r73, 0, r73.length));
        r72 = r86.zzbd();
        goto L26
    L20:
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r8).length() + 24, "Unrecognized flag type: ", r8);
        return null;
    L27:
        String[] r32 = r3.zzc;
        if (r32 == null) goto L33;
        int r42 = 0;
    L31:
        if (r42 >= r32.length) goto L33;
        r122.zze(r32[r42]);
        r42 = r42 + 1;
    L33:
        r2 = r2 + 1;
        goto L6
    L35:
        return (zzmg) r122.zzbd();
    }
}
