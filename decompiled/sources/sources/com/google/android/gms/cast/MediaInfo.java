package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzhs;
import com.google.android.gms.internal.cast.zzhv;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaInfo extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaInfo> CREATOR = null;
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    public static final long UNKNOWN_DURATION = -1;
    public static final long UNKNOWN_START_ABSOLUTE_TIME = -1;
    public static final long zza = 0;
    String zzb;
    private String zzc;
    private int zzd;
    private String zze;
    private MediaMetadata zzf;
    private long zzg;
    private List zzh;
    private TextTrackStyle zzi;
    private List zzj;
    private List zzk;
    private String zzl;
    private VastAdsRequest zzm;
    private long zzn;
    private String zzo;
    private String zzp;

    @HlsSegmentFormat
    private String zzq;

    @HlsVideoSegmentFormat
    private String zzr;
    private JSONObject zzs;
    private final Writer zzt;

    public static class Builder {
        private String zza;
        private int zzb;
        private String zzc;
        private MediaMetadata zzd;
        private long zze;
        private List zzf;
        private TextTrackStyle zzg;
        private String zzh;
        private List zzi;
        private List zzj;
        private String zzk;
        private VastAdsRequest zzl;
        private String zzm;
        private String zzn;

        @HlsSegmentFormat
        private String zzo;

        @HlsVideoSegmentFormat
        private String zzp;

        public Builder() {
            this.zzb = -1;
            this.zze = -1;
        }

        public Builder(String r3) {
            this.zzb = -1;
            this.zze = -1;
            this.zza = r3;
        }

        public Builder(String r3, String r4) {
            this.zzb = -1;
            this.zze = -1;
            this.zza = r3;
            this.zzk = r4;
        }

        public MediaInfo build() {
            return new MediaInfo(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, -1, this.zzm, this.zzn, this.zzo, this.zzp);
        }

        public Builder setAdBreakClips(List<AdBreakClipInfo> r1) {
            this.zzj = r1;
            return this;
        }

        public Builder setAdBreaks(List<AdBreakInfo> r1) {
            this.zzi = r1;
            return this;
        }

        public Builder setAtvEntity(String r1) {
            this.zzm = r1;
            return this;
        }

        public Builder setContentType(String r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setContentUrl(String r1) {
            this.zzn = r1;
            return this;
        }

        public Builder setCustomData(JSONObject r1) {
            if (r1 != null) goto L4;
            String r12 = null;
        L5:
            this.zzh = r12;
            return this;
        L4:
            r12 = r1.toString();
            goto L5
        }

        public Builder setEntity(String r1) {
            this.zzk = r1;
            return this;
        }

        public Builder setHlsSegmentFormat(@HlsSegmentFormat String r1) {
            this.zzo = r1;
            return this;
        }

        public Builder setHlsVideoSegmentFormat(@HlsVideoSegmentFormat String r1) {
            this.zzp = r1;
            return this;
        }

        public Builder setMediaTracks(List<MediaTrack> r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setMetadata(MediaMetadata r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setStreamDuration(long r3) {
            if (r3 < 0) goto L5;
        L9:
            this.zze = r3;
            return this;
        L5:
            if (r3 == (-1)) goto L9;
            f$$ExternalSyntheticBUOutline1.m("Invalid stream duration");
            return null;
        }

        public Builder setStreamType(int r2) {
            if (r2 >= (-1)) goto L5;
        L8:
            f$$ExternalSyntheticBUOutline1.m("invalid stream type");
            return null;
        L5:
            if (r2 > 2) goto L8;
            this.zzb = r2;
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setVmapAdsRequest(VastAdsRequest r1) {
            this.zzl = r1;
            return this;
        }
    }

    public class Writer {
        final /* synthetic */ MediaInfo zza;

        public Writer(MediaInfo r1) {
            Objects.requireNonNull(r1);
            this.zza = r1;
        }

        public void setAdBreakClips(List<AdBreakClipInfo> r1) {
            this.zza.zzk(r1);
        }

        public void setAdBreaks(List<AdBreakInfo> r1) {
            this.zza.zzj(r1);
        }

        public void setContentId(String r1) {
            this.zza.zzc(r1);
        }

        public void setContentType(String r1) {
            this.zza.zze(r1);
        }

        public void setContentUrl(String r1) {
            this.zza.zzo(r1);
        }

        public void setCustomData(JSONObject r1) {
            this.zza.zzr(r1);
        }

        public void setEntity(String r1) {
            this.zza.zzl(r1);
        }

        public void setHlsSegmentFormat(@HlsSegmentFormat String r1) {
            this.zza.zzp(r1);
        }

        public void setHlsVideoSegmentFormat(@HlsVideoSegmentFormat String r1) {
            this.zza.zzq(r1);
        }

        public void setMediaTracks(List<MediaTrack> r1) {
            this.zza.zzh(r1);
        }

        public void setMetadata(MediaMetadata r1) {
            this.zza.zzf(r1);
        }

        public void setStartAbsoluteTime(long r3) {
            if (r3 < 0) goto L5;
        L9:
            this.zza.zzn(r3);
            return;
        L5:
            if (r3 == (-1)) goto L9;
            f$$ExternalSyntheticBUOutline1.m("Invalid start absolute time");
        }

        public void setStreamDuration(long r3) {
            if (r3 < 0) goto L5;
        L9:
            this.zza.zzg(r3);
            return;
        L5:
            if (r3 == (-1)) goto L9;
            f$$ExternalSyntheticBUOutline1.m("Invalid stream duration");
        }

        public void setStreamType(int r2) {
            if (r2 >= (-1)) goto L5;
        L8:
            f$$ExternalSyntheticBUOutline1.m("invalid stream type");
            return;
        L5:
            if (r2 > 2) goto L8;
            this.zza.zzd(r2);
        }

        public void setTextTrackStyle(TextTrackStyle r1) {
            this.zza.zzi(r1);
        }

        public void setVmapAdsRequest(VastAdsRequest r1) {
            this.zza.zzm(r1);
        }
    }

    static {
        zza = CastUtils.secToMillisec(-1);
        CREATOR = new zzbr();
    }

    public MediaInfo(String r3, int r4, String r5, MediaMetadata r6, long r7, List r9, TextTrackStyle r10, String r11, List r12, List r13, String r14, VastAdsRequest r15, long r16, String r18, String r19, @HlsSegmentFormat String r20, @HlsVideoSegmentFormat String r21) {
        this.zzt = new Writer(this);
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r9;
        this.zzi = r10;
        this.zzb = r11;
        if (r11 != null) goto L16;
        this.zzs = null;
    L8:
        this.zzj = r12;
        this.zzk = r13;
        this.zzl = r14;
        this.zzm = r15;
        this.zzn = r16;
        this.zzo = r18;
        this.zzp = r19;
        this.zzq = r20;
        this.zzr = r21;
        if (this.zzc != null) goto L15;
        if (r19 != null) goto L18;
        if (r14 != null) goto L19;
        f$$ExternalSyntheticBUOutline1.m("Either contentID or contentUrl or entity should be set");
        throw null;
    L19:
        return;
    L18:
        return;
    L15:
        return;
    L16:
        this.zzs = new JSONObject(this.zzb);     // Catch: JSONException -> L6
    L6:
        this.zzs = null;
        this.zzb = null;
        goto L8
    }

    public MediaInfo(JSONObject r27) {
        this(r27.optString("contentId"), -1, null, null, -1, null, null, null, null, null, null, null, -1, null, null, null, null);
        String r1 = r27.optString("streamType", "NONE");
        int r5 = 2;
        if ("NONE".equals(r1) == false) goto L6;
        this.zzd = 0;
    L12:
        this.zze = CastUtils.optStringOrNull(r27, "contentType");
        if (r27.has("metadata") == false) goto L15;
        JSONObject r12 = r27.getJSONObject("metadata");
        MediaMetadata r8 = new MediaMetadata(r12.getInt("metadataType"));
        this.zzf = r8;
        r8.zzb(r12);
    L15:
        this.zzg = -1;
        if (this.zzd == 2) goto L29;
        if (r27.has("duration") == false) goto L29;
        if (r27.isNull("duration") == true) goto L29;
        double r13 = r27.optDouble("duration", 0.0d);
        if (Double.isNaN(r13) == true) goto L29;
        if (Double.isInfinite(r13) == true) goto L29;
        if (r13 < 0.0d) goto L29;
        this.zzg = CastUtils.secToMillisec(r13);
    L29:
        if (r27.has("tracks") == false) goto L73;
        ArrayList r2 = new ArrayList();
        JSONArray r14 = r27.getJSONArray("tracks");
        int r122 = 0;
    L32:
        if (r122 >= r14.length()) goto L72;
        JSONObject r132 = r14.getJSONObject(r122);
        String r142 = MediaTrack.ROLE_ALTERNATE;
        long r16 = r132.getLong("trackId");
        String r143 = r132.optString("type");
        int r18 = 3;
        if ("TEXT".equals(r143) == false) goto L37;
        int r144 = 3;
        r18 = 1;
    L43:
        String r19 = CastUtils.optStringOrNull(r132, "trackContentId");
        String r20 = CastUtils.optStringOrNull(r132, "trackContentType");
        String r21 = CastUtils.optStringOrNull(r132, "name");
        String r22 = CastUtils.optStringOrNull(r132, "language");
        if (r132.has("subtype") == false) goto L62;
        String r15 = r132.getString("subtype");
        if ("SUBTITLES".equals(r15) == false) goto L49;
        int r23 = 1;
    L64:
        if (r132.has("roles") == false) goto L70;
        int r145 = zzhv.$r8$clinit;
        zzhs r146 = new zzhs();
        JSONArray r4 = r132.getJSONArray("roles");
        int r152 = 0;
    L67:
        if (r152 >= r4.length()) goto L69;
        r146.zzb(r4.optString(r152));
        r152 = r152 + 1;
        goto L67
    L69:
        zzhv r24 = r146.zzc();
    L71:
        r2.add(new MediaTrack(r16, r18, r19, r20, r21, r22, r23, r24, r132.optJSONObject("customData")));
        r122 = r122 + 1;
        r5 = 2;
        goto L32
    L70:
        r24 = null;
        goto L71
    L49:
        if ("CAPTIONS".equals(r15) == false) goto L52;
        r23 = r5;
        goto L64
    L52:
        if ("DESCRIPTIONS".equals(r15) == false) goto L55;
        r23 = r144;
        goto L64
    L55:
        if ("CHAPTERS".equals(r15) == false) goto L59;
        int r42 = 4;
    L57:
        r23 = r42;
        goto L64
    L59:
        if ("METADATA".equals(r15) == false) goto L61;
        r42 = 5;
        goto L57
    L61:
        r23 = -1;
        goto L64
    L62:
        r23 = 0;
        goto L64
    L37:
        if ("AUDIO".equals(r143) == false) goto L40;
        r144 = 3;
        r18 = r5;
        goto L43
    L40:
        if ("VIDEO".equals(r143) == false) goto L42;
        r144 = 3;
        goto L43
    L42:
        r144 = 3;
        r18 = 0;
        goto L43
    L72:
        this.zzh = new ArrayList(r2);
    L75:
        if (r27.has("textTrackStyle") == false) goto L77;
        JSONObject r17 = r27.getJSONObject("textTrackStyle");
        TextTrackStyle r25 = new TextTrackStyle();
        r25.fromJson(r17);
        this.zzi = r25;
    L78:
        zza(r27);
        this.zzs = r27.optJSONObject("customData");
        this.zzl = CastUtils.optStringOrNull(r27, "entity");
        this.zzo = CastUtils.optStringOrNull(r27, "atvEntity");
        this.zzm = VastAdsRequest.fromJson(r27.optJSONObject("vmapAdsRequest"));
        if (r27.has("startAbsoluteTime") == false) goto L90;
        if (r27.isNull("startAbsoluteTime") == true) goto L90;
        double r110 = r27.optDouble("startAbsoluteTime");
        if (Double.isNaN(r110) == true) goto L90;
        if (Double.isInfinite(r110) == true) goto L90;
        if (r110 < 0.0d) goto L90;
        this.zzn = CastUtils.secToMillisec(r110);
    L90:
        if (r27.has("contentUrl") == false) goto L92;
        this.zzp = r27.optString("contentUrl");
    L92:
        this.zzq = CastUtils.optStringOrNull(r27, "hlsSegmentFormat");
        this.zzr = CastUtils.optStringOrNull(r27, "hlsVideoSegmentFormat");
        return;
    L77:
        this.zzi = null;
        goto L78
    L73:
        this.zzh = null;
        goto L75
    L6:
        if ("BUFFERED".equals(r1) == false) goto L9;
        this.zzd = 1;
        goto L12
    L9:
        if ("LIVE".equals(r1) == false) goto L11;
        this.zzd = 2;
        goto L12
    L11:
        this.zzd = -1;
        goto L12
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof MediaInfo) == true) goto L8;
        return false;
    L8:
        MediaInfo r82 = (MediaInfo) r8;
        JSONObject r1 = this.zzs;
        if (r1 == null) goto L11;
        boolean r3 = false;
    L12:
        JSONObject r4 = r82.zzs;
        if (r4 == null) goto L15;
        boolean r5 = false;
    L16:
        if (r3 == r5) goto L18;
        return false;
    L18:
        if (r1 == null) goto L24;
        if (r4 == null) goto L24;
        if (JsonUtils.areJsonValuesEquivalent(r1, r4) == true) goto L24;
        return false;
    L24:
        if (CastUtils.zza(this.zzc, r82.zzc) == true) goto L26;
    L56:
        return false;
    L26:
        if (this.zzd != r82.zzd) goto L56;
        if (CastUtils.zza(this.zze, r82.zze) == false) goto L56;
        if (CastUtils.zza(this.zzf, r82.zzf) == false) goto L56;
        if (this.zzg != r82.zzg) goto L56;
        if (CastUtils.zza(this.zzh, r82.zzh) == false) goto L56;
        if (CastUtils.zza(this.zzi, r82.zzi) == false) goto L56;
        if (CastUtils.zza(this.zzj, r82.zzj) == false) goto L56;
        if (CastUtils.zza(this.zzk, r82.zzk) == false) goto L56;
        if (CastUtils.zza(this.zzl, r82.zzl) == false) goto L56;
        if (CastUtils.zza(this.zzm, r82.zzm) == false) goto L56;
        if (this.zzn != r82.zzn) goto L56;
        if (CastUtils.zza(this.zzo, r82.zzo) == false) goto L56;
        if (CastUtils.zza(this.zzp, r82.zzp) == false) goto L56;
        if (CastUtils.zza(this.zzq, r82.zzq) == false) goto L56;
        if (CastUtils.zza(this.zzr, r82.zzr) == false) goto L56;
        return true;
    L15:
        r5 = true;
        goto L16
    L11:
        r3 = true;
        goto L12
    }

    public List<AdBreakClipInfo> getAdBreakClips() {
        List r0 = this.zzk;
        if (r0 != null) goto L7;
        return null;
    L7:
        return Collections.unmodifiableList(r0);
    }

    public List<AdBreakInfo> getAdBreaks() {
        List r0 = this.zzj;
        if (r0 != null) goto L7;
        return null;
    L7:
        return Collections.unmodifiableList(r0);
    }

    public String getContentId() {
        String r0 = this.zzc;
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r0;
    }

    public String getContentType() {
        return this.zze;
    }

    public String getContentUrl() {
        return this.zzp;
    }

    public JSONObject getCustomData() {
        return this.zzs;
    }

    public String getEntity() {
        return this.zzl;
    }

    @HlsSegmentFormat
    public String getHlsSegmentFormat() {
        return this.zzq;
    }

    @HlsVideoSegmentFormat
    public String getHlsVideoSegmentFormat() {
        return this.zzr;
    }

    public List<MediaTrack> getMediaTracks() {
        return this.zzh;
    }

    public MediaMetadata getMetadata() {
        return this.zzf;
    }

    public long getStartAbsoluteTime() {
        return this.zzn;
    }

    public long getStreamDuration() {
        return this.zzg;
    }

    public int getStreamType() {
        return this.zzd;
    }

    public TextTrackStyle getTextTrackStyle() {
        return this.zzi;
    }

    public VastAdsRequest getVmapAdsRequest() {
        return this.zzm;
    }

    public Writer getWriter() {
        return this.zzt;
    }

    public int hashCode() {
        return com.google.android.gms.common.internal.Objects.hashCode(new Object[]{this.zzc, Integer.valueOf(this.zzd), this.zze, this.zzf, Long.valueOf(this.zzg), String.valueOf(this.zzs), this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, Long.valueOf(this.zzn), this.zzo, this.zzq, this.zzr});
    }

    public void setTextTrackStyle(TextTrackStyle r1) {
        this.zzi = r1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r7, int r8) {
        JSONObject r0 = this.zzs;
        if (r0 != null) goto L5;
        String r02 = null;
    L6:
        this.zzb = r02;
        int r03 = SafeParcelWriter.beginObjectHeader(r7);
        SafeParcelWriter.writeString(r7, 2, getContentId(), false);
        SafeParcelWriter.writeInt(r7, 3, getStreamType());
        SafeParcelWriter.writeString(r7, 4, getContentType(), false);
        SafeParcelWriter.writeParcelable(r7, 5, getMetadata(), r8, false);
        SafeParcelWriter.writeLong(r7, 6, getStreamDuration());
        SafeParcelWriter.writeTypedList(r7, 7, getMediaTracks(), false);
        SafeParcelWriter.writeParcelable(r7, 8, getTextTrackStyle(), r8, false);
        SafeParcelWriter.writeString(r7, 9, this.zzb, false);
        SafeParcelWriter.writeTypedList(r7, 10, getAdBreaks(), false);
        SafeParcelWriter.writeTypedList(r7, 11, getAdBreakClips(), false);
        SafeParcelWriter.writeString(r7, 12, getEntity(), false);
        SafeParcelWriter.writeParcelable(r7, 13, getVmapAdsRequest(), r8, false);
        SafeParcelWriter.writeLong(r7, 14, getStartAbsoluteTime());
        SafeParcelWriter.writeString(r7, 15, this.zzo, false);
        SafeParcelWriter.writeString(r7, 16, getContentUrl(), false);
        SafeParcelWriter.writeString(r7, 17, getHlsSegmentFormat(), false);
        SafeParcelWriter.writeString(r7, 18, getHlsVideoSegmentFormat(), false);
        SafeParcelWriter.finishObjectHeader(r7, r03);
        return;
    L5:
        r02 = r0.toString();
        goto L6
    }

    public final void zza(JSONObject r26) {
        int r7 = 0;
        if (r26.has("breaks") == false) goto L31;
        JSONArray r4 = r26.getJSONArray("breaks");
        ArrayList r9 = new ArrayList(r4.length());
        int r10 = 0;
    L6:
        if (r10 >= r4.length()) goto L29;
        JSONObject r0 = r4.getJSONObject(r10);
        Parcelable.Creator<AdBreakInfo> r11 = AdBreakInfo.CREATOR;
        if (r0 != null) goto L11;
    L9:
        AdBreakInfo r13 = null;
    L26:
        if (r13 == null) goto L28;
        r9.add(r13);
        r10 = r10 + 1;
        r7 = 0;
        goto L6
    L28:
        r9.clear();
        goto L29
    L11:
        if (r0.has("id") == false) goto L9;
        if (r0.has("position") == false) goto L9;
        String r16 = r0.getString("id");     // Catch: JSONException -> L21
        long r14 = CastUtils.secToMillisec(r0.getLong("position"));     // Catch: JSONException -> L21
        boolean r19 = r0.optBoolean("isWatched");     // Catch: JSONException -> L21
        long r17 = CastUtils.secToMillisec(r0.optLong("duration"));     // Catch: JSONException -> L21
        JSONArray r112 = r0.optJSONArray("breakClipIds");     // Catch: JSONException -> L21
        String[] r12 = new String[r7];     // Catch: JSONException -> L21
        if (r112 == null) goto L23;
        r12 = new String[r112.length()];     // Catch: JSONException -> L21
        int r132 = r7;
    L19:
        if (r132 >= r112.length()) goto L23;
        r12[r132] = r112.getString(r132);     // Catch: JSONException -> L21
        r132 = r132 + 1;     // Catch: JSONException -> L21
    L23:
        r13 = new AdBreakInfo(r14, r16, r17, r19, r12, r0.optBoolean("isEmbedded"), r0.optBoolean("expanded"));     // Catch: JSONException -> L21
    L21:
        e = move-exception;
        Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakInfo from JSON: %s", new Object[]{e.getMessage()}));
    L29:
        this.zzj = new ArrayList(r9);
    L31:
        if (r26.has("breakClips") == false) goto L75;
        JSONArray r2 = r26.getJSONArray("breakClips");
        ArrayList r42 = new ArrayList(r2.length());
        int r72 = 0;
    L34:
        if (r72 >= r2.length()) goto L64;
        JSONObject r02 = r2.getJSONObject(r72);
        Parcelable.Creator<AdBreakClipInfo> r92 = AdBreakClipInfo.CREATOR;
        if (r02 != null) goto L39;
    L37:
        AdBreakClipInfo r102 = null;
    L61:
        if (r102 == null) goto L63;
        r42.add(r102);
        r72 = r72 + 1;
        goto L34
    L63:
        r42.clear();
        goto L64
    L39:
        if (r02.has("id") == false) goto L37;
        String r113 = r02.getString("id");     // Catch: JSONException -> L45
        long r133 = CastUtils.secToMillisec(r02.optLong("duration"));     // Catch: JSONException -> L45
        String r172 = CastUtils.optStringOrNull(r02, "clickThroughUrl");     // Catch: JSONException -> L45
        String r15 = CastUtils.optStringOrNull(r02, "contentUrl");     // Catch: JSONException -> L45
        String r93 = CastUtils.optStringOrNull(r02, "mimeType");     // Catch: JSONException -> L45
        if (r93 != null) goto L44;
        r93 = CastUtils.optStringOrNull(r02, "contentType");     // Catch: JSONException -> L45
    L44:
        String r162 = r93;
        String r122 = CastUtils.optStringOrNull(r02, "title");     // Catch: JSONException -> L45
        JSONObject r94 = r02.optJSONObject("customData");     // Catch: JSONException -> L45
        String r192 = CastUtils.optStringOrNull(r02, "contentId");     // Catch: JSONException -> L45
        String r20 = CastUtils.optStringOrNull(r02, "posterUrl");     // Catch: JSONException -> L45
        if (r02.has("whenSkippable") == false) goto L51;
        JSONObject r262 = r94;
        long r8 = CastUtils.secToMillisec(((Integer) r02.get("whenSkippable")).intValue());     // Catch: JSONException -> L45
    L50:
        long r21 = r8;
        String r23 = CastUtils.optStringOrNull(r02, "hlsSegmentFormat");     // Catch: JSONException -> L45
        VastAdsRequest r24 = VastAdsRequest.fromJson(r02.optJSONObject("vastAdsRequest"));     // Catch: JSONException -> L45
        if (r262 != null) goto L55;
    L56:
        String r18 = null;
    L58:
        r102 = new AdBreakClipInfo(r113, r122, r133, r15, r162, r172, r18, r192, r20, r21, r23, r24);     // Catch: JSONException -> L45
        goto L61
    L55:
        if (r262.length() == 0) goto L56;
        r18 = r262.toString();     // Catch: JSONException -> L45
        goto L58
    L51:
        r262 = r94;
        r8 = -1;
    L45:
        e = move-exception;
        Log.d("AdBreakClipInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", new Object[]{e.getMessage()}));
    L64:
        this.zzk = new ArrayList(r42);
        return;
    }

    public final JSONObject zzb() {
        JSONObject r0 = new JSONObject();
        r0.put("contentId", this.zzc);     // Catch: JSONException -> L64
        r0.putOpt("contentUrl", this.zzp);     // Catch: JSONException -> L64
        int r1 = this.zzd;     // Catch: JSONException -> L64
        if (r1 != 1) goto L6;
        String r12 = "BUFFERED";
    L10:
        r0.put("streamType", r12);     // Catch: JSONException -> L64
        String r13 = this.zze;     // Catch: JSONException -> L64
        if (r13 == null) goto L13;
        r0.put("contentType", r13);     // Catch: JSONException -> L64
    L13:
        MediaMetadata r14 = this.zzf;     // Catch: JSONException -> L64
        if (r14 == null) goto L16;
        r0.put("metadata", r14.zza());     // Catch: JSONException -> L64
    L16:
        long r15 = this.zzg;     // Catch: JSONException -> L64
        if (r15 > (-1)) goto L20;
        r0.put("duration", JSONObject.NULL);     // Catch: JSONException -> L64
    L22:
        if (this.zzh == null) goto L28;
        JSONArray r16 = new JSONArray();     // Catch: JSONException -> L64
        Iterator r2 = this.zzh.iterator();     // Catch: JSONException -> L64
    L25:
        if (r2.hasNext() == false) goto L27;
        r16.put(((MediaTrack) r2.next()).zza());     // Catch: JSONException -> L64
        goto L25
    L27:
        r0.put("tracks", r16);     // Catch: JSONException -> L64
    L28:
        TextTrackStyle r17 = this.zzi;     // Catch: JSONException -> L64
        if (r17 == null) goto L31;
        r0.put("textTrackStyle", r17.zza());     // Catch: JSONException -> L64
    L31:
        JSONObject r18 = this.zzs;     // Catch: JSONException -> L64
        if (r18 == null) goto L34;
        r0.put("customData", r18);     // Catch: JSONException -> L64
    L34:
        String r19 = this.zzl;     // Catch: JSONException -> L64
        if (r19 == null) goto L38;
        r0.put("entity", r19);     // Catch: JSONException -> L64
    L38:
        if (this.zzj == null) goto L45;
        JSONArray r110 = new JSONArray();     // Catch: JSONException -> L64
        Iterator r22 = this.zzj.iterator();     // Catch: JSONException -> L64
    L41:
        if (r22.hasNext() == false) goto L43;
        r110.put(((AdBreakInfo) r22.next()).zza());     // Catch: JSONException -> L64
        goto L41
    L43:
        r0.put("breaks", r110);     // Catch: JSONException -> L64
    L45:
        if (this.zzk == null) goto L51;
        JSONArray r111 = new JSONArray();     // Catch: JSONException -> L64
        Iterator r23 = this.zzk.iterator();     // Catch: JSONException -> L64
    L48:
        if (r23.hasNext() == false) goto L50;
        r111.put(((AdBreakClipInfo) r23.next()).zza());     // Catch: JSONException -> L64
        goto L48
    L50:
        r0.put("breakClips", r111);     // Catch: JSONException -> L64
    L51:
        VastAdsRequest r112 = this.zzm;     // Catch: JSONException -> L64
        if (r112 == null) goto L54;
        r0.put("vmapAdsRequest", r112.zza());     // Catch: JSONException -> L64
    L54:
        long r113 = this.zzn;     // Catch: JSONException -> L64
        if (r113 == (-1)) goto L57;
        r0.put("startAbsoluteTime", CastUtils.millisecToSec(r113));     // Catch: JSONException -> L64
    L57:
        r0.putOpt("atvEntity", this.zzo);     // Catch: JSONException -> L64
        String r114 = this.zzq;     // Catch: JSONException -> L64
        if (r114 == null) goto L60;
        r0.put("hlsSegmentFormat", r114);     // Catch: JSONException -> L64
    L60:
        String r7 = this.zzr;     // Catch: JSONException -> L64
        if (r7 == null) goto L63;
        r0.put("hlsVideoSegmentFormat", r7);     // Catch: JSONException -> L64
        goto L63
    L20:
        r0.put("duration", CastUtils.millisecToSec(r15));     // Catch: JSONException -> L64
        goto L22
    L6:
        if (r1 == 2) goto L8;
        r12 = "NONE";
        goto L10
    L8:
        r12 = "LIVE";
    L63:
        return r0;
    }

    public final /* synthetic */ void zzc(String r1) {
        this.zzc = r1;
    }

    public final /* synthetic */ void zzd(int r1) {
        this.zzd = r1;
    }

    public final /* synthetic */ void zze(String r1) {
        this.zze = r1;
    }

    public final /* synthetic */ void zzf(MediaMetadata r1) {
        this.zzf = r1;
    }

    public final /* synthetic */ void zzg(long r1) {
        this.zzg = r1;
    }

    public final /* synthetic */ void zzh(List r1) {
        this.zzh = r1;
    }

    public final /* synthetic */ void zzi(TextTrackStyle r1) {
        this.zzi = r1;
    }

    public final /* synthetic */ void zzj(List r1) {
        this.zzj = r1;
    }

    public final /* synthetic */ void zzk(List r1) {
        this.zzk = r1;
    }

    public final /* synthetic */ void zzl(String r1) {
        this.zzl = r1;
    }

    public final /* synthetic */ void zzm(VastAdsRequest r1) {
        this.zzm = r1;
    }

    public final /* synthetic */ void zzn(long r1) {
        this.zzn = r1;
    }

    public final /* synthetic */ void zzo(String r1) {
        this.zzp = r1;
    }

    public final /* synthetic */ void zzp(String r1) {
        this.zzq = r1;
    }

    public final /* synthetic */ void zzq(String r1) {
        this.zzr = r1;
    }

    public final /* synthetic */ void zzr(JSONObject r1) {
        this.zzs = r1;
    }
}
