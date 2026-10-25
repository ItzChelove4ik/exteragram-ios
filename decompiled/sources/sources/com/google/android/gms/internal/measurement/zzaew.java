package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzaew extends LinkedHashMap {
    private static final zzaew zzb = null;
    private boolean zza;

    static {
        zzaew r0 = new zzaew();
        zzb = r0;
        r0.zza = false;
    }

    private zzaew() {
        this.zza = true;
    }

    private zzaew(Map r1) {
        super(r1);
        this.zza = true;
    }

    public static zzaew zza() {
        return zzb;
    }

    private static int zzf(Object r2) {
        if ((r2 instanceof byte[]) == false) goto L9;
        byte[] r22 = (byte[]) r2;
        int r0 = r22.length;
        int r23 = zzaed.zzb(r0, r22, 0, r0);
        if (r23 != 0) goto L14;
        return 1;
    L14:
        return r23;
    L9:
        if ((r2 instanceof zzady) == false) goto L11;
        Type$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        return r2.hashCode();
    }

    private final void zzg() {
        if (this.zza == false) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzg();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (isEmpty() == false) goto L7;
        return Collections.EMPTY_SET;
    L7:
        return super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object r4) {
        if ((r4 instanceof Map) == false) goto L25;
        Map r42 = (Map) r4;
        if (this != r42) goto L8;
        return true;
    L8:
        if (size() != r42.size()) goto L34;
        Iterator r3 = entrySet().iterator();
    L12:
        if (r3.hasNext() == false) goto L33;
        Map.Entry r0 = (Map.Entry) r3.next();
        if (r42.containsKey(r0.getKey()) == false) goto L36;
        Object r1 = r0.getValue();
        Object r02 = r42.get(r0.getKey());
        if ((r1 instanceof byte[]) == true) goto L18;
    L20:
        boolean r03 = r1.equals(r02);
    L21:
        if (r03 == true) goto L12;
        return false;
    L18:
        if ((r02 instanceof byte[]) == false) goto L20;
        r03 = Arrays.equals((byte[]) r1, (byte[]) r02);
        goto L21
    L36:
        return false;
    L33:
        return true;
    L34:
        return false;
    L25:
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        Iterator r3 = entrySet().iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry r1 = (Map.Entry) r3.next();
        int r2 = zzf(r1.getKey());
        r0 = r0 + (zzf(r1.getValue()) ^ r2);
        goto L4
    L6:
        return r0;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object put(Object r1, Object r2) {
        zzg();
        r1.getClass();
        r2.getClass();
        return super.put(r1, r2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map r3) {
        zzg();
        Iterator r0 = r3.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        Object r1 = r0.next();
        r1.getClass();
        r3.get(r1).getClass();
        goto L4
    L6:
        super.putAll(r3);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object r1) {
        zzg();
        return super.remove(r1);
    }

    public final void zzb(zzaew r2) {
        zzg();
        if (r2.isEmpty() == true) goto L6;
        putAll(r2);
        return;
    }

    public final zzaew zzc() {
        if (isEmpty() == false) goto L7;
        return new zzaew();
    L7:
        return new zzaew(this);
    }

    public final void zzd() {
        this.zza = false;
    }

    public final boolean zze() {
        return this.zza;
    }
}
