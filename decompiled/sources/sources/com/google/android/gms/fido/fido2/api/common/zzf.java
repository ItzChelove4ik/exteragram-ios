package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fido.zzgx;

/* loaded from: classes4.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = null;
    private final zzgx zza;
    private final zzgx zzb;

    static {
        CREATOR = new zzg();
    }

    public zzf(zzgx r1, zzgx r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzf) == true) goto L5;
        return false;
    L5:
        zzf r42 = (zzf) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L11:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L11;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        zzgx r62 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        byte[] r1 = null;
        if (r62 != null) goto L5;
        byte[] r63 = null;
    L6:
        SafeParcelWriter.writeByteArray(r5, 1, r63, false);
        zzgx r4 = this.zzb;
        if (r4 == null) goto L10;
        r1 = r4.zzm();
    L10:
        SafeParcelWriter.writeByteArray(r5, 2, r1, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
        return;
    L5:
        r63 = r62.zzm();
        goto L6
    }
}
