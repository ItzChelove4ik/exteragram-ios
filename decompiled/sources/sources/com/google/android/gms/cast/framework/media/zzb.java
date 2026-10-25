package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzb extends com.google.android.gms.internal.cast.zza implements zzd {
    public zzb(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.media.IImagePicker");
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final WebImage zze(MediaMetadata r1, int r2) {
        throw null;
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final IObjectWrapper zzf() {
        Parcel r2 = zzb(2, zza());
        IObjectWrapper r0 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final WebImage zzg(MediaMetadata r1, ImageHints r2) {
        throw null;
    }
}
