package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzf extends zzjb<zzfi$zzf, zzb> implements zzkm {
    private static final zzfi$zzf zzl = null;
    private static volatile zzkx<zzfi$zzf> zzm;
    private int zzc;
    private String zzd;
    private String zze;
    private zzjl<String> zzf;
    private int zzg;
    private String zzh;
    private long zzi;
    private long zzj;
    private zzjl<zzfi$zzn> zzk;

    public enum zza extends Enum<zza> implements zzje {
        private static final zza zza = null;
        private static final zza zzb = null;
        private static final zza zzc = null;
        private static final zza zzd = null;
        private static final zzjh<zza> zze = null;
        private static final /* synthetic */ zza[] zzg = null;
        private final int zzf;

        static {
            zza r0 = new zza("RESULT_UNKNOWN", 0, 0);
            zza = r0;
            zza r1 = new zza("RESULT_SUCCESS", 1, 1);
            zzb = r1;
            zza r2 = new zza("RESULT_FAIL", 2, 2);
            zzc = r2;
            zza r3 = new zza("RESULT_SKIPPED", 3, 3);
            zzd = r3;
            zzg = new zza[]{r0, r1, r2, r3};
            zze = new zzfp();
        }

        zza(String r1, int r2, int r3) {
            this.zzf = r3;
        }

        public static zza[] values() {
            return (zza[]) zzg.clone();
        }

        public static zza zza(int r1) {
            if (r1 == 0) goto L18;
            if (r1 == 1) goto L16;
            if (r1 == 2) goto L14;
            if (r1 == 3) goto L12;
            return null;
        L12:
            return zzd;
        L14:
            return zzc;
        L16:
            return zzb;
        L18:
            return zza;
        }

        public static zzjg zzb() {
            return zzfo.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zza.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.gms.internal.vision.zzje
        public final int zza() {
            return this.zzf;
        }
    }

    public static final class zzb extends zzjb.zzb<zzfi$zzf, zzb> implements zzkm {
        private zzb() {
            super(zzfi$zzf.zzb());
        }

        public /* synthetic */ zzb(zzfk r1) {
            this();
        }

        public final zzb zza(long r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzf.zza((zzfi$zzf) this.zza, r2);
            return this;
        }

        public final zzb zza(Iterable<? extends zzfi$zzn> r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzf.zza((zzfi$zzf) this.zza, r2);
            return this;
        }

        public final zzb zza(String r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzf.zza((zzfi$zzf) this.zza, r2);
            return this;
        }

        public final zzb zzb(long r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzf.zzb((zzfi$zzf) this.zza, r2);
            return this;
        }
    }

    static {
        zzfi$zzf r0 = new zzfi$zzf();
        zzl = r0;
        zzjb.zza(zzfi$zzf.class, r0);
    }

    private zzfi$zzf() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzjb.zzo();
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzk = zzjb.zzo();
    }

    public static zzb zza() {
        return zzl.zzj();
    }

    private final void zza(long r2) {
        this.zzc |= 16;
        this.zzi = r2;
    }

    public static /* synthetic */ void zza(zzfi$zzf r0, long r1) {
        r0.zza(r1);
    }

    public static /* synthetic */ void zza(zzfi$zzf r0, Iterable r1) {
        r0.zza(r1);
    }

    public static /* synthetic */ void zza(zzfi$zzf r0, String r1) {
        r0.zza(r1);
    }

    private final void zza(Iterable<? extends zzfi$zzn> r3) {
        zzjl<zzfi$zzn> r0 = this.zzk;
        if (r0.zza() == true) goto L5;
        this.zzk = zzjb.zza(r0);
    L5:
        zzhf.zza(r3, this.zzk);
    }

    private final void zza(String r2) {
        r2.getClass();
        this.zzc |= 1;
        this.zzd = r2;
    }

    public static /* synthetic */ zzfi$zzf zzb() {
        return zzl;
    }

    private final void zzb(long r2) {
        this.zzc |= 32;
        this.zzj = r2;
    }

    public static /* synthetic */ void zzb(zzfi$zzf r0, long r1) {
        r0.zzb(r1);
    }

    /* JADX WARN: Type inference failed for: r11v12, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzf>] */
    @Override // com.google.android.gms.internal.vision.zzjb
    public final Object zza(int r12, Object r13, Object r14) {
        switch(zzfk.zza[r12 - 1]) {
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
        zzkx<zzfi$zzf> r11 = zzm;
        if (r11 == null) goto L11;
        return r11;
    L11:
        monitor-enter(zzfi$zzf.class);
        zzkx<zzfi$zzf> r112 = zzm;     // Catch: Throwable -> L15
        zzkx<zzfi$zzf> r113 = r112;
        if (r112 != null) goto L17;
        ?? r114 = new zzjb.zza(zzl);     // Catch: Throwable -> L15
        zzm = r114;     // Catch: Throwable -> L15
        r113 = r114;
    L17:
        monitor-exit(zzfi$zzf.class);     // Catch: Throwable -> L15
        return r113;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r115 = {"zzc", "zzd", "zze", "zzf", "zzg", zza.zzb(), "zzh", "zzi", "zzj", "zzk", zzfi$zzn.class};
        return zzjb.zza(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0002\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003\u001a\u0004ဌ\u0002\u0005ဈ\u0003\u0006ဂ\u0004\u0007ဂ\u0005\b\u001b", r115);
    L7:
        return (byte) 1;
    L23:
        return zzl;
    L27:
        return new zzb(null);
    L29:
        return new zzfi$zzf();
    }
}
