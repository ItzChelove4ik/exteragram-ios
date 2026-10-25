package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzjs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzjs> CREATOR = null;
    public final List zza;

    static {
        CREATOR = new zzjt();
    }

    public zzjs(List r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzjs) == true) goto L10;
        return false;
    L10:
        return this.zza.equals(((zzjs) r2).zza);
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("FlagOverrides(");
        Iterator r3 = this.zza.iterator();
        boolean r1 = true;
    L4:
        if (r3.hasNext() == false) goto L9;
        zzjq r2 = (zzjq) r3.next();
        if (r1 == true) goto L8;
        r0.append(", ");
    L8:
        r2.zza(r0);
        r1 = false;
        goto L4
    L9:
        r0.append(")");
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        List r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeTypedList(r3, 2, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
