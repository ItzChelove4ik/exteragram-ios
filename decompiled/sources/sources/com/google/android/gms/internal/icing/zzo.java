package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzo extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zzo> CREATOR = null;
    public Status zza;
    public List<zzx> zzb;

    @Deprecated
    public String[] zzc;

    static {
        CREATOR = new zzp();
    }

    public zzo(Status r1, List<zzx> r2, String[] r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 1, this.zza, r6, false);
        SafeParcelWriter.writeTypedList(r5, 2, this.zzb, false);
        SafeParcelWriter.writeStringArray(r5, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
