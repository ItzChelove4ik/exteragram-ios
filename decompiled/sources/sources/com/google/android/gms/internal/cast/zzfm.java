package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzfm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfm> CREATOR = null;
    private final String zza;
    private final byte[] zzb;
    private final List zzc;

    static {
        CREATOR = new zzfl();
    }

    public zzfm(String r1, byte[] r2, List r3) {
        this.zza = r1;
        this.zzb = r2;
        if (r3 != null) goto L5;
        ArrayList r12 = new ArrayList(0);
    L6:
        this.zzc = r12;
        return;
    L5:
        r12 = new ArrayList(r3);
        goto L6
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzfm) == true) goto L8;
        return false;
    L8:
        zzfm r52 = (zzfm) r5;
        if (Objects.equal(this.zza, r52.zza) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L15;
        if (Objects.equal(this.zzc, r52.zzc) == false) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        SafeParcelWriter.writeByteArray(r4, 2, this.zzb, false);
        SafeParcelWriter.writeIntegerList(r4, 3, new ArrayList(this.zzc), false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
