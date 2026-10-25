package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AuthenticationExtensions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthenticationExtensions> CREATOR = null;
    private final FidoAppIdExtension zza;
    private final zzs zzb;
    private final UserVerificationMethodExtension zzc;
    private final zzz zzd;
    private final zzab zze;
    private final zzad zzf;
    private final zzu zzg;
    private final zzag zzh;
    private final GoogleThirdPartyPaymentExtension zzi;
    private final zzak zzj;
    private final zzaw zzk;
    private final zzai zzl;

    public static final class Builder {
        private FidoAppIdExtension zza;
        private UserVerificationMethodExtension zzb;
        private zzs zzc;
        private zzz zzd;
        private zzab zze;
        private zzad zzf;
        private zzu zzg;
        private zzag zzh;
        private GoogleThirdPartyPaymentExtension zzi;
        private zzak zzj;
        private zzaw zzk;
        private zzai zzl;

        public Builder() {
        }

        public AuthenticationExtensions build() {
            return new AuthenticationExtensions(this.zza, this.zzc, this.zzb, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl);
        }

        public Builder setFido2Extension(FidoAppIdExtension r1) {
            this.zza = r1;
            return this;
        }

        public Builder setGoogleThirdPartyPaymentExtension(GoogleThirdPartyPaymentExtension r1) {
            this.zzi = r1;
            return this;
        }

        public Builder setUserVerificationMethodExtension(UserVerificationMethodExtension r1) {
            this.zzb = r1;
            return this;
        }

        public final Builder zza(zzs r1) {
            this.zzc = r1;
            return this;
        }

        public final Builder zzb(zzu r1) {
            this.zzg = r1;
            return this;
        }

        public final Builder zzc(zzz r1) {
            this.zzd = r1;
            return this;
        }

        public final Builder zzd(zzab r1) {
            this.zze = r1;
            return this;
        }

        public final Builder zze(zzad r1) {
            this.zzf = r1;
            return this;
        }

        public final Builder zzf(zzag r1) {
            this.zzh = r1;
            return this;
        }

        public final Builder zzg(zzak r1) {
            this.zzj = r1;
            return this;
        }

        public final Builder zzh(zzaw r1) {
            this.zzk = r1;
            return this;
        }
    }

    static {
        CREATOR = new zzd();
    }

    public AuthenticationExtensions(FidoAppIdExtension r1, zzs r2, UserVerificationMethodExtension r3, zzz r4, zzab r5, zzad r6, zzu r7, zzag r8, GoogleThirdPartyPaymentExtension r9, zzak r10, zzaw r11, zzai r12) {
        this.zza = r1;
        this.zzc = r3;
        this.zzb = r2;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
        this.zzk = r11;
        this.zzl = r12;
    }

    public static AuthenticationExtensions zza(JSONObject r12) {
        Builder r0 = new Builder();
        if (r12.has("fidoAppIdExtension") == false) goto L6;
        r0.setFido2Extension(new FidoAppIdExtension(r12.getJSONObject("fidoAppIdExtension").getString("appid")));
    L6:
        if (r12.has("appid") == false) goto L8;
        r0.setFido2Extension(new FidoAppIdExtension(r12.getString("appid")));
    L8:
        int r3 = 0;
        if (r12.has("prf") == false) goto L16;
        if (r12.has("prfAlreadyHashed") == true) goto L14;
        r0.zzg(zzak.zza(r12.getJSONObject("prf"), false));
    L19:
        if (r12.has("cableAuthenticationExtension") == false) goto L26;
        JSONArray r1 = r12.getJSONArray("cableAuthenticationExtension");
        ArrayList r2 = new ArrayList();
    L22:
        if (r3 >= r1.length()) goto L24;
        JSONObject r4 = r1.getJSONObject(r3);
        r2.add(new zzq(r4.getLong("version"), Base64.decode(r4.getString("clientEid"), 11), Base64.decode(r4.getString("authenticatorEid"), 11), Base64.decode(r4.getString("sessionPreKey"), 11)));
        r3 = r3 + 1;
        goto L22
    L24:
        r0.zza(new zzs(r2));
    L26:
        if (r12.has("userVerificationMethodExtension") == false) goto L29;
        r0.setUserVerificationMethodExtension(new UserVerificationMethodExtension(r12.getJSONObject("userVerificationMethodExtension").getBoolean("uvm")));
    L29:
        if (r12.has("google_multiAssertionExtension") == false) goto L32;
        r0.zzc(new zzz(r12.getJSONObject("google_multiAssertionExtension").getBoolean("requestForMultiAssertion")));
    L32:
        if (r12.has("google_sessionIdExtension") == false) goto L35;
        r0.zzd(new zzab(r12.getJSONObject("google_sessionIdExtension").getInt("sessionId")));
    L35:
        if (r12.has("google_silentVerificationExtension") == false) goto L38;
        r0.zze(new zzad(r12.getJSONObject("google_silentVerificationExtension").getBoolean("silentVerification")));
    L38:
        if (r12.has("devicePublicKeyExtension") == false) goto L41;
        r0.zzb(new zzu(r12.getJSONObject("devicePublicKeyExtension").getBoolean("devicePublicKey")));
    L41:
        if (r12.has("google_tunnelServerIdExtension") == false) goto L44;
        r0.zzf(new zzag(r12.getJSONObject("google_tunnelServerIdExtension").getString("tunnelServerId")));
    L44:
        if (r12.has("google_thirdPartyPaymentExtension") == false) goto L47;
        r0.setGoogleThirdPartyPaymentExtension(new GoogleThirdPartyPaymentExtension(r12.getJSONObject("google_thirdPartyPaymentExtension").getBoolean("thirdPartyPayment")));
    L47:
        if (r12.has("txAuthSimple") == false) goto L50;
        r0.zzh(new zzaw(r12.getString("txAuthSimple")));
    L50:
        return r0.build();
    L14:
        throw new JSONException("both prf and prfAlreadyHashed extensions found");
    L16:
        if (r12.has("prfAlreadyHashed") == false) goto L19;
        r0.zzg(zzak.zza(r12.getJSONObject("prfAlreadyHashed"), true));
        goto L19
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof AuthenticationExtensions) == true) goto L5;
        return false;
    L5:
        AuthenticationExtensions r42 = (AuthenticationExtensions) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L31:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L31;
        if (Objects.equal(this.zzc, r42.zzc) == false) goto L31;
        if (Objects.equal(this.zzd, r42.zzd) == false) goto L31;
        if (Objects.equal(this.zze, r42.zze) == false) goto L31;
        if (Objects.equal(this.zzf, r42.zzf) == false) goto L31;
        if (Objects.equal(this.zzg, r42.zzg) == false) goto L31;
        if (Objects.equal(this.zzh, r42.zzh) == false) goto L31;
        if (Objects.equal(this.zzi, r42.zzi) == false) goto L31;
        if (Objects.equal(this.zzj, r42.zzj) == false) goto L31;
        if (Objects.equal(this.zzk, r42.zzk) == false) goto L31;
        if (Objects.equal(this.zzl, r42.zzl) == false) goto L31;
        return true;
    }

    public FidoAppIdExtension getFidoAppIdExtension() {
        return this.zza;
    }

    public UserVerificationMethodExtension getUserVerificationMethodExtension() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl});
    }

    public final String toString() {
        zzaw r0 = this.zzk;
        zzak r1 = this.zzj;
        GoogleThirdPartyPaymentExtension r2 = this.zzi;
        zzag r3 = this.zzh;
        zzu r4 = this.zzg;
        zzad r5 = this.zzf;
        zzab r6 = this.zze;
        zzz r7 = this.zzd;
        UserVerificationMethodExtension r8 = this.zzc;
        zzs r9 = this.zzb;
        return "AuthenticationExtensions{\n fidoAppIdExtension=" + String.valueOf(this.zza) + ", \n cableAuthenticationExtension=" + String.valueOf(r9) + ", \n userVerificationMethodExtension=" + String.valueOf(r8) + ", \n googleMultiAssertionExtension=" + String.valueOf(r7) + ", \n googleSessionIdExtension=" + String.valueOf(r6) + ", \n googleSilentVerificationExtension=" + String.valueOf(r5) + ", \n devicePublicKeyExtension=" + String.valueOf(r4) + ", \n googleTunnelServerIdExtension=" + String.valueOf(r3) + ", \n googleThirdPartyPaymentExtension=" + String.valueOf(r2) + ", \n prfExtension=" + String.valueOf(r1) + ", \n simpleTransactionAuthorizationExtension=" + String.valueOf(r0) + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, getFidoAppIdExtension(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzb, r6, false);
        SafeParcelWriter.writeParcelable(r5, 4, getUserVerificationMethodExtension(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 5, this.zzd, r6, false);
        SafeParcelWriter.writeParcelable(r5, 6, this.zze, r6, false);
        SafeParcelWriter.writeParcelable(r5, 7, this.zzf, r6, false);
        SafeParcelWriter.writeParcelable(r5, 8, this.zzg, r6, false);
        SafeParcelWriter.writeParcelable(r5, 9, this.zzh, r6, false);
        SafeParcelWriter.writeParcelable(r5, 10, this.zzi, r6, false);
        SafeParcelWriter.writeParcelable(r5, 11, this.zzj, r6, false);
        SafeParcelWriter.writeParcelable(r5, 12, this.zzk, r6, false);
        SafeParcelWriter.writeParcelable(r5, 13, this.zzl, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
