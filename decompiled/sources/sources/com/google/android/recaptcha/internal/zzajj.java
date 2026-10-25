package com.google.android.recaptcha.internal;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzajj extends zzafv implements zzagy {
    private static final zzajj zzv = null;
    private static volatile zzahe zzw;
    private int zza;
    private int zzd;
    private Object zze;
    private int zzf;
    private int zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private String zzk;
    private long zzl;
    private zzafe zzm;
    private int zzn;
    private zzaja zzo;
    private zzajr zzp;
    private String zzq;
    private zzahx zzr;
    private zzafe zzs;
    private zzagc zzt;
    private int zzu;

    static {
        zzajj r0 = new zzajj();
        zzv = r0;
        zzafv.zzL(zzajj.class, r0);
    }

    private zzajj() {
        this.zzd = 0;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzk = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzq = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzt = zzafv.zzO();
    }

    public static zzajh zzd() {
        return (zzajh) zzv.zzF();
    }

    public static zzajj zze() {
        return zzv;
    }

    public static /* synthetic */ zzajj zzq() {
        return zzv;
    }

    public final zzafe zza() {
        zzafe r0 = this.zzm;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzafe.zzd();
    }

    public final /* synthetic */ void zzaa(int r1) {
        this.zzn = r1 - 2;
    }

    public final boolean zzb() {
        if ((this.zza & 32) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final int zzc() {
        return this.zzu;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r23, Object r24, Object r25) {
        int r0 = r23 - 1;
        if (r0 == 0) goto L38;
        if (r0 == 2) goto L36;
        if (r0 == 3) goto L34;
        if (r0 == 4) goto L32;
        if (r0 == 5) goto L30;
        if (r0 != 6) goto L28;
        zzahe r02 = zzw;
        if (r02 == null) goto L17;
        return r02;
    L17:
        monitor-enter(zzajj.class);
        zzahe r03 = zzw;     // Catch: Throwable -> L21
        if (r03 != null) goto L23;
        r03 = new zzafr(zzv);     // Catch: Throwable -> L21
        zzw = r03;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajj.class);     // Catch: Throwable -> L21
        return r03;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzv;
    L32:
        return new zzajh(null);
    L34:
        return new zzajj();
    L36:
        return zzafv.zzM(zzv, "\u0000\u0011\u0001\u0001\u0001\u0013\u0011\u0000\u0001\u0000\u0001\f\u0002Ȉ\u0003\u0003\u0004\f\u0005ဉ\u0001\u0006ဉ\u0002\u0007Ȉ\bȈ\tȈ\nဉ\u0000\u000bဉ\u0003\rဉ\u0004\u000eȈ\u000f<\u0000\u0011'\u0012င\u0005\u0013\f", new Object[]{"zze", "zzd", "zza", "zzf", "zzi", "zzl", "zzn", "zzo", "zzp", "zzq", "zzj", "zzk", "zzm", "zzr", "zzs", "zzh", zzait.class, "zzt", "zzu", "zzg"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(String r1) {
        Objects.requireNonNull(r1);
        this.zzh = r1;
    }

    public final /* synthetic */ void zzh(String r1) {
        Objects.requireNonNull(r1);
        this.zzi = r1;
    }

    public final /* synthetic */ void zzi(String r1) {
        Objects.requireNonNull(r1);
        this.zzj = r1;
    }

    public final /* synthetic */ void zzj(zzafe r1) {
        Objects.requireNonNull(r1);
        this.zzm = r1;
        this.zza |= 1;
    }

    public final /* synthetic */ void zzk(zzaja r1) {
        Objects.requireNonNull(r1);
        this.zzo = r1;
        this.zza |= 2;
    }

    public final /* synthetic */ void zzl(zzajr r1) {
        Objects.requireNonNull(r1);
        this.zzp = r1;
        this.zza |= 4;
    }

    public final /* synthetic */ void zzn(zzahx r1) {
        Objects.requireNonNull(r1);
        this.zzr = r1;
        this.zza |= 8;
    }

    public final /* synthetic */ void zzo(int r2) {
        zzagc r22 = this.zzt;
        if (r22.zza() == true) goto L5;
        this.zzt = zzafv.zzP(r22);
    L5:
        this.zzt.zzh(0);
    }

    public final /* synthetic */ void zzp(int r2) {
        this.zza |= 32;
        this.zzu = r2;
    }

    public final int zzr() {
        switch(this.zzf) {
            case 0: goto L91;
            case 1: goto L90;
            case 2: goto L89;
            case 3: goto L88;
            case 4: goto L87;
            case 5: goto L86;
            case 6: goto L85;
            case 7: goto L84;
            case 8: goto L83;
            case 9: goto L82;
            case 10: goto L81;
            case 11: goto L80;
            case 12: goto L79;
            case 13: goto L78;
            case 14: goto L77;
            case 15: goto L76;
            case 16: goto L75;
            case 17: goto L74;
            case 18: goto L73;
            case 19: goto L72;
            case 20: goto L71;
            case 21: goto L70;
            case 22: goto L69;
            case 23: goto L68;
            case 24: goto L67;
            case 25: goto L66;
            case 26: goto L65;
            case 27: goto L64;
            case 28: goto L63;
            case 29: goto L62;
            case 30: goto L61;
            case 31: goto L60;
            case 32: goto L59;
            case 33: goto L58;
            case 34: goto L57;
            case 35: goto L56;
            case 36: goto L55;
            case 37: goto L54;
            case 38: goto L53;
            case 39: goto L52;
            case 40: goto L51;
            case 41: goto L50;
            case 42: goto L49;
            case 43: goto L48;
            case 44: goto L47;
            case 45: goto L46;
            case 46: goto L45;
            case 47: goto L44;
            case 48: goto L43;
            case 49: goto L42;
            case 50: goto L41;
            case 51: goto L40;
            case 52: goto L39;
            case 53: goto L38;
            case 54: goto L37;
            case 55: goto L36;
            case 56: goto L35;
            case 57: goto L34;
            case 58: goto L33;
            case 59: goto L32;
            case 60: goto L31;
            case 61: goto L30;
            case 62: goto L29;
            case 63: goto L28;
            case 64: goto L27;
            case 65: goto L26;
            case 66: goto L25;
            case 67: goto L24;
            case 68: goto L23;
            case 69: goto L22;
            case 70: goto L21;
            case 71: goto L20;
            case 72: goto L19;
            case 73: goto L18;
            case 74: goto L17;
            case 75: goto L16;
            case 76: goto L15;
            case 77: goto L14;
            case 78: goto L13;
            case 79: goto L12;
            case 80: goto L11;
            case 81: goto L10;
            case 82: goto L9;
            case 83: goto L8;
            case 84: goto L7;
            case 85: goto L6;
            case 86: goto L5;
            default: goto L4;
        };
    L4:
        int r0 = 0;
    L92:
        if (r0 != 0) goto L95;
        return 1;
    L95:
        return r0;
    L5:
        r0 = 88;
        goto L92
    L6:
        r0 = 87;
        goto L92
    L7:
        r0 = 86;
        goto L92
    L8:
        r0 = 85;
        goto L92
    L9:
        r0 = 84;
        goto L92
    L10:
        r0 = 83;
        goto L92
    L11:
        r0 = 82;
        goto L92
    L12:
        r0 = 81;
        goto L92
    L13:
        r0 = 80;
        goto L92
    L14:
        r0 = 79;
        goto L92
    L15:
        r0 = 78;
        goto L92
    L16:
        r0 = 77;
        goto L92
    L17:
        r0 = 76;
        goto L92
    L18:
        r0 = 75;
        goto L92
    L19:
        r0 = 74;
        goto L92
    L20:
        r0 = 73;
        goto L92
    L21:
        r0 = 72;
        goto L92
    L22:
        r0 = 71;
        goto L92
    L23:
        r0 = 70;
        goto L92
    L24:
        r0 = 69;
        goto L92
    L25:
        r0 = 68;
        goto L92
    L26:
        r0 = 67;
        goto L92
    L27:
        r0 = 66;
        goto L92
    L28:
        r0 = 65;
        goto L92
    L29:
        r0 = 64;
        goto L92
    L30:
        r0 = 63;
        goto L92
    L31:
        r0 = 62;
        goto L92
    L32:
        r0 = 61;
        goto L92
    L33:
        r0 = 60;
        goto L92
    L34:
        r0 = 59;
        goto L92
    L35:
        r0 = 58;
        goto L92
    L36:
        r0 = 57;
        goto L92
    L37:
        r0 = 56;
        goto L92
    L38:
        r0 = 55;
        goto L92
    L39:
        r0 = 54;
        goto L92
    L40:
        r0 = 53;
        goto L92
    L41:
        r0 = 52;
        goto L92
    L42:
        r0 = 51;
        goto L92
    L43:
        r0 = 50;
        goto L92
    L44:
        r0 = 49;
        goto L92
    L45:
        r0 = 48;
        goto L92
    L46:
        r0 = 47;
        goto L92
    L47:
        r0 = 46;
        goto L92
    L48:
        r0 = 45;
        goto L92
    L49:
        r0 = 44;
        goto L92
    L50:
        r0 = 43;
        goto L92
    L51:
        r0 = 42;
        goto L92
    L52:
        r0 = 41;
        goto L92
    L53:
        r0 = 40;
        goto L92
    L54:
        r0 = 39;
        goto L92
    L55:
        r0 = 38;
        goto L92
    L56:
        r0 = 37;
        goto L92
    L57:
        r0 = 36;
        goto L92
    L58:
        r0 = 35;
        goto L92
    L59:
        r0 = 34;
        goto L92
    L60:
        r0 = 33;
        goto L92
    L61:
        r0 = 32;
        goto L92
    L62:
        r0 = 31;
        goto L92
    L63:
        r0 = 30;
        goto L92
    L64:
        r0 = 29;
        goto L92
    L65:
        r0 = 28;
        goto L92
    L66:
        r0 = 27;
        goto L92
    L67:
        r0 = 26;
        goto L92
    L68:
        r0 = 25;
        goto L92
    L69:
        r0 = 24;
        goto L92
    L70:
        r0 = 23;
        goto L92
    L71:
        r0 = 22;
        goto L92
    L72:
        r0 = 21;
        goto L92
    L73:
        r0 = 20;
        goto L92
    L74:
        r0 = 19;
        goto L92
    L75:
        r0 = 18;
        goto L92
    L76:
        r0 = 17;
        goto L92
    L77:
        r0 = 16;
        goto L92
    L78:
        r0 = 15;
        goto L92
    L79:
        r0 = 14;
        goto L92
    L80:
        r0 = 13;
        goto L92
    L81:
        r0 = 12;
        goto L92
    L82:
        r0 = 11;
        goto L92
    L83:
        r0 = 10;
        goto L92
    L84:
        r0 = 9;
        goto L92
    L85:
        r0 = 8;
        goto L92
    L86:
        r0 = 7;
        goto L92
    L87:
        r0 = 6;
        goto L92
    L88:
        r0 = 5;
        goto L92
    L89:
        r0 = 4;
        goto L92
    L90:
        r0 = 3;
        goto L92
    L91:
        r0 = 2;
        goto L92
    }

    public final /* synthetic */ void zzt(int r1) {
        this.zzf = zzaji.zza(r1);
    }

    public final /* synthetic */ void zzu(int r1) {
        this.zzg = r1 - 2;
    }
}
