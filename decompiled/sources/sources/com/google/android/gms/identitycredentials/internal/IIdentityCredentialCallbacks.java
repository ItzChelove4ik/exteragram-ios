package com.google.android.gms.identitycredentials.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.identitycredentials.ClearCreationOptionsResponse;
import com.google.android.gms.identitycredentials.ClearCredentialStateResponse;
import com.google.android.gms.identitycredentials.ClearExportResponse;
import com.google.android.gms.identitycredentials.ClearRegistryResponse;
import com.google.android.gms.identitycredentials.CreateCredentialHandle;
import com.google.android.gms.identitycredentials.CreateCredentialResponse;
import com.google.android.gms.identitycredentials.CredentialTransferCapabilities;
import com.google.android.gms.identitycredentials.ExportCredentialsToDeviceSetupResponse;
import com.google.android.gms.identitycredentials.ImportCredentialsForDeviceSetupResponse;
import com.google.android.gms.identitycredentials.PendingGetCredentialHandle;
import com.google.android.gms.identitycredentials.PendingImportCredentialsHandle;
import com.google.android.gms.identitycredentials.RegisterCreationOptionsResponse;
import com.google.android.gms.identitycredentials.RegisterExportResponse;
import com.google.android.gms.identitycredentials.RegistrationResponse;
import com.google.android.gms.identitycredentials.SignalCredentialStateResponse;
import com.google.android.gms.internal.identity_credentials.zzb;
import com.google.android.gms.internal.identity_credentials.zzc;

/* loaded from: classes4.dex */
public interface IIdentityCredentialCallbacks extends IInterface {

    public static abstract class Stub extends zzb implements IIdentityCredentialCallbacks {
        public Stub() {
            super("com.google.android.gms.identitycredentials.internal.IIdentityCredentialCallbacks");
        }

