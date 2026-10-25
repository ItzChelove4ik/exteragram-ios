package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzbp implements zzfr {
    private final zzbn zzfo;

    private zzbp(zzbn r2) {
        zzbn r22 = (zzbn) zzci.zza(r2, "output");
        this.zzfo = r22;
        r22.zzfz = this;
    }

    public static zzbp zza(zzbn r1) {
        zzbp r0 = r1.zzfz;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new zzbp(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r1, double r2) {
        this.zzfo.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r1, float r2) {
        this.zzfo.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r1, long r2) {
        this.zzfo.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r1, zzbb r2) {
        this.zzfo.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final <K, V> void zza(int r5, zzdh<K, V> r6, Map<K, V> r7) {
        Iterator<Map.Entry<K, V>> r72 = r7.entrySet().iterator();
    L4:
        if (r72.hasNext() == false) goto L6;
        Map.Entry<K, V> r0 = r72.next();
        this.zzfo.zzb(r5, 2);
        this.zzfo.zzo(zzdg.zza(r6, r0.getKey(), r0.getValue()));
        zzdg.zza(this.zzfo, r6, r0.getKey(), r0.getValue());
        goto L4
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r2, Object r3) {
        boolean r0 = r3 instanceof zzbb;
        zzbn r1 = this.zzfo;
        if (r0 == false) goto L6;
        r1.zzb(r2, (zzbb) r3);
        return;
    L6:
        r1.zzb(r2, (zzdo) r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r1, Object r2, zzef r3) {
        this.zzfo.zza(r1, (zzdo) r2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r1, String r2) {
        this.zzfo.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r6, List<String> r7) {
        int r1 = 0;
        if ((r7 instanceof zzcx) == false) goto L13;
        zzcx r0 = (zzcx) r7;
    L6:
        if (r1 >= r7.size()) goto L15;
        Object r2 = r0.getRaw(r1);
        boolean r3 = r2 instanceof String;
        zzbn r4 = this.zzfo;
        if (r3 == false) goto L10;
        r4.zza(r6, (String) r2);
    L11:
        r1 = r1 + 1;
        goto L6
    L10:
        r4.zza(r6, (zzbb) r2);
        goto L11
    L15:
        return;
    L13:
        if (r1 >= r7.size()) goto L20;
        this.zzfo.zza(r6, r7.get(r1));
        r1 = r1 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r3, List<?> r4, zzef r5) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        zza(r3, r4.get(r0), r5);
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zzfo.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzbn.zzs(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zzfo.zzn(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zzfo.zzc(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzaa(int r2) {
        this.zzfo.zzb(r2, 3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzab(int r2) {
        this.zzfo.zzb(r2, 4);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final int zzaj() {
        return zzcg.zzg.zzko;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r1, long r2) {
        this.zzfo.zzb(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r2, Object r3, zzef r4) {
        zzbn r1 = this.zzfo;
        r1.zzb(r2, 3);
        r4.zza((zzdo) r3, r1.zzfz);
        r1.zzb(r2, 4);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r4, List<zzbb> r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        this.zzfo.zza(r4, r5.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r3, List<?> r4, zzef r5) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        zzb(r3, r4.get(r0), r5);
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zzfo.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzbn.zzv(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zzfo.zzq(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zzfo.zzf(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r1, boolean r2) {
        this.zzfo.zzb(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzc(int r1, int r2) {
        this.zzfo.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzc(int r1, long r2) {
        this.zzfo.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzc(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zzfo.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzbn.zze(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zzfo.zzb(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zzfo.zza(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzd(int r1, int r2) {
        this.zzfo.zzd(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzd(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zzfo.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzbn.zzf(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zzfo.zzb(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zzfo.zza(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zze(int r1, int r2) {
        this.zzfo.zze(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zze(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zzfo.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzbn.zzh(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zzfo.zzd(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zzfo.zzc(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzf(int r1, int r2) {
        this.zzfo.zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzf(int r3, List<Float> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zzfo.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzbn.zzb(r4.get(r32).floatValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zzfo.zza(r4.get(r0).floatValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zzfo.zza(r3, r4.get(r0).floatValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzg(int r4, List<Double> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zzfo.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzbn.zzb(r5.get(r42).doubleValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zzfo.zza(r5.get(r0).doubleValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zzfo.zza(r4, r5.get(r0).doubleValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzh(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zzfo.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzbn.zzx(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zzfo.zzn(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zzfo.zzc(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzi(int r1, long r2) {
        this.zzfo.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzi(int r3, List<Boolean> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zzfo.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzbn.zzb(r4.get(r32).booleanValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zzfo.zza(r4.get(r0).booleanValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zzfo.zzb(r3, r4.get(r0).booleanValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzj(int r1, long r2) {
        this.zzfo.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzj(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zzfo.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzbn.zzt(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zzfo.zzo(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zzfo.zzd(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzk(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zzfo.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzbn.zzw(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zzfo.zzq(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zzfo.zzf(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzl(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zzfo.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzbn.zzi(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zzfo.zzd(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zzfo.zzc(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzm(int r1, int r2) {
        this.zzfo.zzf(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzm(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zzfo.zzb(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzbn.zzu(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zzfo.zzp(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zzfo.zze(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzn(int r1, int r2) {
        this.zzfo.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzn(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zzfo.zzb(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzbn.zzg(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zzfo.zzo(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zzfo.zzc(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zzfo.zzb(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }
}
