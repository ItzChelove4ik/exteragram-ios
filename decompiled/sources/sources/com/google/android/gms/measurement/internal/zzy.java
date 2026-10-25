package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzahn;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzy {
    final /* synthetic */ zzad zza;
    private String zzb;
    private boolean zzc;
    private com.google.android.gms.internal.measurement.zzii zzd;
    private BitSet zze;
    private BitSet zzf;
    private Map zzg;
    private Map zzh;

    public /* synthetic */ zzy(zzad r1, String r2, com.google.android.gms.internal.measurement.zzii r3, BitSet r4, BitSet r5, Map r6, Map r7, byte[] r8) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
        this.zzh = new ArrayMap();
        Iterator r12 = r7.keySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        Integer r22 = (Integer) r12.next();
        ArrayList r42 = new ArrayList();
        r42.add((Long) r7.get(r22));
        this.zzh.put(r22, r42);
        goto L4
    L6:
        this.zzc = false;
        this.zzd = r3;
    }

    public /* synthetic */ zzy(zzad r1, String r2, byte[] r3) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
        this.zzc = true;
        this.zze = new BitSet();
        this.zzf = new BitSet();
        this.zzg = new ArrayMap();
        this.zzh = new ArrayMap();
    }

    public final void zza(zzab r10) {
        int r0 = r10.zza();
        if (r10.zzd == null) goto L5;
        this.zzf.set(r0, true);
    L5:
        Boolean r1 = r10.zze;
        if (r1 == null) goto L9;
        this.zze.set(r0, r1.booleanValue());
    L9:
        if (r10.zzf == null) goto L16;
        Map r12 = this.zzg;
        Integer r4 = Integer.valueOf(r0);
        Long r13 = (Long) r12.get(r4);
        long r5 = r10.zzf.longValue() / 1000;
        if (r13 != null) goto L13;
    L14:
        this.zzg.put(r4, Long.valueOf(r5));
        goto L16
    L13:
        if (r5 > r13.longValue()) goto L14;
    L16:
        if (r10.zzg == null) goto L36;
        Map r14 = this.zzh;
        Integer r02 = Integer.valueOf(r0);
        List r15 = (List) r14.get(r02);
        if (r15 != null) goto L21;
        r15 = new ArrayList();
        this.zzh.put(r02, r15);
    L21:
        if (r10.zzb() == false) goto L23;
        r15.clear();
    L23:
        zzahn.zza();
        zzic r03 = this.zza.zzu;
        zzal r42 = r03.zzc();
        String r52 = this.zzb;
        zzfx r6 = zzfy.zzaF;
        if (r42.zzp(r52, r6) == true) goto L26;
    L28:
        zzahn.zza();
        boolean r9 = r03.zzc().zzp(this.zzb, r6);
        Long r102 = r10.zzg;
        if (r9 == false) goto L34;
        Long r92 = Long.valueOf(r102.longValue() / 1000);
        if (r15.contains(r92) == true) goto L37;
        r15.add(r92);
        return;
    L37:
        return;
    L34:
        r15.add(Long.valueOf(r102.longValue() / 1000));
        return;
    L26:
        if (r10.zzc() == false) goto L28;
        r15.clear();
        goto L28
    }

    public final com.google.android.gms.internal.measurement.zzhg zzb(int r7) {
        com.google.android.gms.internal.measurement.zzhf r0 = com.google.android.gms.internal.measurement.zzhg.zzi();
        r0.zza(r7);
        r0.zzd(this.zzc);
        com.google.android.gms.internal.measurement.zzii r72 = this.zzd;
        if (r72 == null) goto L5;
        r0.zzc(r72);
    L5:
        com.google.android.gms.internal.measurement.zzih r73 = com.google.android.gms.internal.measurement.zzii.zzj();
        r73.zzc(zzpk.zzp(this.zze));
        r73.zza(zzpk.zzp(this.zzf));
        Map r1 = this.zzg;
        if (r1 != null) goto L8;
        ArrayList r12 = null;
    L15:
        if (r12 == null) goto L17;
        r73.zze(r12);
    L17:
        Map r13 = this.zzh;
        if (r13 != null) goto L20;
        List r6 = Collections.EMPTY_LIST;
    L28:
        r73.zzg(r6);
        r0.zzb(r73);
        return (com.google.android.gms.internal.measurement.zzhg) r0.zzbd();
    L20:
        ArrayList r2 = new ArrayList(r13.size());
        Iterator r14 = this.zzh.keySet().iterator();
    L22:
        if (r14.hasNext() == false) goto L27;
        Integer r3 = (Integer) r14.next();
        com.google.android.gms.internal.measurement.zzij r4 = com.google.android.gms.internal.measurement.zzik.zzf();
        r4.zza(r3.intValue());
        List r32 = (List) this.zzh.get(r3);
        if (r32 == null) goto L26;
        Collections.sort(r32);
        r4.zzb(r32);
    L26:
        r2.add((com.google.android.gms.internal.measurement.zzik) r4.zzbd());
        goto L22
    L27:
        r6 = r2;
        goto L28
    L8:
        ArrayList r22 = new ArrayList(r1.size());
        Iterator r15 = this.zzg.keySet().iterator();
    L10:
        if (r15.hasNext() == false) goto L14;
        Integer r33 = (Integer) r15.next();
        int r42 = r33.intValue();
        Long r34 = (Long) this.zzg.get(r33);
        if (r34 == null) goto L10;
        com.google.android.gms.internal.measurement.zzhp r5 = com.google.android.gms.internal.measurement.zzhq.zze();
        r5.zza(r42);
        r5.zzb(r34.longValue());
        r22.add((com.google.android.gms.internal.measurement.zzhq) r5.zzbd());
        goto L10
    L14:
        r12 = r22;
        goto L15
    }

    public final /* synthetic */ BitSet zzc() {
        return this.zze;
    }
}
