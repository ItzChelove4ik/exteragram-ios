package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzn extends zzjb<zzfi$zzn, zza> implements zzkm {
    private static final zzfi$zzn zzh = null;
    private static volatile zzkx<zzfi$zzn> zzi;
    private int zzc;
    private zzfi$zzd zzd;
    private int zze;
    private zzfi$zzh zzf;
    private zzfi$zzc zzg;

    public static final class zza extends zzjb.zzb<zzfi$zzn, zza> implements zzkm {
        private zza() {
            super(zzfi$zzn.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    static {
        zzfi$zzn r0 = new zzfi$zzn();
        zzh = r0;
        zzjb.zza(zzfi$zzn.class, r0);
    }

    private zzfi$zzn() {
    }

    public static /* synthetic */ zzfi$zzn zzb() {
        return zzh;
    }

    /* JADX WARN: Type inference failed for: r1v13, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzn>] */
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
        zzkx<zzfi$zzn> r1 = zzi;
        if (r1 == null) goto L11;
        return r1;
    L11:
        monitor-enter(zzfi$zzn.class);
        zzkx<zzfi$zzn> r12 = zzi;     // Catch: Throwable -> L15
        zzkx<zzfi$zzn> r13 = r12;
        if (r12 != null) goto L17;
        ?? r14 = new zzjb.zza(zzh);     // Catch: Throwable -> L15
        zzi = r14;     // Catch: Throwable -> L15
        r13 = r14;
    L17:
        monitor-exit(zzfi$zzn.class);     // Catch: Throwable -> L15
        return r13;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzfi$zzn r32 = zzh;
        return zzjb.zza(r32, "\u0001\u0004\u0000\u0001\u0001\u0011\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002င\u0001\u0010ဉ\u0002\u0011ဉ\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
    L7:
        return (byte) 1;
    L23:
        return zzh;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzn();
    }
}
