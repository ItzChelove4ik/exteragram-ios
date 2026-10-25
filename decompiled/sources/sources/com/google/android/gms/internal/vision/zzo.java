package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

/* loaded from: classes5.dex */
public final class zzo extends zzb implements zzl {
    public zzo(IBinder r2) {
        super(r2, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    @Override // com.google.android.gms.internal.vision.zzl
    public final void zza() {
        zzb(3, a_());
    }

    @Override // com.google.android.gms.internal.vision.zzl
    public final Barcode[] zza(IObjectWrapper r2, zzs r3) {
        Parcel r0 = a_();
        zzd.zza(r0, r2);
        zzd.zza(r0, r3);
        Parcel r1 = zza(1, r0);
        Barcode[] r22 = (Barcode[]) r1.createTypedArray(Barcode.CREATOR);
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.vision.zzl
    public final Barcode[] zzb(IObjectWrapper r2, zzs r3) {
        Parcel r0 = a_();
        zzd.zza(r0, r2);
        zzd.zza(r0, r3);
        Parcel r1 = zza(2, r0);
        Barcode[] r22 = (Barcode[]) r1.createTypedArray(Barcode.CREATOR);
        r1.recycle();
        return r22;
    }
}
