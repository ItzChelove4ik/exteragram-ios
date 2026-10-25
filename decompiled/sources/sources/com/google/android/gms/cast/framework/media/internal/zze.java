package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zze extends com.google.android.gms.internal.cast.zza implements zzg {
    public zze(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zzg
    public final Bitmap zze(Uri r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zzc(r0, r2);
        Parcel r1 = zzb(1, r0);
        Bitmap r22 = (Bitmap) com.google.android.gms.internal.cast.zzc.zzb(r1, Bitmap.CREATOR);
        r1.recycle();
        return r22;
    }
}
