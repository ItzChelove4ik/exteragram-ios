package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class zzha extends zzfu<zzha> implements Cloneable {
    private String tag;
    public long zzbjf;
    public long zzbjg;
    private long zzbjh;
    public int zzbji;
    private String zzbjj;
    private int zzbjk;
    private boolean zzbjl;
    private zzhb[] zzbjm;
    private byte[] zzbjn;
    private zzge$zzd zzbjo;
    public byte[] zzbjp;
    private String zzbjq;
    private String zzbjr;
    private String zzbjt;
    public long zzbju;
    public byte[] zzbjw;
    private String zzbjx;
    private int zzbjy;
    private int[] zzbjz;
    private long zzbka;
    private zzge$zzs zzbkb;
    public boolean zzbkc;

    public zzha() {
        this.zzbjf = 0;
        this.zzbjg = 0;
        this.zzbjh = 0;
        this.tag = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzbji = 0;
        this.zzbjj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzbjk = 0;
        this.zzbjl = false;
        this.zzbjm = zzhb.zzge();
        byte[] r4 = zzgb.zzse;
        this.zzbjn = r4;
        this.zzbjo = null;
        this.zzbjp = r4;
        this.zzbjq = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzbjr = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzbjt = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzbju = 180000;
        this.zzbjw = r4;
        this.zzbjx = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzbjy = 0;
        this.zzbjz = zzgb.zzrx;
        this.zzbka = 0;
        this.zzbkb = null;
        this.zzbkc = false;
        this.zzrs = -1;
    }

    private final zzha zzgd() {
        zzha r0 = (zzha) super.zzeo();     // Catch: CloneNotSupportedException -> L23
        zzhb[] r1 = this.zzbjm;
        if (r1 != null) goto L6;
    L11:
        zzge$zzd r12 = this.zzbjo;
        if (r12 == null) goto L14;
        r0.zzbjo = r12;
    L14:
        int[] r13 = this.zzbjz;
        if (r13 != null) goto L17;
    L19:
        zzge$zzs r4 = this.zzbkb;
        if (r4 == null) goto L22;
        r0.zzbkb = r4;
    L22:
        return r0;
    L17:
        if (r13.length <= 0) goto L19;
        r0.zzbjz = (int[]) r13.clone();
        goto L19
    L6:
        if (r1.length <= 0) goto L11;
        r0.zzbjm = new zzhb[r1.length];
        int r14 = 0;
    L8:
        zzhb[] r2 = this.zzbjm;
        if (r14 >= r2.length) goto L11;
        zzhb r22 = r2[r14];
        r14 = r14 + 1;
    L23:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    public final /* synthetic */ Object clone() {
        return zzgd();
    }

    public final boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzha) == true) goto L8;
        return false;
    L8:
        zzha r82 = (zzha) r8;
        if (this.zzbjf == r82.zzbjf) goto L12;
        return false;
    L12:
        if (this.zzbjg == r82.zzbjg) goto L14;
        return false;
    L14:
        String r1 = this.tag;
        String r3 = r82.tag;
        if (r1 != null) goto L19;
        if (r3 == null) goto L22;
        return false;
    L22:
        if (this.zzbji == r82.zzbji) goto L24;
        return false;
    L24:
        String r12 = this.zzbjj;
        String r32 = r82.zzbjj;
        if (r12 != null) goto L29;
        if (r32 == null) goto L32;
        return false;
    L32:
        if (zzfy.equals(this.zzbjm, r82.zzbjm) == true) goto L35;
        return false;
    L35:
        if (Arrays.equals(this.zzbjn, r82.zzbjn) == true) goto L37;
        return false;
    L37:
        zzge$zzd r13 = this.zzbjo;
        zzge$zzd r33 = r82.zzbjo;
        if (r13 != null) goto L42;
        if (r33 == null) goto L45;
        return false;
    L45:
        if (Arrays.equals(this.zzbjp, r82.zzbjp) == true) goto L47;
        return false;
    L47:
        String r14 = this.zzbjq;
        String r34 = r82.zzbjq;
        if (r14 != null) goto L52;
        if (r34 == null) goto L54;
        return false;
    L54:
        String r15 = this.zzbjr;
        String r35 = r82.zzbjr;
        if (r15 != null) goto L59;
        if (r35 == null) goto L61;
        return false;
    L61:
        String r16 = this.zzbjt;
        String r36 = r82.zzbjt;
        if (r16 != null) goto L66;
        if (r36 == null) goto L69;
        return false;
    L69:
        if (this.zzbju == r82.zzbju) goto L72;
        return false;
    L72:
        if (Arrays.equals(this.zzbjw, r82.zzbjw) == true) goto L74;
        return false;
    L74:
        String r17 = this.zzbjx;
        String r37 = r82.zzbjx;
        if (r17 != null) goto L79;
        if (r37 == null) goto L82;
        return false;
    L82:
        if (zzfy.equals(this.zzbjz, r82.zzbjz) == true) goto L84;
        return false;
    L84:
        zzge$zzs r18 = this.zzbkb;
        zzge$zzs r38 = r82.zzbkb;
        if (r18 != null) goto L89;
        if (r38 == null) goto L92;
        return false;
    L92:
        if (this.zzbkc == r82.zzbkc) goto L94;
        return false;
    L94:
        return true;
    L89:
        if (r18.equals(r38) == true) goto L92;
        return false;
    L79:
        if (r17.equals(r37) == true) goto L82;
        return false;
    L66:
        if (r16.equals(r36) == true) goto L69;
        return false;
    L59:
        if (r15.equals(r35) == true) goto L61;
        return false;
    L52:
        if (r14.equals(r34) == true) goto L54;
        return false;
    L42:
        if (r13.equals(r33) == true) goto L45;
        return false;
    L29:
        if (r12.equals(r32) == true) goto L32;
        return false;
    L19:
        if (r1.equals(r3) == true) goto L22;
        return false;
    }

    public final int hashCode() {
        int r0 = (zzha.class.getName().hashCode() + 527) * 31;
        long r1 = this.zzbjf;
        int r02 = (r0 + ((int) (r1 ^ (r1 >>> 32)))) * 31;
        long r12 = this.zzbjg;
        int r03 = (r02 + ((int) (r12 ^ (r12 >>> 32)))) * 961;
        String r13 = this.tag;
        int r2 = 0;
        if (r13 != null) goto L5;
        int r14 = 0;
    L6:
        int r04 = (((r03 + r14) * 31) + this.zzbji) * 31;
        String r15 = this.zzbjj;
        if (r15 != null) goto L9;
        int r16 = 0;
    L10:
        int r05 = (r04 + r16) * 961;
        int r17 = 1237;
        int r06 = ((((r05 + 1237) * 31) + zzfy.hashCode(this.zzbjm)) * 31) + Arrays.hashCode(this.zzbjn);
        zzge$zzd r4 = this.zzbjo;
        int r07 = r06 * 31;
        if (r4 != null) goto L13;
        int r42 = 0;
    L14:
        int r08 = (((r07 + r42) * 31) + Arrays.hashCode(this.zzbjp)) * 31;
        String r43 = this.zzbjq;
        if (r43 != null) goto L17;
        int r44 = 0;
    L18:
        int r09 = (r08 + r44) * 31;
        String r45 = this.zzbjr;
        if (r45 != null) goto L21;
        int r46 = 0;
    L22:
        int r010 = (r09 + r46) * 961;
        String r47 = this.zzbjt;
        if (r47 != null) goto L25;
        int r48 = 0;
    L26:
        int r011 = (r010 + r48) * 31;
        long r49 = this.zzbju;
        int r012 = (((r011 + ((int) (r49 ^ (r49 >>> 32)))) * 961) + Arrays.hashCode(this.zzbjw)) * 31;
        String r3 = this.zzbjx;
        if (r3 != null) goto L29;
        int r32 = 0;
    L30:
        int r013 = ((r012 + r32) * 961) + zzfy.hashCode(this.zzbjz);
        zzge$zzs r33 = this.zzbkb;
        int r014 = r013 * 961;
        if (r33 == null) goto L34;
        r2 = r33.hashCode();
    L34:
        int r015 = (r014 + r2) * 31;
        if (this.zzbkc == false) goto L38;
        r17 = 1231;
    L38:
        return (r015 + r17) * 31;
    L29:
        r32 = r3.hashCode();
        goto L30
    L25:
        r48 = r47.hashCode();
        goto L26
    L21:
        r46 = r45.hashCode();
        goto L22
    L17:
        r44 = r43.hashCode();
        goto L18
    L13:
        r42 = r4.hashCode();
        goto L14
    L9:
        r16 = r15.hashCode();
        goto L10
    L5:
        r14 = r13.hashCode();
        goto L6
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final void zza(zzfs r11) {
        long r0 = this.zzbjf;
        if (r0 == 0) goto L5;
        r11.zzi(1, r0);
    L5:
        String r02 = this.tag;
        if (r02 != null) goto L8;
    L10:
        zzhb[] r03 = this.zzbjm;
        int r4 = 0;
        if (r03 != null) goto L13;
    L18:
        byte[] r04 = this.zzbjn;
        byte[] r5 = zzgb.zzse;
        if (Arrays.equals(r04, r5) == true) goto L22;
        r11.zza(4, this.zzbjn);
    L22:
        if (Arrays.equals(this.zzbjp, r5) == true) goto L24;
        r11.zza(6, this.zzbjp);
    L24:
        String r05 = this.zzbjq;
        if (r05 != null) goto L27;
    L29:
        zzge$zzd r06 = this.zzbjo;
        if (r06 == null) goto L32;
        r11.zze(9, r06);
    L32:
        int r07 = this.zzbji;
        if (r07 == 0) goto L35;
        r11.zzc(11, r07);
    L35:
        String r08 = this.zzbjr;
        if (r08 != null) goto L38;
    L40:
        String r09 = this.zzbjt;
        if (r09 != null) goto L43;
    L45:
        long r6 = this.zzbju;
        if (r6 == 180000) goto L48;
        r11.zzb(15, 0);
        r11.zzn(zzfs.zzj(r6));
    L48:
        long r62 = this.zzbjg;
        if (r62 == 0) goto L52;
        r11.zzi(17, r62);
    L52:
        if (Arrays.equals(this.zzbjw, r5) == true) goto L54;
        r11.zza(18, this.zzbjw);
    L54:
        int[] r010 = this.zzbjz;
        if (r010 != null) goto L57;
    L61:
        zzge$zzs r011 = this.zzbkb;
        if (r011 == null) goto L64;
        r11.zze(23, r011);
    L64:
        String r012 = this.zzbjx;
        if (r012 != null) goto L67;
    L69:
        boolean r013 = this.zzbkc;
        if (r013 == false) goto L72;
        r11.zzb(25, r013);
    L72:
        String r014 = this.zzbjj;
        if (r014 != null) goto L75;
    L77:
        super.zza(r11);
        return;
    L75:
        if (r014.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L77;
        r11.zza(26, this.zzbjj);
        goto L77
    L67:
        if (r012.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L69;
        r11.zza(24, this.zzbjx);
        goto L69
    L57:
        if (r010.length <= 0) goto L61;
    L58:
        int[] r015 = this.zzbjz;
        if (r4 >= r015.length) goto L61;
        r11.zzc(20, r015[r4]);
        r4 = r4 + 1;
        goto L58
    L43:
        if (r09.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L45;
        r11.zza(14, this.zzbjt);
        goto L45
    L38:
        if (r08.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L40;
        r11.zza(13, this.zzbjr);
        goto L40
    L27:
        if (r05.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L29;
        r11.zza(8, this.zzbjq);
        goto L29
    L13:
        if (r03.length <= 0) goto L18;
        int r016 = 0;
    L15:
        zzhb[] r52 = this.zzbjm;
        if (r016 >= r52.length) goto L18;
        zzhb r53 = r52[r016];
        r016 = r016 + 1;
        goto L15
    L8:
        if (r02.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L10;
        r11.zza(2, this.tag);
        goto L10
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final int zzen() {
        int r0 = super.zzen();
        long r1 = this.zzbjf;
        if (r1 == 0) goto L5;
        r0 = r0 + zzfs.zzd(1, r1);
    L5:
        String r12 = this.tag;
        if (r12 != null) goto L8;
    L10:
        zzhb[] r13 = this.zzbjm;
        int r7 = 0;
        if (r13 != null) goto L13;
    L18:
        byte[] r14 = this.zzbjn;
        byte[] r8 = zzgb.zzse;
        if (Arrays.equals(r14, r8) == true) goto L22;
        r0 = r0 + zzfs.zzb(4, this.zzbjn);
    L22:
        if (Arrays.equals(this.zzbjp, r8) == true) goto L24;
        r0 = r0 + zzfs.zzb(6, this.zzbjp);
    L24:
        String r15 = this.zzbjq;
        if (r15 != null) goto L27;
    L29:
        zzge$zzd r16 = this.zzbjo;
        if (r16 == null) goto L32;
        r0 = r0 + zzbn.zzc(9, r16);
    L32:
        int r17 = this.zzbji;
        if (r17 == 0) goto L35;
        r0 = r0 + (zzfs.zzr(11) + zzfs.zzs(r17));
    L35:
        String r18 = this.zzbjr;
        if (r18 != null) goto L38;
    L40:
        String r19 = this.zzbjt;
        if (r19 != null) goto L43;
    L45:
        long r9 = this.zzbju;
        if (r9 == 180000) goto L48;
        r0 = r0 + (zzfs.zzr(15) + zzfs.zzo(zzfs.zzj(r9)));
    L48:
        long r92 = this.zzbjg;
        if (r92 == 0) goto L52;
        r0 = r0 + zzfs.zzd(17, r92);
    L52:
        if (Arrays.equals(this.zzbjw, r8) == true) goto L54;
        r0 = r0 + zzfs.zzb(18, this.zzbjw);
    L54:
        int[] r110 = this.zzbjz;
        if (r110 != null) goto L57;
    L63:
        zzge$zzs r111 = this.zzbkb;
        if (r111 == null) goto L66;
        r0 = r0 + zzbn.zzc(23, r111);
    L66:
        String r112 = this.zzbjx;
        if (r112 == null) goto L72;
        if (r112.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L72;
        r0 = r0 + zzfs.zzb(24, this.zzbjx);
    L72:
        if (this.zzbkc == false) goto L74;
        r0 = r0 + (zzfs.zzr(25) + 1);
    L74:
        String r113 = this.zzbjj;
        if (r113 != null) goto L77;
        return r0;
    L77:
        if (r113.equals(_UrlKt.FRAGMENT_ENCODE_SET) == false) goto L79;
        return r0;
    L79:
        return r0 + zzfs.zzb(26, this.zzbjj);
    L57:
        if (r110.length <= 0) goto L63;
        int r114 = 0;
    L59:
        int[] r3 = this.zzbjz;
        if (r7 >= r3.length) goto L62;
        r114 = r114 + zzfs.zzs(r3[r7]);
        r7 = r7 + 1;
        goto L59
    L62:
        r0 = (r0 + r114) + (r3.length * 2);
        goto L63
    L43:
        if (r19.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L45;
        r0 = r0 + zzfs.zzb(14, this.zzbjt);
        goto L45
    L38:
        if (r18.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L40;
        r0 = r0 + zzfs.zzb(13, this.zzbjr);
        goto L40
    L27:
        if (r15.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L29;
        r0 = r0 + zzfs.zzb(8, this.zzbjq);
        goto L29
    L13:
        if (r13.length <= 0) goto L18;
        int r115 = 0;
    L15:
        zzhb[] r82 = this.zzbjm;
        if (r115 >= r82.length) goto L18;
        zzhb r83 = r82[r115];
        r115 = r115 + 1;
        goto L15
    L8:
        if (r12.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L10;
        r0 = r0 + zzfs.zzb(2, this.tag);
        goto L10
    }
}
