package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zza extends zzjb<zzfi$zza, zza> implements zzkm {
    private static final zzfi$zza zzf = null;
    private static volatile zzkx<zzfi$zza> zzg;
    private int zzc;
    private String zzd;
    private String zze;

    public static final class zza extends zzjb.zzb<zzfi$zza, zza> implements zzkm {
        private zza() {
            super(zzfi$zza.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }

        public final zza zza(String r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zza.zza((zzfi$zza) this.zza, r2);
            return this;
        }

        public final zza zzb(String r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zza.zzb((zzfi$zza) this.zza, r2);
            return this;
        }
    }

    static {
        zzfi$zza r0 = new zzfi$zza();
        zzf = r0;
        zzjb.zza(zzfi$zza.class, r0);
    }

    private zzfi$zza() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zza zza() {
        return zzf.zzj();
    }

    public static /* synthetic */ void zza(zzfi$zza r0, String r1) {
        r0.zza(r1);
    }

    private final void zza(String r2) {
        r2.getClass();
        this.zzc |= 1;
        this.zzd = r2;
    }

    public static /* synthetic */ zzfi$zza zzb() {
        return zzf;
    }

    public static /* synthetic */ void zzb(zzfi$zza r0, String r1) {
        r0.zzb(r1);
    }

    private final void zzb(String r2) {
        r2.getClass();
        this.zzc |= 2;
        this.zze = r2;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zza>] */
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
        zzkx<zzfi$zza> r0 = zzg;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(zzfi$zza.class);
        zzkx<zzfi$zza> r02 = zzg;     // Catch: Throwable -> L15
        zzkx<zzfi$zza> r03 = r02;
        if (r02 != null) goto L17;
        ?? r04 = new zzjb.zza(zzf);     // Catch: Throwable -> L15
        zzg = r04;     // Catch: Throwable -> L15
        r03 = r04;
    L17:
        monitor-exit(zzfi$zza.class);     // Catch: Throwable -> L15
        return r03;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzfi$zza r22 = zzf;
        return zzjb.zza(r22, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzc", "zzd", "zze"});
    L7:
        return (byte) 1;
    L23:
        return zzf;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zza();
    }
}
