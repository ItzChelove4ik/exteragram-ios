package org.telegram.messenger.pip.utils;

import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.core.math.MathUtils;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.NotificationBadge;

/* loaded from: classes.dex */
public class PipDuration {
    private int count;
    private long estimated;
    private final SharedPreferences mPrefs;
    private long start;

    public PipDuration(String r5) {
        SharedPreferences r52 = ApplicationLoader.applicationContext.getSharedPreferences("pip_duration_" + r5, 0);
        this.mPrefs = r52;
        this.estimated = r52.getLong("estimated", 400);
        this.count = r52.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
    }

    public long end() {
        if (this.start != 0) goto L5;
        return 0;
    L5:
        long r0 = SystemClock.uptimeMillis() - this.start;
        this.estimated = ((this.estimated * MathUtils.clamp(this.count, 0, 9)) / 10) + (((10 - r4) * r0) / 10);
        this.start = 0;
        this.count++;
        this.mPrefs.edit().putLong("estimated", this.estimated).putInt(NotificationBadge.NewHtcHomeBadger.COUNT, this.count).apply();
        return r0;
    }

    public long estimated() {
        return this.estimated;
    }

    public boolean isStarted() {
        if (this.start == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public float progress() {
        if (this.estimated > 0) goto L5;
        return 0.5f;
    L5:
        return MathUtils.clamp((SystemClock.uptimeMillis() - this.start) / this.estimated, 0.0f, 1.0f);
    }

    public void start() {
        this.start = SystemClock.uptimeMillis();
    }
}
