package com.exteragram.messenger.preferences;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UItem;

/* loaded from: classes4.dex */
public final /* synthetic */ class BasePreferencesActivity$$ExternalSyntheticLambda7 implements Utilities.Callback5 {
    public final /* synthetic */ BasePreferencesActivity f$0;

    public /* synthetic */ BasePreferencesActivity$$ExternalSyntheticLambda7(BasePreferencesActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback5
    public final void run(Object r1, Object r2, Object r3, Object r4, Object r5) {
        this.f$0.onClick((UItem) r1, (View) r2, ((Integer) r3).intValue(), ((Float) r4).floatValue(), ((Float) r5).floatValue());
    }
}
