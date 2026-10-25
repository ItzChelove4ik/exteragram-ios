package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public abstract class zzact {
    public static zzst zza(zzsk r5, zzvt r6) {
        zzvk r62 = new zzvk();
        int r0 = 0;
    L4:
        if (r0 >= r5.zzc()) goto L31;
        zzsi r1 = ((zzsj) r5).zzd(r0);
        if (r1.zzb().equals(zzsg.zza) == false) goto L30;
        zzse r2 = r1.zza();
        if ((r2 instanceof zzyb) == false) goto L11;
        zztv r3 = zzabm.zza;
        zzst r22 = zzabm.zzb((zzyb) r2, zzti.zza());
    L19:
        zzse r32 = r1.zza();
        if ((r32 instanceof zzaax) == false) goto L23;
        zzady r33 = ((zzaax) r32).zze();
    L25:
        r62.zza(r33, new zzacs(r22, r1.zzc()));
        goto L30
    L23:
        if ((r32 instanceof zzui) == false) goto L26;
        r33 = ((zzui) r32).zzd();
        goto L25
    L26:
        String r52 = r32.getClass().getName();
        String r02 = String.valueOf(r32.zza());
        StringBuilder r23 = new StringBuilder((r52.length() + 59) + r02.length());
        r23.append("Cannot get output prefix for key of class ");
        r23.append(r52);
        r23.append(" with parameters ");
        r23.append(r02);
        throw new GeneralSecurityException(r23.toString());
    L11:
        if ((r2 instanceof zzaal) == false) goto L14;
        r22 = zzadv.zzb((zzaal) r2);
        goto L19
    L14:
        if ((r2 instanceof zzzz) == false) goto L17;
        r22 = zzadr.zzb((zzzz) r2);
        goto L19
    L17:
        if ((r2 instanceof zzyo) == false) goto L29;
        r22 = zzacx.zzb((zzyo) r2);
        goto L19
    L29:
        throw new GeneralSecurityException("Unknown key class: ".concat(String.valueOf(r2.getClass())));
    L30:
        r0 = r0 + 1;
        goto L4
    L31:
        r5.zze(zzul.class);
        zzum r53 = zzup.zza;
        return new zzacr(r62.zzb(), r53);
    }
}
