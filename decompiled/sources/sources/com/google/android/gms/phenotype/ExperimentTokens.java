package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class ExperimentTokens extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ExperimentTokens> CREATOR = null;
    private static final zza zzaa = null;
    private static final byte[][] zzn = null;
    private static final ExperimentTokens zzo = null;
    private static final zza zzx = null;
    private static final zza zzy = null;
    private static final zza zzz = null;
    private final String zzp;
    private final byte[] zzq;
    private final byte[][] zzr;
    private final byte[][] zzs;
    private final byte[][] zzt;
    private final byte[][] zzu;
    private final int[] zzv;
    private final byte[][] zzw;

    public interface zza {
    }

    static {
        CREATOR = new zzh();
        byte[][] r4 = new byte[0][];
        zzn = r4;
        zzo = new ExperimentTokens(_UrlKt.FRAGMENT_ENCODE_SET, null, r4, r4, r4, r4, null, null);
        zzx = new zzd();
        zzy = new zze();
        zzz = new zzf();
        zzaa = new zzg();
    }

    public ExperimentTokens(String r1, byte[] r2, byte[][] r3, byte[][] r4, byte[][] r5, byte[][] r6, int[] r7, byte[][] r8) {
        this.zzp = r1;
        this.zzq = r2;
        this.zzr = r3;
        this.zzs = r4;
        this.zzt = r5;
        this.zzu = r6;
        this.zzv = r7;
        this.zzw = r8;
    }

    private static List<Integer> zza(int[] r4) {
        if (r4 == null) goto L4;
        ArrayList r0 = new ArrayList(r4.length);
        int r1 = r4.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L8;
        r0.add(Integer.valueOf(r4[r2]));
        r2 = r2 + 1;
        goto L6
    L8:
        Collections.sort(r0);
        return r0;
    L4:
        return Collections.EMPTY_LIST;
    }

    private static List<String> zza(byte[][] r5) {
        if (r5 == null) goto L4;
        ArrayList r0 = new ArrayList(r5.length);
        int r1 = r5.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L8;
        r0.add(Base64.encodeToString(r5[r2], 3));
        r2 = r2 + 1;
        goto L6
    L8:
        Collections.sort(r0);
        return r0;
    L4:
        return Collections.EMPTY_LIST;
    }

    private static void zza(StringBuilder r5, String r6, byte[][] r7) {
        r5.append(r6);
        r5.append("=");
        if (r7 != null) goto L7;
        String r62 = "null";
    L5:
        r5.append(r62);
        return;
    L7:
        r5.append("(");
        int r63 = r7.length;
        boolean r1 = true;
        int r2 = 0;
    L8:
        if (r2 >= r63) goto L13;
        byte[] r3 = r7[r2];
        if (r1 == true) goto L12;
        r5.append(", ");
    L12:
        r5.append("'");
        r5.append(Base64.encodeToString(r3, 3));
        r5.append("'");
        r2 = r2 + 1;
        r1 = false;
        goto L8
    L13:
        r62 = ")";
        goto L5
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof ExperimentTokens) == false) goto L22;
        ExperimentTokens r42 = (ExperimentTokens) r4;
        if (zzn.equals(this.zzp, r42.zzp) == false) goto L22;
        if (Arrays.equals(this.zzq, r42.zzq) == false) goto L22;
        if (zzn.equals(zza(this.zzr), zza(r42.zzr)) == false) goto L22;
        if (zzn.equals(zza(this.zzs), zza(r42.zzs)) == false) goto L22;
        if (zzn.equals(zza(this.zzt), zza(r42.zzt)) == false) goto L22;
        if (zzn.equals(zza(this.zzu), zza(r42.zzu)) == false) goto L22;
        if (zzn.equals(zza(this.zzv), zza(r42.zzv)) == false) goto L22;
        if (zzn.equals(zza(this.zzw), zza(r42.zzw)) == false) goto L22;
        return true;
    L22:
        return false;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("ExperimentTokens");
        r0.append("(");
        String r2 = this.zzp;
        if (r2 != null) goto L5;
        String r22 = "null";
    L6:
        r0.append(r22);
        r0.append(", direct=");
        byte[] r23 = this.zzq;
        if (r23 != null) goto L9;
        r0.append("null");
    L10:
        r0.append(", ");
        zza(r0, "GAIA", this.zzr);
        r0.append(", ");
        zza(r0, "PSEUDO", this.zzs);
        r0.append(", ");
        zza(r0, "ALWAYS", this.zzt);
        r0.append(", ");
        zza(r0, "OTHER", this.zzu);
        r0.append(", ");
        int[] r4 = this.zzv;
        r0.append("weak");
        r0.append("=");
        if (r4 != null) goto L13;
        r0.append("null");
    L20:
        r0.append(", ");
        zza(r0, "directs", this.zzw);
        r0.append(")");
        return r0.toString();
    L13:
        r0.append("(");
        int r1 = r4.length;
        boolean r6 = true;
        int r7 = 0;
    L14:
        if (r7 >= r1) goto L19;
        int r8 = r4[r7];
        if (r6 == true) goto L18;
        r0.append(", ");
    L18:
        r0.append(r8);
        r7 = r7 + 1;
        r6 = false;
        goto L14
    L19:
        r0.append(")");
        goto L20
    L9:
        r0.append("'");
        r0.append(Base64.encodeToString(r23, 3));
        r0.append("'");
        goto L10
    L5:
        StringBuilder r62 = new StringBuilder(String.valueOf(r2).length() + 2);
        r62.append("'");
        r62.append(r2);
        r62.append("'");
        r22 = r62.toString();
        goto L6
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zzp, false);
        SafeParcelWriter.writeByteArray(r4, 3, this.zzq, false);
        SafeParcelWriter.writeByteArrayArray(r4, 4, this.zzr, false);
        SafeParcelWriter.writeByteArrayArray(r4, 5, this.zzs, false);
        SafeParcelWriter.writeByteArrayArray(r4, 6, this.zzt, false);
        SafeParcelWriter.writeByteArrayArray(r4, 7, this.zzu, false);
        SafeParcelWriter.writeIntArray(r4, 8, this.zzv, false);
        SafeParcelWriter.writeByteArrayArray(r4, 9, this.zzw, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
