package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference;
import com.google.android.gms.internal.fido.zzbl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class PublicKeyCredentialCreationOptions extends RequestOptions {
    public static final Parcelable.Creator<PublicKeyCredentialCreationOptions> CREATOR = null;
    private final PublicKeyCredentialRpEntity zza;
    private final PublicKeyCredentialUserEntity zzb;
    private final byte[] zzc;
    private final List zzd;
    private final Double zze;
    private final List zzf;
    private final AuthenticatorSelectionCriteria zzg;
    private final Integer zzh;
    private final TokenBinding zzi;
    private final AttestationConveyancePreference zzj;
    private final AuthenticationExtensions zzk;
    private final String zzl;
    private ResultReceiver zzm;

    public static final class Builder {
        private PublicKeyCredentialRpEntity zza;
        private PublicKeyCredentialUserEntity zzb;
        private byte[] zzc;
        private List zzd;
        private Double zze;
        private List zzf;
        private AuthenticatorSelectionCriteria zzg;
        private Integer zzh;
        private TokenBinding zzi;
        private AttestationConveyancePreference zzj;
        private AuthenticationExtensions zzk;

        public Builder() {
        }

        public PublicKeyCredentialCreationOptions build() {
            PublicKeyCredentialRpEntity r1 = this.zza;
            PublicKeyCredentialUserEntity r2 = this.zzb;
            byte[] r3 = this.zzc;
            List r4 = this.zzd;
            Double r5 = this.zze;
            List r6 = this.zzf;
            AuthenticatorSelectionCriteria r7 = this.zzg;
            Integer r8 = this.zzh;
            TokenBinding r9 = this.zzi;
            AttestationConveyancePreference r10 = this.zzj;
            if (r10 != null) goto L5;
            String r102 = null;
        L7:
            return new PublicKeyCredentialCreationOptions(r1, r2, r3, r4, r5, r6, r7, r8, r9, r102, this.zzk, null, null);
        L5:
            r102 = r10.toString();
            goto L7
        }

        public Builder setAttestationConveyancePreference(AttestationConveyancePreference r1) {
            this.zzj = r1;
            return this;
        }

        public Builder setAuthenticationExtensions(AuthenticationExtensions r1) {
            this.zzk = r1;
            return this;
        }

        public Builder setAuthenticatorSelection(AuthenticatorSelectionCriteria r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setChallenge(byte[] r1) {
            this.zzc = (byte[]) Preconditions.checkNotNull(r1);
            return this;
        }

        public Builder setExcludeList(List<PublicKeyCredentialDescriptor> r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setParameters(List<PublicKeyCredentialParameters> r1) {
            this.zzd = (List) Preconditions.checkNotNull(r1);
            return this;
        }

        public Builder setRp(PublicKeyCredentialRpEntity r1) {
            this.zza = (PublicKeyCredentialRpEntity) Preconditions.checkNotNull(r1);
            return this;
        }

        public Builder setTimeoutSeconds(Double r1) {
            this.zze = r1;
            return this;
        }

        public Builder setUser(PublicKeyCredentialUserEntity r1) {
            this.zzb = (PublicKeyCredentialUserEntity) Preconditions.checkNotNull(r1);
            return this;
        }
    }

    static {
        CREATOR = new zzan();
    }

    public PublicKeyCredentialCreationOptions(PublicKeyCredentialRpEntity r1, PublicKeyCredentialUserEntity r2, byte[] r3, List r4, Double r5, List r6, AuthenticatorSelectionCriteria r7, Integer r8, TokenBinding r9, String r10, AuthenticationExtensions r11, String r12, ResultReceiver r13) {
        this.zzm = r13;
        if (r12 != null) goto L22;
        this.zza = (PublicKeyCredentialRpEntity) Preconditions.checkNotNull(r1);
        this.zzb = (PublicKeyCredentialUserEntity) Preconditions.checkNotNull(r2);
        this.zzc = (byte[]) Preconditions.checkNotNull(r3);
        this.zzd = (List) Preconditions.checkNotNull(r4);
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        if (r10 != null) goto L20;
        this.zzj = null;
    L18:
        this.zzk = r11;
        this.zzl = null;
        return;
    L20:
        this.zzj = AttestationConveyancePreference.fromString(r10);     // Catch: AttestationConveyancePreference.UnsupportedAttestationConveyancePreferenceException -> L14
    L14:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    L22:
        PublicKeyCredentialCreationOptions r14 = zza(new JSONObject(r12));     // Catch: JSONException -> L7
        this.zza = r14.zza;
        this.zzb = r14.zzb;
        this.zzc = r14.zzc;
        this.zzd = r14.zzd;
        this.zze = r14.zze;
        this.zzf = r14.zzf;
        this.zzg = r14.zzg;
        this.zzh = r14.zzh;
        this.zzi = r14.zzi;
        this.zzj = r14.zzj;
        this.zzk = r14.zzk;
        this.zzl = r12;
        return;
    L7:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    }

    public PublicKeyCredentialCreationOptions(String r3) {
        PublicKeyCredentialCreationOptions r0 = zza(new JSONObject(r3));     // Catch: JSONException -> L6
        this.zza = r0.zza;
        this.zzb = r0.zzb;
        this.zzc = r0.zzc;
        this.zzd = r0.zzd;
        this.zze = r0.zze;
        this.zzf = r0.zzf;
        this.zzg = r0.zzg;
        this.zzh = r0.zzh;
        this.zzi = r0.zzi;
        this.zzj = r0.zzj;
        this.zzk = r0.zzk;
        this.zzl = r3;
        return;
    L6:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    }

    public static PublicKeyCredentialCreationOptions zza(JSONObject r10) {
        Builder r0 = new Builder();
        JSONObject r1 = r10.getJSONObject("rp");
        Parcelable.Creator<PublicKeyCredentialRpEntity> r2 = PublicKeyCredentialRpEntity.CREATOR;
        String r3 = r1.getString("id");
        String r5 = r1.getString("name");
        String r8 = null;
        if (r1.has("icon") == false) goto L5;
        String r12 = r1.optString("icon");
    L6:
        r0.setRp(new PublicKeyCredentialRpEntity(r3, r5, r12));
        JSONObject r13 = r10.getJSONObject("user");
        Parcelable.Creator<PublicKeyCredentialUserEntity> r32 = PublicKeyCredentialUserEntity.CREATOR;
        byte[] r22 = Base64Utils.decodeUrlSafeNoPadding(r13.getString("id"));
        String r33 = r13.getString("name");
        String r4 = r13.optString("displayName");
        if (r13.has("icon") == false) goto L9;
        String r14 = r13.optString("icon");
    L10:
        r0.setUser(new PublicKeyCredentialUserEntity(r22, r33, r14, r4));
        r0.setChallenge(Base64Utils.decodeUrlSafeNoPadding(r10.getString("challenge")));
        JSONArray r15 = r10.getJSONArray("pubKeyCredParams");
        ArrayList r23 = new ArrayList();
        int r34 = 0;
        int r42 = 0;
    L12:
        if (r42 >= r15.length()) goto L21;
        JSONObject r52 = r15.getJSONObject(r42);
        zzbl r53 = zzbl.zzd(new PublicKeyCredentialParameters(r52.getString("type"), r52.getInt("alg")));     // Catch: IllegalArgumentException -> L16
    L18:
        if (r53.zzb() == false) goto L20;
        r23.add(r53.zza());
    L20:
        r42 = r42 + 1;
    L16:
        r53 = zzbl.zzc();
        goto L18
    L21:
        r0.setParameters(r23);
        if (r10.has("timeout") == false) goto L25;
        r0.setTimeoutSeconds(Double.valueOf(r10.getDouble("timeout") / 1000.0d));
    L25:
        if (r10.has("excludeCredentials") == false) goto L32;
        JSONArray r16 = r10.getJSONArray("excludeCredentials");
        ArrayList r24 = new ArrayList();
    L28:
        if (r34 >= r16.length()) goto L30;
        r24.add(PublicKeyCredentialDescriptor.zza(r16.getJSONObject(r34)));
        r34 = r34 + 1;
        goto L28
    L30:
        r0.setExcludeList(r24);
    L32:
        if (r10.has("authenticatorSelection") == false) goto L50;
        JSONObject r17 = r10.getJSONObject("authenticatorSelection");
        Parcelable.Creator<AuthenticatorSelectionCriteria> r25 = AuthenticatorSelectionCriteria.CREATOR;
        if (r17.has("authenticatorAttachment") == false) goto L36;
        String r26 = r17.optString("authenticatorAttachment");
    L38:
        if (r17.has("residentKey") == false) goto L40;
        String r35 = r17.optString("residentKey");
    L42:
        if (r17.has("requireResidentKey") == false) goto L44;
        Boolean r43 = Boolean.valueOf(r17.optBoolean("requireResidentKey"));
    L46:
        if (r17.has("userVerification") == false) goto L48;
        r8 = r17.optString("userVerification");
    L48:
        r0.setAuthenticatorSelection(new AuthenticatorSelectionCriteria(r26, r43, r8, r35));
        goto L50
    L44:
        r43 = null;
        goto L46
    L40:
        r35 = null;
        goto L42
    L36:
        r26 = null;
    L50:
        if (r10.has("extensions") == false) goto L53;
        r0.setAuthenticationExtensions(AuthenticationExtensions.zza(r10.getJSONObject("extensions")));
    L53:
        if (r10.has("attestation") == false) goto L59;
        r0.setAttestationConveyancePreference(AttestationConveyancePreference.fromString(r10.getString("attestation")));     // Catch: AttestationConveyancePreference.UnsupportedAttestationConveyancePreferenceException -> L56
    L56:
        e = move-exception;
        Log.w("PKCCreationOptions", "Invalid AttestationConveyancePreference", e);
        r0.setAttestationConveyancePreference(AttestationConveyancePreference.NONE);
    L59:
        return r0.build();
    L9:
        r14 = null;
        goto L10
    L5:
        r12 = null;
        goto L6
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof PublicKeyCredentialCreationOptions) == true) goto L5;
        return false;
    L5:
        PublicKeyCredentialCreationOptions r42 = (PublicKeyCredentialCreationOptions) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L42:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L42;
        if (Arrays.equals(this.zzc, r42.zzc) == false) goto L42;
        if (Objects.equal(this.zze, r42.zze) == false) goto L42;
        if (this.zzd.containsAll(r42.zzd) == false) goto L42;
        if (r42.zzd.containsAll(this.zzd) == false) goto L42;
        List r0 = this.zzf;
        if (r0 == null) goto L20;
    L21:
        if (r0 == null) goto L42;
        List r2 = r42.zzf;
        if (r2 == null) goto L42;
        if (r0.containsAll(r2) == false) goto L42;
        if (r42.zzf.containsAll(this.zzf) == false) goto L42;
    L29:
        if (Objects.equal(this.zzg, r42.zzg) == false) goto L42;
        if (Objects.equal(this.zzh, r42.zzh) == false) goto L42;
        if (Objects.equal(this.zzi, r42.zzi) == false) goto L42;
        if (Objects.equal(this.zzj, r42.zzj) == false) goto L42;
        if (Objects.equal(this.zzk, r42.zzk) == false) goto L42;
        if (Objects.equal(this.zzl, r42.zzl) == false) goto L42;
        return true;
    L20:
        if (r42.zzf == null) goto L29;
        goto L21
    }

    public String getAttestationConveyancePreferenceAsString() {
        AttestationConveyancePreference r0 = this.zzj;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.toString();
    }

    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zzk;
    }

    public AuthenticatorSelectionCriteria getAuthenticatorSelection() {
        return this.zzg;
    }

    public byte[] getChallenge() {
        return this.zzc;
    }

    public List<PublicKeyCredentialDescriptor> getExcludeList() {
        return this.zzf;
    }

    public String getJsonString() {
        return this.zzl;
    }

    public List<PublicKeyCredentialParameters> getParameters() {
        return this.zzd;
    }

    public Integer getRequestId() {
        return this.zzh;
    }

    public PublicKeyCredentialRpEntity getRp() {
        return this.zza;
    }

    public Double getTimeoutSeconds() {
        return this.zze;
    }

    public TokenBinding getTokenBinding() {
        return this.zzi;
    }

    public PublicKeyCredentialUserEntity getUser() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, Integer.valueOf(Arrays.hashCode(this.zzc)), this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl});
    }

    public final String toString() {
        AuthenticationExtensions r0 = this.zzk;
        AttestationConveyancePreference r1 = this.zzj;
        TokenBinding r2 = this.zzi;
        AuthenticatorSelectionCriteria r3 = this.zzg;
        List r4 = this.zzf;
        List r5 = this.zzd;
        byte[] r6 = this.zzc;
        PublicKeyCredentialUserEntity r7 = this.zzb;
        return "PublicKeyCredentialCreationOptions{\n rp=" + String.valueOf(this.zza) + ", \n user=" + String.valueOf(r7) + ", \n challenge=" + Base64Utils.encodeUrlSafeNoPadding(r6) + ", \n parameters=" + String.valueOf(r5) + ", \n timeoutSeconds=" + this.zze + ", \n excludeList=" + String.valueOf(r4) + ", \n authenticatorSelection=" + String.valueOf(r3) + ", \n requestId=" + this.zzh + ", \n tokenBinding=" + String.valueOf(r2) + ", \n attestationConveyancePreference=" + String.valueOf(r1) + ", \n authenticationExtensions=" + String.valueOf(r0) + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, getRp(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, getUser(), r6, false);
        SafeParcelWriter.writeByteArray(r5, 4, getChallenge(), false);
        SafeParcelWriter.writeTypedList(r5, 5, getParameters(), false);
        SafeParcelWriter.writeDoubleObject(r5, 6, getTimeoutSeconds(), false);
        SafeParcelWriter.writeTypedList(r5, 7, getExcludeList(), false);
        SafeParcelWriter.writeParcelable(r5, 8, getAuthenticatorSelection(), r6, false);
        SafeParcelWriter.writeIntegerObject(r5, 9, getRequestId(), false);
        SafeParcelWriter.writeParcelable(r5, 10, getTokenBinding(), r6, false);
        SafeParcelWriter.writeString(r5, 11, getAttestationConveyancePreferenceAsString(), false);
        SafeParcelWriter.writeParcelable(r5, 12, getAuthenticationExtensions(), r6, false);
        SafeParcelWriter.writeString(r5, 13, getJsonString(), false);
        SafeParcelWriter.writeParcelable(r5, 14, this.zzm, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
