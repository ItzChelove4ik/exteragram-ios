package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;

/* loaded from: classes4.dex */
public class PublicKeyCredentialParameters extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PublicKeyCredentialParameters> CREATOR = null;
    private final PublicKeyCredentialType zza;
    private final COSEAlgorithmIdentifier zzb;

    static {
        CREATOR = new zzaq();
    }

    public PublicKeyCredentialParameters(String r2, int r3) {
        Preconditions.checkNotNull(r2);
        this.zza = PublicKeyCredentialType.fromString(r2);     // Catch: PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException -> L10
        Preconditions.checkNotNull(Integer.valueOf(r3));
        this.zzb = COSEAlgorithmIdentifier.fromCoseValue(r3);     // Catch: COSEAlgorithmIdentifier.UnsupportedAlgorithmIdentifierException -> L7
        return;
    L7:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    L10:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof PublicKeyCredentialParameters) == true) goto L5;
        return false;
    L5:
        PublicKeyCredentialParameters r42 = (PublicKeyCredentialParameters) r4;
        if (this.zza.equals(r42.zza) == true) goto L8;
    L11:
        return false;
    L8:
        if (this.zzb.equals(r42.zzb) == false) goto L11;
        return true;
    }

    public int getAlgorithmIdAsInteger() {
        return this.zzb.toCoseValue();
    }

    public String getTypeAsString() {
        return this.zza.toString();
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        COSEAlgorithmIdentifier r0 = this.zzb;
        return "PublicKeyCredentialParameters{\n type=" + String.valueOf(this.zza) + ", \n algorithm=" + String.valueOf(r0) + "\n }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, getTypeAsString(), false);
        SafeParcelWriter.writeIntegerObject(r4, 3, Integer.valueOf(getAlgorithmIdAsInteger()), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
