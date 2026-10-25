package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzi extends zzjb<zzfi$zzi, zza> implements zzkm {
    private static final zzfi$zzi zzg = null;
    private static volatile zzkx<zzfi$zzi> zzh;
    private int zzc;
    private zzfi$zzj zzd;
    private zzfi$zzl zze;
    private zzjl<zzfi$zzf> zzf;

    public static final class zza extends zzjb.zzb<zzfi$zzi, zza> implements zzkm {
        private zza() {
            super(zzfi$zzi.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }

        public final zza zza(zzfi$zzj r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzi.zza((zzfi$zzi) this.zza, r2);
            return this;
        }

        public final zza zza(Iterable<? extends zzfi$zzf> r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzi.zza((zzfi$zzi) this.zza, r2);
            return this;
        }
    }

    static {
        zzfi$zzi r0 = new zzfi$zzi();
        zzg = r0;
        zzjb.zza(zzfi$zzi.class, r0);
    }

    private zzfi$zzi() {
        this.zzf = zzjb.zzo();
    }

    public static zza zza() {
        return zzg.zzj();
    }

    public static /* synthetic */ void zza(zzfi$zzi r0, zzfi$zzj r1) {
        r0.zza(r1);
    }

    public static /* synthetic */ void zza(zzfi$zzi r0, Iterable r1) {
        r0.zza(r1);
    }

    private final void zza(zzfi$zzj r1) {
        r1.getClass();
        this.zzd = r1;
        this.zzc |= 1;
    }

    private final void zza(Iterable<? extends zzfi$zzf> r1) {
        zzc();
        zzhf.zza(r1, this.zzf);
    }

    public static /* synthetic */ zzfi$zzi zzb() {
        return zzg;
    }

    private final void zzc() {
        zzjl<zzfi$zzf> r0 = this.zzf;
        if (r0.zza() == true) goto L6;
        this.zzf = zzjb.zza(r0);
        return;
    }

    /* JADX WARN: Type inference failed for: r1v13, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzi>] */
    @Override // com.google.android.gms.internal.vision.zzjb
    public final Object zza(int r2, Object r3, Object r4) {
        switch(zzfk.zza[r2 - 1]) {
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
        zzkx<zzfi$zzi> r1 = zzh;
        if (r1 == null) goto L11;
        return r1;
    L11:
        monitor-enter(zzfi$zzi.class);
        zzkx<zzfi$zzi> r12 = zzh;     // Catch: Throwable -> L15
        zzkx<zzfi$zzi> r13 = r12;
        if (r12 != null) goto L17;
        ?? r14 = new zzjb.zza(zzg);     // Catch: Throwable -> L15
        zzh = r14;     // Catch: Throwable -> L15
        r13 = r14;
    L17:
        monitor-exit(zzfi$zzi.class);     // Catch: Throwable -> L15
        return r13;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzfi$zzi r32 = zzg;
        return zzjb.zza(r32, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", zzfi$zzf.class});
    L7:
        return (byte) 1;
    L23:
        return zzg;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzi();
    }
}
