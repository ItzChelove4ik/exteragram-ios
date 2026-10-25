package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class ApiMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ApiMetadata> CREATOR = null;
    public static final ApiMetadata zza = null;
    private static final ApiMetadata zzb = null;
    private final ComplianceOptions zzc;
    private final boolean zzd;
    private boolean zze;

    public static final class Builder {
        private ComplianceOptions zza;
        private boolean zzb;
        private boolean zzc;

        public Builder() {
            this.zzb = false;
        }

        public ApiMetadata build() {
            ApiMetadata r0 = new ApiMetadata(this.zza, this.zzb);
            r0.zza(this.zzc);
            return r0;
        }

        public Builder setCallbackSupportEnabled(boolean r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setComplianceOptions(ComplianceOptions r1) {
            this.zza = r1;
            return this;
        }

        public final /* synthetic */ Builder zza(boolean r1) {
            this.zzc = r1;
            return this;
        }
    }

    static {
        CREATOR = zza.zza();
        zzb = newBuilder().build();
        Builder r0 = newBuilder();
        r0.zza(true);
        zza = r0.build();
    }

    public ApiMetadata(ComplianceOptions r1, boolean r2) {
        this.zzc = r1;
        this.zzd = r2;
    }

    public static final ApiMetadata fromComplianceOptions(ComplianceOptions r1) {
        Builder r0 = newBuilder();
        r0.setComplianceOptions(r1);
        return r0.build();
    }

    public static final ApiMetadata getEmptyInstance() {
        return zzb;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof ApiMetadata) == true) goto L5;
        return false;
    L5:
        ApiMetadata r42 = (ApiMetadata) r4;
        if (Objects.equal(this.zzc, r42.zzc) == true) goto L8;
    L13:
        return false;
    L8:
        if (this.zze != r42.zze) goto L13;
        if (this.zzd != r42.zzd) goto L13;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzc, Boolean.valueOf(this.zze), Boolean.valueOf(this.zzd)});
    }

    public Builder toBuilder() {
        Builder r0 = newBuilder();
        r0.setComplianceOptions(this.zzc);
        r0.setCallbackSupportEnabled(this.zzd);
        r0.zza(this.zze);
        return r0;
    }

    public final String toString() {
        String r2 = String.valueOf(this.zzc);
        StringBuilder r1 = new StringBuilder(r2.length() + 31);
        r1.append("ApiMetadata(complianceOptions=");
        r1.append(r2);
        r1.append(")");
        return r1.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        if (this.zze == false) goto L6;
        r5.setDataPosition(r5.dataPosition() - 4);
        r5.setDataSize(r5.dataSize() - 4);
        return;
    L6:
        r5.writeInt(-204102970);
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 1, this.zzc, r6, false);
        SafeParcelWriter.writeBoolean(r5, 2, this.zzd);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final /* synthetic */ void zza(boolean r1) {
        this.zze = r1;
    }
}