        @Override // com.google.android.gms.internal.identity_credentials.zzb
        public boolean dispatchTransaction(int r1, Parcel r2, Parcel r3, int r4) {
            switch(r1) {
                case 1: goto L19;
                case 2: goto L18;
                case 3: goto L17;
                case 4: goto L16;
                case 5: goto L15;
                case 6: goto L14;
                case 7: goto L13;
                case 8: goto L12;
                case 9: goto L11;
                case 10: goto L10;
                case 11: goto L9;
                case 12: goto L8;
                case 13: goto L7;
                case 14: goto L6;
                case 15: goto L5;
                default: goto L3;
            };
        L3:
            return false;
        L5:
            Status r12 = (Status) zzc.zza(r2, Status.CREATOR);
            ClearCreationOptionsResponse r32 = (ClearCreationOptionsResponse) zzc.zza(r2, ClearCreationOptionsResponse.CREATOR);
            enforceNoDataAvail(r2);
            onClearCreationOptions(r12, r32);
            return true;
        L6:
            Status r13 = (Status) zzc.zza(r2, Status.CREATOR);
            CredentialTransferCapabilities r33 = (CredentialTransferCapabilities) zzc.zza(r2, CredentialTransferCapabilities.CREATOR);
            enforceNoDataAvail(r2);
            onGetCredentialTransferCapabilities(r13, r33);
            return true;
        L7:
            Status r14 = (Status) zzc.zza(r2, Status.CREATOR);
            ExportCredentialsToDeviceSetupResponse r34 = (ExportCredentialsToDeviceSetupResponse) zzc.zza(r2, ExportCredentialsToDeviceSetupResponse.CREATOR);
            enforceNoDataAvail(r2);
            onExportCredentialsToDeviceSetup(r14, r34);
            return true;
        L8:
            Status r15 = (Status) zzc.zza(r2, Status.CREATOR);
            ImportCredentialsForDeviceSetupResponse r35 = (ImportCredentialsForDeviceSetupResponse) zzc.zza(r2, ImportCredentialsForDeviceSetupResponse.CREATOR);
            enforceNoDataAvail(r2);
            onImportCredentialsForDeviceSetup(r15, r35);
            return true;
        L9:
            Status r16 = (Status) zzc.zza(r2, Status.CREATOR);
            ClearExportResponse r36 = (ClearExportResponse) zzc.zza(r2, ClearExportResponse.CREATOR);
            enforceNoDataAvail(r2);
            onClearExport(r16, r36);
            return true;
        L10:
            Status r17 = (Status) zzc.zza(r2, Status.CREATOR);
            SignalCredentialStateResponse r37 = (SignalCredentialStateResponse) zzc.zza(r2, SignalCredentialStateResponse.CREATOR);
            enforceNoDataAvail(r2);
            onSignalCredentialState(r17, r37);
            return true;
        L11:
            Status r18 = (Status) zzc.zza(r2, Status.CREATOR);
            ClearCredentialStateResponse r38 = (ClearCredentialStateResponse) zzc.zza(r2, ClearCredentialStateResponse.CREATOR);
            enforceNoDataAvail(r2);
            onClearCredentialState(r18, r38);
            return true;
        L12:
            Status r19 = (Status) zzc.zza(r2, Status.CREATOR);
            RegisterCreationOptionsResponse r39 = (RegisterCreationOptionsResponse) zzc.zza(r2, RegisterCreationOptionsResponse.CREATOR);
            enforceNoDataAvail(r2);
            onRegisterCreationOptions(r19, r39);
            return true;
        L13:
            Status r110 = (Status) zzc.zza(r2, Status.CREATOR);
            CreateCredentialHandle r310 = (CreateCredentialHandle) zzc.zza(r2, CreateCredentialHandle.CREATOR);
            enforceNoDataAvail(r2);
            onCreateCredentialV2(r110, r310);
            return true;
        L14:
            Status r111 = (Status) zzc.zza(r2, Status.CREATOR);
            CreateCredentialResponse r311 = (CreateCredentialResponse) zzc.zza(r2, CreateCredentialResponse.CREATOR);
            enforceNoDataAvail(r2);
            onCreateCredential(r111, r311);
            return true;
        L15:
            Status r112 = (Status) zzc.zza(r2, Status.CREATOR);
            RegisterExportResponse r312 = (RegisterExportResponse) zzc.zza(r2, RegisterExportResponse.CREATOR);
            enforceNoDataAvail(r2);
            onRegisterExport(r112, r312);
            return true;
        L16:
            Status r113 = (Status) zzc.zza(r2, Status.CREATOR);
            PendingImportCredentialsHandle r313 = (PendingImportCredentialsHandle) zzc.zza(r2, PendingImportCredentialsHandle.CREATOR);
            enforceNoDataAvail(r2);
            onImportCredentials(r113, r313);
            return true;
        L17:
            Status r114 = (Status) zzc.zza(r2, Status.CREATOR);
            ClearRegistryResponse r314 = (ClearRegistryResponse) zzc.zza(r2, ClearRegistryResponse.CREATOR);
            enforceNoDataAvail(r2);
            onClearRegistry(r114, r314);
            return true;
        L18:
            Status r115 = (Status) zzc.zza(r2, Status.CREATOR);
            RegistrationResponse r315 = (RegistrationResponse) zzc.zza(r2, RegistrationResponse.CREATOR);
            enforceNoDataAvail(r2);
            onRegisterCredentials(r115, r315);
            return true;
        L19:
            Status r116 = (Status) zzc.zza(r2, Status.CREATOR);
            PendingGetCredentialHandle r316 = (PendingGetCredentialHandle) zzc.zza(r2, PendingGetCredentialHandle.CREATOR);
            enforceNoDataAvail(r2);
            onGetCredential(r116, r316);
            return true;
        }
    }

    void onClearCreationOptions(Status r1, ClearCreationOptionsResponse r2);

    void onClearCredentialState(Status r1, ClearCredentialStateResponse r2);

    void onClearExport(Status r1, ClearExportResponse r2);

    void onClearRegistry(Status r1, ClearRegistryResponse r2);

    void onCreateCredential(Status r1, CreateCredentialResponse r2);

    void onCreateCredentialV2(Status r1, CreateCredentialHandle r2);

    void onExportCredentialsToDeviceSetup(Status r1, ExportCredentialsToDeviceSetupResponse r2);

    void onGetCredential(Status r1, PendingGetCredentialHandle r2);

    void onGetCredentialTransferCapabilities(Status r1, CredentialTransferCapabilities r2);

    void onImportCredentials(Status r1, PendingImportCredentialsHandle r2);

    void onImportCredentialsForDeviceSetup(Status r1, ImportCredentialsForDeviceSetupResponse r2);

    void onRegisterCreationOptions(Status r1, RegisterCreationOptionsResponse r2);

    void onRegisterCredentials(Status r1, RegistrationResponse r2);

    void onRegisterExport(Status r1, RegisterExportResponse r2);

    void onSignalCredentialState(Status r1, SignalCredentialStateResponse r2);
}
