package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzay extends zzk implements IInterface {
    public zzay(IBinder r2) {
        super(r2, "com.google.android.apps.aicore.aidl.ICortanaService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzav zzd(zzaf r2, zzax r3) {
        Parcel r0 = zza();
        zzm.zzb(r0, r2);
        r0.writeStrongBinder(r3);
        Parcel r1 = zzb(2, r0);
        IBinder r22 = r1.readStrongBinder();
        if (r22 != null) goto L5;
        zzav r23 = null;
    L9:
        r1.recycle();
        return r23;
    L5:
        IInterface r32 = r22.queryLocalInterface("com.google.android.apps.aicore.aidl.ICancellationCallback");
        if ((r32 instanceof zzav) == false) goto L8;
        r23 = (zzav) r32;
        goto L9
    L8:
        r23 = new zzat(r22);
        goto L9
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzav zze(zzbn r3) {
        Parcel r0 = zza();
        int r1 = zzm.$r8$clinit;
        r0.writeStrongBinder(r3);
        Parcel r2 = zzb(3, r0);
        IBinder r32 = r2.readStrongBinder();
        if (r32 != null) goto L5;
        zzav r33 = null;
    L9:
        r2.recycle();
        return r33;
    L5:
        IInterface r02 = r32.queryLocalInterface("com.google.android.apps.aicore.aidl.ICancellationCallback");
        if ((r02 instanceof zzav) == false) goto L8;
        r33 = (zzav) r02;
        goto L9
    L8:
        r33 = new zzat(r32);
        goto L9
    }
}
