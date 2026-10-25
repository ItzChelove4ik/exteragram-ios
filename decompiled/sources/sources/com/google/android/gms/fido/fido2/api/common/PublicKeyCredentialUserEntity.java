package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.fido.zzgx;

/* loaded from: classes4.dex */
public class PublicKeyCredentialUserEntity extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PublicKeyCredentialUserEntity> CREATOR = null;
    private final zzgx zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;

    static {
        CREATOR = new zzau();
    }

    public PublicKeyCredentialUserEntity(zzgx r1, String r2, String r3, String r4) {
        this.zza = (zzgx) Preconditions.checkNotNull(r1);
        this.zzb = (String) Preconditions.checkNotNull(r2);
        this.zzc = r3;
        this.zzd = (String) Preconditions.checkNotNull(r4);
    }

    public PublicKeyCredentialUserEntity(byte[] r3, String r4, String r5, String r6) {
        byte[] r32 = (byte[]) Preconditions.checkNotNull(r3);
        zzgx r0 = zzgx.zzb;
        this(zzgx.zzl(r32, 0, r32.length), r4, r5, r6);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof PublicKeyCredentialUserEntity) == true) goto L5;
        return false;
    L5:
        PublicKeyCredentialUserEntity r42 = (PublicKeyCredentialUserEntity) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L15:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L15;
        if (Objects.equal(this.zzc, r42.zzc) == false) goto L15;
        if (Objects.equal(this.zzd, r42.zzd) == false) goto L15;
        return true;
    }

    public String getDisplayName() {
        return this.zzd;
    }

    public String getIcon() {
        return this.zzc;
    }

    public byte[] getId() {
        return this.zza.zzm();
    }

    public String getName() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd});
    }

    public final String toString() {
        return "PublicKeyCredentialUserEntity{\n id=" + Base64Utils.encodeUrlSafeNoPadding(this.zza.zzm()) + ", \n name='" + this.zzb + "', \n icon='" + this.zzc + "', \n displayName='" + this.zzd + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeByteArray(r4, 2, getId(), false);
        SafeParcelWriter.writeString(r4, 3, getName(), false);
        SafeParcelWriter.writeString(r4, 4, getIcon(), false);
        SafeParcelWriter.writeString(r4, 5, getDisplayName(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
