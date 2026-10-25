package com.google.android.gms.internal.location;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcelable;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class zzec extends GmsClient {
    private final SimpleArrayMap zzf;
    private final SimpleArrayMap zzg;
    private final SimpleArrayMap zzh;

    public zzec(Context r8, Looper r9, ClientSettings r10, ConnectionCallbacks r11, OnConnectionFailedListener r12) {
        super(r8, r9, 23, r10, r11, r12);
        this.zzf = new SimpleArrayMap();
        this.zzg = new SimpleArrayMap();
        this.zzh = new SimpleArrayMap();
        new SimpleArrayMap();
    }

    private final boolean zzG(Feature r6) {
        Feature[] r5 = getAvailableFeatures();
        if (r5 == null) goto L18;
        int r1 = 0;
    L6:
        if (r1 >= r5.length) goto L11;
        Feature r2 = r5[r1];
        if (r6.getName().equals(r2.getName()) == true) goto L12;
        r1 = r1 + 1;
    L12:
        if (r2 != null) goto L15;
        return false;
    L15:
        if (r2.getVersion() < r6.getVersion()) goto L18;
        return true;
    L11:
        r2 = null;
    L18:
        return false;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if ((r1 instanceof zzu) == false) goto L10;
        return (zzu) r1;
    L10:
        return new zzt(r2);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return zzn.zzp;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 11717000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onConnectionSuspended(int r2) {
        super.onConnectionSuspended(r2);
        SimpleArrayMap r22 = this.zzf;
        monitor-enter(r22);
        r22.clear();     // Catch: Throwable -> L21
        monitor-exit(r22);     // Catch: Throwable -> L21
        SimpleArrayMap r0 = this.zzg;
        monitor-enter(r0);
        r0.clear();     // Catch: Throwable -> L18
        monitor-exit(r0);     // Catch: Throwable -> L18
        SimpleArrayMap r1 = this.zzh;
        monitor-enter(r1);
        r1.clear();     // Catch: Throwable -> L15
        monitor-exit(r1);     // Catch: Throwable -> L15
        return;
    L15:
        th = move-exception;
        throw th;
    L18:
        th = move-exception;
        throw th;
    L21:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }

    public final void zzq(LastLocationRequest r7, TaskCompletionSource r8) {
        if (zzG(zzn.zzj) == false) goto L7;
        ((zzu) getService()).zzq(r7, new zzed(4, null, new zzdj(r8), null, null));
        return;
    L7:
        if (zzG(zzn.zzf) == false) goto L10;
        ((zzu) getService()).zzr(r7, new zzdj(r8));
        return;
    L10:
        r8.setResult(((zzu) getService()).zzs());
    }

    public final void zzt(zzdq r24, LocationRequest r25, TaskCompletionSource r26) {
        ListenerHolder r2 = r24.zza();
        ListenerHolder.ListenerKey r3 = r2.getListenerKey();
        Objects.requireNonNull(r3);
        boolean r4 = zzG(zzn.zzj);
        SimpleArrayMap r5 = this.zzg;
        monitor-enter(r5);
        zzdu r6 = (zzdu) r5.get(r3);     // Catch: Throwable -> L9
        if (r6 == null) goto L11;
        if (r4 == true) goto L11;
        r6.zzc(r2);     // Catch: Throwable -> L9
        zzdu r12 = r6;
        r6 = null;
    L12:
        if (r4 == false) goto L14;
        ((zzu) getService()).zzw(zzed.zzb(r6, r12, r3.toIdString()), r25, new zzdi(null, r26));     // Catch: Throwable -> L9
    L15:
        monitor-exit(r5);     // Catch: Throwable -> L9
        return;
    L14:
        ((zzu) getService()).zzv(new zzeh(1, new zzef(r25, null, false, false, false, false, null, LongCompanionObject.MAX_VALUE), null, r12, null, new zzdc(r26, r12), r3.toIdString()));     // Catch: Throwable -> L9
    L11:
        zzdu r22 = new zzdu(r24);     // Catch: Throwable -> L9
        r5.put(r3, r22);     // Catch: Throwable -> L9
        r12 = r22;
    L9:
        th = move-exception;
        throw th;
    }

    public final void zzw(ListenerHolder.ListenerKey r11, boolean r12, TaskCompletionSource r13) {
        SimpleArrayMap r1 = this.zzg;
        monitor-enter(r1);
        zzdu r6 = (zzdu) r1.remove(r11);     // Catch: Throwable -> L9
        if (r6 != null) goto L11;
        r13.setResult(Boolean.FALSE);     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L11:
        r6.zzg();     // Catch: Throwable -> L9
        if (r12 == true) goto L14;
        r13.setResult(Boolean.TRUE);     // Catch: Throwable -> L9
    L18:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L14:
        if (zzG(zzn.zzj) == false) goto L16;
        zzu r10 = (zzu) getService();     // Catch: Throwable -> L9
        Parcelable.Creator<zzed> r112 = zzed.CREATOR;     // Catch: Throwable -> L9
        int r122 = System.identityHashCode(r6);     // Catch: Throwable -> L9
        StringBuilder r2 = new StringBuilder(String.valueOf(r122).length() + 18);     // Catch: Throwable -> L9
        r2.append("ILocationCallback@");     // Catch: Throwable -> L9
        r2.append(r122);     // Catch: Throwable -> L9
        r10.zzx(zzed.zzb(null, r6, r2.toString()), new zzdi(Boolean.TRUE, r13));     // Catch: Throwable -> L9
        goto L18
    L16:
        ((zzu) getService()).zzv(new zzeh(2, null, null, r6, null, new zzdm(Boolean.TRUE, r13), null));     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }
}
