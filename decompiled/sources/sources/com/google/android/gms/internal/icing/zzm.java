package com.google.android.gms.internal.icing;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzm> CREATOR = null;
    public final int zza;
    final Bundle zzb;

    static {
        CREATOR = new zzn();
    }

    public zzm(int r1, Bundle r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if ((r7 instanceof zzm) == true) goto L8;
        return false;
    L8:
        zzm r72 = (zzm) r7;
        if (this.zza == r72.zza) goto L11;
        return false;
    L11:
        Bundle r1 = this.zzb;
        Bundle r3 = r72.zzb;
        if (r1 != null) goto L16;
        if (r3 != null) goto L15;
        return true;
    L15:
        return false;
    L16:
        if (r3 != null) goto L19;
        return false;
    L19:
        if (r1.size() == r72.zzb.size()) goto L21;
        return false;
    L21:
        Iterator<String> r12 = this.zzb.keySet().iterator();
    L23:
        if (r12.hasNext() == false) goto L29;
        String r32 = r12.next();
        if (r72.zzb.containsKey(r32) == false) goto L28;
        if (Objects.equal(this.zzb.getString(r32), r72.zzb.getString(r32)) == true) goto L23;
    L28:
        return false;
    L29:
        return true;
    }

    public final int hashCode() {
        ArrayList r0 = new ArrayList();
        r0.add(Integer.valueOf(this.zza));
        Bundle r1 = this.zzb;
        if (r1 == null) goto L11;
        Iterator<String> r12 = r1.keySet().iterator();
    L6:
        if (r12.hasNext() == false) goto L11;
        String r2 = r12.next();
        r0.add(r2);
        String r22 = this.zzb.getString(r2);
        if (r22 == null) goto L6;
        r0.add(r22);
    L11:
        return Objects.hashCode(r0.toArray(new Object[0]));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, this.zza);
        SafeParcelWriter.writeBundle(r3, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
