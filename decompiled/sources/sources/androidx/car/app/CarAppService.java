package androidx.car.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.car.app.utils.ThreadUtils;
import androidx.car.app.validation.HostValidator;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes3.dex */
public abstract class CarAppService extends Service {
    private AppInfo mAppInfo;
    private final Map<SessionInfo, CarAppBinder> mBinders;
    private HostInfo mHostInfo;

    public static /* synthetic */ void $r8$lambda$BoF8lRhyLvMMyIqziQS6Yiscbqo(CarAppService r1, SessionInfo r2) {
        Map<SessionInfo, CarAppBinder> r0 = r1.mBinders;
        monitor-enter(r0);
        CarAppBinder r12 = r1.mBinders.remove(r2);     // Catch: Throwable -> L7
        if (r12 == null) goto L9;
        r12.onDestroyLifecycle();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    /* renamed from: $r8$lambda$qvBH8jOIRq-ebk_oQomZTKGMWMw, reason: not valid java name */
    public static /* synthetic */ void m632$r8$lambda$qvBH8jOIRqebk_oQomZTKGMWMw(CarAppService r5) {
        Map<SessionInfo, CarAppBinder> r0 = r5.mBinders;
        monitor-enter(r0);
        Iterator<CarAppBinder> r52 = r5.mBinders.values().iterator();     // Catch: Throwable -> L10
    L6:
        if (r52.hasNext() == false) goto L13;
        CarAppBinder r1 = r52.next();     // Catch: Throwable -> L10
        if (Log.isLoggable("CarApp", 3) == false) goto L12;
        Log.d("CarApp", "Executing onAutoDriveEnabled for " + r1.getCurrentSessionInfo());     // Catch: Throwable -> L10
    L12:
        r1.onAutoDriveEnabled();     // Catch: Throwable -> L10
        goto L6
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public CarAppService() {
        this.mBinders = new HashMap();
    }

    public abstract HostValidator createHostValidator();

    @Override // android.app.Service
    public final void dump(FileDescriptor r3, PrintWriter r4, String[] r5) {
        super.dump(r3, r4, r5);
        int r32 = r5.length;
        int r42 = 0;
    L3:
        if (r42 >= r32) goto L8;
        if ("AUTO_DRIVE".equals(r5[r42]) == false) goto L7;
        ThreadUtils.runOnMain(new CarAppService$$ExternalSyntheticLambda0(this));
    L7:
        r42 = r42 + 1;
        goto L3
    }

    public AppInfo getAppInfo() {
        if (this.mAppInfo != null) goto L6;
        this.mAppInfo = AppInfo.create(this);
    L6:
        return this.mAppInfo;
    }

    public final HostInfo getHostInfo() {
        return this.mHostInfo;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent r4) {
        if (SessionInfoIntentEncoder.containsSessionInfo(r4) == false) goto L5;
        SessionInfo r42 = SessionInfoIntentEncoder.decode(r4);
    L6:
        Map<SessionInfo, CarAppBinder> r0 = this.mBinders;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (this.mBinders.containsKey(r42) == true) goto L13;
        this.mBinders.put(r42, new CarAppBinder(this, r42));     // Catch: Throwable -> L11
    L13:
        CarAppBinder r3 = this.mBinders.get(r42);     // Catch: Throwable -> L11
        Objects.requireNonNull(r3);     // Catch: Throwable -> L11
        CarAppBinder r32 = r3;     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r32;
    L5:
        r42 = SessionInfo.DEFAULT_SESSION_INFO;
        goto L6
    }

    public Session onCreateSession() {
        throw new RuntimeException("Please override and implement CarAppService#onCreateSession(SessionInfo).");
    }

    public Session onCreateSession(SessionInfo r1) {
        return onCreateSession();
    }

    @Override // android.app.Service
    public void onDestroy() {
        Map<SessionInfo, CarAppBinder> r0 = this.mBinders;
        monitor-enter(r0);
        Iterator<CarAppBinder> r1 = this.mBinders.values().iterator();     // Catch: Throwable -> L8
    L6:
        if (r1.hasNext() == false) goto L10;
        r1.next().destroy();     // Catch: Throwable -> L8
        goto L6
    L10:
        this.mBinders.clear();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent r5) {
        if (Log.isLoggable("CarApp", 3) == false) goto L6;
        Log.d("CarApp", "onUnbind intent: " + r5);
    L6:
        if (SessionInfoIntentEncoder.containsSessionInfo(r5) == false) goto L8;
        SessionInfo r52 = SessionInfoIntentEncoder.decode(r5);
    L9:
        ThreadUtils.runOnMain(new CarAppService$$ExternalSyntheticLambda1(this, r52));
        if (Log.isLoggable("CarApp", 3) == false) goto L14;
        Log.d("CarApp", "onUnbind completed");
        return true;
    L14:
        return true;
    L8:
        r52 = SessionInfo.DEFAULT_SESSION_INFO;
        goto L9
    }

    public void setHostInfo(HostInfo r1) {
        this.mHostInfo = r1;
    }
}
