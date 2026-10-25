package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AdBreakInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AdBreakInfo> CREATOR = null;
    private final long zza;
    private final String zzb;
    private final long zzc;
    private final boolean zzd;
    private final String[] zze;
    private final boolean zzf;
    private final boolean zzg;

    public static class Builder {
        private final long zza;
        private String zzb;
        private long zzc;
        private boolean zzd;
        private boolean zze;
        private String[] zzf;
        private boolean zzg;

        public Builder(long r1) {
            this.zza = r1;
        }

        public AdBreakInfo build() {
            return new AdBreakInfo(this.zza, this.zzb, this.zzc, this.zzd, this.zzf, this.zze, this.zzg);
        }

        public Builder setBreakClipIds(String[] r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setDurationInMs(long r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setId(String r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setIsEmbedded(boolean r1) {
            this.zze = r1;
            return this;
        }

        public Builder setIsExpanded(boolean r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setIsWatched(boolean r1) {
            this.zzd = r1;
            return this;
        }
    }

    static {
        CREATOR = new zzb();
    }

    public AdBreakInfo(long r1, String r3, long r4, boolean r6, String[] r7, boolean r8, boolean r9) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r6;
        this.zze = r7;
        this.zzf = r8;
        this.zzg = r9;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof AdBreakInfo) == true) goto L8;
        return false;
    L8:
        AdBreakInfo r82 = (AdBreakInfo) r8;
        if (CastUtils.zza(this.zzb, r82.zzb) == true) goto L11;
    L23:
        return false;
    L11:
        if (this.zza != r82.zza) goto L23;
        if (this.zzc != r82.zzc) goto L23;
        if (this.zzd != r82.zzd) goto L23;
        if (Arrays.equals(this.zze, r82.zze) == false) goto L23;
        if (this.zzf != r82.zzf) goto L23;
        if (this.zzg != r82.zzg) goto L23;
        return true;
    }

    public String[] getBreakClipIds() {
        return this.zze;
    }

    public long getDurationInMs() {
        return this.zzc;
    }

    public String getId() {
        return this.zzb;
    }

    public long getPlaybackPositionInMs() {
        return this.zza;
    }

    public int hashCode() {
        return this.zzb.hashCode();
    }

    public boolean isEmbedded() {
        return this.zzf;
    }

    public boolean isExpanded() {
        return this.zzg;
    }

    public boolean isWatched() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r6, int r7) {
        int r72 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeLong(r6, 2, getPlaybackPositionInMs());
        SafeParcelWriter.writeString(r6, 3, getId(), false);
        SafeParcelWriter.writeLong(r6, 4, getDurationInMs());
        SafeParcelWriter.writeBoolean(r6, 5, isWatched());
        SafeParcelWriter.writeStringArray(r6, 6, getBreakClipIds(), false);
        SafeParcelWriter.writeBoolean(r6, 7, isEmbedded());
        SafeParcelWriter.writeBoolean(r6, 8, isExpanded());
        SafeParcelWriter.finishObjectHeader(r6, r72);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();
        r0.put("id", this.zzb);     // Catch: JSONException -> L10
        r0.put("position", CastUtils.millisecToSec(this.zza));     // Catch: JSONException -> L10
        r0.put("isWatched", this.zzd);     // Catch: JSONException -> L10
        r0.put("isEmbedded", this.zzf);     // Catch: JSONException -> L10
        r0.put("duration", CastUtils.millisecToSec(this.zzc));     // Catch: JSONException -> L10
        r0.put("expanded", this.zzg);     // Catch: JSONException -> L10
        String[] r5 = this.zze;     // Catch: JSONException -> L10
        if (r5 == null) goto L9;
        JSONArray r1 = new JSONArray();     // Catch: JSONException -> L10
        int r2 = r5.length;     // Catch: JSONException -> L10
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L8;
        r1.put(r5[r3]);     // Catch: JSONException -> L10
        r3 = r3 + 1;     // Catch: JSONException -> L10
        goto L6
    L8:
        r0.put("breakClipIds", r1);     // Catch: JSONException -> L10
    L9:
        return r0;
    }
}
