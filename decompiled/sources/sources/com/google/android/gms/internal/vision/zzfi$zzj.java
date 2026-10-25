package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import kotlin.text.Typography;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzj extends zzjb<zzfi$zzj, zzb> implements zzkm {
    private static final zzfi$zzj zzi = null;
    private static volatile zzkx<zzfi$zzj> zzj;
    private int zzc;
    private int zzd;
    private long zze;
    private long zzf;
    private long zzg;
    private long zzh;

    public enum zza extends Enum<zza> implements zzje {
        private static final zza zza = null;
        private static final zza zzb = null;
        private static final zza zzc = null;
        private static final zza zzd = null;
        private static final zzjh<zza> zze = null;
        private static final /* synthetic */ zza[] zzg = null;
        private final int zzf;

        static {
            zza r0 = new zza("FORMAT_UNKNOWN", 0, 0);
            zza = r0;
            zza r1 = new zza("FORMAT_LUMINANCE", 1, 1);
            zzb = r1;
            zza r2 = new zza("FORMAT_RGB8", 2, 2);
            zzc = r2;
            zza r3 = new zza("FORMAT_MONOCHROME", 3, 3);
            zzd = r3;
            zzg = new zza[]{r0, r1, r2, r3};
            zze = new zzfx();
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
            return zzfw.zza;
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

    public static final class zzb extends zzjb.zzb<zzfi$zzj, zzb> implements zzkm {
        private zzb() {
            super(zzfi$zzj.zzb());
        }

        public /* synthetic */ zzb(zzfk r1) {
            this();
        }

        public final zzb zza(long r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzj.zza((zzfi$zzj) this.zza, r2);
            return this;
        }

        public final zzb zzb(long r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzj.zzb((zzfi$zzj) this.zza, r2);
            return this;
        }

        public final zzb zzc(long r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzj.zzc((zzfi$zzj) this.zza, r2);
            return this;
        }

        public final zzb zzd(long r2) {
            if (this.zzb == false) goto L5;
            zzb();
            this.zzb = false;
        L5:
            zzfi$zzj.zzd((zzfi$zzj) this.zza, r2);
            return this;
        }
    }

    static {
        zzfi$zzj r0 = new zzfi$zzj();
        zzi = r0;
        zzjb.zza(zzfi$zzj.class, r0);
    }

    private zzfi$zzj() {
    }

    public static zzb zza() {
        return zzi.zzj();
    }

    private final void zza(long r2) {
        this.zzc |= 2;
        this.zze = r2;
    }

    public static /* synthetic */ void zza(zzfi$zzj r0, long r1) {
        r0.zza(r1);
    }

    public static /* synthetic */ zzfi$zzj zzb() {
        return zzi;
    }

    private final void zzb(long r2) {
        this.zzc |= 4;
        this.zzf = r2;
    }

    public static /* synthetic */ void zzb(zzfi$zzj r0, long r1) {
        r0.zzb(r1);
    }

    private final void zzc(long r2) {
        this.zzc |= 8;
        this.zzg = r2;
    }

    public static /* synthetic */ void zzc(zzfi$zzj r0, long r1) {
        r0.zzc(r1);
    }

    private final void zzd(long r2) {
        this.zzc |= 16;
        this.zzh = r2;
    }

    public static /* synthetic */ void zzd(zzfi$zzj r0, long r1) {
        r0.zzd(r1);
    }

    /* JADX WARN: Type inference failed for: r7v12, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzj>] */
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
        zzkx<zzfi$zzj> r7 = zzj;
        if (r7 == null) goto L11;
        return r7;
    L11:
        monitor-enter(zzfi$zzj.class);
        zzkx<zzfi$zzj> r72 = zzj;     // Catch: Throwable -> L15
        zzkx<zzfi$zzj> r73 = r72;
        if (r72 != null) goto L17;
        ?? r74 = new zzjb.zza(zzi);     // Catch: Throwable -> L15
        zzj = r74;     // Catch: Throwable -> L15
        r73 = r74;
    L17:
        monitor-exit(zzfi$zzj.class);     // Catch: Throwable -> L15
        return r73;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r75 = {"zzc", "zzd", zza.zzb(), "zze", "zzf", "zzh", "zzg"};
        return zzjb.zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0004\u0005ဂ\u0003", r75);
    L7:
        return (byte) 1;
    L23:
        return zzi;
    L27:
        return new zzb(null);
    L29:
        return new zzfi$zzj();
    }
}
