package com.google.android.gms.internal.mlkit_genai_prompt;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public abstract class zzmo implements Map, Serializable {
    private transient zzms zza;
    private transient zzms zzb;
    private transient zzmg zzc;

    public zzmo() {
    }

    public static zzmo zza() {
        return zznl.zza;
    }

    public static zzmo zzb(Object r1, Object r2) {
        zzlu.zza("optional-module-barcode", "com.google.android.gms.vision.barcode");
        return zznl.zzj(1, new Object[]{"optional-module-barcode", "com.google.android.gms.vision.barcode"}, null);
    }

    public static zzmo zzc(Object r0, Object r1, Object r2, Object r3) {
        zzlu.zza(r0, r1);
        zzlu.zza(r2, r3);
        return zznl.zzj(2, new Object[]{r0, r1, r2, r3}, null);
    }

    public static zzmo zzd(Map r2) {
        if ((r2 instanceof zzmo) == true) goto L5;
    L8:
        Set r22 = r2.entrySet();
        if (r22 == null) goto L11;
        int r0 = r22.size();
    L12:
        zzmn r1 = new zzmn(r0);
        r1.zzb(r22);
        return r1.zzc();
    L11:
        r0 = 4;
        goto L12
    L5:
        if ((r2 instanceof SortedMap) == true) goto L8;
        return (zzmo) r2;
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
        return zzh().contains(r1);
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set entrySet() {
        return zze();
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
        return zzno.zza(zze());
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
        zzms r0 = this.zzb;
        if (r0 != null) goto L6;
        zzms r02 = zzg();
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
        zzlu.zzb(r0, "size");
        StringBuilder r2 = new StringBuilder((int) Math.min(r0 * 8, 1073741824));
        r2.append('{');
        Iterator r5 = entrySet().iterator();
        boolean r02 = true;
    L4:
        if (r5.hasNext() == false) goto L9;
        Map.Entry r1 = (Map.Entry) r5.next();
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
        return zzh();
    }

    public final zzms zze() {
        zzms r0 = this.zza;
        if (r0 != null) goto L6;
        zzms r02 = zzf();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract zzms zzf();

    public abstract zzms zzg();

    public final zzmg zzh() {
        zzmg r0 = this.zzc;
        if (r0 != null) goto L6;
        zzmg r02 = zzi();
        this.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract zzmg zzi();
}
