package com.google.android.gms.internal.vision;

import kotlin.text.Typography;

/* loaded from: classes5.dex */
public enum zzgz extends Enum<zzgz> implements zzje {
    private static final zzgz zza = null;
    private static final zzgz zzb = null;
    private static final zzgz zzc = null;
    private static final zzgz zzd = null;
    private static final zzgz zze = null;
    private static final zzgz zzf = null;
    private static final zzgz zzg = null;
    private static final zzgz zzh = null;
    private static final zzgz zzi = null;
    private static final zzgz zzj = null;
    private static final zzgz zzk = null;
    private static final zzgz zzl = null;
    private static final zzgz zzm = null;
    private static final zzgz zzn = null;
    private static final zzgz zzo = null;
    private static final zzgz zzp = null;
    private static final zzjh<zzgz> zzq = null;
    private static final /* synthetic */ zzgz[] zzs = null;
    private final int zzr;

    static {
        zzgz r1 = new zzgz("UNRECOGNIZED", 0, 0);
        zza = r1;
        zzgz r2 = new zzgz("CODE_128", 1, 1);
        zzb = r2;
        zzgz r3 = new zzgz("CODE_39", 2, 2);
        zzc = r3;
        zzgz r4 = new zzgz("CODE_93", 3, 3);
        zzd = r4;
        zzgz r5 = new zzgz("CODABAR", 4, 4);
        zze = r5;
        zzgz r6 = new zzgz("DATA_MATRIX", 5, 5);
        zzf = r6;
        zzgz r7 = new zzgz("EAN_13", 6, 6);
        zzg = r7;
        zzgz r8 = new zzgz("EAN_8", 7, 7);
        zzh = r8;
        zzgz r9 = new zzgz("ITF", 8, 8);
        zzi = r9;
        zzgz r10 = new zzgz("QR_CODE", 9, 9);
        zzj = r10;
        zzgz r11 = new zzgz("UPC_A", 10, 10);
        zzk = r11;
        zzgz r12 = new zzgz("UPC_E", 11, 11);
        zzl = r12;
        zzgz r13 = new zzgz("PDF417", 12, 12);
        zzm = r13;
        zzgz r14 = new zzgz("AZTEC", 13, 13);
        zzn = r14;
        zzgz r15 = new zzgz("DATABAR", 14, 14);
        zzo = r15;
        zzgz r0 = new zzgz("TEZ_CODE", 15, 16);
        zzp = r0;
        zzs = new zzgz[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0};
        zzq = new zzgy();
    }

    zzgz(String r1, int r2, int r3) {
        this.zzr = r3;
    }

    public static zzgz[] values() {
        return (zzgz[]) zzs.clone();
    }

    public static zzgz zza(int r0) {
        switch(r0) {
            case 0: goto L36;
            case 1: goto L34;
            case 2: goto L32;
            case 3: goto L30;
            case 4: goto L28;
            case 5: goto L26;
            case 6: goto L24;
            case 7: goto L22;
            case 8: goto L20;
            case 9: goto L18;
            case 10: goto L16;
            case 11: goto L14;
            case 12: goto L12;
            case 13: goto L10;
            case 14: goto L8;
            case 15: goto L3;
            case 16: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return zzp;
    L8:
        return zzo;
    L10:
        return zzn;
    L12:
        return zzm;
    L14:
        return zzl;
    L16:
        return zzk;
    L18:
        return zzj;
    L20:
        return zzi;
    L22:
        return zzh;
    L24:
        return zzg;
    L26:
        return zzf;
    L28:
        return zze;
    L30:
        return zzd;
    L32:
        return zzc;
    L34:
        return zzb;
    L36:
        return zza;
    }

    public static zzjg zzb() {
        return zzhb.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzgz.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzr + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.vision.zzje
    public final int zza() {
        return this.zzr;
    }
}
