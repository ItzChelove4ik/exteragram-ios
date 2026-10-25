package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes5.dex */
public final class zzp extends zzb implements zzn {
    public zzp(IBinder r2) {
        super(r2, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
    }

    @Override // com.google.android.gms.internal.vision.zzn
    public final zzl zza(IObjectWrapper r2, zzk r3) {
        Parcel r0 = a_();
        zzd.zza(r0, r2);
        zzd.zza(r0, r3);
        Parcel r1 = zza(1, r0);
        IBinder r22 = r1.readStrongBinder();
        if (r22 != null) goto L5;
        zzl r23 = null;
    L9:
        r1.recycle();
        return r23;
    L5:
        IInterface r32 = r22.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
        if ((r32 instanceof zzl) == false) goto L8;
        r23 = (zzl) r32;
        goto L9
    L8:
        r23 = new zzo(r22);
        goto L9
    }
}
