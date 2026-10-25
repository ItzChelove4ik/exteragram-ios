package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.firebase.crashlytics.internal.Logger;

/* loaded from: classes5.dex */
class BatteryState {
    private final Float level;
    private final boolean powerConnected;

    private BatteryState(Float r1, boolean r2) {
        this.powerConnected = r2;
        this.level = r1;
    }

    public static BatteryState get(Context r4) {
        boolean r0 = false;
        Float r1 = null;
        Intent r42 = r4.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));     // Catch: IllegalStateException -> L7
        if (r42 == null) goto L10;
        r0 = isPowerConnected(r42);     // Catch: IllegalStateException -> L7
        r1 = getLevel(r42);     // Catch: IllegalStateException -> L7
    L10:
        return new BatteryState(r1, r0);
    L7:
        e = move-exception;
        Logger.getLogger().e("An error occurred getting battery state.", e);
        goto L10
    }

    private static Float getLevel(Intent r3) {
        int r0 = r3.getIntExtra("level", -1);
        int r32 = r3.getIntExtra("scale", -1);
        if (r0 == (-1)) goto L8;
        if (r32 != (-1)) goto L7;
        return null;
    L7:
        return Float.valueOf(r0 / r32);
    L8:
        return null;
    }

    private static boolean isPowerConnected(Intent r2) {
        int r22 = r2.getIntExtra("status", -1);
        if (r22 != (-1)) goto L6;
        return false;
    L6:
        if (r22 != 2) goto L8;
        return true;
    L8:
        if (r22 == 5) goto L13;
        return false;
    L13:
        return true;
    }

    public Float getBatteryLevel() {
        return this.level;
    }

    public int getBatteryVelocity() {
        if (this.powerConnected == true) goto L5;
        return 1;
    L5:
        if (this.level != null) goto L8;
        return 1;
    L8:
        if (r4.floatValue() >= 0.99d) goto L11;
        return 2;
    L11:
        return 3;
    }
}
