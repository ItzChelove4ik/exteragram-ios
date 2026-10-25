package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogsBotsAdapter$$ExternalSyntheticLambda2 implements Utilities.Callback2 {
    public final /* synthetic */ DialogsBotsAdapter f$0;

    public /* synthetic */ DialogsBotsAdapter$$ExternalSyntheticLambda2(DialogsBotsAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
