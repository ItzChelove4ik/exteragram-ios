package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MediaQueueContainerMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaQueueContainerMetadata> CREATOR = null;
    public static final int MEDIA_QUEUE_CONTAINER_TYPE_AUDIO_BOOK = 1;
    public static final int MEDIA_QUEUE_CONTAINER_TYPE_GENERIC = 0;
    private int zza;
    private String zzb;
    private List zzc;
    private List zzd;
    private double zze;

    public static class Builder {
        private final MediaQueueContainerMetadata zza;

        public Builder() {
            this.zza = new MediaQueueContainerMetadata(null);
        }

        public MediaQueueContainerMetadata build() {
            return new MediaQueueContainerMetadata(this.zza, null);
        }

        public Builder setContainerDuration(double r2) {
            this.zza.zzf(r2);
            return this;
        }

        public Builder setContainerImages(List<WebImage> r2) {
            this.zza.zze(r2);
            return this;
        }

        public Builder setContainerType(int r2) {
            this.zza.zzc(r2);
            return this;
        }

        public Builder setSections(List<MediaMetadata> r2) {
            this.zza.zza(r2);
            return this;
        }

        public Builder setTitle(String r2) {
            this.zza.zzd(r2);
            return this;
        }

        public final Builder zza(JSONObject r2) {
            this.zza.zzg(r2);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQueueContainerType {
    }

    static {
        CREATOR = new zzbw();
    }

    private MediaQueueContainerMetadata() {
        throw null;
    }

    public MediaQueueContainerMetadata(int r1, String r2, List r3, List r4, double r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    public /* synthetic */ MediaQueueContainerMetadata(MediaQueueContainerMetadata r1, byte[] r2) {
        this.zza = r1.zza;
        this.zzb = r1.zzb;
        this.zzc = r1.zzc;
        this.zzd = r1.zzd;
        this.zze = r1.zze;
    }

    public /* synthetic */ MediaQueueContainerMetadata(byte[] r1) {
        zzh();
    }

    private final void zzh() {
        this.zza = 0;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = 0.0d;
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof MediaQueueContainerMetadata) == true) goto L8;
        return false;
    L8:
        MediaQueueContainerMetadata r62 = (MediaQueueContainerMetadata) r6;
        if (this.zza == r62.zza) goto L11;
    L19:
        return false;
    L11:
        if (TextUtils.equals(this.zzb, r62.zzb) == false) goto L19;
        if (Objects.equal(this.zzc, r62.zzc) == false) goto L19;
        if (Objects.equal(this.zzd, r62.zzd) == false) goto L19;
        if (this.zze != r62.zze) goto L19;
        return true;
    }

    public double getContainerDuration() {
        return this.zze;
    }

    public List<WebImage> getContainerImages() {
        List r0 = this.zzd;
        if (r0 != null) goto L7;
        return null;
    L7:
        return Collections.unmodifiableList(r0);
    }

    public int getContainerType() {
        return this.zza;
    }

    public List<MediaMetadata> getSections() {
        List r0 = this.zzc;
        if (r0 != null) goto L7;
        return null;
    L7:
        return Collections.unmodifiableList(r0);
    }

    public String getTitle() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zza), this.zzb, this.zzc, this.zzd, Double.valueOf(this.zze)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, getContainerType());
        SafeParcelWriter.writeString(r4, 3, getTitle(), false);
        SafeParcelWriter.writeTypedList(r4, 4, getSections(), false);
        SafeParcelWriter.writeTypedList(r4, 5, getContainerImages(), false);
        SafeParcelWriter.writeDouble(r4, 6, getContainerDuration());
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final void zza(List r2) {
        if (r2 != null) goto L4;
        ArrayList r22 = null;
    L5:
        this.zzc = r22;
        return;
    L4:
        r22 = new ArrayList(r2);
        goto L5
    }

    public final JSONObject zzb() {
        JSONObject r0 = new JSONObject();
        int r1 = this.zza;     // Catch: JSONException -> L30
        if (r1 != 0) goto L7;
        r0.put("containerType", "GENERIC_CONTAINER");     // Catch: JSONException -> L30
    L12:
        if (TextUtils.isEmpty(this.zzb) == true) goto L14;
        r0.put("title", this.zzb);     // Catch: JSONException -> L30
    L14:
        List r12 = this.zzc;     // Catch: JSONException -> L30
        if (r12 != null) goto L17;
    L23:
        List r13 = this.zzd;     // Catch: JSONException -> L30
        if (r13 != null) goto L26;
    L28:
        r0.put("containerDuration", this.zze);     // Catch: JSONException -> L30
        goto L29
    L26:
        if (r13.isEmpty() == true) goto L28;
        r0.put("containerImages", com.google.android.gms.cast.internal.media.zza.zzb(this.zzd));     // Catch: JSONException -> L30
        goto L28
    L17:
        if (r12.isEmpty() == true) goto L23;
        JSONArray r14 = new JSONArray();     // Catch: JSONException -> L30
        Iterator r2 = this.zzc.iterator();     // Catch: JSONException -> L30
    L20:
        if (r2.hasNext() == false) goto L22;
        r14.put(((MediaMetadata) r2.next()).zza());     // Catch: JSONException -> L30
        goto L20
    L22:
        r0.put("sections", r14);     // Catch: JSONException -> L30
        goto L23
    L7:
        if (r1 != 1) goto L12;
        r0.put("containerType", "AUDIOBOOK_CONTAINER");     // Catch: JSONException -> L30
    L29:
        return r0;
    }

    public final /* synthetic */ void zzc(int r1) {
        this.zza = r1;
    }

    public final /* synthetic */ void zzd(String r1) {
        this.zzb = r1;
    }

    public final /* synthetic */ void zze(List r2) {
        if (r2 != null) goto L4;
        ArrayList r22 = null;
    L5:
        this.zzd = r22;
        return;
    L4:
        r22 = new ArrayList(r2);
        goto L5
    }

    public final /* synthetic */ void zzf(double r1) {
        this.zze = r1;
    }

    public final /* synthetic */ void zzg(JSONObject r6) {
        zzh();
        String r0 = r6.optString("containerType", _UrlKt.FRAGMENT_ENCODE_SET);
        int r1 = r0.hashCode();
        int r3 = 0;
        if (r1 == 6924225) goto L11;
        if (r1 == 828666841) goto L8;
    L13:
        this.zzb = CastUtils.optStringOrNull(r6, "title");
        JSONArray r02 = r6.optJSONArray("sections");
        if (r02 == null) goto L22;
        ArrayList r12 = new ArrayList();
        this.zzc = r12;
    L17:
        if (r3 >= r02.length()) goto L22;
        JSONObject r2 = r02.optJSONObject(r3);
        if (r2 == null) goto L21;
        MediaMetadata r4 = new MediaMetadata();
        r4.zzb(r2);
        r12.add(r4);
    L21:
        r3 = r3 + 1;
    L22:
        JSONArray r03 = r6.optJSONArray("containerImages");
        if (r03 == null) goto L25;
        ArrayList r13 = new ArrayList();
        this.zzd = r13;
        com.google.android.gms.cast.internal.media.zza.zza(r13, r03);
    L25:
        this.zze = r6.optDouble("containerDuration", this.zze);
        return;
    L8:
        if (r0.equals("GENERIC_CONTAINER") == false) goto L13;
        this.zza = 0;
        goto L13
    L11:
        if (r0.equals("AUDIOBOOK_CONTAINER") == false) goto L13;
        this.zza = 1;
        goto L13
    }
}
