package com.google.android.gms.internal.fido;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;

/* loaded from: classes4.dex */
public final class zzcj extends zzcd implements NavigableMap {
    private static final Comparator zzb = null;
    private static final zzcj zzc = null;
    private final transient zzcv zzd;
    private final transient zzcc zze;
    private final transient zzcj zzf;

    static {
        zzcq r0 = zzcq.zza;
        zzb = r0;
        zzcv r02 = zzck.zzs(r0);
        int r2 = zzcc.$r8$clinit;
        zzc = new zzcj(r02, zzct.zza, null);
    }

    public zzcj(zzcv r1, zzcc r2, zzcj r3) {
        this.zzd = r1;
        this.zze = r2;
        this.zzf = r3;
    }

    public static /* bridge */ /* synthetic */ zzcc zze(zzcj r0) {
        return r0.zze;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzcj zzf(Map r11) {
        Comparator r0 = zzb;
        Comparator r1 = r11.comparator();
        int r2 = 1;
        if (r1 != null) goto L5;
        boolean r12 = true;
    L6:
        Collection r112 = r11.entrySet();
        Map.Entry[] r3 = zzcd.zza;
        if (r112 != null) goto L13;
        Iterator r113 = r112.iterator();
        Collection r4 = new ArrayList();
        r113.getClass();
    L10:
        if (r113.hasNext() == false) goto L12;
        r4.add(r113.next());
        goto L10
    L12:
        r112 = r4;
    L13:
        Map.Entry[] r114 = (Map.Entry[]) r112.toArray(r3);
        int r32 = r114.length;
        if (r32 == 0) goto L33;
        int r5 = 0;
        if (r32 == 1) goto L30;
        Object[] r6 = new Object[r32];
        Object[] r7 = new Object[r32];
        if (r12 == false) goto L21;
    L19:
        if (r5 >= r32) goto L29;
        Map.Entry r13 = r114[r5];
        Objects.requireNonNull(r13);
        Map.Entry r14 = r13;
        Object r22 = r14.getKey();
        Object r15 = r14.getValue();
        zzbv.zza(r22, r15);
        r6[r5] = r22;
        r7[r5] = r15;
        r5 = r5 + 1;
    L29:
        return new zzcj(new zzcv(zzcc.zzh(r6, r32), r0), zzcc.zzh(r7, r32), null);
    L21:
        Arrays.sort(r114, 0, r32, new zzcg(r0));
        Map.Entry r16 = r114[0];
        Objects.requireNonNull(r16);
        Map.Entry r17 = r16;
        Object r8 = r17.getKey();
        r6[0] = r8;
        Object r18 = r17.getValue();
        r7[0] = r18;
        zzbv.zza(r6[0], r18);
    L22:
        if (r2 >= r32) goto L29;
        Map.Entry r19 = r114[r2 - 1];
        Objects.requireNonNull(r19);
        Map.Entry r110 = r19;
        Map.Entry r52 = r114[r2];
        Objects.requireNonNull(r52);
        Map.Entry r53 = r52;
        Object r9 = r53.getKey();
        Object r10 = r53.getValue();
        zzbv.zza(r9, r10);
        r6[r2] = r9;
        r7[r2] = r10;
        if (r0.compare(r8, r9) == 0) goto L27;
        r2 = r2 + 1;
        r8 = r9;
        goto L22
    L27:
        throw new IllegalArgumentException("Multiple entries with same key: " + String.valueOf(r110) + " and " + String.valueOf(r53));
    L30:
        Map.Entry r115 = r114[0];
        Objects.requireNonNull(r115);
        Map.Entry r116 = r115;
        return new zzcj(new zzcv(zzcc.zzj(r116.getKey()), r0), zzcc.zzj(r116.getValue()), null);
    L33:
        return zzg(r0);
    L5:
        r12 = r0.equals(r1);
        goto L6
    }

    public static zzcj zzg(Comparator r3) {
        if (zzcq.zza.equals(r3) == true) goto L5;
        zzcv r32 = zzck.zzs(r3);
        int r1 = zzcc.$r8$clinit;
        return new zzcj(r32, zzct.zza, null);
    L5:
        return zzc;
    }

    public static /* bridge */ /* synthetic */ zzcv zzk(zzcj r0) {
        return r0.zzd;
    }

    private final zzcj zzl(int r3, int r4) {
        if (r3 == 0) goto L4;
    L7:
        zzcv r0 = this.zzd;
        if (r3 == r4) goto L10;
        zzcc r2 = this.zze;
        return new zzcj(r0.zzw(r3, r4), r2.zzg(r3, r4), null);
    L10:
        return zzg(r0.zza);
    L4:
        if (r4 == this.zze.size()) goto L6;
        r3 = 0;
        goto L7
    L6:
        return this;
    }

    @Override // java.util.NavigableMap
    public final Map.Entry ceilingEntry(Object r2) {
        return zzj(r2, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Object ceilingKey(Object r1) {
        return zzco.zza(ceilingEntry(r1));
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        return this.zzd.zza;
    }

    @Override // java.util.NavigableMap
    public final /* synthetic */ NavigableSet descendingKeySet() {
        return this.zzd.zzn();
    }

    @Override // java.util.NavigableMap
    public final /* bridge */ /* synthetic */ NavigableMap descendingMap() {
        zzcj r0 = this.zzf;
        if (r0 != null) goto L14;
        boolean r02 = isEmpty();
        zzcv r1 = this.zzd;
        if (r02 == false) goto L13;
        Comparator r3 = r1.zza;
        if ((r3 instanceof zzcs) == false) goto L9;
        zzcs r32 = (zzcs) r3;
    L11:
        return zzg(r32.zza());
    L9:
        r32 = new zzbw(r3);
        goto L11
    L13:
        return new zzcj((zzcv) r1.zzn(), this.zze.zzf(), this);
    L14:
        return r0;
    }

    @Override // java.util.Map, java.util.SortedMap
    public final /* bridge */ /* synthetic */ Set entrySet() {
        return zzc();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry firstEntry() {
        if (isEmpty() == false) goto L7;
        return null;
    L7:
        return (Map.Entry) zzc().zzi().get(0);
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return this.zzd.first();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry floorEntry(Object r2) {
        return zzh(r2, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public final Object floorKey(Object r1) {
        return zzco.zza(floorEntry(r1));
    }

    @Override // com.google.android.gms.internal.fido.zzcd, java.util.Map
    public final Object get(Object r4) {
        zzcv r0 = this.zzd;
        if (r4 != null) goto L14;
    L4:
        int r42 = -1;
    L8:
        if (r42 != (-1)) goto L12;
        return null;
    L12:
        return this.zze.get(r42);
    L14:
        r42 = Collections.binarySearch(r0.zzd, r4, r0.zza);     // Catch: ClassCastException -> L13
        if (r42 >= 0) goto L8;
        goto L8
    }

    @Override // java.util.NavigableMap
    public final /* bridge */ /* synthetic */ NavigableMap headMap(Object r1, boolean r2) {
        return zzh(r1, r2);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final /* synthetic */ SortedMap headMap(Object r2) {
        return zzh(r2, false);
    }

    @Override // java.util.NavigableMap
    public final Map.Entry higherEntry(Object r2) {
        return zzj(r2, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Object higherKey(Object r1) {
        return zzco.zza(higherEntry(r1));
    }

    @Override // java.util.Map, java.util.SortedMap
    public final /* synthetic */ Set keySet() {
        return this.zzd;
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lastEntry() {
        if (isEmpty() == false) goto L7;
        return null;
    L7:
        return (Map.Entry) zzc().zzi().get(this.zze.size() - 1);
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return this.zzd.last();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lowerEntry(Object r2) {
        return zzh(r2, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    public final Object lowerKey(Object r1) {
        return zzco.zza(lowerEntry(r1));
    }

    @Override // java.util.NavigableMap
    public final /* synthetic */ NavigableSet navigableKeySet() {
        return this.zzd;
    }

    @Override // java.util.NavigableMap
    @Deprecated
    public final Map.Entry pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    @Deprecated
    public final Map.Entry pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final int size() {
        return this.zze.size();
    }

    @Override // java.util.NavigableMap
    public final /* bridge */ /* synthetic */ NavigableMap subMap(Object r1, boolean r2, Object r3, boolean r4) {
        return zzi(r1, r2, r3, r4);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final /* bridge */ /* synthetic */ SortedMap subMap(Object r3, Object r4) {
        return zzi(r3, true, r4, false);
    }

    @Override // java.util.NavigableMap
    public final /* bridge */ /* synthetic */ NavigableMap tailMap(Object r1, boolean r2) {
        return zzj(r1, r2);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final /* synthetic */ SortedMap tailMap(Object r2) {
        return zzj(r2, true);
    }

    @Override // java.util.Map, java.util.SortedMap
    public final /* synthetic */ Collection values() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.fido.zzcd
    public final zzby zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.fido.zzcd
    public final zzcf zzb() {
        if (isEmpty() == false) goto L7;
        return zzcu.zza;
    L7:
        return new zzci(this);
    }

    public final zzcj zzh(Object r2, boolean r3) {
        r2.getClass();
        return zzl(0, this.zzd.zzu(r2, r3));
    }

    public final zzcj zzi(Object r2, boolean r3, Object r4, boolean r5) {
        r2.getClass();
        r4.getClass();
        if (this.zzd.zza.compare(r2, r4) <= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m(zzbo.zza("expected fromKey <= toKey but %s > %s", new Object[]{r2, r4}));
        return null;
    L5:
        return zzh(r4, r5).zzj(r2, r3);
    }

    public final zzcj zzj(Object r2, boolean r3) {
        r2.getClass();
        return zzl(this.zzd.zzv(r2, r3), this.zze.size());
    }
}
