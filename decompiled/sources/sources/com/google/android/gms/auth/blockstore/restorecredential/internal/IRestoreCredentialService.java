package com.google.android.gms.auth.blockstore.restorecredential.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialRequest;
import com.google.android.gms.internal.auth_blockstore.zza;
import com.google.android.gms.internal.auth_blockstore.zzb;
import com.google.android.gms.internal.auth_blockstore.zzc;

/* loaded from: classes4.dex */
public interface IRestoreCredentialService extends IInterface {

    public static abstract class Stub extends zzb implements IRestoreCredentialService {

        public static class Proxy extends zza implements IRestoreCredentialService {
            public Proxy(IBinder r2) {
                super(r2, "com.google.android.gms.auth.blockstore.restorecredential.internal.IRestoreCredentialService");
            }

            @Override // com.google.android.gms.auth.blockstore.restorecredential.internal.IRestoreCredentialService
            public void getRestoreCredential(GetRestoreCredentialRequest r2, IGetRestoreCredentialCallback r3) {
                Parcel r0 = obtainAndWriteInterfaceToken();
                zzc.zzb(r0, r2);
                zzc.zzc(r0, r3);
                transactAndReadExceptionReturnVoid(2, r0);
            }
        }

        public static IRestoreCredentialService asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("com.google.android.gms.auth.blockstore.restorecredential.internal.IRestoreCredentialService");
            if ((r0 instanceof IRestoreCredentialService) == false) goto L10;
            return (IRestoreCredentialService) r0;
        L10:
            return new Proxy(r2);
        }
    }

    void getRestoreCredential(GetRestoreCredentialRequest r1, IGetRestoreCredentialCallback r2);
}
