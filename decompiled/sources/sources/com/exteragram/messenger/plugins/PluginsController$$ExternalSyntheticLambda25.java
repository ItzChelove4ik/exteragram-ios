package com.exteragram.messenger.plugins;

import com.exteragram.messenger.plugins.PluginsController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class PluginsController$$ExternalSyntheticLambda25 implements PluginsController.EngineHookCaller {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ PluginsController$$ExternalSyntheticLambda25(String r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.exteragram.messenger.plugins.PluginsController.EngineHookCaller
    public final PluginsController.HookResult call(PluginsController.PluginsEngine r2, Object r3, String r4) {
        return PluginsController.m1553$r8$lambda$RcBK0orj8QEYVxVVjeONxndSuU(this.f$0, this.f$1, r2, (TLRPC.Updates) r3, r4);
    }
}
