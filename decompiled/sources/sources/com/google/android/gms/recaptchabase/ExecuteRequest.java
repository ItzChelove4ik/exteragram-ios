package com.google.android.gms.recaptchabase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class ExecuteRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ExecuteRequest> CREATOR = null;
    private String zza;
    private String zzb;

    public static final class Builder {
        private final ExecuteRequest zza;

        public Builder() {
            this.zza = new ExecuteRequest(null);
        }

        public ExecuteRequest build() {
            return this.zza;
        }

        public Builder setExecuteId(String r2) {
            ExecuteRequest.zzc(this.zza, r2);
            return this;
        }

        public Builder setNonce(String r2) {
            ExecuteRequest.zzd(this.zza, r2);
            return this;
        }
    }

    static {
        CREATOR = new zzb();
    }

    public /* synthetic */ ExecuteRequest(zza r1) {
    }

    public ExecuteRequest(String r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static /* bridge */ /* synthetic */ void zzc(ExecuteRequest r0, String r1) {
        r0.zzb = r1;
    }

    public static /* bridge */ /* synthetic */ void zzd(ExecuteRequest r0, String r1) {
        r0.zza = r1;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ExecuteRequest) == false) goto L12;
        ExecuteRequest r52 = (ExecuteRequest) r5;
        if (Objects.equal(this.zza, r52.zza) == false) goto L12;
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public String getExecuteId() {
        return this.zzb;
    }

    public String getNonce() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, getNonce(), false);
        SafeParcelWriter.writeString(r4, 2, getExecuteId(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
