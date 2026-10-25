package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class VastAdsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VastAdsRequest> CREATOR = null;
    private final String zza;
    private final String zzb;

    public static class Builder {
        private String zza;
        private String zzb;

        public Builder() {
        }

        public VastAdsRequest build() {
            return new VastAdsRequest(this.zza, this.zzb);
        }

        public Builder setAdTagUrl(String r1) {
            this.zza = r1;
            return this;
        }

        public Builder setAdsResponse(String r1) {
            this.zzb = r1;
            return this;
        }
    }

    static {
        CREATOR = new zzdg();
    }

    public VastAdsRequest(String r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static VastAdsRequest fromJson(JSONObject r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        return new VastAdsRequest(CastUtils.optStringOrNull(r2, "adTagUrl"), CastUtils.optStringOrNull(r2, "adsResponse"));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof VastAdsRequest) == true) goto L8;
        return false;
    L8:
        VastAdsRequest r52 = (VastAdsRequest) r5;
        if (CastUtils.zza(this.zza, r52.zza) == true) goto L11;
    L13:
        return false;
    L11:
        if (CastUtils.zza(this.zzb, r52.zzb) == false) goto L13;
        return true;
    }

    public String getAdTagUrl() {
        return this.zza;
    }

    public String getAdsResponse() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, getAdTagUrl(), false);
        SafeParcelWriter.writeString(r4, 3, getAdsResponse(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();
        String r1 = this.zza;     // Catch: JSONException -> L10
        if (r1 == null) goto L6;
        r0.put("adTagUrl", r1);     // Catch: JSONException -> L10
    L6:
        String r3 = this.zzb;     // Catch: JSONException -> L10
        if (r3 == null) goto L9;
        r0.put("adsResponse", r3);     // Catch: JSONException -> L10
    L9:
        return r0;
    }
}
