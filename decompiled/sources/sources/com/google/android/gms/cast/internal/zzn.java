package com.google.android.gms.cast.internal;

import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzn extends GoogleApi {
    private static final Api.ClientKey zza = null;
    private static final Api.AbstractClientBuilder zzb = null;
    private static final Api zzc = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zza = r0;
        zze r1 = new zze();
        zzb = r1;
        zzc = new Api("CastApi.API", r1, r0);
    }

    public zzn(Context r4) {
        super(r4, zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task zza(String[] r3, String r4, List r5) {
        return doWrite(TaskApiCall.builder().setMethodKey(8423).run(new zzm(this, r3, r4, null)).build());
    }

    public final Task zzb(String[] r3) {
        return doRead(TaskApiCall.builder().run(new zzj(this, r3)).setFeatures(new Feature[]{com.google.android.gms.cast.zzaq.zzd}).setAutoResolveMissingFeatures(false).setMethodKey(8425).build());
    }

    public final Task zzc(String[] r3) {
        return doRead(TaskApiCall.builder().run(new zzk(this, r3)).setFeatures(new Feature[]{com.google.android.gms.cast.zzaq.zzg}).setAutoResolveMissingFeatures(false).setMethodKey(8426).build());
    }

    public final Task zzd(String[] r3) {
        return doRead(TaskApiCall.builder().run(new zzl(this, r3)).setFeatures(new Feature[]{com.google.android.gms.cast.zzaq.zzh}).setAutoResolveMissingFeatures(false).setMethodKey(8427).build());
    }
}
