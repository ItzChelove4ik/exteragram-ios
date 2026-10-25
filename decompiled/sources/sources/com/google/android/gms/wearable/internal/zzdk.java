package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzdk extends DataBufferRef implements DataItem {
    private final int zza;

    public zzdk(DataHolder r1, int r2, int r3) {
        super(r1, r2);
        this.zza = r3;
    }

    public final Map<String, DataItemAsset> getAssets() {
        HashMap r0 = new HashMap(this.zza);
        int r1 = 0;
    L4:
        if (r1 >= this.zza) goto L9;
        zzdg r2 = new zzdg(this.mDataHolder, this.mDataRow + r1);
        if (r2.getString("asset_key") == null) goto L8;
        r0.put(r2.getString("asset_key"), r2);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        return r0;
    }

    public final Uri getUri() {
        return Uri.parse(getString("path"));
    }

    public final String toString() {
        boolean r0 = Log.isLoggable("DataItem", 3);
        byte[] r1 = getByteArray("data");
        Map<String, DataItemAsset> r2 = getAssets();
        StringBuilder r3 = new StringBuilder("DataItemRef{ ");
        r3.append("uri=".concat(String.valueOf(getUri())));
        if (r1 != null) goto L5;
        Object r5 = "null";
    L6:
        r3.append(", dataSz=".concat(r5.toString()));
        r3.append(", numAssets=" + r2.size());
        if (r0 == true) goto L9;
    L15:
        r3.append(" }");
        return r3.toString();
    L9:
        if (r2.isEmpty() == true) goto L15;
        r3.append(", assets=[");
        Iterator<Map.Entry<String, DataItemAsset>> r52 = r2.entrySet().iterator();
        String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r52.hasNext() == false) goto L14;
        Map.Entry<String, DataItemAsset> r12 = r52.next();
        r3.append(r02 + r12.getKey() + ": " + r12.getValue().getId());
        r02 = ", ";
        goto L12
    L14:
        r3.append("]");
        goto L15
    L5:
        r5 = Integer.valueOf(r1.length);
        goto L6
    }
}
