package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes5.dex */
public final class zzdi extends AbstractSafeParcelable implements DataItem {
    public static final Parcelable.Creator<zzdi> CREATOR = null;
    private final Uri zza;
    private final Map zzb;
    private byte[] zzc;

    static {
        CREATOR = new zzdj();
    }

    public zzdi(Uri r4, Bundle r5, byte[] r6) {
        this.zza = r4;
        HashMap r42 = new HashMap();
        r5.setClassLoader((ClassLoader) Preconditions.checkNotNull(DataItemAssetParcelable.class.getClassLoader()));
        Iterator<String> r0 = r5.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        String r1 = r0.next();
        r42.put(r1, (DataItemAssetParcelable) Preconditions.checkNotNull(r5.getParcelable(r1)));
        goto L4
    L6:
        this.zzb = r42;
        this.zzc = r6;
    }

    public final String toString() {
        boolean r0 = Log.isLoggable("DataItem", 3);
        StringBuilder r1 = new StringBuilder("DataItemParcelable[@");
        r1.append(Integer.toHexString(hashCode()));
        byte[] r2 = this.zzc;
        if (r2 != null) goto L5;
        Object r22 = "null";
    L6:
        r1.append(",dataSz=".concat(r22.toString()));
        r1.append(", numAssets=" + this.zzb.size());
        r1.append(", uri=".concat(String.valueOf(this.zza)));
        if (r0 == true) goto L10;
        r1.append("]");
        return r1.toString();
    L10:
        r1.append("]\n  assets: ");
        Iterator r02 = this.zzb.keySet().iterator();
    L12:
        if (r02.hasNext() == false) goto L14;
        String r23 = (String) r02.next();
        r1.append("\n    " + r23 + ": " + String.valueOf(this.zzb.get(r23)));
        goto L12
    L14:
        r1.append("\n  ]");
        return r1.toString();
    L5:
        r22 = Integer.valueOf(r2.length);
        goto L6
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r7, int r8) {
        int r0 = SafeParcelWriter.beginObjectHeader(r7);
        SafeParcelWriter.writeParcelable(r7, 2, this.zza, r8, false);
        Bundle r82 = new Bundle();
        r82.setClassLoader((ClassLoader) Preconditions.checkNotNull(DataItemAssetParcelable.class.getClassLoader()));
        Iterator r1 = this.zzb.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        Map.Entry r2 = (Map.Entry) r1.next();
        r82.putParcelable((String) r2.getKey(), new DataItemAssetParcelable((DataItemAsset) r2.getValue()));
        goto L4
    L6:
        SafeParcelWriter.writeBundle(r7, 4, r82, false);
        SafeParcelWriter.writeByteArray(r7, 5, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r7, r0);
    }
}
