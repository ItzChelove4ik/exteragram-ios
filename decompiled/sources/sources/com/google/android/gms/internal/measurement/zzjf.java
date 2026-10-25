package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public final class zzjf extends AbstractSafeParcelable implements Comparable<zzjf> {
    public static final Parcelable.Creator<zzjf> CREATOR = null;
    public final int zza;
    public final zzjo[] zzb;
    public final String[] zzc;
    public final Map zzd;

    static {
        CREATOR = new zzjg();
    }

    public zzjf(int r5, zzjo[] r6, String[] r7) {
        this.zza = r5;
        this.zzb = r6;
        this.zzd = new TreeMap();
        int r52 = r6.length;
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L5;
        zzjo r1 = r6[r0];
        this.zzd.put(r1.zza, r1);
        r0 = r0 + 1;
        goto L3
    L5:
        this.zzc = r7;
        if (r7 == null) goto L10;
        Arrays.sort(r7);
        return;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzjf r1) {
        int r12 = r1.zza;
        return this.zza - r12;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzjf) == false) goto L12;
        zzjf r42 = (zzjf) r4;
        if (this.zza != r42.zza) goto L12;
        if (zzkl.zza(this.zzd, r42.zzd) == false) goto L12;
        if (Arrays.equals(this.zzc, r42.zzc) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("Configuration(");
        r0.append(this.zza);
        r0.append(", (");
        Iterator r1 = this.zzd.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r0.append((zzjo) r1.next());
        r0.append(", ");
        goto L4
    L6:
        r0.append("), (");
        String[] r4 = this.zzc;
        if (r4 == null) goto L12;
        int r12 = 0;
    L10:
        if (r12 >= r4.length) goto L13;
        r0.append(r4[r12]);
        r0.append(", ");
        r12 = r12 + 1;
    L13:
        r0.append("))");
        return r0.toString();
    L12:
        r0.append("null");
        goto L13
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 2, r0);
        SafeParcelWriter.writeTypedArray(r5, 3, this.zzb, r6, false);
        SafeParcelWriter.writeStringArray(r5, 4, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
