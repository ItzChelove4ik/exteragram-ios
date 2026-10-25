package com.exteragram.messenger.plugins.ui;

import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.ui.PluginsActivity;
import com.exteragram.messenger.plugins.ui.components.PluginCell;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginsActivity$createPluginItem$1$$ExternalSyntheticLambda4 implements Utilities.Callback {
    public final /* synthetic */ PluginsActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ Plugin f$2;
    public final /* synthetic */ PluginCell f$3;

    public /* synthetic */ PluginsActivity$createPluginItem$1$$ExternalSyntheticLambda4(PluginsActivity r1, boolean r2, Plugin r3, PluginCell r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        PluginsActivity.AnonymousClass1.$r8$lambda$B78fwne4P6pAvuzj85JvNnMgcJ4(this.f$0, this.f$1, this.f$2, this.f$3, (String) r4);
    }
}
