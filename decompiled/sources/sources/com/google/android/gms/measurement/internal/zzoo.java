package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzoo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoo> CREATOR = null;
    public final List zza;

    static {
        CREATOR = new zzop();
    }

    public zzoo(List r1) {
        this.zza = r1;
    }

    public static zzoo zza(zzls... r2) {
        ArrayList r0 = new ArrayList(1);
        r0.add(Integer.valueOf(r2[0].zza()));
        return new zzoo(r0);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        List r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeIntegerList(r3, 1, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
