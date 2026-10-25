package com.google.android.gms.wearable;

import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.internal.zzao;
import com.google.android.gms.wearable.internal.zzbf;
import com.google.android.gms.wearable.internal.zzev;
import com.google.android.gms.wearable.internal.zzez;
import com.google.android.gms.wearable.internal.zzfx;
import com.google.android.gms.wearable.internal.zzgm;
import com.google.android.gms.wearable.internal.zzi;
import com.google.android.gms.wearable.internal.zziu;
import java.util.List;

/* loaded from: classes5.dex */
final class zzaa extends zzez {
    final /* synthetic */ WearableListenerService zza;
    private volatile int zzb;

    public /* synthetic */ zzaa(WearableListenerService r1, zzz r2) {
        this.zza = r1;
        this.zzb = -1;
    }

    public static final /* synthetic */ void zzm(zzev r2, Task r3) {
        if (r3.isSuccessful() == false) goto L6;
        zzo(r2, true, (byte[]) r3.getResult());
        return;
    L6:
        Log.e("WearableLS", "Failed to resolve future, sending null response", r3.getException());
        zzo(r2, false, null);
    }

    private final boolean zzn(Runnable r3, String r4, Object r5) {
        if (Log.isLoggable("WearableLS", 3) == false) goto L5;
        Log.d("WearableLS", String.format("%s: %s %s", new Object[]{r4, WearableListenerService.zza(this.zza).toString(), r5}));
    L5:
        int r42 = Binder.getCallingUid();
        if (r42 != this.zzb) goto L9;
    L16:
        Object r52 = WearableListenerService.zze(this.zza);
        monitor-enter(r52);
        WearableListenerService r2 = this.zza;     // Catch: Throwable -> L22
        if (WearableListenerService.zzf(r2) == false) goto L24;
        monitor-exit(r52);     // Catch: Throwable -> L22
        return false;
    L24:
        WearableListenerService.zzc(r2).post(r3);     // Catch: Throwable -> L22
        monitor-exit(r52);     // Catch: Throwable -> L22
        return true;
    L22:
        th = move-exception;
        throw th;
    L9:
        if (zziu.zza(this.zza).zzb("com.google.android.wearable.app.cn") == false) goto L14;
        if (UidVerifier.uidHasPackageName(this.zza, r42, "com.google.android.wearable.app.cn") == false) goto L14;
        this.zzb = r42;
    L14:
        if (UidVerifier.isGooglePlayServicesUid(this.zza, r42) == false) goto L30;
        this.zzb = r42;
        goto L16
    L30:
        Log.e("WearableLS", "Caller is not GooglePlayServices; caller UID: " + r42);
        return false;
    }

    private static final void zzo(zzev r0, boolean r1, byte[] r2) {
        r0.zzd(r1, r2);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        Log.e("WearableLS", "Failed to send a response back", e);
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzb(zzbf r3) {
        zzn(new zzy(this, r3), "onChannelEvent", r3);
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzc(zzao r3) {
        zzn(new zzv(this, r3), "onConnectedCapabilityChanged", r3);
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzd(List r3) {
        zzn(new zzu(this, r3), "onConnectedNodes", r3);
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zze(DataHolder r5) {
        if (zzn(new zzq(this, r5), "onDataItemChanged", String.valueOf(r5) + ", rows=" + r5.getCount()) == true) goto L12;
        r5.close();
        return;
    L12:
        return;
    L7:
        th = move-exception;
        r5.close();
        throw th;
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzf(zzi r3) {
        zzn(new zzx(this, r3), "onEntityUpdate", r3);
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzg(zzfx r3) {
        zzn(new zzr(this, r3), "onMessageReceived", r3);
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzh(com.google.android.gms.wearable.internal.zzl r3) {
        zzn(new zzw(this, r3), "onNotificationReceived", r3);
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzi(zzgm r3) {
        zzn(new zzs(this, r3), "onPeerConnected", r3);
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzj(zzgm r3) {
        zzn(new zzt(this, r3), "onPeerDisconnected", r3);
    }

    public final /* synthetic */ void zzk(zzfx r4, zzev r5) {
        Task<byte[]> r42 = this.zza.onRequest(r4.getSourceNodeId(), r4.getPath(), r4.getData());
        if (r42 != null) goto L6;
        zzo(r5, false, null);
        return;
    L6:
        r42.addOnCompleteListener(new zzo(this, r5, null));
    }

    @Override // com.google.android.gms.wearable.internal.zzfa
    public final void zzl(zzfx r3, zzev r4) {
        zzn(new zzp(this, r3, r4, null), "onRequestReceived", r3);
    }
}
