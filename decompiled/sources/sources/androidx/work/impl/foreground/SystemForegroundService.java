package androidx.work.impl.foreground;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;
import androidx.work.impl.foreground.SystemForegroundDispatcher;

/* loaded from: classes4.dex */
public class SystemForegroundService extends LifecycleService implements SystemForegroundDispatcher.Callback {
    private static final String TAG = null;
    private static SystemForegroundService sForegroundService;
    SystemForegroundDispatcher mDispatcher;
    private boolean mIsShutdown;
    NotificationManager mNotificationManager;

    public static class Api29Impl {
        public static void startForeground(Service r0, int r1, Notification r2, int r3) {
            r0.startForeground(r1, r2, r3);
        }
    }

    public static class Api31Impl {
        public static void startForeground(Service r1, int r2, Notification r3, int r4) {
            r1.startForeground(r2, r3, r4);     // Catch: SecurityException -> L5 ForegroundServiceStartNotAllowedException -> L7
            return;
        L7:
            e = move-exception;
            Logger.get().warning(SystemForegroundService.access$000(), "Unable to start foreground service", e);
            return;
        L5:
            e = move-exception;
            Logger.get().warning(SystemForegroundService.access$000(), "Unable to start foreground service", e);
        }
    }

    static {
        TAG = Logger.tagWithPrefix("SystemFgService");
        sForegroundService = null;
    }

    public SystemForegroundService() {
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    private void initializeDispatcher() {
        this.mNotificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
        SystemForegroundDispatcher r0 = new SystemForegroundDispatcher(getApplicationContext());
        this.mDispatcher = r0;
        r0.setCallback(this);
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public void cancelNotification(int r1) {
        this.mNotificationManager.cancel(r1);
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public void notify(int r1, Notification r2) {
        this.mNotificationManager.notify(r1, r2);
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        sForegroundService = this;
        initializeDispatcher();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mDispatcher.onDestroy();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public int onStartCommand(Intent r3, int r4, int r5) {
        super.onStartCommand(r3, r4, r5);
        if (this.mIsShutdown == false) goto L5;
        Logger.get().info(TAG, "Re-initializing SystemForegroundService after a request to shut-down.");
        this.mDispatcher.onDestroy();
        initializeDispatcher();
        this.mIsShutdown = false;
    L5:
        if (r3 == null) goto L9;
        this.mDispatcher.onStartCommand(r3, r5);
        return 3;
    L9:
        return 3;
    }

    @Override // android.app.Service
    public void onTimeout(int r3) {
        if (Build.VERSION.SDK_INT < 35) goto L5;
        return;
    L5:
        this.mDispatcher.onTimeout(r3, 2048);
    }

    public void onTimeout(int r1, int r2) {
        this.mDispatcher.onTimeout(r1, r2);
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public void startForeground(int r3, int r4, Notification r5) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L7;
        Api31Impl.startForeground(this, r3, r5, r4);
        return;
    L7:
        if (r0 < 29) goto L10;
        Api29Impl.startForeground(this, r3, r5, r4);
        return;
    L10:
        startForeground(r3, r5);
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public void stop(int r5) {
        this.mIsShutdown = true;
        Logger.get().debug(TAG, "Shutting down.");
        if (Build.VERSION.SDK_INT < 26) goto L5;
        stopForeground(true);
    L5:
        sForegroundService = null;
        stopSelf(r5);
    }
}
