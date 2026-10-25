package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public abstract class zzhy implements Map, Serializable {
    private transient zzhz zza;
    private transient zzhz zzb;
    private transient zzhr zzc;

    public zzhy() {
    }

    public static zzhy zza() {
        return zzih.zza;
    }

    public static zzhy zzb(Iterable r2) {
        if ((r2 instanceof Collection) == false) goto L5;
        int r0 = ((Collection) r2).size();
    L6:
        zzhx r1 = new zzhx(r0);
        r1.zza(r2);
        zzhw r22 = r1.zzc;
        if (r22 != null) goto L14;
        zzih r23 = zzih.zzh(r1.zzb, r1.zza, r1);
        zzhw r02 = r1.zzc;
        if (r02 != null) goto L12;
        return r23;
    L12:
        throw r02.zza();
    L14:
        throw r22.zza();
    L5:
        r0 = 4;
        goto L6
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
        return zzf().contains(r1);
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set entrySet() {
        return zzc();
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
        return zzij.zza(zzc());
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
        zzhz r0 = this.zzb;
        if (r0 != null) goto L6;
        zzhz r02 = zze();
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
        if (r0 < 0) goto L13;
        StringBuilder r2 = new StringBuilder((int) Math.min(r0 * 8, 1073741824));
        r2.append('{');
        Iterator r5 = entrySet().iterator();
        boolean r02 = true;
    L6:
        if (r5.hasNext() == false) goto L11;
        Map.Entry r1 = (Map.Entry) r5.next();
        if (r02 == true) goto L10;
        r2.append(", ");
    L10:
        r2.append(r1.getKey());
        r2.append(SignatureVisitor.INSTANCEOF);
        r2.append(r1.getValue());
        r02 = false;
        goto L6
    L11:
        r2.append('}');
        return r2.toString();
    L13:
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r0).length() + 33, "size cannot be negative but was: ", r0);
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        return zzf();
    }

    public final zzhz zzc() {
        zzhz r0 = this.zza;
        if (r0 != null) goto L6;
        zzhz r02 = zzd();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract zzhz zzd();

    public abstract zzhz zze();

    public final zzhr zzf() {
        zzhr r0 = this.zzc;
        if (r0 != null) goto L6;
        zzhr r02 = zzg();
        this.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract zzhr zzg();
}
