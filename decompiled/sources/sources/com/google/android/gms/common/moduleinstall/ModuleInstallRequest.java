package com.google.android.gms.common.moduleinstall;

import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class ModuleInstallRequest {
    private final List zaa;
    private final Executor zac;

    public static class Builder {
        private final List zaa;
        private Executor zac;

        public Builder() {
            this.zaa = new ArrayList();
        }

        public Builder addApi(OptionalModuleApi r2) {
            this.zaa.add(r2);
            return this;
        }

        public ModuleInstallRequest build() {
            return new ModuleInstallRequest(this.zaa, null, this.zac, true, null);
        }
    }

    public /* synthetic */ ModuleInstallRequest(List r1, InstallStatusListener r2, Executor r3, boolean r4, byte[] r5) {
        Preconditions.checkNotNull(r1, "APIs must not be null.");
        Preconditions.checkArgument(!r1.isEmpty(), "APIs must not be empty.");
        if (r3 == null) goto L5;
        Preconditions.checkNotNull(r2, "Listener must not be null when listener executor is set.");
    L5:
        this.zaa = r1;
        this.zac = r3;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<OptionalModuleApi> getApis() {
        return this.zaa;
    }

    public InstallStatusListener getListener() {
        return null;
    }

    public Executor getListenerExecutor() {
        return this.zac;
    }
}
