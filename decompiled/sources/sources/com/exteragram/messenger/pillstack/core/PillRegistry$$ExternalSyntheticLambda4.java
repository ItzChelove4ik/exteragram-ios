package com.exteragram.messenger.pillstack.core;

import android.content.Context;
import com.exteragram.messenger.pillstack.core.PillRegistry;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import com.exteragram.messenger.pillstack.ui.pills.crypto.BtcPill;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public final /* synthetic */ class PillRegistry$$ExternalSyntheticLambda4 implements PillRegistry.PillCreator {
    public /* synthetic */ PillRegistry$$ExternalSyntheticLambda4() {
    }

    @Override // com.exteragram.messenger.pillstack.core.PillRegistry.PillCreator
    public final BasePill create(Context r1, Theme.ResourcesProvider r2) {
        return new BtcPill(r1, r2);
    }
}
