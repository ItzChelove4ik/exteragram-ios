package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fido.fido2.api.common.Attachment;

/* loaded from: classes4.dex */
public class AuthenticatorSelectionCriteria extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthenticatorSelectionCriteria> CREATOR = null;
    private final Attachment zza;
    private final Boolean zzb;
    private final UserVerificationRequirement zzc;
    private final ResidentKeyRequirement zzd;

    public static class Builder {
        private Attachment zza;
        private Boolean zzb;
        private ResidentKeyRequirement zzc;

        public Builder() {
        }

        public AuthenticatorSelectionCriteria build() {
            Attachment r1 = this.zza;
            if (r1 != null) goto L5;
            String r12 = null;
        L6:
            Boolean r3 = this.zzb;
            ResidentKeyRequirement r4 = this.zzc;
            if (r4 != null) goto L9;
            String r42 = null;
        L11:
            return new AuthenticatorSelectionCriteria(r12, r3, null, r42);
        L9:
            r42 = r4.toString();
            goto L11
        L5:
            r12 = r1.toString();
            goto L6
        }

        public Builder setAttachment(Attachment r1) {
            this.zza = r1;
            return this;
        }

        public Builder setRequireResidentKey(Boolean r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setResidentKeyRequirement(ResidentKeyRequirement r1) {
            this.zzc = r1;
            return this;
        }
    }

    static {
        CREATOR = new zzm();
    }

    public AuthenticatorSelectionCriteria(String r2, Boolean r3, String r4, String r5) {
        if (r2 != null) goto L23;
        Attachment r22 = null;
    L6:
        this.zza = r22;     // Catch: Throwable -> L16 zzbc -> L18 Attachment.UnsupportedAttachmentException -> L20
        this.zzb = r3;     // Catch: Throwable -> L16 zzbc -> L18 Attachment.UnsupportedAttachmentException -> L20
        if (r4 != null) goto L9;
        UserVerificationRequirement r23 = null;
    L10:
        this.zzc = r23;     // Catch: Throwable -> L16 zzbc -> L18 Attachment.UnsupportedAttachmentException -> L20
        if (r5 != null) goto L13;
        ResidentKeyRequirement r24 = null;
    L14:
        this.zzd = r24;     // Catch: Throwable -> L16 zzbc -> L18 Attachment.UnsupportedAttachmentException -> L20
        return;
    L16:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    L13:
        r24 = ResidentKeyRequirement.fromString(r5);     // Catch: Throwable -> L16 zzbc -> L18 Attachment.UnsupportedAttachmentException -> L20
        goto L14
    L9:
        r23 = UserVerificationRequirement.fromString(r4);     // Catch: Throwable -> L16 zzbc -> L18 Attachment.UnsupportedAttachmentException -> L20
        goto L10
    L23:
        r22 = Attachment.fromString(r2);     // Catch: Throwable -> L16 zzbc -> L18 Attachment.UnsupportedAttachmentException -> L20
        goto L6
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof AuthenticatorSelectionCriteria) == true) goto L5;
        return false;
    L5:
        AuthenticatorSelectionCriteria r42 = (AuthenticatorSelectionCriteria) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L15:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L15;
        if (Objects.equal(this.zzc, r42.zzc) == false) goto L15;
        if (Objects.equal(getResidentKeyRequirement(), r42.getResidentKeyRequirement()) == false) goto L15;
        return true;
    }

    public String getAttachmentAsString() {
        Attachment r0 = this.zza;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.toString();
    }

    public Boolean getRequireResidentKey() {
        return this.zzb;
    }

    public ResidentKeyRequirement getResidentKeyRequirement() {
        ResidentKeyRequirement r0 = this.zzd;
        if (r0 != null) goto L11;
        Boolean r1 = this.zzb;
        r0 = null;
        if (r1 == null) goto L11;
        if (r1.booleanValue() == true) goto L10;
        return null;
    L10:
        return ResidentKeyRequirement.RESIDENT_KEY_REQUIRED;
    L11:
        return r0;
    }

    public String getResidentKeyRequirementAsString() {
        ResidentKeyRequirement r0 = getResidentKeyRequirement();
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.toString();
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, getResidentKeyRequirement()});
    }

    public final String toString() {
        ResidentKeyRequirement r0 = this.zzd;
        UserVerificationRequirement r1 = this.zzc;
        return "AuthenticatorSelectionCriteria{\n attachment=" + String.valueOf(this.zza) + ", \n requireResidentKey=" + this.zzb + ", \n requireUserVerification=" + String.valueOf(r1) + ", \n residentKeyRequirement=" + String.valueOf(r0) + "\n }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, getAttachmentAsString(), false);
        SafeParcelWriter.writeBooleanObject(r4, 3, getRequireResidentKey(), false);
        UserVerificationRequirement r0 = this.zzc;
        if (r0 != null) goto L5;
        String r02 = null;
    L6:
        SafeParcelWriter.writeString(r4, 4, r02, false);
        SafeParcelWriter.writeString(r4, 5, getResidentKeyRequirementAsString(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
        return;
    L5:
        r02 = r0.toString();
        goto L6
    }
}
