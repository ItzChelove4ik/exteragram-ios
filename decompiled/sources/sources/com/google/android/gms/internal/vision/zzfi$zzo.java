package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzo extends zzjb<zzfi$zzo, zza> implements zzkm {
    private static final zzfi$zzo zzi = null;
    private static volatile zzkx<zzfi$zzo> zzj;
    private int zzc;
    private zzfi$zze zzd;
    private zzfi$zzk zze;
    private zzfi$zzi zzf;
    private int zzg;
    private boolean zzh;

    public static final class zza extends zzjb.zzb<zzfi$zzo, zza> implements zzkm {
        private zza() {
            super(zzfi$zzo.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }

        public final zza zza(zzfi$zzi r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzo.zza((zzfi$zzo) this.zza, r2);
            return this;
        }
    }

    static {
        zzfi$zzo r0 = new zzfi$zzo();
        zzi = r0;
        zzjb.zza(zzfi$zzo.class, r0);
    }

    private zzfi$zzo() {
    }

    public static zza zza() {
        return zzi.zzj();
    }

    private final void zza(zzfi$zzi r1) {
        r1.getClass();
        this.zzf = r1;
        this.zzc |= 4;
    }

    public static /* synthetic */ void zza(zzfi$zzo r0, zzfi$zzi r1) {
        r0.zza(r1);
    }

    public static /* synthetic */ zzfi$zzo zzb() {
        return zzi;
    }

    /* JADX WARN: Type inference failed for: r6v12, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzo>] */
    @Override // com.google.android.gms.internal.vision.zzjb
    public final Object zza(int r7, Object r8, Object r9) {
        switch(zzfk.zza[r7 - 1]) {
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
        zzkx<zzfi$zzo> r6 = zzj;
        if (r6 == null) goto L11;
        return r6;
    L11:
        monitor-enter(zzfi$zzo.class);
        zzkx<zzfi$zzo> r62 = zzj;     // Catch: Throwable -> L15
        zzkx<zzfi$zzo> r63 = r62;
        if (r62 != null) goto L17;
        ?? r64 = new zzjb.zza(zzi);     // Catch: Throwable -> L15
        zzj = r64;     // Catch: Throwable -> L15
        r63 = r64;
    L17:
        monitor-exit(zzfi$zzo.class);     // Catch: Throwable -> L15
        return r63;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzfi$zzo r82 = zzi;
        return zzjb.zza(r82, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004င\u0003\u0005ဇ\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh"});
    L7:
        return (byte) 1;
    L23:
        return zzi;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzo();
    }
}
