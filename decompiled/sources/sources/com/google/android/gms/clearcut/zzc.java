package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzc> CREATOR = null;
    private final boolean zzad;
    private final long zzae;
    private final long zzaf;

    static {
        CREATOR = new zzd();
    }

    public zzc(boolean r1, long r2, long r4) {
        this.zzad = r1;
        this.zzae = r2;
        this.zzaf = r4;
    }

    public final boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof zzc) == false) goto L14;
        zzc r82 = (zzc) r8;
        if (this.zzad != r82.zzad) goto L14;
        if (this.zzae != r82.zzae) goto L14;
        if (this.zzaf != r82.zzaf) goto L14;
        return true;
    L14:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zzad), Long.valueOf(this.zzae), Long.valueOf(this.zzaf)});
    }

    public final String toString() {
        return "CollectForDebugParcelable[skipPersistentStorage: " + this.zzad + ",collectForDebugStartTimeMillis: " + this.zzae + ",collectForDebugExpiryTimeMillis: " + this.zzaf + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeBoolean(r4, 1, this.zzad);
        SafeParcelWriter.writeLong(r4, 2, this.zzaf);
        SafeParcelWriter.writeLong(r4, 3, this.zzae);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
