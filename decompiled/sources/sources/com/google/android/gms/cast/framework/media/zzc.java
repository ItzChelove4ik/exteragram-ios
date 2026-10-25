package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public abstract class zzc extends com.google.android.gms.internal.cast.zzb implements zzd {
    public zzc() {
        super("com.google.android.gms.cast.framework.media.IImagePicker");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r2, Parcel r3, Parcel r4, int r5) {
        if (r2 != 1) goto L5;
        MediaMetadata r22 = (MediaMetadata) com.google.android.gms.internal.cast.zzc.zzb(r3, MediaMetadata.CREATOR);
        int r0 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        WebImage r1 = zze(r22, r0);
        r4.writeNoException();
        com.google.android.gms.internal.cast.zzc.zzd(r4, r1);
    L16:
        return true;
    L5:
        if (r2 != 2) goto L7;
        IObjectWrapper r12 = zzf();
        r4.writeNoException();
        com.google.android.gms.internal.cast.zzc.zze(r4, r12);
        goto L16
    L7:
        if (r2 != 3) goto L9;
        r4.writeNoException();
        r4.writeInt(12451000);
        goto L16
    L9:
        if (r2 == 4) goto L12;
        return false;
    L12:
        MediaMetadata r23 = (MediaMetadata) com.google.android.gms.internal.cast.zzc.zzb(r3, MediaMetadata.CREATOR);
        ImageHints r02 = (ImageHints) com.google.android.gms.internal.cast.zzc.zzb(r3, ImageHints.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        WebImage r13 = zzg(r23, r02);
        r4.writeNoException();
        com.google.android.gms.internal.cast.zzc.zzd(r4, r13);
        goto L16
    }
}
