package com.google.android.gms.internal.measurement;

import com.android.dx.util.IntList$$ExternalSyntheticBUOutline0;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class zzae implements Iterable, zzao, zzak {
    final SortedMap zza;
    final Map zzb;

    public zzae() {
        this.zza = new TreeMap();
        this.zzb = new TreeMap();
    }

    public zzae(List r3) {
        this();
        if (r3 == null) goto L8;
        int r0 = 0;
    L6:
        if (r0 >= r3.size()) goto L10;
        zzn(r0, (zzao) r3.get(r0));
        r0 = r0 + 1;
        goto L6
    L10:
        return;
    }

    public final boolean equals(Object r7) {
        if (r7 != this) goto L6;
        return true;
    L6:
        if ((r7 instanceof zzae) == true) goto L8;
        return false;
    L8:
        zzae r72 = (zzae) r7;
        if (zzh() == r72.zzh()) goto L11;
        return false;
    L11:
        SortedMap r1 = this.zza;
        if (r1.isEmpty() == true) goto L14;
        int r3 = ((Integer) r1.firstKey()).intValue();
    L17:
        if (r3 > ((Integer) r1.lastKey()).intValue()) goto L22;
        if (zzl(r3).equals(r72.zzl(r3)) == false) goto L20;
        r3 = r3 + 1;
        goto L17
    L20:
        return false;
    L22:
        return true;
    L14:
        return r72.zza.isEmpty();
    }

    public final int hashCode() {
        return this.zza.hashCode() * 31;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzad(this);
    }

    public final String toString() {
        return zzs(",");
    }

    public final List zzb() {
        ArrayList r0 = new ArrayList(zzh());
        int r1 = 0;
    L4:
        if (r1 >= zzh()) goto L6;
        r0.add(zzl(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final String zzc() {
        return zzs(",");
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzcG(String r2, zzg r3, List r4) {
        if ("concat".equals(r2) == true) goto L46;
        if ("every".equals(r2) == true) goto L46;
        if ("filter".equals(r2) == true) goto L46;
        if ("forEach".equals(r2) == true) goto L46;
        if ("indexOf".equals(r2) == true) goto L46;
        if ("join".equals(r2) == true) goto L46;
        if ("lastIndexOf".equals(r2) == true) goto L46;
        if ("map".equals(r2) == true) goto L46;
        if ("pop".equals(r2) == true) goto L46;
        if ("push".equals(r2) == true) goto L46;
        if ("reduce".equals(r2) == true) goto L46;
        if ("reduceRight".equals(r2) == true) goto L46;
        if ("reverse".equals(r2) == true) goto L46;
        if ("shift".equals(r2) == true) goto L46;
        if ("slice".equals(r2) == true) goto L46;
        if ("some".equals(r2) == true) goto L46;
        if ("sort".equals(r2) == true) goto L46;
        if ("splice".equals(r2) == true) goto L46;
        if ("toString".equals(r2) == true) goto L46;
        if ("unshift".equals(r2) == true) goto L46;
        return zzak.zzu(this, new zzas(r2), r3, r4);
    L46:
        return zzba.zza(r2, this, r3, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Double zzd() {
        SortedMap r0 = this.zza;
        if (r0.size() != 1) goto L7;
        return zzl(0).zzd();
    L7:
        if (r0.size() > 0) goto L11;
        return Double.valueOf(0.0d);
    L11:
        return Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Boolean zze() {
        return Boolean.TRUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final Iterator zzf() {
        return new zzac(this, this.zza.keySet().iterator(), this.zzb.keySet().iterator());
    }

    public final Iterator zzg() {
        return this.zza.keySet().iterator();
    }

    public final int zzh() {
        SortedMap r1 = this.zza;
        if (r1.isEmpty() == false) goto L7;
        return 0;
    L7:
        return ((Integer) r1.lastKey()).intValue() + 1;
    }

    public final int zzi() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final boolean zzj(String r2) {
        if ("length".equals(r2) == false) goto L5;
        return true;
    L5:
        if (this.zzb.containsKey(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final zzao zzk(String r3) {
        if ("length".equals(r3) == false) goto L7;
        return new zzah(Double.valueOf(zzh()));
    L7:
        if (zzj(r3) == false) goto L12;
        zzao r2 = (zzao) this.zzb.get(r3);
        if (r2 == null) goto L12;
        return r2;
    L12:
        return zzao.zzf;
    }

    public final zzao zzl(int r2) {
        if (r2 < zzh()) goto L5;
        IntList$$ExternalSyntheticBUOutline0.m("Attempting to get element outside of current array");
        return null;
    L5:
        if (zzo(r2) == false) goto L10;
        zzao r1 = (zzao) this.zza.get(Integer.valueOf(r2));
        if (r1 == null) goto L10;
        return r1;
    L10:
        return zzao.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzak
    public final void zzm(String r1, zzao r2) {
        Map r0 = this.zzb;
        if (r2 != null) goto L6;
        r0.remove(r1);
        return;
    L6:
        r0.put(r1, r2);
    }

    @RequiresNonNull({"elements"})
    public final void zzn(int r2, zzao r3) {
        if (r2 > 32468) goto L13;
        if (r2 < 0) goto L11;
        SortedMap r1 = this.zza;
        if (r3 != null) goto L9;
        r1.remove(Integer.valueOf(r2));
        return;
    L9:
        r1.put(Integer.valueOf(r2), r3);
        return;
    L11:
        StringBuilder r0 = new StringBuilder(String.valueOf(r2).length() + 21);
        r0.append("Out of bounds index: ");
        r0.append(r2);
        throw new IndexOutOfBoundsException(r0.toString());
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Array too large");
    }

    public final boolean zzo(int r3) {
        if (r3 < 0) goto L7;
        SortedMap r2 = this.zza;
        if (r3 > ((Integer) r2.lastKey()).intValue()) goto L7;
        return r2.containsKey(Integer.valueOf(r3));
    L7:
        StringBuilder r1 = new StringBuilder(String.valueOf(r3).length() + 21);
        r1.append("Out of bounds index: ");
        r1.append(r3);
        throw new IndexOutOfBoundsException(r1.toString());
    }

    public final void zzp() {
        this.zza.clear();
    }

    public final void zzq(int r6, zzao r7) {
        if (r6 >= 0) goto L4;
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r6).length() + 21, "Invalid value index: ", r6);
        return;
    L4:
        if (r6 < zzh()) goto L7;
        zzn(r6, r7);
        return;
    L7:
        SortedMap r0 = this.zza;
        int r1 = ((Integer) r0.lastKey()).intValue();
    L8:
        if (r1 < r6) goto L13;
        Integer r2 = Integer.valueOf(r1);
        zzao r3 = (zzao) r0.get(r2);
        if (r3 == null) goto L12;
        zzn(r1 + 1, r3);
        r0.remove(r2);
    L12:
        r1 = r1 - 1;
        goto L8
    L13:
        zzn(r6, r7);
    }

    public final void zzr(int r4) {
        SortedMap r3 = this.zza;
        int r0 = ((Integer) r3.lastKey()).intValue();
        if (r4 > r0) goto L18;
        if (r4 < 0) goto L26;
        r3.remove(Integer.valueOf(r4));
        if (r4 != r0) goto L13;
        int r42 = r4 - 1;
        Integer r02 = Integer.valueOf(r42);
        if (r3.containsKey(r02) == true) goto L24;
        if (r42 < 0) goto L25;
        r3.put(r02, zzao.zzf);
        return;
    L25:
        return;
    L24:
        return;
    L13:
        r4 = r4 + 1;
        if (r4 > ((Integer) r3.lastKey()).intValue()) goto L27;
        Integer r03 = Integer.valueOf(r4);
        zzao r1 = (zzao) r3.get(r03);
        if (r1 == null) goto L13;
        r3.put(Integer.valueOf(r4 - 1), r1);
        r3.remove(r03);
        goto L13
    L27:
        return;
    L26:
        return;
    }

    public final String zzs(String r6) {
        StringBuilder r0 = new StringBuilder();
        if (this.zza.isEmpty() == true) goto L18;
        int r2 = 0;
    L5:
        if (r6 != null) goto L7;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (r2 >= zzh()) goto L16;
        zzao r4 = zzl(r2);
        r0.append(r3);
        if ((r4 instanceof zzat) == true) goto L15;
        if ((r4 instanceof zzam) == true) goto L15;
        r0.append(r4.zzc());
    L15:
        r2 = r2 + 1;
        goto L5
    L16:
        r0.delete(0, r3.length());
        goto L18
    L7:
        r3 = r6;
    L18:
        return r0.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        zzae r0 = new zzae();
        Iterator r4 = this.zza.entrySet().iterator();
    L4:
        if (r4.hasNext() == false) goto L9;
        Map.Entry r1 = (Map.Entry) r4.next();
        boolean r2 = r1.getValue() instanceof zzak;
        SortedMap r3 = r0.zza;
        if (r2 == true) goto L7;
        r3.put((Integer) r1.getKey(), ((zzao) r1.getValue()).zzt());
        goto L4
    L7:
        r3.put((Integer) r1.getKey(), (zzao) r1.getValue());
        goto L4
    L9:
        return r0;
    }
}
