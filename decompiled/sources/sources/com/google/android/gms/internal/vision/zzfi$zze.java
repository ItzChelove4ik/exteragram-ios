package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzfi$zze extends zzjb<zzfi$zze, zza> implements zzkm {
    private static final zzfi$zze zzl = null;
    private static volatile zzkx<zzfi$zze> zzm;
    private int zzc;
    private String zzd;
    private boolean zze;
    private int zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    private String zzj;
    private boolean zzk;

    public static final class zza extends zzjb.zzb<zzfi$zze, zza> implements zzkm {
        private zza() {
            super(zzfi$zze.zza());
        }

        public /* synthetic */ zza(zzfk r1) {
            this();
        }
    }

    public enum zzb extends Enum<zzb> implements zzje {
        private static final zzb zza = null;
        private static final zzb zzb = null;
        private static final zzb zzc = null;
        private static final zzb zzd = null;
        private static final zzjh<zzb> zze = null;
        private static final /* synthetic */ zzb[] zzg = null;
        private final int zzf;

        static {
            zzb r0 = new zzb("REASON_UNKNOWN", 0, 0);
            zza = r0;
            zzb r1 = new zzb("REASON_MISSING", 1, 1);
            zzb = r1;
            zzb r2 = new zzb("REASON_UPGRADE", 2, 2);
            zzc = r2;
            zzb r3 = new zzb("REASON_INVALID", 3, 3);
            zzd = r3;
            zzg = new zzb[]{r0, r1, r2, r3};
            zze = new zzfm();
        }

        zzb(String r1, int r2, int r3) {
            this.zzf = r3;
        }

        public static zzb[] values() {
            return (zzb[]) zzg.clone();
        }

        public static zzb zza(int r1) {
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
            return zzfn.zza;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzf + " name=" + name() + Typography.greater;
        }

        @Override // com.google.android.gms.internal.vision.zzje
        public final int zza() {
            return this.zzf;
        }
    }

    static {
        zzfi$zze r0 = new zzfi$zze();
        zzl = r0;
        zzjb.zza(zzfi$zze.class, r0);
    }

    private zzfi$zze() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzfi$zze zza() {
        return zzl;
    }

    /* JADX WARN: Type inference failed for: r10v12, types: [com.google.android.gms.internal.vision.zzjb$zza, com.google.android.gms.internal.vision.zzkx<com.google.android.gms.internal.vision.zzfi$zze>] */
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
        zzkx<zzfi$zze> r10 = zzm;
        if (r10 == null) goto L11;
        return r10;
    L11:
        monitor-enter(zzfi$zze.class);
        zzkx<zzfi$zze> r102 = zzm;     // Catch: Throwable -> L15
        zzkx<zzfi$zze> r103 = r102;
        if (r102 != null) goto L17;
        ?? r104 = new zzjb.zza(zzl);     // Catch: Throwable -> L15
        zzm = r104;     // Catch: Throwable -> L15
        r103 = r104;
    L17:
        monitor-exit(zzfi$zze.class);     // Catch: Throwable -> L15
        return r103;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r105 = {"zzc", "zzd", "zze", "zzf", zzb.zzb(), "zzg", "zzh", "zzi", "zzj", "zzk"};
        return zzjb.zza(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဌ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဈ\u0006\bဇ\u0007", r105);
    L7:
        return (byte) 1;
    L23:
        return zzl;
    L27:
        return new zza(null);
    L29:
        return new zzfi$zze();
    }
}
