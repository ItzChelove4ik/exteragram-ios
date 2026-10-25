package org.telegram.ui.Components;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

/* loaded from: classes3.dex */
public abstract class ForegroundDetector implements Application.ActivityLifecycleCallbacks {
    private static ForegroundDetector Instance;
    private long enterBackgroundTime;
    private CopyOnWriteArrayList<Listener> listeners;
    private int refs;
    private boolean wasInBackground;

    public interface Listener {
        void onBecameBackground();

        void onBecameForeground();
    }

    static {
    }

    public ForegroundDetector(Application r3) {
        this.wasInBackground = true;
        this.enterBackgroundTime = 0;
        this.listeners = new CopyOnWriteArrayList();
        Instance = this;
        r3.registerActivityLifecycleCallbacks(this);
    }

    public static ForegroundDetector getInstance() {
        return Instance;
    }

    public void addListener(Listener r1) {
        this.listeners.add(r1);
    }

    public boolean isBackground() {
        if (this.refs != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isForeground() {
        if (this.refs <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isWasInBackground(boolean r5) {
        if (r5 == false) goto L7;
        if ((SystemClock.elapsedRealtime() - this.enterBackgroundTime) >= 200) goto L7;
        this.wasInBackground = false;
    L7:
        return this.wasInBackground;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity r1, Bundle r2) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity r1, Bundle r2) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity r5) {
        int r52 = this.refs + 1;
        this.refs = r52;
        if (r52 == 1) goto L5;
        return;
    L5:
        if ((SystemClock.elapsedRealtime() - this.enterBackgroundTime) >= 200) goto L8;
        this.wasInBackground = false;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        FileLog.d("switch to foreground");
    L10:
        Iterator<Listener> r4 = this.listeners.iterator();
    L12:
        if (r4.hasNext() == false) goto L25;
        r4.next().onBecameForeground();     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity r4) {
        int r42 = this.refs - 1;
        this.refs = r42;
        if (r42 != 0) goto L15;
        this.enterBackgroundTime = SystemClock.elapsedRealtime();
        this.wasInBackground = true;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("switch to background");
    L7:
        Iterator<Listener> r3 = this.listeners.iterator();
    L9:
        if (r3.hasNext() == false) goto L22;
        r3.next().onBecameBackground();     // Catch: Exception -> L13
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L9
    L22:
        return;
    }

    public void removeListener(Listener r1) {
        this.listeners.remove(r1);
    }

    public void resetBackgroundVar() {
        this.wasInBackground = false;
    }
}
