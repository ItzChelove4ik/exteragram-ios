package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzjo extends AbstractSafeParcelable implements Comparable<zzjo> {
    public static final Parcelable.Creator<zzjo> CREATOR = null;
    public final String zza;
    final long zzb;
    final boolean zzc;
    final double zzd;
    final String zze;
    final byte[] zzf;
    public final int zzg;
    public final int zzh;
    private final int zzi;

    static {
        CREATOR = new zzjp();
    }

    public zzjo(String r1, long r2, boolean r4, double r5, String r7, byte[] r8, int r9, int r10, int r11) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r7;
        this.zzf = r8;
        this.zzg = r9;
        this.zzh = r10;
        this.zzi = r11;
    }

    private static int zzg(int r0, int r1) {
        if (r0 >= r1) goto L5;
        return -1;
    L5:
        if (r0 == r1) goto L8;
        return 1;
    L8:
        return 0;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(zzjo r7) {
        zzjo r72 = r7;
        String r0 = r72.zza;
        int r02 = this.zza.compareTo(r0);
        if (r02 == 0) goto L5;
        return r02;
    L5:
        int r03 = this.zzg;
        int r1 = zzg(r03, r72.zzg);
        if (r1 == 0) goto L8;
        return r1;
    L8:
        int r2 = 0;
        if (r03 != 1) goto L11;
        long r4 = this.zzb;
        long r6 = r72.zzb;
        if (r4 >= r6) goto L55;
        return -1;
    L55:
        if (r4 != r6) goto L57;
        return 0;
    L57:
        return 1;
    L11:
        if (r03 != 2) goto L13;
        boolean r62 = this.zzc;
        if (r62 != r72.zzc) goto L49;
        return 0;
    L49:
        if (r62 == false) goto L51;
        return 1;
    L51:
        return -1;
    L13:
        if (r03 == 3) goto L45;
        if (r03 != 4) goto L17;
        String r63 = this.zze;
        String r73 = r72.zze;
        if (r63 != r73) goto L38;
        return 0;
    L38:
        if (r63 != null) goto L40;
        return -1;
    L40:
        if (r73 != null) goto L43;
        return 1;
    L43:
        return r63.compareTo(r73);
    L17:
        if (r03 != 5) goto L33;
        byte[] r64 = this.zzf;
        byte[] r74 = r72.zzf;
        if (r64 != r74) goto L21;
        return 0;
    L21:
        if (r64 != null) goto L23;
        return -1;
    L23:
        if (r74 != null) goto L25;
        return 1;
    L25:
        int r04 = r74.length;
        int r12 = r64.length;
        if (r2 >= Math.min(r12, r04)) goto L32;
        int r05 = r64[r2] - r74[r2];
        if (r05 != 0) goto L29;
        r2 = r2 + 1;
        goto L25
    L29:
        return r05;
    L32:
        return zzg(r12, r04);
    L33:
        StringBuilder r13 = new StringBuilder(String.valueOf(r03).length() + 20);
        r13.append("Invalid enum value: ");
        r13.append(r03);
        throw new AssertionError(r13.toString());
    L45:
        return Double.compare(this.zzd, r72.zzd);
    }

    public final boolean equals(Object r6) {
        if ((r6 instanceof zzjo) == false) goto L41;
        zzjo r62 = (zzjo) r6;
        if (zzkl.zza(this.zza, r62.zza) == false) goto L41;
        int r0 = this.zzg;
        if (r0 != r62.zzg) goto L41;
        if (this.zzh != r62.zzh) goto L41;
        if (this.zzi != r62.zzi) goto L41;
        if (r0 == 1) goto L38;
        if (r0 == 2) goto L34;
        if (r0 == 3) goto L30;
        if (r0 == 4) goto L28;
        if (r0 == 5) goto L24;
        StringBuilder r1 = new StringBuilder(String.valueOf(r0).length() + 20);
        r1.append("Invalid enum value: ");
        r1.append(r0);
        throw new AssertionError(r1.toString());
    L24:
        return Arrays.equals(this.zzf, r62.zzf);
    L28:
        return zzkl.zza(this.zze, r62.zze);
    L30:
        if (this.zzd == r62.zzd) goto L32;
        return false;
    L32:
        return true;
    L34:
        if (this.zzc == r62.zzc) goto L36;
        return false;
    L36:
        return true;
    L38:
        if (this.zzb == r62.zzb) goto L40;
        return false;
    L40:
        return true;
    L41:
        return false;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder();
        zzf(r0);
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r8, int r9) {
        String r92 = this.zza;
        boolean r0 = zzjp.zzb(r92);
        int r1 = SafeParcelWriter.beginObjectHeader(r8);
        if (r0 == true) goto L5;
        SafeParcelWriter.writeString(r8, 2, r92, false);
    L5:
        long r3 = this.zzb;
        if (r3 == 0) goto L9;
        SafeParcelWriter.writeLong(r8, 3, r3);
    L9:
        if (this.zzc == false) goto L11;
        SafeParcelWriter.writeBoolean(r8, 4, true);
    L11:
        double r32 = this.zzd;
        if (r32 == 0.0d) goto L14;
        SafeParcelWriter.writeDouble(r8, 5, r32);
    L14:
        String r93 = this.zze;
        if (zzjp.zzb(r93) == true) goto L17;
        SafeParcelWriter.writeString(r8, 6, r93, false);
    L17:
        byte[] r94 = this.zzf;
        if (zzjp.zzb(r94) == true) goto L20;
        SafeParcelWriter.writeByteArray(r8, 7, r94, false);
    L20:
        int r95 = this.zzg;
        if (zzjp.zza(r95) == true) goto L23;
        SafeParcelWriter.writeInt(r8, 8, r95);
    L23:
        int r96 = this.zzh;
        if (zzjp.zza(r96) == true) goto L26;
        SafeParcelWriter.writeInt(r8, 9, r96);
    L26:
        int r7 = this.zzi;
        if (zzjp.zza(r7) == true) goto L29;
        SafeParcelWriter.writeInt(r8, 10, r7);
    L29:
        SafeParcelWriter.finishObjectHeader(r8, r1);
    }

    public final long zza() {
        if (this.zzg == 1) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Not a long type");
        return 0;
    L5:
        return this.zzb;
    }

    public final boolean zzb() {
        if (this.zzg == 2) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Not a boolean type");
        return false;
    L5:
        return this.zzc;
    }

    public final double zzc() {
        if (this.zzg == 3) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Not a double type");
        return 0.0d;
    L5:
        return this.zzd;
    }

    public final String zzd() {
        if (this.zzg == 4) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Not a String type");
        return null;
    L5:
        return (String) Preconditions.checkNotNull(this.zze);
    }

    public final byte[] zze() {
        if (this.zzg == 5) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Not a bytes type");
        return null;
    L5:
        return (byte[]) Preconditions.checkNotNull(this.zzf);
    }

    public final void zzf(StringBuilder r7) {
        r7.append("Flag(");
        String r0 = this.zza;
        r7.append(r0);
        r7.append(", ");
        int r2 = this.zzg;
        if (r2 != 1) goto L5;
        r7.append(this.zzb);
    L19:
        r7.append(", ");
        r7.append(r2);
        r7.append(", ");
        r7.append(this.zzh);
        r7.append(", ");
        r7.append(this.zzi);
        r7.append(")");
        return;
    L5:
        if (r2 != 2) goto L7;
        r7.append(this.zzc);
        goto L19
    L7:
        if (r2 != 3) goto L9;
        r7.append(this.zzd);
        goto L19
    L9:
        if (r2 != 4) goto L11;
        r7.append("'");
        r7.append((String) Preconditions.checkNotNull(this.zze));
        r7.append("'");
        goto L19
    L11:
        if (r2 != 5) goto L13;
        r7.append("'");
        r7.append(Base64.encodeToString((byte[]) Preconditions.checkNotNull(this.zzf), 3));
        r7.append("'");
        goto L19
    L13:
        int r72 = String.valueOf(r0).length();
        StringBuilder r3 = new StringBuilder((r72 + 16) + String.valueOf(r2).length());
        r3.append("Invalid type: ");
        r3.append(r0);
        r3.append(", ");
        r3.append(r2);
        throw new AssertionError(r3.toString());
    }
}
