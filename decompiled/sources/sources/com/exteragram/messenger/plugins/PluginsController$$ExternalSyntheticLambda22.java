package com.exteragram.messenger.plugins;

import com.exteragram.messenger.plugins.PluginsController;
import org.telegram.tgnet.TLObject;

/* loaded from: classes.dex */
public final /* synthetic */ class PluginsController$$ExternalSyntheticLambda22 implements PluginsController.EngineHookCaller {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ PluginsController$$ExternalSyntheticLambda22(String r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.EngineHookCaller
    public final PluginsController.HookResult call(PluginsController.PluginsEngine r2, Object r3, String r4) {
        return PluginsController.m1557$r8$lambda$eTddhbteklHVrAGcUqAfEmORgE(this.f$0, this.f$1, r2, (TLObject) r3, r4);
    }
}
