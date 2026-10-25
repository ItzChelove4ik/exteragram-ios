package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@UsedByNative("wrapper.cc")
/* loaded from: classes5.dex */
public final class LandmarkParcel extends AbstractSafeParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<LandmarkParcel> CREATOR = null;
    public final float zza;
    public final float zzb;
    public final int zzc;
    private final int zzd;

    static {
        CREATOR = new zzm();
    }

    @UsedByNative("wrapper.cc")
    public LandmarkParcel(int r1, float r2, float r3, int r4) {
        this.zzd = r1;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@RecentlyNonNull Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, this.zzd);
        SafeParcelWriter.writeFloat(r3, 2, this.zza);
        SafeParcelWriter.writeFloat(r3, 3, this.zzb);
        SafeParcelWriter.writeInt(r3, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
