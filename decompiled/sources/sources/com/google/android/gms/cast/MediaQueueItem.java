package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import java.util.Arrays;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaQueueItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaQueueItem> CREATOR = null;
    public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
    public static final int INVALID_ITEM_ID = 0;
    String zza;
    private MediaInfo zzb;
    private int zzc;
    private boolean zzd;
    private double zze;
    private double zzf;
    private double zzg;
    private long[] zzh;
    private JSONObject zzi;
    private final Writer zzj;

    public static class Builder {
        private final MediaQueueItem zza;

        public Builder(MediaInfo r3) {
            this.zza = new MediaQueueItem(r3, null);
        }

        public Builder(MediaQueueItem r3) {
            this.zza = new MediaQueueItem(r3, null);
        }

        public Builder(JSONObject r2) {
            this.zza = new MediaQueueItem(r2);
        }

        public MediaQueueItem build() {
            MediaQueueItem r0 = this.zza;
            r0.zza();
            return r0;
        }

        public Builder clearItemId() {
            this.zza.getWriter().setItemId(0);
            return this;
        }

        public Builder setActiveTrackIds(long[] r2) {
            this.zza.getWriter().setActiveTrackIds(r2);
            return this;
        }

        public Builder setAutoplay(boolean r2) {
            this.zza.getWriter().setAutoplay(r2);
            return this;
        }

        public Builder setCustomData(JSONObject r2) {
            this.zza.getWriter().setCustomData(r2);
            return this;
        }

        public Builder setItemId(int r2) {
            this.zza.getWriter().setItemId(r2);
            return this;
        }

        public Builder setPlaybackDuration(double r2) {
            this.zza.getWriter().setPlaybackDuration(r2);
            return this;
        }

        public Builder setPreloadTime(double r2) {
            this.zza.getWriter().setPreloadTime(r2);
            return this;
        }

        public Builder setStartTime(double r2) {
            this.zza.getWriter().setStartTime(r2);
            return this;
        }
    }

    public class Writer {
        final /* synthetic */ MediaQueueItem zza;

        public Writer(MediaQueueItem r1) {
            Objects.requireNonNull(r1);
            this.zza = r1;
        }

        public void setActiveTrackIds(long[] r1) {
            this.zza.zzh(r1);
        }

        public void setAutoplay(boolean r1) {
            this.zza.zzd(r1);
        }

        public void setCustomData(JSONObject r1) {
            this.zza.zzi(r1);
        }

        public void setItemId(int r1) {
            this.zza.zzc(r1);
        }

        public void setMedia(MediaInfo r1) {
            this.zza.zzb(r1);
        }

        public void setPlaybackDuration(double r2) {
            if (Double.isNaN(r2) == true) goto L6;
            this.zza.zzf(r2);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("playbackDuration cannot be NaN.");
        }

        public void setPreloadTime(double r3) {
            if (Double.isNaN(r3) == false) goto L5;
        L8:
            f$$ExternalSyntheticBUOutline1.m("preloadTime cannot be negative or NaN.");
            return;
        L5:
            if (r3 < 0.0d) goto L8;
            this.zza.zzg(r3);
        }

        public void setStartTime(double r3) {
            if (Double.isNaN(r3) == false) goto L5;
        L9:
            this.zza.zze(r3);
            return;
        L5:
            if (r3 >= 0.0d) goto L9;
            f$$ExternalSyntheticBUOutline1.m("startTime cannot be negative.");
        }
    }

    static {
        CREATOR = new zzby();
    }

    public MediaQueueItem(MediaInfo r3, int r4, boolean r5, double r6, double r8, double r10, long[] r12, String r13) {
        this.zze = Double.NaN;
        this.zzj = new Writer(this);
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r8;
        this.zzg = r10;
        this.zzh = r12;
        this.zza = r13;
        if (r13 != null) goto L10;
        this.zzi = null;
        return;
    L10:
        this.zzi = new JSONObject(this.zza);     // Catch: JSONException -> L6
        return;
    L6:
        this.zzi = null;
        this.zza = null;
    }

    public /* synthetic */ MediaQueueItem(MediaInfo r13, byte[] r14) {
        this(r13, 0, true, Double.NaN, Double.POSITIVE_INFINITY, 0.0d, null, null);
        if (r13 == null) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("media cannot be null.");
        throw null;
    }

    public /* synthetic */ MediaQueueItem(MediaQueueItem r13, byte[] r14) {
        this(r13.getMedia(), r13.getItemId(), r13.getAutoplay(), r13.getStartTime(), r13.getPlaybackDuration(), r13.getPreloadTime(), r13.getActiveTrackIds(), null);
        if (this.zzb == null) goto L6;
        this.zzi = r13.getCustomData();
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("media cannot be null.");
        throw null;
    }

    public MediaQueueItem(JSONObject r13) {
        this(null, 0, true, Double.NaN, Double.POSITIVE_INFINITY, 0.0d, null, null);
        fromJson(r13);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof MediaQueueItem) == true) goto L8;
        return false;
    L8:
        MediaQueueItem r82 = (MediaQueueItem) r8;
        JSONObject r1 = this.zzi;
        if (r1 == null) goto L11;
        boolean r3 = false;
    L12:
        JSONObject r4 = r82.zzi;
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
        if (CastUtils.zza(this.zzb, r82.zzb) == true) goto L26;
    L42:
        return false;
    L26:
        if (this.zzc != r82.zzc) goto L42;
        if (this.zzd != r82.zzd) goto L42;
        if (Double.isNaN(this.zze) == false) goto L34;
        if (Double.isNaN(r82.zze) == false) goto L34;
    L36:
        if (this.zzf != r82.zzf) goto L42;
        if (this.zzg != r82.zzg) goto L42;
        if (Arrays.equals(this.zzh, r82.zzh) == false) goto L42;
        return true;
    L34:
        if (this.zze != r82.zze) goto L42;
    L15:
        r5 = true;
        goto L16
    L11:
        r3 = true;
        goto L12
    }

    public boolean fromJson(JSONObject r12) {
        boolean r2 = false;
        if (r12.has("media") == false) goto L5;
        this.zzb = new MediaInfo(r12.getJSONObject("media"));
        boolean r0 = true;
    L7:
        if (r12.has("itemId") == false) goto L12;
        int r1 = r12.getInt("itemId");
        if (this.zzc == r1) goto L12;
        this.zzc = r1;
        r0 = true;
    L12:
        if (r12.has("autoplay") == false) goto L16;
        boolean r13 = r12.getBoolean("autoplay");
        if (this.zzd == r13) goto L16;
        this.zzd = r13;
        r0 = true;
    L16:
        double r4 = r12.optDouble("startTime");
        if (Double.isNaN(r4) == Double.isNaN(this.zze)) goto L20;
    L23:
        this.zze = r4;
        r0 = true;
    L25:
        if (r12.has("playbackDuration") == false) goto L30;
        double r42 = r12.getDouble("playbackDuration");
        if (Math.abs(r42 - this.zzf) <= 1.0E-7d) goto L30;
        this.zzf = r42;
        r0 = true;
    L30:
        if (r12.has("preloadTime") == false) goto L35;
        double r43 = r12.getDouble("preloadTime");
        if (Math.abs(r43 - this.zzg) <= 1.0E-7d) goto L35;
        this.zzg = r43;
        r0 = true;
    L35:
        if (r12.has("activeTrackIds") == false) goto L51;
        JSONArray r14 = r12.getJSONArray("activeTrackIds");
        int r44 = r14.length();
        long[] r5 = new long[r44];
        int r6 = 0;
    L37:
        if (r6 >= r44) goto L39;
        r5[r6] = r14.getLong(r6);
        r6 = r6 + 1;
        goto L37
    L39:
        long[] r15 = this.zzh;
        if (r15 != null) goto L43;
    L41:
        r2 = true;
    L52:
        if (r2 == false) goto L55;
        this.zzh = r5;
        r0 = true;
    L55:
        if (r12.has("customData") == false) goto L58;
        this.zzi = r12.getJSONObject("customData");
        return true;
    L58:
        return r0;
    L43:
        if (r15.length != r44) goto L41;
        int r16 = 0;
    L46:
        if (r16 >= r44) goto L52;
        if (this.zzh[r16] != r5[r16]) goto L41;
        r16 = r16 + 1;
        goto L46
    L51:
        r5 = null;
        goto L52
    L20:
        if (Double.isNaN(r4) == true) goto L25;
        if (Math.abs(r4 - this.zze) <= 1.0E-7d) goto L25;
    L5:
        r0 = false;
        goto L7
    }

    public long[] getActiveTrackIds() {
        return this.zzh;
    }

    public boolean getAutoplay() {
        return this.zzd;
    }

    public JSONObject getCustomData() {
        return this.zzi;
    }

    public int getItemId() {
        return this.zzc;
    }

    public MediaInfo getMedia() {
        return this.zzb;
    }

    public double getPlaybackDuration() {
        return this.zzf;
    }

    public double getPreloadTime() {
        return this.zzg;
    }

    public double getStartTime() {
        return this.zze;
    }

    public Writer getWriter() {
        return this.zzj;
    }

    public int hashCode() {
        return com.google.android.gms.common.internal.Objects.hashCode(new Object[]{this.zzb, Integer.valueOf(this.zzc), Boolean.valueOf(this.zzd), Double.valueOf(this.zze), Double.valueOf(this.zzf), Double.valueOf(this.zzg), Integer.valueOf(Arrays.hashCode(this.zzh)), String.valueOf(this.zzi)});
    }

    public JSONObject toJson() {
        JSONObject r0 = new JSONObject();
        MediaInfo r1 = this.zzb;     // Catch: JSONException -> L25
        if (r1 == null) goto L6;
        r0.put("media", r1.zzb());     // Catch: JSONException -> L25
    L6:
        int r12 = this.zzc;     // Catch: JSONException -> L25
        if (r12 == 0) goto L9;
        r0.put("itemId", r12);     // Catch: JSONException -> L25
    L9:
        r0.put("autoplay", this.zzd);     // Catch: JSONException -> L25
        if (Double.isNaN(this.zze) == true) goto L12;
        r0.put("startTime", this.zze);     // Catch: JSONException -> L25
    L12:
        double r13 = this.zzf;     // Catch: JSONException -> L25
        if (r13 == Double.POSITIVE_INFINITY) goto L15;
        r0.put("playbackDuration", r13);     // Catch: JSONException -> L25
    L15:
        r0.put("preloadTime", this.zzg);     // Catch: JSONException -> L25
        if (this.zzh == null) goto L21;
        JSONArray r14 = new JSONArray();     // Catch: JSONException -> L25
        long[] r2 = this.zzh;     // Catch: JSONException -> L25
        int r3 = r2.length;     // Catch: JSONException -> L25
        int r4 = 0;
    L18:
        if (r4 >= r3) goto L20;
        r14.put(r2[r4]);     // Catch: JSONException -> L25
        r4 = r4 + 1;     // Catch: JSONException -> L25
        goto L18
    L20:
        r0.put("activeTrackIds", r14);     // Catch: JSONException -> L25
    L21:
        JSONObject r7 = this.zzi;     // Catch: JSONException -> L25
        if (r7 == null) goto L24;
        r0.put("customData", r7);     // Catch: JSONException -> L25
    L24:
        return r0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        JSONObject r0 = this.zzi;
        if (r0 != null) goto L5;
        String r02 = null;
    L6:
        this.zza = r02;
        int r03 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, getMedia(), r6, false);
        SafeParcelWriter.writeInt(r5, 3, getItemId());
        SafeParcelWriter.writeBoolean(r5, 4, getAutoplay());
        SafeParcelWriter.writeDouble(r5, 5, getStartTime());
        SafeParcelWriter.writeDouble(r5, 6, getPlaybackDuration());
        SafeParcelWriter.writeDouble(r5, 7, getPreloadTime());
        SafeParcelWriter.writeLongArray(r5, 8, getActiveTrackIds(), false);
        SafeParcelWriter.writeString(r5, 9, this.zza, false);
        SafeParcelWriter.finishObjectHeader(r5, r03);
        return;
    L5:
        r02 = r0.toString();
        goto L6
    }

    public final void zza() {
        if (this.zzb != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("media cannot be null.");
        return;
    L5:
        if (Double.isNaN(this.zze) == true) goto L12;
        if (this.zze >= 0.0d) goto L12;
        f$$ExternalSyntheticBUOutline1.m("startTime cannot be negative or NaN.");
        return;
    L12:
        if (Double.isNaN(this.zzf) == false) goto L14;
        f$$ExternalSyntheticBUOutline1.m("playbackDuration cannot be NaN.");
        return;
    L14:
        if (Double.isNaN(this.zzg) == false) goto L16;
    L18:
        f$$ExternalSyntheticBUOutline1.m("preloadTime cannot be negative or Nan.");
        return;
    L16:
        if (this.zzg < 0.0d) goto L18;
    }

    public final /* synthetic */ void zzb(MediaInfo r1) {
        this.zzb = r1;
    }

    public final /* synthetic */ void zzc(int r1) {
        this.zzc = r1;
    }

    public final /* synthetic */ void zzd(boolean r1) {
        this.zzd = r1;
    }

    public final /* synthetic */ void zze(double r1) {
        this.zze = r1;
    }

    public final /* synthetic */ void zzf(double r1) {
        this.zzf = r1;
    }

    public final /* synthetic */ void zzg(double r1) {
        this.zzg = r1;
    }

    public final /* synthetic */ void zzh(long[] r1) {
        this.zzh = r1;
    }

    public final /* synthetic */ void zzi(JSONObject r1) {
        this.zzi = r1;
    }
}
