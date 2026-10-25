package com.google.android.gms.cloudmessaging;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class RegisterRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RegisterRequest> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private int zzf;
    private final String zzg;

    static {
        CREATOR = new zzx();
    }

    public RegisterRequest(String r1, String r2, String r3, String r4, String r5, int r6, String r7) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
    }

    public RegisterRequest(String r1, String r2, String r3, String r4, String r5, String r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzg = r6;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        SafeParcelWriter.writeString(r4, 2, this.zzb, false);
        SafeParcelWriter.writeString(r4, 3, this.zzc, false);
        SafeParcelWriter.writeString(r4, 4, this.zzd, false);
        SafeParcelWriter.writeString(r4, 5, this.zze, false);
        SafeParcelWriter.writeInt(r4, 6, this.zzf);
        SafeParcelWriter.writeString(r4, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }

    public final void zza(int r1) {
        this.zzf = r1;
    }
}
