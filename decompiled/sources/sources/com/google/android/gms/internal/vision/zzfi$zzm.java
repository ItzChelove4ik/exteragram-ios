package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzm extends zzjb<zzfi$zzm, zza> implements zzkm {
    private static final zzfi$zzm zzf = null;
    private static volatile zzkx<zzfi$zzm> zzg;
    private int zzc;
    private int zzd;
    private int zze;

    public static final class zza extends zzjb.zzb<zzfi$zzm, zza> implements zzkm {
        private zza() {
            super(zzfi$zzm.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    static {
        zzfi$zzm r0 = new zzfi$zzm();
        zzf = r0;
        zzjb.zza(zzfi$zzm.class, r0);
    }

    private zzfi$zzm() {
    }

    public static /* synthetic */ zzfi$zzm zzb() {
        return zzf;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzm>] */
    @Override // com.google.android.gms.internal.vision.zzjb
    public final Object zza(int r1, Object r2, Object r3) {
        switch(zzfk.zza[r1 - 1]) {
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
        zzkx<zzfi$zzm> r0 = zzg;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(zzfi$zzm.class);
        zzkx<zzfi$zzm> r02 = zzg;     // Catch: Throwable -> L15
        zzkx<zzfi$zzm> r03 = r02;
        if (r02 != null) goto L17;
        ?? r04 = new zzjb.zza(zzf);     // Catch: Throwable -> L15
        zzg = r04;     // Catch: Throwable -> L15
        r03 = r04;
    L17:
        monitor-exit(zzfi$zzm.class);     // Catch: Throwable -> L15
        return r03;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzfi$zzm r22 = zzf;
        return zzjb.zza(r22, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzc", "zzd", "zze"});
    L7:
        return (byte) 1;
    L23:
        return zzf;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzm();
    }
}
