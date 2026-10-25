package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AdBreakClipInfo extends AbstractSafeParcelable {
    public static final long AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakClipInfo> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final long zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private String zzg;
    private final String zzh;
    private final String zzi;
    private final long zzj;

    @HlsSegmentFormat
    private final String zzk;
    private final VastAdsRequest zzl;
    private JSONObject zzm;

    public static class Builder {
        private final String zza;
        private String zzb;
        private long zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private String zzg;
        private String zzh;
        private String zzi;
        private long zzj;

        @HlsSegmentFormat
        private String zzk;
        private VastAdsRequest zzl;

        public Builder(String r3) {
            this.zzj = -1;
            this.zza = r3;
        }

        public AdBreakClipInfo build() {
            return new AdBreakClipInfo(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl);
        }

        public Builder setClickThroughUrl(String r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setContentId(String r1) {
            this.zzh = r1;
            return this;
        }

        public Builder setContentUrl(String r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setCustomDataJsonString(String r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setDurationInMs(long r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setHlsSegmentFormat(String r1) {
            this.zzk = r1;
            return this;
        }

        public Builder setImageUrl(String r1) {
            this.zzi = r1;
            return this;
        }

        public Builder setMimeType(String r1) {
            this.zze = r1;
            return this;
        }

        public Builder setTitle(String r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setVastAdsRequest(VastAdsRequest r1) {
            this.zzl = r1;
            return this;
        }

        public Builder setWhenSkippableInMs(long r1) {
            this.zzj = r1;
            return this;
        }
    }

    static {
        CREATOR = new zza();
    }

    public AdBreakClipInfo(String r1, String r2, long r3, String r5, String r6, String r7, String r8, String r9, String r10, long r11, @HlsSegmentFormat String r13, VastAdsRequest r14) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi = r10;
        this.zzj = r11;
        this.zzk = r13;
        this.zzl = r14;
        if (TextUtils.isEmpty(r8) == false) goto L11;
        this.zzm = new JSONObject();
        return;
    L11:
        this.zzm = new JSONObject(this.zzg);     // Catch: JSONException -> L6
        return;
    L6:
        e = move-exception;
        Log.w("AdBreakClipInfo", String.format(Locale.ROOT, "Error creating AdBreakClipInfo: %s", new Object[]{e.getMessage()}));
        this.zzg = null;
        this.zzm = new JSONObject();
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof AdBreakClipInfo) == true) goto L8;
        return false;
    L8:
        AdBreakClipInfo r82 = (AdBreakClipInfo) r8;
        if (CastUtils.zza(this.zza, r82.zza) == true) goto L11;
    L33:
        return false;
    L11:
        if (CastUtils.zza(this.zzb, r82.zzb) == false) goto L33;
        if (this.zzc != r82.zzc) goto L33;
        if (CastUtils.zza(this.zzd, r82.zzd) == false) goto L33;
        if (CastUtils.zza(this.zze, r82.zze) == false) goto L33;
        if (CastUtils.zza(this.zzf, r82.zzf) == false) goto L33;
        if (CastUtils.zza(this.zzg, r82.zzg) == false) goto L33;
        if (CastUtils.zza(this.zzh, r82.zzh) == false) goto L33;
        if (CastUtils.zza(this.zzi, r82.zzi) == false) goto L33;
        if (this.zzj != r82.zzj) goto L33;
        if (CastUtils.zza(this.zzk, r82.zzk) == false) goto L33;
        if (CastUtils.zza(this.zzl, r82.zzl) == false) goto L33;
        return true;
    }

    public String getClickThroughUrl() {
        return this.zzf;
    }

    public String getContentId() {
        return this.zzh;
    }

    public String getContentUrl() {
        return this.zzd;
    }

    public JSONObject getCustomData() {
        return this.zzm;
    }

    public long getDurationInMs() {
        return this.zzc;
    }

    public String getHlsSegmentFormat() {
        return this.zzk;
    }

    public String getId() {
        return this.zza;
    }

    public String getImageUrl() {
        return this.zzi;
    }

    public String getMimeType() {
        return this.zze;
    }

    public String getTitle() {
        return this.zzb;
    }

    public VastAdsRequest getVastAdsRequest() {
        return this.zzl;
    }

    public long getWhenSkippableInMs() {
        return this.zzj;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, Long.valueOf(this.zzc), this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, Long.valueOf(this.zzj), this.zzk, this.zzl});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r7, int r8) {
        int r0 = SafeParcelWriter.beginObjectHeader(r7);
        SafeParcelWriter.writeString(r7, 2, getId(), false);
        SafeParcelWriter.writeString(r7, 3, getTitle(), false);
        SafeParcelWriter.writeLong(r7, 4, getDurationInMs());
        SafeParcelWriter.writeString(r7, 5, getContentUrl(), false);
        SafeParcelWriter.writeString(r7, 6, getMimeType(), false);
        SafeParcelWriter.writeString(r7, 7, getClickThroughUrl(), false);
        SafeParcelWriter.writeString(r7, 8, this.zzg, false);
        SafeParcelWriter.writeString(r7, 9, getContentId(), false);
        SafeParcelWriter.writeString(r7, 10, getImageUrl(), false);
        SafeParcelWriter.writeLong(r7, 11, getWhenSkippableInMs());
        SafeParcelWriter.writeString(r7, 12, getHlsSegmentFormat(), false);
        SafeParcelWriter.writeParcelable(r7, 13, getVastAdsRequest(), r8, false);
        SafeParcelWriter.finishObjectHeader(r7, r0);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();
        r0.put("id", this.zza);     // Catch: JSONException -> L34
        r0.put("duration", CastUtils.millisecToSec(this.zzc));     // Catch: JSONException -> L34
        long r1 = this.zzj;     // Catch: JSONException -> L34
        if (r1 == (-1)) goto L6;
        r0.put("whenSkippable", CastUtils.millisecToSec(r1));     // Catch: JSONException -> L34
    L6:
        String r12 = this.zzh;     // Catch: JSONException -> L34
        if (r12 == null) goto L9;
        r0.put("contentId", r12);     // Catch: JSONException -> L34
    L9:
        String r13 = this.zze;     // Catch: JSONException -> L34
        if (r13 == null) goto L12;
        r0.put("contentType", r13);     // Catch: JSONException -> L34
    L12:
        String r14 = this.zzb;     // Catch: JSONException -> L34
        if (r14 == null) goto L15;
        r0.put("title", r14);     // Catch: JSONException -> L34
    L15:
        String r15 = this.zzd;     // Catch: JSONException -> L34
        if (r15 == null) goto L18;
        r0.put("contentUrl", r15);     // Catch: JSONException -> L34
    L18:
        String r16 = this.zzf;     // Catch: JSONException -> L34
        if (r16 == null) goto L21;
        r0.put("clickThroughUrl", r16);     // Catch: JSONException -> L34
    L21:
        JSONObject r17 = this.zzm;     // Catch: JSONException -> L34
        if (r17 == null) goto L24;
        r0.put("customData", r17);     // Catch: JSONException -> L34
    L24:
        String r18 = this.zzi;     // Catch: JSONException -> L34
        if (r18 == null) goto L27;
        r0.put("posterUrl", r18);     // Catch: JSONException -> L34
    L27:
        String r19 = this.zzk;     // Catch: JSONException -> L34
        if (r19 == null) goto L30;
        r0.put("hlsSegmentFormat", r19);     // Catch: JSONException -> L34
    L30:
        VastAdsRequest r5 = this.zzl;     // Catch: JSONException -> L34
        if (r5 == null) goto L33;
        r0.put("vastAdsRequest", r5.zza());     // Catch: JSONException -> L34
    L33:
        return r0;
    }
}
