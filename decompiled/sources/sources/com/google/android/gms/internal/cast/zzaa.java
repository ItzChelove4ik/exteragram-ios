package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.util.DefaultClock;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzaa {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Logger zzd = null;
    private static final String zzf = null;
    private static long zzg;
    CastSession zza;
    public int zzb;
    private final zzhg zze;
    private final List zzh;
    private final List zzi;
    private final List zzj;
    private final Map zzk;
    private final zzj zzl;
    private final String zzm;
    private final long zzn;
    private final long zzo;
    private String zzp;
    private String zzq;
    private zzt zzr;
    private String zzs;
    private String zzt;
    private String zzu;
    private String zzv;
    private String zzw;
    private String zzx;
    private int zzy;

    static {
        zzd = new Logger("SessionFlowSummary");
        zzf = "22.3.1";
        zzg = System.currentTimeMillis();
    }

    private zzaa(zzj r3, String r4) {
        this.zze = zzhj.zza(zzz.zza);
        this.zzh = Collections.synchronizedList(new ArrayList());
        this.zzi = Collections.synchronizedList(new ArrayList());
        this.zzj = Collections.synchronizedList(new ArrayList());
        this.zzk = Collections.synchronizedMap(new HashMap());
        this.zzb = 0;
        this.zzl = r3;
        this.zzm = r4;
        this.zzn = DefaultClock.getInstance().currentTimeMillis();
        long r32 = zzg;
        zzg = 1 + r32;
        this.zzo = r32;
    }

    public static zzaa zza(zzj r1, String r2) {
        return new zzaa(r1, r2);
    }

    public final void zzb(zzcs r3) {
        r3.zza(this.zzn);
        this.zzh.add(r3);
    }

    public final void zzc(zzac r3) {
        r3.zza(this.zzn);
        this.zzi.add(r3);
    }

    public final void zzd(zzcq r3) {
        r3.zza(this.zzn);
        this.zzj.add(r3);
    }

    public final void zze(zzt r3) {
        zzt r0 = this.zzr;
        if (r0 != null) goto L5;
    L7:
        r3.zzb(this.zzn);
        this.zzr = r3;
        return;
    L5:
        if (r0.zza() == false) goto L7;
    }

    public final void zzf() {
        this.zzy++;
    }

    public final void zzg(String r2) {
        String r0 = this.zzp;
        if (r0 != null) goto L7;
        this.zzp = r2;
        return;
    L7:
        if (TextUtils.equals(r2, r0) == true) goto L10;
        zzj(4);
        return;
    }

    public final void zzh(CastSession r3) {
        if (r3 != null) goto L5;
        zzj(2);
        return;
    L5:
        CastDevice r0 = r3.getCastDevice();
        if (r0 != null) goto L9;
        zzj(3);
        return;
    L9:
        this.zza = r3;
        String r1 = this.zzq;
        if (r1 != null) goto L17;
        this.zzq = r0.zza();
        this.zzs = r0.getModelName();
        this.zzb = r0.zzd();
        com.google.android.gms.cast.internal.zzaa r02 = r0.zzb();
        if (r02 == null) goto L14;
        this.zzt = r02.zza();
        this.zzu = r02.zzb();
        this.zzv = r02.zzc();
        this.zzw = r02.zzd();
        this.zzx = r02.zze();
    L14:
        r3.zzm();
        return;
    L17:
        if (TextUtils.equals(r1, r0.zza()) == true) goto L20;
        zzj(5);
        return;
    }

    public final void zzi() {
        CastSession r0 = this.zza;
        if (r0 == null) goto L5;
        r0.zzb(null);
        this.zza = null;
    L5:
        long r02 = this.zzo;
        zzqq r2 = zzqr.zzc();
        r2.zza(r02);
        String r03 = this.zzq;
        if (r03 == null) goto L8;
        r2.zzf(r03);
    L8:
        zzur r04 = zzus.zza();
        if (TextUtils.isEmpty(this.zzs) == true) goto L12;
        r2.zzb(this.zzs);
        r04.zza(this.zzs);
    L12:
        if (TextUtils.isEmpty(this.zzt) == true) goto L15;
        r04.zzb(this.zzt);
    L15:
        if (TextUtils.isEmpty(this.zzu) == true) goto L18;
        r04.zzc(this.zzu);
    L18:
        if (TextUtils.isEmpty(this.zzv) == true) goto L21;
        r04.zzd(this.zzv);
    L21:
        if (TextUtils.isEmpty(this.zzw) == true) goto L24;
        r04.zze(this.zzw);
    L24:
        if (TextUtils.isEmpty(this.zzx) == true) goto L26;
        r04.zzf(this.zzx);
    L26:
        r04.zzg(zzco.zza(this.zzb));
        r2.zzn((zzus) r04.zzu());
        zzqb r05 = zzqc.zza();
        r05.zzb(zzf);
        r05.zza(this.zzm);
        r2.zzl((zzqc) r05.zzu());
        zzhg r06 = this.zze;
        zzqy r1 = zzqz.zza();
        String r07 = (String) r06.zza();
        if (r07 == null) goto L29;
        zzro r3 = zzrp.zza();
        r3.zza(r07);
        r1.zza((zzrp) r3.zzu());
    L29:
        String r08 = this.zzp;
        if (r08 != null) goto L69;
    L36:
        List r09 = this.zzh;
        if (r09.isEmpty() == true) goto L43;
        ArrayList r32 = new ArrayList();
        Iterator r010 = r09.iterator();
    L40:
        if (r010.hasNext() == false) goto L42;
        r32.add(((zzcs) r010.next()).zzb());
        goto L40
    L42:
        r1.zzc(r32);
    L43:
        List r011 = this.zzi;
        if (r011.isEmpty() == true) goto L50;
        ArrayList r33 = new ArrayList();
        Iterator r012 = r011.iterator();
    L47:
        if (r012.hasNext() == false) goto L49;
        r33.add(((zzac) r012.next()).zzb());
        goto L47
    L49:
        r1.zze(r33);
    L50:
        List r013 = this.zzj;
        if (r013.isEmpty() == true) goto L58;
        ArrayList r34 = new ArrayList();
        Iterator r014 = r013.iterator();
    L54:
        if (r014.hasNext() == false) goto L56;
        r34.add(((zzcq) r014.next()).zzb());
        goto L54
    L56:
        r1.zzd(r34);
    L58:
        if (this.zzr == null) goto L60;
        ArrayList r015 = new ArrayList();
        r015.add(this.zzr.zzc());
        r1.zzg(r015);
    L60:
        Map r016 = this.zzk;
        if (r016.isEmpty() == true) goto L67;
        ArrayList r35 = new ArrayList();
        Iterator r017 = r016.values().iterator();
    L64:
        if (r017.hasNext() == false) goto L66;
        r35.add(((zzae) r017.next()).zza());
        goto L64
    L66:
        r1.zzf(r35);
    L67:
        r1.zzh(this.zzy);
        r2.zzk((zzqz) r1.zzu());
        zzqr r018 = (zzqr) r2.zzu();
        this.zzl.zzd(r018, 233);
        return;
    L69:
        String r36 = r08.replace("-", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: NumberFormatException -> L33
        long r37 = new BigInteger(r36.substring(0, Math.min(16, r36.length())), 16).longValue();     // Catch: NumberFormatException -> L33
    L35:
        r1.zzb(r37);
    L33:
        e = move-exception;
        zzd.w(e, "receiverSessionId %s is not valid for hash", new Object[]{r08});
        r37 = 0;
        goto L35
    }

    public final void zzj(int r5) {
        Map r0 = this.zzk;
        Integer r1 = Integer.valueOf(r5 - 1);
        zzae r2 = (zzae) r0.get(r1);
        if (r2 != null) goto L6;
        zzae r52 = new zzae(new zzad(r5));
        r52.zzb(this.zzn);
        r0.put(r1, r52);
        return;
    L6:
        r2.zzc();
    }
}
