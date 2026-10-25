package com.exteragram.messenger.plugins;

import com.exteragram.messenger.plugins.PluginsController;
import org.telegram.messenger.SendMessagesHelper;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginsController$$ExternalSyntheticLambda16 implements PluginsController.EngineHookCaller {
    public final /* synthetic */ int f$0;

    public /* synthetic */ PluginsController$$ExternalSyntheticLambda16(int r1) {
        this.f$0 = r1;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.EngineHookCaller
    public final PluginsController.HookResult call(PluginsController.PluginsEngine r1, Object r2, String r3) {
        return PluginsController.$r8$lambda$MmT7vCRk4Nquw9rR9nIOFmGcmH0(this.f$0, r1, (SendMessagesHelper.SendMessageParams) r2, r3);
    }
}
