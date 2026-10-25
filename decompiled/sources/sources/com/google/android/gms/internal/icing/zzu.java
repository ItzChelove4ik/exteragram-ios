package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzu> CREATOR = null;
    public final boolean zza;

    static {
        CREATOR = new zzv();
    }

    public zzu(boolean r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof zzu) == true) goto L9;
        return false;
    L9:
        if (this.zza != ((zzu) r4).zza) goto L11;
        return true;
    L11:
        return false;
    }

    public final int hashCode() {
        if (this.zza == false) goto L6;
        return 1;
    L6:
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r2, int r3) {
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeBoolean(r2, 1, this.zza);
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
