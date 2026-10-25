package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzaeh;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* loaded from: classes5.dex */
public final class zzht extends zzos implements zzak {
    final Map zza;
    final Map zzb;
    final Map zzc;
    final Map zzd;
    final LruCache zze;
    final com.google.android.gms.internal.measurement.zzr zzf;
    private final Map zzh;
    private final Map zzi;
    private final Map zzj;
    private final Map zzk;
    private final Map zzl;
    private final Map zzm;

    public zzht(zzpg r2) {
        super(r2);
        this.zzh = new ArrayMap();
        this.zza = new ArrayMap();
        this.zzb = new ArrayMap();
        this.zzc = new ArrayMap();
        this.zzd = new ArrayMap();
        this.zzi = new ArrayMap();
        this.zzk = new ArrayMap();
        this.zzl = new ArrayMap();
        this.zzm = new ArrayMap();
        this.zzj = new ArrayMap();
        this.zze = new zzhm(this, 20);
        this.zzf = new zzhn(this);
    }

    private final void zzF(String r6) {
        zzay();
        zzg();
        Preconditions.checkNotEmpty(r6);
        Map r0 = this.zzi;
        if (r0.get(r6) != null) goto L10;
        zzaq r1 = this.zzg.zzj().zzy(r6);
        if (r1 != null) goto L8;
        this.zzh.put(r6, null);
        this.zzb.put(r6, null);
        this.zza.put(r6, null);
        this.zzc.put(r6, null);
        this.zzd.put(r6, null);
        r0.put(r6, null);
        this.zzk.put(r6, null);
        this.zzl.put(r6, null);
        this.zzm.put(r6, null);
        this.zzj.put(r6, null);
        return;
    L8:
        com.google.android.gms.internal.measurement.zzgk r2 = (com.google.android.gms.internal.measurement.zzgk) zzI(r6, r1.zza).zzco();
        zzG(r6, r2);
        this.zzh.put(r6, zzJ((com.google.android.gms.internal.measurement.zzgl) r2.zzbd()));
        r0.put(r6, (com.google.android.gms.internal.measurement.zzgl) r2.zzbd());
        zzH(r6, (com.google.android.gms.internal.measurement.zzgl) r2.zzbd());
        this.zzk.put(r6, r2.zzh());
        this.zzl.put(r6, r1.zzb);
        this.zzm.put(r6, r1.zzc);
        return;
    }

    private final void zzG(String r14, com.google.android.gms.internal.measurement.zzgk r15) {
        HashSet r0 = new HashSet();
        ArrayList r1 = new ArrayList();
        ArrayMap r2 = new ArrayMap();
        ArrayMap r3 = new ArrayMap();
        ArrayMap r4 = new ArrayMap();
        Iterator r5 = r15.zzg().iterator();
    L4:
        if (r5.hasNext() == false) goto L6;
        r0.add(((com.google.android.gms.internal.measurement.zzgh) r5.next()).zza());
        goto L4
    L6:
        zzic r52 = this.zzu;
        zzal r6 = r52.zzc();
        zzfx r7 = zzfy.zzaV;
        int r9 = 0;
        if (r6.zzp(null, r7) == false) goto L10;
        r1.addAll(r15.zzi());
    L10:
        if (r9 >= r15.zza()) goto L37;
        com.google.android.gms.internal.measurement.zzgi r62 = (com.google.android.gms.internal.measurement.zzgi) r15.zzb(r9).zzco();
        if (r62.zza().isEmpty() == false) goto L14;
        r52.zzaW().zze().zza("EventConfig contained null event name");
    L36:
        r9 = r9 + 1;
        goto L10
    L14:
        String r10 = r62.zza();
        String r11 = zzjm.zzb(r62.zza());
        if (TextUtils.isEmpty(r11) == true) goto L18;
        r62.zzb(r11);
        r15.zzc(r9, r62);
    L18:
        if (r62.zzc() == false) goto L23;
        if (r62.zzd() == false) goto L23;
        r2.put(r10, Boolean.TRUE);
    L23:
        if (r62.zze() == false) goto L28;
        if (r62.zzf() == false) goto L28;
        r3.put(r62.zza(), Boolean.TRUE);
    L28:
        if (r62.zzg() == false) goto L36;
        if (r62.zzh() >= 2) goto L32;
    L35:
        r52.zzaW().zze().zzc("Invalid sampling rate. Event name, sample rate", r62.zza(), Integer.valueOf(r62.zzh()));
        goto L36
    L32:
        if (r62.zzh() > 65535) goto L35;
        r4.put(r62.zza(), Integer.valueOf(r62.zzh()));
        goto L36
    L37:
        this.zza.put(r14, r0);
        if (r52.zzc().zzp(null, r7) == false) goto L40;
        this.zzd.put(r14, r1);
    L40:
        this.zzb.put(r14, r2);
        this.zzc.put(r14, r3);
        this.zzj.put(r14, r4);
    }

