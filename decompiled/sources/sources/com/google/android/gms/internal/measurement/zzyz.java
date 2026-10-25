package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public enum zzyz extends Enum {
    public static final zzyz zza = null;
    public static final zzyz zzb = null;
    public static final zzyz zzc = null;
    public static final zzyz zzd = null;
    public static final zzyz zze = null;
    public static final zzyz zzf = null;
    public static final zzyz zzg = null;
    public static final zzyz zzh = null;
    public static final zzyz zzi = null;
    public static final zzyz zzj = null;
    private static final zzyz[] zzk = null;
    private static final /* synthetic */ zzyz[] zzp = null;
    private final char zzl;
    private final zzzb zzm;
    private final int zzn;
    private final String zzo;

    static {
        zzyz r0 = new zzyz("STRING", 0, 's', zzzb.zza, "-#", true);
        zza = r0;
        zzyz r1 = new zzyz("BOOLEAN", 1, 'b', zzzb.zzb, "-", true);
        zzb = r1;
        zzyz r2 = new zzyz("CHAR", 2, 'c', zzzb.zzc, "-", true);
        zzc = r2;
        zzzb r8 = zzzb.zzd;
        zzyz r3 = new zzyz("DECIMAL", 3, 'd', r8, "-0+ ,(", false);
        zzd = r3;
        zzyz r4 = new zzyz("OCTAL", 4, 'o', r8, "-#0(", false);
        zze = r4;
        zzyz r42 = new zzyz("HEX", 5, 'x', r8, "-#0(", true);
        zzf = r42;
        zzzb r16 = zzzb.zze;
        zzyz r6 = new zzyz("FLOAT", 6, 'f', r16, "-#0+ ,(", false);
        zzg = r6;
        zzyz r7 = new zzyz("EXPONENT", 7, 'e', r16, "-#0+ (", true);
        zzh = r7;
        zzyz r82 = new zzyz("GENERAL", 8, 'g', r16, "-0+ ,(", true);
        zzi = r82;
        zzyz r9 = new zzyz("EXPONENT_HEX", 9, 'a', r16, "-#0+ ", true);
        zzj = r9;
        zzp = new zzyz[]{r0, r1, r2, r3, r4, r42, r6, r7, r82, r9};
        zzk = new zzyz[26];
        zzyz[] r02 = values();
        int r12 = r02.length;
        int r22 = 0;
    L3:
        if (r22 >= r12) goto L5;
        zzyz r32 = r02[r22];
        int r43 = zzf(r32.zzl);
        zzk[r43] = r32;
        r22 = r22 + 1;
        goto L3
    }

    zzyz(String r1, int r2, char r3, zzzb r4, String r5, boolean r6) {
        this.zzl = r3;
        this.zzm = r4;
        this.zzn = zzza.zzc(r5, r6);
        StringBuilder r22 = new StringBuilder(String.valueOf(r3).length() + 1);
        r22.append("%");
        r22.append(r3);
        this.zzo = r22.toString();
    }

    public static zzyz[] values() {
        return (zzyz[]) zzp.clone();
    }

    public static zzyz zza(char r2) {
        zzyz r0 = zzk[zzf(r2)];
        if ((r2 & ' ') != 0) goto L10;
        if (r0 != null) goto L6;
        return null;
    L6:
        if ((r0.zzn & 128) == 0) goto L11;
        return r0;
    L11:
        return null;
    L10:
        return r0;
    }

    private static int zzf(char r0) {
        return (r0 | ' ') - 97;
    }

    public final char zzb() {
        return this.zzl;
    }

    public final zzzb zzc() {
        return this.zzm;
    }

    public final int zzd() {
        return this.zzn;
    }

    public final String zze() {
        return this.zzo;
    }
}
