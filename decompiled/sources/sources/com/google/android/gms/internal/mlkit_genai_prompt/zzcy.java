package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzcy extends AbstractSafeParcelable implements zzv {
    public static final Parcelable.Creator<zzcy> CREATOR = null;
    public static final zzcp zza = null;
    private static final zzcp zzb = null;
    private final int zzc;
    private final String zzd;
    private final boolean zze;
    private final int zzf;
    private final double zzg;
    private final zzml zzh;
    private final long zzi;

    static {
        zzco r0 = zzcp.zzu();
        zzco r1 = zzcp.zzu();
        r1.zza(1);
        zzcp r12 = r1.zzp();
        zzco r3 = zzcp.zzu();
        r3.zza(2);
        zzcp r32 = r3.zzp();
        zzco r5 = zzcp.zzu();
        r5.zza(3);
        zzcp r52 = r5.zzp();
        zzco r7 = zzcp.zzu();
        r7.zza(4);
        r0.zzl(zzml.zzo(r12, r32, r52, r7.zzp()));
        zzcp r02 = r0.zzp();
        zzb = r02;
        zzco r13 = zzcp.zzu();
        zzco r33 = zzcp.zzu();
        r33.zza(1);
        zzcp r2 = r33.zzp();
        zzco r34 = zzcp.zzu();
        r34.zza(2);
        zzcp r35 = r34.zzp();
        zzco r4 = zzcp.zzu();
        r4.zza(3);
        zzcp r42 = r4.zzp();
        zzco r53 = zzcp.zzu();
        r53.zza(4);
        zzcp r54 = r53.zzp();
        zzco r6 = zzcp.zzu();
        r6.zza(5);
        r6.zzf(r02);
        r13.zzl(zzml.zzp(r2, r35, r42, r54, r6.zzp()));
        r13.zzc("The actual value of the argument");
        zza = r13.zzp();
        CREATOR = new zzcz();
    }

    public zzcy(int r1, String r2, boolean r3, int r4, double r5, List r7, long r8) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzg = r5;
        this.zzh = zzml.zzq(r7);
        this.zzi = r8;
    }

    public final String toString() {
        int r0 = this.zzc;
        if (r0 == 0) goto L27;
        if (r0 == 1) goto L25;
        if (r0 == 2) goto L23;
        if (r0 == 3) goto L21;
        if (r0 == 4) goto L19;
        if (r0 == 5) goto L15;
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r0).length() + 18, "Unsupported kind: ", r0);
        return null;
    L15:
        return String.valueOf(this.zzi);
    L19:
        return this.zzh.toString();
    L21:
        return String.valueOf(this.zzg);
    L23:
        return String.valueOf(this.zzf);
    L25:
        return String.valueOf(this.zze);
    L27:
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r6, int r7) {
        int r72 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeInt(r6, 1, this.zzc);
        SafeParcelWriter.writeString(r6, 2, this.zzd, false);
        SafeParcelWriter.writeBoolean(r6, 3, this.zze);
        SafeParcelWriter.writeInt(r6, 4, this.zzf);
        SafeParcelWriter.writeDouble(r6, 5, this.zzg);
        SafeParcelWriter.writeTypedList(r6, 6, this.zzh, false);
        SafeParcelWriter.writeLong(r6, 7, this.zzi);
        SafeParcelWriter.finishObjectHeader(r6, r72);
    }

    public final int zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zzd;
    }

    public final boolean zzc() {
        return this.zze;
    }

    public final int zzd() {
        return this.zzf;
    }

    public final double zze() {
        return this.zzg;
    }

    public final zzml zzf() {
        return this.zzh;
    }

    public final long zzg() {
        return this.zzi;
    }
}