    private final void zzH(String r5, com.google.android.gms.internal.measurement.zzgl r6) {
        if (r6.zzk() == 0) goto L13;
        zzic r0 = this.zzu;
        r0.zzaW().zzk().zzb("EES programs found", Integer.valueOf(r6.zzk()));
        com.google.android.gms.internal.measurement.zzja r62 = (com.google.android.gms.internal.measurement.zzja) r6.zzj().get(0);
        com.google.android.gms.internal.measurement.zzc r1 = new com.google.android.gms.internal.measurement.zzc();     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        r1.zza("internal.remoteConfig", new zzhs(this, r5));     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        r1.zza("internal.appMetadata", new zzhp(this, r5));     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        r1.zza("internal.logger", new zzhq(this));     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        r1.zzf(r62);     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        this.zze.put(r5, r1);     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        r0.zzaW().zzk().zzc("EES program loaded for appId, activities", r5, Integer.valueOf(r62.zzb().zzb()));     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        Iterator r63 = r62.zzb().zza().iterator();     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
    L6:
        if (r63.hasNext() == false) goto L10;
        com.google.android.gms.internal.measurement.zziy r12 = (com.google.android.gms.internal.measurement.zziy) r63.next();     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        r0.zzaW().zzk().zzb("EES program activity", r12.zza());     // Catch: com.google.android.gms.internal.measurement.zzd -> L11
        goto L6
    L10:
        return;
    L11:
        this.zzu.zzaW().zzb().zzb("Failed to load EES program. appId", r5);
        return;
    L13:
        this.zze.remove(r5);
    }

    private final com.google.android.gms.internal.measurement.zzgl zzI(String r8, byte[] r9) {
        if (r9 == null) goto L5;
        com.google.android.gms.internal.measurement.zzgl r92 = (com.google.android.gms.internal.measurement.zzgl) ((com.google.android.gms.internal.measurement.zzgk) zzpk.zzw(com.google.android.gms.internal.measurement.zzgl.zzs(), r9)).zzbd();     // Catch: RuntimeException -> L9 zzaeh -> L11
        zzgs r1 = this.zzu.zzaW().zzk();     // Catch: RuntimeException -> L9 zzaeh -> L11
        String r4 = null;
        if (r92.zza() == false) goto L13;
        Long r3 = Long.valueOf(r92.zzb());     // Catch: RuntimeException -> L9 zzaeh -> L11
    L15:
        if (r92.zzc() == false) goto L17;
        r4 = r92.zzd();     // Catch: RuntimeException -> L9 zzaeh -> L11
    L17:
        r1.zzc("Parsed config. version, gmp_app_id", r3, r4);     // Catch: RuntimeException -> L9 zzaeh -> L11
        return r92;
    L13:
        r3 = null;
    L11:
        e = move-exception;
        this.zzu.zzaW().zze().zzc("Unable to merge remote config. appId", zzgu.zzl(r8), e);
        return com.google.android.gms.internal.measurement.zzgl.zzt();
    L9:
        e = move-exception;
        this.zzu.zzaW().zze().zzc("Unable to merge remote config. appId", zzgu.zzl(r8), e);
        return com.google.android.gms.internal.measurement.zzgl.zzt();
    L5:
        return com.google.android.gms.internal.measurement.zzgl.zzt();
    }

