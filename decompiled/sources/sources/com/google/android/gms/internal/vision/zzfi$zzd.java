package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzd extends zzjb<zzfi$zzd, zza> implements zzkm {
    private static final zzfi$zzd zzd = null;
    private static volatile zzkx<zzfi$zzd> zze;
    private zzjl<zzfi$zzm> zzc;

    public static final class zza extends zzjb.zzb<zzfi$zzd, zza> implements zzkm {
        private zza() {
            super(zzfi$zzd.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    static {
        zzfi$zzd r0 = new zzfi$zzd();
        zzd = r0;
        zzjb.zza(zzfi$zzd.class, r0);
    }

    private zzfi$zzd() {
        this.zzc = zzjb.zzo();
    }

    public static /* synthetic */ zzfi$zzd zzb() {
        return zzd;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzd>] */
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
        zzkx<zzfi$zzd> r0 = zze;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(zzfi$zzd.class);
        zzkx<zzfi$zzd> r02 = zze;     // Catch: Throwable -> L15
        zzkx<zzfi$zzd> r03 = r02;
        if (r02 != null) goto L17;
        ?? r04 = new zzjb.zza(zzd);     // Catch: Throwable -> L15
        zze = r04;     // Catch: Throwable -> L15
        r03 = r04;
    L17:
        monitor-exit(zzfi$zzd.class);     // Catch: Throwable -> L15
        return r03;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzfi$zzd r22 = zzd;
        return zzjb.zza(r22, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzfi$zzm.class});
    L7:
        return (byte) 1;
    L23:
        return zzd;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzd();
    }
}
