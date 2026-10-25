package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzdi<K, V> extends LinkedHashMap<K, V> {
    private static final zzdi zzme = null;
    private boolean zzfa;

    static {
        zzdi r0 = new zzdi();
        zzme = r0;
        r0.zzfa = false;
    }

    private zzdi() {
        this.zzfa = true;
    }

    private zzdi(Map<K, V> r1) {
        super(r1);
        this.zzfa = true;
    }

    public static <K, V> zzdi<K, V> zzbz() {
        return zzme;
    }

    private final void zzcb() {
        if (this.zzfa == false) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    private static int zzf(Object r1) {
        if ((r1 instanceof byte[]) == false) goto L7;
        return zzci.hashCode((byte[]) r1);
    L7:
        if ((r1 instanceof zzcj) == false) goto L9;
        Type$$ExternalSyntheticBUOutline0.m();
        return 0;
    L9:
        return r1.hashCode();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzcb();
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
        int r2 = zzf(r1.getKey());
        r0 = r0 + (zzf(r1.getValue()) ^ r2);
        goto L4
    L6:
        return r0;
    }

    public final boolean isMutable() {
        return this.zzfa;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K r1, V r2) {
        zzcb();
        zzci.checkNotNull(r1);
        zzci.checkNotNull(r2);
        return (V) super.put(r1, r2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> r3) {
        zzcb();
        Iterator<? extends K> r0 = r3.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        K r1 = r0.next();
        zzci.checkNotNull(r1);
        zzci.checkNotNull(r3.get(r1));
        goto L4
    L6:
        super.putAll(r3);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object r1) {
        zzcb();
        return (V) super.remove(r1);
    }

    public final void zza(zzdi<K, V> r2) {
        zzcb();
        if (r2.isEmpty() == true) goto L6;
        putAll(r2);
        return;
    }

    public final zzdi<K, V> zzca() {
        if (isEmpty() == false) goto L7;
        return new zzdi();
    L7:
        return new zzdi(this);
    }

    public final void zzv() {
        this.zzfa = false;
    }
}
