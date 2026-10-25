package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes5.dex */
public final class zzk extends com.google.android.gms.internal.vision.zzb implements zzi {
    public zzk(IBinder r2) {
        super(r2, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    @Override // com.google.android.gms.vision.face.internal.client.zzi
    public final zzh newFaceDetector(IObjectWrapper r2, zzf r3) {
        Parcel r0 = a_();
        com.google.android.gms.internal.vision.zzd.zza(r0, r2);
        com.google.android.gms.internal.vision.zzd.zza(r0, r3);
        Parcel r1 = zza(1, r0);
        IBinder r22 = r1.readStrongBinder();
        if (r22 != null) goto L5;
        zzh r23 = null;
    L9:
        r1.recycle();
        return r23;
    L5:
        IInterface r32 = r22.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
        if ((r32 instanceof zzh) == false) goto L8;
        r23 = (zzh) r32;
        goto L9
    L8:
        r23 = new zzj(r22);
        goto L9
    }
}
