package com.google.android.gms.cast.framework;

import android.content.Context;
import android.preference.PreferenceManager;

/* loaded from: classes4.dex */
public final class zzbe {
    public static void zza(Context r2) {
        PreferenceManager.getDefaultSharedPreferences(r2).edit().putBoolean("googlecast-introOverlayShown", true).apply();
    }
}
