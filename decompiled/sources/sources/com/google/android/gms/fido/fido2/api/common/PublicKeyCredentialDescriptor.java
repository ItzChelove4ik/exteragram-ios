package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;
import com.google.android.gms.internal.fido.zzcf;
import com.google.android.gms.internal.fido.zzgx;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class PublicKeyCredentialDescriptor extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PublicKeyCredentialDescriptor> CREATOR = null;
    private static final zzcf zza = null;
    private final PublicKeyCredentialType zzb;
    private final zzgx zzc;
    private final List zzd;

    static {
        zza = zzcf.zzm(com.google.android.gms.internal.fido.zzh.zza, com.google.android.gms.internal.fido.zzh.zzb);
        CREATOR = new zzap();
    }

    public PublicKeyCredentialDescriptor(String r1, zzgx r2, List<Transport> r3) {
        Preconditions.checkNotNull(r1);
        this.zzb = PublicKeyCredentialType.fromString(r1);     // Catch: PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException -> L6
        this.zzc = (zzgx) Preconditions.checkNotNull(r2);
        this.zzd = r3;
        return;
    L6:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    }

    public PublicKeyCredentialDescriptor(String r3, byte[] r4, List<Transport> r5) {
        zzgx r0 = zzgx.zzb;
        this(r3, zzgx.zzl(r4, 0, r4.length), r5);
    }

    public static PublicKeyCredentialDescriptor zza(JSONObject r4) {
        String r0 = r4.getString("type");
        byte[] r1 = Base64.decode(r4.getString("id"), 11);
        if (r4.has("transports") == false) goto L5;
        List<Transport> r42 = Transport.parseTransports(r4.getJSONArray("transports"));
    L7:
        return new PublicKeyCredentialDescriptor(r0, r1, r42);
    L5:
        r42 = null;
        goto L7
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof PublicKeyCredentialDescriptor) == true) goto L5;
        return false;
    L5:
        PublicKeyCredentialDescriptor r52 = (PublicKeyCredentialDescriptor) r5;
        if (this.zzb.equals(r52.zzb) == true) goto L9;
        return false;
    L9:
        if (Objects.equal(this.zzc, r52.zzc) == true) goto L11;
        return false;
    L11:
        List r0 = this.zzd;
        if (r0 == null) goto L14;
    L17:
        if (r0 == null) goto L26;
        List r3 = r52.zzd;
        if (r3 == null) goto L26;
        if (r0.containsAll(r3) == false) goto L26;
        if (r52.zzd.containsAll(this.zzd) == false) goto L26;
        return true;
    L26:
        return false;
    L14:
        if (r52.zzd != null) goto L17;
        return true;
    }

    public byte[] getId() {
        return this.zzc.zzm();
    }

    public List<Transport> getTransports() {
        return this.zzd;
    }

    public String getTypeAsString() {
        return this.zzb.toString();
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zzb, this.zzc, this.zzd});
    }

    public final String toString() {
        return "PublicKeyCredentialDescriptor{\n type=" + String.valueOf(this.zzb) + ", \n id=" + Base64Utils.encodeUrlSafeNoPadding(getId()) + ", \n transports=" + String.valueOf(this.zzd) + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, getTypeAsString(), false);
        SafeParcelWriter.writeByteArray(r4, 3, getId(), false);
        SafeParcelWriter.writeTypedList(r4, 4, getTransports(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
