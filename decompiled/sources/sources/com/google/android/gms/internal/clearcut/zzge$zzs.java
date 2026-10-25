package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzge$zzs extends zzcg<zzge$zzs, zza> implements zzdq {
    private static final zzge$zzs zzbfc = null;
    private static volatile zzdz<zzge$zzs> zzbg;
    private int zzbb;
    private int zzbfa;
    private int zzbfb;

    public static final class zza extends zzcg.zza<zzge$zzs, zza> implements zzdq {
        private zza() {
            super(zzge$zzs.zzfl());
        }

        public /* synthetic */ zza(zzgf r1) {
            this();
        }
    }

    public enum zzb extends Enum<zzb> implements zzcj {
        private static final zzb zzbfd = null;
        private static final zzb zzbfe = null;
        private static final zzb zzbff = null;
        private static final zzb zzbfg = null;
        private static final zzb zzbfh = null;
        private static final zzb zzbfi = null;
        private static final zzb zzbfj = null;
        private static final zzb zzbfk = null;
        private static final zzb zzbfl = null;
        private static final zzb zzbfm = null;
        private static final zzb zzbfn = null;
        private static final zzb zzbfo = null;
        private static final zzb zzbfp = null;
        private static final zzb zzbfq = null;
        private static final zzb zzbfr = null;
        private static final zzb zzbfs = null;
        private static final zzb zzbft = null;
        private static final zzb zzbfu = null;
        private static final zzb zzbfv = null;
        private static final zzb zzbfw = null;
        private static final zzb zzbfx = null;
        private static final /* synthetic */ zzb[] zzbfy = null;
        private static final zzck<zzb> zzbq = null;
        private final int value;

        static {
            zzb r1 = new zzb("UNKNOWN_MOBILE_SUBTYPE", 0, 0);
            zzbfd = r1;
            zzb r2 = new zzb("GPRS", 1, 1);
            zzbfe = r2;
            zzb r3 = new zzb("EDGE", 2, 2);
            zzbff = r3;
            zzb r4 = new zzb("UMTS", 3, 3);
            zzbfg = r4;
            zzb r5 = new zzb("CDMA", 4, 4);
            zzbfh = r5;
            zzb r6 = new zzb("EVDO_0", 5, 5);
            zzbfi = r6;
            zzb r7 = new zzb("EVDO_A", 6, 6);
            zzbfj = r7;
            zzb r8 = new zzb("RTT", 7, 7);
            zzbfk = r8;
            zzb r9 = new zzb("HSDPA", 8, 8);
            zzbfl = r9;
            zzb r10 = new zzb("HSUPA", 9, 9);
            zzbfm = r10;
            zzb r11 = new zzb("HSPA", 10, 10);
            zzbfn = r11;
            zzb r12 = new zzb("IDEN", 11, 11);
            zzbfo = r12;
            zzb r13 = new zzb("EVDO_B", 12, 12);
            zzbfp = r13;
            zzb r14 = new zzb("LTE", 13, 13);
            zzbfq = r14;
            zzb r15 = new zzb("EHRPD", 14, 14);
            zzbfr = r15;
            zzb r0 = new zzb("HSPAP", 15, 15);
            zzbfs = r0;
            zzb r16 = new zzb("GSM", 16, 16);
            zzbft = r16;
            zzb r02 = new zzb("TD_SCDMA", 17, 17);
            zzbfu = r02;
            zzb r17 = new zzb("IWLAN", 18, 18);
            zzbfv = r17;
            zzb r03 = new zzb("LTE_CA", 19, 19);
            zzbfw = r03;
            zzb r18 = new zzb("COMBINED", 20, 100);
            zzbfx = r18;
            zzbfy = new zzb[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r02, r17, r03, r18};
            zzbq = new zzgo();
        }

        zzb(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static zzb[] values() {
            return (zzb[]) zzbfy.clone();
        }

        public static zzb zzaz(int r1) {
            if (r1 == 100) goto L48;
            switch(r1) {
                case 0: goto L46;
                case 1: goto L44;
                case 2: goto L42;
                case 3: goto L40;
                case 4: goto L38;
                case 5: goto L36;
                case 6: goto L34;
                case 7: goto L32;
                case 8: goto L30;
                case 9: goto L28;
                case 10: goto L26;
                case 11: goto L24;
                case 12: goto L22;
                case 13: goto L20;
                case 14: goto L18;
                case 15: goto L16;
                case 16: goto L14;
                case 17: goto L12;
                case 18: goto L10;
                case 19: goto L8;
                default: goto L5;
            };
        L5:
            return null;
        L8:
            return zzbfw;
        L10:
            return zzbfv;
        L12:
            return zzbfu;
        L14:
            return zzbft;
        L16:
            return zzbfs;
        L18:
            return zzbfr;
        L20:
            return zzbfq;
        L22:
            return zzbfp;
        L24:
            return zzbfo;
        L26:
            return zzbfn;
        L28:
            return zzbfm;
        L30:
            return zzbfl;
        L32:
            return zzbfk;
        L34:
            return zzbfj;
        L36:
            return zzbfi;
        L38:
            return zzbfh;
        L40:
            return zzbfg;
        L42:
            return zzbff;
        L44:
            return zzbfe;
        L46:
            return zzbfd;
        L48:
            return zzbfx;
        }

        public static zzck<zzb> zzd() {
            return zzbq;
        }
    }

    public enum zzc extends Enum<zzc> implements zzcj {
        private static final zzc zzbfz = null;
        private static final zzc zzbga = null;
        private static final zzc zzbgb = null;
        private static final zzc zzbgc = null;
        private static final zzc zzbgd = null;
        private static final zzc zzbge = null;
        private static final zzc zzbgf = null;
        private static final zzc zzbgg = null;
        private static final zzc zzbgh = null;
        private static final zzc zzbgi = null;
        private static final zzc zzbgj = null;
        private static final zzc zzbgk = null;
        private static final zzc zzbgl = null;
        private static final zzc zzbgm = null;
        private static final zzc zzbgn = null;
        private static final zzc zzbgo = null;
        private static final zzc zzbgp = null;
        private static final zzc zzbgq = null;
        private static final zzc zzbgr = null;
        private static final /* synthetic */ zzc[] zzbgs = null;
        private static final zzck<zzc> zzbq = null;
        private final int value;

        static {
            zzc r1 = new zzc("NONE", 0, -1);
            zzbfz = r1;
            zzc r2 = new zzc("MOBILE", 1, 0);
            zzbga = r2;
            zzc r3 = new zzc("WIFI", 2, 1);
            zzbgb = r3;
            zzc r4 = new zzc("MOBILE_MMS", 3, 2);
            zzbgc = r4;
            zzc r5 = new zzc("MOBILE_SUPL", 4, 3);
            zzbgd = r5;
            zzc r6 = new zzc("MOBILE_DUN", 5, 4);
            zzbge = r6;
            zzc r7 = new zzc("MOBILE_HIPRI", 6, 5);
            zzbgf = r7;
            zzc r8 = new zzc("WIMAX", 7, 6);
            zzbgg = r8;
            zzc r9 = new zzc("BLUETOOTH", 8, 7);
            zzbgh = r9;
            zzc r10 = new zzc("DUMMY", 9, 8);
            zzbgi = r10;
            zzc r11 = new zzc("ETHERNET", 10, 9);
            zzbgj = r11;
            zzc r12 = new zzc("MOBILE_FOTA", 11, 10);
            zzbgk = r12;
            zzc r13 = new zzc("MOBILE_IMS", 12, 11);
            zzbgl = r13;
            zzc r14 = new zzc("MOBILE_CBS", 13, 12);
            zzbgm = r14;
            zzc r15 = new zzc("WIFI_P2P", 14, 13);
            zzbgn = r15;
            zzc r0 = new zzc("MOBILE_IA", 15, 14);
            zzbgo = r0;
            zzc r16 = new zzc("MOBILE_EMERGENCY", 16, 15);
            zzbgp = r16;
            zzc r22 = new zzc("PROXY", 17, 16);
            zzbgq = r22;
            zzc r02 = new zzc("VPN", 18, 17);
            zzbgr = r02;
            zzbgs = new zzc[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r22, r02};
            zzbq = new zzgp();
        }

        zzc(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static zzc[] values() {
            return (zzc[]) zzbgs.clone();
        }

        public static zzc zzba(int r0) {
            switch(r0) {
                case -1: goto L42;
                case 0: goto L40;
                case 1: goto L38;
                case 2: goto L36;
                case 3: goto L34;
                case 4: goto L32;
                case 5: goto L30;
                case 6: goto L28;
                case 7: goto L26;
                case 8: goto L24;
                case 9: goto L22;
                case 10: goto L20;
                case 11: goto L18;
                case 12: goto L16;
                case 13: goto L14;
                case 14: goto L12;
                case 15: goto L10;
                case 16: goto L8;
                case 17: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return zzbgr;
        L8:
            return zzbgq;
        L10:
            return zzbgp;
        L12:
            return zzbgo;
        L14:
            return zzbgn;
        L16:
            return zzbgm;
        L18:
            return zzbgl;
        L20:
            return zzbgk;
        L22:
            return zzbgj;
        L24:
            return zzbgi;
        L26:
            return zzbgh;
        L28:
            return zzbgg;
        L30:
            return zzbgf;
        L32:
            return zzbge;
        L34:
            return zzbgd;
        L36:
            return zzbgc;
        L38:
            return zzbgb;
        L40:
            return zzbga;
        L42:
            return zzbfz;
        }

        public static zzck<zzc> zzd() {
            return zzbq;
        }
    }

    static {
        zzge$zzs r0 = new zzge$zzs();
        zzbfc = r0;
        zzcg.zza(zzge$zzs.class, r0);
    }

    private zzge$zzs() {
        this.zzbfa = -1;
    }

    public static /* synthetic */ zzge$zzs zzfl() {
        return zzbfc;
    }

    /* JADX WARN: Type inference failed for: r1v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzge$zzs>] */
    @Override // com.google.android.gms.internal.clearcut.zzcg
    public final Object zza(int r2, Object r3, Object r4) {
        switch(zzgf.zzba[r2 - 1]) {
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
        zzdz<zzge$zzs> r1 = zzbg;
        if (r1 == null) goto L11;
        return r1;
    L11:
        monitor-enter(zzge$zzs.class);
        zzdz<zzge$zzs> r12 = zzbg;     // Catch: Throwable -> L15
        zzdz<zzge$zzs> r13 = r12;
        if (r12 != null) goto L17;
        ?? r14 = new zzcg.zzb(zzbfc);     // Catch: Throwable -> L15
        zzbg = r14;     // Catch: Throwable -> L15
        r13 = r14;
    L17:
        monitor-exit(zzge$zzs.class);     // Catch: Throwable -> L15
        return r13;
    L15:
        th = move-exception;
        throw th;
    L24:
        Object[] r15 = {"zzbb", "zzbfa", zzc.zzd(), "zzbfb", zzb.zzd()};
        return zzcg.zza(zzbfc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001", r15);
    L7:
        return (byte) 1;
    L23:
        return zzbfc;
    L27:
        return new zza(null);
    L29:
        return new zzge$zzs();
    }
}
