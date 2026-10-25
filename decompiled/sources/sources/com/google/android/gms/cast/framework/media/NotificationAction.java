package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class NotificationAction extends AbstractSafeParcelable {
    public static final Parcelable.Creator<NotificationAction> CREATOR = null;
    private final String zza;
    private final int zzb;
    private final String zzc;

    public static final class Builder {
        String zza;
        int zzb;
        String zzc;

        public Builder() {
        }

        public NotificationAction build() {
            return new NotificationAction(this.zza, this.zzb, this.zzc);
        }

        public Builder setAction(String r2) {
            if (TextUtils.isEmpty(r2) == true) goto L6;
            this.zza = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("action cannot be null or an empty string.");
            return null;
        }

        public Builder setContentDescription(String r2) {
            if (TextUtils.isEmpty(r2) == true) goto L6;
            this.zzc = r2;
            return this;
        L6:
            f$$ExternalSyntheticBUOutline1.m("contentDescription cannot be null  or an empty string.");
            return null;
        }

        public Builder setIconResId(int r1) {
            this.zzb = r1;
            return this;
        }
    }

    static {
        CREATOR = new zzq();
    }

    public NotificationAction(String r1, int r2, String r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public String getAction() {
        return this.zza;
    }

    public String getContentDescription() {
        return this.zzc;
    }

    public int getIconResId() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, getAction(), false);
        SafeParcelWriter.writeInt(r4, 3, getIconResId());
        SafeParcelWriter.writeString(r4, 4, getContentDescription(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
