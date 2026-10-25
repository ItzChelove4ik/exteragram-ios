package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzge$zzd extends zzcg<zzge$zzd, zza> implements zzdq {
    private static volatile zzdz<zzge$zzd> zzbg;
    private static final zzge$zzd zztx = null;
    private int zzbb;
    private int zztu;
    private String zztv;
    private String zztw;

    public static final class zza extends zzcg.zza<zzge$zzd, zza> implements zzdq {
        private zza() {
            super(zzge$zzd.zzew());
        }

        public /* synthetic */ zza(zzgf r1) {
            this();
        }
    }

    static {
        zzge$zzd r0 = new zzge$zzd();
        zztx = r0;
        zzcg.zza(zzge$zzd.class, r0);
    }

    private zzge$zzd() {
        this.zztv = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zztw = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzge$zzd zzew() {
        return zztx;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzd>] */
    @Override // com.google.android.gms.internal.clearcut.zzcg
    public final Object zza(int r1, Object r2, Object r3) {
        switch(zzgf.zzba[r1 - 1]) {
            case 1: goto L29;
            case 2: goto L27;
            case 3: goto L24;
            case 4: goto L23;
            case 5: goto L8;
            case 6: goto L7;
            case 7: goto L5;
            default: goto L4;
        };
    L4:
        Type$$ExternalSyntheticBUOutline0.m();
    L5:
        return null;
    L8:
        zzdz<zzge$zzd> r0 = zzbg;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(zzge$zzd.class);
        zzdz<zzge$zzd> r02 = zzbg;     // Catch: Throwable -> L15
        zzdz<zzge$zzd> r03 = r02;
        if (r02 != null) goto L17;
        ?? r04 = new zzcg.zzb(zztx);     // Catch: Throwable -> L15
        zzbg = r04;     // Catch: Throwable -> L15
        r03 = r04;
    L17:
        monitor-exit(zzge$zzd.class);     // Catch: Throwable -> L15
        return r03;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzge$zzd r22 = zztx;
        return zzcg.zza(r22, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\b\u0002", new Object[]{"zzbb", "zztu", "zztv", "zztw"});
    L7:
        return (byte) 1;
    L23:
        return zztx;
    L27:
        return new zza(null);
    L29:
        return new zzge$zzd();
    }
}
