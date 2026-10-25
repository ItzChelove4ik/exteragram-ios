package com.exteragram.messenger.preferences;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;

/* loaded from: classes4.dex */
public final /* synthetic */ class BasePreferencesActivity$$ExternalSyntheticLambda6 implements Utilities.Callback2 {
    public final /* synthetic */ BasePreferencesActivity f$0;

    public /* synthetic */ BasePreferencesActivity$$ExternalSyntheticLambda6(BasePreferencesActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
