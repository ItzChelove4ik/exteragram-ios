package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public abstract class SessionProvider {
    private final Context zza;
    private final String zzb;
    private final zzbi zzc;

    public SessionProvider(Context r3, String r4) {
        this.zzc = new zzbi(this, null);
        this.zza = ((Context) Preconditions.checkNotNull(r3)).getApplicationContext();
        this.zzb = Preconditions.checkNotEmpty(r4);
    }

    public abstract Session createSession(String r1);

    public final String getCategory() {
        return this.zzb;
    }

    public final Context getContext() {
        return this.zza;
    }

    public abstract boolean isSessionRecoverable();

    public final IBinder zza() {
        return this.zzc;
    }
}
