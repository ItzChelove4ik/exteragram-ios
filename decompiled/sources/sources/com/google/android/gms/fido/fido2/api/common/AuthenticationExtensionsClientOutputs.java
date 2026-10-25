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
public class AuthenticationExtensionsClientOutputs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthenticationExtensionsClientOutputs> CREATOR = null;
    private final UvmEntries zza;
    private final zzf zzb;
    private final AuthenticationExtensionsCredPropsOutputs zzc;
    private final zzh zzd;
    private final String zze;

    static {
        CREATOR = new zzc();
    }

    public AuthenticationExtensionsClientOutputs(UvmEntries r1, zzf r2, AuthenticationExtensionsCredPropsOutputs r3, zzh r4, String r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof AuthenticationExtensionsClientOutputs) == true) goto L5;
        return false;
    L5:
        AuthenticationExtensionsClientOutputs r42 = (AuthenticationExtensionsClientOutputs) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L17:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L17;
        if (Objects.equal(this.zzc, r42.zzc) == false) goto L17;
        if (Objects.equal(this.zzd, r42.zzd) == false) goto L17;
        if (Objects.equal(this.zze, r42.zze) == false) goto L17;
        return true;
    }

    public AuthenticationExtensionsCredPropsOutputs getCredProps() {
        return this.zzc;
    }

    public UvmEntries getUvmEntries() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, this.zze});
    }

    public final String toString() {
        return "AuthenticationExtensionsClientOutputs{" + zza().toString() + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 1, getUvmEntries(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 2, this.zzb, r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, getCredProps(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzd, r6, false);
        SafeParcelWriter.writeString(r5, 5, this.zze, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L15
        AuthenticationExtensionsCredPropsOutputs r1 = this.zzc;     // Catch: JSONException -> L15
        if (r1 == null) goto L5;
        r0.put("credProps", r1.zza());     // Catch: JSONException -> L15
    L5:
        UvmEntries r12 = this.zza;     // Catch: JSONException -> L15
        if (r12 == null) goto L8;
        r0.put("uvm", r12.zza());     // Catch: JSONException -> L15
    L8:
        zzh r13 = this.zzd;     // Catch: JSONException -> L15
        if (r13 == null) goto L11;
        r0.put("prf", r13.zza());     // Catch: JSONException -> L15
    L11:
        String r3 = this.zze;     // Catch: JSONException -> L15
        if (r3 == null) goto L14;
        r0.put("txAuthSimple", r3);     // Catch: JSONException -> L15
    L14:
        return r0;
    L15:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error encoding AuthenticationExtensionsClientOutputs to JSON object", e);
        return null;
    }
}
