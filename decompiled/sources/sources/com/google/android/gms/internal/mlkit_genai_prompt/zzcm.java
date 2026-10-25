package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzcm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcm> CREATOR = null;
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final float zzd;
    private final float zze;
    private final float zzf;

    static {
        CREATOR = new zzcn();
    }

    public zzcm(int r1, int r2, int r3, float r4, float r5, float r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzcm) == false) goto L20;
        zzcm r52 = (zzcm) r5;
        if (this.zza != r52.zza) goto L20;
        if (this.zzb != r52.zzb) goto L20;
        if (this.zzc != r52.zzc) goto L20;
        if (Float.compare(this.zzd, r52.zzd) != 0) goto L20;
        if (Float.compare(this.zze, r52.zze) != 0) goto L20;
        if (Float.compare(this.zzf, r52.zzf) != 0) goto L20;
        return true;
    L20:
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Float.valueOf(this.zzd), Float.valueOf(this.zze), Float.valueOf(this.zzf)});
    }

    public final String toString() {
        int r0 = this.zza;
        int r1 = String.valueOf(r0).length();
        int r2 = this.zzb;
        int r3 = String.valueOf(r2).length();
        int r4 = this.zzc;
        int r5 = String.valueOf(r4).length();
        float r6 = this.zzd;
        int r7 = String.valueOf(r6).length();
        float r8 = this.zze;
        int r9 = String.valueOf(r8).length();
        float r11 = this.zzf;
        int r12 = ((((((((r1 + 50) + r3) + 18) + r5) + 16) + r7) + 12) + r9) + 18;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r11).length()) + 1);
        r32.append("QuotaExceededErrorDetails {quotaType=");
        r32.append(r0);
        r32.append(", quotaScope=");
        r32.append(r2);
        r32.append(", quotaCheckStage=");
        r32.append(r4);
        r32.append(", periodSeconds=");
        r32.append(r6);
        r32.append(", costLimit=");
        r32.append(r8);
        r32.append(", tryAgainSeconds=");
        r32.append(r11);
        r32.append("}");
        return r32.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, this.zza);
        SafeParcelWriter.writeInt(r3, 2, this.zzb);
        SafeParcelWriter.writeInt(r3, 3, this.zzc);
        SafeParcelWriter.writeFloat(r3, 4, this.zzd);
        SafeParcelWriter.writeFloat(r3, 5, this.zze);
        SafeParcelWriter.writeFloat(r3, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    public final float zza() {
        return this.zzf;
    }
}
