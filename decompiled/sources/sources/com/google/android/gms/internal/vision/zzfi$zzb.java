package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzb extends zzjb<zzfi$zzb, zza> implements zzkm {
    private static final zzji<Integer, zzgz> zzd = null;
    private static final zzfi$zzb zze = null;
    private static volatile zzkx<zzfi$zzb> zzf;
    private zzjj zzc;

    public static final class zza extends zzjb.zzb<zzfi$zzb, zza> implements zzkm {
        private zza() {
            super(zzfi$zzb.zza());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzfl, com.google.android.gms.internal.vision.zzji<java.lang.Integer, com.google.android.gms.internal.vision.zzgz>] */
    static {
        zzd = new zzfl();
        zzfi$zzb r0 = new zzfi$zzb();
        zze = r0;
        zzjb.zza(zzfi$zzb.class, r0);
    }

    private zzfi$zzb() {
        this.zzc = zzjb.zzn();
    }

    public static /* synthetic */ zzfi$zzb zza() {
        return zze;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzb>] */
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
        zzkx<zzfi$zzb> r0 = zzf;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(zzfi$zzb.class);
        zzkx<zzfi$zzb> r02 = zzf;     // Catch: Throwable -> L15
        zzkx<zzfi$zzb> r03 = r02;
        if (r02 != null) goto L17;
        ?? r04 = new zzjb.zza(zze);     // Catch: Throwable -> L15
        zzf = r04;     // Catch: Throwable -> L15
        r03 = r04;
    L17:
        monitor-exit(zzfi$zzb.class);     // Catch: Throwable -> L15
        return r03;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r05 = {"zzc", zzgz.zzb()};
        return zzjb.zza(zze, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001e", r05);
    L7:
        return (byte) 1;
    L23:
        return zze;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzb();
    }
}
