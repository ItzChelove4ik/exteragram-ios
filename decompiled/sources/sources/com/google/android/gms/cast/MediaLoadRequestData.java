package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.MediaQueueData;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaLoadRequestData extends AbstractSafeParcelable implements RequestData {
    public static final Parcelable.Creator<MediaLoadRequestData> CREATOR = null;
    public static final double PLAYBACK_RATE_MAX = 2.0d;
    public static final double PLAYBACK_RATE_MIN = 0.5d;
    public static final long PLAY_POSITION_UNASSIGNED = -1;
    private static final Logger zzb = null;
    String zza;
    private final MediaInfo zzc;
    private final MediaQueueData zzd;
    private final Boolean zze;
    private final long zzf;
    private final double zzg;
    private final long[] zzh;
    private final JSONObject zzi;
    private final String zzj;
    private final String zzk;
    private final String zzl;
    private final String zzm;
    private long zzn;

    public static class Builder {
        private MediaInfo zza;
        private MediaQueueData zzb;
        private Boolean zzc;
        private long zzd;
        private double zze;
        private long[] zzf;
        private JSONObject zzg;
        private String zzh;
        private String zzi;
        private String zzj;
        private String zzk;
        private long zzl;

        public Builder() {
            this.zzc = Boolean.TRUE;
            this.zzd = -1;
            this.zze = 1.0d;
        }

        public Builder(MediaLoadRequestData r3) {
            this.zzc = Boolean.TRUE;
            this.zzd = -1;
            this.zze = 1.0d;
            this.zza = r3.getMediaInfo();
            this.zzb = r3.getQueueData();
            this.zzc = r3.getAutoplay();
            this.zzd = r3.getCurrentTime();
            this.zze = r3.getPlaybackRate();
            this.zzf = r3.getActiveTrackIds();
            this.zzg = r3.getCustomData();
            this.zzh = r3.getCredentials();
            this.zzi = r3.getCredentialsType();
            this.zzj = r3.zza();
            this.zzk = r3.zzb();
            this.zzl = r3.getRequestId();
        }

        public MediaLoadRequestData build() {
            return new MediaLoadRequestData(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, null);
        }

        public Builder setActiveTrackIds(long[] r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setAtvCredentials(String r1) {
            this.zzj = r1;
            return this;
        }

        public Builder setAtvCredentialsType(String r1) {
            this.zzk = r1;
            return this;
        }

        public Builder setAutoplay(Boolean r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setCredentials(String r1) {
            this.zzh = r1;
            return this;
        }

        public Builder setCredentialsType(String r1) {
            this.zzi = r1;
            return this;
        }

        public Builder setCurrentTime(long r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setCustomData(JSONObject r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setMediaInfo(MediaInfo r1) {
            this.zza = r1;
            return this;
        }

        public Builder setPlaybackRate(double r3) {
            if (Double.compare(r3, 2.0d) <= 0) goto L5;
        L8:
            f$$ExternalSyntheticBUOutline1.m("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
            return null;
        L5:
            if (Double.compare(r3, 0.5d) < 0) goto L8;
            this.zze = r3;
            return this;
        }

        public Builder setQueueData(MediaQueueData r1) {
            this.zzb = r1;
            return this;
        }

        public final Builder zza(long r1) {
            this.zzl = r1;
            return this;
        }
    }

    static {
        zzb = new Logger("MediaLoadRequestData");
        CREATOR = new zzbt();
    }

    public MediaLoadRequestData(MediaInfo r17, MediaQueueData r18, Boolean r19, long r20, double r22, long[] r24, String r25, String r26, String r27, String r28, String r29, long r30) {
        this(r17, r18, r19, r20, r22, r24, CastUtils.jsonStringToJsonObject(r25), r26, r27, r28, r29, r30);
    }

    private MediaLoadRequestData(MediaInfo r1, MediaQueueData r2, Boolean r3, long r4, double r6, long[] r8, JSONObject r9, String r10, String r11, String r12, String r13, long r14) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzg = r6;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
        this.zzk = r11;
        this.zzl = r12;
        this.zzm = r13;
        this.zzn = r14;
    }

    public /* synthetic */ MediaLoadRequestData(MediaInfo r1, MediaQueueData r2, Boolean r3, long r4, double r6, long[] r8, JSONObject r9, String r10, String r11, String r12, String r13, long r14, byte[] r16) {
        this(r1, r2, r3, r4, r6, r8, r9, r10, r11, r12, r13, r14);
    }

    public static MediaLoadRequestData fromJson(JSONObject r7) {
        Builder r4 = new Builder();
        if (r7.has("media") == false) goto L7;
        r4.setMediaInfo(new MediaInfo(r7.getJSONObject("media")));     // Catch: JSONException -> L26
    L7:
        if (r7.has("queueData") == false) goto L10;
        MediaQueueData.Builder r3 = new MediaQueueData.Builder();     // Catch: JSONException -> L26
        r3.zza(r7.getJSONObject("queueData"));     // Catch: JSONException -> L26
        r4.setQueueData(r3.build());     // Catch: JSONException -> L26
    L10:
        if (r7.has("autoplay") == false) goto L12;
        r4.setAutoplay(Boolean.valueOf(r7.getBoolean("autoplay")));     // Catch: JSONException -> L26
    L14:
        if (r7.has("currentTime") == false) goto L16;
        r4.setCurrentTime(CastUtils.secToMillisec(r7.getDouble("currentTime")));     // Catch: JSONException -> L26
    L17:
        r4.setPlaybackRate(r7.optDouble("playbackRate", 1.0d));     // Catch: JSONException -> L26
        r4.setCredentials(CastUtils.optStringOrNull(r7, "credentials"));     // Catch: JSONException -> L26
        r4.setCredentialsType(CastUtils.optStringOrNull(r7, "credentialsType"));     // Catch: JSONException -> L26
        r4.setAtvCredentials(CastUtils.optStringOrNull(r7, "atvCredentials"));     // Catch: JSONException -> L26
        r4.setAtvCredentialsType(CastUtils.optStringOrNull(r7, "atvCredentialsType"));     // Catch: JSONException -> L26
        r4.zza(r7.optLong("requestId"));     // Catch: JSONException -> L26
        JSONArray r0 = r7.optJSONArray("activeTrackIds");     // Catch: JSONException -> L26
        if (r0 == null) goto L24;
        long[] r1 = new long[r0.length()];     // Catch: JSONException -> L26
        int r2 = 0;
    L21:
        if (r2 >= r0.length()) goto L23;
        r1[r2] = r0.getLong(r2);     // Catch: JSONException -> L26
        r2 = r2 + 1;     // Catch: JSONException -> L26
        goto L21
    L23:
        r4.setActiveTrackIds(r1);     // Catch: JSONException -> L26
    L24:
        r4.setCustomData(r7.optJSONObject("customData"));     // Catch: JSONException -> L26
        return r4.build();
    L16:
        r4.setCurrentTime(-1);     // Catch: JSONException -> L26
        goto L17
    L12:
        r4.setAutoplay(null);     // Catch: JSONException -> L26
    L27:
        return r4.build();
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof MediaLoadRequestData) == true) goto L8;
        return false;
    L8:
        MediaLoadRequestData r82 = (MediaLoadRequestData) r8;
        if (JsonUtils.areJsonValuesEquivalent(this.zzi, r82.zzi) == true) goto L12;
        return false;
    L12:
        if (Objects.equal(this.zzc, r82.zzc) == true) goto L14;
    L34:
        return false;
    L14:
        if (Objects.equal(this.zzd, r82.zzd) == false) goto L34;
        if (Objects.equal(this.zze, r82.zze) == false) goto L34;
        if (this.zzf != r82.zzf) goto L34;
        if (this.zzg != r82.zzg) goto L34;
        if (Arrays.equals(this.zzh, r82.zzh) == false) goto L34;
        if (Objects.equal(this.zzj, r82.zzj) == false) goto L34;
        if (Objects.equal(this.zzk, r82.zzk) == false) goto L34;
        if (Objects.equal(this.zzl, r82.zzl) == false) goto L34;
        if (Objects.equal(this.zzm, r82.zzm) == false) goto L34;
        if (this.zzn != r82.zzn) goto L34;
        return true;
    }

    public long[] getActiveTrackIds() {
        return this.zzh;
    }

    public Boolean getAutoplay() {
        return this.zze;
    }

    public String getCredentials() {
        return this.zzj;
    }

    public String getCredentialsType() {
        return this.zzk;
    }

    public long getCurrentTime() {
        return this.zzf;
    }

    @Override // com.google.android.gms.cast.RequestData
    public JSONObject getCustomData() {
        return this.zzi;
    }

    public MediaInfo getMediaInfo() {
        return this.zzc;
    }

    public double getPlaybackRate() {
        return this.zzg;
    }

    public MediaQueueData getQueueData() {
        return this.zzd;
    }

    @Override // com.google.android.gms.cast.RequestData
    public long getRequestId() {
        return this.zzn;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zzc, this.zzd, this.zze, Long.valueOf(this.zzf), Double.valueOf(this.zzg), this.zzh, String.valueOf(this.zzi), this.zzj, this.zzk, this.zzl, this.zzm, Long.valueOf(this.zzn)});
    }

    public void setRequestId(long r1) {
        this.zzn = r1;
    }

    public JSONObject toJson() {
        JSONObject r0 = new JSONObject();
        MediaInfo r1 = this.zzc;     // Catch: JSONException -> L21
        if (r1 == null) goto L6;
        r0.put("media", r1.zzb());     // Catch: JSONException -> L21
    L6:
        MediaQueueData r12 = this.zzd;     // Catch: JSONException -> L21
        if (r12 == null) goto L9;
        r0.put("queueData", r12.zzb());     // Catch: JSONException -> L21
    L9:
        r0.putOpt("autoplay", this.zze);     // Catch: JSONException -> L21
        long r13 = this.zzf;     // Catch: JSONException -> L21
        if (r13 == (-1)) goto L12;
        r0.put("currentTime", CastUtils.millisecToSec(r13));     // Catch: JSONException -> L21
    L12:
        r0.put("playbackRate", this.zzg);     // Catch: JSONException -> L21
        r0.putOpt("credentials", this.zzj);     // Catch: JSONException -> L21
        r0.putOpt("credentialsType", this.zzk);     // Catch: JSONException -> L21
        r0.putOpt("atvCredentials", this.zzl);     // Catch: JSONException -> L21
        r0.putOpt("atvCredentialsType", this.zzm);     // Catch: JSONException -> L21
        long[] r14 = this.zzh;     // Catch: JSONException -> L21
        if (r14 == null) goto L19;
        JSONArray r2 = new JSONArray();     // Catch: JSONException -> L21
        int r3 = 0;
    L16:
        if (r3 >= r14.length) goto L18;
        r2.put(r3, r14[r3]);     // Catch: JSONException -> L21
        r3 = r3 + 1;     // Catch: JSONException -> L21
        goto L16
    L18:
        r0.put("activeTrackIds", r2);     // Catch: JSONException -> L21
    L19:
        r0.putOpt("customData", this.zzi);     // Catch: JSONException -> L21
        r0.put("requestId", this.zzn);     // Catch: JSONException -> L21
        return r0;
    L21:
        e = move-exception;
        zzb.e("Error transforming MediaLoadRequestData into JSONObject", new Object[]{e});
        return new JSONObject();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        JSONObject r0 = this.zzi;
        if (r0 != null) goto L5;
        String r02 = null;
    L6:
        this.zza = r02;
        int r03 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, getMediaInfo(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, getQueueData(), r6, false);
        SafeParcelWriter.writeBooleanObject(r5, 4, getAutoplay(), false);
        SafeParcelWriter.writeLong(r5, 5, getCurrentTime());
        SafeParcelWriter.writeDouble(r5, 6, getPlaybackRate());
        SafeParcelWriter.writeLongArray(r5, 7, getActiveTrackIds(), false);
        SafeParcelWriter.writeString(r5, 8, this.zza, false);
        SafeParcelWriter.writeString(r5, 9, getCredentials(), false);
        SafeParcelWriter.writeString(r5, 10, getCredentialsType(), false);
        SafeParcelWriter.writeString(r5, 11, this.zzl, false);
        SafeParcelWriter.writeString(r5, 12, this.zzm, false);
        SafeParcelWriter.writeLong(r5, 13, getRequestId());
        SafeParcelWriter.finishObjectHeader(r5, r03);
        return;
    L5:
        r02 = r0.toString();
        goto L6
    }

    public final String zza() {
        return this.zzl;
    }

    public final String zzb() {
        return this.zzm;
    }
}
