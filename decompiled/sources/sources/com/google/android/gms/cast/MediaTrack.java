package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzhv;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class MediaTrack extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaTrack> CREATOR = null;
    public static final String ROLE_ALTERNATE = "alternate";
    public static final String ROLE_CAPTION = "caption";
    public static final String ROLE_COMMENTARY = "commentary";
    public static final String ROLE_DESCRIPTION = "description";
    public static final String ROLE_DUB = "dub";
    public static final String ROLE_EMERGENCY = "emergency";
    public static final String ROLE_FORCED_SUBTITLE = "forced_subtitle";
    public static final String ROLE_MAIN = "main";
    public static final String ROLE_SIGN = "sign";
    public static final String ROLE_SUBTITLE = "subtitle";
    public static final String ROLE_SUPPLEMENTARY = "supplementary";
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    String zza;
    private final long zzb;
    private final int zzc;
    private String zzd;
    private String zze;
    private final String zzf;
    private final String zzg;
    private final int zzh;
    private final List zzi;
    private final JSONObject zzj;

    public static class Builder {
        private final long zza;
        private final int zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private int zzg;
        private List zzh;
        private JSONObject zzi;

        public Builder(long r2, int r4) {
            this.zzg = 0;
            this.zza = r2;
            this.zzb = r4;
        }

        public MediaTrack build() {
            return new MediaTrack(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi);
        }

        public Builder setContentId(String r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setContentType(String r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setCustomData(JSONObject r1) {
            this.zzi = r1;
            return this;
        }

        public Builder setLanguage(String r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setLanguage(Locale r1) {
            this.zzf = CastUtils.zzd(r1);
            return this;
        }

        public Builder setName(String r1) {
            this.zze = r1;
            return this;
        }

        public Builder setRoles(List<String> r1) {
            if (r1 == null) goto L4;
            r1 = zzhv.zzj(r1);
        L4:
            this.zzh = r1;
            return this;
        }

        public Builder setSubtype(int r3) {
            if (r3 >= (-1)) goto L5;
        L15:
            zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r3).length() + 16, "invalid subtype ", r3);
            return null;
        L5:
            if (r3 > 5) goto L15;
            if (r3 != 0) goto L8;
        L13:
            this.zzg = r3;
            return this;
        L8:
            if (this.zzb == 1) goto L13;
            f$$ExternalSyntheticBUOutline1.m("subtypes are only valid for text tracks");
            return null;
        }
    }

    static {
        CREATOR = new zzca();
    }

    public MediaTrack(long r1, int r3, String r4, String r5, String r6, String r7, int r8, List r9, JSONObject r10) {
        this.zzb = r1;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof MediaTrack) == true) goto L8;
        return false;
    L8:
        MediaTrack r82 = (MediaTrack) r8;
        JSONObject r1 = this.zzj;
        if (r1 == null) goto L11;
        boolean r3 = false;
    L12:
        JSONObject r4 = r82.zzj;
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
        if (this.zzb == r82.zzb) goto L26;
    L40:
        return false;
    L26:
        if (this.zzc != r82.zzc) goto L40;
        if (CastUtils.zza(this.zzd, r82.zzd) == false) goto L40;
        if (CastUtils.zza(this.zze, r82.zze) == false) goto L40;
        if (CastUtils.zza(this.zzf, r82.zzf) == false) goto L40;
        if (CastUtils.zza(this.zzg, r82.zzg) == false) goto L40;
        if (this.zzh != r82.zzh) goto L40;
        if (CastUtils.zza(this.zzi, r82.zzi) == false) goto L40;
        return true;
    L15:
        r5 = true;
        goto L16
    L11:
        r3 = true;
        goto L12
    }

    public String getContentId() {
        return this.zzd;
    }

    public String getContentType() {
        return this.zze;
    }

    public JSONObject getCustomData() {
        return this.zzj;
    }

    public long getId() {
        return this.zzb;
    }

    public String getLanguage() {
        return this.zzg;
    }

    public Locale getLanguageLocale() {
        String r1 = this.zzg;
        if (TextUtils.isEmpty(r1) == false) goto L7;
        return null;
    L7:
        return Locale.forLanguageTag(r1);
    }

    public String getName() {
        return this.zzf;
    }

    public List<String> getRoles() {
        return this.zzi;
    }

    public int getSubtype() {
        return this.zzh;
    }

    public int getType() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd, this.zze, this.zzf, this.zzg, Integer.valueOf(this.zzh), this.zzi, String.valueOf(this.zzj)});
    }

    public void setContentId(String r1) {
        this.zzd = r1;
    }

    public void setContentType(String r1) {
        this.zze = r1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        JSONObject r52 = this.zzj;
        if (r52 != null) goto L5;
        String r53 = null;
    L6:
        this.zza = r53;
        int r54 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeLong(r4, 2, getId());
        SafeParcelWriter.writeInt(r4, 3, getType());
        SafeParcelWriter.writeString(r4, 4, getContentId(), false);
        SafeParcelWriter.writeString(r4, 5, getContentType(), false);
        SafeParcelWriter.writeString(r4, 6, getName(), false);
        SafeParcelWriter.writeString(r4, 7, getLanguage(), false);
        SafeParcelWriter.writeInt(r4, 8, getSubtype());
        SafeParcelWriter.writeStringList(r4, 9, getRoles(), false);
        SafeParcelWriter.writeString(r4, 10, this.zza, false);
        SafeParcelWriter.finishObjectHeader(r4, r54);
        return;
    L5:
        r53 = r52.toString();
        goto L6
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();
        r0.put("trackId", this.zzb);     // Catch: JSONException -> L46
        int r1 = this.zzc;     // Catch: JSONException -> L46
        if (r1 == 1) goto L11;
        if (r1 == 2) goto L10;
        if (r1 != 3) goto L12;
        r0.put("type", "VIDEO");     // Catch: JSONException -> L46
    L12:
        String r12 = this.zzd;     // Catch: JSONException -> L46
        if (r12 == null) goto L15;
        r0.put("trackContentId", r12);     // Catch: JSONException -> L46
    L15:
        String r13 = this.zze;     // Catch: JSONException -> L46
        if (r13 == null) goto L18;
        r0.put("trackContentType", r13);     // Catch: JSONException -> L46
    L18:
        String r14 = this.zzf;     // Catch: JSONException -> L46
        if (r14 == null) goto L21;
        r0.put("name", r14);     // Catch: JSONException -> L46
    L21:
        String r15 = this.zzg;     // Catch: JSONException -> L46
        if (TextUtils.isEmpty(r15) == true) goto L24;
        r0.put("language", r15);     // Catch: JSONException -> L46
    L24:
        int r16 = this.zzh;     // Catch: JSONException -> L46
        if (r16 == 1) goto L38;
        if (r16 == 2) goto L37;
        if (r16 != 3) goto L30;
        r0.put("subtype", "DESCRIPTIONS");     // Catch: JSONException -> L46
    L39:
        List r17 = this.zzi;     // Catch: JSONException -> L46
        if (r17 == null) goto L42;
        r0.put("roles", new JSONArray(r17));     // Catch: JSONException -> L46
    L42:
        JSONObject r6 = this.zzj;     // Catch: JSONException -> L46
        if (r6 == null) goto L45;
        r0.put("customData", r6);     // Catch: JSONException -> L46
        goto L45
    L30:
        if (r16 != 4) goto L32;
        r0.put("subtype", "CHAPTERS");     // Catch: JSONException -> L46
        goto L39
    L32:
        if (r16 != 5) goto L39;
        r0.put("subtype", "METADATA");     // Catch: JSONException -> L46
        goto L39
    L37:
        r0.put("subtype", "CAPTIONS");     // Catch: JSONException -> L46
        goto L39
    L38:
        r0.put("subtype", "SUBTITLES");     // Catch: JSONException -> L46
        goto L39
    L10:
        r0.put("type", "AUDIO");     // Catch: JSONException -> L46
        goto L12
    L11:
        r0.put("type", "TEXT");     // Catch: JSONException -> L46
    L45:
        return r0;
    }
}
