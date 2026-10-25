package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzfw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfw> CREATOR = null;
    private final boolean zza;
    private final boolean zzb;
    private final int zzc;

    static {
        CREATOR = new zzfx();
    }

    public zzfw(boolean r1, boolean r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeBoolean(r3, 2, this.zza);
        SafeParcelWriter.writeBoolean(r3, 3, this.zzb);
        SafeParcelWriter.writeInt(r3, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
