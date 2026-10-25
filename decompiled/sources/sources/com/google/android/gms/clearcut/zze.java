package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zze extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zze> CREATOR = null;
    public final zzha zzaa;
    public zzr zzag;
    public byte[] zzah;
    private int[] zzai;
    private String[] zzaj;
    private int[] zzak;
    private byte[][] zzal;
    private ExperimentTokens[] zzam;
    private boolean zzz;

    static {
        CREATOR = new zzf();
    }

    public zze(zzr r1, zzha r2, ClearcutLogger.zzb r3, ClearcutLogger.zzb r4, int[] r5, String[] r6, int[] r7, byte[][] r8, ExperimentTokens[] r9, boolean r10) {
        this.zzag = r1;
        this.zzaa = r2;
        this.zzai = r5;
        this.zzaj = null;
        this.zzak = r7;
        this.zzal = null;
        this.zzam = null;
        this.zzz = r10;
    }

    public zze(zzr r1, byte[] r2, int[] r3, String[] r4, int[] r5, byte[][] r6, boolean r7, ExperimentTokens[] r8) {
        this.zzag = r1;
        this.zzah = r2;
        this.zzai = r3;
        this.zzaj = r4;
        this.zzaa = null;
        this.zzak = r5;
        this.zzal = r6;
        this.zzam = r8;
        this.zzz = r7;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zze) == false) goto L30;
        zze r52 = (zze) r5;
        if (Objects.equal(this.zzag, r52.zzag) == false) goto L30;
        if (Arrays.equals(this.zzah, r52.zzah) == false) goto L30;
        if (Arrays.equals(this.zzai, r52.zzai) == false) goto L30;
        if (Arrays.equals(this.zzaj, r52.zzaj) == false) goto L30;
        if (Objects.equal(this.zzaa, r52.zzaa) == false) goto L30;
        if (Objects.equal(null, null) == false) goto L30;
        if (Objects.equal(null, null) == false) goto L30;
        if (Arrays.equals(this.zzak, r52.zzak) == false) goto L30;
        if (Arrays.deepEquals(this.zzal, r52.zzal) == false) goto L30;
        if (Arrays.equals(this.zzam, r52.zzam) == false) goto L30;
        if (this.zzz != r52.zzz) goto L30;
        return true;
    L30:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzag, this.zzah, this.zzai, this.zzaj, this.zzaa, null, null, this.zzak, this.zzal, this.zzam, Boolean.valueOf(this.zzz)});
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("LogEventParcelable[");
        r0.append(this.zzag);
        r0.append(", LogEventBytes: ");
        byte[] r1 = this.zzah;
        if (r1 != null) goto L5;
        String r12 = null;
    L6:
        r0.append(r12);
        r0.append(", TestCodes: ");
        r0.append(Arrays.toString(this.zzai));
        r0.append(", MendelPackages: ");
        r0.append(Arrays.toString(this.zzaj));
        r0.append(", LogEvent: ");
        r0.append(this.zzaa);
        r0.append(", ExtensionProducer: null, VeProducer: null, ExperimentIDs: ");
        r0.append(Arrays.toString(this.zzak));
        r0.append(", ExperimentTokens: ");
        r0.append(Arrays.toString(this.zzal));
        r0.append(", ExperimentTokensParcelables: ");
        r0.append(Arrays.toString(this.zzam));
        r0.append(", AddPhenotypeExperimentTokens: ");
        r0.append(this.zzz);
        r0.append("]");
        return r0.toString();
    L5:
        r12 = new String(r1);
        goto L6
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, this.zzag, r6, false);
        SafeParcelWriter.writeByteArray(r5, 3, this.zzah, false);
        SafeParcelWriter.writeIntArray(r5, 4, this.zzai, false);
        SafeParcelWriter.writeStringArray(r5, 5, this.zzaj, false);
        SafeParcelWriter.writeIntArray(r5, 6, this.zzak, false);
        SafeParcelWriter.writeByteArrayArray(r5, 7, this.zzal, false);
        SafeParcelWriter.writeBoolean(r5, 8, this.zzz);
        SafeParcelWriter.writeTypedArray(r5, 9, this.zzam, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
