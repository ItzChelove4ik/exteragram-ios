package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class zzm extends zzt<zzl> {
    private final zzk zza;

    public zzm(Context r3, zzk r4) {
        super(r3, "BarcodeNativeHandle", "barcode");
        this.zza = r4;
        zzd();
    }

    @Override // com.google.android.gms.internal.vision.zzt
    public final /* synthetic */ zzl zza(DynamiteModule r4, Context r5) {
        IBinder r42 = r4.instantiate("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        if (r42 != null) goto L5;
        zzn r1 = null;
    L9:
        if (r1 != null) goto L12;
        return null;
    L12:
        return r1.zza(ObjectWrapper.wrap(r5), (zzk) Preconditions.checkNotNull(this.zza));
    L5:
        IInterface r12 = r42.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
        if ((r12 instanceof zzn) == false) goto L8;
        r1 = (zzn) r12;
        goto L9
    L8:
        r1 = new zzp(r42);
        goto L9
    }

    @Override // com.google.android.gms.internal.vision.zzt
    public final void zza() {
        if (zzb() == false) goto L6;
        ((zzl) Preconditions.checkNotNull(zzd())).zza();
        return;
    }

    public final Barcode[] zza(Bitmap r3, zzs r4) {
        if (zzb() == false) goto L5;
        IObjectWrapper r32 = ObjectWrapper.wrap(r3);     // Catch: RemoteException -> L8
        return ((zzl) Preconditions.checkNotNull(zzd())).zzb(r32, r4);
    L8:
        e = move-exception;
        Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
        return new Barcode[0];
    L5:
        return new Barcode[0];
    }

    public final Barcode[] zza(ByteBuffer r3, zzs r4) {
        if (zzb() == false) goto L5;
        IObjectWrapper r32 = ObjectWrapper.wrap(r3);     // Catch: RemoteException -> L8
        return ((zzl) Preconditions.checkNotNull(zzd())).zza(r32, r4);
    L8:
        e = move-exception;
        Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
        return new Barcode[0];
    L5:
        return new Barcode[0];
    }
}