    private static final Map zzJ(com.google.android.gms.internal.measurement.zzgl r3) {
        ArrayMap r0 = new ArrayMap();
        if (r3 == null) goto L8;
        Iterator r32 = r3.zze().iterator();
    L6:
        if (r32.hasNext() == false) goto L8;
        com.google.android.gms.internal.measurement.zzgt r1 = (com.google.android.gms.internal.measurement.zzgt) r32.next();
        r0.put(r1.zza(), r1.zzb());
    L8:
        return r0;
    }

    private static final zzjk zzK(int r1) {
        int r12 = r1 - 1;
        if (r12 == 1) goto L19;
        if (r12 == 2) goto L17;
        if (r12 == 3) goto L15;
        if (r12 == 4) goto L13;
        return null;
    L13:
        return zzjk.zzd;
    L15:
        return zzjk.zzc;
    L17:
        return zzjk.zzb;
    L19:
        return zzjk.zza;
    }

    public final SortedSet zzA(String r2) {
        zzg();
        zzF(r2);
        TreeSet r0 = new TreeSet();
        com.google.android.gms.internal.measurement.zzgf r1 = zzy(r2);
        if (r1 == null) goto L9;
        Iterator r12 = r1.zzc().iterator();
    L7:
        if (r12.hasNext() == false) goto L9;
        r0.add(((com.google.android.gms.internal.measurement.zzgc) r12.next()).zza());
    L9:
        return r0;
    }

    public final zzji zzB(String r2, zzjk r3) {
        zzg();
        zzF(r2);
        com.google.android.gms.internal.measurement.zzgf r1 = zzy(r2);
        if (r1 == null) goto L5;
        Iterator r12 = r1.zzf().iterator();
    L8:
        if (r12.hasNext() == false) goto L22;
        com.google.android.gms.internal.measurement.zzfu r22 = (com.google.android.gms.internal.measurement.zzfu) r12.next();
        if (zzK(r22.zzb()) != r3) goto L8;
        int r13 = r22.zzc() - 1;
        if (r13 == 1) goto L20;
        if (r13 == 2) goto L18;
        return zzji.zza;
    L18:
        return zzji.zzc;
    L20:
        return zzji.zzd;
    L22:
        return zzji.zza;
    L5:
        return zzji.zza;
    }

    public final boolean zzC(String r4) {
        zzg();
        zzF(r4);
        com.google.android.gms.internal.measurement.zzgf r3 = zzy(r4);
        if (r3 != null) goto L5;
        return false;
    L5:
        Iterator r32 = r3.zza().iterator();
    L7:
        if (r32.hasNext() == false) goto L14;
        com.google.android.gms.internal.measurement.zzfu r0 = (com.google.android.gms.internal.measurement.zzfu) r32.next();
        if (r0.zzb() != 3) goto L7;
        if (r0.zzd() != 3) goto L7;
        return true;
    L14:
        return false;
    }

    public final /* synthetic */ com.google.android.gms.internal.measurement.zzc zzD(String r4) {
        zzay();
        Preconditions.checkNotEmpty(r4);
        zzaq r0 = this.zzg.zzj().zzy(r4);
        if (r0 != null) goto L6;
        return null;
    L6:
        this.zzu.zzaW().zzk().zzb("Populate EES config from database on cache miss. appId", r4);
        zzH(r4, zzI(r4, r0.zza));
        return (com.google.android.gms.internal.measurement.zzc) this.zze.snapshot().get(r4);
    }

    public final /* synthetic */ Map zzE() {
        return this.zzh;
    }

