package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzil implements zzmr {
    private final zzii zza;

    private zzil(zzii r2) {
        zzii r22 = (zzii) zzjf.zza(r2, "output");
        this.zza = r22;
        r22.zza = this;
    }

    public static zzil zza(zzii r1) {
        zzil r0 = r1.zza;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new zzil(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final int zza() {
        return zzmq.zza;
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r2) {
        this.zza.zza(r2, 3);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r1, double r2) {
        this.zza.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r1, float r2) {
        this.zza.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r1, int r2) {
        this.zza.zze(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r1, long r2) {
        this.zza.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r1, zzht r2) {
        this.zza.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final <K, V> void zza(int r5, zzkf<K, V> r6, Map<K, V> r7) {
        Iterator<Map.Entry<K, V>> r72 = r7.entrySet().iterator();
    L4:
        if (r72.hasNext() == false) goto L6;
        Map.Entry<K, V> r0 = r72.next();
        this.zza.zza(r5, 2);
        this.zza.zzb(zzkc.zza(r6, r0.getKey(), r0.getValue()));
        zzkc.zza(this.zza, r6, r0.getKey(), r0.getValue());
        goto L4
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r2, Object r3) {
        boolean r0 = r3 instanceof zzht;
        zzii r1 = this.zza;
        if (r0 == false) goto L6;
        r1.zzb(r2, (zzht) r3);
        return;
    L6:
        r1.zza(r2, (zzkk) r3);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r1, Object r2, zzlc r3) {
        this.zza.zza(r1, (zzkk) r2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r1, String r2) {
        this.zza.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r6, List<String> r7) {
        int r1 = 0;
        if ((r7 instanceof zzjv) == false) goto L13;
        zzjv r0 = (zzjv) r7;
    L6:
        if (r1 >= r7.size()) goto L15;
        Object r2 = r0.zzb(r1);
        boolean r3 = r2 instanceof String;
        zzii r4 = this.zza;
        if (r3 == false) goto L10;
        r4.zza(r6, (String) r2);
    L11:
        r1 = r1 + 1;
        goto L6
    L10:
        r4.zza(r6, (zzht) r2);
        goto L11
    L15:
        return;
    L13:
        if (r1 >= r7.size()) goto L20;
        this.zza.zza(r6, r7.get(r1));
        r1 = r1 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r3, List<?> r4, zzlc r5) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        zza(r3, r4.get(r0), r5);
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zza.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzii.zzf(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zza.zzb(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zza.zza(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zza.zzb(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zza(int r1, boolean r2) {
        this.zza.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzb(int r2) {
        this.zza.zza(r2, 4);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzb(int r1, int r2) {
        this.zza.zzb(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzb(int r1, long r2) {
        this.zza.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzb(int r2, Object r3, zzlc r4) {
        zzii r1 = this.zza;
        r1.zza(r2, 3);
        r4.zza((zzkk) r3, r1.zza);
        r1.zza(r2, 4);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzb(int r4, List<zzht> r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        this.zza.zza(r4, r5.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzb(int r3, List<?> r4, zzlc r5) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        zzb(r3, r4.get(r0), r5);
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzb(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zza.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzii.zzi(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zza.zzb(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zza.zzd(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zza.zze(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzc(int r1, int r2) {
        this.zza.zzb(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzc(int r1, long r2) {
        this.zza.zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzc(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zza.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzii.zzd(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zza.zzb(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zza.zza(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zza.zza(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzd(int r1, int r2) {
        this.zza.zze(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzd(int r1, long r2) {
        this.zza.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzd(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zza.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzii.zze(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zza.zzb(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zza.zza(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zza.zza(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zze(int r1, int r2) {
        this.zza.zzc(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zze(int r1, long r2) {
        this.zza.zzb(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zze(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zza.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzii.zzg(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zza.zzb(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zza.zzc(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zza.zzc(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzf(int r1, int r2) {
        this.zza.zzd(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzf(int r3, List<Float> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zza.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzii.zzb(r4.get(r32).floatValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zza.zzb(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zza.zza(r4.get(r0).floatValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zza.zza(r3, r4.get(r0).floatValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzg(int r4, List<Double> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zza.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzii.zzb(r5.get(r42).doubleValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zza.zzb(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zza.zza(r5.get(r0).doubleValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zza.zza(r4, r5.get(r0).doubleValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzh(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zza.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzii.zzk(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zza.zzb(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zza.zza(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zza.zzb(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzi(int r3, List<Boolean> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zza.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzii.zzb(r4.get(r32).booleanValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zza.zzb(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zza.zza(r4.get(r0).booleanValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zza.zza(r3, r4.get(r0).booleanValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzj(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zza.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzii.zzg(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zza.zzb(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zza.zzb(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zza.zzc(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzk(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zza.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzii.zzj(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zza.zzb(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zza.zzd(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zza.zze(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzl(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zza.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzii.zzh(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zza.zzb(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zza.zzc(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zza.zzc(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzm(int r3, List<Integer> r4, boolean r5) {
        int r0 = 0;
        if (r5 == false) goto L13;
        this.zza.zza(r3, 2);
        int r32 = 0;
        int r52 = 0;
    L6:
        if (r32 >= r4.size()) goto L8;
        r52 = r52 + zzii.zzh(r4.get(r32).intValue());
        r32 = r32 + 1;
        goto L6
    L8:
        this.zza.zzb(r52);
    L10:
        if (r0 >= r4.size()) goto L15;
        this.zza.zzc(r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r4.size()) goto L19;
        this.zza.zzd(r3, r4.get(r0).intValue());
        r0 = r0 + 1;
        goto L13
    }

    @Override // com.google.android.gms.internal.vision.zzmr
    public final void zzn(int r4, List<Long> r5, boolean r6) {
        int r0 = 0;
        if (r6 == false) goto L13;
        this.zza.zza(r4, 2);
        int r42 = 0;
        int r62 = 0;
    L6:
        if (r42 >= r5.size()) goto L8;
        r62 = r62 + zzii.zzf(r5.get(r42).longValue());
        r42 = r42 + 1;
        goto L6
    L8:
        this.zza.zzb(r62);
    L10:
        if (r0 >= r5.size()) goto L15;
        this.zza.zzb(r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L10
    L15:
        return;
    L13:
        if (r0 >= r5.size()) goto L19;
        this.zza.zzb(r4, r5.get(r0).longValue());
        r0 = r0 + 1;
        goto L13
    }
}
