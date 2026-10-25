package com.exteragram.messenger.pillstack.core;

import android.content.Context;
import com.exteragram.messenger.pillstack.core.PillRegistry;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import com.exteragram.messenger.pillstack.ui.pills.crypto.UsdPill;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public final /* synthetic */ class PillRegistry$$ExternalSyntheticLambda5 implements PillRegistry.PillCreator {
    public /* synthetic */ PillRegistry$$ExternalSyntheticLambda5() {
    }

    @Override // com.exteragram.messenger.pillstack.core.PillRegistry.PillCreator
    public final BasePill create(Context r1, Theme.ResourcesProvider r2) {
        return new UsdPill(r1, r2);
    }
}
