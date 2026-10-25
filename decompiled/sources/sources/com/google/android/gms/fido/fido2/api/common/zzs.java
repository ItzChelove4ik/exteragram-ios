package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = null;
    private final List zza;

    static {
        CREATOR = new zzt();
    }

    public zzs(List r1) {
        this.zza = (List) Preconditions.checkNotNull(r1);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzs) == true) goto L5;
        return false;
    L5:
        zzs r42 = (zzs) r4;
        if (this.zza.containsAll(r42.zza) == true) goto L8;
    L11:
        return false;
    L8:
        if (r42.zza.containsAll(this.zza) == false) goto L11;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{new HashSet(this.zza)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        List r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeTypedList(r3, 1, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
