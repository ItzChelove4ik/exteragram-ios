package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public final class LocationSettingsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LocationSettingsRequest> CREATOR = null;
    private final List zza;
    private final boolean zzb;
    private final boolean zzc;

    public static final class Builder {
        private final ArrayList zza;
        private boolean zzb;
        private boolean zzc;

        public Builder() {
            this.zza = new ArrayList();
            this.zzb = false;
            this.zzc = false;
        }

        public Builder addLocationRequest(LocationRequest r2) {
            if (r2 == null) goto L4;
            this.zza.add(r2);
        L4:
            return this;
        }

        public LocationSettingsRequest build() {
            return new LocationSettingsRequest(this.zza, this.zzb, this.zzc);
        }
    }

    static {
        CREATOR = new zzag();
    }

    public LocationSettingsRequest(List r1, boolean r2, boolean r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        List r52 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeTypedList(r4, 1, Collections.unmodifiableList(r52), false);
        SafeParcelWriter.writeBoolean(r4, 2, this.zzb);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
