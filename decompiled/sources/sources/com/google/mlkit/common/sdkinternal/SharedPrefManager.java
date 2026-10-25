package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.firebase.components.Component;
import com.google.firebase.components.Dependency;
import java.util.UUID;

/* loaded from: classes.dex */
public class SharedPrefManager {
    public static final Component<?> COMPONENT = null;
    protected final Context zza;

    static {
        COMPONENT = Component.builder(SharedPrefManager.class).add(Dependency.required(MlKitContext.class)).add(Dependency.required(Context.class)).factory(new zzs()).build();
    }

    public SharedPrefManager(Context r1) {
        this.zza = r1;
    }

    public synchronized String getMlSdkInstanceId() {
        monitor-enter(this);
        String r0 = zza().getString("ml_sdk_instance_id", null);     // Catch: Throwable -> L10
        if (r0 == null) goto L7;
        monitor-exit(this);
        return r0;
    L7:
        String r02 = UUID.randomUUID().toString();     // Catch: Throwable -> L10
        zza().edit().putString("ml_sdk_instance_id", r02).apply();     // Catch: Throwable -> L10
        monitor-exit(this);
        return r02;
    L10:
        th = move-exception;
        throw th;
    }

    public final SharedPreferences zza() {
        return this.zza.getSharedPreferences("com.google.mlkit.internal", 0);
    }
}
