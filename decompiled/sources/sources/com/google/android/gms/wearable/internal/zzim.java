package com.google.android.gms.wearable.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public final class zzim extends GmsClient {
    private final ExecutorService zze;
    private final zzff zzf;
    private final zzff zzg;
    private final zzff zzh;
    private final zzff zzi;
    private final zzff zzj;
    private final zzff zzk;
    private final zzff zzl;
    private final zzff zzm;
    private final zzff zzn;
    private final zzff zzo;
    private final zziu zzp;
    private final File zzq;

    public zzim(Context r10, Looper r11, GoogleApiClient.ConnectionCallbacks r12, GoogleApiClient.OnConnectionFailedListener r13, ClientSettings r14) {
        com.google.android.gms.internal.wearable.zzh.zza();
        ExecutorService r0 = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
        zziu r1 = zziu.zza(r10);
        super(r10, r11, 14, r14, r12, r13);
        this.zzf = new zzff();
        this.zzg = new zzff();
        this.zzh = new zzff();
        this.zzi = new zzff();
        this.zzj = new zzff();
        this.zzk = new zzff();
        this.zzl = new zzff();
        this.zzm = new zzff();
        this.zzn = new zzff();
        this.zzo = new zzff();
        this.zze = (ExecutorService) Preconditions.checkNotNull(r0);
        this.zzp = r1;
        File r102 = new File(new File(r10.getFilesDir(), "wearos_assets"), "streamtmp");
        r102.mkdirs();
        File[] r9 = r102.listFiles();
        if (r9 == null) goto L7;
        int r112 = r9.length;
        int r122 = 0;
    L5:
        if (r122 >= r112) goto L7;
        r9[r122].delete();
        r122 = r122 + 1;
    L7:
        this.zzq = r102;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void connect(BaseGmsClient.ConnectionProgressReportCallbacks r7) {
        if (requiresGooglePlayServices() == false) goto L20;
    L18:
        super.connect(r7);
        return;
    L20:
        Bundle r2 = getContext().getPackageManager().getApplicationInfo("com.google.android.wearable.app.cn", 128).metaData;     // Catch: PackageManager.NameNotFoundException -> L16
        if (r2 == null) goto L7;
        int r22 = r2.getInt("com.google.android.wearable.api.version", 0);     // Catch: PackageManager.NameNotFoundException -> L16
    L9:
        if (r22 >= 8600000) goto L18;
        Log.w("WearableClient", "The Wear OS app is out of date. Requires API version 8600000 but found " + r22);     // Catch: PackageManager.NameNotFoundException -> L16
        Context r1 = getContext();     // Catch: PackageManager.NameNotFoundException -> L16
        Context r23 = getContext();     // Catch: PackageManager.NameNotFoundException -> L16
        Intent r4 = new Intent("com.google.android.wearable.app.cn.UPDATE_ANDROID_WEAR").setPackage("com.google.android.wearable.app.cn");     // Catch: PackageManager.NameNotFoundException -> L16
        if (r23.getPackageManager().resolveActivity(r4, 65536) != null) goto L14;
        r4 = new Intent("android.intent.action.VIEW", Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.wearable.app.cn").build());     // Catch: PackageManager.NameNotFoundException -> L16
    L14:
        triggerNotAvailable(r7, 6, PendingIntent.getActivity(r1, 0, r4, com.google.android.gms.internal.wearable.zzd.zza));     // Catch: PackageManager.NameNotFoundException -> L16
        return;
    L7:
        r22 = 0;
    L16:
        triggerNotAvailable(r7, 16, null);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
        if ((r1 instanceof zzfb) == false) goto L10;
        return (zzfb) r1;
    L10:
        return new zzfb(r2);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return com.google.android.gms.wearable.zze.zzo;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 8600000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.wearable.internal.IWearableService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.wearable.BIND";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServicePackage() {
        if (this.zzp.zzb("com.google.android.wearable.app.cn") == false) goto L5;
        return "com.google.android.wearable.app.cn";
    L5:
        return "com.google.android.gms";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onPostInitHandler(int r4, IBinder r5, Bundle r6, int r7) {
        if (Log.isLoggable("WearableClient", 2) == false) goto L5;
        Log.v("WearableClient", "onPostInitHandler: statusCode " + r4);
    L5:
        if (r4 != 0) goto L7;
        this.zzf.zzb(r5);
        this.zzg.zzb(r5);
        this.zzh.zzb(r5);
        this.zzj.zzb(r5);
        this.zzk.zzb(r5);
        this.zzl.zzb(r5);
        this.zzm.zzb(r5);
        this.zzn.zzb(r5);
        this.zzo.zzb(r5);
        this.zzi.zzb(r5);
        r4 = 0;
    L7:
        super.onPostInitHandler(r4, r5, r6, r7);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean requiresGooglePlayServices() {
        if (this.zzp.zzb("com.google.android.wearable.app.cn") == true) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }
}
