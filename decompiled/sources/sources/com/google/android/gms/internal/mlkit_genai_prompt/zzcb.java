package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzcb extends AbstractSafeParcelable implements zzv {
    public static final Parcelable.Creator<zzcb> CREATOR = null;
    private final List zza;
    private final ParcelFileDescriptor zzb;

    static {
        CREATOR = new zzcc();
    }

    public zzcb(List r1, ParcelFileDescriptor r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static zzcb zzb(List r1, ParcelFileDescriptor r2) {
        return new zzcb(r1, r2);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        List r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeTypedList(r5, 1, r0, false);
        SafeParcelWriter.writeParcelable(r5, 2, this.zzb, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final ParcelFileDescriptor zza() {
        return this.zzb;
    }
}
