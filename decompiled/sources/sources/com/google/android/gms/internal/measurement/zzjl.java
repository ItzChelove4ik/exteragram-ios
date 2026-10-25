package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzjl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzjl> CREATOR = null;
    public static final byte[][] zza = null;
    public final String zzb;
    public final byte[] zzc;
    public final byte[][] zzd;
    public final byte[][] zze;
    public final byte[][] zzf;
    public final byte[][] zzg;
    public final int[] zzh;
    public final byte[][] zzi;
    public final int[] zzj;
    final byte[][] zzk;

    static {
        byte[][] r4 = new byte[0][];
        zza = r4;
        CREATOR = new zzjm();
        new zzjl(_UrlKt.FRAGMENT_ENCODE_SET, null, r4, r4, r4, r4, null, null, null, null);
    }

    public zzjl(String r1, byte[] r2, byte[][] r3, byte[][] r4, byte[][] r5, byte[][] r6, int[] r7, byte[][] r8, int[] r9, byte[][] r10) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
        this.zzh = r7;
        this.zzi = r8;
        this.zzj = r9;
        this.zzk = r10;
    }

    private static void zza(StringBuilder r4, String r5, byte[][] r6) {
        r4.append(r5);
        r4.append("=");
        if (r6 != null) goto L6;
        r4.append("null");
        return;
    L6:
        r4.append("(");
        boolean r52 = true;
        int r1 = 0;
    L8:
        if (r1 >= r6.length) goto L13;
        byte[] r2 = r6[r1];
        if (r52 == true) goto L12;
        r4.append(", ");
    L12:
        r4.append("'");
        Preconditions.checkNotNull(r2);
        r4.append(Base64.encodeToString(r2, 3));
        r4.append("'");
        r1 = r1 + 1;
        r52 = false;
        goto L8
    L13:
        r4.append(")");
    }

    private final Set zzb() {
        ArrayList r0 = new ArrayList();
        byte[][] r1 = this.zzi;
        if (r1 == null) goto L5;
        Collections.addAll(r0, r1);
    L5:
        byte[] r2 = this.zzc;
        if (r2 == null) goto L9;
        r0.add(r2);
    L9:
        return zzc((byte[][]) r0.toArray(new byte[0][]));
    }

    private static Set zzc(byte[][] r5) {
        if (r5 == null) goto L11;
        int r0 = r5.length;
        if (r0 == 0) goto L11;
        HashSet r1 = Sets.newHashSetWithExpectedSize(r0);
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        byte[] r3 = r5[r2];
        Preconditions.checkNotNull(r3);
        r1.add(Base64.encodeToString(r3, 3));
        r2 = r2 + 1;
        goto L7
    L9:
        return r1;
    L11:
        return Collections.EMPTY_SET;
    }

    private static Set zzd(int[] r4) {
        if (r4 == null) goto L11;
        int r0 = r4.length;
        if (r0 == 0) goto L11;
        HashSet r1 = Sets.newHashSetWithExpectedSize(r0);
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        r1.add(Integer.valueOf(r4[r2]));
        r2 = r2 + 1;
        goto L7
    L9:
        return r1;
    L11:
        return Collections.EMPTY_SET;
    }

    private static List zze(int[] r5) {
        if (r5 == null) goto L4;
        ArrayList r1 = new ArrayList(r5.length >> 1);
        int r0 = 0;
    L7:
        if (r0 >= r5.length) goto L9;
        r1.add(new zzju(r5[r0], r5[r0 + 1]));
        r0 = r0 + 2;
        goto L7
    L9:
        Collections.sort(r1);
        return r1;
    L4:
        return Collections.EMPTY_LIST;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzjl) == false) goto L24;
        zzjl r42 = (zzjl) r4;
        if (zzkl.zza(this.zzb, r42.zzb) == false) goto L24;
        if (zzkl.zza(zzb(), r42.zzb()) == false) goto L24;
        if (zzkl.zza(zzc(this.zzd), zzc(r42.zzd)) == false) goto L24;
        if (zzkl.zza(zzc(this.zze), zzc(r42.zze)) == false) goto L24;
        if (zzkl.zza(zzc(this.zzf), zzc(r42.zzf)) == false) goto L24;
        if (zzkl.zza(zzc(this.zzg), zzc(r42.zzg)) == false) goto L24;
        if (zzkl.zza(zzd(this.zzh), zzd(r42.zzh)) == false) goto L24;
        if (zzkl.zza(zze(this.zzj), zze(r42.zzj)) == false) goto L24;
        if (zzkl.zza(zzc(this.zzk), zzc(r42.zzk)) == false) goto L24;
        return true;
    L24:
        return false;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("ExperimentTokens");
        r0.append("(");
        String r1 = this.zzb;
        if (r1 != null) goto L5;
        String r12 = "null";
    L6:
        r0.append(r12);
        byte[] r13 = this.zzc;
        r0.append(", direct==");
        if (r13 != null) goto L9;
        r0.append("null");
    L10:
        r0.append(", ");
        zza(r0, "GAIA=", this.zzd);
        r0.append(", ");
        zza(r0, "PSEUDO=", this.zze);
        r0.append(", ");
        zza(r0, "ALWAYS=", this.zzf);
        r0.append(", ");
        zza(r0, "OTHER=", this.zzg);
        r0.append(", weak=");
        r0.append(Arrays.toString(this.zzh));
        r0.append(", ");
        zza(r0, "directs=", this.zzi);
        r0.append(", genDims=");
        r0.append(Arrays.toString(zze(this.zzj).toArray()));
        r0.append(", ");
        zza(r0, "external=", this.zzk);
        r0.append(")");
        return r0.toString();
    L9:
        r0.append("'");
        r0.append(Base64.encodeToString(r13, 3));
        r0.append("'");
        goto L10
    L5:
        StringBuilder r5 = new StringBuilder(r1.length() + 2);
        r5.append("'");
        r5.append(r1);
        r5.append("'");
        r12 = r5.toString();
        goto L6
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zzb;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, r52, false);
        SafeParcelWriter.writeByteArray(r4, 3, this.zzc, false);
        SafeParcelWriter.writeByteArrayArray(r4, 4, this.zzd, false);
        SafeParcelWriter.writeByteArrayArray(r4, 5, this.zze, false);
        SafeParcelWriter.writeByteArrayArray(r4, 6, this.zzf, false);
        SafeParcelWriter.writeByteArrayArray(r4, 7, this.zzg, false);
        SafeParcelWriter.writeIntArray(r4, 8, this.zzh, false);
        SafeParcelWriter.writeByteArrayArray(r4, 9, this.zzi, false);
        SafeParcelWriter.writeIntArray(r4, 10, this.zzj, false);
        SafeParcelWriter.writeByteArrayArray(r4, 11, this.zzk, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
