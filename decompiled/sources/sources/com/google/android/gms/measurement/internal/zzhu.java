package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R$string;

/* loaded from: classes.dex */
public abstract class zzhu {
    public static String zza(Context r2) {
        return r2.getResources().getResourcePackageName(R$string.common_google_play_services_unknown_issue);
    L5:
        return r2.getPackageName();
    }

    public static final String zzb(String r1, Resources r2, String r3) {
        int r12 = r2.getIdentifier("google_app_id", "string", r3);
        if (r12 != 0) goto L8;
        return null;
    L8:
        return r2.getString(r12);
    L7:
        return null;
    }

    public static final Boolean zzc(String r1, Resources r2, String r3) {
        int r12 = r2.getIdentifier("google_analytics_force_disable_updates", "bool", r3);
        if (r12 != 0) goto L8;
        return null;
    L8:
        return Boolean.valueOf(r2.getBoolean(r12));
    L7:
        return null;
    }
}
