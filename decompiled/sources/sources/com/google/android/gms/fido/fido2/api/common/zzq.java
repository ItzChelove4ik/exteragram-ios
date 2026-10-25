package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fido.zzgx;

/* loaded from: classes4.dex */
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = null;
    private final long zza;
    private final zzgx zzb;
    private final zzgx zzc;
    private final zzgx zzd;

    static {
        CREATOR = new zzr();
    }

    public zzq(long r3, byte[] r5, byte[] r6, byte[] r7) {
        byte[] r52 = (byte[]) Preconditions.checkNotNull(r5);
        zzgx r0 = zzgx.zzb;
        zzgx r53 = zzgx.zzl(r52, 0, r52.length);
        byte[] r62 = (byte[]) Preconditions.checkNotNull(r6);
        zzgx r63 = zzgx.zzl(r62, 0, r62.length);
        byte[] r72 = (byte[]) Preconditions.checkNotNull(r7);
        zzgx r73 = zzgx.zzl(r72, 0, r72.length);
        this.zza = r3;
        this.zzb = (zzgx) Preconditions.checkNotNull(r53);
        this.zzc = (zzgx) Preconditions.checkNotNull(r63);
        this.zzd = (zzgx) Preconditions.checkNotNull(r73);
    }

    public final boolean equals(Object r7) {
        if ((r7 instanceof zzq) == true) goto L5;
        return false;
    L5:
        zzq r72 = (zzq) r7;
        if (this.zza == r72.zza) goto L8;
    L15:
        return false;
    L8:
        if (Objects.equal(this.zzb, r72.zzb) == false) goto L15;
        if (Objects.equal(this.zzc, r72.zzc) == false) goto L15;
        if (Objects.equal(this.zzd, r72.zzd) == false) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zza), this.zzb, this.zzc, this.zzd});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        long r0 = this.zza;
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeLong(r4, 1, r0);
        SafeParcelWriter.writeByteArray(r4, 2, this.zzb.zzm(), false);
        SafeParcelWriter.writeByteArray(r4, 3, this.zzc.zzm(), false);
        SafeParcelWriter.writeByteArray(r4, 4, this.zzd.zzm(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
