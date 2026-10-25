package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

@Deprecated
/* loaded from: classes4.dex */
public class CastRemoteDisplayClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api.AbstractClientBuilder zzc = null;
    private static final Api zzd = null;
    private final Logger zza;
    private VirtualDisplay zzb;

    static {
        zzv r0 = new zzv();
        zzc = r0;
        zzd = new Api("CastRemoteDisplay.API", r0, com.google.android.gms.cast.internal.zzal.zzd);
    }

    public CastRemoteDisplayClient(Context r4) {
        super(r4, zzd, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zza = new Logger("CastRemoteDisplay");
    }

    public Task<Display> startRemoteDisplay(CastDevice r7, String r8, @CastRemoteDisplay.Configuration int r9, PendingIntent r10) {
        return zze(r7, r8, r9, r10, null);
    }

    public Task<Void> stopRemoteDisplay() {
        return doWrite(TaskApiCall.builder().setMethodKey(8402).run(new zzz(this)).build());
    }

    public final /* synthetic */ void zza() {
        VirtualDisplay r0 = this.zzb;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getDisplay() == null) goto L7;
        Logger r02 = this.zza;
        int r1 = this.zzb.getDisplay().getDisplayId();
        StringBuilder r3 = new StringBuilder(String.valueOf(r1).length() + 27);
        r3.append("releasing virtual display: ");
        r3.append(r1);
        r02.d(r3.toString(), new Object[0]);
    L7:
        VirtualDisplay r03 = this.zzb;
        if (r03 == null) goto L12;
        r03.release();
        this.zzb = null;
        return;
    }

    public final /* synthetic */ Logger zzb() {
        return this.zza;
    }

    public final /* synthetic */ VirtualDisplay zzc() {
        return this.zzb;
    }

    public final /* synthetic */ void zzd(VirtualDisplay r1) {
        this.zzb = r1;
    }

    public final Task zze(CastDevice r9, String r10, @CastRemoteDisplay.Configuration int r11, PendingIntent r12, zzag r13) {
        return doWrite(TaskApiCall.builder().setMethodKey(8401).run(new zzaa(this, r11, r13, r12, r9, r10)).build());
    }
}
