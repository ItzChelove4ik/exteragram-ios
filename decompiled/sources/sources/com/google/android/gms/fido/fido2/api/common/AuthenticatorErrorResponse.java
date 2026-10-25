package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fido.fido2.api.common.ErrorCode;
import com.google.android.gms.internal.fido.zzbi;
import com.google.android.gms.internal.fido.zzbj;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class AuthenticatorErrorResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator<AuthenticatorErrorResponse> CREATOR = null;
    private final ErrorCode zza;
    private final String zzb;
    private final int zzc;

    static {
        CREATOR = new zzl();
    }

    public AuthenticatorErrorResponse(int r1, String r2, int r3) {
        this.zza = ErrorCode.toErrorCode(r1);     // Catch: ErrorCode.UnsupportedErrorCodeException -> L6
        this.zzb = r2;
        this.zzc = r3;
        return;
    L6:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof AuthenticatorErrorResponse) == true) goto L5;
        return false;
    L5:
        AuthenticatorErrorResponse r42 = (AuthenticatorErrorResponse) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L13:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L13;
        if (Objects.equal(Integer.valueOf(this.zzc), Integer.valueOf(r42.zzc)) == false) goto L13;
        return true;
    }

    public ErrorCode getErrorCode() {
        return this.zza;
    }

    public int getErrorCodeAsInt() {
        return this.zza.getCode();
    }

    public String getErrorMessage() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, Integer.valueOf(this.zzc)});
    }

    public String toString() {
        zzbi r0 = zzbj.zza(this);
        r0.zza("errorCode", this.zza.getCode());
        String r3 = this.zzb;
        if (r3 == null) goto L6;
        r0.zzb("errorMessage", r3);
    L6:
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, getErrorCodeAsInt());
        SafeParcelWriter.writeString(r4, 3, getErrorMessage(), false);
        SafeParcelWriter.writeInt(r4, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L6
        r0.put("code", this.zza.getCode());     // Catch: JSONException -> L6
        String r3 = this.zzb;     // Catch: JSONException -> L6
        if (r3 == null) goto L5;
        r0.put("message", r3);     // Catch: JSONException -> L6
    L5:
        return r0;
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error encoding AuthenticatorErrorResponse to JSON object", e);
        return null;
    }
}
