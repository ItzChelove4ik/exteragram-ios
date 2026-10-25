package com.google.android.recaptcha.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.UUID;

/* loaded from: classes5.dex */
public final class zzlj {
    private static final String zzd = null;
    private final zzlr zza;
    private final zzli zzb;
    private final zzhd zzc;

    static {
        zzd = UUID.randomUUID().toString();
    }

    public zzlj(zzlr r1, zzli r2, zzhd r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static final /* synthetic */ String zzb() {
        return zzd;
    }

    public final void zza(zzajh r5, String r6, zzaja r7) {
        if (r7 == null) goto L4;
        r5.zzf(r7);
    L4:
        Iterator r72 = this.zzc.zza().iterator();
    L6:
        if (r72.hasNext() == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r72.next());
        r5.zzt(0);
        goto L6
    L9:
        if (r5.zzu() == false) goto L11;
        int r73 = zzhj.$r8$clinit;
        int r74 = r5.zzv();
        int r75 = r74 + 20000;
        zzhj.zza(r75, zzaio.zzb(r5.zzd()) * 1000);
    L12:
        r5.zzg(this.zzb.zza(r6));
        zzamj r62 = zzamk.zzd();
        r62.zza(r5);
        zzamk r52 = (zzamk) r62.zzp();
        this.zza.zza(r52);
        return;
    L11:
        int r76 = zzhj.$r8$clinit;
        int r77 = r5.zzx();
        long r2 = zzaio.zzb(r5.zzd()) * 1000;
        zzhj.zza(zzaji.zza(r77) + 10000, r2);
        goto L12
    }
}
