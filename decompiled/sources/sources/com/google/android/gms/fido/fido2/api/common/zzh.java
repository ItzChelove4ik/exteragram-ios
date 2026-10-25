package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fido.zzgx;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = null;
    private final boolean zza;
    private final zzgx zzb;

    static {
        CREATOR = new zzi();
    }

    public zzh(boolean r1, zzgx r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzh) == true) goto L5;
        return false;
    L5:
        zzh r42 = (zzh) r4;
        if (this.zza == r42.zza) goto L8;
    L11:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L11;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza), this.zzb});
    }

    public final String toString() {
        return "AuthenticationExtensionsPrfOutputs{" + zza().toString() + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        boolean r42 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeBoolean(r3, 1, r42);
        SafeParcelWriter.writeByteArray(r3, 2, zzb(), false);
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L12
        if (this.zza == false) goto L5;
        r0.put("enabled", true);     // Catch: JSONException -> L12
    L5:
        byte[] r6 = zzb();     // Catch: JSONException -> L12
        if (r6 == null) goto L11;
        JSONObject r1 = new JSONObject();     // Catch: JSONException -> L12
        r1.put("first", Base64.encodeToString(Arrays.copyOf(r6, 32), 11));     // Catch: JSONException -> L12
        if (r6.length != 64) goto L10;
        r1.put("second", Base64.encodeToString(Arrays.copyOfRange(r6, 32, 64), 11));     // Catch: JSONException -> L12
    L10:
        r0.put("results", r1);     // Catch: JSONException -> L12
    L11:
        return r0;
    L12:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error encoding AuthenticationExtensionsPrfOutputs to JSON object", e);
        return null;
    }

    public final byte[] zzb() {
        zzgx r0 = this.zzb;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzm();
    }
}
