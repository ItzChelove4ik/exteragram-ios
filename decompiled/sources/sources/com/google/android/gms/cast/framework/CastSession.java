package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzbx;
import com.google.android.gms.internal.cast.zzcj;
import com.google.android.gms.tasks.Task;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes4.dex */
public class CastSession extends Session {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = null;
    private final Context zzc;
    private final Set zzd;
    private final zzal zze;
    private final CastOptions zzf;
    private final zzbx zzg;
    private final com.google.android.gms.cast.framework.media.internal.zzs zzh;
    private com.google.android.gms.cast.zzq zzi;
    private RemoteMediaClient zzj;
    private CastDevice zzk;
    private Cast.ApplicationConnectionResult zzl;
    private zzs zzm;

    static {
        zzb = new Logger("CastSession");
    }

    public CastSession(Context r1, String r2, String r3, CastOptions r4, zzbx r5, com.google.android.gms.cast.framework.media.internal.zzs r6) {
        super(r1, r2, r3);
        this.zzd = new HashSet();
        this.zzc = r1.getApplicationContext();
        this.zzf = r4;
        this.zzg = r5;
        this.zzh = r6;
        this.zze = com.google.android.gms.internal.cast.zzay.zzc(r1, r4, zzn(), new zzv(this, null));
    }

    public static /* synthetic */ Logger zzg() {
        return zzb;
    }

    private final void zzo(Bundle r7) {
        CastDevice r72 = CastDevice.getFromBundle(r7);
        this.zzk = r72;
        if (r72 == null) goto L5;
        com.google.android.gms.cast.zzq r73 = this.zzi;
        if (r73 == null) goto L13;
        r73.zzd();
        this.zzi = null;
    L13:
        zzb.d("Acquiring a connection to Google Play Services for %s", new Object[]{this.zzk});
        CastDevice r74 = (CastDevice) Preconditions.checkNotNull(this.zzk);
        Bundle r1 = new Bundle();
        CastOptions r2 = this.zzf;
        if (r2 != null) goto L16;
        CastMediaOptions r22 = null;
    L17:
        if (r22 != null) goto L19;
        NotificationOptions r3 = null;
    L20:
        boolean r4 = true;
        if (r22 != null) goto L23;
    L25:
        boolean r23 = false;
    L26:
        if (r3 != null) goto L29;
        r4 = false;
    L29:
        r1.putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", r4);
        r1.putBoolean("com.google.android.gms.cast.EXTRA_CAST_REMOTE_CONTROL_NOTIFICATION_ENABLED", r23);
        zzbx r24 = this.zzg;
        r1.putBoolean("com.google.android.gms.cast.EXTRA_CAST_ALWAYS_FOLLOW_SESSION_ENABLED", r24.zzo());
        r1.putBoolean("com.google.android.gms.cast.EXTRA_USE_ROUTE_CONNECTION", r24.zzq());
        Cast.CastOptions.Builder r25 = new Cast.CastOptions.Builder(r74, new zzw(this, null));
        r25.zza(r1);
        com.google.android.gms.cast.zzq r75 = Cast.zza(this.zzc, r25.build());
        r75.zzb(new zzz(this, null));
        this.zzi = r75;
        r75.zzc();
        return;
    L23:
        if (r22.zza() == false) goto L25;
        r23 = true;
        goto L26
    L19:
        r3 = r22.getNotificationOptions();
        goto L20
    L16:
        r22 = r2.getCastMediaOptions();
        goto L17
    L5:
        if (isResuming() == false) goto L8;
        notifyFailedToResumeSession(2153);
        return;
    L8:
        notifyFailedToStartSession(2151);
    }

    public void addCastListener(Cast.Listener r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 == null) goto L6;
        this.zzd.add(r2);
        return;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void end(boolean r5) {
        zzal r0 = this.zze;
        if (r0 != null) goto L11;
        return;
    L11:
        r0.zzj(r5, 0);     // Catch: RemoteException -> L7
    L9:
        notifySessionEnded(0);
        return;
    L7:
        e = move-exception;
        zzb.d(e, "Unable to call %s on %s.", new Object[]{"disconnectFromDevice", zzal.class.getSimpleName()});
        goto L9
    }

