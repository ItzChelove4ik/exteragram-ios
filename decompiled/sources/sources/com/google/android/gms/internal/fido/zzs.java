package com.google.android.gms.internal.fido;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;

/* loaded from: classes4.dex */
public final class zzs extends zza implements IInterface {
    public zzs(IBinder r2) {
        super(r2, "com.google.android.gms.fido.fido2.internal.regular.IFido2AppService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzc(zzr r3, PublicKeyCredentialCreationOptions r4) {
        Parcel r0 = zza();
        int r1 = zzc.$r8$clinit;
        r0.writeStrongBinder(r3);
        zzc.zzd(r0, r4);
        zzb(1, r0);
    }
}
