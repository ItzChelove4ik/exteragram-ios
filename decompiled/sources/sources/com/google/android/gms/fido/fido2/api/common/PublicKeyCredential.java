package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.fido.zzgx;
import com.google.android.gms.internal.fido.zzia;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class PublicKeyCredential extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PublicKeyCredential> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final zzgx zzc;
    private final AuthenticatorAttestationResponse zzd;
    private final AuthenticatorAssertionResponse zze;
    private final AuthenticatorErrorResponse zzf;
    private final AuthenticationExtensionsClientOutputs zzg;
    private final String zzh;
    private String zzi;

    static {
        CREATOR = new zzao();
    }

    private PublicKeyCredential(String r4, String r5, zzgx r6, AuthenticatorAttestationResponse r7, AuthenticatorAssertionResponse r8, AuthenticatorErrorResponse r9, AuthenticationExtensionsClientOutputs r10, String r11, String r12) {
        boolean r122 = false;
        if (r7 == null) goto L8;
        if (r8 != null) goto L8;
        if (r9 != null) goto L8;
    L7:
        boolean r1 = true;
    L16:
        Preconditions.checkArgument(r1, "Must provide a response object.");
        if (r9 != null) goto L20;
        if (r4 == null) goto L21;
        if (r6 != null) goto L20;
    L21:
        Preconditions.checkArgument(r122, "Must provide id and rawId if not an error response.");
        this.zza = r4;
        this.zzb = r5;
        this.zzc = r6;
        this.zzd = r7;
        this.zze = r8;
        this.zzf = r9;
        this.zzg = r10;
        this.zzh = r11;
        this.zzi = null;
        return;
    L20:
        r122 = true;
    L8:
        if (r7 != null) goto L11;
        if (r8 == null) goto L11;
        if (r9 == null) goto L7;
    L11:
        if (r7 != null) goto L15;
        if (r8 != null) goto L15;
        if (r9 != null) goto L7;
    L15:
        r1 = false;
        goto L16
    }

    public PublicKeyCredential(String r11, String r12, byte[] r13, AuthenticatorAttestationResponse r14, AuthenticatorAssertionResponse r15, AuthenticatorErrorResponse r16, AuthenticationExtensionsClientOutputs r17, String r18, String r19) {
        if (r13 != null) goto L5;
        zzgx r132 = null;
    L6:
        this(r11, r12, r132, r14, r15, r16, r17, r18, r19);
        return;
    L5:
        r132 = zzgx.zzl(r13, 0, r13.length);
        goto L6
    }

    public static PublicKeyCredential deserializeFromBytes(byte[] r1) {
        return (PublicKeyCredential) SafeParcelableSerializer.deserializeFromBytes(r1, CREATOR);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof PublicKeyCredential) == true) goto L5;
        return false;
    L5:
        PublicKeyCredential r42 = (PublicKeyCredential) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L23:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L23;
        if (Objects.equal(this.zzc, r42.zzc) == false) goto L23;
        if (Objects.equal(this.zzd, r42.zzd) == false) goto L23;
        if (Objects.equal(this.zze, r42.zze) == false) goto L23;
        if (Objects.equal(this.zzf, r42.zzf) == false) goto L23;
        if (Objects.equal(this.zzg, r42.zzg) == false) goto L23;
        if (Objects.equal(this.zzh, r42.zzh) == false) goto L23;
        return true;
    }

    public String getAuthenticatorAttachment() {
        return this.zzh;
    }

    public AuthenticationExtensionsClientOutputs getClientExtensionResults() {
        return this.zzg;
    }

    public String getId() {
        return this.zza;
    }

    public byte[] getRawId() {
        zzgx r0 = this.zzc;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzm();
    }

    public AuthenticatorResponse getResponse() {
        AuthenticatorAttestationResponse r0 = this.zzd;
        if (r0 == null) goto L5;
        return r0;
    L5:
        AuthenticatorAssertionResponse r02 = this.zze;
        if (r02 == null) goto L8;
        return r02;
    L8:
        AuthenticatorErrorResponse r1 = this.zzf;
        if (r1 == null) goto L11;
        return r1;
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("No response set.");
        return null;
    }

    public String getType() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zze, this.zzd, this.zzf, this.zzg, this.zzh});
    }

    public String toJson() {
        return zza().toString();
    }

    public final String toString() {
        zzgx r0 = this.zzc;
        if (r0 != null) goto L5;
        byte[] r02 = null;
    L6:
        String r1 = this.zzb;
        String r2 = this.zza;
        AuthenticatorAttestationResponse r3 = this.zzd;
        AuthenticatorAssertionResponse r4 = this.zze;
        AuthenticatorErrorResponse r5 = this.zzf;
        AuthenticationExtensionsClientOutputs r6 = this.zzg;
        String r9 = this.zzh;
        return "PublicKeyCredential{\n id='" + r2 + "', \n type='" + r1 + "', \n rawId=" + Base64Utils.encodeUrlSafeNoPadding(r02) + ", \n registerResponse=" + String.valueOf(r3) + ", \n signResponse=" + String.valueOf(r4) + ", \n errorResponse=" + String.valueOf(r5) + ", \n extensionsClientOutputs=" + String.valueOf(r6) + ", \n authenticatorAttachment='" + r9 + "'}";
    L5:
        r02 = r0.zzm();
        goto L6
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        if (zzia.zzc() == false) goto L5;
        this.zzi = zza().toString();
    L5:
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, getId(), false);
        SafeParcelWriter.writeString(r5, 2, getType(), false);
        SafeParcelWriter.writeByteArray(r5, 3, getRawId(), false);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzd, r6, false);
        SafeParcelWriter.writeParcelable(r5, 5, this.zze, r6, false);
        SafeParcelWriter.writeParcelable(r5, 6, this.zzf, r6, false);
        SafeParcelWriter.writeParcelable(r5, 7, getClientExtensionResults(), r6, false);
        SafeParcelWriter.writeString(r5, 8, getAuthenticatorAttachment(), false);
        SafeParcelWriter.writeString(r5, 9, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
        this.zzi = null;
    }

    public final JSONObject zza() {
        JSONObject r1 = new JSONObject();     // Catch: JSONException -> L8
        zzgx r2 = this.zzc;     // Catch: JSONException -> L8
        if (r2 != null) goto L6;
    L10:
        String r22 = this.zzh;     // Catch: JSONException -> L8
        if (r22 == null) goto L13;
        r1.put("authenticatorAttachment", r22);     // Catch: JSONException -> L8
    L13:
        String r23 = this.zzb;     // Catch: JSONException -> L8
        if (r23 != null) goto L16;
    L18:
        String r24 = this.zza;     // Catch: JSONException -> L8
        if (r24 == null) goto L21;
        r1.put("id", r24);     // Catch: JSONException -> L8
    L21:
        String r25 = PluginsConstants.RESPONSE;
        AuthenticatorAssertionResponse r3 = this.zze;     // Catch: JSONException -> L8
        boolean r4 = true;
        if (r3 == null) goto L24;
        JSONObject r32 = r3.zza();     // Catch: JSONException -> L8
    L31:
        if (r32 == null) goto L33;
        r1.put(r25, r32);     // Catch: JSONException -> L8
    L33:
        AuthenticationExtensionsClientOutputs r5 = this.zzg;     // Catch: JSONException -> L8
        if (r5 == null) goto L38;
        r1.put("clientExtensionResults", r5.zza());     // Catch: JSONException -> L8
        return r1;
    L38:
        if (r4 == false) goto L40;
        r1.put("clientExtensionResults", new JSONObject());     // Catch: JSONException -> L8
    L40:
        return r1;
    L24:
        AuthenticatorAttestationResponse r33 = this.zzd;     // Catch: JSONException -> L8
        if (r33 == null) goto L27;
        r32 = r33.zza();     // Catch: JSONException -> L8
        goto L31
    L27:
        AuthenticatorErrorResponse r34 = this.zzf;     // Catch: JSONException -> L8
        r4 = false;
        if (r34 == null) goto L30;
        r32 = r34.zza();     // Catch: JSONException -> L8
        r25 = "error";
        goto L31
    L30:
        r32 = null;
        goto L31
    L16:
        if (this.zzf != null) goto L18;
        r1.put("type", r23);     // Catch: JSONException -> L8
        goto L18
    L6:
        if (r2.zzm().length <= 0) goto L10;
        r1.put("rawId", Base64Utils.encodeUrlSafeNoPadding(this.zzc.zzm()));     // Catch: JSONException -> L8
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error encoding PublicKeyCredential to JSON object", e);
        return null;
    }
}
