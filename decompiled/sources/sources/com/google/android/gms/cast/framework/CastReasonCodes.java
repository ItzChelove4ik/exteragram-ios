package com.google.android.gms.cast.framework;

import android.os.Bundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* loaded from: classes4.dex */
public class CastReasonCodes {
    public static final int APPLICATION_LAUNCH_ERROR = 10;
    public static final int CASTING_ROUTE_CHANGED = 9;
    public static final int CASTING_STOPPED = 2;
    public static final int CAST_CANCELLED = 11;
    public static final int CAST_INTERNAL_ERROR = 1;
    public static final int CAST_SOCKET_ERROR = 6;
    public static final int CAST_TIMEOUT = 7;
    public static final int NETWORK_ERROR = 8;
    public static final int RECEIVER_APP_NOT_RUNNING = 3;
    public static final int SESSION_RESUME_FAILED = 5;
    public static final int SESSION_START_FAILED = 4;
    public static final int UNKNOWN_REASON = 0;
    private final Map zza;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CastReasonType {
    }

    public CastReasonCodes(Bundle r2) {
        this.zza = com.google.android.gms.internal.cast.zzaz.zza(r2, "com.google.android.gms.cast.MAP_CAST_STATUS_CODES_TO_CAST_REASON_CODES");
    }

    public final int zza(int r2) {
        Map r1 = this.zza;
        if (r1 == null) goto L11;
        Integer r22 = Integer.valueOf(r2);
        if (r1.containsKey(r22) == false) goto L14;
        Integer r12 = (Integer) r1.get(r22);
        if (r12 != null) goto L10;
        return 0;
    L10:
        return r12.intValue();
    L14:
        return 0;
    L11:
        return 0;
    }
}
