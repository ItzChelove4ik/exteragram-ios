package com.google.android.gms.internal.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzm> CREATOR = null;
    String[] zza;
    int[] zzb;
    RemoteViews zzc;
    byte[] zzd;

    static {
        CREATOR = new zzn();
    }

    public zzm(String[] r1, int[] r2, RemoteViews r3, byte[] r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeStringArray(r5, 1, this.zza, false);
        SafeParcelWriter.writeIntArray(r5, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeByteArray(r5, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
