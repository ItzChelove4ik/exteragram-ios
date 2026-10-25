package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.DataItemAsset;

@KeepName
/* loaded from: classes5.dex */
public class DataItemAssetParcelable extends AbstractSafeParcelable implements DataItemAsset, ReflectedParcelable {
    public static final Parcelable.Creator<DataItemAssetParcelable> CREATOR = null;
    private final String zza;
    private final String zzb;

    static {
        CREATOR = new zzdf();
    }

    public DataItemAssetParcelable(DataItemAsset r2) {
        this.zza = (String) Preconditions.checkNotNull(r2.getId());
        this.zzb = (String) Preconditions.checkNotNull(r2.getDataItemKey());
    }

    public DataItemAssetParcelable(String r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public final String getDataItemKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.wearable.DataItemAsset
    public final String getId() {
        return this.zza;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("DataItemAssetParcelable[@");
        r0.append(Integer.toHexString(hashCode()));
        if (this.zza != null) goto L5;
        r0.append(",noid");
    L6:
        r0.append(", key=");
        r0.append(this.zzb);
        r0.append("]");
        return r0.toString();
    L5:
        r0.append(",");
        r0.append(this.zza);
        goto L6
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zza, false);
        SafeParcelWriter.writeString(r4, 3, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
