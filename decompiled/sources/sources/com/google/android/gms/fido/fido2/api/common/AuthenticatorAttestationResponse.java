package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.internal.fido.zzbi;
import com.google.android.gms.internal.fido.zzbj;
import com.google.android.gms.internal.fido.zzgf;
import com.google.android.gms.internal.fido.zzgj;
import com.google.android.gms.internal.fido.zzgx;
import com.google.android.gms.internal.fido.zzhj;
import com.google.android.gms.internal.fido.zzhm;
import com.google.android.gms.internal.fido.zzho;
import com.google.android.gms.internal.fido.zzhp;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class AuthenticatorAttestationResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator<AuthenticatorAttestationResponse> CREATOR = null;
    private final zzgx zza;
    private final zzgx zzb;
    private final zzgx zzc;
    private final String[] zzd;

    static {
        CREATOR = new zzk();
    }

    public AuthenticatorAttestationResponse(byte[] r3, byte[] r4, byte[] r5, String[] r6) {
        byte[] r32 = (byte[]) Preconditions.checkNotNull(r3);
        zzgx r0 = zzgx.zzb;
        zzgx r33 = zzgx.zzl(r32, 0, r32.length);
        byte[] r42 = (byte[]) Preconditions.checkNotNull(r4);
        zzgx r43 = zzgx.zzl(r42, 0, r42.length);
        byte[] r52 = (byte[]) Preconditions.checkNotNull(r5);
        zzgx r53 = zzgx.zzl(r52, 0, r52.length);
        this.zza = (zzgx) Preconditions.checkNotNull(r33);
        this.zzb = (zzgx) Preconditions.checkNotNull(r43);
        this.zzc = (zzgx) Preconditions.checkNotNull(r53);
        this.zzd = (String[]) Preconditions.checkNotNull(r6);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof AuthenticatorAttestationResponse) == true) goto L5;
        return false;
    L5:
        AuthenticatorAttestationResponse r42 = (AuthenticatorAttestationResponse) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L13:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L13;
        if (Objects.equal(this.zzc, r42.zzc) == false) goto L13;
        return true;
    }

    public byte[] getAttestationObject() {
        return this.zzc.zzm();
    }

    public byte[] getClientDataJSON() {
        return this.zzb.zzm();
    }

    @Deprecated
    public byte[] getKeyHandle() {
        return this.zza.zzm();
    }

    public String[] getTransports() {
        return this.zzd;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(Objects.hashCode(new Object[]{this.zza})), Integer.valueOf(Objects.hashCode(new Object[]{this.zzb})), Integer.valueOf(Objects.hashCode(new Object[]{this.zzc}))});
    }

    public String toString() {
        zzbi r0 = zzbj.zza(this);
        zzgf r1 = zzgf.zzf();
        byte[] r2 = getKeyHandle();
        r0.zzb("keyHandle", r1.zzg(r2, 0, r2.length));
        zzgf r12 = zzgf.zzf();
        byte[] r22 = getClientDataJSON();
        r0.zzb("clientDataJSON", r12.zzg(r22, 0, r22.length));
        zzgf r13 = zzgf.zzf();
        byte[] r23 = getAttestationObject();
        r0.zzb("attestationObject", r13.zzg(r23, 0, r23.length));
        r0.zzb("transports", Arrays.toString(this.zzd));
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeByteArray(r4, 2, getKeyHandle(), false);
        SafeParcelWriter.writeByteArray(r4, 3, getClientDataJSON(), false);
        SafeParcelWriter.writeByteArray(r4, 4, getAttestationObject(), false);
        SafeParcelWriter.writeStringArray(r4, 5, getTransports(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final JSONObject zza() {
        JSONObject r2 = new JSONObject();     // Catch: JSONException -> L6
        if (this.zzb == null) goto L9;
        r2.put("clientDataJSON", Base64Utils.encodeUrlSafeNoPadding(getClientDataJSON()));     // Catch: JSONException -> L6
    L9:
        if (this.zzc == null) goto L11;
        r2.put("attestationObject", Base64Utils.encodeUrlSafeNoPadding(getAttestationObject()));     // Catch: JSONException -> L6
    L11:
        JSONArray r3 = new JSONArray();     // Catch: JSONException -> L6
        int r5 = 0;
    L12:
        String[] r6 = this.zzd;     // Catch: JSONException -> L6
        if (r5 >= r6.length) goto L19;
        if (r6[r5].equals(Transport.HYBRID.toString()) == false) goto L17;
        r3.put(r5, "hybrid");     // Catch: JSONException -> L6
    L18:
        r5 = r5 + 1;     // Catch: JSONException -> L6
        goto L12
    L17:
        r3.put(r5, this.zzd[r5]);     // Catch: JSONException -> L6
        goto L18
    L19:
        r2.put("transports", r3);     // Catch: JSONException -> L6
        zzhp r0 = (zzhp) zzhp.zzj(getAttestationObject()).zzh().zzc().get(zzhp.zzi("authData"));     // Catch: JSONException -> L6 zzho -> L94
        if (r0 == null) goto L97;
        zzgx r02 = r0.zze().zzc();     // Catch: JSONException -> L6 zzho -> L94
        ByteBuffer r32 = r02.zzi();     // Catch: JSONException -> L6
        r32.position(r32.position() + 32);     // Catch: JSONException -> L6 IllegalArgumentException -> L88
        if ((r32.get() & 64) == 0) goto L91;
        r32.position(r32.position() + 4);     // Catch: JSONException -> L6 IllegalArgumentException -> L88
        r32.position(r32.position() + 16);     // Catch: JSONException -> L6 IllegalArgumentException -> L88
        r32.position(r32.position() + r32.getShort());     // Catch: JSONException -> L6 IllegalArgumentException -> L88
        zzhm r33 = zzhp.zzk(r02.zzg(r32.position(), r02.zzd()).zzh()).zzh();     // Catch: JSONException -> L6 zzhj -> L83 zzho -> L85
        zzhp r52 = (zzhp) r33.zzc().get(zzhp.zzg(3));     // Catch: JSONException -> L6
        zzhp r7 = (zzhp) r33.zzc().get(zzhp.zzg(1));     // Catch: JSONException -> L6
        if (r52 == null) goto L82;
        if (r7 == null) goto L82;
        long r11 = r52.zzf().zzc();     // Catch: JSONException -> L6 zzho -> L54
        long r13 = r7.zzf().zzc();     // Catch: JSONException -> L6 zzho -> L54
        if (r13 != 1) goto L36;
    L39:
        zzhp r53 = (zzhp) r33.zzc().get(zzhp.zzg(-1));     // Catch: JSONException -> L6 zzho -> L54
        if (r53 == null) goto L78;
        long r17 = r53.zzf().zzc();     // Catch: JSONException -> L6 zzho -> L54
        if (r13 != 2) goto L61;
        if (r17 != 1) goto L61;
        zzhp r54 = (zzhp) r33.zzc().get(zzhp.zzg(-2));     // Catch: JSONException -> L6 zzho -> L54
        zzhp r34 = (zzhp) r33.zzc().get(zzhp.zzg(-3));     // Catch: JSONException -> L6 zzho -> L54
        if (r54 == null) goto L59;
        if (r34 == null) goto L59;
        zzgx r55 = r54.zze().zzc();     // Catch: JSONException -> L6 zzho -> L54
        zzgx r35 = r34.zze().zzc();     // Catch: JSONException -> L6 zzho -> L54
        if (r55.zzd() != 32) goto L57;
        if (r35.zzd() != 32) goto L57;
        byte[] r36 = zzgj.zza(new byte[][]{Base64.decode("MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE", 0), r55.zzm(), r35.zzm()});     // Catch: JSONException -> L6 zzho -> L54
    L73:
        r2.put("authenticatorData", Base64Utils.encodeUrlSafeNoPadding(r02.zzm()));     // Catch: JSONException -> L6
        r2.put("publicKeyAlgorithm", r11);     // Catch: JSONException -> L6
        if (r36 == null) goto L76;
        r2.put("publicKey", Base64Utils.encodeUrlSafeNoPadding(r36));     // Catch: JSONException -> L6
    L76:
        return r2;
    L57:
        throw new IllegalArgumentException("COSE coordinates are the wrong size");     // Catch: JSONException -> L6 zzho -> L54
    L59:
        throw new IllegalArgumentException("COSE key missing required fields");     // Catch: JSONException -> L6 zzho -> L54
    L61:
        if (r13 == 1) goto L63;
    L38:
        r36 = null;
        goto L73
    L63:
        if (r17 != 6) goto L38;
        zzhp r37 = (zzhp) r33.zzc().get(zzhp.zzg(-2));     // Catch: JSONException -> L6 zzho -> L54
        if (r37 == null) goto L72;
        zzgx r38 = r37.zze().zzc();     // Catch: JSONException -> L6 zzho -> L54
        if (r38.zzd() != 32) goto L70;
        r36 = zzgj.zza(new byte[][]{Base64.decode("MCowBQYDK2VwAyEA", 0), r38.zzm()});     // Catch: JSONException -> L6 zzho -> L54
        goto L73
    L70:
        throw new IllegalArgumentException("COSE coordinates are the wrong size");     // Catch: JSONException -> L6 zzho -> L54
    L72:
        throw new IllegalArgumentException("COSE key missing required fields");     // Catch: JSONException -> L6 zzho -> L54
    L78:
        throw new IllegalArgumentException("COSE key missing required fields");     // Catch: JSONException -> L6 zzho -> L54
    L36:
        if (r13 != 2) goto L38;
        r13 = 2;
    L54:
        e = move-exception;
        throw new IllegalArgumentException("COSE key ill-formed", e);     // Catch: JSONException -> L6
    L82:
        throw new IllegalArgumentException("COSE key missing required fields");     // Catch: JSONException -> L6
    L83:
        e = e;
    L87:
        throw new IllegalArgumentException("failed to parse COSE key", e);     // Catch: JSONException -> L6
    L85:
        e = e;
        goto L87
    L91:
        throw new IllegalArgumentException("authData does not include credential data");     // Catch: JSONException -> L6 IllegalArgumentException -> L88
    L88:
        e = move-exception;
        throw new IllegalArgumentException("ill-formed authenticator data", e);     // Catch: JSONException -> L6
    L97:
        throw new IllegalArgumentException("attestation object missing authData");     // Catch: JSONException -> L6 zzho -> L94
    L94:
        e = move-exception;
        throw new IllegalArgumentException("authData value has wrong type", e);     // Catch: JSONException -> L6
    L100:
        e = e;
    L104:
        throw new IllegalArgumentException("failed to parse attestation object", e);     // Catch: JSONException -> L6
    L102:
        e = e;
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error encoding AuthenticatorAttestationResponse to JSON object", e);
        return null;
    }
}
