package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class CredentialsData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CredentialsData> CREATOR = null;
    public static final String CREDENTIALS_TYPE_ANDROID = "android";
    public static final String CREDENTIALS_TYPE_CLOUD = "cloud";
    public static final String CREDENTIALS_TYPE_IOS = "ios";
    public static final String CREDENTIALS_TYPE_WEB = "web";
    private final String zza;
    private final String zzb;

    public static class Builder {
        private String zza;
        private String zzb;

        public Builder() {
            this.zzb = "android";
        }

        public CredentialsData build() {
            return new CredentialsData(this.zza, this.zzb);
        }

        public Builder setCredentials(String r1) {
            this.zza = r1;
            return this;
        }

        public Builder setCredentialsType(String r1) {
            this.zzb = r1;
            return this;
        }
    }

    static {
        CREATOR = new zzal();
    }

    public CredentialsData(String r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static CredentialsData fromJson(JSONObject r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        return new CredentialsData(CastUtils.optStringOrNull(r2, "credentials"), CastUtils.optStringOrNull(r2, "credentialsType"));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CredentialsData) == true) goto L8;
        return false;
    L8:
        CredentialsData r52 = (CredentialsData) r5;
        if (Objects.equal(this.zza, r52.zza) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L13;
        return true;
    }

    public String getCredentials() {
        return this.zza;
    }

    public String getCredentialsType() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, getCredentials(), false);
        SafeParcelWriter.writeString(r4, 2, getCredentialsType(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
