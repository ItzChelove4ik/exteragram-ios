package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzbn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbn> CREATOR = null;
    private final int zza;

    static {
        CREATOR = new zzbo();
    }

    public zzbn() {
        this.zza = 0;
    }

    public zzbn(int r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r4) {
        if (r4 != this) goto L6;
        return true;
    L6:
        if ((r4 instanceof zzbn) == true) goto L9;
        return false;
    L9:
        if (this.zza != ((zzbn) r4).zza) goto L11;
        return true;
    L11:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zza)});
    }

    public final String toString() {
        int r1 = this.zza;
        if (r1 != 0) goto L5;
        String r12 = "STRONG";
    L10:
        return String.format("joinOptions(connectionType=%s)", new Object[]{r12});
    L5:
        if (r1 == 2) goto L7;
        r12 = "UNKNOWN";
        goto L10
    L7:
        r12 = "INVISIBLE";
        goto L10
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r2, int r3) {
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeInt(r2, 2, this.zza);
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
