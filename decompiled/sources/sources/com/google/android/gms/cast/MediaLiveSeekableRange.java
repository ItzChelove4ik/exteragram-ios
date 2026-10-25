package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaLiveSeekableRange extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaLiveSeekableRange> CREATOR = null;
    private static final Logger zza = null;
    private final long zzb;
    private final long zzc;
    private final boolean zzd;
    private final boolean zze;

    public static class Builder {
        private long zza;
        private long zzb;
        private boolean zzc;
        private boolean zzd;

        public Builder() {
        }

        public MediaLiveSeekableRange build() {
            return new MediaLiveSeekableRange(this.zza, this.zzb, this.zzc, this.zzd);
        }

        public Builder setEndTime(long r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setIsLiveDone(boolean r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setIsMovingWindow(boolean r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setStartTime(long r1) {
            this.zza = r1;
            return this;
        }
    }

    static {
        zza = new Logger("MediaLiveSeekableRange");
        CREATOR = new zzbs();
    }

    public MediaLiveSeekableRange(long r3, long r5, boolean r7, boolean r8) {
        this.zzb = Math.max(r3, 0);
        this.zzc = Math.max(r5, 0);
        this.zzd = r7;
        this.zze = r8;
    }

    public static MediaLiveSeekableRange zzb(JSONObject r12) {
        if (r12 != null) goto L6;
        return null;
    L6:
        if (r12.has("start") == true) goto L8;
    L13:
        return null;
    L8:
        if (r12.has("end") == false) goto L13;
        return new MediaLiveSeekableRange(CastUtils.secToMillisec(r12.getDouble("start")), CastUtils.secToMillisec(r12.getDouble("end")), r12.optBoolean("isMovingWindow"), r12.optBoolean("isLiveDone"));
    L12:
        zza.e("Ignoring Malformed MediaLiveSeekableRange: ".concat(r12.toString()), new Object[0]);
        goto L13
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof MediaLiveSeekableRange) == true) goto L8;
        return false;
    L8:
        MediaLiveSeekableRange r82 = (MediaLiveSeekableRange) r8;
        if (this.zzb == r82.zzb) goto L11;
    L17:
        return false;
    L11:
        if (this.zzc != r82.zzc) goto L17;
        if (this.zzd != r82.zzd) goto L17;
        if (this.zze != r82.zze) goto L17;
        return true;
    }

    public long getEndTime() {
        return this.zzc;
    }

    public long getStartTime() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zzb), Long.valueOf(this.zzc), Boolean.valueOf(this.zzd), Boolean.valueOf(this.zze)});
    }

    public boolean isLiveDone() {
        return this.zze;
    }

    public boolean isMovingWindow() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeLong(r4, 2, getStartTime());
        SafeParcelWriter.writeLong(r4, 3, getEndTime());
        SafeParcelWriter.writeBoolean(r4, 4, isMovingWindow());
        SafeParcelWriter.writeBoolean(r4, 5, isLiveDone());
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L4
        r0.put("start", CastUtils.millisecToSec(this.zzb));     // Catch: JSONException -> L4
        r0.put("end", CastUtils.millisecToSec(this.zzc));     // Catch: JSONException -> L4
        r0.put("isMovingWindow", this.zzd);     // Catch: JSONException -> L4
        r0.put("isLiveDone", this.zze);     // Catch: JSONException -> L4
        return r0;
    L4:
        zza.e("Error transforming MediaLiveSeekableRange into JSONObject", new Object[0]);
        return new JSONObject();
    }
}
