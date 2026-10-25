package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzc extends zzjb<zzfi$zzc, zza> implements zzkm {
    private static final zzfi$zzc zzg = null;
    private static volatile zzkx<zzfi$zzc> zzh;
    private int zzc;
    private int zzd;
    private int zze;
    private String zzf;

    public static final class zza extends zzjb.zzb<zzfi$zzc, zza> implements zzkm {
        private zza() {
            super(zzfi$zzc.zza());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    static {
        zzfi$zzc r0 = new zzfi$zzc();
        zzg = r0;
        zzjb.zza(zzfi$zzc.class, r0);
    }

    private zzfi$zzc() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzfi$zzc zza() {
        return zzg;
    }

    /* JADX WARN: Type inference failed for: r6v12, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzc>] */
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
        zzkx<zzfi$zzc> r6 = zzh;
        if (r6 == null) goto L11;
        return r6;
    L11:
        monitor-enter(zzfi$zzc.class);
        zzkx<zzfi$zzc> r62 = zzh;     // Catch: Throwable -> L15
        zzkx<zzfi$zzc> r63 = r62;
        if (r62 != null) goto L17;
        ?? r64 = new zzjb.zza(zzg);     // Catch: Throwable -> L15
        zzh = r64;     // Catch: Throwable -> L15
        r63 = r64;
    L17:
        monitor-exit(zzfi$zzc.class);     // Catch: Throwable -> L15
        return r63;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r65 = {"zzc", "zzd", zzgz.zzb(), "zze", zzha.zzb(), "zzf"};
        return zzjb.zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဈ\u0002", r65);
    L7:
        return (byte) 1;
    L23:
        return zzg;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzc();
    }
}
