package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzfn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfn> CREATOR = null;
    private final List zza;
    private final boolean zzb;
    private final boolean zzc;

    static {
        new zzfn(null, false, false);
        CREATOR = new zzfo();
    }

    public zzfn(List r2, boolean r3, boolean r4) {
        if (r2 != null) goto L5;
        ArrayList r22 = new ArrayList(0);
    L6:
        this.zza = r22;
        this.zzb = r3;
        this.zzc = r4;
        return;
    L5:
        r22 = new ArrayList(r2);
        goto L6
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzfn) == true) goto L8;
        return false;
    L8:
        zzfn r52 = (zzfn) r5;
        if (Objects.equal(this.zza, r52.zza) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equal(Boolean.valueOf(this.zzb), Boolean.valueOf(r52.zzb)) == false) goto L13;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, Boolean.valueOf(this.zzb)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeTypedList(r4, 1, new ArrayList(this.zza), false);
        SafeParcelWriter.writeBoolean(r4, 2, this.zzb);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
