package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public class LifecycleActivity {
    private final Object zza;

    public LifecycleActivity(Activity r2) {
        Preconditions.checkNotNull(r2, "Activity must not be null");
        this.zza = r2;
    }

    public final boolean zza() {
        return this.zza instanceof FragmentActivity;
    }

    public final boolean zzb() {
        return this.zza instanceof Activity;
    }

    public final Activity zzc() {
        return (Activity) this.zza;
    }

    public final FragmentActivity zzd() {
        return (FragmentActivity) this.zza;
    }
}
