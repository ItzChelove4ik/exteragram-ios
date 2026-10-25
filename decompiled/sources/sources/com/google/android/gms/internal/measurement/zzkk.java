package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.util.Pair;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzkk extends GoogleApi {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        Pair.create(zzkm.zza(0), Tasks.forResult(null));
    }

    public zzkk(Context r4) {
        super(r4, zzjx.zza, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task zza(String r3, int r4, String[] r5, byte[] r6) {
        return doRead(TaskApiCall.builder().run(new zzki(r3, 0, r5, null)).build());
    }

    public final Task zzb(String r3, String r4, String r5) {
        return doRead(TaskApiCall.builder().run(new zzkb(r3, _UrlKt.FRAGMENT_ENCODE_SET, null)).build());
    }

    public final Task zzc(String r3) {
        return doRead(TaskApiCall.builder().run(new zzkc(r3)).build());
    }

    public final Task zzd(zzme r4) {
        Preconditions.checkNotNull(r4);
        return doRead(TaskApiCall.builder().run(new zzkd(r4)).setFeatures(new Feature[]{zzjn.zza}).setAutoResolveMissingFeatures(false).build()).continueWithTask(MoreExecutors.directExecutor(), new zzke(this, r4));
    }

    public final Task zze() {
        return doRead(TaskApiCall.builder().run(new zzkf(this)).setFeatures(new Feature[]{zzjn.zzi}).setAutoResolveMissingFeatures(false).build());
    }

    public final Task zzf(zzpm r6) {
        ListenerHolder r62 = registerListener(r6, zzku.class.getSimpleName());
        String r1 = ProcessUtils.getMyProcessName();
        if (r1 != null) goto L5;
        String r0 = "__PH_INTERNAL__NO_PROCESS__";
    L6:
        zzkg r12 = new zzkg(this, r0, r62);
        zzkh r02 = zzkh.zza;
        return doRegisterEventListener(RegistrationMethods.builder().withHolder(r62).register(r12).unregister(r02).setFeatures(new Feature[]{zzjn.zzd}).setAutoResolveMissingFeatures(false).build());
    L5:
        int r2 = r1.length() + 1;
        int r03 = System.identityHashCode(zzku.class);
        StringBuilder r4 = new StringBuilder(r2 + String.valueOf(r03).length());
        r4.append(r1);
        r4.append("|");
        r4.append(r03);
        r0 = r4.toString();
        goto L6
    }
}
