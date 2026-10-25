package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.vision.Frame;

/* loaded from: classes5.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = null;
    public int zza;
    public int zzb;
    public int zzc;
    public long zzd;
    public int zze;

    static {
        CREATOR = new zzv();
    }

    public zzs() {
    }

    public zzs(int r1, int r2, int r3, long r4, int r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r6;
    }

    public static zzs zza(Frame r3) {
        zzs r0 = new zzs();
        r0.zza = r3.getMetadata().getWidth();
        r0.zzb = r3.getMetadata().getHeight();
        r0.zze = r3.getMetadata().getRotation();
        r0.zzc = r3.getMetadata().getId();
        r0.zzd = r3.getMetadata().getTimestampMillis();
        return r0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, this.zza);
        SafeParcelWriter.writeInt(r4, 3, this.zzb);
        SafeParcelWriter.writeInt(r4, 4, this.zzc);
        SafeParcelWriter.writeLong(r4, 5, this.zzd);
        SafeParcelWriter.writeInt(r4, 6, this.zze);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
