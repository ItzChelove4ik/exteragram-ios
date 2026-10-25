package com.google.android.gms.internal.wallet;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentDataRequest;

/* loaded from: classes5.dex */
public final class zzy extends GmsClient {
    private final Context zze;
    private final int zzf;
    private final String zzg;
    private final int zzh;
    private final boolean zzi;

    public zzy(Context r8, Looper r9, ClientSettings r10, GoogleApiClient.ConnectionCallbacks r11, GoogleApiClient.OnConnectionFailedListener r12, int r13, int r14, boolean r15) {
        super(r8, r9, 4, r10, r11, r12);
        this.zze = r8;
        this.zzf = r13;
        Account r7 = r10.getAccount();
        if (r7 == null) goto L5;
        String r72 = r7.name;
    L6:
        this.zzg = r72;
        this.zzh = r14;
        this.zzi = r15;
        return;
    L5:
        r72 = null;
        goto L6
    }

    private final Bundle zzu() {
        String r0 = this.zze.getPackageName();
        Bundle r1 = new Bundle();
        r1.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", this.zzf);
        r1.putBoolean("com.google.android.gms.wallet.EXTRA_USING_ANDROID_PAY_BRAND", this.zzi);
        r1.putString("androidPackageName", r0);
        String r02 = this.zzg;
        if (TextUtils.isEmpty(r02) == true) goto L5;
        r1.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(r02, "com.google"));
    L5:
        r1.putInt("com.google.android.gms.wallet.EXTRA_THEME", this.zzh);
        return r1;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
        if ((r1 instanceof zzo) == false) goto L10;
        return (zzo) r1;
    L10:
        return new zzo(r2);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return com.google.android.gms.wallet.zzk.zzi;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12600000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.wallet.service.BIND";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean requiresAccount() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzs(IsReadyToPayRequest r2, TaskCompletionSource r3) {
        zzu r0 = new zzu(r3);
        ((zzo) getService()).zzf(r2, zzu(), r0);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        Log.e("WalletClientImpl", "RemoteException during isReadyToPay", e);
        r0.zzc(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzt(PaymentDataRequest r4, TaskCompletionSource r5) {
        Bundle r0 = zzu();
        r0.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzx r1 = new zzx(r5);
        ((zzo) getService()).zzg(r4, r0, r1);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        Log.e("WalletClientImpl", "RemoteException getting payment data", e);
        r1.zzf(Status.RESULT_INTERNAL_ERROR, null, Bundle.EMPTY);
    }
}
