package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaQueueData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaStatus extends AbstractSafeParcelable {
    public static final long COMMAND_DISLIKE = 32768;
    public static final long COMMAND_EDIT_TRACKS = 4096;
    public static final long COMMAND_FOLLOW = 65536;
    public static final long COMMAND_LIKE = 16384;
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_PLAYBACK_RATE = 8192;
    public static final long COMMAND_QUEUE_NEXT = 64;
    public static final long COMMAND_QUEUE_PREVIOUS = 128;
    public static final long COMMAND_QUEUE_REPEAT = 3072;
    public static final long COMMAND_QUEUE_REPEAT_ALL = 1024;
    public static final long COMMAND_QUEUE_REPEAT_ONE = 2048;
    public static final long COMMAND_QUEUE_SHUFFLE = 256;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_AD = 512;

    @Deprecated
    public static final long COMMAND_SKIP_BACKWARD = 32;

    @Deprecated
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_STREAM_TRANSFER = 262144;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final long COMMAND_UNFOLLOW = 131072;
    public static final Parcelable.Creator<MediaStatus> CREATOR = null;
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_LOADING = 5;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    public static final int REPEAT_MODE_REPEAT_ALL = 1;
    public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
    public static final int REPEAT_MODE_REPEAT_OFF = 0;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;
    private static final Logger zzx = null;
    MediaInfo zza;
    long zzb;
    int zzc;
    double zzd;
    int zze;
    int zzf;
    long zzg;
    long zzh;
    double zzi;
    boolean zzj;
    long[] zzk;
    int zzl;
    int zzm;
    String zzn;
    JSONObject zzo;
    int zzp;
    final List zzq;
    boolean zzr;
    AdBreakStatus zzs;
    VideoInfo zzt;
    MediaLiveSeekableRange zzu;
    MediaQueueData zzv;
    boolean zzw;
    private final SparseArray zzy;
    private final Writer zzz;

    public static class Builder {
        private MediaInfo zza;
        private long zzb;
        private int zzc;
        private double zzd;
        private int zze;
        private int zzf;
        private long zzg;
        private long zzh;
        private double zzi;
        private boolean zzj;
        private long[] zzk;
        private int zzl;
        private int zzm;
        private JSONObject zzn;
        private int zzo;
        private final List zzp;
        private boolean zzq;
        private AdBreakStatus zzr;
        private VideoInfo zzs;
        private MediaLiveSeekableRange zzt;
        private MediaQueueData zzu;

        public Builder() {
            this.zzc = 0;
            this.zze = 0;
            this.zzf = 0;
            this.zzl = 0;
            this.zzm = 0;
            this.zzo = 0;
            this.zzp = new ArrayList();
        }

        public MediaStatus build() {
            MediaStatus r1 = new MediaStatus(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, null, this.zzo, this.zzp, this.zzq, this.zzr, this.zzs, this.zzt, this.zzu);
            r1.zzo = this.zzn;
            return r1;
        }

        public Builder setActiveTrackIds(long[] r1) {
            this.zzk = r1;
            return this;
        }

        public Builder setAdBreakStatus(AdBreakStatus r1) {
            this.zzr = r1;
            return this;
        }

        public Builder setCurrentItemId(int r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setCustomData(JSONObject r1) {
            this.zzn = r1;
            return this;
        }

        public Builder setIdleReason(int r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setIsMute(boolean r1) {
            this.zzj = r1;
            return this;
        }

        public Builder setIsPlayingAd(boolean r1) {
            this.zzq = r1;
            return this;
        }

        public Builder setLiveSeekableRange(MediaLiveSeekableRange r1) {
            this.zzt = r1;
            return this;
        }

        public Builder setLoadingItemId(int r1) {
            this.zzl = r1;
            return this;
        }

        public Builder setMediaInfo(MediaInfo r1) {
            this.zza = r1;
            return this;
        }

        public Builder setMediaSessionId(long r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setPlaybackRate(double r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setPlayerState(int r1) {
            this.zze = r1;
            return this;
        }

        public Builder setPreloadedItemId(int r1) {
            this.zzm = r1;
            return this;
        }

        public Builder setQueueData(MediaQueueData r1) {
            this.zzu = r1;
            return this;
        }

        public Builder setQueueItems(List<MediaQueueItem> r2) {
            List r0 = this.zzp;
            r0.clear();
            r0.addAll(r2);
            return this;
        }

        public Builder setQueueRepeatMode(int r1) {
            this.zzo = r1;
            return this;
        }

        public Builder setStreamPosition(long r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setStreamVolume(double r1) {
            this.zzi = r1;
            return this;
        }

        public Builder setSupportedMediaCommands(long r1) {
            this.zzh = r1;
            return this;
        }

        public Builder setVideoInfo(VideoInfo r1) {
            this.zzs = r1;
            return this;
        }
    }

    public class Writer {
        final /* synthetic */ MediaStatus zza;

        public Writer(MediaStatus r1) {
            Objects.requireNonNull(r1);
            this.zza = r1;
        }

        public void setActiveTrackIds(long[] r1) {
            this.zza.zzk = r1;
        }

        public void setAdBreakStatus(AdBreakStatus r1) {
            this.zza.zzs = r1;
        }

        public void setCurrentItemId(int r1) {
            this.zza.zzc = r1;
        }

        public void setCustomData(JSONObject r1) {
            MediaStatus r0 = this.zza;
            r0.zzo = r1;
            r0.zzn = null;
        }

        public void setIdleReason(int r1) {
            this.zza.zzf = r1;
        }

        public void setIsPlayingAd(boolean r1) {
            this.zza.zzr = r1;
        }

        public void setLiveSeekableRange(MediaLiveSeekableRange r1) {
            this.zza.zzu = r1;
        }

        public void setLoadingItemId(int r1) {
            this.zza.zzl = r1;
        }

        public void setMediaInfo(MediaInfo r1) {
            this.zza.zza = r1;
        }

        public void setMute(boolean r1) {
            this.zza.zzj = r1;
        }

        public void setPlaybackRate(double r1) {
            this.zza.zzd = r1;
        }

        public void setPlayerState(int r1) {
            this.zza.zze = r1;
        }

        public void setPreloadedItemId(int r1) {
            this.zza.zzm = r1;
        }

        public void setQueueData(MediaQueueData r1) {
            this.zza.zzv = r1;
        }

        public void setQueueItems(List<MediaQueueItem> r1) {
            this.zza.zzd(r1);
        }

        public void setQueueRepeatMode(int r1) {
            this.zza.zzp = r1;
        }

        public void setShuffle(boolean r1) {
            this.zza.zzw = r1;
        }

        public void setStreamPosition(long r1) {
            this.zza.zzg = r1;
        }

        public void setStreamVolume(double r1) {
            this.zza.zzi = r1;
        }

        public void setSupportedMediaCommands(long r1) {
            this.zza.zzh = r1;
        }

        public void setVideoInfo(VideoInfo r1) {
            this.zza.zzt = r1;
        }
    }

    static {
        zzx = new Logger("MediaStatus");
        CREATOR = new zzbz();
    }

    @SuppressLint({"NonSdkVisibleApi"})
    public MediaStatus(MediaInfo r5, long r6, int r8, double r9, int r11, int r12, long r13, long r15, double r17, boolean r19, long[] r20, int r21, int r22, String r23, int r24, List r25, boolean r26, AdBreakStatus r27, VideoInfo r28, MediaLiveSeekableRange r29, MediaQueueData r30) {
        this.zzq = new ArrayList();
        this.zzy = new SparseArray();
        this.zzz = new Writer(this);
        this.zza = r5;
        this.zzb = r6;
        this.zzc = r8;
        this.zzd = r9;
        this.zze = r11;
        this.zzf = r12;
        this.zzg = r13;
        this.zzh = r15;
        this.zzi = r17;
        this.zzj = r19;
        this.zzk = r20;
        this.zzl = r21;
        this.zzm = r22;
        this.zzn = r23;
        if (r23 != null) goto L20;
        this.zzo = null;
    L8:
        this.zzp = r24;
        if (r25 != null) goto L11;
    L13:
        this.zzr = r26;
        this.zzs = r27;
        this.zzt = r28;
        this.zzu = r29;
        this.zzv = r30;
        boolean r52 = false;
        if (r30 != null) goto L16;
    L18:
        this.zzw = r52;
        return;
    L16:
        if (r30.zza() == false) goto L18;
        r52 = true;
        goto L18
    L11:
        if (r25.isEmpty() == true) goto L13;
        zze(r25);
        goto L13
    L20:
        this.zzo = new JSONObject(this.zzn);     // Catch: JSONException -> L6
    L6:
        this.zzo = null;
        this.zzn = null;
        goto L8
    }

    public MediaStatus(JSONObject r28) {
        this(null, 0, 0, 0.0d, 0, 0, 0, 0, 0.0d, false, null, 0, 0, null, 0, null, false, null, null, null, null);
        zzb(r28, 0);
    }

    private final void zze(List r5) {
        List r0 = this.zzq;
        r0.clear();
        SparseArray r4 = this.zzy;
        r4.clear();
        if (r5 == null) goto L8;
        int r1 = 0;
    L6:
        if (r1 >= r5.size()) goto L10;
        MediaQueueItem r2 = (MediaQueueItem) r5.get(r1);
        r0.add(r2);
        r4.put(r2.getItemId(), Integer.valueOf(r1));
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    private static final boolean zzf(int r2, int r3, int r4, int r5) {
        if (r2 == 1) goto L5;
        return false;
    L5:
        if (r3 != 1) goto L7;
    L14:
        if (r4 == 0) goto L16;
        return false;
    L16:
        return true;
    L7:
        if (r3 != 2) goto L9;
        if (r5 != 2) goto L13;
        return false;
    L13:
        return true;
    L9:
        if (r3 == 3) goto L14;
        return true;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof MediaStatus) == true) goto L8;
        return false;
    L8:
        MediaStatus r82 = (MediaStatus) r8;
        if (this.zzo == null) goto L11;
        boolean r1 = false;
    L13:
        if (r82.zzo == null) goto L15;
        boolean r3 = false;
    L16:
        if (r1 == r3) goto L19;
        return false;
    L19:
        if (this.zzb == r82.zzb) goto L21;
    L68:
        return false;
    L21:
        if (this.zzc != r82.zzc) goto L68;
        if (this.zzd != r82.zzd) goto L68;
        if (this.zze != r82.zze) goto L68;
        if (this.zzf != r82.zzf) goto L68;
        if (this.zzg != r82.zzg) goto L68;
        if (this.zzi != r82.zzi) goto L68;
        if (this.zzj != r82.zzj) goto L68;
        if (this.zzl != r82.zzl) goto L68;
        if (this.zzm != r82.zzm) goto L68;
        if (this.zzp != r82.zzp) goto L68;
        if (Arrays.equals(this.zzk, r82.zzk) == false) goto L68;
        if (CastUtils.zza(Long.valueOf(this.zzh), Long.valueOf(r82.zzh)) == false) goto L68;
        if (CastUtils.zza(this.zzq, r82.zzq) == false) goto L68;
        if (CastUtils.zza(this.zza, r82.zza) == false) goto L68;
        JSONObject r12 = this.zzo;
        if (r12 == null) goto L56;
        JSONObject r32 = r82.zzo;
        if (r32 == null) goto L56;
        if (JsonUtils.areJsonValuesEquivalent(r12, r32) == false) goto L68;
    L56:
        if (this.zzr != r82.isPlayingAd()) goto L68;
        if (CastUtils.zza(this.zzs, r82.zzs) == false) goto L68;
        if (CastUtils.zza(this.zzt, r82.zzt) == false) goto L68;
        if (CastUtils.zza(this.zzu, r82.zzu) == false) goto L68;
        if (com.google.android.gms.common.internal.Objects.equal(this.zzv, r82.zzv) == false) goto L68;
        if (this.zzw != r82.zzw) goto L68;
        return true;
    L15:
        r3 = true;
        goto L16
    L11:
        r1 = true;
        goto L13
    }

    public long[] getActiveTrackIds() {
        return this.zzk;
    }

    public AdBreakStatus getAdBreakStatus() {
        return this.zzs;
    }

    public AdBreakInfo getCurrentAdBreak() {
        AdBreakStatus r0 = this.zzs;
        if (r0 != null) goto L5;
        return null;
    L5:
        String r02 = r0.getBreakId();
        if (TextUtils.isEmpty(r02) == false) goto L8;
        return null;
    L8:
        MediaInfo r4 = this.zza;
        if (r4 != null) goto L11;
        return null;
    L11:
        List<AdBreakInfo> r42 = r4.getAdBreaks();
        if (r42 != null) goto L14;
    L22:
        return null;
    L14:
        if (r42.isEmpty() == true) goto L22;
        Iterator<AdBreakInfo> r43 = r42.iterator();
    L18:
        if (r43.hasNext() == false) goto L22;
        AdBreakInfo r2 = r43.next();
        if (r02.equals(r2.getId()) == false) goto L18;
        return r2;
    }

    public AdBreakClipInfo getCurrentAdBreakClip() {
        AdBreakStatus r0 = this.zzs;
        if (r0 != null) goto L5;
        return null;
    L5:
        String r02 = r0.getBreakClipId();
        if (TextUtils.isEmpty(r02) == false) goto L8;
        return null;
    L8:
        MediaInfo r4 = this.zza;
        if (r4 != null) goto L11;
        return null;
    L11:
        List<AdBreakClipInfo> r42 = r4.getAdBreakClips();
        if (r42 != null) goto L14;
    L22:
        return null;
    L14:
        if (r42.isEmpty() == true) goto L22;
        Iterator<AdBreakClipInfo> r43 = r42.iterator();
    L18:
        if (r43.hasNext() == false) goto L22;
        AdBreakClipInfo r2 = r43.next();
        if (r02.equals(r2.getId()) == false) goto L18;
        return r2;
    }

    public int getCurrentItemId() {
        return this.zzc;
    }

    public JSONObject getCustomData() {
        return this.zzo;
    }

    public int getIdleReason() {
        return this.zzf;
    }

    public Integer getIndexById(int r1) {
        return (Integer) this.zzy.get(r1);
    }

    public MediaQueueItem getItemById(int r2) {
        Integer r22 = (Integer) this.zzy.get(r2);
        if (r22 != null) goto L7;
        return null;
    L7:
        return (MediaQueueItem) this.zzq.get(r22.intValue());
    }

    public MediaQueueItem getItemByIndex(int r2) {
        if (r2 < 0) goto L8;
        List r1 = this.zzq;
        if (r2 < r1.size()) goto L7;
        return null;
    L7:
        return (MediaQueueItem) r1.get(r2);
    L8:
        return null;
    }

    public MediaLiveSeekableRange getLiveSeekableRange() {
        return this.zzu;
    }

    public int getLoadingItemId() {
        return this.zzl;
    }

    public MediaInfo getMediaInfo() {
        return this.zza;
    }

    public double getPlaybackRate() {
        return this.zzd;
    }

    public int getPlayerState() {
        return this.zze;
    }

    public int getPreloadedItemId() {
        return this.zzm;
    }

    public MediaQueueData getQueueData() {
        return this.zzv;
    }

    public MediaQueueItem getQueueItem(int r1) {
        return getItemByIndex(r1);
    }

    public MediaQueueItem getQueueItemById(int r1) {
        return getItemById(r1);
    }

    public int getQueueItemCount() {
        return this.zzq.size();
    }

    public List<MediaQueueItem> getQueueItems() {
        return this.zzq;
    }

    public int getQueueRepeatMode() {
        return this.zzp;
    }

    public long getStreamPosition() {
        return this.zzg;
    }

    public double getStreamVolume() {
        return this.zzi;
    }

    public long getSupportedMediaCommands() {
        return this.zzh;
    }

    public VideoInfo getVideoInfo() {
        return this.zzt;
    }

    public Writer getWriter() {
        return this.zzz;
    }

    public int hashCode() {
        return com.google.android.gms.common.internal.Objects.hashCode(new Object[]{this.zza, Long.valueOf(this.zzb), Integer.valueOf(this.zzc), Double.valueOf(this.zzd), Integer.valueOf(this.zze), Integer.valueOf(this.zzf), Long.valueOf(this.zzg), Long.valueOf(this.zzh), Double.valueOf(this.zzi), Boolean.valueOf(this.zzj), Integer.valueOf(Arrays.hashCode(this.zzk)), Integer.valueOf(this.zzl), Integer.valueOf(this.zzm), String.valueOf(this.zzo), Integer.valueOf(this.zzp), this.zzq, Boolean.valueOf(this.zzr), this.zzs, this.zzt, this.zzu, this.zzv});
    }

    public boolean isMediaCommandSupported(long r3) {
        if ((this.zzh & r3) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isMute() {
        return this.zzj;
    }

    public boolean isPlayingAd() {
        return this.zzr;
    }

    public JSONObject toJson() {
        JSONObject r0 = new JSONObject();
        r0.put("mediaSessionId", this.zzb);     // Catch: JSONException -> L13
        int r3 = this.zze;     // Catch: JSONException -> L13
        String r4 = "IDLE";
        if (r3 == 1) goto L18;
        if (r3 == 2) goto L17;
        if (r3 == 3) goto L16;
        if (r3 != 4) goto L10;
        r4 = "BUFFERING";
        goto L18
    L10:
        if (r3 != 5) goto L18;
        r4 = "LOADING";
        goto L18
    L16:
        r4 = "PAUSED";
        goto L18
    L17:
        r4 = "PLAYING";
    L18:
        r0.put("playerState", r4);     // Catch: JSONException -> L13
        JSONArray r32 = null;
        if (this.zze != 1) goto L31;
        int r2 = this.zzf;     // Catch: JSONException -> L13
        if (r2 == 1) goto L29;
        if (r2 == 2) goto L28;
        if (r2 == 3) goto L27;
        if (r2 == 4) goto L26;
        String r22 = null;
    L30:
        r0.putOpt("idleReason", r22);     // Catch: JSONException -> L13
        goto L31
    L26:
        r22 = MediaError.ERROR_TYPE_ERROR;
        goto L30
    L27:
        r22 = "INTERRUPTED";
        goto L30
    L28:
        r22 = "CANCELLED";
        goto L30
    L29:
        r22 = "FINISHED";
    L31:
        r0.put("playbackRate", this.zzd);     // Catch: JSONException -> L13
        r0.put("currentTime", CastUtils.millisecToSec(this.zzg));     // Catch: JSONException -> L13
        r0.put("supportedMediaCommands", this.zzh);     // Catch: JSONException -> L13
        JSONObject r42 = new JSONObject();     // Catch: JSONException -> L13
        r42.put("level", this.zzi);     // Catch: JSONException -> L13
        r42.put("muted", this.zzj);     // Catch: JSONException -> L13
        r0.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, r42);     // Catch: JSONException -> L13
        if (this.zzk == null) goto L37;
        r32 = new JSONArray();     // Catch: JSONException -> L13
        long[] r43 = this.zzk;     // Catch: JSONException -> L13
        int r5 = r43.length;     // Catch: JSONException -> L13
        int r6 = 0;
    L35:
        if (r6 >= r5) goto L37;
        r32.put(r43[r6]);     // Catch: JSONException -> L13
        r6 = r6 + 1;     // Catch: JSONException -> L13
    L37:
        r0.putOpt("activeTrackIds", r32);     // Catch: JSONException -> L13
        r0.putOpt("customData", this.zzo);     // Catch: JSONException -> L13
        r0.putOpt("shuffle", Boolean.valueOf(this.zzw));     // Catch: JSONException -> L13
        MediaInfo r23 = this.zza;     // Catch: JSONException -> L13
        if (r23 == null) goto L40;
        r0.putOpt("media", r23.zzb());     // Catch: JSONException -> L13
    L40:
        int r24 = this.zzc;     // Catch: JSONException -> L13
        if (r24 == 0) goto L43;
        r0.put("currentItemId", r24);     // Catch: JSONException -> L13
    L43:
        int r25 = this.zzm;     // Catch: JSONException -> L13
        if (r25 == 0) goto L46;
        r0.put("preloadedItemId", r25);     // Catch: JSONException -> L13
    L46:
        int r26 = this.zzl;     // Catch: JSONException -> L13
        if (r26 == 0) goto L49;
        r0.put("loadingItemId", r26);     // Catch: JSONException -> L13
    L49:
        AdBreakStatus r27 = this.zzs;     // Catch: JSONException -> L13
        if (r27 == null) goto L52;
        r0.putOpt("breakStatus", r27.zza());     // Catch: JSONException -> L13
    L52:
        VideoInfo r28 = this.zzt;     // Catch: JSONException -> L13
        if (r28 == null) goto L55;
        r0.putOpt("videoInfo", r28.zza());     // Catch: JSONException -> L13
    L55:
        MediaQueueData r29 = this.zzv;     // Catch: JSONException -> L13
        if (r29 == null) goto L58;
        r0.putOpt("queueData", r29.zzb());     // Catch: JSONException -> L13
    L58:
        MediaLiveSeekableRange r210 = this.zzu;     // Catch: JSONException -> L13
        if (r210 == null) goto L61;
        r0.putOpt("liveSeekableRange", r210.zza());     // Catch: JSONException -> L13
    L61:
        r0.putOpt("repeatMode", MediaCommon.zza(Integer.valueOf(this.zzp)));     // Catch: JSONException -> L13
        List r10 = this.zzq;     // Catch: JSONException -> L13
        if (r10 != null) goto L64;
    L70:
        return r0;
    L64:
        if (r10.isEmpty() == true) goto L70;
        JSONArray r211 = new JSONArray();     // Catch: JSONException -> L13
        Iterator r102 = r10.iterator();     // Catch: JSONException -> L13
    L67:
        if (r102.hasNext() == false) goto L69;
        r211.put(((MediaQueueItem) r102.next()).toJson());     // Catch: JSONException -> L13
        goto L67
    L69:
        r0.put(PluginsConstants.Settings.ITEMS, r211);     // Catch: JSONException -> L13
    L13:
        e = move-exception;
        zzx.e(e, "Error transforming MediaStatus into JSONObject", new Object[0]);
        return new JSONObject();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r7, int r8) {
        JSONObject r0 = this.zzo;
        if (r0 != null) goto L5;
        String r02 = null;
    L6:
        this.zzn = r02;
        int r03 = SafeParcelWriter.beginObjectHeader(r7);
        SafeParcelWriter.writeParcelable(r7, 2, getMediaInfo(), r8, false);
        SafeParcelWriter.writeLong(r7, 3, this.zzb);
        SafeParcelWriter.writeInt(r7, 4, getCurrentItemId());
        SafeParcelWriter.writeDouble(r7, 5, getPlaybackRate());
        SafeParcelWriter.writeInt(r7, 6, getPlayerState());
        SafeParcelWriter.writeInt(r7, 7, getIdleReason());
        SafeParcelWriter.writeLong(r7, 8, getStreamPosition());
        SafeParcelWriter.writeLong(r7, 9, this.zzh);
        SafeParcelWriter.writeDouble(r7, 10, getStreamVolume());
        SafeParcelWriter.writeBoolean(r7, 11, isMute());
        SafeParcelWriter.writeLongArray(r7, 12, getActiveTrackIds(), false);
        SafeParcelWriter.writeInt(r7, 13, getLoadingItemId());
        SafeParcelWriter.writeInt(r7, 14, getPreloadedItemId());
        SafeParcelWriter.writeString(r7, 15, this.zzn, false);
        SafeParcelWriter.writeInt(r7, 16, this.zzp);
        SafeParcelWriter.writeTypedList(r7, 17, this.zzq, false);
        SafeParcelWriter.writeBoolean(r7, 18, isPlayingAd());
        SafeParcelWriter.writeParcelable(r7, 19, getAdBreakStatus(), r8, false);
        SafeParcelWriter.writeParcelable(r7, 20, getVideoInfo(), r8, false);
        SafeParcelWriter.writeParcelable(r7, 21, getLiveSeekableRange(), r8, false);
        SafeParcelWriter.writeParcelable(r7, 22, getQueueData(), r8, false);
        SafeParcelWriter.finishObjectHeader(r7, r03);
        return;
    L5:
        r02 = r0.toString();
        goto L6
    }

    public final long zza() {
        return this.zzb;
    }

    public final int zzb(JSONObject r14, int r15) {
        JSONObject r1 = r14.optJSONObject("extendedStatus");
        boolean r2 = false;
        if (r1 != null) goto L209;
    L14:
        long r0 = r14.getLong("mediaSessionId");
        if (r0 == this.zzb) goto L17;
        this.zzb = r0;
        int r02 = 1;
    L19:
        if (r14.has("playerState") == false) goto L59;
        String r12 = r14.getString("playerState");
        int r6 = 3;
        if (r12.equals("IDLE") == false) goto L24;
        int r13 = 1;
    L37:
        if (r13 == this.zze) goto L39;
        this.zze = r13;
        r02 = r02 | 2;
    L39:
        if (r13 != 1) goto L59;
        if (r14.has("idleReason") == false) goto L59;
        String r16 = r14.getString("idleReason");
        if (r16.equals("CANCELLED") == false) goto L46;
        r6 = 2;
    L56:
        if (r6 == this.zzf) goto L59;
        this.zzf = r6;
        r02 = r02 | 2;
        goto L59
    L46:
        if (r16.equals("INTERRUPTED") == true) goto L56;
        if (r16.equals("FINISHED") == false) goto L52;
        r6 = 1;
        goto L56
    L52:
        if (r16.equals(MediaError.ERROR_TYPE_ERROR) == false) goto L54;
        r6 = 4;
        goto L56
    L54:
        r6 = 0;
        goto L56
    L24:
        if (r12.equals("PLAYING") == false) goto L27;
        r13 = 2;
        goto L37
    L27:
        if (r12.equals("PAUSED") == false) goto L30;
        r13 = 3;
        goto L37
    L30:
        if (r12.equals("BUFFERING") == false) goto L33;
        r13 = 4;
        goto L37
    L33:
        if (r12.equals("LOADING") == false) goto L35;
        r13 = 5;
        goto L37
    L35:
        r13 = 0;
    L59:
        if (r14.has("playbackRate") == false) goto L64;
        double r62 = r14.getDouble("playbackRate");
        if (this.zzd == r62) goto L64;
        this.zzd = r62;
        r02 = r02 | 2;
    L64:
        if (r14.has("currentTime") == false) goto L70;
        long r63 = CastUtils.secToMillisec(r14.getDouble("currentTime"));
        if (r63 == this.zzg) goto L68;
        this.zzg = r63;
        r02 = r02 | 2;
    L68:
        r02 = r02 | 128;
    L70:
        if (r14.has("supportedMediaCommands") == false) goto L75;
        long r64 = r14.getLong("supportedMediaCommands");
        if (r64 == this.zzh) goto L75;
        this.zzh = r64;
        r02 = r02 | 2;
    L75:
        if (r14.has(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME) == false) goto L84;
        if (r15 != 0) goto L84;
        JSONObject r152 = r14.getJSONObject(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
        double r65 = r152.getDouble("level");
        if (r65 == this.zzi) goto L80;
        this.zzi = r65;
        r02 = r02 | 2;
    L80:
        boolean r153 = r152.getBoolean("muted");
        if (r153 == this.zzj) goto L84;
        this.zzj = r153;
        r02 = r02 | 2;
    L84:
        if (r14.has("activeTrackIds") == false) goto L86;
        JSONArray r154 = r14.getJSONArray("activeTrackIds");
    L87:
        long[] r155 = CastUtils.zzg(r154);
        long[] r17 = this.zzk;
        if (r155 == null) goto L99;
        if (r17 != null) goto L92;
    L100:
        this.zzk = r155;
        r02 = r02 | 2;
    L102:
        if (r14.has("customData") == false) goto L105;
        this.zzo = r14.getJSONObject("customData");
        this.zzn = null;
        r02 = r02 | 2;
    L105:
        if (r14.has("media") == false) goto L115;
        JSONObject r156 = r14.getJSONObject("media");
        MediaInfo r18 = new MediaInfo(r156);
        MediaInfo r66 = this.zza;
        if (r66 != null) goto L109;
    L110:
        this.zza = r18;
        r02 = r02 | 2;
    L112:
        if (r156.has("metadata") == false) goto L115;
        r02 = r02 | 4;
        goto L115
    L109:
        if (r66.equals(r18) == true) goto L112;
    L115:
        if (r14.has("currentItemId") == false) goto L119;
        int r157 = r14.getInt("currentItemId");
        if (this.zzc == r157) goto L119;
        this.zzc = r157;
        r02 = r02 | 2;
    L119:
        int r158 = r14.optInt("preloadedItemId", 0);
        if (this.zzm == r158) goto L122;
        this.zzm = r158;
        r02 = r02 | 16;
    L122:
        int r159 = r14.optInt("loadingItemId", 0);
        if (this.zzl == r159) goto L125;
        this.zzl = r159;
        r02 = r02 | 2;
    L125:
        MediaInfo r1510 = this.zza;
        if (r1510 != null) goto L128;
        int r1511 = -1;
    L130:
        if (zzf(this.zze, this.zzf, this.zzl, r1511) == false) goto L132;
        this.zzc = 0;
        this.zzl = 0;
        this.zzm = 0;
        List r1512 = this.zzq;
        if (r1512.isEmpty() == true) goto L170;
        r02 = r02 | 8;
        this.zzp = 0;
        r1512.clear();
        this.zzy.clear();
    L170:
        AdBreakStatus r1513 = AdBreakStatus.zzb(r14.optJSONObject("breakStatus"));
        AdBreakStatus r19 = this.zzs;
        if (r19 != null) goto L173;
        if (r1513 == null) goto L173;
    L176:
        if (r1513 != null) goto L178;
    L182:
        this.zzr = r2;
        this.zzs = r1513;
        r02 = r02 | 32;
    L183:
        VideoInfo r1514 = VideoInfo.zzb(r14.optJSONObject("videoInfo"));
        VideoInfo r110 = this.zzt;
        if (r110 != null) goto L186;
        if (r1514 == null) goto L186;
    L189:
        this.zzt = r1514;
        r02 = r02 | 64;
    L191:
        if (r14.has("breakInfo") == false) goto L196;
        MediaInfo r111 = this.zza;
        if (r111 == null) goto L196;
        r111.zza(r14.getJSONObject("breakInfo"));
        r02 = r02 | 2;
    L196:
        if (r14.has("queueData") == false) goto L201;
        MediaQueueData.Builder r112 = new MediaQueueData.Builder();
        r112.zza(r14.getJSONObject("queueData"));
        MediaQueueData r1515 = r112.build();
        this.zzv = r1515;
        boolean r1516 = r1515.zza();
        if (this.zzw == r1516) goto L201;
        this.zzw = r1516;
        r02 = r02 | 8;
    L201:
        if (r14.has("liveSeekableRange") == false) goto L204;
        int r03 = r02 | 2;
        this.zzu = MediaLiveSeekableRange.zzb(r14.optJSONObject("liveSeekableRange"));
        return r03;
    L204:
        if (this.zzu == null) goto L206;
        r02 = r02 | 2;
    L206:
        this.zzu = null;
        return r02;
    L186:
        if (r110 == null) goto L191;
        if (r110.equals(r1514) == true) goto L191;
    L178:
        if (r1513.getBreakId() == null) goto L180;
    L181:
        r2 = true;
        goto L182
    L180:
        if (r1513.getBreakClipId() == null) goto L182;
    L173:
        if (r19 == null) goto L183;
        if (r19.equals(r1513) == true) goto L183;
    L132:
        if (r14.has("repeatMode") == false) goto L140;
        Integer r1517 = MediaCommon.mediaRepeatModeFromString(r14.getString("repeatMode"));
        if (r1517 != null) goto L136;
        int r1518 = this.zzp;
    L138:
        if (this.zzp == r1518) goto L140;
        this.zzp = r1518;
        boolean r1519 = true;
    L142:
        if (r14.has(PluginsConstants.Settings.ITEMS) == false) goto L165;
        JSONArray r113 = r14.getJSONArray(PluginsConstants.Settings.ITEMS);
        int r67 = r113.length();
        SparseArray r7 = new SparseArray();
        int r8 = 0;
    L144:
        if (r8 >= r67) goto L146;
        r7.put(r8, Integer.valueOf(r113.getJSONObject(r8).getInt("itemId")));
        r8 = r8 + 1;
        goto L144
    L146:
        ArrayList r82 = new ArrayList();
        int r9 = 0;
    L147:
        if (r9 >= r67) goto L161;
        Integer r10 = (Integer) r7.get(r9);
        JSONObject r11 = r113.getJSONObject(r9);
        MediaQueueItem r122 = getItemById(r10.intValue());
        if (r122 == null) goto L154;
        r1519 = r1519 | r122.fromJson(r11);
        r82.add(r122);
        if (r9 == getIndexById(r10.intValue()).intValue()) goto L159;
    L152:
        r1519 = true;
    L159:
        r9 = r9 + 1;
        goto L147
    L154:
        if (r10.intValue() != this.zzc) goto L158;
        MediaInfo r1520 = this.zza;
        if (r1520 == null) goto L158;
        MediaQueueItem r1521 = new MediaQueueItem.Builder(r1520).build();
        r1521.fromJson(r11);
        r82.add(r1521);
    L158:
        r82.add(new MediaQueueItem(r11));
        goto L152
    L161:
        if (this.zzq.size() == r67) goto L163;
        boolean r114 = false;
    L164:
        r1519 = r1519 | (!r114);
        zze(r82);
        goto L165
    L163:
        r114 = true;
    L165:
        if (r1519 == false) goto L170;
        r02 = r02 | 8;
        goto L170
    L136:
        r1518 = r1517.intValue();
    L140:
        r1519 = false;
        goto L142
    L128:
        r1511 = r1510.getStreamType();
        goto L130
    L92:
        if (r17.length != r155.length) goto L100;
        int r115 = 0;
    L95:
        if (r115 >= r155.length) goto L102;
        if (this.zzk[r115] != r155[r115]) goto L100;
        r115 = r115 + 1;
        goto L95
    L99:
        if (r17 == null) goto L102;
    L86:
        r154 = null;
        goto L87
    L17:
        r02 = 0;
        goto L19
    L209:
        ArrayList r3 = new ArrayList();     // Catch: JSONException -> L208
        Iterator<String> r4 = r14.keys();     // Catch: JSONException -> L208
    L6:
        if (r4.hasNext() == false) goto L8;
        r3.add(r4.next());     // Catch: JSONException -> L208
        goto L6
    L8:
        JSONObject r42 = new JSONObject(r14, (String[]) r3.toArray(new String[0]));     // Catch: JSONException -> L208
        Iterator<String> r32 = r1.keys();     // Catch: JSONException -> L208
    L10:
        if (r32.hasNext() == false) goto L12;
        String r5 = r32.next();     // Catch: JSONException -> L208
        r42.put(r5, r1.get(r5));     // Catch: JSONException -> L208
        goto L10
    L12:
        r42.remove("extendedStatus");     // Catch: JSONException -> L208
        r14 = r42;
        goto L14
    }

    public final boolean zzc() {
        MediaInfo r0 = this.zza;
        if (r0 != null) goto L5;
        int r02 = -1;
    L7:
        return zzf(this.zze, this.zzf, this.zzl, r02);
    L5:
        r02 = r0.getStreamType();
        goto L7
    }

    public final /* synthetic */ void zzd(List r1) {
        zze(r1);
    }
}
