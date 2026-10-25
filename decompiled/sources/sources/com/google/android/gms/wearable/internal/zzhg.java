package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzhg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzhg> CREATOR = null;
    public final int zza;
    public final long zzb;
    public final List zzc;

    static {
        CREATOR = new zzhh();
    }

    public zzhg(int r1, long r2, List r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, this.zza);
        SafeParcelWriter.writeLong(r4, 3, this.zzb);
        SafeParcelWriter.writeTypedList(r4, 4, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
