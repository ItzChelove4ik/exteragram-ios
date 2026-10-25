package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class AuthenticationExtensionsCredPropsOutputs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthenticationExtensionsCredPropsOutputs> CREATOR = null;
    private final boolean zza;

    static {
        CREATOR = new zze();
    }

    public AuthenticationExtensionsCredPropsOutputs(boolean r1) {
        this.zza = r1;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof AuthenticationExtensionsCredPropsOutputs) == true) goto L6;
        return false;
    L6:
        if (this.zza != ((AuthenticationExtensionsCredPropsOutputs) r3).zza) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean getIsDiscoverableCredential() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r2, int r3) {
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeBoolean(r2, 1, getIsDiscoverableCredential());
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L4
        r0.put("rk", this.zza);     // Catch: JSONException -> L4
        return r0;
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error encoding AuthenticationExtensionsCredPropsOutputs to JSON object", e);
        return null;
    }
}
