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
public class AdBreakStatus extends AbstractSafeParcelable {

    @Deprecated
    public static final int AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakStatus> CREATOR = null;
    private static final Logger zza = null;
    private final long zzb;
    private final long zzc;
    private final String zzd;
    private final String zze;
    private final long zzf;

    public static class Builder {
        private long zza;
        private long zzb;
        private String zzc;
        private String zzd;
        private long zze;

        public Builder() {
            this.zze = -1;
        }

        public AdBreakStatus build() {
            return new AdBreakStatus(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        public Builder setBreakClipId(String r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setBreakId(String r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setCurrentBreakClipTimeInMs(long r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setCurrentBreakTimeInMs(long r1) {
            this.zza = r1;
            return this;
        }

        public Builder setWhenSkippableInMs(long r1) {
            this.zze = r1;
            return this;
        }
    }

    static {
        zza = new Logger("AdBreakStatus");
        CREATOR = new zzc();
    }

    public AdBreakStatus(long r1, long r3, String r5, String r6, long r7) {
        this.zzb = r1;
        this.zzc = r3;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
    }

    public static AdBreakStatus zzb(JSONObject r14) {
        if (r14 != null) goto L6;
        return null;
    L6:
        if (r14.has("currentBreakTime") == true) goto L8;
    L19:
        return null;
    L8:
        if (r14.has("currentBreakClipTime") == false) goto L19;
        long r6 = CastUtils.secToMillisec(r14.getLong("currentBreakTime"));     // Catch: JSONException -> L14
        long r8 = CastUtils.secToMillisec(r14.getLong("currentBreakClipTime"));     // Catch: JSONException -> L14
        String r10 = CastUtils.optStringOrNull(r14, "breakId");     // Catch: JSONException -> L14
        String r11 = CastUtils.optStringOrNull(r14, "breakClipId");     // Catch: JSONException -> L14
        long r4 = r14.optLong("whenSkippable", -1);     // Catch: JSONException -> L14
        if (r4 == (-1)) goto L16;
        r4 = CastUtils.secToMillisec(r4);     // Catch: JSONException -> L14
    L16:
        return new AdBreakStatus(r6, r8, r10, r11, r4);
    L14:
        e = move-exception;
        zza.e(e, "Error while creating an AdBreakClipInfo from JSON", new Object[0]);
        goto L19
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof AdBreakStatus) == true) goto L8;
        return false;
    L8:
        AdBreakStatus r82 = (AdBreakStatus) r8;
        if (this.zzb == r82.zzb) goto L11;
    L19:
        return false;
    L11:
        if (this.zzc != r82.zzc) goto L19;
        if (CastUtils.zza(this.zzd, r82.zzd) == false) goto L19;
        if (CastUtils.zza(this.zze, r82.zze) == false) goto L19;
        if (this.zzf != r82.zzf) goto L19;
        return true;
    }

    public String getBreakClipId() {
        return this.zze;
    }

    public String getBreakId() {
        return this.zzd;
    }

    public long getCurrentBreakClipTimeInMs() {
        return this.zzc;
    }

    public long getCurrentBreakTimeInMs() {
        return this.zzb;
    }

    public long getWhenSkippableInMs() {
        return this.zzf;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zzb), Long.valueOf(this.zzc), this.zzd, this.zze, Long.valueOf(this.zzf)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeLong(r4, 2, getCurrentBreakTimeInMs());
        SafeParcelWriter.writeLong(r4, 3, getCurrentBreakClipTimeInMs());
        SafeParcelWriter.writeString(r4, 4, getBreakId(), false);
        SafeParcelWriter.writeString(r4, 5, getBreakClipId(), false);
        SafeParcelWriter.writeLong(r4, 6, getWhenSkippableInMs());
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();
        r0.put("currentBreakTime", CastUtils.millisecToSec(this.zzb));     // Catch: JSONException -> L7
        r0.put("currentBreakClipTime", CastUtils.millisecToSec(this.zzc));     // Catch: JSONException -> L7
        r0.putOpt("breakId", this.zzd);     // Catch: JSONException -> L7
        r0.putOpt("breakClipId", this.zze);     // Catch: JSONException -> L7
        long r1 = this.zzf;     // Catch: JSONException -> L7
        if (r1 == (-1)) goto L6;
        r0.put("whenSkippable", CastUtils.millisecToSec(r1));     // Catch: JSONException -> L7
    L6:
        return r0;
    L7:
        e = move-exception;
        zza.e(e, "Error transforming AdBreakStatus into JSONObject", new Object[0]);
        return new JSONObject();
    }
}
