package com.google.android.gms.common.moduleinstall;

import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public interface ModuleInstallClient {
    Task<ModuleAvailabilityResponse> areModulesAvailable(OptionalModuleApi... r1);

    Task<ModuleInstallResponse> installModules(ModuleInstallRequest r1);
}
