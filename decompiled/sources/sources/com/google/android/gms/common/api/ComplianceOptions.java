package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class ComplianceOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ComplianceOptions> CREATOR = null;
    public static final ComplianceOptions zza = null;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final boolean zze;

    public static final class Builder {
        private int zza;
        private int zzb;
        private int zzc;
        private boolean zzd;

        public Builder() {
            this.zza = -1;
            this.zzb = -1;
            this.zzc = 0;
            this.zzd = true;
        }

        public ComplianceOptions build() {
            return new ComplianceOptions(this.zza, this.zzb, this.zzc, this.zzd);
        }

        public Builder setCallerProductId(int r1) {
            this.zza = r1;
            return this;
        }

        public Builder setDataOwnerProductId(int r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setIsUserData(boolean r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setProcessingReason(int r1) {
            this.zzc = r1;
            return this;
        }
    }

    static {
        Builder r0 = newBuilder();
        r0.setCallerProductId(-1);
        r0.setDataOwnerProductId(-1);
        r0.setProcessingReason(0);
        r0.setIsUserData(true);
        zza = r0.build();
        CREATOR = new zzc();
    }

    public ComplianceOptions(int r1, int r2, int r3, boolean r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final Builder newBuilder(Context r0) {
        return newBuilder();
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof ComplianceOptions) == true) goto L5;
        return false;
    L5:
        ComplianceOptions r42 = (ComplianceOptions) r4;
        if (this.zzb == r42.zzb) goto L8;
    L15:
        return false;
    L8:
        if (this.zzc != r42.zzc) goto L15;
        if (this.zzd != r42.zzd) goto L15;
        if (this.zze != r42.zze) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Boolean.valueOf(this.zze)});
    }

    public final String toString() {
        int r0 = this.zzb;
        int r1 = String.valueOf(r0).length();
        int r2 = this.zzc;
        int r3 = String.valueOf(r2).length();
        int r4 = this.zzd;
        int r5 = String.valueOf(r4).length();
        boolean r7 = this.zze;
        int r12 = ((((r1 + 55) + r3) + 19) + r5) + 13;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r7).length()) + 1);
        r32.append("ComplianceOptions{callerProductId=");
        r32.append(r0);
        r32.append(", dataOwnerProductId=");
        r32.append(r2);
        r32.append(", processingReason=");
        r32.append(r4);
        r32.append(", isUserData=");
        r32.append(r7);
        r32.append("}");
        return r32.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = this.zzb;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, r42);
        SafeParcelWriter.writeInt(r3, 2, this.zzc);
        SafeParcelWriter.writeInt(r3, 3, this.zzd);
        SafeParcelWriter.writeBoolean(r3, 4, this.zze);
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }
}
