package com.google.android.gms.vision.face.internal.client;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zza> CREATOR = null;
    public final PointF[] zza;
    public final int zzb;

    static {
        CREATOR = new zzc();
    }

    public zza(PointF[] r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeTypedArray(r5, 2, this.zza, r6, false);
        SafeParcelWriter.writeInt(r5, 3, this.zzb);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
