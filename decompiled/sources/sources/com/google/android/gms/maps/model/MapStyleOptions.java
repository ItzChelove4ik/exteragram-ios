package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.IOUtils;
import java.nio.charset.StandardCharsets;

/* loaded from: classes5.dex */
public final class MapStyleOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MapStyleOptions> CREATOR = null;
    private final String zza;

    static {
        CREATOR = new zzo();
    }

    public MapStyleOptions(String r2) {
        Preconditions.checkNotNull(r2, "json must not be null");
        this.zza = r2;
    }

    public static MapStyleOptions loadRawResourceStyle(Context r3, int r4) {
        return new MapStyleOptions(new String(IOUtils.readInputStreamFully(r3.getResources().openRawResource(r4)), StandardCharsets.UTF_8));
    L5:
        e = move-exception;
        throw new Resources.NotFoundException("Failed to read resource " + r4 + ": " + e.toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        String r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeString(r3, 2, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
