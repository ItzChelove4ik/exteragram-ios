package com.google.android.gms.cast;

import com.google.android.gms.common.internal.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaSeekOptions {
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    private final long zza;
    private final int zzb;
    private final boolean zzc;
    private final JSONObject zzd;

    public static class Builder {
        private long zza;
        private int zzb;
        private boolean zzc;
        private JSONObject zzd;

        public Builder() {
            this.zzb = 0;
        }

        public MediaSeekOptions build() {
            return new MediaSeekOptions(this.zza, this.zzb, this.zzc, this.zzd, null);
        }

        public Builder setCustomData(JSONObject r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setIsSeekToInfinite(boolean r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setPosition(long r1) {
            this.zza = r1;
            return this;
        }

        public Builder setResumeState(int r1) {
            this.zzb = r1;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResumeState {
    }

    public /* synthetic */ MediaSeekOptions(long r1, int r3, boolean r4, JSONObject r5, byte[] r6) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof MediaSeekOptions) == true) goto L8;
        return false;
    L8:
        MediaSeekOptions r82 = (MediaSeekOptions) r8;
        if (this.zza == r82.zza) goto L11;
    L17:
        return false;
    L11:
        if (this.zzb != r82.zzb) goto L17;
        if (this.zzc != r82.zzc) goto L17;
        if (Objects.equal(this.zzd, r82.zzd) == false) goto L17;
        return true;
    }

    public JSONObject getCustomData() {
        return this.zzd;
    }

    public long getPosition() {
        return this.zza;
    }

    public int getResumeState() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zza), Integer.valueOf(this.zzb), Boolean.valueOf(this.zzc), this.zzd});
    }

    public boolean isSeekToInfinite() {
        return this.zzc;
    }
}
