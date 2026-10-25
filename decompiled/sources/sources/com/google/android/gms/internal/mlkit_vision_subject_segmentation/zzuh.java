package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzuh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzuh> CREATOR = null;
    private final List zza;
    private final float[] zzb;
    private final Bitmap zzc;
    private final List zzd;

    static {
        CREATOR = new zzui();
    }

    public zzuh(List r1, float[] r2, Bitmap r3, List r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        List r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeTypedList(r5, 1, r0, false);
        SafeParcelWriter.writeFloatArray(r5, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeFloatList(r5, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final Bitmap zza() {
        return this.zzc;
    }

    public final List zzb() {
        return this.zzd;
    }

    public final List zzc() {
        return this.zza;
    }

    public final float[] zzd() {
        return this.zzb;
    }
}
