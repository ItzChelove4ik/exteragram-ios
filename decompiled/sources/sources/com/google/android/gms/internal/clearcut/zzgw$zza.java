package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzgw$zza extends zzcg<zzgw$zza, zza> implements zzdq {
    private static volatile zzdz<zzgw$zza> zzbg;
    private static final zzgw$zza zzbir = null;
    private zzcn<zzb> zzbiq;

    public static final class zza extends zzcg.zza<zzgw$zza, zza> implements zzdq {
        private zza() {
            super(zzgw$zza.zzfu());
        }

        public /* synthetic */ zza(zzgx r1) {
            this();
        }
    }

    public static final class zzb extends zzcg<zzb, zza> implements zzdq {
        private static volatile zzdz<zzb> zzbg;
        private static final zzb zzbiv = null;
        private int zzbb;
        private String zzbis;
        private long zzbit;
        private long zzbiu;
        private int zzya;

        public static final class zza extends zzcg.zza<zzb, zza> implements zzdq {
            private zza() {
                super(zzb.zzga());
            }

            public /* synthetic */ zza(zzgx r1) {
                this();
            }

            public final zza zzn(String r2) {
                zzbf();
                zzb.zza((zzb) this.zzjt, r2);
                return this;
            }

            public final zza zzr(long r2) {
                zzbf();
                zzb.zza((zzb) this.zzjt, r2);
                return this;
            }

            public final zza zzs(long r2) {
                zzbf();
                zzb.zzb((zzb) this.zzjt, r2);
                return this;
            }
        }

        static {
            zzb r0 = new zzb();
            zzbiv = r0;
            zzcg.zza(zzb.class, r0);
        }

        private zzb() {
            this.zzbis = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public static /* synthetic */ void zza(zzb r0, long r1) {
            r0.zzp(r1);
        }

        public static /* synthetic */ void zza(zzb r0, String r1) {
            r0.zzm(r1);
        }

        public static /* synthetic */ void zzb(zzb r0, long r1) {
            r0.zzq(r1);
        }

        public static zza zzfz() {
            return (zza) ((zzcg.zza) zzbiv.zza(zzcg.zzg.zzkh, null, null));
        }

        public static /* synthetic */ zzb zzga() {
            return zzbiv;
        }

        private final void zzm(String r2) {
            r2.getClass();
            this.zzbb |= 2;
            this.zzbis = r2;
        }

        private final void zzp(long r2) {
            this.zzbb |= 4;
            this.zzbit = r2;
        }

        private final void zzq(long r2) {
            this.zzbb |= 8;
            this.zzbiu = r2;
        }

        public final int getEventCode() {
            return this.zzya;
        }

        /* JADX WARN: Type inference failed for: r1v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza$zzb>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int r2, Object r3, Object r4) {
            switch(zzgx.zzba[r2 - 1]) {
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
            zzdz<zzb> r1 = zzbg;
            if (r1 == null) goto L11;
            return r1;
        L11:
            monitor-enter(zzb.class);
            zzdz<zzb> r12 = zzbg;     // Catch: Throwable -> L15
            zzdz<zzb> r13 = r12;
            if (r12 != null) goto L17;
            ?? r14 = new zzcg.zzb(zzbiv);     // Catch: Throwable -> L15
            zzbg = r14;     // Catch: Throwable -> L15
            r13 = r14;
        L17:
            monitor-exit(zzb.class);     // Catch: Throwable -> L15
            return r13;
        L15:
            th = move-exception;
            throw th;
        L24:
            zzb r32 = zzbiv;
            return zzcg.zza(r32, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0002\u0003", new Object[]{"zzbb", "zzya", "zzbis", "zzbit", "zzbiu"});
        L7:
            return (byte) 1;
        L23:
            return zzbiv;
        L27:
            return new zza(null);
        L29:
            return new zzb();
        }

        public final boolean zzfv() {
            if ((this.zzbb & 1) != 1) goto L5;
            return true;
        L5:
            return false;
        }

        public final String zzfw() {
            return this.zzbis;
        }

        public final long zzfx() {
            return this.zzbit;
        }

        public final long zzfy() {
            return this.zzbiu;
        }
    }

    static {
        zzgw$zza r0 = new zzgw$zza();
        zzbir = r0;
        zzcg.zza(zzgw$zza.class, r0);
    }

    private zzgw$zza() {
        this.zzbiq = zzcg.zzbb();
    }

    public static zzgw$zza zzft() {
        return zzbir;
    }

    public static /* synthetic */ zzgw$zza zzfu() {
        return zzbir;
    }

    public static zzgw$zza zzi(byte[] r1) {
        return (zzgw$zza) zzcg.zzb(zzbir, r1);
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgw$zza>] */
    @Override // com.google.android.gms.internal.clearcut.zzcg
    public final Object zza(int r1, Object r2, Object r3) {
        switch(zzgx.zzba[r1 - 1]) {
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
        zzdz<zzgw$zza> r0 = zzbg;
        if (r0 == null) goto L11;
        return r0;
    L11:
        monitor-enter(zzgw$zza.class);
        zzdz<zzgw$zza> r02 = zzbg;     // Catch: Throwable -> L15
        zzdz<zzgw$zza> r03 = r02;
        if (r02 != null) goto L17;
        ?? r04 = new zzcg.zzb(zzbir);     // Catch: Throwable -> L15
        zzbg = r04;     // Catch: Throwable -> L15
        r03 = r04;
    L17:
        monitor-exit(zzgw$zza.class);     // Catch: Throwable -> L15
        return r03;
    L15:
        th = move-exception;
        throw th;
    L24:
        zzgw$zza r22 = zzbir;
        return zzcg.zza(r22, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzbiq", zzb.class});
    L7:
        return (byte) 1;
    L23:
        return zzbir;
    L27:
        return new zza(null);
    L29:
        return new zzgw$zza();
    }

    public final List<zzb> zzfs() {
        return this.zzbiq;
    }
}
