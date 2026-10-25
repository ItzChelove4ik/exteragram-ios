package com.google.android.gms.internal.mlkit_vision_label;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public abstract class zzbg implements Map, Serializable {
    private transient zzbh zza;
    private transient zzbh zzb;
    private transient zzaz zzc;

    public zzbg() {
    }

    public static zzbg zzc(Object r1, Object r2) {
        zzal.zzb("optional-module-barcode", "com.google.android.gms.vision.barcode");
        return zzbz.zzg(1, new Object[]{"optional-module-barcode", "com.google.android.gms.vision.barcode"}, null);
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
        return zzb().contains(r1);
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set entrySet() {
        return zzf();
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
        return zzcb.zza(zzf());
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
        zzbh r0 = this.zzb;
        if (r0 != null) goto L6;
        zzbh r02 = zze();
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
        zzal.zza(r0, "size");
        StringBuilder r1 = new StringBuilder((int) Math.min(r0 * 8, 1073741824));
        r1.append('{');
        Iterator r6 = entrySet().iterator();
        boolean r02 = true;
    L4:
        if (r6.hasNext() == false) goto L9;
        Map.Entry r2 = (Map.Entry) r6.next();
        if (r02 == true) goto L8;
        r1.append(", ");
    L8:
        r1.append(r2.getKey());
        r1.append(SignatureVisitor.INSTANCEOF);
        r1.append(r2.getValue());
        r02 = false;
        goto L4
    L9:
        r1.append('}');
        return r1.toString();
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        return zzb();
    }

    public abstract zzaz zza();

    public final zzaz zzb() {
        zzaz r0 = this.zzc;
        if (r0 != null) goto L6;
        zzaz r02 = zza();
        this.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract zzbh zzd();

    public abstract zzbh zze();

    public final zzbh zzf() {
        zzbh r0 = this.zza;
        if (r0 != null) goto L6;
        zzbh r02 = zzd();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }
}
