package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzoq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoq> CREATOR = null;
    public final List zza;

    static {
        CREATOR = new zzor();
    }

    public zzoq(List r1) {
        this.zza = r1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        List r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeTypedList(r3, 1, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
