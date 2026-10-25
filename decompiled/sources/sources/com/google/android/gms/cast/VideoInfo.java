package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class VideoInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VideoInfo> CREATOR = null;
    public static final int HDR_TYPE_DV = 3;
    public static final int HDR_TYPE_HDR = 4;
    public static final int HDR_TYPE_HDR10 = 2;
    public static final int HDR_TYPE_SDR = 1;
    public static final int HDR_TYPE_UNKNOWN = 0;
    private static final Logger zza = null;
    private final int zzb;
    private final int zzc;
    private final int zzd;

    public static class Builder {
        private int zza;
        private int zzb;
        private int zzc;

        public Builder() {
        }

        public VideoInfo build() {
            return new VideoInfo(this.zza, this.zzb, this.zzc);
        }

        public Builder setHdrType(int r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setHeight(int r1) {
            this.zzb = r1;
            return this;
        }

        public Builder setWidth(int r1) {
            this.zza = r1;
            return this;
        }
    }

    static {
        zza = new Logger("VideoInfo");
        CREATOR = new zzdh();
    }

    public VideoInfo(int r1, int r2, int r3) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
    }

    public static VideoInfo zzb(JSONObject r6) {
        if (r6 != null) goto L34;
        return null;
    L34:
        String r2 = r6.getString("hdrType");     // Catch: JSONException -> L31
        int r3 = r2.hashCode();     // Catch: JSONException -> L31
        if (r3 == 3218) goto L26;
        if (r3 == 103158) goto L23;
        if (r3 == 113729) goto L20;
        if (r3 == 99136405) goto L17;
    L28:
        zza.d("Unknown HDR type: %s", new Object[]{r2});     // Catch: JSONException -> L31
        int r22 = 0;
    L29:
        return new VideoInfo(r6.getInt("width"), r6.getInt("height"), r22);
    L17:
        if (r2.equals("hdr10") == false) goto L28;
        r22 = 2;
        goto L29
    L20:
        if (r2.equals("sdr") == false) goto L28;
        r22 = 1;
        goto L29
    L23:
        if (r2.equals("hdr") == false) goto L28;
        r22 = 4;
        goto L29
    L26:
        if (r2.equals("dv") == false) goto L28;
        r22 = 3;
    L31:
        e = move-exception;
        zza.d(e, "Error while creating a VideoInfo instance from JSON", new Object[0]);
        return null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof VideoInfo) == true) goto L8;
        return false;
    L8:
        VideoInfo r52 = (VideoInfo) r5;
        if (this.zzc == r52.getHeight()) goto L11;
    L15:
        return false;
    L11:
        if (this.zzb != r52.getWidth()) goto L15;
        if (this.zzd != r52.getHdrType()) goto L15;
        return true;
    }

    public int getHdrType() {
        return this.zzd;
    }

    public int getHeight() {
        return this.zzc;
    }

    public int getWidth() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), Integer.valueOf(this.zzd)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 2, getWidth());
        SafeParcelWriter.writeInt(r3, 3, getHeight());
        SafeParcelWriter.writeInt(r3, 4, getHdrType());
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();
        r0.put("width", this.zzb);     // Catch: JSONException -> L18
        r0.put("height", this.zzc);     // Catch: JSONException -> L18
        int r3 = this.zzd;     // Catch: JSONException -> L18
        if (r3 != 1) goto L6;
        String r32 = "sdr";
    L16:
        r0.put("hdrType", r32);     // Catch: JSONException -> L18
        return r0;
    L6:
        if (r3 != 2) goto L8;
        r32 = "hdr10";
        goto L16
    L8:
        if (r3 != 3) goto L10;
        r32 = "dv";
        goto L16
    L10:
        if (r3 == 4) goto L12;
        r32 = null;
        goto L16
    L12:
        r32 = "hdr";
    L18:
        zza.e("Failed to transform VideoInfo into Json", new Object[0]);
        return new JSONObject();
    }
}
