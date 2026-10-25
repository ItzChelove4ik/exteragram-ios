package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzke<K, V> extends LinkedHashMap<K, V> {
    private static final zzke zzb = null;
    private boolean zza;

    static {
        zzke r0 = new zzke();
        zzb = r0;
        r0.zza = false;
    }

    private zzke() {
        this.zza = true;
    }

    private zzke(Map<K, V> r1) {
        super(r1);
        this.zza = true;
    }

    private static int zza(Object r1) {
        if ((r1 instanceof byte[]) == false) goto L7;
        return zzjf.zzc((byte[]) r1);
    L7:
        if ((r1 instanceof zzje) == false) goto L9;
        Type$$ExternalSyntheticBUOutline0.m();
        return 0;
    L9:
        return r1.hashCode();
    }

    public static <K, V> zzke<K, V> zza() {
        return zzb;
    }

    private final void zze() {
        if (this.zza == false) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zze();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        if (isEmpty() == false) goto L7;
        return Collections.EMPTY_SET;
    L7:
        return super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object r6) {
        if ((r6 instanceof Map) == false) goto L26;
        Map r62 = (Map) r6;
        if (this != r62) goto L7;
    L23:
        boolean r5 = true;
    L24:
        if (r5 == false) goto L26;
        return true;
    L7:
        if (size() == r62.size()) goto L9;
    L8:
        r5 = false;
        goto L24
    L9:
        Iterator<Map.Entry<K, V>> r52 = entrySet().iterator();
    L11:
        if (r52.hasNext() == false) goto L23;
        Map.Entry<K, V> r2 = r52.next();
        if (r62.containsKey(r2.getKey()) == false) goto L8;
        V r3 = r2.getValue();
        Object r22 = r62.get(r2.getKey());
        if ((r3 instanceof byte[]) == true) goto L18;
    L20:
        boolean r23 = r3.equals(r22);
    L21:
        if (r23 == true) goto L11;
    L18:
        if ((r22 instanceof byte[]) == false) goto L20;
        r23 = Arrays.equals((byte[]) r3, (byte[]) r22);
    L26:
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        Iterator<Map.Entry<K, V>> r3 = entrySet().iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry<K, V> r1 = r3.next();
        int r2 = zza(r1.getKey());
        r0 = r0 + (zza(r1.getValue()) ^ r2);
        goto L4
    L6:
        return r0;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K r1, V r2) {
        zze();
        zzjf.zza(r1);
        zzjf.zza(r2);
        return (V) super.put(r1, r2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> r3) {
        zze();
        Iterator<? extends K> r0 = r3.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        K r1 = r0.next();
        zzjf.zza(r1);
        zzjf.zza(r3.get(r1));
        goto L4
    L6:
        super.putAll(r3);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object r1) {
        zze();
        return (V) super.remove(r1);
    }

    public final void zza(zzke<K, V> r2) {
        zze();
        if (r2.isEmpty() == true) goto L6;
        putAll(r2);
        return;
    }

    public final zzke<K, V> zzb() {
        if (isEmpty() == false) goto L7;
        return new zzke();
    L7:
        return new zzke(this);
    }

    public final void zzc() {
        this.zza = false;
    }

    public final boolean zzd() {
        return this.zza;
    }
}