    @Override // com.google.android.gms.measurement.internal.zzak
    public final String zza(String r1, String r2) {
        zzg();
        zzF(r1);
        Map r0 = (Map) this.zzh.get(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return (String) r0.get(r2);
    }

    public final com.google.android.gms.internal.measurement.zzgl zzb(String r1) {
        zzay();
        zzg();
        Preconditions.checkNotEmpty(r1);
        zzF(r1);
        return (com.google.android.gms.internal.measurement.zzgl) this.zzi.get(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    public final boolean zzbc() {
        return false;
    }

    public final String zzc(String r1) {
        zzg();
        zzF(r1);
        return (String) this.zzk.get(r1);
    }

    public final String zzd(String r1) {
        zzg();
        return (String) this.zzl.get(r1);
    }

    public final String zze(String r1) {
        zzg();
        return (String) this.zzm.get(r1);
    }

    public final void zzf(String r2) {
        zzg();
        this.zzl.put(r2, null);
    }

    public final void zzh(String r1) {
        zzg();
        this.zzi.remove(r1);
    }

    public final boolean zzi(String r6, byte[] r7, String r8, String r9) {
        zzay();
        zzg();
        Preconditions.checkNotEmpty(r6);
        com.google.android.gms.internal.measurement.zzgk r0 = (com.google.android.gms.internal.measurement.zzgk) zzI(r6, r7).zzco();
        zzG(r6, r0);
        zzH(r6, (com.google.android.gms.internal.measurement.zzgl) r0.zzbd());
        this.zzi.put(r6, (com.google.android.gms.internal.measurement.zzgl) r0.zzbd());
        this.zzk.put(r6, r0.zzh());
        this.zzl.put(r6, r8);
        this.zzm.put(r6, r9);
        this.zzh.put(r6, zzJ((com.google.android.gms.internal.measurement.zzgl) r0.zzbd()));
        this.zzg.zzj().zzag(r6, new ArrayList(r0.zzd()));
        r0.zze();     // Catch: RuntimeException -> L5
        r7 = ((com.google.android.gms.internal.measurement.zzgl) r0.zzbd()).zzcd();     // Catch: RuntimeException -> L5
    L7:
        zzaw r1 = this.zzg.zzj();
        Preconditions.checkNotEmpty(r6);
        r1.zzg();
        r1.zzay();
        ContentValues r2 = new ContentValues();
        r2.put("remote_config", r7);
        r2.put("config_last_modified_time", r8);
        r2.put("e_tag", r9);
    L12:
        e = move-exception;
        r1.zzu.zzaW().zzb().zzc("Error storing remote config. appId", zzgu.zzl(r6), e);
    L14:
        r0.zzf();
        this.zzi.put(r6, (com.google.android.gms.internal.measurement.zzgl) r0.zzbd());
        return true;
    L9:
        if (r1.zze().update("apps", r2, "app_id = ?", new String[]{r6}) != 0) goto L14;
        r1.zzu.zzaW().zzb().zzb("Failed to update remote config (got 0). appId", zzgu.zzl(r6));     // Catch: SQLiteException -> L12
    L5:
        e = move-exception;
        this.zzu.zzaW().zze().zzc("Unable to serialize reduced-size config. Storing full config instead. appId", zzgu.zzl(r6), e);
        goto L7
    }

    public final boolean zzj(String r3, String r4) {
        zzg();
        zzF(r3);
        if (zzo(r3) == false) goto L8;
        if (zzpp.zzac(r4) == false) goto L8;
        return true;
    L8:
        if (zzp(r3) == true) goto L10;
    L12:
        Map r2 = (Map) this.zzb.get(r3);
        if (r2 == null) goto L19;
        Boolean r22 = (Boolean) r2.get(r4);
        if (r22 != null) goto L18;
        return false;
    L18:
        return r22.booleanValue();
    L19:
        return false;
    L10:
        if (zzpp.zzh(r4) == false) goto L12;
        return true;
    }

    public final boolean zzk(String r3, String r4) {
        zzg();
        zzF(r3);
        if ("ecommerce_purchase".equals(r4) == false) goto L6;
        return true;
    L6:
        if ("purchase".equals(r4) == false) goto L8;
    L18:
        return true;
    L8:
        if ("refund".equals(r4) == true) goto L18;
        Map r2 = (Map) this.zzc.get(r3);
        if (r2 == null) goto L17;
        Boolean r22 = (Boolean) r2.get(r4);
        if (r22 != null) goto L16;
        return false;
    L16:
        return r22.booleanValue();
    L17:
        return false;
    }

    public final Set zzl(String r1) {
        zzg();
        zzF(r1);
        return (Set) this.zza.get(r1);
    }

    public final List zzm(String r1) {
        zzg();
        zzF(r1);
        return (List) this.zzd.get(r1);
    }

    public final int zzn(String r1, String r2) {
        zzg();
        zzF(r1);
        Map r0 = (Map) this.zzj.get(r1);
        if (r0 == null) goto L9;
        Integer r02 = (Integer) r0.get(r2);
        if (r02 != null) goto L8;
        return 1;
    L8:
        return r02.intValue();
    L9:
        return 1;
    }

    public final boolean zzo(String r3) {
        return "1".equals(zza(r3, "measurement.upload.blacklist_internal"));
    }

    public final boolean zzp(String r3) {
        return "1".equals(zza(r3, "measurement.upload.blacklist_public"));
    }

    public final boolean zzq(String r4) {
        zzg();
        zzF(r4);
        Map r3 = this.zza;
        if (r3.get(r4) != null) goto L5;
        return false;
    L5:
        if (((Set) r3.get(r4)).contains("device_model") == false) goto L7;
    L9:
        return true;
    L7:
        if (((Set) r3.get(r4)).contains("device_info") == true) goto L9;
        return false;
    }

    public final boolean zzr(String r4) {
        zzg();
        zzF(r4);
        Map r3 = this.zza;
        if (r3.get(r4) != null) goto L5;
        return false;
    L5:
        if (((Set) r3.get(r4)).contains("os_version") == false) goto L7;
    L9:
        return true;
    L7:
        if (((Set) r3.get(r4)).contains("device_info") == true) goto L9;
        return false;
    }

    public final boolean zzs(String r2) {
        zzg();
        zzF(r2);
        Map r1 = this.zza;
        if (r1.get(r2) != null) goto L5;
        return false;
    L5:
        if (((Set) r1.get(r2)).contains("user_id") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean zzt(String r2) {
        zzg();
        zzF(r2);
        Map r1 = this.zza;
        if (r1.get(r2) != null) goto L5;
        return false;
    L5:
        if (((Set) r1.get(r2)).contains("google_signals") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean zzu(String r2) {
        zzg();
        zzF(r2);
        Map r1 = this.zza;
        if (r1.get(r2) != null) goto L5;
        return false;
    L5:
        if (((Set) r1.get(r2)).contains("app_instance_id") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean zzv(String r2) {
        zzg();
        zzF(r2);
        Map r1 = this.zza;
        if (r1.get(r2) != null) goto L5;
        return false;
    L5:
        if (((Set) r1.get(r2)).contains("enhanced_user_id") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean zzw(String r3, zzjk r4) {
        zzg();
        zzF(r3);
        com.google.android.gms.internal.measurement.zzgf r2 = zzy(r3);
        if (r2 != null) goto L5;
        return false;
    L5:
        Iterator r22 = r2.zza().iterator();
    L7:
        if (r22.hasNext() == false) goto L14;
        com.google.android.gms.internal.measurement.zzfu r0 = (com.google.android.gms.internal.measurement.zzfu) r22.next();
        if (r4 != zzK(r0.zzb())) goto L7;
        if (r0.zzc() != 2) goto L14;
        return true;
    L14:
        return false;
    }

    public final zzjk zzx(String r2, zzjk r3) {
        zzg();
        zzF(r2);
        com.google.android.gms.internal.measurement.zzgf r1 = zzy(r2);
        if (r1 == null) goto L12;
        Iterator r12 = r1.zzb().iterator();
    L7:
        if (r12.hasNext() == false) goto L17;
        com.google.android.gms.internal.measurement.zzfw r22 = (com.google.android.gms.internal.measurement.zzfw) r12.next();
        if (r3 != zzK(r22.zzb())) goto L7;
        return zzK(r22.zzc());
    L17:
        return null;
    L12:
        return null;
    }

    public final com.google.android.gms.internal.measurement.zzgf zzy(String r1) {
        zzg();
        zzF(r1);
        com.google.android.gms.internal.measurement.zzgl r0 = zzb(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.zzn() == true) goto L8;
        return null;
    L8:
        return r0.zzo();
    }

    public final boolean zzz(String r2) {
        zzg();
        zzF(r2);
        com.google.android.gms.internal.measurement.zzgf r1 = zzy(r2);
        if (r1 != null) goto L6;
        return true;
    L6:
        if (r1.zzd() == true) goto L8;
    L12:
        return true;
    L8:
        if (r1.zze() == true) goto L12;
        return false;
    }
}
