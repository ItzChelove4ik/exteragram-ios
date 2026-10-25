package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public final class zzjh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzjh> CREATOR = null;
    public final String zza;
    public final byte[] zzb;
    public final String zzc;
    public final zzjf[] zzd;
    public final Map zze;
    public final boolean zzf;
    public final long zzg;

    static {
        CREATOR = new zzji();
    }

    public zzjh(String r1, String r2, zzjf[] r3, boolean r4, byte[] r5, long r6) {
        this.zza = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zzf = r4;
        this.zzb = r5;
        this.zzg = r6;
        this.zze = new TreeMap();
        int r12 = r3.length;
        int r22 = 0;
    L3:
        if (r22 >= r12) goto L5;
        zzjf r42 = r3[r22];
        this.zze.put(Integer.valueOf(r42.zza), r42);
        r22 = r22 + 1;
        goto L3
    }

    public final boolean equals(Object r5) {
        if ((r5 instanceof zzjh) == false) goto L18;
        zzjh r52 = (zzjh) r5;
        if (zzkl.zza(this.zza, r52.zza) == false) goto L18;
        if (zzkl.zza(this.zzc, r52.zzc) == false) goto L18;
        if (this.zze.equals(r52.zze) == false) goto L18;
        if (this.zzf != r52.zzf) goto L18;
        if (Arrays.equals(this.zzb, r52.zzb) == false) goto L18;
        if (this.zzg != r52.zzg) goto L18;
        return true;
    L18:
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzc, this.zze, Boolean.valueOf(this.zzf), this.zzb, Long.valueOf(this.zzg)});
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("Configurations('");
        r0.append(this.zza);
        r0.append("', '");
        r0.append(this.zzc);
        r0.append("', (");
        Iterator r1 = this.zze.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r0.append((zzjf) r1.next());
        r0.append(", ");
        goto L4
    L6:
        r0.append("), ");
        r0.append(this.zzf);
        r0.append(", ");
        byte[] r12 = this.zzb;
        if (r12 != null) goto L9;
        String r13 = "null";
    L10:
        r0.append(r13);
        r0.append(", ");
        r0.append(this.zzg);
        r0.append(')');
        return r0.toString();
    L9:
        r13 = Base64.encodeToString(r12, 3);
        goto L10
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        String r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, r0, false);
        SafeParcelWriter.writeString(r5, 3, this.zzc, false);
        SafeParcelWriter.writeTypedArray(r5, 4, this.zzd, r6, false);
        SafeParcelWriter.writeBoolean(r5, 5, this.zzf);
        SafeParcelWriter.writeByteArray(r5, 6, this.zzb, false);
        SafeParcelWriter.writeLong(r5, 7, this.zzg);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
