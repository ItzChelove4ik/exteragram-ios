package com.google.android.gms.identitycredentials.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.identitycredentials.CreateCredentialRequest;
import com.google.android.gms.identitycredentials.GetCredentialRequest;
import com.google.android.gms.identitycredentials.SignalCredentialStateRequest;
import com.google.android.gms.internal.identity_credentials.zza;
import com.google.android.gms.internal.identity_credentials.zzb;
import com.google.android.gms.internal.identity_credentials.zzc;

/* loaded from: classes4.dex */
public interface IIdentityCredentialService extends IInterface {

    public static abstract class Stub extends zzb implements IIdentityCredentialService {

        public static class Proxy extends zza implements IIdentityCredentialService {
            public Proxy(IBinder r2) {
                super(r2, "com.google.android.gms.identitycredentials.internal.IIdentityCredentialService");
            }

            @Override // com.google.android.gms.identitycredentials.internal.IIdentityCredentialService
            public void createCredential(IIdentityCredentialCallbacks r2, CreateCredentialRequest r3, ApiMetadata r4) {
                Parcel r0 = obtainAndWriteInterfaceToken();
                zzc.zzc(r0, r2);
                zzc.zzb(r0, r3);
                zzc.zzb(r0, r4);
                transactAndReadExceptionReturnVoid(6, r0);
            }

            @Override // com.google.android.gms.identitycredentials.internal.IIdentityCredentialService
            public void getCredential(IIdentityCredentialCallbacks r2, GetCredentialRequest r3, ApiMetadata r4) {
                Parcel r0 = obtainAndWriteInterfaceToken();
                zzc.zzc(r0, r2);
                zzc.zzb(r0, r3);
                zzc.zzb(r0, r4);
                transactAndReadExceptionReturnVoid(1, r0);
            }

            @Override // com.google.android.gms.identitycredentials.internal.IIdentityCredentialService
            public void signalCredentialState(IIdentityCredentialCallbacks r2, SignalCredentialStateRequest r3, ApiMetadata r4) {
                Parcel r0 = obtainAndWriteInterfaceToken();
                zzc.zzc(r0, r2);
                zzc.zzb(r0, r3);
                zzc.zzb(r0, r4);
                transactAndReadExceptionReturnVoid(10, r0);
            }
        }

        public static IIdentityCredentialService asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("com.google.android.gms.identitycredentials.internal.IIdentityCredentialService");
            if ((r0 instanceof IIdentityCredentialService) == false) goto L10;
            return (IIdentityCredentialService) r0;
        L10:
            return new Proxy(r2);
        }
    }

    void createCredential(IIdentityCredentialCallbacks r1, CreateCredentialRequest r2, ApiMetadata r3);

    void getCredential(IIdentityCredentialCallbacks r1, GetCredentialRequest r2, ApiMetadata r3);

    void signalCredentialState(IIdentityCredentialCallbacks r1, SignalCredentialStateRequest r2, ApiMetadata r3);
}
