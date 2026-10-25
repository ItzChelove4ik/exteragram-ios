package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public abstract class zzacq {
    public static zzss zza(zzsk r4, zzvt r5) {
        int r52 = zzwc.$r8$clinit;
        int r53 = 0;
    L4:
        if (r53 >= r4.zzc()) goto L26;
        zzsj r0 = (zzsj) r4;
        zzsi r1 = r0.zzd(r53);
        if (r1.zzd() == false) goto L25;
        if (r1.zzb().equals(zzsg.zza) == false) goto L25;
        r4.zze(zzul.class);
        zzum r42 = zzup.zza;
        zzse r2 = r0.zzb().zza();
        if ((r2 instanceof zzxz) == false) goto L13;
        zzss r22 = zzabl.zzb((zzxz) r2);
    L22:
        return new zzaco(new zzacp(r22, r0.zzb().zzc()), r42);
    L13:
        if ((r2 instanceof zzaaj) == false) goto L16;
        r22 = zzadt.zzb((zzaaj) r2);
        goto L22
    L16:
        if ((r2 instanceof zzzx) == false) goto L19;
        r22 = zzacd.zzb((zzzx) r2);
        goto L22
    L19:
        if ((r2 instanceof zzyi) == false) goto L24;
        r22 = zzacv.zzb((zzyi) r2);
        goto L22
    L24:
        throw new GeneralSecurityException("Unknown key class: ".concat(String.valueOf(r2.getClass())));
    L25:
        r53 = r53 + 1;
        goto L4
    L26:
        zzsj$$ExternalSyntheticBUOutline1.m("keyset doesn't contain a valid primary key");
        return null;
    }
}
