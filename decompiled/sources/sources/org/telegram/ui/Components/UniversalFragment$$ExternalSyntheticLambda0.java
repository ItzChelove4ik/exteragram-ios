package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class UniversalFragment$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ UniversalFragment f$0;

    public /* synthetic */ UniversalFragment$$ExternalSyntheticLambda0(UniversalFragment r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
