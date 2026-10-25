package com.google.android.gms.cast.framework.devicesuggestions;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes4.dex */
public class DeviceSuggestionResult extends AbstractSafeParcelable {
    public static final int CONFIDENCE_HIGH = 3;
    public static final int CONFIDENCE_LOW = 1;
    public static final int CONFIDENCE_MID = 2;
    public static final Parcelable.Creator<DeviceSuggestionResult> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final int zzc;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Confidence {
    }

    static {
        CREATOR = new zza();
    }

    public DeviceSuggestionResult(String r1, String r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public int getConfidence() {
        return this.zzc;
    }

    public String getDeviceName() {
        return this.zzb;
    }

    public String getRouteId() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, getRouteId(), false);
        SafeParcelWriter.writeString(r4, 2, getDeviceName(), false);
        SafeParcelWriter.writeInt(r4, 3, getConfidence());
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
