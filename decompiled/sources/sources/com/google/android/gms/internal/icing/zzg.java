package com.google.android.gms.internal.icing;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zzg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzg> CREATOR = null;
    final zzk[] zza;
    public final String zzb;
    public final boolean zzc;
    public final Account zzd;

    static {
        CREATOR = new zzh();
    }

    public zzg(zzk[] r1, String r2, boolean r3, Account r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzg) == false) goto L14;
        zzg r42 = (zzg) r4;
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L14;
        if (Objects.equal(Boolean.valueOf(this.zzc), Boolean.valueOf(r42.zzc)) == false) goto L14;
        if (Objects.equal(this.zzd, r42.zzd) == false) goto L14;
        if (Arrays.equals(this.zza, r42.zza) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzb, Boolean.valueOf(this.zzc), this.zzd, Integer.valueOf(Arrays.hashCode(this.zza))});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeTypedArray(r5, 1, this.zza, r6, false);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(r5, 3, this.zzc);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzd, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
