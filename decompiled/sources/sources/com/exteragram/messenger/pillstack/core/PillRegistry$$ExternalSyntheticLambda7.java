package com.exteragram.messenger.pillstack.core;

import android.content.Context;
import com.exteragram.messenger.pillstack.core.PillRegistry;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import com.exteragram.messenger.pillstack.ui.pills.system.ProxyPill;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public final /* synthetic */ class PillRegistry$$ExternalSyntheticLambda7 implements PillRegistry.PillCreator {
    public /* synthetic */ PillRegistry$$ExternalSyntheticLambda7() {
    }

    @Override // com.exteragram.messenger.pillstack.core.PillRegistry.PillCreator
    public final BasePill create(Context r1, Theme.ResourcesProvider r2) {
        return new ProxyPill(r1, r2);
    }
}
