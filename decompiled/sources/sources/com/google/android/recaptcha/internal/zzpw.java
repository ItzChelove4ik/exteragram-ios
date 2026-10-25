package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public abstract class zzpw<K, V> implements Map<K, V>, Serializable {
    private transient zzqb zza;
    private transient zzqb zzb;
    private transient zzpq zzc;

    public zzpw() {
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object r1) {
        if (get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object r1) {
        return zzd().contains(r1);
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set entrySet() {
        return zza();
    }

    @Override // java.util.Map
    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Map) == true) goto L10;
        return false;
    L10:
        return entrySet().equals(((Map) r2).entrySet());
    }

    @Override // java.util.Map
    public abstract Object get(Object r1);

    @Override // java.util.Map
    public final Object getOrDefault(Object r1, Object r2) {
        Object r0 = get(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzqs.zza(zza());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzqb r0 = this.zzb;
        if (r0 != null) goto L6;
        zzqb r02 = zzc();
        this.zzb = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.Map
    @Deprecated
    public final Object put(Object r1, Object r2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final Object remove(Object r1) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int r0 = size();
        zzpa.zzb(r0, "size");
        StringBuilder r2 = new StringBuilder((int) Math.min(r0 * 8, 1073741824));
        r2.append('{');
        Iterator<Map.Entry<K, V>> r5 = entrySet().iterator();
        boolean r02 = true;
    L4:
        if (r5.hasNext() == false) goto L9;
        Map.Entry<K, V> r1 = r5.next();
        if (r02 == true) goto L8;
        r2.append(", ");
    L8:
        r2.append(r1.getKey());
        r2.append(SignatureVisitor.INSTANCEOF);
        r2.append(r1.getValue());
        r02 = false;
        goto L4
    L9:
        r2.append('}');
        return r2.toString();
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        return zzd();
    }

    public final zzqb zza() {
        zzqb r0 = this.zza;
        if (r0 != null) goto L6;
        zzqb r02 = zzb();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract zzqb zzb();

    public abstract zzqb zzc();

    public final zzpq zzd() {
        zzpq r0 = this.zzc;
        if (r0 != null) goto L6;
        zzpq r02 = zze();
        this.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract zzpq zze();
}
