package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzn> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final int zzf;
    private final int zzg;
    private final String zzh;

    static {
        CREATOR = new zzo();
    }

    public zzn(String r1, String r2, int r3, int r4, int r5, int r6, int r7, String r8) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
    }

    public static zzn zza(String r9, String r10, int r11, int r12, int r13, int r14, int r15, String r16) {
        return new zzn(r9, r10, r11, r12, r13, r14, r15, r16);
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzn) == true) goto L8;
        return false;
    L8:
        zzn r52 = (zzn) r5;
        if (this.zzc == r52.zzc) goto L11;
    L19:
        return false;
    L11:
        if (this.zzd != r52.zzd) goto L19;
        if (Objects.equals(this.zza, r52.zza) == false) goto L19;
        if (this.zze != r52.zze) goto L19;
        if (this.zzf != r52.zzf) goto L19;
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Integer.valueOf(this.zze), Integer.valueOf(this.zzf)});
    }

    public final String toString() {
        zzkx r0 = zzky.zza(this);
        r0.zza("name", this.zza);
        r0.zza("modelName", this.zzb);
        r0.zzb("type", this.zzc);
        r0.zzb("variant", this.zzd);
        r0.zzb("id", this.zze);
        r0.zzb("version", this.zzf);
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        SafeParcelWriter.writeString(r4, 2, this.zzb, false);
        SafeParcelWriter.writeInt(r4, 3, this.zzc);
        SafeParcelWriter.writeInt(r4, 4, this.zzd);
        SafeParcelWriter.writeInt(r4, 5, this.zze);
        SafeParcelWriter.writeInt(r4, 6, this.zzf);
        SafeParcelWriter.writeInt(r4, 7, this.zzg);
        SafeParcelWriter.writeString(r4, 8, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final int zzd() {
        return this.zzc;
    }

    public final int zze() {
        return this.zzd;
    }

    public final int zzf() {
        return this.zze;
    }

    public final int zzg() {
        return this.zzf;
    }

    public final int zzh() {
        return this.zzg;
    }

    public final String zzi() {
        return this.zzh;
    }
}
