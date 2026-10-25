package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import kotlin.text.Typography;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zzg extends zzjb<zzfi$zzg, zza> implements zzkm {
    private static final zzfi$zzg zzj = null;
    private static volatile zzkx<zzfi$zzg> zzk;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private boolean zzg;
    private boolean zzh;
    private float zzi;

    public static final class zza extends zzjb.zzb<zzfi$zzg, zza> implements zzkm {
        private zza() {
            super(zzfi$zzg.zzb());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    public enum zzb extends Enum<zzb> implements zzje {
        public static final zzb zza = null;
        public static final zzb zzb = null;
        private static final zzb zzc = null;
        private static final zzjh<zzb> zzd = null;
        private static final /* synthetic */ zzb[] zzf = null;
        private final int zze;

        static {
            zzb r0 = new zzb("CLASSIFICATION_UNKNOWN", 0, 0);
            zzc = r0;
            zzb r1 = new zzb("CLASSIFICATION_NONE", 1, 1);
            zza = r1;
            zzb r2 = new zzb("CLASSIFICATION_ALL", 2, 2);
            zzb = r2;
            zzf = new zzb[]{r0, r1, r2};
            zzd = new zzfq();
        }

        zzb(String r1, int r2, int r3) {
            this.zze = r3;
        }

        public static zzb[] values() {
            return (zzb[]) zzf.clone();
        }

        public static zzb zza(int r1) {
            if (r1 == 0) goto L14;
            if (r1 == 1) goto L12;
            if (r1 == 2) goto L10;
            return null;
        L10:
            return zzb;
        L12:
            return zza;
        L14:
            return zzc;
        }

        public static zzjg zzb() {
            return zzfr.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.gms.internal.vision.zzje
        public final int zza() {
            return this.zze;
        }
    }

    public enum zzc extends Enum<zzc> implements zzje {
        public static final zzc zza = null;
        public static final zzc zzb = null;
        public static final zzc zzc = null;
        private static final zzc zzd = null;
        private static final zzjh<zzc> zze = null;
        private static final /* synthetic */ zzc[] zzg = null;
        private final int zzf;

        static {
            zzc r0 = new zzc("LANDMARK_UNKNOWN", 0, 0);
            zzd = r0;
            zzc r1 = new zzc("LANDMARK_NONE", 1, 1);
            zza = r1;
            zzc r2 = new zzc("LANDMARK_ALL", 2, 2);
            zzb = r2;
            zzc r3 = new zzc("LANDMARK_CONTOUR", 3, 3);
            zzc = r3;
            zzg = new zzc[]{r0, r1, r2, r3};
            zze = new zzft();
        }

        zzc(String r1, int r2, int r3) {
            this.zzf = r3;
        }

        public static zzc[] values() {
            return (zzc[]) zzg.clone();
        }

        public static zzc zza(int r1) {
            if (r1 == 0) goto L18;
            if (r1 == 1) goto L16;
            if (r1 == 2) goto L14;
            if (r1 == 3) goto L12;
            return null;
        L12:
            return zzc;
        L14:
            return zzb;
        L16:
            return zza;
        L18:
            return zzd;
        }

        public static zzjg zzb() {
            return zzfs.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zzc.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.gms.internal.vision.zzje
        public final int zza() {
            return this.zzf;
        }
    }

    public enum zzd extends Enum<zzd> implements zzje {
        public static final zzd zza = null;
        public static final zzd zzb = null;
        public static final zzd zzc = null;
        private static final zzd zzd = null;
        private static final zzjh<zzd> zze = null;
        private static final /* synthetic */ zzd[] zzg = null;
        private final int zzf;

        static {
            zzd r0 = new zzd("MODE_UNKNOWN", 0, 0);
            zzd = r0;
            zzd r1 = new zzd("MODE_ACCURATE", 1, 1);
            zza = r1;
            zzd r2 = new zzd("MODE_FAST", 2, 2);
            zzb = r2;
            zzd r3 = new zzd("MODE_SELFIE", 3, 3);
            zzc = r3;
            zzg = new zzd[]{r0, r1, r2, r3};
            zze = new zzfu();
        }

        zzd(String r1, int r2, int r3) {
            this.zzf = r3;
        }

        public static zzd[] values() {
            return (zzd[]) zzg.clone();
        }

        public static zzd zza(int r1) {
            if (r1 == 0) goto L18;
            if (r1 == 1) goto L16;
            if (r1 == 2) goto L14;
            if (r1 == 3) goto L12;
            return null;
        L12:
            return zzc;
        L14:
            return zzb;
        L16:
            return zza;
        L18:
            return zzd;
        }

        public static zzjg zzb() {
            return zzfv.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zzd.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.gms.internal.vision.zzje
        public final int zza() {
            return this.zzf;
        }
    }

    static {
        zzfi$zzg r0 = new zzfi$zzg();
        zzj = r0;
        zzjb.zza(zzfi$zzg.class, r0);
    }

    private zzfi$zzg() {
    }

    public static /* synthetic */ zzfi$zzg zzb() {
        return zzj;
    }

    /* JADX WARN: Type inference failed for: r10v12, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zzg>] */
    @Override // com.google.android.gms.internal.vision.zzjb
    public final Object zza(int r11, Object r12, Object r13) {
        switch(zzfk.zza[r11 - 1]) {
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
        zzkx<zzfi$zzg> r10 = zzk;
        if (r10 == null) goto L11;
        return r10;
    L11:
        monitor-enter(zzfi$zzg.class);
        zzkx<zzfi$zzg> r102 = zzk;     // Catch: Throwable -> L15
        zzkx<zzfi$zzg> r103 = r102;
        if (r102 != null) goto L17;
        ?? r104 = new zzjb.zza(zzj);     // Catch: Throwable -> L15
        zzk = r104;     // Catch: Throwable -> L15
        r103 = r104;
    L17:
        monitor-exit(zzfi$zzg.class);     // Catch: Throwable -> L15
        return r103;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r105 = {"zzc", "zzd", zzd.zzb(), "zze", zzc.zzb(), "zzf", zzb.zzb(), "zzg", "zzh", "zzi"};
        return zzjb.zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဌ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ခ\u0005", r105);
    L7:
        return (byte) 1;
    L23:
        return zzj;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zzg();
    }
}
