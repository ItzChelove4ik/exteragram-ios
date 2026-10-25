package com.google.android.gms.auth.blockstore.restorecredential.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth_blockstore.zzb;
import com.google.android.gms.internal.auth_blockstore.zzc;

/* loaded from: classes4.dex */
public interface IGetRestoreCredentialCallback extends IInterface {

    public static abstract class Stub extends zzb implements IGetRestoreCredentialCallback {
        public Stub() {
            super("com.google.android.gms.auth.blockstore.restorecredential.internal.IGetRestoreCredentialCallback");
        }

        @Override // com.google.android.gms.internal.auth_blockstore.zzb
        public boolean dispatchTransaction(int r1, Parcel r2, Parcel r3, int r4) {
            if (r1 != 1) goto L6;
            Status r12 = (Status) zzc.zza(r2, Status.CREATOR);
            GetRestoreCredentialResponse r42 = (GetRestoreCredentialResponse) zzc.zza(r2, GetRestoreCredentialResponse.CREATOR);
            enforceNoDataAvail(r2);
            onGetRestoreCredential(r12, r42);
            return true;
        L6:
            return false;
        }
    }

    void onGetRestoreCredential(Status r1, GetRestoreCredentialResponse r2);
}
