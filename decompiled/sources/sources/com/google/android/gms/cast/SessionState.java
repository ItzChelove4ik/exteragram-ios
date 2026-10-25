package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class SessionState extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SessionState> CREATOR = null;
    String zza;
    private final MediaLoadRequestData zzb;
    private final JSONObject zzc;

    public static class Builder {
        private MediaLoadRequestData zza;
        private JSONObject zzb;

        public Builder() {
        }

        public SessionState build() {
            return new SessionState(this.zza, this.zzb);
        }

        public Builder setCustomData(JSONObject r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setLoadRequestData(MediaLoadRequestData r1) {
            this.zza = r1;
            return this;
        }
    }

    static {
        CREATOR = new zzde();
    }

    public SessionState(MediaLoadRequestData r1, JSONObject r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    public static SessionState fromJson(JSONObject r2) {
        JSONObject r0 = r2.optJSONObject("loadRequestData");
        if (r0 == null) goto L5;
        MediaLoadRequestData r02 = MediaLoadRequestData.fromJson(r0);
    L7:
        return new SessionState(r02, r2.optJSONObject("customData"));
    L5:
        r02 = null;
        goto L7
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof SessionState) == true) goto L8;
        return false;
    L8:
        SessionState r42 = (SessionState) r4;
        if (JsonUtils.areJsonValuesEquivalent(this.zzc, r42.zzc) == true) goto L12;
        return false;
    L12:
        return Objects.equal(this.zzb, r42.zzb);
    }

    public JSONObject getCustomData() {
        return this.zzc;
    }

    public MediaLoadRequestData getLoadRequestData() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zzb, String.valueOf(this.zzc)});
    }

    public JSONObject toJson() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L7
        MediaLoadRequestData r1 = this.zzb;     // Catch: JSONException -> L7
        if (r1 == null) goto L5;
        r0.put("loadRequestData", r1.toJson());     // Catch: JSONException -> L7
    L5:
        r0.put("customData", this.zzc);     // Catch: JSONException -> L7
        return r0;
    L7:
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        JSONObject r0 = this.zzc;
        if (r0 != null) goto L5;
        String r02 = null;
    L6:
        this.zza = r02;
        int r03 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, getLoadRequestData(), r6, false);
        SafeParcelWriter.writeString(r5, 3, this.zza, false);
        SafeParcelWriter.finishObjectHeader(r5, r03);
        return;
    L5:
        r02 = r0.toString();
        goto L6
    }
}
