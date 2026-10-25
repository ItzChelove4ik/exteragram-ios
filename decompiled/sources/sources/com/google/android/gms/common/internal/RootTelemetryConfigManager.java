package com.google.android.gms.common.internal;

/* loaded from: classes4.dex */
public final class RootTelemetryConfigManager {
    private static RootTelemetryConfigManager zza;
    private static final RootTelemetryConfiguration zzb = null;
    private RootTelemetryConfiguration zzc;

    static {
        zzb = new RootTelemetryConfiguration(0, false, false, 0, 0);
    }

    private RootTelemetryConfigManager() {
    }

    public static synchronized RootTelemetryConfigManager getInstance() {
        monitor-enter(RootTelemetryConfigManager.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new RootTelemetryConfigManager();     // Catch: Throwable -> L7
    L9:
        RootTelemetryConfigManager r1 = zza;     // Catch: Throwable -> L7
        monitor-exit(RootTelemetryConfigManager.class);
        return r1;
    }

    public RootTelemetryConfiguration getConfig() {
        return this.zzc;
    }

    public final synchronized void zza(RootTelemetryConfiguration r3) {
        monitor-enter(this);
        if (r3 != null) goto L9;
        this.zzc = zzb;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L9:
        RootTelemetryConfiguration r0 = this.zzc;     // Catch: Throwable -> L7
        if (r0 != null) goto L12;
    L16:
        this.zzc = r3;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L12:
        if (r0.getVersion() < r3.getVersion()) goto L16;
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
