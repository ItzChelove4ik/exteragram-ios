package com.exteragram.messenger.pillstack.core;

import android.content.Context;
import com.exteragram.messenger.pillstack.core.PillRegistry;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import com.exteragram.messenger.pillstack.ui.pills.crypto.GramPill;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public final /* synthetic */ class PillRegistry$$ExternalSyntheticLambda3 implements PillRegistry.PillCreator {
    public /* synthetic */ PillRegistry$$ExternalSyntheticLambda3() {
    }

    @Override // com.exteragram.messenger.pillstack.core.PillRegistry.PillCreator
    public final BasePill create(Context r1, Theme.ResourcesProvider r2) {
        return new GramPill(r1, r2);
    }
}
