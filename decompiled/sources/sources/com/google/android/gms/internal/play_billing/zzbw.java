package com.google.android.gms.internal.play_billing;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public abstract class zzbw implements Map, Serializable {
    private transient zzbx zza;
    private transient zzbx zzb;
    private transient zzbq zzc;

    public zzbw() {
    }

    public static zzbw zzc(Object r0, Object r1, Object r2, Object r3, Object r4, Object r5) {
        zzbo.zza("com.android.vending.billing.PURCHASES_UPDATED", r1);
        zzbo.zza("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", r3);
        zzbo.zza("com.android.vending.billing.ALTERNATIVE_BILLING", r5);
        return zzcf.zzg(3, new Object[]{"com.android.vending.billing.PURCHASES_UPDATED", r1, "com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", r3, "com.android.vending.billing.ALTERNATIVE_BILLING", r5}, null);
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
        return zzcg.zza(zzf());
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
        zzbx r0 = this.zzb;
        if (r0 != null) goto L6;
        zzbx r02 = zze();
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
        CharCodeKt$$ExternalSyntheticBUOutline0.m("size cannot be negative but was: ", r0);
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        return zzb();
    }

    public abstract zzbq zza();

    public final zzbq zzb() {
        zzbq r0 = this.zzc;
        if (r0 != null) goto L6;
        zzbq r02 = zza();
        this.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract zzbx zzd();

    public abstract zzbx zze();

    public final zzbx zzf() {
        zzbx r0 = this.zza;
        if (r0 != null) goto L6;
        zzbx r02 = zzd();
        this.zza = r02;
        return r02;
    L6:
        return r0;
    }
}
