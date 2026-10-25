package com.google.android.gms.internal.clearcut;

import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes4.dex */
public enum zzge$zzv$zzb extends Enum<zzge$zzv$zzb> implements zzcj {
    public static final zzge$zzv$zzb zzbhk = null;
    private static final zzge$zzv$zzb zzbhl = null;
    private static final zzge$zzv$zzb zzbhm = null;
    private static final zzge$zzv$zzb zzbhn = null;
    private static final zzge$zzv$zzb zzbho = null;
    private static final /* synthetic */ zzge$zzv$zzb[] zzbhp = null;
    private static final zzck<zzge$zzv$zzb> zzbq = null;
    private final int value;

    static {
        zzge$zzv$zzb r0 = new zzge$zzv$zzb(PluginsConstants.Strategy.DEFAULT, 0, 0);
        zzbhk = r0;
        zzge$zzv$zzb r1 = new zzge$zzv$zzb("UNMETERED_ONLY", 1, 1);
        zzbhl = r1;
        zzge$zzv$zzb r2 = new zzge$zzv$zzb("UNMETERED_OR_DAILY", 2, 2);
        zzbhm = r2;
        zzge$zzv$zzb r3 = new zzge$zzv$zzb("FAST_IF_RADIO_AWAKE", 3, 3);
        zzbhn = r3;
        zzge$zzv$zzb r4 = new zzge$zzv$zzb("NEVER", 4, 4);
        zzbho = r4;
        zzbhp = new zzge$zzv$zzb[]{r0, r1, r2, r3, r4};
        zzbq = new zzgr();
    }

    zzge$zzv$zzb(String r1, int r2, int r3) {
        this.value = r3;
    }

    public static zzge$zzv$zzb[] values() {
        return (zzge$zzv$zzb[]) zzbhp.clone();
    }

    public static zzge$zzv$zzb zzbc(int r1) {
        if (r1 == 0) goto L22;
        if (r1 == 1) goto L20;
        if (r1 == 2) goto L18;
        if (r1 == 3) goto L16;
        if (r1 == 4) goto L14;
        return null;
    L14:
        return zzbho;
    L16:
        return zzbhn;
    L18:
        return zzbhm;
    L20:
        return zzbhl;
    L22:
        return zzbhk;
    }

    public final int zzc() {
        return this.value;
    }
}
