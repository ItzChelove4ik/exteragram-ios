package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaQueueContainerMetadata;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaQueueData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaQueueData> CREATOR = null;
    public static final int MEDIA_QUEUE_TYPE_ALBUM = 1;
    public static final int MEDIA_QUEUE_TYPE_AUDIO_BOOK = 3;
    public static final int MEDIA_QUEUE_TYPE_GENERIC = 0;
    public static final int MEDIA_QUEUE_TYPE_LIVE_TV = 8;
    public static final int MEDIA_QUEUE_TYPE_MOVIE = 9;
    public static final int MEDIA_QUEUE_TYPE_PLAYLIST = 2;
    public static final int MEDIA_QUEUE_TYPE_PODCAST_SERIES = 5;
    public static final int MEDIA_QUEUE_TYPE_RADIO_STATION = 4;
    public static final int MEDIA_QUEUE_TYPE_TV_SERIES = 6;
    public static final int MEDIA_QUEUE_TYPE_VIDEO_PLAYLIST = 7;
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private MediaQueueContainerMetadata zze;
    private int zzf;
    private List zzg;
    private int zzh;
    private long zzi;
    private boolean zzj;

    public static class Builder {
        private final MediaQueueData zza;

        public Builder() {
            this.zza = new MediaQueueData(null);
        }

        public Builder(MediaQueueData r3) {
            this.zza = new MediaQueueData(r3, null);
        }

        public MediaQueueData build() {
            return new MediaQueueData(this.zza, null);
        }

        public Builder setContainerMetadata(MediaQueueContainerMetadata r2) {
            this.zza.zzg(r2);
            return this;
        }

        public Builder setEntity(String r2) {
            this.zza.zzd(r2);
            return this;
        }

        public Builder setItems(List<MediaQueueItem> r2) {
            this.zza.zzh(r2);
            return this;
        }

        public Builder setName(String r2) {
            this.zza.zzf(r2);
            return this;
        }

        public Builder setQueueId(String r2) {
            this.zza.zzc(r2);
            return this;
        }

        public Builder setQueueType(int r2) {
            this.zza.zze(r2);
            return this;
        }

        public Builder setRepeatMode(int r2) {
            this.zza.setRepeatMode(r2);
            return this;
        }

        public Builder setStartIndex(int r2) {
            this.zza.zzi(r2);
            return this;
        }

        public Builder setStartTime(long r2) {
            this.zza.zzj(r2);
            return this;
        }

        public final Builder zza(JSONObject r2) {
            this.zza.zzk(r2);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQueueType {
    }

    static {
        CREATOR = new zzbx();
    }

    private MediaQueueData() {
        throw null;
    }

    public /* synthetic */ MediaQueueData(MediaQueueData r3, byte[] r4) {
        this.zza = r3.zza;
        this.zzb = r3.zzb;
        this.zzc = r3.zzc;
        this.zzd = r3.zzd;
        this.zze = r3.zze;
        this.zzf = r3.zzf;
        this.zzg = r3.zzg;
        this.zzh = r3.zzh;
        this.zzi = r3.zzi;
        this.zzj = r3.zzj;
    }

    public MediaQueueData(String r1, String r2, int r3, String r4, MediaQueueContainerMetadata r5, int r6, List r7, int r8, long r9, boolean r11) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r11;
    }

    public /* synthetic */ MediaQueueData(byte[] r1) {
        zzl();
    }

    private final void zzl() {
        this.zza = null;
        this.zzb = null;
        this.zzc = 0;
        this.zzd = null;
        this.zzf = 0;
        this.zzg = null;
        this.zzh = 0;
        this.zzi = -1;
        this.zzj = false;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof MediaQueueData) == true) goto L8;
        return false;
    L8:
        MediaQueueData r82 = (MediaQueueData) r8;
        if (TextUtils.equals(this.zza, r82.zza) == true) goto L11;
    L29:
        return false;
    L11:
        if (TextUtils.equals(this.zzb, r82.zzb) == false) goto L29;
        if (this.zzc != r82.zzc) goto L29;
        if (TextUtils.equals(this.zzd, r82.zzd) == false) goto L29;
        if (Objects.equal(this.zze, r82.zze) == false) goto L29;
        if (this.zzf != r82.zzf) goto L29;
        if (Objects.equal(this.zzg, r82.zzg) == false) goto L29;
        if (this.zzh != r82.zzh) goto L29;
        if (this.zzi != r82.zzi) goto L29;
        if (this.zzj != r82.zzj) goto L29;
        return true;
    }

    public MediaQueueContainerMetadata getContainerMetadata() {
        return this.zze;
    }

    public String getEntity() {
        return this.zzb;
    }

    public List<MediaQueueItem> getItems() {
        List r0 = this.zzg;
        if (r0 != null) goto L7;
        return null;
    L7:
        return Collections.unmodifiableList(r0);
    }

    public String getName() {
        return this.zzd;
    }

    public String getQueueId() {
        return this.zza;
    }

    public int getQueueType() {
        return this.zzc;
    }

    public int getRepeatMode() {
        return this.zzf;
    }

    public int getStartIndex() {
        return this.zzh;
    }

    public long getStartTime() {
        return this.zzi;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, Integer.valueOf(this.zzc), this.zzd, this.zze, Integer.valueOf(this.zzf), this.zzg, Integer.valueOf(this.zzh), Long.valueOf(this.zzi), Boolean.valueOf(this.zzj)});
    }

    public void setRepeatMode(int r1) {
        this.zzf = r1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, getQueueId(), false);
        SafeParcelWriter.writeString(r5, 3, getEntity(), false);
        SafeParcelWriter.writeInt(r5, 4, getQueueType());
        SafeParcelWriter.writeString(r5, 5, getName(), false);
        SafeParcelWriter.writeParcelable(r5, 6, getContainerMetadata(), r6, false);
        SafeParcelWriter.writeInt(r5, 7, getRepeatMode());
        SafeParcelWriter.writeTypedList(r5, 8, getItems(), false);
        SafeParcelWriter.writeInt(r5, 9, getStartIndex());
        SafeParcelWriter.writeLong(r5, 10, getStartTime());
        SafeParcelWriter.writeBoolean(r5, 11, this.zzj);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final boolean zza() {
        return this.zzj;
    }

    public final JSONObject zzb() {
        JSONObject r0 = new JSONObject();
        if (TextUtils.isEmpty(this.zza) == true) goto L7;
        r0.put("id", this.zza);     // Catch: JSONException -> L45
    L7:
        if (TextUtils.isEmpty(this.zzb) == true) goto L11;
        r0.put("entity", this.zzb);     // Catch: JSONException -> L45
    L11:
        switch(this.zzc) {
            case 1: goto L21;
            case 2: goto L20;
            case 3: goto L19;
            case 4: goto L18;
            case 5: goto L17;
            case 6: goto L16;
            case 7: goto L15;
            case 8: goto L14;
            case 9: goto L13;
            default: goto L23;
        };
    L13:
        r0.put("queueType", "MOVIE");     // Catch: JSONException -> L45
        goto L23
    L14:
        r0.put("queueType", "LIVE_TV");     // Catch: JSONException -> L45
        goto L23
    L15:
        r0.put("queueType", "VIDEO_PLAYLIST");     // Catch: JSONException -> L45
        goto L23
    L16:
        r0.put("queueType", "TV_SERIES");     // Catch: JSONException -> L45
        goto L23
    L17:
        r0.put("queueType", "PODCAST_SERIES");     // Catch: JSONException -> L45
        goto L23
    L18:
        r0.put("queueType", "RADIO_STATION");     // Catch: JSONException -> L45
        goto L23
    L19:
        r0.put("queueType", "AUDIOBOOK");     // Catch: JSONException -> L45
        goto L23
    L20:
        r0.put("queueType", "PLAYLIST");     // Catch: JSONException -> L45
        goto L23
    L21:
        r0.put("queueType", "ALBUM");     // Catch: JSONException -> L45
    L23:
        if (TextUtils.isEmpty(this.zzd) == true) goto L25;
        r0.put("name", this.zzd);     // Catch: JSONException -> L45
    L25:
        MediaQueueContainerMetadata r1 = this.zze;     // Catch: JSONException -> L45
        if (r1 == null) goto L28;
        r0.put("containerMetadata", r1.zzb());     // Catch: JSONException -> L45
    L28:
        String r12 = MediaCommon.zza(Integer.valueOf(this.zzf));     // Catch: JSONException -> L45
        if (r12 == null) goto L31;
        r0.put("repeatMode", r12);     // Catch: JSONException -> L45
    L31:
        List r13 = this.zzg;     // Catch: JSONException -> L45
        if (r13 != null) goto L34;
    L40:
        r0.put("startIndex", this.zzh);     // Catch: JSONException -> L45
        long r14 = this.zzi;     // Catch: JSONException -> L45
        if (r14 == (-1)) goto L43;
        r0.put("startTime", CastUtils.millisecToSec(r14));     // Catch: JSONException -> L45
    L43:
        r0.put("shuffle", this.zzj);     // Catch: JSONException -> L45
        goto L44
    L34:
        if (r13.isEmpty() == true) goto L40;
        JSONArray r15 = new JSONArray();     // Catch: JSONException -> L45
        Iterator r2 = this.zzg.iterator();     // Catch: JSONException -> L45
    L37:
        if (r2.hasNext() == false) goto L39;
        r15.put(((MediaQueueItem) r2.next()).toJson());     // Catch: JSONException -> L45
        goto L37
    L39:
        r0.put(PluginsConstants.Settings.ITEMS, r15);     // Catch: JSONException -> L45
    L44:
        return r0;
    }

    public final /* synthetic */ void zzc(String r1) {
        this.zza = r1;
    }

    public final /* synthetic */ void zzd(String r1) {
        this.zzb = r1;
    }

    public final /* synthetic */ void zze(int r1) {
        this.zzc = r1;
    }

    public final /* synthetic */ void zzf(String r1) {
        this.zzd = r1;
    }

    public final /* synthetic */ void zzg(MediaQueueContainerMetadata r1) {
        this.zze = r1;
    }

    public final /* synthetic */ void zzh(List r2) {
        if (r2 != null) goto L4;
        ArrayList r22 = null;
    L5:
        this.zzg = r22;
        return;
    L4:
        r22 = new ArrayList(r2);
        goto L5
    }

    public final /* synthetic */ void zzi(int r1) {
        this.zzh = r1;
    }

    public final /* synthetic */ void zzj(long r1) {
        this.zzi = r1;
    }

    public final /* synthetic */ void zzk(JSONObject r6) {
        zzl();
        if (r6 != null) goto L5;
        return;
    L5:
        this.zza = CastUtils.optStringOrNull(r6, "id");
        this.zzb = CastUtils.optStringOrNull(r6, "entity");
        String r0 = r6.optString("queueType");
        switch(r0.hashCode()) {
            case -1803151310: goto L34;
            case -1758903120: goto L31;
            case -1632865838: goto L28;
            case -1319760993: goto L25;
            case -1088524588: goto L22;
            case 62359119: goto L19;
            case 73549584: goto L16;
            case 393100598: goto L13;
            case 902303413: goto L9;
            default: goto L36;
        };
    L36:
        this.zzd = CastUtils.optStringOrNull(r6, "name");
        if (r6.has("containerMetadata") == false) goto L39;
        JSONObject r02 = r6.optJSONObject("containerMetadata");
    L40:
        if (r02 == null) goto L42;
        MediaQueueContainerMetadata.Builder r1 = new MediaQueueContainerMetadata.Builder();
        r1.zza(r02);
        this.zze = r1.build();
    L42:
        Integer r03 = MediaCommon.mediaRepeatModeFromString(r6.optString("repeatMode"));
        if (r03 == null) goto L45;
        this.zzf = r03.intValue();
    L45:
        JSONArray r04 = r6.optJSONArray(PluginsConstants.Settings.ITEMS);
        if (r04 == null) goto L54;
        ArrayList r12 = new ArrayList();
        this.zzg = r12;
        int r2 = 0;
    L49:
        if (r2 >= r04.length()) goto L54;
        JSONObject r3 = r04.optJSONObject(r2);
        if (r3 == null) goto L53;
        r12.add(new MediaQueueItem(r3));     // Catch: JSONException -> L59
    L53:
        r2 = r2 + 1;
    L54:
        this.zzh = r6.optInt("startIndex", this.zzh);
        if (r6.has("startTime") == false) goto L57;
        this.zzi = CastUtils.secToMillisec(r6.optDouble("startTime", this.zzi));
    L57:
        this.zzj = r6.optBoolean("shuffle");
        return;
    L39:
        r02 = null;
        goto L40
    L9:
        if (r0.equals("LIVE_TV") == false) goto L36;
        int r05 = 8;
    L11:
        this.zzc = r05;
        goto L36
    L13:
        if (r0.equals("VIDEO_PLAYLIST") == false) goto L36;
        r05 = 7;
        goto L11
    L16:
        if (r0.equals("MOVIE") == false) goto L36;
        r05 = 9;
        goto L11
    L19:
        if (r0.equals("ALBUM") == false) goto L36;
        r05 = 1;
        goto L11
    L22:
        if (r0.equals("TV_SERIES") == false) goto L36;
        r05 = 6;
        goto L11
    L25:
        if (r0.equals("AUDIOBOOK") == false) goto L36;
        r05 = 3;
        goto L11
    L28:
        if (r0.equals("PLAYLIST") == false) goto L36;
        r05 = 2;
        goto L11
    L31:
        if (r0.equals("RADIO_STATION") == false) goto L36;
        r05 = 4;
        goto L11
    L34:
        if (r0.equals("PODCAST_SERIES") == false) goto L36;
        r05 = 5;
        goto L11
    }
}
