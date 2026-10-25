package com.google.android.gms.cast;

import c.f$$ExternalSyntheticBUOutline1;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaLoadOptions {
    public static final double PLAYBACK_RATE_MAX = 2.0d;
    public static final double PLAYBACK_RATE_MIN = 0.5d;
    private final boolean zza;
    private final long zzb;
    private final double zzc;
    private final long[] zzd;
    private final JSONObject zze;
    private final String zzf;
    private final String zzg;

    public static class Builder {
        private boolean zza;
        private long zzb;
        private double zzc;
        private long[] zzd;
        private JSONObject zze;
        private String zzf;
        private String zzg;

        public Builder() {
            this.zza = true;
            this.zzb = -1;
            this.zzc = 1.0d;
        }

        public MediaLoadOptions build() {
            return new MediaLoadOptions(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, null);
        }

        public Builder setActiveTrackIds(long[] r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setAutoplay(boolean r1) {
            this.zza = r1;
            return this;
        }

        public Builder setCredentials(String r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setCredentialsType(String r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setCustomData(JSONObject r1) {
            this.zze = r1;
            return this;
        }

        public Builder setPlayPosition(long r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setPlaybackRate(double r3) {
            if (Double.compare(r3, 2.0d) <= 0) goto L5;
        L8:
            f$$ExternalSyntheticBUOutline1.m("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
            return null;
        L5:
            if (Double.compare(r3, 0.5d) < 0) goto L8;
            this.zzc = r3;
            return this;
        }
    }

    public /* synthetic */ MediaLoadOptions(boolean r1, long r2, double r4, long[] r6, JSONObject r7, String r8, String r9, byte[] r10) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        this.zzd = r6;
        this.zze = r7;
        this.zzf = r8;
        this.zzg = r9;
    }

    public long[] getActiveTrackIds() {
        return this.zzd;
    }

    public boolean getAutoplay() {
        return this.zza;
    }

    public String getCredentials() {
        return this.zzf;
    }

    public String getCredentialsType() {
        return this.zzg;
    }

    public JSONObject getCustomData() {
        return this.zze;
    }

    public long getPlayPosition() {
        return this.zzb;
    }

    public double getPlaybackRate() {
        return this.zzc;
    }
}
