package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Display;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzfg;
import com.google.android.gms.internal.cast.zzfk;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

@SuppressLint({"ForegroundServiceType"})
@Deprecated
/* loaded from: classes4.dex */
public abstract class CastRemoteDisplayLocalService extends Service {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = null;
    private static final int zzc = 0;
    private static final Object zzd = null;
    private static final AtomicBoolean zze = null;

    @SuppressLint({"StaticFieldLeak"})
    private static CastRemoteDisplayLocalService zzv;
    private String zzf;
    private WeakReference zzg;
    private zzak zzh;
    private NotificationSettings zzi;
    private Notification zzj;
    private boolean zzk;
    private PendingIntent zzl;
    private CastDevice zzm;
    private Display zzn;
    private Context zzo;
    private ServiceConnection zzp;
    private Handler zzq;
    private MediaRouter zzr;
    private boolean zzs;
    private CastRemoteDisplayClient zzt;
    private final MediaRouter.Callback zzu;
    private final IBinder zzw;

    public interface Callbacks {
        void onRemoteDisplayMuteStateChanged(boolean r1);

        void onRemoteDisplaySessionEnded(CastRemoteDisplayLocalService r1);

        void onRemoteDisplaySessionError(Status r1);

        void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService r1);

        void onServiceCreated(CastRemoteDisplayLocalService r1);
    }

    public static final class NotificationSettings {
        private Notification zza;
        private PendingIntent zzb;
        private String zzc;
        private String zzd;

        public static final class Builder {
            private final NotificationSettings zza;

            public Builder() {
                this.zza = new NotificationSettings(null);
            }

            public NotificationSettings build() {
                NotificationSettings r2 = this.zza;
                if (r2.zza() == null) goto L18;
                if (TextUtils.isEmpty(r2.zze()) == true) goto L7;
                f$$ExternalSyntheticBUOutline1.m("notificationTitle requires using the default notification");
                return null;
            L7:
                if (TextUtils.isEmpty(r2.zzg()) == true) goto L9;
                f$$ExternalSyntheticBUOutline1.m("notificationText requires using the default notification");
                return null;
            L9:
                if (r2.zzc() == null) goto L26;
                f$$ExternalSyntheticBUOutline1.m("notificationPendingIntent requires using the default notification");
                return null;
            L26:
                return r2;
            L18:
                if (TextUtils.isEmpty(r2.zze()) == false) goto L26;
                if (TextUtils.isEmpty(r2.zzg()) == false) goto L26;
                if (r2.zzc() != null) goto L26;
                f$$ExternalSyntheticBUOutline1.m("At least an argument must be provided");
                return null;
            }

            public Builder setNotification(Notification r2) {
                this.zza.zzb(r2);
                return this;
            }

            public Builder setNotificationPendingIntent(PendingIntent r2) {
                this.zza.zzd(r2);
                return this;
            }

            public Builder setNotificationText(String r2) {
                this.zza.zzh(r2);
                return this;
            }

            public Builder setNotificationTitle(String r2) {
                this.zza.zzf(r2);
                return this;
            }
        }

        private NotificationSettings() {
            throw null;
        }

        public /* synthetic */ NotificationSettings(NotificationSettings r1, byte[] r2) {
            this.zza = r1.zza;
            this.zzb = r1.zzb;
            this.zzc = r1.zzc;
            this.zzd = r1.zzd;
        }

        public /* synthetic */ NotificationSettings(byte[] r1) {
        }

        public final /* synthetic */ Notification zza() {
            return this.zza;
        }

        public final /* synthetic */ void zzb(Notification r1) {
            this.zza = r1;
        }

        public final /* synthetic */ PendingIntent zzc() {
            return this.zzb;
        }

        public final /* synthetic */ void zzd(PendingIntent r1) {
            this.zzb = r1;
        }

        public final /* synthetic */ String zze() {
            return this.zzc;
        }

        public final /* synthetic */ void zzf(String r1) {
            this.zzc = r1;
        }

        public final /* synthetic */ String zzg() {
            return this.zzd;
        }

        public final /* synthetic */ void zzh(String r1) {
            this.zzd = r1;
        }
    }

    public static class Options {

        @CastRemoteDisplay.Configuration
        int zza;

        public Options() {
            this.zza = 2;
        }

        @CastRemoteDisplay.Configuration
        public int getConfigPreset() {
            return this.zza;
        }

        public void setConfigPreset(@CastRemoteDisplay.Configuration int r1) {
            this.zza = r1;
        }
    }

    static {
        zzb = new Logger("CastRDLocalService");
        zzc = R.id.cast_notification_id;
        zzd = new Object();
        zze = new AtomicBoolean(false);
    }

    public CastRemoteDisplayLocalService() {
        this.zzs = false;
        this.zzu = new zzab(this);
        this.zzw = new zzaj(this);
    }

    public static CastRemoteDisplayLocalService getInstance() {
        Object r0 = zzd;
        monitor-enter(r0);
        CastRemoteDisplayLocalService r1 = zzv;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static void setDebugEnabled() {
        zzb.zzb(true);
    }

    public static void startService(Context r7, Class<? extends CastRemoteDisplayLocalService> r8, String r9, CastDevice r10, NotificationSettings r11, Callbacks r12) {
        startServiceWithOptions(r7, r8, r9, r10, new Options(), r11, r12);
    }

    public static void startServiceWithOptions(Context r8, Class<? extends CastRemoteDisplayLocalService> r9, String r10, CastDevice r11, Options r12, NotificationSettings r13, Callbacks r14) {
        Logger r0 = zzb;
        r0.d("Starting Service", new Object[0]);
        Object r1 = zzd;
        monitor-enter(r1);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zzv == null) goto L9;
        r0.w("An existing service had not been stopped before starting one", new Object[0]);     // Catch: Throwable -> L7
        zzv(true);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r1);     // Catch: Throwable -> L7
        ComponentName r15 = new ComponentName(r8, r9);     // Catch: PackageManager.NameNotFoundException -> L30
        ServiceInfo r16 = r8.getPackageManager().getServiceInfo(r15, 128);     // Catch: PackageManager.NameNotFoundException -> L30
        if (r16 != null) goto L13;
    L17:
        Preconditions.checkNotNull(r8, "activityContext is required.");
        Preconditions.checkNotNull(r9, "serviceClass is required.");
        Preconditions.checkNotNull(r10, "applicationId is required.");
        Preconditions.checkNotNull(r11, "device is required.");
        Preconditions.checkNotNull(r12, "options is required.");
        Preconditions.checkNotNull(r13, "notificationSettings is required.");
        Preconditions.checkNotNull(r14, "callbacks is required.");
        if (r13.zza() != null) goto L25;
        if (r13.zzc() != null) goto L25;
        f$$ExternalSyntheticBUOutline1.m("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
        return;
    L25:
        if (zze.getAndSet(true) == false) goto L28;
        r0.e("Service is already being started, startService has been called twice", new Object[0]);
        return;
    L28:
        Intent r02 = new Intent(r8, r9);
        r8.startService(r02);
        ConnectionTracker.getInstance().bindService(r8, r02, new zzad(r10, r11, r12, r13, r8, r14), 64);
        return;
    L13:
        if (r16.exported == false) goto L17;
        throw new IllegalStateException("The service must not be exported, verify the manifest configuration");     // Catch: PackageManager.NameNotFoundException -> L30
    L30:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Service not found, did you forget to configure it in the manifest?", e);
    }

    public static void stopService() {
        zzv(false);
    }

    public static /* synthetic */ void zzb(boolean r0) {
        zzv(false);
    }

    public static /* synthetic */ Logger zzi() {
        return zzb;
    }

    public static /* synthetic */ Object zzj() {
        return zzd;
    }

    public static /* synthetic */ AtomicBoolean zzk() {
        return zze;
    }

    public static /* synthetic */ CastRemoteDisplayLocalService zzt() {
        return zzv;
    }

    private final void zzu(boolean r3) {
        zzx("Stopping Service");
        Preconditions.checkMainThread("stopServiceInstanceInternal must be called on the main thread");
        if (r3 == true) goto L8;
        if (this.zzr == null) goto L8;
        zzx("Setting default route");
        MediaRouter r32 = this.zzr;
        r32.selectRoute(r32.getDefaultRoute());
    L8:
        if (this.zzh == null) goto L10;
        zzx("Unregistering notification receiver");
        unregisterReceiver(this.zzh);
    L10:
        zzx("stopRemoteDisplaySession");
        zzx("stopRemoteDisplay");
        this.zzt.stopRemoteDisplay().addOnCompleteListener(new zzai(this));
        Callbacks r33 = (Callbacks) this.zzg.get();
        if (r33 == null) goto L13;
        r33.onRemoteDisplaySessionEnded(this);
    L13:
        onDismissPresentation();
        zzx("Stopping the remote display Service");
        stopForeground(true);
        stopSelf();
        if (this.zzr == null) goto L16;
        Preconditions.checkMainThread("CastRemoteDisplayLocalService calls must be done on the main thread");
        zzx("removeMediaRouterCallback");
        this.zzr.removeCallback(this.zzu);
    L16:
        Context r34 = this.zzo;
        ServiceConnection r0 = this.zzp;
        if (r34 == null) goto L22;
        if (r0 == null) goto L22;
        ConnectionTracker.getInstance().unbindService(r34, r0);     // Catch: IllegalArgumentException -> L21
    L21:
        zzx("No need to unbind service, already unbound");
    L22:
        this.zzp = null;
        this.zzo = null;
        this.zzf = null;
        this.zzj = null;
        this.zzn = null;
    }

    private static void zzv(boolean r4) {
        Logger r0 = zzb;
        r0.d("Stopping Service", new Object[0]);
        zze.set(false);
        Object r1 = zzd;
        monitor-enter(r1);
        CastRemoteDisplayLocalService r3 = zzv;     // Catch: Throwable -> L9
        if (r3 != null) goto L11;
        r0.e("Service is already being stopped", new Object[0]);     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L11:
        zzv = null;     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        if (r3.zzq != null) goto L16;
        return;
    L16:
        if (Looper.myLooper() == Looper.getMainLooper()) goto L19;
        r3.zzq.post(new zzae(r3, r4));
        return;
    L19:
        r3.zzu(r4);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private final Notification zzw(boolean r5) {
        zzx("createDefaultNotification");
        String r0 = this.zzi.zze();
        String r1 = this.zzi.zzg();
        if (r5 == false) goto L5;
        int r52 = R.string.cast_notification_connected_message;
        int r2 = R.drawable.cast_ic_notification_on;
    L7:
        if (TextUtils.isEmpty(r0) == false) goto L10;
        r0 = (String) getPackageManager().getApplicationLabel(getApplicationInfo());
    L10:
        if (TextUtils.isEmpty(r1) == false) goto L12;
        r1 = getString(r52, new Object[]{this.zzm.getFriendlyName()});
    L12:
        NotificationCompat.Builder r53 = new NotificationCompat.Builder(this, "cast_remote_display_local_service").setContentTitle(r0).setContentText(r1).setContentIntent(this.zzi.zzc()).setSmallIcon(r2).setOngoing(true);
        String r02 = getString(R.string.cast_notification_disconnect);
        if (this.zzl != null) goto L16;
        Preconditions.checkNotNull(this.zzo, "activityContext is required.");
        Intent r12 = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
        r12.setPackage(this.zzo.getPackageName());
        this.zzl = zzfg.zzb(this, 0, r12, 201326592);
    L16:
        return r53.addAction(android.R.drawable.ic_menu_close_clear_cancel, r02, this.zzl).build();
    L5:
        r52 = R.string.cast_notification_connecting_message;
        r2 = R.drawable.cast_ic_notification_connecting;
        goto L7
    }

    private final void zzx(String r2) {
        zzb.d("[Instance: %s] %s", new Object[]{this, r2});
    }

    public Display getCastRemoteDisplay() {
        return this.zzn;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        zzx("onBind");
        return this.zzw;
    }

    @Override // android.app.Service
    public void onCreate() {
        zzx("onCreate");
        super.onCreate();
        zzfk r0 = new zzfk(getMainLooper());
        this.zzq = r0;
        r0.postDelayed(new zzac(this), 100);
        if (this.zzt != null) goto L6;
        this.zzt = CastRemoteDisplay.getClient(this);
    L6:
        if (PlatformVersion.isAtLeastO() == true) goto L8;
        return;
    L8:
        NotificationManager r02 = (NotificationManager) getSystemService(NotificationManager.class);
        CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline1.m();
        NotificationChannel r4 = CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m("cast_remote_display_local_service", getString(R.string.cast_notification_default_channel_name), 2);
        r4.setShowBadge(false);
        r02.createNotificationChannel(r4);
    }

    public abstract void onCreatePresentation(Display r1);

    public abstract void onDismissPresentation();

    @Override // android.app.Service
    public int onStartCommand(Intent r1, int r2, int r3) {
        zzx("onStartCommand");
        this.zzs = true;
        return 2;
    }

    @Deprecated
    public void updateNotificationSettings(NotificationSettings r3) {
        if (PlatformVersion.isAtLeastS() == false) goto L5;
        return;
    L5:
        Preconditions.checkNotNull(r3, "notificationSettings is required.");
        Preconditions.checkNotNull(this.zzq, "Service is not ready yet.");
        this.zzq.post(new zzaf(this, r3));
    }

    public final /* synthetic */ void zza(boolean r1) {
        zzu(r1);
    }

    public final /* synthetic */ void zzc(NotificationSettings r3) {
        Preconditions.checkMainThread("updateNotificationSettingsInternal must be called on the main thread");
        if (this.zzi != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("No current notification settings to update");
        return;
    L5:
        if (this.zzk == true) goto L7;
        Preconditions.checkNotNull(r3.zza(), "notification is required.");
        Notification r32 = r3.zza();
        this.zzj = r32;
        this.zzi.zzb(r32);
    L21:
        startForeground(zzc, this.zzj);
        return;
    L7:
        if (r3.zza() == null) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("Current mode is default notification, notification attribute must not be provided");
        return;
    L9:
        if (r3.zzc() == null) goto L12;
        this.zzi.zzd(r3.zzc());
    L12:
        if (TextUtils.isEmpty(r3.zze()) == true) goto L15;
        this.zzi.zzf(r3.zze());
    L15:
        if (TextUtils.isEmpty(r3.zzg()) == true) goto L17;
        this.zzi.zzh(r3.zzg());
    L17:
        this.zzj = zzw(true);
        goto L21
    }

    public final /* synthetic */ void zzd(Display r2) {
        if (r2 != null) goto L5;
        zzb.e("Cast Remote Display session created without display", new Object[0]);
        return;
    L5:
        this.zzn = r2;
        if (this.zzk == false) goto L8;
        Notification r22 = zzw(true);
        this.zzj = r22;
        startForeground(zzc, r22);
    L8:
        Callbacks r23 = (Callbacks) this.zzg.get();
        if (r23 == null) goto L11;
        r23.onRemoteDisplaySessionStarted(this);
    L11:
        Preconditions.checkNotNull(this.zzn, "display is required.");
        onCreatePresentation(this.zzn);
    }

    public final /* synthetic */ boolean zze(String r10, CastDevice r11, Options r12, NotificationSettings r13, Context r14, ServiceConnection r15, Callbacks r16) {
        zzx("startRemoteDisplaySession");
        Preconditions.checkMainThread("Starting the Cast Remote Display must be done on the main thread");
        Object r1 = zzd;
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (zzv == null) goto L11;
        zzb.w("An existing service had not been stopped before starting one", new Object[0]);     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return false;
    L11:
        zzv = this;     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        this.zzg = new WeakReference(r16);
        this.zzf = r10;
        this.zzm = r11;
        this.zzo = r14;
        this.zzp = r15;
        if (this.zzr != null) goto L16;
        this.zzr = MediaRouter.getInstance(getApplicationContext());
    L16:
        Preconditions.checkNotNull(this.zzf, "applicationId is required.");
        MediaRouteSelector r102 = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(this.zzf)).build();
        zzx("addMediaRouterCallback");
        this.zzr.addCallback(r102, this.zzu, 4);
        this.zzj = r13.zza();
        this.zzh = new zzak(null);
        IntentFilter r103 = new IntentFilter();
        r103.addAction("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
        r103.addAction("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
        boolean r0 = PlatformVersion.isAtLeastT();
        zzak r3 = this.zzh;
        if (r0 == false) goto L19;
        registerReceiver(r3, r103, 4);
    L20:
        NotificationSettings r104 = new NotificationSettings(r13, null);
        this.zzi = r104;
        if (r104.zza() != null) goto L23;
        this.zzk = true;
        this.zzj = zzw(false);
    L24:
        startForeground(zzc, this.zzj);
        zzx("startRemoteDisplay");
        Intent r105 = new Intent("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
        Preconditions.checkNotNull(this.zzo, "activityContext is required.");
        r105.setPackage(this.zzo.getPackageName());
        PendingIntent r7 = zzfg.zzb(this, 0, r105, 67108864);
        zzag r8 = new zzag(this);
        Preconditions.checkNotNull(this.zzf, "applicationId is required.");
        this.zzt.zze(r11, this.zzf, r12.getConfigPreset(), r7, r8).addOnCompleteListener(new zzah(this));
        Callbacks r106 = (Callbacks) this.zzg.get();
        if (r106 != null) goto L27;
        return true;
    L27:
        r106.onServiceCreated(this);
        return true;
    L23:
        this.zzk = false;
        this.zzj = this.zzi.zza();
        goto L24
    L19:
        ContextCompat.registerReceiver(this, r3, r103, 2);
        goto L20
    }

    public final /* synthetic */ void zzf() {
        Callbacks r2 = (Callbacks) this.zzg.get();
        if (r2 == null) goto L5;
        r2.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
    L5:
        stopService();
    }

    public final /* synthetic */ void zzg(String r1) {
        zzx(r1);
    }

    public final /* synthetic */ void zzh(String r2) {
        zzb.e("[Instance: %s] %s", new Object[]{this, "The local service has not been been started, stopping it"});
    }

    public final /* synthetic */ WeakReference zzl() {
        return this.zzg;
    }

    public final /* synthetic */ CastDevice zzm() {
        return this.zzm;
    }

    public final /* synthetic */ void zzn(Display r1) {
        this.zzn = null;
    }

    public final /* synthetic */ Context zzo() {
        return this.zzo;
    }

    public final /* synthetic */ void zzp(Context r1) {
        this.zzo = null;
    }

    public final /* synthetic */ ServiceConnection zzq() {
        return this.zzp;
    }

    public final /* synthetic */ void zzr(ServiceConnection r1) {
        this.zzp = null;
    }

    public final /* synthetic */ boolean zzs() {
        return this.zzs;
    }
}
