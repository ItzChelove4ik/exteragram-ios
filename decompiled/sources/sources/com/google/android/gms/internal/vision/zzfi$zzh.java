package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzh extends zzjb<zzfi$zzh, zza> implements zzkm {
    private static final zzfi$zzh zzj = null;
    private static volatile zzkx<zzfi$zzh> zzk;
    private int zzc;
    private float zzd;
    private float zze;
    private float zzf;
    private float zzg;
    private float zzh;
    private float zzi;

    public static final class zza extends zzjb.zzb<zzfi$zzh, zza> implements zzkm {
        private zza() {
            super(zzfi$zzh.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    static {
        zzfi$zzh r0 = new zzfi$zzh();
        zzj = r0;
        zzjb.zza(zzfi$zzh.class, r0);
    }

    private zzfi$zzh() {
    }

    public static /* synthetic */ zzfi$zzh zzb() {
        return zzj;
    }

    /* JADX WARN: Type inference failed for: r7v12, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzh>] */
    @Override // com.google.android.gms.internal.vision.zzjb
    public final Object zza(int r8, Object r9, Object r10) {
        switch(zzfk.zza[r8 - 1]) {
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
        zzkx<zzfi$zzh> r7 = zzk;
        if (r7 == null) goto L11;
        return r7;
    L11:
        monitor-enter(zzfi$zzh.class);
        zzkx<zzfi$zzh> r72 = zzk;     // Catch: Throwable -> L15
        zzkx<zzfi$zzh> r73 = r72;
        if (r72 != null) goto L17;
        ?? r74 = new zzjb.zza(zzj);     // Catch: Throwable -> L15
        zzk = r74;     // Catch: Throwable -> L15
        r73 = r74;
    L17:
        monitor-exit(zzfi$zzh.class);     // Catch: Throwable -> L15
        return r73;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzfi$zzh r92 = zzj;
        return zzjb.zza(r92, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
    L7:
        return (byte) 1;
    L23:
        return zzj;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzh();
    }
}
