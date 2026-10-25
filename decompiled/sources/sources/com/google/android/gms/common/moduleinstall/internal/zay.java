package com.google.android.gms.common.moduleinstall.internal;

import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class zay extends GoogleApi implements ModuleInstallClient {
    private static final Api.ClientKey zac = null;
    private static final Api.AbstractClientBuilder zad = null;
    private static final Api zae = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zac = r0;
        zai r1 = new zai();
        zad = r1;
        zae = new Api("ModuleInstall.API", r1, r0);
    }

    public zay(Context r4) {
        super(r4, zae, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public static final ApiFeatureRequest zad(boolean r4, OptionalModuleApi... r5) {
        Preconditions.checkNotNull(r5, "Requested APIs must not be null.");
        int r0 = r5.length;
        int r1 = 0;
        if (r0 <= 0) goto L5;
        boolean r2 = true;
    L6:
        Preconditions.checkArgument(r2, "Please provide at least one OptionalModuleApi.");
    L7:
        if (r1 >= r0) goto L10;
        Preconditions.checkNotNull(r5[r1], "Requested API must not be null.");
        r1 = r1 + 1;
        goto L7
    L10:
        return ApiFeatureRequest.zaa(Arrays.asList(r5), r4);
    L5:
        r2 = false;
        goto L6
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task<ModuleAvailabilityResponse> areModulesAvailable(OptionalModuleApi... r4) {
        ApiFeatureRequest r42 = zad(false, r4);
        if (r42.getApiFeatures().isEmpty() == true) goto L5;
        TaskApiCall.Builder r1 = TaskApiCall.builder();
        r1.setFeatures(new Feature[]{zap.zaa});
        r1.setMethodKey(27301);
        r1.setAutoResolveMissingFeatures(false);
        r1.run(new zax(this, r42));
        return doRead(r1.build());
    L5:
        return Tasks.forResult(new ModuleAvailabilityResponse(true, 0));
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task<ModuleInstallResponse> installModules(ModuleInstallRequest r3) {
        ApiFeatureRequest r0 = ApiFeatureRequest.fromModuleInstallRequest(r3);
        r3.getListener();
        r3.getListenerExecutor();
        if (r0.getApiFeatures().isEmpty() == true) goto L5;
        TaskApiCall.Builder r32 = TaskApiCall.builder();
        r32.setFeatures(new Feature[]{zap.zaa});
        r32.setAutoResolveMissingFeatures(true);
        r32.setMethodKey(27304);
        r32.run(new zar(this, r0));
        return doRead(r32.build());
    L5:
        return Tasks.forResult(new ModuleInstallResponse(0));
    }
}
