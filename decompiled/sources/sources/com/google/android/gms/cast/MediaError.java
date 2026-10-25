package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaError extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaError> CREATOR = null;
    public static final String ERROR_REASON_APP_ERROR = "APP_ERROR";
    public static final String ERROR_REASON_AUTHENTICATION_EXPIRED = "AUTHENTICATION_EXPIRED";
    public static final String ERROR_REASON_CONCURRENT_STREAM_LIMIT = "CONCURRENT_STREAM_LIMIT";
    public static final String ERROR_REASON_CONTENT_ALREADY_PLAYING = "CONTENT_ALREADY_PLAYING";
    public static final String ERROR_REASON_CONTENT_FILTERED = "CONTENT_FILTERED";
    public static final String ERROR_REASON_DUPLICATE_REQUEST_ID = "DUPLICATE_REQUEST_ID";
    public static final String ERROR_REASON_END_OF_QUEUE = "END_OF_QUEUE";
    public static final String ERROR_REASON_GENERIC_LOAD_ERROR = "GENERIC_LOAD_ERROR";
    public static final String ERROR_REASON_INVALID_COMMAND = "INVALID_COMMAND";
    public static final String ERROR_REASON_INVALID_MEDIA_SESSION_ID = "INVALID_MEDIA_SESSION_ID";
    public static final String ERROR_REASON_INVALID_PARAMS = "INVALID_PARAMS";
    public static final String ERROR_REASON_INVALID_REQUEST = "INVALID_REQUEST";
    public static final String ERROR_REASON_LANGUAGE_NOT_SUPPORTED = "LANGUAGE_NOT_SUPPORTED";
    public static final String ERROR_REASON_NOT_AVAILABLE_IN_REGION = "NOT_AVAILABLE_IN_REGION";
    public static final String ERROR_REASON_NOT_SUPPORTED = "NOT_SUPPORTED";
    public static final String ERROR_REASON_PARENTAL_CONTROL_RESTRICTED = "PARENTAL_CONTROL_RESTRICTED";
    public static final String ERROR_REASON_PREMIUM_ACCOUNT_REQUIRED = "PREMIUM_ACCOUNT_REQUIRED";
    public static final String ERROR_REASON_SKIP_LIMIT_REACHED = "SKIP_LIMIT_REACHED";
    public static final String ERROR_REASON_VIDEO_DEVICE_REQUIRED = "VIDEO_DEVICE_REQUIRED";
    public static final String ERROR_TYPE_ERROR = "ERROR";
    public static final String ERROR_TYPE_INVALID_PLAYER_STATE = "INVALID_PLAYER_STATE";
    public static final String ERROR_TYPE_INVALID_REQUEST = "INVALID_REQUEST";
    public static final String ERROR_TYPE_LOAD_CANCELLED = "LOAD_CANCELLED";
    public static final String ERROR_TYPE_LOAD_FAILED = "LOAD_FAILED";
    String zza;
    private String zzb;
    private long zzc;

    @DetailedErrorCode
    private final Integer zzd;
    private final String zze;
    private final JSONObject zzf;

    public static class Builder {
        private Integer zza;
        private long zzb;
        private String zzc;
        private JSONObject zzd;
        private String zze;

        public Builder() {
            this.zze = MediaError.ERROR_TYPE_ERROR;
        }

        public MediaError build() {
            String r1 = this.zze;
            if (r1 != null) goto L6;
            r1 = MediaError.ERROR_TYPE_ERROR;
        L6:
            return new MediaError(r1, this.zzb, this.zza, this.zzc, this.zzd);
        }

        public Builder setCustomData(JSONObject r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setDetailedErrorCode(Integer r1) {
            this.zza = r1;
            return this;
        }

        public Builder setReason(String r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setRequestId(long r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setType(String r1) {
            this.zze = r1;
            return this;
        }
    }

    public @interface DetailedErrorCode {
        public static final int APP = 900;
        public static final int BREAK_CLIP_LOADING_ERROR = 901;
        public static final int BREAK_SEEK_INTERCEPTOR_ERROR = 902;
        public static final int DASH_INVALID_SEGMENT_INFO = 423;
        public static final int DASH_MANIFEST_NO_MIMETYPE = 422;
        public static final int DASH_MANIFEST_NO_PERIODS = 421;
        public static final int DASH_MANIFEST_UNKNOWN = 420;
        public static final int DASH_NETWORK = 321;
        public static final int DASH_NO_INIT = 322;
        public static final int GENERIC = 999;
        public static final int HLS_MANIFEST_MASTER = 411;
        public static final int HLS_MANIFEST_PLAYLIST = 412;
        public static final int HLS_NETWORK_INVALID_SEGMENT = 315;
        public static final int HLS_NETWORK_KEY_LOAD = 314;
        public static final int HLS_NETWORK_MASTER_PLAYLIST = 311;
        public static final int HLS_NETWORK_NO_KEY_RESPONSE = 313;
        public static final int HLS_NETWORK_PLAYLIST = 312;
        public static final int HLS_SEGMENT_PARSING = 316;
        public static final int IMAGE_ERROR = 903;
        public static final int LOAD_FAILED = 905;
        public static final int LOAD_INTERRUPTED = 904;
        public static final int MANIFEST_UNKNOWN = 400;
        public static final int MEDIAKEYS_NETWORK = 201;
        public static final int MEDIAKEYS_UNKNOWN = 200;
        public static final int MEDIAKEYS_UNSUPPORTED = 202;
        public static final int MEDIAKEYS_WEBCRYPTO = 203;
        public static final int MEDIA_ABORTED = 101;
        public static final int MEDIA_DECODE = 102;
        public static final int MEDIA_ERROR_MESSAGE = 906;
        public static final int MEDIA_NETWORK = 103;
        public static final int MEDIA_SRC_NOT_SUPPORTED = 104;
        public static final int MEDIA_UNKNOWN = 100;
        public static final int NETWORK_UNKNOWN = 300;
        public static final int SEGMENT_NETWORK = 301;
        public static final int SEGMENT_UNKNOWN = 500;
        public static final int SMOOTH_MANIFEST = 431;
        public static final int SMOOTH_NETWORK = 331;
        public static final int SMOOTH_NO_MEDIA_DATA = 332;
        public static final int SOURCE_BUFFER_FAILURE = 110;
        public static final int TEXT_UNKNOWN = 600;
    }

    static {
        CREATOR = new zzbq();
    }

    public MediaError(String r1, long r2, Integer r4, String r5, JSONObject r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    public static MediaError zza(JSONObject r9) {
        String r3 = r9.optString("type", ERROR_TYPE_ERROR);
        long r4 = r9.optLong("requestId");
        JSONObject r2 = null;
        if (r9.has("detailedErrorCode") == false) goto L5;
        Integer r6 = Integer.valueOf(r9.optInt("detailedErrorCode"));
    L6:
        String r7 = CastUtils.optStringOrNull(r9, "reason");
        if (r9.has("customData") == false) goto L10;
        r2 = r9.optJSONObject("customData");
    L10:
        return new MediaError(r3, r4, r6, r7, r2);
    L5:
        r6 = null;
        goto L6
    }

    public JSONObject getCustomData() {
        return this.zzf;
    }

    public Integer getDetailedErrorCode() {
        return this.zzd;
    }

    public String getReason() {
        return this.zze;
    }

    public long getRequestId() {
        return this.zzc;
    }

    public String getType() {
        return this.zzb;
    }

    public void setRequestId(long r1) {
        this.zzc = r1;
    }

    public void setType(String r1) {
        this.zzb = r1;
    }

    public JSONObject toJson() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L7
        r0.put("requestId", this.zzc);     // Catch: JSONException -> L7
        r0.putOpt("detailedErrorCode", this.zzd);     // Catch: JSONException -> L7
        r0.putOpt("reason", this.zze);     // Catch: JSONException -> L7
        r0.put("customData", this.zzf);     // Catch: JSONException -> L7
        String r4 = this.zzb;     // Catch: JSONException -> L7
        if (r4 != null) goto L5;
        r4 = ERROR_TYPE_ERROR;
    L5:
        r0.putOpt("type", r4);     // Catch: JSONException -> L7
        return r0;
    L8:
        return new JSONObject();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r6, int r7) {
        JSONObject r72 = this.zzf;
        if (r72 != null) goto L5;
        String r73 = null;
    L6:
        this.zza = r73;
        int r74 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeString(r6, 2, getType(), false);
        SafeParcelWriter.writeLong(r6, 3, getRequestId());
        SafeParcelWriter.writeIntegerObject(r6, 4, getDetailedErrorCode(), false);
        SafeParcelWriter.writeString(r6, 5, getReason(), false);
        SafeParcelWriter.writeString(r6, 6, this.zza, false);
        SafeParcelWriter.finishObjectHeader(r6, r74);
        return;
    L5:
        r73 = r72.toString();
        goto L6
    }
}
