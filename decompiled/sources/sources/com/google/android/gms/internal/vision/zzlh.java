package com.google.android.gms.internal.vision;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
abstract class zzlh<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int zza;
    private List<zzlm> zzb;
    private Map<K, V> zzc;
    private boolean zzd;
    private volatile zzlo zze;
    private Map<K, V> zzf;
    private volatile zzli zzg;

    private zzlh(int r1) {
        this.zza = r1;
        this.zzb = Collections.EMPTY_LIST;
        Map<K, V> r12 = Collections.EMPTY_MAP;
        this.zzc = r12;
        this.zzf = r12;
    }

    public /* synthetic */ zzlh(int r1, zzlg r2) {
        this(r1);
    }

    private final int zza(K r5) {
        int r0 = this.zzb.size();
        int r1 = r0 - 1;
        if (r1 < 0) goto L11;
        int r2 = r5.compareTo((Comparable) this.zzb.get(r1).getKey());
        if (r2 <= 0) goto L9;
        int r02 = r0 + 1;
    L8:
        return -r02;
    L9:
        if (r2 != 0) goto L11;
        return r1;
    L11:
        int r03 = 0;
    L12:
        if (r03 > r1) goto L19;
        int r22 = (r03 + r1) / 2;
        int r3 = r5.compareTo((Comparable) this.zzb.get(r22).getKey());
        if (r3 < 0) goto L15;
        if (r3 <= 0) goto L18;
        r03 = r22 + 1;
        goto L12
    L18:
        return r22;
    L15:
        r1 = r22 - 1;
        goto L12
    L19:
        r02 = r03 + 1;
        goto L8
    }

    public static <FieldDescriptorType extends zziw<FieldDescriptorType>> zzlh<FieldDescriptorType, Object> zza(int r1) {
        return new zzlg(r1);
    }

    public static /* synthetic */ Object zza(zzlh r0, int r1) {
        return r0.zzc(r1);
    }

    public static /* synthetic */ void zza(zzlh r0) {
        r0.zzf();
    }

    public static /* synthetic */ List zzb(zzlh r0) {
        return r0.zzb;
    }

    private final V zzc(int r5) {
        zzf();
        V r52 = (V) this.zzb.remove(r5).getValue();
        if (this.zzc.isEmpty() == true) goto L5;
        Iterator<Map.Entry<K, V>> r0 = zzg().entrySet().iterator();
        this.zzb.add(new zzlm(this, r0.next()));
        r0.remove();
    L5:
        return r52;
    }

    public static /* synthetic */ Map zzc(zzlh r0) {
        return r0.zzc;
    }

    public static /* synthetic */ Map zzd(zzlh r0) {
        return r0.zzf;
    }

    private final void zzf() {
        if (this.zzd == true) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    private final SortedMap<K, V> zzg() {
        zzf();
        if (this.zzc.isEmpty() == false) goto L8;
        if ((this.zzc instanceof TreeMap) == true) goto L8;
        TreeMap r0 = new TreeMap();
        this.zzc = r0;
        this.zzf = r0.descendingMap();
    L8:
        return (SortedMap) this.zzc;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzf();
        if (this.zzb.isEmpty() == true) goto L6;
        this.zzb.clear();
    L6:
        if (this.zzc.isEmpty() == true) goto L9;
        this.zzc.clear();
        return;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object r2) {
        Comparable r22 = (Comparable) r2;
        if (zza(r22) < 0) goto L5;
        return true;
    L5:
        if (this.zzc.containsKey(r22) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zze != null) goto L6;
        this.zze = new zzlo(this, null);
    L6:
        return this.zze;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzlh) == false) goto L8;
        zzlh r82 = (zzlh) r8;
        int r1 = size();
        if (r1 == r82.size()) goto L12;
        return false;
    L12:
        int r2 = zzc();
        if (r2 != r82.zzc()) goto L15;
        int r4 = 0;
    L17:
        if (r4 >= r2) goto L22;
        if (zzb(r4).equals(r82.zzb(r4)) == false) goto L20;
        r4 = r4 + 1;
        goto L17
    L20:
        return false;
    L22:
        if (r2 != r1) goto L24;
        return true;
    L24:
        return this.zzc.equals(r82.zzc);
    L15:
        return entrySet().equals(r82.entrySet());
    L8:
        return super.equals(r8);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object r2) {
        Comparable r22 = (Comparable) r2;
        int r0 = zza(r22);
        if (r0 < 0) goto L7;
        return (V) this.zzb.get(r0).getValue();
    L7:
        return this.zzc.get(r22);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int r0 = zzc();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L6;
        r2 = r2 + this.zzb.get(r1).hashCode();
        r1 = r1 + 1;
        goto L3
    L6:
        if (this.zzc.size() > 0) goto L8;
        return r2;
    L8:
        return r2 + this.zzc.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object r1, Object r2) {
        return zza((Comparable) r1, r2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object r2) {
        zzf();
        Comparable r22 = (Comparable) r2;
        int r0 = zza(r22);
        if (r0 < 0) goto L7;
        return zzc(r0);
    L7:
        if (this.zzc.isEmpty() == false) goto L11;
        return null;
    L11:
        return this.zzc.remove(r22);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzb.size() + this.zzc.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final V zza(K r5, V r6) {
        zzf();
        int r0 = zza(r5);
        if (r0 >= 0) goto L5;
        zzf();
        if (this.zzb.isEmpty() == true) goto L9;
    L11:
        int r02 = -(r0 + 1);
        if (r02 >= this.zza) goto L14;
        int r1 = this.zzb.size();
        int r2 = this.zza;
        if (r1 != r2) goto L18;
        zzlm r12 = this.zzb.remove(r2 - 1);
        zzg().put((Comparable) r12.getKey(), r12.getValue());
    L18:
        this.zzb.add(r02, new zzlm(this, r5, r6));
        return null;
    L14:
        return zzg().put(r5, r6);
    L9:
        if ((this.zzb instanceof ArrayList) == true) goto L11;
        this.zzb = new ArrayList(this.zza);
        goto L11
    L5:
        return (V) this.zzb.get(r0).setValue(r6);
    }

    public void zza() {
        if (this.zzd == false) goto L5;
        return;
    L5:
        if (this.zzc.isEmpty() == false) goto L7;
        Map<K, V> r0 = Collections.EMPTY_MAP;
    L8:
        this.zzc = r0;
        if (this.zzf.isEmpty() == false) goto L11;
        Map<K, V> r02 = Collections.EMPTY_MAP;
    L12:
        this.zzf = r02;
        this.zzd = true;
        return;
    L11:
        r02 = Collections.unmodifiableMap(this.zzf);
        goto L12
    L7:
        r0 = Collections.unmodifiableMap(this.zzc);
        goto L8
    }

    public final Map.Entry<K, V> zzb(int r1) {
        return this.zzb.get(r1);
    }

    public final boolean zzb() {
        return this.zzd;
    }

    public final int zzc() {
        return this.zzb.size();
    }

    public final Iterable<Map.Entry<K, V>> zzd() {
        if (this.zzc.isEmpty() == false) goto L7;
        return zzll.zza();
    L7:
        return this.zzc.entrySet();
    }

    public final Set<Map.Entry<K, V>> zze() {
        if (this.zzg != null) goto L6;
        this.zzg = new zzli(this, null);
    L6:
        return this.zzg;
    }
}