    public int getActiveInputState() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L5;
        return -1;
    L5:
        if (r1.zza() == true) goto L7;
        return -1;
    L7:
        return r1.zzm();
    }

    public Cast.ApplicationConnectionResult getApplicationConnectionResult() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzl;
    }

    public ApplicationMetadata getApplicationMetadata() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r1.zza() == true) goto L7;
        return null;
    L7:
        return r1.zzo();
    }

    public String getApplicationStatus() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r1.zza() == true) goto L7;
        return null;
    L7:
        return r1.zzp();
    }

    @Pure
    public CastDevice getCastDevice() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzk;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzj;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        RemoteMediaClient r0 = this.zzj;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.getStreamDuration() - this.zzj.getApproximateStreamPosition();
    }

    public int getStandbyState() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L5;
        return -1;
    L5:
        if (r1.zza() == true) goto L7;
        return -1;
    L7:
        return r1.zzn();
    }

    public double getVolume() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r2 = this.zzi;
        if (r2 != null) goto L5;
        return 0.0d;
    L5:
        if (r2.zza() == true) goto L7;
        return 0.0d;
    L7:
        return r2.zzj();
    }

    public boolean isMute() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.zza() == true) goto L7;
        return false;
    L7:
        if (r1.zzl() == false) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void onResuming(Bundle r1) {
        this.zzk = CastDevice.getFromBundle(r1);
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void onStarting(Bundle r1) {
        this.zzk = CastDevice.getFromBundle(r1);
    }

    public void removeCastListener(Cast.Listener r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 == null) goto L6;
        this.zzd.remove(r2);
        return;
    }

    public void removeMessageReceivedCallbacks(String r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 == null) goto L6;
        r1.zzr(r2);
        return;
    }

    public void requestStatus() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 == null) goto L6;
        r1.zze();
        return;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void resume(Bundle r1) {
        zzo(r1);
    }

    public PendingResult<Status> sendMessage(String r2, String r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L7;
        return PendingResults.immediatePendingResult(new Status(17));
    L7:
        return zzcj.zza(r1.zzf(r2, r3), zzy.zza, zzx.zza);
    }

    public void setMessageReceivedCallbacks(String r2, Cast.MessageReceivedCallback r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.zza() == false) goto L9;
        r1.zzq(r2, r3);
        return;
    }

    public void setMute(boolean r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.zza() == false) goto L9;
        r1.zzk(r2);
        return;
    }

    public void setVolume(double r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        com.google.android.gms.cast.zzq r1 = this.zzi;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.zza() == false) goto L9;
        r1.zzi(r2);
        return;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public void start(Bundle r1) {
        zzo(r1);
    }

    @Override // com.google.android.gms.cast.framework.Session
    public final void zza(Bundle r5) {
        CastDevice r52 = CastDevice.getFromBundle(r5);
        if (r52 != null) goto L5;
        return;
    L5:
        if (r52.equals(this.zzk) == true) goto L34;
        boolean r1 = false;
        if (TextUtils.isEmpty(r52.getFriendlyName()) == true) goto L13;
        CastDevice r0 = this.zzk;
        if (r0 != null) goto L11;
    L12:
        r1 = true;
        goto L13
    L11:
        if (TextUtils.equals(r0.getFriendlyName(), r52.getFriendlyName()) == false) goto L12;
    L13:
        this.zzk = r52;
        Logger r02 = zzb;
        if (true == r1) goto L16;
        String r2 = "unchanged";
    L17:
        r02.d("update to device (%s) with name %s", new Object[]{r52, r2});
        if (r1 == false) goto L35;
        CastDevice r53 = this.zzk;
        if (r53 == null) goto L36;
        com.google.android.gms.cast.framework.media.internal.zzs r03 = this.zzh;
        if (r03 == null) goto L24;
        r03.zzc(r53);
    L24:
        Iterator r54 = new HashSet(this.zzd).iterator();
    L26:
        if (r54.hasNext() == false) goto L28;
        ((Cast.Listener) r54.next()).onDeviceNameChanged();
        goto L26
    L28:
        zzs r4 = this.zzm;
        if (r4 == null) goto L37;
        r4.zzd();
        return;
    L37:
        return;
    L36:
        return;
    L35:
        return;
    L16:
        r2 = "changed";
        goto L17
    }

    public final void zzb(zzs r1) {
        this.zzm = r1;
    }

    public final com.google.android.gms.cast.framework.media.internal.zzs zzc() {
        return this.zzh;
    }

    public final boolean zzd() {
        return this.zzg.zzo();
    }

    public final /* synthetic */ void zze(String r4, Task r5) {
        zzal r0 = this.zze;
        if (r0 != null) goto L26;
        return;
    L26:
    L23:
        e = move-exception;
        zzb.d(e, "Unable to call %s on %s.", new Object[]{"methods", zzal.class.getSimpleName()});
        return;
    L6:
        if (r5.isSuccessful() == false) goto L17;
        Cast.ApplicationConnectionResult r52 = (Cast.ApplicationConnectionResult) r5.getResult();     // Catch: RemoteException -> L23
        this.zzl = r52;     // Catch: RemoteException -> L23
        if (r52.getStatus() == null) goto L14;
        if (r52.getStatus().isSuccess() == false) goto L14;
        zzb.d("%s() -> success result", new Object[]{r4});     // Catch: RemoteException -> L23
        RemoteMediaClient r42 = new RemoteMediaClient(new com.google.android.gms.cast.internal.zzar(null));     // Catch: RemoteException -> L23
        this.zzj = r42;     // Catch: RemoteException -> L23
        r42.zza(this.zzi);     // Catch: RemoteException -> L23
        this.zzj.registerCallback(new zzr(this));     // Catch: RemoteException -> L23
        this.zzj.zzb();     // Catch: RemoteException -> L23
        this.zzh.zza(this.zzj, getCastDevice());     // Catch: RemoteException -> L23
        r0.zzh((ApplicationMetadata) Preconditions.checkNotNull(r52.getApplicationMetadata()), r52.getApplicationStatus(), (String) Preconditions.checkNotNull(r52.getSessionId()), r52.getWasLaunched());     // Catch: RemoteException -> L23
        return;
    L14:
        if (r52.getStatus() == null) goto L21;
        zzb.d("%s() -> failure result", new Object[]{r4});     // Catch: RemoteException -> L23
        r0.zzi(r52.getStatus().getStatusCode());     // Catch: RemoteException -> L23
        return;
    L21:
        r0.zzi(2476);     // Catch: RemoteException -> L23
        return;
    L17:
        Exception r3 = r5.getException();     // Catch: RemoteException -> L23
        if ((r3 instanceof ApiException) == false) goto L21;
        r0.zzi(((ApiException) r3).getStatusCode());     // Catch: RemoteException -> L23
    }

    public final /* synthetic */ void zzf(int r2) {
        this.zzh.zzb(r2);
        com.google.android.gms.cast.zzq r22 = this.zzi;
        if (r22 == null) goto L5;
        r22.zzd();
        this.zzi = null;
    L5:
        this.zzk = null;
        RemoteMediaClient r23 = this.zzj;
        if (r23 == null) goto L8;
        r23.zza(null);
        this.zzj = null;
    L8:
        this.zzl = null;
    }

    public final /* synthetic */ Set zzh() {
        return this.zzd;
    }

    public final /* synthetic */ zzal zzi() {
        return this.zze;
    }

    public final /* synthetic */ com.google.android.gms.cast.zzq zzj() {
        return this.zzi;
    }

    public final /* synthetic */ RemoteMediaClient zzk() {
        return this.zzj;
    }

    public final /* synthetic */ zzs zzl() {
        return this.zzm;
    }
}
