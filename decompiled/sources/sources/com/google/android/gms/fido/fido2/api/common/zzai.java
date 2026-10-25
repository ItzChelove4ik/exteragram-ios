package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.fido.zzgx;
import com.google.android.gms.internal.fido.zzhp;

/* loaded from: classes4.dex */
public final class zzai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzai> CREATOR = null;
    static final zzhp zza = null;
    static final zzhp zzb = null;
    static final zzhp zzc = null;
    static final zzhp zzd = null;
    private final zzgx zze;
    private final zzgx zzf;
    private final zzgx zzg;
    private final int zzh;

    static {
        CREATOR = new zzaj();
        zza = zzhp.zzg(1);
        zzb = zzhp.zzg(2);
        zzc = zzhp.zzg(3);
        zzd = zzhp.zzg(4);
    }

    public zzai(zzgx r1, zzgx r2, zzgx r3, int r4) {
        this.zze = r1;
        this.zzf = r2;
        this.zzg = r3;
        this.zzh = r4;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzai) == true) goto L5;
        return false;
    L5:
        zzai r42 = (zzai) r4;
        if (Objects.equal(this.zze, r42.zze) == true) goto L8;
    L15:
        return false;
    L8:
        if (Objects.equal(this.zzf, r42.zzf) == false) goto L15;
        if (Objects.equal(this.zzg, r42.zzg) == false) goto L15;
        if (this.zzh != r42.zzh) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zze, this.zzf, this.zzg, Integer.valueOf(this.zzh)});
    }

    public final String toString() {
        return "HmacSecretExtension{coseKeyAgreement=" + Base64Utils.encodeUrlSafeNoPadding(zza()) + ", saltEnc=" + Base64Utils.encodeUrlSafeNoPadding(zzc()) + ", saltAuth=" + Base64Utils.encodeUrlSafeNoPadding(zzb()) + ", getPinUvAuthProtocol=" + this.zzh + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeByteArray(r4, 1, zza(), false);
        SafeParcelWriter.writeByteArray(r4, 2, zzc(), false);
        SafeParcelWriter.writeByteArray(r4, 3, zzb(), false);
        SafeParcelWriter.writeInt(r4, 4, this.zzh);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final byte[] zza() {
        zzgx r0 = this.zze;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzm();
    }

    public final byte[] zzb() {
        zzgx r0 = this.zzg;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzm();
    }

    public final byte[] zzc() {
        zzgx r0 = this.zzf;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzm();
    }
}
