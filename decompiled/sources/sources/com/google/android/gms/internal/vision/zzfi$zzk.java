package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzk extends zzjb<zzfi$zzk, zza> implements zzkm {
    private static final zzfi$zzk zzj = null;
    private static volatile zzkx<zzfi$zzk> zzk;
    private int zzc;
    private String zzd;
    private long zze;
    private zzfi$zza zzf;
    private String zzg;
    private zzfi$zzg zzh;
    private zzfi$zzb zzi;

    public static final class zza extends zzjb.zzb<zzfi$zzk, zza> implements zzkm {
        private zza() {
            super(zzfi$zzk.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    static {
        zzfi$zzk r0 = new zzfi$zzk();
        zzj = r0;
        zzjb.zza(zzfi$zzk.class, r0);
    }

    private zzfi$zzk() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzfi$zzk zzb() {
        return zzj;
    }

    /* JADX WARN: Type inference failed for: r7v12, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzk>] */
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
        zzkx<zzfi$zzk> r7 = zzk;
        if (r7 == null) goto L11;
        return r7;
    L11:
        monitor-enter(zzfi$zzk.class);
        zzkx<zzfi$zzk> r72 = zzk;     // Catch: Throwable -> L15
        zzkx<zzfi$zzk> r73 = r72;
        if (r72 != null) goto L17;
        ?? r74 = new zzjb.zza(zzj);     // Catch: Throwable -> L15
        zzk = r74;     // Catch: Throwable -> L15
        r73 = r74;
    L17:
        monitor-exit(zzfi$zzk.class);     // Catch: Throwable -> L15
        return r73;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzfi$zzk r92 = zzj;
        return zzjb.zza(r92, "\u0001\u0006\u0000\u0001\u0001\u0011\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဉ\u0002\u0006ဈ\u0003\u0010ဉ\u0004\u0011ဉ\u0005", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
    L7:
        return (byte) 1;
    L23:
        return zzj;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzk();
    }
}
