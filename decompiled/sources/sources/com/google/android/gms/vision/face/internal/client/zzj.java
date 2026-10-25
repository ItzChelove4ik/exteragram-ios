package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzs;

/* loaded from: classes5.dex */
public final class zzj extends com.google.android.gms.internal.vision.zzb implements zzh {
    public zzj(IBinder r2) {
        super(r2, "com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    @Override // com.google.android.gms.vision.face.internal.client.zzh
    public final void zza() {
        zzb(3, a_());
    }

    @Override // com.google.android.gms.vision.face.internal.client.zzh
    public final FaceParcel[] zza(IObjectWrapper r2, IObjectWrapper r3, IObjectWrapper r4, int r5, int r6, int r7, int r8, int r9, int r10, zzs r11) {
        Parcel r0 = a_();
        com.google.android.gms.internal.vision.zzd.zza(r0, r2);
        com.google.android.gms.internal.vision.zzd.zza(r0, r3);
        com.google.android.gms.internal.vision.zzd.zza(r0, r4);
        r0.writeInt(r5);
        r0.writeInt(r6);
        r0.writeInt(r7);
        r0.writeInt(r8);
        r0.writeInt(r9);
        r0.writeInt(r10);
        com.google.android.gms.internal.vision.zzd.zza(r0, r11);
        Parcel r1 = zza(4, r0);
        FaceParcel[] r22 = (FaceParcel[]) r1.createTypedArray(FaceParcel.CREATOR);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.vision.face.internal.client.zzh
    public final FaceParcel[] zza(IObjectWrapper r2, zzs r3) {
        Parcel r0 = a_();
        com.google.android.gms.internal.vision.zzd.zza(r0, r2);
        com.google.android.gms.internal.vision.zzd.zza(r0, r3);
        Parcel r1 = zza(1, r0);
        FaceParcel[] r22 = (FaceParcel[]) r1.createTypedArray(FaceParcel.CREATOR);
        r1.recycle();
        return r22;
    }
}
