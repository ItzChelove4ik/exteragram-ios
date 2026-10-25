package com.exteragram.messenger.plugins;

import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.hooks.PluginsHooks;

/* loaded from: classes.dex */
public final /* synthetic */ class PluginsController$$ExternalSyntheticLambda8 implements PluginsController.EngineHookCaller {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ PluginsController$$ExternalSyntheticLambda8(String r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.EngineHookCaller
    public final PluginsController.HookResult call(PluginsController.PluginsEngine r2, Object r3, String r4) {
        return PluginsController.$r8$lambda$IWnCzRTqaOHWXjeZmvg6EKw6nq0(this.f$0, this.f$1, r2, (PluginsHooks.PostRequestResult) r3, r4);
    }
}
