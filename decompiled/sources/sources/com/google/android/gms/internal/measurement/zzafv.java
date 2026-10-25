package com.google.android.gms.internal.measurement;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class zzafv extends AbstractMap {
    private Object[] zza;
    private int zzb;
    private Map zzc;
    private boolean zzd;
    private volatile zzafu zze;
    private Map zzf;

    public /* synthetic */ zzafv(byte[] r1) {
        Map r12 = Collections.EMPTY_MAP;
        this.zzc = r12;
        this.zzf = r12;
    }

    private final Object zzl(int r7) {
        zzn();
        Object r0 = ((zzafs) this.zza[r7]).getValue();
        Object[] r1 = this.zza;
        System.arraycopy(r1, r7 + 1, r1, r7, (this.zzb - r7) - 1);
        this.zzb--;
        if (this.zzc.isEmpty() == true) goto L5;
        Iterator r72 = zzo().entrySet().iterator();
        Object[] r12 = this.zza;
        int r2 = this.zzb;
        Map.Entry r4 = (Map.Entry) r72.next();
        r12[r2] = new zzafs(this, (Comparable) r4.getKey(), r4.getValue());
        this.zzb++;
        r72.remove();
    L5:
        return r0;
    }

    private final int zzm(Comparable r5) {
        int r0 = this.zzb;
        int r1 = r0 - 1;
        int r2 = 0;
        if (r1 < 0) goto L11;
        int r3 = r5.compareTo(((zzafs) this.zza[r1]).zza());
        if (r3 > 0) goto L7;
        if (r3 != 0) goto L11;
        return r1;
    L7:
        return -(r0 + 1);
    L11:
        if (r2 > r1) goto L19;
        int r02 = (r2 + r1) / 2;
        int r32 = r5.compareTo(((zzafs) this.zza[r02]).zza());
        if (r32 < 0) goto L14;
        if (r32 <= 0) goto L17;
        r2 = r02 + 1;
        goto L11
    L17:
        return r02;
    L14:
        r1 = r02 - 1;
        goto L11
    L19:
        return -(r2 + 1);
    }

    private final void zzn() {
        if (this.zzd == true) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    private final SortedMap zzo() {
        zzn();
        if (this.zzc.isEmpty() == false) goto L8;
        if ((this.zzc instanceof TreeMap) == true) goto L8;
        TreeMap r0 = new TreeMap();
        this.zzc = r0;
        this.zzf = r0.descendingMap();
    L8:
        return (SortedMap) this.zzc;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzn();
        if (this.zzb == 0) goto L6;
        this.zza = null;
        this.zzb = 0;
    L6:
        if (this.zzc.isEmpty() == true) goto L9;
        this.zzc.clear();
        return;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object r2) {
        Comparable r22 = (Comparable) r2;
        if (zzm(r22) < 0) goto L5;
        return true;
    L5:
        if (this.zzc.containsKey(r22) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.zze != null) goto L6;
        this.zze = new zzafu(this, null);
    L6:
        return this.zze;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzafv) == false) goto L8;
        zzafv r82 = (zzafv) r8;
        int r1 = size();
        if (r1 != r82.size()) goto L25;
        int r2 = this.zzb;
        if (r2 != r82.zzb) goto L24;
        int r4 = 0;
    L14:
        if (r4 >= r2) goto L19;
        if (zzd(r4).equals(r82.zzd(r4)) == false) goto L17;
        r4 = r4 + 1;
        goto L14
    L17:
        return false;
    L19:
        if (r2 != r1) goto L21;
        return true;
    L21:
        return this.zzc.equals(r82.zzc);
    L24:
        return entrySet().equals(r82.entrySet());
    L25:
        return false;
    L8:
        return super.equals(r8);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object r2) {
        Comparable r22 = (Comparable) r2;
        int r0 = zzm(r22);
        if (r0 < 0) goto L7;
        return ((zzafs) this.zza[r0]).getValue();
    L7:
        return this.zzc.get(r22);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int r0 = this.zzb;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L6;
        r2 = r2 + this.zza[r1].hashCode();
        r1 = r1 + 1;
        goto L3
    L6:
        if (this.zzc.size() > 0) goto L8;
        return r2;
    L8:
        return r2 + this.zzc.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object put(Object r1, Object r2) {
        return zzf((Comparable) r1, r2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object r2) {
        zzn();
        Comparable r22 = (Comparable) r2;
        int r0 = zzm(r22);
        if (r0 < 0) goto L7;
        return zzl(r0);
    L7:
        if (this.zzc.isEmpty() == false) goto L11;
        return null;
    L11:
        return this.zzc.remove(r22);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zzb + this.zzc.size();
    }

    public void zza() {
        if (this.zzd == false) goto L5;
        return;
    L5:
        if (this.zzc.isEmpty() == false) goto L7;
        Map r0 = Collections.EMPTY_MAP;
    L8:
        this.zzc = r0;
        if (this.zzf.isEmpty() == false) goto L11;
        Map r02 = Collections.EMPTY_MAP;
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

    public final boolean zzb() {
        return this.zzd;
    }

    public final int zzc() {
        return this.zzb;
    }

    public final Map.Entry zzd(int r2) {
        if (r2 >= this.zzb) goto L7;
        return (zzafs) this.zza[r2];
    L7:
        throw new ArrayIndexOutOfBoundsException(r2);
    }

    public final Iterable zze() {
        if (this.zzc.isEmpty() == false) goto L7;
        return Collections.EMPTY_SET;
    L7:
        return this.zzc.entrySet();
    }

    public final Object zzf(Comparable r5, Object r6) {
        zzn();
        int r0 = zzm(r5);
        if (r0 >= 0) goto L5;
        zzn();
        if (this.zza != null) goto L9;
        this.zza = new Object[16];
    L9:
        int r02 = -(r0 + 1);
        if (r02 < 16) goto L14;
        return zzo().put(r5, r6);
    L14:
        if (this.zzb != 16) goto L16;
        zzafs r1 = (zzafs) this.zza[15];
        this.zzb = 15;
        zzo().put(r1.zza(), r1.getValue());
    L16:
        Object[] r12 = this.zza;
        int r3 = r12.length;
        System.arraycopy(r12, r02, r12, r02 + 1, 15 - r02);
        this.zza[r02] = new zzafs(this, r5, r6);
        this.zzb++;
        return null;
    L5:
        return ((zzafs) this.zza[r0]).setValue(r6);
    }

    public final /* synthetic */ Object zzg(int r1) {
        return zzl(r1);
    }

    public final /* synthetic */ void zzh() {
        zzn();
    }

    public final /* synthetic */ Object[] zzi() {
        return this.zza;
    }

    public final /* synthetic */ int zzj() {
        return this.zzb;
    }

    public final /* synthetic */ Map zzk() {
        return this.zzc;
    }
}
